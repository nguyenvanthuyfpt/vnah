package com.bo.importdata;


import com.dao.connection.DBConnector;
import com.dao.importdata.DImportData;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BImportData
{
    public FBeans getAllTablesInData(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllTablesInData()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DImportData dao = new DImportData();          
             result = dao.getAllTablesInData(conn,seed);
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
    public FBeans getAllColumnsInTable(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllColumnsInTable()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DImportData dao = new DImportData();          
             result = dao.getAllColumnsInTable(conn,seed);
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
    public FBeans getPaging(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getPaging()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DImportData dao = new DImportData();          
             result = dao.getPaging(conn,seed);
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
    public String[][] getAllRecord(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllRecord()";
         String[][] result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DImportData dao = new DImportData();          
             result = dao.getAllRecord(conn,seed);
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
    public boolean insert(FBeans beans,FSeed seed,String[][] mang2c)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DImportData dao = new DImportData();          
             result = dao.insert(conn,beans,seed,mang2c);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
//          //.println(ex);
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public boolean addTable(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->addTable()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DImportData dao = new DImportData();          
             result = dao.addTable(conn,seed);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
//          //.println(ex);
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public boolean deleteTable(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->deleteTable()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DImportData dao = new DImportData();          
             result = dao.delete(conn,seed);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
//          //.println(ex);
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
             DImportData dao = new DImportData();          
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
}
