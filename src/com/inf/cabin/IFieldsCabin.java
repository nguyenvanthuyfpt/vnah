package com.inf.cabin;



public interface IFieldsCabin {
    //  SET OF FIELDS ON CABIN TABLES
        public final String CABIN_ID="ID";
        public final String CABIN_USERS_ID="USERS_ID";
        public final String CABIN_NAME="NAME";
        public final String CABIN_DESCRIPTION="DESCRIPTION";
        public final String CABIN_TIMECREATE="TIMECREATE";        
        public final String CABIN_CABINTYPE_ID="CABINTYPE_ID";
        public final String CABIN_FILESTORE="FILESTORE";
        public final String CABIN_REALNAME="REALNAME";
        public final String CABIN_CAPACITY="CAPACITY";
        public final String CABIN_TYPE="TYPE";
        public final String[] CABIN_ALL_FIELDS = {CABIN_USERS_ID,CABIN_NAME,CABIN_DESCRIPTION,CABIN_TIMECREATE,CABIN_CABINTYPE_ID,CABIN_FILESTORE,CABIN_REALNAME,CABIN_CAPACITY,CABIN_TYPE}; 

//TABLE CABIN_SHARE
    public final String CABIN_SHARE_USER_ID="USER_ID";
    public final String CABIN_SHARE_CABIN_ID="CABIN_ID";
    public final String[] CABIN_SHARE_ALL_FIELDS = {CABIN_SHARE_CABIN_ID,CABIN_SHARE_USER_ID}; 
    //  SET OF FIELDS ON CABINTYPE TABLES
    public final String CABINTYPE_ID="ID";
    public final String CABINTYPE_CODE="CODE";
    public final String CABINTYPE_NAME="NAME";
    public final String CABINTYPE_DESCRIPTION="DESCRIPTION";
    public final String CABINTYPE_USER_ID="USER_ID";
    public final String CABINTYPE_PARENT_ID="PARENT_ID";
    public final String CABINTYPE_TYPE="TYPE";
    public final String CABINTYPE_DEPARTMENT_ID="DEPARTMENT_ID";    
    public final String[] CABINTYPE_ALL_FIELDS = {CABINTYPE_PARENT_ID,CABINTYPE_USER_ID,CABINTYPE_CODE,CABINTYPE_NAME,CABINTYPE_DESCRIPTION,CABINTYPE_TYPE,CABINTYPE_DEPARTMENT_ID}; 
}
