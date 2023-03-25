package com.form.report;


import com.form.FBeans;
import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FReport extends FSeed
{
    private int[] userIdS;
    private int toPertion;
    private FBeans empsRev;
    private String userFullName;
    private int pageIndex;
    private int block;
    private FormFile upFile;
    private String creator;
    private int type;
    private int id;
    private int meId;
    private int userId;
    private String name;
    private String description;
    private String timeCreate;
    private String timeCreateForm;
    private String timeCreateTo;
    private int permission;
    private int reportType_id;
    private String fileStore;
    private String realName;
    private String nameCategory;
    private int[] ids;

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
   public void reset(){
        this.id=0;
        this.creator="";
        this.name="";
        this.description="";
        this.timeCreate="";
        this.fileStore="";
        this.setUpFile(null);
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


    public int getReportType_id() {
        return reportType_id;
    }

    public void setReportType_id(int reportType_id) {
        this.reportType_id = reportType_id;
    }

    public String getTimeCreateForm() {
        return timeCreateForm;
    }

    public void setTimeCreateForm(String timeCreateForm) {
        this.timeCreateForm = timeCreateForm;
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

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int[] getIds() {
        return ids;
    }

    public void setIds(int[] ids) {
        this.ids = ids;
    }

    public int getMeId() {
        return meId;
    }

    public void setMeId(int meId) {
        this.meId = meId;
    }

    public int getToPertion() {
        return toPertion;
    }

    public void setToPertion(int toPertion) {
        this.toPertion = toPertion;
    }

    public FBeans getEmpsRev() {
        return empsRev;
    }

    public void setEmpsRev(FBeans empsRev) {
        this.empsRev = empsRev;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public int[] getUserIdS() {
        return userIdS;
    }

    public void setUserIdS(int[] userIdS) {
        this.userIdS = userIdS;
    }
}
