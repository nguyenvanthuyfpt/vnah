package com.bo.doc.from;


import com.dao.connection.DBConnector;
import com.dao.doc.from.DFrom;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.from.FFrom;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BFrom
{
  
  public FBeans getAllFrom(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllFrom()";
    Connection cnn = null;
    DFrom dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DFrom();
      beans = dao.getAllFrom(cnn,seed);
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
  
    public FBeans getAllFomByFromId(int fomId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllFomByFromId()";
      Connection cnn = null;
      DFrom dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DFrom();
        beans = dao.getAllFomByFromId(cnn,fomId);
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
  
    public FBeans getFromInID(String fromsId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getFromInID()";
      Connection cnn = null;
      DFrom dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DFrom();
        beans = dao.getFromInID(cnn,fromsId);
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
  public boolean addNew(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    boolean result = false;
    Connection conn = null;
    FFrom bean = (FFrom)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DFrom dao = new DFrom();             
         if(!dao.isExistAdd(conn,seed)){
             result = dao.addNew(conn,bean);
         }
      DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        result = false;
     }
     finally 
     {
        DBConnector.closeConnection(conn);
     }    
     return result;
  }
  
  public boolean update(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->update()";
    Connection conn = null;
    FFrom bean = (FFrom)seed;
    boolean result=false;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DFrom dao = new DFrom();         
         if(!dao.isExistUpdate(conn,seed)){
             result=dao.update(conn,bean);
         }
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
    final String LOCATION = this.toString() + "->addNew()";
    Connection conn = null;
    boolean result=false;
    FFrom bean = (FFrom)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DFrom dao = new DFrom();  
          result=dao.delete(conn,bean);
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

 
  public FFrom getFormById(FFrom bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getFormById()";
   Connection cnn = null;
   DFrom dao = null;
   FFrom beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     dao = new DFrom();
     beantemp = dao.getFromById(cnn, bean);    
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
