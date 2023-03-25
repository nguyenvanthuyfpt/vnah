package com.dao.mycontact;


import com.dao.DSqlAdmin;
import com.dao.pgroups.DPgroups;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.login.FLoginSystem;
import com.form.mycontact.FMycontact;
import com.form.pgroups.FPgroup;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DMycontact  extends DSqlAdmin
{
   
    public boolean getUserInformation(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->getUserInformation()";
    boolean result = false;
    FLoginSystem bean = (FLoginSystem)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_USERS_LOGIN);         
          pstmt.setString(PARAM_01,bean.getUsername());
          pstmt.setString(PARAM_02,bean.getPassword());
          rs = pstmt.executeQuery();
          if(rs.next()){
            bean.setId(rs.getInt(USERS_USER_ID));
            bean.me.setId(bean.getId());
            bean.me.setUsername(rs.getString(USERS_USERNAME));
            bean.me.setPassword(rs.getString(USERS_PASSWORD));
            bean.me.setDepartmentID(rs.getInt(USERS_DEPARTMENT_ID));
            bean.me.setGroupID(rs.getInt(USERS_GROUP_ID));
            bean.me.setFullName(rs.getString(USERS_FULLNAME));                           
            bean.me.setPicture(rs.getString(USERS_PICTURE));
            bean.me.setAPP_ID(rs.getString(USERS_APP));
            bean.setActive(rs.getInt(USERS_ACTIVE));  
            int daysLive = rs.getInt(USERS_PERIOD);
            bean.setDaysLive(daysLive);
            if(daysLive>0){
                Date today = bean.getCurrentDate();
                Date lastvisit = rs.getDate(USERS_DATE_PASSWORD);
                if(lastvisit!=null){
                    daysLive-=bean.getDays(lastvisit,today);
                }
            }
            if(bean.getActive()>0 && (bean.getDaysLive()==0 || daysLive>0)){
              bean.me.setRole(rs.getInt(USERS_ROLE));
              bean.me.setPrivilege(rs.getInt(USERS_PRIVILEGE));
              bean.me.setTimeStart(new Timestamp(new Date().getTime()));     
              bean.me.setExtTagInt(daysLive);
            }
            if(bean.getDaysLive()>0) bean.setDaysLive(daysLive);
            result = true;
          }
     }
     catch (SQLException sqle) {            
         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
     }
     finally {
         closeResultSet(rs);
         closePreparedStatement(pstmt);        
     }
     return result;
    }
   
    public FMycontact getRecordById(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FMycontact bean =(FMycontact)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_MYCONTACT_BY_ID);
        ////.println(SQL_SELECT_USERS_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
        prstm.setLong(PARAM_02,bean.getUserId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = getInformation(cnn,rs);        
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
     
      boolean flag = false;
      PreparedStatement prstm = null;      
      try{                    
          List params = setParams(seed);              
          flag = execute(cnn,SQL_INSERT_MYCONTACT,params)>0;                            
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
          
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return flag;    
    }
 
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FMycontact bean = (FMycontact)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_UPDATE_MYCONTACT,params)>0;
     }
      catch(EException ex)
      {
       if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      FMycontact bean = (FMycontact)seed;     
      return  delete(cnn, TABLE_MYCONTACT, MYCONTACT_ID + EQUAL + bean.getId())>0;
    }    

  
    
    public FBeans getAllMycontactByPgroup(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getUserByGroupID()";
      FBeans beans = null;
      FMycontact bean = (FMycontact)seed;
      try
      {            
        String SQL=SQL_SELECT_MYCONTACT;       
          List params = new ArrayList();      
          params.add(bean.getUserId());
        if (bean.getPgroupId()>0){
            SQL+=SQL_SELECT_MYCONTACT_ADD_WHERE_PGROUP;
            params.add(bean.getPgroupId());
        }
        if (bean.getFullName()!=null && !bean.getFullName().equals("")){
            SQL+=SQL_SELECT_MYCONTACT_ADD_WHERE_FULLNAME;
            params.add(PER_CENT +  bean.getFullName() + PER_CENT);
        }     
        ////.println(SQL);
        beans = getMultiRecords(cnn,SQL,params,bean.getPageIndex());
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
      }
      return beans;        
    }
   
    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,List params,int pageIndex) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
          prstm = prepareStatement(cnn,SQL_SELECT,params);
          ////.println(SQL_SELECT);
          rs = prstm.executeQuery();
          beans = new FBeans();
          if(pageIndex>0){
              rs.last(); 
              beans.setTotalRows(rs.getRow());
              beans.setPageIndex(pageIndex);
              if(beans.getFirstRecord()<=1){
                  rs.beforeFirst();
              }else{
                  rs.absolute(beans.getFirstRecord()-1);
              }
          }
        int i=0;
        while((rs != null) && (rs.next()) && (pageIndex<=0 || (i<AppConfigs.APP_ROWS_VIEW)))
        {
            i++;           
            FMycontact beanTemp=new FMycontact();
            beanTemp.setId(rs.getInt(MYCONTACT_ID));
            beanTemp.setFullName(rs.getString(MYCONTACT_FULLNAME));
            beanTemp.setIcq(rs.getString(MYCONTACT_ICQ));
            beans.add(beanTemp);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    
    public FPgroup getAllContactByPGroup(Connection cnn,FPgroup bean) throws EException
    {
      final String LOCATION = this.toString() + "getAllMycontact()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {   
          List params = new ArrayList(); 
          params.add(bean.getUserId());
          params.add(bean.getId());
          prstm = prepareStatement(cnn,SQL_SELECT_ALL_MYCONTACT_BY_PGROUP + ORDER_BY + MYCONTACT_ID + DESC,params);
          //.println(SQL_SELECT_ALL_MYCONTACT_BY_PGROUP + ORDER_BY + MYCONTACT_ID);
          rs = prstm.executeQuery();
          beans = new FBeans();  
          FMycontact beanTemp = null;
          bean.setMycontacts(new FBeans());
        while((rs != null) && (rs.next()))
        {
            beanTemp = new FMycontact();
            beanTemp = getInformation(cnn,rs);            
            bean.getMycontacts().add(beanTemp);  
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
    
    public FBeans getViewMycontact(Connection cnn,long userId) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
          List params = new ArrayList();
          params.add(userId);
          prstm = prepareStatement(cnn,SQL_SELECT_PGROUPS,params);
          //.println(SQL_SELECT);
          rs = prstm.executeQuery();
          beans = new FBeans();         
          DPgroups daoG = new DPgroups();
          FPgroup beanTemp = null ;        
            while((rs != null) && (rs.next()))
            {
                beanTemp = new FPgroup();
                beanTemp = daoG.getInformation(rs)  ;  
                beanTemp.setMycontacts(new FBeans());                            
                beanTemp.getMycontacts().add(getAllContactByPGroup(cnn,beanTemp));                    
                beans.add(beanTemp);
            }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public FBeans searchList(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FMycontact bean=(FMycontact)seed;
      ResultSet rs = null;
      String SQL_SELECT=SQL_SELECT_MYCONTACT;
      //////.println(SQL_SELECT_MYCONTACT);
      try
      {
          List  params =new ArrayList();
          params.add(bean.getUserId());
          if(bean.getCc()!=null && !bean.getCc().equals("")){
              SQL_SELECT+=AND + OPEN +  MYCONTACT_ICQ + LIKE + QUESTION;
              params.add(PER_CENT + bean.getCc() + PER_CENT);
              SQL_SELECT+=OR + MYCONTACT_FULLNAME + LIKE + QUESTION + CLOSE;
              params.add(PER_CENT + bean.getCc() + PER_CENT);
          }
          if(bean.getToAddress()!=null && !bean.getToAddress().equals("")){
              SQL_SELECT+=AND + OPEN +  MYCONTACT_ICQ + LIKE + QUESTION;
              params.add(PER_CENT + bean.getToAddress() + PER_CENT);
              SQL_SELECT+=OR + MYCONTACT_FULLNAME + LIKE + QUESTION + CLOSE;
              params.add(PER_CENT + bean.getToAddress() + PER_CENT);
          }
          ////.println(SQL_SELECT);
          prstm = prepareStatement(cnn,SQL_SELECT,params);
          rs = prstm.executeQuery();
          
          beans = new FBeans();         
          while((rs != null) && (rs.next()))
            {
                bean= new FMycontact();
                bean.setId(rs.getInt(MYCONTACT_ID));
                bean.setFullName(rs.getString(MYCONTACT_FULLNAME));
                bean.setEmail(rs.getString(MYCONTACT_ICQ));
                
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
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    
    public FMycontact getInformation(Connection cnn, ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FMycontact beanTemp = new FMycontact();
         try {
           beanTemp.setId(rs.getInt(MYCONTACT_ID));
           beanTemp.setFullName(rs.getString(MYCONTACT_FULLNAME));                                      
           beanTemp.setEmail(rs.getString(MYCONTACT_EMAIL));                           
           beanTemp.setPhone(rs.getString(MYCONTACT_PHONE));
           beanTemp.setAddress(rs.getString(MYCONTACT_ADDRESS));
           beanTemp.setDescription(rs.getString(MYCONTACT_DESCRIPTION));
           beanTemp.setDateCreate(beanTemp.dateToString(rs.getDate(MYCONTACT_DATE_CREATE)));
           beanTemp.setPgroupId(rs.getInt(MYCONTACT_PGROUP_ID));
           beanTemp.setIcq(rs.getString(MYCONTACT_ICQ));
           beanTemp.setYm(rs.getString(MYCONTACT_YM));
           beanTemp.setMsn(rs.getString(MYCONTACT_MSN));
           beanTemp.setGtalk(rs.getString(MYCONTACT_GTALK));
           beanTemp.setSkype(rs.getString(MYCONTACT_SKYPE));
           beanTemp.setUserId(rs.getLong(MYCONTACT_USER_ID));
           beanTemp.setActive(rs.getInt(MYCONTACT_ACTIVE));
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
        FMycontact bean = (FMycontact)seed;
        List params = new ArrayList();
         try {          
             params.add(bean.getFullName());    
             params.add(bean.getEmail());                           
             params.add(bean.getPhone());
             params.add(bean.getAddress());                           
             params.add(bean.getDescription());
             params.add(bean.getCurrentSqlDate());
             params.add(bean.getPgroupId());
             params.add(bean.getIcq());
             params.add(bean.getYm());
             params.add(bean.getMsn());
             params.add(bean.getGtalk());
             params.add(bean.getSkype());
             params.add(bean.getUserId());
             params.add(bean.getActive());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
}
