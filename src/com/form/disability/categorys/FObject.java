package com.form.disability.categorys;

import com.form.FBeans;
import com.form.FSeed;

public class FObject extends FSeed {
    private int id;
    private int dtlId;
    private int ins=0;
    private int parentID;
    private int[] indIds;
    private String selIndIds = "";
    private int userId;
    private int year = getYear(getCurrentSqlDate());
    private String code;
    private String name;
    private String description;
    private int type;
    private String createDate = dateToString(getCurrentDate());
    private String modifyDate = dateToString(getCurrentDate());
    private int totalIndicator;
    private int level = 0;
    private FBeans objects;
    private int pageIndex;
    private int totalResult;

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

    public void setObjects(FBeans objects) {
        this.objects = objects;
    }

    public FBeans getObjects() {
        return objects;
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

    public void reset() {
        this.id = 0;
        this.createDate = dateToString(getCurrentDate());
        this.modifyDate = dateToString(getCurrentDate());
        this.parentID = 0;
        this.code = "";
        this.name = "";
        this.description = "";
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setIndIds(int[] indIds) {
        this.indIds = indIds;
    }

    public int[] getIndIds() {
        return indIds;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setSelIndIds(String selIndIds) {
        this.selIndIds = selIndIds;
    }

    public String getSelIndIds() {
        return selIndIds;
    }

    public void setTotalIndicator(int totalIndicator) {
        this.totalIndicator = totalIndicator;
    }

    public int getTotalIndicator() {
        return totalIndicator;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setIns(int ins) {
        this.ins = ins;
    }

    public int getIns() {
        return ins;
    }
}
