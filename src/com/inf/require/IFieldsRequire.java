package com.inf.require;
public interface IFieldsRequire {
    
    //TABLE RM_RULE_TRAILER
    public final String RM_RULE_TRAILER_RULE_ID                 = "REQUIRE_RULE_ID";
    public final String RM_RULE_TRAILER_TITLE                   = "TITLE";
    public final String RM_RULE_TRAILER_ACTIVE                  = "ACTIVE";
    public final String RM_RULE_TRAILER_STATUS_ID               = "STATUS_ID";
    public final String RM_RULE_TRAILER_CREATOR                 = "CREATOR";
    public final String RM_RULE_TRAILER_TIMECREATE              = "TIMECREATE";
    public final String RM_RULE_TRAILER_RM_REPPLY               = "RM_REPPLY";
    public final String RM_RULE_TRAILER_RM_COMMENT              = "RM_COMMENT";
    public final String RM_RULE_TRAILER_RM_TRAILER              = "RM_TRAILER";
    public final String RM_RULE_TRAILER_RM_DIRECT               = "RM_DIRECT";
    public final String RM_RULE_TRAILER_RM_SELECT_DEPARTMENT    = "RM_SELECT_DEPARTMENT";
    public final String RM_RULE_TRAILER_RM_SELECT_EMP           = "RM_SELECT_EMP";
    public final String RM_RULE_TRAILER_RM_DEADLINE             = "DEADLINE";
    public final String RM_RULE_TRAILER_RM_NOTINCHARGE          = "NOTINCHARGE";
    public final String RM_RULE_TRAILER_RM_STATUSES             = "STATUSES";
    public final String RM_RULE_TRAILER_RM_STATUSES_STORE       = "STATUS_STORE";
    public final String RM_RULE_TRAILER_RM_STORE                = "STORE";
    public final String RM_RULE_TRAILER_RM_COMMENT_VIEW         = "COMMENT_VIEW";
    
    public final String[] RM_RULE_TRAILER_ALL_FIELDS ={RM_RULE_TRAILER_TITLE,RM_RULE_TRAILER_ACTIVE,RM_RULE_TRAILER_STATUS_ID,RM_RULE_TRAILER_CREATOR,RM_RULE_TRAILER_TIMECREATE,RM_RULE_TRAILER_RM_REPPLY,RM_RULE_TRAILER_RM_COMMENT,RM_RULE_TRAILER_RM_TRAILER,RM_RULE_TRAILER_RM_DIRECT,RM_RULE_TRAILER_RM_SELECT_DEPARTMENT,RM_RULE_TRAILER_RM_SELECT_EMP,RM_RULE_TRAILER_RM_DEADLINE,RM_RULE_TRAILER_RM_NOTINCHARGE,RM_RULE_TRAILER_RM_STATUSES,RM_RULE_TRAILER_RM_STATUSES_STORE,RM_RULE_TRAILER_RM_STORE,RM_RULE_TRAILER_RM_COMMENT_VIEW};
    
    //TABLE RM_RULE_BOSS
    public final String RM_RULE_BOSS_REQUIRE_RULE_ID      = "REQUIRE_RULE_ID";
    public final String  RM_RULE_BOSS_USER_ID             = "USER_ID";
    public final String[] RM_RULE_BOSS_REQUIRE_ALL_FIELDS ={RM_RULE_BOSS_REQUIRE_RULE_ID,RM_RULE_BOSS_USER_ID};
     //TABLE RM_RULE_OFFICE
    public final String RM_RULE_OFFICE_USER_ID           = "USER_ID";
    public final String RM_RULE_OFFICE_REQUIRE_RULE_ID   = "REQUIRE_RULE_ID";
    public final String RM_RULE_OFFICE_PRIORITIE         = "PRIORITIE";
    public final String[] RM_RULE_OFFICE_ALL_FIELDS ={RM_RULE_OFFICE_USER_ID,RM_RULE_OFFICE_REQUIRE_RULE_ID,RM_RULE_OFFICE_PRIORITIE};
    //TABLE RM_CATEGORY
     public final String RM_CATEGORY_RM_CAT_ID           = "RM_CAT_ID";
     public final String RM_CATEGORY_CODE                = "CODE";
     public final String RM_CATEGORY_RM_CAT_NAME         = "RM_CAT_NAME";
     public final String RM_CATEGORY_RM_CAT_PARENT_ID    = "RM_CAT_PARENT_ID";
     public final String[] REPORT_RM_CATEGORY_ALL_FIELDS ={RM_CATEGORY_CODE,RM_CATEGORY_RM_CAT_NAME,RM_CATEGORY_RM_CAT_PARENT_ID};
    
