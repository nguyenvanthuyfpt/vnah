package com.bo.doc.docreport;


import com.dao.connection.DBConnector;
import com.dao.doc.docreport.DDocReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docreport.FDocReport;

import com.lib.AppConfigs;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.sql.Connection;

import java.util.List;


public class BDocReport
{
 
    public FBeans getTotalDocByStatus(int workflowId,List params) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTotalDocByStatus()";
     Connection cnn = null;
     FBeans beans= null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       beans = new DDocReport().getTotalDocByStatus(cnn,workflowId,params);    
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
    
    
    public FBeans getTotalDocByTransfer(int workflowId,List params) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTotalDocByTransfer()";
     Connection cnn = null;
     FBeans beans= null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       beans = new DDocReport().getTotalDocByTransfer(cnn,workflowId,params);    
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
    
    
    public FBeans getTotalDocByDocType(int workflowId,List params) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTotalDocByDocType()";
     Connection cnn = null;
     FBeans beans= null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       beans = new DDocReport().getTotalDocByDocType(cnn,workflowId,params);    
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
    
    
    public FBeans getTotalDocByBranch(int workflowId,List params) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTotalDocByDocType()";
     Connection cnn = null;
     FBeans beans= null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       beans = new DDocReport().getTotalDocByBranch(cnn,workflowId,params);    
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
    
    public FBeans getAllDoc(FDocReport bean,int pageIndex,int views) throws  EException
    {
     final String LOCATION = this.toString() + "~>getAllDoc()";
     Connection cnn = null;
     FBeans beans= null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       beans = new DDocReport().getAllDoc(cnn,bean,pageIndex,views);    
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
    
    public FBeans getAllReportExcel(FSeed seed,List params,int type) throws  EException
    {
     final String LOCATION = this.toString() + "~>getAllReportExcel()";
     Connection cnn = null;
     FBeans beans= null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       beans = new DDocReport().getAllReportExcel(cnn,seed,params,type);    
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
    
    public String exportExcel(FBeans beans,FSeed seed,String excelFile,int type) throws  EException,FileNotFoundException,IOException
    {
      final String LOCATION = this.toString() + "~>exportExcel()";
      Connection cnn = null;
      String result = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        DDocReport dao = new DDocReport();
           result = dao.exportExcel(beans,seed,excelFile,type);
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
    
    public FBeans getUserByDepartmentID(int id,int pageIndex)throws EException
      {
         final String LOCATION = this + "->getUserByDepartmentID()";
         FBeans beans = null;
         Connection conn = null;
         try {
              conn= DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DDocReport dao = new DDocReport();
             beans = dao.getUserByDepartmentID(conn,id,pageIndex);
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
}
