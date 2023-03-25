
package com.form.mail;


import com.form.FBeans;
import com.form.FSeed;

import javax.mail.Address;

import org.apache.struts.upload.FormFile;


public class FMail  extends FSeed
{
    private int checkIsTo;
    private int errorMsg;
    private int[] fileIds;
    private Address[] ccs=null;
    private Address[] tos=null;
    private String personal;
    private String searchContent;
    private String toAddress;
    private String restoreFolder;
    private String till;
    private FBeans allFiles =new FBeans();
    private FormFile[] file;
    private FormFile filetem;
    private String fileName;
    private int checkHaveFile;
    private int fileId;
    private int totalInbox;
    private String fileSize;
    private String fileRead;
    private int pageIndex;
    private String filePath;
    //mail
    private int readed;
    private int accountId;
    private int type;
    private int sercure;
    private int mailId;
    private int[] mailIds;
    private String hostMail;
    private int postMail;
    private String userMail;
    private String passMail;
    private String from;
    private String to;
    private String replyTo;
    
    private String cc;
    private String subject;
    private String content;
    private String dateSend;
    private String dateRecv;
    private String folderName;
    
    
    //count messager
    private int totalNewMessage;
    private int totalUnReadedMessage;
    private int totalDeletedMessage;
    private int totalSentMessage;
    private int totalMessage;
    private int flagged;
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
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
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

  

    public int getPostMail() {
        return postMail;
    }

    public void setPostMail(int postMail) {
        this.postMail = postMail;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getPassMail() {
        return passMail;
    }

    public void setPassMail(String passMail) {
        this.passMail = passMail;
    }

    public String getHostMail() {
        return hostMail;
    }

    public void setHostMail(String hostMail) {
        this.hostMail = hostMail;
    }

    public int getMailId() {
        return mailId;
    }

    public void setMailId(int mailId) {
        this.mailId = mailId;
    }

    public String getDateRecv() {
        return dateRecv;
    }

    public void setDateRecv(String dateRecv) {
        this.dateRecv = dateRecv;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getFileRead() {
        return fileRead;
    }

    public void setFileRead(String fileRead) {
        this.fileRead = fileRead;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public FormFile getFiletem() {
        return filetem;
    }

    public void setFiletem(FormFile filetem) {
        this.filetem = filetem;
    }

    public int getTotalInbox() {
        return totalInbox;
    }

    public void setTotalInbox(int totalInbox) {
        this.totalInbox = totalInbox;
    }

  

    public int getFileId() {
        return fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    public FBeans getAllFiles() {
        return allFiles;
    }

    public void setAllFiles(FBeans allFiles) {
        this.allFiles = allFiles;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getTotalNewMessage() {
        return totalNewMessage;
    }

    public void setTotalNewMessage(int totalNewMessage) {
        this.totalNewMessage = totalNewMessage;
    }

    public int getTotalDeletedMessage() {
        return totalDeletedMessage;
    }

    public void setTotalDeletedMessage(int totalDeletedMessage) {
        this.totalDeletedMessage = totalDeletedMessage;
    }

    public int getTotalMessage() {
        return totalMessage;
    }

    public void setTotalMessage(int totalMessage) {
        this.totalMessage = totalMessage;
    }

    public int getTotalUnReadedMessage() {
        return totalUnReadedMessage;
    }

    public void setTotalUnReadedMessage(int totalUnReadedMessage) {
        this.totalUnReadedMessage = totalUnReadedMessage;
    }

    public int getTotalSentMessage() {
        return totalSentMessage;
    }

    public void setTotalSentMessage(int totalSentMessage) {
        this.totalSentMessage = totalSentMessage;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int[] getMailIds() {
        return mailIds;
    }

    public void setMailIds(int[] mailIds) {
        this.mailIds = mailIds;
    }
    public void setMailIds(int indexId,int value) {
        this.mailIds[indexId] = value;
    }
    public String getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public int getSercure() {
        return sercure;
    }

    public void setSercure(int sercure) {
        this.sercure = sercure;
    }

    public int getCheckHaveFile() {
        return checkHaveFile;
    }

    public void setCheckHaveFile(int checkHaveFile) {
        this.checkHaveFile = checkHaveFile;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }


    public Address[] getCcs() {
        return ccs;
    }

    public void setCcs(Address[] ccs) {
        this.ccs = ccs;
    }

    public Address[] getTos() {
        return tos;
    }

    public void setTos(Address[] tos) {
        this.tos = tos;
    }

    public int getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(int errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public int[] getFileIds() {
        return fileIds;
    }

    public void setFileIds(int[] fileIds) {
        this.fileIds = fileIds;
    }

    public String getTill() {
        return till;
    }

    public void setTill(String till) {
        this.till = till;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public int getFlagged() {
        return flagged;
    }

    public void setFlagged(int flagged) {
        this.flagged = flagged;
    }

    public String getRestoreFolder() {
        return restoreFolder;
    }

    public void setRestoreFolder(String restoreFolder) {
        this.restoreFolder = restoreFolder;
    }


    public int getCheckIsTo() {
        return checkIsTo;
    }

    public void setCheckIsTo(int checkIsTo) {
        this.checkIsTo = checkIsTo;
    }
}
