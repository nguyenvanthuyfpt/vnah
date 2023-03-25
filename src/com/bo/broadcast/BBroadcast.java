package com.bo.broadcast;


import com.dao.broadcast.DBroadcast;
import com.dao.connection.DBConnector;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.broadcast.FBroadcast;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BBroadcast
{
  public FBeans getAllBroadcast(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllBroadcast()";
    Connection cnn = null;
    DBroadcast docBroad = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);      
      docBroad = new DBroadcast();
      beans = docBroad.getAllBroadcast(cnn,seed); 
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
  
    public FBeans getAllBroadcastTop(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllBroadcastTop()";
      Connection cnn = null;
      DBroadcast docBroad = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);      
        docBroad = new DBroadcast();
        beans = docBroad.getAllBroadcastTop(cnn,seed); 
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
  
    public FBeans getAllBroadcastShow(FBroadcast bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllBroadcastShow()";
      Connection cnn = null;
      DBroadcast docBroad = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);      
        docBroad = new DBroadcast();
        beans = docBroad.getAllBroadcastShow(cnn,bean); 
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
    public FBeans getShowCalenda(FBroadcast bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>getShowCalenda()";
      Connection cnn = null;
      DBroadcast docBroad = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);      
        docBroad = new DBroadcast();
        beans = docBroad.getShowCalenda(cnn,bean); 
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
    FBroadcast bean = (FBroadcast)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DBroadcast dao = new DBroadcast();            
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
  
  public boolean orders(int orders,int id,int index) throws  EException
  {
    final String LOCATION = this.toString() + "->orders()";
    Connection conn = null;   
    boolean result = false;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DBroadcast dao = new DBroadcast();              
        result = dao.updateOrder(conn,orders,id,index);                        
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
  
    public boolean update(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;   
      boolean result = false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DBroadcast dao = new DBroadcast();              
          result = dao.update(conn,seed);                        
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
    boolean result = false;
    try {        
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DBroadcast dao = new DBroadcast();         
        result = dao.delete(conn,seed);                
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

  public boolean checkNameBroadcast(FSeed seed) throws EException 
  {
    final String LOCATION = this + "->checkNameBroadcast()";
    boolean result = false;
    Connection conn = null;    
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DBroadcast dao = new DBroadcast();          
        result = dao.broadcastCheckTitle(conn,seed);
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
  
 
  public FBroadcast getBroadcastById(FBroadcast bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getBroadcastById()";
   Connection cnn = null;
   DBroadcast dao = null;
   FBroadcast beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);     
     dao = new DBroadcast();
     beantemp = dao.getBroadcastById(cnn, bean);    
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
