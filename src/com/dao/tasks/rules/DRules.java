package com.dao.tasks.rules;


import com.dao.DSql;
import com.dao.connection.DBConnector;
import com.dao.tasks.categories.DCategories;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.tasks.rules.FRules;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DRules extends DSql{
   
    public FBeans getAllRecord(Connection cnn,FSeed seed) throws EException{
      final String LOCATION = this.toString() + "getAllRecord()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FRules bean=null;
      FRules beanC= (FRules)seed;     
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();       
        params.add(beanC.getApp());
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_RULES,params);   
        
        //.println(SQL_SELECT_ALL_RULES);
        rs = prstm.executeQuery();                  
        beans = new FBeans();
        if (rs!=null)    rs.last(); 
             beans.setTotalRows(rs.getRow());
             beans.setPageIndex(1);
        if(beans.getFirstRecord()<=1){
             rs.beforeFirst();
        }else{
            rs.absolute(beans.getFirstRecord()-1);
        }  
        int j =0;
        while((rs != null) && (rs.next()) && (j<AppConfigs.APP_ROWS_VIEW)){
            bean =new FRules();
            bean = getInformation(rs);
            
            bean.setListsBoss(new FBeans());                            
            bean.getListsBoss().add(getAllBossByRuleId(cnn,bean));                          
            
            bean.setListsOffice(new FBeans());                            
            bean.getListsOffice().add(getAllOfficerByRuleId(cnn,bean));         
            
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
    
    public FRules getAllOfficerByRuleId(Connection cnn,FRules bean) throws EException{
      final String LOCATION = this.toString() + "getAllOfficerByRuleId()";
      FBeans beans = null;
      PreparedStatement prstm = null;           
      ResultSet rs = null;
      try{
      
        List params = new ArrayList(); 
        params.add(bean.getRuleId());
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_USERS_IN_OFFICER,params);        
        rs = prstm.executeQuery();
        FRules beanTemp = null;
        bean.setListsOffice(new FBeans());
        while((rs != null) && (rs.next())){
              beanTemp =new FRules();
              beanTemp.setUserId(rs.getInt(USERS_USER_ID));
              beanTemp.setUserFullName(rs.getString(USERS_FULLNAME));
              bean.getListsOffice().add(beanTemp);           
        }
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
    }
    
    
    
    
//    FTask beantemp = null;
//    bean.setTasks(new FBeans());
//    while ((rs != null) && (rs.next())) {           
//        beantemp = new FTask();
//        beantemp = getInformation(cnn, rs);
//        DUser user= new DUser();
//        user.getFullNameDateAssign(cnn,beantemp.getTask_id(),(int)beanTask.getUser_id(),beantemp);    
//        user.getIdAssign(cnn,beantemp.getTask_id(),beanTask.getIdMe(),beantemp);    
//       
//        beantemp.setDetailts(new FBeans());
//        DDetailt detailt = new DDetailt();                           
//        beantemp.getDetailts().add(detailt.getAllDetailtByTaskId(cnn,beantemp,Roles,beanTask,extTagLong));
//        bean.getTasks().add(beantemp);
//    
//    }
    
    public FRules getAllBossByRuleId(Connection cnn,FRules bean) throws EException{
      final String LOCATION = this.toString() + "getAllBossByRuleId()";
      FBeans beans = null;
      PreparedStatement prstm = null;         
      ResultSet rs = null;
      try{      
        
        List params = new ArrayList();    
        params.add(bean.getRuleId());
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_USERS_IN_BOSS,params);   
        //.println(SQL_SELECT_ALL_USERS_IN_BOSS);
        rs = prstm.executeQuery();
        FRules beanTemp = null;
        bean.setListsBoss(new FBeans());
        while((rs != null) && (rs.next())){
            beanTemp =new FRules();
            beanTemp.setUserId(rs.getInt(USERS_USER_ID));
            beanTemp.setUserFullName(rs.getString(USERS_FULLNAME));
            bean.getListsBoss().add(beanTemp);           
        }
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return bean;        
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
    
    public FRules getRuleByid(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRuleByid()";
      PreparedStatement prstm = null;
      ResultSet rs = null;      
      FRules beanC = (FRules)seed;         
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_RULES_BY_ID);         
            prstm.setLong(PARAM_01,beanC.getRuleId());       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next()))
            {            
                beanC = getInformation(rs);
                
                beanC.setListsBoss(new FBeans());                            
                beanC.getListsBoss().add(getAllBossByRuleId(cnn,beanC));                          
                
                beanC.setListsOffice(new FBeans());                            
                beanC.getListsOffice().add(getAllOfficerByRuleId(cnn,beanC));    
                
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
     
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      try
      {
          List params = setParams(seed);
          FRules bean = (FRules)seed;
          result = execute(cnn,SQL_INSERT_ASSIGN_RULES,params)>0;
          int ruleId =getValue(cnn,TABLE_TASK_ASSIGN_RULES,MAX(TASK_ASSIGN_RULES_RULE_ID));
          if(ruleId>0){
              insertUsersBoss(cnn,bean.getBossUsersId(),ruleId);
              insertUsersOfficer(cnn,bean.getOffUsersId(),ruleId);
          }
          
      }
      catch(Exception sqle){
            if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
      
    public boolean insertUsersBoss(Connection cnn, int[] users,int ruleId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      PreparedStatement pstmt = null;
      ResultSet rs = null;    
      try
      {
          pstmt = cnn.prepareStatement(SQL_INSERT_ASSIGN_BOSS);    
          if (users!=null){                          
              for (int i =0;i<users.length;i++){                
                    pstmt.setInt(PARAM_01,users[i]);
                    pstmt.setInt(PARAM_02,ruleId);
                    pstmt.addBatch();                   
              }
              if (users.length>0){
                  result = pstmt.executeBatch().length>0;
              }
          }          
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public boolean insertUsersOfficer(Connection cnn, int[] users,int ruleId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      PreparedStatement pstmt = null;
      ResultSet rs = null;    
      try
      {
          pstmt = cnn.prepareStatement(SQL_INSERT_ASSIGN_OFFICERS);    
          if (users!=null){                          
              for (int i =0;i<users.length;i++){                
                    pstmt.setInt(PARAM_01,users[i]);
                    pstmt.setInt(PARAM_02,ruleId);
                    pstmt.addBatch();                   
              }
              if (users.length>0){
                  result = pstmt.executeBatch().length>0;
              }
          }          
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
        FRules bean = (FRules)seed;
        List params = setParams(seed);
        params.add(bean.getRuleId());
        result = execute(cnn,SQL_ASSIGN_RULES_UPDATE,params)>0;
        result = deleteUsersFromRule(cnn,seed);
        result = insertUsersBoss(cnn,bean.getBossUsersId(),bean.getRuleId());
        result = insertUsersOfficer(cnn,bean.getOffUsersId(),bean.getRuleId());
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean updateActive(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FRules bean = (FRules)seed;
        List params = new ArrayList();
        params.add(bean.getActive());
        params.add(bean.getRuleId());
        result = execute(cnn,SQL_ASSIGN_RULES_UPDATE_ACTIVE,params)>0;       
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + DELETE;
      boolean result= false;
      FRules bean = (FRules)seed;
      result = delete(cnn, TABLE_TASK_ASSIGN_RULES, TASK_ASSIGN_RULES_RULE_ID + EQUAL + bean.getRuleId())>0;
      result =  deleteUsersFromRule(cnn,seed);
      return result;
    }  
    
    public boolean deleteUsersFromRule(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + DELETE;
      boolean result= false;
      FRules bean = (FRules)seed;
      result = delete(cnn, TABLE_TASK_ASSIGN_OFFICERS, TASK_ASSIGN_OFFICERS_RULE_ID + EQUAL + bean.getRuleId())>0;
      result = delete(cnn, TABLE_TASK_ASSIGN_BOSS, TASK_ASSIGN_BOSS_RULE_ID + EQUAL + bean.getRuleId())>0;       
      return result;
    }  
    
    
    public FRules getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FRules bean = new FRules();
         try {
             bean.setRuleId(rs.getInt(TASK_ASSIGN_RULES_RULE_ID));
             bean.setTitle(rs.getString(TASK_ASSIGN_RULES_TITLE));
             bean.setActive(rs.getInt(TASK_ASSIGN_RULES_ACTIVE)); 
             
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
        FRules bean = (FRules)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getTitle());                                                                                                                 
             params.add(bean.getActive());   
             params.add(bean.getApp());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
}
