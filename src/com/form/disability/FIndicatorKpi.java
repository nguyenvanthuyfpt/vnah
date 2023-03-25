package com.form.disability;


import com.form.FBeans;
import com.form.FSeed;

import com.util.Formater;

import java.util.Map;

public class FIndicatorKpi extends FSeed {
    private int id;
    private int parentID;
    private int userId;
    private String code;
    private String name;
    private String description;
    private String createDate = dateToString(getCurrentDate());
    private String modifyDate = dateToString(getCurrentDate());   
    
    private int month = getMonth(getCurrentSqlDate());
    private int year = getYear(getCurrentSqlDate());
    private int period = Formater.getPeriod(month);
    
    private Map<String, String> mapBaseline;    
    private String baseline;
    private int targetYear;
    private int targetJustification;
    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int actual;
    private int pageIndex;
    private int totalResult;
    private FBeans indicators;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetJustification(int targetJustification) {
        this.targetJustification = targetJustification;
    }

    public int getTargetJustification() {
        return targetJustification;
    }

    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getQ4() {
        return q4;
    }

    

    public void setIndicators(FBeans indicators) {
        this.indicators = indicators;
    }

    public FBeans getIndicators() {
        return indicators;
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

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getActual() {
        return actual;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }

    public void setMapBaseline(Map<String, String> mapBaseline) {
        this.mapBaseline = mapBaseline;
    }

    public Map<String, String> getMapBaseline() {
        return mapBaseline;
    }
}
