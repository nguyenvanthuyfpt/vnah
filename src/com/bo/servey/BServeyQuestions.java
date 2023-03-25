package com.bo.servey;


import com.dao.connection.DBConnector;
import com.dao.servey.DServeyQuestions;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.servey.FServeyQuestions;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BServeyQuestions
{
  public FBeans getAll() throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllCa()";
    Connection cnn = null;
    DServeyQuestions dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DServeyQuestions();
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
    public FBeans getByServeyId(int serveyId) throws  EException{
      final String LOCATION = this.toString() + "~>getByServeyId()";
      Connection cnn = null;
      DServeyQuestions dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DServeyQuestions();
        beans = dao.getByServeyId(cnn,serveyId);
                
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
    FServeyQuestions bean = (FServeyQuestions)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DServeyQuestions dao = new DServeyQuestions();             
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
    public boolean count(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->addNew()";
      boolean result = false;
      Connection conn = null;
      FServeyQuestions bean = (FServeyQuestions)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DServeyQuestions dao = new DServeyQuestions();
          if(!dao.checkExitsQuestionAndUserId(conn,seed)){
              result = dao.count(conn,bean);
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
    
  public boolean update(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->update()";
    Connection conn = null;
    boolean result=false;
    FServeyQuestions bean = (FServeyQuestions)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DServeyQuestions dao = new DServeyQuestions();         
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
   
  public boolean delete(int id,int userId) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    Connection conn = null;
    boolean result=false;
    try {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DServeyQuestions dao = new DServeyQuestions();  
        result=dao.delete(conn,id,userId);
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

  public FServeyQuestions getById(int id) throws  EException
  {
   final String LOCATION = this.toString() + "~>getById()";
   Connection cnn = null;
   DServeyQuestions dao = null;
   FServeyQuestions beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DServeyQuestions();
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
