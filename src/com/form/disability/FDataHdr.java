package com.form.disability;


import com.form.FBeans;
import com.form.FSeed;

import com.util.Formater;
import com.util.Utilities;

import java.util.HashMap;
import java.util.Map;

public class FDataHdr extends FSeed {
    private int id;
    private int dtlId;
    private int nktId;
    private int perId;
    private int eventId;
    private int parentID;
    private int userId;
    private String notify;
    
    private String notifyInit;
    private String notifyNext;
    private String notifyNumInput;
    
    // Rank
    private int rankId;
    private String rankCreateDate = Formater.date2str(getCurrentDate());
    private String rankInitDate;
    private int rankNum = 0;
    private String rankName;
    private int parRankId;
    private String parRankName;
    private int rankResult = 0;
    private int rankHasSP = 0;
    private int rankHasRK = 0;
    private int rankHasRQ = 0;
    private int reRank = 0;
    private String rankPercent;
    private String rankHas = "0";
    private String curRankHas = "0";
    private int curResult = 0;
    private String rankInput;
    private String breadcrumb;
    private int rankNumIndicator = 0;
    private Map<String, String> rankResult1;
    private Map<String, String> rankResult2;
    private Map<String, String> rankResult3;
    
    private int locationId = 0;
    private int subLocationId = 0;
    private int districtId = 0;
    private int communeId = 0;
    
    private int objId;
    private int indId;
    private String indIds;
    private int ins;
    private int refId;
    private int tabId;
    private int yearReport;

    private int typeSel;
    private int perSel = 0;
    private int disSel;

    private boolean hasDtl = false;
    private String startDate;
    private String endDate;
    private int voteResult;

    private String objCode;
    private String objName;
    private String objDesc;

    private String code;
    private String name;
    private String description;
    private String createDate = Formater.date2str(getCurrentDate());
    private String modifyDate;
    private String locationName;

    private int totalDis = 0 ;
    private int hours = 0;
    private int type;
    private int month = getMonth(getCurrentSqlDate());
    private int year = Utilities.getCurrentYear(getCurrentDate());
    private int quarter = Formater.getPeriod(month);

    private int lvl;
    private int typePeriod = 1; // Kieu Thang/Quy
    private String title;
    private int baseline;
    private int targetYear;
    private String targetJustification;
    private int q1;
    private int q2;
    private int q3;
    private int q4;
    private int actual = 0;
    private int target = 0;

    private int tw = 0;
    private int ttp = 0;
    private int qhu = 0;
    private int pxa = 0;

    private int targetTw = 0;
    private int targetTtp = 0;
    private int targetQhu = 0;
    private int targetPxa = 0;

    private int targetM = 0;
    private int targetQ = 0;
    private int targetY = 0;

    private int accM = 0;
    private int accQ = 0;
    private int accY = 0;

    private String note;

    private int pageIndex;
    private int totalResult;

    // NKT
    private String activity;
    private String time;
    private String location;
    private String disCode;
    private String disCodeNkt;
    private String disName;
    private int disSex = -1;
    private String disPassport;
    private String disPhone;
    private String disBirth;
    private String disNation;
    private String disAddress;
    private int disCarrer;
    private int disDioxin;
    private int disStatus;

    private String ncsName = "";
    private int ncsBirth;
    private String ncsRelation = "-1";
    private String ncsPhone;
    private int ncsSex = -1;

    // Phan loai
    private int plDtlId;
    private int ncDtlId;
    private int htDtlId;

    private int disPhanLoaiId;
    private String disThoiDiemMac =
        String.valueOf(getYear(getCurrentSqlDate()));
    private String disNgayTK;
    private String disChuanDoan;
    private int disNguyenNhanId;
    private int disMucDoId;
    private int disDiaDiem;
    private int disDungCu;
    private int disDoiTuong = -1;
    private int duAnId = -1;
    private String disDangTatIds;
    private int[] disPhanLoaiIds;

