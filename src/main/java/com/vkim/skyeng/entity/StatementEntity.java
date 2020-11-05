package com.vkim.skyeng.entity;

import com.vkim.skyeng.SyncState;

import javax.persistence.*;

@Entity
@Table(name = "statements", indexes = {
        @Index(name = "statements_idx_01", columnList = "id", unique = true),
        @Index(name = "statements_idx_02", columnList = "syncState"),
        @Index(name = "statements_idx_04", columnList = "packId")
})
@SequenceGenerator(name = "sequence_generator", sequenceName = "statements_seq", allocationSize = 1)
public class StatementEntity extends LongIdEntity {

    private String credit;
    private String name;
    private String inn;
    private String paymentDetails;
    private String packId;
    @Enumerated(EnumType.STRING)
    private SyncState syncState;
    private String log;

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId;
    }

    public SyncState getSyncState() {
        return syncState;
    }

    public void setSyncState(SyncState syncState) {
        this.syncState = syncState;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
