package com.form.disability.search;


import com.form.FBeans;
import com.form.FSeed;

import java.util.Map;

public class FSearch extends FSeed {

    private int id;
    private int rank_id;
    private int ttHoTro;
    private int typeResult;
    private int roleId;
    private int exportTemp = 0;
    private String ma;
    private String maSo;
    private String nkt;
    private String trangthai;
    private String duAnHT;
    private String ngayDongHS;
    private String lydoDongHS;
    private String nguoiDongHS;
    private String cmnd;
    private String ngaySinh;
    private String namSinh;
    private String ngaySinhTu = null;
    private String ngaySinhDen = null;

    private String ngayTaiKhamTu = null;
    private String ngayTaiKhamDen = null;

    private String ngayCapNhatTu = null;
    private String ngayCapNhatDen = null;

    private String ngayLapTu = null;
    private String ngayLapDen = null;

    private int sex = -1;
    private String gioiTinh;
    private int soNKT = -1;
    private String soNha;
    private String thonTo;
    private int tinhId;
    private int qhuyenId;
    private int pxaId;
    private String tinhName;
    private String huyenName;
    private String pxaName;
    private String chuanDoan;
    private String searchHTVaDT;

    // NhuCau-HoTro
    private String ncauName;
    private String ncauDungCuKhac;
    private String ncauCreateDate;
    private String htroName;
    private String htroCreateDate;
    private String htroTgianNhan;
    private int nguonHotroId;
    private int[] nguonHoTroIds;
    private String nguonHoTro;
    private int knChiTra;
    private String knChiTraName;
    private int theBhyte;
    private String theBhyteName;
    private int sdThe;
    private String sdTheName;
    private int sdThePhcn;
    private String sdThePhcnName;
    private String mtieuGdinh;
    private String mtieuDtri;
    private String ctVltl;
    private String ctHdtl;
    private String ctAntl;
    private String mdoPtdl;
    private String mdoHlong;
    private String dungCuKhac;
    private String htroDiaDiemTK;
    private String ngayTKhamTu;
    private String ngayTKhamDen;
    private int numHomeVisit;    
    
    private String moTaDCTG;
    private String thoiGianTK;
    private int isHomeVisit = -1;
    
    // Dang tat
    private String dTatName;
    private String dTatNgayCapNhat;
    private String dTatNgayTaiKham;
    private String dTatDiaDiemKham;
    private String dTatTDiemKT;
    private String dTatTinhTrang;
    private String dTatNguyenNhan;
    private String dTatMucDo;

    private String dieuKienName;
    private String panel;
    private String panelSelected;
    private String members;
    private String membersRule;

    private String phoneNumber;
    private String lastupdate;
    private String donViCt;

    private int trinhDoId;
    private int tdChuyenMon;
    private String chucVuHT;
    private int ngheNghiepHT;
    private String ngheNghiep;
    private int ttHonNhanId;
    private int nguonNuocId;
    private int NhaOId;
    private int nguyenNhanId;
    private int danTocId;
    private String danTocName;
    private int mucDoKhuyetTat;
    private int linhvucNql;

    private String tenNql;
    // Chu ho
    private String chuhoName;
    private String chuhoNamSinh;

    // Nguoi cham soc
    private String ncsTen;
    private String ncsNamSinh;
    private int ncsQuanHe;
    private String ncsQuanHeName;
    private String ncsDienThoai;
    private int ncsGioiTinh;
    private String ncsGioiTinhName;

    private int chatdocDaCam = -1;
    private String daCam;
    private int khangChien = -1;
    private int doiTuong = -1;
    private int mucDo = -1;

    private int[] idEmp;
    private int[] checkEmp;
    private String emps;
    private int checkEmpAll;
    private int checkEmpAllPage;
    private int selected;

    //list report
    private int listId;
    private int idSelect;
    private int listidEmp;
    private String listName;
    private int empEstablised;
    private String dateCreate;
    private String employeeIdArray;
    private int employeeId;
    private int amountEmp;
    private int checkListReport;
    private String listCode;
    private String empsout;
    private int user_id;

    //phan loai dang tat
    private String dangTat;
    private String dt_lydo;
    private String dt_ngayTu = null;
    private String dt_ngayDen  = null;
    private String dt_tkhamTu  = null;
    private String dt_tkhamDen  = null;
    private int dt_diaDiem;
    private int dt_mucDo;
    private String dt_tinhTrang;

    // PHCN
    private String phcnCanThiep;
    private String phcnDungCu;
    private String phcnDungCuKhac;  
    private String htNhaO;
    private String htCTVS;
    private String htNgay;

    private String dt_dangTatIds;
    private int[] dt_phanLoaiIds;

    // phan loai ho tro && nhu cau
    private String ht_lydo;
    private String ht_capNhatTu = null;
    private String ht_capNhatDen = null;
    private String ht_ngayTu = null;
    private String ht_ngayDen = null;
    private String ht_taiKhamTu = null;
    private String ht_taiKhamDen = null;
    private int ht_diaDiemTK = -1;
    private int ht_statusId = -1;
    private int[] ht_phanLoaiIds = null;
    private int[] nc_phanLoaiIds = null;
    private int[] ct_phanLoaiIds = null;
    private int[] tt_phanLoaiIds = null;
    private int[] indIds = null;

    // Quan Ly Ca
    private int quanLyCa = 0;
    private int kyBC = getMonth(getCurrentSqlDate());
    private int namBC = getYear(getCurrentSqlDate());
    private int[] fields;

