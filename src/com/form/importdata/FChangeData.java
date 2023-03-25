package com.form.importdata;


import com.form.FSeed;

public class FChangeData extends FSeed
{
    private int id;
    private String nameTable;
    private String nameSQL;
    private String contentSearch;
 
    
    public void reset(){
        this.setId(0);


    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTable() {
        return nameTable;
    }

    public void setNameTable(String nameTable) {
        this.nameTable = nameTable;
    }

    public String getNameSQL() {
        return nameSQL;
    }

    public void setNameSQL(String nameSQL) {
        this.nameSQL = nameSQL;
    }

    public String getContentSearch() {
        return contentSearch;
    }

    public void setContentSearch(String contentSearch) {
        this.contentSearch = contentSearch;
    }
}
