package com.epam.ta.core.enums;

public enum Device {

    NEXUS("nexus"),
    PIXEL("pixel"),
    PIXEL2("pixel2");

    private String value;

    Device(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }

}