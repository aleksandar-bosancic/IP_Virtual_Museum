package com.museum.bank.admin.beans;

import java.io.Serializable;
import java.sql.Timestamp;

public class BankAccountBean implements Serializable {
    private static final long serialVersionUID = -7587239060689814185L;

    private Integer id;
    private String holderName;
    private String number;
    private Type type;
    private Timestamp validThru;
    private String pin;
    private Double balance;
    private Boolean blocked;

    public BankAccountBean(Integer id, String holderName, String number, Type type, Timestamp validThru, String pin, Double balance, Boolean blocked) {
        this.id = id;
        this.holderName = holderName;
        this.number = number;
        this.type = type;
        this.validThru = validThru;
        this.pin = pin;
        this.balance = balance;
        this.blocked = blocked;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Timestamp getValidThru() {
        return validThru;
    }

    public void setValidThru(Timestamp validThru) {
        this.validThru = validThru;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getBlocked() {
        return blocked;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }
}
