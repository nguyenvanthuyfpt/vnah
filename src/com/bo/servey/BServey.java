package com.bo.servey;


import com.dao.connection.DBConnector;
import com.dao.servey.DServey;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.servey.FServey;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BServey
{
  public FBeans getAll() throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllCa()";
    Connection cnn = null;
    DServey dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DServey();
      beans = dao.getAll(cnn);
              
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
    FServey bean = (FServey)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DServey dao = new DServey();             
         result = dao.insert(conn,bean);
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
    boolean result=false;
    FServey bean = (FServey)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DServey dao = new DServey();         
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
    public boolean checkInser(String userMail,int id) throws  EException,SQLException
    {
      final String LOCATION = this.toString() + "->checkInser()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DServey dao = new DServey();         
          result=dao.isExistAddNew(conn,userMail,id);
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
    public boolean checkUpdate(String userMail,int id) throws  EException,SQLException
    {
      final String LOCATION = this.toString() + "->checkUpdate()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DServey dao = new DServey();         
          result=dao.isExistUpdate(conn,userMail,id);
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
    try {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DServey dao = new DServey();  
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

  public FServey getById(int id) throws  EException
  {
   final String LOCATION = this.toString() + "~>getById()";
   Connection cnn = null;
   DServey dao = null;
   FServey beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DServey();
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
    public FBeans getByActiveTrue() throws  EException
    {
     final String LOCATION = this.toString() + "~>getByActiveTrue()";
     Connection cnn = null;
     DServey dao = null;
     FBeans beans = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DServey();
       beans = dao.getByActiveTrue(cnn);    
       DBConnector.endTransaction(cnn);
     }catch (EException sqle){
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }finally{
       DBConnector.closeConnection(cnn);
     }
     return beans;
    }
}
