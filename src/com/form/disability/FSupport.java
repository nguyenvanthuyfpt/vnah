package com.form.disability;


import com.form.FSeed;

import com.util.Formater;

public class FSupport extends FSeed {

    private int id;
    private int parentId;
    private int statusId;
    private String mode;
    private String createDate = Formater.date2str(getCurrentDate());

    private String supportSel;
    private int idNkt;
    private int userId;
    private String fullName;
    private String dateCreate = Formater.date2str(getCurrentDate());
    private String reson;
    private String nguonhotro;
    private int nguonId;
    private String hotroIds;
    private int[] supportIds;
    private String dateForm = Formater.date2str(getCurrentDate());
    private String dateTo = Formater.date2str(getCurrentDate());

    private String nCauHtro;
    private int nguonhotroId;

    private String dangDuocHTCuThe;
    private String dangDuocHTNguonHT;
    private String dangDuocHTNgay;

    private String duocHTCuThe;
    private String duocHTNguonHT;
    private String duocHTNgay;

    // Add by ThuyNV
    private String macbenh;
    private String dungcuKhac;
    private String phcnKhac;
    private String phauthuatKhac;
    private String yteKhac;
    private String trocapThuongXuyenKhac;
    private String trocapDotXuatKhac;
    private String caithienKhac;
    private String loaivayKhac;
    private String sotienvayKhac;
    private String mucdichvayKhac;
    private String tochucXaHoiKhac;
    private String nhucauDoiSongKhac;
    private String nhucauGiaoDucKhac;

    private String thoiDiemTK = "";
    private int diaDiemKham;
    private int baoQuanDC;
    private int doiTuong;

    // Add for KPI
    private int knChiTra;
    private int theBhyte;
    private int sdThe;
    private int sdThePhcn;
    private String mtieuGdinh;
    private String mtieuDtri;
    private String ctVltl;
    private String ctHdtl;
    private String ctAntl;

    private String ctGddb;
    private String ctCsgn;

    private String mdoPtdl;
    private String mdoHlong;

    private int spVnah;
    private int spTtp;
    private int spQhu;
    private int spPxa;

    private String sp1;
    private String sp2;
    private String sp3;
    private String sp4;
    private String sp5;
    
    private int stt;
    private String nguoiTHTen;
    private int nguoiTHCv;
    
    private String loaiNhuCau;
    private String tenNhuCau;
    
    private int hasVisit;
    private String startAt;
    private String endAt;
    private String latitude;
    private String longitude;
    private String location;
    
