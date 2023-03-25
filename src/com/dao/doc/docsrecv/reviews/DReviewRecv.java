//package com.dao.doc.docsrecv.reviews;
//
//
//import com.dao.foryou.DSqlForYou;
//
//import com.form.FSeed;
//import com.form.FBeans;
//import com.exp.EException;
//
//
//import com.form.doc.docsSearch.FDocsSearch;
//import com.form.doc.docssend.FDocssend;
//
//import com.lib.AppConfigs;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.struts.upload.FormFile;
//
//public class DReviewRecv extends DSqlForYou
//{
//    public boolean isExistAdd(Connection conn, FSeed seed)throws EException{
//    final String LOCATION = "->isExist()";
//    boolean result = false;
//    FDocssend bean = (FDocssend)seed;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null; 
//     try {
//          pstmt = conn.prepareStatement(SQL_DOCSSEND_CHECK_CODE_ADD);    
//          pstmt.setString(PARAM_01,bean.getDocCode());//ma cong van ko duoc trung`
//          rs = pstmt.executeQuery();
//          result = rs!=null && rs.next();
//     }
//     catch (SQLException sqle) {            
//         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
//     }
//     finally {
//         closeResultSet(rs);
//         closePreparedStatement(pstmt);        
//     }
//     return result;
//    }
//    public boolean isExistUpdate(Connection conn, FSeed seed)throws EException{
//    final String LOCATION = "->isExist()";
//    boolean result = false;
//    FDocssend bean = (FDocssend)seed;
//    PreparedStatement pstmt = null;
//    ResultSet rs = null; 
//     try {
//                pstmt = conn.prepareStatement(SQL_DOCSSEND_CHECK_CODE_UPDATE);  
//                pstmt.setInt(PARAM_01,bean.getId());
//                pstmt.setString(PARAM_02,bean.getDocCode());
//                rs = pstmt.executeQuery();
//                result = rs!=null && rs.next();
//     }
//     catch (SQLException sqle) {            
//         if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
//     }
//     finally {
//         closeResultSet(rs);
//         closePreparedStatement(pstmt);        
//     }
//     return result;
//    }
////  public FBeans getAlldocssend(Connection cnn,FSeed seed) throws EException
////  {
////    final String LOCATION = this.toString() + "~~>getAlldocssend()";
////    PreparedStatement prpstm = null;
////    FDocssend bean=(FDocssend)seed;
////    ResultSet rs = null;
////    FBeans beans = null;
////    try
////    {
////      prpstm = cnn.prepareStatement(SQL_SELECT_ALL_DOCSSEND,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);     
////    //.println(SQL_SELECT_ALL_DOCSSEND);
////      rs = prpstm.executeQuery();
////        beans = new FBeans();
////        if (rs!=null)    rs.last(); 
////        beans.setTotalRows(rs.getRow());
////        beans.setPageIndex(bean.getPageIndex());
////            if(beans.getFirstRecord()<=1){
////                rs.beforeFirst();
////            }else{
////                rs.absolute(beans.getFirstRecord()-1);
////            }
////            int i=0;
////      while((rs != null) && (rs.next())&& getDk(i,bean.getPageIndex()))
////      { i++;
////          beans.add(getInformation(rs));
////      }
////    }
////    catch(SQLException sqle)
////    {
////      if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
////    }
////    finally
////    {
////      closeResultSet(rs);
////      closePreparedStatement(prpstm);
////    }
////    return beans;
////  }
//  
//  public boolean addNew(Connection cnn, FSeed seed) throws  EException
//  {
//      final String LOCATION = this.toString() + INSERT;
//      Boolean result = false;
//      PreparedStatement prstm = null;
//     
//      try
//      {
//          List params = setParams(seed);
//          prstm = prepareStatement(cnn,SQL_DOCSSEND_ADD_NEW,params);
//            result = prstm.executeUpdate()>0;
//   
//      }
//      catch(Exception sqle)
//      {
//        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
//      }
//      finally
//      {
//        closePreparedStatement(prstm);
//      }
//    return result;
//  }
//  
//  public boolean update(Connection cnn, FSeed seed) throws  EException
//  {
//      final String LOCATION = this.toString() + UPDATE;
//      boolean result = false;
//      PreparedStatement prstm = null;
//      try
//      {FDocssend bean = (FDocssend)seed;
//        List params = setParams(seed);
//          params.add(bean.getId());
//          //.print(SQL_DOCSSEND_UPDATE);
//        prstm = prepareStatement(cnn,SQL_DOCSSEND_UPDATE,params);
//        
//        result = prstm.executeUpdate()>0;
//      }
//      catch(SQLException sqle)
//      {
//        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
//      }
//      finally
//      {
//        closePreparedStatement(prstm);
//      }
//    return result;
//  }
//  
//  public boolean delete(Connection cnn, FSeed seed)throws EException
//  {
//    final String LOCATION = this.toString() + "~~>delete()";
//    FDocssend bean = (FDocssend)seed;
//    boolean result=true;
//    try
//    {
//      delete(cnn, TABLE_DOCSSEND, DOCSSEND_DOC_ID + EQUAL + bean.getId());
//    }
//    catch(EException ex)
//    {
//      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
//      result=false;
//    }
//    return result;
//  }  
//
//  
////  public FDocssend getById(Connection cnn, FDocssend bean) throws EException 
////  {
////    final String LOCATION = this.toString() + "getById()";
////    PreparedStatement prpstm = null;
////    ResultSet rs = null;
////      FDocssend beantemp =new FDocssend();
////    try
////    {
////      prpstm = cnn.prepareStatement(SQL_SELECT_DOCSSEND_BY_ID);
////      prpstm.setInt(PARAM_01,bean.getId());
////      rs = prpstm.executeQuery();
////      if((rs != null) && (rs.next()))
////      {
////          beantemp=getInformation(rs);
////          DFilesSend dao2=new DFilesSend();
////          FBeans beanFiles=dao2.getAllByDocId(cnn,beantemp.getId());
////          beantemp.setAllFiles(beanFiles);
////      }
////    }
////    catch(SQLException sqle)
////    {
////      if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
////    }
////    finally
////    {
////      closeResultSet(rs);
////      closePreparedStatement(prpstm);
////    }
////    return beantemp;    
////  } 
//    public FDocssend getTopId(Connection cnn) throws EException 
//    {
//      final String LOCATION = this.toString() + "getTopId()";
//      PreparedStatement prpstm = null;
//      ResultSet rs = null;
//      FDocssend beantemp = new FDocssend();
//      try
//      {
//        prpstm = cnn.prepareStatement("SELECT TOP 1.*,B.FULLNAME FROM DOCS_SEND A INNER JOIN USERS B ON A.USER_ID=B.USER_ID ORDER BY DOC_ID DESC");
//        rs = prpstm.executeQuery();
//        if((rs != null) && (rs.next()))
//        {
//                beantemp=getInformation(rs);
//        }
//      }
//      catch(SQLException sqle)
//      {
//        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
//      }
//      finally
//      {
//        closeResultSet(rs);
//        closePreparedStatement(prpstm);
//      }
//      return beantemp;    
//    }
//    public FDocssend getInformation(ResultSet rs) throws EException
//    {
//        final String LOCATION = "->getInformation()";
//        FDocssend beantemp = new FDocssend();
//         try {
//             beantemp.setId(rs.getInt(DOCSSEND_DOC_ID));
//             beantemp.setFormId(rs.getInt(DOCSSEND_FORM_ID));
//             beantemp.setLocalCode(rs.getString(DOCSSEND_LOCALCODE));
//             beantemp.setDocCode(rs.getString(DOCSSEND_DOCCODE));
//             beantemp.setCreator(rs.getString(DOCSSEND_CREATOR));
//             beantemp.setUserId(rs.getInt(DOCSSEND_USER_ID));
//             beantemp.setStatusId(rs.getInt(DOCSSEND_STATUS_ID));
//             beantemp.setTimeCreate(rs.getString(DOCSSEND_TIMECREATE));
//             beantemp.setLocalDate(rs.getString(DOCSSEND_LOCALDATE));
//             beantemp.setDocDate(rs.getString(DOCSSEND_DOCDATE));
//             beantemp.setStoreAgeId(rs.getInt(DOCSSEND_STOREAGE_ID));
//             beantemp.setExpressId(rs.getInt(DOCSSEND_EXPRESS_ID));
//             beantemp.setSecureId(rs.getInt(DOCSSEND_SECURE_ID));
//             beantemp.setViaId(rs.getInt(DOCSSEND_VIA_ID));
//             beantemp.setFromId(rs.getInt(DOCSSEND_FROM_ID));
//             beantemp.setAddress(rs.getString(DOCSSEND_ADDRESS));
//             beantemp.setDocsTypeId(rs.getInt(DOCSSEND_DOCTYPE_ID));
//             beantemp.setAbstracts(rs.getString(DOCSSEND_ABSTRACT));
//             beantemp.setDescription(rs.getString(DOCSSEND_DESCRIPTION));
//             beantemp.setSigner(rs.getString(DOCSSEND_SIGNER));
//             beantemp.setDeadLine(rs.getString(DOCSSEND_DEADLINE));
//             beantemp.setDossierId(rs.getInt(DOCSSEND_DOSSIERS_ID));
//             beantemp.setWorkflowId(rs.getInt(DOCSSEND_WORKFLOW_ID));
//         }
//         catch (SQLException sqle) {            
//             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
//         }
//         finally {
//         }
//         return beantemp;
//    }
//
//
//    public List setParams(FSeed seed) throws EException
//    {
//        final String LOCATION = "->setParams()";
//        FDocssend bean = (FDocssend)seed;
//        List params = new ArrayList();
//         try {
//             params.add(bean.getFormId());
//             params.add(bean.getLocalCode());                           
//             params.add(bean.getDocCode());                           
//             params.add(bean.me.getId());                           
//             params.add(bean.getStatusId());                           
//             params.add(bean.getTimeCreate());   
//             params.add(bean.getLocalDate());   
//             params.add(bean.getDocDate());   
//             params.add(bean.getStoreAgeId());   
//             params.add(bean.getExpressId());   
//             params.add(bean.getSecureId());   
//             params.add(bean.getViaId());   
//             params.add(bean.getFromId());   
//             params.add(bean.getAddress());   
//             params.add(bean.getDocsTypeId());   
//             params.add(bean.getAbstracts());   
//             params.add(bean.getDescription());   
//             params.add(bean.getSigner());   
//             params.add(bean.getDeadLine());   
//             params.add(bean.getDossierId());   
//             params.add(bean.getWorkflowId());   
//         }
//         catch (Exception exp) {            
//             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
//         }
//         finally {
//         }
//         return params;
//    }
//    
//    public FBeans search(Connection cnn,FSeed seed) throws EException 
//    {
//      final String LOCATION = this.toString() + "search()";
//      PreparedStatement prpstm = null;
//      ResultSet rs = null;
//      FDocsSearch bean =(FDocsSearch)seed;
//      FBeans beans =null;
//      String SELECT="";//SQL_SELECT_ALL_DOCSSEND_SEARCH;
//      String WHERE ="";
//      try
//      {
//        prpstm = cnn.prepareStatement(getCivi(SELECT,WHERE,bean),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//        rs = prpstm.executeQuery();
//        beans = new FBeans();
//        if (rs!=null)    rs.last(); 
//        beans.setTotalRows(rs.getRow());
//        beans.setPageIndex(bean.getPageIndex());
//        if(beans.getFirstRecord()<=1){
//            rs.beforeFirst();
//        }else{
//            rs.absolute(beans.getFirstRecord()-1);
//        }
//        int i=0;
//            while((rs != null) && (rs.next())&& getDk(i,bean.getPageIndex()))
//        {
//            i++;
//                beans.add(getInformation(rs));
//        }
//      }
//      catch(SQLException sqle)
//      {
//        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
//      }
//      finally
//      {
//        closeResultSet(rs);
//        closePreparedStatement(prpstm);
//      }
//      return beans;    
//    }
//    private Boolean getDk(int i,int pagingNum)
//    {
//    boolean dk=false;
//            if(pagingNum==-1){
//                dk=true;
//            }else{
//            dk=(i<AppConfigs.APP_ROWS_VIEW);
//            }
//    return dk;
//    }
//    private String getCivi(String SELECT,String WHERE,FDocsSearch bean){
//        if(!bean.getLocalCode().equals(""))  {
//            WHERE=WHERE+" " +AND+ DOCSSEND_LOCALCODE + " like " +"'%"+ bean.getLocalCode()+"%'";
//        }
//        if(!bean.getDocCode().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_DOCCODE + " like " + "'%" +bean.getDocCode()+"%'";
//        }
//        if(!bean.getCreator().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_CREATOR + " like " +"'%"+ bean.getCreator()+"%'";
//        }
//        if(bean.getStatusId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_STATUS_ID + EQUAL +"'"+ bean.getStatusId()+"'";
//        }
//        if(!bean.getTimeCreateFrom().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_TIMECREATE + " >= " +"'"+ bean.getTimeCreateFrom()+"'";
//        }
//        if(!bean.getTimeCreateTo().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_TIMECREATE + " < " +"'"+ bean.dateToString(bean.addDays(bean.stringToDate(bean.getTimeCreateTo()),1)) +"'";
//        }
//        if(!bean.getLocalDateFrom().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_LOCALDATE + " >= " +"'"+ bean.getLocalDateFrom()+"'";
//        }
//        if(!bean.getLocalDateTo().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_LOCALDATE + " < " +"'"+ bean.dateToString(bean.addDays(bean.stringToDate(bean.getLocalDateTo()),1)) +"'";
//        }
//        if(!bean.getDocDateFrom().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_DOCDATE + " >= " +"'"+ bean.getDocDateFrom()+"'";
//        }
//        if(!bean.getDocDateTo().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_DOCDATE + " < " +"'"+ bean.dateToString(bean.addDays(bean.stringToDate(bean.getDocDateTo()),1))+"'";
//        }
//        
//        if(bean.getStoreAgeId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_STOREAGE_ID + EQUAL + bean.getStoreAgeId();
//        }
//        if(bean.getExpressId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_EXPRESS_ID + EQUAL + bean.getExpressId();
//        }
//        if(bean.getSecureId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_SECURE_ID + EQUAL + bean.getSecureId();
//        }
//        if(bean.getFromId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_FROM_ID + EQUAL + bean.getFromId();
//        }
//        
//        if(bean.getDocsTypeId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_DOCTYPE_ID + EQUAL + bean.getDocsTypeId();
//        }
//        
//        if(!bean.getSigner().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_SIGNER + " like " +"'"+ bean.getSigner()+"'";
//        }
//        if(!bean.getDeadLineFrom().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_DEADLINE + " >= " +"'"+ bean.getDeadLineFrom()+"'";
//        }
//        if(!bean.getDeadLineTo().equals(""))  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_DEADLINE + " < " +"'"+ bean.dateToString(bean.addDays(bean.stringToDate(bean.getDeadLineTo()),1))+"'";
//        }
//        if(bean.getDossierId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_DOSSIERS_ID + EQUAL + bean.getDossierId();
//        }
//        if(bean.getWorkflowId()!=0)  {
//          WHERE=WHERE+" " +AND+ DOCSSEND_WORKFLOW_ID + EQUAL + bean.getWorkflowId();
//        }
//        if(WHERE.equals("") || WHERE==""){
//            WHERE=" AND 1=2";
//        }
//        String SQL=SELECT+WHERE;
//    return SQL;    
//    }
//    
//}
