package com.vkim.skyeng.dto;

public class CompanyDto extends BaseDto {

    private Long externalCompanyId;
    private String companyName;
    private String managers;
    private String credit;
    private String paymentNumber;

    public Long getExternalCompanyId() {
        return externalCompanyId;
    }

    public void setExternalCompanyId(Long externalCompanyId) {
        this.externalCompanyId = externalCompanyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getManagers() {
        return managers;
    }

    public void setManagers(String managers) {
        this.managers = managers;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }
}
