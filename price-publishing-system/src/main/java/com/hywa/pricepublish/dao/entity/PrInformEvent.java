package com.hywa.pricepublish.dao.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@NoArgsConstructor
@Data
public class PrInformEvent {
    /** 主键*/
    private String id;

    /** 举报事件*/
    private String name;

    /** 被举报人*/
    private String personBeAccused;

    /** 经营地址*/
    private String businessAddress;

    /** 注册地址*/
    private String registeredAddress;

    /** 描述*/
    private String description;

    /** 举报人id*/
    private String informerId;

    /** 办理人id*/
    private String operatorId;

    /** 创建时间*/
    private Date createTime;

    /** 创建人*/
    private String createUser;

    /** 修改时间*/
    private Date updateTime;

    /** 修改人*/
    private String updateUser;

    /** 举报编码*/
    private Long code;

    /** 查询码*/
    private Long searchCode;

    /** 处理进度 0未处理 1 处理中 2 处理完成*/
    private Short state;

    /** 办理结果 0无效办理 1证据不足 2 处理完成*/
    private Short evaluate;

    /** 监管局信息*/
    private String supervisiondepartment;

}