    private int pageIndex;
    private int totalResult;

    private int dieuKienId;
    private String ncHoTroCT;
    private String hoTroDaNhan;
    private String tgNhanHt;
    private String nguonHt;
    private String nguoiQlTrucTiep;
    private int dataType = 1;

    private Map<String, Object> mapObject;

    // Kpi value
    private int objId;
    private String objDesc;
    private int indId;
    private String indDesc;
    private int eventId;
    private int dtlId;
    private int hdrId;
    private String createDate;
    private int userId;
    private String userName;
    private String userFullName;
    private int locationId;
    private String locationName;
    private int yearReport;
    private int month;
    private int quarter;
    private int year;
    private String period;
    private int periodType;
    private String activity;
    private String time;
    private int target;
    private int actual;
    private int tw;
    private int ttp;
    private int qhu;
    private int pxa;

    private int targetTw;
    private int targetTtp;
    private int targetQhu;
    private int targetPxa;

    private int targetM;
    private int targetQ;
    private int targetY;

    private int accM;
    private int accQ;
    private int accY;
    private String note;

    // Kpi Person
    private String perCode;
    private String perName;
    private int perSex;
    private int perMale;
    private int perFemale;
    private String perAgency;
    private String perTitle;
    private String perAddress;
    private String perContact;
    private int perHours;

    // Kpi Event
    public String eventCode;
    public String eventActivity;
    public String eventLocation;
    public String eventStartDate;
    public String eventEndDate;
    
    private String commCreateDate;
    private String commP1;
    private String commP2;
    private String commP3;
    private String commP4;
    private String commP5;
    private String commP6;
    private String commP7;
    private String commP8;

    // Kpi Disability
    private int statusId = -1;
    private int duAnId = -1;
    private int dioxin = -1;
    
    private int disDoiTuong= -1; 
    private String nguoiTHTen;
    private int nguoiTHCv = -1;
   
    private FBeans store = new FBeans();

    public void reset() {
        this.id = 0;
        this.sex = -1;
        this.soNKT = -1;
        this.ma = "";
        this.nkt = "";
        this.cmnd = "";
        this.ngaySinh = "";
        this.soNha = "";
        //this.tinhId = 0;
        //this.tinhName = "";
        this.chuanDoan = "";
        this.searchHTVaDT = "";
        this.dieuKienId = 0;
        this.dieuKienName = "";
        this.panel = "";
        this.members = "";
        this.linhvucNql = 0;
        this.tenNql = "";

        // Dang Tat
        this.dt_lydo = "";
        this.dt_ngayTu = "";
        this.dt_ngayDen = "";
        this.dt_dangTatIds = "";
        this.dt_phanLoaiIds = null;

        // Nhu Cau - Ho Tro - Can Thiep
        this.ht_lydo = "";
        this.ht_ngayTu = "";
        this.ht_ngayDen = "";
        this.ht_phanLoaiIds = null;
        this.nc_phanLoaiIds = null;
        this.ct_phanLoaiIds = null;
        this.tt_phanLoaiIds = null;
        this.isHomeVisit = -1;

        this.ht_statusId = -1;
        this.quanLyCa = 0;
        this.nguonHt = "";
        this.doiTuong = -1;
        this.pageIndex = 1;

        // Person
        this.perName = "";
        this.perSex = -1;
        this.perTitle = "";
        this.perAgency = "";
        this.ngayCapNhatTu = "";
        this.ngayCapNhatDen = "";
    }

