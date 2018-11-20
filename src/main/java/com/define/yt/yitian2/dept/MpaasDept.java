package com.define.yt.yitian2.dept;

import com.definesys.mpaas.query.json.MpaasDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

import com.definesys.mpaas.query.model.MpaasBasePojo;
import com.definesys.mpaas.query.json.MpaasDateSerializer;
import com.definesys.mpaas.query.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @Copyright: Shanghai Definesys Company.All rights reserved.
 * @Description:
 * @author: xulei
 * @since: 2018-11-13
 * @history: 1.2018-11-13 created by xulei
 */

@Table(value = "mpaas_dept")
public class MpaasDept extends MpaasBasePojo {

    @RowID(sequence = "mpaas_dept_s", type = RowIDType.AUTO)
    private Integer id;

    private String name;

    private String manager;

    @SystemColumn(SystemColumnType.OBJECT_VERSION)
    @Column(value = "object_version")
    private Integer objectVersion;

    @SystemColumn(SystemColumnType.CREATE_BY)
    @Column(value = "create_by")
    private String createBy;

    @JsonSerialize(using = MpaasDateSerializer.class)
    @JsonDeserialize(using = MpaasDateDeserializer.class)
    @SystemColumn(SystemColumnType.CREATE_ON)
    @Column(value = "create_date")
    private Date createDate;

    @SystemColumn(SystemColumnType.LASTUPDATE_BY)
    @Column(value = "lastupdate_by")
    private String lastupdateBy;

    @JsonSerialize(using = MpaasDateSerializer.class)
    @JsonDeserialize(using = MpaasDateDeserializer.class)
    @SystemColumn(SystemColumnType.LASTUPDATE_ON)
    @Column(value = "lastupdate_date")
    private Date lastupdateDate;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManager() {
        return this.manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
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