package com.form.disability.categorys;

import com.form.FSeed;

public class FHotro extends FSeed {
    private int id;
    private String code;
    private String name;
    private String space;
    private int parentID;
    private int level;
    private String dangTatIds;
    private int[] phanLoaiIds;

    public FHotro() {
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSpace() {
        return space;
    }

    public void setSpace(String space) {
        this.space = space;
    }


    public String getDangTatIds() {
        return dangTatIds;
    }

    public void setDangTatIds(String dangTatIds) {
        this.dangTatIds = dangTatIds;
    }

    public int[] getPhanLoaiIds() {
        return phanLoaiIds;
    }

    public void setPhanLoaiIds(int[] phanLoaiIds) {
        this.phanLoaiIds = phanLoaiIds;
    }
}
