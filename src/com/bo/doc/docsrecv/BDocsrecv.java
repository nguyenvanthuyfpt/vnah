package com.bo.doc.docsrecv;


import com.dao.connection.DBConnector;
import com.dao.doc.assign.DAssignRecv;
import com.dao.doc.docsrecv.DDocsrecv;
import com.dao.doc.docsrecv.DFilesRecv;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docsrecv.FDocsrecv;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BDocsrecv
{
  
  public FBeans getAllDocsrecv(FSeed seed,int userId,String statuses,int checkWait) throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllDocsrecv()";
    Connection cnn = null;
    DDocsrecv dao = null;
    FBeans beans = null;
    try {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DDocsrecv();
      beans = dao.getAllDocsrecv(cnn,seed,userId,1,statuses,checkWait);
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
  
    public FBeans searchStore(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>searchStore()";
      Connection cnn = null;
      DDocsrecv dao = null;
      FBeans beans = null;
      try {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsrecv();
        beans = dao.searchStore(cnn,seed,userId);
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
  
    public int getCountOfYear(FDocsrecv bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getCountOfYear()";
     Connection cnn = null;
     DDocsrecv dao = null;
     int result = 0;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DDocsrecv();
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
     return result+1;
    }
    public FBeans getAllDocsrecvDetailt(FSeed seed,int userId,String statuses,int checkWait) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDocsrecv()";
      Connection cnn = null;
      DDocsrecv dao = null;
      FBeans beans = null;
      try {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsrecv();
        beans = dao.getAllDocsrecvDetailt(cnn,seed,userId,1,statuses,checkWait);
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
    public FBeans searchDocReferent(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>searchDocReferent()";
      Connection cnn = null;
      DDocsrecv dao = null;
      FBeans beans = null;
      try {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsrecv();
        beans = dao.searchDocReferent(cnn,seed,userId);
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
    public FBeans getAllDocsrecvByDossiers(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDocsrecv()";
      Connection cnn = null;
      DDocsrecv dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsrecv();        
        beans = dao.getAllDocsrecv(cnn,seed,userId,0,"-1000",0);
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
    
    public FBeans getAllDocsrecvOBServer(FSeed seed,int userId,int checkWait) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDocsrecvOBServer()";
      Connection cnn = null;
      DDocsrecv dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DDocsrecv();
        beans = dao.getAllDocsrecvOBServer(cnn,seed,userId,checkWait);              
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
    public FBeans search(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>search()";
      Connection cnn = null;
      DDocsrecv Secure = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        Secure = new DDocsrecv();
     //   beans = Secure.search(cnn,seed);
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
    
    public FBeans getDocReference(FSeed seed) throws  EException
    {
     final String LOCATION = this.toString() + "~>getDocReference()";
     Connection cnn = null;
     DDocsrecv dao = null;
     FBeans beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DDocsrecv();
       beantemp = dao.getDocReference(cnn,seed);   
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
    
    
    
  public boolean addNew(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    boolean result = false;
    Connection conn = null;
    FDocsrecv bean = (FDocsrecv)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DDocsrecv dao = new DDocsrecv();   
         if((bean.getDocCode()!=null) || (!bean.getDocCode().equals(""))){
             if(!dao.isExistAdd(conn,seed)){
                 result = dao.addNew(conn,bean);                
             }
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
  
  
    public boolean insertMe(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->insertMe()";
      boolean result = false;
      Connection conn = null;
      FDocsrecv bean = (FDocsrecv)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignRecv daoAR = new DAssignRecv();
          daoAR.insertMe(conn,bean);          
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
    FDocsrecv bean = (FDocsrecv)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DDocsrecv dao = new DDocsrecv();
       // if(!dao.isExistUpdate(conn,seed)){
            result=dao.update(conn,bean);
       // }
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
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
      FDocsrecv bean = (FDocsrecv)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocsrecv dao = new DDocsrecv();       
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
  
    public boolean updateStatus(int statusId,int docId) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocsrecv dao = new DDocsrecv();
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
    
    
    public boolean updateView(int view,long userId) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocsrecv dao = new DDocsrecv();
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
    
    public boolean updateDossiers(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->updateDossiers()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocsrecv dao = new DDocsrecv();
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
    public boolean updateClassify(int docId,int classifyId) throws  EException
    {
      final String LOCATION = this.toString() + "->updateClassify()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocsrecv dao = new DDocsrecv();
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
  public boolean delete(FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + "->delete()";
    Connection conn = null;
    boolean result=false;
    FDocsrecv bean = (FDocsrecv)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DDocsrecv dao = new DDocsrecv();  
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

 
  public FDocsrecv getById(FDocsrecv bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getFormById()";
   Connection cnn = null;
   DDocsrecv dao = null;
   FDocsrecv beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     dao = new DDocsrecv();
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
    public FDocsrecv getTopId(int userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTopId()";
     Connection cnn = null;
     DDocsrecv dao = null;
     FDocsrecv beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DDocsrecv();
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
    
    
    public FDocsrecv getDocsByMaxId(int userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getDocsByCode()";
     Connection cnn = null;
     DDocsrecv dao = null;
     FDocsrecv beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DDocsrecv();
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
    
    public FDocsrecv getDetail(FDocsrecv bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getFormById()";
     Connection cnn = null;
     DDocsrecv dao = null;
     FDocsrecv beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DDocsrecv();
       beantemp = dao.getDetail(cnn,bean);   
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
    
    public FBeans getAllFileDetail(FDocsrecv bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>getAllFileDetail()";
     Connection cnn = null;
     DFilesRecv dao = null;
     FBeans beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);             
       dao = new DFilesRecv();        
        beantemp = dao.getAllByDocId(cnn,bean.getId());
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
    
    
    public int checkDocsDeadLine(FDocsrecv bean) throws  EException
    {
     final String LOCATION = this.toString() + "~>checkDocsDeadLine()";
     Connection cnn = null;
     DFilesRecv dao = null;
    int result = 0;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);             
       dao = new DFilesRecv();        
       result = dao.checkDocsDeadLine(cnn,bean);
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
    public boolean delTrailerByUserRecv(long meId,int docId,int forYouId) throws  EException
    {
      final String LOCATION = this.toString() + "->delTrailerByUserRecv()";
      Connection conn = null;
      boolean result=false;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DDocsrecv dao = new DDocsrecv();  
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
}
