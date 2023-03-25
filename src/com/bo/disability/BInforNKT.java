package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DInforNKT;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FInforNKT;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BInforNKT
{
    DInforNKT dao = new DInforNKT();          
   
    public FBeans getAllTemp(FInforNKT bean)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllByIdNkt()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.getAllTemp(conn,bean);
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
    public String getSRC(int nktId)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllByIdNkt()";
         String result ="";
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.getSRC(conn,nktId);
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
    
 
    public boolean addBatch(int[] ids,int nktId)throws EException,SQLException
      {
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.addBatch(conn,ids,nktId);
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
  
  
//    public FInforNKT getById(int id)throws EException,SQLException
//      {
//         final String LOCATION = this + "->getById()";
//         FInforNKT result = new FInforNKT();
//         Connection conn = null;
//         try {
//             conn = DBConnector.getConnection();
//             DBConnector.startTransaction(conn);
//             result = dao.getById(conn,id);
//             DBConnector.endTransaction(conn);            
//          }
//          catch (EException ex) {
//             DBConnector.rollBackTransaction(conn);
//             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
//          }
//          finally {
//             DBConnector.closeConnection(conn);
//         }
//     return result;    
//     }

}
