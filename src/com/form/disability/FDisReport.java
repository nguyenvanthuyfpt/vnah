package com.form.disability;

import com.form.FSeed;

public class FDisReport extends FSeed{
    
    private int id;
    private int nktId;
    private String createDate;
    private String createBy;
    
    private int ktbtThuongXuyen = 0;
    private int ktbtTapDung = 0;
    
    private int dctgPhuHop = 0;
    private int dctgThuongXuyen = 0;
    private int dctgBaoQuan = 0; //0:Tot; 1:BinhThuong; 2:Xau
    
    private int hdNcs = 0;
    private int canThiep = 0;
    private String huongCanThiep;
    private String htroDuKien;
    private int objId = 0;
    
    public FDisReport() {
        super();
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setNktId(int nktId) {
        this.nktId = nktId;
    }

    public int getNktId() {
        return nktId;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setKtbtThuongXuyen(int ktbtThuongXuyen) {
        this.ktbtThuongXuyen = ktbtThuongXuyen;
    }

    public int getKtbtThuongXuyen() {
        return ktbtThuongXuyen;
    }

    public void setKtbtTapDung(int ktbtTapDung) {
        this.ktbtTapDung = ktbtTapDung;
    }

    public int getKtbtTapDung() {
        return ktbtTapDung;
    }

    public void setDctgPhuHop(int dctgPhuHop) {
        this.dctgPhuHop = dctgPhuHop;
    }

    public int getDctgPhuHop() {
        return dctgPhuHop;
    }

    public void setDctgThuongXuyen(int dctgThuongXuyen) {
        this.dctgThuongXuyen = dctgThuongXuyen;
    }

    public int getDctgThuongXuyen() {
        return dctgThuongXuyen;
    }

    public void setDctgBaoQuan(int dctgBaoQuan) {
        this.dctgBaoQuan = dctgBaoQuan;
    }

    public int getDctgBaoQuan() {
        return dctgBaoQuan;
    }

    public void setHdNcs(int hdNcs) {
        this.hdNcs = hdNcs;
    }

    public int getHdNcs() {
        return hdNcs;
    }

    public void setHuongCanThiep(String huongCanThiep) {
        this.huongCanThiep = huongCanThiep;
    }

    public String getHuongCanThiep() {
        return huongCanThiep;
    }

    public void setHtroDuKien(String htroDuKien) {
        this.htroDuKien = htroDuKien;
    }

    public String getHtroDuKien() {
        return htroDuKien;
    }

    public void setCanThiep(int canThiep) {
        this.canThiep = canThiep;
    }

    public int getCanThiep() {
        return canThiep;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }
}
