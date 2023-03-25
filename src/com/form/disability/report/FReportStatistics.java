package com.form.disability.report;


import com.form.FBeans;
import com.form.FSeed;

public class FReportStatistics extends FSeed {
    private String tinhId;
    private String diaBan;
    private String maNKT;
    private String hotenNKT;
    private String ngaySinh;
    private String gioiTinh;
    private String tuyen;
    private String diaChi;
    private String dangTat;
    private String mucDo;
    private String quanLyCa;

    private String gducLopHoaNhap;
    private String gducLopChuyenBiet;
    private String gducLopBanChuyenBiet;
    private String gducHTroHocPhi;
    private String gducHTroHocPhuDao;
    private String gducHTroDCuHocTap;
    private String gducHTroQAo;
    private String gducHTroKhac;

    private String xhoiTCapThuongXuyen;
    private String xhoiTCapDotXuat;
    private String xhoiHTroNhaO;
    private String xhoiCThienVSNS;
    private String xhoiDTaoNghe;
    private String xhoiViecLam;
    private String xhoiTuDoanh;
    private String xhoiVayVon;
    private String xhoiTGiaTCXH;
    private String xhoiTGiaCSTKT;
    private String xhoiCSocCSBTXH;

    private String yteKhamXDKT;
    private String yteTheBHYT;
    private String yteTapPHCN;
    private String yteKhamBenh;
    private String yteDCuPHCN;
    private String ytePThuatChinhHinh;
    private String yteKhac;

    private String bdongPSinhMoi;
    private String bdongPSinhChuyenDen;
    private String bdongDaHoaNhap;
    private String bdongBoCuoc;
    private String bdongChuyenDi;
    private String bdongXoa;
    private String bdongChet;

    private int total;
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


    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setTuyen(String tuyen) {
        this.tuyen = tuyen;
    }

