package com.bo.mycontact;


import com.dao.connection.DBConnector;
import com.dao.mycontact.DMycontact;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.mycontact.FMycontact;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BMycontact
{
    public FBeans getAllMycontactByPgroup(FSeed seed)throws EException
      {
         final String LOCATION = this + "->getAllMycontactByPgroup()";
         FBeans beans = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DMycontact dao = new DMycontact();          
             beans = dao.getAllMycontactByPgroup(conn,seed);
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
     
     
    public FBeans getViewMycontact(long userId)throws EException
      {
         final String LOCATION = this + "->getViewMycontact()";
         FBeans beans = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DMycontact dao = new DMycontact();          
             beans = dao.getViewMycontact(conn,userId);
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
    public FBeans searchList(FSeed seed)throws EException
      {
         final String LOCATION = this + "->searchList()";
         FBeans beans = null;
         Connection conn = null;
         try {
              conn = DBConnector.getConnection();
              DBConnector.startTransaction(conn);
             DMycontact dao = new DMycontact();          
             beans = dao.searchList(conn,seed);
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
    
    public boolean delete(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DMycontact dao = new DMycontact();          
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
    public boolean insert(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false; //thanh cong
         Connection conn = null;
         
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DMycontact dao = new DMycontact();              
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
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DMycontact dao = new DMycontact();                      
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
  
  public FMycontact getRecordById(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getRecordById()";
        FMycontact result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DMycontact dao = new DMycontact();          
            result = dao.getRecordById(conn,seed);
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
