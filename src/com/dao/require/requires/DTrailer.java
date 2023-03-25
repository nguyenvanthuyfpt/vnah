package com.dao.require.requires;


import com.dao.require.DSqlRequire;

import com.exp.EException;

import com.form.FSeed;
import com.form.doc.assign.FDocAssign;
import com.form.require.requires.FRequire;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DTrailer  extends DSqlRequire
{
    
    
    public String getUserPrioritoesByRmId(Connection cnn, int ruleId) throws EException
    {
        final String LOCATION = this.toString() + "getUserPrioritoesByRmId()";
        PreparedStatement prstm = null;
        String usersRecv ="";
        ResultSet rs = null;
        try
        {
          
          prstm = cnn.prepareStatement(SQL_SELECT_RM_EMP_PRI);
          prstm.setLong(PARAM_01,ruleId);         
          rs = prstm.executeQuery();       
          while((rs != null) && rs.next()){ 
            if (!usersRecv.equals("")) usersRecv += ",";            
              usersRecv += "|"+rs.getInt(PARAM_01)+"|";
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
          result =  execute(cnn,SQL_UPDATE_READED_RECV_FOR_PEOPLE_SEND_ME,params)>0;//da sua                  
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
      FRequire bean = (FRequire)seed;
      try
      {  
          if(bean.getReviewIds()==null){
              bean.setReviewIds(getUserPrioritoesByRmId(cnn,bean.getRmId()));
          }          
          List params = setParams(bean);
          result =  execute(cnn,SQL_INSERT_RM_REVIEW,params)>0;              
           
      }
      catch(Exception sqle)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
      }
      return result;    
    }  
    
    
    public List setParams(FRequire bean) throws EException
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
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
   public boolean checkRole(int pr, int key_id){       
          return (pr>0 && (key_id==(pr & key_id)))?true:false;
   }
}
