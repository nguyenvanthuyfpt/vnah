package com.inf.report;



public interface IFieldsReport {
    
    //TABLE REPORT TYPE
     public final String REPORT_CATEGORY_ID= "CATEGORY_ID";
     public final String REPORT_CATEGORY_CODE = "CODE";
     public final String REPORT_CATEGORY_NAME = "NAME";
     public final String REPORT_CATEGORY_DESCRIPTION = "DESCRIPTION";
     public final String[] REPORT_CATEGORY_ALL_FIELDS ={REPORT_CATEGORY_CODE,REPORT_CATEGORY_NAME,REPORT_CATEGORY_DESCRIPTION};

    //TABLE REPORT
     public final String REPORT_ID="ID";
     public final String REPORT_USERS_ID="USERS_ID";
     public final String REPORT_NAME="NAME";
     public final String REPORT_DESCRIPTION="DESCRIPTION";
     public final String REPORT_TIMECREATE="TIMECREATE";        
     public final String REPORT_PERMISSION="PERMISSION";        
     public final String REPORT_TYPE_ID="CATEGORY_ID";
     public final String REPORT_FILESTORE="FILESTORE";
     public final String REPORT_REALNAME="REALNAME";
     public final String REPORT_NAME_CATEGORY="NAME_CATEGORY";
     public final String[] REPORT_ALL_FIELDS = {REPORT_USERS_ID,REPORT_NAME,REPORT_DESCRIPTION,REPORT_TIMECREATE,REPORT_PERMISSION,REPORT_TYPE_ID,REPORT_FILESTORE,REPORT_REALNAME}; 
   
   
    //TABLE REPORT_SHARE
     public final String REPORT_SHARE_USER_ID="USER_ID";
     public final String REPORT_SHARE_REPORT_ID="REPORT_ID";
     public final String[] REPORT_SHARE_ALL_FIELDS = {REPORT_SHARE_REPORT_ID,REPORT_SHARE_USER_ID}; 
        
}
