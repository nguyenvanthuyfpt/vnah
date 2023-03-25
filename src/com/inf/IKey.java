
/*
 * IKey.java
 *
  */
package com.inf;


import com.inf.mail.IKeyMail;

import com.lib.AppConfigs;

/**
 * IKey
 */
public class IKey extends  IKeyMail
{
    
    public static final String LUCENE_PATH_INDEX=getValue("LUCENE.PATH.INDEX");
    public static final String LUCENE_PATH_FILE=getValue("LUCENE.PATH.FILE");
    
    
    public static final String SYSTEM_PATH_IMAGES_CONFIG_DEFAULT=getValue("SYSTEM.PATH.IMAGES.CONFIG.DEFAULT");
    public static final String SYSTEM_PATH_STYLES_CONFIG_DEFAULT=getValue("SYSTEM.PATH.STYLES.CONFIG.DEFAULT");


    public static final String APP_DATE_VIETNAMESE   = "APP.DATE.VIETNAMESE";      
    public static final int COUNT_CHECK_DATE = 3;
    public static final int TEMPLATE_HOSTNEW= Integer.parseInt(getValue("TEMPLATE.HOSTNEW"));
    public static final String[] DATE_TYPES= {APP_DATE_YEAR,APP_DATE_MONTH, AppConfigs.APP_DATE_DEFAULT};
    public static final String MESSAGES_FILE_PATH=getValue("MESSAGES.FILE.PATH");
    public static final String TASKS_FILE_PATH=getValue("TASKS.FILE.PATH");
    
    public static final String DOC_FILE_REVIEW_RECV_PATH=getValue("DOC.FILE.REVIEW.RECV.PATH");
    public static final String DOC_FILE_REVIEW_SEND_PATH=getValue("DOC.FILE.REVIEW.SEND.PATH");
    public static final String DOC_FILE_REVIEW_DRAFT_PATH=getValue("DOC.FILE.REVIEW.DRAFT.PATH");
    public static final String FILE_MANAGER=getValue("FILE.MANAGER");
    
    public static final String FILE_MAIL_UPLOAD=getValue("FILE.MAIL.UPLOAD");
    
    public static final String APP_BROADCAST_VIEW=getValue("APP.BROADCAST.VIEW");      
      //FOR DOCS
       public static final String CHECK_OBSERVER_DOCSRECV="BRuleObserverDocsRecv";
       public static final String CHECK_OBSERVER_DOCSRECV_BLOCKFILE="BRuleObserverDocsRecvBlockFile";
       public static final String CHECK_OBSERVER_DOCSRECV_DELETE_DOCS="BRuleObserverDocsRecvDeleteDocs";
       public static final String CHECK_OBSERVER_DOCSSEND="BRuleObserverDocsSend";
       public static final String CHECK_OBSERVER_DOCSSEND_BLOCKFILE="BRuleObserverDocsSendBlockFile";
       public static final String CHECK_OBSERVER_DOCSSEND_DELETE_DOCS="BRuleObserverDocsSendDeleteDocs";
    
    public static final String CHECK_CREATOR_DOCSRECV="BCheckRulesCreatorDataRecv";
    public static final String CHECK_CREATOR_DOCSSEND="BCheckRulesCreatorDataSend";
    public static final String CHECK_CREATOR_DOCSSEND_DT="BCheckRulesCreatorDataSendDT";

    
    
    public static final String CHECK_RULE_DOCSRECV="BRuleDocsRecv";
    public static final String CHECK_RULE_DOCSSEND="BRuleDocsSend";
    
    public static final String CHECK_RULE_REQUIRES="BRuleRequires";
    
    public static final String CHECK_USER_IN_RULE_DOCSSEND="BCheckUserInRulesDocssend";
    public static final String CHECK_USER_IN_RULE_DOCSRECV="BCheckUserInRulesDocsrecv";
    public static final int DOCS_MESSAGES_DATELINE= Integer.parseInt(getValue("DOCS.MESSAGES.DATELINE"));
    public static final int MESSAGES_DAYS_REMOVE= Integer.parseInt(getValue("MESSAGES.DAYS.REMOVE"));
    public static final int DOC_FIELDS_OPEN= Integer.parseInt(getValue("DOC.FIELDS.OPEN"));    
//MODULE EMAIL 
    public static final String SYSTEM_FILE_SCHIP = getValue("SYSTEM.FILE.SCHIP") ;
    public static final String MAIL_FOLDER_UPLOAD = getValue("MAIL.FOLDER.UPLOAD") ;
    public static final String CHECK_OBSERVER_RM="BRuleObserverRm";
    
    public static final int DOCSSEND_WORKFLOWID= Integer.parseInt(getValue("DOCSSEND.WORKFLOWID"));
    public static final int DOCSRECV_WORKFLOWID= Integer.parseInt(getValue("DOCSRECV.WORKFLOWID"));
    
    
}