    // Nhu cau + Ho tro
    private int stt;
    private int supportId;
    private String supportName;
    private String mode;
    private String dateCreate;
    private int disNhuCauId;
    private String hotroIds;
    private int[] supportIds;
    private int statusId;
    private int nguonId;
    private String caithienKhac;
    private String nguoiTHTen;
    private String nguoiTHCv;

    // Person
    private int perLocationId;
    private String perAddress;
    private String perContact;
    private String perCode;
    private String perName;
    private int perSex;
    private String perBirth;
    private String perAgency;
    private String perTitle;
    private int perHours;

    // Event
    private String eventSel = "";
    private String eventIds;
    private String eventCode;
    private int eventType;
    private int eventField;
    private String eventActivity;
    private String eventLocation;
    private String eventCreateDate;
    private String eventModifyDate;
    private String eventStartDate;
    private String eventEndDate;

    private int inputType = 0;
    private int state = 0;
    private FBeans indicators;
    private int total;
    private String anchor;

    private int nguonHoTroId = 0;
    private String nguonhotro;
    private String dateForm;
    private String dateTo;
    private String reson;
    private int ketqua;
    
    public int hasVisit;
    public int knChiTra;
    public int theBhyte;
    public int sdThe;
    public int sdThePhcn;
    public String mtieuGdinh;
    public String mtieuDtri;
    public String ctVltl;
    public String ctHdtl;
    public String ctAntl;
    public String ctGddb;
    public String ctCsgn;
    public String mdoPtdl;
    public String mdoHlong;
    public String dungcuKhac;
    public String phcnKhac;

    public String ploaiCreateDate = Formater.date2str(getCurrentDate());
    public String ncauCreateDate = Formater.date2str(getCurrentDate());
    public String htroCreateDate = Formater.date2str(getCurrentDate());
    public String dgiaCreateDate = Formater.date2str(getCurrentDate());
    
    public int p0 = 0;
    public int p1 = 0;
    public int p2 = 0;
    public int p3 = 0;
    public int p4 = 0;
    
    public int rptId;
    private String rptCreateDate = Formater.date2str(getCurrentDate());;
    private String rptCreateBy;
    private int rptP1;
    private int rptP2;
    private int rptP3;
    private int rptP4;
    private int rptP5;
    private int rptNcs;
    private int rptHuongCanThiep;
    private String rptCanThiep;
    private String rptHtroDKien;
    private int rptObj;
    
    public int pfId;
    public int pfStatus;
    public int pfResonId;
    public String pfCreateOn = Formater.date2str(getCurrentDate());;
    public String pfCreateBy;
    public String pfUpdateOn;
    public int pfUpdateBy;
    public String pfAssessment;
    
    public String hdrCreateDate;
    
    private FBeans store = new FBeans();

