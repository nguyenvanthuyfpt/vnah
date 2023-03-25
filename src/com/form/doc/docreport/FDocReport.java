package com.form.doc.docreport;


import com.form.FSeed;

public class FDocReport extends FSeed
{
    public void reset(){
        this.docCode="";
        this.abstracts="";
        this.signer="";
        this.docDate="";
        this.fromVnName="";
    }
        private String docCode;
        private String abstracts;
        private String docDate;
        private String fromVnName;
        private String description;
        private String signer;
        private String docIds;
        private int workflowId;
         private int typeTotal;
    
        private int type;
        private int[] fields;
        private int typeReport;
        private String fromDate;
        private String toDate;
    
        private int pageIndex;
        private int statusId;
        private String statusName;
        
        private int transferId;
        private String  transferName;
        
        private int docTypeId;
        private String  docTypeName;
        
        private int branchId;
        private String  branchName;
        
        private int amount;
        private int userId;
        private int depId;
        private int checkObserver;
        private int totalReport;

   

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public int getTypeReport() {
        return typeReport;
    }

    public void setTypeReport(int typeReport) {
        this.typeReport = typeReport;
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

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public String getTransferName() {
        return transferName;
    }

    public void setTransferName(String transferName) {
        this.transferName = transferName;
    }

    public int getDocTypeId() {
        return docTypeId;
    }

    public void setDocTypeId(int docTypeId) {
        this.docTypeId = docTypeId;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int[] getFields() {
        return fields;
    }

    public void setFields(int[] fields) {
        this.fields = fields;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getFromVnName() {
        return fromVnName;
    }

    public void setFromVnName(String fromVnName) {
        this.fromVnName = fromVnName;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getDocIds() {
        return docIds;
    }

    public void setDocIds(String docIds) {
        this.docIds = docIds;
    }


    public int getCheckObserver() {
        return checkObserver;
    }

    public void setCheckObserver(int checkObserver) {
        this.checkObserver = checkObserver;
    }

    public int getTotalReport() {
        return totalReport;
    }

    public void setTotalReport(int totalReport) {
        this.totalReport = totalReport;
    }

    public int getTypeTotal() {
        return typeTotal;
    }

    public void setTypeTotal(int typeTotal) {
        this.typeTotal = typeTotal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
