package com.form.template;


import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FTemplate extends FSeed
{
    private int type;
    private int block;
    private FormFile upFile;
    private String creator;
    private int id;
    private int userId;
    private String name;
    private String description;
    private String timeCreate;
    private String timeCreateFrom;
    private String timeCreateTo;
    private int permission;
    private int templateType_id;
    private String fileStore;
    private String realName;
    private String nameCaterory;
    private String code;
    private int hostNew;
    private String nameDep;
    private int departmentId;
    private String effectiveDate;
    private int pageIndex;
    private int versionId;
    private int[] ids;
    public void reset(){
        this.id=0;
        this.departmentId=0;
        this.creator="";
        this.name="";
        this.code="";
        this.description="";
        this.timeCreate="";
        this.effectiveDate="";
        this.permission=0;
        this.templateType_id=0;
        this.fileStore="";
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

   

    public String getFileStore() {
        return fileStore;
    }

    public void setFileStore(String fileStore) {
        this.fileStore = fileStore;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }

    public FormFile getUpFile() {
        return upFile;
    }

    public void setUpFile(FormFile upFile) {
        this.upFile = upFile;
    }
   

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getTemplateType_id() {
        return templateType_id;
    }

    public void setTemplateType_id(int templateType_id) {
        this.templateType_id = templateType_id;
    }

    public String getTimeCreateFrom() {
        return timeCreateFrom;
    }

    public void setTimeCreateFrom(String timeCreateFrom) {
        this.timeCreateFrom = timeCreateFrom;
    }

    public String getTimeCreateTo() {
        return timeCreateTo;
    }

    public void setTimeCreateTo(String timeCreateTo) {
        this.timeCreateTo = timeCreateTo;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getNameCaterory() {
        return nameCaterory;
    }

    public void setNameCaterory(String nameCaterory) {
        this.nameCaterory = nameCaterory;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getNameDep() {
        return nameDep;
    }

    public void setNameDep(String nameDep) {
        this.nameDep = nameDep;
    }

    public int getHostNew() {
        return hostNew;
    }

    public void setHostNew(int hostNew) {
        this.hostNew = hostNew;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }
}
