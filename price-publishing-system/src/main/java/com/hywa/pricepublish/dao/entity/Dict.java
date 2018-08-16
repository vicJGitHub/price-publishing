package com.hywa.pricepublish.dao.entity;

public class Dict {
    private String id;

    private String name;

    private String code;

    private String dictTypeId;

    private String selectUrl;

    private String unSelectUrl;

    private short isDel;

    public short getIsDel() {
        return isDel;
    }

    public void setIsDel(short isDel) {
        this.isDel = isDel;
    }

    public String getSelectUrl() {
        return selectUrl;
    }

    public void setSelectUrl(String selectUrl) {
        this.selectUrl = selectUrl;
    }

    public String getUnSelectUrl() {
        return unSelectUrl;
    }

    public void setUnSelectUrl(String unSelectUrl) {
        this.unSelectUrl = unSelectUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId == null ? null : dictTypeId.trim();
    }
}