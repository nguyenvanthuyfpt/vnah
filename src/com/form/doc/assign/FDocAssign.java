package com.form.doc.assign;


import com.form.FBeans;
import com.form.FSeed;

import org.apache.struts.upload.FormFile;


public class FDocAssign extends FSeed
{
    private int checkDefineFileEmit;
    private int checkClassify;
    private int storeId;
    private int storeClassify;
    private int tabActive;
    private int wrongWay;
    private FBeans recvUsers= new FBeans();
    private int[] usersId;
    private String[]  disposeUser;
    private long[]  viewUser;
    private int[] idFiles; 
    private int departmentId;
    private int ruleId;
    private int dossierId;
    private long changeId;
    private int review;
    private int groupId;    
    private long meId;
    private int usersAssign;
    private int usersRecv;
    private int notReply;
    private int foryouCreator;
    private int forYouId;
    private int workflowId;
    private int readed;
    private String docName;
    private String sendUserName;
    private String nameCreator;
    private String recvUserName;
    private String docCode;
    private String createDate;
    private String timeSend;
    private int checkAllEmp ;
    private int checkEmp;
    private int type;
    private int id;
    private long creator;
    private String userReply;
    
    private int direct;
    private int activeForyou;
    private int pageIndex;
    
    private int reviewId;
    private int docId;
    private int fileId;
    private String timeCreate;  
    private String title;
    private String issue; 
    private FormFile fileUpload; 
    private String fileName; 
    private String pathFile; 
    private int checkDirect;
    private int checkAssign;
    private int checkObserver;
    private int checkReview;
    private int checkSelectRecv;
    private int checkComman;
    private int checkActive;
    private int statusId;
    private int checkSelectDept;
    private int checkViewReview;
    private long secureId;
    private int checkStore;
    private int views;
    private int obServer;
    private String deadLine;
    private int checkDetail;
    private int checkReply;
    private int checkDeadline;
    private int checkUnReaded;
    private int checkNotIncharge;
    private int checkStoreDrapt;
    private int checkShowTransfer;
    private String checkTcaption;
    private String checkRecaption;
    private String checkRebcaption;
    private String checkRedcaption;
    private int checkDocreference;
    private int checkReadOnly;
    private String statusIds;
    
    private int checkDossier;
    private int checkDocReply;
    private int checkDocTranfer;
    private int checkUpdateDraft;
    private int checkExcuteGroup;
    private int checkSendSms;
    private int checkExcuteNotView;
    private int checkReviewFile;
    private int checkForyouAssign;
    private int checkReviewForYou;
    private int block;
    private int indexTrailer;
    
    
    private String members;
    private String membersFile;
    private String reviewIds;
    private String groupMembers;
    private String departmentMembers;
    
    
    private String fromVnName;
    private String nameStatus;
    private String abstracts;
    private String docDate;
    private String signer;
    private String currentDateLocal;
    
    private FBeans userRecvIds=new FBeans();
    public int[] getUsersId() {
        return usersId;
    }

