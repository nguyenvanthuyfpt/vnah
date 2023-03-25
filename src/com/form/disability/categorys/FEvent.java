package com.form.disability.categorys;


import com.form.FSeed;

public class FEvent extends FSeed {
    private int id;
    private int parentID;
    private String name;

    private int hdrId;
    private int objId;
    private int indId;
    private int year;
    
    private int expire = 0;
    private int total = 0;

    private int eventId;
    private int eventType;
    private int eventField;
    private int locationId;
    private String locationName;
    private String code;
    private String location;
    private String activity;
    private String createDate = dateToString(getCurrentDate());
    private String createFrom;
    private String createTo;
    private String modifyDate = dateToString(getCurrentDate());
    private String startDate;
    private String endDate;
    private int dtlId;
    private int pageIndex;
    private int totalResult;

    public void reset() {
        this.eventId = 0;
        this.code = "";
        this.activity = "";
        this.location = "";
        this.eventType = 0;
        this.eventField = 0;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventField(int eventField) {
        this.eventField = eventField;
    }

    public int getEventField() {
        return eventField;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
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

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getParentID() {
        return parentID;
    }


    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
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

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }


    public void setHdrId(int hdrId) {
        this.hdrId = hdrId;
    }

    public int getHdrId() {
        return hdrId;
    }

    public void setCreateFrom(String createFrom) {
        this.createFrom = createFrom;
    }

    public String getCreateFrom() {
        return createFrom;
    }

    public void setCreateTo(String createTo) {
        this.createTo = createTo;
    }

    public String getCreateTo() {
        return createTo;
    }
    
    public void setExpire(int expire) {
        this.expire = expire;
    }

    public int getExpire() {
        return expire;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
}
