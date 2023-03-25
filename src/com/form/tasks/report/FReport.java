package com.form.tasks.report;


import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FReport extends FSeed{
    private int checkHaveReport;
    private String checkEmps;
    private int[] checkEmp;
    private int id;
    private int problemId;
    private int assignId;  
    private int creator; 
    private String report;
    private String timeReport;
    private String reportUser;
    private FormFile fileUplaod;
    private String fileName;
    private String pathFile;
    private int type;
    private String problemName;
    
    private String creatorName;
    private int  complate;
    private int  complateAssign;
    private int  worker;
    private int  incharge;
    private int  pageIndex;
    private long secureId;

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public String getTimeReport() {
        return timeReport;
    }

    public void setTimeReport(String timeReport) {
        this.timeReport = timeReport;
    }

    public FormFile getFileUplaod() {
        return fileUplaod;
    }

    public void setFileUplaod(FormFile fileUplaod) {
        this.fileUplaod = fileUplaod;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public int getComplate() {
        return complate;
    }

    public void setComplate(int complate) {
        this.complate = complate;
    }

    public int getIncharge() {
        return incharge;
    }

    public void setIncharge(int incharge) {
        this.incharge = incharge;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getSecureId() {
        return secureId;
    }

    public void setSecureId(long secureId) {
        this.secureId = secureId;
    }

    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public int getComplateAssign() {
        return complateAssign;
    }

    public void setComplateAssign(int complateAssign) {
        this.complateAssign = complateAssign;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public int getCheckHaveReport() {
        return checkHaveReport;
    }

    public void setCheckHaveReport(int checkHaveReport) {
        this.checkHaveReport = checkHaveReport;
    }

    public int[] getCheckEmp() {
        return checkEmp;
    }

    public void setCheckEmp(int[] checkEmp) {
        this.checkEmp = checkEmp;
    }

    public String getCheckEmps() {
        return checkEmps;
    }

    public void setCheckEmps(String checkEmps) {
        this.checkEmps = checkEmps;
    }
}
