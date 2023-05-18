package by.teachmeskills.hw_12052023.model;

import java.time.LocalDateTime;

public class BankAccount {
    private String id;
    private String merchantId;
    private AccountStatus status;
    private String accountNumber;
    private LocalDateTime createdAt;

    public BankAccount(String id, String merchantId, AccountStatus status, String accountNumber, LocalDateTime createdAt) {
        this.id = id;
        this.merchantId = merchantId;
        this.status = status;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public BankAccount(String id, AccountStatus status, String accountNumber, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.accountNumber = accountNumber;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
