package com.form.disability;


import com.form.FSeed;

import com.util.Formater;

public class FDataDtl extends FSeed {
    private int id;
    private int dataId;
    private int objId;
    private int indId;
    private int userId;
    private String fullName;
    private int locationId;
    private String createDate = dateToString(getCurrentDate());
    private String modifyDate;

    private int period = 0; // 0-Thang / 1-Quy
    private int month = getMonth(getCurrentSqlDate());
    private int quarter = Formater.getPeriod(getMonth(getCurrentSqlDate()));
    private int year = getYear(getCurrentSqlDate());

    private String baseline;
    private String activity;
    private String time;
    private String location;
    private int actual;
    private int target;

    private int tw;
    private int ttp;
    private int qhu;
    private int pxa;
    
    private int targetTw;
    private int targetTtp;
    private int targetQhu;
    private int targetPxa;

    private int targetM;
    private int targetQ;
    private int targetY;

    private int accM;
    private int accQ;
    private int accY;
    
    private String note;

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

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getBaseline() {
        return baseline;
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

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getActual() {
        return actual;
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

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setTw(int tw) {
        this.tw = tw;
    }

    public int getTw() {
        return tw;
    }

    public void setTtp(int ttp) {
        this.ttp = ttp;
    }

    public int getTtp() {
        return ttp;
    }

    public void setQhu(int qhu) {
        this.qhu = qhu;
    }

    public int getQhu() {
        return qhu;
    }

    public void setPxa(int pxa) {
        this.pxa = pxa;
    }

    public int getPxa() {
        return pxa;
    }

    public void setTargetM(int targetM) {
        this.targetM = targetM;
    }

    public int getTargetM() {
        return targetM;
    }

    public void setTargetQ(int targetQ) {
        this.targetQ = targetQ;
    }

    public int getTargetQ() {
        return targetQ;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setAccM(int accM) {
        this.accM = accM;
    }

    public int getAccM() {
        return accM;
    }

    public void setAccQ(int accQ) {
        this.accQ = accQ;
    }

    public int getAccQ() {
        return accQ;
    }

    public void setAccY(int accY) {
        this.accY = accY;
    }

    public int getAccY() {
        return accY;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setTargetTw(int targetTw) {
        this.targetTw = targetTw;
    }

    public int getTargetTw() {
        return targetTw;
    }

    public void setTargetTtp(int targetTtp) {
        this.targetTtp = targetTtp;
    }

    public int getTargetTtp() {
        return targetTtp;
    }

    public void setTargetQhu(int targetQhu) {
        this.targetQhu = targetQhu;
    }

    public int getTargetQhu() {
        return targetQhu;
    }

    public void setTargetPxa(int targetPxa) {
        this.targetPxa = targetPxa;
    }

    public int getTargetPxa() {
        return targetPxa;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }
}
