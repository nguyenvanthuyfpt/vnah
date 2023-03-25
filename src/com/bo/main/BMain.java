package com.bo.main;


import com.dao.connection.DBConnector;
import com.dao.main.DMain;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.require.trailer.FRequireTrailer;
import com.form.doc.assign.FDocAssign;
import com.form.main.FMain;

import com.lib.AppConfigs;

import java.sql.Connection;

public class BMain {
    
//    public boolean checkSms() throws  EException
//    {
//      final String LOCATION = this.toString() + "~>checkSms()";
//      Connection cnn = null;    
//      boolean result=false;
//      try{      
//            cnn = DBConnector.getConnection();
//            DBConnector.startTransaction(cnn);
//            DMain dao = new DMain();
//            result = dao.checkSms(cnn);
//            DBConnector.endTransaction(cnn);
//      }
//      catch (EException sqle)
//      {
//        DBConnector.rollBackTransaction(cnn);
//        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
//      }
//      finally
//      {
//        DBConnector.closeConnection(cnn);
//      }
//      return result;
//    }
//    
    
    
    
    
    public FRequireTrailer checkRulesRequire(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~>checkRulesRequire()";
      Connection cnn = null;       
      FRequireTrailer bean = new FRequireTrailer();
      try{      
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DMain dao = new DMain();
            bean = dao.checkRulesRequire(cnn,seed);
            DBConnector.endTransaction(cnn);
      }
      catch (EException sqle)
      {
        DBConnector.rollBackTransaction(cnn);
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }
      finally
      {
        DBConnector.closeConnection(cnn);
      }
      return bean;
    }
    
    public FBeans getDocRecvByStatus(FDocAssign beanAssign,int obServer)throws EException
      {
         final String LOCATION = this + "->getDocRecvByStatus()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             result = dao.getDocRecvByStatus(conn,beanAssign,obServer);             
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
     
     
    public FMain getDocRecvByRead(FDocAssign beanAssign,int obServer,int checkWait,String statuses)throws EException
      {
         final String LOCATION = this + "->getDocRecvByRead()";
         FMain result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             result = dao.getDocRecvByRead(conn,beanAssign,obServer,statuses,checkWait);             
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
     
     
    
    public FBeans getTotalWaitSend(int userId)throws EException
      {
         final String LOCATION = this + "->getTotalWait()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             result = dao.getTotalWaitSend(conn,userId);             
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
    public FBeans getTotalWaitRecv(int userId)throws EException
      {
         final String LOCATION = this + "->getTotalWaitRecv()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             result = dao.getTotalWaitRecv(conn,userId);             
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
    
//    public FMain getAmountDocRecvView(long userId)throws EException
//      {
//         final String LOCATION = this + "->getAmountDocRecvView()";        
//         Connection conn = null;
//         FMain result = null;
//         try {
//             conn = DBConnector.getConnection();
//             DBConnector.startTransaction(conn);
//             DMain dao = new DMain(); 
//             result = dao.getAmountDocRecvView(conn,userId);             
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
     
    public void updateMinimize(String menuId,int userId,int minimize) throws EException
      {
         final String LOCATION = this + "->updateMinimize()";
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             dao.updateMinimize(conn,menuId,userId,minimize);             
             DBConnector.endTransaction(conn);            
          }catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } finally {
             DBConnector.closeConnection(conn);
         }
     }
     
    public void updateMinimizeContactNew(String menuId,int userId,int minimize) throws EException
      {
         final String LOCATION = this + "->updateMinimizeContactNew()";
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             dao.updateMinimizeContactNew(conn,menuId,userId,minimize);             
             DBConnector.endTransaction(conn);            
          }catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          } finally {
             DBConnector.closeConnection(conn);
         }
     }
     
     
    public FBeans getAmountOfStatus(int type,long userId,int obServer,String statusIds)throws EException
      {
         final String LOCATION = this + "->getAmountOfStatus()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             result = dao.getAmountOfStatus(conn,type,userId,obServer,statusIds);             
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
   
    public FMain getDocsendRead(int type,long userId,int obServer,int checkWaitSend, String status)throws EException
      {
         final String LOCATION = this + "->getDocsendRead()";
         FMain result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             result = dao.getDocsendRead(conn,type,userId,obServer,checkWaitSend,status);             
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
    public FBeans getPortletClose(long userId)throws EException
      {
         final String LOCATION = this + "->getPortletClose()";
         FBeans result = null;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DMain dao = new DMain(); 
             result = dao.getPortletClose(conn,userId);             
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
