package com.dao.template.templateType;


import com.dao.template.DSqlTemplate;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.template.templateType.FTemplateType;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DTemplateType extends DSqlTemplate
{
    public FBeans getAllTemplateType(Connection cnn) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllTemplateType()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_SELECT_ALL);       
        rs = prpstm.executeQuery();
        beans = new FBeans();
        FTemplateType beantemp = null;
        while((rs != null) && (rs.next()))
        {
          beantemp = new FTemplateType();
          beantemp.setId(rs.getInt(TEMPLATE_CATEGORY_ID));
          beantemp.setCode(rs.getString(TEMPLATE_CATEGORY_CODE));
          beantemp.setName(rs.getString(TEMPLATE_CATEGORY_NAME));
          beantemp.setDescription(rs.getString(TEMPLATE_CATEGORY_DESCRIPTION));
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
      FTemplateType bean = null;
      try
      {
        bean = (FTemplateType)seed;
        prstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_ADD_NEW);
        prstm.setString(PARAM_01,bean.getCode());
        prstm.setString(PARAM_02,bean.getName());
        prstm.setString(PARAM_03,bean.getDescription());
        prstm.execute();
        //Chua biet su dung cau lenh nao trong day
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
      FTemplateType bean = null;
      try
      {
        bean = (FTemplateType)seed;
        prstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_UPDATE);
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
      FTemplateType bean = (FTemplateType)seed;
      try
      {
        delete(cnn, TABLE_TEMPLATES, TEMPLATE_TEMPLATETYPE_ID + EQUAL + bean.getId());
        
        delete(cnn, TABLE_TEMPLATE_CATEGORY, TEMPLATE_CATEGORY_ID + EQUAL + bean.getId());
      }
      catch(EException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
    }  
    public boolean checkNameTemplateType(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>checkNameTemplateType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTemplateType bean = (FTemplateType)seed;
      try
      {
        bean = (FTemplateType)seed;
        prstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_CHECK_NAME);
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

    public boolean checkNameIdTemplateType(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>checkNameIdTemplateType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTemplateType bean = (FTemplateType)seed;
      try
      {
        bean = (FTemplateType)seed;
        prstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_CHECK_NAME_ID);
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

    public boolean checkCodeIdTemplateType(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>checkNameIdTemplateType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTemplateType bean = (FTemplateType)seed;
      try
      {
        bean = (FTemplateType)seed;
        prstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_CHECK_CODE_ID);
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
    
    public FTemplateType getTemplateTypeById(Connection cnn, FTemplateType bean) throws EException 
    {
      final String LOCATION = this.toString() + "getTemplateTypeById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FTemplateType beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_SELECT_BY_ID);
        prpstm.setInt(PARAM_01,bean.getId());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
          beantemp = new FTemplateType();
          beantemp.setId(rs.getInt(TEMPLATE_CATEGORY_ID));
          beantemp.setCode(rs.getString(TEMPLATE_CATEGORY_CODE));
          beantemp.setName(rs.getString(TEMPLATE_CATEGORY_NAME));
          beantemp.setDescription(rs.getString(TEMPLATE_CATEGORY_DESCRIPTION));
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
    public boolean checkTemplateTypeType(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkNamePositions()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTemplateType bean = (FTemplateType)seed;
      try
      {     
        prstm = cnn.prepareStatement(SQL_TEMPLATE_TYPE_CHECK_CODE);
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
