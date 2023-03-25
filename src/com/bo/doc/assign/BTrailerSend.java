package com.bo.doc.assign;


import com.dao.connection.DBConnector;
import com.dao.doc.assign.DTrailerSend;

import com.exp.EException;

import com.form.FBeans;
import com.form.doc.assign.FDocAssign;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;


public class BTrailerSend
{
   
    public boolean checkExitsDocId_In_Doc_trailer_send(int docId) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
        DTrailerSend dao = null;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
           dao = new DTrailerSend();
              result=dao.checkExitsDocId_In_Doc_trailer_send(conn,docId);
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
    public FBeans getDocAssignByDocId(FDocAssign bean,int userId) throws  EException
    {
     final String LOCATION = this.toString() + "~>getDocAssignByDocId()";
     Connection cnn = null;
     DTrailerSend dao = null;
     FBeans beans = null;
     try{      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DTrailerSend();
        beans = dao.getDocAssignByDocId(cnn, bean,userId);
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
    public void updateReaded(int id,int readed,long userId,int foryouId)throws EException,SQLException
      {
         final String LOCATION = this + "->updateReaded()";
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
              DTrailerSend dao =new DTrailerSend();
             dao.updateReaded(conn,id,readed,userId,foryouId);
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
    public void updateReadedOfSendPeople(int id,int readed,int userId)throws EException,SQLException
      {
         final String LOCATION = this + "->updateReadedOfSendPeople()";
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
              DTrailerSend dao =new DTrailerSend();
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
}