    public void setUsersId(int[] usersId) {
        this.usersId = usersId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getUsersAssign() {
        return usersAssign;
    }

    public void setUsersAssign(int usersAssign) {
        this.usersAssign = usersAssign;
    }

    public int getUsersRecv() {
        return usersRecv;
    }

    public void setUsersRecv(int usersRecv) {
        this.usersRecv = usersRecv;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public int getCheckAllEmp() {
        return checkAllEmp;
    }

    public void setCheckAllEmp(int checkAllEmp) {
        this.checkAllEmp = checkAllEmp;
    }

    public int getCheckEmp() {
        return checkEmp;
    }

    public void setCheckEmp(int checkEmp) {
        this.checkEmp = checkEmp;
    }

  

  

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getDocId() {
        return docId;
    }

    public void setDocId(int docId) {
        this.docId = docId;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


   

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public FormFile getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FormFile fileUpload) {
        this.fileUpload = fileUpload;
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

    public String getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(String timeSend) {
        this.timeSend = timeSend;
    }

    public String getSendUserName() {
        return sendUserName;
    }

    public void setSendUserName(String sendUserName) {
        this.sendUserName = sendUserName;
    }


    public String getRecvUserName() {
        return recvUserName;
    }

    public void setRecvUserName(String recvUserName) {
        this.recvUserName = recvUserName;
    }

   

   

    
    public String getNameCreator() {
        return nameCreator;
    }

    public void setNameCreator(String nameCreator) {
        this.nameCreator = nameCreator;
    }

    public int getCheckReview() {
        return checkReview;
    }

    public void setCheckReview(int checkReview) {
        this.checkReview = checkReview;
    }

   
  
  
    public int getWorkflowId() {
        return workflowId;
    }

    public void setWorkflowId(int workflowId) {
        this.workflowId = workflowId;
    }

    public int getCheckDirect() {
        return checkDirect;
    }

    public void setCheckDirect(int checkDirect) {
        this.checkDirect = checkDirect;
    }

    public int getCheckAssign() {
        return checkAssign;
    }

    public void setCheckAssign(int checkAssign) {
        this.checkAssign = checkAssign;
    }

    public int getCheckObserver() {
        return checkObserver;
    }

    public void setCheckObserver(int checkObserver) {
        this.checkObserver = checkObserver;
    }

    public int getCheckSelectRecv() {
        return checkSelectRecv;
    }

    public void setCheckSelectRecv(int checkSelectRecv) {
        this.checkSelectRecv = checkSelectRecv;
    }

    public int getCheckComman() {
        return checkComman;
    }

    public void setCheckComman(int checkComman) {
        this.checkComman = checkComman;
    }

   

    public int getCheckActive() {
        return checkActive;
    }

    public void setCheckActive(int checkActive) {
        this.checkActive = checkActive;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public long getMeId() {
        return meId;
    }

    public void setMeId(long meId) {
        this.meId = meId;
    }

    public int getCheckSelectDept() {
        return checkSelectDept;
    }

    public void setCheckSelectDept(int checkSelectDept) {
        this.checkSelectDept = checkSelectDept;
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

    public int getForyouCreator() {
        return foryouCreator;
    }

    public void setForyouCreator(int foryouCreator) {
        this.foryouCreator = foryouCreator;
    }

    public int getCheckViewReview() {
        return checkViewReview;
    }

    public void setCheckViewReview(int checkViewReview) {
        this.checkViewReview = checkViewReview;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(String deadLine) {
        this.deadLine = deadLine;
    }


    public FBeans getUserRecvIds() {
        return userRecvIds;
    }

    public void setUserRecvIds(FBeans userRecvIds) {
        this.userRecvIds = userRecvIds;
    }

    public FBeans getRecvUsers() {
        return recvUsers;
    }

    public void setRecvUsers(FBeans recvUsers) {
        this.recvUsers = recvUsers;
    }
    
    public void setRecvUser(FDocAssign recvUser) {
       this.getRecvUsers().add(recvUser);
    }

    public int getTabActive() {
        return tabActive;
    }

    public void setTabActive(int tabActive) {
        this.tabActive = tabActive;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getDossierId() {
        return dossierId;
    }

    public void setDossierId(int dossierId) {
        this.dossierId = dossierId;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public int getObServer() {
        return obServer;
    }

    public void setObServer(int obServer) {
        this.obServer = obServer;
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

    public int getCheckDetail() {
        return checkDetail;
    }

    public void setCheckDetail(int checkDetail) {
        this.checkDetail = checkDetail;
    }

    public int getCheckReply() {
        return checkReply;
    }

    public void setCheckReply(int checkReply) {
        this.checkReply = checkReply;
    }

    public String[] getDisposeUser() {
        return disposeUser;
    }

    public void setDisposeUser(String[] disposeUser) {
        this.disposeUser = disposeUser;
    }

    public long[] getViewUser() {
        return viewUser;
    }

    public void setViewUser(long[] viewUser) {
        this.viewUser = viewUser;
    }

   
    public int getCheckStoreDrapt() {
        return checkStoreDrapt;
    }

    public void setCheckStoreDrapt(int checkStoreDrapt) {
        this.checkStoreDrapt = checkStoreDrapt;
    }

    public String getStatusIds() {
        return statusIds;
    }

    public void setStatusIds(String statusIds) {
        this.statusIds = statusIds;
    }

    public int getCheckShowTransfer() {
        return checkShowTransfer;
    }

    public void setCheckShowTransfer(int checkShowTransfer) {
        this.checkShowTransfer = checkShowTransfer;
    }

    public int getCheckDeadline() {
        return checkDeadline;
    }

    public void setCheckDeadline(int checkDeadline) {
        this.checkDeadline = checkDeadline;
    }

    public int getCheckUnReaded() {
        return checkUnReaded;
    }

    public void setCheckUnReaded(int checkUnReaded) {
        this.checkUnReaded = checkUnReaded;
    }

    public int getCheckNotIncharge() {
        return checkNotIncharge;
    }

    public void setCheckNotIncharge(int checkNotIncharge) {
        this.checkNotIncharge = checkNotIncharge;
    }

    public String getCheckTcaption() {
        return checkTcaption;
    }

    public void setCheckTcaption(String checkTcaption) {
        this.checkTcaption = checkTcaption;
    }

    public String getCheckRecaption() {
        return checkRecaption;
    }

    public void setCheckRecaption(String checkRecaption) {
        this.checkRecaption = checkRecaption;
    }

    public String getFromVnName() {
        return fromVnName;
    }

    public void setFromVnName(String fromVnName) {
        this.fromVnName = fromVnName;
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

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getCheckRebcaption() {
        return checkRebcaption;
    }

    public void setCheckRebcaption(String checkRebcaption) {
        this.checkRebcaption = checkRebcaption;
    }

    public String getCheckRedcaption() {
        return checkRedcaption;
    }

    public void setCheckRedcaption(String checkRedcaption) {
        this.checkRedcaption = checkRedcaption;
    }


    public int getCheckDocreference() {
        return checkDocreference;
    }

    public void setCheckDocreference(int checkDocreference) {
        this.checkDocreference = checkDocreference;
    }

    public int getBlock() {
        return block;
    }

    public void setBlock(int block) {
        this.block = block;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public int getCheckDossier() {
        return checkDossier;
    }

    public void setCheckDossier(int checkDossier) {
        this.checkDossier = checkDossier;
    }

    public int getCheckDocReply() {
        return checkDocReply;
    }

    public void setCheckDocReply(int checkDocReply) {
        this.checkDocReply = checkDocReply;
    }

    public int getCheckDocTranfer() {
        return checkDocTranfer;
    }

    public void setCheckDocTranfer(int checkDocTranfer) {
        this.checkDocTranfer = checkDocTranfer;
    }

    public String getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(String groupMembers) {
        this.groupMembers = groupMembers;
    }

    public String getDepartmentMembers() {
        return departmentMembers;
    }

    public void setDepartmentMembers(String departmentMembers) {
        this.departmentMembers = departmentMembers;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public String getNameStatus() {
        return nameStatus;
    }

    public void setNameStatus(String nameStatus) {
        this.nameStatus = nameStatus;
    }


    public int getCheckUpdateDraft() {
        return checkUpdateDraft;
    }

    public void setCheckUpdateDraft(int checkUpdateDraft) {
        this.checkUpdateDraft = checkUpdateDraft;
    }

    public int getCheckReadOnly() {
        return checkReadOnly;
    }

    public void setCheckReadOnly(int checkReadOnly) {
        this.checkReadOnly = checkReadOnly;
    }

    

    public int getCheckStore() {
        return checkStore;
    }

    public void setCheckStore(int checkStore) {
        this.checkStore = checkStore;
    }

    public int getCheckReviewFile() {
        return checkReviewFile;
    }

    public void setCheckReviewFile(int checkReviewFile) {
        this.checkReviewFile = checkReviewFile;
    }

    public int getCheckExcuteNotView() {
        return checkExcuteNotView;
    }

    public void setCheckExcuteNotView(int checkExcuteNotView) {
        this.checkExcuteNotView = checkExcuteNotView;
    }

    public int getCheckExcuteGroup() {
        return checkExcuteGroup;
    }

    public void setCheckExcuteGroup(int checkExcuteGroup) {
        this.checkExcuteGroup = checkExcuteGroup;
    }

    public int getCheckForyouAssign() {
        return checkForyouAssign;
    }

    public void setCheckForyouAssign(int checkForyouAssign) {
        this.checkForyouAssign = checkForyouAssign;
    }

    public int getCheckReviewForYou() {
        return checkReviewForYou;
    }

    public void setCheckReviewForYou(int checkReviewForYou) {
        this.checkReviewForYou = checkReviewForYou;
    }

    public String getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(String reviewIds) {
        this.reviewIds = reviewIds;
    }

    public int getCheckDefineFileEmit() {
        return checkDefineFileEmit;
    }

    public void setCheckDefineFileEmit(int checkDefineFileEmit) {
        this.checkDefineFileEmit = checkDefineFileEmit;
    }

    public String getMembersFile() {
        return membersFile;
    }

    public void setMembersFile(String membersFile) {
        this.membersFile = membersFile;
    }

    public int getCheckClassify() {
        return checkClassify;
    }

    public void setCheckClassify(int checkClassify) {
        this.checkClassify = checkClassify;
    }

    public int getStoreClassify() {
        return storeClassify;
    }

    public void setStoreClassify(int storeClassify) {
        this.storeClassify = storeClassify;
    }

    public int getNotReply() {
        return notReply;
    }

    public void setNotReply(int notReply) {
        this.notReply = notReply;
    }

    public int getForYouId() {
        return forYouId;
    }

    public void setForYouId(int forYouId) {
        this.forYouId = forYouId;
    }

    public int getWrongWay() {
        return wrongWay;
    }

    public void setWrongWay(int wrongWay) {
        this.wrongWay = wrongWay;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getActiveForyou() {
        return activeForyou;
    }

    public void setActiveForyou(int activeForyou) {
        this.activeForyou = activeForyou;
    }

    public int[] getIdFiles() {
        return idFiles;
    }

    public void setIdFiles(int[] idFiles) {
        this.idFiles = idFiles;
    }

    public String getUserReply() {
        return userReply;
    }

    public void setUserReply(String userReply) {
        this.userReply = userReply;
    }

    public int getIndexTrailer() {
        return indexTrailer;
    }

    public void setIndexTrailer(int indexTrailer) {
        this.indexTrailer = indexTrailer;
    }

    public String getCurrentDateLocal() {
        return currentDateLocal;
    }

    public void setCurrentDateLocal(String currentDateLocal) {
        this.currentDateLocal = currentDateLocal;
    }

    public int getCheckSendSms() {
        return checkSendSms;
    }

    public void setCheckSendSms(int checkSendSms) {
        this.checkSendSms = checkSendSms;
    }
}
