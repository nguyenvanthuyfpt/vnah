package com.bo.template.templateType;


import com.dao.connection.DBConnector;
import com.dao.template.templateType.DTemplateType;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.template.templateType.FTemplateType;

import com.lib.AppConfigs;

import java.sql.Connection;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;


public class BTemplateType
{
  public FBeans getAllTemplateType() throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllTemplateType()";
    Connection cnn = null;
    DTemplateType templateType = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      templateType = new DTemplateType();
      beans = templateType.getAllTemplateType(cnn);      
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
    FTemplateType bean = (FTemplateType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DTemplateType dao = new DTemplateType();             
        
        if(dao.checkCodeIdTemplateType(conn, bean))
        {
            errors.add("alert",new ActionError("errors.category.templateType.existcode"));
        }else if(dao.checkNameTemplateType(conn, bean))  
       {
         errors.add("alert",new ActionError("errors.category.templateType.existname"));
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
    FTemplateType bean = (FTemplateType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DTemplateType dao = new DTemplateType();         
       if(dao.checkCodeIdTemplateType(conn, bean))
       {
           errors.add("alert",new ActionError("errors.category.templateType.existcode"));
       }else if(dao.checkNameIdTemplateType(conn, bean))
       {
        errors.add("alert",new ActionError("errors.category.templateType.existname"));
       }
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
    FTemplateType bean = (FTemplateType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DTemplateType dao = new DTemplateType();  
       
          dao.delete(conn,bean);
            errors.add("alert",new ActionError("alert.delete.successfull"));
       
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

  public boolean checkNametemplateType(FSeed seed) throws EException 
  {
    final String LOCATION = this + "->checkNametemplateType()";
    boolean result = true;
    Connection conn = null;
    FTemplateType bean = (FTemplateType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DTemplateType dao = new DTemplateType();          
        result = dao.checkNameTemplateType(conn,bean);
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
  
  public boolean checkNameIDTemplateType(FSeed seed) throws EException 
  {
   final String LOCATION = this + "->checkNameIDTemplateType()";
   boolean result = true;
   Connection conn = null;
   FTemplateType bean = (FTemplateType)seed;
   try 
   {
       conn = DBConnector.getConnection();
       DBConnector.startTransaction(conn);
       DTemplateType dao = new DTemplateType();          
       result = dao.checkNameIdTemplateType(conn,bean);
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
  
  public FTemplateType gettemplateTypeById(FTemplateType bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>gettemplateTypeById()";
   Connection cnn = null;
   DTemplateType dao = null;
   FTemplateType beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DTemplateType();
     beantemp = dao.getTemplateTypeById(cnn, bean);    

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
  public boolean checkCodetemplateType(FSeed seed) throws EException 
  {
    final String LOCATION = this + "->checkNameDepartment()";
    boolean result = true;
    Connection conn = null;
    FTemplateType bean = (FTemplateType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DTemplateType dao = new DTemplateType();          
        result = dao.checkCodeIdTemplateType(conn,bean);
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
