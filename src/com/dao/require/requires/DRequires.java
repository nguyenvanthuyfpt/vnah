package com.dao.require.requires;


import com.dao.require.DSqlRequire;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.groups.FGroup;
import com.form.admin.require.trailer.FRequireTrailer;
import com.form.admin.users.FUser;
import com.form.require.requires.FRequire;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


public class DRequires  extends DSqlRequire
{
    public int getTotalWaitRecv(Connection cnn,long userId) throws EException{
      final String LOCATION = this.toString() + "getTotalWaitRecv()";
      
      PreparedStatement prstm = null;
      int result = 0;
      ResultSet rs = null;
      try{
        List params = new ArrayList();
        String SQL=SQL_AMOUNT_RM_WAIT;       
        params.add(userId);
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery(); 
        if((rs != null) && (rs.next())){            
            result =   rs.getInt(PARAM_01);                
        }
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return result;        
    }
    
    
    public int getTotalRMByEmpRecv(Connection cnn,long userId) throws EException{
      final String LOCATION = this.toString() + "getTotalRMByEmpRecv()";
      
      PreparedStatement prstm = null;
      int result = 0;
      ResultSet rs = null;
      try{
        List params = new ArrayList();
        String SQL=SQL_AMOUNT_RM_ALL;       
        params.add(userId);
        prstm = prepareStatement(cnn,SQL,params); 
        rs = prstm.executeQuery();                                
       
        if((rs != null) && (rs.next())){            
            result =   rs.getInt(PARAM_01);                
        }
      }catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally{
        closeResultSet(rs);
        closePreparedStatement(prstm);
      }
      return result;        
    }
    
    
    public FBeans getRmByStatus(Connection cnn,FRequire beanRm) throws EException{
      final String LOCATION = this.toString() + "getRmByStatus()";
      FBeans beans = null;
      String SQL=SQL_SQL_AMOUNT_RM_BY_STATUS;
      PreparedStatement prstm = null;
      FRequire bean=null;    
      ResultSet rs = null;
      try{
          
        if(beanRm.getRmStatusIds()==null || beanRm.getRmStatusIds().equals("")){
          beanRm.setRmStatusIds("1000");
        }
        List params = new ArrayList();       
        SQL=SQL.replaceAll("#",beanRm.getRmStatusIds());
        params.add(beanRm.getCreator());
        //////.println(SQL + ORDER_BY + TABLE_RM_STATUS + STOP + RM_STATUS_STATUS_ID + ASC);
        prstm = prepareStatement(cnn,SQL + ORDER_BY + TABLE_RM_STATUS + STOP + RM_STATUS_STATUS_ID + ASC,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL,params));
        while((rs != null) && (rs.next())){
            bean =new FRequire();            
            bean.setRmStatus(rs.getInt(RM_STATUS_STATUS_ID));
            bean.setName(rs.getString(STATUS_NAME));
            bean.setAmount(rs.getInt("AMOUNT"));           
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
    
    
    public FBeans getRmByStatusObserver(Connection cnn,FRequire beanRm) throws EException{
      final String LOCATION = this.toString() + "getRmByStatus()";
      FBeans beans = null;
      String SQL=SQL_SQL_AMOUNT_RM_BY_STATUS_OB_SERVER;
      PreparedStatement prstm = null;
      FRequire bean=null;    
      ResultSet rs = null;
      try{
          
        if(beanRm.getRmStatusIds()==null || beanRm.getRmStatusIds().equals("")){
          beanRm.setRmStatusIds("1000");
        }
        List params = new ArrayList();       
        SQL=SQL.replaceAll("#",beanRm.getRmStatusIds());
        params.add(1);//readed
        ////.println(SQL + ORDER_BY + TABLE_RM_STATUS + STOP + RM_STATUS_STATUS_ID + ASC);
        prstm = prepareStatement(cnn,SQL + ORDER_BY + TABLE_RM_STATUS + STOP + RM_STATUS_STATUS_ID + ASC,params); 
        rs = prstm.executeQuery();                                
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL,params));
        while((rs != null) && (rs.next())){
            bean =new FRequire();            
            bean.setRmStatus(rs.getInt(RM_STATUS_STATUS_ID));
            bean.setName(rs.getString(STATUS_NAME));
            bean.setAmount(rs.getInt("AMOUNT"));           
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
    
    
    public boolean updateReadedReply(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          FRequire bean = (FRequire)seed;
          updateReadedMe(cnn,bean);          
          if (bean.getUserReply()>0){
              updateReadedRecvReply(cnn,bean); 
          }
         
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public boolean updateReadedRecvReply(Connection cnn,FRequire bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try{ 
          List params = new ArrayList();                 
          params.add(0);
          params.add(bean.getRmId());        
          params.add(bean.getUserReply());                        
          result =  execute(cnn,SQL_UPDATE_READED_RM_REPLY,params)>0;//DA SUA        
          
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    } 
    
    public String getUserPrioritoesByRmId(Connection cnn, int ruleId, int departmentId) throws EException
    {
        final String LOCATION = this.toString() + "getUserPrioritoesByRmId()";
        PreparedStatement prstm = null;
        String usersRecv ="";
        ResultSet rs = null;
        try
        {
          String SQL = SQL_SELECT_RM_EMP_PRI;          
          if (departmentId>0){
              SQL = SELECT + TABLE_USERS + STOP + USERS_USER_ID + FROM + TABLE_USERS  + WHERE  + TABLE_USERS + STOP + USERS_USER_ID + IN + OPEN  + SQL_SELECT_RM_EMP_PRI +  CLOSE + AND + USERS_DEPARTMENT_ID + EQUAL + QUESTION ;
          }
          //////.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,ruleId);    
          if (departmentId>0){
              prstm.setLong(PARAM_02,departmentId);    
          }
          rs = prstm.executeQuery();       
          while((rs != null) && rs.next()){ 
            if (!usersRecv.equals("")) usersRecv += ",";            
              usersRecv += rs.getInt(PARAM_01);
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
    public boolean insertReview(Connection cnn,FSeed seed,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FRequire bean = (FRequire)seed;
      try
      {  
          if(bean.getReviewIds()==null){
              bean.setReviewIds(getUserPrioritoesByRmId(cnn,bean.getRmRuleId(),bean.getDepartmentId()));
          }
          List params = setParamsReview(bean);
          result =  execute(cnn,SQL_INSERT_RM_REVIEW,params)>0;                         
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    public FBeans getAllReview(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllReview()";
      PreparedStatement prpstm = null;
      FRequire   bean = (FRequire)seed;
      ResultSet rs = null;
      FBeans beans = null;
      String SQL_SELECT=SQL_SELECT_ALL_RM_REVIEW_PUPLIC;    
      try
      {            
        List params = new ArrayList();
        if(bean.getObServer()>0){//xem mo rong
          params.add(bean.getRmId());
        }else{//xem ca nhan
          SQL_SELECT=SQL_SELECT_ALL_RM_REVIEW_RECV_PRIVATE;
          params.add(bean.getRmId());
          params.add(bean.getCreator());
          params.add(PER_CENT + "|" + bean.getCreator() + "|" + PER_CENT);
        }        
          ////.println(SQL_SELECT);
          prpstm = prepareStatement(cnn,SQL_SELECT,params);   
          rs = prpstm.executeQuery();
          beans = new FBeans();       
          FRequire beantemp = null;
          int block=0;
          while(rs != null && rs.next()){          
              beantemp = new FRequire();
              beantemp = getInformationReview(rs);                        
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
    
    public FBeans getRMRecvByRmId(Connection cnn, FRequire bean,int userId) throws EException 
    {
      final String LOCATION = this.toString() + "getDocRecvByDocId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try
      {
          String SQL = SQL_SELECT_RM_TRAILER_MOVE;
          List params =new ArrayList();
          params.add(bean.getRmId());
          if (bean.getObServer()==0){
              SQL += SQL_SELECT_RM_TRAILER_MOVE_BY_CRAETOR ;
              params.add(userId);params.add(userId);params.add(userId);
          }
          ////.println(SQL);
          prpstm=prepareStatement(cnn,SQL,params);
          rs = prpstm.executeQuery();
          beans = new FBeans();
          String time = "";
          FRequire beanRequireSend =null;
          FRequire beanRequireRecv =null;
        while(rs != null && rs.next()){
            long now = rs.getTimestamp(RM_TRAILER_TIMESEND).getTime();
            if(!time.equals(rs.getInt(RM_TRAILER_USERSEND_ID) + "|" + now)){
                beanRequireSend =new FRequire();
                beanRequireSend=getInformationRequire(rs);
                if(beanRequireSend!=null) beans.add(beanRequireSend);
            }else{
                beanRequireRecv =new FRequire();
                beanRequireRecv=getInformationRequire(rs);
                if(beanRequireRecv!=null) beanRequireSend.setRecvUser(beanRequireRecv);
            }
            time = beanRequireSend.getUserSend()+ "|" +now;
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
    public FBeans getUserByDepartmentId(Connection cnn, FSeed seed,int depId,int groupId) throws EException
    {
        final String LOCATION = this.toString() + "getUserByGroupId()";
        FBeans beans = new FBeans();       
        PreparedStatement prstm = null;
        FRequireTrailer beanC = (FRequireTrailer)seed;
        ResultSet rs = null;
        try{           
        String SQL =SELECT +  TABLE_USERS + STOP + USERS_USER_ID + COMMA + TABLE_USERS + STOP + USERS_FULLNAME +  FROM  + TABLE_USERS + WHERE + USERS_USER_ID + IN + OPEN + SELECT +  TABLE_RM_RULE_OFFICE + STOP + RM_RULE_OFFICE_USER_ID +  FROM +  TABLE_RM_RULE_OFFICE +  LEFT_JOIN +  TABLE_RM_RULE_TRAILER + ON + TABLE_RM_RULE_OFFICE + STOP + RM_RULE_OFFICE_REQUIRE_RULE_ID + EQUAL  + TABLE_RM_RULE_TRAILER + STOP + RM_RULE_TRAILER_RULE_ID + LEFT_JOIN + TABLE_RM_RULE_BOSS + ON + TABLE_RM_RULE_OFFICE + STOP + RM_RULE_OFFICE_REQUIRE_RULE_ID + EQUAL + TABLE_RM_RULE_BOSS +STOP + RM_RULE_BOSS_REQUIRE_RULE_ID +  WHERE +  TABLE_RM_RULE_BOSS + STOP + RM_RULE_BOSS_USER_ID + EQUAL + QUESTION +  AND + TABLE_RM_RULE_TRAILER + STOP + RM_RULE_TRAILER_ACTIVE + DIFF + 0 +  AND +  TABLE_RM_RULE_TRAILER + STOP + RM_RULE_TRAILER_RULE_ID + EQUAL + QUESTION +  CLOSE ;
        List params =new ArrayList();
        params.add(beanC.getCreator());
        params.add(beanC.getRuleId());
        if(groupId>0){
            SQL+= AND +  USERS_GROUP_ID + EQUAL + QUESTION;
            params.add(groupId);
        }
        if(depId>0){
            SQL+= AND +  USERS_DEPARTMENT_ID + EQUAL + QUESTION;
            params.add(depId);
        }
        
          prstm = prepareStatement(cnn,SQL,params);   
          rs = prstm.executeQuery();   
         FUser bean= null;
          while((rs != null) && rs.next()){
              bean = new FUser();            
              bean.setId(rs.getInt(USERS_USER_ID));
              bean.setFullName(rs.getString(USERS_FULLNAME));             
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
    
    public FBeans getAllGroupsByRule(Connection cnn,FRequireTrailer bean) throws EException
    {
        final String LOCATION = this.toString() + "getAllGroupsByRule()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          String SQL = SQL_GROUP_SELECT_ALL_GROUP_IN_RM_RULE + SQL_GROUP_ADD_WHERE_ID_BY_RM_RULE.replaceAll("#",SQL_GROUP_SELECT_ADD_WHERE_IN_RM_RULE) ;
          //.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,bean.getCreator());
          prstm.setLong(PARAM_02,bean.getRuleId());
          rs = prstm.executeQuery();       
          FGroup beanTemp = null;
          while((rs != null) && rs.next()){ 
               beanTemp = new FGroup();
               beanTemp.setId(rs.getInt(GROUPS_GROUP_ID));
               beanTemp.setName(rs.getString(GROUPS_NAME));
               beanTemp.setParentID(rs.getInt(GROUPS_PARENT_ID));                
               beans.add(beanTemp);
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
    
    public FBeans getAllRecordByRule(Connection cnn,FRequireTrailer bean) throws EException
    {
      final String LOCATION = this.toString() + "getAllRecordByRule()";
      FBeans beans = null;
      PreparedStatement prstm = null;
      ResultSet rs = null;
     
      try
      {
           
           
            prstm = cnn.prepareStatement(SQL_DEPARTMENT_BY_RM_RULE);
            //////.println(SQL_DEPARTMENT_BY_RM_RULE);
            prstm.setLong(PARAM_01,bean.getCreator());
            prstm.setInt(PARAM_02,bean.getRuleId());            
            rs = prstm.executeQuery();  
            FDepartment beantemp = null;
            beans = new FBeans();
            while((rs != null) && rs.next()) {
                beantemp = new FDepartment();
                beantemp.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
                beantemp.setName(rs.getString(DEPARTMENTS_NAME));                
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
        closePreparedStatement(prstm);
      }
      return beans;        
    }
    public FRequire getTopId(Connection cnn,long userId) throws EException 
    {
      final String LOCATION = this.toString() + "getTopId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FRequire beantemp = new FRequire();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_RM_BY_TOP);
          prpstm.setLong(PARAM_01,userId);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
                beantemp.setRmId(rs.getInt(PARAM_01));
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
    
    public boolean insertDirect(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;      
      FRequire   bean = (FRequire)seed;
      boolean result =false;
      try
      { 
          
          String SQL_INSERT_DOC_TRAILERS = bean.getDepartmentId()>0?SQL_INSERT_RM_TRAILER.replaceAll("#",SQL_SELECT_ALL_USER_PRIO_ADD_BY_DEPARTMENT_ID_RM):SQL_INSERT_RM_TRAILER.replaceAll("#",SQL_SELECT_ALL_USER_PRIO_RM);                 
          ////.println(SQL_INSERT_DOC_TRAILERS);
          List params = new ArrayList();
          params.add(bean.getRmId());
          params.add(bean.getCreator()); 
          params.add(0); 
          params.add(new java.sql.Timestamp(System.currentTimeMillis()));                           
          params.add(bean.getRmStatus());
          if (bean.getDepartmentId()>0){
              params.add(bean.getDepartmentId());                           
          }
          params.add(bean.getCreator());           
          params.add(bean.getRmId());                    
          result=execute(cnn,SQL_INSERT_DOC_TRAILERS,params)>0;           
          updateReadedMe(cnn,bean);
          updateReadedRecv(cnn,bean);
          if (result) {            
              updateStatusRm(cnn,seed);          
          }
      }
        catch(Exception sqle)
        {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
        }    
      return result;
    }
    
    public boolean updateReadedRecv(Connection cnn,FRequire beanRm) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      String SQL=SQL_UPDATE_READED_RM_EMP_RECV;
      try
      {  
          List params = new ArrayList();          
          params.add(0);
          params.add(beanRm.getRmId());
          if(beanRm.getMembers()==null || beanRm.getMembers().equals("")){            
              params.add(beanRm.getCreator());
              SQL=SQL.replaceAll("#",SQL_SELECT_RM_EMP_PRIORITIES);
          }else{
              String recv="";
              String[] value = beanRm.getMembers().split(",");
              for (int i =0;i<value.length;i++){                  
                    if (value[i]!=null){             
                              if (!recv.equals("")) recv += ",";            
                                    recv += Integer.parseInt(value[i].split("#")[1]);
                              
                     }
              }
              SQL=SQL.replaceAll("#",recv.equals("")?"0":recv);
          }
          //////.println(SQL);
          result =  execute(cnn,SQL,params)>0;                   
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    } 
    
    public boolean updateStatusRm(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      FRequire bean = (FRequire)seed;
      try
      {  
         List params = new ArrayList(); 
         params.add(bean.getRmStatus());
         params.add(bean.getRmId());                 
         result =  execute(cnn,SQL_UPDATE_RM_REQUIRE,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    public boolean updateReadedMe(Connection cnn,FRequire bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(1);
          params.add(bean.getRmId());         
          params.add(bean.getCreator());
          result =  execute(cnn,SQL_UPDATE_TRAILER_READED_FOR_ME,params)>0;//da sua                   
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }
    
    public boolean insertMe(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FRequire bean = (FRequire)seed;
      try
      {  
        String SQL_INSERT_RM_TRAILERS = INSERT_INTO + TABLE_RM_TRAILER + FIELDS(RM_TRAILER_ALL_FIELDS, true) +  VALUES(RM_TRAILER_ALL_FIELDS.length);
        List params = new ArrayList();
        params.add(bean.getRmId());
        params.add(bean.getUserSend());            
        params.add(1);
        params.add(new java.sql.Timestamp(System.currentTimeMillis()));                           
        params.add(AppConfigs.RM_STATUS_NEW);
        params.add(bean.getUserSend());   
        result=execute(cnn,SQL_INSERT_RM_TRAILERS,params)>0;  
      }
      catch(Exception sqle)
      {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;
    } 
    
    public FBeans getAllDepartmentPri(Connection cnn, long userId,int idDepartment) throws EException
    {
        final String LOCATION = this.toString() + "getAllDepartmentPri()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try
        {
          String SQL = SQL_DEPARTMENT_SELECT_ALL_DEPARTMENT_IN_RULE + SQL_DEPARTMENT_ADD_WHERE_ID_BY_RULE.replaceAll("#",SQL_DEPARTMENT_SELECT_ADD_WHERE_IN_RM_RULE_PRIORITIES) ;        
          ////.println(SQL);
          prstm = cnn.prepareStatement(SQL);
          prstm.setLong(PARAM_01,userId);       
          rs = prstm.executeQuery();       
          FDepartment bean = null;
          while((rs != null) && rs.next()){ 
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
  
    
    
    public int selectExitsListId(Connection conn, int listId)throws EException{
    final String LOCATION = "->selectExitsListId()";
    int result=0; 
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          String SQl =""; //SQL_SELECT_LIST_ID_REQUIRE
          pstmt = conn.prepareStatement(SQl);                  
          pstmt.setInt(PARAM_01,listId);
          rs = pstmt.executeQuery();
          if ( rs!=null && rs.next()){
              result=1;
          }
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
     
     
    public boolean isExist(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FRequire bean = (FRequire)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          String SQl =""; //SQL_SELECT_REQUIRES_INFORMATION
          pstmt = conn.prepareStatement(SQl);         
          pstmt.setString(PARAM_01,bean.getCode());
          //pstmt.setInt(PARAM_02,bean.getId());
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
    public boolean isExistListId(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExistListId()";
    boolean result = false;
    FRequire bean = (FRequire)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          String SQl =""; //SQL_SELECT_REQUIRES__ISEXIT_LIST_ID
          pstmt = conn.prepareStatement(SQl);                 
        //  pstmt.setInt(PARAM_01,bean.getListId());
          rs = pstmt.executeQuery();
          int kq=rs.getInt(1);result=kq>0?true:false;          
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
    public boolean isExistListIdEdit(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExistListIdEdit()";
    boolean result = false;
    FRequire bean = (FRequire)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          String SQl =""; //SQL_SELECT_REQUIRES__ISEXIT_LIST_ID_EDIT 
          pstmt = conn.prepareStatement(SQl);                 
        //  pstmt.setInt(PARAM_01,bean.getListId());
         // pstmt.setInt(PARAM_02,bean.getId());
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
    public FRequire getRecordByCode(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getRecordByCode()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FRequire bean = new FRequire();
      bean = (FRequire)seed;
      try
      {
          String SQl =""; //SQL_SELECT_REQUIRES_BY_CODE 
        prstm = cnn.prepareStatement(SQl);
        prstm.setString(PARAM_01,bean.getCode());
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
           // bean = getInformation(cnn,rs);
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

    public FRequire getById(Connection cnn,FRequire bean) throws EException
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FRequire beanTemp = new FRequire();    
      try
      {  
       String SQL = SQL_REQUIRES_SELECT_BY_ID;
       if (bean.getObServer()==0){
           SQL +=  SQL_SELECT_REQUIRES_BY_CREATOR ;
       }
        prstm = cnn.prepareStatement(SQL);        
        prstm.setInt(PARAM_01,bean.getRmId());
        if (bean.getObServer()==0){
            prstm.setLong(PARAM_02,bean.getCreator());
        }
        rs = prstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
            beanTemp = getInformation(rs,true);
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
    
   
    
    public boolean insert(Connection cnn,FSeed seed,int meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      FRequire bean = (FRequire)seed;
      PreparedStatement prstm = null;
      try
      {  
              prstm = cnn.prepareStatement(SQL_INSERT_RM_TRAILER_EMP_SELECT);
              String[] value = bean.getMembers().split(",");
              prstm.setTimestamp(PARAM_04,new java.sql.Timestamp(System.currentTimeMillis()));  
                for (int i =0;i<value.length;i++){                
                  if (value[i]!=null && !value[i].equals("")){
                      if(!checkAddBatch(cnn,bean.getRmId(),Integer.parseInt(value[i].split("#")[1]))){
                        prstm.setInt(PARAM_01,bean.getRmId());
                        prstm.setLong(PARAM_02,bean.getCreator());                         
                        prstm.setInt(PARAM_03,0); 
                        prstm.setInt(PARAM_05,bean.getRmStatus());                           
                        prstm.setInt(PARAM_06,Integer.parseInt(value[i].split("#")[1]));   
                        prstm.addBatch();   
                      }
                  }           
                }  
                
                result=prstm.executeBatch().length>0; 
                updateReadedMe(cnn,bean);
            if (result) {                                        
                   updateStatusRm(cnn,seed);
                   updateReadedRecv(cnn,bean);
               }else if (AppConfigs.DOC_READ_EXCUTE==1){
                   bean.setReaded(0);                
                  updateReadedRecv(cnn,bean);
               }
                    
           
          }
          catch(Exception sqle)
          {
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
          }
          return result;
    } 
    public boolean checkAddBatch(Connection cnn, int rmId,int userRecv_id) throws EException
    {
        final String LOCATION = this.toString() + "checkAddBatch()";
        PreparedStatement prstm = null;
        boolean result =false;
        ResultSet rs = null;
        try
        {
          prstm = cnn.prepareStatement(SQL_CHECK_RM_TRAILER_ADDBATCH);
          prstm.setLong(PARAM_01,rmId);         
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
    public boolean AddNew(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      PreparedStatement prstm = null;     
      try
      {
          List params = setParams(seed);
          prstm = prepareStatement(cnn,SQL_REQUIRES_ADD_NEW,params);
          result = prstm.executeUpdate()>0 ;
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
        FRequire bean = (FRequire)seed;
        List params = setParams(seed);
      //  params.add(bean.getId());
        String SQL ="" ;//SQL_UPDATE_REQUIRES_REQUIRETYPE
        prstm = cnn.prepareStatement(SQL);
        //print(SQL_UPDATE_REQUIRES_REQUIRETYPE);
        
        for(int i=0;i<params.size();i++){
          if(params.get(i)==null){
            prstm.setNull(PARAM_01+i,Types.DATE);
          }else{
            prstm.setObject(PARAM_01+i,params.get(i));
          }
        }
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
    
    
    
    public boolean updateStatusStore(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;        
        try{ 
            
            FRequire bean = (FRequire)seed;
            List params = new ArrayList();
            params.add(bean.getRmStatusStore());
            params.add(bean.getRmId());       
            result=execute(cnn,SQL_UPDATE_REQUIRE_STATUS,params)>0;  
            updateStatusTrailer(cnn,seed);
            
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
    
    public boolean updateStatusTrailer(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;        
        try{ 
            
            FRequire bean = (FRequire)seed;
            List params = new ArrayList();
            params.add(bean.getRmStatusStore());
            params.add(1);
            params.add(bean.getRmId());       
            result=execute(cnn,SQL_UPDATE_REQUIRE_TRAILER_STATUS,params)>0;                 
            
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
//
    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + DELETE;
      boolean result = false;
      FRequire bean = (FRequire)seed;
      result =delete(cnn, TABLE_RM_TRAILER, RM_TRAILER_RM_ID + EQUAL + bean.getRmId())>0;          
      delete(cnn, TABLE_RM_REQUIRE, RM_REQUIRE_ID + EQUAL + bean.getRmId());      
      return result;
//             
    } 
    
 public boolean checkDeleteRm(Connection cnn, int rmId) throws EException
 {
     final String LOCATION = this.toString() + "checkDeleteRm()";
     PreparedStatement prstm = null;
     boolean result =false;
     ResultSet rs = null;
     try
     {
       prstm = cnn.prepareStatement(SQL_CHECK_RM_TRAILER_DELETE);
       prstm.setLong(PARAM_01,rmId);              
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

    public FBeans getAllRequires(Connection cnn,FRequire bean) throws EException {
      final String LOCATION = this.toString() + "getAllRequires()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try{      
         
        String SQL = SQL_REQUIRES_SELECT_ALL;
        List params= new ArrayList();
        params.add(bean.getCreator());         
        if (bean.getRmStatus()>0){
            SQL += AND + TABLE_RM_REQUIRE + STOP + RM_REQUIRE_RM_STATUS_ID + EQUAL + QUESTION;  
            params.add(bean.getRmStatus());
        }else if (bean.getRmStatus()==0){
            SQL += AND + TABLE_RM_TRAILER + STOP + RM_TRAILER_READED + EQUAL + QUESTION;  
            params.add(0);
        }
        ////.println(SQL + SQL_REQUIRES_WHERE);
        prpstm = prepareStatement(cnn,SQL + SQL_REQUIRES_WHERE,params);         
        prpstm.setFetchSize((1+bean.getPagesIndex())*AppConfigs.APP_ROWS_VIEW);
        prpstm.setMaxRows((1+bean.getPagesIndex())*AppConfigs.APP_ROWS_VIEW);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if(bean.getPagesIndex()>0){
          beans.setTotalRows(count(cnn,SQL,params));
          beans.setPageIndex(bean.getPagesIndex());
          if(beans.getFirstRecord()<=1){
          rs.beforeFirst();
          }else{
          rs.absolute(beans.getFirstRecord()-1);
          }
        }
        int i=0;
        while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW))
        {
            i++;
            beans.add(getInformation(rs,true));
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
    
    
    public FBeans getAllRequiresObserver(Connection cnn,FRequire bean) throws EException {
      final String LOCATION = this.toString() + "getAllRequiresObserver()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try{        
        String WHERE_SELCT= WHERE + TRUE;
        String SQL = SQL_TABLE_ALL_REQUIRE_OBSERVER;
        List params= new ArrayList();
        if (bean.getRmStatus()>0){
            WHERE_SELCT += AND + TABLE_RM_REQUIRE + STOP + RM_REQUIRE_RM_STATUS_ID + EQUAL + QUESTION;  
            params.add(bean.getRmStatus());
        }
        ////.println(SQL + WHERE_SELCT  + ORDER_BY + RM_REQUIRE_RM_DATECREATE + DESC);
        prpstm = prepareStatement(cnn,SQL + WHERE_SELCT  + ORDER_BY + RM_REQUIRE_RM_DATECREATE + DESC,params);         
        prpstm.setFetchSize((1+bean.getPagesIndex())*AppConfigs.APP_ROWS_VIEW);
        prpstm.setMaxRows((1+bean.getPagesIndex())*AppConfigs.APP_ROWS_VIEW);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if(bean.getPagesIndex()>0){
          beans.setTotalRows(count(cnn,SQL,params));
          beans.setPageIndex(bean.getPagesIndex());
          if(beans.getFirstRecord()<=1){
          rs.beforeFirst();
          }else{
          rs.absolute(beans.getFirstRecord()-1);
          }
        }
        int i=0;
        while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW))
        {
            i++;
            beans.add(getInformation(rs,true));
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

   
 public FRequire getInformationRequire(ResultSet rs) throws EException
 {
     final String LOCATION = "->getInformationRecvSend()";
     FRequire beanTemp = new FRequire();
      try {   
              beanTemp.setRmId(rs.getInt(RM_TRAILER_RM_ID));
 
              beanTemp.setTimeCreate(beanTemp.dateToString(new Date (rs.getTimestamp(RM_TRAILER_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));                            
              beanTemp.setUserSend(rs.getInt(RM_TRAILER_USERSEND_ID)); 
              beanTemp.setUserRecv(rs.getInt(RM_TRAILER_USERRECV_ID));
              beanTemp.setReaded(rs.getInt(RM_TRAILER_READED));
              beanTemp.setUserSendName(rs.getString("USERSEND"));
              beanTemp.setUserRecvName(rs.getString("USERRECV"));
              beanTemp.setRmStatusName(rs.getString(RM_STATUS_NAME));
      }
      catch (SQLException sqle) {            
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
      }
      finally {
      }
      return beanTemp;
 }
     
    public FRequire getInformationReview(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FRequire beantemp = new FRequire();
         try {             
                 beantemp.setReviewId(rs.getInt(RM_REVIEW_REVIEW_ID));
                 beantemp.setRmId(rs.getInt(RM_REVIEW_RM_ID));
                 beantemp.setCreator(rs.getInt(RM_REVIEW_CREATOR));
                 beantemp.setCreatorName(rs.getString(USERS_FULLNAME));
                 beantemp.setTimeCreate(beantemp.dateToString(new Date (rs.getTimestamp(RM_REVIEW_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
                 beantemp.setTitle(rs.getString(RM_REVIEW_TITLE));
                 beantemp.setIssue(rs.getString(RM_REVIEW_ISSUE));                                 
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    } 
      
    public FRequire getInformation(ResultSet rs,boolean check) throws EException{
    
        final String LOCATION = "->getInformation()";
        FRequire require = new FRequire();              
        try {
           require.setRmId(rs.getInt(RM_REQUIRE_ID));
           require.setCode(rs.getString(RM_REQUIRE_RM_CODE));
           require.setName(rs.getString(RM_REQUIRE_RM_TITLE));
           require.setDatetimto(require.dateToString(rs.getDate(RM_REQUIRE_RM_DATETO)));
           require.setDatetimFrom(require.dateToString(rs.getDate(RM_REQUIRE_RM_DATEFROM)));
           require.setTimto(rs.getString(RM_REQUIRE_RM_TIMETO));
           require.setTimFrom(rs.getString(RM_REQUIRE_RM_TIMEFROM));
           require.setDateline(require.dateToString(rs.getDate(RM_REQUIRE_RM_DATELINE)));
           require.setDayOfWeek(rs.getString(RM_REQUIRE_RM_WEEK_DAY));
           require.setRepply(rs.getInt(RM_REQUIRE_RM_REPLY));
           require.setCreator(rs.getLong(RM_REQUIRE_RM_CREATOR));
           require.setActive(rs.getInt(RM_REQUIRE_RM_ACTIVE));
           require.setContent(rs.getString(RM_REQUIRE_RM_CONTENT));
           require.setRmStatus(rs.getInt(RM_REQUIRE_RM_STATUS_ID));
           require.setCateId(rs.getInt(RM_REQUIRE_RM_CATEGORY_ID));           
           require.setDateCreate(require.dateToString(new Date (rs.getTimestamp(RM_REQUIRE_RM_DATECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
           require.setReaded(rs.getInt(RM_TRAILER_READED));      
           require.setRmStatusName(rs.getString("RM_STATUSNAME"));
           if (check){
           require.setCategoryName(rs.getString("RMCATNAME"));
           //require.setDepartmentName(rs.getString("DEPARTMENTSNAME"));
           require.setCreatorName(rs.getString("USERSFULLNAME"));  
           }
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return require;
    }
    public List setParamsReview(FRequire bean) throws EException
    {
        final String LOCATION = "->setParams()";        
        List params = new ArrayList();
         try {
             params.add(bean.getRmId());
             params.add(bean.getCreator());
             params.add(bean.getReaded());
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));                             
             params.add(bean.getTitle());
             params.add(bean.getIssue()==null?"":bean.getIssue());        
             params.add(bean.getReviewIds());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FRequire bean = (FRequire)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getCode());
             params.add(bean.getName());
             params.add(bean.stringToSqlDate(bean.getDatetimto()));
             params.add(bean.stringToSqlDate(bean.getDatetimFrom()));
             params.add(bean.getTimto());
             params.add(bean.getTimFrom());
             params.add(bean.getDayOfWeek());
             params.add(bean.getRepply());
             params.add(bean.getDepartmentId());
             params.add(bean.getCateId());
             params.add(bean.getRmStatus());
             params.add(bean.getContent());            
             params.add(bean.getActive());
             params.add(bean.getCreator());
             params.add(bean.stringToSqlDate(bean.getDateCreate()));
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
   public boolean checkRole(int pr, int key_id){       
          return (pr>0 && (key_id==(pr & key_id)))?true:false;}
}
