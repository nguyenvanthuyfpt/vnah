package com.form.disability.report;

import com.form.FBeans;
import com.form.FSeed;

public class FReportGroup extends FSeed {
    private String tinhId;
    private String diaBan;
    private String maNKT;
    private String hotenNKT;
    private String namSinh;
    private String gioiTinh;
    private String hotenCH;
    private String diaChi;
    private String thonTo;
    private String dangTat;
    private String mucDoKT;

    private String nhatam;
    private String nhakienco;
    private String nguyenvong;
    private String ghichu;
    private String td_vanhoa;
    private String nc_vanhoa;
    private String td_chuyenmon;
    private String nghemuonhoc;
    private String vieclam;

    private int total = 0;
    private int total_f = 0;  // Nu
    private int total_m = 0;  // Nam
    private int total_0_6 = 0;
    private int total_f_0_6 = 0;
    private int total_m_0_6 = 0;
    private int total_6_15 = 0;
    private int total_f_6_15 = 0;
    private int total_m_6_15 = 0;
    private int total_15_60 = 0;
    private int total_f_15_60 = 0;
    private int total_m_15_60 = 0;
    private int total_60_80 = 0;
    private int total_f_60_80 = 0;
    private int total_m_60_80 = 0;
    private int total_80 = 0;
    private int total_f_80 = 0;
    private int total_m_80 = 0;    
    
    private int t_bamsinh = 0;
    private int t_benh = 0;
    private int t_laodong = 0;
    private int t_giaothong = 0;
    private int t_chientranh = 0;
    private int t_khac = 0;
    
    private int total_60 = 0;
    private int t_60_bamsinh = 0;
    private int t_60_benh = 0;
    private int t_60_laodong = 0;
    private int t_60_giaothong = 0;
    private int t_60_chientranh = 0;
    private int t_60_khac = 0;
    
    private int total_yte = 0;
    private int hotro_1 = 0;
    private int hotro_2 = 0;
    private int hotro_3 = 0;
    private int hotro_4 = 0;
    private int hotro_5 = 0;
    private int hotro_6 = 0;
    
    private int nhucau_1 = 0;
    private int nhucau_2 = 0;
    private int nhucau_3 = 0;
    private int nhucau_4 = 0;
    private int nhucau_5 = 0;
    private int nhucau_6 = 0;
    private int nhucau_7 = 0;
    
    private int dantoc_kinh = 0;
    private int dantoc_khac = 0;
    private int trinhdo_1 = 0;
    private int trinhdo_2 = 0;
    private int trinhdo_3 = 0;
    private int trinhdo_4 = 0;
    private int trinhdo_5 = 0;
    private int trinhdo_6 = 0;
    
    private int dangtat_1 = 0;
    private int dangtat_2 = 0;
    private int dangtat_3 = 0;
    private int dangtat_4 = 0;
    private int dangtat_5 = 0;
    private int dangtat_6 = 0;
    
    private int mucdo_1 = 0;
    private int mucdo_2 = 0;
    private int mucdo_3 = 0;        
    
    private FBeans store = new FBeans();

    public void setTinhId(String tinhId) {
        this.tinhId = tinhId;
    }

    public String getTinhId() {
        return tinhId;
    }

    public void setDiaBan(String diaBan) {
        this.diaBan = diaBan;
    }

    public String getDiaBan() {
        return diaBan;
    }

    public void setMaNKT(String maNKT) {
        this.maNKT = maNKT;
    }

    public String getMaNKT() {
        return maNKT;
    }

    public void setHotenNKT(String hotenNKT) {
        this.hotenNKT = hotenNKT;
    }

