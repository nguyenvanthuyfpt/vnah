package com.bo.doc.docssend;


import com.dao.connection.DBConnector;
import com.dao.doc.docssend.DFilesSend;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docssend.FDocssend;
import com.form.doc.docssend.FFilesSend;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BFilesSend
{
    public boolean insert(FFilesSend bean) throws  EException{
      final String LOCATION = this.toString() + "->addNew()";
      boolean result = true;
      Connection conn = null;
      try {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DFilesSend dao = new DFilesSend();             
           result = dao.insert(conn,bean);
        DBConnector.endTransaction(conn);            
       }catch (EException ex) {
          DBConnector.rollBackTransaction(conn);
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          result = false;
       }finally {
          DBConnector.closeConnection(conn);
       }    
       return result;
    }
    
    public FFilesSend getLastFile(FFilesSend bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getLastFile()";
     Connection cnn = null;
     DFilesSend dao = null;
     FFilesSend beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       
       dao = new DFilesSend();
       beantemp = dao.getLastFile(cnn, bean);    

       DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally
     {
       DBConnector.closeConnection(cnn);
     }
     return beantemp;
    }
    
    public FBeans getAllByDocId(int docId) throws EException 
    {
     final String LOCATION = this.toString() + "~>getAllByDocId()";
     Connection cnn = null;
     DFilesSend dao = null;
     FBeans beans = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DFilesSend();
       beans = dao.getAllByDocId(cnn,docId,0);    
       DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally
     {
       DBConnector.closeConnection(cnn);
     }
     return beans;
    }
    
    public FBeans getAllFileEmit(int docId,int views) throws EException 
    {
     final String LOCATION = this.toString() + "~>getAllFileEmit()";
     Connection cnn = null;
     DFilesSend dao = null;
     FBeans beans = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DFilesSend();
       beans = dao.getAllByDocId(cnn,docId,views);    
       DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally
     {
       DBConnector.closeConnection(cnn);
     }
     return beans;
    }
    public FBeans getAllFilesEqualNameByDoc(int docId,int fileId) throws EException 
    {
     final String LOCATION = this.toString() + "~>getAllFilesEqualNameByDoc()";
     Connection cnn = null;
     DFilesSend dao = null;
     FBeans beans = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DFilesSend();
       beans = dao.getAllFilesEqualNameByDoc(cnn,docId,fileId);    
       DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally
     {
       DBConnector.closeConnection(cnn);
     }
     return beans;
    }
    
  public boolean addBath(FSeed seed,int docId) throws  EException
  {
    final String LOCATION = this.toString() + "->addBath()";
    boolean result = false;
    Connection conn = null;
    FDocssend bean = (FDocssend)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DFilesSend dao = new DFilesSend();   
        result = dao.addBath(conn,bean,docId);
        DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
     }
     finally 
     {
        DBConnector.closeConnection(conn);
     }    
     return result;
  }
  public boolean delete(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->delete()";
    Connection conn = null;
    boolean result=true;
    FFilesSend bean = (FFilesSend)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DFilesSend dao = new DFilesSend();  
         result= dao.delete(conn,bean);
        DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
     }
     finally 
     {
        DBConnector.closeConnection(conn);
    }    
    return result;
  }  

 
  public FFilesSend getById(FFilesSend bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getFormById()";
   Connection cnn = null;
   DFilesSend dao = null;
   FFilesSend beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DFilesSend();
     beantemp = dao.getById(cnn, bean);    

     DBConnector.endTransaction(cnn);
   }
   catch (EException sqle)
   {
     DBConnector.rollBackTransaction(cnn);
     if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
   }
   finally
   {
     DBConnector.closeConnection(cnn);
   }
   return beantemp;
  }
    public boolean addReadedFile(int fileId,long meId) throws  EException
    {
      final String LOCATION = this.toString() + "->addReadedFile()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DFilesSend dao = new DFilesSend();          
          result = dao.addReadedFile(conn,fileId,meId);
          DBConnector.endTransaction(conn);            
       }
       catch (EException ex) 
       {
          DBConnector.rollBackTransaction(conn);
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
       }
       finally 
       {
          DBConnector.closeConnection(conn);
       }    
       return result;
    }
    public FFilesSend getMaxbyDocId(int docId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getMaxbyDocId()";
     Connection cnn = null;
     DFilesSend dao = null;
     FFilesSend beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       
       dao = new DFilesSend();
       beantemp = dao.getMaxbyDocId(cnn, docId);    

       DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally
     {
       DBConnector.closeConnection(cnn);
     }
     return beantemp;
    }
    public FFilesSend getMaxVByDoc(int docId,String realName) throws  EException
    {
     final String LOCATION = this.toString() + "~>getMaxVByDoc()";
     Connection cnn = null;
     DFilesSend dao = null;
     FFilesSend beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       
       dao = new DFilesSend();
       beantemp = dao.getMaxVByDoc(cnn, docId,realName);    

       DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally
     {
       DBConnector.closeConnection(cnn);
     }
     return beantemp;
    }
}
