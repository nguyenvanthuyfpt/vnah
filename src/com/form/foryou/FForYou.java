package com.form.foryou;

import com.form.FSeed;


public class FForYou extends FSeed {
    //them 2 fiels dem
    
    private String boss;
    private String forWho;
    private int id;
    private int pageIndex;
    private int userIdFrom;
    private long meId;
    private int userIdTo;
    private String problem;
    private String dateFrom;
    private String dateTo;
    private int status;
    private String dateCreate;
    private int workflowId ;
    private int publicInfor;
    
    private String titleMess;
    private String tempMess;
    
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

    
    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public int getUserIdFrom() {
        return userIdFrom;
    }

    public void setUserIdFrom(int userIdFrom) {
        this.userIdFrom = userIdFrom;
    }

    public int getUserIdTo() {
        return userIdTo;
    }

    public void setUserIdTo(int userIdTo) {
        this.userIdTo = userIdTo;
    }

    public String getBoss() {
        return boss;
    }

    public void setBoss(String boss) {
        this.boss = boss;
    }

    public String getForWho() {
        return forWho;
    }

    public void setForWho(String forWho) {
        this.forWho = forWho;
    }


    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public long getMeId() {
        return meId;
    }

    public void setMeId(long meId) {
        this.meId = meId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPublicInfor() {
        return publicInfor;
    }

    public void setPublicInfor(int publicInfor) {
        this.publicInfor = publicInfor;
    }

    public String getTitleMess() {
        return titleMess;
    }

    public void setTitleMess(String titleMess) {
        this.titleMess = titleMess;
    }

    public String getTempMess() {
        return tempMess;
    }

    public void setTempMess(String tempMess) {
        this.tempMess = tempMess;
    }
}
