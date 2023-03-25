package com.inf.ForYou;

import com.inf.admin.IFieldsAdmin;

public interface IFieldsForYou extends IFieldsAdmin {

    //  SET OF FIELDS ON FORYOU TABLES
    public final String FORYOU_ID="ID";
    public final String FORYOU_USER_ID_FROM="USER_ID_FROM";
    public final String FORYOU_USER_ID_TO="USER_ID_TO";
    public final String FORYOU_PROBLEM="PROBLEM";
    public final String FORYOU_DATEFROM="DATEFROM";
    public final String FORYOU_DATETO="DATETO";
    public final String FORYOU_STATUS="STATUS";
    public final String FORYOU_DATECREATE="DATECREATE";
    public final String FORYOU_WORKFLOW_ID="WORKFLOW_ID";
    
    public final String[] FORYOU_ALL_FIELDS = {FORYOU_USER_ID_FROM, FORYOU_USER_ID_TO,FORYOU_PROBLEM,FORYOU_DATEFROM,FORYOU_DATETO,FORYOU_STATUS,FORYOU_DATECREATE,FORYOU_WORKFLOW_ID}; 



}
