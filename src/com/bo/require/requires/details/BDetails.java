package com.bo.require.requires.details;


import com.dao.connection.DBConnector;
import com.dao.require.requires.details.DDetails;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.require.requires.details.FDetail;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class BDetails
{
  public BDetails()
  {
  }
   public FDetail getRecordByID(FSeed seed)throws EException,SQLException
     {
        final String LOCATION = this + "->getRecordByID()";
        FDetail result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            DDetails dao = new DDetails();          
            result = dao.getRecordByID(conn,seed);
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
    public FBeans getRecordByRequire(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getRecordByRequire()";
         FBeans result = null;
         Connection conn = null;
         List params = new ArrayList();
         FDetail bean = (FDetail)seed;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DDetails dao = new DDetails();          
             params.add(bean.getRequire_id());
            // result = dao.getMultiRecords(conn,dao.SQL_SELECT_REQUIREDETAILS_BY_REQUIRE,params);
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
             DDetails dao = new DDetails();          
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
}
