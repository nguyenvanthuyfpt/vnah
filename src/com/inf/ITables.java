package com.inf;

import com.inf.ForYou.ITablesForYou;
import com.inf.admin.ITablesAdmin;
import com.inf.admin.doc.ITablesDocAdmin;
import com.inf.agenda.ITablesAgenda;
import com.inf.cabin.ITablesCabin;
import com.inf.disability.ITablesDisability;
import com.inf.doc.ITablesDoc;
import com.inf.mail.ITablesMail;
import com.inf.messages.ITablesMessages;
import com.inf.report.ITablesReport;
import com.inf.require.ITablesRequire;
import com.inf.tasks.ITablesTasks;


public interface ITables extends ITablesAdmin,ITablesDocAdmin,ITablesMessages,ITablesTasks,ITablesForYou,ITablesCabin,ITablesAgenda,ITablesReport,ITablesDoc,ITablesMail,ITablesRequire,ITablesDisability{   
    
    public final String TABLE_SERVEY= "SERVEY";
    public final String TABLE_SERVEY_QUESTIONS= "SERVEY_QUESTIONS";
    public final String TABLE_SERVEY_CHOSE= "SERVEY_CHOSE";
    public final String TABLE_DOC_REFERENCE= "DOC_REFERENCE";    
    public final String TABLE_BROADCAST= "BROADCAST";
    public final String TABLE_THEME= "THEME";
    
}
