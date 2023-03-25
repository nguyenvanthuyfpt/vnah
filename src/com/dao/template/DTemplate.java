package com.dao.template;


import com.dao.calendar.DCalendarLib;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.template.FTemplate;

import com.inf.IKey;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DTemplate  extends DSqlTemplate{
   
    public boolean restore(Connection cnn,int[] ids,int value) throws SQLException, EException {
      final String LOCATION = this.toString() + UPDATE;
      boolean flag = false;
      PreparedStatement prstm = null;
      try{
            prstm = cnn.prepareStatement(SQL_TEMPLATE_PERMISION);
             for (int i =0;i<ids.length;i++){
                 prstm.setInt(PARAM_01,value);
                 prstm.setInt(PARAM_02,ids[i]);
                 prstm.addBatch();
                 flag = true;
             }
             if (flag){
                 prstm.executeBatch();
             }
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }finally{
        closePreparedStatement(prstm);
      }
      return flag;
    }
    public String getAllADMIN(Connection cnn,int userId) throws EException
    {
      final String LOCATION = this.toString() + "getAllADMIN()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String  temp="#";
      try{
        prstm = cnn.prepareStatement(SQL_SELECT_ADD_ADMIN_TEMPLATE);
        //////.println(SQL_SELECT_ADD_ADMIN_TEMPLATE);
        prstm.setInt(PARAM_01,userId);
        rs = prstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            temp+=rs.getInt(TEMPLATE_CATEGORY_ADMIN_CATEGORY_ID) + "#";
        }
      } catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return temp;        
    }
    public FBeans getAll(Connection cnn,FSeed seed,int userId,int type) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTemplate bean=(FTemplate)seed;
      String SQL_WHERE=WHERE + TRUE;
      String SQL=SQL_SELECT_PRIVATE_STORE;
      try {
       // String ids=getAllADMIN(cnn,userId);
        List params = new ArrayList();
        if(bean.getName()!=null && !bean.getName().equals("")){
            params.add(PER_CENT + bean.getName() + PER_CENT);
            params.add(PER_CENT + bean.getName() + PER_CENT);
            params.add(PER_CENT + bean.getName() + PER_CENT);
            
            SQL_WHERE+=AND + OPEN + TABLE_TEMPLATES + STOP + TEMPLATE_CODE + LIKE + QUESTION ;
            SQL_WHERE+=OR + TABLE_TEMPLATES + STOP + TEMPLATE_NAME + LIKE + QUESTION ;
            SQL_WHERE+=OR + TABLE_TEMPLATES + STOP + TEMPLATE_DESCRIPTION + LIKE + QUESTION + CLOSE;
        }
        if(bean.getTemplateType_id()>0){
            params.add(bean.getTemplateType_id());
            SQL_WHERE+=AND +  TABLE_TEMPLATES + STOP + TEMPLATE_TEMPLATETYPE_ID + EQUAL+QUESTION;
        }
        if(bean.getDepartmentId()>0){
            params.add(bean.getDepartmentId());
            SQL_WHERE+=AND +  TABLE_DEPARTMENTS + STOP + TEMPLATE_DEPARTMENT_ID + EQUAL + QUESTION;
        }
        if(type==1){
            params.add(type);
            SQL_WHERE+=AND +  TABLE_TEMPLATES + STOP + TEMPLATE_PERMISSION + EQUAL + QUESTION;
        }else{
            params.add(type);
            params.add(userId);
            SQL_WHERE+=AND +  TABLE_TEMPLATES + STOP + TEMPLATE_PERMISSION + EQUAL + QUESTION + AND + TABLE_TEMPLATES + STOP + TEMPLATE_USER_ID + EQUAL + QUESTION;
        }
        SQL += SQL_WHERE;
        //////.println(SQL);
        prstm = prepareStatement(cnn,SQL + ORDER_BY + TABLE_TEMPLATES + STOP + TEMPLATE_CODE + COMMA + TABLE_TEMPLATES + STOP + TEMPLATE_TIMECREATE + DESC,params);
        rs = prstm.executeQuery();
        
        beans = new FBeans();
          beans.setTotalRows(count(cnn,SQL,params));
          beans.setPageIndex(bean.getPageIndex());
            if(beans.getFirstRecord()<=1){
                rs.beforeFirst();
            }else{
                rs.absolute(beans.getFirstRecord()-1);
            }
            int i=0;
          while(rs != null && rs.next()&& i<AppConfigs.APP_ROWS_VIEW){
            i++;
                bean=new FTemplate();
                bean=getInformation(rs,true);
                bean.setBlock(bean.getUserId()==userId?1:0);
                //bean.setBlock(ids.indexOf("#" + bean.getTemplateType_id() + "#")>=0?1:0);
                if(new DCalendarLib().convertDateToID(bean.addDays(bean.stringToDate(bean.getTimeCreate()),IKey.TEMPLATE_HOSTNEW))>=new DCalendarLib().convertDateToID(bean.getCurrentSqlDate())){
                    bean.setHostNew(1);
                }
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
    public boolean isExistUpdate(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FTemplate bean = (FTemplate)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
        pstmt = conn.prepareStatement(SQL_TEMPLATE_CHECK_CODE_UPDATE);  
        pstmt.setInt(PARAM_01,bean.getId());
        pstmt.setString(PARAM_02,bean.getName());
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
    public FTemplate getRecordByID(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByID()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTemplate bean = (FTemplate)seed;
      try
      {
      //.print(SQL_SELECT_TEMPLATE_BY_ID);
        prstm = cnn.prepareStatement(SQL_SELECT_TEMPLATE_BY_ID);
        prstm.setInt(PARAM_01,bean.getId());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            bean=new FTemplate();
            bean.setId(rs.getInt(TEMPLATE_ID));
            bean.setUserId(rs.getInt(TEMPLATE_USER_ID));
            bean.setCode(rs.getString(TEMPLATE_CODE));
            bean.setName(rs.getString(TEMPLATE_NAME));
              bean.setDescription(rs.getString(TEMPLATE_DESCRIPTION));
              bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(TEMPLATE_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));
              bean.setPermission(rs.getInt(TEMPLATE_PERMISSION));
              bean.setTemplateType_id(rs.getInt(TEMPLATE_TEMPLATETYPE_ID));
              bean.setFileStore(rs.getString(TEMPLATE_FILESTORE));
              bean.setRealName(rs.getString(TEMPLATE_REALNAME));
              bean.setDepartmentId(rs.getInt(TEMPLATE_DEPARTMENT_ID));
              bean.setEffectiveDate(bean.dateToString(rs.getDate(TEMPLATE_EFFECTIVEDATE)));
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
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          List params = setParams(seed);
          prstm = prepareStatement(cnn,SQL_INSERT_TEMPLATE,params);
          result = prstm.executeUpdate()>0;
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
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FTemplate bean = (FTemplate)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_UPDATE_TEMPLATE,params);
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
    public boolean updatePermision(Connection cnn,int id,int permission) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
            prstm = cnn.prepareStatement(SQL_TEMPLATE_PERMISION);
            prstm.setInt(PARAM_01,permission);
            prstm.setInt(PARAM_02,id);
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
    public boolean delete(Connection cnn,int[] ids) throws EException{
    for(int i=0;i<ids.length;i++){
        delete(cnn, TABLE_TEMPLATES, TEMPLATE_ID + EQUAL + ids[i] );
    }
      return true;
    }    
    

    public FTemplate getInformation(ResultSet rs,boolean full) throws EException
    {
        final String LOCATION = "->getInformation()";
        FTemplate bean = new FTemplate();
         try {
           bean.setId(rs.getInt(TEMPLATE_ID));
             bean.setCreator(rs.getString(USERS_FULLNAME));
             bean.setUserId(rs.getInt(TEMPLATE_USER_ID));
             bean.setCode(rs.getString(TEMPLATE_CODE));
             bean.setName(rs.getString(TEMPLATE_NAME));
             bean.setDescription(rs.getString(TEMPLATE_DESCRIPTION));
             bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(TEMPLATE_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));
             bean.setPermission(rs.getInt(TEMPLATE_PERMISSION));
             bean.setTemplateType_id(rs.getInt(TEMPLATE_TEMPLATETYPE_ID));
             bean.setFileStore(rs.getString(TEMPLATE_FILESTORE));
             bean.setRealName(rs.getString(TEMPLATE_REALNAME));
             bean.setNameDep(rs.getString("DEP_NAME"));
             bean.setDepartmentId(rs.getInt(TEMPLATE_DEPARTMENT_ID));
             bean.setEffectiveDate(bean.dateToString(rs.getDate(TEMPLATE_EFFECTIVEDATE)));
             if(full){
                 bean.setNameCaterory(rs.getString(TEMPLATE_NAME_CATEGORY));
             }
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
   
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FTemplate bean = (FTemplate)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getUserId());
             params.add(bean.getCode());
             params.add(bean.getName());
             params.add(bean.getDescription());                           
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));                     
             params.add(1);                           
             params.add(bean.getTemplateType_id());                           
             params.add(bean.getFileStore());   
             params.add(bean.getRealName());   
             params.add(bean.getDepartmentId());   
             params.add(bean.stringToSqlDate(bean.getEffectiveDate()));   
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public FBeans getByCode(Connection cnn,String code,int type) throws EException
    {
      final String LOCATION = this.toString() + "getByCode()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FTemplate bean =null;
        FBeans beans=null;
      try{
        prstm = cnn.prepareStatement(SQL_GET_BY_CODE);
        //////.println(SQL_GET_BY_CODE);
        prstm.setString(PARAM_01,code);
        prstm.setInt(PARAM_02,type);
        rs = prstm.executeQuery();
        beans=new FBeans();
        while((rs != null) && (rs.next())){
            bean=new FTemplate();
            bean.setId(rs.getInt(TEMPLATE_ID));
            bean.setName(rs.getString(TEMPLATE_NAME));
            bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(TEMPLATE_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));
            bean.setFileStore(rs.getString(TEMPLATE_FILESTORE));
            bean.setRealName(rs.getString(TEMPLATE_REALNAME));
            beans.add(bean);
        }
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally
      {
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }  
   
}
