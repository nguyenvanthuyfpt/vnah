package com.bo.report;


import com.dao.connection.DBConnector;
import com.dao.report.DReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.report.FReport;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BReport
{
  public BReport()
  {
  }
  
    public boolean restore(int[] ids,int value)throws EException,SQLException
      {
         final String LOCATION = this + "->restore()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
              DReport dao = new DReport();          
             result = dao.restore(conn,ids,value);
             DBConnector.endTransaction(conn);            
          }catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
     
   public FReport getRecordByID(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getAppByID()";
        FReport result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            DReport dao = new DReport();          
            result = dao.getRecordByID(conn,seed);
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
    public boolean delete(int id)throws EException,SQLException{
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
              DReport dao = new DReport();          
             result = dao.delete(conn,id);
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
    public boolean updatePermision(int id,int permission)throws EException,SQLException
      {
         final String LOCATION = this + "->updatePermision()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DReport dao = new DReport();          
             result = dao.updatePermision(conn,id,permission);
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
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DReport dao = new DReport();          
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
             DReport dao = new DReport();          
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

    public FBeans getAllByType(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllByType()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DReport dao = new DReport();          
             result = dao.getAllByType(conn,seed);
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
    public FBeans getUserByRules(long userId)throws EException,SQLException
      {
         final String LOCATION = this + "->getUserByRules()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DReport dao = new DReport();          
             result = dao.getUserByRules(conn,userId);
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
