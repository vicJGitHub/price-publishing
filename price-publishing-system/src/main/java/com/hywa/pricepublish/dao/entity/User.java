package com.hywa.pricepublish.dao.entity;

import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.enums.CommonEnum;
import com.hywa.pricepublish.common.utils.StringUtils;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.representation.UserRep;
import com.hywa.pricepublish.representation.ext.UserRepExt;

import java.util.Date;

public class User {

    private String id;

    private String name;

    private Short sex;

    private String telephone;

    private String password;

    private String account;

    private String email;

    private Date updateTime;

    private Date createTime;

    private String updateUser;

    private String createUser;

    private Short isDel;

    private String job;

    private String workUnit;

    private Integer age;

    public User() {
    }

    public User(UserRepExt userRep){
        this.setId(UUIDUtils.randomUUID());
        this.setName(userRep.getName());
        this.setPassword(StringUtils.md5(userRep.getPwd()));
        this.setName(userRep.getName());
        this.setAge(userRep.getAge());
        this.setWorkUnit(userRep.getWorkUnit());
        this.setJob(userRep.getJobTitle());
        this.setIsDel(ConstantPool.NOT_DEL);
        this.setTelephone(userRep.getPhone());
        this.setSex(sex == null ? null : CommonEnum.getIndex(userRep.getSex()));
        this.setCreateTime(new Date());
        this.setCreateUser(userRep.getCreateUser());
        if (userRep.getSex() != null) {
            this.setSex(CommonEnum.getIndex(userRep.getSex()));
        }
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

    public Short getSex() {
        return sex;
    }

    public void setSex(Short sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser == null ? null : updateUser.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Short getIsDel() {
        return isDel;
    }

    public void setIsDel(Short isDel) {
        this.isDel = isDel;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public String getWorkUnit() {
        return workUnit;
    }

    public void setWorkUnit(String workUnit) {
        this.workUnit = workUnit;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}