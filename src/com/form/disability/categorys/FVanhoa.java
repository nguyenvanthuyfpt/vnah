package com.form.disability.categorys;

import com.form.FBeans;
import com.form.FSeed;

public class FVanhoa extends FSeed {
    private int id;
    private String code;
    private String name;
    private int parentID;
    private FBeans tranfers;

    public FVanhoa() {
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
}
