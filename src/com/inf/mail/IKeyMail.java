package com.inf.mail;

import com.inf.tasks.IKeyTasks;

public class IKeyMail extends IKeyTasks
{  
            //STATUS READED
            public static final int MAIL_UN_READED=0;
            public static final int MAIL_READED=1;
            //TYPE OF MAIL 
            public static final int MAIL_TYPE_INBOX=0;//MAIL NHAN DUOC
            public static final int MAIL_TYPE_OUTBOX=1;//MAIL SEND FAIL
            public static final int MAIL_TYPE_SENDED=2;//MAIL DA GUI THANH CONG
            public static final int MAIL_TYPE_DELETE=3;//MAIL TAM XOA
            public static final int MAIL_TYPE_DRAFT=4;//MAIL SPAM
            //FOLDER MAIL SERVER
            public static final String FOLDER_INBOX="INBOX";//MAIL INBOX
            public static final String FOLDER_SENT="INBOX.Sent";//MAIL INBOX
            public static final String FOLDER_OUTBOX="OUTBOX";//MAIL INBOX
            public static final String FOLDER_DRAFT="DRAFT";//MAIL INBOX
            public static final String FOLDER_TRASH="INBOX.Trash";//MAIL DELETED
             public static final String FOLDER_SPAM="SPAM";//MAIL DELETED
            
     
}