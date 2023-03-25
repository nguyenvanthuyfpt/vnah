package com.bo.messages.create;


import com.dao.connection.DBConnector;
import com.dao.messages.create.DCreateFiles;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.messages.create.FCreate;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BCreateFiles
{  
    public boolean delete(FSeed seed)throws EException,SQLException {
        final String LOCATION = this + "->delete()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            DCreateFiles dao = new DCreateFiles();          
            result = dao.delete(conn,seed);
            DBConnector.endTransaction(conn);            
        }
        catch (EException  ex) {
        
            DBConnector.rollBackTransaction(conn);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        }
        finally {
            DBConnector.closeConnection(conn);
        }
        return result;    
     }
     
     public boolean addBath(FSeed seed)throws EException,SQLException{
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCreateFiles dao = new DCreateFiles();          
             result = dao.addBath(conn,seed);
             DBConnector.endTransaction(conn);            
          }catch (EException  ex) {
              DBConnector.rollBackTransaction(conn);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
           }
          finally {
               DBConnector.closeConnection(conn);
         }
        return result;    
     }
     
    public FCreate getByFileId(int fileId)throws EException,SQLException{
        final String LOCATION = this + "->getByFileId()";
        FCreate  bean=new FCreate();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            DCreateFiles dao = new DCreateFiles();          
            bean = dao.getByFileId(conn,fileId);
            DBConnector.endTransaction(conn);            
         }catch (EException  ex) {
             DBConnector.rollBackTransaction(conn);
           if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
         finally {
              DBConnector.closeConnection(conn);
        }
       return bean;    
    }
    
    public FBeans getAllFileByMessageId(int MessageId)throws EException,SQLException{
        final String LOCATION = this + "->getAllFileByMessageId()";
        FBeans  beans=null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            DCreateFiles dao = new DCreateFiles();          
            beans = dao.getAllFileByMessageId(conn,MessageId);
            DBConnector.endTransaction(conn);            
         }catch (EException  ex) {
             DBConnector.rollBackTransaction(conn);
           if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
         finally {
              DBConnector.closeConnection(conn);
        }
       return beans;    
    }
}
