package com.inf.doc;

import com.inf.ForYou.IKeyForYou;


public class IKeyDoc extends IKeyForYou {  
    
    public static final int DOC_STATUS_VIEW_DEADLINE= Integer.parseInt(getValue("DOC.STATUS.VIEW.DEADLINE"));
    public static final int DOC_STATUS_VIEW_ALL= Integer.parseInt(getValue("DOC.STATUS.VIEW.ALL"));
    public static final int DOC_STATUS_VIEW_WAIT= Integer.parseInt(getValue("DOC.STATUS.VIEW.WAIT"));
    
    public static final int RM_STATUS_UNREAD = Integer.parseInt(getValue("RM.STATUS.UNREAD"));
    public static final int RM_STATUS_NEW   = Integer.parseInt(getValue("RM.STATUS.NEW"));
    public static final int RM_STATUS_END  = Integer.parseInt(getValue("RM.STATUS.STORE"));
    public static final int RM_STATUS_VIEW_ALL  = Integer.parseInt(getValue("RM.STATUS.VIEW.ALL"));
    
    
    public static final int STATUS_UNREAD= Integer.parseInt(getValue("STATUS.UNREAD"));
    public static final int STATUS_NEW= Integer.parseInt(getValue("STATUS.NEW"));
    public static final int STATUS_END= Integer.parseInt(getValue("STATUS.END"));
    public static final int STATUS_STORE= Integer.parseInt(getValue("STATUS.STORE"));
    public static final int DOC_STATUS_COUNT_NOT_IN= Integer.parseInt(getValue("DOC.STATUS.COUNT.NOT.IN"));
    public static final int DOC_CLASSIFY_ACTIVE= Integer.parseInt(getValue("DOC.CLASSIFY.ACTIVE"));
    public static final int DOC_READ_EXCUTE= Integer.parseInt(getValue("DOC.READ.EXCUTE"));
    public static final String DOC_NOIMAGER_PATH_FILE= getValue("DOC.NOIMAGER.PATH.FILE") ;
    public static final int DOCSSEND_WORKFLOWID= Integer.parseInt(getValue("DOCSSEND.WORKFLOWID"));
    public static final int DOCSRECV_WORKFLOWID= Integer.parseInt(getValue("DOCSRECV.WORKFLOWID"));
    public static final int REPORT_SHEETLIST_X = Integer.parseInt(getValue("REPORT.SHEETLIST.X")) ;
    public static final int REPORT_SHEETLIST_Y = Integer.parseInt(getValue("REPORT.SHEETLIST.Y")) ;    
    public static final int DOC_STORE_DOCSEND_DOCRECV= Integer.parseInt(getValue("DOC.STORE.DOCSEND.DOCRECV"));
    
    public static final int REPORT_TYPE_TOTALS_STATUS=0;
    public static final int REPORT_TYPE_TOTALS_VIEWS=1;
    public static final int REPORT_TYPE_TOTALS_DOCTYPE=2;
    public static final int REPORT_TYPE_TOTALS_BRANCH=3;
}