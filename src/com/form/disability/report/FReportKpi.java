package com.form.disability.report;


import com.form.FBeans;
import com.form.FSeed;

import com.util.Formater;
import com.util.Utilities;


public class FReportKpi extends FSeed {
    private String timeExport;
    private int monthReport = getMonth(getCurrentSqlDate());
    private int quarterReport = Formater.getPeriod(monthReport);
    private int yearReport = Utilities.getCurrentYear(getCurrentDate());
    private String periodType = "0";
    private String val = "";
    private String period = "";

    private String fromMonth = String.valueOf(getMonth(getCurrentSqlDate()));
    private String toMonth = String.valueOf(getMonth(getCurrentSqlDate()));

    private String fromYear = String.valueOf(getYear(getCurrentSqlDate()));
    private String toYear = String.valueOf(getYear(getCurrentSqlDate()));

    private String createDateTo = "";
    private String createDateFrom = "";

    private String dvuDateFrom = "";
    private String dvuDateTo = "";

    private String tdgDateFrom = "";
    private String tdgDateTo = "";

    private String dmcDateFrom = "";
    private String dmcDateTo = "";
    
    private String fromDate = "";
    private String toDate = "";

    private int tinhId;
    private String tinhName;

    private int qhuId;
    private String qhuName;

    private int pxaId;
    private String pxaName;

    private int projectId;
    private int statusId = -1;

    private String func;
    private int objNum;
    private int indNum;
    private int objId;
    private String objName;
    private int extend = 0;

    private String jobLastUpdate;
    private String jobMsg;

    private int indId;
    private String indCode;
    private String indDesc;
    private int indBaseline;
    
    private int duAnId = -1;

    private int targetYear;
    private int targetQ1;
    private int targetQ2;
    private int targetQ3;
    private int targetQ4;
    private int targetJus;
    private int indNote;
    private String baseline;
    private String yearBaseline;
    private String valueBaseline;
    private String target;
    private String cumulative;
    private String actual;
    private String actualQ1;
    private String actualQ2;
    private String actualQ3;
    private String actualQ4;
    private String percent;
    private String onTarget;
    private String comment;
    private String subFunction = "04.01";

    private String name;
    private int male;
    private int female;
    private String address;
    private String phoneNumber;
    private String methodIntervention;

    private int isDis;
    private int notDis;
    private String typeLevelDis;

    private int numberTimes;

    private int isInsurance;
    private int notInsurance;
    private String total;
    private String totalFemale;
    private String totalUnder6;
    private String totalUnder16;
    private String totalDacam;
    private String totalDihoc;
    private String totalCoviec;
    private String totalVisit;
    private String totalVisitMale;
    private String totalVisitFemale;
    private String totalA;
    private String totalB;
    private String totalC;
    private String totalD;
    private String totalDisVisit;
    private String totalDisMale;
    private String totalDisFemale;
    private String totalDisLv1;
    private String totalDisLv2;
    private String totalDisLv3;
    private String totalDisLv4;
    private int paramvalue_1;

    private int paramvalue_1a;
    private int paramvalue_1b;
    private int paramvalue_2;

    private int paramvalue_2a;
    private int paramvalue_2a1;
    private int paramvalue_2a2;
    private int paramvalue_2a3;
    private int paramvalue_2a4;

    private int paramvalue_2b;
    private int paramvalue_2c;

    private int paramvalue_3;

    private int paramvalue_4;
    private int paramvalue_5;
    private int paramvalue_6;
    private int paramvalue_6a;
    private int paramvalue_6b;
    private int paramvalue_7;
    private int paramvalue_8;
    private int paramvalue_9;
    private int paramvalue_10;
    private int paramvalue_11;
    private int paramvalue_12;
    private int paramvalue_13;
    private int paramvalue_14;
    private int paramvalue_15;
    private int paramvalue_16;
    private int paramvalue_17;
    private int paramvalue_18;
    private int paramvalue_19;
    private int paramvalue_20;
    private int paramvalue_21;
    private int paramvalue_22;
    private int paramvalue_23;
    private int paramvalue_24;
    private int paramvalue_25;
    private int paramvalue_26;
    private int paramvalue_27;
    private int paramvalue_28;
    private int paramvalue_29;
    private int paramvalue_30;
    private int paramvalue_31;
    private int paramvalue_32;
    private int paramvalue_33;
    private int paramvalue_34;
    private int paramvalue_35;
    private int paramvalue_36;
    private int paramvalue_37;
    private int paramvalue_38;
    private int paramvalue_39;
    private int paramvalue_40;
    private int paramvalue_41;
    private int paramvalue_42;
    private int stt;
    private int id;
    private String soTT;
    private String maSo;
    private String ten;
    private int yearBirth;
    private String sex;
    private String danToc;
    private String ngheNghiep;
    private String soNha;
    private String dienThoai;
    private String createDate;
    private String ncsTen;
    private String ncsSdt;
    private String ncsSex;
    private String ncsQheNKT;
    private String trangThai;
    private String dangTat;
    private String tTrangKTat;
    private String dangTatVanDong;
    private String mucDo;
    private String nguyenNhanKTat;
    private String ngayDangTat;
    private String daCam;
    private String ngayDongHS;
    private String lyDoDongHS;
    private String nguoiDongHS;
    private String ngayMoHS;
    
