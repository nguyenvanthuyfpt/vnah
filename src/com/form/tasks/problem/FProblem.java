package com.form.tasks.problem;


import com.form.FBeans;
import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FProblem extends FSeed{
//doc
    private int checkHaveReport;
    private int checkHaveReview;
    private int views;
    private int[] checkEmp;
    private int dossierId;
    private int docId;
    private int problemId;
    private int creator;
    private String nameCreator;
    private String title;
    private String problem;   
    private String report;   
    private String timeCreate;
    private int complete;
    private int completeAssign;
    private int root;
    private int assignCheck;
    private String fromDate;
    private String toDate;
    private int categoriesId;
    private FBeans asigns;
    private FBeans reports;
    private int departmentID;
    private FormFile fileUplaod;
    private String fileName;
    private String pathFile;
    private int[] usersId;
    private int[] usersIdNew;
    private int stop;
    private int viewStop;
    private int stopAssign;
    private int checkAllEmp; 
    private int complateSearch;
    private int complate;
    private int workflowId;
    
    private int readed;
    private int accepted;
    private int incharge;
    private String inchargeName;
    private int assignId;
    private int worker;
    private String workerName;
    private String timeCreateAssign;
    
    private String categoriesName;    
    private int type;    
    private int app;
    private int statusId;
    private FormFile fileUpload;
   
    private int pageIndex;
    private int checkEdit;
    private long secureId;
    
    private int amountDeadline;
    private int typeDeadLine;
    
    private int amountSend;
    private int amountTotalsSend;
    private int amountTotalsRecv;
    private int amountRecv;
    private int amountCate;
    private int amount;
    private int amountRecvRead;
    private int amountRecvUnRead;
    
    private int cabinType_id;
    private int back;

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
    }

  

   

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getNameCreator() {
        return nameCreator;
    }

    public void setNameCreator(String nameCreator) {
        this.nameCreator = nameCreator;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

   
    public int getRoot() {
        return root;
    }

    public void setRoot(int root) {
        this.root = root;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public int getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(int categoriesId) {
        this.categoriesId = categoriesId;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    public int[] getUsersId() {
        return usersId;
    }

    public void setUsersId(int[] usersId) {
        this.usersId = usersId;
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

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

   

    public int getIncharge() {
        return incharge;
    }

    public void setIncharge(int incharge) {
        this.incharge = incharge;
    }

    public int getAccepted() {
        return accepted;
    }

    public void setAccepted(int accepted) {
        this.accepted = accepted;
    }

    public int getAssignId() {
        return assignId;
    }

    public void setAssignId(int assignId) {
        this.assignId = assignId;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public String getTimeCreateAssign() {
        return timeCreateAssign;
    }

    public void setTimeCreateAssign(String timeCreateAssign) {
        this.timeCreateAssign = timeCreateAssign;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCheckEdit() {
        return checkEdit;
    }

    public void setCheckEdit(int checkEdit) {
        this.checkEdit = checkEdit;
    }

    public int getComplete() {
        return complete;
    }

    public void setComplete(int complete) {
        this.complete = complete;
    }

    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public String getInchargeName() {
        return inchargeName;
    }

    public void setInchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }
    
    public void reset(){       
       this.title = "";
       this.problem = "";
    }

    public int getComplateSearch() {
        return complateSearch;
    }

    public void setComplateSearch(int complateSearch) {
        this.complateSearch = complateSearch;
    }

    public int getAssignCheck() {
        return assignCheck;
    }

    public void setAssignCheck(int assignCheck) {
        this.assignCheck = assignCheck;
    }

    public FBeans getAsigns() {
        return asigns;
    }

    public void setAsigns(FBeans asigns) {
        this.asigns = asigns;
    }

    public FBeans getReports() {
        return reports;
    }

    public void setReports(FBeans reports) {
        this.reports = reports;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public int getCheckAllEmp() {
        return checkAllEmp;
    }

    public void setCheckAllEmp(int checkAllEmp) {
        this.checkAllEmp = checkAllEmp;
    }

    public int[] getUsersIdNew() {
        return usersIdNew;
    }

    public void setUsersIdNew(int[] usersIdNew) {
        this.usersIdNew = usersIdNew;
    }

    public int getStop() {
        return stop;
    }

    public void setStop(int stop) {
        this.stop = stop;
    }

    public long getSecureId() {
        return secureId;
    }

    public void setSecureId(long secureId) {
        this.secureId = secureId;
    }

    public int getStopAssign() {
        return stopAssign;
    }

    public void setStopAssign(int stopAssign) {
        this.stopAssign = stopAssign;
    }

    public int getCompleteAssign() {
        return completeAssign;
    }

    public void setCompleteAssign(int completeAssign) {
        this.completeAssign = completeAssign;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public int getAmountSend() {
        return amountSend;
    }

    public void setAmountSend(int amountSend) {
        this.amountSend = amountSend;
    }

    public int getAmountRecv() {
        return amountRecv;
    }

    public void setAmountRecv(int amountRecv) {
        this.amountRecv = amountRecv;
    }

    public int getAmountCate() {
        return amountCate;
    }

    public void setAmountCate(int amountCate) {
        this.amountCate = amountCate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountRecvRead() {
        return amountRecvRead;
    }

    public void setAmountRecvRead(int amountRecvRead) {
        this.amountRecvRead = amountRecvRead;
    }

    public int getAmountRecvUnRead() {
        return amountRecvUnRead;
    }

    public void setAmountRecvUnRead(int amountRecvUnRead) {
        this.amountRecvUnRead = amountRecvUnRead;
    }

    public int getAmountDeadline() {
        return amountDeadline;
    }

    public void setAmountDeadline(int amountDeadline) {
        this.amountDeadline = amountDeadline;
    }

    public int getTypeDeadLine() {
        return typeDeadLine;
    }

    public void setTypeDeadLine(int typeDeadLine) {
        this.typeDeadLine = typeDeadLine;
    }

    public int getAmountTotalsSend() {
        return amountTotalsSend;
    }

    public void setAmountTotalsSend(int amountTotalsSend) {
        this.amountTotalsSend = amountTotalsSend;
    }

    public int getAmountTotalsRecv() {
        return amountTotalsRecv;
    }

    public void setAmountTotalsRecv(int amountTotalsRecv) {
        this.amountTotalsRecv = amountTotalsRecv;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getDossierId() {
        return dossierId;
    }

    public void setDossierId(int dossierId) {
        this.dossierId = dossierId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public int getComplate() {
        return complate;
    }

    public void setComplate(int complate) {
        this.complate = complate;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
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

    public int getCheckHaveReview() {
        return checkHaveReview;
    }

    public void setCheckHaveReview(int checkHaveReview) {
        this.checkHaveReview = checkHaveReview;
    }

    public int getViewStop() {
        return viewStop;
    }

    public void setViewStop(int viewStop) {
        this.viewStop = viewStop;
    }

    public int getCabinType_id() {
        return cabinType_id;
    }

    public void setCabinType_id(int cabinType_id) {
        this.cabinType_id = cabinType_id;
    }

    public int getBack() {
        return back;
    }

    public void setBack(int back) {
        this.back = back;
    }
}
