package com.form.disability.report;


import com.form.FBeans;
import com.form.FSeed;

import com.util.Formater;

public class FReportKpiObject extends FSeed {

    private String timeExport;
    private int monthReport = getMonth(getCurrentSqlDate());    
    private int quarterReport = Formater.getPeriod(monthReport);
    private int yearReport = getYear(getCurrentSqlDate());
    private String periodType = "0";
    private String val = "";

    private String fromMonth = String.valueOf(getMonth(getCurrentSqlDate()));
    private String toMonth = String.valueOf(getMonth(getCurrentSqlDate()));

    private String fromYear = String.valueOf(getYear(getCurrentSqlDate()));
    private String toYear = String.valueOf(getYear(getCurrentSqlDate()));

    private int tinhId;
    private String tinhName;
    private String func;
  
    private int objNum;
    private int indNum;
    private int objId;
    private String objName;

    private int indId;
    private String indCode;
    private String indDesc;
    private int indBaseline;
    private int targetYear;
    private int targetQ1;
    private int targetQ2;
    private int targetQ3;
    private int targetQ4;
    private int targetJus;
    private int indNote;

    private String baseline;
    private String target;
    private String actual;
    private String percent;

    private FBeans store = new FBeans();

    public void setTimeExport(String timeExport) {
        this.timeExport = timeExport;
    }

    public String getTimeExport() {
        return timeExport;
    }

    public void setMonthReport(int monthReport) {
        this.monthReport = monthReport;
    }

    public int getMonthReport() {
        return monthReport;
    }

    public void setQuarterReport(int quarterReport) {
        this.quarterReport = quarterReport;
    }

    public int getQuarterReport() {
        return quarterReport;
    }

    public void setYearReport(int yearReport) {
        this.yearReport = yearReport;
    }

    public int getYearReport() {
        return yearReport;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public String getToYear() {
        return toYear;
    }

    public void setTinhId(int tinhId) {
        this.tinhId = tinhId;
    }

    public int getTinhId() {
        return tinhId;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    public String getTinhName() {
        return tinhName;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getFunc() {
        return func;
    }

    public void setIndNum(int indNum) {
        this.indNum = indNum;
    }

    public int getIndNum() {
        return indNum;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjName() {
        return objName;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setIndCode(String indCode) {
        this.indCode = indCode;
    }

    public String getIndCode() {
        return indCode;
    }

    public void setIndDesc(String indDesc) {
        this.indDesc = indDesc;
    }

    public String getIndDesc() {
        return indDesc;
    }

    public void setIndBaseline(int indBaseline) {
        this.indBaseline = indBaseline;
    }

    public int getIndBaseline() {
        return indBaseline;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetQ1(int targetQ1) {
        this.targetQ1 = targetQ1;
    }

    public int getTargetQ1() {
        return targetQ1;
    }

    public void setTargetQ2(int targetQ2) {
        this.targetQ2 = targetQ2;
    }

    public int getTargetQ2() {
        return targetQ2;
    }

    public void setTargetQ3(int targetQ3) {
        this.targetQ3 = targetQ3;
    }

    public int getTargetQ3() {
        return targetQ3;
    }

    public void setTargetQ4(int targetQ4) {
        this.targetQ4 = targetQ4;
    }

    public int getTargetQ4() {
        return targetQ4;
    }

    public void setTargetJus(int targetJus) {
        this.targetJus = targetJus;
    }

    public int getTargetJus() {
        return targetJus;
    }

    public void setIndNote(int indNote) {
        this.indNote = indNote;
    }

    public int getIndNote() {
        return indNote;
    }

    

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPercent() {
        return percent;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setObjNum(int objNum) {
        this.objNum = objNum;
    }

    public int getObjNum() {
        return objNum;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getActual() {
        return actual;
    }
}
