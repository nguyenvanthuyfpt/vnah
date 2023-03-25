package com.form.disability;

import com.form.FSeed;

public class FHomeVisit extends FSeed {
    private int id;
    private int supportId;
    private int nktId;
    private int createBy;

    private String startAt;
    private String endAt;
    private double latitude;
    private double longgitude;

    private int pageIndex;
    private int totalResult;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }

    public int getSupportId() {
        return supportId;
    }

    public void setNktId(int nktId) {
        this.nktId = nktId;
    }

    public int getNktId() {
        return nktId;
    }

    public void setCreateBy(int createBy) {
        this.createBy = createBy;
    }

    public int getCreateBy() {
        return createBy;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getEndAt() {
        return endAt;
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

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLonggitude(double longgitude) {
        this.longgitude = longgitude;
    }

    public double getLonggitude() {
        return longgitude;
    }
}
