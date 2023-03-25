package com.form.require.requires;


import com.form.FBeans;
import com.form.FSeed;

import com.inf.admin.IConstantsAdmin;

public class FRequire extends FSeed
{    
    private int rmId;
    private int rmRuleId;
    private int amount;
    private String code;
    private int cateId;
    private int obServer;    
    private int rmStatus;
    private int rmStatusStore;
    private String rmStatusName;
    private String rmStatusIds;
    private String name;
    private String content;
    private String dateline;
    private String datetimto;
    private String datetimFrom;
    private String dateCreate;
    private String timto;
    private String timFrom;
    private String dayOfWeek;
    private int repply;
    private int departmentId;
    private int groupId;
    private int active;
    private long surcureId;
    private long creator;
    private long secureId;    
    private int pagesIndex;
    private String categoryName;
    private String departmentName;
    private String creatorName;
    private long userSend;
    private String userSendName;
    private long userRecv;
    private String userRecvName;
    private FBeans recvUsers= new FBeans();
    
    private int reviewId;
    private int readed;
    private String title;
    private String issue;
    private String timeCreate;
    private String reviewIds;
    private long userReply;
    private int checkReview;    
    private String members;
   
    
    
    
    public void setDayOfWeeks(int[] dayOfWeeks)  { 
         this.dayOfWeek="";
         if(dayOfWeeks!=null){
             for(int i=0;i<dayOfWeeks.length;i++){
                 this.dayOfWeek+= IConstantsAdmin.APP_SEPARATE_ + dayOfWeeks[i] + IConstantsAdmin.APP_SEPARATE_;
             }
         }
     }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public int getRmStatus() {
        return rmStatus;
    }

    public void setRmStatus(int rmStatus) {
        this.rmStatus = rmStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public long getSurcureId() {
        return surcureId;
    }

    public void setSurcureId(long surcureId) {
        this.surcureId = surcureId;
    }

    public String getDatetimto() {
        return datetimto;
    }

    public void setDatetimto(String datetimto) {
        this.datetimto = datetimto;
    }

    public String getDatetimFrom() {
        return datetimFrom;
    }

    public void setDatetimFrom(String datetimFrom) {
        this.datetimFrom = datetimFrom;
    }

    public String getTimto() {
        return timto;
    }

    public void setTimto(String timto) {
        this.timto = timto;
    }

    public String getTimFrom() {
        return timFrom;
    }

    public void setTimFrom(String timFrom) {
        this.timFrom = timFrom;
    }

    

    public int getRepply() {
        return repply;
    }

    public void setRepply(int repply) {
        this.repply = repply;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getPagesIndex() {
        return pagesIndex;
    }

    public void setPagesIndex(int pagesIndex) {
        this.pagesIndex = pagesIndex;
    }

    public int getRmId() {
        return rmId;
    }

    public void setRmId(int rmId) {
        this.rmId = rmId;
    }

    public long getCreator() {
        return creator;
    }

    public void setCreator(long creator) {
        this.creator = creator;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getRmStatusName() {
        return rmStatusName;
    }

    public void setRmStatusName(String rmStatusName) {
        this.rmStatusName = rmStatusName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(String timeCreate) {
        this.timeCreate = timeCreate;
    }

    public String getReviewIds() {
        return reviewIds;
    }

    public void setReviewIds(String reviewIds) {
        this.reviewIds = reviewIds;
    }


    public long getSecureId() {
        return secureId;
    }

    public void setSecureId(long secureId) {
        this.secureId = secureId;
    }

    public long getUserSend() {
        return userSend;
    }

    public void setUserSend(long userSend) {
        this.userSend = userSend;
    }

    public long getUserRecv() {
        return userRecv;
    }

    public void setUserRecv(long userRecv) {
        this.userRecv = userRecv;
    }

    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getUserSendName() {
        return userSendName;
    }

    public void setUserSendName(String userSendName) {
        this.userSendName = userSendName;
    }

    public String getUserRecvName() {
        return userRecvName;
    }

    public void setUserRecvName(String userRecvName) {
        this.userRecvName = userRecvName;
    }

    public FBeans getRecvUsers() {
        return recvUsers;
    }
    
    public void setRecvUsers(FBeans recvUsers) {
        this.recvUsers = recvUsers;
    }
    
    public void setRecvUser(FRequire recvUser) {
       this.getRecvUsers().add(recvUser);
    }

    public int getRmRuleId() {
        return rmRuleId;
    }

    public void setRmRuleId(int rmRuleId) {
        this.rmRuleId = rmRuleId;
    }

    public long getUserReply() {
        return userReply;
    }

    public void setUserReply(long userReply) {
        this.userReply = userReply;
    }

    public String getRmStatusIds() {
        return rmStatusIds;
    }

    public void setRmStatusIds(String rmStatusIds) {
        this.rmStatusIds = rmStatusIds;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCheckReview() {
        return checkReview;
    }

    public void setCheckReview(int checkReview) {
        this.checkReview = checkReview;
    }

    public int getRmStatusStore() {
        return rmStatusStore;
    }

    public void setRmStatusStore(int rmStatusStore) {
        this.rmStatusStore = rmStatusStore;
    }

    public int getObServer() {
        return obServer;
    }

    public void setObServer(int obServer) {
        this.obServer = obServer;
    }
}
