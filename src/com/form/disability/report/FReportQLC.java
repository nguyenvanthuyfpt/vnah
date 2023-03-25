package com.form.disability.report;

import com.form.FBeans;
import com.form.FSeed;

public class FReportQLC extends FSeed {
    private int id;
    private int kyBC = getMonth(getCurrentSqlDate());
    private int namBC = getYear(getCurrentSqlDate());
    private int id_tinh;
    private int level;
    private String func;
    private String nameArea;
    private FBeans store = new FBeans();  // 1.Thong tin ve nkt tai dia phuong
    private FBeans store1 = new FBeans(); // 2.Thong tin qlth tai dia phuong trong thang
    private FBeans store2 = new FBeans(); // 3.Cong tac chuyen tuyen va ket noi
    private FBeans store3 = new FBeans(); // 3.Cong tac chuyen tuyen va ket noi

    private String diaBan;
    private int tGia;
    private int tGiaLaoDong;
    private int tGiaYTe;
    private int tGiaGiaoDuc;

    private int lKe;
    private int lKeLaoDong;
    private int lKeYTe;
    private int lKeGiaoDuc;

    private int kThuc;
    private int ngNhan1;
    private int ngNhan2;
    private int ngNhan3;
    private int ngNhan4;

    private String hoTen;
    private String maSo;
    private String diaChi;
    private String namSinh;
    private String dangTat;
    private String mucDoKTat;
    private String hoanCanhKTe;
    private String hoTenChuHo;
    private String tenNgQLy;
    
    private String nktDauKy;
    private String nktDauKyLDong;
    private String nktDauKyYTe;
    private String nktDauKyGDuc;
    
    private int nktTang;
    private int nktTangLDong;
    private int nktTangYTe;
    private int nktTangGDuc;
    
    private int nktGiam;
    private int nktGiamLDong;
    private int nktGiamYTe;
    private int nktGiamGDuc;
    
    private int nktCuoiKy;
    private int nktCuoiKyLDong;
    private int nktCuoiKyYTe;
    private int nktCuoiKyGDuc;
    
    // NTK co nhu cau QLTH
    private int nktTH;
    private int nktTHLDong;
    private int nktTHYTe;
    private int nktTHGDuc;
    
    private int nktTC;
    private int nktTCLDong;
    private int nktTCYTe;
    private int nktTCGDuc;
    
    // NTK thuoc dien QLTH
    private int qlcTH;
    private int qlcTHLDong;
    private int qlcTHYTe;
    private int qlcTHGDuc;
    
    private int qlcTC;
    private int qlcTCLDong;
    private int qlcTCYTe;
    private int qlcTCGDuc;
    
    private int qlcDauKy;
    private int qlcDauKyLDong;
    private int qlcDauKyYTe;
    private int qlcDauKyGDuc;
    
    private int qlcTang;
    private int qlcTangLDong;
    private int qlcTangYTe;
    private int qlcTangGDuc;
    
    private int qlcGiam;
    private int qlcGiamLDong;
    private int qlcGiamYTe;
    private int qlcGiamGDuc;
    
    private int qlcCuoiKy;
    private int qlcCuoiKyLDong;
    private int qlcCuoiKyYTe;
    private int qlcCuoiKyGDuc;

    private int total;
    private int totalThang;
    private int totalLuyKe;
    
    private int totalXaHoi;
    private int totalXaHoiThang;
    private int totalXaHoiLuyKe;
    
    private int totalYTe;
    private int totalYTeThang;
    private int totalYTeLuyKe;
    
    private int totalGiaoDuc;
    private int totalGiaoDucThang;
    private int totalGiaoDucLuyKe;
    
