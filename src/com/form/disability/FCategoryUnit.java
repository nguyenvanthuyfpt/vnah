package com.form.disability;

import com.form.FSeed;

public class FCategoryUnit extends FSeed{

    private int id;
    private String name;

    public FCategoryUnit() {
    }

    public void reset() {
        this.id = 0;
        this.name = "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
