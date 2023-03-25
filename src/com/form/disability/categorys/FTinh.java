package com.form.disability.categorys;

import com.form.FBeans;
import com.form.FSeed;

public class FTinh extends FSeed {
    private int id;
    private String code;
    private String name;
    private String memberArea;
    private String space;
    private int parentID;
    private int level;
    private FBeans tranfers;
    private String nameTemp;
    private int parentName;
    private int creator;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public FBeans getTranfers() {
        return tranfers;
    }

    public void setTranfers(FBeans tranfers) {
        this.tranfers = tranfers;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getNameTemp() {
        return nameTemp;
    }

    public void setNameTemp(String nameTemp) {
        this.nameTemp = nameTemp;
    }

    public int getParentName() {
        return parentName;
    }

    public void setParentName(int parentName) {
        this.parentName = parentName;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getMemberArea() {
        return memberArea;
    }

    public void setMemberArea(String memberArea) {
        this.memberArea = memberArea;
    }
}
