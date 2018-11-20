package com.define.yt.yitian2.user;

import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.annotation.*;

import java.sql.Blob;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: xulei
 * @since: 2018-11-14
 * @history: 1.2018-11-14 created by xulei
 */
@Table(value = "mpaas_user")
public class MpaasUser extends MpaasBasePojo {

    @RowID(sequence = "MPAAS_USER_S", type = RowIDType.AUTO)
    private Integer id;

    private String code;

    private String name;

    private Integer age;

    private String email;

    @JsonSerialize(using = MpaasDateSerializer.class)
    @JsonDeserialize(using = MpaasDateDeserializer.class)
    private Date birthday;

    private Double salary;

    private String description;

    private Blob avatar;

    @Column(value = "dept_id")
    private Integer deptId;

    @Column(value = "object_version")
    private Integer objectVersion;

    @Column(value = "create_by")
    private String createBy;

    @JsonSerialize(using = MpaasDateSerializer.class)
    @JsonDeserialize(using = MpaasDateDeserializer.class)
    @Column(value = "create_date")
    private Date createDate;

    @Column(value = "lastupdate_by")
    private String lastupdateBy;

    @JsonSerialize(using = MpaasDateSerializer.class)
    @JsonDeserialize(using = MpaasDateDeserializer.class)
    @Column(value = "lastupdate_date")
    private Date lastupdateDate;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthday() {
        return this.birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Blob getAvatar() {
        return this.avatar;
    }

    public void setAvatar(Blob avatar) {
        this.avatar = avatar;
    }

    public Integer getDeptId() {
        return this.deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getObjectVersion() {
        return this.objectVersion;
    }

    public void setObjectVersion(Integer objectVersion) {
        this.objectVersion = objectVersion;
    }

    public String getCreateBy() {
        return this.createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastupdateBy() {
        return this.lastupdateBy;
    }

    public void setLastupdateBy(String lastupdateBy) {
        this.lastupdateBy = lastupdateBy;
    }

    public Date getLastupdateDate() {
        return this.lastupdateDate;
    }

    public void setLastupdateDate(Date lastupdateDate) {
        this.lastupdateDate = lastupdateDate;
    }
}