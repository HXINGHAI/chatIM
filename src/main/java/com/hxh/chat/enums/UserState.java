package com.hxh.chat.enums;

public enum UserState {

    /**
     * 用户在线
     */
    ONLINE("1001","在线"),

    /**
     * 用户离线
     */
    OFFLINE("1002","离线");


    private String code;

    private String value;

    UserState(String code, String value) {
        this.code = code;
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
