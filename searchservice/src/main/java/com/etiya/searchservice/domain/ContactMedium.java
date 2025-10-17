package com.etiya.searchservice.domain;

public class ContactMedium {

    private int id;
    private String type;
    private String value;
    private boolean isPrimary;
    private String customerId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }


    public ContactMedium(int id, String type, String value, boolean isPrimary, String customerId) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.isPrimary = isPrimary;
        this.customerId = customerId;
    }

    public ContactMedium(int id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public ContactMedium() {
    }
}