package com.hywa.pricepublish.representation;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PrContentInfoRep {
    /** 主键*/
    private String id;

    /** 内容名称*/
    private String name;

    /** 标题*/
    @NotNull(message = "标题为空")
    private String title;

    /** 浏览次数*/
    private Integer browsingNum;

    /** 发表时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishedTime;

    /** 作者*/
    private String auther;

    /** 是否首页推荐(1是0不是)*/
    @NotNull(message = "需指明是否首页推荐")
    private Short isRecommend;

    /** 是否首页引用(1是0不是)*/
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

    /**内容*/
    @NotNull(message = "文章内容不能为空")
    private String content;

    /**内容主键*/
    private String contentId;

    /** 是否删除(1是0不是)*/
    private Short isDel;
}
