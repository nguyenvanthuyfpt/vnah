package com.dao.pgroups;


import com.dao.DSqlAdmin;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.pgroups.FPgroup;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DPgroups  extends DSqlAdmin
{
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FPgroup bean = (FPgroup)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_SELECT_PGROUPS_INFORMATION);         
          pstmt.setString(PARAM_01,bean.getName());
          pstmt.setInt(PARAM_02,bean.getId());
          pstmt.setLong(PARAM_03,bean.getUserId());
          rs = pstmt.executeQuery();
          result = rs!=null && rs.next();
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
    
    public FPgroup getRecordById(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FPgroup bean = new FPgroup();
      bean = (FPgroup)seed;
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_PGROUPS_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean = getInformation(rs);
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
      Boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          FPgroup bean = (FPgroup)seed;
          List params = setParams(seed);
          result = execute(cnn,SQL_INSERT_PGROUP,params)>0;         
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
    
    public boolean update(Connection cnn, FSeed seed) throws  SQLException,EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FPgroup bean = (FPgroup)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_UPDATE_PGROUPS,params)>0;       
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + DELETE;
      FPgroup bean = (FPgroup)seed;
      delete(cnn, TABLE_MYCONTACT, MYCONTACT_PGROUP_ID + EQUAL + bean.getId());
      return 0 < delete(cnn, TABLE_PGROUPS, PGROUPS_ID + EQUAL + bean.getId());
    }    

    public FBeans getMultiRecords(Connection cnn,String SQL_SELECT,long userId) throws EException
    {
      final String LOCATION = this.toString() + "getMultiRecords()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try
      {
        List params = new ArrayList();
        params.add(userId);
        prstm = prepareStatement(cnn,SQL_SELECT,params);
        rs = prstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            FPgroup group = new FPgroup();
            group = getInformation(rs);  
            beans.add(group);
           

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
    
    
    public FPgroup getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FPgroup pgroup = new FPgroup();
         try {
           pgroup.setId(rs.getInt(PGROUPS_ID));          
           pgroup.setName(rs.getString(GROUPS_NAME));          
           pgroup.setDescription(rs.getString(PGROUPS_DESCRIPTION));
           pgroup.setDateCreate(pgroup.dateToString(rs.getDate(PGROUPS_DATE_CREATE)));
           pgroup.setUserId(rs.getLong(PGROUPS_USER_ID));   
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return pgroup;
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FPgroup bean = (FPgroup)seed;
        List params = new ArrayList();
         try {           
             params.add(bean.getName());
             params.add(bean.getParentId());                           
             params.add(bean.getDescription());
             params.add(bean.stringToSqlDate(bean.getDateCreate()));
             params.add(bean.getUserId());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }

}
