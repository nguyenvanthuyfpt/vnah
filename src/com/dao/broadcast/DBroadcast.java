package com.dao.broadcast;


import com.dao.messages.DSqlMessages;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.broadcast.FBroadcast;

import com.inf.agenda.IKeyAgenda;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DBroadcast extends DSqlMessages
{
    public FBeans getAllBroadcastTop(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllBroadcastTop()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FBroadcast beanB = (FBroadcast)seed;
      try
      {      
        List params = new ArrayList();
        String SQL = SQL_BROADCAST_SELECT_ALL + AND + BROADCAST_ID + DIFF + QUESTION;        
        params.add(beanB.getBroadcastId());
        prpstm = prepareStatement(cnn,SQL+SQL_BROADCAST_ORDER_BY,params);
        rs = prpstm.executeQuery();         
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL,params));
        beans.setPageIndex(beanB.getPageIndex());
        if(beans.getFirstRecord()<=1){
            rs.beforeFirst();
        }else{
            rs.absolute(beans.getFirstRecord()-1);
        }    
        int i=0;
        while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW))
        {                 
                beanB=new FBroadcast();
                beanB=getInformation(rs);
                beanB.setFullName(rs.getString(USERS_FULLNAME));
                beans.add(beanB);
          i++;
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
    
    public FBeans getAllBroadcast(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllBroadcast()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      FBroadcast beanB = (FBroadcast)seed;
      try
      {      
        List params = new ArrayList();
        String SQL = SQL_BROADCAST_SELECT_ALL;
        if (beanB.getTitle()!=null && !beanB.getTitle().equals("")){
            SQL += SQL_BROADCAST_ADD_TITLE;
            params.add(PER_CENT + beanB.getTitle() + PER_CENT);
        } 
        if (beanB.getCreatetime()!=null && !beanB.getCreatetime().equals("")){
            SQL += SQL_BROADCAST_ADD_CREATETIME;
            params.add(beanB.stringToSqlDate(beanB.getCreatetime()));
            params.add(beanB.addDays(beanB.stringToSqlDate(beanB.getCreatetime()),1));
        }
        prpstm = prepareStatement(cnn,SQL+SQL_BROADCAST_ORDER_BY,params);
        rs = prpstm.executeQuery();         
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL,params));
        beans.setPageIndex(beanB.getPageIndex());
        if(beans.getFirstRecord()<=1){
            rs.beforeFirst();
        }else{
            rs.absolute(beans.getFirstRecord()-1);
        }    
        int i=0;
        while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW))
        {                 
                beanB=new FBroadcast();
                beanB=getInformation(rs);
                beanB.setFullName(rs.getString(USERS_FULLNAME));
                beans.add(beanB);
          i++;
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
    
    
    public FBeans getAllBroadcastShow(Connection cnn,FBroadcast beanB) throws EException
    {
      final String LOCATION = this.toString() + "~~>getAllBroadcastShow()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try{      
        List params = new ArrayList();     
        prpstm = prepareStatement(cnn,SQL_BROADCAST_SELECT_ALL_SHOW_2 + SQL_BROADCAST_ORDER_BY,params);
        rs = prpstm.executeQuery();         
        beans = new FBeans(); 
        int i=1;
        while((rs != null) && (rs.next() && (i <= IKeyAgenda.BROADCAST_VIEW_DAYS)))
        {  
          i++;
          beans.add(getInformation(rs));         
        }
      } catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }finally{
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;
    }
    public FBeans getShowCalenda(Connection cnn,FBroadcast beanB) throws EException
    {
      final String LOCATION = this.toString() + "~~>getShowCalenda()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBeans beans = null;
      try{      
        List params = new ArrayList();     
        params.add(beanB.stringToSqlDate(beanB.getCreatetime()));
        params.add(beanB.addDays(beanB.stringToSqlDate(beanB.getCreatetime()),1));
        prpstm = prepareStatement(cnn,SQL_BROADCAST_SELECT_ALL_SHOW + SQL_BROADCAST_ORDER_BY,params);
        rs = prpstm.executeQuery();         
        beans = new FBeans();        
        int i =1;
        while((rs != null) && (rs.next()) && (i<=IKeyAgenda.BROADCAST_VIEW_DAYS))
        {      
        i++;
          beans.add(getInformation(rs));         
        }
      } catch(SQLException sqle){
        if(AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
      }finally{
        closeResultSet(rs);
        closePreparedStatement(prpstm);
      }
      return beans;
    }
    
    public boolean addNew(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "addNew()";
      boolean result = true;
      PreparedStatement prstm = null;      
      try
      {
          List params = new ArrayList();
          params=setParams(seed);     
          params.add(getMaxOrder(cnn)+1);
          result=execute(cnn,SQL_BROADCAST_INSERT,params)>0;
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
    
    public boolean update(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      FBroadcast bean = (FBroadcast)seed;
      boolean result = false;
      try{
          List params = setParams(seed);
          params.add(bean.getOrders()>0?bean.getOrders():1); 
          params.add(bean.getBroadcastId());
          result = execute(cnn,SQL_BROADCAST_UPDATE,params)>0;
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
//    public boolean orders(Connection cnn, int orders,int id,int index) throws  EException
//    {
//      final String LOCATION = this.toString() + UPDATE;
//      PreparedStatement prstm = null;
//   
//      boolean result = false;
//      try
//      {
//          FBroadcast bean = getInforBroadcastByOrders(cnn,orders,index);
//          List params = new ArrayList();
//          params.add(bean.getOrders());
//          params.add(id);
//          result = execute(cnn,SQL_BROADCAST_UPDATE_ORDER,params)>0;
//          
//          params =  new ArrayList();
//          params.add(orders);
//          params.add(bean.getBroadcastId());
//          result = execute(cnn,SQL_BROADCAST_UPDATE_ORDER,params)>0;
//      }
//      catch(Exception sqle)
//      {
//        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
//      }
//      finally
//      {
//        closePreparedStatement(prstm);
//      }
//      return result;
//    }
//   
    public boolean updateOrder(Connection cnn, int orders,int id,int index) throws  EException
    {
      final String LOCATION = this.toString() + UPDATE;
      PreparedStatement prstm = null;
      boolean result=false;
      try
      {//index==1 len   index==-1 xuong
          List params = new ArrayList();
          FBroadcast bean =new FBroadcast();
          if(index==1){
              bean=getLen(cnn,orders);
          }else{
              bean=getXuong(cnn,orders);
          }
          params.add(orders);
          params.add(bean.getBroadcastId());
          result = execute(cnn,SQL_BROADCAST_UPDATE_ORDER,params)>0;
          
          params.clear();
          params.add(bean.getOrders());
          params.add(id);
          result = execute(cnn,SQL_BROADCAST_UPDATE_ORDER,params)>0;
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
      boolean result = false;
      FBroadcast bean = (FBroadcast)seed;
      try{            
            result = delete(cnn, TABLE_BROADCAST, BROADCAST_ID + EQUAL + bean.getBroadcastId())>0;
      }
      catch(EException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
      }
      return result ;
    }  
    
    public boolean broadcastCheckTitle(Connection cnn, FSeed seed) throws  EException
    {
      final String LOCATION = this.toString() + "~~>broadcastCheckTitle()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FBroadcast bean = (FBroadcast)seed;
      try
      {       
        prstm = cnn.prepareStatement(SQL_BROADCAST_CHECK_TITLE);
        prstm.setString(PARAM_01,bean.getTitle());
        prstm.setInt(PARAM_02,bean.getBroadcastId());
        rs = prstm.executeQuery();
        result = (rs != null) && rs.next();
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);        
      }
      return result;
    }

  
    
    public FBroadcast getBroadcastById(Connection cnn, FBroadcast bean) throws EException 
    {
      final String LOCATION = this.toString() + "getBroadcastById()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBroadcast beantemp = null;
      try
      {
        prpstm = cnn.prepareStatement(SQL_BROADCAST_SELECT_BROADCAST_BY_ID);
        prpstm.setInt(PARAM_01,bean.getBroadcastId());
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {         
          beantemp = getInformation(rs);
          beantemp.setFullName(rs.getString(USERS_FULLNAME));
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
    
    public FBroadcast getLen(Connection cnn,int order) throws EException 
    {
      final String LOCATION = this.toString() + "getTren()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBroadcast beantemp = null;
      String SQL_SELECT =SQL_BROADCAST_SELECT_ALL_SHOW_2 + WHERE + BROADCAST_ORDERS + ">" + QUESTION + ORDER_BY + BROADCAST_ORDERS + ASC;
      try
      {
      prpstm = cnn.prepareStatement(SQL_SELECT);
        prpstm.setInt(PARAM_01,order);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {         
          beantemp = getInformation(rs);
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
    
    public FBroadcast getXuong(Connection cnn,int order) throws EException 
    {
      final String LOCATION = this.toString() + "getByOrder()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBroadcast beantemp = null;
      String SQL_SELECT =SQL_BROADCAST_SELECT_ALL_SHOW_2 + WHERE + BROADCAST_ORDERS + "<" + QUESTION + ORDER_BY + BROADCAST_ORDERS + DESC;
      try
      {
        prpstm = cnn.prepareStatement(SQL_SELECT);
        prpstm.setInt(PARAM_01,order);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {         
          beantemp = getInformation(rs);
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
    
    public int getMaxOrder(Connection cnn) throws EException 
    {
      final String LOCATION = this.toString() + "getMaxOrder()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      int order=0;
      try
      {
        prpstm = cnn.prepareStatement(SELECT + MAX + OPEN + BROADCAST_ORDERS + CLOSE + FROM + TABLE_BROADCAST);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {         
          order=rs.getInt(PARAM_01);
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
      return order;    
    }    
    public FBroadcast getInforBroadcastByOrders(Connection cnn,int orders,int index) throws EException 
    {
      final String LOCATION = this.toString() + "getInforBroadcastByOrders()";
      PreparedStatement prpstm = null;
      ResultSet rs = null;
      FBroadcast beantemp = null;
      try
      {
        String SQL = index>0?SQL_BROADCAST_ORDERS_BROADCAST.replaceAll("#",SQL_BROADCAST_ORDERS_MIN_BROADCAST):SQL_BROADCAST_ORDERS_BROADCAST.replaceAll("#",SQL_BROADCAST_ORDERS_MAX_BROADCAST);
        prpstm = cnn.prepareStatement(SQL);
        prpstm.setInt(PARAM_01,orders);
        rs = prpstm.executeQuery();
        if((rs != null) && (rs.next()))
        {         
          beantemp = getInformation(rs);
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
   
    
    
    public FBroadcast getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FBroadcast bean = new FBroadcast();
         try {
             bean.setBroadcastId(rs.getInt(BROADCAST_ID));
             bean.setTitle(rs.getString(BROADCAST_TITLE));
             bean.setContent(rs.getString(BROADCAST_CONTENT));              
             bean.setUser_id(rs.getInt(BROADCAST_USER_ID));
             bean.setCreatetime(bean.dateToString(new Date (rs.getTimestamp(BROADCAST_CREATETIME).getTime()),AppConfigs.APP_DATE_TIME));            
             bean.setSpecial(rs.getInt(BROADCAST_SPECIAL)); 
             bean.setOrders(rs.getInt(BROADCAST_ORDERS)); 
             bean.setFulltext(rs.getString(BROADCAST_FULLTEXT));              
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
        FBroadcast bean = (FBroadcast)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getTitle());                                                                                                                 
             params.add(bean.getContent());   
             params.add(bean.getUser_id());            
             params.add(new java.sql.Timestamp(System.currentTimeMillis()));   
             params.add(bean.getSpecial());
             params.add(bean.getFulltext());
            
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    
    
 
}
