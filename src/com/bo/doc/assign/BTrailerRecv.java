package com.bo.doc.assign;


import com.dao.connection.DBConnector;
import com.dao.doc.assign.DTrailerRecv;

import com.exp.EException;

import com.form.FBeans;
import com.form.doc.assign.FDocAssign;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BTrailerRecv
{
    public void updateReadedOfSendPeople(int id,int readed,int userId)throws EException,SQLException
      {
         final String LOCATION = this + "->updateReadedOfSendPeople()";
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
              DTrailerRecv dao =new DTrailerRecv();
             dao.updateReadedOfSendPeople(conn,id,readed,userId);
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     }
    public boolean checkExitsDocId_In_Doc_trailer_recv(int docId) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
        DTrailerRecv dao = null;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
           dao = new DTrailerRecv();
              result=dao.checkExitsDocId_In_Doc_trailer_recv(conn,docId);
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
    public FBeans getDocRecvByDocId(FDocAssign bean,int userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getDocRecvByDocId()";
     Connection cnn = null;
     DTrailerRecv dao = null;
     FBeans beans = null;
     try{      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DTrailerRecv();
        beans = dao.getDocRecvByDocId(cnn, bean,userId);
        DBConnector.endTransaction(cnn);
     }
     catch (EException sqle)
     {
       DBConnector.rollBackTransaction(cnn);
       if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
       DBConnector.closeConnection(cnn);
     }
     return beans;
    }
}
