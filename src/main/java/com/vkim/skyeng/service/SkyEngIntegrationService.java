package com.vkim.skyeng.service;

import static java.util.stream.Collectors.groupingBy;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SkyEngIntegrationService {

  @Getter
  @Setter
  @EqualsAndHashCode
  @ToString
  public static class ExternalCompany {

    private Long companyId;
    private String companyName;
    private String paymentType;
    private Long dealId;
    private Long contractId;
    private Long inn;
    boolean innMatched;

  }

  @Getter
  @Setter
  @EqualsAndHashCode
  @ToString
  static class Contract {

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

  public static enum ManagerType {
    KAM("Current Sales Manager"), MC("Support Manager"), AM("Administrative Manager");

    private String name;

    ManagerType(String name) {
      this.name = name;
    }
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
                + ". Detail message: " +
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

  public static List<ExternalCompany> getCompanyByNameAndInn(String companyName, String inn)
      throws URISyntaxException {
    List<ExternalCompany> companies = new ArrayList<>();
    String stringEntity = doGet(new URIBuilder("https://backend.skyeng.ru/api/companies/by-name/")
        .setParameter("name", companyName).build());
    JSONObject jsonObject = new JSONObject(stringEntity);
    JSONArray jsonArray = (JSONArray) jsonObject.get("data");
    for (int i = 0; i < jsonArray.length(); i++) {
      ExternalCompany company = new ExternalCompany();
      company.setCompanyId(jsonArray.getJSONObject(i).getLong("companyId"));
      company.setCompanyName(jsonArray.getJSONObject(i).getString("companyName"));
      company.setPaymentType(jsonArray.getJSONObject(i).getString("paymentType"));
      company.setDealId(jsonArray.getJSONObject(i).getLong("dealId"));
      company.setContractId(jsonArray.getJSONObject(i).getLong("contractId"));
      if (!company.getPaymentType().equals("special_offer")) {
        companies.add(company);
      }
      String pipedriveStringEntity = doGet(new URIBuilder(
          "https://backend.skyeng.ru/api/companies/" + company.getCompanyId()
              + "/pipedrive/organizations/").build());
      JSONObject pipedriveJsonObject = new JSONObject(pipedriveStringEntity);
      company.setInn(pipedriveJsonObject.getJSONObject("data").getLong("tin"));
      company.setInnMatched(StringUtils.equals(company.getInn().toString(), inn));
    }
    return companies;
  }

  public static Contract getContract(Long id) {
    String contractStringEntity = null;
    try {
      contractStringEntity = doGet(new URIBuilder(
          "https://backend.skyeng.ru/api/companies/deals/contracts/" + id
              + "/").build());
    } catch (URISyntaxException e) {
      e.printStackTrace();
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
      currentSalesManager.setSurname(
          jsonObject.getJSONObject("data").getJSONObject("currentSalesManager")
              .getJSONObject("general").getString("surname"));
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
      supportManager.setSurname(
          jsonObject.getJSONObject("data").getJSONObject("supportManager").getJSONObject("general")
              .getString("surname"));
      supportManager.setManagerType(ManagerType.KAM);
      contract.setSupportManager(supportManager);
    }
    return contract;
  }

  public static void main(String[] args) throws URISyntaxException {
    List<ExternalCompany> result = getCompanyByNameAndInn("софтвайс", "7810372867");
    System.out.println(result.stream().collect(
        groupingBy(ExternalCompany::getCompanyName,
            Collectors.mapping(externalCompany -> getContract(externalCompany.getContractId()), Collectors.toList()))));;
  }
}
