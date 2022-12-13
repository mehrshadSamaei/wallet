package com.example.wallet.entity.enums;

public enum Status {
    active("active") , nonActive("nonActive");

    private String convertString;

    public String getConvertString() {
        return convertString;
    }

    public void setConvertString(String convertString) {
        this.convertString = convertString;
    }

    Status(String convertString) {
        this.convertString = convertString;
    }
}
