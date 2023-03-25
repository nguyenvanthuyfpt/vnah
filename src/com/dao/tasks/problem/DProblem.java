package com.dao.tasks.problem;


import com.dao.admin.departments.DDepartments;
import com.dao.admin.users.DUsers;
import com.dao.tasks.DSqlTasks;
import com.dao.tasks.categories.DCategories;
import com.dao.tasks.report.DReport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.users.FUser;
import com.form.messages.create.FCreate;
import com.form.tasks.categories.FCategories;
import com.form.tasks.problem.FProblem;
import com.form.tasks.problem.FUserPext;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DProblem extends DSqlTasks{

    public FBeans getAllCategories(Connection cnn,FSeed seed) throws EException{
      final String LOCATION = this.toString() + "getAllCategories()";
      FBeans beans = null;
      PreparedStatement prstm = null;
        FCategories beanC =(FCategories)seed;  
      FCategories bean=null;    
      ResultSet rs = null;
      String SQL_SELECT=SQL_SELECT_ALL_CATEGORIES + AND + TASK_CATEGORIES_CREATOR + EQUAL + QUESTION + SQL_SELECT_ADD_ORDER;
      try{
      
        List params = new ArrayList();
        params.add(beanC.getCreator());
        ////.println(SQL_SELECT);
        prstm = prepareStatement(cnn,SQL_SELECT,params); 
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
    
    
    public FProblem getAllAmount(Connection cnn, FSeed seed,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllAmount()";      
        FProblem bean= (FProblem)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {                     
            List params = new ArrayList();         
            params.add(userId);                       
            params.add(userId);         
            ////.println(SQL_SELECT_AMOUNT_PROPLEM_SEND);
            prstm = prepareStatement(cnn,SQL_SELECT_AMOUNT_PROPLEM_SEND,params); 
            rs = prstm.executeQuery();                        
            if((rs != null) && rs.next()) {
               bean.setAmountSend(rs.getInt(PARAM_01));            
                bean.setAmountTotalsSend(rs.getInt(PARAM_02));
            }
            getAllAmountRev(cnn,bean,userId);
            bean.setAmountCate(getAllAmountCate(cnn,userId));//tong so danh muc            
            bean.setAmount(bean.getAmountRecv() + bean.getAmountSend());
           
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
    public int getMoreDeadLine(Connection cnn,long userId,int type) throws EException
    {
        final String LOCATION = this.toString() + "getMoreDeadLine()";      
        FSeed bean =new FSeed();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        int total=0;
        try {                     
            List params = new ArrayList();
            params.add(userId);
            params.add(bean.addDays(bean.getCurrentSqlDate(),AppConfigs.TASKS_MORE_DEADLINE));
            params.add(0);//STOP
            prstm = prepareStatement(cnn,type==0?SQL_SELECT_AMOUNT_MORE_DEADLINE_RECV:SQL_SELECT_AMOUNT_MORE_DEADLINE_SEND,params); 
            rs = prstm.executeQuery();                        
            if((rs != null) && rs.next()) {
               total=rs.getInt(PARAM_01);
            }
        }catch(SQLException sqle){
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }finally{
          closeResultSet(rs);
          closePreparedStatement(prstm);
        }
        return total;  
    }
    
    public FProblem getAllAmountRev(Connection cnn,FProblem bean, int userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllAmountRev()";             
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {                     
            List params = new ArrayList();         
            params.add(userId);                       
            params.add(userId);                       
            prstm = prepareStatement(cnn,SQL_SELECT_AMOUNT_PROPLEM_RECV,params); 
            ////.println(SQL_SELECT_AMOUNT_PROPLEM_RECV);
            rs = prstm.executeQuery();                        
            if((rs != null) && rs.next()) {                
                bean.setAmountRecv(rs.getInt(PARAM_01));
                bean.setAmountTotalsRecv(rs.getInt(PARAM_02));
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
    
    public int getAllAmountCate(Connection cnn,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllAmountCate()";             
        PreparedStatement prstm = null;
        int result =0;
        ResultSet rs = null;
        try {                     
            List params = new ArrayList();         
            params.add(userId);                       
            prstm = prepareStatement(cnn,SQL_SELECT_AMOUNT_PROPLEM_CATE,params); 
            //println(SQL_SELECT_AMOUNT_PROPLEM_CATE);
            rs = prstm.executeQuery();                        
            if((rs != null) && rs.next()) {
               result = rs.getInt(PARAM_01);            
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
    
    public FBeans getUserByProblemId(Connection cnn, FSeed seed,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByProblemId()";
        FBeans beans = new FBeans();
        FUser bean= null;;
        PreparedStatement prstm = null;
        FProblem beanC = (FProblem)seed;
        ResultSet rs = null;
        try
        {         
          prstm = cnn.prepareStatement(SQL_USER_IN_PROPLEM);                  
          prstm.setInt(PARAM_01,beanC.getProblemId());           
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next())
          {
              bean = new FUser();
              DUsers dao = new DUsers(); 
              bean= dao.getInformation(cnn,rs);
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
        final String LOCATION = this.toString() + "getUserRevId()";       
        PreparedStatement prstm = null;
        FCreate beanC = (FCreate)seed;
        ResultSet rs = null;
        String result = "";
        try  {         
          prstm = cnn.prepareStatement(SQL_USER_IN_REC_TO_PERSON + SQL_USER_WHERE_DEPARTMENT);              
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
    public FBeans getUserByDepartmentId(Connection cnn, FSeed seed) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        FUserPext bean= null;;
        PreparedStatement prstm = null;
        FProblem beanC = (FProblem)seed;
        ResultSet rs = null;
        try
        {
         
          String SQL = SQL_USER_BY_DEPARTMENT_ID + SQL_TASK_RULE_JOIN ;
          prstm = cnn.prepareStatement(SQL);  
//          //.println(SQL);
          prstm.setInt(PARAM_01,beanC.getDepartmentID());   
          prstm.setInt(PARAM_02,beanC.getCreator());
          prstm.setInt(PARAM_03,beanC.getCreator());
           
      
          rs = prstm.executeQuery();         
          while((rs != null) && rs.next()){
              bean = new FUserPext();            
              bean = getInformationUser(cnn,rs);             
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
    
    public FBeans getAllDepartments(Connection cnn,long userId) throws EException
    {
      return getAllSubDepartments(cnn,0,userId);    
    }
    public FBeans getAllSubDepartments(Connection cnn, int idDepartment,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          String SQL = SQL_DEPARTMENT_SELECT_ALL_DEPARTMENT_IN_RULE + SQL_DEPARTMENT_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_DEPARTMENT_SELECT_ADD_WHERE_IN_TASK_RULE);
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);
          rs = prstm.executeQuery();        
          FDepartment bean = null;
          while((rs != null) && (rs.next()))
          {
            
              bean = new FDepartment();
              bean.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
              bean.setName(rs.getString(DEPARTMENTS_NAME));
              bean.setParentID(rs.getInt(DEPARTMENTS_PARENT_ID));                
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
    
    public FBeans getAllRecord(Connection cnn,FSeed seed,long userId) throws EException{
      final String LOCATION = this.toString() + "getAllRecord()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FProblem  bean = null;
      FProblem  beanC = (FProblem)seed;        
      ResultSet rs = null;
      String SQLSEARCH ="";
      String[] SQL_AND_WHERE_COMPLETE={"",SQL_AND_WHERE_ALL_PROPLEM_COMPLETE,SQL_AND_WHERE_ALL_PROPLEM_NOT_COMPLETE};
      String SQL = SQL_SELECT_ALL_PROPLEM_FILL[beanC.getType()] + AND + SQL_AND_WHERE_COMPLETE[beanC.getComplateSearch()];
      try{
        List params = new ArrayList();
        params.add(userId); 
        SQL = SQL.replaceAll("#",beanC.getCategoriesId()==0?"":SQL_SELECT_BY_CATEGORIES);    
        if(beanC.getCategoriesId()>0){
            params.add(beanC.getCategoriesId());  
        }
        if (beanC.getTitle()!=null && !beanC.getTitle().equals("")){
            SQLSEARCH += SQL_SELECT_PROPLEM_ADD_WHERE_TITLE;
            SQLSEARCH +=SQL_SELECT_PROPLEM_ADD_WHERE_PROBLEM;
            params.add(PER_CENT + beanC.getTitle() + PER_CENT );
            params.add(PER_CENT + beanC.getTitle() + PER_CENT );
        }
        if(beanC.getAmountDeadline()>0){
            SQL=beanC.getType()==0?SQL_SELECT_BY_DEADLINE_SEND:SQL_SELECT_BY_DEADLINE_RECV;
            params=new ArrayList();
            params.add(userId);
            params.add(beanC.addDays(beanC.getCurrentSqlDate(),AppConfigs.TASKS_MORE_DEADLINE));
        }
        if(beanC.getCategoriesId()!=0){
            SQLSEARCH+= AND + TABLE_TASK_CATEGORIES + STOP + TASK_CATEGORIES_BLOCK + EQUAL + 0;
        }
        SQLSEARCH+= AND + TABLE_TASK_PROBLEMS + STOP + TASK_PROBLEMS_STOP + EQUAL + beanC.getViewStop();//Cong viec dang tam dung
        
        prstm = prepareStatement(cnn,SQL + SQLSEARCH + SQL_SELECT_PROPLEM_ORDER,params);        
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
          int j = 0;
          while((rs != null) && (rs.next()) && (j<AppConfigs.APP_ROWS_VIEW)){
            bean =new FProblem();
            bean = getInformation(rs);
            bean.setNameCreator(getUserName(cnn,bean.getCreator()));           
            if (beanC.getType()==1){
                getInforAssign(bean,rs);
            }               
            bean.setAsigns(new FBeans());                            
            bean.getAsigns().add(getAllProblemAssign(cnn,bean,userId));
            if(bean.getAsigns()!=null && bean.getAsigns().size()>0){
                for(int i =0;i<bean.getAsigns().size();i++){
                    FProblem beanTemp =(FProblem)bean.getAsigns().get(i);
                    if(bean.getCheckHaveReport()==0){
                        bean.setCheckHaveReport(beanTemp.getCheckHaveReport());
                    }
                    if(beanTemp.getWorker()==userId){
                        bean.setCheckHaveReview(beanTemp.getCheckHaveReview());
                    }
                    if(beanTemp.getCheckHaveReport()==1 && beanTemp.getCheckHaveReview()==1){
                        break;
                    }
                }
            }
            beans.add(bean);  
            j ++;
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
    
    public FProblem getAllProblemAssign(Connection cnn,FProblem bean,long userId) throws EException{
      final String LOCATION = this.toString() + "getAllProblemAssign()";
      PreparedStatement prstm = null;         
      ResultSet rs = null;
      try{ 
        List params = new ArrayList();    
        params.add(bean.getProblemId());   
        ////.println(SQL_SELECT_ALL_PROPLEM_ASSIGN);
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_PROPLEM_ASSIGN,params);           
        rs = prstm.executeQuery();
        FProblem beanTemp = null;
        bean.setAsigns(new FBeans());
        while((rs != null) && (rs.next())){
            beanTemp =new FProblem();
            beanTemp.setAssignId(rs.getInt(TASK_ASSIGN_ASSIGN_ID));
            beanTemp.setWorker(rs.getInt(TASK_ASSIGN_WORKER));
            beanTemp.setWorkerName(getUserName(cnn,beanTemp.getWorker()));   
            beanTemp.setReaded(rs.getInt(TASK_ASSIGN_READ));
            beanTemp.setAccepted(rs.getInt(TASK_ASSIGN_ACCEPTED));
            beanTemp.setCompleteAssign(rs.getInt(TASK_ASSIGN_COMPLETE));
            beanTemp.setIncharge(rs.getInt(TASK_ASSIGN_INCHARGE));
            beanTemp.setTimeCreateAssign(bean.dateToString(new Date (rs.getTimestamp(TASK_ASSIGN_TIMEASSIGN).getTime()),AppConfigs.APP_DATE_TIME));            
            beanTemp.setStop(rs.getInt(TASK_ASSIGN_STOP));
            beanTemp.setCheckHaveReport(rs.getInt(TASK_ASSIGN_HAVE_REPORT));
            beanTemp.setCheckHaveReview(rs.getInt(TASK_ASSIGN_ASSIGN_HAVE_REPORT));
            
            bean.getAsigns().add(beanTemp);           
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
    
    public FBeans getAllReportOfWorker(Connection cnn,FSeed seed) throws EException{
      //KHONG PHAN TRANG
      final String LOCATION = this.toString() + "getAllReportOfWorker()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FProblem  bean = null;
      FProblem  beanC = (FProblem)seed;        
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        params.add(beanC.getProblemId()); 
        
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_ASSIGN.replaceAll("#",""),params);       
        rs = prstm.executeQuery();   
        beans = new FBeans();      
        int j = 0;
        while((rs != null) && (rs.next())){
            bean =new FProblem();
            bean = getInformationAssign(rs);
            bean.setWorkerName(getUserName(cnn,bean.getWorker()));           
            beans.add(bean);  
            j ++;
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
    
    
    public FBeans getListReportOfWorker(Connection cnn,FSeed seed,long userId) throws EException{
      final String LOCATION = this.toString() + "getListReportOfWorker()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FProblem  bean = null;
      FProblem  beanC = (FProblem)seed;        
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        params.add(beanC.getProblemId());           
        prstm = prepareStatement(cnn,SQL_SELECT_PROPLEM_BY_ID,params); 
        //println(SQL_SELECT_PROPLEM_BY_ID);
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
          int j = 0;
          while((rs != null) && (rs.next()) && (j<AppConfigs.APP_ROWS_VIEW)){
            bean =new FProblem();
            bean = getInformation(rs);           
            
            DReport dao = new DReport();                
            // get y kien nguoi tao
            bean.setReports(new FBeans());            
            bean.getReports().add(dao.getReportListByCreator(cnn,bean)); 
            
            // get thong tin ca nguoi thuc hien
            bean.setAsigns(new FBeans());             
            bean.getAsigns().add(getAllAssignByProblemId(cnn,bean,userId));   
            bean.setIncharge(getInchargeId(cnn,bean.getProblemId()));
            bean.setInchargeName(getUserName(cnn,bean.getIncharge()));
            bean.setNameCreator(rs.getString(USERS_FULLNAME));
            
            beans.add(bean);  
            j ++;
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

    
    public FProblem getAllAssignByProblemId(Connection cnn,FProblem bean,long userId) throws EException{
          final String LOCATION = this.toString() + "getAllAssignByProblemId()";
          FBeans beans = null;
          PreparedStatement prstm = null;
          ResultSet rs = null;
          try{
          
            List params = new ArrayList();
             params.add(bean.getProblemId());       
              String SQL ="";
             // if (bean.getCreator()==(int)userId || checkIncharge(cnn,bean.getProblemId(),userId)==1){
                  SQL = SQL_SELECT_ALL_ASSIGN.replaceAll("#","");
             // }else {
               //   SQL = SQL_SELECT_ALL_ASSIGN.replaceAll("#",SQL_SELECT_PROPLEM_ASSIGN_ADD_WHERE_WORDKER);
               //   params.add(userId);  
             // }
              //.println(SQL);
            prstm = prepareStatement(cnn,SQL,params); 
            //.println(SQL_SELECT_ALL_ASSIGN);
            rs = prstm.executeQuery();   
            beans = new FBeans();              
              FProblem beanTemp=null;          
              bean.setAsigns(new FBeans());          
              while((rs != null) && (rs.next())){
                beanTemp =new FProblem();
                beanTemp = getInformationAssign(rs);
                
                DReport dao = new DReport();                
                beanTemp.setReports(new FBeans());
                beanTemp.setCreator(bean.getCreator());
                beanTemp.getReports().add(dao.getAllReportList(cnn,beanTemp));  
                
                bean.getAsigns().add(beanTemp); 

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

   
    
    public void getInforAssign(FProblem bean,ResultSet rs) throws EException{
      final String LOCATION = this.toString() + "getInforAssign()";
      try{
            bean.setAssignId(rs.getInt(TASK_ASSIGN_ASSIGN_ID));
            bean.setWorker(rs.getInt(TASK_ASSIGN_WORKER));
            bean.setReaded(rs.getInt(TASK_ASSIGN_READ));
            bean.setAccepted(rs.getInt(TASK_ASSIGN_ACCEPTED));
            bean.setCompleteAssign(rs.getInt("TASKASSIGNCOMPLETE"));
            bean.setIncharge(rs.getInt(TASK_ASSIGN_INCHARGE));
            bean.setTimeCreateAssign(bean.dateToString(new Date (rs.getTimestamp(TASK_ASSIGN_TIMEASSIGN).getTime()),AppConfigs.APP_DATE_TIME));                        
            bean.setStopAssign(rs.getInt("TASKASSIGNSTOP"));
      }
      catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
    }
    public int checkIncharge(Connection cnn,int problemId,long worker) throws EException{
      final String LOCATION = this.toString() + "checkIncharge()";
      int result = 0;
      PreparedStatement prstm = null;      
      ResultSet rs = null;
      try{      
        List params = new ArrayList();
        params.add(problemId);
        params.add(worker);
        params.add(worker);
        prstm = prepareStatement(cnn,SQL_SELECT_PROPLEM_INCHARGE,params); 
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
    
    public int getAssignId(Connection cnn,int problemId) throws EException{
      final String LOCATION = this.toString() + "getAssignId()";
      int result = 0;
      PreparedStatement prstm = null;      
      ResultSet rs = null;
      try{      
        List params = new ArrayList();
        params.add(problemId);       
        prstm = prepareStatement(cnn,SQL_SELECT_PROPLEM_ASSIGN_ID,params); 
        rs = prstm.executeQuery();  
        if((rs != null) && (rs.next()))  {
            result = rs.getInt(TASK_ASSIGN_ASSIGN_ID);        
        }
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
    
    public FBeans getAllProblem(Connection cnn) throws EException{
      final String LOCATION = this.toString() + "getAllProblem()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      FProblem  bean=null;          
      ResultSet rs = null;
      try{
      
        List params = new ArrayList();
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_PROPLEM + SQL_SELECT_PROPLEM_ORDER,params); 
        rs = prstm.executeQuery();    
        beans = new FBeans();
        while((rs != null) && (rs.next())){
            bean =new FProblem();
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
    
    public int  getInchargeId(Connection cnn, int problemId) throws EException
    {
      final String LOCATION = this.toString() + "getInchargeId()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      int result = 0;
          
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_ALL_ASSIGN.replaceAll("#",""));
            //println(SQL_SELECT_ALL_ASSIGN.replaceAll("#",""));
            prstm.setLong(PARAM_01,problemId);       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())){            
                result = rs.getInt(TASK_ASSIGN_INCHARGE);
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
    
    public FProblem  getAccepted(Connection cnn, FProblem bean,int userId) throws EException
    {
      final String LOCATION = this.toString() + "getAccepted()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      int result = 0;
      
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_ALL_ASSIGN_ACCEPTED);
            //println(SQL_SELECT_ALL_ASSIGN_ACCEPTED);
            prstm.setLong(PARAM_01,bean.getProblemId());  
            prstm.setLong(PARAM_02,userId);  
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())){            
                bean.setAssignId(rs.getInt(TASK_ASSIGN_ASSIGN_ID));
                bean.setAccepted(rs.getInt(TASK_ASSIGN_ACCEPTED));
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
    
    public String  getCategoriesName(Connection cnn, int categoriesId) throws EException
    {
      final String LOCATION = this.toString() + "getCategoriesName()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String result = "";
          
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_CATEGORIES_NAME);         
            prstm.setLong(PARAM_01,categoriesId);       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())){            
                result = rs.getString(TASK_CATEGORIES_TITLE);
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
    
    public FProblem getProblemById(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getProblemById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;      
      FProblem beanC = (FProblem)seed;   
      FProblem beanTemp = null;
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_PROPLEM_BY_ID); 
            //println((SQL_SELECT_PROPLEM_BY_ID));
            prstm.setLong(PARAM_01,beanC.getProblemId());       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())) {   
                beanTemp = new FProblem();
                beanTemp = getInformation(rs);                     
                beanTemp.setNameCreator(rs.getString(USERS_FULLNAME));
                beanTemp.setCategoriesName(rs.getString("TASKCATEGORIESTITLE"));
                if (beanTemp.getCreator()!=beanC.getCreator()){
                    getAccepted(cnn,beanTemp,beanC.getCreator());                   
                }
                beanTemp.setIncharge(getInchargeId(cnn,beanTemp.getProblemId()));
                beanTemp.setInchargeName(getUserName(cnn,beanTemp.getIncharge()));
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
      return beanTemp;        
    }
    
    
    public FProblem getProblemByIdRoot(Connection cnn, FProblem beanC) throws EException
    {
      final String LOCATION = this.toString() + "getProblemByIdRoot()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FProblem bean = null;
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_PROPLEM_BY_ID + SQL_ADD_WHERE_ROOT);         
            prstm.setLong(PARAM_01,beanC.getProblemId());  
            prstm.setLong(PARAM_02,0);  
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())) {       
                bean = new FProblem();
                bean = getInformation(rs);                     
                bean.setNameCreator(getUserName(cnn,bean.getCreator()));
                bean.setIncharge(getInchargeId(cnn,bean.getProblemId()));
                bean.setInchargeName(getUserName(cnn,bean.getIncharge()));
                bean.setCategoriesName(getCategoriesName(cnn,bean.getCategoriesId()));
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
    
    public FProblem getAssignById(Connection cnn, int assignId) throws EException
    {
      final String LOCATION = this.toString() + "getProblemById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;      
      FProblem beanC = new FProblem();         
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_ASSIGN_ID);         
            prstm.setLong(PARAM_01,assignId);       
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())) {            
                beanC = getInformationAssign(rs);
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
    
    public int getMaxProblemId(Connection cnn,int userId) throws EException
    {
      final String LOCATION = this.toString() + "getMaxProblemId()";
      PreparedStatement prstm = null;
      ResultSet rs = null;  
      int result = 0;           
      try { 
            prstm = cnn.prepareStatement(SQL_SELECT_PROPLEM_MAX_ID);  
            prstm.setInt(PARAM_01,userId);
            rs = prstm.executeQuery();
            if((rs != null) && (rs.next())){            
                result = rs.getInt(PARAM_01);                
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
    
    public int getAssignCheck(Connection cnn,long  userId) throws EException
       {
         final String LOCATION = this.toString() + "getAssignCheck()";
         PreparedStatement prstm = null;
         ResultSet rs = null;            
         int result = 0;
         try   
         {
          
           prstm = cnn.prepareStatement(SQL_SELECT_PROPLEM_ASSIGN_CHECK);   
           //.println(SQL_SELECT_PROPLEM_ASSIGN_CHECK);
           prstm.setLong(PARAM_01,userId);
           rs = prstm.executeQuery();
           if((rs != null) && (rs.next())){           
               result = 1;
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

   
     
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      try
      {
          List params = setParams(seed);
          result = execute(cnn,SQL_INSERT_PROPLEM,params)>0;
          result = insertAssign(cnn,seed) ;
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
          //.println(SQL_INSERT_ASSIGN);
          if (bean.getUsersId()!=null){
              bean.setProblemId(getMaxProblemId(cnn,bean.getCreator()));
              for (int i =0;i<bean.getUsersId().length;i++){                
                    pstmt.setInt(PARAM_01,bean.getProblemId());
                    pstmt.setInt(PARAM_02,bean.getUsersId()[i]);
                    pstmt.setInt(PARAM_03,bean.getReaded());
                    pstmt.setInt(PARAM_04,bean.getAccepted());                    
                    pstmt.setInt(PARAM_05,bean.getComplete());
                    pstmt.setInt(PARAM_06,bean.getIncharge());
                    pstmt.setTimestamp(PARAM_07,new java.sql.Timestamp(System.currentTimeMillis()));                   
                    pstmt.setInt(PARAM_08,0);
                    pstmt.setInt(PARAM_09,0);//khong co bao cao
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
    

    
    public boolean update(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = setParams(seed);
        params.add(bean.getProblemId());
        result = execute(cnn,SQL_PROPLEM_UPDATE,params)>0;
        //result = updateAssign(cnn,seed);
        result = insertAssignsNew(cnn,seed);
        result = updateReadedBypeoBlemId(cnn,seed);
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean insertAssignsNew(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      FProblem bean = (FProblem)seed;
      PreparedStatement pstmt = null;   
      boolean flag = false;
      try
      {
         
          pstmt = cnn.prepareStatement(SQL_INSERT_ASSIGN);    
          if (bean.getUsersIdNew()!=null){            
              for (int i =0;i<bean.getUsersIdNew().length;i++){                
                    pstmt.setInt(PARAM_01,bean.getProblemId());
                    pstmt.setInt(PARAM_02,bean.getUsersIdNew()[i]);
                    pstmt.setInt(PARAM_03,bean.getReaded());
                    pstmt.setInt(PARAM_04,bean.getAccepted());                    
                    pstmt.setInt(PARAM_05,bean.getComplete());
                    pstmt.setInt(PARAM_06,0);
                    pstmt.setTimestamp(PARAM_07,new java.sql.Timestamp(System.currentTimeMillis()));                   
                    pstmt.setInt(PARAM_08,0);
                  pstmt.setInt(PARAM_09,0);
                    pstmt.addBatch();
                    flag = true;                                
              }
              if (flag){
                  result = pstmt.executeBatch().length>0;
              }
          }   
          updateIncharge(cnn,seed);
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
   
    public boolean updateReaded(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(1);        
        params.add(bean.getAssignId()); 
        params.add(bean.getProblemId());
        result = execute(cnn,SQL_PROPLEM_UPDATE_READED,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean updateReadedBypeoBlemId(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(0);              
        params.add(bean.getProblemId());
        result = execute(cnn,SQL_PROPLEM_UPDATE_READED_BY_PROPLEMID,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean updateAccepted(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(bean.getAccepted());        
        params.add(bean.getAssignId());
        params.add(bean.getProblemId());
        result = execute(cnn,SQL_PROPLEM_UPDATE_ACCEPTED,params)>0;
        updateReaded(cnn,seed);
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    public boolean updateProblemStop(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(bean.getStop());             
        params.add(bean.getProblemId());
        result = execute(cnn,SQL_PROPLEM_UPDATE_STOP,params)>0;
        result = execute(cnn,SQL_PROPLEM_ASSIGN_UPDATE_STOP,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    public boolean updateIncharge(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(bean.getIncharge());             
        params.add(bean.getProblemId());
        result = execute(cnn,SQL_ASSIGN_UPDATE_INCHARGE,params)>0;
      }
      catch(EException sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    public boolean updateAssigStop(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      try
      {
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
        params.add(bean.getStop());             
        params.add(bean.getAssignId());
        result = execute(cnn,SQL_ASSIGN_UPDATE_STOP,params)>0;
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
      boolean result = false;
      FProblem bean = (FProblem)seed;      
      result = delete(cnn, TABLE_TASK_REPORTS, TASK_REPORTS_ASSIGN_ID + EQUAL + getAssignId(cnn,bean.getProblemId()))>0;
      result = delete(cnn, TABLE_TASK_ASSIGN, TASK_ASSIGN_PROPLEM_ID + EQUAL + bean.getProblemId())>0;
      result =  delete(cnn, TABLE_TASK_PROBLEMS, TASK_PROBLEMS_PROBLEM_ID + EQUAL + bean.getProblemId())>0;
      return result;
    }  
    
    
    public FProblem getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FProblem bean = new FProblem();
         try {
             bean.setProblemId(rs.getInt(TASK_PROBLEMS_PROBLEM_ID));
             bean.setCreator(rs.getInt(TASK_PROBLEMS_CREATOR));   
             bean.setTitle(rs.getString(TASK_PROBLEMS_TITLE));
             bean.setProblem(rs.getString(TASK_PROBLEMS_PROBLEM));
             bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(TASK_PROBLEMS_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
             bean.setFileName(rs.getString(TASK_PROBLEMS_FILE));
             bean.setComplete(rs.getInt(TASK_PROBLEMS_COMPLETE));
             bean.setRoot(rs.getInt(TASK_PROBLEMS_ROOT));
             bean.setFromDate(bean.dateToString(rs.getDate(TASK_PROBLEMS_FROMDATE)));            
             bean.setToDate(bean.dateToString(rs.getDate(TASK_PROBLEMS_TODATE)));            
             bean.setCategoriesId(rs.getInt(TASK_PROBLEMS_CATEGORY_ID));
             bean.setStop(rs.getInt(TASK_PROBLEMS_STOP));
             bean.setPathFile(rs.getString(TASK_PROBLEMS_PATH_FILE));
             //bean.setCheckHaveReview(rs.getInt(TASK_PROBLEMS_ACTIVE_REPORT));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
    
    public FProblem getInformationAssign(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FProblem bean = new FProblem();
         try {
             bean.setAssignId(rs.getInt(TASK_ASSIGN_ASSIGN_ID));
             bean.setProblemId(rs.getInt(TASK_ASSIGN_PROPLEM_ID));
             bean.setWorker(rs.getInt(TASK_ASSIGN_WORKER));
             bean.setReaded(rs.getInt(TASK_ASSIGN_READ));
             bean.setAccepted(rs.getInt(TASK_ASSIGN_ACCEPTED));
             bean.setComplete(rs.getInt(TASK_ASSIGN_COMPLETE));
             bean.setIncharge(rs.getInt(TASK_ASSIGN_INCHARGE));
             bean.setTimeCreateAssign(bean.dateToString(new Date (rs.getTimestamp(TASK_ASSIGN_TIMEASSIGN).getTime()),AppConfigs.APP_DATE_TIME));                                     
             bean.setStop(rs.getInt(TASK_ASSIGN_STOP));          
             bean.setWorkerName(rs.getString(USERS_FULLNAME));             
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
        FProblem bean = (FProblem)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getCreator()); 
             params.add(bean.getTitle());    
             params.add(bean.getProblem());                    
             params.add(new java.sql.Timestamp(System.currentTimeMillis())); 
             params.add(bean.getFileName()); 
             params.add(bean.getComplete()); 
             params.add(bean.getRoot()); 
             params.add(bean.stringToSqlDate(bean.getFromDate())); 
             params.add(bean.stringToSqlDate(bean.getToDate()));                                                                            
             params.add(bean.getCategoriesId());  
             params.add(bean.getStop()); 
             params.add(bean.getPathFile()); 
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    
    public int checkHaveReport(Connection cnn,long meId) throws EException{
      final String LOCATION = this.toString() + "checkHaveReport()";
      int result = 0;
      PreparedStatement prstm = null;      
      ResultSet rs = null;
      try{      
        List params = new ArrayList();
        params.add(meId);       
        prstm = prepareStatement(cnn,SQL_SELECT_CHECK_HAVE_REPORT,params); 
        rs = prstm.executeQuery();  
            if((rs != null) && (rs.next()))  {
                result = rs.getInt(PARAM_01);        
            }
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
    public int checkHaveReview(Connection cnn,long meId) throws EException{
      final String LOCATION = this.toString() + "checkHaveReview()";
      int result = 0;
      PreparedStatement prstm = null;      
      ResultSet rs = null;
      try{      
        List params = new ArrayList();
        params.add(meId);       
        prstm = prepareStatement(cnn,SQL_SELECT_CHECK_HAVE_REVIEW,params); 
        rs = prstm.executeQuery();  
            if((rs != null) && (rs.next()))  {
                result = rs.getInt(PARAM_01);        
            }
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
    
    
}
