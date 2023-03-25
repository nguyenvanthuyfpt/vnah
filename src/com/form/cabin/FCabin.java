package com.form.cabin;


import com.form.FBeans;
import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FCabin extends FSeed
{
    private String capacity;
    private int toPertion;
    private FBeans empsRev;
    private FBeans depsRev;
    private int[] depIdS;
    private String contentSearch;
    private String contentLucene;
    private int id;
    private int[] userIdS;
    private int departmentID;
    private int type=0;
    private String fullName;
    private FormFile upFile;
    private String timeCreateFrom;
    private String timeCreateTo;
    private int pageIndex;
    private String realName;
    private String userFullName;
    
    private String nameCategory;
    private int userId;
    private long meId;
    private String name;
    private String description;
    private String timeCreate;
    private int cabinType_id=0;
    private int cabinTypeInput_id=0;
    private String fileStore;
    private String rootPath;
    private int idEditName;
    private int typeFile;
    private int order_by=-1;
    private int back;
    
    public void reset(){
        this.setId(0);
        this.cabinType_id=0;
        this.cabinTypeInput_id=0;
        this.userId=0;
        this.name="";
        this.description="";
        this.timeCreate="";
        this.fileStore="";
        this.upFile=null;
        this.realName="";
        this.capacity="";
        this.order_by=-1;
    }


    
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public FormFile getUpFile() {
        return upFile;
    }

    public void setUpFile(FormFile upFile) {
        this.upFile = upFile;
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

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
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

    public int getCabinType_id() {
        return cabinType_id;
    }

    public void setCabinType_id(int cabinType_id) {
        this.cabinType_id = cabinType_id;
    }

    public String getFileStore() {
        return fileStore;
    }

    public void setFileStore(String fileStore) {
        this.fileStore = fileStore;
    }

    public long getMeId() {
        return meId;
    }

    public void setMeId(long meId) {
        this.meId = meId;
    }

    public FBeans getEmpsRev() {
        return empsRev;
    }

    public void setEmpsRev(FBeans empsRev) {
        this.empsRev = empsRev;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int[] getUserIdS() {
        return userIdS;
    }

    public void setUserIdS(int[] userIdS) {
        this.userIdS = userIdS;
    }

    public int getToPertion() {
        return toPertion;
    }

    public void setToPertion(int toPertion) {
        this.toPertion = toPertion;
    }

    public FBeans getDepsRev() {
        return depsRev;
    }

    public void setDepsRev(FBeans depsRev) {
        this.depsRev = depsRev;
    }

    public int[] getDepIdS() {
        return depIdS;
    }

    public void setDepIdS(int[] depIdS) {
        this.depIdS = depIdS;
    }

    public String getContentSearch() {
        return contentSearch;
    }

    public void setContentSearch(String contentSearch) {
        this.contentSearch = contentSearch;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public int getIdEditName() {
        return idEditName;
    }

    public void setIdEditName(int idEditName) {
        this.idEditName = idEditName;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public int getOrder_by() {
        return order_by;
    }

    public void setOrder_by(int order_by) {
        this.order_by = order_by;
    }

    public int getCabinTypeInput_id() {
        return cabinTypeInput_id;
    }

    public void setCabinTypeInput_id(int cabinTypeInput_id) {
        this.cabinTypeInput_id = cabinTypeInput_id;
    }

    public int getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(int typeFile) {
        this.typeFile = typeFile;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }

    public String getContentLucene() {
        return contentLucene;
    }

    public void setContentLucene(String contentLucene) {
        this.contentLucene = contentLucene;
    }
}
