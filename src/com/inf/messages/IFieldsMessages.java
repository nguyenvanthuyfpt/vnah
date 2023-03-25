package com.inf.messages;


import com.inf.admin.IFieldsAdmin;

public interface IFieldsMessages extends IFieldsAdmin {
    //  SET OF FIELDS ON MESSAGE_FILES TABLES
    public final String FILE_ID="ID";
    public final String FILE_MESSAGES_ID="MESSAGE_ID";
    public final String FILE_MESSAGES_FILENAME="FILENAME";
    public final String FILE_MESSAGES_READNAME="READNAME";
    public final String FILE_MESSAGES_PATH="PATH";
    public final String[] FILE_MESSAGES_ALL_FIELDS = {FILE_MESSAGES_ID,FILE_MESSAGES_FILENAME,FILE_MESSAGES_READNAME,FILE_MESSAGES_PATH}; 
    
    //  SET OF FIELDS ON MESSAGES TABLES
  public final String MESSAGES_ID="ID";
  public final String MESSAGES_NAME="NAME";
  public final String MESSAGES_CREATOR="CREATOR";
  public final String MESSAGES_TIMECREATOR="TIMECREATOR";
  public final String MESSAGES_FILES="FILES";  
  public final String MESSAGES_REMOVE="REMOVE";
  public final String MESSAGES_FULLTEXT="FULLTEXT";
  public final String MESSAGES_PATH_FILE ="PATH_FILE";
    public final String MESSAGES_EMAIL="EMAIL";
  
  public final String[] MESSAGES_ALL_FIELDS = {MESSAGES_NAME, MESSAGES_CREATOR,MESSAGES_TIMECREATOR,MESSAGES_FILES,MESSAGES_REMOVE,MESSAGES_FULLTEXT,MESSAGES_PATH_FILE,MESSAGES_EMAIL}; 

  //  SET OF FIELDS ON RECIEVERS TABLES
  public final String RECIEVERS_ID ="ID";
  public final String RECIEVERS_MESSAGES_ID ="MESSAGES_ID";
  public final String RECIEVERS_TO_PERSON="TO_PERSON";
  public final String RECIEVERS_HIDDEN="HIDDEN";
  public final String RECIEVERS_REMOVE="REMOVE";
  public final String RECIEVERS_READ="READED";
    public final String RECIEVERS_EMAIL="EMAIL";
  public final String[] RECIEVERS_ALL_FIELDS = {RECIEVERS_MESSAGES_ID, RECIEVERS_TO_PERSON,RECIEVERS_HIDDEN,RECIEVERS_REMOVE,RECIEVERS_READ,RECIEVERS_EMAIL}; 
}
