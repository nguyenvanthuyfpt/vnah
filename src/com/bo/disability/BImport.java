package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DImport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.HashMap;
import java.util.Map;


public class BImport
{
    public boolean insert(FSeed seed,String data[][])throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result =  new DImport().insert(conn,seed,data);
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
    public FBeans getAllTable(boolean isCategory) throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         FBeans result =null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result =  new DImport().getAllTable(conn, isCategory);
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
    
    public Map<String, String> getAllColumn(String tableName) throws EException, SQLException{
        final String LOCATION = this + "->insert()";
        FBeans result = null;
        Map<String, String> map_column = new HashMap<String, String>();
        Connection conn = null;
        
        try{
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            map_column =  new DImport().getAllColumn(conn, tableName);
            DBConnector.endTransaction(conn);  
            
        }catch (EException ex){        
            DBConnector.rollBackTransaction(conn);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
            
        }finally{
            DBConnector.closeConnection(conn);
        }
        return map_column;
    }
}
