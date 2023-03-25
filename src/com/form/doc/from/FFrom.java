package com.form.doc.from;

import com.form.FSeed;

public class FFrom extends FSeed
{
  private String fromsId;
  private int id;
  private int fomId;
  private String vnName;
  private String enName;
  private String code;
  private int type;
  private String description;
  private String contentSearch;

     public void reset(){
            this.setId(0);
            this.vnName="";
            this.enName="";
            this.code="";
            this.description="";
           this.contentSearch="";
     }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVnName() {
        return vnName;
    }

    public void setVnName(String vnName) {
        this.vnName = vnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getContentSearch() {
        return contentSearch;
    }

    public void setContentSearch(String contentSearch) {
        this.contentSearch = contentSearch;
    }

    public String getFromsId() {
        return fromsId;
    }

    public void setFromsId(String fromsId) {
        this.fromsId = fromsId;
    }


    public int getFomId() {
        return fomId;
    }

    public void setFomId(int fomId) {
        this.fomId = fomId;
    }
}
