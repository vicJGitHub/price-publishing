package com.hywa.pricepublish.dao.entity;

import java.util.Date;

public class Log {
    private String id;

    private String userid;

    private String reqIp;

    private String reqUri;

    private String reqHost;

    private Integer reqPort;

    private Short errorCode;

    private String errorMessage;

    private Date createTime;

    private String realErrorMessage;

    private String parameter;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getReqIp() {
        return reqIp;
    }

    public void setReqIp(String reqIp) {
        this.reqIp = reqIp == null ? null : reqIp.trim();
    }

    public String getReqUri() {
        return reqUri;
    }

    public void setReqUri(String reqUri) {
        this.reqUri = reqUri == null ? null : reqUri.trim();
    }

    public String getReqHost() {
        return reqHost;
    }

    public void setReqHost(String reqHost) {
        this.reqHost = reqHost == null ? null : reqHost.trim();
    }

    public Integer getReqPort() {
        return reqPort;
    }

    public void setReqPort(Integer reqPort) {
        this.reqPort = reqPort;
    }

    public Short getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Short errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage == null ? null : errorMessage.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRealErrorMessage() {
        return realErrorMessage;
    }

    public void setRealErrorMessage(String realErrorMessage) {
        this.realErrorMessage = realErrorMessage == null ? null : realErrorMessage.trim();
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
    }
}