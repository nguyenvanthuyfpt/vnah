package com.bo.foryou;


import com.dao.connection.DBConnector;
import com.dao.foryou.DForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.foryou.FForYou;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BForYou
{
  
  public FBeans getAll() throws  EException
  {
    final String LOCATION = this.toString() + "~>getAlldao()";
    Connection cnn = null;
    DForYou dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DForYou();
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
  
    public FBeans getAllDocByForyouId(int forYouId,int workflowId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDocByForyouId()";
      Connection cnn = null;
      DForYou dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DForYou();
        beans = dao.getAllDocByForyouId(cnn,forYouId,workflowId);
                
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
    public FBeans getByUserIdTo(FSeed seed,int userIdFrom) throws  EException
    {
      final String LOCATION = this.toString() + "~>getByUserIdTo()";
      Connection cnn = null;
      DForYou dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DForYou();
        beans = dao.getByUserIdTo(cnn,seed,userIdFrom);
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
    public FBeans getByUserIdFrom(FSeed seed,int userIdFrom) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAlldao()";
      Connection cnn = null;
      DForYou dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DForYou();
        beans = dao.getByUserIdFrom(cnn,seed,userIdFrom);                
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
    public FBeans getAllDiffUserId(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDiffUserId()";
      Connection cnn = null;
      DForYou dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DForYou();
        beans = dao.getAllDiffUserId(cnn,seed);
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
    
    public FBeans getAllDetailt(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDetailt()";
      Connection cnn = null;
      DForYou dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DForYou();
        beans = dao.getAllDetailt(cnn,seed);
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
    public FBeans getAllUsers() throws  EException
    {
      final String LOCATION = this.toString() + "~>getAlldao()";
      Connection cnn = null;
      DForYou dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DForYou();
        beans = dao.getAllUsers(cnn);
                
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
    FForYou bean = (FForYou)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DForYou dao = new DForYou();             
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
  
  public boolean update(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->update()";
    Connection conn = null;
      boolean result = true;
    FForYou bean = (FForYou)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DForYou dao = new DForYou();         
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
  
    public boolean updateActive(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
        boolean result = true;
      FForYou bean = (FForYou)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DForYou dao = new DForYou();         
          result=dao.updateActive(conn,bean);
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
    FForYou bean = (FForYou)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DForYou dao = new DForYou();  
        if (!dao.checkDelete(conn,bean.getId())){
            result=dao.delete(conn,bean);
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

  
  public FForYou getById(FForYou bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getFormById()";
   Connection cnn = null;
   DForYou dao = null;
   FForYou beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     dao = new DForYou();
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
      
  
}
