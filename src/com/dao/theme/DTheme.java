package com.dao.theme;


import com.dao.DSql;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.theme.FTheme;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DTheme extends DSql{
    
    public FTheme getById(Connection cnn, FTheme bean) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FTheme beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_THEME_BY_ID);
        prpstm.setInt(PARAM_01,bean.getId());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
          beantemp = new FTheme();
            beantemp.setId(rs.getInt(THEME_ID));
            beantemp.setTitle(rs.getString(THEME_TITLE));
            beantemp.setPathImages(rs.getString(THEME_PATHIMAGES));             
            beantemp.setPathStyle(rs.getString(THEME_PATHSTYLE));             
            beantemp.setActive(rs.getInt(THEME_ACTIVE));             
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
    public String[] getTopActive(Connection cnn) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      String[] theme={"",""};
      try
      {
        prpstm = cnn.prepareStatement(SQP_THEME_TOP_ACTIVE);
        prpstm.setInt(PARAM_01,1);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            theme[0]=rs.getString(THEME_PATHIMAGES);
            theme[1]=rs.getString(THEME_PATHSTYLE);
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
      return theme;    
    } 
    
    public FBeans getAll(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAll()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_THEME_SELECT_ALL);       
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FTheme beantemp = null;
        while((rs != null) && (rs.next()))
        {
          beantemp = new FTheme();
            beantemp.setId(rs.getInt(THEME_ID));
            beantemp.setTitle(rs.getString(THEME_TITLE));
            beantemp.setPathImages(rs.getString(THEME_PATHIMAGES));             
            beantemp.setPathStyle(rs.getString(THEME_PATHSTYLE));             
            beantemp.setActive(rs.getInt(THEME_ACTIVE));             
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
    public FBeans getAllActive(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAll()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try
      {
        prpstm = cnn.prepareStatement(SELECT + STAR + FROM + TABLE_THEME + WHERE + THEME_ACTIVE + EQUAL + 1);       
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FTheme beantemp = null;
        while((rs != null) && (rs.next()))
        {
          beantemp = new FTheme();
            beantemp.setId(rs.getInt(THEME_ID));
            beantemp.setTitle(rs.getString(THEME_TITLE));
            beantemp.setPathImages(rs.getString(THEME_PATHIMAGES));             
            beantemp.setPathStyle(rs.getString(THEME_PATHSTYLE));             
            beantemp.setActive(rs.getInt(THEME_ACTIVE));             
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
      boolean result = false;
      PreparedStatement prstm = null;
      FTheme bean = (FTheme)seed;    
      try
      {
        List params = new ArrayList();
        params.add(bean.getTitle()) ;
        params.add(bean.getPathImages()); 
          params.add(bean.getPathStyle()); 
        params.add(bean.getActive()); 
        result = execute(cnn,SQL_THEME_ADD_NEW,params)>0;
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
    
    public boolean update(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      FTheme bean = (FTheme)seed;    
      boolean result =false;
      try
      {
          List params = new ArrayList();          
          params.add(bean.getTitle()) ;
          params.add(bean.getPathImages()); 
          params.add(bean.getPathStyle()); 
          params.add(bean.getActive()); 
          params.add(bean.getId()) ; 
          result = execute(cnn,SQL_THEME_UPDATE,params)>0;       
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
    
    public boolean delete(Connection cnn, FSeed seed)throws EException
    {
      final String LOCATION = this.toString() + "~~>delete()";
      FTheme bean = (FTheme)seed;    
      boolean result = false;
      try
      {
        result =delete(cnn, TABLE_RM_CATEGORY, THEME_ID + EQUAL + bean.getId())>0;
      }
      catch(EException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      return result;
    }  
  
}
