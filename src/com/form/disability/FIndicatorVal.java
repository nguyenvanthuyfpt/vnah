package com.form.disability;

import com.form.FBeans;
import com.form.FSeed;

public class FIndicatorVal extends FSeed {


    private int id;
    private String createDate = dateToString(getCurrentDate());
    private String modifyDate = dateToString(getCurrentDate());
    private int indId;
    private int locationId;
    private int quarter;
    private int year;
    private String period;
    private int val;
    private int type = -1;

    private String locationName;
    private int pageIndex;
    private int totalResult;
    private FBeans indicatorVals;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
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

    public void setIndicatorVals(FBeans indicatorVals) {
        this.indicatorVals = indicatorVals;
    }

    public FBeans getIndicatorVals() {
        return indicatorVals;
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


    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
