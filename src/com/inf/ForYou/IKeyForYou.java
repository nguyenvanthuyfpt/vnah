package com.inf.ForYou;


import com.inf.admin.IKeyAdmin;

public class IKeyForYou extends IKeyAdmin
{  
    public static final String CABIN_FILE_PATH = getValue("CABIN.FILE.PATH") ;    
    public static final String CABIN_FILE_NAME = getValue("CABIN.FILE.NAME") ;
    
    public static final String TEMPLATE_FILE_NAME = getValue(" TEMPLATE.FILE.PATH") ;
    public static final String TEMPLATE_FILE_PATH = getValue("TEMPLATE.FILE.PATH") ;
    
    public static final String DOC_FOLDER_ROOT = getValue("DOC.FOLDER.ROOT") ;
    public static final String DOC_FOLDER_UPLOAD = getValue("DOC.FOLDER.UPLOAD") ;
    
    public static final String REPORT_FILE_PATH = getValue("REPORT.FILE.PATH") ;
    public static final String REPORT_FILE_NAME = getValue("REPORT.FILE.NAME") ;
    
}