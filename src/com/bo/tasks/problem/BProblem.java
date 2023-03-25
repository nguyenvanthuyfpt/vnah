package com.bo.tasks.problem;


import com.dao.connection.DBConnector;
import com.dao.tasks.problem.DProblem;
import com.dao.tasks.report.DReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.tasks.problem.FProblem;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BProblem {
    
    public FBeans getReportByAssignId(FProblem bean) throws  EException {
        final String LOCATION = this.toString() + "~>getReportByWorker()";
        Connection cnn = null;       
        FBeans beans = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DReport  dao  = new DReport();
            beans = dao.getReportByAssignId(cnn, bean);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beans;
    }

    
    public FBeans getReviewByAssign(FProblem bean) throws  EException {
        final String LOCATION = this.toString() + "~>getReviewByAssign()";
        Connection cnn = null;       
        FBeans beans = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DReport  dao  = new DReport();
            beans = dao.getReviewByAssign(cnn, bean);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beans;
    }
    
      public int getAssignCheck(long userId)throws EException,SQLException
      {
         final String LOCATION = this + "->getAssignCheck()";         
         int result =0;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem();          
             result = dao.getAssignCheck(conn,userId);
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
    public FBeans getAllCategories(FSeed seed)throws EException
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
    public FBeans getUserByProblemId(FSeed seed,int userId) throws  EException{
      final String LOCATION = this.toString() + "~>getUserByProblemId()";
      Connection cnn = null;
      DProblem dao = null;
      FBeans beans = null;
      FProblem bean = (FProblem)seed;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DProblem();     
        beans = dao.getUserByProblemId(cnn,seed,userId);                          
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
    public int getMoreDeadLine(long userId,int type) throws  EException{
      final String LOCATION = this.toString() + "~>getMoreDeadLine()";
      Connection cnn = null;
      int total=0;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        total = new DProblem().getMoreDeadLine(cnn,userId,type);
        DBConnector.endTransaction(cnn);
      }catch (EException sqle){
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }finally{
        DBConnector.closeConnection(cnn);
      }
      return total;
    }
    
    public FProblem getAllAmount(FSeed seed, int userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllAmount()";
      Connection cnn = null;
      DProblem dao = null;
      FProblem bean = new FProblem();    
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DProblem();
        bean = dao.getAllAmount(cnn,seed,userId);
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
    
    
    public FBeans getAllRecord(FSeed seed, long userId)throws EException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem(); 
             result = dao.getAllRecord(conn,seed,userId);             
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
     
    public String  getUserName(int userId)throws EException
      {
         final String LOCATION = this + "->getUserName()";
         String result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem(); 
             result = dao.getUserName(conn,userId);             
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
    
    public FProblem getProblemByIdRoot(FProblem bean) throws  EException {
        final String LOCATION = this.toString() + "~>getProblemByIdRoot()";
        Connection cnn = null;       
        FProblem beantemp = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DProblem  dao  = new DProblem();
            beantemp = dao.getProblemByIdRoot(cnn, bean);
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
    
    
    public FBeans getAllReportOfWorker(FProblem bean) throws  EException {
        final String LOCATION = this.toString() + "~>getAllReportOfWorker()";
        Connection cnn = null;       
        FBeans beans = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DProblem  dao  = new DProblem();
            beans = dao.getAllReportOfWorker(cnn, bean);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beans;
    }
    
    public FBeans getListReportOfWorker(FProblem bean,long userId) throws  EException {
        final String LOCATION = this.toString() + "~>getListReportOfWorker()";
        Connection cnn = null;       
        FBeans beans = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DProblem  dao  = new DProblem();
            beans = dao.getListReportOfWorker(cnn, bean,userId);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beans;
    }

    
   
    
    public FProblem getAssignById(int assignId) throws  EException {
        final String LOCATION = this.toString() + "~>getAssignById()";
        Connection cnn = null;       
        FProblem beantemp = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DProblem  dao  = new DProblem();
            beantemp = dao.getAssignById(cnn, assignId);
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
     
    public boolean insert(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem();                   
             result = dao.insert(conn,seed);             
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
     
    public boolean updateProblemStop(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateProblemStop()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem();                   
             result = dao.updateProblemStop(conn,seed);
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
     
    public boolean updateAssignStop(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateAssignStop()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem();                   
             result = dao.updateAssigStop(conn,seed);
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
     
    public boolean updateReaded(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->updateReaded()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DProblem dao = new DProblem();                   
             result = dao.updateReaded(conn,seed);
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
    
    
    public int checkHaveReport(long meId)throws EException,SQLException
    {
       final String LOCATION = this + "->checkHaveReport()";         
       int result =0;
       Connection conn = null;
       try {
           conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
           DBConnector.startTransaction(conn);
           DProblem dao = new DProblem();          
           result = dao.checkHaveReport(conn,meId);
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
    
    public int checkHaveReview(long meId)throws EException,SQLException
    {
       final String LOCATION = this + "->checkHaveReview()";         
       int result =0;
       Connection conn = null;
       try {
           conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
           DBConnector.startTransaction(conn);
           DProblem dao = new DProblem();          
           result = dao.checkHaveReview(conn,meId);
           DBConnector.endTransaction(conn);            
        } catch (EException ex) {
           DBConnector.rollBackTransaction(conn);
           if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        }
        finally {
           DBConnector.closeConnection(conn);
       }
    return result;
    }
}
