package com.form.doc.docsrecv;


import com.form.FBeans;
import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FDocsrecv extends FSeed
{
    private int[] idFiles;
    private int[] emailFileIds;
    private int[] storesId;
    private int storeIdDocInput;
    
    
    private int storeId;
    private String userReply;
    
    private int numberVersion;
    private int numberPage;
    private int classifyId;    
    private String classifyName;
    private int branchId;
    
    private FBeans allFiles;
    private FBeans stores;    
    private String fileName;
    private int docId;
    private long userRecvId;
    private int app;
    private int departmentId;
    private int typeDoc;
    private int type;
    private int blockFile;
    private int userId;
    private int checkCreator;
    private int blockUpdate;
    private int pageIndex;
    private int id;
    private int readed;
    private int forYouId;
    private String checked;
    private String title;
    private String localCode;
    private String docCode;
    private String creator;
    private String storeArea;    
    private String storeIds;        
    private int statusId;
    private int storeStatusId;
    private String timeCreate;
    private String localDate;
    private String docDate;
    private int storeAgeId;
    private int expressId;
    private int secureId;
    private int viaId;
    private int fromId;
    private int formId;
    private String address;
    private int docsTypeId;
    private String abstracts;
    private String description;
    private String signer;
    private String deadLine;
    private int dossierId;
    private int workflowId;
    private int fileId;
    private long changeId;
  
    private FormFile[] file;
    private String issue;
    
    private String formName;
    private String fromVnName;
    private String fromEnName;
    private String statusName;
    private String statusColor;
    private String expressColor;
    private String expressName;
    private String viaName;
    private String docTypeName;
    private String dossiersName;
    private String secureName;
    private String depName;
    
    private String timeSend;
    private int checkDirect;
    private int selectPage;
    private int checkObServer;
    private int checkForYou;
    private int obServer;
    
    private String[] values;
    private int dossierId_doc;
    private int views;
    private String content;
    private int field;
    private String referentId;
    private int docstatus;
    
    
    private String timeCreateFrom;
    private String timeCreateTo;
    
    private String localDateFrom;
    private String localDateTo;
    
    private String docDateFrom;
    private String docDateTo;
    
    private String deadLineFrom;
    private String deadLineTo;
    private FBeans rulesForYou;
    

  public void reset()
  {
        this.setId(0);
        this.setFormId(0);
        this.setLocalCode("");
        this.setDocCode("");
        this.setCreator("");
        this.setStatusId(1);
        this.setTimeCreate("");
        this.setLocalDate("");
        this.setDocDate("");
        this.setStoreAgeId(0);
        this.setExpressId(0);
        this.setSecureId(0);
        this.setViaId(0);
        this.setFromId(0);
        this.setAddress("");
        this.setDocsTypeId(0);
        this.setAbstracts("");
        this.setDescription("");
        this.setSigner("");
        this.setDeadLine("");
        this.setDossierId(0);
        this.setWorkflowId(0);
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

  
  

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public String getDocDate() {
        return docDate;
    }

    public void setDocDate(String docDate) {
        this.docDate = docDate;
    }
    public void resetFiles(){
    this.file=null;
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

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
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

      public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

  
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public FBeans getAllFiles() {
        return allFiles;
    }

    public void setAllFiles(FBeans allFiles) {
        this.allFiles = allFiles;
    }

    public FormFile getFile(int i) {
        if(file != null && file.length>0 && file.length>i){
            return file[i];
        }
        return null;
    }

    public void setFile(int i, FormFile file) {
        if(file.getFileSize()>0){
            FormFile[] mem = null;
            int k = this.file==null?0:this.file.length;
            if(k>0){
                mem = new FormFile[k+1];
                for(int j=0;j<k;j++){
                    mem[j] = this.file[j];
                }
            }
            this.file = new FormFile[k+1];
            this.file[k] = file;
            if(k>0){
                for(int j=0;j<k;j++){
                   this.file[j] = mem[j];
                }
            }
        }
    }

    public int getTotalFile() {
        if(file!=null){
            return file.length;
        }
        return 0;
    }

  
    public int[] getIdFiles() {
        
        return idFiles;
    }

    public void setIdFiles(int[] idFiles) {
        this.idFiles = idFiles;
    }

   
    public int getTypeDoc() {
        return typeDoc;
    }

    public void setTypeDoc(int typeDoc) {
        this.typeDoc = typeDoc;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
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

//    public int getIdFilesRecv(int i) {
//        
//        return idFilesRecv[i];
//    }
//
//    public void setIdFilesRecv(int[] idFilesRecv) {
//        this.idFilesRecv = idFilesRecv;
//    }

    public int getBlockUpdate() {
        return blockUpdate;
    }

    public void setBlockUpdate(int blockUpdate) {
        this.blockUpdate = blockUpdate;
    }

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

  

    public int getSelectPage() {
        return selectPage;
    }

    public void setSelectPage(int selectPage) {
        this.selectPage = selectPage;
    }

    public String getStatusColor() {
        return statusColor;
    }

    public void setStatusColor(String statusColor) {
        this.statusColor = statusColor;
    }


    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getForYouId() {
        return forYouId;
    }

    public void setForYouId(int forYouId) {
        this.forYouId = forYouId;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public long getChangeId() {
        return changeId;
    }

    public void setChangeId(long changeId) {
        this.changeId = changeId;
    }

    public int getCheckDirect() {
        return checkDirect;
    }

    public void setCheckDirect(int checkDirect) {
        this.checkDirect = checkDirect;
    }

    public int getCheckCreator() {
        return checkCreator;
    }

    public void setCheckCreator(int checkCreator) {
        this.checkCreator = checkCreator;
    }

    public int getCheckObServer() {
        return checkObServer;
    }

    public void setCheckObServer(int checkObServer) {
        this.checkObServer = checkObServer;
    }


    public int getObServer() {
        return obServer;
    }

    public void setObServer(int obServer) {
        this.obServer = obServer;
    }

    public String getFromVnName() {
        return fromVnName;
    }

    public void setFromVnName(String fromVnName) {
        this.fromVnName = fromVnName;
    }

    public String getFromEnName() {
        return fromEnName;
    }

    public void setFromEnName(String fromEnName) {
        this.fromEnName = fromEnName;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
 
    public String getValue(int index) {
        return values[index];
    }

    public String[] getValues() {
        return values;
    }
    
    public void createValues(int length){
        values = new String[length];
    }
    public void setValues(int i,String value) {
        this.values[i] = value;
    }

    public int getDossierId_doc() {
        return dossierId_doc;
    }

    public void setDossierId_doc(int dossierId_doc) {
        this.dossierId_doc = dossierId_doc;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getExpressColor() {
        return expressColor;
    }

    public void setExpressColor(String expressColor) {
        this.expressColor = expressColor;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }

    public String getTimeCreateFrom() {
        return timeCreateFrom;
    }

    public void setTimeCreateFrom(String timeCreateFrom) {
        this.timeCreateFrom = timeCreateFrom;
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

    public String getDocDateFrom() {
        return docDateFrom;
    }

    public void setDocDateFrom(String docDateFrom) {
        this.docDateFrom = docDateFrom;
    }

    public String getDocDateTo() {
        return docDateTo;
    }

    public void setDocDateTo(String docDateTo) {
        this.docDateTo = docDateTo;
    }

    public String getDeadLineFrom() {
        return deadLineFrom;
    }

    public void setDeadLineFrom(String deadLineFrom) {
        this.deadLineFrom = deadLineFrom;
    }

    public String getDeadLineTo() {
        return deadLineTo;
    }

    public void setDeadLineTo(String deadLineTo) {
        this.deadLineTo = deadLineTo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getStoreStatusId() {
        return storeStatusId;
    }

    public void setStoreStatusId(int storeStatusId) {
        this.storeStatusId = storeStatusId;
    }

    public FBeans getRulesForYou() {
        return rulesForYou;
    }

    public void setRulesForYou(FBeans rulesForYou) {
        this.rulesForYou = rulesForYou;
    }

    public int getCheckForYou() {
        return checkForYou;
    }

    public void setCheckForYou(int checkForYou) {
        this.checkForYou = checkForYou;
    }

    public int getDocstatus() {
        return docstatus;
    }

    public void setDocstatus(int docstatus) {
        this.docstatus = docstatus;
    }

    public String getReferentId() {
        return referentId;
    }

    public void setReferentId(String referentId) {
        this.referentId = referentId;
    }

    public long getUserRecvId() {
        return userRecvId;
    }

    public void setUserRecvId(long userRecvId) {
        this.userRecvId = userRecvId;
    }

    public int getNumberVersion() {
        return numberVersion;
    }

    public void setNumberVersion(int numberVersion) {
        this.numberVersion = numberVersion;
    }

    public int getNumberPage() {
        return numberPage;
    }

    public void setNumberPage(int numberPage) {
        this.numberPage = numberPage;
    }

    public int getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(int classifyId) {
        this.classifyId = classifyId;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

   

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getUserReply() {
        return userReply;
    }

    public void setUserReply(String userReply) {
        this.userReply = userReply;
    }

    public int getBlockFile() {
        return blockFile;
    }

    public void setBlockFile(int blockFile) {
        this.blockFile = blockFile;
    }

    public int[] getEmailFileIds() {
        return emailFileIds;
    }

    public void setEmailFileIds(int[] emailFileIds) {
        this.emailFileIds = emailFileIds;
    }

    public String getStoreArea() {
        return storeArea;
    }

    public void setStoreArea(String storeArea) {
        this.storeArea = storeArea;
    }

    public int[] getStoresId() {
        return storesId;
    }

    public void setStoresId(int[] storesId) {
        this.storesId = storesId;
    }

    public String getStoreIds() {
        return storeIds;
    }

    public void setStoreIds(String storeIds) {
        this.storeIds = storeIds;
    }

    public FBeans getStores() {
        return stores;
    }

    public void setStores(FBeans stores) {
        this.stores = stores;
    }

    public int getStoreIdDocInput() {
        return storeIdDocInput;
    }

    public void setStoreIdDocInput(int storeIdDocInput) {
        this.storeIdDocInput = storeIdDocInput;
    }
}
