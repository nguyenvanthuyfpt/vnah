package com.bo.template;


import com.dao.connection.DBConnector;
import com.dao.template.DTemplate;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.template.FTemplate;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BTemplate
{
    DTemplate dao = new DTemplate();          
   public FTemplate getRecordByID(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getAppByID()";
        FTemplate result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
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
    public boolean delete(int[] ids)throws EException,SQLException{
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.delete(conn,ids);
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
    public boolean restore(int[] ids,int value)throws EException,SQLException
      {
         final String LOCATION = this + "->restore()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
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
    public boolean updatePermision(int id,int permission)throws EException,SQLException
      {
         final String LOCATION = this + "->updatePermision()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
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
    public boolean update(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
            if(!dao.isExistUpdate(conn,seed)){
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
    public FBeans getAll(FSeed seed,int userId,int type)throws EException,SQLException
      {
         final String LOCATION = this + "->getAll()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.getAll(conn,seed,userId,type);
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
    public FBeans getByCode(String code,int type)throws EException,SQLException
      {
         final String LOCATION = this + "->getByCode()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.getByCode(conn,code,type);
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
