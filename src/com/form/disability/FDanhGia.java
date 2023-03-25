package com.form.disability;


import com.form.FSeed;

public class FDanhGia extends FSeed {

    private int id;
    private int idDanhGia;    
    private int idNkt;
    private int userId;
    private String fullName;
    private String dateCreate;
    private int status;
    private int trongKy;
    private int batDau;
    private int kyDanhGia=1;
    private int namDanhGia;
    
    private int yteSucKhoe;
    private int kinhteXaHoi;
    private int giaoDuc;
    
    public void reset(){
        this.id=0;        
        this.yteSucKhoe=0;
        this.kinhteXaHoi=0;
        this.giaoDuc=0;
    }

    public int getIdNkt() {
        return idNkt;
    }

    public void setIdNkt(int idNkt) {
        this.idNkt = idNkt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

 
    public int getIdDanhGia() {
        return idDanhGia;
    }

    public void setIdDanhGia(int idDanhGia) {
        this.idDanhGia = idDanhGia;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTrongKy() {
        return trongKy;
    }

    public void setTrongKy(int trongKy) {
        this.trongKy = trongKy;
    }

    public int getBatDau() {
        return batDau;
    }

    public void setBatDau(int batDau) {
        this.batDau = batDau;
    }

    public int getNamDanhGia() {
        return namDanhGia;
    }

    public void setNamDanhGia(int namDanhGia) {
        this.namDanhGia = namDanhGia;
    }

    public int getKyDanhGia() {
        return kyDanhGia;
    }

    public void setKyDanhGia(int kyDanhGia) {
        this.kyDanhGia = kyDanhGia;
    }

    public void setYteSucKhoe(int yteSucKhoe) {
        this.yteSucKhoe = yteSucKhoe;
    }

    public int getYteSucKhoe() {
        return yteSucKhoe;
    }

    public void setKinhteXaHoi(int kinhteXaHoi) {
        this.kinhteXaHoi = kinhteXaHoi;
    }

    public int getKinhteXaHoi() {
        return kinhteXaHoi;
    }

    public void setGiaoDuc(int giaoDuc) {
        this.giaoDuc = giaoDuc;
    }

    public int getGiaoDuc() {
        return giaoDuc;
    }
}
