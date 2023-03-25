package com.bo.directory;


import com.dao.connection.DBConnector;
import com.dao.directory.DDirectory;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BDirectory {
    
    public FBeans getAllSearch(FSeed seed)throws EException
      {
         final String LOCATION = this + "->getAllCategories()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DDirectory dao = new DDirectory(); 
             result = dao.getAllSearch(conn,seed);             
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
