package com.vkim.skyeng.entity;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "companies", indexes = {
        @Index(name = "companies_idx_01", columnList = "id", unique = true),
        @Index(name = "companies_idx_02", columnList = "companyName", unique = true)
})
@SequenceGenerator(name = "sequence_generator", sequenceName = "companies_seq", allocationSize = 1)
public class CompanyEntity extends LongIdEntity {

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
