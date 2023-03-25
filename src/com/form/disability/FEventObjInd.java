package com.form.disability;

import com.form.FSeed;

public class FEventObjInd extends FSeed {
    private int eventId;
    private int indId;
    private int objId;
    private int year;

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }
}
