package com.bo.doc.assign;


import com.dao.connection.DBConnector;
import com.dao.doc.assign.DAssignRecv;
import com.dao.doc.docssend.DDocssend;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docssend.FDocssend;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BAssignRecv
{
    
    public int getUserRecvDoc(int docId,long userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getUserRecvDoc()";
      Connection cnn = null;
      DAssignRecv dao = null;
      int  result  =0;;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignRecv();
        result = dao.getUserRecvDoc(cnn,docId,userId);
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
      return result;
    }
    public FBeans getAllRecordByRule(FDocAssign bean,long userId)throws EException
      {
         final String LOCATION = this + "->getAllRecordByRule()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DAssignRecv dao = new DAssignRecv();          
             result = dao.getAllRecordByRule(conn,bean,userId);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    
    public boolean updateReadedReply(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->updateReadedReply()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignRecv dao = new DAssignRecv();          
          result = dao.updateReadedReply(conn,seed);
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
    public boolean updateReadedAssignRecv(FDocAssign beanAssign) throws  EException
    {
      final String LOCATION = this.toString() + "->updateReadedRecv()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignRecv dao = new DAssignRecv();          
          result = dao.updateReadedAssignRecv(conn,beanAssign);
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
    
    public boolean updateReadedMe(FDocAssign beanAssign) throws  EException
    {
      final String LOCATION = this.toString() + "->updateReadedMe()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignRecv dao = new DAssignRecv();          
          result = dao.updateReadedMe(conn,beanAssign);
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
    public FBeans getAllGroupByRuleAndTranfer(long userId,int workFolowId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllGroupByRuleAndTranfer()";
      Connection cnn = null;
      DAssignRecv dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignRecv();
        beans = dao.getAllGroupByRuleAndTranfer(cnn,userId,workFolowId);
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
   
    public FBeans getAllDepartment(long userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDepartment()";
      Connection cnn = null;
      DAssignRecv dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignRecv();
        beans = dao.getAllDepartments(cnn,userId);
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
    
    
    public FBeans getAllGroupByRule(long userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllGroupByRule()";
      Connection cnn = null;
      DAssignRecv dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignRecv();
        beans = dao.getAllGroupsByRule(cnn,userId);
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
    
    public FDocAssign getByReviewId(int reviewId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getByReviewId()";
      Connection cnn = null;
      DAssignRecv dao = null;
      FDocAssign bean = new FDocAssign();   
      try
      {      
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            dao = new DAssignRecv();
            bean = dao.getByReviewId(cnn,reviewId);
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
      return bean;
    }  
    public FBeans getDocAssignByDocId(FDocAssign bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getDocAssignByDocId()";
     Connection cnn = null;
     DAssignRecv dao = null;
     FBeans beans = null;
     try{      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DAssignRecv();
        beans = dao.getDocAssignByDocId(cnn, bean);
        DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
       DBConnector.closeConnection(cnn);
     }
     return beans;
    }
    
    public FBeans getUserByDepartmentId(FSeed seed,int depId,int groupId) throws  EException{
      final String LOCATION = this.toString() + "~>getUserByDepartmentId()";
      Connection cnn = null;
      DAssignRecv dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignRecv();
        beans = dao.getUserByDepartmentId(cnn,seed,depId,groupId);                          
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
    
    
    public FBeans getUserByGroupId(FSeed seed,int groupId) throws  EException{
      final String LOCATION = this.toString() + "~>getUserByGroupId()";
      Connection cnn = null;
      DAssignRecv dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignRecv();
        beans = dao.getUserByGroupId(cnn,seed,groupId);                          
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

  
    public FBeans getAllReview(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllReview()";
      Connection cnn = null;
      DAssignRecv dao = null;
      FBeans beans = null;   
      try
      {      
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            dao = new DAssignRecv();
            beans = dao.getAllReview(cnn,seed);
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
    
    
  
    public FDocAssign checkAsignRule(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>checkAsignRule()";
      Connection cnn = null;
      boolean result = false;
      DAssignRecv dao = null;
      FDocAssign bean = (FDocAssign)seed;
      try
      {      
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            dao = new DAssignRecv();
            bean = dao.checkAsignRule(cnn,seed);
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
      return bean;
    }
    
  public boolean insert(FSeed seed,int userId) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    boolean result = false;
    Connection conn = null;    
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DAssignRecv dao = new DAssignRecv();          
        result = dao.insert(conn,seed,userId);
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
  
    public boolean insertDirect(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->insertDirect()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignRecv dao = new DAssignRecv();          
          result = dao.insertDirect(conn,seed);
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
  
    public boolean insertReview(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "->insertReview()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignRecv dao = new DAssignRecv();          
          result = dao.insertReview(conn,seed,userId);
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
    FDocssend bean = (FDocssend)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DDocssend dao = new DDocssend();   
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
    final String LOCATION = this.toString() + "->delete()";
    Connection conn = null;
    boolean result=true;
    FDocssend bean = (FDocssend)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DDocssend dao = new DDocssend();  
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
  
}
