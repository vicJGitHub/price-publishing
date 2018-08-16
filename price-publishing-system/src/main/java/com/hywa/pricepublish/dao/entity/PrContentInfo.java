package com.hywa.pricepublish.dao.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * 图文发布基本信息
 */
public class PrContentInfo {
    /** 主键*/
    private String id;

    /** 内容名称*/
    private String name;

    /** 标题*/
    private String title;

    /** 浏览次数*/
    private Integer browsingNum;

    /** 发表时间*/
    private Date publishedTime;

    /** 作者*/
    private String auther;

    /** 是否首页推荐(1是0不是 默认0)*/
    private Short isRecommend;

    /** 是否首页引用(1是0不是 默认0)*/
    private Short isUserble;

    /** 文章封面地址*/
    private String cover;

    /** 文章概要*/
    private String summary;

    /** 创建时间*/
    private Date createTime;

    /** 创建人*/
    private String createUser;

    /** 修改时间*/
    private Date updateTime;

    /** 修改人*/
    private String updateUser;

    /** 内容主键*/
    private String contentId;

    /** 是否删除(1是0不是 默认0)*/
    private Short isDel;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getBrowsingNum() {
        return browsingNum;
    }

    public void setBrowsingNum(Integer browsingNum) {
        this.browsingNum = browsingNum;
    }

    public Date getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(Date publishedTime) {
        this.publishedTime = publishedTime;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther == null ? null : auther.trim();
    }

    public Short getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Short isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Short getIsUserble() {
        return isUserble;
    }

    public void setIsUserble(Short isUserble) {
        this.isUserble = isUserble;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover == null ? null : cover.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId == null ? null : contentId.trim();
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }
}