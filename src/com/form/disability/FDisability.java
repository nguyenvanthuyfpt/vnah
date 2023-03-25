package com.form.disability;


import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FDisability extends FSeed {

    private String nguoiQuanLyTen;
    private String nguoiQuanLyDonVi;
    private int nguoiQuanLyLinhVuc;

    private String searchSub;
    private int type;
    private int id;
    private int dtlId;
    private String code;
    private int yearRepor;
    private int indId;
    private int objId;
    private int userId;
    private int inputType;
    private String ma; // So thu tu
    private String ma_nkt; // Mã NKT
    private String nktTen;
    private String nkt;
    private int phanLoaiId;
    private String cmnd;
    private String ngaySinh;
    private int sex;
    private String gioiTinh;
    private String soNha;
    private String thonTo;
    private int tinhId;
    private int districtId;
    private int communeId;
    private String tinhName;
    private int dantocId;
    private String dantocName;
    private FormFile upFile;
    private String fileStore;
    private String fileRead;
    private String chuanDoan;
    private int dieuKienId;
    private String dieuKienName;
    private String phoneNumber;
    private String quanHe;
    private int trinhDoId;

    private int doiTuongId;
    private int nguonNuocId;
    private int nhaOId;

    private int ttHonNhanId;
    private String tdChuyenMon;
    private int ngheNghiepHT;
    private String chucVuHT;
    private String dangTat;
    private String nguyenNhanKT;
    private String thoiDiemKT;
    
    private int visitEmpId;
    private String dangTatIds;
    private int[] phanLoaiIds;
    private String reson;
    private int dinhDang_NS;
    private String dateLastUpdate;

    private String dateCreate;
    private String userCreate;
    private String userLastUpdate;

    private int level;
    private int totalPopulation;

    private int tongSoCon;
    private int tongSoConDuoi16;
    private int chatDocDaCam;
    private int khangChien;

    private String tenChuHo;
    private int namSinhChuHo;
    private int quanHeChuHo;
    private int songuoi_chuho;
    private int soNKT_chuho;
    private int nhaVeSinhChuHo;
    private int nhaVeSinhNKTChuHo;

    private String tenChamSoc;
    private int namSinhChamSoc;
    private int quanHeChamSoc;
    private int gioiTinhChamSoc;
    private String sdtLienLac;

    // Add by ThuyNV
    private String chuyenmonKhac;
    private String nghenghiepKhac;
    private String honnhanKhac;
    private String giaoducKhac; // Chuc vu khac

    private int trocapId;
    private String trocapKhac;
    private int trangthai;
    private int category_id;

    private int kyBC = getMonth(getCurrentSqlDate()) > 6 ? 2 : 1;
    private int namBC = getYear(getCurrentSqlDate());

    private String func;
    private int pageIndex;
    private int totalResult;    
    private int day;
    private String lastDateSupport;
    private String tkThoiDiem;
    private int duAnId;
    private int tkDiaDiem;
    
    private int typeCount = 0;

    public void reset() {
        this.id = 0;
    }

    public int getType() {
        return type;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTinhId() {
        return tinhId;
    }

    public void setTinhId(int tinhId) {
        this.tinhId = tinhId;
    }

    public int getDantocId() {
        return dantocId;
    }

    public void setDantocId(int dantocId) {
        this.dantocId = dantocId;
    }

    public String getNkt() {
        return nkt;
    }

    public void setNkt(String nkt) {
        this.nkt = nkt;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


    public FormFile getUpFile() {
        return upFile;
    }

    public void setUpFile(FormFile upFile) {
        this.upFile = upFile;
    }

    public String getFileStore() {
        return fileStore;
    }

    public void setFileStore(String fileStore) {
        this.fileStore = fileStore;
    }

    public String getFileRead() {
        return fileRead;
    }

    public void setFileRead(String fileRead) {
        this.fileRead = fileRead;
    }

    public String getChuanDoan() {
        return chuanDoan;
    }

    public void setChuanDoan(String chuanDoan) {
        this.chuanDoan = chuanDoan;
    }

    public int getDieuKienId() {
        return dieuKienId;
    }

    public void setDieuKienId(int dieuKienId) {
        this.dieuKienId = dieuKienId;
    }

    public String getSoNha() {
        return soNha;
    }

    public void setSoNha(String soNha) {
        this.soNha = soNha;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTenNKT() {
        return nktTen;
    }

    public String getDangTatIds() {
        return dangTatIds;
    }

    public void setDangTatIds(String dangTatIds) {
        this.dangTatIds = dangTatIds;
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

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public int getPhanLoaiId() {
        return phanLoaiId;
    }

    public void setPhanLoaiId(int phanLoaiId) {
        this.phanLoaiId = phanLoaiId;
    }


    public int getTrinhDoId() {
        return trinhDoId;
    }

    public void setTrinhDoId(int trinhDoId) {
        this.trinhDoId = trinhDoId;
    }


    public int getVisitEmpId() {
        return visitEmpId;
    }

    public void setVisitEmpId(int visitEmpId) {
        this.visitEmpId = visitEmpId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getDinhDang_NS() {
        return dinhDang_NS;
    }

    public void setDinhDang_NS(int dinhDang_NS) {
        this.dinhDang_NS = dinhDang_NS;
    }

    public String getDateLastUpdate() {
        return dateLastUpdate;
    }

    public void setDateLastUpdate(String dateLastUpdate) {
        this.dateLastUpdate = dateLastUpdate;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public void setTotalPopulation(int totalPopulation) {
        this.totalPopulation = totalPopulation;
    }

    public int getTtHonNhanId() {
        return ttHonNhanId;
    }

    public void setTtHonNhanId(int ttHonNhanId) {
        this.ttHonNhanId = ttHonNhanId;
    }

    public String getTdChuyenMon() {
        return tdChuyenMon;
    }

    public void setTdChuyenMon(String tdChuyenMon) {
        this.tdChuyenMon = tdChuyenMon;
    }

    public int getNgheNghiepHT() {
        return ngheNghiepHT;
    }

    public void setNgheNghiepHT(int ngheNghiepHT) {
        this.ngheNghiepHT = ngheNghiepHT;
    }

    public String getChucVuHT() {
        return chucVuHT;
    }

    public void setChucVuHT(String chucVuHT) {
        this.chucVuHT = chucVuHT;
    }

    public String getSearchSub() {
        return searchSub;
    }

    public void setSearchSub(String searchSub) {
        this.searchSub = searchSub;
    }


    public String getDangTat() {
        return dangTat;
    }

    public void setDangTat(String dangTat) {
        this.dangTat = dangTat;
    }

    public String getNguyenNhanKT() {
        return nguyenNhanKT;
    }

    public void setNguyenNhanKT(String nguyenNhanKT) {
        this.nguyenNhanKT = nguyenNhanKT;
    }

    public String getThoiDiemKT() {
        return thoiDiemKT;
    }

    public void setThoiDiemKT(String thoiDiemKT) {
        this.thoiDiemKT = thoiDiemKT;
    }

    public String getDantocName() {
        return dantocName;
    }

    public void setDantocName(String dantocName) {
        this.dantocName = dantocName;
    }

    public String getDieuKienName() {
        return dieuKienName;
    }

    public void setDieuKienName(String dieuKienName) {
        this.dieuKienName = dieuKienName;
    }

    public String getQuanHe() {
        return quanHe;
    }

    public void setQuanHe(String quanHe) {
        this.quanHe = quanHe;
    }

    public int getNguonNuocId() {
        return nguonNuocId;
    }

    public void setNguonNuocId(int nguonNuocId) {
        this.nguonNuocId = nguonNuocId;
    }

    public int getNhaOId() {
        return nhaOId;
    }

    public void setNhaOId(int nhaOId) {
        this.nhaOId = nhaOId;
    }

    public String getTenChuHo() {
        return tenChuHo;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }


    public int getQuanHeChuHo() {
        return quanHeChuHo;
    }

    public void setQuanHeChuHo(int quanHeChuHo) {
        this.quanHeChuHo = quanHeChuHo;
    }

    public String getTenChamSoc() {
        return tenChamSoc;
    }

    public void setTenChamSoc(String tenChamSoc) {
        this.tenChamSoc = tenChamSoc;
    }

    public int getQuanHeChamSoc() {
        return quanHeChamSoc;
    }

    public void setQuanHeChamSoc(int quanHeChamSoc) {
        this.quanHeChamSoc = quanHeChamSoc;
    }

    public int getNhaVeSinhChuHo() {
        return nhaVeSinhChuHo;
    }

    public void setNhaVeSinhChuHo(int nhaVeSinhChuHo) {
        this.nhaVeSinhChuHo = nhaVeSinhChuHo;
    }

    public int getNhaVeSinhNKTChuHo() {
        return nhaVeSinhNKTChuHo;
    }

    public void setNhaVeSinhNKTChuHo(int nhaVeSinhNKTChuHo) {
        this.nhaVeSinhNKTChuHo = nhaVeSinhNKTChuHo;
    }

    public int getChatDocDaCam() {
        return chatDocDaCam;
    }

    public void setChatDocDaCam(int chatDocDaCam) {
        this.chatDocDaCam = chatDocDaCam;
    }

    public int getTongSoCon() {
        return tongSoCon;
    }

    public void setTongSoCon(int tongSoCon) {
        this.tongSoCon = tongSoCon;
    }

    public int getTongSoConDuoi16() {
        return tongSoConDuoi16;
    }

    public void setTongSoConDuoi16(int tongSoConDuoi16) {
        this.tongSoConDuoi16 = tongSoConDuoi16;
    }

    public String getNguoiQuanLyTen() {
        return nguoiQuanLyTen;
    }

    public void setNguoiQuanLyTen(String nguoiQuanLyTen) {
        this.nguoiQuanLyTen = nguoiQuanLyTen;
    }

    public String getNguoiQuanLyDonVi() {
        return nguoiQuanLyDonVi;
    }

    public void setNguoiQuanLyDonVi(String nguoiQuanLyDonVi) {
        this.nguoiQuanLyDonVi = nguoiQuanLyDonVi;
    }

    public int getSonguoi_chuho() {
        return songuoi_chuho;
    }

    public void setSonguoi_chuho(int songuoi_chuho) {
        this.songuoi_chuho = songuoi_chuho;
    }

    public int getSoNKT_chuho() {
        return soNKT_chuho;
    }

    public void setSoNKT_chuho(int soNKT_chuho) {
        this.soNKT_chuho = soNKT_chuho;
    }

    public int getNamSinhChuHo() {
        return namSinhChuHo;
    }

    public void setNamSinhChuHo(int namSinhChuHo) {
        this.namSinhChuHo = namSinhChuHo;
    }

    public int getNamSinhChamSoc() {
        return namSinhChamSoc;
    }

    public void setNamSinhChamSoc(int namSinhChamSoc) {
        this.namSinhChamSoc = namSinhChamSoc;
    }

    public int getKyBC() {
        return kyBC;
    }

    public void setKyBC(int kyBC) {
        this.kyBC = kyBC;
    }

    public int getNamBC() {
        return namBC;
    }

    public void setNamBC(int namBC) {
        this.namBC = namBC;
    }

    public String getTinhName() {
        return tinhName;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    public String getSdtLienLac() {
        return sdtLienLac;
    }

    public void setSdtLienLac(String sdtLienLac) {
        this.sdtLienLac = sdtLienLac;
    }

    public void setChuyenMonKhac(String chuyenmonKhac) {
        this.chuyenmonKhac = chuyenmonKhac;
    }

    public String getChuyenMonKhac() {
        return chuyenmonKhac;
    }

    public void setNgheNghiepKhac(String nghenghiepKhac) {
        this.nghenghiepKhac = nghenghiepKhac;
    }

    public String getNgheNghiepKhac() {
        return nghenghiepKhac;
    }

    public void setHonNhanKhac(String honnhanKhac) {
        this.honnhanKhac = honnhanKhac;
    }

    public String getHonNhanKhac() {
        return honnhanKhac;
    }

    public void setGiaoDucKhac(String giaoducKhac) {
        this.giaoducKhac = giaoducKhac;
    }

    public String getGiaoDucKhac() {
        return giaoducKhac;
    }

    public void setTroCapKhac(String trocapKhac) {
        this.trocapKhac = trocapKhac;
    }

    public String getTroCapKhac() {
        return trocapKhac;
    }

    public void setTroCapId(int trocapId) {
        this.trocapId = trocapId;
    }

    public int getTroCapId() {
        return trocapId;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setThonTo(String thonTo) {
        this.thonTo = thonTo;
    }

    public String getThonTo() {
        return thonTo;
    }

    public void setMa_nkt(String ma_nkt) {
        this.ma_nkt = ma_nkt;
    }

    public String getMa_nkt() {
        return ma_nkt;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setFunc(String func) {
        this.func = func;
    }

    public String getFunc() {
        return func;
    }

    public void setNktTen(String nktTen) {
        this.nktTen = nktTen;
    }

    public void setKhangChien(int khangChien) {
        this.khangChien = khangChien;
    }

    public int getKhangChien() {
        return khangChien;
    }

    public void setNguoiQuanLyLinhVuc(int nguoiQuanLyLinhVuc) {
        this.nguoiQuanLyLinhVuc = nguoiQuanLyLinhVuc;
    }

    public int getNguoiQuanLyLinhVuc() {
        return nguoiQuanLyLinhVuc;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getUserCreate() {
        return userCreate;
    }

    public void setUserLastUpdate(String userLastUpdate) {
        this.userLastUpdate = userLastUpdate;
    }

    public String getUserLastUpdate() {
        return userLastUpdate;
    }

    public void setDoiTuongId(int doiTuongId) {
        this.doiTuongId = doiTuongId;
    }

    public int getDoiTuongId() {
        return doiTuongId;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public int getInputType() {
        return inputType;
    }

    public void setGioiTinhChamSoc(int gioiTinhChamSoc) {
        this.gioiTinhChamSoc = gioiTinhChamSoc;
    }

    public int getGioiTinhChamSoc() {
        return gioiTinhChamSoc;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setYearRepor(int yearRepor) {
        this.yearRepor = yearRepor;
    }

    public int getYearRepor() {
        return yearRepor;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return day;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setTypeCount(int typeCount) {
        this.typeCount = typeCount;
    }

    public int getTypeCount() {
        return typeCount;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setLastDateSupport(String lastDateSupport) {
        this.lastDateSupport = lastDateSupport;
    }

    public String getLastDateSupport() {
        return lastDateSupport;
    }

    public void setTkThoiDiem(String tkThoiDiem) {
        this.tkThoiDiem = tkThoiDiem;
    }

    public String getTkThoiDiem() {
        return tkThoiDiem;
    }

    public void setTkDiaDiem(int tkDiaDiem) {
        this.tkDiaDiem = tkDiaDiem;
    }

    public int getTkDiaDiem() {
        return tkDiaDiem;
    }

    public void setDuAnId(int duAnId) {
        this.duAnId = duAnId;
    }

    public int getDuAnId() {
        return duAnId;
    }
}
