package com.example.wallet.entity.enums;

public enum Type {
    success("success") , fail("fail") ;
    private String convertString;

    public String getConvertString() {
        return convertString;
    }

    public void setConvertString(String convertString) {
        this.convertString = convertString;
    }

    Type(String convertString) {
        this.convertString = convertString;
    }
}
