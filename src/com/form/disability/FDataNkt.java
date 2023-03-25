package com.form.disability;

import com.form.FBeans;
import com.form.FSeed;

public class FDataNkt extends FSeed {
    private int nktId;
    private int userId;
    private int dataId;
    private int hours;
    
    private String createDate;
    private String createUser;
    private String modifyDate;
    
    private String activity;
    private String time;
    private String location;
    private String disCode;
    private String disName;
    private String disPassport;
    private String disPhone;
    private int disSex;
    private int isDioxin;
    private int disJob;
    private String disBirth;
    private String disNation;
    private String disAddress;
    private String disCarers;
    private String disRelation;
    private int disLocationId;
    private String disLocationName;

    private int pageIndex;
    private int totalResult;
    private FBeans dataNkts;

    public void setNktId(int nktId) {
        this.nktId = nktId;
    }

    public int getNktId() {
        return nktId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getDataId() {
        return dataId;
    }


    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setDataNkts(FBeans dataNkts) {
        this.dataNkts = dataNkts;
    }

    public FBeans getDataNkts() {
        return dataNkts;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public String getDisCode() {
        return disCode;
    }


    public void setDisSex(int disSex) {
        this.disSex = disSex;
    }

    public int getDisSex() {
        return disSex;
    }

    public void setDisBirth(String disBirth) {
        this.disBirth = disBirth;
    }

    public String getDisBirth() {
        return disBirth;
    }

    public void setDisNation(String disNation) {
        this.disNation = disNation;
    }

    public String getDisNation() {
        return disNation;
    }

    public void setDisAddress(String disAddress) {
        this.disAddress = disAddress;
    }

    public String getDisAddress() {
        return disAddress;
    }

    public void setDisCarers(String disCarers) {
        this.disCarers = disCarers;
    }

    public String getDisCarers() {
        return disCarers;
    }

    public void setDisRelation(String disRelation) {
        this.disRelation = disRelation;
    }

    public String getDisRelation() {
        return disRelation;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisName() {
        return disName;
    }
    
    public void setDisLocationId(int disLocationId) {
        this.disLocationId = disLocationId;
    }

    public int getDisLocationId() {
        return disLocationId;
    }

    public void setDisLocationName(String disLocationName) {
        this.disLocationName = disLocationName;
    }

    public String getDisLocationName() {
        return disLocationName;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setCreateUser(String createUser) {
	this.createUser = createUser;
    }

    public String getCreateUser() {
	return createUser;
    }

    public void setDisPassport(String disPassport) {
	this.disPassport = disPassport;
    }

    public String getDisPassport() {
	return disPassport;
    }

    public void setDisPhone(String disPhone) {
	this.disPhone = disPhone;
    }

    public String getDisPhone() {
	return disPhone;
    }

    public void setIsDioxin(int isDioxin) {
	this.isDioxin = isDioxin;
    }

    public int getIsDioxin() {
	return isDioxin;
    }

    public void setDisJob(int disJob) {
	this.disJob = disJob;
    }

    public int getDisJob() {
	return disJob;
    }
}
