package com.form.messages.create;


import com.form.FBeans;
import com.form.FSeed;

import org.apache.struts.upload.FormFile;

public class FCreate extends FSeed


{
    private int sendMail;
    private int sendSms;
    private String cc;
    private FBeans allFiles;
    private FormFile fileUpload;
    private FormFile[] file;
    private int sendConpany;
    private String userRecvFullname;
    private int[] fileIds;
    private int fileId;
    private String fileName;
    private String toAddress;
    private String contentFast;
    
    private String readName;
    private String pathFile;
 //end files
 
    private int id;        
    private String name;   
    private String fulltext;
    private int creator;
    private String timeCreate;
    
    
    private int remove;
    private String userFullName;
    private int reply;
    
    private int idRec;  
    private int hiddenRec;
    private int removeRec;
    private int readed;
    private int toPertion;
    private int type;
    private int delete;
    private int pageIndex;
    private int app;
    
    private int depGroup;     
    private String empDeps;
    private String emps;    
    private int departmentID;    
    private int groupId;    
    private String toDepartment; 
    private int[] usersId; 
    private String checkAllEmp;
    private int[] checkEmp;
    private int amount;
    private int amountSend;
    private int amountRev;
    private int amountRevRead;
    private int amountRevUnRead;
    private int amountDel;
    private long secureId;
    private FBeans empsRev;
    private int send;
    private String order;  
    private int orderIndex;  
    private String checkAllPages;
    
    
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }


    public String getFulltext() {
        return fulltext;
    }

    public void setFulltext(String fulltext) {
        this.fulltext = fulltext;
    }

    

    
    public String getEmpDeps() {
        return empDeps;
    }

    public void setEmpDeps(String empDeps) {
        this.empDeps = empDeps;
    }

    public String getToDepartment() {
        return toDepartment;
    }

    public void setToDepartment(String toDepartment) {
        this.toDepartment = toDepartment;
    }

    public String getEmps() {
        return emps;
    }

    public void setEmps(String emps) {
        this.emps = emps;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }

    
    

    public String getCheckAllPages() {
        return checkAllPages;
    }

    public void setCheckAllPages(String checkAllPages) {
        this.checkAllPages = checkAllPages;
    }


    public int[] getUsersId() {
        return usersId;
    }

    public void setUsersId(int[] usersId) {
        this.usersId = usersId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

   

    public int getRemove() {
        return remove;
    }

    public void setRemove(int remove) {
        this.remove = remove;
    }

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getCheckAllEmp() {
        return checkAllEmp;
    }

    public void setCheckAllEmp(String checkAllEmp) {
        this.checkAllEmp = checkAllEmp;
    }

    public int[] getCheckEmp() {
        return checkEmp;
    }

    public void setCheckEmp(int[] checkEmp) {
        this.checkEmp = checkEmp;
    }

    public int getSend() {
        return send;
    }

    public void setSend(int send) {
        this.send = send;
    }

    public int getHiddenRec() {
        return hiddenRec;
    }

    public void setHiddenRec(int hiddenRec) {
        this.hiddenRec = hiddenRec;
    }

    public int getRemoveRec() {
        return removeRec;
    }

    public void setRemoveRec(int removeRec) {
        this.removeRec = removeRec;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public int getToPertion() {
        return toPertion;
    }

    public void setToPertion(int toPertion) {
        this.toPertion = toPertion;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public int getDepGroup() {
        return depGroup;
    }

    public void setDepGroup(int depGroup) {
        this.depGroup = depGroup;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }


    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getAmountSend() {
        return amountSend;
    }

    public void setAmountSend(int amountSend) {
        this.amountSend = amountSend;
    }

    public int getAmountRev() {
        return amountRev;
    }

    public void setAmountRev(int amountRev) {
        this.amountRev = amountRev;
    }

    public int getAmountDel() {
        return amountDel;
    }

    public void setAmountDel(int amountDel) {
        this.amountDel = amountDel;
    }

    public int getApp() {
        return app;
    }

    public void setApp(int app) {
        this.app = app;
    }

    public FBeans getEmpsRev() {
        return empsRev;
    }

    public void setEmpsRev(FBeans empsRev) {
        this.empsRev = empsRev;
    }

    public long getSecureId() {
        return secureId;
    }

    public void setSecureId(long secureId) {
        this.secureId = secureId;
    }

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmountRevRead() {
        return amountRevRead;
    }

    public void setAmountRevRead(int amountRevRead) {
        this.amountRevRead = amountRevRead;
    }

    public int getAmountRevUnRead() {
        return amountRevUnRead;
    }

    public void setAmountRevUnRead(int amountRevUnRead) {
        this.amountRevUnRead = amountRevUnRead;
    }


    public int getReply() {
        return reply;
    }

    public void setReply(int reply) {
        this.reply = reply;
    }

    public String getReadName() {
        return readName;
    }

    public void setReadName(String readName) {
        this.readName = readName;
    }
    
    public void resetFiles(){
    this.file=null;
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

    public FBeans getAllFiles() {
        return allFiles;
    }

    public void setAllFiles(FBeans allFiles) {
        this.allFiles = allFiles;
    }

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public int getSendMail() {
        return sendMail;
    }

    public void setSendMail(int sendMail) {
        this.sendMail = sendMail;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public int getDelete() {
        return delete;
    }

    public void setDelete(int delete) {
        this.delete = delete;
    }

    public String getUserRecvFullname() {
        return userRecvFullname;
    }

    public void setUserRecvFullname(String userRecvFullname) {
        this.userRecvFullname = userRecvFullname;
    }

    public String getContentFast() {
        return contentFast;
    }

    public void setContentFast(String contentFast) {
        this.contentFast = contentFast;
    }

    public int getSendConpany() {
        return sendConpany;
    }

    public void setSendConpany(int sendConpany) {
        this.sendConpany = sendConpany;
    }

    public int[] getFileIds() {
        return fileIds;
    }

    public void setFileIds(int[] fileIds) {
        this.fileIds = fileIds;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public int getSendSms() {
        return sendSms;
    }

    public void setSendSms(int sendSms) {
        this.sendSms = sendSms;
    }
}
