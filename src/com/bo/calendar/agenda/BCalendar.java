package com.bo.calendar.agenda;


import com.dao.calendar.agenda.DCalendar;
import com.dao.connection.DBConnector;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.calendar.agenda.FAgenda;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;


public class BCalendar
{
  
    public FBeans getEventsTest(int userId, Date dayStart,int Days,int type,long departmentId) throws  EException
    {
      final String LOCATION = this.toString() + "~>getEventsTest()";
      Connection cnn = null;
      DCalendar dao = null;
      FBeans beans =new FBeans();
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DCalendar();
        beans = dao.getEventsTest(cnn,userId,dayStart,Days,type,departmentId);
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
    public FBeans getEventsByMonth(int userId, Date dayStart,int Days,int type,long departmentID) throws  EException
    {
      final String LOCATION = this.toString() + "~>getEventsByMonth()";
      Connection cnn = null;
      DCalendar dao = null;
      FBeans beans =new FBeans();
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DCalendar();
        beans = dao.getEventsByMonth(cnn,userId,dayStart,Days,type,departmentID);
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
    
    public boolean addNew(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->addNew()";
      boolean result = false;
      Connection conn = null;
      FAgenda bean = (FAgenda)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DCalendar dao = new DCalendar();   
           result=dao.addNew(conn,bean);
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
    
    public boolean update(FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "->update()";
      Connection conn = null;
      boolean result=false;
      FAgenda bean = (FAgenda)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DCalendar dao = new DCalendar();   
          result=dao.update(conn,bean);
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


    
    public FAgenda getById(int id) throws  EException
    {
     final String LOCATION = this.toString() + "~>getFormById()";
     Connection cnn = null;
     DCalendar dao = null;
     FAgenda beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       
       dao = new DCalendar();
       beantemp = dao.getById(cnn,id);    

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
     return beantemp;
    }
    
    public FAgenda getTop(long id) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTop()";
     Connection cnn = null;
     DCalendar dao = null;
     FAgenda beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       
       dao = new DCalendar();
       beantemp = dao.getTop(cnn,id);    

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
     return beantemp;
    }
    
    public boolean delete(int id) throws  EException
    {
      final String LOCATION = this.toString() + "->delete()";
      Connection conn = null;
      boolean result=true;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DCalendar dao = new DCalendar();  
           result= dao.delete(conn,id);
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
        public FBeans getCalendar(Date day,long userId,int type,long departmentId) throws  EException{
         final String LOCATION = this.toString() + "~>getCalendar()";
         Connection cnn = null;
         DCalendar dao = null;
         FBeans beans= null;
         try
         {      
           cnn = DBConnector.getConnection();
           DBConnector.startTransaction(cnn);
           dao = new DCalendar();
           beans= dao.getCalendar(cnn,day,userId,type,departmentId);    
           DBConnector.endTransaction(cnn);
         }catch (EException sqle){
           DBConnector.rollBackTransaction(cnn);
           if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally
         {
           DBConnector.closeConnection(cnn);
         }
         return beans;
        }
        

}
