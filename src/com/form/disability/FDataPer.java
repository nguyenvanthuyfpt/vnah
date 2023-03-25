package com.form.disability;

import com.form.FSeed;

public class FDataPer extends FSeed {
    private String createDate;
    private int dataId;
    private int perId;
    private int eventId;
    private int result;
    private int hours;
    
    private int objId;
    private int indId;
    private int locationId;

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getDataId() {
        return dataId;
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

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
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

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }
}