    public String getHotenNKT() {
        return hotenNKT;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setHotenCH(String hotenCH) {
        this.hotenCH = hotenCH;
    }

    public String getHotenCH() {
        return hotenCH;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDangTat(String dangTat) {
        this.dangTat = dangTat;
    }

    public String getDangTat() {
        return dangTat;
    }

    public void setMucDoKT(String mucDoKT) {
        this.mucDoKT = mucDoKT;
    }

    public String getMucDoKT() {
        return mucDoKT;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }
    
    public void setNhatam(String nhatam) {
        this.nhatam = nhatam;
    }

    public String getNhatam() {
        return nhatam;
    }

    public void setNhakienco(String nhakienco) {
        this.nhakienco = nhakienco;
    }

    public String getNhakienco() {
        return nhakienco;
    }

    public void setNguyenvong(String nguyenvong) {
        this.nguyenvong = nguyenvong;
    }

    public String getNguyenvong() {
        return nguyenvong;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setTd_vanhoa(String td_vanhoa) {
        this.td_vanhoa = td_vanhoa;
    }

    public String getTd_vanhoa() {
        return td_vanhoa;
    }

    public void setTd_chuyenmon(String td_chuyenmon) {
        this.td_chuyenmon = td_chuyenmon;
    }

    public String getTd_chuyenmon() {
        return td_chuyenmon;
    }

    public void setNghemuonhoc(String nghemuonhoc) {
        this.nghemuonhoc = nghemuonhoc;
    }

    public String getNghemuonhoc() {
        return nghemuonhoc;
    }

    public void setVieclam(String vieclam) {
        this.vieclam = vieclam;
    }

    public String getVieclam() {
        return vieclam;
    }

    public void setNc_vanhoa(String nc_vanhoa) {
        this.nc_vanhoa = nc_vanhoa;
    }

    public String getNc_vanhoa() {
        return nc_vanhoa;
    }
    
    public void setTotal_f(int total_f) {
        this.total_f = total_f;
    }

    public int getTotal_f() {
        return total_f;
    }

    public void setTotal_m(int total_m) {
        this.total_m = total_m;
    }

    public int getTotal_m() {
        return total_m;
    }
    
    

    public void setTotal_6_15(int total_6_15) {
        this.total_6_15 = total_6_15;
    }

    public int getTotal_6_15() {
        return total_6_15;
    }

    public void setTotal_f_6_15(int total_f_6_15) {
        this.total_f_6_15 = total_f_6_15;
    }

    public int getTotal_f_6_15() {
        return total_f_6_15;
    }

    public void setTotal_m_6_15(int total_m_6_15) {
        this.total_m_6_15 = total_m_6_15;
    }

    public int getTotal_m_6_15() {
        return total_m_6_15;
    }

    public void setTotal_15_60(int total_15_60) {
        this.total_15_60 = total_15_60;
    }

    public int getTotal_15_60() {
        return total_15_60;
    }

    public void setTotal_f_15_60(int total_f_15_60) {
        this.total_f_15_60 = total_f_15_60;
    }

    public int getTotal_f_15_60() {
        return total_f_15_60;
    }

    public void setTotal_m_15_60(int total_m_15_60) {
        this.total_m_15_60 = total_m_15_60;
    }

    public int getTotal_m_15_60() {
        return total_m_15_60;
    }

    public void setTotal_60_80(int total_60_80) {
        this.total_60_80 = total_60_80;
    }

    public int getTotal_60_80() {
        return total_60_80;
    }

    public void setTotal_f_60_80(int total_f_60_80) {
        this.total_f_60_80 = total_f_60_80;
    }

    public int getTotal_f_60_80() {
        return total_f_60_80;
    }

    public void setTotal_m_60_80(int total_m_60_80) {
        this.total_m_60_80 = total_m_60_80;
    }

    public int getTotal_m_60_80() {
        return total_m_60_80;
    }

    public void setTotal_80(int total_80) {
        this.total_80 = total_80;
    }

    public int getTotal_80() {
        return total_80;
    }

    public void setTotal_f_80(int total_f_80) {
        this.total_f_80 = total_f_80;
    }

    public int getTotal_f_80() {
        return total_f_80;
    }

    public void setTotal_m_80(int total_m_80) {
        this.total_m_80 = total_m_80;
    }

    public int getTotal_m_80() {
        return total_m_80;
    }

    public void setT_bamsinh(int t_bamsinh) {
        this.t_bamsinh = t_bamsinh;
    }

    public int getT_bamsinh() {
        return t_bamsinh;
    }

    public void setT_benh(int t_benh) {
        this.t_benh = t_benh;
    }

    public int getT_benh() {
        return t_benh;
    }

    public void setT_laodong(int t_laodong) {
        this.t_laodong = t_laodong;
    }

    public int getT_laodong() {
        return t_laodong;
    }

    public void setT_giaothong(int t_giaothong) {
        this.t_giaothong = t_giaothong;
    }

    public int getT_giaothong() {
        return t_giaothong;
    }

    public void setT_chientranh(int t_chientranh) {
        this.t_chientranh = t_chientranh;
    }

    public int getT_chientranh() {
        return t_chientranh;
    }

    public void setT_khac(int t_khac) {
        this.t_khac = t_khac;
    }

    public int getT_khac() {
        return t_khac;
    }

    public void setTotal_60(int total_60) {
        this.total_60 = total_60;
    }

    public int getTotal_60() {
        return total_60;
    }

    public void setT_60_bamsinh(int t_60_bamsinh) {
        this.t_60_bamsinh = t_60_bamsinh;
    }

    public int getT_60_bamsinh() {
        return t_60_bamsinh;
    }

    public void setT_60_benh(int t_60_benh) {
        this.t_60_benh = t_60_benh;
    }

    public int getT_60_benh() {
        return t_60_benh;
    }

    public void setT_60_laodong(int t_60_laodong) {
        this.t_60_laodong = t_60_laodong;
    }

    public int getT_60_laodong() {
        return t_60_laodong;
    }

    public void setT_60_giaothong(int t_60_giaothong) {
        this.t_60_giaothong = t_60_giaothong;
    }

    public int getT_60_giaothong() {
        return t_60_giaothong;
    }

    public void setT_60_chientranh(int t_60_chientranh) {
        this.t_60_chientranh = t_60_chientranh;
    }

    public int getT_60_chientranh() {
        return t_60_chientranh;
    }

    public void setT_60_khac(int t_60_khac) {
        this.t_60_khac = t_60_khac;
    }

    public int getT_60_khac() {
        return t_60_khac;
    }

    public void setTotal_yte(int total_yte) {
        this.total_yte = total_yte;
    }

    public int getTotal_yte() {
        return total_yte;
    }

    public void setHotro_1(int hotro_1) {
        this.hotro_1 = hotro_1;
    }

    public int getHotro_1() {
        return hotro_1;
    }

    public void setHotro_2(int hotro_2) {
        this.hotro_2 = hotro_2;
    }

    public int getHotro_2() {
        return hotro_2;
    }

    public void setHotro_3(int hotro_3) {
        this.hotro_3 = hotro_3;
    }

    public int getHotro_3() {
        return hotro_3;
    }

    public void setHotro_4(int hotro_4) {
        this.hotro_4 = hotro_4;
    }

    public int getHotro_4() {
        return hotro_4;
    }

    public void setHotro_5(int hotro_5) {
        this.hotro_5 = hotro_5;
    }

    public int getHotro_5() {
        return hotro_5;
    }

    public void setHotro_6(int hotro_6) {
        this.hotro_6 = hotro_6;
    }

    public int getHotro_6() {
        return hotro_6;
    }

    public void setNhucau_1(int nhucau_1) {
        this.nhucau_1 = nhucau_1;
    }

    public int getNhucau_1() {
        return nhucau_1;
    }

    public void setNhucau_2(int nhucau_2) {
        this.nhucau_2 = nhucau_2;
    }

    public int getNhucau_2() {
        return nhucau_2;
    }

    public void setNhucau_3(int nhucau_3) {
        this.nhucau_3 = nhucau_3;
    }

    public int getNhucau_3() {
        return nhucau_3;
    }

    public void setNhucau_4(int nhucau_4) {
        this.nhucau_4 = nhucau_4;
    }

    public int getNhucau_4() {
        return nhucau_4;
    }

    public void setNhucau_5(int nhucau_5) {
        this.nhucau_5 = nhucau_5;
    }

    public int getNhucau_5() {
        return nhucau_5;
    }

    public void setNhucau_6(int nhucau_6) {
        this.nhucau_6 = nhucau_6;
    }

    public int getNhucau_6() {
        return nhucau_6;
    }

    public void setNhucau_7(int nhucau_7) {
        this.nhucau_7 = nhucau_7;
    }

    public int getNhucau_7() {
        return nhucau_7;
    }

    public void setDantoc_kinh(int dantoc_kinh) {
        this.dantoc_kinh = dantoc_kinh;
    }

    public int getDantoc_kinh() {
        return dantoc_kinh;
    }

    public void setDantoc_khac(int dantoc_khac) {
        this.dantoc_khac = dantoc_khac;
    }

    public int getDantoc_khac() {
        return dantoc_khac;
    }

    public void setTrinhdo_1(int trinhdo_1) {
        this.trinhdo_1 = trinhdo_1;
    }

    public int getTrinhdo_1() {
        return trinhdo_1;
    }

    public void setTrinhdo_2(int trinhdo_2) {
        this.trinhdo_2 = trinhdo_2;
    }

    public int getTrinhdo_2() {
        return trinhdo_2;
    }

    public void setTrinhdo_3(int trinhdo_3) {
        this.trinhdo_3 = trinhdo_3;
    }

    public int getTrinhdo_3() {
        return trinhdo_3;
    }

    public void setTrinhdo_4(int trinhdo_4) {
        this.trinhdo_4 = trinhdo_4;
    }

    public int getTrinhdo_4() {
        return trinhdo_4;
    }

    public void setTrinhdo_5(int trinhdo_5) {
        this.trinhdo_5 = trinhdo_5;
    }

    public int getTrinhdo_5() {
        return trinhdo_5;
    }

    public void setTrinhdo_6(int trinhdo_6) {
        this.trinhdo_6 = trinhdo_6;
    }

    public int getTrinhdo_6() {
        return trinhdo_6;
    }

    public void setDangtat_1(int dangtat_1) {
        this.dangtat_1 = dangtat_1;
    }

    public int getDangtat_1() {
        return dangtat_1;
    }

    public void setDangtat_2(int dangtat_2) {
        this.dangtat_2 = dangtat_2;
    }

    public int getDangtat_2() {
        return dangtat_2;
    }

    public void setDangtat_3(int dangtat_3) {
        this.dangtat_3 = dangtat_3;
    }

    public int getDangtat_3() {
        return dangtat_3;
    }

    public void setDangtat_4(int dangtat_4) {
        this.dangtat_4 = dangtat_4;
    }

    public int getDangtat_4() {
        return dangtat_4;
    }

    public void setDangtat_5(int dangtat_5) {
        this.dangtat_5 = dangtat_5;
    }

    public int getDangtat_5() {
        return dangtat_5;
    }

    public void setDangtat_6(int dangtat_6) {
        this.dangtat_6 = dangtat_6;
    }

    public int getDangtat_6() {
        return dangtat_6;
    }

    public void setMucdo_1(int mucdo_1) {
        this.mucdo_1 = mucdo_1;
    }

    public int getMucdo_1() {
        return mucdo_1;
    }

    public void setMucdo_2(int mucdo_2) {
        this.mucdo_2 = mucdo_2;
    }

    public int getMucdo_2() {
        return mucdo_2;
    }

    public void setMucdo_3(int mucdo_3) {
        this.mucdo_3 = mucdo_3;
    }

    public int getMucdo_3() {
        return mucdo_3;
    }

    public void setTotal_0_6(int total_0_6) {
        this.total_0_6 = total_0_6;
    }

    public int getTotal_0_6() {
        return total_0_6;
    }

    public void setTotal_f_0_6(int total_f_0_6) {
        this.total_f_0_6 = total_f_0_6;
    }

    public int getTotal_f_0_6() {
        return total_f_0_6;
    }

    public void setTotal_m_0_6(int total_m_0_6) {
        this.total_m_0_6 = total_m_0_6;
    }

    public int getTotal_m_0_6() {
        return total_m_0_6;
    }

    public void setThonTo(String thonTo) {
        this.thonTo = thonTo;
    }

    public String getThonTo() {
        return thonTo;
    }
}
