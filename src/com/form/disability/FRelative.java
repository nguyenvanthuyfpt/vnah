package com.form.disability;


import com.form.FSeed;

public class FRelative extends FSeed {
    private String searchSub;

    private int id;
    private int idNkt;
    private int idRelativeNkt = 0;
    private String ten;
    private String tenLyDo;
    private int lydoId = 0;

    public void reset() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdNkt() {
        return idNkt;
    }

    public void setIdNkt(int idNkt) {
        this.idNkt = idNkt;
    }


    public int getIdRelativeNkt() {
        return idRelativeNkt;
    }

    public void setIdRelativeNkt(int idRelativeNkt) {
        this.idRelativeNkt = idRelativeNkt;
    }

    public int getLydoId() {
        return lydoId;
    }

    public void setLydoId(int lydoId) {
        this.lydoId = lydoId;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSearchSub() {
        return searchSub;
    }

    public void setSearchSub(String searchSub) {
        this.searchSub = searchSub;
    }

    public String getTenLyDo() {
        return tenLyDo;
    }

    public void setTenLyDo(String tenLyDo) {
        this.tenLyDo = tenLyDo;
    }
}
