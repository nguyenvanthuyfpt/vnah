package com.bo.tasks.report;


import com.dao.connection.DBConnector;
import com.dao.tasks.problem.DProblem;
import com.dao.tasks.report.DReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.tasks.problem.FProblem;
import com.form.tasks.report.FReport;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BReport {
    
    public FBeans getAllCategories(FSeed seed) throws EException
      {
         final String LOCATION = this + "->getAllCategories()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem(); 
             result = dao.getAllCategories(conn,seed);             
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
     
    public FBeans getAllUser()throws EException
      {
         final String LOCATION = this + "->getAllUser()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem(); 
             result = dao.getAllUser(conn);             
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
     
    public FBeans getUserByDepartmentId(FSeed seed) throws  EException{
      final String LOCATION = this.toString() + "~>getUserByDepartmentId()";
      Connection cnn = null;
      DProblem dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DProblem();
        beans = dao.getUserByDepartmentId(cnn,seed);                          
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
      DProblem dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DProblem();
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
    public FBeans getAllRecord(FSeed seed,long meId)throws EException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DReport dao = new DReport(); 
             result = dao.getAllRecord(conn,seed,meId);             
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
     
    public FBeans getAllProblem()throws EException
      {
         final String LOCATION = this + "->getAllProblem()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem(); 
             result = dao.getAllProblem(conn);             
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
    public FProblem getProblemById(FProblem bean) throws  EException {
        final String LOCATION = this.toString() + "~>getProblemById()";
        Connection cnn = null;       
        FProblem beantemp = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DProblem  dao  = new DProblem();
            beantemp = dao.getProblemById(cnn, bean);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beantemp;
    }
    
    public FReport getReportId(FReport bean) throws  EException {
        final String LOCATION = this.toString() + "~>getReportId()";
        Connection cnn = null;       
        FReport beantemp = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DReport  dao  = new DReport();
            beantemp = dao.getReportId(cnn, bean);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beantemp;
    }
    
     
    public boolean insert(FSeed seed,int userId,int newReport)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DReport dao = new DReport();                   
             result = dao.insert(conn,seed,userId,newReport);             
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
         final String LOCATION = this + "->update()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem();                   
             result = dao.update(conn,seed);
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
     
    public boolean updateAccepted(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateAccepted()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem();                   
             result = dao.updateAccepted(conn,seed);
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
    
    public boolean delete(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->addNew()";
      Connection conn = null;
      boolean result = false;     
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DProblem dao = new DProblem();                 
          result=dao.delete(conn,seed);            
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
    
    
    
    public boolean updateStatuReview(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateStatuReview()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DReport dao = new DReport();                   
             result = dao.updateStatuReview(conn,seed);
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