    public void resetListReport() {
        this.listId = 0;
        this.listCode = "";
        this.checkEmp = null;
        this.listName = "";
        this.empEstablised = 0;
        this.dateCreate = "";
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
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

    public int getSoNKT() {
        return soNKT;
    }

    public void setSoNKT(int soNKT) {
        this.soNKT = soNKT;
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

    public String getTinhName() {
        return tinhName;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    public String getDieuKienName() {
        return dieuKienName;
    }

    public void setDieuKienName(String dieuKienName) {
        this.dieuKienName = dieuKienName;
    }

    public String getPanel() {
        return panel;
    }

    public void setPanel(String panel) {
        this.panel = panel;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public String getNgaySinhDen() {
        return ngaySinhDen;
    }

    public void setNgaySinhDen(String ngaySinhDen) {
        this.ngaySinhDen = ngaySinhDen;
    }

    public String getDt_lydo() {
        return dt_lydo;
    }

    public void setDt_lydo(String dt_lydo) {
        this.dt_lydo = dt_lydo;
    }

    public String getDt_ngayTu() {
        return dt_ngayTu;
    }

    public void setDt_ngayTu(String dt_ngayTu) {
        this.dt_ngayTu = dt_ngayTu;
    }

    public String getDt_ngayDen() {
        return dt_ngayDen;
    }

    public void setDt_ngayDen(String dt_ngayDen) {
        this.dt_ngayDen = dt_ngayDen;
    }

    public int[] getDt_phanLoaiIds() {
        return dt_phanLoaiIds;
    }


    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public int[] getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int[] idEmp) {
        this.idEmp = idEmp;
    }

    public int[] getCheckEmp() {
        return checkEmp;
    }

    public void setCheckEmp(int[] checkEmp) {
        this.checkEmp = checkEmp;
    }

    public String getEmps() {
        return emps;
    }

    public void setEmps(String emps) {
        this.emps = emps;
    }

    public int getCheckEmpAll() {
        return checkEmpAll;
    }

    public void setCheckEmpAll(int checkEmpAll) {
        this.checkEmpAll = checkEmpAll;
    }

    public int getCheckEmpAllPage() {
        return checkEmpAllPage;
    }

    public void setCheckEmpAllPage(int checkEmpAllPage) {
        this.checkEmpAllPage = checkEmpAllPage;
    }

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public int getIdSelect() {
        return idSelect;
    }

    public void setIdSelect(int idSelect) {
        this.idSelect = idSelect;
    }

    public int getListidEmp() {
        return listidEmp;
    }

    public void setListidEmp(int listidEmp) {
        this.listidEmp = listidEmp;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public int getEmpEstablised() {
        return empEstablised;
    }

    public void setEmpEstablised(int empEstablised) {
        this.empEstablised = empEstablised;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getEmployeeIdArray() {
        return employeeIdArray;
    }

    public void setEmployeeIdArray(String employeeIdArray) {
        this.employeeIdArray = employeeIdArray;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getAmountEmp() {
        return amountEmp;
    }

    public void setAmountEmp(int amountEmp) {
        this.amountEmp = amountEmp;
    }

    public int getCheckListReport() {
        return checkListReport;
    }

    public void setCheckListReport(int checkListReport) {
        this.checkListReport = checkListReport;
    }

    public String getListCode() {
        return listCode;
    }

    public void setListCode(String listCode) {
        this.listCode = listCode;
    }

    public String getEmpsout() {
        return empsout;
    }

    public void setEmpsout(String empsout) {
        this.empsout = empsout;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(int typeResult) {
        this.typeResult = typeResult;
    }

    public String getHt_lydo() {
        return ht_lydo;
    }

    public void setHt_lydo(String ht_lydo) {
        this.ht_lydo = ht_lydo;
    }

    public String getHt_ngayTu() {
        return ht_ngayTu;
    }

    public void setHt_ngayTu(String ht_ngayTu) {
        this.ht_ngayTu = ht_ngayTu;
    }

    public String getHt_ngayDen() {
        return ht_ngayDen;
    }

    public void setHt_ngayDen(String ht_ngayDen) {
        this.ht_ngayDen = ht_ngayDen;
    }

    public int getHt_statusId() {
        return ht_statusId;
    }

    public void setHt_statusId(int ht_statusId) {
        this.ht_statusId = ht_statusId;
    }

    public int[] getFields() {
        return fields;
    }

    public void setFields(int[] fields) {
        this.fields = fields;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getDonViCt() {
        return donViCt;
    }

    public void setDonViCt(String donViCt) {
        this.donViCt = donViCt;
    }

    public String getChuhoName() {
        return chuhoName;
    }

    public void setChuhoName(String chuhoName) {
        this.chuhoName = chuhoName;
    }

    public String getChuhoNamSinh() {
        return chuhoNamSinh;
    }

    public void setChuhoNamSinh(String chuhoNamSinh) {
        this.chuhoNamSinh = chuhoNamSinh;
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

    public int getQuanLyCa() {
        return quanLyCa;
    }

    public void setQuanLyCa(int quanLyCa) {
        this.quanLyCa = quanLyCa;
    }

    public String getSearchHTVaDT() {
        return searchHTVaDT;
    }

    public void setSearchHTVaDT(String searchHTVaDT) {
        this.searchHTVaDT = searchHTVaDT;
    }

    public String getNcHoTroCT() {
        return ncHoTroCT;
    }

    public void setNcHoTroCT(String ncHoTroCT) {
        this.ncHoTroCT = ncHoTroCT;
    }

    public String getHoTroDaNhan() {
        return hoTroDaNhan;
    }

    public void setHoTroDaNhan(String hoTroDaNhan) {
        this.hoTroDaNhan = hoTroDaNhan;
    }

    public String getTgNhanHt() {
        return tgNhanHt;
    }

    public void setTgNhanHt(String tgNhanHt) {
        this.tgNhanHt = tgNhanHt;
    }

    public String getNguonHt() {
        return nguonHt;
    }

    public void setNguonHt(String nguonHt) {
        this.nguonHt = nguonHt;
    }

    public String getNguoiQlTrucTiep() {
        return nguoiQlTrucTiep;
    }

    public void setNguoiQlTrucTiep(String nguoiQlTrucTiep) {
        this.nguoiQlTrucTiep = nguoiQlTrucTiep;
    }

    public int getRank_id() {
        return rank_id;
    }

    public void setRank_id(int rank_id) {
        this.rank_id = rank_id;
    }

    public int getTtHoTro() {
        return ttHoTro;
    }

    public void setTtHoTro(int ttHoTro) {
        this.ttHoTro = ttHoTro;
    }

    public String getMembersRule() {
        return membersRule;
    }

    public void setMembersRule(String membersRule) {
        this.membersRule = membersRule;
    }

    public void setChatdocDaCam(int chatdocDaCam) {
        this.chatdocDaCam = chatdocDaCam;
    }

    public int getChatdocDaCam() {
        return chatdocDaCam;
    }

    public void setMucDo(int mucDo) {
        this.mucDo = mucDo;
    }

    public int getMucDo() {
        return mucDo;
    }

    public void setNguonHotroId(int nguonHotroId) {
        this.nguonHotroId = nguonHotroId;
    }

    public int getNguonHotroId() {
        return nguonHotroId;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setNgayCapNhatTu(String ngayCapNhatTu) {
        this.ngayCapNhatTu = ngayCapNhatTu;
    }

    public String getNgayCapNhatTu() {
        return ngayCapNhatTu;
    }

    public void setNgayCapNhatDen(String ngayCapNhatDen) {
        this.ngayCapNhatDen = ngayCapNhatDen;
    }

    public String getNgayCapNhatDen() {
        return ngayCapNhatDen;
    }

    public void setNc_phanLoaiIds(int[] nc_phanLoaiIds) {
        int[] arrId = null;
        int length = 0;
        String value = "";
        if (nc_phanLoaiIds != null && nc_phanLoaiIds.length > 0) {
            for (int i = 0; i < nc_phanLoaiIds.length; i++) {
                if (nc_phanLoaiIds[i] != 0) {
                    length++;
                    value +=
                            String.valueOf(nc_phanLoaiIds[i]) + (i <= (nc_phanLoaiIds.length -
                                                                       1) ?
                                                                 "," : "");
                }
            }
            arrId = new int[length];
            String[] arrStrNhuCau = value.split(",");
            for (int j = 0; j < arrStrNhuCau.length; j++) {
                arrId[j] = Integer.parseInt(arrStrNhuCau[j]);
            }

            this.nc_phanLoaiIds = arrId;
        } else {
            this.nc_phanLoaiIds = null;
        }

    }

    public int[] getNc_phanLoaiIds() {
        return nc_phanLoaiIds;
    }

    public void setNguonHoTroIds(int[] nguonHoTroIds) {
        int[] arrId = null;
        int length = 0;
        String value = "";
        if (nguonHoTroIds != null && nguonHoTroIds.length > 0) {
            for (int i = 0; i < nguonHoTroIds.length; i++) {
                if (nguonHoTroIds[i] != 0) {
                    length++;
                    value +=
                            String.valueOf(nguonHoTroIds[i]) + (i <= (nguonHoTroIds.length -
                                                                       1) ?
                                                                 "," : "");
                }
            }
            arrId = new int[length];
            String[] arrStrNhuCau = value.split(",");
            for (int j = 0; j < arrStrNhuCau.length; j++) {
                arrId[j] = Integer.parseInt(arrStrNhuCau[j]);
            }

            this.nguonHoTroIds = arrId;
        } else {
            this.nguonHoTroIds = null;
        }
    }


    public void setHt_phanLoaiIds(int[] ht_phanLoaiIds) {
        int[] arrId = null;
        int length = 0;
        String value = "";
        if (ht_phanLoaiIds != null && ht_phanLoaiIds.length > 0) {
            for (int i = 0; i < ht_phanLoaiIds.length; i++) {
                if (ht_phanLoaiIds[i] != 0) {
                    length++;
                    value +=
                            String.valueOf(ht_phanLoaiIds[i]) + (i < (ht_phanLoaiIds.length -
                                                                      1) ?
                                                                 "," : "");
                }
            }

            arrId = new int[length];
            String[] arrStrNhuCau = value.split(",");
            for (int j = 0; j < arrStrNhuCau.length; j++) {
                arrId[j] = Integer.parseInt(arrStrNhuCau[j]);
            }
            this.ht_phanLoaiIds = arrId;
        } else {
            this.ht_phanLoaiIds = null;
        }
    }

    public int[] getHt_phanLoaiIds() {
        return ht_phanLoaiIds;
    }

    public void setPanelSelected(String panelSelected) {
        this.panelSelected = panelSelected;
    }

    public String getPanelSelected() {
        return panelSelected;
    }

    public void setDt_dangTatIds(String dt_dangTatIds) {
        this.dt_dangTatIds = dt_dangTatIds;
    }

    public String getDt_dangTatIds() {
        return dt_dangTatIds;
    }

    public void setDt_phanLoaiIds(int[] dt_phanLoaiIds) {
        this.dt_phanLoaiIds = dt_phanLoaiIds;
    }

    public void setCt_phanLoaiIds(int[] ct_phanLoaiIds) {
        this.ct_phanLoaiIds = ct_phanLoaiIds;
    }

    public int[] getCt_phanLoaiIds() {
        return ct_phanLoaiIds;
    }

    public void setHt_phanLoaiIds1(int[] ht_phanLoaiIds) {
        this.ht_phanLoaiIds = ht_phanLoaiIds;
    }

    public void setNc_phanLoaiIds1(int[] nc_phanLoaiIds) {
        this.nc_phanLoaiIds = nc_phanLoaiIds;
    }

    public void setTrinhDoId(int trinhDoId) {
        this.trinhDoId = trinhDoId;
    }

    public int getTrinhDoId() {
        return trinhDoId;
    }

    public void setTdChuyenMon(int tdChuyenMon) {
        this.tdChuyenMon = tdChuyenMon;
    }

    public int getTdChuyenMon() {
        return tdChuyenMon;
    }

    public void setNgheNghiepHT(int ngheNghiepHT) {
        this.ngheNghiepHT = ngheNghiepHT;
    }

    public int getNgheNghiepHT() {
        return ngheNghiepHT;
    }

    public void setTtHonNhanId(int ttHonNhanId) {
        this.ttHonNhanId = ttHonNhanId;
    }

    public int getTtHonNhanId() {
        return ttHonNhanId;
    }

    public void setNguonNuocId(int nguonNuocId) {
        this.nguonNuocId = nguonNuocId;
    }

    public int getNguonNuocId() {
        return nguonNuocId;
    }

    public void setNhaOId(int NhaOId) {
        this.NhaOId = NhaOId;
    }

    public int getNhaOId() {
        return NhaOId;
    }

    public void setNguyenNhanId(int nguyenNhanId) {
        this.nguyenNhanId = nguyenNhanId;
    }

    public int getNguyenNhanId() {
        return nguyenNhanId;
    }

    public void setChucVuHT(String chucVuHT) {
        this.chucVuHT = chucVuHT;
    }

    public String getChucVuHT() {
        return chucVuHT;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getMaSo() {
        return maSo;
    }

    public void setDanTocId(int danTocId) {
        this.danTocId = danTocId;
    }

    public int getDanTocId() {
        return danTocId;
    }

    public void setNguonHoTroIds1(int[] nguonHoTroIds) {
        this.nguonHoTroIds = nguonHoTroIds;
    }

    public int[] getNguonHoTroIds() {
        return nguonHoTroIds;
    }

    public void setNguonHoTro(String nguonHoTro) {
        this.nguonHoTro = nguonHoTro;
    }

    public String getNguonHoTro() {
        return nguonHoTro;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setTt_phanLoaiIds(int[] tt_phanLoaiIds) {
        this.tt_phanLoaiIds = tt_phanLoaiIds;
    }

    public int[] getTt_phanLoaiIds() {
        return tt_phanLoaiIds;
    }

    public void setKhangChien(int khangChien) {
        this.khangChien = khangChien;
    }

    public int getKhangChien() {
        return khangChien;
    }

    public void setMucDoKhuyetTat(int mucDoKhuyetTat) {
        this.mucDoKhuyetTat = mucDoKhuyetTat;
    }

    public int getMucDoKhuyetTat() {
        return mucDoKhuyetTat;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setThonTo(String thonTo) {
        this.thonTo = thonTo;
    }

    public String getThonTo() {
        return thonTo;
    }

    public void setLinhvucNql(int linhvucNql) {
        this.linhvucNql = linhvucNql;
    }

    public int getLinhvucNql() {
        return linhvucNql;
    }

    public void setTenNql(String tenNql) {
        this.tenNql = tenNql;
    }

    public String getTenNql() {
        return tenNql;
    }

    public void setNgayLapTu(String ngayLapTu) {
        this.ngayLapTu = ngayLapTu;
    }

    public String getNgayLapTu() {
        return ngayLapTu;
    }

    public void setNgayLapDen(String ngayLapDen) {
        this.ngayLapDen = ngayLapDen;
    }

    public String getNgayLapDen() {
        return ngayLapDen;
    }

    public void setDoiTuong(int doiTuong) {
        this.doiTuong = doiTuong;
    }

    public int getDoiTuong() {
        return doiTuong;
    }

    public void setMapObject(Map<String, Object> mapObject) {
        this.mapObject = mapObject;
    }

    public Map<String, Object> getMapObject() {
        return mapObject;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setHdrId(int hdrId) {
        this.hdrId = hdrId;
    }

    public int getHdrId() {
        return hdrId;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getMonth() {
        return month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getActivity() {
        return activity;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getActual() {
        return actual;
    }


    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    public String getPerCode() {
        return perCode;
    }

    public void setPerName(String perName) {
        this.perName = perName;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerSex(int perSex) {
        this.perSex = perSex;
    }

    public int getPerSex() {
        return perSex;
    }

    public void setPerAgency(String perAgency) {
        this.perAgency = perAgency;
    }

    public String getPerAgency() {
        return perAgency;
    }

    public void setPerTitle(String perTitle) {
        this.perTitle = perTitle;
    }

    public String getPerTitle() {
        return perTitle;
    }

    public void setPerAddress(String perAddress) {
        this.perAddress = perAddress;
    }

    public String getPerAddress() {
        return perAddress;
    }

    public void setPerContact(String perContact) {
        this.perContact = perContact;
    }

    public String getPerContact() {
        return perContact;
    }


    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }


    public void setIndIds(int[] indIds) {
        this.indIds = indIds;
    }

    public int[] getIndIds() {
        return indIds;
    }

    public void setPeriodType(int periodType) {
        this.periodType = periodType;
    }

    public int getPeriodType() {
        return periodType;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public void setNcsTen(String ncsTen) {
        this.ncsTen = ncsTen;
    }

    public String getNcsTen() {
        return ncsTen;
    }

    public void setNcsNamSinh(String ncsNamSinh) {
        this.ncsNamSinh = ncsNamSinh;
    }

    public String getNcsNamSinh() {
        return ncsNamSinh;
    }


    public void setNcsDienThoai(String ncsDienThoai) {
        this.ncsDienThoai = ncsDienThoai;
    }

    public String getNcsDienThoai() {
        return ncsDienThoai;
    }

    public void setNcsGioiTinh(int ncsGioiTinh) {
        this.ncsGioiTinh = ncsGioiTinh;
    }

    public int getNcsGioiTinh() {
        return ncsGioiTinh;
    }

    public void setDTatName(String dTatName) {
        this.dTatName = dTatName;
    }

    public String getDTatName() {
        return dTatName;
    }

    public void setDTatNgayCapNhat(String dTatNgayCapNhat) {
        this.dTatNgayCapNhat = dTatNgayCapNhat;
    }

    public String getDTatNgayCapNhat() {
        return dTatNgayCapNhat;
    }

    public void setDTatNgayTaiKham(String dTatNgayTaiKham) {
        this.dTatNgayTaiKham = dTatNgayTaiKham;
    }

    public String getDTatNgayTaiKham() {
        return dTatNgayTaiKham;
    }

    public void setDTatDiaDiemKham(String dTatDiaDiemKham) {
        this.dTatDiaDiemKham = dTatDiaDiemKham;
    }

    public String getDTatDiaDiemKham() {
        return dTatDiaDiemKham;
    }

    public void setDTatTDiemKT(String dTatTDiemKT) {
        this.dTatTDiemKT = dTatTDiemKT;
    }

    public String getDTatTDiemKT() {
        return dTatTDiemKT;
    }

    public void setDTatTinhTrang(String dTatTinhTrang) {
        this.dTatTinhTrang = dTatTinhTrang;
    }

    public String getDTatTinhTrang() {
        return dTatTinhTrang;
    }

    public void setDTatNguyenNhan(String dTatNguyenNhan) {
        this.dTatNguyenNhan = dTatNguyenNhan;
    }

    public String getDTatNguyenNhan() {
        return dTatNguyenNhan;
    }

    public void setDTatMucDo(String dTatMucDo) {
        this.dTatMucDo = dTatMucDo;
    }

    public String getDTatMucDo() {
        return dTatMucDo;
    }

    public void setNcsQuanHe(int ncsQuanHe) {
        this.ncsQuanHe = ncsQuanHe;
    }

    public int getNcsQuanHe() {
        return ncsQuanHe;
    }

    public void setDangTat(String dangTat) {
        this.dangTat = dangTat;
    }

    public String getDangTat() {
        return dangTat;
    }

    public void setDanTocName(String danTocName) {
        this.danTocName = danTocName;
    }

    public String getDanTocName() {
        return danTocName;
    }

    public void setNgaySinhTu(String ngaySinhTu) {
        this.ngaySinhTu = ngaySinhTu;
    }

    public String getNgaySinhTu() {
        return ngaySinhTu;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
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

    public void setMdoHlong(String mdoHlong) {
        this.mdoHlong = mdoHlong;
    }

    public String getMdoHlong() {
        return mdoHlong;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setDaCam(String daCam) {
        this.daCam = daCam;
    }

    public String getDaCam() {
        return daCam;
    }

    public void setKnChiTraName(String knChiTraName) {
        this.knChiTraName = knChiTraName;
    }

    public String getKnChiTraName() {
        return knChiTraName;
    }

    public void setTheBhyteName(String theBhyteName) {
        this.theBhyteName = theBhyteName;
    }

    public String getTheBhyteName() {
        return theBhyteName;
    }

    public void setSdTheName(String sdTheName) {
        this.sdTheName = sdTheName;
    }

    public String getSdTheName() {
        return sdTheName;
    }

    public void setSdThePhcnName(String sdThePhcnName) {
        this.sdThePhcnName = sdThePhcnName;
    }

    public String getSdThePhcnName() {
        return sdThePhcnName;
    }

    public void setNcsQuanHeName(String ncsQuanHeName) {
        this.ncsQuanHeName = ncsQuanHeName;
    }

    public String getNcsQuanHeName() {
        return ncsQuanHeName;
    }

    public void setNcsGioiTinhName(String ncsGioiTinhName) {
        this.ncsGioiTinhName = ncsGioiTinhName;
    }

    public String getNcsGioiTinhName() {
        return ncsGioiTinhName;
    }

    public void setNcauName(String ncauName) {
        this.ncauName = ncauName;
    }

    public String getNcauName() {
        return ncauName;
    }

    public void setHtroName(String htroName) {
        this.htroName = htroName;
    }

    public String getHtroName() {
        return htroName;
    }

    public void setHtroTgianNhan(String htroTgianNhan) {
        this.htroTgianNhan = htroTgianNhan;
    }

    public String getHtroTgianNhan() {
        return htroTgianNhan;
    }

    public void setNcauCreateDate(String ncauCreateDate) {
        this.ncauCreateDate = ncauCreateDate;
    }

    public String getNcauCreateDate() {
        return ncauCreateDate;
    }

    public void setHtroCreateDate(String htroCreateDate) {
        this.htroCreateDate = htroCreateDate;
    }

    public String getHtroCreateDate() {
        return htroCreateDate;
    }

    public void setDt_tkhamTu(String dt_tkhamTu) {
        this.dt_tkhamTu = dt_tkhamTu;
    }

    public String getDt_tkhamTu() {
        return dt_tkhamTu;
    }

    public void setDt_tkhamDen(String dt_tkhamDen) {
        this.dt_tkhamDen = dt_tkhamDen;
    }

    public String getDt_tkhamDen() {
        return dt_tkhamDen;
    }


    public void setDt_tinhTrang(String dt_tinhTrang) {
        this.dt_tinhTrang = dt_tinhTrang;
    }

    public String getDt_tinhTrang() {
        return dt_tinhTrang;
    }

    public void setHt_capNhatTu(String ht_capNhatTu) {
        this.ht_capNhatTu = ht_capNhatTu;
    }

    public String getHt_capNhatTu() {
        return ht_capNhatTu;
    }

    public void setHt_capNhatDen(String ht_capNhatDen) {
        this.ht_capNhatDen = ht_capNhatDen;
    }

    public String getHt_capNhatDen() {
        return ht_capNhatDen;
    }

    public void setDt_mucDo(int dt_mucDo) {
        this.dt_mucDo = dt_mucDo;
    }

    public int getDt_mucDo() {
        return dt_mucDo;
    }

    public void setDt_diaDiem(int dt_diaDiem) {
        this.dt_diaDiem = dt_diaDiem;
    }

    public int getDt_diaDiem() {
        return dt_diaDiem;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setTw(int tw) {
        this.tw = tw;
    }

    public int getTw() {
        return tw;
    }

    public void setTtp(int ttp) {
        this.ttp = ttp;
    }

    public int getTtp() {
        return ttp;
    }

    public void setQhu(int qhu) {
        this.qhu = qhu;
    }

    public int getQhu() {
        return qhu;
    }

    public void setPxa(int pxa) {
        this.pxa = pxa;
    }

    public int getPxa() {
        return pxa;
    }

    public void setTargetTw(int targetTw) {
        this.targetTw = targetTw;
    }

    public int getTargetTw() {
        return targetTw;
    }

    public void setTargetTtp(int targetTtp) {
        this.targetTtp = targetTtp;
    }

    public int getTargetTtp() {
        return targetTtp;
    }

    public void setTargetQhu(int targetQhu) {
        this.targetQhu = targetQhu;
    }

    public int getTargetQhu() {
        return targetQhu;
    }

    public void setTargetPxa(int targetPxa) {
        this.targetPxa = targetPxa;
    }

    public int getTargetPxa() {
        return targetPxa;
    }

    public void setTargetM(int targetM) {
        this.targetM = targetM;
    }

    public int getTargetM() {
        return targetM;
    }

    public void setTargetQ(int targetQ) {
        this.targetQ = targetQ;
    }

    public int getTargetQ() {
        return targetQ;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setAccM(int accM) {
        this.accM = accM;
    }

    public int getAccM() {
        return accM;
    }

    public void setAccQ(int accQ) {
        this.accQ = accQ;
    }

    public int getAccQ() {
        return accQ;
    }

    public void setAccY(int accY) {
        this.accY = accY;
    }

    public int getAccY() {
        return accY;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventActivity(String eventActivity) {
        this.eventActivity = eventActivity;
    }

    public String getEventActivity() {
        return eventActivity;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventStartDate(String eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getEventStartDate() {
        return eventStartDate;
    }

    public void setEventEndDate(String eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public String getEventEndDate() {
        return eventEndDate;
    }

    public void setExportTemp(int exportTemp) {
        this.exportTemp = exportTemp;
    }

    public int getExportTemp() {
        return exportTemp;
    }

    public void setNamSinh(String namSinh) {
        this.namSinh = namSinh;
    }

    public String getNamSinh() {
        return namSinh;
    }

    public void setNgayTaiKhamTu(String ngayTaiKhamTu) {
        this.ngayTaiKhamTu = ngayTaiKhamTu;
    }

    public String getNgayTaiKhamTu() {
        return ngayTaiKhamTu;
    }

    public void setNgayTaiKhamDen(String ngayTaiKhamDen) {
        this.ngayTaiKhamDen = ngayTaiKhamDen;
    }

    public String getNgayTaiKhamDen() {
        return ngayTaiKhamDen;
    }

    public void setPhcnCanThiep(String phcnCanThiep) {
        this.phcnCanThiep = phcnCanThiep;
    }

    public String getPhcnCanThiep() {
        return phcnCanThiep;
    }

    public void setPhcnDungCu(String phcnDungCu) {
        this.phcnDungCu = phcnDungCu;
    }

    public String getPhcnDungCu() {
        return phcnDungCu;
    }

    public void setHtNhaO(String htNhaO) {
        this.htNhaO = htNhaO;
    }

    public String getHtNhaO() {
        return htNhaO;
    }

    public void setHtNgay(String htNgay) {
        this.htNgay = htNgay;
    }

    public String getHtNgay() {
        return htNgay;
    }

    public void setPerMale(int perMale) {
        this.perMale = perMale;
    }

    public int getPerMale() {
        return perMale;
    }

    public void setPerFemale(int perFemale) {
        this.perFemale = perFemale;
    }

    public int getPerFemale() {
        return perFemale;
    }

    public void setPerHours(int perHours) {
        this.perHours = perHours;
    }

    public int getPerHours() {
        return perHours;
    }

    public void setObjDesc(String objDesc) {
        this.objDesc = objDesc;
    }

    public String getObjDesc() {
        return objDesc;
    }

    public void setIndDesc(String indDesc) {
        this.indDesc = indDesc;
    }

    public String getIndDesc() {
        return indDesc;
    }

    public void setYearReport(int yearReport) {
        this.yearReport = yearReport;
    }

    public int getYearReport() {
        return yearReport;
    }

    public void setDungCuKhac(String dungCuKhac) {
        this.dungCuKhac = dungCuKhac;
    }

    public String getDungCuKhac() {
        return dungCuKhac;
    }

    public void setNcauDungCuKhac(String ncauDungCuKhac) {
        this.ncauDungCuKhac = ncauDungCuKhac;
    }

    public String getNcauDungCuKhac() {
        return ncauDungCuKhac;
    }
    
    public void setQhuyenId(int qhuyenId) {
        this.qhuyenId = qhuyenId;
    }

    public int getQhuyenId() {
        return qhuyenId;
    }

    public void setPxaId(int pxaId) {
        this.pxaId = pxaId;
    }

    public int getPxaId() {
        return pxaId;
    }

    public void setHtroDiaDiemTK(String htroDiaDiemTK) {
        this.htroDiaDiemTK = htroDiaDiemTK;
    }

    public String getHtroDiaDiemTK() {
        return htroDiaDiemTK;
    }

    public void setNgayTKhamTu(String ngayTKhamTu) {
        this.ngayTKhamTu = ngayTKhamTu;
    }

    public String getNgayTKhamTu() {
        return ngayTKhamTu;
    }

    public void setNgayTKhamDen(String ngayTKhamDen) {
        this.ngayTKhamDen = ngayTKhamDen;
    }

    public String getNgayTKhamDen() {
        return ngayTKhamDen;
    }

    public void setHt_taiKhamTu(String ht_taiKhamTu) {
        this.ht_taiKhamTu = ht_taiKhamTu;
    }

    public String getHt_taiKhamTu() {
        return ht_taiKhamTu;
    }

    public void setHt_taiKhamDen(String ht_taiKhamDen) {
        this.ht_taiKhamDen = ht_taiKhamDen;
    }

    public String getHt_taiKhamDen() {
        return ht_taiKhamDen;
    }

    public void setHt_diaDiemTK(int ht_diaDiemTK) {
        this.ht_diaDiemTK = ht_diaDiemTK;
    }

    public int getHt_diaDiemTK() {
        return ht_diaDiemTK;
    }

    public void setNgayDongHS(String ngayDongHS) {
        this.ngayDongHS = ngayDongHS;
    }

    public String getNgayDongHS() {
        return ngayDongHS;
    }

    public void setLydoDongHS(String lydoDongHS) {
        this.lydoDongHS = lydoDongHS;
    }

    public String getLydoDongHS() {
        return lydoDongHS;
    }

    public void setNguoiDongHS(String nguoiDongHS) {
        this.nguoiDongHS = nguoiDongHS;
    }

    public String getNguoiDongHS() {
        return nguoiDongHS;
    }

    public void setCtAntl(String ctAntl) {
        this.ctAntl = ctAntl;
    }

    public String getCtAntl() {
        return ctAntl;
    }

    public void setCommP1(String commP1) {
        this.commP1 = commP1;
    }

    public String getCommP1() {
        return commP1;
    }

    public void setCommP2(String commP2) {
        this.commP2 = commP2;
    }

    public String getCommP2() {
        return commP2;
    }

    public void setCommP3(String commP3) {
        this.commP3 = commP3;
    }

    public String getCommP3() {
        return commP3;
    }

    public void setCommP4(String commP4) {
        this.commP4 = commP4;
    }

    public String getCommP4() {
        return commP4;
    }

    public void setCommP5(String commP5) {
        this.commP5 = commP5;
    }

    public String getCommP5() {
        return commP5;
    }

    public void setCommP6(String commP6) {
        this.commP6 = commP6;
    }

    public String getCommP6() {
        return commP6;
    }

    public void setCommP7(String commP7) {
        this.commP7 = commP7;
    }

    public String getCommP7() {
        return commP7;
    }

    public void setCommP8(String commP8) {
        this.commP8 = commP8;
    }

    public String getCommP8() {
        return commP8;
    }

    public void setCommCreateDate(String commCreateDate) {
        this.commCreateDate = commCreateDate;
    }

    public String getCommCreateDate() {
        return commCreateDate;
    }

    public void setPhcnDungCuKhac(String phcnDungCuKhac) {
        this.phcnDungCuKhac = phcnDungCuKhac;
    }

    public String getPhcnDungCuKhac() {
        return phcnDungCuKhac;
    }

    public void setHtCTVS(String htCTVS) {
        this.htCTVS = htCTVS;
    }

    public String getHtCTVS() {
        return htCTVS;
    }

    public void setHuyenName(String huyenName) {
        this.huyenName = huyenName;
    }

    public String getHuyenName() {
        return huyenName;
    }

    public void setDuAnId(int duAnId) {
        this.duAnId = duAnId;
    }

    public int getDuAnId() {
        return duAnId;
    }

    public void setDuAnHT(String duAnHT) {
        this.duAnHT = duAnHT;
    }

    public String getDuAnHT() {
        return duAnHT;
    }

    public void setDioxin(int dioxin) {
        this.dioxin = dioxin;
    }

    public int getDioxin() {
        return dioxin;
    }

    public void setMoTaDCTG(String moTaDCTG) {
        this.moTaDCTG = moTaDCTG;
    }

    public String getMoTaDCTG() {
        return moTaDCTG;
    }

    public void setPxaName(String pxaName) {
        this.pxaName = pxaName;
    }

    public String getPxaName() {
        return pxaName;
    }

    public void setDisDoiTuong(int disDoiTuong) {
        this.disDoiTuong = disDoiTuong;
    }

    public int getDisDoiTuong() {
        return disDoiTuong;
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

    public void setIsHomeVisit(int isHomeVisit) {
        this.isHomeVisit = isHomeVisit;
    }

    public int getIsHomeVisit() {
        return isHomeVisit;
    }

    public void setThoiGianTK(String thoiGianTK) {
        this.thoiGianTK = thoiGianTK;
    }

    public String getThoiGianTK() {
        return thoiGianTK;
    }

    public void setNumHomeVisit(int numHomeVisit) {
        this.numHomeVisit = numHomeVisit;
    }

    public int getNumHomeVisit() {
        return numHomeVisit;
    }
}
