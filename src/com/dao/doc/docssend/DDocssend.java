package com.dao.doc.docssend;


import com.dao.doc.assign.DAssignRecv;
import com.dao.doc.from.DFrom;
import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.assign.FDocAssign;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docssend.FDocssend;
import com.form.doc.docssend.FFilesSend;

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


public class DDocssend extends DSqlForYou
{
   
   
    public boolean updateView(Connection cnn,int view,long userId) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try{
            prstm = cnn.prepareStatement(SQL_UPDATE_DOC_TRAILER_SEND_VIEW);           
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
    
    public int getCountOfYear(Connection cnn,FDocssend bean) throws EException 
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
        prpstm =prepareStatement(cnn,SQL_SELECT_DOCSSEND_COUNT_OF_YEAR,params);
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
      return result+1;    
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
            result=execute(cnn,SQL_UPDATE_DOCSSEND_CLASSIFY_ID,params)>0;             
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
   
    public boolean updateDossiers(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        FDocssend bean = (FDocssend)seed;
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
    

    public boolean deleteDossirers(Connection cnn,FSeed seed)throws EException
    {
      final String LOCATION = this.toString() + "~~>deleteDossirers()";
      FDocssend bean = (FDocssend)seed;
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
    public boolean updateStatus(Connection cnn,int statusId,int id) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try{
            prstm = cnn.prepareStatement(SQL_UPDATE_DOCSSEND_STATUS_ID);
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
    public boolean isExistAdd(Connection conn, FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    boolean result = false;
    FDocssend bean = (FDocssend)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
          pstmt = conn.prepareStatement(SQL_DOCSSEND_CHECK_CODE_ADD);    
          pstmt.setString(PARAM_01,bean.getDocCode());
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
    FDocssend bean = (FDocssend)seed;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
                pstmt = conn.prepareStatement(SQL_DOCSSEND_CHECK_CODE_UPDATE);  
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
  public FBeans getAlldocssend(Connection cnn,FSeed seed,int userId,int type,int pageIndex,int checkWaitSend,String statused,int checkRulesCreator) throws EException
  {
    final String LOCATION = this.toString() + "~~>getAlldocssend()";
    PreparedStatement prpstm = null;
    FDocssend bean=(FDocssend)seed;
    ResultSet rs = null;
    FBeans beans = null;    
    String SQL_WHERE= WHERE + TRUE;
    String SQL_SELECT=SQL_TABLE_ALL_DOCSSEND;
    List params =new ArrayList();
    params.add(userId);
      //.println(SQL_TABLE_ALL_DOCSSEND);
    SQL_WHERE+=AND + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_BLOCK + EQUAL + 0 + AND + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_USERRECV_ID + EQUAL + QUESTION ;
    if(pageIndex>0){
        if(bean.getClassifyId()>0){
            SQL_WHERE+="" +  AND + TABLE_DOCSSEND + STOP + DOCSSEND_CLASSIFY_ID + EQUAL + QUESTION;
            params.add(bean.getClassifyId());
        }
        
            if(bean.getStatusId()==IKeyDoc.DOC_STATUS_VIEW_ALL){ 
            
                if (AppConfigs.DOC_STATUS_COUNT_NOT_IN==1){
                     SQL_WHERE+=" "+  AND + TABLE_DOCSSEND + STOP + DOCSSEND_STATUS_ID + NOT + IN + OPEN  + ((statused!=null && !statused.equals(""))?statused:"100000") + CLOSE ;
                     if (checkWaitSend==1){
                        SQL_WHERE+=  AND + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_READED + DIFF + IKey.STATUS_UNREAD;
                     }
                }  
                
            }else if(bean.getStatusId()==IKeyDoc.DOC_STATUS_VIEW_WAIT && bean.getViews()==0){
                SQL_WHERE+="" +  AND + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_READED + EQUAL + IKey.STATUS_UNREAD;
            }else if(bean.getStatusId()!=-3 && bean.getViews()==0){//# Tim kiem
                SQL_WHERE+=" " + AND + TABLE_DOCSSEND + STOP + DOCSSEND_STATUS_ID + EQUAL + QUESTION + AND + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_READED + EQUAL + QUESTION;
                params.add(bean.getStatusId());
                params.add(1);
            }
            if(bean.getViews()>=1){
                SQL_WHERE+="" + AND + OPEN  + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_VIEWS + EQUAL + bean.getViews() + OR + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_VIEWS + EQUAL + 2 + CLOSE;
            }
            if(bean.getDossierId()>0){
                SQL_WHERE += " " + AND + TABLE_DOCSSEND + STOP + DOCSSEND_DOC_ID + IN  + OPEN  + SELECT + DOC_DOSSIERS_DOC_ID + FROM + TABLE_DOC_DOSSIERS + WHERE  + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_WORKFLOW_ID + EQUAL + QUESTION + AND + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_DOSSIERS_ID + EQUAL + QUESTION  + CLOSE;
                params.add(2);
                params.add(bean.getDossierId());
            }
            if(type==2){
                SQL_WHERE +=" "+ AND + OPEN + TABLE_DOCSSEND + STOP + DOCSSEND_DOCCODE + EQUAL + "'"+"'" +  OR +  TABLE_DOCSSEND + STOP + DOCSSEND_DOCCODE +  IS +  NULL + CLOSE;
            }
        if(bean.getDocCode()!=null && !bean.getDocCode().equals("")){
            SQL_WHERE+="" +  AND + DOCSSEND_DOCCODE + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getDocCode() + PER_CENT);
        }
        if(bean.getAbstracts()!=null && !bean.getAbstracts().equals("")){
            SQL_WHERE+="" +  AND + DOCSSEND_ABSTRACT + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getAbstracts() + PER_CENT);
        }
        if(bean.getDocDate()!=null && !bean.getDocDate().equals("") ){
            SQL_WHERE+="" +  AND + DOCSSEND_DOCDATE + ">=" + QUESTION ;
            params.add(bean.stringToSqlDate(bean.getDocDate()));
        }
        if(bean.getSigner()!=null && !bean.getSigner().equals("")){
            SQL_WHERE+="" +  AND + DOCSSEND_SIGNER + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getSigner() + PER_CENT);
        }
    }else{
        if(bean.getDossierId()>0){
            SQL_WHERE += " " + AND + TABLE_DOCSSEND + STOP + DOCSSEND_DOC_ID + IN  + OPEN  + SELECT + DOC_DOSSIERS_DOC_ID + FROM + TABLE_DOC_DOSSIERS + WHERE  + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_WORKFLOW_ID + EQUAL + QUESTION + AND + TABLE_DOC_DOSSIERS + STOP + DOC_DOSSIERS_DOSSIERS_ID + EQUAL + QUESTION  + CLOSE;
            params.add(2);
            params.add(bean.getDossierId());
        }
    }
    try{
      ////.println(SQL_SELECT + SQL_WHERE + ORDER_BY   + DOCSSEND_LOCALDATE + DESC);
      prpstm = prepareStatement(cnn,SQL_SELECT + SQL_WHERE + ORDER_BY   + DOCSSEND_LOCALDATE + DESC,params);     
      prpstm.setFetchSize((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
      prpstm.setMaxRows((1+bean.getPageIndex())*AppConfigs.APP_ROWS_VIEW);
      rs = prpstm.executeQuery();
        beans = new FBeans();
        if(pageIndex>0){
        beans.setTotalRows(count(cnn,SQL_SELECT + SQL_WHERE,params));
        beans.setPageIndex(bean.getPageIndex());
            if(beans.getFirstRecord()<=1){
                rs.beforeFirst();
            }else{
                rs.absolute(beans.getFirstRecord()-1);
            }
        }
        int i=0;
          
        FDocAssign beanAssign = new FDocAssign();
        beanAssign.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
        beanAssign.setMeId(userId);  
      
          while((rs != null) && (rs.next()) && (pageIndex<=0 || (i<AppConfigs.APP_ROWS_VIEW))){ 
              
              i++;
              bean=getInformations(rs);
              bean.setDocTypeName(rs.getString("DOCTYPENAME"));
              bean.setForYouId(rs.getInt(DOC_TRAILER_SEND_FORYOU_ID));
              bean.setViews(rs.getInt(DOC_TRAILER_SEND_VIEWS));
              bean.setReaded(rs.getInt(DOC_TRAILER_SEND_READED));
              bean.setBlockUpdate(userId==bean.getUserId()?1:0);
              if(bean.getFromId()!=null && !bean.getFromId().equals("")){
             //   bean.setAllFroms(new DFrom().getFromInID(cnn,bean.getFromId()));
              }
              bean.setAllFiles(new DFilesSend().getAllByDocId(cnn,bean.getId(),checkRulesCreator));
              beanAssign.setForYouId(bean.getForYouId());
              bean.setClassifyName(rs.getString("CLASSIFY_NAME"));
              
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
  
  
    public FBeans getAlldocssendDetailt(Connection cnn,FSeed seed,int userId,int type,int pageIndex,int checkWaitSend,String statused,int checkRulesCreator) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAlldocssendDetailt()";
      PreparedStatement prpstm = null;
      FDocssend bean=(FDocssend)seed;
      ResultSet rs = null;
      FBeans beans = null;    
      String SQL_WHERE= WHERE + TRUE;
      String SQL_SELECT=SQL_TABLE_ALL_DOCSSEND;
      List params =new ArrayList();
      params.add(userId);
      params.add(bean.getId());
       SQL_WHERE+=AND + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_BLOCK + EQUAL + 0 + AND + TABLE_DOC_TRAILER_SEND + STOP + DOC_TRAILER_SEND_USERRECV_ID + EQUAL + QUESTION ;
        SQL_WHERE+= AND + TABLE_DOC_TRAILER_SEND + STOP + DOCSSEND_DOC_ID + EQUAL + QUESTION;
      try{
        //.println(SQL_SELECT + SQL_WHERE + ORDER_BY   + DOCSSEND_LOCALDATE + DESC);
        prpstm = prepareStatement(cnn,SQL_SELECT + SQL_WHERE + ORDER_BY   + DOCSSEND_LOCALDATE + DESC,params);     
        rs = prpstm.executeQuery();
          beans = new FBeans();
          if(pageIndex>0){
          beans.setTotalRows(count(cnn,SQL_SELECT + SQL_WHERE,params));
          beans.setPageIndex(bean.getPageIndex());
              if(beans.getFirstRecord()<=1){
                  rs.beforeFirst();
              }else{
                  rs.absolute(beans.getFirstRecord()-1);
              }
          }
          int i=0;
            
          FDocAssign beanAssign = new FDocAssign();
          beanAssign.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
          beanAssign.setMeId(userId);  
        
            while((rs != null) && (rs.next()) && (pageIndex<=0 || (i<AppConfigs.APP_ROWS_VIEW))){ 
                
                i++;
                bean=getInformations(rs);
                bean.setDocTypeName(rs.getString("DOCTYPENAME"));
                bean.setForYouId(rs.getInt(DOC_TRAILER_SEND_FORYOU_ID));
                bean.setViews(rs.getInt(DOC_TRAILER_SEND_VIEWS));
                bean.setReaded(rs.getInt(DOC_TRAILER_SEND_READED));
                bean.setBlockUpdate(userId==bean.getUserId()?1:0);
                if(bean.getFromId()!=null && !bean.getFromId().equals("")){
               //   bean.setAllFroms(new DFrom().getFromInID(cnn,bean.getFromId()));
                }
                bean.setAllFiles(new DFilesSend().getAllByDocId(cnn,bean.getId(),checkRulesCreator));
                beanAssign.setForYouId(bean.getForYouId());
                bean.setClassifyName(rs.getString("CLASSIFY_NAME"));
                
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
  
    public int checkForYou(Connection conn,int docId,long userId,FSeed seed)throws EException{
    final String LOCATION = "->isExist()";
    int result = 0;
    PreparedStatement pstmt = null;
    ResultSet rs = null; 
     try {
                //.println(SQL_DOCSEND_CHECK_FORYOU);
                pstmt = conn.prepareStatement(SQL_DOCSEND_CHECK_FORYOU);  
                pstmt.setInt(PARAM_01,docId);
                pstmt.setLong(PARAM_02,userId);
                pstmt.setLong(PARAM_03,userId);
                //.println(seed.getCurrentSqlDate());
                //.println(seed.addDays(seed.getCurrentSqlDate(),1));
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
  
  
    public FBeans getAlldocssendObServer(Connection cnn,FSeed seed,int userId,int type,int checkRulesCreator) throws EException//docCode mean is 1 is docssend 2 is dtDocsend
    {
    final String LOCATION = this.toString() + "~~>getAlldocssendObServer()";
    PreparedStatement prpstm = null;
    FDocssend bean=(FDocssend)seed;
    ResultSet rs = null;
    FBeans beans = null;
    List params =new ArrayList();
    String SQL_SELECT;
    String SQL_WHERE=WHERE + TRUE;
    if(type==2){
        SQL_WHERE= AND +  OPEN + TABLE_DOCSSEND + STOP + DOCSSEND_DOCCODE + EQUAL + "'"+"'" +  OR +  TABLE_DOCSSEND + STOP + DOCSSEND_DOCCODE +  IS +  NULL + CLOSE;
    }
        if(bean.getStatusId()>=-2){
            SQL_WHERE+=AND + TABLE_DOCSSEND + STOP + DOCSSEND_STATUS_ID + EQUAL + QUESTION;
            params.add(bean.getStatusId());
        }
        if(bean.getClassifyId()>0){
            SQL_WHERE+="" +  AND + TABLE_DOCSSEND + STOP + DOCSSEND_CLASSIFY_ID + EQUAL + QUESTION;
            params.add(bean.getClassifyId());
        }
    if(bean.getDossierId()>0){
        SQL_WHERE+=AND + TABLE_DOCSSEND + STOP + DOCSSEND_DOSSIERS_ID + EQUAL + QUESTION;
        params.add(bean.getDossierId());
    }
   
        if(bean.getDocCode()!=null && !bean.getDocCode().equals("")){
            SQL_WHERE+="" +  AND + DOCSSEND_DOCCODE + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getDocCode() + PER_CENT);
        }
        if(bean.getAbstracts()!=null && !bean.getAbstracts().equals("")){
            SQL_WHERE+="" +  AND + DOCSSEND_ABSTRACT + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getAbstracts() + PER_CENT);
        }
        if(bean.getDocDate()!=null && !bean.getDocDate().equals("") && bean.isDate(bean.getDocDate())){
            SQL_WHERE+="" +  AND + DOCSSEND_DOCDATE + ">=" + QUESTION ;
            params.add(bean.stringToSqlDate(bean.getDocDate()));
        }
        if(bean.getSigner()!=null && !bean.getSigner().equals("")){
            SQL_WHERE+="" +  AND + DOCSSEND_SIGNER + LIKE + QUESTION ;
            params.add(PER_CENT + bean.getSigner() + PER_CENT);
        }
        SQL_SELECT=SQL_TABLE_ALL_DOCSSEND_OBSERVER + SQL_WHERE ;
    try{
        prpstm = prepareStatement(cnn, SQL_SELECT+ ORDER_BY + DOCSSEND_LOCALDATE + DESC,params);   
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
    while((rs != null) && (rs.next())&& (i<AppConfigs.APP_ROWS_VIEW))
    { i++;
        bean=getInformations(rs);
        bean.setForYouId(rs.getInt(DOC_TRAILER_SEND_FORYOU_ID));
        bean.setBlockUpdate(userId==bean.getUserId()?1:0);
        bean.setAllFiles(new DFilesSend().getAllByDocId(cnn,bean.getId(),checkRulesCreator));
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

  public boolean addNew(Connection cnn, FSeed seed) throws  EException
  {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      try
      {
            FDocssend bean = (FDocssend)seed;
            String docsId = "";
            String fromsId="";
            String storesId = "";
            if(bean.getDocsId()!=null){
                  for (int i = 0;i<bean.getDocsId().length;i++){
                      if (!docsId.equals("")) docsId +="," ;              
                       docsId +=bean.getDocsId()[i];
                  }  
            }
            if(bean.getFromsId()!=null){
                  for (int i = 0;i<bean.getFromsId().length;i++){
                      if (!fromsId.equals("")) fromsId +="," ;              
                       fromsId +=bean.getFromsId()[i];
                  }  
            }
          if(bean.getStoresId()!=null){
                  for (int i = 0;i<bean.getStoresId().length;i++){
                      if (!storesId.equals("")) storesId +="," ;              
                       storesId +=bean.getStoresId()[i];
                  }  
          }
          bean.setReferentId(docsId);//van ban lien quan
          bean.setFromId(fromsId);//van ban lien quan
           bean.setStoreIds(storesId);//van ban lien quan
            List params = setParams(bean);
            result = execute(cnn,SQL_DOCSSEND_ADD_NEW,params)>0;
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
      
        FDocssend bean = (FDocssend)seed;
          String docsId = "";
          String fromsId = "";
          String storesId = "";
            if(bean.getDocsId()!=null){
                    for (int i = 0;i<bean.getDocsId().length;i++){
                        if (!docsId.equals("")) docsId +="," ;              
                         docsId +=bean.getDocsId()[i];
                    }  
            }
          if(bean.getFromsId()!=null){
                  for (int i = 0;i<bean.getFromsId().length;i++){
                      if (!fromsId.equals("")) fromsId +="," ;              
                       fromsId +=bean.getFromsId()[i];
                  }  
          }
          if(bean.getStoresId()!=null){
                  for (int i = 0;i<bean.getStoresId().length;i++){
                      if (!storesId.equals("")) storesId +="," ;              
                       storesId +=bean.getStoresId()[i];
                  }  
          }
          bean.setReferentId(docsId);//van ban lien quan
          bean.setFromId(fromsId);//
          bean.setStoreIds(storesId);//noi luu
        List params = setParamsUpdate(bean);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_DOCSSEND_UPDATE,params);
        result = prstm.executeUpdate()>0;
        
        if(result){
        
            bean.setWorkflowId(AppConfigs.DOCSSEND_WORKFLOWID);
            bean.setDossierId_doc(bean.getDossierId());
           // updateFileDocsSend(cnn,seed);
            updateDossiers(cnn,bean);
            
        }
//        
//          if (bean.getDocsId()!=null){            
//              addNewReference(cnn,bean);
//          }
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
        
          FDocssend bean = (FDocssend)seed;          
          List params = new ArrayList();
          params.add(bean.getBlockFile());
          params.add(bean.getId());
          prstm = prepareStatement(cnn,SQL_DOCSSEND_BLOCK_FILE_UPDATE,params);
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
  
    public boolean updateFileDocsSend(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        FDocAssign bean = (FDocAssign)seed;
        try{
                      
            if(bean.getIdFiles()!=null){  
                List params = new ArrayList();     
                 String members="";
                for (int i=0;i<bean.getIdFiles().length;i++){
                     if (bean.getIdFiles()[i]>0){
                           if (!members.equals(""))  members +=",";
                           members += bean.getIdFiles()[i] ;
                     }
                }
                params.add(bean.getDocId());            
                String SQL = UPDATE + TABLE_FILESSEND + SET + FILESSEND_VIEWS + EQUAL + 1 + WHERE + FILESSEND_ID + IN + OPEN +members + CLOSE + AND + FILESRECV_DOC_ID + EQUAL + QUESTION;
                result=execute(cnn,SQL,params)>0;    
            }
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
  
  
    public boolean addNewReference(Connection cnn, FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        try
        {
            FDocssend bean = (FDocssend)seed;
            String docsId = "";
            for (int i = 0;i<bean.getDocsId().length;i++){
                if (!docsId.equals("")) docsId +="," ;              
                 docsId +=bean.getDocsId()[i];
            }  
            if (docsId.equals("")) docsId = "0";
            List params = new ArrayList();          
            params.add(bean.getId()>0?bean.getId():getTopId(cnn,bean.getUserId()).getId());
            params.add(bean.getCurrentSqlDate());
            params.add(bean.getUserId());     
            params.add(bean.getId());     
            String SQL = SQL_DOCSSEND_ADD_NEW_REFERENCE.replaceAll("#",docsId);         
            result = execute(cnn,SQL,params)>0;
             
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
    public boolean delTrailerByUserRecv(Connection cnn,long meId,int docId,int forYouId)throws EException{
        if (forYouId>0){
            String SQL = SELECT + FORYOU_USER_ID_FROM + FROM + TABLE_FORYOU + WHERE + FORYOU_ID + EQUAL + forYouId + AND + FORYOU_WORKFLOW_ID + EQUAL + AppConfigs.DOCSSEND_WORKFLOWID;
            delete(cnn,TABLE_DOC_TRAILER_SEND,DOC_TRAILER_SEND_USERRECV_ID + EQUAL + OPEN + SQL + CLOSE + AND + DOC_TRAILER_SEND_DOC_ID + EQUAL + docId);  
        }
        return delete(cnn,TABLE_DOC_TRAILER_SEND,DOC_TRAILER_SEND_USERRECV_ID + EQUAL + meId + AND + DOC_TRAILER_SEND_DOC_ID + EQUAL + docId)>0;  
     }
  public boolean delete(Connection cnn, FSeed seed)throws EException
  {
    final String LOCATION = this.toString() + "~~>delete()";
    FDocssend bean = (FDocssend)seed;
    try
     {
        
        delete(cnn,TABLE_DOC_TRAILER_SEND,DOC_TRAILER_SEND_DOC_ID + EQUAL + bean.getId());  
        deleteReview(cnn,bean.getId());
        DFilesSend daoFile =new DFilesSend();
        FFilesSend beanFile =new FFilesSend();
        beanFile.setDocId(bean.getId());
        daoFile.delete(cnn,beanFile);
      delete(cnn,TABLE_DOCSSEND, DOCSSEND_DOC_ID + EQUAL + bean.getId());
     }
    catch(EException ex)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
    }
    return true;
  }  
    public boolean deleteReview(Connection cnn,int reviewId)throws EException
    {
      final String LOCATION = this.toString() + "~~>deleteReview()";
      try
      {
        delete(cnn, TABLE_DOC_FILES_SEND_REVIEW,DOC_FILES_SEND_REVIEW_REVIEW_ID + EQUAL + reviewId);
        delete(cnn, TABLE_DOC_REVIEW_SEND,DOC_REVIEW_SEND_DOC_ID + EQUAL + reviewId);
        
      }
      catch(EException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      return true;
    }  
  

    public FDocssend getTopId(Connection cnn,int userId) throws EException 
    {
      final String LOCATION = this.toString() + "getTopId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocssend beantemp = new FDocssend();
      try
      {
        prpstm = cnn.prepareStatement(SELECT + MAX(DOCSSEND_DOC_ID) + FROM + TABLE_DOCSSEND + WHERE + DOCSSEND_USER_ID + EQUAL + QUESTION);
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
    
    public FDocssend getDocsByMaxId(Connection cnn,int userId) throws EException 
    {
      final String LOCATION = this.toString() + "getTopId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocssend beantemp = new FDocssend();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_DOCSSEND_BY_MAX_ID.replaceAll("#",SELECT + MAX(DOCSSEND_DOC_ID) + FROM + TABLE_DOCSSEND + WHERE + DOCSSEND_USER_ID + EQUAL + QUESTION));
        prpstm.setInt(PARAM_01,userId);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next())){
            beantemp = getInformation(rs,false);
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

    
    public FDocssend getInformation(ResultSet rs,boolean full) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDocssend beantemp = new FDocssend();
         try {
             beantemp.setId(rs.getInt(DOCSSEND_DOC_ID));
             beantemp.setFormId(rs.getInt(DOCSSEND_FORM_ID));
             beantemp.setLocalCode(rs.getString(DOCSSEND_LOCALCODE));
             beantemp.setDocCode(rs.getString(DOCSSEND_DOCCODE));
             beantemp.setCreator(rs.getString("FULLNAME"));
             beantemp.setUserId(rs.getInt(DOCSSEND_USER_ID));
             beantemp.setStatusId(rs.getInt(DOCSSEND_STATUS_ID));
             beantemp.setTimeCreate(beantemp.dateToString(new Date (rs.getTimestamp(DOCSSEND_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
             beantemp.setLocalDate(beantemp.dateToString(rs.getDate(DOCSSEND_LOCALDATE)));            
             beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSSEND_DOCDATE)));
             beantemp.setStoreIds(rs.getString(DOCSSEND_STOREAGE_ID));
             beantemp.setExpressId(rs.getInt(DOCSSEND_EXPRESS_ID));
             beantemp.setSecureId(rs.getInt(DOCSSEND_SECURE_ID));
             beantemp.setViaId(rs.getInt(DOCSSEND_VIA_ID));
             beantemp.setFromId(rs.getString(DOCSSEND_FROM_ID));
             beantemp.setAddress(rs.getString(DOCSSEND_ADDRESS));
             beantemp.setDocsTypeId(rs.getInt(DOCSSEND_DOCTYPE_ID));
             beantemp.setAbstracts(rs.getString(DOCSSEND_ABSTRACT));
             beantemp.setDescription(rs.getString(DOCSSEND_DESCRIPTION));
             beantemp.setSigner(rs.getString(DOCSSEND_SIGNER));
             beantemp.setDeadLine(beantemp.dateToString(rs.getDate(DOCSSEND_DEADLINE)));
             beantemp.setWorkflowId(rs.getInt(DOCSSEND_WORKFLOW_ID));
             beantemp.setReferentId(rs.getString(DOCSSEND_REFERENT));
             //moi them vo
             beantemp.setNumberVersion(rs.getInt(DOCSRECV_NUMBERVERSION));
             beantemp.setNumberPage(rs.getInt(DOCSRECV_NUMBERPAGE));
             beantemp.setBranchId(rs.getInt(DOCSRECV_BRANCH_ID));
             beantemp.setClassifyId(rs.getInt(DOCSRECV_CLASSIFY_ID));
//             if(beantemp.getStoreAgeId()>0){
//                 FDepartment Bdep=new FDepartment();
//                 Bdep.setId(beantemp.getStoreAgeId());
//                 beantemp.setDepName(new BDepartments().getRecordByID(Bdep).getName());
//             }
             if(full){
                 beantemp.setDossierId(rs.getInt("DOCS_DOSSIERS_ID"));////////
                 beantemp.setDossierId_doc(beantemp.getDossierId());///////////
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
        FDocssend bean = (FDocssend)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getFormId());
             params.add(bean.getLocalCode());                           
             params.add(bean.getDocCode());                           
             params.add(bean.getUserId());                           
             params.add(bean.getStatusId());                           
             //params.add(bean.getCurrentSqlDate());  
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
             params.add(2);   
             params.add(bean.getReferentId());   
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
    public List setParamsUpdate(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FDocssend bean = (FDocssend)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getFormId());
             params.add(bean.getLocalCode());                           
             params.add(bean.getDocCode());                           
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
             params.add(2);   
             params.add(bean.getReferentId());
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
    public FDocssend InforSearch__(ResultSet rs) throws EException
    {
        final String LOCATION = "->InforSearch()";
        FDocssend beantemp = new FDocssend();
         try {
             beantemp.setId(rs.getInt(DOCSSEND_DOC_ID));
             
             beantemp.setDocCode(rs.getString(DOCSSEND_DOCCODE));
             beantemp.setLocalCode(rs.getString(DOCSSEND_LOCALCODE));
             beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSSEND_DOCDATE)));
             beantemp.setAbstracts(rs.getString(DOCSSEND_ABSTRACT));
             beantemp.setStatusColor(rs.getString(DOCSSEND_STATUS_COLOR));
             beantemp.setExpressColor(rs.getString(EXPRESS_COLOR));
             beantemp.setStatusName(rs.getString(DOCSSEND_STATUS_NAME));
             beantemp.setReaded(rs.getInt(DOC_TRAILER_SEND_READED));
             beantemp.setCreator(rs.getString(USERS_FULLNAME));//code cong van
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    public FDocssend getInformations(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformations()";
        FDocssend beantemp = new FDocssend();
         try {
             beantemp.setId(rs.getInt(DOCSSEND_DOC_ID));
             beantemp.setDocCode(rs.getString(DOCSSEND_DOCCODE));
             beantemp.setLocalCode(rs.getString(DOCSSEND_LOCALCODE));
             beantemp.setDocDate(beantemp.dateToString(rs.getDate(DOCSSEND_DOCDATE)));
             beantemp.setAbstracts(rs.getString(DOCSSEND_ABSTRACT));
             beantemp.setStatusColor(rs.getString(DOCSSEND_STATUS_COLOR));
             beantemp.setExpressColor(rs.getString(EXPRESS_COLOR));
             beantemp.setStatusName(rs.getString(DOCSSEND_STATUS_NAME));
             beantemp.setReaded(rs.getInt(DOC_TRAILER_SEND_READED));
             beantemp.setSigner(rs.getString(DOCSSEND_SIGNER));
             beantemp.setStatusId(rs.getInt(DOCSSEND_STATUS_ID));
             beantemp.setUserId(rs.getInt(DOCSSEND_USER_ID));
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return beantemp;
    }
    public FDocssend getDetail(Connection cnn, FDocssend bean,int checkRulesCreator) throws EException 
    {
      final String LOCATION = this.toString() + "getDetail()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocssend beantemp =new FDocssend();
      try
      {
        List params =new ArrayList();
        params.add(bean.getUserId());
        params.add(bean.getId());
        params.add(bean.getUserId());
        params.add(bean.getId());
        params.add(bean.getId());
         if ((bean.getObServer()>0 && bean.getCheckObServer()>0) || bean.getObServer()==1){
             //.println(SQL_SELECT_DOCSSEND_BY_ID_DETAIL_OBSERVER);
             prpstm =prepareStatement(cnn,SQL_SELECT_DOCSSEND_BY_ID_DETAIL_OBSERVER,params);
         }else{
             params.add(bean.getUserId());
             //.println(SQL_SELECT_DOCSSEND_BY_ID_DETAIL);
             prpstm =prepareStatement(cnn,SQL_SELECT_DOCSSEND_BY_ID_DETAIL,params);
         }
       
        rs = prpstm.executeQuery();
        DFilesSend dao2=new DFilesSend();
        
        if((rs != null) && (rs.next()))
        {
            beantemp=getInformation(rs,true);  
            if (!(bean.getObServer()>0 || bean.getCheckObServer()>0)){
                beantemp.setReaded(rs.getInt(DOC_TRAILER_RECV_READED));
            }
            if(beantemp.getFromId()!=null && !beantemp.getFromId().equals("")){
              beantemp.setAllFroms(new DFrom().getFromInID(cnn,beantemp.getFromId()));
            }
            
            if(beantemp.getReferentId()!=null && !beantemp.getReferentId().equals("")){
              beantemp.setReferents(getAllReferens(cnn,beantemp.getReferentId()));
            }
            
//            if(beantemp.getStoreIds()!=null && !beantemp.getStoreIds().equals("")){
//              beantemp.setStores(getAllStoreDep(cnn,beantemp.getStoreIds()));
//            }
            
            FBeans beanFiles=dao2.getAllByDocId(cnn,beantemp.getId(),checkRulesCreator);
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
    
    
    public FBeans getAllReferens(Connection cnn,String referents) throws EException 
    {
      final String LOCATION = this.toString() + "getAllReferens()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FDocsrecv beantemp = null;
        FBeans beans=null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_ALLDOCRECV_SELECT_IN_ID.replaceAll("#",referents));
        rs = prpstm.executeQuery();
        beans=new FBeans();
        int i=0;
        while((rs != null) && (rs.next()) && (i<=20))
        {
        i++;
          beantemp = new FDocsrecv();
          beantemp.setId(rs.getInt(DOCSRECV_DOC_ID));
          beantemp.setDocCode(rs.getString(DOCSRECV_DOCCODE));
          beantemp.setAbstracts(rs.getString(DOCSRECV_ABSTRACT));         
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
    
   
    
  
}
