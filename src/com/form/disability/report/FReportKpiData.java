package com.form.disability.report;


import com.form.FBeans;
import com.form.FSeed;


public class FReportKpiData extends FSeed {
    
    private int exportTemp = 0;
    // Common
    private int objId;
    private int indId;

    private String createDate;
    private int userId;
    private String userName;
    private String userFullName;
    private int locationId;
    private String locationName;
    private int dataType;

    // Person
    private int perId;
    private int eventId;
    private String perAddress;
    private String perContact;
    private String perCode;
    private String perName;
    private int perSex;
    private String perAgency;
    private String perTitle;
    private String perBirth;
    private int perHours;

    // Value
    private int valMonth;
    private int valYear;
    private int valPeriod;
    private String valActivity;
    private String valTime;
    private String valActual;

    // Disability
    private int disId;
    private String disCode;
    private String disName;
    private String disBirth;
    private int disSex;
    private String disCarers;
    private String disAddress;
    private String disNation;
    private String disRelation;

    // Event
    public String eventCode;
    public String eventActivity;
    public String eventLocation;
    public String eventStartDate;
    public String eventEndDate;

    private FBeans store = new FBeans();
    private int total = 0;


    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setPerAddress(String perAddress) {
        this.perAddress = perAddress;
    }

    public String getPerAddress() {
        return perAddress;
    }

    public void setPerContact(String perContact) {
        this.perContact = perContact;
    }

    public String getPerContact() {
        return perContact;
    }

    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerAgency(String perAgency) {
        this.perAgency = perAgency;
    }

    public String getPerAgency() {
        return perAgency;
    }

    public void setPerTitle(String perTitle) {
        this.perTitle = perTitle;
    }

    public String getPerTitle() {
        return perTitle;
    }

    public void setPerBirth(String perBirth) {
        this.perBirth = perBirth;
    }

    public String getPerBirth() {
        return perBirth;
    }


    public void setValMonth(int valMonth) {
        this.valMonth = valMonth;
    }

    public int getValMonth() {
        return valMonth;
    }

    public void setValYear(int valYear) {
        this.valYear = valYear;
    }

    public int getValYear() {
        return valYear;
    }

    public void setValPeriod(int valPeriod) {
        this.valPeriod = valPeriod;
    }

    public int getValPeriod() {
        return valPeriod;
    }

    public void setValActivity(String valActivity) {
        this.valActivity = valActivity;
    }

    public String getValActivity() {
        return valActivity;
    }

    public void setValTime(String valTime) {
        this.valTime = valTime;
    }

    public String getValTime() {
        return valTime;
    }

    public void setValActual(String valActual) {
        this.valActual = valActual;
    }

    public String getValActual() {
        return valActual;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisBirth(String disBirth) {
        this.disBirth = disBirth;
    }

    public String getDisBirth() {
        return disBirth;
    }

    public void setDisCarers(String disCarers) {
        this.disCarers = disCarers;
    }

    public String getDisCarers() {
        return disCarers;
    }

    public void setDisAddress(String disAddress) {
        this.disAddress = disAddress;
    }

    public String getDisAddress() {
        return disAddress;
    }

    public void setDisNation(String disNation) {
        this.disNation = disNation;
    }

    public String getDisNation() {
        return disNation;
    }

    public void setDisRelation(String disRelation) {
        this.disRelation = disRelation;
    }

    public String getDisRelation() {
        return disRelation;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataType() {
        return dataType;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setPerId(int perId) {
        this.perId = perId;
    }

    public int getPerId() {
        return perId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setDisId(int disId) {
        this.disId = disId;
    }

    public int getDisId() {
        return disId;
    }

    public void setPerSex(int perSex) {
        this.perSex = perSex;
    }

    public int getPerSex() {
        return perSex;
    }

    public void setDisSex(int disSex) {
        this.disSex = disSex;
    }

    public int getDisSex() {
        return disSex;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventActivity(String eventActivity) {
        this.eventActivity = eventActivity;
    }

    public String getEventActivity() {
        return eventActivity;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setPerHours(int perHours) {
        this.perHours = perHours;
    }

    public int getPerHours() {
        return perHours;
    }

    public void setExportTemp(int exportTemp) {
        this.exportTemp = exportTemp;
    }

    public int getExportTemp() {
        return exportTemp;
    }
}
