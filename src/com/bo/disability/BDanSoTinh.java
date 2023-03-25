package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DDanSoTinh;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanSoTinh;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BDanSoTinh
{
    DDanSoTinh dao = new DDanSoTinh();
    public boolean delete(int ids) throws EException{
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
    public FDanSoTinh getByPeriod(int period,int year,int idProvine)throws EException,SQLException
      {
         final String LOCATION = this + "->getById()";
         FDanSoTinh result = new FDanSoTinh();
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.getByPeriod(conn,period,year,idProvine);
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
              if(!dao.checkExitIdTinh(conn,seed)){
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
         final String LOCATION = this + "->update()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             if(!dao.checkExitIdTinh(conn,seed)){
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
  
    public FBeans getAllByIdTinh(int idTinh)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllByIdTinh()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.getAllByTinh(conn,idTinh);
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
    public FDanSoTinh getById(int id)throws EException,SQLException
      {
         final String LOCATION = this + "->getById()";
         FDanSoTinh result = new FDanSoTinh();
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.getById(conn,id);
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
