package com.form.disability;


import com.form.FSeed;

public class FPhanLoai extends FSeed {

    private int nguyenNhanId;
    private int id;
    private int idNkt;
    private int userId;
    private String fullName;
    private String dateCreate;
    private int thoiDiemKT;
    private String thoiDiemTK = "";
    private int diaDiemKham;
    private String reson;
    private String dangTatIds;
    private String vanDongKhac;
    private String khuyetTatKhac;
    private String nguyenNhanKhac;
    private int capdoKT;
    
    private String dangTat;
    private String nguyenNhan;
    private String mucDoKT;
    
    private int[] phanLoaiIds;

    public void reset() {
        this.id = 0;
        this.fullName = "";
        this.dateCreate = "";
        this.reson = "";
        this.dangTatIds = "";
        this.phanLoaiIds = null;
        this.vanDongKhac = "";
        this.khuyetTatKhac = "";
        this.capdoKT = 0;
        this.nguyenNhanId = 0;
        this.nguyenNhanKhac = "";
        this.thoiDiemTK = "";
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

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
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

    public String getDangTatIds() {
        return dangTatIds;
    }

    public void setDangTatIds(String dangTatIds) {
        this.dangTatIds = dangTatIds;
    }

    public String getVanDongKhac() {
        return vanDongKhac;
    }

    public void setVanDongKhac(String vanDongKhac) {
        this.vanDongKhac = vanDongKhac;
    }

    public String getKhuyetTatKhac() {
        return khuyetTatKhac;
    }

    public void setKhuyetTatKhac(String khuyetTatKhac) {
        this.khuyetTatKhac = khuyetTatKhac;
    }

    public String getNguyenNhanKhac() {
        return nguyenNhanKhac;
    }

    public void setNguyenNhanKhac(String nguyenNhanKhac) {
        this.nguyenNhanKhac = nguyenNhanKhac;
    }

    public int[] getPhanLoaiIds() {

        return phanLoaiIds;
    }

    public void setPhanLoaiIds(int[] phanLoaiIds) {
        if (phanLoaiIds != null && phanLoaiIds.length > 0) {
            String dangTatIds = "#";
            for (int i = 0; i < phanLoaiIds.length; i++) {
                dangTatIds += phanLoaiIds[i] + "#";
            }
            setDangTatIds(dangTatIds);
        }
        this.phanLoaiIds = phanLoaiIds;
    }

    public int getNguyenNhanId() {
        return nguyenNhanId;
    }

    public void setNguyenNhanId(int nguyenNhanId) {
        this.nguyenNhanId = nguyenNhanId;
    }

    public void setCapdoKT(int capdoKT) {
        this.capdoKT = capdoKT;
    }

    public int getCapdoKT() {
        return capdoKT;
    }

    public void setThoiDiemKT(int thoiDiemKT) {
        this.thoiDiemKT = thoiDiemKT;
    }

    public int getThoiDiemKT() {
        return thoiDiemKT;
    }

    public void setThoiDiemTK(String thoiDiemTK) {
        this.thoiDiemTK = thoiDiemTK;
    }

    public String getThoiDiemTK() {
        return thoiDiemTK;
    }

    public void setDiaDiemKham(int diaDiemKham) {
        this.diaDiemKham = diaDiemKham;
    }

    public int getDiaDiemKham() {
        return diaDiemKham;
    }

    public void setDangTat(String dangTat) {
        this.dangTat = dangTat;
    }

    public String getDangTat() {
        return dangTat;
    }

    public void setNguyenNhan(String nguyenNhan) {
        this.nguyenNhan = nguyenNhan;
    }

    public String getNguyenNhan() {
        return nguyenNhan;
    }

    public void setMucDoKT(String mucDoKT) {
        this.mucDoKT = mucDoKT;
    }

    public String getMucDoKT() {
        return mucDoKT;
    }
}
