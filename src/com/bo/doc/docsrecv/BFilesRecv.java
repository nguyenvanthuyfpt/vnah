package com.bo.doc.docsrecv;


import com.dao.connection.DBConnector;
import com.dao.doc.docsrecv.DFilesRecv;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docsrecv.FFilesRecv;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BFilesRecv
{
 
 public FFilesRecv getLastFile(FFilesRecv bean) throws  EException
 {
  final String LOCATION = this.toString() + "~>getLastFile()";
  Connection cnn = null;
  DFilesRecv dao = null;
  FFilesRecv beantemp = null;
  try
  {      
    cnn = DBConnector.getConnection();
    DBConnector.startTransaction(cnn);
    
    dao = new DFilesRecv();
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
   
  public boolean addBath(FSeed seed,int docId) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    boolean result = false;
    Connection conn = null;
    FDocsrecv bean = (FDocsrecv)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DFilesRecv dao = new DFilesRecv();   
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
    FFilesRecv bean = (FFilesRecv)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DFilesRecv dao = new DFilesRecv();  
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

 
  public FFilesRecv getById(FFilesRecv bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getFormById()";
   Connection cnn = null;
   DFilesRecv dao = null;
   FFilesRecv beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DFilesRecv();
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
 
    public FBeans getAllByDocId(int docId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getAllByDocId()";
     Connection cnn = null;
     DFilesRecv dao = null;
     FBeans beans= null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DFilesRecv();
       beans = dao.getAllByDocId(cnn,docId);    
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
}
