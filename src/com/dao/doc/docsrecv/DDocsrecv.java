package com.dao.doc.docsrecv;


import com.bo.admin.departments.BDepartments;
import com.bo.admin.doc.category.doctype.BDocType;

import com.dao.doc.assign.DAssignRecv;
import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.admin.departments.FDepartment;
import com.form.admin.doc.category.doctype.FDocType;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docsrecv.FFilesRecv;

import com.inf.IKey;
import com.inf.doc.IKeyDoc;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDocsrecv extends DSqlForYou
{
    public boolean isExistAdd(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FDocsrecv bean = (FDocsrecv)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_DOCSRECV_CHECK_CODE_ADD);    
          pstmt.setString(PARAM_01,bean.getDocCode());//
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
    public boolean isExistUpdate(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FDocsrecv bean = (FDocsrecv)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
    
     try {
                pstmt = conn.prepareStatement(SQL_DOCSRECV_CHECK_CODE_UPDATE);  
                pstmt.setInt(PARAM_01,bean.getId());
                pstmt.setString(PARAM_02,bean.getDocCode());
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
    
     public FBeans searchStore(Connection cnn,FSeed seed,int userId) throws EException
     {
       final String LOCATION = this.toString() + "~~>searchStore()";
       PreparedStatement prpstm = null;
       FDocsrecv bean=(FDocsrecv)seed;
       ResultSet rs = null;
       FBeans beans = null;
       String SELECT_WHERE="";
       String SQL_SELECT=SQL_SELECT_ALL_DEPARTMENT_BY_SEARCH;
       List params =new ArrayList();       
           if(bean.getStoreArea()!=null && !bean.getStoreArea().equals("")){
               SELECT_WHERE+="" +  AND + DEPARTMENTS_NAME + LIKE + QUESTION ;
               params.add(bean.getStoreArea() + PER_CENT);               
           }
         SQL_SELECT+=SELECT_WHERE;                 
       try
       {
       prpstm = prepareStatement(cnn,SQL_SELECT+ORDER_BY + DEPARTMENTS_NAME,params); 
       rs = prpstm.executeQuery();
       beans = new FBeans();
       FDepartment beanTemp=null;
       int i=0;
       while((rs != null) && (rs.next()) && (i<=20)){ 
               i++;  
               beanTemp = new FDepartment();
               beanTemp.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
               beanTemp.setName(rs.getString(DEPARTMENTS_NAME));
               beanTemp.setCode(rs.getString(DEPARTMENTS_CODE));
               beanTemp.setParentID(rs.getInt(DEPARTMENTS_PARENT_ID));              
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
         closePreparedStatement(prpstm);
       }
       return beans;
     }
    
  public FBeans getAllDocsrecv(Connection cnn,FSeed seed,int userId,int pageIndex,String statused,int checkWait) throws EException
  {
    final String LOCATION = this.toString() + "~~>getAllDocsrecv()";
    PreparedStatement prpstm = null;
    FDocsrecv bean=(FDocsrecv)seed;
    ResultSet rs = null;
    FBeans beans = null;
    String SELECT_WHERE=WHERE + TRUE;
    String SQL_SELECT=SELECT + STAR + FROM + (SQL_TABLE_ALL_DOCSRECV);
    List params =new ArrayList();
    params.add(userId);
    if(pageIndex>0){
        
     if(bean.getClassifyId()>0){ 
     
            SELECT_WHERE+="" +  AND + DOCSRECV_CLASSIFY_ID + EQUAL + QUESTION;
            params.add(bean.getClassifyId());
      }
     
      if(bean.getStatusId()==IKeyDoc.DOC_STATUS_VIEW_ALL){ 
             String SQL_TEMP = "";
             if (AppConfigs.DOC_STATUS_COUNT_NOT_IN==1){
                  SQL_TEMP+=" "+  AND + TABLE_DOCSRECV + STOP + DOCSRECV_STATUS_ID + NOT + IN + OPEN  + ((statused!=null && !statused.equals(""))?statused:"100000") + CLOSE ;
                  if (checkWait==1){
                     SQL_TEMP+=  AND + TABLE_DOC_TRAILER_RECV + STOP + DOC_TRAILER_RECV_READED + DIFF + IKey.STATUS_UNREAD;
                  } 
                 SQL_SELECT =SQL_SELECT.replaceAll("#",SQL_TEMP); 
             }  else {
                 SQL_SELECT =SQL_SELECT.replaceAll("#",""); 
             }
      }
      else if(bean.getStatusId()==IKeyDoc.DOC_STATUS_VIEW_WAIT && bean.getViews()==0){
          
          SELECT_WHERE+="" +  AND + DOC_TRAILER_RECV_READED + EQUAL + IKey.STATUS_UNREAD;
          
      }else if (bean.getStatusId() ==IKeyDoc.DOC_STATUS_VIEW_DEADLINE){//HAN XU LY
         
          SELECT_WHERE += " " +  AND + DOCSRECV_DEADLINE + "<=" + QUESTION;
          params.add(bean.addDays(bean.getCurrentSqlDate(),-(AppConfigs.DOCS_MESSAGES_DATELINE)));    
          
      }else if(bean.getStatusId()!=-3  && bean.getViews()==0){//# Tim kiem
          SELECT_WHERE+="" + AND + DOCSRECV_STATUS_ID + EQUAL + QUESTION + AND + DOC_TRAILER_RECV_READED + DIFF + IKey.STATUS_UNREAD;//reader =1 da doc
          params.add(bean.getStatusId());
          
      }
            
      if(bean.getViews()>0 && bean.getStatusId()==-4){
           SELECT_WHERE+="" + AND + OPEN + "VIEWS" + EQUAL + bean.getViews() + OR + "VIEWS" + EQUAL + 2 + CLOSE;
      }else if(bean.getViews()==-10){
           SELECT_WHERE+="" + AND + OPEN + "VIEWS" + EQUAL + bean.getViews() + CLOSE;
      }
      else if (bean.getViews()==0){
          SELECT_WHERE+="" + AND + "VIEWS" + EQUAL + 0;
      }
      if(bean.getDossierId()>0){
          SELECT_WHERE += " " +AND+  DOCSRECV_DOC_ID + IN  + OPEN  + SELECT + DOC_DOSSIERS_DOC_ID + FROM + TABLE_DOC_DOSSIERS + WHERE + DOC_DOSSIERS_WORKFLOW_ID + EQUAL + QUESTION + AND + DOC_DOSSIERS_DOSSIERS_ID + EQUAL + QUESTION  + CLOSE;
          params.add(1);
          params.add(bean.getDossierId());
      }
       //Tim kiem
        if(bean.getDocCode()!=null && !bean.getDocCode().equals("")){
            SELECT_WHERE+="" +  AND + DOCSRECV_DOCCODE + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getDocCode() + PER_CENT);
        }
        if(bean.getAbstracts()!=null && !bean.getAbstracts().equals("")){
            SELECT_WHERE+="" +  AND + DOCSRECV_ABSTRACT + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getAbstracts() + PER_CENT);
        }
        if(bean.getDocDate()!=null && !bean.getDocDate().equals("")){
            SELECT_WHERE+="" +  AND + DOCSRECV_DOCDATE + ">=" + QUESTION ;
            params.add(bean.stringToSqlDate(bean.getDocDate()));
        }
        if(bean.getFromVnName()!=null && !bean.getFromVnName().equals("")){
            SELECT_WHERE+="" +  AND + "FROMNAME" + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getFromVnName() + PER_CENT);
        }
    }else{
        if(bean.getDossierId()>0){
            SELECT_WHERE += " " +AND+  DOCSRECV_DOC_ID + IN  + OPEN  + SELECT + DOC_DOSSIERS_DOC_ID + FROM + TABLE_DOC_DOSSIERS + WHERE + DOC_DOSSIERS_WORKFLOW_ID + EQUAL + QUESTION + AND + DOC_DOSSIERS_DOSSIERS_ID + EQUAL + QUESTION  + CLOSE;
            params.add(1);
            params.add(bean.getDossierId());
        }
    }
      SQL_SELECT =SQL_SELECT.replaceAll("#",""); 
      SQL_SELECT+=SELECT_WHERE;
    try
    {
        //.println(SQL_SELECT+ORDER_BY + DOCSRECV_LOCALDATE + DESC);        
        prpstm = prepareStatement(cnn,SQL_SELECT+ORDER_BY + DOCSRECV_LOCALDATE + DESC,params); 
        prpstm.setFetchSize((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
        prpstm.setMaxRows((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
        rs = prpstm.executeQuery();
        beans = new FBeans();
        if(pageIndex>0){
            beans.setTotalRows(count(cnn,SQL_SELECT,params));
            beans.setPageIndex(bean.getPageIndex());
            if(beans.getFirstRecord()<=1){
            rs.beforeFirst();
            }else{
            rs.absolute(beans.getFirstRecord()-1);
            }
        }
        int i=0;
        
        FDocAssign beanAssign = new FDocAssign();
        beanAssign.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
        beanAssign.setMeId(userId);
        
        while((rs != null) && (rs.next()) && (pageIndex<=0 || (i<AppConfigs.APP_ROWS_VIEW))){ 
                i++;
                bean=getInformations(rs);
                bean.setDocTypeName(rs.getString("DOCTYPENAME"));
                bean.setForYouId(rs.getInt(DOC_TRAILER_RECV_FORYOU_ID));
                bean.setClassifyName(rs.getString("CLASSIFY_NAME"));
                bean.setViews(rs.getInt(DOC_TRAILER_RECV_VIEWS));
                bean.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
                bean.setBlockUpdate(userId==bean.getUserId()?1:0);
                bean.setAllFiles(new DFilesRecv().getAllByDocId(cnn,bean.getId()));
                beanAssign.setForYouId(bean.getForYouId());             
                if (bean.getForYouId()>0){
                    bean.setCheckForYou(0);    
                    bean.setRulesForYou(new FBeans());                            
                    bean.getRulesForYou().add(new DAssignRecv().checkAsignRule(cnn,beanAssign));                     
                }else {
                    bean.setCheckForYou(checkForYou(cnn,bean.getId(),userId,seed));  
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
      closePreparedStatement(prpstm);
    }
    return beans;
  }
     public int getCountOfYear(Connection cnn,FDocsrecv bean) throws EException 
     {
       final String LOCATION = this.toString() + "getCountOfYear()";
       PreparedStatement prpstm = null;
       ResultSet rs = null;
       int result =0;
       try
       {
        Date dayStart=bean.stringToSqlDate("01/01/"+ bean.getYear(bean.getCurrentSqlDate()));
        String dayEnd=bean.getDaysOfMonth(bean.stringToSqlDate("01/12/"+ bean.getYear(bean.getCurrentSqlDate())))+"/12/"+bean.getYear(bean.getCurrentSqlDate());
        int dayAmount=bean.getDays(dayStart,bean.stringToSqlDate(dayEnd));
        List params = new ArrayList();
        params.add(dayStart);
        params.add(bean.addDays(dayStart,dayAmount-1));           
         prpstm =prepareStatement(cnn,SQL_SELECT_DOCSRECV_COUNT_OF_YEAR,params);
         rs = prpstm.executeQuery();
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
         closePreparedStatement(prpstm);
       }
       return result;    
     }
  
     public FBeans getAllDocsrecvDetailt(Connection cnn,FSeed seed,int userId,int pageIndex,String statused,int checkWait) throws EException
     {
       final String LOCATION = this.toString() + "~~>getAllDocsrecvDetailt()";
       PreparedStatement prpstm = null;
       FDocsrecv bean=(FDocsrecv)seed;
       ResultSet rs = null;
       FBeans beans = null;
       String SELECT_WHERE=WHERE + TRUE;
       String SQL_SELECT=SELECT + STAR + FROM + (SQL_TABLE_ALL_DOCSRECV);
       List params =new ArrayList();
       params.add(userId);
       params.add(bean.getId());
      
        SQL_SELECT+=SELECT_WHERE + AND + DOCSRECV_DOC_ID + EQUAL + QUESTION;
       try
       {
          //.println(SQL_SELECT+ORDER_BY + DOCSRECV_LOCALDATE + DESC);
           prpstm = prepareStatement(cnn,SQL_SELECT+ORDER_BY + DOCSRECV_LOCALDATE + DESC,params); 
           rs = prpstm.executeQuery();
           beans = new FBeans();
           if(pageIndex>0){
               beans.setTotalRows(count(cnn,SQL_SELECT,params));
               beans.setPageIndex(bean.getPageIndex());
               if(beans.getFirstRecord()<=1){
               rs.beforeFirst();
               }else{
               rs.absolute(beans.getFirstRecord()-1);
               }
           }
           int i=0;
           
           FDocAssign beanAssign = new FDocAssign();
           beanAssign.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
           beanAssign.setMeId(userId);
           
           while((rs != null) && (rs.next()) && (pageIndex<=0 || (i<AppConfigs.APP_ROWS_VIEW))){ 
                   i++;
                   bean=getInformations(rs);
                   bean.setDocTypeName(rs.getString("DOCTYPENAME"));
                   bean.setForYouId(rs.getInt(DOC_TRAILER_RECV_FORYOU_ID));
                   bean.setClassifyName(rs.getString("CLASSIFY_NAME"));
                   bean.setViews(rs.getInt(DOC_TRAILER_RECV_VIEWS));
                   bean.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
                   bean.setBlockUpdate(userId==bean.getUserId()?1:0);
                   bean.setAllFiles(new DFilesRecv().getAllByDocId(cnn,bean.getId()));
                   beanAssign.setForYouId(bean.getForYouId());             
                   if (bean.getForYouId()>0){
                       bean.setCheckForYou(0);    
                       bean.setRulesForYou(new FBeans());                            
                       bean.getRulesForYou().add(new DAssignRecv().checkAsignRule(cnn,beanAssign));                     
                   }else {
                       bean.setCheckForYou(checkForYou(cnn,bean.getId(),userId,seed));  
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
         closePreparedStatement(prpstm);
       }
       return beans;
     }
  
     public FBeans searchDocReferent(Connection cnn,FSeed seed,int userId) throws EException
     {
       final String LOCATION = this.toString() + "~~>searchDocReferent()";
       PreparedStatement prpstm = null;
       FDocsrecv bean=(FDocsrecv)seed;
       ResultSet rs = null;
       FBeans beans = null;
       String SELECT_WHERE=WHERE + TRUE;
       String SQL_SELECT=SELECT + STAR + FROM + (SQL_TABLE_ALL_DOCSRECV);
       List params =new ArrayList();
       params.add(userId);
           if(bean.getContent()!=null && !bean.getContent().equals("")){
               SELECT_WHERE+="" +  AND + OPEN + DOCSRECV_DOCCODE + LIKE + QUESTION ;
               params.add(PER_CENT + bean.getContent() + PER_CENT);
               SELECT_WHERE+="" +  OR + DOCSRECV_ABSTRACT + LIKE + QUESTION + CLOSE ;
               params.add(PER_CENT + bean.getContent() + PER_CENT);
           }
         SQL_SELECT+=SELECT_WHERE;
         SQL_SELECT = SQL_SELECT.replaceAll("#","");
       
       try
       {
       prpstm = prepareStatement(cnn,SQL_SELECT+ORDER_BY + DOCSRECV_LOCALDATE + DESC,params); 
       ////.println(SQL_SELECT+ORDER_BY + DOCSRECV_LOCALDATE + DESC);
       rs = prpstm.executeQuery();
       beans = new FBeans();
       int i=0;
       while((rs != null) && (rs.next()) && (i<=20)){ 
               i++;
               bean=getInformations(rs);
               bean.setDocTypeName(rs.getString("DOCTYPENAME"));
               bean.setForYouId(rs.getInt(DOC_TRAILER_RECV_FORYOU_ID));
               bean.setViews(rs.getInt(DOC_TRAILER_RECV_VIEWS));
               bean.setBlockUpdate(userId==bean.getUserId()?1:0);
               bean.setAllFiles(new DFilesRecv().getAllByDocId(cnn,bean.getId()));
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
         closePreparedStatement(prpstm);
       }
       return beans;
     }
     
     public int checkForYou(Connection conn,int docId,long userId,FSeed seed)throws EException{
      final String LOCATION = "->isExist()";
      int result = 0;
      PreparedStatement pstmt = null;
      ResultSet rs = null; 
      try {
                  //.println(SQL_DOCSRECV_CHECK_FORYOU);
                  pstmt = conn.prepareStatement(SQL_DOCSRECV_CHECK_FORYOU);  
                  pstmt.setInt(PARAM_01,docId);
                  pstmt.setLong(PARAM_02,userId);
                  pstmt.setLong(PARAM_03,userId);
                  pstmt.setDate(PARAM_04,seed.getCurrentSqlDate());
                  pstmt.setDate(PARAM_05,seed.addDays(seed.getCurrentSqlDate(),1));  
                 rs = pstmt.executeQuery();
                if( rs!=null && rs.next())  {
                    result = 1;
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
  
    public FBeans getAllDocsrecvOBServer(Connection cnn,FSeed seed,int userId,int checkWait) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllDocsrecvOBServer()";
      PreparedStatement prpstm = null;
      FDocsrecv bean=(FDocsrecv)seed;
      ResultSet rs = null;
      FBeans beans = null;
      String SELECT_WHERE=WHERE + TRUE;
      String SQL_SELECT;
      try{
        List params=new ArrayList();
          if(bean.getClassifyId()>0){
              SELECT_WHERE+="" +  AND + TABLE_DOCSRECV + STOP + DOCSRECV_CLASSIFY_ID + EQUAL + QUESTION;
              params.add(bean.getClassifyId());
          }
         if(bean.getStatusId()==-4){
           // SELECT_WHERE+=  AND + TABLE_STATUS + STOP + STATUS_ID + NOT + IN + OPEN  + SQL_SELECT_DOC_GET_STATUSES + CLOSE;
//            if ((checkWait ==1)){
//                  SELECT_WHERE+= "";// AND + DOC_TRAILER_RECV_READED + DIFF + 0;
//            }
        //    params.add(AppConfigs.DOCSRECV_WORKFLOWID);
        //    params.add(userId);
        } else if(bean.getStatusId()>=-2){
          SELECT_WHERE+=AND + TABLE_DOCSRECV + STOP + DOCSRECV_STATUS_ID + EQUAL  + QUESTION;
          params.add(bean.getStatusId());
        }
        
        if(bean.getDossierId()>0){
            SELECT_WHERE+=AND + TABLE_DOCSRECV + STOP + DOCSRECV_DOSSIERS_ID + EQUAL  + QUESTION;
            params.add(bean.getDossierId());
        }
          //Tim kiem
           if(bean.getDocCode()!=null && !bean.getDocCode().equals("")){
               SELECT_WHERE+="" +  AND + DOCSRECV_DOCCODE + LIKE + QUESTION ;
               params.add(PER_CENT + bean.getDocCode() + PER_CENT);
           }
           if(bean.getAbstracts()!=null && !bean.getAbstracts().equals("")){
               SELECT_WHERE+="" +  AND + DOCSRECV_ABSTRACT + LIKE + QUESTION ;
               params.add(PER_CENT + bean.getAbstracts() + PER_CENT);
           }
           if(bean.getDocDate()!=null && !bean.getDocDate().equals("") && bean.isDate(bean.getDocDate())){
               SELECT_WHERE+="" +  AND + DOCSRECV_DOCDATE + EQUAL + QUESTION ;
               params.add(bean.stringToSqlDate(bean.getDocDate()));
           }
               if(bean.getFromVnName()!=null && !bean.getFromVnName().equals("")){
                   SELECT_WHERE+="" +  AND + "FROMNAME" + LIKE + QUESTION ;
                   params.add(PER_CENT + bean.getFromVnName() + PER_CENT);
               }
        SQL_SELECT = SQL_TABLE_ALL_DOCSRECV_OBSERVER + SELECT_WHERE;
         //.println(SQL_SELECT + ORDER_BY + DOCSRECV_LOCALDATE + DESC);
        prpstm = prepareStatement(cnn,SQL_SELECT + ORDER_BY + DOCSRECV_LOCALDATE + DESC,params); 
        prpstm.setFetchSize((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
        prpstm.setMaxRows((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
        rs = prpstm.executeQuery();
          beans = new FBeans();
          
          beans.setTotalRows(count(cnn,SQL_SELECT,params));
          beans.setPageIndex(bean.getPageIndex());
              if(beans.getFirstRecord()<=1){
                  rs.beforeFirst();
              }else{
                  rs.absolute(beans.getFirstRecord()-1);
              }
              int i=0;
        while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW))
        {
            i++;
            bean=getInformations(rs);
            bean.setForYouId(rs.getInt(DOC_TRAILER_RECV_FORYOU_ID));
            bean.setBlockUpdate(userId==bean.getUserId()?1:0);
            bean.setAllFiles(new DFilesRecv().getAllByDocId(cnn,bean.getId()));  
            bean.setClassifyName(rs.getString("CLASSIFY_NAME"));
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
        closePreparedStatement(prpstm);
      }
      return beans;
    }
     public FBeans getDocReference(Connection cnn,FSeed seed) throws EException
     {
       final String LOCATION = this.toString() + "~~>getDocReference()";
       PreparedStatement prpstm = null;
       FDocsrecv bean=(FDocsrecv)seed;
       ResultSet rs = null;
       FBeans beans = null;            
       try{
         prpstm = cnn.prepareStatement(SQL_ALL_DOC_RECV_IN_ID.replaceAll("#",bean.getReferentId()));    
         rs = prpstm.executeQuery();   
         beans = new FBeans();
         while((rs != null) && (rs.next()))
         {
             bean = new FDocsrecv();
             bean.setId(rs.getInt(DOCSRECV_DOC_ID));
             bean.setDocCode(rs.getString(DOCSRECV_DOCCODE));
             bean.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
             //bean.setDocDate(beantemp.dateToString(rs.getDate(DOCSRECV_DOCDATE)));    
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
         closePreparedStatement(prpstm);
       }
       return beans;
     }
     
     
  public boolean addNew(Connection cnn, FSeed seed) throws  EException
  {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      try
      {
          FDocsrecv bean = (FDocsrecv)seed;
          String storesId="";
          if(bean.getStoresId()!=null){
                  for (int i = 0;i<bean.getStoresId().length;i++){
                      if (!storesId.equals("")) storesId +="," ;              
                       storesId +=bean.getStoresId()[i];
                  }  
          }
          bean.setStoreIds(storesId);
          List params = setParams(seed);
          result = execute(cnn,SQL_DOCSRECV_ADD_NEW,params)>0;
          if(result){
             updateDossiers(cnn,seed);
          }
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      finally
      {
        
      }
    return result;
  }
  
  public boolean update(Connection cnn, FSeed seed) throws  EException
  {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          FDocsrecv bean = (FDocsrecv)seed;
          bean.setUserId(getById(cnn,bean).getUserId());//van lay userId cu 
           String storesId="";
           if(bean.getStoresId()!=null){
                   for (int i = 0;i<bean.getStoresId().length;i++){
                       if (!storesId.equals("")) storesId +="," ;              
                        storesId +=bean.getStoresId()[i];
                   }  
           }
        bean.setStoreIds(storesId);
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_DOCSRECV_UPDATE,params);
        
        result = prstm.executeUpdate()>0;
      if(result){
            bean.setWorkflowId(AppConfigs.DOCSRECV_WORKFLOWID);
          updateDossiers(cnn,bean);
      }
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
  
     public boolean updateBlockFile(Connection cnn, FSeed seed) throws  EException
     {
         final String LOCATION = this.toString() + UPDATE;
         boolean result = false;
         PreparedStatement prstm = null;
         try
         {
         FDocsrecv bean = (FDocsrecv)seed;                  
         prstm = cnn.prepareStatement(SQL_DOCSRECV_BLOCK_FILE_UPDATE);
         prstm.setInt(PARAM_01,bean.getBlockFile());
         prstm.setInt(PARAM_02,bean.getId());
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
  
    public boolean updateStatus(Connection cnn,int statusId,int id) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try{
            prstm = cnn.prepareStatement(SQL_UPDATE_DOCSRECV_STATUS_ID);
            prstm.setInt(PARAM_01,statusId);
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
    
    
     public boolean updateView(Connection cnn,int view,long userId) throws  EException
     {
         final String LOCATION = this.toString() + UPDATE;
         boolean result = false;
         PreparedStatement prstm = null;
         try{
             prstm = cnn.prepareStatement(SQL_UPDATE_DOC_TRAILER_RECV_VIEW);
             //.println(SQL_UPDATE_DOC_TRAILER_RECV_VIEW);
             prstm.setInt(PARAM_01,2);            
             prstm.setLong(PARAM_02,userId);
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
    
    public boolean updateDossiers(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        FDocsrecv bean = (FDocsrecv)seed;
        try{           
            
            deleteDossirers(cnn,seed);
            List params = new ArrayList();
            params.add(bean.getId());
            params.add(bean.getWorkflowId());
            params.add(bean.getDossierId_doc());
            params.add(bean.getUserId());
            params.add(bean.getId());
            params.add(bean.getDossierId_doc());           
            result=execute(cnn,SQL_INSERT_DOSSIER,params)>0;             
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
    
     public boolean updateClassify(Connection cnn,int docId,int classifyId) throws  EException
     {
         final String LOCATION = this.toString() + UPDATE;
         boolean result = false;
         PreparedStatement prstm = null;
         try{           
             List params = new ArrayList();
             params.add(classifyId);
             params.add(docId);
             result=execute(cnn,SQL_UPDATE_DOCSRECV_CLASSIFY_ID,params)>0;             
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
    FDocsrecv bean = (FDocsrecv)seed;
    boolean result=false;
    try
    {
          deleteReview(cnn,bean.getId());
          FFilesRecv beanFile =new FFilesRecv();
          beanFile.setDocId(bean.getId());
          delete(cnn,TABLE_DOC_TRAILER_RECV,DOC_TRAILER_RECV_DOC_ID + EQUAL + bean.getId());  
          new DFilesRecv().delete(cnn,beanFile);
          result=delete(cnn,TABLE_DOCSRECV,DOCSRECV_DOC_ID + EQUAL + bean.getId())>0;  
    }
    catch(EException ex)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
    }
    return result;
  }  
    public boolean deleteReview(Connection cnn,int reviewId)throws EException
    {
      final String LOCATION = this.toString() + "~~>deleteReview()";
      try
      {
            delete(cnn, TABLE_DOC_FILE_REVIEW_RECV, DOC_FILE_REVIEW_RECV_REVIEW_ID + EQUAL + reviewId);
            delete(cnn, TABLE_DOC_REVIEW_RECV, DOC_REVIEW_RECV_DOC_ID + EQUAL + reviewId);
          
      }
      catch(EException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      return true;
    }  
    
    public boolean deleteDossirers(Connection cnn,FSeed seed)throws EException
    {
      final String LOCATION = this.toString() + "~~>deleteDossirers()";
      FDocsrecv bean = (FDocsrecv)seed;
        boolean result=false;
      try
      {       
        result=  delete(cnn, TABLE_DOC_DOSSIERS, DOC_DOSSIERS_DOC_ID  + IN + OPEN + SELECT + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_DOC_ID + FROM + TABLE_DOSSIERS + INNER_JOIN + TABLE_DOC_DOSSIERS + ON + TABLE_DOSSIERS + STOP + DOSSIERS_ID + EQUAL + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_DOSSIERS_ID + WHERE + TABLE_DOSSIERS + STOP + DOSSIERS_CREATOR + EQUAL + bean.getUserId() + AND + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_DOC_ID + EQUAL + bean.getId() + AND + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_WORKFLOW_ID + EQUAL + bean.getWorkflowId() + CLOSE)>0;           
          
      }
      catch(EException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      return result;
    }  
  
  public FDocsrecv getById(Connection cnn, FDocsrecv bean) throws EException 
  {
    final String LOCATION = this.toString() + "getById()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
      FDocsrecv beantemp =new FDocsrecv();
    try
    {
      prpstm = cnn.prepareStatement(SQL_SELECT_DOCSRECV_BY_ID);
      prpstm.setInt(PARAM_01,bean.getId());
      rs = prpstm.executeQuery();
      if((rs != null) && (rs.next()))
      {
          beantemp=getInforById(rs,false);
          if(beantemp.getDocsTypeId()>0){
          FDocType beanDocType=new FDocType();
              beanDocType.setIdDocType(beantemp.getDocsTypeId());
              beantemp.setDocTypeName(new BDocType().getDocTypeById(beanDocType).getNameDocType());
          }
          
          if(beantemp.getStoreAgeId()>0){
              FDepartment Bdep=new FDepartment();
              Bdep.setId(beantemp.getStoreAgeId());
              beantemp.setDepName(new BDepartments().getRecordByID(Bdep).getName());
          }
          beantemp.setDossierId(rs.getInt(DOCSRECV_DOSSIERS_ID));
          DFilesRecv dao2=new DFilesRecv();
          FBeans beanFiles=dao2.getAllByDocId(cnn,beantemp.getId());
          beantemp.setAllFiles(beanFiles);
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
    public FDocsrecv getTopId(Connection cnn,int userId) throws EException 
    {
      final String LOCATION = this.toString() + "getTopId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocsrecv beantemp = new FDocsrecv();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_DOCSRECV_BY_TOP);
          prpstm.setInt(PARAM_01,userId);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
                beantemp.setId(rs.getInt(PARAM_01));
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
    
     public FDocsrecv getDocsByMaxId(Connection cnn,int userId) throws EException 
     {
       final String LOCATION = this.toString() + "getDocsByMaxId()";
       PreparedStatement prpstm = null;
       ResultSet rs = null;
       FDocsrecv beantemp = new FDocsrecv();
       try
       {
         prpstm = cnn.prepareStatement(SQL_SELECT_DOCSRECV_BY_MAX_ID.replaceAll("#",SQL_SELECT_DOCSRECV_BY_TOP));
         prpstm.setInt(PARAM_01,userId);
         ////.println(SQL_SELECT_DOCSRECV_BY_MAX_ID.replaceAll("#",SQL_SELECT_DOCSRECV_BY_TOP));           
         rs = prpstm.executeQuery();
         if((rs != null) && (rs.next())){                 
             beantemp=getInforById(rs,false);
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
    public FDocsrecv getInforById(ResultSet rs,boolean full) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocsrecv beantemp = new FDocsrecv();
         try {
             beantemp.setId(rs.getInt(DOCSRECV_DOC_ID));
             beantemp.setFormId(rs.getInt(DOCSRECV_FORM_ID));
             beantemp.setLocalCode(rs.getString(DOCSRECV_LOCALCODE));
             beantemp.setDocCode(rs.getString(DOCSRECV_DOCCODE));
             beantemp.setCreator(rs.getString(USERS_FULLNAME));
             beantemp.setUserId(rs.getInt(DOCSRECV_USER_ID));
             beantemp.setStatusId(rs.getInt(DOCSRECV_STATUS_ID));             
             beantemp.setTimeCreate(beantemp.dateToString(rs.getDate(DOCSRECV_TIMECREATE)));
             beantemp.setLocalDate(beantemp.dateToString(rs.getDate(DOCSRECV_LOCALDATE)));
             beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSRECV_DOCDATE)));            
             beantemp.setStoreIds(rs.getString(DOCSRECV_STOREAGE_ID));//getDepartment
             beantemp.setExpressId(rs.getInt(DOCSRECV_EXPRESS_ID));
             beantemp.setSecureId(rs.getInt(DOCSRECV_SECURE_ID));
             beantemp.setViaId(rs.getInt(DOCSRECV_VIA_ID));
             beantemp.setFromId(rs.getInt(DOCSRECV_FROM_ID));
             beantemp.setAddress(rs.getString(DOCSRECV_ADDRESS));
             beantemp.setDocsTypeId(rs.getInt(DOCSRECV_DOCTYPE_ID));
             beantemp.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
             beantemp.setDescription(rs.getString(DOCSRECV_DESCRIPTION));
             beantemp.setSigner(rs.getString(DOCSRECV_SIGNER));
             beantemp.setDeadLine(beantemp.dateToString(rs.getDate(DOCSRECV_DEADLINE)));
             beantemp.setWorkflowId(rs.getInt(DOCSRECV_WORKFLOW_ID));
             beantemp.setNumberVersion(rs.getInt(DOCSRECV_NUMBERVERSION));
             beantemp.setNumberPage(rs.getInt(DOCSRECV_NUMBERPAGE));
             beantemp.setBranchId(rs.getInt(DOCSRECV_BRANCH_ID));
             beantemp.setClassifyId(rs.getInt(DOCSRECV_CLASSIFY_ID));
//             
//             if(beantemp.getStoreAgeId()>0){
//                 FDepartment Bdep=new FDepartment();
//                 Bdep.setId(beantemp.getStoreAgeId());
//                 beantemp.setDepName(new BDepartments().getRecordByID(Bdep).getName());
//             }
             
             if(full){
                 beantemp.setDossierId(rs.getInt("DOCS_DOSSIERS_ID"));
                 beantemp.setDossierId_doc(beantemp.getDossierId());
                 beantemp.setFormName(rs.getString("NAME_FORM"));
                 beantemp.setStatusName(rs.getString("NAME_STATUS"));
                 beantemp.setExpressName(rs.getString("NAME_EXPRESS"));
                 beantemp.setViaName(rs.getString("NAME_VIA"));
                 beantemp.setDocTypeName(rs.getString("NAME_DOCTYPE"));
                 beantemp.setSecureName(rs.getString("NAME_SECURE"));
                 beantemp.setDossiersName(rs.getString("NAME_DOSSIERS"));
             }
         }
         catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }


    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FDocsrecv bean = (FDocsrecv)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getFormId());
             params.add(bean.getLocalCode());                           
             params.add(bean.getDocCode());                           
             params.add(bean.getUserId());                           
             params.add(bean.getStatusId());                           
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));
             params.add(bean.stringToSqlDate(bean.getLocalDate()));   
             params.add(bean.stringToSqlDate(bean.getDocDate()));   
             params.add(bean.getStoreIds());   
             params.add(bean.getExpressId());   
             params.add(bean.getSecureId());   
             params.add(bean.getViaId());   
             params.add(bean.getFromId());   
             params.add(bean.getAddress());   
             params.add(bean.getDocsTypeId());   
             params.add(bean.getAbstracts());   
             params.add(bean.getDescription());   
             params.add(bean.getSigner());   
             params.add(bean.stringToSqlDate(bean.getDeadLine()));   
             params.add(bean.getDossierId());   
             params.add(1); 
             //moi them vao 
             params.add(bean.getNumberVersion());   
             params.add(bean.getNumberPage());   
             params.add(bean.getBranchId());   
             params.add(bean.getClassifyId());   
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
     public FDocsrecv  getInformations(ResultSet rs) throws EException
     {
         final String LOCATION = "->getInformations()";
         FDocsrecv beantemp = new FDocsrecv();
          try {
              beantemp.setTimeSend(beantemp.dateToString(new Date (rs.getTimestamp(DOC_TRAILER_RECV_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));            
              beantemp.setId(rs.getInt(DOCSRECV_DOC_ID));//code cong van
              beantemp.setCreator(rs.getString(USERS_FULLNAME));//code cong van
              beantemp.setUserId(rs.getInt(DOCSRECV_USER_ID));//code cong van
              beantemp.setDocCode(rs.getString(DOCSRECV_DOCCODE));
              beantemp.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
              beantemp.setDeadLine(beantemp.dateToString(rs.getDate(DOCSRECV_DEADLINE)));
              beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSRECV_DOCDATE)));
              beantemp.setStatusName(rs.getString(DOCSRECV_STATUS_NAME));
              beantemp.setStatusColor(rs.getString(DOCSRECV_STATUS_COLOR));
              beantemp.setExpressColor(rs.getString(EXPRESS_COLOR));
              beantemp.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
              beantemp.setStatusId(rs.getInt(DOCSRECV_STATUS_ID));
              beantemp.setFromVnName(rs.getString("FROMNAME"));
          }catch (SQLException sqle) {            
              if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
          }
          finally {
          }
          return beantemp;
     }
    public FDocsrecv  InforSearch(ResultSet rs) throws EException
    {
        final String LOCATION = "->InforSearch()";
        FDocsrecv beantemp = new FDocsrecv();
         try {
             beantemp.setTimeSend(beantemp.dateToString(new Date (rs.getTimestamp(DOC_TRAILER_RECV_TIMESEND).getTime()),AppConfigs.APP_DATE_TIME));            
             beantemp.setId(rs.getInt(DOCSRECV_DOC_ID));//code cong van
             beantemp.setCreator(rs.getString(USERS_FULLNAME));//code cong van
             beantemp.setUserId(rs.getInt(DOCSRECV_USER_ID));//code cong van
             beantemp.setDocCode(rs.getString(DOCSRECV_DOCCODE));
             beantemp.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));
             beantemp.setDeadLine(beantemp.dateToString(rs.getDate(DOCSRECV_DEADLINE)));
             beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSRECV_DOCDATE)));
             beantemp.setStatusName(rs.getString(DOCSRECV_STATUS_NAME));
             beantemp.setExpressColor(rs.getString(EXPRESS_COLOR));
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
     public FDocsrecv getDetail(Connection cnn, FDocsrecv bean) throws EException 
     {
       final String LOCATION = this.toString() + "getDetail()";
       PreparedStatement prpstm = null;
       ResultSet rs = null;
       FDocsrecv beantemp =new FDocsrecv();
       try
       {
           List params =new ArrayList();
           params.add(bean.getUserId());
           params.add(bean.getId());
           params.add(bean.getUserId());
           params.add(bean.getId());
           params.add(bean.getId());       
           //.println(SQL_SELECT_DOCSRECV_BY_ID_DETAIL_OBSERVER);
           if ((bean.getObServer()>0 && bean.getCheckObServer()>0) || bean.getObServer()==1){
               prpstm =prepareStatement(cnn,SQL_SELECT_DOCSRECV_BY_ID_DETAIL_OBSERVER,params);
           }else{
               params.add(bean.getUserId());
               prpstm =prepareStatement(cnn,SQL_SELECT_DOCSRECV_BY_ID_DETAIL,params);
           }
           rs = prpstm.executeQuery();
           DFilesRecv dao2=new DFilesRecv();
          
         if((rs != null) && (rs.next())){
            
             beantemp=getInforById(rs,true); 
             
             if(beantemp.getStoreIds()!=null && !beantemp.getStoreIds().equals("")){
               beantemp.setStores(getAllStoreDep(cnn,beantemp.getStoreIds()));
             }
             
             if (!(bean.getObServer()>0 || bean.getCheckObServer()>0)){
                 beantemp.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
                 beantemp.setViews(rs.getInt(DOC_TRAILER_RECV_VIEWS));
             }
             
             FBeans beanFiles=dao2.getAllByDocId(cnn,beantemp.getId());
             beantemp.setForYouId(bean.getForYouId());           
             if (beantemp.getForYouId()==0){
               beantemp.setCheckForYou(checkForYou(cnn,beantemp.getId(),bean.getUserId(),bean));  
             }
             beantemp.setAllFiles(beanFiles);
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
     
     public FBeans getAllStoreDep(Connection cnn,String stores) throws EException 
     {
       final String LOCATION = this.toString() + "getAllStoreDep()";
       PreparedStatement prpstm = null;
       ResultSet rs = null;
       FDepartment beantemp = null;
       FBeans beans=null;
       try
       {
         String SQL = SQL_SELECT_ALL_DEPARTMENT_BY_SEARCH + AND + DEPARTMENTS_DEPARTMENT_ID + IN + OPEN + stores + CLOSE;
         //.println(SQL);
         prpstm = cnn.prepareStatement(SQL);
         rs = prpstm.executeQuery();
         beans=new FBeans();
         int i=0;
         while((rs != null) && (rs.next()))
         {   
           beantemp = new FDepartment();
           beantemp.setId(rs.getInt(DEPARTMENTS_DEPARTMENT_ID));
           beantemp.setCode(rs.getString(DEPARTMENTS_CODE));
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
         closePreparedStatement(prpstm);
       }
       return beans;    
     } 
     public boolean delTrailerByUserRecv(Connection cnn,long meId,int docId,int forYouId)throws EException{
         if (forYouId>0){
             String SQL = SELECT + FORYOU_USER_ID_FROM + FROM + TABLE_FORYOU + WHERE + FORYOU_ID + EQUAL + forYouId + AND + FORYOU_WORKFLOW_ID + EQUAL + AppConfigs.DOCSRECV_WORKFLOWID;
             delete(cnn,TABLE_DOC_TRAILER_RECV,DOC_TRAILER_RECV_USERRECV_ID + EQUAL + OPEN + SQL + CLOSE + AND + DOC_TRAILER_RECV_DOC_ID + EQUAL + docId);
         }
         return delete(cnn,TABLE_DOC_TRAILER_RECV,DOC_TRAILER_RECV_USERRECV_ID + EQUAL + meId + AND + DOC_TRAILER_RECV_DOC_ID + EQUAL + docId)>0;  
      }
 }
