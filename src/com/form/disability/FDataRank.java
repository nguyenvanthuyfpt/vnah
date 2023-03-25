package com.form.disability;


import com.form.FBeans;
import com.form.FSeed;

import com.util.Formater;

public class FDataRank extends FSeed {
    private int id;
    private String createDate = Formater.date2str(getCurrentDate());
    private String modifyDate;

    private int nktId;
    private int rankId;
    private int userId;
    private int locationId;
    private String locationName;

    private int hasSP = 0;
    private int hasRK = 0;
    private int hasRQ = 0;
    private int p0 = 0;
    private int p1 = 0;
    private int p2 = 0;
    private int p3 = 0;
    private int p4 = 0;

    private int pageIndex;
    private int totalResult;

    private FBeans store = new FBeans();


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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

    public void setNktId(int nktId) {
        this.nktId = nktId;
    }

    public int getNktId() {
        return nktId;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getRankId() {
        return rankId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }


    public void setP0(int p0) {
        this.p0 = p0;
    }

    public int getP0() {
        return p0;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP1() {
        return p1;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public int getP2() {
        return p2;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public int getP3() {
        return p3;
    }

    public void setP4(int p4) {
        this.p4 = p4;
    }

    public int getP4() {
        return p4;
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

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setHasSP(int hasSP) {
        this.hasSP = hasSP;
    }

    public int getHasSP() {
        return hasSP;
    }

    public void setHasRK(int hasRK) {
        this.hasRK = hasRK;
    }

    public int getHasRK() {
        return hasRK;
    }

    public void setHasRQ(int hasRQ) {
        this.hasRQ = hasRQ;
    }

    public int getHasRQ() {
        return hasRQ;
    }
}
