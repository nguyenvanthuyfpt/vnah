package com.bo.pgroups;


import com.dao.connection.DBConnector;
import com.dao.pgroups.DPgroups;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.pgroups.FPgroup;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BPgroups 
{
   public FPgroup getRecordById(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getRecordById()";
        FPgroup result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DPgroups dao = new DPgroups();          
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
    public FBeans getAllRecord(long userId)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DPgroups dao = new DPgroups();          
             result = dao.getMultiRecords(conn,dao.SQL_SELECT_PGROUPS,userId);
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
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DPgroups dao = new DPgroups();             
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
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DPgroups dao = new DPgroups();          
             if(!dao.isExist(conn,seed)){
                result = dao.insert(conn,seed);
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
    public boolean update(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DPgroups dao = new DPgroups();          
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

}
