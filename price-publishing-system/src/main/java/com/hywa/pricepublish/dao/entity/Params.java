package com.hywa.pricepublish.dao.entity;

public class Params {
    private String groupCode;

    private String memberCode;

    private String paramKey;

    private String paramValue;

    private Short paramType;

    private Short paramStatus;

    private String note;

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode == null ? null : groupCode.trim();
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode == null ? null : memberCode.trim();
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue == null ? null : paramValue.trim();
    }

    public Short getParamType() {
        return paramType;
    }

    public void setParamType(Short paramType) {
        this.paramType = paramType;
    }

    public Short getParamStatus() {
        return paramStatus;
    }

    public void setParamStatus(Short paramStatus) {
        this.paramStatus = paramStatus;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}