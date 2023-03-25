package com.form.disability;


import com.form.FSeed;

public class FInforNKT extends FSeed {

    private int[] tempId;
    private int nktId;
    private int id;
    private String name;

    public void reset() {
        this.nktId = 0;
    }


    public int getNktId() {
        return nktId;
    }

    public void setNktId(int nktId) {
        this.nktId = nktId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int[] getTempId() {
        return tempId;
    }

    public void setTempId(int[] tempId) {
        this.tempId = tempId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
