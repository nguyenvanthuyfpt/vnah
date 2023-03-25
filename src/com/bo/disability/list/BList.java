package com.bo.disability.list;


import com.dao.connection.DBConnector;
import com.dao.disability.list.DList;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.list.FList;
import com.form.disability.search.FSearch;

import com.lib.AppConfigs;

import java.sql.Connection;

import org.apache.struts.action.ActionErrors;


public class BList
{
  
    //**************************************************   
      public FList getListReportById(FList bean) throws EException {
          final String LOCATION = this.toString() + "~>getListReportById()";
          Connection cnn = null;
          DList dao= null;
          FList beantemp = null;
          try {
              cnn = DBConnector.getConnection();
              DBConnector.startTransaction(cnn);
              dao = new DList();
              beantemp = dao.getListReportById(cnn, bean);
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
      //**********************************************************************
    public String getAllEmpListReport(FList beanL) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllEmpListReport()";
      Connection cnn = null;
      FBeans beans = null;
      String result=null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        DList search = new DList();         
//        result = search.getAllEmpListReport(cnn,beanL);                          
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
        public FBeans getAllListReport(FList bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllListReport()";
      Connection cnn = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        DList search = new DList();         
        beans = search.getAllListReport(cnn,bean);                          
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
    public FBeans getAllListReportCreate(FList bean) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllListReportCreate()";
      Connection cnn = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        DList search = new DList();         
        beans = search.getAllListReportCreate(cnn,bean);                          
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
    //******************************************************
     public FBeans getEmpByListReport(FList bean) throws  EException
     {
       final String LOCATION = this.toString() + "~>getEmpByListReport()";
       Connection cnn = null;
       FBeans beans = null;
       try
       {      
         cnn = DBConnector.getConnection();
         DBConnector.startTransaction(cnn);
         DList search = new DList();         
         beans = search.getEmpByListReport(cnn,bean);                          
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
      //******************************************************
     public boolean delete(FSeed seed) throws  EException {
         final String LOCATION = this.toString() + "->addNew()";
         Connection conn = null;
         boolean result = false;
         FList bean = (FList)seed;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DList dao = new DList();
             result=dao.delete(conn, bean);
             DBConnector.endTransaction(conn);
         } catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, ex);
         } finally {
             DBConnector.closeConnection(conn);
         }
         return result;
     }
      //************************************************************************************
       public boolean deleteEmp(FSeed seed) throws  EException {
           final String LOCATION = this.toString() + "->deleteEmp()";
           Connection conn = null;
           boolean result = false;       
           try {
               conn = DBConnector.getConnection();
               DBConnector.startTransaction(conn);
               DList dao = new DList();
               result=dao.deleteEmp(conn, seed);
               DBConnector.endTransaction(conn);
           } catch (EException ex) {
               DBConnector.rollBackTransaction(conn);
               if (AppConfigs.APP_DEBUG)
                   throw new EException(LOCATION, ex);
           } finally {
               DBConnector.closeConnection(conn);
           }
           return result;
       }
        //************************************************************************************
      public boolean update(FSeed seed) throws  EException {
         final String LOCATION = this.toString() + "->update()";        
         Connection conn = null;
         boolean result=true;         
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DList dao = new DList();
             result=dao.update(conn, seed);
             DBConnector.endTransaction(conn);
         } catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, ex);            
         } finally {
             DBConnector.closeConnection(conn);
         }
         return result;
      }
    //************************************************************************************ 
    public boolean addNew(FSeed seed, ActionErrors errors) throws  EException
    {
      final String LOCATION = this.toString() + "->addNew()";
      boolean result = false;
      Connection conn = null;
      FList bean = (FList)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DList dao = new DList(); 
          if(!bean.getEmps().equals(",") || bean.getCheckEmpAll()==1){
            result = dao.addNew(conn,bean);          
          }
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
    //**********************************************************
    public boolean addNewListEmp(FSeed seed, ActionErrors errors,int index) throws  EException
    {
      final String LOCATION = this.toString() + "->addNewListEmp()";
      boolean result = false;
      Connection conn = null;
      FSearch bean = (FSearch)seed;
      try
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DList dao = new DList();
          result = dao.addNewListEmp(conn,bean,index);
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
    
    public boolean addNewListEmpTemp(FSeed seed, ActionErrors errors,int index) throws  EException
    {
      final String LOCATION = this.toString() + "->addNewListEmpTemp()";
      boolean result = false;
      Connection conn = null;
      FSearch bean = (FSearch)seed;
      try
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DList dao = new DList();
//          result = dao.addNewListEmpTemp(conn,bean,index);
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
    
    public boolean addUpdateListEmp(FSeed seed, ActionErrors errors,int index) throws  EException
    {
      final String LOCATION = this.toString() + "->addUpdateListEmp()";
      boolean result = false;
      Connection conn = null;
      FSearch bean = (FSearch)seed;
      try
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DList dao = new DList();
//          result = dao.addUpdateListEmp(conn,bean,index);
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
    //**********************************************************   
     
       public boolean deleteListEmp(FSeed seed) throws  EException {
           final String LOCATION = this.toString() + "->deleteListEmp()";
           boolean result=false;
           Connection conn = null;
           FSearch bean = (FSearch)seed;
           try {
               conn = DBConnector.getConnection();
               DBConnector.startTransaction(conn);
               DList dao = new DList();
               result=dao.deleteListEmp(conn, bean);
               DBConnector.endTransaction(conn);
           } catch (EException ex) {
               DBConnector.rollBackTransaction(conn);
               if (AppConfigs.APP_DEBUG)
                   throw new EException(LOCATION, ex);
           } finally {
               DBConnector.closeConnection(conn);
           }
           return result;
       }
}
