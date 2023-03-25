package com.dao.messages.create;


import com.action.doc.assign.CopyFile;

import com.dao.messages.DSqlMessages;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.messages.create.FCreate;

import com.lib.AppConfigs;

import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DCreateFiles  extends DSqlMessages
{
    public boolean addBath(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      PreparedStatement prstm = null;
      FCreate bean=(FCreate)seed;
      boolean flag = false;
      String folder = AppConfigs.APP_SYSTEM_PATH + AppConfigs.MESSAGES_FILE_PATH;
      (new File(folder)).mkdirs();                        
      int id=getMaxMessageId(cnn,(int)seed.me.getId());
      try
      {
          prstm = cnn.prepareStatement(SQL_INSERT_FILE_MESSAGES);
          
          for (int i =0;i<bean.getTotalFile();i++){
              String filename=encodeFileName(bean.me.getId(),i);
              bean.upload(bean.getFile(i),folder+filename);
              prstm.setInt(PARAM_01,id);
              prstm.setString(PARAM_02,filename);
              prstm.setString(PARAM_03,new String((bean.getFile(i).getFileName().getBytes()),"UTF-8"));//Tên th?c c?a filé
              prstm.setString(PARAM_04,folder);
              prstm.addBatch();
              flag = true;
          }
          if(bean.getFileIds()!=null && bean.getFileIds().length>0){
          for (int i =0;i<bean.getFileIds().length;i++){
              FCreate beanFile=getByFileId(cnn,bean.getFileIds()[i]);
              String filename=encodeFileName(bean.me.getId(),i);
              CopyFile.copyFile(folder + beanFile.getFileName(),folder+filename);
              prstm.setInt(PARAM_01,id);
              prstm.setString(PARAM_02,filename);
              prstm.setString(PARAM_03,beanFile.getReadName());//Tên th?c c?a filé
              prstm.setString(PARAM_04,folder);
              prstm.addBatch();
              flag = true;
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
      return flag;    
    }
    
    
    public FBeans getAllFileByMessageId(Connection cnn,int id) throws EException 
    {
      final String LOCATION = this.toString() + "getAllFileByMessageId()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans =new FBeans();
      try
      {
        prpstm = cnn.prepareStatement(SELECT + STAR +  FROM + TABLE_MESSAGE_FILES + WHERE + FILE_MESSAGES_ID + EQUAL + QUESTION );
        prpstm.setInt(PARAM_01,id);
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
    public boolean delete(Connection cnn, FSeed seed) throws EException
    {
        final String LOCATION = this.toString() + "~~>delete()";
        FCreate bean = (FCreate)seed;
        boolean result=false;
        try{
          result=delete(cnn, TABLE_MESSAGE_FILES, FILE_MESSAGES_ID + EQUAL + bean.getId())>0;
        }catch(EException ex){
          if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
        }
        return result;
    }   
    public FCreate getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";        
        FCreate bean =new FCreate();
         try {
             bean.setFileId(rs.getInt(FILE_ID));
             bean.setId(rs.getInt(FILE_MESSAGES_ID));
             bean.setFileName(rs.getString(FILE_MESSAGES_FILENAME));
             bean.setReadName(rs.getString(FILE_MESSAGES_READNAME));
             bean.setPathFile(rs.getString(FILE_MESSAGES_PATH));                     
         }catch (SQLException sqle) {
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
  
    public List setParams(FSeed seed) throws EException
    {
        FCreate bean = (FCreate)seed;
             List params = new ArrayList();
             params.add(bean.getId());
             params.add(bean.getFileName());            
             params.add(bean.getReadName());                  
             params.add(bean.getPathFile());  
         return params;
    }
    public int getMaxMessageId(Connection cnn,int userId) throws EException
    {
        final String LOCATION = this.toString() + "getRecordById()";
        PreparedStatement prstm = null;
        ResultSet rs = null;      
        int result =0;
        try
        {
          prstm = cnn.prepareStatement("SELECT MAX(ID) FROM MESSAGE_MESSAGES WHERE CREATOR=?");       
          prstm.setInt(PARAM_01,userId);
          rs = prstm.executeQuery();
          if((rs != null) && (rs.next())){           
              result=rs.getInt(PARAM_01);  
          }
        }catch(SQLException sqle){
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }finally{
          closeResultSet(rs);
          closePreparedStatement(prstm);
        }
        return result;        
    }   
    public FCreate getByFileId(Connection cnn,int fileId) throws EException
    {
        final String LOCATION = this.toString() + "getByFileId()";
        PreparedStatement prstm = null;
        ResultSet rs = null;      
        FCreate bean =new FCreate();
        try
        {
          prstm = cnn.prepareStatement("SELECT * FROM MESSAGE_FILES WHERE ID=?");       
          prstm.setInt(PARAM_01,fileId);
          rs = prstm.executeQuery();
          if((rs != null) && (rs.next())){           
              bean=getInformation(rs);
          }
        }catch(SQLException sqle){
          if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        }finally{
          closeResultSet(rs);
          closePreparedStatement(prstm);
        }
        return bean;        
    }   
    private String encodeFileName(long userID,int i)
    {
        String scr=userID + "."+ i + "." + System.currentTimeMillis(); 
        return scr;
    }
}
