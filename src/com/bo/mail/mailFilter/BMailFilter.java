package com.bo.mail.mailFilter;


import com.dao.connection.DBConnector;
import com.dao.mail.mailFilter.DMailFilter;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.mail.mailFilter.FMailFilter;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BMailFilter
{
    public FBeans getAll(long meId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAll()";
      Connection cnn = null;
      DMailFilter dao = null;
      FBeans beans=null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DMailFilter();
        beans = dao.getAll(cnn,meId);      
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
    
  public FBeans getAllByMeId(long meId) throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllByMeId()";
    Connection cnn = null;
    DMailFilter dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DMailFilter();
      beans = dao.getAllByMeId(cnn,meId);      
                  
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
    boolean result = true;
    Connection conn = null;
    FMailFilter bean = (FMailFilter)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DMailFilter dao = new DMailFilter();             
         result = dao.addNew(conn,bean);
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
  
  
    public boolean addBatch(String[] froms,long meId) throws  EException,Exception
    {
      final String LOCATION = this.toString() + "->addNew()";
      boolean result = true;
      Connection conn = null;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DMailFilter dao = new DMailFilter();             
           result = dao.addBatch(conn,froms,meId);
        DBConnector.endTransaction(conn);            
       }
       catch (Exception ex) 
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
    boolean result=false;
    FMailFilter bean = (FMailFilter)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DMailFilter dao = new DMailFilter();         
         result=dao.update(conn,bean);
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

  public boolean delete(int id) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    Connection conn = null;
    boolean result=false;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DMailFilter dao = new DMailFilter();  
          result=dao.delete(conn,id);
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

  public boolean checkNameDocType(FSeed seed) throws EException 
  {
    final String LOCATION = this + "->checkNameDocType()";
    boolean result = true;
    Connection conn = null;
    FMailFilter bean = (FMailFilter)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DMailFilter dao = new DMailFilter();          
        result = dao.checkNameReportType(conn,bean);
        DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        result = true;
     }
     finally 
     {
        DBConnector.closeConnection(conn);
    }
    return result;    
  }
 
  public FMailFilter getById(int id) throws  EException
  {
   final String LOCATION = this.toString() + "~>getById()";
   Connection cnn = null;
   DMailFilter dao = null;
   FMailFilter beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DMailFilter();
     beantemp = dao.getById(cnn,id);    

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
