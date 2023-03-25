package com.bo.tasks.categories;


import com.dao.connection.DBConnector;
import com.dao.tasks.categories.DCategories;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.tasks.categories.FCategories;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BCategories {
    
    public FBeans getAllRecord(FSeed seed,int userId,int block)throws EException
      {
         final String LOCATION = this + "->getAllRecord()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCategories dao = new DCategories(); 
             result = dao.getAllRecord(conn,seed,userId,block);             
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
     
    public FCategories getCategoriesById(FCategories bean) throws  EException {
        final String LOCATION = this.toString() + "~>getCategoriesById()";
        Connection cnn = null;       
        FCategories beantemp = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DCategories  dao  = new DCategories();
            beantemp = dao.getCategoriesById(cnn, bean);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }
        return beantemp;
    }
     
    public boolean insert(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCategories dao = new DCategories();                   
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
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCategories dao = new DCategories();                   
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
    
    public boolean delete(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->addNew()";
      Connection conn = null;
      boolean result = false;     
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DCategories dao = new DCategories();                 
          result=dao.delete(conn,seed);            
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
}
