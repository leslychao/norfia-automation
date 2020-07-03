package com.vkim.skyeng.service;

import com.vkim.skyeng.SyncState;
import com.vkim.skyeng.dto.AppConfigDto;
import com.vkim.skyeng.dto.CompanyDto;
import com.vkim.skyeng.dto.StatementDto;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SkyEngIntegrationService {

  @Getter
  @Setter
  @EqualsAndHashCode(onlyExplicitlyIncluded = true)
  @ToString
  public static class ExternalCompany {

    @EqualsAndHashCode.Include
    private Long companyId;
    private String companyName;
    private String paymentType;
    private Long dealId;
    private Long contractId;
    private Long inn;

  }

  @Getter
  @Setter
  @EqualsAndHashCode
  @ToString
  public static class Contract implements Comparable<Contract> {

    private Long id;
    private Manager currentSalesManager;
    private Manager administrativeManager;
    private Manager supportManager;

    public int getPriority() {
      int priority = 0;
      if (currentSalesManager != null) {
        priority++;
      }
      if (administrativeManager != null) {
        priority++;
      }
      if (supportManager != null) {
        priority++;
      }
      return priority;
    }

    @Override
    public int compareTo(Contract o) {
      if (this.getPriority() == o.getPriority()) {
        return 0;
      } else if (this.getPriority() < o.getPriority()) {
        return -1;
      } else {
        return 1;
      }
    }
  }

  @Getter
  @Setter
  @EqualsAndHashCode
  @ToString
  public static class Manager {

    private ManagerType managerType;
    private Long id;
    private String name;
    private String surname;
  }

  public enum ManagerType {
    KAM("Current Sales Manager"), MC("Support Manager"), AM("Administrative Manager");

    private String name;

    ManagerType(String name) {
      this.name = name;
    }
  }

  private CompanyService companyService;
  private StatementService statementService;
  private DictionaryService dictionaryService;

  @Autowired
  public SkyEngIntegrationService(CompanyService companyService,
      StatementService statementService, DictionaryService dictionaryService) {
    this.companyService = companyService;
    this.statementService = statementService;
    this.dictionaryService = dictionaryService;
  }

  static String doGet(URI uri) {
    HttpGet httpGet = new HttpGet(uri);
    httpGet.addHeader("Cookie",
        "session_global=83c95424f81ebee8febe30e48e8aacb8; session_cabinet=cc4f51977c549dd7fbcc622c6d420dee");
    try (CloseableHttpClient client = HttpClients
        .createDefault(); CloseableHttpResponse response = client.execute(httpGet)) {
      if (response.getStatusLine().getStatusCode() != 200) {
        throw new RuntimeException(
            "HTTP ошибка с кодом: " + response.getStatusLine().getStatusCode()
                + " Detail message: " +
                response.getStatusLine().getReasonPhrase());
      }
      HttpEntity entity = response.getEntity();
      String result = null;
      if (entity != null) {
        result = EntityUtils.toString(entity);
      }
      return result;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static void setInn(ExternalCompany externalCompany) {
    String stringEntity;
    try {
      stringEntity = doGet(new URIBuilder(
          "https://backend.skyeng.ru/api/companies/" + externalCompany.getCompanyId()
              + "/pipedrive/organizations/").build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    JSONObject jsonObject = new JSONObject(stringEntity);
    externalCompany.setInn(jsonObject.getJSONObject("data").getLong("tin"));
  }

  public static List<ExternalCompany> getCompanies(String companyName) {
    List<ExternalCompany> companies = new ArrayList<>();
    String stringEntity;
    try {
      stringEntity = doGet(new URIBuilder("https://backend.skyeng.ru/api/companies/by-name/")
          .setParameter("name", companyName).build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    JSONObject jsonObject = new JSONObject(stringEntity);
    JSONArray jsonArray = (JSONArray) jsonObject.get("data");
    for (int i = 0; i < jsonArray.length(); i++) {
      ExternalCompany externalCompany = new ExternalCompany();
      externalCompany.setCompanyId(jsonArray.getJSONObject(i).getLong("companyId"));
      externalCompany.setCompanyName(jsonArray.getJSONObject(i).getString("companyName"));
      if (!jsonArray.getJSONObject(i).isNull("paymentType")) {
        externalCompany.setPaymentType(jsonArray.getJSONObject(i).getString("paymentType"));
      }
      externalCompany.setDealId(jsonArray.getJSONObject(i).getLong("dealId"));
      if (!jsonArray.getJSONObject(i).isNull("contractId")) {
        externalCompany.setContractId(jsonArray.getJSONObject(i).getLong("contractId"));
      }
      setInn(externalCompany);
      if (!"special_offer".equals(externalCompany.getPaymentType())) {
        companies.add(externalCompany);
      }
    }
    return companies;
  }

  public static Contract getContract(Long id) {
    if (id == null) {
      return new Contract();
    }
    String contractStringEntity;
    try {
      contractStringEntity = doGet(new URIBuilder(
          "https://backend.skyeng.ru/api/companies/deals/contracts/" + id
              + "/").build());
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }
    if (contractStringEntity == null) {
      throw new RuntimeException("contract not found id: " + id);
    }
    JSONObject jsonObject = new JSONObject(contractStringEntity);
    Contract contract = new Contract();
    contract.setId(jsonObject.getJSONObject("data").getLong("id"));
    if (!jsonObject.getJSONObject("data").isNull("currentSalesManager")) {
      Manager currentSalesManager = new Manager();
      currentSalesManager.setId(
          jsonObject.getJSONObject("data").getJSONObject("currentSalesManager")
              .getJSONObject("general").getLong("id"));
      currentSalesManager.setName(
          jsonObject.getJSONObject("data").getJSONObject("currentSalesManager")
              .getJSONObject("general").getString("name"));
      if (!jsonObject.getJSONObject("data").getJSONObject("currentSalesManager")
          .getJSONObject("general").isNull("surname")) {
        currentSalesManager.setSurname(
            jsonObject.getJSONObject("data").getJSONObject("currentSalesManager")
                .getJSONObject("general").getString("surname"));
      }
      currentSalesManager.setManagerType(ManagerType.KAM);
      contract.setCurrentSalesManager(currentSalesManager);
    }
    if (!jsonObject.getJSONObject("data").isNull("administrativeManager")) {
      Manager administrativeManager = new Manager();
      administrativeManager.setId(
          jsonObject.getJSONObject("data").getJSONObject("administrativeManager")
              .getJSONObject("general").getLong("id"));
      administrativeManager.setName(
          jsonObject.getJSONObject("data").getJSONObject("administrativeManager")
              .getJSONObject("general").getString("name"));
      administrativeManager.setSurname(
          jsonObject.getJSONObject("data").getJSONObject("administrativeManager")
              .getJSONObject("general").getString("surname"));
      administrativeManager.setManagerType(ManagerType.KAM);
      contract.setAdministrativeManager(administrativeManager);
    }
    if (!jsonObject.getJSONObject("data").isNull("supportManager")) {
      Manager supportManager = new Manager();
      supportManager.setId(
          jsonObject.getJSONObject("data").getJSONObject("supportManager").getJSONObject("general")
              .getLong("id"));
      supportManager.setName(
          jsonObject.getJSONObject("data").getJSONObject("supportManager").getJSONObject("general")
              .getString("name"));
      if (!jsonObject.getJSONObject("data").getJSONObject("supportManager").getJSONObject("general")
          .isNull("surname")) {
        supportManager.setSurname(
            jsonObject.getJSONObject("data").getJSONObject("supportManager")
                .getJSONObject("general")
                .getString("surname"));
      }
      supportManager.setManagerType(ManagerType.KAM);
      contract.setSupportManager(supportManager);
    }
    return contract;
  }

  private String extractPaymentNumber(String paymentDetails) {
    Pattern pattern = Pattern
        .compile("6[\\d]{3}", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    Matcher matcher = pattern.matcher(paymentDetails);
    if (matcher.find()) {
      return matcher.group();
    }
    return null;
  }

  private String extractManagerTag(Contract contract) {
    return null;
  }

  public void syncCompanies(AppConfigDto appConfigDto) {
    List<StatementDto> statements = statementService.findByPackId(appConfigDto.getPackId());
    statements.stream()
        .filter(statementDto -> SyncState.READY_TO_SEND == statementDto.getSyncState())
        .forEach(statementDto -> {
          List<ExternalCompany> externalCompanies = getCompanies(statementDto.getShortName());
          Map<ExternalCompany, Contract> companyContractMap = externalCompanies.stream()
              .collect(Collectors.groupingBy(externalCompany -> externalCompany,
                  Collectors
                      .mapping(externalCompany -> getContract(externalCompany.getContractId()),
                          Collectors.collectingAndThen(Collectors.maxBy(Contract::compareTo),
                              Optional::get))));
          StringBuilder stringBuilder = new StringBuilder();
          if (companyContractMap.size() != 1) {
            stringBuilder.append("- company not found \n");
            statementDto.setLog(stringBuilder.toString());
            statementDto.setSyncState(SyncState.SYNC_FAILED);
            statementService.update(statementDto);
            return;
          }
          companyContractMap.forEach((externalCompany, contract) -> {
            if (contract.getId() == null) {
              stringBuilder.append("- contract id is empty \n");
            }
            CompanyDto companyDto = new CompanyDto();
            companyDto.setExternalCompanyId(externalCompany.getCompanyId());
            companyDto.setCompanyName(statementDto.getShortName());
            companyDto.setManagers(
                contract.getSupportManager() + " " + contract.getCurrentSalesManager());
            companyDto.setCredit(statementDto.getCredit());
            String paymentNumber = extractPaymentNumber(statementDto.getPaymentDetails());
            if (paymentNumber == null) {
              stringBuilder.append("- can't extract payment number \n");
            }
            companyDto.setPaymentNumber(paymentNumber);
            boolean innMatched = statementDto.getInn().equals(externalCompany.getInn().toString());
            if (!innMatched) {
              stringBuilder.append("- inn not matched \n");
            }
            if (stringBuilder.length() != 0) {
              statementDto.setLog(stringBuilder.toString());
            }
            statementDto.setSyncState(SyncState.SYNC_SUCCESS);
            statementService.update(statementDto);
            companyService.update(companyDto);
          });
        });
  }
}
