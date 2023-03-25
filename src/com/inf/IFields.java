package com.inf;


import com.inf.ForYou.IFieldsForYou;
import com.inf.admin.IFieldsAdmin;
import com.inf.admin.doc.IFieldsDocAdmin;
import com.inf.agenda.IFieldsAgenda;
import com.inf.cabin.IFieldsCabin;
import com.inf.disability.IFieldsDisability;
import com.inf.doc.IFieldsDoc;
import com.inf.mail.IFieldsMail;
import com.inf.messages.IFieldsMessages;
import com.inf.report.IFieldsReport;
import com.inf.require.IFieldsRequire;
import com.inf.tasks.IFieldsTasks;


public interface IFields extends IFieldsAdmin,IFieldsDocAdmin,IFieldsMessages,IFieldsTasks,IFieldsForYou ,IFieldsCabin,IFieldsAgenda,IFieldsReport,IFieldsDoc,IFieldsMail,IFieldsRequire,IFieldsDisability{

    //  SET OF FIELDS ON SERVEY TABLES
    public final String SERVEY_ID="SERVEYID";
    public final String SERVEY_CODE="CODE";
    public final String SERVEY_NAME="NAME";
    public final String SERVEY_DESCRIPTION="DESCRIPTION";
    public final String SERVEY_FROM_DATE="FROMDATE";
    public final String SERVEY_TO_DATE="TODATE";
    public final String SERVEY_CREATETIME="CREATETIME";
    public final String SERVEY_ORDERS="ORDERS";
    public final String SERVEY_POSITION="POSITION";
    public final String SERVEY_ACTIVE="ACTIVE";
    public final String[] SERVEY_ALL_FIELDS = {SERVEY_CODE,SERVEY_NAME,SERVEY_DESCRIPTION,SERVEY_FROM_DATE,SERVEY_TO_DATE,SERVEY_CREATETIME,SERVEY_ORDERS,SERVEY_POSITION,SERVEY_ACTIVE}; 
    
    //  SET OF FIELDS ON SERVEY_QUESTIONS TABLES
    public final String  QUESTIONS_ID="QUESTION_ID";
    public final String  QUESTIONS_SERVEY_ID="SERVEY_ID";
    public final String  QUESTIONS_QUESTION="QUESTION";
    public final String  QUESTIONS_COUNT="COUNT";
    public final String  QUESTIONS_ORDERS="ORDERS";
    public final String[] QUESTIONS_ALL_FIELDS={QUESTIONS_SERVEY_ID,QUESTIONS_QUESTION,QUESTIONS_COUNT,QUESTIONS_ORDERS};
    public final String[] QUESTIONS_ALL_FIELDS_UPDATE={QUESTIONS_SERVEY_ID,QUESTIONS_QUESTION,QUESTIONS_ORDERS};
    
    //SET FIELDS ON SERVEY_CHOSE
    public final String CHOSE_QUESTION_ID="QUESTION_ID";
    public final String CHOSE_USER_ID="USER_ID";
    public final String CHOSE_CHOSETIME="CHOSETIME";
    public final String[] SERVEY_CHOSE_ALL_FIELDS={CHOSE_QUESTION_ID,CHOSE_USER_ID,CHOSE_CHOSETIME};
    
    //SET FIELDS ON DOC_REFERENCE
    public final String DOC_REFERENCE_DOC_ID="DOC_ID";
    public final String DOC_REFERENCE_CREATE_DATE="CREATEDATE";
    public final String DOC_REFERENCE_USER_ID="USER_ID";
    public final String DOC_REFERENCE_DOC_REFERENCE_ID="DOC_REFERENCE_ID";
    public final String[] DOC_REFERENCE_ALL_FIELDS={DOC_REFERENCE_DOC_ID,DOC_REFERENCE_CREATE_DATE,DOC_REFERENCE_USER_ID,DOC_REFERENCE_DOC_REFERENCE_ID};
    
    //SET FIELDS ON BROADCAST
    public final String BROADCAST_ID            =   "ID";
    public final String BROADCAST_TITLE         =   "TITLE";
    public final String BROADCAST_CONTENT       =   "CONTENT";
    public final String BROADCAST_FULLTEXT       =   "FULLTEXT";
    public final String BROADCAST_USER_ID       =   "USER_ID";
    public final String BROADCAST_CREATETIME    =   "CREATETIME";
    public final String BROADCAST_SPECIAL       =   "SPECIAL";
    public final String BROADCAST_ORDERS        =   "ORDERS";
    public final String[] BROADCAST_ALL_FIELDS  =   {BROADCAST_TITLE,BROADCAST_CONTENT,BROADCAST_USER_ID,BROADCAST_CREATETIME,BROADCAST_SPECIAL,BROADCAST_FULLTEXT,BROADCAST_ORDERS};
    
    
    
    //SET FIELDS ON OPTION THEME
    public final String THEME_ID            =   "ID";
    public final String THEME_TITLE         =   "TITLE";
    public final String THEME_PATHIMAGES=   "PATHIMAGES";
    public final String THEME_PATHSTYLE=   "PATHSTYLE";
    public final String THEME_ACTIVE       =   "ACTIVE";
    
    public final String[] THEME_ALL_FIELDS  =   {THEME_TITLE,THEME_PATHIMAGES,THEME_PATHSTYLE,THEME_ACTIVE};
    
    
}
