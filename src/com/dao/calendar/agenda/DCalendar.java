package com.dao.calendar.agenda;


import com.dao.calendar.DCalendarLib;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.calendar.agenda.FAgenda;

import com.inf.agenda.IKeyAgenda;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DCalendar extends DSqlMyagenda
{
    public FAgenda[] getCalByDay(Connection cnn,int userId, Date dayStart,int Days,int type,long departmentId) throws EException{
        final String LOCATION = this.toString() + "~~>getEvents()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FAgenda bean = new FAgenda();
        FAgenda beanTemp = new FAgenda();
        String[] timeValues=IKeyAgenda.CALENDAR_TIMES_VALUES;
        FAgenda[] events = new FAgenda[timeValues.length];
        String SQL_SELECT=SQL_SELECT_ALL_CALENDAR_FROMDATE_TO_TODATE;
        try
        {
            List params =new ArrayList();
            params.add(dayStart);
            params.add(bean.addDays(dayStart,Days));
            params.add(type);
            if(type==2){//phong
                SQL_SELECT+= AND  + TABLE_CALENDAR + STOP + CALENDA_DEPARTMENT_ID + EQUAL + QUESTION;
                params.add(departmentId);
            }else if(type==0){//ca nhan
                SQL_SELECT+= AND  + TABLE_CALENDAR + STOP + CALENDA_USER_ID + EQUAL + QUESTION;
                params.add(userId);
            }
            prpstm = prepareStatement(cnn,SQL_SELECT,params);
            rs = prpstm.executeQuery();
            while((rs != null) && (rs.next()))
            {
                beanTemp = new FAgenda();
                beanTemp.setEventId(rs.getInt(CALENDA_ID));
                beanTemp.setWhat(rs.getString(CALENDA_WHAT));
                beanTemp.setWhere(rs.getString(CALENDA_WHERE));
                beanTemp.setTimeEvent(bean.dateToString(rs.getDate(CALENDA_TIMEEVENT)));
                beanTemp.setTimeCreate(bean.dateToString(rs.getDate(CALENDA_TIMECREATE)));
                beanTemp.setTimes(rs.getString(CALENDA_FROM_TIME));
                beanTemp.setToTimes(rs.getString(CALENDA_TO_TIME));
                beanTemp.setUserCreate(rs.getInt(CALENDA_USER_ID));
                beanTemp.setType(rs.getInt(CALENDA_TYPE));
                int indexTime=0;
                for(int i=0;i<timeValues.length;i++){
                    if(timeValues[i].equals(beanTemp.getTimes())) {
                        indexTime=i;
                    }
                }
                events[indexTime] = beanTemp;
            }
        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
          closeResultSet(rs);
          closePreparedStatement(prpstm);
        }
        return events;
    }
    
    
    public FBeans getEventsTest(Connection cnn,int userId, Date dayStart,int Days,int type,long departmentId) throws EException{
        FBeans beans=new FBeans();
        FAgenda bean = new FAgenda();
        String[] timeValues=IKeyAgenda.CALENDAR_TIMES_VALUES;
        FAgenda[] events = new FAgenda[timeValues.length];
        DCalendarLib cal = new DCalendarLib();
       try  {
           for(int i=0;i<Days;i++){
                   bean = new FAgenda();
                   bean.setDay(bean.addDays(dayStart,i));
//                   //.println(bean.getDay());
                   bean.setDayId(cal.convertDateToID(bean.getDay()));
                   bean.setTimeEvent(bean.dateToString(bean.getDay()));
                   FAgenda beanTemp =new FAgenda();
                   events=getCalByDay(cnn,userId,bean.getDay(),1,type,departmentId);
                   for(int j=0;j<timeValues.length;j++){
                       if(events[j]!=null && timeValues[j].equals(events[j].getTimes())){
                           bean.getEventsInDay().add(events[j]);
                       }else{    
                           beanTemp =new FAgenda();
                           beanTemp.setTimes(timeValues[j]);
                           beanTemp.setToTimes(timeValues[j]);
                           beanTemp.setTimeEvent(bean.dateToString(bean.getDay()));
                           beanTemp.setWhat("");
                           bean.getEventsInDay().add(beanTemp);
                       }
                   }
                   beans.add(bean);
           }
       } catch (Exception ex)  {
           ex.printStackTrace();
       } finally  {
       }
       
        return beans;
    }
    
    public FBeans getEventsByMonth(Connection cnn,int userId, Date dayStart,int Days,int type,long departmentID) throws EException{
        FBeans beans=new FBeans();
        FAgenda bean = new FAgenda();
        DCalendarLib calen=new DCalendarLib();
        try  {
           for(int i=0;i<Days;i++){
               bean = new FAgenda();
               bean.setDay(bean.addDays(dayStart,i));
               bean.setDayId(calen.convertDateToID(bean.getDay()));
               bean.setTimeEvent(bean.dateToString(bean.getDay()));
               bean.setEventsInDay(getCalByDayMonth(cnn,userId,bean.getDay(),1,type,departmentID));
               beans.add(bean);
           }
        } catch (Exception ex)  {
           ex.printStackTrace();
        } finally  {
        }
        
        return beans;
    }
    
    public FBeans getCalByDayMonth(Connection cnn,int userId, Date dayStart,int Days,int type,long departmentId) throws EException{
        final String LOCATION = this.toString() + "~~>getEvents()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans =new FBeans();
        FAgenda bean = new FAgenda();
        String SQL_SELECT=SQL_SELECT_ALL_CALENDAR_FROMDATE_TO_TODATE;
        String SQL_ORDER= ORDER_BY + CALENDA_TIMEEVENT + COMMA + CALENDA_FROM_TIME;
        try
        {
            List params =new ArrayList();
            params.add(dayStart);
            params.add(bean.addDays(dayStart,Days));
            params.add(type);
            if(type==0){
                SQL_SELECT+=AND + TABLE_CALENDAR + STOP + CALENDA_USER_ID + EQUAL + QUESTION;
                params.add(userId);
            }else if(type==2){
                SQL_SELECT+=AND + TABLE_CALENDAR + STOP + CALENDA_DEPARTMENT_ID + EQUAL + QUESTION;
                params.add(departmentId);
            }
            prpstm =prepareStatement(cnn,SQL_SELECT+SQL_ORDER,params);
            rs = prpstm.executeQuery();
            while((rs != null) && (rs.next()))
            {
                bean= new FAgenda();
                bean.setEventId(rs.getInt(CALENDA_ID));
                bean.setWhat(rs.getString(CALENDA_WHAT));
                bean.setWhere(rs.getString(CALENDA_WHERE));
                bean.setTimeEvent(bean.dateToString(rs.getDate(CALENDA_TIMEEVENT)));
                bean.setTimeCreate(bean.dateToString(rs.getDate(CALENDA_TIMECREATE)));
                bean.setTimes(rs.getString(CALENDA_FROM_TIME));
                bean.setToTimes(rs.getString(CALENDA_TO_TIME));
                bean.setUserCreate(rs.getInt(CALENDA_USER_ID));
                bean.setType(rs.getInt(CALENDA_TYPE));
                beans.add(bean);
            }
        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }
        finally
        {
          closeResultSet(rs);
          closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public boolean addNew(Connection cnn, FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try
        {
            List params = setParams(seed);
            result = prepareStatement(cnn,SQL_CALENDAR_ADD_NEW,params).executeUpdate()>0;
        }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
        finally
        {
          closePreparedStatement(prstm);
        }
      return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws  EException{
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try
            {
            FAgenda bean=(FAgenda)seed;
            List params = setParams(seed);
            params.add(bean.getEventId());
            prstm = prepareStatement(cnn,SQL_CALENDAR_UPDATE,params);
            result = prstm.executeUpdate()>0;
        }
        catch(SQLException sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }
        finally
        {
          closePreparedStatement(prstm);
        }
      return result;
    }
   
    public FAgenda getById(Connection cnn, int id) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FAgenda beantemp =new FAgenda();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_CALENDAR_BY_ID);
        prpstm.setInt(PARAM_01,id);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beantemp=getInformation(rs);
            
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beantemp;    
    } 
    
    public FAgenda getTop(Connection cnn,long meId) throws EException 
    {
      final String LOCATION = this.toString() + "getTop()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FAgenda beantemp =new FAgenda();
      try
      {
        prpstm = cnn.prepareStatement(SELECT + STAR + FROM + TABLE_CALENDAR + WHERE + CALENDA_ID + EQUAL + OPEN + SELECT +  MAX + OPEN + CALENDA_ID + CLOSE + FROM + TABLE_CALENDAR + WHERE + CALENDA_USER_ID + EQUAL + QUESTION + CLOSE);
        prpstm.setLong(PARAM_01,meId);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beantemp=getInformation(rs);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beantemp;    
    } 
    
    
    public FAgenda getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FAgenda beanTemp = new FAgenda();
         try {
             beanTemp.setEventId(rs.getInt(CALENDA_ID));
             beanTemp.setWhat(rs.getString(CALENDA_WHAT));
             beanTemp.setWhere(rs.getString(CALENDA_WHERE));
             beanTemp.setTimeEvent(beanTemp.dateToString(rs.getDate(CALENDA_TIMEEVENT)));
             beanTemp.setTimeCreate(rs.getString(CALENDA_TIMECREATE));
             beanTemp.setTimes(rs.getString(CALENDA_FROM_TIME));
             beanTemp.setToTimes(rs.getString(CALENDA_TO_TIME));
             beanTemp.setUserCreate(rs.getInt(CALENDA_USER_ID));
             beanTemp.setType(rs.getInt(CALENDA_TYPE));
             beanTemp.setDepartmentId(rs.getInt(CALENDA_DEPARTMENT_ID));
             
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beanTemp;
    }
      public List setParams(FSeed seed) throws EException
      {
          final String LOCATION = "->setParams()";
          FAgenda bean = (FAgenda)seed;
          List params = new ArrayList();
           try {
               params.add(bean.getWhat());   
               params.add(bean.getWhere());   
               params.add(bean.stringToSqlDate(bean.getTimeEvent()));   
               params.add(bean.getCurrentSqlDate()); 
               params.add(bean.getTimes()); 
               params.add(bean.getToTimes()); 
               params.add(bean.getUserCreate()); 
               params.add(bean.getType());
               params.add(bean.getDepartmentId());
           }
           catch (Exception exp) {            
               if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
           }
           finally {
           }
           return params;
      }
     
    public boolean delete(Connection cnn,int id)throws EException
    {
    return delete(cnn, TABLE_CALENDAR, CALENDA_ID + EQUAL + id)>0;
    }  
   
   
    public FBeans getCalendar(Connection cnn,Date day,long userId,int type,long departmentId) throws EException 
    {
      final String LOCATION = this.toString() + "getCalendar()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FAgenda beantemp =new FAgenda();
      FBeans beans =new FBeans();
      String SQL_SELECT=SQL_SELECT_CALENDA_AT_MAIN;
      String SQL_ORDER=ORDER_BY + CALENDA_TIMEEVENT + COMMA + CALENDA_FROM_TIME;
      
      try
      {
        List params = new ArrayList();
        params.add(day);
        params.add(beantemp.addDays(day,IKeyAgenda.CALENDAR_VIEW_DAYS));
        params.add(type);
        if(type==0){//CA NHAN
            SQL_SELECT+= AND + CALENDA_USER_ID + EQUAL + QUESTION;
            params.add(userId);
        }else if(type==2){//PHONG
            SQL_SELECT+=AND + CALENDA_DEPARTMENT_ID + EQUAL + QUESTION;
            params.add(departmentId);
        }
        
        prpstm = prepareStatement(cnn,SQL_SELECT + SQL_ORDER,params);
        rs = prpstm.executeQuery();
        while((rs != null) && (rs.next())){
            beans.add(getInformation(rs));
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;    
    } 
 
}
