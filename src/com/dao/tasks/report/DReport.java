package com.dao.tasks.report;


import com.dao.admin.departments.DDepartments;
import com.dao.admin.users.DUsers;
import com.dao.tasks.DSqlTasks;
import com.dao.tasks.categories.DCategories;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.users.FUser;
import com.form.messages.create.FCreate;
import com.form.tasks.categories.FCategories;
import com.form.tasks.problem.FProblem;
import com.form.tasks.problem.FUserPext;
import com.form.tasks.report.FReport;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DReport extends DSqlTasks{
    
    
    public FBeans getReportByAssignId(Connection cnn,FSeed seed) throws EException{
      final String LOCATION = this.toString() + "getReportByAssignId()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FProblem bean = (FProblem)seed;    
      FReport beanR = null;
      ResultSet rs = null;
      try{
        updateStatuReport(cnn,bean);
       // activeProblem(cnn,bean.getProblemId(),0);
        //updateComAsign(cnn,bean,(int)bean.me.getId(),0);
        List params = new ArrayList();
        params.add(bean.getAssignId());
        String SQL = SQL_SELECT_REPORT + SQL_SELECT_REPORT_ADD_ASSIGN_ID + SQL_SELECT_REPORT_ORDER_DESC;        
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                               
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            beanR =new FReport(); 
            beanR = getInformation(rs);            
            beans.add(beanR);
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
    public FBeans getReviewByAssign(Connection cnn,FSeed seed) throws EException{
      final String LOCATION = this.toString() + "getReportByAssignId()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FProblem bean = (FProblem)seed;    
      FReport beanR = null;
      ResultSet rs = null;
      String SQL  = SQL_SELECT_REPORT_REVIEW + SQL_SELECT_REPORT_ADD_ASSIGN_ID + SQL_SELECT_REPORT_ADD_PROBLEM_ID;
      try{
        List params = new ArrayList();
        params.add(0);
        params.add(bean.getProblemId());
        params.add(PER_CENT +"#"+ bean.getCreator() +"#" + PER_CENT);
        SQL += AND + OPEN + TASK_REPORTS_EMPS + LIKE + QUESTION;        
        
        params.add(bean.getCreator());
        SQL += OR + TASK_REPORTS_CREATOR + EQUAL + QUESTION + CLOSE;
        
        prstm = prepareStatement(cnn,SQL+SQL_SELECT_REPORT_ORDER_DESC,params); 
        rs = prstm.executeQuery();                               
        beans = new FBeans();        
        while((rs != null) && (rs.next())){
            beanR =new FReport();           
            beanR = getInformation(rs);  
            beanR.setCreatorName(rs.getString(USERS_FULLNAME));
            beans.add(beanR);
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
    public FBeans getAllCategories(Connection cnn) throws EException{
      final String LOCATION = this.toString() + "getAllCategories()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FCategories bean=null;    
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_CATEGORIES + SQL_SELECT_ADD_ORDER,params); 
        rs = prstm.executeQuery();                          
        DCategories daoC = new DCategories();
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            bean =new FCategories();            
            bean = daoC.getInformation(rs);
            bean.setNameCreator(getUserName(cnn,bean.getCreator()));
            beans.add(bean);
           
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
  
    public FProblem getAllReportList(Connection cnn,FProblem bean) throws EException{
          final String LOCATION = this.toString() + "getAllReportList()";      
          PreparedStatement prstm = null;      
          FReport beans = null;
          ResultSet rs = null;
          try{
          
            List params = new ArrayList();
            params.add(bean.getAssignId());
          //  params.add(bean.getProblemId());
         //   params.add(bean.getCreator());            
            prstm = prepareStatement(cnn,SQL_SELECT_REPORT + SQL_SELECT_REPORT_ADD_WERE,params); 
            //println(SQL_SELECT_REPORT + SQL_SELECT_REPORT_ADD_WERE);
            rs = prstm.executeQuery();                          
            DCategories daoC = new DCategories();
            FReport beanTemp = null;           
            bean.setReports(new FBeans());
            while((rs != null) && (rs.next())){
                beanTemp =new FReport();            
                beanTemp = getInformation(rs);                 
                bean.getReports().add(beanTemp); 
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
        
      public FProblem getReportListByCreator(Connection cnn,FProblem bean) throws EException{
          final String LOCATION = this.toString() + "getReportListByCreator()";      
          PreparedStatement prstm = null;      
          FReport beans = null;
          ResultSet rs = null;
          try{
          
            List params = new ArrayList();
            //params.add(bean.getAssignId());
            params.add(bean.getProblemId());
            params.add(bean.getCreator());
            prstm = prepareStatement(cnn,SQL_SELECT_REPORT + SQL_SELECT_REPORT_ADD_WERE_CREATOR,params); 
            //println(SQL_SELECT_REPORT + SQL_SELECT_REPORT_ADD_WERE_CREATOR);
            rs = prstm.executeQuery();                          
            DCategories daoC = new DCategories();
            FReport beanTemp = null;           
            bean.setReports(new FBeans());
            while((rs != null) && (rs.next())){
                beanTemp =new FReport();            
                beanTemp = getInformation(rs);                 
                bean.getReports().add(beanTemp); 
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
    
    public FBeans getAllUser(Connection cnn) throws EException{
      final String LOCATION = this.toString() + "getAllUser()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FUser bean=null;    
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        prstm = prepareStatement(cnn,SQL_ALL_USER,params); 
        rs = prstm.executeQuery();                          
        DUsers daoU = new DUsers();
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            bean =new FUser();            
            bean = daoU.getInformation(cnn,rs);            
            beans.add(bean);
           
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
    

    public String getUserAssigned(Connection cnn, FSeed seed,int userId,int userIdRev ) throws EException
    {
        final String LOCATION = this.toString() + "getUserAssigned()";
        PreparedStatement prstm = null;
        FCreate beanC = (FCreate)seed;
        ResultSet rs = null;
        String result = "";
        try  {         
          prstm = cnn.prepareStatement(SQL_USER_IN_REC_TO_PERSON + SQL_USER_WHERE_DEPARTMENT);        
          //.println(SQL_USER_IN_REC_TO_PERSON);
          prstm.setInt(PARAM_01,beanC.getId());  
          prstm.setInt(PARAM_02,userId);
          prstm.setInt(PARAM_03,beanC.getDepartmentID());  
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next())
          {
            if (rs.getInt(USERS_USER_ID)==userIdRev){
             result = "checked";
             break;
            }
            
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
    public FBeans getUserByDepartmentId(Connection cnn, FSeed seed,int userId,int departmentId) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        FUserPext bean= null;;
        PreparedStatement prstm = null;
        FProblem beanC = (FProblem)seed;
        ResultSet rs = null;
        try
        {
         
          prstm = cnn.prepareStatement(SQL_USER_BY_DEPARTMENT_ID);        
          prstm.setInt(PARAM_01,beanC.getDepartmentID()>0?beanC.getDepartmentID():departmentId);   
          prstm.setInt(PARAM_02,userId);   
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next()){
              bean = new FUserPext();            
              bean = getInformationUser(cnn,rs);
              //bean.setChecked(getUserAssigned(cnn,seed,userId,bean.getId())); 
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
    
    public FUserPext getInformationUser(Connection cnn, ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformationUser()";
        FUserPext user = new FUserPext();
         try {
           user.setId(rs.getInt(USERS_USER_ID));

           user.setUsername(rs.getString(USERS_USERNAME));
           user.setPassword(rs.getString(USERS_PASSWORD));
           user.setFullName(rs.getString(USERS_FULLNAME));                           
           user.setPicture(rs.getString(USERS_PICTURE));
           user.setEmail(rs.getString(USERS_EMAIL));                           
           user.setPhone(rs.getString(USERS_PHONE));
           user.setAddress(rs.getString(USERS_ADDRESS));
           user.setDescription(rs.getString(USERS_DESCRIPTION));

           user.setDateCreate(user.dateToString(rs.getDate(USERS_DATE_CREATE)));
           user.setDatePassword(user.dateToString(rs.getDate(USERS_DATE_PASSWORD)));                           
           user.setDateLogin(user.dateToString(rs.getDate(USERS_DATE_LOGIN)));

           user.setSex(rs.getInt(USERS_SEX));
           user.setDepartmentID(rs.getInt(USERS_DEPARTMENT_ID));
             FDepartment department = new FDepartment();
             department.setId(user.getDepartmentID());
             DDepartments departments = new DDepartments();
             department = departments.getRecordByID(cnn,department);           
           user.setDepartmentName(department.getName());           
           user.setRole(rs.getInt(USERS_ROLE));
           user.setPrivilege(rs.getInt(USERS_PRIVILEGE));
           user.setActive(rs.getInt(USERS_ACTIVE));
           user.setPeriod(rs.getInt(USERS_PERIOD));
           user.setGroupID(rs.getInt(USERS_GROUP_ID));
           user.setApp(rs.getString(USERS_APP));
           user.setChangePassword(rs.getInt(USERS_CHANGE_PASSWORD));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return user;
    }
    
    public FBeans getAllDepartments(Connection cnn) throws EException
    {
      return getAllSubDepartments(cnn,0);    
    }
    public FBeans getAllSubDepartments(Connection cnn, int idDepartment) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_DEPARTMENT_SELECT_ALL_DEPARTMENT);
          rs = prstm.executeQuery();
          String members = ",";
          FDepartment bean = null;
          boolean all = idDepartment==0;
          boolean start = false;
          int id = -1; 
              while((rs != null) && (rs.next() && members!=null))
              {
                id = rs.getInt(PARAM_01);
                if((all && members.indexOf("," + id + ",")<0) || (!start && id == idDepartment)){
                   start = true;
                   bean = new FDepartment();
                   bean.setId(id);
                   bean.setName(rs.getString(PARAM_02));
                   bean.setParentID(rs.getInt(PARAM_03));
                   if(id>0){
                        members+= id +  ",";
                        beans.add(bean);
                   }
                }
                if(start){
                    if(all || members.indexOf("," + id + ",")>=0){
                        id = rs.getInt(PARAM_04);
                        bean = new FDepartment();
                        bean.setId(id);
                        bean.setName(rs.getString(PARAM_05));
                        bean.setParentID(rs.getInt(PARAM_06));            
                        if(id>0){
                              members+= id +  ",";
                              beans.add(bean);
                        }
                    }else if(!all){
                        members=null;
                    }
                 }
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
    public FBeans getAllRecord(Connection cnn,FSeed seed,long meId) throws EException{
      final String LOCATION = this.toString() + "getAllRecord()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FReport  bean = (FReport)seed;   
      
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        
          
        String SQL = "";
        if (bean.getCreator()==meId){
             SQL = SQL_SELECT_REPORT + SQL_SELECT_REPORT_ADD_WERE_CREATOR + SQL_SELECT_REPORT_ORDER;
            params.add(bean.getProblemId());   
            params.add(bean.getCreator());   
        }else{
            SQL = SQL_SELECT_REPORT + SQL_SELECT_REPORT_ADD_WERE + SQL_SELECT_REPORT_ORDER;
            params.add(bean.getAssignId());   
        }
        
        prstm = prepareStatement(cnn,SQL,params);          
        rs = prstm.executeQuery();   
        beans = new FBeans();                  
        while((rs != null) && (rs.next())){
            bean =new FReport();
            bean = getInformation(rs);           
            beans.add(bean);   
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
    public FReport getReportId(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getProblemById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;      
      FReport beanC = (FReport)seed;         
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_PROPLEM_REPORT_BY_ID);         
            prstm.setLong(PARAM_01,beanC.getId());       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())) {            
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
    
    public void getInforAssign(Connection cnn,FProblem bean,long userId) throws EException{
      final String LOCATION = this.toString() + "getInforAssign()";
      
      PreparedStatement prstm = null;      
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        params.add(bean.getProblemId());
        params.add(userId);
        prstm = prepareStatement(cnn,SQL_SELECT_ASSIGN_BY_ID,params); 
        rs = prstm.executeQuery();   
      
        if((rs != null) && (rs.next())){
            
            bean.setAssignId(rs.getInt(TASK_ASSIGN_ASSIGN_ID));
            bean.setWorker(rs.getInt(TASK_ASSIGN_WORKER));
            bean.setReaded(rs.getInt(TASK_ASSIGN_READ));
            bean.setAccepted(rs.getInt(TASK_ASSIGN_ACCEPTED));
            bean.setComplete(rs.getInt(TASK_ASSIGN_COMPLETE));
            bean.setIncharge(rs.getInt(TASK_ASSIGN_INCHARGE));
            bean.setTimeCreateAssign(bean.dateToString(new Date (rs.getTimestamp(TASK_ASSIGN_TIMEASSIGN).getTime()),AppConfigs.APP_DATE_TIME));                        
            
        }
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
       
    }
    
    public int checkEdit(Connection cnn,int problemId) throws EException{
      final String LOCATION = this.toString() + "checkEdit()";
      int result = 0;
      PreparedStatement prstm = null;      
      ResultSet rs = null;
      try{      
        List params = new ArrayList();
        params.add(problemId);       
        prstm = prepareStatement(cnn,SQL_SELECT_PROPLEM_CHECK_EDIT,params); 
        rs = prstm.executeQuery();  
        if((rs != null) && (rs.next()))  result = 1;        
      }
      catch(SQLException sqle){
            if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
       return result;
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
    
   
   
     
    public boolean insert(Connection cnn, FSeed seed,int userI,int newReport) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;   
      try
      {
          FReport bean = (FReport)seed;
          List params = setParams(seed);
          result = execute(cnn,SQL_INSERT_REPORTS,params)>0;
          updateComAsign(cnn,seed,userI,newReport);  
            if(bean.getCheckEmp()!=null && bean.getCheckEmp().length>0){
              assignHaveReport(cnn,seed,userI,newReport);
            }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
       return result;    
    }
    
    public boolean insertAssign(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      FProblem bean = (FProblem)seed;
      PreparedStatement pstmt = null;   
      boolean flag = false;
      try
      {
         
          pstmt = cnn.prepareStatement(SQL_INSERT_ASSIGN);    
          if (bean.getUsersId()!=null){
          
              for (int i =0;i<bean.getUsersId().length;i++){                
                    pstmt.setInt(PARAM_01,bean.getProblemId());
                    pstmt.setInt(PARAM_02,bean.getUsersId()[i]);
                    pstmt.setInt(PARAM_03,bean.getReaded());
                    pstmt.setInt(PARAM_04,bean.getAccepted());                    
                    pstmt.setInt(PARAM_05,bean.getComplete());
                    pstmt.setInt(PARAM_06,bean.getIncharge());
                    pstmt.setDate(PARAM_07,bean.getCurrentSqlDate());                   
                    pstmt.setInt(PARAM_08,1);                   
                    pstmt.addBatch();
                    flag = true;                                
              }
              if (flag){
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
    
    public boolean updateComAsign(Connection cnn, FSeed seed,int userId,int newReport) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FReport bean = (FReport)seed;
        List params = new ArrayList();
        params.add(bean.getComplate());
        params.add(newReport);//have a new report
        params.add(bean.getAssignId());
        result = execute(cnn,SQL_ASSINH_COMPLATE_UPDATE,params)>0;
        if (bean.getIncharge()==userId){
          result = updateComPro(cnn,bean);
        }
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    public boolean assignHaveReport(Connection cnn, FSeed seed,int userId,int newReport) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      FReport bean = (FReport)seed;
      String SQL_SELECT=SQL_UPDATE_HAVE_A_REPORT;
      try
      {
        List params = new ArrayList();
        params.add(newReport);//have a new report
        params.add(bean.getProblemId());
        String checkEmps="";
        if(bean.getCheckEmp()!=null && bean.getCheckEmp().length>0){
        for(int i=0;i<bean.getCheckEmp().length;i++){
            if(!checkEmps.equals("")) checkEmps+=",";
            checkEmps+=bean.getCheckEmp()[i];
        }
        }
        result = execute(cnn,SQL_SELECT.replaceAll("#",checkEmps),params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean activeProblem(Connection cnn,int problemId,int active) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        List params = new ArrayList();
        params.add(active);//have a new report
        params.add(problemId);
        result = execute(cnn,SQL_UPDATE_PROBLEM_ACTIVE,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean updateComPro(Connection cnn,FReport bean) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {       
        List params = new ArrayList();
        params.add(bean.getComplate());              
        params.add(bean.getProblemId());
        result = execute(cnn,SQL_PROPLEM_UPDATE_COMPLATE,params)>0;
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
      FProblem bean = (FProblem)seed;
      return 0 < delete(cnn, TABLE_TASK_PROBLEMS, TASK_PROBLEMS_PROBLEM_ID + EQUAL + bean.getProblemId());
    }  
    
    
    public FReport getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FReport bean = new FReport();
         try {
             bean.setId(rs.getInt(TASK_REPORTS_REPORT_ID));
             bean.setAssignId(rs.getInt(TASK_REPORTS_ASSIGN_ID));
             bean.setReport(rs.getString(TASK_REPORTS_REPORT));                
             bean.setTimeReport(bean.dateToString(new Date (rs.getTimestamp(TASK_REPORTS_TIMEREPORT).getTime()),AppConfigs.APP_DATE_TIME));            
             bean.setFileName(rs.getString(TASK_REPORTS_FILE));
             bean.setComplate(rs.getInt(TASK_REPORTS_COMPLETE));
             bean.setPathFile(rs.getString(TASK_REPORTS_PATH_FILE));
             
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
        FReport bean = (FReport)seed;
        
        List params = new ArrayList();
         try {          
             String checkEmps="";
             if(bean.getCheckEmp()!=null && bean.getCheckEmp().length>0){
               for(int i=0;i<bean.getCheckEmp().length;i++){
                   checkEmps+="#" + bean.getCheckEmp()[i] + "#";
               }
             }
             
             params.add(bean.getAssignId());                    
             params.add(bean.getReport());                    
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));                  
             params.add(bean.getFileName());    
             params.add(bean.getComplate()); 
             params.add(bean.getPathFile()); 
             params.add(bean.getProblemId());
             params.add(bean.getCreator());
             params.add(checkEmps);
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    public boolean updateStatuReport(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(0);//da doc bao cao
        params.add(bean.getAssignId());
        params.add(seed.me.getId());
        
        result = execute(cnn,SQL_UPDATE_ASSIGN_STATUS_REPORT,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean updateStatuReview(Connection cnn, FSeed seed) throws EException {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(0);//da doc bao cao
        params.add(bean.getAssignId());
        params.add(seed.me.getId());
        result = execute(cnn,SQL_UPDATE_ASSIGN_STATUS_REVIEW,params)>0;
      } catch(EException sqle) {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
 
}
