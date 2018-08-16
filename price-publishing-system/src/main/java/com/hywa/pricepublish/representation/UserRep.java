package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.dao.entity.User;
import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.ToString;

@ToString
public class UserRep implements Serializable {

    private static final long serialVersionUID = -4963266899668807475L;

    private String userId;

    @NotNull(message = "用户名不能为空")
    private String name;

    private String phone;

    private String sex;

    private String jobTitle;

    //@NotNull(message = "请绑定区域")
    private String regionId;

    @NotNull(message = "工作单位不能为空")
    private String workUnit;

    //最小限制1,你写10
    @Min(value = 1, message = "年龄最小限制1")
    @Max(value = 100, message = "年龄最大限制100")
    private Integer age;

    @NotNull(message = "密码不能为空")
    private String pwd;
    private String accessToken;

    public UserRep() {
    }

    public UserRep(String userId, String name, String phone,
            Short sex, String jobTitle, String workUnit,
            Integer age, String regionId) {
        this.setUserId(userId);
        this.setName(name);
        this.setPhone(phone);
        this.setSex(sex == null ? null : CommonEnum.getSex(sex));
        this.setJobTitle(jobTitle);
        this.setWorkUnit(workUnit);
        this.setAge(age);
        this.setRegionId(regionId);
    }

    public UserRep(User user) {
        this.setUserId(user.getId());
        this.setName(user.getName());
        this.setPhone(user.getTelephone());
        this.setSex(CommonEnum.getSex(user.getSex()));
        this.setJobTitle(user.getJob());
        this.setWorkUnit(user.getWorkUnit());
        this.setAge(user.getAge());
    }

    public UserRep(User user, String regionId) {
        this(user);
        this.setRegionId(regionId);
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setAccessToken(String token) {
        this.accessToken = token;
    }

    public String getAccessToken() {
        return accessToken;
    }
}


