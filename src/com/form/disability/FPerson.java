package com.form.disability;


import com.form.FSeed;

import com.util.Constant;

public class FPerson extends FSeed {
    private int id;
    private int dataId;
    private int objId;
    private int indId;
    private int refId;
    private int userId;
    private int eventId;
    private int eventType;
    private int eventField;
    private String startDate;
    private String endDate;
    private int voteResult;
    private int state = Constant.KPI_STATE_INPUT;
    private String createDate = dateToString(getCurrentDate());
    private String createFrom;
    private String createTo;
    private String modifyDate;

    private int type;

    private int locationId;
    private String locationName;
    private String address;
    private String baseline;
    private String contact;
    private String code;
    private String name;
    private int sex;
    private String gioiTinh;
    private String agency;
    private String title;
    private String birth;
    private int hours;
    private String checked;
    private int pageIndex;
    private int totalResult;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getDataId() {
        return dataId;
    }


    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
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

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAgency() {
        return agency;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
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

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getBirth() {
        return birth;
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

    public void setVoteResult(int voteResult) {
        this.voteResult = voteResult;
    }

    public int getVoteResult() {
        return voteResult;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact() {
        return contact;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getChecked() {
        return checked;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public int getRefId() {
        return refId;
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

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
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
}