    private String hTroPHCN;
    private String hTroCThiepCN;
    private String hTroDungCu;
    private String hTroCTaoCTVS;
    private String hTroTuiDDuong;
    
    private int hTroCVu1 = 0; 
    private int hTroCVu2 = 0;
    private int hTroCVu3 = 0;
    private int hTroCVu4 = 0;
    
    private int hTroRehad = 0;
    private int hTroHomecare = 0;
  
    private String hTroNguon;
    private int ncauId;
    private String nhuCau;
    private String nCauPHCN;
    private String nCauCThiepCN;
    private String nCauDungCu;
    private String nCauCTaoCTVS;
    private String ngayNhuCau;
    private int htroId;
    private String hoTro;
    private String hoTroNoiNhan;
    private String dungCu;
    private String ngayHoTro;
    private String tgianHoTro;
    private String luotHoTro;
    private String commNgay;
    private String commHoTen;
    private String commMaSo;
    private String commGioiTinh;
    private String commDiaChi;
    private String commP1;
    private String commP2;
    private String commP3;
    private String commP4;
    private String commP5;
    private String commP6;
    private String commP7;
    private String commP8;

    private String ncauVLTL;
    private String ncauHDTL;
    private String ncauNNTL;
    private String ncauDCHT;
    private String ncauWC;

    private String dgNum0;
    private String dgNum1;
    private String dgNum2;
    private String dgNum3;
    private String dgNum4;

    private String dgCTh1;
    private String dgCTh2;
    private String dgCTh3;
    private String dgCTh4;

    private String htroPhcnVNAH;
    private String htroPhcnTTP;
    private String htroPhcnQHU;
    private String htroPhcnPXA;
    
    private String dgNgay;
    private String cthNum0;
    private String cthNum1;
    private String cthNum2;
    private String cthNum3;
    private String cthNum4;

    private String cthTyLe;
    private String cthMuc;
    
    private int khamNum1;
    private int khamNum2;
    private int khamNum3;
    private int khamNum4;
    
    private String createdBy;
    private String startTime;
    private String endTime;
    private String rehab;
    private String homecare;
    
    public FReportKpi() {
    }

    private FBeans store = new FBeans();


    public void setTimeExport(String timeExport) {
        this.timeExport = timeExport;
    }

    public String getTimeExport() {
        return timeExport;
    }


    public void setMonthReport(int monthReport) {
        this.monthReport = monthReport;
    }

    public int getMonthReport() {
        return monthReport;
    }

    public void setQuarterReport(int quarterReport) {
        this.quarterReport = quarterReport;
    }

    public int getQuarterReport() {
        return quarterReport;
    }

    public void setYearReport(int yearReport) {
        this.yearReport = yearReport;
    }