    public String getTuyen() {
        return tuyen;
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
    
    

    public void setGducLopHoaNhap(String gducLopHoaNhap) {
        this.gducLopHoaNhap = gducLopHoaNhap;
    }

    public String getGducLopHoaNhap() {
        return gducLopHoaNhap;
    }

    public void setGducLopChuyenBiet(String gducLopChuyenBiet) {
        this.gducLopChuyenBiet = gducLopChuyenBiet;
    }

    public String getGducLopChuyenBiet() {
        return gducLopChuyenBiet;
    }

    public void setGducLopBanChuyenBiet(String gducLopBanChuyenBiet) {
        this.gducLopBanChuyenBiet = gducLopBanChuyenBiet;
    }

    public String getGducLopBanChuyenBiet() {
        return gducLopBanChuyenBiet;
    }

    public void setGducHTroHocPhi(String gducHTroHocPhi) {
        this.gducHTroHocPhi = gducHTroHocPhi;
    }

    public String getGducHTroHocPhi() {
        return gducHTroHocPhi;
    }

    public void setGducHTroHocPhuDao(String gducHTroHocPhuDao) {
        this.gducHTroHocPhuDao = gducHTroHocPhuDao;
    }

    public String getGducHTroHocPhuDao() {
        return gducHTroHocPhuDao;
    }

    public void setGducHTroDCuHocTap(String gducHTroDCuHocTap) {
        this.gducHTroDCuHocTap = gducHTroDCuHocTap;
    }

    public String getGducHTroDCuHocTap() {
        return gducHTroDCuHocTap;
    }

    public void setGducHTroQAo(String gducHTroQAo) {
        this.gducHTroQAo = gducHTroQAo;
    }

    public String getGducHTroQAo() {
        return gducHTroQAo;
    }

    public void setGducHTroKhac(String gducHTroKhac) {
        this.gducHTroKhac = gducHTroKhac;
    }

    public String getGducHTroKhac() {
        return gducHTroKhac;
    }

    public void setXhoiTCapThuongXuyen(String xhoiTCapThuongXuyen) {
        this.xhoiTCapThuongXuyen = xhoiTCapThuongXuyen;
    }

    public String getXhoiTCapThuongXuyen() {
        return xhoiTCapThuongXuyen;
    }

    public void setXhoiTCapDotXuat(String xhoiTCapDotXuat) {
        this.xhoiTCapDotXuat = xhoiTCapDotXuat;
    }

    public String getXhoiTCapDotXuat() {
        return xhoiTCapDotXuat;
    }

    public void setXhoiHTroNhaO(String xhoiHTroNhaO) {
        this.xhoiHTroNhaO = xhoiHTroNhaO;
    }

    public String getXhoiHTroNhaO() {
        return xhoiHTroNhaO;
    }

    public void setXhoiCThienVSNS(String xhoiCThienVSNS) {
        this.xhoiCThienVSNS = xhoiCThienVSNS;
    }

    public String getXhoiCThienVSNS() {
        return xhoiCThienVSNS;
    }

    public void setXhoiViecLam(String xhoiViecLam) {
        this.xhoiViecLam = xhoiViecLam;
    }

    public String getXhoiViecLam() {
        return xhoiViecLam;
    }

    public void setXhoiTuDoanh(String xhoiTuDoanh) {
        this.xhoiTuDoanh = xhoiTuDoanh;
    }

    public String getXhoiTuDoanh() {
        return xhoiTuDoanh;
    }

    public void setXhoiVayVon(String xhoiVayVon) {
        this.xhoiVayVon = xhoiVayVon;
    }

    public String getXhoiVayVon() {
        return xhoiVayVon;
    }

    public void setXhoiTGiaTCXH(String xhoiTGiaTCXH) {
        this.xhoiTGiaTCXH = xhoiTGiaTCXH;
    }

    public String getXhoiTGiaTCXH() {
        return xhoiTGiaTCXH;
    }

    public void setXhoiTGiaCSTKT(String xhoiTGiaCSTKT) {
        this.xhoiTGiaCSTKT = xhoiTGiaCSTKT;
    }

    public String getXhoiTGiaCSTKT() {
        return xhoiTGiaCSTKT;
    }

    public void setXhoiCSocCSBTXH(String xhoiCSocCSBTXH) {
        this.xhoiCSocCSBTXH = xhoiCSocCSBTXH;
    }

    public String getXhoiCSocCSBTXH() {
        return xhoiCSocCSBTXH;
    }

    public void setYteKhamXDKT(String yteKhamXDKT) {
        this.yteKhamXDKT = yteKhamXDKT;
    }

    public String getYteKhamXDKT() {
        return yteKhamXDKT;
    }

    public void setYteTheBHYT(String yteTheBHYT) {
        this.yteTheBHYT = yteTheBHYT;
    }

    public String getYteTheBHYT() {
        return yteTheBHYT;
    }

    public void setYteTapPHCN(String yteTapPHCN) {
        this.yteTapPHCN = yteTapPHCN;
    }

    public String getYteTapPHCN() {
        return yteTapPHCN;
    }

    public void setYteKhamBenh(String yteKhamBenh) {
        this.yteKhamBenh = yteKhamBenh;
    }

    public String getYteKhamBenh() {
        return yteKhamBenh;
    }

    public void setYteDCuPHCN(String yteDCuPHCN) {
        this.yteDCuPHCN = yteDCuPHCN;
    }

    public String getYteDCuPHCN() {
        return yteDCuPHCN;
    }

    public void setYtePThuatChinhHinh(String ytePThuatChinhHinh) {
        this.ytePThuatChinhHinh = ytePThuatChinhHinh;
    }

    public String getYtePThuatChinhHinh() {
        return ytePThuatChinhHinh;
    }

    public void setYteKhac(String yteKhac) {
        this.yteKhac = yteKhac;
    }

    public String getYteKhac() {
        return yteKhac;
    }

    public void setBdongPSinhMoi(String bdongPSinhMoi) {
        this.bdongPSinhMoi = bdongPSinhMoi;
    }

    public String getBdongPSinhMoi() {
        return bdongPSinhMoi;
    }

    public void setBdongPSinhChuyenDen(String bdongPSinhChuyenDen) {
        this.bdongPSinhChuyenDen = bdongPSinhChuyenDen;
    }

    public String getBdongPSinhChuyenDen() {
        return bdongPSinhChuyenDen;
    }

    public void setBdongDaHoaNhap(String bdongDaHoaNhap) {
        this.bdongDaHoaNhap = bdongDaHoaNhap;
    }

    public String getBdongDaHoaNhap() {
        return bdongDaHoaNhap;
    }

    public void setBdongBoCuoc(String bdongBoCuoc) {
        this.bdongBoCuoc = bdongBoCuoc;
    }

    public String getBdongBoCuoc() {
        return bdongBoCuoc;
    }

    public void setBdongChuyenDi(String bdongChuyenDi) {
        this.bdongChuyenDi = bdongChuyenDi;
    }

    public String getBdongChuyenDi() {
        return bdongChuyenDi;
    }

    public void setBdongXoa(String bdongXoa) {
        this.bdongXoa = bdongXoa;
    }

    public String getBdongXoa() {
        return bdongXoa;
    }

    public void setBdongChet(String bdongChet) {
        this.bdongChet = bdongChet;
    }

    public String getBdongChet() {
        return bdongChet;
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

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setXhoiDTaoNghe(String xhoiDTaoNghe) {
        this.xhoiDTaoNghe = xhoiDTaoNghe;
    }

    public String getXhoiDTaoNghe() {
        return xhoiDTaoNghe;
    }

    public void setMucDo(String mucDo) {
        this.mucDo = mucDo;
    }

    public String getMucDo() {
        return mucDo;
    }

    public void setQuanLyCa(String quanLyCa) {
        this.quanLyCa = quanLyCa;
    }

    public String getQuanLyCa() {
        return quanLyCa;
    }
}
