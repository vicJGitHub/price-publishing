package com.hywa.pricepublish.dao.entity;

/**
 * 图文发布正文
 */
public class PrContent {
    /** 主键*/
    private String id;

    /** 是否删除(1是0不是)*/
    private Short isDel;

    /** 内容*/
    private String content;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}