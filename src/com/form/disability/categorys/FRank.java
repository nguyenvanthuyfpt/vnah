package com.form.disability.categorys;

import com.form.FBeans;
import com.form.FSeed;

public class FRank extends FSeed {
    private int idNkt;
    private int dtlId;
    private int userId;
    private String userName;
    private String breadcrumb;
    private int level;
    private String fullName;
    private String danhgiaIds;
    private int id;
    private int parentID;
    private String code;
    private String name;
    private int report = 0;
    private String createDate = dateToString(getCurrentDate());
    private String modifyDate = dateToString(getCurrentDate());
    private int total = 0;
    private int result = 0;
    private int hasSP = 0;
    private int prevHasSP = 0;
    private int hasDel = 0;
    private int hasRK = 0;
    private int hasRQ = 0;
    private String percent = "";
    private String numRanked = ""; 
    private FBeans ranks;
    private int pageIndex;
    private int totalResult;
    
    private int rankNum;
    private String rankDate;
    private String rsInit;
    private String rsHasSP;
    private String rs1St;
    private String rs2Nd;
    private String rs3Rd;
    
    private String reson;
    private String tochucKhac;


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

    public void reset() {
        this.id = 0;
        this.createDate = dateToString(getCurrentDate());
        this.modifyDate = dateToString(getCurrentDate());
        this.parentID = 0;
        this.code = "";
        this.name = "";
        this.report = 0;
    }

    public void setRanks(FBeans ranks) {
        this.ranks = ranks;
    }

    public FBeans getRanks() {
        return ranks;
    }

    public void setIdNkt(int idNkt) {
        this.idNkt = idNkt;
    }

    public int getIdNkt() {
        return idNkt;
    }

    public void setDanhgiaIds(String danhgiaIds) {
        this.danhgiaIds = danhgiaIds;
    }

    public String getDanhgiaIds() {
        return danhgiaIds;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getReson() {
        return reson;
    }

    public void setTochucKhac(String tochucKhac) {
        this.tochucKhac = tochucKhac;
    }

    public String getTochucKhac() {
        return tochucKhac;
    }
    
    public void setBreadcrumb(String breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public String getBreadcrumb() {
        return breadcrumb;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    
    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setDtlId(int dtlId) {
        this.dtlId = dtlId;
    }

    public int getDtlId() {
        return dtlId;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setReport(int report) {
        this.report = report;
    }

    public int getReport() {
        return report;
    }

    public void setHasSP(int hasSP) {
        this.hasSP = hasSP;
    }

    public int getHasSP() {
        return hasSP;
    }

    public void setRsInit(String rsInit) {
        this.rsInit = rsInit;
    }

    public String getRsInit() {
        return rsInit;
    }

    public void setRsHasSP(String rsHasSP) {
        this.rsHasSP = rsHasSP;
    }

    public String getRsHasSP() {
        return rsHasSP;
    }

    public void setRs1St(String rs1St) {
        this.rs1St = rs1St;
    }

    public String getRs1St() {
        return rs1St;
    }

    public void setRs2Nd(String rs2Nd) {
        this.rs2Nd = rs2Nd;
    }

    public String getRs2Nd() {
        return rs2Nd;
    }

    public void setRs3Rd(String rs3Rd) {
        this.rs3Rd = rs3Rd;
    }

    public String getRs3Rd() {
        return rs3Rd;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankDate(String rankDate) {
        this.rankDate = rankDate;
    }

    public String getRankDate() {
        return rankDate;
    }

    public void setHasRK(int hasRK) {
        this.hasRK = hasRK;
    }

    public int getHasRK() {
        return hasRK;
    }

    public void setHasRQ(int hasRQ) {
        this.hasRQ = hasRQ;
    }

    public int getHasRQ() {
        return hasRQ;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getPercent() {
        return percent;
    }

    public void setNumRanked(String numRanked) {
        this.numRanked = numRanked;
    }

    public String getNumRanked() {
        return numRanked;
    }

    public void setPrevHasSP(int prevHasSP) {
        this.prevHasSP = prevHasSP;
    }

    public int getPrevHasSP() {
        return prevHasSP;
    }

    public void setHasDel(int hasDel) {
        this.hasDel = hasDel;
    }

    public int getHasDel() {
        return hasDel;
    }
}
