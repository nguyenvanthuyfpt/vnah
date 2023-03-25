package com.dao.doc.docssend;


import com.dao.foryou.DSqlForYou;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.doc.docssend.FDocssend;
import com.form.doc.docssend.FFilesSend;

import com.lib.AppConfigs;

import java.io.File;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DFilesSend extends DSqlForYou
{
    public boolean insert(Connection cnn,FFilesSend bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      Boolean result = false;
      FSeed seed =new FSeed();
      PreparedStatement prstm = null;
      try{
          List params =new ArrayList();
          params.add(bean.getDocId());
          params.add(new String((bean.getFileName().getBytes()),"UTF-8"));
          params.add(bean.getFile());
          params.add(seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD));
          params.add(bean.getUserId());
          params.add(new java.sql.Timestamp(System.currentTimeMillis()));
          params.add(bean.getBlockFile());
          prstm = prepareStatement(cnn,SQL_FILESSEND_ADD_NEW,params);
          result = prstm.executeUpdate()>0;
      }catch(Exception sqle){
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }finally{
        closePreparedStatement(prstm);
      }
      return result;    
    }
    public FFilesSend getLastFile(Connection cnn, FFilesSend bean) throws EException 
    {
      final String LOCATION = this.toString() + "getLastFile()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FFilesSend beantemp = new FFilesSend();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILESEND_BY_LAST);
        prpstm.setInt(PARAM_01,bean.getDocId());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next())){               
                beantemp=getInformation(rs,beantemp);
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
    
  public boolean addBath(Connection cnn, FSeed seed,int docId) throws  EException
  {
      final String LOCATION = this.toString() + INSERT;
      Boolean flag = false;
      PreparedStatement prstm = null;
      FDocssend bean=(FDocssend)seed;
      String dirs = AppConfigs.APP_SYSTEM_PATH + AppConfigs.DOC_FOLDER_ROOT+  AppConfigs.SYSTEM_FILE_SCHIP + seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD);
      String filename="";
      (new File(dirs)).mkdirs();                        
      try
      {
          prstm = cnn.prepareStatement(SQL_FILESSEND_ADD_NEW);
           ////.println(SQL_FILESSEND_ADD_NEW);
                for (int i =0;i<bean.getTotalFile();i++){
                    filename=encodeFileName(bean.me.getId(),i+1);
                    bean.upload(bean.getFile(i),dirs+filename);
                    prstm.setInt(PARAM_01,docId);
                    prstm.setString(PARAM_02,new String((bean.getFile(i).getFileName().getBytes()),"UTF-8"));
                    prstm.setString(PARAM_03,filename);
                    prstm.setString(PARAM_04,seed.dateToString(seed.getCurrentSqlDate(),AppConfigs.DOC_FOLDER_UPLOAD));
                    prstm.setLong(PARAM_05,bean.getUserId());              
                    prstm.setTimestamp(PARAM_06,new java.sql.Timestamp(System.currentTimeMillis()));
                    prstm.setInt(PARAM_07,0);
                    prstm.setInt(PARAM_08,1);
                    prstm.setString(PARAM_09,bean.getFileText()[i]);//Ghi chu file
                    prstm.setInt(PARAM_10,0);//so lan doc =0
                    prstm.setInt(PARAM_11,0);//so lan doc =0
                    prstm.addBatch();
                    flag = true;
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
  
  
    public boolean addFileOnline(Connection cnn, FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        FDocssend bean=(FDocssend)seed;                               
        try
        {           
        //.println(SQL_FILESSEND_ADD_NEW_FILEONLINE);
             List params =new ArrayList();
             params.add(bean.getDocId());
             params.add(new String((bean.getFileName().getBytes()),"UTF-8"));
             params.add(bean.getReName());
             params.add(bean.getPathFile());
             params.add(bean.getUserId());
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));            
             if (bean.getIndex()>0){
                 params.add(bean.getFileId());
                 params.add(bean.getDescription());
                 params.add(0);
                 params.add(0);
                 params.add(new String((bean.getFileName().getBytes()),"UTF-8"));      
                 params.add(bean.getDocId()); 
//                 //.println(SQL_FILESSEND_ADD_NEW_FILEONLINE);
                 prstm = prepareStatement(cnn,SQL_FILESSEND_ADD_NEW_FILEONLINE,params);
             }else{
                 params.add(0);
                 params.add(1);
                 params.add("");
                 params.add(0);
                 params.add(0); 
                 //.println(SQL_FILESSEND_ADD_NEW);
                 prstm = prepareStatement(cnn,SQL_FILESSEND_ADD_NEW,params);
             }
             result = prstm.executeUpdate()>0;
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
    public boolean addReadedFile(Connection cnn,int fileId,long meId) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;      
      try
      {  
          List params = new ArrayList();          
          params.add(fileId);
          params.add(meId);
          //.println(SQL_UPDATE_READED_ADD_ONE_WHEN_DOWNLOAD);
          result =  execute(cnn,SQL_UPDATE_READED_ADD_ONE_WHEN_DOWNLOAD,params)>0;
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
  public boolean delete(Connection cnn, FSeed seed)throws EException
  {
    final String LOCATION = this.toString() + "~~>delete()";
    FFilesSend bean = (FFilesSend)seed;
    try
            {
                    if(bean.getIdFiles()>0){
                        delete(cnn, TABLE_FILESSEND, FILESSEND_ID + EQUAL + bean.getIdFiles());
                    }else{
                        delete(cnn, TABLE_FILESSEND, FILESSEND_DOC_ID + EQUAL + bean.getDocId());
                    }
            }
    catch(EException ex)
    {
      if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
    }
    return true;
  }  

  
  public FFilesSend getById(Connection cnn, FFilesSend bean) throws EException 
  {
    final String LOCATION = this.toString() + "getById()";
    PreparedStatement prpstm = null;
    ResultSet rs = null;
    FFilesSend beantemp = new FFilesSend();
    try
    {
      prpstm = cnn.prepareStatement(SQL_SELECT_FILESEND_BY_ID);
      prpstm.setInt(PARAM_01,bean.getIdFiles());
      rs = prpstm.executeQuery();
      if((rs != null) && (rs.next()))
      {
          beantemp=getInformation(rs,beantemp);
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
  
  
    public FFilesSend getMaxbyDocId(Connection cnn,int docId) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FFilesSend beantemp = new FFilesSend();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILESEND_MAX_BY_DOC_ID);
        prpstm.setInt(PARAM_01,docId);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
         beantemp=getInformation(rs,beantemp);
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
    
    
    public FFilesSend getMaxVByDoc(Connection cnn,int docId,String realName) throws EException 
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FFilesSend beantemp = new FFilesSend();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FRILESEND_MAX_VERSION_BY_DOC);
        prpstm.setInt(PARAM_01,docId);
          prpstm.setString(PARAM_02,realName);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {
               
                beantemp=getInformation(rs,beantemp);
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
    
    
    public FBeans getAllByDocId(Connection cnn,int DocId,int views) throws EException 
    {
      final String LOCATION = this.toString() + "getAllByDocId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      String SQL=SQL_SELECT_FILESSEND_BY_ID;
      String sql_ORDER_BY= ORDER_BY + FILESSEND_NAME + COMMA + FILESSEND_ID + DESC;
      try
      {
        List params =new ArrayList();
        params.add(DocId);
        if(views>0){
            params.add(views);
            SQL+= AND + TABLE_FILESSEND + STOP  + FILESSEND_VIEWS + EQUAL + QUESTION ;
        }
//          //.println(SQL + sql_ORDER_BY);
        prpstm = prepareStatement(cnn,SQL + sql_ORDER_BY,params);
        
        rs = prpstm.executeQuery();
        String[] temp = null;
        while((rs != null) && (rs.next()))
        {
            FFilesSend beanTemp = new FFilesSend();  
            beanTemp = getInformation(rs,beanTemp);             
            beanTemp.setUserName(rs.getString(USERS_FULLNAME));
            beans.add(beanTemp);
        }
      }
      catch(SQLException sqle)
      {
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }
      finally {
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;    
    }
    
    public FBeans getAllFilesEqualNameByDoc(Connection cnn,int DocId,int fileId) throws EException 
    {
      final String LOCATION = this.toString() + "getAllFilesEqualNameByDoc()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
        FBeans beans =new FBeans();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILES_EQUALS_NAME_BY_ID);
        //.println(SQL_SELECT_FILES_EQUALS_NAME_BY_ID);
        prpstm.setInt(PARAM_01,DocId);
        prpstm.setInt(PARAM_02,fileId);
        prpstm.setInt(PARAM_03,DocId);
        rs = prpstm.executeQuery();
        while((rs != null) && (rs.next()))
        {
            FFilesSend beantemp = new FFilesSend();
            beantemp.setUserName(rs.getString(USERS_FULLNAME));
            if (rs.getDate(FILESSEND_CREATETIME)!=null){
               beantemp.setCreateTimeName(beantemp.dateToString(new Date (rs.getTimestamp(FILESSEND_CREATETIME).getTime()),AppConfigs.APP_DATE_TIME));            
            }
            beans.add(getInformation(rs,beantemp));
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
    
    
    public FBeans getAllByFileByeDoc(Connection cnn,FDocssend bean) throws EException 
    {
      final String LOCATION = this.toString() + "getAllByFileByeDoc()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT_FILESSEND_BY_ID_ORTHER);       
        prpstm.setInt(PARAM_01,bean.getId());
        rs = prpstm.executeQuery();        
        while((rs != null) && (rs.next()))
        {
             FFilesSend beantemp = new FFilesSend();
             beantemp.setUserName(rs.getString(USERS_FULLNAME));
             if (rs.getDate(FILESSEND_CREATETIME)!=null){
                beantemp.setCreateTimeName(bean.dateToString(new Date (rs.getTimestamp(FILESSEND_CREATETIME).getTime()),AppConfigs.APP_DATE_TIME));            
             }
             beans.add(getInformation(rs,beantemp));
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
    
  
    
    
    public FFilesSend getInformation(ResultSet rs,FFilesSend beantemp) throws EException
    {
        final String LOCATION = "->getInformation()";        
         try {
             beantemp.setIdFiles(rs.getInt(FILESSEND_ID));
             beantemp.setDocId(rs.getInt(FILESSEND_DOC_ID));
             beantemp.setFileName(rs.getString(FILESSEND_NAME));             
             beantemp.setFile(rs.getString(FILESSEND_FILE));
             beantemp.setCreateTimeName(rs.getString(FILESSEND_CREATETIME));
             beantemp.setPath(rs.getString(FILESSEND_PATH));  
             beantemp.setVersion(rs.getInt(FILESSEND_VERSION));
             beantemp.setParent_id(rs.getInt(FILESSEND_PARRENT_FILE_ID));
             beantemp.setViews(rs.getString(FILESSEND_VIEWS));
             beantemp.setDescription(rs.getString(FILESSEND_FILE_TEXT));
             beantemp.setNumberReadedFile(rs.getInt(FILESSEND_NUMBER_READED_FILE));
             beantemp.setBlockFile(rs.getInt(FILESSEND_FILE_ID_BLOCK));
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
        FFilesSend bean = (FFilesSend)seed;
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
    
    private String encodeFileName(long userID,int index)
    {
        return userID + "." + System.currentTimeMillis() + "_" + index; 
    }
}