    public int getYearReport() {
        return yearReport;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public String getPeriodType() {
        return periodType;
    }

    public void setFromMonth(String fromMonth) {
        this.fromMonth = fromMonth;
    }

    public String getFromMonth() {
        return fromMonth;
    }

    public void setToMonth(String toMonth) {
        this.toMonth = toMonth;
    }

    public String getToMonth() {
        return toMonth;
    }

    public void setFromYear(String fromYear) {
        this.fromYear = fromYear;
    }

    public String getFromYear() {
        return fromYear;
    }

    public void setToYear(String toYear) {
        this.toYear = toYear;
    }

    public String getToYear() {
        return toYear;
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

    public void setFunc(String func) {
        this.func = func;
    }

    public String getFunc() {
        return func;
    }

    public void setIndNum(int indNum) {
        this.indNum = indNum;
    }

    public int getIndNum() {
        return indNum;
    }

    public void setObjId(int objId) {
        this.objId = objId;
    }

    public int getObjId() {
        return objId;
    }

    public void setObjName(String objName) {
        this.objName = objName;
    }

    public String getObjName() {
        return objName;
    }

    public void setIndId(int indId) {
        this.indId = indId;
    }

    public int getIndId() {
        return indId;
    }

    public void setIndCode(String indCode) {
        this.indCode = indCode;
    }

    public String getIndCode() {
        return indCode;
    }

    public void setIndDesc(String indDesc) {
        this.indDesc = indDesc;
    }

    public String getIndDesc() {
        return indDesc;
    }

    public void setIndBaseline(int indBaseline) {
        this.indBaseline = indBaseline;
    }

    public int getIndBaseline() {
        return indBaseline;
    }

    public void setTargetYear(int targetYear) {
        this.targetYear = targetYear;
    }

    public int getTargetYear() {
        return targetYear;
    }

    public void setTargetQ1(int targetQ1) {
        this.targetQ1 = targetQ1;
    }

    public int getTargetQ1() {
        return targetQ1;
    }

    public void setTargetQ2(int targetQ2) {
        this.targetQ2 = targetQ2;
    }

    public int getTargetQ2() {
        return targetQ2;
    }

    public void setTargetQ3(int targetQ3) {
        this.targetQ3 = targetQ3;
    }

    public int getTargetQ3() {
        return targetQ3;
    }

    public void setTargetQ4(int targetQ4) {
        this.targetQ4 = targetQ4;
    }

    public int getTargetQ4() {
        return targetQ4;
    }

    public void setTargetJus(int targetJus) {
        this.targetJus = targetJus;
    }

    public int getTargetJus() {
        return targetJus;
    }

    public void setIndNote(int indNote) {
        this.indNote = indNote;
    }

    public int getIndNote() {
        return indNote;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPercent() {
        return percent;
    }

    public void setStore(FBeans store) {
        this.store = store;
    }

    public FBeans getStore() {
        return store;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getVal() {
        return val;
    }

    public void setObjNum(int objNum) {
        this.objNum = objNum;
    }

    public int getObjNum() {
        return objNum;
    }

    public void setBaseline(String baseline) {
        this.baseline = baseline;
    }

    public String getBaseline() {
        return baseline;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getTarget() {
        return target;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getActual() {
        return actual;
    }

    public void setSubFunction(String subFunction) {
        this.subFunction = subFunction;
    }

    public String getSubFunction() {
        return subFunction;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMale(int male) {
        this.male = male;
    }

    public int getMale() {
        return male;
    }

    public void setFemale(int female) {
        this.female = female;
    }

    public int getFemale() {
        return female;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setMethodIntervention(String methodIntervention) {
        this.methodIntervention = methodIntervention;
    }

    public String getMethodIntervention() {
        return methodIntervention;
    }

    public void setIsDis(int isDis) {
        this.isDis = isDis;
    }

    public int getIsDis() {
        return isDis;
    }

    public void setNotDis(int notDis) {
        this.notDis = notDis;
    }

    public int getNotDis() {
        return notDis;
    }

    public void setTypeLevelDis(String typeLevelDis) {
        this.typeLevelDis = typeLevelDis;
    }

    public String getTypeLevelDis() {
        return typeLevelDis;
    }

    public void setNumberTimes(int numberTimes) {
        this.numberTimes = numberTimes;
    }

    public int getNumberTimes() {
        return numberTimes;
    }

    public void setIsInsurance(int isInsurance) {
        this.isInsurance = isInsurance;
    }

    public int getIsInsurance() {
        return isInsurance;
    }

    public void setNotInsurance(int notInsurance) {
        this.notInsurance = notInsurance;
    }

    public int getNotInsurance() {
        return notInsurance;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotalFemale(String totalFemale) {
        this.totalFemale = totalFemale;
    }

    public String getTotalFemale() {
        return totalFemale;
    }

    public void setTotalUnder6(String totalUnder6) {
        this.totalUnder6 = totalUnder6;
    }

    public String getTotalUnder6() {
        return totalUnder6;
    }

    public void setTotalUnder16(String totalUnder16) {
        this.totalUnder16 = totalUnder16;
    }

    public String getTotalUnder16() {
        return totalUnder16;
    }

    public void setTotalDacam(String totalDacam) {
        this.totalDacam = totalDacam;
    }

    public String getTotalDacam() {
        return totalDacam;
    }

    public void setTotalDihoc(String totalDihoc) {
        this.totalDihoc = totalDihoc;
    }

    public String getTotalDihoc() {
        return totalDihoc;
    }

    public void setTotalCoviec(String totalCoviec) {
        this.totalCoviec = totalCoviec;
    }

    public String getTotalCoviec() {
        return totalCoviec;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setActualQ1(String actualQ1) {
        this.actualQ1 = actualQ1;
    }

    public String getActualQ1() {
        return actualQ1;
    }

    public void setActualQ2(String actualQ2) {
        this.actualQ2 = actualQ2;
    }

    public String getActualQ2() {
        return actualQ2;
    }

    public void setActualQ3(String actualQ3) {
        this.actualQ3 = actualQ3;
    }

    public String getActualQ3() {
        return actualQ3;
    }

    public void setActualQ4(String actualQ4) {
        this.actualQ4 = actualQ4;
    }

    public String getActualQ4() {
        return actualQ4;
    }

    public void setOnTarget(String onTarget) {
        this.onTarget = onTarget;
    }

    public String getOnTarget() {
        return onTarget;
    }

    public void setParamvalue_1(int paramvalue_1) {
        this.paramvalue_1 = paramvalue_1;
    }

    public int getParamvalue_1() {
        return paramvalue_1;
    }

    public void setParamvalue_2(int paramvalue_2) {
        this.paramvalue_2 = paramvalue_2;
    }

    public int getParamvalue_2() {
        return paramvalue_2;
    }

    public void setParamvalue_3(int paramvalue_3) {
        this.paramvalue_3 = paramvalue_3;
    }

    public int getParamvalue_3() {
        return paramvalue_3;
    }

    public void setParamvalue_4(int paramvalue_4) {
        this.paramvalue_4 = paramvalue_4;
    }

    public int getParamvalue_4() {
        return paramvalue_4;
    }

    public void setParamvalue_5(int paramvalue_5) {
        this.paramvalue_5 = paramvalue_5;
    }

    public int getParamvalue_5() {
        return paramvalue_5;
    }

    public void setParamvalue_6(int paramvalue_6) {
        this.paramvalue_6 = paramvalue_6;
    }

    public int getParamvalue_6() {
        return paramvalue_6;
    }

    public void setParamvalue_7(int paramvalue_7) {
        this.paramvalue_7 = paramvalue_7;
    }

    public int getParamvalue_7() {
        return paramvalue_7;
    }

    public void setParamvalue_8(int paramvalue_8) {
        this.paramvalue_8 = paramvalue_8;
    }

    public int getParamvalue_8() {
        return paramvalue_8;
    }

    public void setParamvalue_9(int paramvalue_9) {
        this.paramvalue_9 = paramvalue_9;
    }

    public int getParamvalue_9() {
        return paramvalue_9;
    }

    public void setParamvalue_10(int paramvalue_10) {
        this.paramvalue_10 = paramvalue_10;
    }

    public int getParamvalue_10() {
        return paramvalue_10;
    }

    public void setParamvalue_11(int paramvalue_11) {
        this.paramvalue_11 = paramvalue_11;
    }

    public int getParamvalue_11() {
        return paramvalue_11;
    }

    public void setParamvalue_12(int paramvalue_12) {
        this.paramvalue_12 = paramvalue_12;
    }

    public int getParamvalue_12() {
        return paramvalue_12;
    }

    public void setParamvalue_13(int paramvalue_13) {
        this.paramvalue_13 = paramvalue_13;
    }

    public int getParamvalue_13() {
        return paramvalue_13;
    }

    public void setParamvalue_14(int paramvalue_14) {
        this.paramvalue_14 = paramvalue_14;
    }

    public int getParamvalue_14() {
        return paramvalue_14;
    }

    public void setParamvalue_15(int paramvalue_15) {
        this.paramvalue_15 = paramvalue_15;
    }

    public int getParamvalue_15() {
        return paramvalue_15;
    }

    public void setParamvalue_16(int paramvalue_16) {
        this.paramvalue_16 = paramvalue_16;
    }

    public int getParamvalue_16() {
        return paramvalue_16;
    }

    public void setParamvalue_17(int paramvalue_17) {
        this.paramvalue_17 = paramvalue_17;
    }

    public int getParamvalue_17() {
        return paramvalue_17;
    }

    public void setParamvalue_18(int paramvalue_18) {
        this.paramvalue_18 = paramvalue_18;
    }

    public int getParamvalue_18() {
        return paramvalue_18;
    }

    public void setParamvalue_19(int paramvalue_19) {
        this.paramvalue_19 = paramvalue_19;
    }

    public int getParamvalue_19() {
        return paramvalue_19;
    }

    public void setParamvalue_20(int paramvalue_20) {
        this.paramvalue_20 = paramvalue_20;
    }

    public int getParamvalue_20() {
        return paramvalue_20;
    }

    public void setParamvalue_21(int paramvalue_21) {
        this.paramvalue_21 = paramvalue_21;
    }

    public int getParamvalue_21() {
        return paramvalue_21;
    }

    public void setParamvalue_22(int paramvalue_22) {
        this.paramvalue_22 = paramvalue_22;
    }

    public int getParamvalue_22() {
        return paramvalue_22;
    }

    public void setParamvalue_23(int paramvalue_23) {
        this.paramvalue_23 = paramvalue_23;
    }

    public int getParamvalue_23() {
        return paramvalue_23;
    }

    public void setParamvalue_24(int paramvalue_24) {
        this.paramvalue_24 = paramvalue_24;
    }

    public int getParamvalue_24() {
        return paramvalue_24;
    }

    public void setParamvalue_25(int paramvalue_25) {
        this.paramvalue_25 = paramvalue_25;
    }

    public int getParamvalue_25() {
        return paramvalue_25;
    }

    public void setParamvalue_26(int paramvalue_26) {
        this.paramvalue_26 = paramvalue_26;
    }

    public int getParamvalue_26() {
        return paramvalue_26;
    }

    public void setParamvalue_27(int paramvalue_27) {
        this.paramvalue_27 = paramvalue_27;
    }

    public int getParamvalue_27() {
        return paramvalue_27;
    }

    public void setParamvalue_28(int paramvalue_28) {
        this.paramvalue_28 = paramvalue_28;
    }

    public int getParamvalue_28() {
        return paramvalue_28;
    }

    public void setParamvalue_29(int paramvalue_29) {
        this.paramvalue_29 = paramvalue_29;
    }

    public int getParamvalue_29() {
        return paramvalue_29;
    }

    public void setParamvalue_30(int paramvalue_30) {
        this.paramvalue_30 = paramvalue_30;
    }

    public int getParamvalue_30() {
        return paramvalue_30;
    }

    public void setParamvalue_31(int paramvalue_31) {
        this.paramvalue_31 = paramvalue_31;
    }

    public int getParamvalue_31() {
        return paramvalue_31;
    }

    public void setParamvalue_32(int paramvalue_32) {
        this.paramvalue_32 = paramvalue_32;
    }

    public int getParamvalue_32() {
        return paramvalue_32;
    }

    public void setParamvalue_33(int paramvalue_33) {
        this.paramvalue_33 = paramvalue_33;
    }

    public int getParamvalue_33() {
        return paramvalue_33;
    }

    public void setParamvalue_34(int paramvalue_34) {
        this.paramvalue_34 = paramvalue_34;
    }

    public int getParamvalue_34() {
        return paramvalue_34;
    }

    public void setParamvalue_35(int paramvalue_35) {
        this.paramvalue_35 = paramvalue_35;
    }

    public int getParamvalue_35() {
        return paramvalue_35;
    }

    public void setParamvalue_36(int paramvalue_36) {
        this.paramvalue_36 = paramvalue_36;
    }

    public int getParamvalue_36() {
        return paramvalue_36;
    }

    public void setParamvalue_37(int paramvalue_37) {
        this.paramvalue_37 = paramvalue_37;
    }

    public int getParamvalue_37() {
        return paramvalue_37;
    }

    public void setParamvalue_38(int paramvalue_38) {
        this.paramvalue_38 = paramvalue_38;
    }

    public int getParamvalue_38() {
        return paramvalue_38;
    }

    public void setParamvalue_39(int paramvalue_39) {
        this.paramvalue_39 = paramvalue_39;
    }

    public int getParamvalue_39() {
        return paramvalue_39;
    }

    public void setParamvalue_40(int paramvalue_40) {
        this.paramvalue_40 = paramvalue_40;
    }

    public int getParamvalue_40() {
        return paramvalue_40;
    }

    public void setParamvalue_41(int paramvalue_41) {
        this.paramvalue_41 = paramvalue_41;
    }

    public int getParamvalue_41() {
        return paramvalue_41;
    }

    public void setParamvalue_42(int paramvalue_42) {
        this.paramvalue_42 = paramvalue_42;
    }

    public int getParamvalue_42() {
        return paramvalue_42;
    }

    public void setYearBaseline(String yearBaseline) {
        this.yearBaseline = yearBaseline;
    }

    public String getYearBaseline() {
        return yearBaseline;
    }

    public void setValueBaseline(String valueBaseline) {
        this.valueBaseline = valueBaseline;
    }

    public String getValueBaseline() {
        return valueBaseline;
    }

    public void setCumulative(String cumulative) {
        this.cumulative = cumulative;
    }

    public String getCumulative() {
        return cumulative;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setExtend(int extend) {
        this.extend = extend;
    }

    public int getExtend() {
        return extend;
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

    public void setTotalVisit(String totalVisit) {
        this.totalVisit = totalVisit;
    }

    public String getTotalVisit() {
        return totalVisit;
    }

    public void setTotalVisitMale(String totalVisitMale) {
        this.totalVisitMale = totalVisitMale;
    }

    public String getTotalVisitMale() {
        return totalVisitMale;
    }

    public void setTotalVisitFemale(String totalVisitFemale) {
        this.totalVisitFemale = totalVisitFemale;
    }

    public String getTotalVisitFemale() {
        return totalVisitFemale;
    }

    public void setTotalA(String totalA) {
        this.totalA = totalA;
    }

    public String getTotalA() {
        return totalA;
    }

    public void setTotalB(String totalB) {
        this.totalB = totalB;
    }

    public String getTotalB() {
        return totalB;
    }

    public void setTotalC(String totalC) {
        this.totalC = totalC;
    }

    public String getTotalC() {
        return totalC;
    }

    public void setTotalD(String totalD) {
        this.totalD = totalD;
    }

    public String getTotalD() {
        return totalD;
    }

    public void setTotalDisVisit(String totalDisVisit) {
        this.totalDisVisit = totalDisVisit;
    }

    public String getTotalDisVisit() {
        return totalDisVisit;
    }

    public void setTotalDisMale(String totalDisMale) {
        this.totalDisMale = totalDisMale;
    }

    public String getTotalDisMale() {
        return totalDisMale;
    }

    public void setTotalDisFemale(String totalDisFemale) {
        this.totalDisFemale = totalDisFemale;
    }

    public String getTotalDisFemale() {
        return totalDisFemale;
    }

    public void setTotalDisLv1(String totalDisLv1) {
        this.totalDisLv1 = totalDisLv1;
    }

    public String getTotalDisLv1() {
        return totalDisLv1;
    }

    public void setTotalDisLv2(String totalDisLv2) {
        this.totalDisLv2 = totalDisLv2;
    }

    public String getTotalDisLv2() {
        return totalDisLv2;
    }

    public void setTotalDisLv3(String totalDisLv3) {
        this.totalDisLv3 = totalDisLv3;
    }

    public String getTotalDisLv3() {
        return totalDisLv3;
    }

    public void setTotalDisLv4(String totalDisLv4) {
        this.totalDisLv4 = totalDisLv4;
    }

    public String getTotalDisLv4() {
        return totalDisLv4;
    }

    public void setCommNgay(String commNgay) {
        this.commNgay = commNgay;
    }

    public String getCommNgay() {
        return commNgay;
    }

    public void setCommHoTen(String commHoTen) {
        this.commHoTen = commHoTen;
    }

    public String getCommHoTen() {
        return commHoTen;
    }

    public void setCommMaSo(String commMaSo) {
        this.commMaSo = commMaSo;
    }

    public String getCommMaSo() {
        return commMaSo;
    }

    public void setCommGioiTinh(String commGioiTinh) {
        this.commGioiTinh = commGioiTinh;
    }

    public String getCommGioiTinh() {
        return commGioiTinh;
    }

    public void setCommDiaChi(String commDiaChi) {
        this.commDiaChi = commDiaChi;
    }

    public String getCommDiaChi() {
        return commDiaChi;
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

    public void setNgayDongHS(String ngayDongHS) {
        this.ngayDongHS = ngayDongHS;
    }

    public String getNgayDongHS() {
        return ngayDongHS;
    }

    public void setHTroPHCN(String hTroPHCN) {
        this.hTroPHCN = hTroPHCN;
    }

    public String getHTroPHCN() {
        return hTroPHCN;
    }

    public void setHTroCThiepCN(String hTroCThiepCN) {
        this.hTroCThiepCN = hTroCThiepCN;
    }

    public String getHTroCThiepCN() {
        return hTroCThiepCN;
    }

    public void setHTroDungCu(String hTroDungCu) {
        this.hTroDungCu = hTroDungCu;
    }

    public String getHTroDungCu() {
        return hTroDungCu;
    }

    public void setHTroCTaoCTVS(String hTroCTaoCTVS) {
        this.hTroCTaoCTVS = hTroCTaoCTVS;
    }

    public String getHTroCTaoCTVS() {
        return hTroCTaoCTVS;
    }

    public void setNCauPHCN(String nCauPHCN) {
        this.nCauPHCN = nCauPHCN;
    }

    public String getNCauPHCN() {
        return nCauPHCN;
    }

    public void setNCauCThiepCN(String nCauCThiepCN) {
        this.nCauCThiepCN = nCauCThiepCN;
    }

    public String getNCauCThiepCN() {
        return nCauCThiepCN;
    }

    public void setNCauDungCu(String nCauDungCu) {
        this.nCauDungCu = nCauDungCu;
    }

    public String getNCauDungCu() {
        return nCauDungCu;
    }

    public void setNCauCTaoCTVS(String nCauCTaoCTVS) {
        this.nCauCTaoCTVS = nCauCTaoCTVS;
    }

    public String getNCauCTaoCTVS() {
        return nCauCTaoCTVS;
    }

    public void setCreateDateTo(String createDateTo) {
        this.createDateTo = createDateTo;
    }

    public String getCreateDateTo() {
        return createDateTo;
    }

    public void setCreateDateFrom(String createDateFrom) {
        this.createDateFrom = createDateFrom;
    }

    public String getCreateDateFrom() {
        return createDateFrom;
    }

    public void setJobLastUpdate(String jobLastUpdate) {
        this.jobLastUpdate = jobLastUpdate;
    }

    public String getJobLastUpdate() {
        return jobLastUpdate;
    }

    public void setJobMsg(String jobMsg) {
        this.jobMsg = jobMsg;
    }

    public String getJobMsg() {
        return jobMsg;
    }

    public void setDvuDateFrom(String dvuDateFrom) {
        this.dvuDateFrom = dvuDateFrom;
    }

    public String getDvuDateFrom() {
        return dvuDateFrom;
    }

    public void setDvuDateTo(String dvuDateTo) {
        this.dvuDateTo = dvuDateTo;
    }

    public String getDvuDateTo() {
        return dvuDateTo;
    }

    public void setTdgDateFrom(String tdgDateFrom) {
        this.tdgDateFrom = tdgDateFrom;
    }

    public String getTdgDateFrom() {
        return tdgDateFrom;
    }

    public void setTdgDateTo(String tdgDateTo) {
        this.tdgDateTo = tdgDateTo;
    }

    public String getTdgDateTo() {
        return tdgDateTo;
    }

    public void setDmcDateFrom(String dmcDateFrom) {
        this.dmcDateFrom = dmcDateFrom;
    }

    public String getDmcDateFrom() {
        return dmcDateFrom;
    }

    public void setDmcDateTo(String dmcDateTo) {
        this.dmcDateTo = dmcDateTo;
    }

    public String getDmcDateTo() {
        return dmcDateTo;
    }


    public void setQhuId(int qhuId) {
        this.qhuId = qhuId;
    }

    public int getQhuId() {
        return qhuId;
    }

    public void setQhuName(String qhuName) {
        this.qhuName = qhuName;
    }

    public String getQhuName() {
        return qhuName;
    }

    public void setPxaId(int pxaId) {
        this.pxaId = pxaId;
    }

    public int getPxaId() {
        return pxaId;
    }

    public void setPxaName(String pxaName) {
        this.pxaName = pxaName;
    }

    public String getPxaName() {
        return pxaName;
    }

    public void setNcauVLTL(String ncauVLTL) {
        this.ncauVLTL = ncauVLTL;
    }

    public String getNcauVLTL() {
        return ncauVLTL;
    }

    public void setNcauHDTL(String ncauHDTL) {
        this.ncauHDTL = ncauHDTL;
    }

    public String getNcauHDTL() {
        return ncauHDTL;
    }

    public void setNcauNNTL(String ncauNNTL) {
        this.ncauNNTL = ncauNNTL;
    }

    public String getNcauNNTL() {
        return ncauNNTL;
    }

    public void setNcauDCHT(String ncauDCHT) {
        this.ncauDCHT = ncauDCHT;
    }

    public String getNcauDCHT() {
        return ncauDCHT;
    }

    public void setNcauWC(String ncauWC) {
        this.ncauWC = ncauWC;
    }

    public String getNcauWC() {
        return ncauWC;
    }

    public void setDgNum0(String dgNum0) {
        this.dgNum0 = dgNum0;
    }

    public String getDgNum0() {
        return dgNum0;
    }

    public void setDgNum1(String dgNum1) {
        this.dgNum1 = dgNum1;
    }

    public String getDgNum1() {
        return dgNum1;
    }

    public void setDgNum2(String dgNum2) {
        this.dgNum2 = dgNum2;
    }

    public String getDgNum2() {
        return dgNum2;
    }

    public void setDgNum3(String dgNum3) {
        this.dgNum3 = dgNum3;
    }

    public String getDgNum3() {
        return dgNum3;
    }

    public void setDgNum4(String dgNum4) {
        this.dgNum4 = dgNum4;
    }

    public String getDgNum4() {
        return dgNum4;
    }

    public void setDgCTh1(String dgCTh1) {
        this.dgCTh1 = dgCTh1;
    }

    public String getDgCTh1() {
        return dgCTh1;
    }

    public void setDgCTh2(String dgCTh2) {
        this.dgCTh2 = dgCTh2;
    }

    public String getDgCTh2() {
        return dgCTh2;
    }

    public void setDgCTh3(String dgCTh3) {
        this.dgCTh3 = dgCTh3;
    }

    public String getDgCTh3() {
        return dgCTh3;
    }

    public void setDgCTh4(String dgCTh4) {
        this.dgCTh4 = dgCTh4;
    }

    public String getDgCTh4() {
        return dgCTh4;
    }

    public void setHtroPhcnVNAH(String htroPhcnVNAH) {
        this.htroPhcnVNAH = htroPhcnVNAH;
    }

    public String getHtroPhcnVNAH() {
        return htroPhcnVNAH;
    }

    public void setHtroPhcnTTP(String htroPhcnTTP) {
        this.htroPhcnTTP = htroPhcnTTP;
    }

    public String getHtroPhcnTTP() {
        return htroPhcnTTP;
    }

    public void setHtroPhcnQHU(String htroPhcnQHU) {
        this.htroPhcnQHU = htroPhcnQHU;
    }

    public String getHtroPhcnQHU() {
        return htroPhcnQHU;
    }

    public void setHtroPhcnPXA(String htroPhcnPXA) {
        this.htroPhcnPXA = htroPhcnPXA;
    }

    public String getHtroPhcnPXA() {
        return htroPhcnPXA;
    }

    public void setDgNgay(String dgNgay) {
        this.dgNgay = dgNgay;
    }

    public String getDgNgay() {
        return dgNgay;
    }

    public void setCthNum0(String cthNum0) {
        this.cthNum0 = cthNum0;
    }

    public String getCthNum0() {
        return cthNum0;
    }

    public void setCthNum1(String cthNum1) {
        this.cthNum1 = cthNum1;
    }

    public String getCthNum1() {
        return cthNum1;
    }

    public void setCthNum2(String cthNum2) {
        this.cthNum2 = cthNum2;
    }

    public String getCthNum2() {
        return cthNum2;
    }

    public void setCthNum3(String cthNum3) {
        this.cthNum3 = cthNum3;
    }

    public String getCthNum3() {
        return cthNum3;
    }

    public void setCthNum4(String cthNum4) {
        this.cthNum4 = cthNum4;
    }

    public String getCthNum4() {
        return cthNum4;
    }

    public void setCthTyLe(String cthTyLe) {
        this.cthTyLe = cthTyLe;
    }

    public String getCthTyLe() {
        return cthTyLe;
    }

    public void setCthMuc(String cthMuc) {
        this.cthMuc = cthMuc;
    }

    public String getCthMuc() {
        return cthMuc;
    }

    public void setDangTatVanDong(String dangTatVanDong) {
        this.dangTatVanDong = dangTatVanDong;
    }

    public String getDangTatVanDong() {
        return dangTatVanDong;
    }

    public void setParamvalue_6a(int paramvalue_6a) {
        this.paramvalue_6a = paramvalue_6a;
    }

    public int getParamvalue_6a() {
        return paramvalue_6a;
    }

    public void setParamvalue_6b(int paramvalue_6b) {
        this.paramvalue_6b = paramvalue_6b;
    }

    public int getParamvalue_6b() {
        return paramvalue_6b;
    }

    public void setParamvalue_1a(int paramvalue_1a) {
        this.paramvalue_1a = paramvalue_1a;
    }

    public int getParamvalue_1a() {
        return paramvalue_1a;
    }

    public void setParamvalue_1b(int paramvalue_1b) {
        this.paramvalue_1b = paramvalue_1b;
    }

    public int getParamvalue_1b() {
        return paramvalue_1b;
    }

    public void setParamvalue_2a(int paramvalue_2a) {
        this.paramvalue_2a = paramvalue_2a;
    }

    public int getParamvalue_2a() {
        return paramvalue_2a;
    }

    public void setParamvalue_2b(int paramvalue_2b) {
        this.paramvalue_2b = paramvalue_2b;
    }

    public int getParamvalue_2b() {
        return paramvalue_2b;
    }

    public void setParamvalue_2c(int paramvalue_2c) {
        this.paramvalue_2c = paramvalue_2c;
    }

    public int getParamvalue_2c() {
        return paramvalue_2c;
    }

    public void setParamvalue_2a1(int paramvalue_2a1) {
        this.paramvalue_2a1 = paramvalue_2a1;
    }

    public int getParamvalue_2a1() {
        return paramvalue_2a1;
    }

    public void setParamvalue_2a2(int paramvalue_2a2) {
        this.paramvalue_2a2 = paramvalue_2a2;
    }

    public int getParamvalue_2a2() {
        return paramvalue_2a2;
    }

    public void setParamvalue_2a3(int paramvalue_2a3) {
        this.paramvalue_2a3 = paramvalue_2a3;
    }

    public int getParamvalue_2a3() {
        return paramvalue_2a3;
    }

    public void setParamvalue_2a4(int paramvalue_2a4) {
        this.paramvalue_2a4 = paramvalue_2a4;
    }

    public int getParamvalue_2a4() {
        return paramvalue_2a4;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setHTroNguon(String hTroNguon) {
        this.hTroNguon = hTroNguon;
    }

    public String getHTroNguon() {
        return hTroNguon;
    }

    public void setTTrangKTat(String tTrangKTat) {
        this.tTrangKTat = tTrangKTat;
    }

    public String getTTrangKTat() {
        return tTrangKTat;
    }
   
    public void setNgayMoHS(String ngayMoHS) {
        this.ngayMoHS = ngayMoHS;
    }

    public String getNgayMoHS() {
        return ngayMoHS;
    }

    public void setKhamNum1(int khamNum1) {
        this.khamNum1 = khamNum1;
    }

    public int getKhamNum1() {
        return khamNum1;
    }

    public void setKhamNum2(int khamNum2) {
        this.khamNum2 = khamNum2;
    }

    public int getKhamNum2() {
        return khamNum2;
    }

    public void setKhamNum3(int khamNum3) {
        this.khamNum3 = khamNum3;
    }

    public int getKhamNum3() {
        return khamNum3;
    }

    public void setKhamNum4(int khamNum4) {
        this.khamNum4 = khamNum4;
    }

    public int getKhamNum4() {
        return khamNum4;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setDuAnId(int duAnId) {
        this.duAnId = duAnId;
    }

    public int getDuAnId() {
        return duAnId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setHTroTuiDDuong(String hTroTuiDDuong) {
        this.hTroTuiDDuong = hTroTuiDDuong;
    }

    public String getHTroTuiDDuong() {
        return hTroTuiDDuong;
    }

    public void setHTroCVu1(int hTroCVu1) {
        this.hTroCVu1 = hTroCVu1;
    }

    public int getHTroCVu1() {
        return hTroCVu1;
    }

    public void setHTroCVu2(int hTroCVu2) {
        this.hTroCVu2 = hTroCVu2;
    }

    public int getHTroCVu2() {
        return hTroCVu2;
    }

    public void setHTroCVu3(int hTroCVu3) {
        this.hTroCVu3 = hTroCVu3;
    }

    public int getHTroCVu3() {
        return hTroCVu3;
    }

    public void setHTroCVu4(int hTroCVu4) {
        this.hTroCVu4 = hTroCVu4;
    }

    public int getHTroCVu4() {
        return hTroCVu4;
    }

    public void setHTroRehad(int hTroRehad) {
        this.hTroRehad = hTroRehad;
    }

    public int getHTroRehad() {
        return hTroRehad;
    }

    public void setHTroHomecare(int hTroHomecare) {
        this.hTroHomecare = hTroHomecare;
    }

    public int getHTroHomecare() {
        return hTroHomecare;
    }

    public void setLyDoDongHS(String lyDoDongHS) {
        this.lyDoDongHS = lyDoDongHS;
    }

    public String getLyDoDongHS() {
        return lyDoDongHS;
    }

    public void setNguoiDongHS(String nguoiDongHS) {
        this.nguoiDongHS = nguoiDongHS;
    }

    public String getNguoiDongHS() {
        return nguoiDongHS;
    }

    public void setTgianHoTro(String tgianHoTro) {
        this.tgianHoTro = tgianHoTro;
    }

    public String getTgianHoTro() {
        return tgianHoTro;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setRehab(String rehab) {
        this.rehab = rehab;
    }

    public String getRehab() {
        return rehab;
    }

    public void setHomecare(String homecare) {
        this.homecare = homecare;
    }

    public String getHomecare() {
        return homecare;
    }

    public void setLuotHoTro(String luotHoTro) {
        this.luotHoTro = luotHoTro;
    }

    public String getLuotHoTro() {
        return luotHoTro;
    }

    public void setSoTT(String soTT) {
        this.soTT = soTT;
    }

    public String getSoTT() {
        return soTT;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNcsQheNKT(String ncsQheNKT) {
        this.ncsQheNKT = ncsQheNKT;
    }

    public String getNcsQheNKT() {
        return ncsQheNKT;
    }

    public void setNguyenNhanKTat(String nguyenNhanKTat) {
        this.nguyenNhanKTat = nguyenNhanKTat;
    }

    public String getNguyenNhanKTat() {
        return nguyenNhanKTat;
    }
}
