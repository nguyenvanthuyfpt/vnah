package com.form.disability;

import com.form.FSeed;

public class FObjectInd extends FSeed {
    private int id;
    private int objId;
    private int indId;
    private int year;
    private int ins;

    private int locationId;
    private String code;
    private String name;
    private String description;
    private int type;
    private String selIndIds;
    private int total;


    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
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

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setSelIndIds(String selIndIds) {
        this.selIndIds = selIndIds;
    }

    public String getSelIndIds() {
        return selIndIds;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIns(int ins) {
        this.ins = ins;
    }

    public int getIns() {
        return ins;
    }
}
