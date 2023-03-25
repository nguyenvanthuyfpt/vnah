package com.bo.cabin.cabinType;


import com.dao.cabin.cabinType.DCabinType;
import com.dao.connection.DBConnector;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.cabin.cabinType.FCabinType;

import com.lib.AppConfigs;

import java.sql.Connection;


public class BCabinType
{
  
  public FBeans getAll(long meId,int type,int depId) throws  EException
  {
    final String LOCATION = this.toString() + "~>getAllCabin()";
    Connection cnn = null;
    DCabinType dao = null;
    FBeans beans = null;
    try
    {      
      cnn = DBConnector.getConnection();
      DBConnector.startTransaction(cnn);
      dao = new DCabinType();
      beans = dao.getAll(cnn,meId,type,depId);
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
    FCabinType bean = (FCabinType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DCabinType dao = new DCabinType();   
//        if(!dao.checkCodeCabinType(conn,seed)){
            result = dao.addNew(conn,bean);
//        }
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
    FCabinType bean = (FCabinType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DCabinType dao = new DCabinType();         
//         if(!dao.isExistUpdate(conn,seed)){
         result=dao.update(conn,bean);
//         }
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
    final String LOCATION = this.toString() + "->addNew()";
    Connection conn = null;
    boolean result=false;
    FCabinType bean = (FCabinType)seed;
    try 
    {
        conn = DBConnector.getConnection();
        DBConnector.startTransaction(conn);
        DCabinType dao = new DCabinType();  
        if(!dao.checkIdCabinTypeExistCabin(conn,bean)){
        
         result= dao.delete(conn,bean);
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

 
  public FCabinType getCabinTypeById(FCabinType bean) throws  EException
  {
   final String LOCATION = this.toString() + "~>getFormById()";
   Connection cnn = null;
   DCabinType dao = null;
   FCabinType beantemp = null;
   try
   {      
     cnn = DBConnector.getConnection();
     DBConnector.startTransaction(cnn);
     
     dao = new DCabinType();
     beantemp = dao.getCabinTypeById(cnn, bean);    

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
    public FBeans getAllCabinTypeByType(int type,long meId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getAllCabinTypeByType()";
     Connection cnn = null;
     DCabinType dao = null;
     FBeans beans = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       dao = new DCabinType();
       beans = dao.getAllCabinTypeByType(cnn,type,meId);    
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
 
   
}
