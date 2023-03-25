package com.bo.require.requires;


import com.dao.connection.DBConnector;
import com.dao.doc.assign.DAssignRecv;

import com.exp.EException;

import com.form.FSeed;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BTrailer
{
   
    public boolean insertReview(FSeed seed,int userId) throws  EException
    {
      final String LOCATION = this.toString() + "->insertReview()";
      boolean result = false;
      Connection conn = null;    
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DAssignRecv dao = new DAssignRecv();          
          result = dao.insertReview(conn,seed,userId);
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
