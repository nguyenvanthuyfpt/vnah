package com.bo.require.requires;


import com.dao.connection.DBConnector;
import com.dao.require.requires.DRequires;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.require.trailer.FRequireTrailer;
import com.form.require.requires.FRequire;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BRequires
{
    public int getTotalRMByEmpRecv(long userId)throws EException
      {
         final String LOCATION = this + "->getTotalWaitRm()";
         int result = 0;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires(); 
             result = dao.getTotalRMByEmpRecv(conn,userId);             
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
    
    public int getTotalWaitRecv(long userId)throws EException
      {
         final String LOCATION = this + "->getTotalWaitRm()";
         int result = 0;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires(); 
             result = dao.getTotalWaitRecv(conn,userId);             
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
    public FBeans getRmByStatus(FRequire beanRm)throws EException
      {
         final String LOCATION = this + "->getRmByStatus()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires(); 
             result = dao.getRmByStatus(conn,beanRm);             
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
    
    public FBeans getRmByStatusObserver(FRequire beanRm)throws EException
      {
         final String LOCATION = this + "->getRmByStatus()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires(); 
             result = dao.getRmByStatusObserver(conn,beanRm);             
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
          DRequires dao = new DRequires();          
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
    public boolean insertReview(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "->insertReview()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DRequires dao = new DRequires();          
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
    public FBeans getAllReview(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllReview()";
      Connection cnn = null;
      DRequires dao = null;
      FBeans beans = null;   
      try
      {      
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            dao = new DRequires();
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
    
   
    public FBeans getRMRecvByRmId(FRequire bean,int userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getRMRecvByRmId()";
     Connection cnn = null;
     DRequires dao = null;
     FBeans beans = null;
     try{      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DRequires();
        beans = dao.getRMRecvByRmId(cnn, bean,userId);
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
      DRequires dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DRequires();
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
    public FBeans getAllGroupByRule(FRequireTrailer bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllGroupByRule()";
      Connection cnn = null;
      DRequires dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DRequires();
        beans = dao.getAllGroupsByRule(cnn,bean);
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
     
    
    public FBeans getAllRecordByRule(FRequireTrailer bean)throws EException
      {
         final String LOCATION = this + "->getAllRecordByRule()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
             result = dao.getAllRecordByRule(conn,bean);
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
   
    public FBeans getAllDepartmentPri(long userId,int depId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDepartmentPri()";
      Connection cnn = null;
      DRequires dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DRequires();
        beans = dao.getAllDepartmentPri(cnn,userId,depId);
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
     
    public FBeans search(FRequire bean)throws EException,SQLException
      {
         final String LOCATION = this + "->search()";
         Connection conn = null;
         DRequires dao = new DRequires(); 
         FBeans beans =new FBeans();
         try {
              conn = DBConnector.getConnection();
              DBConnector.startTransaction(conn);          
             // beans=dao.search(conn,bean);
              DBConnector.endTransaction(conn); 
              
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
         return beans;
    }

    public FRequire getRecordByCode(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getRecordByCode()";
         FRequire result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
             result = dao.getRecordByCode(conn,seed);
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

    public boolean insert(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "->addNew()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DRequires dao = new DRequires();          
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
       DRequires dao = new DRequires();          
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

 
 public boolean insertMe(FSeed seed) throws  EException
 {
   final String LOCATION = this.toString() + "->insertMe()";
   boolean result = false;
   Connection conn = null;
   FRequire bean = (FRequire)seed;
   try 
   {
       conn = DBConnector.getConnection();
       DBConnector.startTransaction(conn);
       DRequires daoAR = new DRequires();
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
    public FRequire getById(FRequire bean)throws EException,SQLException
      {
         final String LOCATION = this + "->getRecordByID()";
         FRequire result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
             result = dao.getById(conn,bean);
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
 
    
    
    public FRequire getTopId(long userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTopId()";
     Connection cnn = null;
     DRequires dao = null;
     FRequire beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);       
       dao = new DRequires();
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
    
    public FBeans getAllRequires(FRequire bean)throws EException,SQLException
    {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();                  
             result = dao.getAllRequires(conn,bean);
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
     
    public FBeans getAllRequiresObserver(FRequire bean)throws EException,SQLException
    {
         final String LOCATION = this + "->getAllRequiresObserver()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();                  
             result = dao.getAllRequiresObserver(conn,bean);
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
     
    public FBeans getAllAssetReport(FRequire bean)throws EException,SQLException
    {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
          
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
            // result = dao.getAllAssetReport(conn,dao.SQL_SELECT_REQUIRES_REPORT,null,bean);
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
     
    public boolean delete(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
             result = dao.delete(conn,seed);
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
     
    public boolean deletes(String[] dels)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
            // result = dao.deletes(conn,dels);
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
    public boolean AddNew(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();                   
            result = dao.AddNew(conn,seed);         
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
    public boolean isExist(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
          //   if(!dao.isExist(conn,seed)){
                result = dao.isExist(conn,seed);
            // }
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
    public boolean isExistListId(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
          //   if(!dao.isExist(conn,seed)){
                result = dao.isExistListId(conn,seed);
            // }
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
    public boolean isExistListIdEdit(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
          //   if(!dao.isExist(conn,seed)){
                result = dao.isExistListIdEdit(conn,seed);
            // }
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
    public boolean update(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();          
             if(!dao.isExist(conn,seed)){
                 result = dao.update(conn,seed);
             }
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
     
    public boolean updateStatusStore(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateStatusStore()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DRequires dao = new DRequires();                      
             result = dao.updateStatusStore(conn,seed);             
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

}
