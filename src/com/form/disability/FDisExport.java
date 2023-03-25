package com.form.disability;

import com.form.FBeans;
import com.form.FSeed;

public class FDisExport extends FSeed{
    
    private int stt;
    private int id;
    private int tinhId;
    private String tinhName;
    private String maSo;
    private String ten;
    private int yearBirth;
    private String sex;
    private String soNha;
    private String dienThoai;
    private String createDate;
    
    private String ncsTen;
    private String ncsSdt;
    private String ncsSex;
    
    private String trangThai;
    private String dangTat;
    private String mucDo;
    private String ngayDangTat;
    private String daCam;
    
    private int ncauId;
    private String nhuCau;
    private String ngayNhuCau;
    
    private int htroId;
    private String hoTro;
    private String hoTroNoiNhan;
    private String dungCu;
    private String ngayHoTro;
    
    private FBeans store = new FBeans();
    
    public FDisExport() {
        super();
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTinhId(int tinhId) {
        this.tinhId = tinhId;
    }

    public int getTinhId() {
        return tinhId;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    public String getTinhName() {
        return tinhName;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTen() {
        return ten;
    }

    public void setYearBirth(int yearBirth) {
        this.yearBirth = yearBirth;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setNcsTen(String ncsTen) {
        this.ncsTen = ncsTen;
    }

    public String getNcsTen() {
        return ncsTen;
    }

    public void setNcsSdt(String ncsSdt) {
        this.ncsSdt = ncsSdt;
    }

    public String getNcsSdt() {
        return ncsSdt;
    }

    public void setNcsSex(String ncsSex) {
        this.ncsSex = ncsSex;
    }

    public String getNcsSex() {
        return ncsSex;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setDangTat(String dangTat) {
        this.dangTat = dangTat;
    }

    public String getDangTat() {
        return dangTat;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public String getMucDo() {
        return mucDo;
    }

    public void setNgayDangTat(String ngayDangTat) {
        this.ngayDangTat = ngayDangTat;
    }

    public String getNgayDangTat() {
        return ngayDangTat;
    }

    public void setDaCam(String daCam) {
        this.daCam = daCam;
    }

    public String getDaCam() {
        return daCam;
    }

    public void setNcauId(int ncauId) {
        this.ncauId = ncauId;
    }

    public int getNcauId() {
        return ncauId;
    }

    public void setNhuCau(String nhuCau) {
        this.nhuCau = nhuCau;
    }

    public String getNhuCau() {
        return nhuCau;
    }

    public void setNgayNhuCau(String ngayNhuCau) {
        this.ngayNhuCau = ngayNhuCau;
    }

    public String getNgayNhuCau() {
        return ngayNhuCau;
    }

    public void setHtroId(int htroId) {
        this.htroId = htroId;
    }

    public int getHtroId() {
        return htroId;
    }

    public void setHoTro(String hoTro) {
        this.hoTro = hoTro;
    }

    public String getHoTro() {
        return hoTro;
    }

    public void setHoTroNoiNhan(String hoTroNoiNhan) {
        this.hoTroNoiNhan = hoTroNoiNhan;
    }

    public String getHoTroNoiNhan() {
        return hoTroNoiNhan;
    }

    public void setDungCu(String dungCu) {
        this.dungCu = dungCu;
    }

    public String getDungCu() {
        return dungCu;
    }

    public void setNgayHoTro(String ngayHoTro) {
        this.ngayHoTro = ngayHoTro;
    }

    public String getNgayHoTro() {
        return ngayHoTro;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }
}
