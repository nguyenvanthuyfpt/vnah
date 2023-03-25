package com.bo.doc.assign;


import com.dao.connection.DBConnector;
import com.dao.doc.assign.DAssignSend;
import com.dao.doc.docssend.DDocssend;
import com.dao.doc.docssend.DFilesSend;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.doc.rules.FDocRules;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docssend.FDocssend;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BAssignSend
{
    public boolean updateReadedMe(FDocAssign beanAssign) throws  EException
    {
      final String LOCATION = this.toString() + "->updateReadedMe()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
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
    public boolean updateReadedReply(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->updateReadedReply()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
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
  
    public boolean updateReadedTest(int readed,int docId,int meId) throws  EException
    {
      final String LOCATION = this.toString() + "->insertReview()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
          result = dao.updateReadedTest(conn,readed,docId,meId);
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
    public FBeans getAllDepartment(long userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDepartment()";
      Connection cnn = null;
      DAssignSend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignSend();
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
    
    public FBeans getAllDepartmentSelect(long userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDepartmentSelect()";
      Connection cnn = null;
      DAssignSend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignSend();
        beans = dao.getAllDepartmentsSelect(cnn,userId);
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
    public FBeans getUserInRule(FSeed seed ,int depId,int groupId) throws  EException{
      final String LOCATION = this.toString() + "~>getUserInRule()";
      Connection cnn = null;
      DAssignSend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignSend();
        beans = dao.getUserInRule(cnn,seed,depId,groupId);                          
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
      DAssignSend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignSend();
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
    
    public FBeans getDocAssignByDocId(FDocAssign bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getDocAssignByDocId()";
     Connection cnn = null;
     DAssignSend dao = null;
     FBeans beans = null;
     try{      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DAssignSend();
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
    
    public FBeans getUserByDepartmentId(FSeed seed,int departmentId) throws  EException{
      final String LOCATION = this.toString() + "~>getUserByDepartmentId()";
      Connection cnn = null;
      DAssignSend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignSend();
        beans = dao.getUserByDepartmentId(cnn,seed,departmentId);                          
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
  public FBeans getAlldocsByType(FSeed seed,int userId) throws  EException
  {
    final String LOCATION = this.toString() + "~>getAlldocsByType()";
    Connection cnn = null;
    DAssignSend Secure = null;
    FBeans beans = null;   
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      Secure = new DAssignSend();
      beans = Secure.getAlldocsByType(cnn,seed,userId);
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
      DAssignSend dao = null;
      FBeans beans = null;   
      try
      {      
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            dao = new DAssignSend();
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
    
    public FDocAssign getByReviewId(int reviewId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getByReviewId()";
      Connection cnn = null;
      DAssignSend dao = null;
      FDocAssign bean = new FDocAssign();   
      try
      {      
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            dao = new DAssignSend();
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
    
  public boolean insert(FSeed seed,int userId,String usersSend) throws  EException
  {
    final String LOCATION = this.toString() + "->insert()";
    boolean result = false;
    Connection conn = null;    
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DAssignSend dao = new DAssignSend();          
        result = dao.insert(conn,seed,userId,usersSend);
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
  
    public boolean insertEmitEdit(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->insert()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
          result = dao.insertEmitEdit(conn,seed);
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
  
    public boolean AddFileOnline(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->AddFileOnline()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DFilesSend dao = new DFilesSend();          
          result = dao.addFileOnline(conn,seed);
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
  
    public boolean insertDirect(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "->insertDirect()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
          result = dao.insertDirect(conn,seed,userId);
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
    
    public boolean insertDirectAnWear(FSeed seed,int userId,int docIdRecv) throws  EException
    {
      final String LOCATION = this.toString() + "->insertDirectAnWear()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
          result = dao.insertDirectAnWear(conn,seed,userId,docIdRecv);
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
    
    
    public FDocRules checkObserver(int userId,int workflowId) throws  EException
    {
      final String LOCATION = this.toString() + "->insertDirect()";     
      Connection conn = null;    
      FDocRules bean = null;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
          bean = dao.checkObserver(conn,userId,workflowId);
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
       return bean;
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
          DAssignSend dao = new DAssignSend();          
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
  
//    public boolean updateReadedForPeopleSend(int readed,int docId,int meId,int foryouId,long userReply,FSeed seed) throws  EException
//    {
//      final String LOCATION = this.toString() + "->insertReview()";
//      boolean result = false;
//      Connection conn = null;    
//      try 
//      {
//          conn = DBConnector.getConnection();
//          DBConnector.startTransaction(conn);
//          DAssignSend dao = new DAssignSend();          
//          result = dao.updateReadedForPeopleSend(conn,readed,docId,meId,foryouId,userReply,seed);
//          DBConnector.endTransaction(conn);            
//       }
//       catch (EException ex) 
//       {
//          DBConnector.rollBackTransaction(conn);
//          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
//       }
//       finally 
//       {
//          DBConnector.closeConnection(conn);
//       }    
//       return result;
//    }
    
    
    public boolean updateReadedAssignRecv(FDocAssign beanAssign) throws  EException
    {
      final String LOCATION = this.toString() + "->updateReadedAssignRecv()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend dao = new DAssignSend();          
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
  
    public int getUserSendDoc(int docId,long userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getUserSendDoc()";
      Connection cnn = null;
      DAssignSend dao = null;
      int  result  =0;;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DAssignSend();
        result = dao.getUserSendDoc(cnn,docId,userId);
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
  
}
