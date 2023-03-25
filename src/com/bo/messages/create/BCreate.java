package com.bo.messages.create;


import com.dao.connection.DBConnector;
import com.dao.mail.SendMail;
import com.dao.messages.create.DCreate;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.mail.FMailAccount;
import com.form.messages.create.FCreate;

import com.inf.messages.IConstantsMessages;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.SQLException;

public class BCreate
{  
    public boolean delete(FSeed seed)throws EException,SQLException {
        final String LOCATION = this + "->delete()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            DCreate dao = new DCreate();          
            result = dao.delete(conn,seed);
            DBConnector.endTransaction(conn);            
        }
        catch (EException  ex) {
        
            DBConnector.rollBackTransaction(conn);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
        }
        finally {
            DBConnector.closeConnection(conn);
        }
        return result;    
     }
     
     public boolean insert(FSeed seed)throws EException,SQLException{
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCreate dao = new DCreate();          
             result = dao.insert(conn,seed);
             DBConnector.endTransaction(conn);            
          }
          catch (EException  ex) {
              DBConnector.rollBackTransaction(conn);
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
            }
          finally {
               DBConnector.closeConnection(conn);
         }
        return result;    
     }
  
     
    public boolean updateRemove(FSeed seed)throws EException,SQLException {
    
         final String LOCATION = this + "->insert()";
         boolean result = false;
         Connection conn = null;
         FCreate bean = (FCreate)seed;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCreate dao = new DCreate(); 
             if (bean.getType() != IConstantsMessages.STATUS_SEND_REV_DEL[2] ){
                result = dao.updateRemove(conn,seed);
                 result = dao.delete(conn,seed);
             }else{
                 if(bean.getCheckEmp()!=null){
                    result = dao.delete(conn,seed);
                 }
             }
             DBConnector.endTransaction(conn);            
          }
          catch (EException  ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
         return result;    
     }
     
    public boolean updateUnRemove(FSeed seed)throws EException,SQLException {
    
         final String LOCATION = this + "->updateUnRemove()";
         boolean result = false;
         Connection conn = null;       
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCreate dao = new DCreate();             
             result = dao.updateUnRemove(conn,seed);             
             DBConnector.endTransaction(conn);            
          }
          catch (EException  ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
         return result;    
     }
     
    public FBeans getAllMessRecByUserId(FSeed seed, int userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllMessRecByUserId()";
      Connection cnn = null;
      DCreate dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DCreate();
        beans = dao.getAllMessRecByUserId(cnn,seed,userId);
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
      return beans;
    }
    
    public FCreate getAllAmount(FSeed seed, int userId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllAmount()";
      Connection cnn = null;
      DCreate dao = null;
      FCreate bean = new FCreate();    
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DCreate();
        bean = dao.getAllAmount(cnn,seed,userId);
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

    public FBeans getAllDepartment() throws  EException
    {
      final String LOCATION = this.toString() + "~>getAllDepartment()";
      Connection cnn = null;
      DCreate dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DCreate();
        beans = dao.getAllDepartments(cnn);
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
      return beans;
    }

    public FBeans getUserByDepartmentId(FSeed seed,int departmentId) throws  EException{
      final String LOCATION = this.toString() + "~>getUserByDepartmentId()";
      Connection cnn = null;
      DCreate dao = null;
      FBeans beans = null;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DCreate();
        beans = dao.getUserByDepartmentId(cnn,seed,departmentId);                          
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
      return beans;
    }
    
    public FBeans getUserByInUserId(FSeed seed,int userId) throws  EException{
      final String LOCATION = this.toString() + "~>getUserByInUserId()";
      Connection cnn = null;
      DCreate dao = null;
      FBeans beans = null;
      FCreate bean = (FCreate)seed;
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);        
        dao = new DCreate();
        if (bean.getEmps()!=null){
            beans = dao.getUserByInUserId(cnn,seed,userId);                          
        }else{
            beans = dao.getUserByMessageId(cnn,seed,userId);                          
        }
        
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
      return beans;
    }
    
   
  
   public FCreate getRecordByID(FSeed seed,int userId)throws EException,SQLException
     {
        final String LOCATION = this + "->getRecordByID()";
        FCreate result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
            DBConnector.startTransaction(conn);
            DCreate dao = new DCreate();          
            result = dao.getRecordById(conn,seed,userId);
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
   
    public int getTopId(long userId)throws EException,SQLException
      {
         final String LOCATION = this + "->getTopId()";
         int id=0;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection(AppConfigs.ADMIN_CONNECTION_ID);
             DBConnector.startTransaction(conn);
             DCreate dao = new DCreate();          
             id = dao.getTopId(conn,userId);
             DBConnector.endTransaction(conn);            
          }catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return id;    
     }
   
    public boolean sendMail(String fullName,FMailAccount beanEmail,String Subject,String Content,String recipient,String[] attachments,String[] files)throws EException,SQLException{
            boolean result=false;
            try  {
                result=new SendMail().sendmailFromDoc(fullName,beanEmail,Subject,Content,recipient,attachments,files);
            } catch (Exception ex)  {
                ex.printStackTrace();
            }
            return result;
        }

}
