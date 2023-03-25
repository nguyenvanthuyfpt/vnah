package com.bo.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.report.DReportTotal;
import com.dao.disability.report.DReportTotal_params;

import com.exp.EException;

import com.form.FSeed;
import com.form.disability.FPopulation;
import com.form.disability.report.FReportTotal;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BReportTotal
{
    DReportTotal dao = new DReportTotal();          
 
 
    
    public boolean insert(FSeed seed,FPopulation beanPo)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.insert(conn,seed,beanPo);
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
 
  

    public FReportTotal getById(int id)throws EException,SQLException
      {
         final String LOCATION = this + "->getById()";
         FReportTotal result = new FReportTotal();
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
    public int getCount(String sql)throws EException,SQLException
      {
         final String LOCATION = this + "->getById()";
         int result = 0;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
              DReportTotal_params daoT = new DReportTotal_params();          

             result = daoT.getCount(conn,sql);
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