    public void reset() {
        this.id = 0;
        this.dateCreate = Formater.date2str(getCurrentDate());
        this.createDate = Formater.date2str(getCurrentDate());
        this.dateForm = Formater.date2str(getCurrentDate());
        this.dateTo = Formater.date2str(getCurrentDate());
        this.reson = "";
        this.hotroIds = "";
        this.supportIds = null;
        this.macbenh = "";
        this.dungcuKhac = "";
        this.yteKhac = "";
        this.trocapDotXuatKhac = "";
        this.trocapThuongXuyenKhac = "";
        this.caithienKhac = "";
        this.loaivayKhac = "";
        this.sotienvayKhac = "";
        this.mucdichvayKhac = "";
        this.nhucauDoiSongKhac = "";
        this.nhucauGiaoDucKhac = "";
        this.nguonhotroId = 0;
        this.nguonhotro = "";

        this.knChiTra = 0;
        this.theBhyte = 0;
        this.sdThe = 0;
        this.sdThePhcn = 0;
        this.mtieuGdinh = "";
        this.mtieuDtri = "";
        this.ctVltl = "";
        this.ctHdtl = "";
        this.diaDiemKham = 0;
        this.thoiDiemTK = "";
        this.doiTuong = -1;
        this.stt = 0;
        this.hotroIds = "";
        this.nguoiTHCv = -1;
        this.nguoiTHTen = "";
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
    
    

    /*
    public String getHotroIds() {
        return hotroIds;
    }

    public void setHotroIds(String hotroIds) {
        if (hotroIds.length() > 3) {
            this.hotroIds = hotroIds.replaceAll("#0#", "#");
        } else {
            this.hotroIds = "";
        }
    }

    public int[] getSupportIds() {
        return supportIds;
    }

    public void setSupportIds(int[] supportIds) {
        if (supportIds != null && supportIds.length > 0) {
            String hotroIds = "#";
            for (int i = 0; i < supportIds.length; i++) {
                hotroIds += supportIds[i] + "#";
            }
            setHotroIds(hotroIds);
        }
        this.supportIds = supportIds;
    }
    */


    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDateForm() {
        return dateForm;
    }

    public void setDateForm(String dateForm) {
        this.dateForm = dateForm;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getNguonhotro() {
        return nguonhotro;
    }

    public void setNguonhotro(String nguonhotro) {
        this.nguonhotro = nguonhotro;
    }

    public String getDangDuocHTCuThe() {
        return dangDuocHTCuThe;
    }

    public void setDangDuocHTCuThe(String dangDuocHTCuThe) {
        this.dangDuocHTCuThe = dangDuocHTCuThe;
    }

    public String getDangDuocHTNguonHT() {
        return dangDuocHTNguonHT;
    }

    public void setDangDuocHTNguonHT(String dangDuocHTNguonHT) {
        this.dangDuocHTNguonHT = dangDuocHTNguonHT;
    }

    public String getDangDuocHTNgay() {
        return dangDuocHTNgay;
    }

    public void setDangDuocHTNgay(String dangDuocHTNgay) {
        this.dangDuocHTNgay = dangDuocHTNgay;
    }

    public String getDuocHTCuThe() {
        return duocHTCuThe;
    }

    public void setDuocHTCuThe(String duocHTCuThe) {
        this.duocHTCuThe = duocHTCuThe;
    }

    public String getDuocHTNguonHT() {
        return duocHTNguonHT;
    }

    public void setDuocHTNguonHT(String duocHTNguonHT) {
        this.duocHTNguonHT = duocHTNguonHT;
    }

    public String getDuocHTNgay() {
        return duocHTNgay;
    }

    public void setDuocHTNgay(String duocHTNgay) {
        this.duocHTNgay = duocHTNgay;
    }

    public int getNguonHoTroId() {
        return nguonhotroId;
    }

    public void setNguonHoTroId(int nguonhotroId) {
        this.nguonhotroId = nguonhotroId;
    }

    public void setMacbenh(String macbenh) {
        this.macbenh = macbenh;
    }

    public String getMacbenh() {
        return macbenh;
    }

    public void setDungcuKhac(String dungcuKhac) {
        this.dungcuKhac = dungcuKhac;
    }

    public String getDungcuKhac() {
        return dungcuKhac;
    }

    public void setPhauthuatKhac(String phauthuatKhac) {
        this.phauthuatKhac = phauthuatKhac;
    }

    public String getPhauthuatKhac() {
        return phauthuatKhac;
    }

    public void setYteKhac(String yteKhac) {
        this.yteKhac = yteKhac;
    }

    public String getYteKhac() {
        return yteKhac;
    }

    public void setTrocapThuongXuyenKhac(String trocapThuongXuyenKhac) {
        this.trocapThuongXuyenKhac = trocapThuongXuyenKhac;
    }

    public String getTrocapThuongXuyenKhac() {
        return trocapThuongXuyenKhac;
    }

    public void setTrocapDotXuatKhac(String trocapDotXuatKhac) {
        this.trocapDotXuatKhac = trocapDotXuatKhac;
    }

    public String getTrocapDotXuatKhac() {
        return trocapDotXuatKhac;
    }

    public void setCaithienKhac(String caithienKhac) {
        this.caithienKhac = caithienKhac;
    }

    public String getCaithienKhac() {
        return caithienKhac;
    }

    public void setLoaivayKhac(String loaivayKhac) {
        this.loaivayKhac = loaivayKhac;
    }

    public String getLoaivayKhac() {
        return loaivayKhac;
    }

    public void setSotienvayKhac(String sotienvayKhac) {
        this.sotienvayKhac = sotienvayKhac;
    }

    public String getSotienvayKhac() {
        return sotienvayKhac;
    }

    public void setMucdichvayKhac(String mucdichvayKhac) {
        this.mucdichvayKhac = mucdichvayKhac;
    }

    public String getMucdichvayKhac() {
        return mucdichvayKhac;
    }

    public void setTochucXaHoiKhac(String tochucXaHoiKhac) {
        this.tochucXaHoiKhac = tochucXaHoiKhac;
    }

    public String getTochucXaHoiKhac() {
        return tochucXaHoiKhac;
    }

    public void setNhucauDoiSongKhac(String nhucauDoiSongKhac) {
        this.nhucauDoiSongKhac = nhucauDoiSongKhac;
    }

    public String getNhucauDoiSongKhac() {
        return nhucauDoiSongKhac;
    }

    public void setNhucauGiaoDucKhac(String nhucauGiaoDucKhac) {
        this.nhucauGiaoDucKhac = nhucauGiaoDucKhac;
    }

    public String getNhucauGiaoDucKhac() {
        return nhucauGiaoDucKhac;
    }

    public void setHotroIds(String hotroIds) {
        this.hotroIds = hotroIds;
    }

    public String getHotroIds() {
        return hotroIds;
    }

    public void setSupportIds(int[] supportIds) {
        this.supportIds = supportIds;
    }

    public int[] getSupportIds() {
        return supportIds;
    }

    public void setKnChiTra(int knChiTra) {
        this.knChiTra = knChiTra;
    }

    public int getKnChiTra() {
        return knChiTra;
    }

    public void setTheBhyte(int theBhyte) {
        this.theBhyte = theBhyte;
    }

    public int getTheBhyte() {
        return theBhyte;
    }

    public void setSdThe(int sdThe) {
        this.sdThe = sdThe;
    }

    public int getSdThe() {
        return sdThe;
    }

    public void setSdThePhcn(int sdThePhcn) {
        this.sdThePhcn = sdThePhcn;
    }

    public int getSdThePhcn() {
        return sdThePhcn;
    }

    public void setMtieuGdinh(String mtieuGdinh) {
        this.mtieuGdinh = mtieuGdinh;
    }

    public String getMtieuGdinh() {
        return mtieuGdinh;
    }

    public void setMtieuDtri(String mtieuDtri) {
        this.mtieuDtri = mtieuDtri;
    }

    public String getMtieuDtri() {
        return mtieuDtri;
    }

    public void setCtVltl(String ctVltl) {
        this.ctVltl = ctVltl;
    }

    public String getCtVltl() {
        return ctVltl;
    }

    public void setCtHdtl(String ctHdtl) {
        this.ctHdtl = ctHdtl;
    }

    public String getCtHdtl() {
        return ctHdtl;
    }

    public void setMdoPtdl(String mdoPtdl) {
        this.mdoPtdl = mdoPtdl;
    }

    public String getMdoPtdl() {
        return mdoPtdl;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }

    public void setMdoHlong(String mdoHlong) {
        this.mdoHlong = mdoHlong;
    }

    public String getMdoHlong() {
        return mdoHlong;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setNCauHtro(String nCauHtro) {
        this.nCauHtro = nCauHtro;
    }

    public String getNCauHtro() {
        return nCauHtro;
    }

    public void setSupportSel(String supportSel) {
        this.supportSel = supportSel;
    }

    public String getSupportSel() {
        return supportSel;
    }

    public void setCtAntl(String ctAntl) {
        this.ctAntl = ctAntl;
    }

    public String getCtAntl() {
        return ctAntl;
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

    public void setDoiTuong(int doiTuong) {
        this.doiTuong = doiTuong;
    }

    public int getDoiTuong() {
        return doiTuong;
    }

    public void setBaoQuanDC(int baoQuanDC) {
        this.baoQuanDC = baoQuanDC;
    }

    public int getBaoQuanDC() {
        return baoQuanDC;
    }

    public void setSpVnah(int spVnah) {
        this.spVnah = spVnah;
    }

    public int getSpVnah() {
        return spVnah;
    }

    public void setSpTtp(int spTtp) {
        this.spTtp = spTtp;
    }

    public int getSpTtp() {
        return spTtp;
    }

    public void setSpQhu(int spQhu) {
        this.spQhu = spQhu;
    }

    public int getSpQhu() {
        return spQhu;
    }

    public void setSpPxa(int spPxa) {
        this.spPxa = spPxa;
    }

    public int getSpPxa() {
        return spPxa;
    }

    public void setSp1(String sp1) {
        this.sp1 = sp1;
    }

    public String getSp1() {
        return sp1;
    }

    public void setSp2(String sp2) {
        this.sp2 = sp2;
    }

    public String getSp2() {
        return sp2;
    }

    public void setSp3(String sp3) {
        this.sp3 = sp3;
    }

    public String getSp3() {
        return sp3;
    }

    public void setSp4(String sp4) {
        this.sp4 = sp4;
    }

    public String getSp4() {
        return sp4;
    }

    public void setSp5(String sp5) {
        this.sp5 = sp5;
    }

    public String getSp5() {
        return sp5;
    }


    public void setCtGddb(String ctGddb) {
        this.ctGddb = ctGddb;
    }

    public String getCtGddb() {
        return ctGddb;
    }

    public void setCtCsgn(String ctCsgn) {
        this.ctCsgn = ctCsgn;
    }

    public String getCtCsgn() {
        return ctCsgn;
    }

    public void setLoaiNhuCau(String loaiNhuCau) {
        this.loaiNhuCau = loaiNhuCau;
    }

    public String getLoaiNhuCau() {
        return loaiNhuCau;
    }

    public void setTenNhuCau(String tenNhuCau) {
        this.tenNhuCau = tenNhuCau;
    }

    public String getTenNhuCau() {
        return tenNhuCau;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public int getStt() {
        return stt;
    }

    public void setNguonId(int nguonId) {
        this.nguonId = nguonId;
    }

    public int getNguonId() {
        return nguonId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setPhcnKhac(String phcnKhac) {
        this.phcnKhac = phcnKhac;
    }

    public String getPhcnKhac() {
        return phcnKhac;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setHasVisit(int hasVisit) {
        this.hasVisit = hasVisit;
    }

    public int getHasVisit() {
        return hasVisit;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setNguoiTHTen(String nguoiTHTen) {
        this.nguoiTHTen = nguoiTHTen;
    }

    public String getNguoiTHTen() {
        return nguoiTHTen;
    }

    public void setNguoiTHCv(int nguoiTHCv) {
        this.nguoiTHCv = nguoiTHCv;
    }

    public int getNguoiTHCv() {
        return nguoiTHCv;
    }
}