    //TABLE RM_STATUS
     public final String RM_STATUS_STATUS_ID= "STATUS_ID";
     public final String RM_STATUS_CODE = "CODE";
     public final String RM_STATUS_NAME = "NAME";
     public final String RM_STATUS_DESCRIPTION = "DESCRIPTION";
     public final String[] REPORT_RM_STATUS_ALL_FIELDS ={RM_STATUS_CODE,RM_STATUS_NAME,RM_STATUS_DESCRIPTION};
     
    //TABLE RM_REVIEW
     public final String RM_REVIEW_REVIEW_ID   = "REVIEW_ID";
     public final String RM_REVIEW_RM_ID       = "RM_ID";
     public final String RM_REVIEW_CREATOR     = "CREATOR";
     public final String RM_REVIEW_READED      = "READED";
     public final String RM_REVIEW_TIMECREATE  = "TIMECREATE";
     public final String RM_REVIEW_TITLE       = "TITLE";
      public final String RM_REVIEW_ISSUE        = "ISSUE";
    public final String RM_REVIEW_REVIEW_IDS       = "REVIEW_IDS";
     public final String[] REPORT_RM_REVIEW_ALL_FIELDS ={RM_REVIEW_RM_ID,RM_REVIEW_CREATOR,RM_REVIEW_READED,RM_REVIEW_TIMECREATE,RM_REVIEW_TITLE,RM_REVIEW_ISSUE,RM_REVIEW_REVIEW_IDS};
    
    //BANG REPORT
     public final String RM_REQUIRE_ID="RM_ID";
     public final String RM_REQUIRE_RM_CODE="RM_CODE";
     public final String RM_REQUIRE_RM_TITLE="RM_TITLE";
     public final String RM_REQUIRE_RM_DATETO="RM_DATETO";
     public final String RM_REQUIRE_RM_DATEFROM="RM_DATEFROM";
     public final String RM_REQUIRE_RM_TIMETO="RM_TIMETO";        
     public final String RM_REQUIRE_RM_TIMEFROM="RM_TIMEFROM";        
     public final String RM_REQUIRE_RM_WEEK_DAY="RM_WEEK_DAY";
     public final String RM_REQUIRE_RM_REPLY="RM_REPLY";
     public final String RM_REQUIRE_RM_DEPARTMENT_ID="RM_DEPARTMENT_ID";
     public final String RM_REQUIRE_RM_CATEGORY_ID="RM_CATEGORY_ID";
     public final String RM_REQUIRE_RM_STATUS_ID="RM_STATUS_ID";
     public final String RM_REQUIRE_RM_CONTENT="RM_CONTENT";
     public final String RM_REQUIRE_RM_DATELINE="RM_DATELINE";
     public final String RM_REQUIRE_RM_ACTIVE="RM_ACTIVE";  
     public final String RM_REQUIRE_RM_CREATOR="RM_CREATOR";  
     public final String RM_REQUIRE_RM_DATECREATE="RM_DATECREATE";  
     public final String[] RM_REQUIRE_ALL_FIELDS = {RM_REQUIRE_RM_CODE,RM_REQUIRE_RM_TITLE,RM_REQUIRE_RM_DATETO,RM_REQUIRE_RM_DATEFROM,RM_REQUIRE_RM_TIMETO,RM_REQUIRE_RM_TIMEFROM,RM_REQUIRE_RM_WEEK_DAY,RM_REQUIRE_RM_REPLY,RM_REQUIRE_RM_DEPARTMENT_ID,RM_REQUIRE_RM_CATEGORY_ID,RM_REQUIRE_RM_STATUS_ID,RM_REQUIRE_RM_CONTENT,RM_REQUIRE_RM_ACTIVE,RM_REQUIRE_RM_CREATOR,RM_REQUIRE_RM_DATECREATE}; 
     
    //TABLE RM_TRAILER
     public final String RM_TRAILER_RM_ID          = "RM_ID";     
     public final String RM_TRAILER_USERSEND_ID    = "USERSEND_ID";     
     public final String RM_TRAILER_READED         = "READED";
     public final String RM_TRAILER_TIMESEND       = "TIMESEND";
     public final String RM_TRAILER_RMSTATUS        = "RM_STATUS";
     public final String RM_TRAILER_USERRECV_ID     = "USERRECV_ID";
     public final String[] RM_TRAILER_ALL_FIELDS ={RM_TRAILER_RM_ID,RM_TRAILER_USERSEND_ID,RM_TRAILER_READED,RM_TRAILER_TIMESEND,RM_TRAILER_RMSTATUS,RM_TRAILER_USERRECV_ID};
     
    //TABLE RM_OBSERVER
     public final String RM_OBSERVER_USER_ID          = "USER_ID";     
     public final String RM_OBSERVER_FULLNAME         = "FULLNAME"; 
     public final String RM_OBSERVER_DELETE_REQUIRE   = "DELETE_REQUIRE"; 
     public final String[] RM_OBSERVER_ALL_FIELDS ={RM_OBSERVER_USER_ID,RM_OBSERVER_DELETE_REQUIRE,RM_OBSERVER_FULLNAME};
     
}
