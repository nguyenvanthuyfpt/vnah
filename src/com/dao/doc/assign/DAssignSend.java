package com.dao.doc.assign;


import com.dao.admin.doc.category.transfer.DTransfer;
import com.dao.admin.doc.rules.DDocRules;
import com.dao.admin.users.DUsers;
import com.dao.doc.docsSearch.DDocsSearch;
import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.doc.category.transfer.FTransfer;
import com.form.admin.doc.rules.FDocRules;
import com.form.admin.groups.FGroup;
import com.form.admin.users.FUser;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docssend.FDocssend;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DAssignSend extends DSqlForYou
{
   
    public int getUserSendDoc(Connection cnn,int docId,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getUserSendDoc()";
        int result =0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        { 
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL_USER_SEND_DOCSEND);
          prstm.setLong(PARAM_01,docId);
          prstm.setLong(PARAM_02,userId);
          rs = prstm.executeQuery();       
         
          if((rs != null) && rs.next()){ 
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
    public boolean updateReadedReply(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          FDocAssign bean = (FDocAssign)seed;
          updateReadedMe(cnn,bean);          
          if (AppConfigs.DOC_READ_EXCUTE==1 && bean.getUserReply()!=null){
                  updateReadedRecvReply(cnn,bean); 
          }
          
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    public boolean updateReadedRecvReply(Connection cnn,FDocAssign bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try{ 
          List params = new ArrayList();                  
          String[] userReply =bean.getUserReply().split(",");
          params.add(0);
          params.add(bean.getDocId());        
          params.add(Integer.parseInt(userReply[0]));                  
          result =  execute(cnn,SQL_UPDATE_READED_SEND_FOR_PEOPLE_FORYOU_RECV,params)>0;//DA SUA           
          if (userReply.length>1 &&  Integer.parseInt(userReply[1])>0){
              params = new ArrayList();    
              params.add(0);
              params.add(bean.getDocId());  
              params.add(Integer.parseInt(userReply[1]));              
              execute(cnn,SQL_UPDATE_READED_SEND_FOR_FORYOU,params);
          }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    } 
    
    
    public boolean updateReadedMe(Connection cnn,FDocAssign bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(1);
          params.add(bean.getDocId());         
          params.add(bean.getMeId());
          //.println(SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_ME);
          result =  execute(cnn,SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_ME,params)>0;//da sua         
           if (AppConfigs.DOC_READ_EXCUTE==1){  
               updateReadedForYou(cnn,bean);  
           }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public boolean updateReadedForYou(Connection cnn,FDocAssign bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
               List params = new ArrayList();                             
               params = new ArrayList();   
               if (bean.getForYouId()>0 ){
                   //.println(SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_ME);
                   params.add(1);
                   params.add(bean.getDocId());
                   params.add(bean.getForyouCreator()); 
                  result = execute(cnn,SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_ME,params)>0;       
               }else{
                  
                   if (bean.getIndexTrailer()==1){ // Chuyen lai 
                   
                       params = new ArrayList();                 
                       params.add(0);
                       params.add(bean.getDocId());
                       params.add((bean.getUserReply()!=null && !bean.getUserReply().equals(""))?Integer.parseInt(bean.getUserReply().split(",")[0]):0);                     
                       params.add(bean.stringToSqlDate(bean.getCurrentDateLocal()));
                       params.add(bean.addDays(bean.stringToSqlDate(bean.getCurrentDateLocal()),1));  
                       //.println(SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_TO_FORYOU_INDEX1);
                       execute(cnn,SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_TO_FORYOU_INDEX1,params);                         
                   }else if (bean.getIndexTrailer()==2){ // Chon nguoi nhan
                       String[] value = bean.getMembers().split(",");
                       String userRecv ="";
                       for (int i =0;i<value.length;i++){   
                               if (!userRecv.equals("")) userRecv +=",";
                                userRecv +=Integer.parseInt(value[i].split("#")[1]);
                        }
                        params = new ArrayList();                 
                        params.add(0);
                        params.add(bean.getDocId());
                       params.add(bean.stringToSqlDate(bean.getCurrentDateLocal()));
                       params.add(bean.addDays(bean.stringToSqlDate(bean.getCurrentDateLocal()),1));  
                        //.println(SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_TO_FORYOU_INDEX2.replaceAll("#",!userRecv.equals("")?userRecv:0+""));
                        execute(cnn,SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_TO_FORYOU_INDEX2.replaceAll("#",!userRecv.equals("")?userRecv:0+""),params);                         
                       
                   }else if (bean.getIndexTrailer()==3){ // chuyen truc tiep
                     
                       String SQL_USER_RECV = bean.getDepartmentId()>0?SQL_SELECT_ALL_USER_PRIO_ADD_BY_DEPARTMENT_ID_SEND_USER_RECV:SQL_SELECT_ALL_USER_PRIO_SEND_USER_RECV;
                       params = new ArrayList();                 
                       params.add(0);
                       params.add(bean.getDocId());                         
                       if ( bean.getDepartmentId()>0){
                           params.add(bean.getDepartmentId());
                           params.add(AppConfigs.DOCSSEND_WORKFLOWID);
                           params.add(bean.getMeId());
                       }else {
                           params.add(AppConfigs.DOCSSEND_WORKFLOWID);
                           params.add(bean.getMeId());
                       }    
                       params.add(bean.stringToSqlDate(bean.getCurrentDateLocal()));
                       params.add(bean.addDays(bean.stringToSqlDate(bean.getCurrentDateLocal()),1));  
                       execute(cnn,SQL_UPDATE_READED_SEND_FOR_PEOPLE_SEND_TO_FORYOU_INDEX2.replaceAll("#",SQL_USER_RECV),params); 
                   }
                   
               }               
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }    
    
    public FBeans getUserInRule(Connection cnn, FSeed seed,int depId,int groupId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByGroupId()";
        FBeans beans = new FBeans();
        FBeans beansTranfer = new FBeans();
        PreparedStatement prstm = null;
        FDocAssign beanC = (FDocAssign)seed;
        ResultSet rs = null;
        try{
            if (beanC.getCheckShowTransfer()==1){                            
                beansTranfer = new DTransfer().getAllTransfer(cnn);
            }
        String SQL =SELECT +  TABLE_USERS + STOP + USERS_USER_ID + COMMA + TABLE_USERS + STOP + USERS_FULLNAME +  FROM  + TABLE_USERS + WHERE + USERS_USER_ID + IN + OPEN + SELECT +  TABLE_DOC_OFFICERS + STOP + DOC_OFFICERS_USER_ID +  FROM +  TABLE_DOC_OFFICERS +  LEFT_JOIN +  TABLE_DOC_RULES + ON + TABLE_DOC_OFFICERS + STOP + DOC_OFFICERS_RULE_ID + EQUAL  + TABLE_DOC_RULES + STOP + DOC_RULES_RULE_ID + LEFT_JOIN + TABLE_DOC_BOSS + ON + TABLE_DOC_OFFICERS + STOP + DOC_OFFICERS_RULE_ID + EQUAL + TABLE_DOC_BOSS +STOP + DOC_BOSS_RULE_ID +  WHERE +  TABLE_DOC_BOSS + STOP + DOC_BOSS_USER_ID + EQUAL + QUESTION +  AND + TABLE_DOC_RULES + STOP + DOC_RULES_ACTIVE + DIFF + 0 +  AND +  TABLE_DOC_RULES + STOP + DOC_RULES_WORKFLOW_ID + EQUAL + 2 +  CLOSE ;
        List params =new ArrayList();
        params.add(beanC.getMeId());
        if(groupId>0){
            SQL+= AND +  USERS_GROUP_ID + EQUAL + QUESTION;
            params.add(groupId);
        }
        if(depId>0){
            SQL+= AND +  USERS_DEPARTMENT_ID + EQUAL + QUESTION;
            params.add(depId);
        }
        //.println(SQL);
          prstm = prepareStatement(cnn,SQL,params);   
          rs = prstm.executeQuery();   
         FUser bean= null;
          while((rs != null) && rs.next()){
              bean = new FUser();            
              bean.setId(rs.getInt(USERS_USER_ID));
              bean.setFullName(rs.getString(USERS_FULLNAME));
              if (beanC.getCheckShowTransfer()==1){                  
                 bean.setTranfers(new FBeans());                            
                 bean.getTranfers().add(getTransfer(cnn,bean,beansTranfer));  
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
    
    public FBeans getAllDepartments(Connection cnn,long userId) throws EException
    {
      return getAllSubDepartments(cnn,0,userId);    
    }
    
    
  
    public FBeans getAllDepartmentsSelect(Connection cnn,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllDepartmentsSelect()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          String SQL = SQL_DEPARTMENT_SELECT_ALL_DEPARTMENT_IN_RULE + SQL_DEPARTMENT_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_DEPARTMENT_SELECT_ADD_WHERE_IN_DOC_RULE) ;
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);
          prstm.setLong(PARAM_02,AppConfigs.DOCSSEND_WORKFLOWID);
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
    
    
    public FBeans getAllSubDepartments(Connection cnn, int idDepartment,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllSubDepartments()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          String SQL = SQL_DEPARTMENT_SELECT_ALL_DEPARTMENT_IN_RULE + SQL_DEPARTMENT_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_DEPARTMENT_SELECT_ADD_WHERE_IN_DOC_RULE_PRIORITIES) ;
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);
          prstm.setLong(PARAM_02,AppConfigs.DOCSSEND_WORKFLOWID);
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
    
    public FBeans getAllGroupsByRule(Connection cnn,long userId) throws EException
    {
        final String LOCATION = this.toString() + "getAllGroupsByRule()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {//+ SQL_DEPARTMENT_ORDER_BY_TREE
          String SQL = SQL_GROUP_SELECT_ALL_GROUP_IN_RULE + SQL_GROUP_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_GROUP_SELECT_ADD_WHERE_IN_DOC_RULE) ;
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);
          prstm.setLong(PARAM_02,AppConfigs.DOCSSEND_WORKFLOWID);
          rs = prstm.executeQuery();       
          FGroup bean = null;
          while((rs != null) && rs.next()){ 
               bean = new FGroup();
               bean.setId(rs.getInt(GROUPS_GROUP_ID));
               bean.setName(rs.getString(GROUPS_NAME));
               bean.setParentID(rs.getInt(GROUPS_PARENT_ID));                
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
    public FBeans getUserByDepartmentId(Connection cnn, FSeed seed,int departmentId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByDepartmentId()";
        FBeans beans = new FBeans();
        FUser bean= null;
        PreparedStatement prstm = null;
        FDocAssign beanC = (FDocAssign)seed;
        
        ResultSet rs = null;     
        try
        {
        
        FBeans beansTranfer = new FBeans();
        if (beanC.getCheckShowTransfer()==1){                            
            beansTranfer = new DTransfer().getAllTransfer(cnn);
        }
        
        String SQL1="SELECT * FROM USERS WHERE  USERS.USER_ID<>? AND USERS.USER_ID IN ( SELECT DOC_OFFICERS.USER_ID FROM DOC_RULES LEFT JOIN DOC_OFFICERS ON DOC_RULES.RULE_ID=DOC_OFFICERS.RULE_ID LEFT JOIN DOC_BOSS ON DOC_RULES.RULE_ID=DOC_BOSS.RULE_ID WHERE DOC_RULES.ACTIVE<>0 AND DOC_RULES.WORKFLOW_ID=? AND DOC_BOSS.USER_ID=?)  OR (USERS.USER_ID IN ( SELECT DOC_PRIORITIES.USER_ID FROM DOC_RULES LEFT JOIN DOC_PRIORITIES ON DOC_RULES.RULE_ID=DOC_PRIORITIES.RULE_ID LEFT JOIN DOC_BOSS ON DOC_RULES.RULE_ID=DOC_BOSS.RULE_ID WHERE DOC_RULES.ACTIVE<>0 AND DOC_RULES.WORKFLOW_ID=? AND DOC_BOSS.USER_ID=?)  )  AND USERS.USER_ID NOT  IN ( SELECT DOC_TRAILER_SEND.USERRECV_ID FROM DOC_TRAILER_SEND WHERE DOC_ID=?) ";
        List params =new ArrayList();
        params.add(beanC.getMeId());   
        params.add(beanC.getWorkflowId());
        params.add(beanC.getMeId());         
        params.add(beanC.getWorkflowId());
        params.add(beanC.getMeId());
        params.add(beanC.getId());   
        prstm =prepareStatement(cnn,SQL1,params);    
        rs = prstm.executeQuery();   
        DUsers daoU = new DUsers();
        beans = new FBeans();                    
        while((rs != null) && (rs.next())){            
              bean = new FUser();            
              bean = daoU.getInformation(cnn,rs); 
              if (beanC.getCheckShowTransfer()==1){                  
                 bean.setTranfers(new FBeans());                            
                 bean.getTranfers().add(getTransfer(cnn,bean,beansTranfer));  
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
    
    public FUser getTransfer(Connection cnn,FUser bean,FBeans beans) {     
     
      FTransfer beanTemp = null;
        bean.setTranfers(new FBeans());                      
        if (beans!=null){
            for (int i=0;i<beans.size();i++){
            
                beanTemp =new FTransfer();
                beanTemp = (FTransfer)beans.get(i);                
                bean.getTranfers().add(beanTemp); 
                
            }
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
    
    
    
    
    public FBeans getDocAssignByDocId(Connection cnn, FDocAssign bean) throws EException 
    {
      final String LOCATION = this.toString() + "getDocAssignByDocId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_DOC_TRAILER_SEND_BY_DOC_ID);
        prpstm.setInt(PARAM_01,bean.getId());
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if((rs != null) && (rs.next()))
        {
            FDocAssign beantemp =new FDocAssign();
            beantemp=getInformationAssignSend(rs);           
            beantemp.setRecvUserName(getUserName(cnn,beantemp.getUsersRecv()));
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
    
    
    public int getReviewIdTop(Connection cnn,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getReviewIdTop()";
        int result = 0;
        PreparedStatement prstm = null;        
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_SELECT_ALL_DOC_REVIEW_SEND_MAX_ID);
          prstm.setInt(PARAM_01,userId);
          rs = prstm.executeQuery();           
          if((rs != null) && rs.next()){
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
    
    
   
   
  public FBeans getAlldocsByType(Connection cnn,FSeed seed,int userId) throws EException
  {
    final String LOCATION = this.toString() + "~~>getAlldocsByType()";
    PreparedStatement prpstm = null;
    FDocAssign   bean = (FDocAssign)seed;
    ResultSet rs = null;
    FBeans beans = null;
    try
    {
        String SQL = SQL_SELECT_ALL_DOCS_SEND + SQL_SELECT_ALL_DOCSSEND_WHERE; //     
        prpstm = cnn.prepareStatement(SQL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);   
        prpstm.setInt(PARAM_01,userId);
        //.println(SQL);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if (rs!=null)    rs.last(); 
        beans.setTotalRows(rs.getRow());
        beans.setPageIndex(bean.getPageIndex());
        if(beans.getFirstRecord()<=1){
            rs.beforeFirst();
        }else{
          rs.absolute(beans.getFirstRecord()-1);
        }
        int i=0;
        DDocsSearch daoS = new DDocsSearch();
        while(rs != null && rs.next() && i<AppConfigs.APP_ROWS_VIEW)
        { 
           i++;
          beans.add(daoS.getInformation(rs));
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
  
  
    public FBeans getAllReview(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllReview()";
      PreparedStatement prpstm = null;
      FDocAssign   bean = (FDocAssign)seed;
      ResultSet rs = null;
      FBeans beans = null;
      String SQL_SELECT=SQL_SELECT_ALL_DOC_REVIEW_SEND_PUPLIC;
      try
      {
          FDocAssign BRules=new DAssignRecv().checkAsignRule(cnn,bean);
          List params = new ArrayList();
          if(BRules.getCheckViewReview()==1 || bean.getObServer()==1){
             params.add(bean.getDocId());
          }else{
             SQL_SELECT=SQL_SELECT_ALL_DOC_REVIEW_SEND_PRIVATE;
             params.add(bean.getDocId());
             params.add(bean.getMeId());
             params.add(PER_CENT + "|" + bean.getMeId() + "|" + PER_CENT);
          }

          prpstm =prepareStatement(cnn,SQL_SELECT,params);   
          rs = prpstm.executeQuery();
          beans = new FBeans();
          FDocAssign beantemp = null;
          int block=0;
          while(rs != null && rs.next())
          { 
            beantemp = new FDocAssign();
            beantemp = getInformationReviewSend(rs);            
            if(block==0){
                beantemp.setCheckEmp(beantemp.getCreator()!=bean.getMeId()?1:0);
                if(beantemp.getCheckEmp()==1){
                    block=1;
                }
            }
            checkReviewForYou(cnn,beantemp);
            beans.add(beantemp);
          }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;
    }
    
    public  void checkReviewForYou(Connection cnn,FDocAssign bean ) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkReviewForYou()";
      PreparedStatement prpstm = null;   
      ResultSet rs = null;
      FBeans beans = null;
      int result = 0;
      try
      {
         
          prpstm = cnn.prepareStatement(SQL_SELECT_CHECK_DOC_REVIEW_SEND_FORYOU);             
          prpstm.setInt(PARAM_01,bean.getDocId());
          prpstm.setInt(PARAM_02,bean.getReviewId());                   
          rs = prpstm.executeQuery();          
          if(rs != null && rs.next()){ 
              bean.setCheckReviewForYou(rs.getInt(DOC_TRAILER_SEND_USERSEND_ID));
              bean.setForYouId(rs.getInt(DOC_TRAILER_SEND_FORYOU_ID));
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
    }
    
    public FDocAssign getByReviewId(Connection cnn,int reviewId) throws EException
    {
      final String LOCATION = this.toString() + "~~>getByIdReview()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocAssign bean =new FDocAssign();
      try
      {
          List params = new ArrayList();
          params.add(reviewId);
          prpstm =prepareStatement(cnn,SELECT + STAR + FROM + TABLE_DOC_FILES_SEND_REVIEW + WHERE + DOC_FILES_SEND_REVIEW_REVIEW_ID + EQUAL + QUESTION ,params);   
          rs = prpstm.executeQuery();
          if(rs != null && rs.next())
          { 
              bean.setReviewId(rs.getInt(DOC_FILES_SEND_REVIEW_REVIEW_ID));
              bean.setFileName(rs.getString("FILES"));
              bean.setPathFile(rs.getString("PATH_FILE"));
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
      return bean;
    }
    public  FDocRules checkObserver(Connection cnn,long userId,int workflowId) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkObserver()";
      PreparedStatement prpstm = null;   
      ResultSet rs = null;
      FDocRules bean = new FDocRules();    
      try
      {
         
          prpstm = cnn.prepareStatement(SQL_SELECT_ALL_DOC_CHECK_OBSERVER,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);   
          ////.println(SQL_SELECT_ALL_DOC_CHECK_OBSERVER);
          prpstm.setLong(PARAM_01,userId);
          prpstm.setInt(PARAM_02,workflowId);                   
          rs = prpstm.executeQuery();          
          if(rs != null && rs.next()){ 
              bean.setObserverId(1);  
              bean.setBlockFile(rs.getInt(DOC_OBSERVERS_BLOCK_FILE));  
              bean.setDeleteDocs(rs.getInt(DOC_OBSERVERS_DELETE_DOCS));                
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
      return bean;
    }
    
    public boolean insertDirect(Connection cnn,FSeed seed,int userId) throws EException
    {
      final String LOCATION = this.toString() + "~~>insertDirect()";  
      FDocAssign   bean = (FDocAssign)seed;      
      boolean result =false;
      try
      { 
          
          String SQL_INSERT_DOC_TRALER_SEND_ = bean.getDepartmentId()>0?SQL_INSERT_DOC_TRALER_SEND.replaceAll("#",SQL_SELECT_ALL_USER_PRIO_ADD_BY_DEPARTMENT_ID_SEND):SQL_INSERT_DOC_TRALER_SEND.replaceAll("#",SQL_SELECT_ALL_USER_PRIO_SEND);
          List params = new ArrayList();
              params.add(bean.getDocId());
              params.add(userId); 
              params.add(0);
              params.add(new java.sql.Timestamp(System.currentTimeMillis()));                    
              params.add(0);
              params.add(bean.getViews());
              params.add(bean.getStatusId());
              params.add(0);
          if (bean.getDepartmentId()>0){
              params.add(bean.getDepartmentId());
              params.add(bean.getWorkflowId());
              params.add(bean.getForYouId()>0?bean.getForyouCreator():userId);
          }else {
              params.add(bean.getWorkflowId());
              params.add(bean.getForYouId()>0?bean.getForyouCreator():userId);
          }
          params.add(bean.getDocId());        
          result=execute(cnn,SQL_INSERT_DOC_TRALER_SEND_,params)>0;   
          updateReadedMe(cnn,bean);
          if (result) {
              insertForYou(cnn,seed,userId);
              updateStatusDoc(cnn,seed);  
          }else if (AppConfigs.DOC_READ_EXCUTE==1){
               bean.setReaded(0);
               bean.setDisposeUser(null);
               updateReadedAssignRecv(cnn,bean);
          }
          }
          catch(Exception sqle)
          {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
          }
          return result;
    }
    
    
    public boolean insertDirectAnWear(Connection cnn,FSeed seed,int userId,int docIdRecv) throws EException
    {
      final String LOCATION = this.toString() + "~~>insertDirectAnWear()";  
      FDocAssign   bean = (FDocAssign)seed;      
      boolean result =false;
      try
      { 
          List params = new ArrayList();
          params.add(bean.getDocId());
          params.add(userId); 
          params.add(0);
          params.add(new java.sql.Timestamp(System.currentTimeMillis()));                    
          params.add(0);
          params.add(bean.getViews());
          params.add(bean.getStatusId());
          params.add(0);                            
          params.add(docIdRecv);    
          params.add(userId);                 
          result=execute(cnn,SQL_INSERT_DOC_TRALER_SEND.replaceAll("#",OPEN + SQL_SELECT_TRALER_RECV_FOR_ANWEAR + CLOSE),params)>0;                   
          if (result) {
              insertForYou(cnn,seed,userId);
              updateStatusDoc(cnn,seed);  
          }
      }
      catch(Exception sqle)
      {
         if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    }
    
    
    public boolean insertForYou(Connection cnn,FSeed seed,int userId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;      
      FDocAssign   bean = (FDocAssign)seed;
      boolean result =false;
      try
      { 
          List params = new ArrayList();
          params.add(bean.getDocId());          
          params.add(0);
          params.add(new java.sql.Timestamp(System.currentTimeMillis()));
          params.add(bean.getViews());  
          params.add(bean.getStatusId());  
          params.add(0);  
          
          params.add(bean.getWorkflowId());  
          params.add(bean.getCurrentSqlDate()); 
          params.add(bean.getCurrentSqlDate());          
          params.add(bean.getDocId());
          params.add(userId); 
          params.add(bean.getDocId());  
          params.add(bean.getWorkflowId());  
          params.add(bean.getCurrentSqlDate());  
          params.add(bean.getCurrentSqlDate()); 
          params.add(bean.getDocId());
          params.add(userId); 
          //.println(SQL_INSERT_DOC_TRAILER_SEND_FORYOU);
          result=execute(cnn,SQL_INSERT_DOC_TRAILER_SEND_FORYOU,params)>0;    
          
      }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }    
      return result;
    }

    public String getSelectUserTrailerRecvByDocId(Connection cnn, int docId) throws EException
    {
        final String LOCATION = this.toString() + "usersRecv()";
        PreparedStatement prstm = null;
        String usersRecv ="";
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_INSERT_DOC_TRAILER_SEND_BY_DOC_ID);
          prstm.setLong(PARAM_01,docId);         
          rs = prstm.executeQuery();       
          FDepartment bean = null;
          while((rs != null) && rs.next()){ 
            if (!usersRecv.equals("")) usersRecv += ",";            
              usersRecv += rs.getInt(PARAM_01)+"";
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
        return usersRecv;      
    }
        public boolean checkAddBatch(Connection cnn, int docId,int userRecv_id) throws EException
        {
            final String LOCATION = this.toString() + "checkAddBatch()";
            PreparedStatement prstm = null;
            boolean result =false;
            ResultSet rs = null;
            try
            {
              prstm = cnn.prepareStatement(SQL_CHECK_DOC_TRAILER_SEND_ADDBATCH);
              //.println(SQL_CHECK_DOC_TRAILER_SEND_ADDBATCH);
              prstm.setLong(PARAM_01,docId);         
                prstm.setLong(PARAM_02,userRecv_id);
                rs = prstm.executeQuery();
                result = (rs!=null) && (rs.next());     
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

    public boolean insertEmitEdit(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;       
      FDocAssign bean = (FDocAssign)seed;
      PreparedStatement prstm = null;
      try {   
              prstm = cnn.prepareStatement(SQL_INSERT_DOC_TRALER_SEND_SELECT);
                String[] value = bean.getMembers().split(",");
                  for (int i =0;i<value.length;i++){   
                      if (value[i]!=null && !value[i].equals("")){
                        if(!checkAddBatch(cnn,bean.getId(),Integer.parseInt(value[i]))){
                          prstm.setInt(PARAM_01,bean.getId());
                          prstm.setLong(PARAM_02,bean.getMeId());                         
                          prstm.setInt(PARAM_03,0);                          
                          prstm.setTimestamp(PARAM_04,new java.sql.Timestamp(System.currentTimeMillis()));
                          prstm.setInt(PARAM_05,0);
                          prstm.setInt(PARAM_06, 1);   
                          prstm.setInt(PARAM_07,bean.getStatusId());   
                          prstm.setInt(PARAM_08,0);   
                          prstm.setInt(PARAM_09,Integer.parseInt(value[i]));   
                          prstm.addBatch();        
                        }
                      }
                      
                  }  
              result=prstm.executeBatch().length>0;              
          }
          catch(Exception sqle)
          {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
          }
          return result;
    }  


 public boolean insert(Connection cnn,FSeed seed,int meId,String usersSend) throws EException
 {
   final String LOCATION = this.toString() + INSERT;
   boolean result = false;       
   FDocAssign bean = (FDocAssign)seed;
   PreparedStatement prstm = null;
   try {   
           prstm = cnn.prepareStatement(SQL_INSERT_DOC_TRALER_SEND_SELECT);
             String[] value = bean.getMembers().split(",");
               for (int i =0;i<value.length;i++){   
                   if (value[i]!=null && !value[i].equals("")){
                     if(!checkAddBatch(cnn,bean.getId(),Integer.parseInt(value[i].split("#")[1]))){
                       prstm.setInt(PARAM_01,bean.getId());
                       prstm.setLong(PARAM_02,bean.getMeId());                         
                       prstm.setInt(PARAM_03,0);
                       //prstm.setDate(PARAM_04,bean.DateToSqlDate(new java.sql.Timestamp(System.currentTimeMillis())));
                       prstm.setTimestamp(PARAM_04,new java.sql.Timestamp(System.currentTimeMillis()));
                       prstm.setInt(PARAM_05,0);
                       prstm.setInt(PARAM_06, Integer.parseInt(value[i].split("#")[0]));   
                       prstm.setInt(PARAM_07,bean.getStatusId());   
                       prstm.setInt(PARAM_08,0);   
                       prstm.setInt(PARAM_09,Integer.parseInt(value[i].split("#")[1]));   
                       prstm.addBatch();        
                     }
                   }
                   
               }  
           result=prstm.executeBatch().length>0; 
           updateReadedMe(cnn,bean);
           if (result){
              insertForYou(cnn,seed,meId);
               updateStatusDoc(cnn,seed);  
           }else if (AppConfigs.DOC_READ_EXCUTE==1){
             bean.setReaded(0);
             bean.setDisposeUser(value);
             updateReadedAssignRecv(cnn,bean);
           }
       }
       catch(Exception sqle)
       {
       if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
       }
       return result;
 }  
        
    public boolean updateStatusDoc(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      try{  
         List params = new ArrayList(); 
         params.add(bean.getStatusId());
         params.add(bean.getDocId());                 
         result =  execute(cnn,SQL_UPDATE_DOC_SEND,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    
    public boolean updateReadReview(Connection cnn,int id,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(id);       
          //.println(SQL_UPDATE_DOC_SEND_READ_REVIEW);
          result =  execute(cnn,SQL_UPDATE_DOC_SEND_READ_REVIEW,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    

    public boolean updateReadedRecv(Connection cnn,int id,int meId,int userReply) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(0);
          params.add(id);        
          params.add(userReply);                  
        //  result =  execute(cnn,SQL_UPDATE_READED_FOR_PEOPLE_FORYOU_RECV,params)>0;
          updateReadedSendCheckForYou(cnn,id,userReply);
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    } 
    
    public boolean updateReadedForyou(Connection cnn,int id,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(1);
          params.add(id);
          params.add(id);          
          params.add(meId);      
          result =  execute(cnn,SQL_UPDATE_READED_FOR_PEOPLE_FORYOU,params)>0; 
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    public boolean updateReadedSendForYou(Connection cnn,int id,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(0);
          params.add(id);
          params.add(id);  
          params.add(id);  
          params.add(meId);
          params.add(id);  
          params.add(meId);   
          //.println(SQL_UPDATE_READED_FOR_PEOPLE_SEND_FOR_FORYOU);
          result =  execute(cnn,SQL_UPDATE_READED_FOR_PEOPLE_SEND_FOR_FORYOU,params)>0;            
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    public boolean updateReadedSendCheckForYou(Connection cnn,int id,int userReply) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;    
      FSeed seed = new FSeed();
      try
      {  
          List params = new ArrayList();  
          params.add(0);
          params.add(id);
          params.add(AppConfigs.DOCSSEND_WORKFLOWID);  
          params.add(seed.getCurrentSqlDate());  
          params.add(seed.addDays(seed.getCurrentSqlDate(),1));      
          params.add(userReply);          
          result =  execute(cnn,SQL_UPDATE_READED_FOR_PEOPLE_SEND_CHECK_FOR_FORYOU,params)>0;            
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    public boolean updateReadedTest(Connection cnn,int readed,int id,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(id);
          params.add(meId);
          result =  execute(cnn,SQL_UPDATE_READED_FOR_PEOPLE_RECV_ED,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  

    public boolean updateReadedAssignRecv(Connection cnn,FDocAssign beanAssign) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      String SQL=SQL_UPDATE_READED_FOR_PEOPLE_RECV;
      try
      {  
          List params = new ArrayList();          
          params.add(beanAssign.getReaded());
          params.add(beanAssign.getDocId());
          if(beanAssign.getDisposeUser()==null){
              params.add(beanAssign.getWorkflowId());
              params.add(beanAssign.getForYouId()>0?beanAssign.getForyouCreator():beanAssign.getMeId());
              SQL=SQL_UPDATE_READED_FOR_PEOPLE_RECV.replaceAll("#",SQL_SELECT_PEOPLE_PRIORITIES);
          }else{
              String recv="";
              for (int i =0;i<beanAssign.getDisposeUser().length;i++){   
                  String temp = beanAssign.getDisposeUser()[i];
                  String[] value = temp.split("#");
                  if (!recv.equals("")) recv += ",";            
                    recv += value[1]+"";
              }
              
              SQL=SQL_UPDATE_READED_FOR_PEOPLE_RECV.replaceAll("#",recv);
          }
          //.println(SQL);
          result =  execute(cnn,SQL,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    public boolean insertReview(Connection cnn,FSeed seed,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      try
      {
          if(bean.getReviewIds()==null || bean.getReviewIds().equals("")){
              bean.setReviewIds(new DDocRules().getUserPrioritoesByRuleId(cnn,bean.getRuleId()));
          }
            List params = setParams(bean);
            result =  execute(cnn,SQL_INSERT_DOC_REVIEW_SEND,params)>0;
             if (bean.getFileName()!=null && !bean.getFileName().equals("")){
                 int reviewId = getReviewIdTop(cnn,meId);
                 result = false;
                 if (reviewId>0) result = insertReviewFile(cnn,bean,reviewId);
             }
          if(result){
              insertReviewForYou(cnn,bean,meId);
          }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    public boolean insertReviewForYou(Connection cnn,FSeed seed,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      try
      {  
          List params = new ArrayList();
          params.add(bean.getId());
          params.add(new java.sql.Timestamp(System.currentTimeMillis()));                             
          params.add(bean.getTitle());
          params.add(bean.getIssue()==null?"":bean.getIssue());
          params.add(meId);
          params.add(bean.getDeadLine()==null?"":bean.getDeadLine());
          params.add(bean.getReviewIds()==null?"":bean.getReviewIds());
          params.add(bean.getId());
          params.add(meId);
          //.println(SQL_INSERT_DOC_REVIEW_SEND_FORYOU);
          result =  execute(cnn,SQL_INSERT_DOC_REVIEW_SEND_FORYOU,params)>0;
          if (result){
              if (bean.getFileUpload()!=null && bean.getFileUpload().getFileSize()>0){
                  int   reviewId = getReviewIdTop(cnn,meId);
                  result = insertReviewFile(cnn,bean,reviewId);
             }
            // result = updateReadReview(cnn,bean.getId(),meId);
          }
        
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    public boolean insertReviewFile(Connection cnn,FSeed seed,int reviewId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FDocAssign bean = (FDocAssign)seed;
      bean.setReviewId(reviewId);
      try
      {  
         List params = setParamsFile(seed);
         result =  execute(cnn,SQL_INSERT_DOC_REVIEW_FILE_SEND,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
   
    public FDocAssign getInformationReviewRecv(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocAssign beantemp = new FDocAssign();
         try {             
                 beantemp.setReviewId(rs.getInt(DOC_REVIEW_RECV_REVIEW_ID));
                 beantemp.setDocId(rs.getInt(DOC_REVIEW_RECV_DOC_ID));
                 beantemp.setCreator(rs.getInt(DOC_REVIEW_RECV_CREATOR));
                 beantemp.setTimeCreate(beantemp.dateToString(new Date (rs.getTimestamp(DOC_REVIEW_RECV_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
                 beantemp.setTitle(rs.getString(DOC_REVIEW_RECV_TITLE));
                 beantemp.setIssue(rs.getString(DOC_REVIEW_RECV_ISSUE));
                 beantemp.setFileName(rs.getString(DOC_FILE_REVIEW_RECV_FILES));
                 beantemp.setIssue(rs.getString(DOC_REVIEW_RECV_ISSUE));
                 beantemp.setNameCreator(rs.getString(USERS_FULLNAME));
                 beantemp.setDeadLine(rs.getString(DOC_REVIEW_RECV_DEADLINE));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    
    public FDocAssign getInformationReviewSend(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocAssign beantemp = new FDocAssign();
         try {             
                 beantemp.setReviewId(rs.getInt(DOC_REVIEW_SEND_REVIEW_ID));
                 beantemp.setDocId(rs.getInt(DOC_REVIEW_SEND_DOC_ID));
                 beantemp.setCreator(rs.getInt(DOC_REVIEW_SEND_CREATOR));
                 beantemp.setTimeCreate(beantemp.dateToString(new Date (rs.getTimestamp(DOC_REVIEW_SEND_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
                 beantemp.setTitle(rs.getString(DOC_REVIEW_SEND_TITLE));
                 beantemp.setIssue(rs.getString(DOC_REVIEW_SEND_ISSUE));
                 beantemp.setFileName(rs.getString(DOC_FILES_SEND_REVIEW_FILES));
                 beantemp.setPathFile(rs.getString(DOC_FILES_SEND_REVIEW_PATH_FILE));
                 beantemp.setNameCreator(rs.getString(USERS_FULLNAME));
                 beantemp.setForyouCreator(rs.getInt(DOC_REVIEW_SEND_FORYOU_CREATOR));
                 beantemp.setDeadLine(rs.getString(DOC_REVIEW_RECV_DEADLINE));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    
    
    public FDocAssign getInformationAssignSend(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocAssign beanTemp = new FDocAssign();
         try {   
                 beanTemp.setDocId(rs.getInt(DOC_TRAILER_SEND_DOC_ID));
                 beanTemp.setUsersAssign(rs.getInt(DOC_TRAILER_SEND_USERSEND_ID));
                 beanTemp.setUsersRecv(rs.getInt(DOC_TRAILER_SEND_USERRECV_ID));
                 beanTemp.setReaded(rs.getInt(DOC_TRAILER_SEND_READED));
                 beanTemp.setTimeSend(beanTemp.dateToString(new Date (rs.getTimestamp(DOC_TRAILER_SEND_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));                            
                 beanTemp.setSendUserName(rs.getString(USERS_FULLNAME));
                 beanTemp.setForYouId(rs.getInt(DOC_TRAILER_SEND_FORYOU_ID));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beanTemp;
    }
    
    public FDocAssign getInformationRecvSend(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocAssign beanTemp = new FDocAssign();
         try {   
                 beanTemp.setDocId(rs.getInt(DOC_TRAILER_RECV_DOC_ID));
                 beanTemp.setUsersAssign(rs.getInt(DOC_TRAILER_RECV_USERSEND_ID));
                 beanTemp.setUsersRecv(rs.getInt(DOC_TRAILER_RECV_USERRECV_ID));
                 beanTemp.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
                 beanTemp.setTimeSend(beanTemp.dateToString(new Date (rs.getTimestamp(DOC_TRAILER_RECV_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));                            
                 beanTemp.setSendUserName(rs.getString(USERS_FULLNAME));
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beanTemp;
    }
    
   
   
    public List setParams(FDocAssign bean) throws EException
    {
        final String LOCATION = "->setParams()";
        
        List params = new ArrayList();
         try {
             params.add(bean.getDocId());
             params.add(bean.getCreator());                           
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));                             
             params.add(bean.getTitle());
             params.add(bean.getIssue()==null?"":bean.getIssue());
             params.add(0);
             params.add(bean.getDeadLine()==null?"":bean.getDeadLine());
             params.add(bean.getReviewIds()==null?"":bean.getReviewIds());
             
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    
    public List setParamsFile(FSeed seed) throws EException
    {
        final String LOCATION = "->setParamsFile()";
        FDocAssign bean = (FDocAssign)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getReviewId());
             params.add("filex");                           
             params.add(bean.getFileName());
             params.add(bean.getPathFile());
                                                  
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    public boolean insertMe(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;     
      FDocssend bean = (FDocssend)seed;
      try
      {  
      String SQL_INSERT_DOC_TRAILERS_SEND = INSERT_INTO + TABLE_DOC_TRAILER_SEND + FIELDS(DOC_TRAILER_SEND_ALL_FIELDS, true) +  VALUES(DOC_TRAILER_SEND_ALL_FIELDS.length);
      List params = new ArrayList();
      params.add(bean.getId());
      params.add(bean.getUserId()); 
      params.add(1);
      params.add(new java.sql.Timestamp(System.currentTimeMillis()));                    
      params.add(0);
      params.add(0);
      params.add(AppConfigs.STATUS_NEW);
      params.add(0);
      params.add(bean.getUserId());    
        result=execute(cnn,SQL_INSERT_DOC_TRAILERS_SEND ,params)>0;  
      }
      catch(Exception sqle)
      {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    } 
  
    
}
