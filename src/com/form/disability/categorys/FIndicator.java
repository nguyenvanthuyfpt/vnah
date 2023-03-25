package com.form.disability.categorys;

import com.form.FBeans;
import com.form.FSeed;

public class FIndicator extends FSeed {
    private int id;
    private int mapId;
    private int dtlId;
    private int indId;
    private int parentID;
    private int userId;
    private String code;
    private String name;
    private String nameParent;
    private String description;
    private String createDate = dateToString(getCurrentDate());
    private String modifyDate = dateToString(getCurrentDate());

    private int locationId;
    private String locationName;
    private int quarter;
    private int year;
    private int value;

    private int baseline;
    private int targetYear;
    private String targetJustification;
    private int typePeriod = 0;

    private int m1;
    private int m2;
    private int m3;
    private int m4;
    private int m5;
    private int m6;
    private int m7;
    private int m8;
    private int m9;
    private int m10;
    private int m11;
    private int m12;

    private int q1;
    private int q2;
    private int q3;
    private int q4;
    
    private int dataType = 0;  // 0: Target; 1 baseline
    private int type = -1;
    private int lvl = 0;

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

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setBaseline(int baseline) {
        this.baseline = baseline;
    }

    public int getBaseline() {
        return baseline;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetJustification(String targetJustification) {
        this.targetJustification = targetJustification;
    }

    public String getTargetJustification() {
        return targetJustification;
    }

    public void setTypePeriod(int typePeriod) {
        this.typePeriod = typePeriod;
    }

    public int getTypePeriod() {
        return typePeriod;
    }

    public void setM1(int m1) {
        this.m1 = m1;
    }

    public int getM1() {
        return m1;
    }

    public void setM2(int m2) {
        this.m2 = m2;
    }

    public int getM2() {
        return m2;
    }

    public void setM3(int m3) {
        this.m3 = m3;
    }

    public int getM3() {
        return m3;
    }

    public void setM4(int m4) {
        this.m4 = m4;
    }

    public int getM4() {
        return m4;
    }

    public void setM5(int m5) {
        this.m5 = m5;
    }

    public int getM5() {
        return m5;
    }

    public void setM6(int m6) {
        this.m6 = m6;
    }

    public int getM6() {
        return m6;
    }

    public void setM7(int m7) {
        this.m7 = m7;
    }

    public int getM7() {
        return m7;
    }

    public void setM8(int m8) {
        this.m8 = m8;
    }

    public int getM8() {
        return m8;
    }

    public void setM9(int m9) {
        this.m9 = m9;
    }

    public int getM9() {
        return m9;
    }

    public void setM10(int m10) {
        this.m10 = m10;
    }

    public int getM10() {
        return m10;
    }

    public void setM11(int m11) {
        this.m11 = m11;
    }

    public int getM11() {
        return m11;
    }

    public void setM12(int m12) {
        this.m12 = m12;
    }

    public int getM12() {
        return m12;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
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

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }


    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataType() {
        return dataType;
    }

    public void setMapId(int mapId) {
        this.mapId = mapId;
    }

    public int getMapId() {
        return mapId;
    }

    public void setNameParent(String nameParent) {
        this.nameParent = nameParent;
    }

    public String getNameParent() {
        return nameParent;
    }
}
