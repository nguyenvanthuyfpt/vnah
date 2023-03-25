package com.bo.cabin;


import com.dao.cabin.DCabin;
import com.dao.connection.DBConnector;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.cabin.FCabin;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BCabin
{
   
   
   
    public boolean updateShare(int id,long userId,int cabinTypeId)throws EException,SQLException
         {
            final String LOCATION = this + "->updateShare()";
            boolean result = false;
            Connection conn = null;
            try {
                conn = DBConnector.getConnection();
                DBConnector.startTransaction(conn);
                DCabin dao = new DCabin();          
                result = dao.updateShare(conn,id,userId,cabinTypeId);
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

   public FCabin getById(int id)throws EException,SQLException
     {
        final String LOCATION = this + "->getById()";
        FCabin result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            DCabin dao = new DCabin();          
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

    public boolean delete(int id)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCabin dao = new DCabin();          
             result = dao.delete(conn,id);
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
             DCabin dao = new DCabin();          
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
    public boolean tranferData(FCabin bean)throws EException,SQLException
      {
         final String LOCATION = this + "->tranferData()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCabin dao = new DCabin();          
             result = dao.tranferData(conn,bean);
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
             DCabin dao = new DCabin();          
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

    public FBeans getAllByType(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllByType()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCabin dao = new DCabin();          
             result = dao.getAllByType(conn,seed);
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
 
    public FBeans getAllCabinUnionCabinType(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->getAllCabinUnionCabinType()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCabin dao = new DCabin();          
             result = dao.getAllCabinUnionCabinType(conn,seed);
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
 
    public FBeans getUserShare(int meId)throws EException,SQLException
      {
         final String LOCATION = this + "->getUserShare()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCabin dao = new DCabin();          
             result = dao.getUserShare(conn,meId);
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
     
    public boolean checkFileStoreCabin(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCabin dao = new DCabin();          
             result = dao.checkFileStoreCabin(conn,seed);
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
