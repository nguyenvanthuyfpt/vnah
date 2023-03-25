package com.form.disability.categorys;


import com.form.FBeans;
import com.form.FSeed;

import java.sql.Date;

public class FNguonhotro extends FSeed {
    private int id;
    private String code;
    private String name;
    private String description;
    private int parentID;
    private int position;
    private Date createdate;
    private FBeans tranfers;

    public FNguonhotro() {
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

    public FBeans getTranfers() {
        return tranfers;
    }

    public void setTranfers(FBeans tranfers) {
        this.tranfers = tranfers;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getCreatedate() {
        return createdate;
    }
}
