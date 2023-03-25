package com.form.disability;

import com.form.FSeed;

public class FThongTinTuyen extends FSeed {
    private int id;
    private int id_tinh;
    private String name_tinh;
    private String member;

    private String totalPopulation;
    private String totalMale; //  Nam
    private String totalFemale; //  Nu

    private String femaleHasJob;
    private String totalHasJob; // SL NKT co viec lam

    private String femaleJobLess;
    private String totalJobLess; // SL NKT that nghiep

    private int kyBC = getMonth(getCurrentSqlDate()) > 6 ? 2 : 1;
    private int namBC = getYear(getCurrentSqlDate());

    private int level; // Cap dia ban (TP/Quan-Huyen/Phuong-Xa)
    private String tinhName;
    private String dateUpdate;

    private int pageIndex;
    private int totalResult;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId_tinh(int id_tinh) {
        this.id_tinh = id_tinh;
    }

    public int getId_tinh() {
        return id_tinh;
    }

    public void setTotalPopulation(String totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public String getTotalPopulation() {
        return totalPopulation;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    public String getTinhName() {
        return tinhName;
    }

    public void setDateUpdate(String dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public String getDateUpdate() {
        return dateUpdate;
    }

    public void reset() {
        this.id = 0;
        this.totalFemale = "0";
        this.totalMale = "0";
        this.totalPopulation = "0";
        this.totalHasJob = "0";
        this.totalJobLess = "0";
        this.femaleHasJob = "0";
        this.femaleJobLess = "0";
        this.tinhName = "";
        this.kyBC = getMonth(getCurrentSqlDate()) > 6 ? 2 : 1;
        this.namBC = getYear(getCurrentSqlDate());
        this.setDateUpdate(new FSeed().dateToString(new FSeed().getCurrentSqlDate()));
    }

    public void setTotalMale(String totalMale) {
        this.totalMale = totalMale;
    }

    public String getTotalMale() {
        return totalMale;
    }

    public void setTotalFemale(String totalFemale) {
        this.totalFemale = totalFemale;
    }

    public String getTotalFemale() {
        return totalFemale;
    }

    public void setTotalHasJob(String totalHasJob) {
        this.totalHasJob = totalHasJob;
    }

    public String getTotalHasJob() {
        return totalHasJob;
    }

    public void setTotalJobLess(String totalJobLess) {
        this.totalJobLess = totalJobLess;
    }

    public String getTotalJobLess() {
        return totalJobLess;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setKyBC(int kyBC) {
        this.kyBC = kyBC;
    }

    public int getKyBC() {
        return kyBC;
    }

    public void setNamBC(int namBC) {
        this.namBC = namBC;
    }

    public int getNamBC() {
        return namBC;
    }

    public void setName_tinh(String name_tinh) {
        this.name_tinh = name_tinh;
    }

    public String getName_tinh() {
        return name_tinh;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public String getMember() {
        return member;
    }

    public void setFemaleHasJob(String femaleHasJob) {
        this.femaleHasJob = femaleHasJob;
    }

    public String getFemaleHasJob() {
        return femaleHasJob;
    }

    public void setFemaleJobLess(String femaleJobLess) {
        this.femaleJobLess = femaleJobLess;
    }

    public String getFemaleJobLess() {
        return femaleJobLess;
    }


    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResult() {
        return totalResult;
    }
}
