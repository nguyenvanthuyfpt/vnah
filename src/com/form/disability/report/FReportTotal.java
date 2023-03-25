package com.form.disability.report;


import com.form.FSeed;

import com.util.Formater;

public class FReportTotal extends FSeed {
    private int id;
    private int tinhId;
    private int from;
    private int to;

    private int fromMonth = getMonth(getCurrentSqlDate()) - 1;
    private int toMonth = getMonth(getCurrentSqlDate());

    private String commune;
    private String district;
    private String province;
    private String tinhName;
    private String reportType = "1";
    private String fieldType;

    private int period = getMonth(getCurrentSqlDate()) > 6 ? 2 : 1;
    private int yearPeriod = getYear(getCurrentSqlDate());
    private int fromYear = getYear(getCurrentSqlDate());
    private int toYear = getYear(getCurrentSqlDate());

    private int periodType = 0;
    private int month = getMonth(getCurrentSqlDate());
    private int quarter = Formater.getPeriod(month);
    private int year = getYear(getCurrentSqlDate());

    private String[] paramValue = new String[385];

    private String func;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTinhId(int tinhId) {
        this.tinhId = tinhId;
    }

    public int getTinhId() {
        return tinhId;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getFrom() {
        return from;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int getTo() {
        return to;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getCommune() {
        return commune;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrict() {
        return district;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvince() {
        return province;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    public String getTinhName() {
        return tinhName;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportType() {
        return reportType;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public int getPeriod() {
        return period;
    }


    public void setParamValue(String[] paramValue) {
        this.paramValue = paramValue;
    }

    public String[] getParamValue() {
        return paramValue;
    }

    public void setParamValue(String value, int index) {
        this.paramValue[index] = value;
    }

    public void setFromMonth(int fromMonth) {
        this.fromMonth = fromMonth;
    }

    public int getFromMonth() {
        return fromMonth;
    }

    public void setToMonth(int toMonth) {
        this.toMonth = toMonth;
    }

    public int getToMonth() {
        return toMonth;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getFunc() {
        return func;
    }

    public void setFromYear(int fromYear) {
        this.fromYear = fromYear;
    }

    public int getFromYear() {
        return fromYear;
    }

    public void setToYear(int toYear) {
        this.toYear = toYear;
    }

    public int getToYear() {
        return toYear;
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

    public void setPeriodType(int periodType) {
        this.periodType = periodType;
    }

    public int getPeriodType() {
        return periodType;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setYearPeriod(int yearPeriod) {
        this.yearPeriod = yearPeriod;
    }

    public int getYearPeriod() {
        return yearPeriod;
    }
}
