package com.bo.report.reportType;


import com.dao.connection.DBConnector;
import com.dao.report.reportType.DReportType;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.report.reportType.FReportType;

import com.lib.AppConfigs;

import java.sql.Connection;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;


public class BReportType
{
  public FBeans getAllReportType() throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllReportType()";
    Connection cnn = null;
    DReportType doctype = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      
      doctype = new DReportType();
      beans = doctype.getAllReportType(cnn);      
                  
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
    
  public boolean addNew(FSeed seed, ActionErrors errors) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    boolean result = true;
    Connection conn = null;
    FReportType bean = (FReportType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportType dao = new DReportType();             
        
        if(dao.checkCodeReportType(conn, bean))
        {
            errors.add("alert",new ActionError("errors.category.reportType.existcode"));
        }else if(dao.checkNameReportType(conn, bean))  
       {
         errors.add("alert",new ActionError("errors.category.reportType.existname"));
       }        
       
       if(errors.isEmpty())
       {
         result = dao.addNew(conn,bean);
           errors.add("alert",new ActionError("alert.insert.successfull"));
         bean.reset();        
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
  
  public void update(FSeed seed, ActionErrors errors) throws  EException
  {
    final String LOCATION = this.toString() + "->update()";
    Connection conn = null;
    FReportType bean = (FReportType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportType dao = new DReportType();         
//       if(dao.checkCodeIDReportType(conn, bean))
//       {
//           errors.add("alert",new ActionError("errors.category.doctype.existcode"));
//       }
//       
//       if(dao.checkNameIDReportType(conn, bean))
//       {
//        errors.add("alert",new ActionError("errors.category.doctype.existname"));
//       }
//       
       if(errors.isEmpty())
       {
         dao.update(conn,bean);
           errors.add("alert",new ActionError("alert.update.successfull"));
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
  }  

  public void delete(FSeed seed, ActionErrors errors) throws  EException
  {
    final String LOCATION = this.toString() + "->addNew()";
    Connection conn = null;
    FReportType bean = (FReportType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportType dao = new DReportType();  
       
          dao.delete(conn,bean);
            
       
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
  }  

  public boolean checkNameDocType(FSeed seed) throws EException 
  {
    final String LOCATION = this + "->checkNameDocType()";
    boolean result = true;
    Connection conn = null;
    FReportType bean = (FReportType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportType dao = new DReportType();          
        result = dao.checkNameReportType(conn,bean);
        DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        result = true;
     }
     finally 
     {
        DBConnector.closeConnection(conn);
    }
    return result;    
  }
  
  public boolean checkNameIDReportType(FSeed seed) throws EException 
  {
   final String LOCATION = this + "->checkNameIDReportType()";
   boolean result = true;
   Connection conn = null;
   FReportType bean = (FReportType)seed;
   try 
   {
       conn = DBConnector.getConnection();
       DBConnector.startTransaction(conn);
       DReportType dao = new DReportType();          
       result = dao.checkNameIdReportType(conn,bean);
       DBConnector.endTransaction(conn);            
    }
    catch (EException ex) 
    {
       DBConnector.rollBackTransaction(conn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
       result = true;
    }
    finally 
    {
       DBConnector.closeConnection(conn);
    }
   return result;    
  }
  
  public FReportType getDocTypeById(FReportType bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getDocTypeById()";
   Connection cnn = null;
   DReportType dao = null;
   FReportType beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DReportType();
     beantemp = dao.getReportTypeById(cnn, bean);    

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
  public boolean checkCodeDocType(FSeed seed) throws EException 
  {
    final String LOCATION = this + "->checkNameDepartment()";
    boolean result = true;
    Connection conn = null;
    FReportType bean = (FReportType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DReportType dao = new DReportType();          
        result = dao.checkCodeReportType(conn,bean);
        DBConnector.endTransaction(conn);            
     }
     catch (EException ex) 
     {
        DBConnector.rollBackTransaction(conn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        result = true;
     }
     finally 
     {
        DBConnector.closeConnection(conn);
    }
    return result;    
  }    
 
}