    public void reset() {
        this.nameArea = "";
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId_tinh(int id_tinh) {
        this.id_tinh = id_tinh;
    }

    public int getId_tinh() {
        return id_tinh;
    }


    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setKyBC(int kyBC) {
        this.kyBC = kyBC;
    }

    public int getKyBC() {
        return kyBC;
    }

    public void setNamBC(int namBC) {
        this.namBC = namBC;
    }

    public int getNamBC() {
        return namBC;
    }


    public void setFunc(String func) {
        this.func = func;
    }

    public String getFunc() {
        return func;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setStore1(FBeans store1) {
        this.store1 = store1;
    }

    public FBeans getStore1() {
        return store1;
    }

    public void setDiaBan(String diaBan) {
        this.diaBan = diaBan;
    }

    public String getDiaBan() {
        return diaBan;
    }

    public void setTGia(int tGia) {
        this.tGia = tGia;
    }

    public int getTGia() {
        return tGia;
    }

    public void setTGiaLaoDong(int tGiaLaoDong) {
        this.tGiaLaoDong = tGiaLaoDong;
    }

    public int getTGiaLaoDong() {
        return tGiaLaoDong;
    }

    public void setTGiaYTe(int tGiaYTe) {
        this.tGiaYTe = tGiaYTe;
    }

    public int getTGiaYTe() {
        return tGiaYTe;
    }

    public void setTGiaGiaoDuc(int tGiaGiaoDuc) {
        this.tGiaGiaoDuc = tGiaGiaoDuc;
    }

    public int getTGiaGiaoDuc() {
        return tGiaGiaoDuc;
    }

    public void setLKe(int lKe) {
        this.lKe = lKe;
    }

    public int getLKe() {
        return lKe;
    }

    public void setLKeLaoDong(int lKeLaoDong) {
        this.lKeLaoDong = lKeLaoDong;
    }

    public int getLKeLaoDong() {
        return lKeLaoDong;
    }

    public void setLKeYTe(int lKeYTe) {
        this.lKeYTe = lKeYTe;
    }

    public int getLKeYTe() {
        return lKeYTe;
    }

    public void setLKeGiaoDuc(int lKeGiaoDuc) {
        this.lKeGiaoDuc = lKeGiaoDuc;
    }

    public int getLKeGiaoDuc() {
        return lKeGiaoDuc;
    }

    public void setKThuc(int kThuc) {
        this.kThuc = kThuc;
    }

    public int getKThuc() {
        return kThuc;
    }

    public void setNgNhan1(int ngNhan1) {
        this.ngNhan1 = ngNhan1;
    }

    public int getNgNhan1() {
        return ngNhan1;
    }

    public void setNgNhan2(int ngNhan2) {
        this.ngNhan2 = ngNhan2;
    }

    public int getNgNhan2() {
        return ngNhan2;
    }

    public void setNgNhan3(int ngNhan3) {
        this.ngNhan3 = ngNhan3;
    }

    public int getNgNhan3() {
        return ngNhan3;
    }

    public void setNgNhan4(int ngNhan4) {
        this.ngNhan4 = ngNhan4;
    }

    public int getNgNhan4() {
        return ngNhan4;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setDangTat(String dangTat) {
        this.dangTat = dangTat;
    }

    public String getDangTat() {
        return dangTat;
    }

    public void setMucDoKTat(String mucDoKTat) {
        this.mucDoKTat = mucDoKTat;
    }

    public String getMucDoKTat() {
        return mucDoKTat;
    }

    public void setHoanCanhKTe(String hoanCanhKTe) {
        this.hoanCanhKTe = hoanCanhKTe;
    }

    public String getHoanCanhKTe() {
        return hoanCanhKTe;
    }

    public void setHoTenChuHo(String hoTenChuHo) {
        this.hoTenChuHo = hoTenChuHo;
    }

    public String getHoTenChuHo() {
        return hoTenChuHo;
    }

    public void setTenNgQLy(String tenNgQLy) {
        this.tenNgQLy = tenNgQLy;
    }

    public String getTenNgQLy() {
        return tenNgQLy;
    }

    public void setNktDauKy(String nktDauKy) {
        this.nktDauKy = nktDauKy;
    }

    public String getNktDauKy() {
        return nktDauKy;
    }

    public void setNktDauKyLDong(String nktDauKyLDong) {
        this.nktDauKyLDong = nktDauKyLDong;
    }

    public String getNktDauKyLDong() {
        return nktDauKyLDong;
    }

    public void setNktDauKyYTe(String nktDauKyYTe) {
        this.nktDauKyYTe = nktDauKyYTe;
    }

    public String getNktDauKyYTe() {
        return nktDauKyYTe;
    }

    public void setNktDauKyGDuc(String nktDauKyGDuc) {
        this.nktDauKyGDuc = nktDauKyGDuc;
    }

    public String getNktDauKyGDuc() {
        return nktDauKyGDuc;
    }

    public void setNktTang(int nktTang) {
        this.nktTang = nktTang;
    }

    public int getNktTang() {
        return nktTang;
    }

    public void setNktTangLDong(int nktTangLDong) {
        this.nktTangLDong = nktTangLDong;
    }

    public int getNktTangLDong() {
        return nktTangLDong;
    }

    public void setNktTangYTe(int nktTangYTe) {
        this.nktTangYTe = nktTangYTe;
    }

    public int getNktTangYTe() {
        return nktTangYTe;
    }

    public void setNktTangGDuc(int nktTangGDuc) {
        this.nktTangGDuc = nktTangGDuc;
    }

    public int getNktTangGDuc() {
        return nktTangGDuc;
    }

    public void setNktGiam(int nktGiam) {
        this.nktGiam = nktGiam;
    }

    public int getNktGiam() {
        return nktGiam;
    }

    public void setNktGiamLDong(int nktGiamLDong) {
        this.nktGiamLDong = nktGiamLDong;
    }

    public int getNktGiamLDong() {
        return nktGiamLDong;
    }

    public void setNktGiamYTe(int nktGiamYTe) {
        this.nktGiamYTe = nktGiamYTe;
    }

    public int getNktGiamYTe() {
        return nktGiamYTe;
    }

    public void setNktGiamGDuc(int nktGiamGDuc) {
        this.nktGiamGDuc = nktGiamGDuc;
    }

    public int getNktGiamGDuc() {
        return nktGiamGDuc;
    }

    public void setNktCuoiKy(int nktCuoiKy) {
        this.nktCuoiKy = nktCuoiKy;
    }

    public int getNktCuoiKy() {
        return nktCuoiKy;
    }

    public void setNktCuoiKyLDong(int nktCuoiKyLDong) {
        this.nktCuoiKyLDong = nktCuoiKyLDong;
    }

    public int getNktCuoiKyLDong() {
        return nktCuoiKyLDong;
    }

    public void setNktCuoiKyYTe(int nktCuoiKyYTe) {
        this.nktCuoiKyYTe = nktCuoiKyYTe;
    }

    public int getNktCuoiKyYTe() {
        return nktCuoiKyYTe;
    }

    public void setNktCuoiKyGDuc(int nktCuoiKyGDuc) {
        this.nktCuoiKyGDuc = nktCuoiKyGDuc;
    }

    public int getNktCuoiKyGDuc() {
        return nktCuoiKyGDuc;
    }

    public void setNktTH(int nktTH) {
        this.nktTH = nktTH;
    }

    public int getNktTH() {
        return nktTH;
    }

    public void setNktTHLDong(int nktTHLDong) {
        this.nktTHLDong = nktTHLDong;
    }

    public int getNktTHLDong() {
        return nktTHLDong;
    }

    public void setNktTHYTe(int nktTHYTe) {
        this.nktTHYTe = nktTHYTe;
    }

    public int getNktTHYTe() {
        return nktTHYTe;
    }

    public void setNktTHGDuc(int nktTHGDuc) {
        this.nktTHGDuc = nktTHGDuc;
    }

    public int getNktTHGDuc() {
        return nktTHGDuc;
    }

    public void setNktTC(int nktTC) {
        this.nktTC = nktTC;
    }

    public int getNktTC() {
        return nktTC;
    }

    public void setNktTCLDong(int nktTCLDong) {
        this.nktTCLDong = nktTCLDong;
    }

    public int getNktTCLDong() {
        return nktTCLDong;
    }

    public void setNktTCYTe(int nktTCYTe) {
        this.nktTCYTe = nktTCYTe;
    }

    public int getNktTCYTe() {
        return nktTCYTe;
    }

    public void setNktTCGDuc(int nktTCGDuc) {
        this.nktTCGDuc = nktTCGDuc;
    }

    public int getNktTCGDuc() {
        return nktTCGDuc;
    }

    public void setQlcTH(int qlcTH) {
        this.qlcTH = qlcTH;
    }

    public int getQlcTH() {
        return qlcTH;
    }

    public void setQlcTHLDong(int qlcTHLDong) {
        this.qlcTHLDong = qlcTHLDong;
    }

    public int getQlcTHLDong() {
        return qlcTHLDong;
    }

    public void setQlcTHYTe(int qlcTHYTe) {
        this.qlcTHYTe = qlcTHYTe;
    }

    public int getQlcTHYTe() {
        return qlcTHYTe;
    }

    public void setQlcTHGDuc(int qlcTHGDuc) {
        this.qlcTHGDuc = qlcTHGDuc;
    }

    public int getQlcTHGDuc() {
        return qlcTHGDuc;
    }

    public void setQlcTC(int qlcTC) {
        this.qlcTC = qlcTC;
    }

    public int getQlcTC() {
        return qlcTC;
    }

    public void setQlcTCLDong(int qlcTCLDong) {
        this.qlcTCLDong = qlcTCLDong;
    }

    public int getQlcTCLDong() {
        return qlcTCLDong;
    }

    public void setQlcTCYTe(int qlcTCYTe) {
        this.qlcTCYTe = qlcTCYTe;
    }

    public int getQlcTCYTe() {
        return qlcTCYTe;
    }

    public void setQlcTCGDuc(int qlcTCGDuc) {
        this.qlcTCGDuc = qlcTCGDuc;
    }

    public int getQlcTCGDuc() {
        return qlcTCGDuc;
    }

    public void setQlcDauKy(int qlcDauKy) {
        this.qlcDauKy = qlcDauKy;
    }

    public int getQlcDauKy() {
        return qlcDauKy;
    }

    public void setQlcDauKyLDong(int qlcDauKyLDong) {
        this.qlcDauKyLDong = qlcDauKyLDong;
    }

    public int getQlcDauKyLDong() {
        return qlcDauKyLDong;
    }

    public void setQlcDauKyYTe(int qlcDauKyYTe) {
        this.qlcDauKyYTe = qlcDauKyYTe;
    }

    public int getQlcDauKyYTe() {
        return qlcDauKyYTe;
    }

    public void setQlcDauKyGDuc(int qlcDauKyGDuc) {
        this.qlcDauKyGDuc = qlcDauKyGDuc;
    }

    public int getQlcDauKyGDuc() {
        return qlcDauKyGDuc;
    }

    public void setQlcTang(int qlcTang) {
        this.qlcTang = qlcTang;
    }

    public int getQlcTang() {
        return qlcTang;
    }

    public void setQlcTangLDong(int qlcTangLDong) {
        this.qlcTangLDong = qlcTangLDong;
    }

    public int getQlcTangLDong() {
        return qlcTangLDong;
    }

    public void setQlcTangYTe(int qlcTangYTe) {
        this.qlcTangYTe = qlcTangYTe;
    }

    public int getQlcTangYTe() {
        return qlcTangYTe;
    }

    public void setQlcTangGDuc(int qlcTangGDuc) {
        this.qlcTangGDuc = qlcTangGDuc;
    }

    public int getQlcTangGDuc() {
        return qlcTangGDuc;
    }

    public void setQlcGiam(int qlcGiam) {
        this.qlcGiam = qlcGiam;
    }

    public int getQlcGiam() {
        return qlcGiam;
    }

    public void setQlcGiamLDong(int qlcGiamLDong) {
        this.qlcGiamLDong = qlcGiamLDong;
    }

    public int getQlcGiamLDong() {
        return qlcGiamLDong;
    }

    public void setQlcGiamYTe(int qlcGiamYTe) {
        this.qlcGiamYTe = qlcGiamYTe;
    }

    public int getQlcGiamYTe() {
        return qlcGiamYTe;
    }

    public void setQlcGiamGDuc(int qlcGiamGDuc) {
        this.qlcGiamGDuc = qlcGiamGDuc;
    }

    public int getQlcGiamGDuc() {
        return qlcGiamGDuc;
    }

    public void setQlcCuoiKy(int qlcCuoiKy) {
        this.qlcCuoiKy = qlcCuoiKy;
    }

    public int getQlcCuoiKy() {
        return qlcCuoiKy;
    }

    public void setQlcCuoiKyLDong(int qlcCuoiKyLDong) {
        this.qlcCuoiKyLDong = qlcCuoiKyLDong;
    }

    public int getQlcCuoiKyLDong() {
        return qlcCuoiKyLDong;
    }

    public void setQlcCuoiKyYTe(int qlcCuoiKyYTe) {
        this.qlcCuoiKyYTe = qlcCuoiKyYTe;
    }

    public int getQlcCuoiKyYTe() {
        return qlcCuoiKyYTe;
    }

    public void setQlcCuoiKyGDuc(int qlcCuoiKyGDuc) {
        this.qlcCuoiKyGDuc = qlcCuoiKyGDuc;
    }

    public int getQlcCuoiKyGDuc() {
        return qlcCuoiKyGDuc;
    }
    
    public void setStore2(FBeans store2) {
        this.store2 = store2;
    }

    public FBeans getStore2() {
        return store2;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotalThang(int totalThang) {
        this.totalThang = totalThang;
    }

    public int getTotalThang() {
        return totalThang;
    }

    public void setTotalLuyKe(int totalLuyKe) {
        this.totalLuyKe = totalLuyKe;
    }

    public int getTotalLuyKe() {
        return totalLuyKe;
    }

    public void setTotalXaHoi(int totalXaHoi) {
        this.totalXaHoi = totalXaHoi;
    }

    public int getTotalXaHoi() {
        return totalXaHoi;
    }

    public void setTotalXaHoiThang(int totalXaHoiThang) {
        this.totalXaHoiThang = totalXaHoiThang;
    }

    public int getTotalXaHoiThang() {
        return totalXaHoiThang;
    }

    public void setTotalXaHoiLuyKe(int totalXaHoiLuyKe) {
        this.totalXaHoiLuyKe = totalXaHoiLuyKe;
    }

    public int getTotalXaHoiLuyKe() {
        return totalXaHoiLuyKe;
    }

    public void setTotalYTe(int totalYTe) {
        this.totalYTe = totalYTe;
    }

    public int getTotalYTe() {
        return totalYTe;
    }

    public void setTotalYTeThang(int totalYTeThang) {
        this.totalYTeThang = totalYTeThang;
    }

    public int getTotalYTeThang() {
        return totalYTeThang;
    }

    public void setTotalYTeLuyKe(int totalYTeLuyKe) {
        this.totalYTeLuyKe = totalYTeLuyKe;
    }

    public int getTotalYTeLuyKe() {
        return totalYTeLuyKe;
    }

    public void setTotalGiaoDuc(int totalGiaoDuc) {
        this.totalGiaoDuc = totalGiaoDuc;
    }

    public int getTotalGiaoDuc() {
        return totalGiaoDuc;
    }

    public void setTotalGiaoDucThang(int totalGiaoDucThang) {
        this.totalGiaoDucThang = totalGiaoDucThang;
    }

    public int getTotalGiaoDucThang() {
        return totalGiaoDucThang;
    }

    public void setTotalGiaoDucLuyKe(int totalGiaoDucLuyKe) {
        this.totalGiaoDucLuyKe = totalGiaoDucLuyKe;
    }

    public int getTotalGiaoDucLuyKe() {
        return totalGiaoDucLuyKe;
    }

    public void setStore3(FBeans store3) {
        this.store3 = store3;
    }

    public FBeans getStore3() {
        return store3;
    }
}
