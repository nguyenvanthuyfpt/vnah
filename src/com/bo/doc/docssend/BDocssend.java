package com.bo.doc.docssend;


import com.dao.connection.DBConnector;
import com.dao.doc.assign.DAssignSend;
import com.dao.doc.docssend.DDocssend;
import com.dao.doc.docssend.DFilesSend;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docssend.FDocssend;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BDocssend
{

    public boolean updateView(int view,long userId) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocssend dao = new DDocssend();
          result=dao.updateView(conn,view,userId);
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
    
    public int getCountOfYear(FDocssend bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getCountOfYear()";
     Connection cnn = null;
     DDocssend dao = null;
     int result = 0;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DDocssend();
       result = dao.getCountOfYear(cnn,bean);    
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
    
    public boolean updateClassify(int docId,int classifyId) throws  EException
    {
      final String LOCATION = this.toString() + "->updateClassify()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocssend dao = new DDocssend();
          result=dao.updateClassify(conn,docId,classifyId);
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
    
    public boolean updateStatus(int statusId,int docId) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocssend dao = new DDocssend();
              result=dao.updateStatus(conn,statusId,docId);
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
    public boolean updateDossiers(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->updateDossiers()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocssend dao = new DDocssend();
          result=dao.updateDossiers(conn,seed);
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
    

    
  public FBeans getAlldocssend(FSeed seed,int userId,int type,int checkWaitSend,String statused,int checkRulesCreator) throws  EException
  {
    final String LOCATION = this.toString() + "~>getAlldocssend()";
    Connection cnn = null;
    DDocssend dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DDocssend();
      beans = dao.getAlldocssend(cnn,seed,userId,type,1,checkWaitSend,statused,checkRulesCreator);              
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
  
    public FBeans getAlldocssendDetailt(FSeed seed,int userId,int type,int checkWaitSend,String statused,int checkRulesCreator) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAlldocssendDetailt()";
      Connection cnn = null;
      DDocssend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocssend();
        beans = dao.getAlldocssendDetailt(cnn,seed,userId,type,1,checkWaitSend,statused,checkRulesCreator);              
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
  
  
    public FBeans getAlldocssendByDossiers(FSeed seed,int userId,int type) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAlldocssendByDossiers()";
      Connection cnn = null;
      DDocssend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocssend();
        beans = dao.getAlldocssend(cnn,seed,userId,type,0,0,"10000",0);              
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
    public FBeans getAlldocssendObServer(FSeed seed,int userId,int type,int checkRulesCreator) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAlldocssendObServer()";
      Connection cnn = null;
      DDocssend dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocssend();
        beans = dao.getAlldocssendObServer(cnn,seed,userId,type,checkRulesCreator);              
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
    FDocssend bean = (FDocssend)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DDocssend dao = new DDocssend();   
        if(bean.getDocCode()!=null && (!bean.getDocCode().equals(""))){
            if(!dao.isExistAdd(conn,seed)){
                result = dao.addNew(conn,bean);
            }
        }else{
            result = dao.addNew(conn,bean);
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
    public boolean isExistAdd(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->isExistAdd()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
           DDocssend dao = new DDocssend();   
          result = dao.isExistAdd(conn,seed);              
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
  
    public boolean insertMe(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->inserMe()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignSend daoAR = new DAssignSend();
          result = daoAR.insertMe(conn,seed);              
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
    
  
    
    public boolean updateFileDocsSend(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->updateFileDocsSend()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocssend dao = new DDocssend();       
          result=dao.updateFileDocsSend(conn,seed);
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
  
    public boolean updateBlockFile(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->updateBlockFile()";
      Connection conn = null;
      boolean result=false;
      FDocssend bean = (FDocssend)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocssend dao = new DDocssend();       
          result=dao.updateBlockFile(conn,bean);
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
   
    public boolean delTrailerByUserRecv(long meId,int docId,int forYouId) throws  EException
    {
      final String LOCATION = this.toString() + "->delTrailerByUserRecv()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocssend dao = new DDocssend();  
           result= dao.delTrailerByUserRecv(conn,meId,docId,forYouId);
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
    

    public FDocssend getTopId(int userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTopId()";
     Connection cnn = null;
     DDocssend dao = null;
     FDocssend beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DDocssend();
       beantemp = dao.getTopId(cnn,userId);    
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
    
    
    public FDocssend getDocsByMaxId(int userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTopId()";
     Connection cnn = null;
     DDocssend dao = null;
     FDocssend beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DDocssend();
       beantemp = dao.getDocsByMaxId(cnn,userId);    
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
    
    public FDocssend getDetail(FDocssend bean,int checkRulesCreator) throws  EException
    {
     final String LOCATION = this.toString() + "~>getDetail()";
     Connection cnn = null;
     DDocssend dao = null;
     FDocssend beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DDocssend();
       beantemp = dao.getDetail(cnn,bean,checkRulesCreator);
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
    
    public void sendMailDoc(String Subject,String Content,String recipient,String[] attachments,String[] files) throws  EException
        {
         final String LOCATION = this.toString() + "~>sendMailDoc()";
         Connection cnn = null;
         DDocssend dao = null;
         try
         {      
           cnn = DBConnector.getConnection();
           DBConnector.startTransaction(cnn);       
           dao = new DDocssend();
          // new SendMail().sendmailFromDoc(Subject,Content,recipient,attachments,files);
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
        }  
    public FBeans getAllFileDetail(FDocssend bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getAllFileDetail()";
     Connection cnn = null;
     DFilesSend dao = null;
     FBeans beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DFilesSend();        
       beantemp = dao.getAllByFileByeDoc(cnn,bean);
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
