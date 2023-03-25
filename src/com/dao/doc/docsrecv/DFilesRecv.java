package com.dao.doc.docsrecv;


import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docsrecv.FDocsrecv;
import com.form.doc.docsrecv.FFilesRecv;

import com.lib.AppConfigs;

import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DFilesRecv extends DSqlForYou
{
  

  public boolean addBath(Connection cnn, FSeed seed,int docId) throws  EException
  {
      final String LOCATION = this.toString() + INSERT;
      Boolean flag = false;
      PreparedStatement prstm = null;
      FDocsrecv bean=(FDocsrecv)seed;
      String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT + AppConfigs.SYSTEM_FILE_SCHIP + seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD);
      String filename="";
      (new File(dirs)).mkdirs();                        
      try
      {
                prstm = cnn.prepareStatement(SQL_FILESRECV_ADD_NEW);
                int doc_id=(bean.getId()>0)?bean.getId():docId;
                for (int i =0;i<bean.getTotalFile();i++){
                    filename=encodeFileName(bean.me.getId());
                    bean.upload(bean.getFile(i),dirs+filename);
                    prstm.setInt(PARAM_01,doc_id);
                    prstm.setString(PARAM_02,new String((bean.getFile(i).getFileName().getBytes()),"UTF-8"));
                    prstm.setString(PARAM_03,filename);
                    prstm.setString(PARAM_04,seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD));
                    prstm.setInt(PARAM_05,bean.getBlockFile());
                    prstm.addBatch();
                    flag = true;
                }
               if(bean.me.getExtInformation()!=null && bean.getIdFiles()!=null){
                   List scans = (List)bean.me.getExtInformation();
                   int[] ids = bean.getIdFiles();
                   String scanPath = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FILE_REVIEW_DRAFT_PATH + bean.me.getId()+ ".";          
                   for (int i =0;i<ids.length;i++){
                       File fileSrc = new File(scanPath + scans.get(ids[i]));
                       if(fileSrc.exists()){
                           filename=encodeFileName(bean.me.getId());
                           File fileDes = new File(dirs + filename);
                           fileSrc.renameTo(fileDes);
                           fileSrc.delete();
                           prstm.setInt(PARAM_01,doc_id);
                           prstm.setString(PARAM_02,(String)scans.get(ids[i]));
                           prstm.setString(PARAM_03,filename);
                           prstm.setString(PARAM_04,seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD));
                           prstm.setInt(PARAM_05,0);
                           prstm.addBatch();                       
                           flag = true;
                       }
                   }
               }
                if (flag){
                    prstm.executeBatch();
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
    return flag;
  }
    public FFilesRecv getLastFile(Connection cnn, FFilesRecv bean) throws EException 
    {
      final String LOCATION = this.toString() + "getLastFile()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FFilesRecv beantemp = new FFilesRecv();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILERECV_BY_LAST);
        prpstm.setInt(PARAM_01,bean.getDocId());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
                beantemp=getInformation(rs);
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

  public boolean delete(Connection cnn, FSeed seed)throws EException
  {
    final String LOCATION = this.toString() + "~~>delete()";
    FFilesRecv bean = (FFilesRecv)seed;
    boolean result=false;
    try
    {
    if(bean.getIdFiles()>0){
        result = delete(cnn, TABLE_FILESRECV, FILESRECV_ID + EQUAL + bean.getIdFiles())>0;
    }else{
        result = delete(cnn, TABLE_FILESRECV, FILESRECV_DOC_ID + EQUAL + bean.getDocId())>0;
    }
      
    }
    catch(EException ex)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
    }
    return result;
  }  
 
  public FFilesRecv getById(Connection cnn, FFilesRecv bean) throws EException 
  {
    final String LOCATION = this.toString() + "getById()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FFilesRecv beantemp = new FFilesRecv();
    try
    {
      prpstm = cnn.prepareStatement(SQL_SELECT_FILERECV_BY_ID);
      prpstm.setInt(PARAM_01,bean.getIdFiles());
      rs = prpstm.executeQuery();
      if((rs != null) && (rs.next()))
      {
              beantemp=getInformation(rs);
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
  
    public FBeans getAllByDocId(Connection cnn,int DocId) throws EException 
    {
      final String LOCATION = this.toString() + "getAllByDocId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILESRECV_BY_ID);
        prpstm.setInt(PARAM_01,DocId);
        rs = prpstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            beans.add(getInformation(rs));
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
    
    public int checkDocsDeadLine(Connection cnn,FDocsrecv bean) throws EException 
    {
      final String LOCATION = this.toString() + "checkDocsDeadLine()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      int result = 0;
      try
      {
        List params = new ArrayList();      
       
        //.println(SQL_CHECK_DEADLINE);
        params.add(bean.getUserId());
        params.add(bean.addDays(bean.getCurrentSqlDate(),-(AppConfigs.DOCS_MESSAGES_DATELINE)));       
        prpstm = prepareStatement(cnn,SQL_CHECK_DEADLINE,params);
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
  
    public FFilesRecv getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FFilesRecv beantemp = new FFilesRecv();
         try {
             beantemp.setIdFiles(rs.getInt(FILESRECV_ID));
             beantemp.setDocId(rs.getInt(FILESRECV_DOC_ID));
             beantemp.setFileName(rs.getString(FILESRECV_NAME));
             beantemp.setFile(rs.getString(FILESRECV_FILE));
             beantemp.setPath(rs.getString(FILESRECV_PATH));
             beantemp.setBlockFile(rs.getInt(FILESRECV_FILE_ID_BLOCK));             
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
        FFilesRecv bean = (FFilesRecv)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getDocId());                           
             params.add(bean.getFileName());                           
             params.add(bean.getFile());    
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    private String encodeFileName(long userID)
    {
        return userID + "." + System.currentTimeMillis(); 
    }
    
  
}
