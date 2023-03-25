package com.form.doc.docsSearch;

import com.form.FBeans;
import com.form.FSeed;


public class FDocsSearch extends FSeed
{

    
    private int[] fields;
    private String[] docsId;
    private int type;
    private int fileId;
    private int pageIndex;
    private int typeDoc;
    private int id;
    private int userId;
    private int formId;
    private String localCode;//
     private String temp;//
    private String docCode;
    private String abstracts;
    private String description;
    private String creator;
    private int statusId;
    private String timeCreateFrom;
    private String timeCreate;
    private String deadLine;
    private String docDate;
    private String localDate;
    private String timeCreateTo;
    private String localDateFrom;
    private String issue;
    private String localDateTo;
    private String docDateFrom;
    
    private String docDateTo;
    private String deadLineFrom;
    private String deadLineTo;
    private String DocRererenceTemp;
    
    private int storeAgeId;
    private int expressId;
    private int secureId;
    private int viaId;
    private int fromId;
    private String address;
    private int docsTypeId;
    private String signer;
    
    private int dossierId;
    private int workflowId;
    private int export; 
    private FBeans BFiles;
    
    
    //DETAIL
    private String formName;
    private String statusName;
    private String expressName;
    private String viaName;
    private String docTypeName;
    private String dossiersName;
    private String secureName;
    
    
    
    
    

  public void reset()
  {
        this.setId(0);
        this.setFormId(0);
        this.setLocalCode("");
        this.setDocCode("");
        this.setCreator("");
        this.setStatusId(0);
        this.setTimeCreateFrom("");
        this.setTimeCreateTo("");
        this.setLocalDateFrom("");
        this.setLocalDateTo("");
        this.setDocDateFrom("");
        this.setDocDateTo("");
        this.setStoreAgeId(0);
        this.setExpressId(0);
        this.setSecureId(0);
        this.setViaId(0);
        this.setFromId(0);
        this.setAddress("");
        this.setDocsTypeId(0);
        this.setSigner("");
        this.setDeadLineFrom("");
        this.setDeadLineTo("");
        this.setDossierId(0);
        this.setWorkflowId(0);
  }

    public int getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(int typeDoc) {
        this.typeDoc = typeDoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFormId() {
        return formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public String getLocalCode() {
        return localCode;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

  
  

    public String getTimeCreateTo() {
        return timeCreateTo;
    }

    public void setTimeCreateTo(String timeCreateTo) {
        this.timeCreateTo = timeCreateTo;
    }

    public String getLocalDateFrom() {
        return localDateFrom;
    }

    public void setLocalDateFrom(String localDateFrom) {
        this.localDateFrom = localDateFrom;
    }

    public String getLocalDateTo() {
        return localDateTo;
    }

    public void setLocalDateTo(String localDateTo) {
        this.localDateTo = localDateTo;
    }

  

    public String getDocDateTo() {
        return docDateTo;
    }

    public void setDocDateTo(String docDateTo) {
        this.docDateTo = docDateTo;
    }

  
    public String getDeadLineTo() {
        return deadLineTo;
    }

    public void setDeadLineTo(String deadLineTo) {
        this.deadLineTo = deadLineTo;
    }

    public int getStoreAgeId() {
        return storeAgeId;
    }

    public void setStoreAgeId(int storeAgeId) {
        this.storeAgeId = storeAgeId;
    }

    public int getExpressId() {
        return expressId;
    }

    public void setExpressId(int expressId) {
        this.expressId = expressId;
    }

    public int getSecureId() {
        return secureId;
    }

    public void setSecureId(int secureId) {
        this.secureId = secureId;
    }

    public int getViaId() {
        return viaId;
    }

    public void setViaId(int viaId) {
        this.viaId = viaId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDocsTypeId() {
        return docsTypeId;
    }

    public void setDocsTypeId(int docsTypeId) {
        this.docsTypeId = docsTypeId;
    }

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public int getDossierId() {
        return dossierId;
    }

    public void setDossierId(int dossierId) {
        this.dossierId = dossierId;
    }

    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public String getTimeCreateFrom() {
        return timeCreateFrom;
    }

    public void setTimeCreateFrom(String timeCreateFrom) {
        this.timeCreateFrom = timeCreateFrom;
    }

    public String getDocDateFrom() {
        return docDateFrom;
    }

    public void setDocDateFrom(String docDateFrom) {
        this.docDateFrom = docDateFrom;
    }

    public String getDeadLineFrom() {
        return deadLineFrom;
    }

    public void setDeadLineFrom(String deadLineFrom) {
        this.deadLineFrom = deadLineFrom;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getViaName() {
        return viaName;
    }

    public void setViaName(String viaName) {
        this.viaName = viaName;
    }

    public String getDocTypeName() {
        return docTypeName;
    }

    public void setDocTypeName(String docTypeName) {
        this.docTypeName = docTypeName;
    }

    public String getDossiersName() {
        return dossiersName;
    }

    public void setDossiersName(String dossiersName) {
        this.dossiersName = dossiersName;
    }

    public String getSecureName() {
        return secureName;
    }

    public void setSecureName(String secureName) {
        this.secureName = secureName;
    }

    public FBeans getBFiles() {
        return BFiles;
    }

    public void setBFiles(FBeans BFiles) {
        this.BFiles = BFiles;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int[] getFields() {
        return fields;
    }

    public void setFields(int[] fields) {
        this.fields = fields;
    }

    public int getExport() {
        return export;
    }

    public void setExport(int export) {
        this.export = export;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String[] getDocsId() {
        return docsId;
    }

    public void setDocsId(String[] docsId) {
        this.docsId = docsId;
    }

    public String getDocRererenceTemp() {
        return DocRererenceTemp;
    }

    public void setDocRererenceTemp(String DocRererenceTemp) {
        this.DocRererenceTemp = DocRererenceTemp;
    }
}