    public void reset() {
        this.perName = "";
        this.perTitle = "";
        this.perAgency = "";
        this.perAddress = "";
        this.perContact = "";
        this.hours = 0;
        this.voteResult = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getParentID() {
        return parentID;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public int getTargetYear() {
        return targetYear;
    }


    public void setQ1(int q1) {
        this.q1 = q1;
    }

    public int getQ1() {
        return q1;
    }

    public void setQ2(int q2) {
        this.q2 = q2;
    }

    public int getQ2() {
        return q2;
    }

    public void setQ3(int q3) {
        this.q3 = q3;
    }

    public int getQ3() {
        return q3;
    }

    public void setQ4(int q4) {
        this.q4 = q4;
    }

    public int getQ4() {
        return q4;
    }

    public void setIndicators(FBeans indicators) {
        this.indicators = indicators;
    }

    public FBeans getIndicators() {
        return indicators;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResult() {
        return totalResult;
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

    public void setActual(int actual) {
        this.actual = actual;
    }

    public int getActual() {
        return actual;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }


    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
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

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setDisCode(String disCode) {
        this.disCode = disCode;
    }

    public String getDisCode() {
        return disCode;
    }

    public void setDisBirth(String disBirth) {
        this.disBirth = disBirth;
    }

    public String getDisBirth() {
        return disBirth;
    }

    public void setDisNation(String disNation) {
        this.disNation = disNation;
    }

    public String getDisNation() {
        return disNation;
    }

    public void setDisAddress(String disAddress) {
        this.disAddress = disAddress;
    }

    public String getDisAddress() {
        return disAddress;
    }

    public void setDisSex(int disSex) {
        this.disSex = disSex;
    }

    public int getDisSex() {
        return disSex;
    }

    public void setDisName(String disName) {
        this.disName = disName;
    }

    public String getDisName() {
        return disName;
    }

    public void setNktId(int nktId) {
        this.nktId = nktId;
    }

    public int getNktId() {
        return nktId;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationName() {
        return locationName;
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

    public void setPerHours(int perHours) {
        this.perHours = perHours;
    }

    public int getPerHours() {
        return perHours;
    }


    public void setPerId(int perId) {
        this.perId = perId;
    }

    public int getPerId() {
        return perId;
    }

    public void setPerBirth(String perBirth) {
        this.perBirth = perBirth;
    }

    public String getPerBirth() {
        return perBirth;
    }


    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setVoteResult(int voteResult) {
        this.voteResult = voteResult;
    }

    public int getVoteResult() {
        return voteResult;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventField(int eventField) {
        this.eventField = eventField;
    }

    public int getEventField() {
        return eventField;
    }

    public void setTabId(int tabId) {
        this.tabId = tabId;
    }

    public int getTabId() {
        return tabId;
    }


    public void setTypeSel(int typeSel) {
        this.typeSel = typeSel;
    }

    public int getTypeSel() {
        return typeSel;
    }

    public void setPerSel(int perSel) {
        this.perSel = perSel;
    }

    public int getPerSel() {
        return perSel;
    }

    public void setDisSel(int disSel) {
        this.disSel = disSel;
    }

    public int getDisSel() {
        return disSel;
    }

    public void setPerLocationId(int perLocationId) {
        this.perLocationId = perLocationId;
    }

    public int getPerLocationId() {
        return perLocationId;
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

    public void setPerCode(String perCode) {
        this.perCode = perCode;
    }

    public String getPerCode() {
        return perCode;
    }


    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }


    public void setEventCreateDate(String eventCreateDate) {
        this.eventCreateDate = eventCreateDate;
    }

    public String getEventCreateDate() {
        return eventCreateDate;
    }

    public void setEventModifyDate(String eventModifyDate) {
        this.eventModifyDate = eventModifyDate;
    }

    public String getEventModifyDate() {
        return eventModifyDate;
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

    public void setBaseline(int baseline) {
        this.baseline = baseline;
    }

    public int getBaseline() {
        return baseline;
    }

    public void setTargetJustification(String targetJustification) {
        this.targetJustification = targetJustification;
    }

    public String getTargetJustification() {
        return targetJustification;
    }

    public void setTypePeriod(int typePeriod) {
        this.typePeriod = typePeriod;
    }

    public int getTypePeriod() {
        return typePeriod;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setDisPassport(String disPassport) {
        this.disPassport = disPassport;
    }

    public String getDisPassport() {
        return disPassport;
    }


    public void setInputType(int inputType) {
        this.inputType = inputType;
    }

    public int getInputType() {
        return inputType;
    }

    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }

    public String getAnchor() {
        return anchor;
    }

    public void setHasDtl(boolean hasDtl) {
        this.hasDtl = hasDtl;
    }

    public boolean isHasDtl() {
        return hasDtl;
    }

    public void setNcsSex(int ncsSex) {
        this.ncsSex = ncsSex;
    }

    public int getNcsSex() {
        return ncsSex;
    }


    public void setDisCarrer(int disCarrer) {
        this.disCarrer = disCarrer;
    }

    public int getDisCarrer() {
        return disCarrer;
    }

    public void setDisDioxin(int disDioxin) {
        this.disDioxin = disDioxin;
    }

    public int getDisDioxin() {
        return disDioxin;
    }

    public void setNcsName(String ncsName) {
        this.ncsName = ncsName;
    }

    public String getNcsName() {
        return ncsName;
    }


    public void setNcsRelation(String ncsRelation) {
        this.ncsRelation = ncsRelation;
    }

    public String getNcsRelation() {
        return ncsRelation;
    }

    public void setNcsPhone(String ncsPhone) {
        this.ncsPhone = ncsPhone;
    }

    public String getNcsPhone() {
        return ncsPhone;
    }

    public void setNcsBirth(int ncsBirth) {
        this.ncsBirth = ncsBirth;
    }

    public int getNcsBirth() {
        return ncsBirth;
    }

    public void setDisThoiDiemMac(String disThoiDiemMac) {
        this.disThoiDiemMac = disThoiDiemMac;
    }

    public String getDisThoiDiemMac() {
        return disThoiDiemMac;
    }

    public void setDisNguyenNhanId(int disNguyenNhanId) {
        this.disNguyenNhanId = disNguyenNhanId;
    }

    public int getDisNguyenNhanId() {
        return disNguyenNhanId;
    }

    public void setDisMucDoId(int disMucDoId) {
        this.disMucDoId = disMucDoId;
    }

    public int getDisMucDoId() {
        return disMucDoId;
    }

    public void setDisChuanDoan(String disChuanDoan) {
        this.disChuanDoan = disChuanDoan;
    }

    public String getDisChuanDoan() {
        return disChuanDoan;
    }

    public void setDisPhanLoaiId(int disPhanLoaiId) {
        this.disPhanLoaiId = disPhanLoaiId;
    }

    public int getDisPhanLoaiId() {
        return disPhanLoaiId;
    }

    public void setDisNhuCauId(int disNhuCauId) {
        this.disNhuCauId = disNhuCauId;
    }

    public int getDisNhuCauId() {
        return disNhuCauId;
    }

    public void setHotroIds(String hotroIds) {
        this.hotroIds = hotroIds;
    }

    public String getHotroIds() {
        return hotroIds;
    }

    

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setCaithienKhac(String caithienKhac) {
        this.caithienKhac = caithienKhac;
    }

    public String getCaithienKhac() {
        return caithienKhac;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void resetDis() {
        this.nktId = 0;
        this.dtlId = 0;
        this.disAddress = "";
        this.disBirth = "";
        this.disCarrer = 0;
        this.disChuanDoan = "";
        this.disCode = "";
        this.disDioxin = 0;
        this.disMucDoId = 0;
        this.disName = "";
        this.disNation = "";
        this.disPassport = "";
        this.disSex = 0;
        this.disCodeNkt = "";
        this.disStatus = 0;

        this.ncsName = "";
        this.ncsBirth = 0;
        this.ncsRelation = "0";
        this.ncsPhone = "";
        this.ncsSex = 0;
    }

    public void resetPhanLoai() {
        this.plDtlId = 0;
        this.createDate = Formater.date2str(getCurrentDate());
        this.disNgayTK = "";
        this.disChuanDoan = "";
        this.disDangTatIds = "";
        this.disNguyenNhanId = 0;
        this.disMucDoId = 0;
        this.disDiaDiem = 0;
    }
    
      public void resetReport() {
        this.rptId = 0;
        this.rptCreateDate = Formater.date2str(getCurrentDate());
        this.rptCreateBy = "";
        this.rptP1 = 0;
        this.rptP2 = 0;
        this.rptP3 = 0;
        this.rptP4 = 0;
        this.rptP5 = 0;
        this.rptNcs = 0;
        this.rptHuongCanThiep = 0;
        this.rptCanThiep = "";
        this.rptHtroDKien = "";
        this.rptObj = -1;
    }
      
      public void resetProfile() {
        this.pfId = 0;
        this.pfCreateOn = Formater.date2str(getCurrentDate());
        this.pfCreateBy = "";
        this.pfResonId = -1;
        this.pfStatus = -1;
        this.pfUpdateOn = null;
        this.pfUpdateBy = -1;
        this.pfAssessment = "";
      }  

    public void setPlDtlId(int plDtlId) {
        this.plDtlId = plDtlId;
    }

    public int getPlDtlId() {
        return plDtlId;
    }

    public void setNcDtlId(int ncDtlId) {
        this.ncDtlId = ncDtlId;
    }

    public int getNcDtlId() {
        return ncDtlId;
    }

    public void setHtDtlId(int htDtlId) {
        this.htDtlId = htDtlId;
    }

    public int getHtDtlId() {
        return htDtlId;
    }

    public void setNguonHoTroId(int nguonHoTroId) {
        this.nguonHoTroId = nguonHoTroId;
    }

    public int getNguonHoTroId() {
        return nguonHoTroId;
    }

    public void setNguonhotro(String nguonhotro) {
        this.nguonhotro = nguonhotro;
    }

    public String getNguonhotro() {
        return nguonhotro;
    }

    public void setDateForm(String dateForm) {
        this.dateForm = dateForm;
    }

    public String getDateForm() {
        return dateForm;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getReson() {
        return reson;
    }

    public void setKetqua(int ketqua) {
        this.ketqua = ketqua;
    }

    public int getKetqua() {
        return ketqua;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
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

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjCode(String objCode) {
        this.objCode = objCode;
    }

    public String getObjCode() {
        return objCode;
    }

    public void setObjDesc(String objDesc) {
        this.objDesc = objDesc;
    }

    public String getObjDesc() {
        return objDesc;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setDisCodeNkt(String disCodeNkt) {
        this.disCodeNkt = disCodeNkt;
    }

    public String getDisCodeNkt() {
        return disCodeNkt;
    }

    public void setDisDangTatIds(String disDangTatIds) {
        this.disDangTatIds = disDangTatIds;
    }

    public String getDisDangTatIds() {
        return disDangTatIds;
    }
    
    public void setSupportIds(int[] supportIds) {
        if (supportIds != null && supportIds.length > 0) {
            String hoTroIds = "#";
            for (int i = 0; i < supportIds.length; i++) {
                hoTroIds += supportIds[i] + "#";
            }
            setHotroIds(hotroIds);
        }
        this.supportIds = supportIds;
    }

    public int[] getSupportIds() {
        return supportIds;
    }

    public int[] getDisPhanLoaiIds() {
        return disPhanLoaiIds;
    }

    public void setDisPhanLoaiIds(int[] disPhanLoaiIds) {
        if (disPhanLoaiIds != null && disPhanLoaiIds.length > 0) {
            String dangTatIds = "#";
            for (int i = 0; i < disPhanLoaiIds.length; i++) {
                dangTatIds += disPhanLoaiIds[i] + "#";
            }
            setDisDangTatIds(dangTatIds);
        }
        this.disPhanLoaiIds = disPhanLoaiIds;
    }

    public void setDisNgayTK(String disNgayTK) {
        this.disNgayTK = disNgayTK;
    }

    public String getDisNgayTK() {
        return disNgayTK;
    }

    public void setDisDiaDiem(int disDiaDiem) {
        this.disDiaDiem = disDiaDiem;
    }

    public int getDisDiaDiem() {
        return disDiaDiem;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }

    public int getRefId() {
        return refId;
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

    public void setTarget(int target) {
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
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

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDisStatus(int disStatus) {
        this.disStatus = disStatus;
    }

    public int getDisStatus() {
        return disStatus;
    }

    public void setMdoHlong(String mdoHlong) {
        this.mdoHlong = mdoHlong;
    }

    public String getMdoHlong() {
        return mdoHlong;
    }

    public void setEventIds(String eventIds) {
        this.eventIds = eventIds;
    }

    public String getEventIds() {
        return eventIds;
    }

    public void setEventSel(String eventSel) {
        this.eventSel = eventSel;
    }

    public String getEventSel() {
        return eventSel;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setSupportId(int supportId) {
        this.supportId = supportId;
    }

    public int getSupportId() {
        return supportId;
    }

    public void setSupportName(String supportName) {
        this.supportName = supportName;
    }

    public String getSupportName() {
        return supportName;
    }

    public void setIns(int ins) {
        this.ins = ins;
    }

    public int getIns() {
        return ins;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setYearReport(int yearReport) {
        this.yearReport = yearReport;
    }

    public int getYearReport() {
        return yearReport;
    }

    public void setPloaiCreateDate(String ploaiCreateDate) {
        this.ploaiCreateDate = ploaiCreateDate;
    }

    public String getPloaiCreateDate() {
        return ploaiCreateDate;
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

    public void setDgiaCreateDate(String dgiaCreateDate) {
        this.dgiaCreateDate = dgiaCreateDate;
    }

    public String getDgiaCreateDate() {
        return dgiaCreateDate;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setTotalDis(int totalDis) {
        this.totalDis = totalDis;
    }

    public int getTotalDis() {
        return totalDis;
    }

    public void setDungcuKhac(String dungcuKhac) {
        this.dungcuKhac = dungcuKhac;
    }

    public String getDungcuKhac() {
        return dungcuKhac;
    }

    public void setRankId(int rankId) {
        this.rankId = rankId;
    }

    public int getRankId() {
        return rankId;
    }

    public void setP0(int p0) {
        this.p0 = p0;
    }

    public int getP0() {
        return p0;
    }

    public void setP1(int p1) {
        this.p1 = p1;
    }

    public int getP1() {
        return p1;
    }

    public void setP2(int p2) {
        this.p2 = p2;
    }

    public int getP2() {
        return p2;
    }

    public void setP3(int p3) {
        this.p3 = p3;
    }

    public int getP3() {
        return p3;
    }

    public void setP4(int p4) {
        this.p4 = p4;
    }

    public int getP4() {
        return p4;
    }

    public void setIndIds(String indIds) {
        this.indIds = indIds;
    }

    public String getIndIds() {
        return indIds;
    }

    public void setCtAntl(String ctAntl) {
        this.ctAntl = ctAntl;
    }

    public String getCtAntl() {
        return ctAntl;
    }
    
    public void setRankName(String rankName) {
        this.rankName = rankName;
    }

    public String getRankName() {
        return rankName;
    }

    public void setParRankId(int parRankId) {
        this.parRankId = parRankId;
    }

    public int getParRankId() {
        return parRankId;
    }

    public void setParRankName(String parRankName) {
        this.parRankName = parRankName;
    }

    public String getParRankName() {
        return parRankName;
    }

    public void setRankResult(int rankResult) {
        this.rankResult = rankResult;
    }

    public int getRankResult() {
        return rankResult;
    }

    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public String getBreadcrumb() {
        return breadcrumb;
    }

    public void setRankCreateDate(String rankCreateDate) {
        this.rankCreateDate = rankCreateDate;
    }

    public String getRankCreateDate() {
        return rankCreateDate;
    }

    public void setDisPhone(String disPhone) {
        this.disPhone = disPhone;
    }

    public String getDisPhone() {
        return disPhone;
    }

    public void setRptId(int rptId) {
        this.rptId = rptId;
    }

    public int getRptId() {
        return rptId;
    }

    public void setRptCreateDate(String rptCreateDate) {
        this.rptCreateDate = rptCreateDate;
    }

    public String getRptCreateDate() {
        return rptCreateDate;
    }

    public void setRptCreateBy(String rptCreateBy) {
        this.rptCreateBy = rptCreateBy;
    }

    public String getRptCreateBy() {
        return rptCreateBy;
    }

    public void setRptP1(int rptP1) {
        this.rptP1 = rptP1;
    }

    public int getRptP1() {
        return rptP1;
    }

    public void setRptP2(int rptP2) {
        this.rptP2 = rptP2;
    }

    public int getRptP2() {
        return rptP2;
    }

    public void setRptP3(int rptP3) {
        this.rptP3 = rptP3;
    }

    public int getRptP3() {
        return rptP3;
    }

    public void setRptP4(int rptP4) {
        this.rptP4 = rptP4;
    }

    public int getRptP4() {
        return rptP4;
    }

    public void setRptP5(int rptP5) {
        this.rptP5 = rptP5;
    }

    public int getRptP5() {
        return rptP5;
    }

    public void setRptNcs(int rptNcs) {
        this.rptNcs = rptNcs;
    }

    public int getRptNcs() {
        return rptNcs;
    }

    public void setRptCanThiep(String rptCanThiep) {
        this.rptCanThiep = rptCanThiep;
    }

    public String getRptCanThiep() {
        return rptCanThiep;
    }

    public void setPfId(int pfId) {
        this.pfId = pfId;
    }

    public int getPfId() {
        return pfId;
    }

    public void setPfStatus(int pfStatus) {
        this.pfStatus = pfStatus;
    }

    public int getPfStatus() {
        return pfStatus;
    }

    public void setPfResonId(int pfResonId) {
        this.pfResonId = pfResonId;
    }

    public int getPfResonId() {
        return pfResonId;
    }

    public void setPfCreateOn(String pfCreateOn) {
        this.pfCreateOn = pfCreateOn;
    }

    public String getPfCreateOn() {
        return pfCreateOn;
    }

    public void setPfCreateBy(String pfCreateBy) {
        this.pfCreateBy = pfCreateBy;
    }

    public String getPfCreateBy() {
        return pfCreateBy;
    }

    public void setPfUpdateOn(String pfUpdateOn) {
        this.pfUpdateOn = pfUpdateOn;
    }

    public String getPfUpdateOn() {
        return pfUpdateOn;
    }

    public void setPfUpdateBy(int pfUpdateBy) {
        this.pfUpdateBy = pfUpdateBy;
    }

    public int getPfUpdateBy() {
        return pfUpdateBy;
    }

    public void setPfAssessment(String pfAssessment) {
        this.pfAssessment = pfAssessment;
    }

    public String getPfAssessment() {
        return pfAssessment;
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

    public void setSubLocationId(int subLocationId) {
        this.subLocationId = subLocationId;
    }

    public int getSubLocationId() {
        return subLocationId;
    }

    public void setRptHtroDKien(String rptHtroDKien) {
        this.rptHtroDKien = rptHtroDKien;
    }

    public String getRptHtroDKien() {
        return rptHtroDKien;
    }

    public void setRptHuongCanThiep(int rptHuongCanThiep) {
        this.rptHuongCanThiep = rptHuongCanThiep;
    }

    public int getRptHuongCanThiep() {
        return rptHuongCanThiep;
    }

    public void setRptObj(int rptObj) {
        this.rptObj = rptObj;
    }

    public int getRptObj() {
        return rptObj;
    }

    public void setRankNumIndicator(int rankNumIndicator) {
        this.rankNumIndicator = rankNumIndicator;
    }

    public int getRankNumIndicator() {
        return rankNumIndicator;
    }

    public void setNotify(String notify) {
        this.notify = notify;
    }

    public String getNotify() {
        return notify;
    }

    public void setRankHasSP(int rankHasSP) {
        this.rankHasSP = rankHasSP;
    }

    public int getRankHasSP() {
        return rankHasSP;
    }

    public void setNotifyInit(String notifyInit) {
        this.notifyInit = notifyInit;
    }

    public String getNotifyInit() {
        return notifyInit;
    }

    public void setNotifyNext(String notifyNext) {
        this.notifyNext = notifyNext;
    }

    public String getNotifyNext() {
        return notifyNext;
    }

    public void setNotifyNumInput(String notifyNumInput) {
        this.notifyNumInput = notifyNumInput;
    }

    public String getNotifyNumInput() {
        return notifyNumInput;
    }

    public void setRankResult1(Map<String, String> rankResult1) {
        this.rankResult1 = rankResult1;
    }

    public Map<String, String> getRankResult1() {
        return rankResult1;
    }

    public void setRankResult2(Map<String, String> rankResult2) {
        this.rankResult2 = rankResult2;
    }

    public Map<String, String> getRankResult2() {
        return rankResult2;
    }

    public void setRankResult3(Map<String, String> rankResult3) {
        this.rankResult3 = rankResult3;
    }

    public Map<String, String> getRankResult3() {
        return rankResult3;
    }

    public void setDisDungCu(int disDungCu) {
        this.disDungCu = disDungCu;
    }

    public int getDisDungCu() {
        return disDungCu;
    }

    public void setDisDoiTuong(int disDoiTuong) {
        this.disDoiTuong = disDoiTuong;
    }

    public int getDisDoiTuong() {
        return disDoiTuong;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public int getRankNum() {
        return rankNum;
    }
    
    
   
    public void setRankInput(String rankInput) {
        this.rankInput = rankInput;
    }

    public String getRankInput() {
        return rankInput;
    }

    public void setRankHas(String rankHas) {
        this.rankHas = rankHas;
    }

    public String getRankHas() {
        return rankHas;
    }

    public void setRankHasRK(int rankHasRK) {
        this.rankHasRK = rankHasRK;
    }

    public int getRankHasRK() {
        return rankHasRK;
    }

    public void setRankHasRQ(int rankHasRQ) {
        this.rankHasRQ = rankHasRQ;
    }

    public int getRankHasRQ() {
        return rankHasRQ;
    }

    public void setCurRankHas(String curRankHas) {
        this.curRankHas = curRankHas;
    }

    public String getCurRankHas() {
        return curRankHas;
    }

    public void setReRank(int reRank) {
        this.reRank = reRank;
    }

    public int getReRank() {
        return reRank;
    }

    public void setHdrCreateDate(String hdrCreateDate) {
        this.hdrCreateDate = hdrCreateDate;
    }

    public String getHdrCreateDate() {
        return hdrCreateDate;
    }

    public void setRankInitDate(String rankInitDate) {
        this.rankInitDate = rankInitDate;
    }

    public String getRankInitDate() {
        return rankInitDate;
    }

    public void setRankPercent(String rankPercent) {
        this.rankPercent = rankPercent;
    }

    public String getRankPercent() {
        return rankPercent;
    }

    public void setCurResult(int curResult) {
        this.curResult = curResult;
    }

    public int getCurResult() {
        return curResult;
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

    public void setDuAnId(int duAnId) {
        this.duAnId = duAnId;
    }

    public int getDuAnId() {
        return duAnId;
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

    public void setPhcnKhac(String phcnKhac) {
        this.phcnKhac = phcnKhac;
    }

    public String getPhcnKhac() {
        return phcnKhac;
    }

    public void setHasVisit(int hasVisit) {
        this.hasVisit = hasVisit;
    }

    public int getHasVisit() {
        return hasVisit;
    }

    public void setNguoiTHTen(String nguoiTHTen) {
        this.nguoiTHTen = nguoiTHTen;
    }

    public String getNguoiTHTen() {
        return nguoiTHTen;
    }

    public void setNguoiTHCv(String nguoiTHCv) {
        this.nguoiTHCv = nguoiTHCv;
    }

    public String getNguoiTHCv() {
        return nguoiTHCv;
    }
}
