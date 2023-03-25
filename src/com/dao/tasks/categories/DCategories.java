package com.dao.tasks.categories;


import com.dao.connection.DBConnector;
import com.dao.tasks.DSqlTasks;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.tasks.categories.FCategories;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


public class DCategories extends DSqlTasks{
   
    public FBeans getAllRecord(Connection cnn,FSeed seed,int userId,int block) throws EException{
      final String LOCATION = this.toString() + "getAllRecord()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FCategories bean=null;
      FCategories beanC= (FCategories)seed;     
      ResultSet rs = null;
      try{
        List params = new ArrayList();
        params.add(userId);
        String SQLSEARCH = "";
        if (beanC.getTitleSearch()!=null){
            if (!beanC.getTitleSearch().trim().equals("")){
                SQLSEARCH += SQL_CATEGORIES_WHERE_TITLE; 
                params.add(PER_CENT + beanC.getTitleSearch() + PER_CENT);
            }
        }
        if(block==0){
        SQLSEARCH += AND + TASK_CATEGORIES_BLOCK + EQUAL + QUESTION;
        params.add(0);
        }
        String SQL = SQL_SELECT_ALL_CATEGORIES + SQL_SELECT_CATEGORIES_ADD_WHERE_CREATOR + SQLSEARCH + SQL_SELECT_ADD_ORDER;
        ////.println(SQL);
        
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                  
        beans = new FBeans();
        if (rs!=null)    rs.last(); 
             beans.setTotalRows(rs.getRow());
             beans.setPageIndex(beanC.getPageIndex());
        if(beans.getFirstRecord()<=1){
             rs.beforeFirst();
        }else{
            rs.absolute(beans.getFirstRecord()-1);
        }  
        int j =0;
        while((rs != null) && (rs.next()) && (j<AppConfigs.APP_ROWS_VIEW)){
            bean =new FCategories();
            bean = getInformation(rs);
            bean.setNameCreator(getUserName(cnn,bean.getCreator()));
            beans.add(bean);
            j++;
        }
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    
    public String  getUserName(Connection cnn, int userId) throws EException
    {
      final String LOCATION = this.toString() + "getUserName()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String result = "";
          
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_CREATOR_NAME);         
            prstm.setLong(PARAM_01,userId);       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next()))
            {            
                result = rs.getString(USERS_FULLNAME);
                
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
      return result;        
    }
    
    public FCategories getCategoriesById(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getCategoriesById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;      
      FCategories beanC = (FCategories)seed;         
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_CATEGORIES_BY_ID);         
            prstm.setLong(PARAM_01,beanC.getId());       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next()))
            {            
                beanC = getInformation(rs);
                
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
      return beanC;        
    }
    
    public boolean delete(FSeed seed)throws EException,SQLException
      {
         final String LOCATION = this + "->delete()";
         boolean result = false;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             DCategories dao = new DCategories();                 
             result = dao.delete(conn,seed);             
             DBConnector.endTransaction(conn);            
          }
          catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,ex);
          }
          finally {
             DBConnector.closeConnection(conn);
         }
     return result;    
     }
    public boolean insert_(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "insert()";
      boolean result = true;
      PreparedStatement prstm = null;
      FCategories bean = null;
      try
      {
        bean = (FCategories)seed;
        prstm = cnn.prepareStatement(SQL_INSERT_CATEGORIES);
        prstm.setInt(PARAM_01,bean.getCreator());
        prstm.setTimestamp(PARAM_02,new Timestamp(System.currentTimeMillis()));
        prstm.setString(PARAM_03,bean.getTitle());
        prstm.setString(PARAM_04,bean.getDescription());
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
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      try
      {
          List params = setParams(seed);
          result = execute(cnn,SQL_INSERT_CATEGORIES,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public boolean update(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FCategories bean = (FCategories)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        result = execute(cnn,SQL_CATEGORIES_UPDATE,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      FCategories bean = (FCategories)seed;
      return 0 < delete(cnn, TABLE_TASK_CATEGORIES, TASK_CATEGORIES_CATEGORY_ID + EQUAL + bean.getId());
    }  
    
    
    public FCategories getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FCategories bean = new FCategories();
         try {
             bean.setId(rs.getInt(TASK_CATEGORIES_CATEGORY_ID));
             bean.setCreator(rs.getInt(TASK_CATEGORIES_CREATOR));            
             bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(TASK_CATEGORIES_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
             bean.setTitle(rs.getString(TASK_CATEGORIES_TITLE));
             bean.setDescription(rs.getString(TASK_CATEGORIES_DESCRIPTION));
             bean.setBlock(rs.getInt(TASK_CATEGORIES_BLOCK));
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
        FCategories bean = (FCategories)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getCreator());                         
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));                  
             params.add(bean.getTitle());                                                                            
             params.add(bean.getDescription());                                        
             params.add(bean.getBlock());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
}
