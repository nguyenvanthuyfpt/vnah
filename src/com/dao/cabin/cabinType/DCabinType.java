package com.dao.cabin.cabinType;


import com.dao.cabin.DSqlCabin;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.cabin.cabinType.FCabinType;

import com.inf.cabin.IKeyCabin;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DCabinType extends DSqlCabin
{
    
    public String getAllByParentId(Connection cnn,int cabinTypeId) throws EException
    {
      final String LOCATION = this.toString() + "getMembers()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String cabinTypeIds=cabinTypeId+"";
      try
      {
            prstm = cnn.prepareStatement(SELECT + CABINTYPE_ID + FROM + TABLE_CABINTYPE + WHERE +CABINTYPE_PARENT_ID + EQUAL + QUESTION);
            prstm.setInt(PARAM_01,cabinTypeId);
            rs = prstm.executeQuery();
            while((rs != null) && (rs.next())){
                  cabinTypeIds +=","+rs.getInt(PARAM_01);
            }
      }catch(SQLException sqle)
          {
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
          }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return cabinTypeIds;
    }
    
  public FBeans getAll(Connection cnn,long meId,int type,int depId) throws EException
  {
    final String LOCATION = this.toString() + "~~>getAll()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FBeans beans = null;
    String SELECT_SQL=SQL_SELECT_ALL_CABINTYPE;
    String SQL_WHERE=WHERE + TRUE;
    try
    {
      List params =new ArrayList();
      
      if(type==IKeyCabin.CABIN_PRIVATE){
          params.add(meId);
          params.add(type);
          SQL_WHERE+=AND + CABINTYPE_USER_ID + EQUAL + QUESTION + AND + CABINTYPE_TYPE + EQUAL + QUESTION ;
      }else if(type==IKeyCabin.CABIN_PUBLIC){
          
          params.add(IKeyCabin.CABIN_PUBLIC);
          SQL_WHERE+=AND + CABINTYPE_TYPE + EQUAL + QUESTION;
      }else if(type==IKeyCabin.CABIN_SHARE){
          params.add(meId);
          params.add(IKeyCabin.CABIN_PRIVATE);
          SQL_WHERE+=AND + CABINTYPE_USER_ID + EQUAL + QUESTION + AND + CABINTYPE_TYPE + EQUAL + QUESTION ;
      }else{
          
          params.add(IKeyCabin.CABIN_DEPARTMENT);
          params.add(depId);
          SQL_WHERE+=AND + CABINTYPE_TYPE + EQUAL + QUESTION;
          SQL_WHERE+=AND + CABINTYPE_DEPARTMENT_ID + EQUAL + QUESTION;
      }
      
      SELECT_SQL+=SQL_WHERE;
//      //.println(SELECT_SQL);
      prpstm =prepareStatement(cnn,SELECT_SQL,params);     
      rs = prpstm.executeQuery();
      beans = new FBeans();
      FCabinType beantemp = null;
      while((rs != null) && (rs.next()))
      {
        beantemp = new FCabinType();
                beantemp.setId(rs.getInt(CABINTYPE_ID));
                beantemp.setParentID(rs.getInt(CABINTYPE_PARENT_ID));
                beantemp.setUserId(rs.getInt(CABINTYPE_USER_ID));
                beantemp.setCode(rs.getString(CABINTYPE_CODE));
                beantemp.setName(rs.getString(CABINTYPE_NAME));
                beantemp.setDescription(rs.getString(CABINTYPE_DESCRIPTION));
                beantemp.setType(rs.getInt(CABINTYPE_TYPE));
                beantemp.setDepartmentId(rs.getInt(CABINTYPE_DEPARTMENT_ID));
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
    FCabinType bean = null;
    try
    {
      bean = (FCabinType)seed;
      List params = new ArrayList();
        params.add(bean.getParentID());
        params.add(bean.getUserId());
        params.add(bean.getCode());
        params.add(bean.getName());
        params.add(bean.getDescription());
        params.add(bean.getType());
        params.add(bean.getDepartmentId());
      prstm = prepareStatement(cnn,SQL_CABINTYPE_ADD_NEW,params);
      
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
    public boolean isExistUpdate(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FCabinType bean = (FCabinType)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_CABINTYPE_CHECK_CODE_UPDATE);  
          pstmt.setInt(PARAM_01,bean.getId());
         pstmt.setString(PARAM_02,bean.getCode());
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
  public boolean update(Connection cnn, FSeed seed) throws  EException
  {
    final String LOCATION = this.toString() + UPDATE;
    PreparedStatement prstm = null;
    FCabinType bean = (FCabinType)seed;
    boolean retult=false;
    try
    {
      List params =new ArrayList();
        params.add(bean.getParentID());
        params.add(bean.getUserId());
        params.add(bean.getCode());
        params.add(bean.getName());
        params.add(bean.getDescription());
        params.add(bean.getType());
        params.add(bean.getDepartmentId());
        params.add(bean.getId());
      prstm = prepareStatement(cnn,SQL_CABINTYPE_UPDATE,params);
      retult=prstm.executeUpdate()>0;
    }
    catch(Exception sqle)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
    }
    finally
    {
      closePreparedStatement(prstm);
    }
    return retult;
  }
     
  public boolean delete(Connection cnn, FSeed seed)throws EException
  {
    FCabinType bean = (FCabinType)seed;
    return delete(cnn, TABLE_CABINTYPE, CABINTYPE_ID + EQUAL + bean.getId())>0;
  }  

  
  public FCabinType getCabinTypeById(Connection cnn, FCabinType bean) throws EException 
  {
    final String LOCATION = this.toString() + "getCabinTypeById()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FCabinType beantemp = null;
    try
    {
      prpstm = cnn.prepareStatement(SQL_CABINTYPE_SELECT_CABINTYPE_BY_ID);
      prpstm.setInt(PARAM_01,bean.getId());
      rs = prpstm.executeQuery();
      if((rs != null) && (rs.next()))
      {
        beantemp = new FCabinType();
            beantemp.setId(rs.getInt(CABINTYPE_ID));
            beantemp.setParentID(rs.getInt(CABINTYPE_PARENT_ID));
            beantemp.setUserId(rs.getInt(CABINTYPE_USER_ID));
            beantemp.setCode(rs.getString(CABINTYPE_CODE));
            beantemp.setName(rs.getString(CABINTYPE_NAME));
            beantemp.setDescription(rs.getString(CABINTYPE_DESCRIPTION));
            beantemp.setType(rs.getInt(CABINTYPE_TYPE));
          beantemp.setDepartmentId(rs.getInt(CABINTYPE_DEPARTMENT_ID));
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
    public FBeans getAllCabinTypeByType(Connection cnn, int type,long meId) throws EException 
    {
      final String LOCATION = this.toString() + "getCabinTypeByType()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FCabinType beantemp = null;
      FBeans beans =null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_CABINTYPE_SELECT_CABINTYPE_BY_TYPE);
        prpstm.setInt(PARAM_01,type);
        prpstm.setLong(PARAM_02,meId);
        rs = prpstm.executeQuery();
        beans=new FBeans();
        while((rs != null) && (rs.next()))
        {
              beantemp = new FCabinType();
              beantemp.setId(rs.getInt(CABINTYPE_ID));
              beantemp.setParentID(rs.getInt(CABINTYPE_PARENT_ID));
              beantemp.setUserId(rs.getInt(CABINTYPE_USER_ID));
              beantemp.setCode(rs.getString(CABINTYPE_CODE));
              beantemp.setName(rs.getString(CABINTYPE_NAME));
              beantemp.setDescription(rs.getString(CABINTYPE_DESCRIPTION));
              beantemp.setType(rs.getInt(CABINTYPE_TYPE));
              beantemp.setDepartmentId(rs.getInt(CABINTYPE_DEPARTMENT_ID));
              beans.add(beantemp);
        }
      } catch(SQLException sqle)
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
    
  public boolean checkIdCabinTypeExistCabin(Connection cnn, FSeed seed) throws EException
  {
    final String LOCATION = this.toString() + "~~>checkIdCabinTypeExistCabin()";
    boolean result = true;
    PreparedStatement prstm = null;
    ResultSet rs = null;
    FCabinType bean = (FCabinType)seed;
    try
    {     
      prstm = cnn.prepareStatement(SQL_CABINTYPE_CHECK_ID_EXIST_CABIN);
//      //.println(SQL_CABINTYPE_CHECK_ID_EXIST_CABIN);
      prstm.setInt(PARAM_01,bean.getId());
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


}
