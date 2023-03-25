package com.bo.calendarMeeting.agenda;


import com.dao.calendarMeeting.DCalendarMeeting;
import com.dao.connection.DBConnector;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.calendarMeeting.agenda.FAgendaMeeting;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;


public class BCalendarMeeting
{
    public FBeans getEventsTest(int userId, Date dayStart,int Days) throws  EException
    {
      final String LOCATION = this.toString() + "~>getEventsTest()";
      Connection cnn = null;
      DCalendarMeeting dao = null;
      FBeans beans =new FBeans();
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DCalendarMeeting();
        beans = dao.getEventsTest(cnn,userId,dayStart,Days);
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
    public FBeans getEventsByMonth(int userId, Date dayStart,int Days) throws  EException
    {
      final String LOCATION = this.toString() + "~>getEventsByMonth()";
      Connection cnn = null;
      DCalendarMeeting dao = null;
      FBeans beans =new FBeans();
      try
      {      
        cnn = DBConnector.getConnection();
        DBConnector.startTransaction(cnn);
        dao = new DCalendarMeeting();
        beans = dao.getEventsByMonth(cnn,userId,dayStart,Days);
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
      FAgendaMeeting bean = (FAgendaMeeting)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DCalendarMeeting dao = new DCalendarMeeting();   
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
      FAgendaMeeting bean = (FAgendaMeeting)seed;
      try 
      {
          conn = DBConnector.getConnection();
          DBConnector.startTransaction(conn);
          DCalendarMeeting dao = new DCalendarMeeting();   
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


    
    public FAgendaMeeting getById(int id) throws  EException
    {
     final String LOCATION = this.toString() + "~>getFormById()";
     Connection cnn = null;
     DCalendarMeeting dao = null;
     FAgendaMeeting beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       
       dao = new DCalendarMeeting();
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
    
    public FAgendaMeeting getTop(long id) throws  EException
    {
     final String LOCATION = this.toString() + "~>getTop()";
     Connection cnn = null;
     DCalendarMeeting dao = null;
     FAgendaMeeting beantemp = null;
     try
     {      
       cnn = DBConnector.getConnection();
       DBConnector.startTransaction(cnn);
       
       dao = new DCalendarMeeting();
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
          DCalendarMeeting dao = new DCalendarMeeting();  
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
         DCalendarMeeting dao = null;
         FBeans beans= null;
         try
         {      
           cnn = DBConnector.getConnection();
           DBConnector.startTransaction(cnn);
           dao = new DCalendarMeeting();
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
