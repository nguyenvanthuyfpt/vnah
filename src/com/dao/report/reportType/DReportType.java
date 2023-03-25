package com.dao.report.reportType;


import com.dao.report.DSqlReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.report.reportType.FReportType;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DReportType extends DSqlReport
{
    public FBeans getAllReportType(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllReportType()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_REPORT_TYPE_SELECT_ALL);       
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FReportType beantemp = null;
        while((rs != null) && (rs.next()))
        {
          beantemp = new FReportType();
          beantemp.setId(rs.getInt(REPORT_CATEGORY_ID));
          beantemp.setCode(rs.getString(REPORT_CATEGORY_CODE));
          beantemp.setName(rs.getString(REPORT_CATEGORY_NAME));
          beantemp.setDescription(rs.getString(REPORT_CATEGORY_DESCRIPTION));
          beans.add(beantemp);
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
      final String LOCATION = this.toString() + "addNew()";
      boolean result = true;
      PreparedStatement prstm = null;
      FReportType bean = null;
      try
      {
        bean = (FReportType)seed;
        prstm = cnn.prepareStatement(SQL_REPORT_TYPE_ADD_NEW);
        prstm.setString(PARAM_01,bean.getCode());
        prstm.setString(PARAM_02,bean.getName());
        prstm.setString(PARAM_03,bean.getDescription());
        prstm.execute();
  
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        result= false;
      }
      finally
      {
        closePreparedStatement(prstm);
      }
      return result;
    }
    
    public void update(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      FReportType bean = null;
      try
      {
        bean = (FReportType)seed;
        prstm = cnn.prepareStatement(SQL_REPORT_TYPE_UPDATE);
        prstm.setString(PARAM_01,bean.getCode());
        prstm.setString(PARAM_02,bean.getName());
          prstm.setString(PARAM_03,bean.getDescription());
        prstm.setInt(PARAM_04,bean.getId());
        prstm.executeUpdate();
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        closePreparedStatement(prstm);
      }
    }
    
    public void delete(Connection cnn, FSeed seed)throws EException
    {
      final String LOCATION = this.toString() + "~~>delete()";
      FReportType bean = (FReportType)seed;
      try
      {
        delete(cnn, TABLE_REPORT_CATEGORY, REPORT_CATEGORY_ID + EQUAL + bean.getId());
      }
      catch(EException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
    }  
    public boolean checkNameReportType(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>checkNameReportType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReportType bean = (FReportType)seed;
      try
      {
        bean = (FReportType)seed;
        prstm = cnn.prepareStatement(SQL_REPORT_TYPE_CHECK_NAME);
        prstm.setString(PARAM_01,bean.getName());
        rs = prstm.executeQuery();
        result = (rs != null) && rs.next();
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
        result = true;
      }
      return result;
    }

    public boolean checkNameIdReportType(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>checkNameIdReportType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReportType bean = (FReportType)seed;
      try
      {
        bean = (FReportType)seed;
        prstm = cnn.prepareStatement(SQL_REPORT_TYPE_CHECK_NAME_ID);
        prstm.setString(PARAM_01,bean.getCode());
        prstm.setInt(PARAM_02,bean.getId());
        rs = prstm.executeQuery();
        result = (rs != null) && rs.next();
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
        result = true;
      }
      return result;
    }  

    public boolean checkCodeIdReportType(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>checkNameIdReportType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReportType bean = (FReportType)seed;
      try
      {
        bean = (FReportType)seed;
        prstm = cnn.prepareStatement(SQL_REPORT_TYPE_CHECK_CODE_ID);
        prstm.setString(PARAM_01,bean.getCode());
        prstm.setInt(PARAM_02,bean.getId());
        rs = prstm.executeQuery();
        result = (rs != null) && rs.next();
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
        result = true;
      }
      return result;
    }    
    
    public FReportType getReportTypeById(Connection cnn, FReportType bean) throws EException 
    {
      final String LOCATION = this.toString() + "getReportTypeById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FReportType beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_REPORT_TYPE_SELECT_BY_ID);
        prpstm.setInt(PARAM_01,bean.getId());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
          beantemp = new FReportType();
          beantemp.setId(rs.getInt(REPORT_CATEGORY_ID));
          beantemp.setCode(rs.getString(REPORT_CATEGORY_CODE));
          beantemp.setName(rs.getString(REPORT_CATEGORY_NAME));
            beantemp.setDescription(rs.getString(REPORT_CATEGORY_DESCRIPTION));
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
    public boolean checkCodeReportType(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkNamePositions()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FReportType bean = (FReportType)seed;
      try
      {     
        prstm = cnn.prepareStatement(SQL_REPORT_TYPE_CHECK_CODE);
        prstm.setString(PARAM_01,bean.getCode());
        rs = prstm.executeQuery();
        result = (rs != null) && rs.next();
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
        result = true;
      }
      return result;        
    }  
    
}
