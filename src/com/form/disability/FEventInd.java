package com.form.disability;

import com.form.FSeed;

public class FEventInd extends FSeed {
    private int eventId;
    private int indId;

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
}
