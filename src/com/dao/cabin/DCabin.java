package com.dao.cabin;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.cabin.FCabin;

import com.inf.IRoles;
import com.inf.cabin.IKeyCabin;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;


public class DCabin  extends DSqlCabin
{
    public FBeans getAllByType(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
      FBeans beans = new FBeans();
      FCabin bean=(FCabin)seed;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String SQL_SELECT=SQL_SELECT_ALL_TYPE;
      String SQL_WHERE=WHERE + TRUE;
      try {
        List params =new ArrayList();
        if(bean.getType()==IKeyCabin.CABIN_PRIVATE){//CA NHAN
            SQL_WHERE+= AND + CABIN_USERS_ID + EQUAL + QUESTION;// + AND + CABIN_TYPE + EQUAL + QUESTION ;
            params.add(bean.getMeId());
            //params.add(IKeyCabin.CABIN_PRIVATE);
            
        }else if(bean.getType()==IKeyCabin.CABIN_SHARE){//CHIA SE
            if(bean.getUserId()>0){
                SQL_WHERE+= AND + TABLE_CABIN + STOP + CABIN_USERS_ID + EQUAL + QUESTION;
                params.add(bean.getUserId());
            }
            SQL_WHERE+= AND + CABIN_ID + IN + OPEN + SELECT + CABIN_SHARE_CABIN_ID + FROM + TABLE_CABIN_SHARE + WHERE + CABIN_SHARE_USER_ID + EQUAL + QUESTION + CLOSE;
            params.add(bean.getMeId());
            
            ////.println(SQL_SELECT + SQL_WHERE);
            
        }else if(bean.getType()==IKeyCabin.CABIN_PUBLIC){    //CO QUAN
            SQL_WHERE+= AND + CABIN_TYPE + EQUAL + QUESTION;
            params.add(IKeyCabin.CABIN_PUBLIC);
            
        }else{//PHONG
            SQL_WHERE+= AND + CABIN_TYPE + EQUAL + QUESTION + AND + CABIN_USERS_ID + IN + OPEN + SELECT + USERS_USER_ID + FROM + TABLE_USERS + WHERE + USERS_DEPARTMENT_ID + EQUAL + QUESTION + CLOSE;
            params.add(IKeyCabin.CABIN_DEPARTMENT);
            params.add(bean.getDepartmentID());
        }
        if(bean.getCabinType_id()>0){
            SQL_WHERE+= AND + CABIN_CABINTYPE_ID + EQUAL + QUESTION;
            params.add(bean.getCabinType_id());
        }
        if(bean.getContentSearch()!=null && !bean.getContentSearch().equals("")){
            SQL_WHERE+= AND +" (UPPER("+ TABLE_CABIN + STOP + CABIN_REALNAME + CLOSE + LIKE + " UPPER("+QUESTION + CLOSE+ OR +" UPPER("+ TABLE_CABIN + STOP + CABIN_NAME + CLOSE + LIKE + " UPPER("+QUESTION + CLOSE + CLOSE ;
//            SQL_WHERE+= AND + OPEN + TABLE_CABIN + STOP + CABIN_NAME + LIKE + QUESTION + OR + TABLE_CABIN + STOP + CABIN_REALNAME + LIKE + QUESTION + CLOSE ;
//            params.add(PER_CENT + bean.getContentSearch() + PER_CENT );
            params.add(PER_CENT + bean.getContentSearch() + PER_CENT );
            params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
        }
        SQL_SELECT+=SQL_WHERE;
        prstm = prepareStatement(cnn,SQL_SELECT + ORDER_BY + CABIN_TIMECREATE,params);
//        //.println(SQL_SELECT + ORDER_BY + CABIN_TIMECREATE);
        rs = prstm.executeQuery();
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL_SELECT,params));
        beans.setPageIndex(0);
        if(beans.getFirstRecord()<=1){
          rs.beforeFirst();
        }else{
          rs.absolute(beans.getFirstRecord()-1);
        }
        int i=0;
       while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW)){
            i++;
            bean=getInformation(rs);
            bean.setFullName(rs.getString(USERS_FULLNAME));
            bean.setEmpsRev(getEmpsRecv(cnn,bean.getId()));
            bean.setDepartmentID(rs.getInt(CABINTYPE_DEPARTMENT_ID));
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
    public FBeans getAllCabinUnionCabinType(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "getAllCabinUnionCabinType()";
      FBeans beans = new FBeans();
      FCabin bean=(FCabin)seed;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      String SQL_SELECT=SQL_SELECT_ALL_CABIN_UNION_CABINTYPE;
        String SQL_SELECT_CABIN=SQL_SELECT_ALL_CABIN_UNION_CABIN;
      try {
        List params =new ArrayList();
          if(bean.getType()==1){
              SQL_SELECT+=AND + CABINTYPE_USER_ID + EQUAL + bean.getMeId();
              SQL_SELECT_CABIN+=AND + CABIN_USERS_ID + EQUAL + bean.getMeId();
          }else if(bean.getType()==3){
              SQL_SELECT+=AND + CABINTYPE_DEPARTMENT_ID + EQUAL + bean.getDepartmentID();
//              SQL_SELECT_CABIN+=AND + CABIN_USERS_ID + " IN " + SELECT + bean.getDepartmentID();
          
          }
              SQL_SELECT+=AND + CABINTYPE_PARENT_ID + EQUAL + bean.getCabinType_id();
              SQL_SELECT_CABIN+=AND + CABIN_CABINTYPE_ID + EQUAL + bean.getCabinType_id();
          params.add(bean.getType());
          params.add(bean.getType());

          String SQL_ORDERBY="";
          if(bean.getOrder_by()==0){
              SQL_ORDERBY=ORDER_BY+CABIN_NAME;
          }else  if(bean.getOrder_by()==1){
              SQL_ORDERBY=ORDER_BY+CABIN_NAME + DESC;
          }else  if(bean.getOrder_by()==2){
              SQL_ORDERBY=ORDER_BY+CABIN_CAPACITY;
          }else  if(bean.getOrder_by()==3){
              SQL_ORDERBY=ORDER_BY+CABIN_CAPACITY+DESC;
          }else  if(bean.getOrder_by()==4){
              SQL_ORDERBY=ORDER_BY+CABIN_TIMECREATE;
          }else  if(bean.getOrder_by()==5){
              SQL_ORDERBY=ORDER_BY+CABIN_TIMECREATE+DESC;
          }
          
          SQL_SELECT+=UNION+SQL_SELECT_CABIN;
          if(bean.getContentSearch()!=null && !bean.getContentSearch().equals("")){
              SQL_SELECT+= AND +" ( UPPER("+ TABLE_CABIN + STOP + CABIN_REALNAME + CLOSE + LIKE + " UPPER("+QUESTION + CLOSE+OR +" UPPER("+ TABLE_CABIN + STOP + CABIN_NAME + CLOSE + LIKE + " UPPER("+QUESTION + CLOSE  + CLOSE ;
              params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
              params.add(PER_CENT + bean.getContentSearch() + PER_CENT);
          }
          SQL_SELECT+=SQL_ORDERBY;
//          //.println(bean.getContentLucene());
//          //.println(SQL_SELECT);
        prstm = prepareStatement(cnn,SQL_SELECT,params);
//        //.println(SQL_SELECT);
        rs = prstm.executeQuery();
        beans = new FBeans();
        beans.setTotalRows(count(cnn,SQL_SELECT,params));
        beans.setPageIndex(0);
        if(beans.getFirstRecord()<=1){
          rs.beforeFirst();
        }else{
          rs.absolute(beans.getFirstRecord()-1);
        }
        int i=0;
       while((rs != null) && (rs.next()) && (i<AppConfigs.APP_ROWS_VIEW)){
            i++;
            bean = getInformation(rs);
            bean.setFullName(rs.getString(USERS_FULLNAME));
            bean.setDepartmentID(rs.getInt(CABINTYPE_DEPARTMENT_ID));
            bean.setTypeFile(rs.getInt("typefile"));
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
    public FCabin getById(Connection cnn,int id) throws EException
    {
      final String LOCATION = this.toString() + "getById()";
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FCabin bean =new FCabin();
      try
      {
        prstm = cnn.prepareStatement(SQL_SELECT_BY_ID);
        prstm.setInt(PARAM_01,id);
//        //.println(SQL_SELECT_BY_ID);
        rs = prstm.executeQuery();
          ResultSetMetaData rsmd =  rs.getMetaData();
          for(int i=0;i<rsmd.getColumnCount();i++){
//              //.println(rsmd.getColumnName(i+1) + ":" + rsmd.getColumnTypeName(i+1));
          }

        if((rs != null) && (rs.next()))
        {
            bean = getInformation(rs);
            if(bean.getType()==IKeyCabin.CABIN_DEPARTMENT){
               // bean.setDepsRev(getDepsRecv(cnn,bean.getId()));
            }else{
                bean.setEmpsRev(getEmpsRecv(cnn,bean.getId()));
            }
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
    
    public boolean insert(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      PreparedStatement prstm = null;
      FCabin bean =(FCabin)seed;
      try
      {
          List params = setParams(seed);
          prstm = prepareStatement(cnn,SQL_INSERT_CABIN,params);
            result = prstm.executeUpdate()>0;
            if(!seed.me.isRole(IRoles.RADMINISTRATOR)){
            if(result && bean.getUserIdS()!=null){
                addBathCabinUsers(cnn,seed);
            }
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
    
    public boolean tranferData(Connection cnn, FCabin bean) throws EException
    {
      final String LOCATION = this.toString() + INSERT;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
          List params = setParamsTranfer(bean);
          prstm = prepareStatement(cnn,SQL_INSERT_CABIN,params);
          ////.println(SQL_INSERT_CABIN);
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
    
    
    public boolean deleteCabinUser(Connection cnn,int id) throws EException{
                return delete(cnn, TABLE_CABIN_SHARE, CABIN_SHARE_CABIN_ID + EQUAL + id )>0;
        }    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
      final String LOCATION = this.toString() + UPDATE;
      boolean result = false;
      PreparedStatement prstm = null;
      try
      {
        FCabin bean = (FCabin)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_UPDATE_CABIN,params);
        result = prstm.executeUpdate()>0;
//          if(!seed.me.isRole(IRoles.RADMINISTRATOR)){
          if(result && bean.getUserIdS()!=null){
              deleteCabinUser(cnn,bean.getId());
              addBathCabinUsers(cnn,seed);
          }
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

    public boolean delete(Connection cnn,int id) throws EException{
            return delete(cnn, TABLE_CABIN,CABIN_ID + EQUAL + id )>0;
    }    
    
    public FCabin getInformation(ResultSet rs) throws EException
    {
        final String LOCATION = "->getInformation()";
        FCabin bean = new FCabin();
         try {
             bean.setId(rs.getInt(CABIN_ID));
             bean.setUserId(rs.getInt(CABIN_USERS_ID));
             bean.setName(rs.getString(CABIN_NAME));
             bean.setDescription(rs.getString(CABIN_DESCRIPTION));
              if(rs.getTimestamp(CABIN_TIMECREATE)==null){
                   bean.setTimeCreate(null);
               }else{
               bean.setTimeCreate(bean.dateToString(new Date (rs.getTimestamp(CABIN_TIMECREATE).getTime()),AppConfigs.APP_DATE_TIME));            
               }
             bean.setCabinType_id(rs.getInt(CABIN_CABINTYPE_ID));
             bean.setFileStore(rs.getString(CABIN_FILESTORE));
             bean.setRealName(rs.getString(CABIN_REALNAME));
             bean.setCapacity(rs.getString(CABIN_CAPACITY));
             bean.setType(rs.getInt(CABIN_TYPE));
             
         }catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }
 
    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FCabin bean = (FCabin)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getMeId());
             params.add(bean.getName());
             params.add(bean.getDescription());                           
             params.add(new Timestamp(System.currentTimeMillis()));                  
             params.add(bean.getCabinType_id());                           
             params.add(bean.getFileStore());   
             params.add(bean.getRealName());
             params.add(bean.getCapacity());
             params.add(bean.getType());
         }catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public List setParamsTranfer(FCabin bean) throws EException
    {
        final String LOCATION = "->setParams()";
        List params = new ArrayList();
         try {
             
             params.add(bean.getMeId());
             params.add(bean.getName());
             params.add(bean.getDescription());    
             params.add(new Timestamp(System.currentTimeMillis()));                  
//             params.add(bean.getCurrentSqlDate());                           
             params.add(bean.getCabinType_id());                           
             params.add(bean.getFileStore());   
             params.add(bean.getRealName());
             params.add(bean.getCapacity());
             params.add(bean.getType());
         }catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public boolean updateShare(Connection cnn,int id,long meId,int cabinTypeId) throws SQLException, EException
       {
         final String LOCATION = this.toString() + UPDATE;
         boolean result = false;
         PreparedStatement prstm = null;
         try{
               List params =new ArrayList();
               params.add(cabinTypeId);
               params.add(id);
               params.add(meId);
               prstm = prepareStatement(cnn,UPDATE + TABLE_CABIN_SHARE + SET + CABIN_SHARE_USER_ID + EQUAL + QUESTION + WHERE + CABIN_SHARE_CABIN_ID+ EQUAL + QUESTION + AND + CABIN_SHARE_USER_ID + EQUAL + QUESTION,params);
               result = prstm.executeUpdate()>0;
         }catch(SQLException sqle){
           if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,sqle);
         }
         finally
         {
           closePreparedStatement(prstm);
         }
         return result;
       }

    public FBeans getEmpsRecv(Connection cnn,int id) throws EException{
      final String LOCATION = this.toString() + "getEmpsRecv()";
      PreparedStatement prstm = null;         
      ResultSet rs = null;
      FCabin beantemp=null;
      FBeans beans=new FBeans();
      try{
              List params = new ArrayList();    
              params.add(id);
              prstm = prepareStatement(cnn,SQL_SELECT_USERS_RECV,params); 
              rs = prstm.executeQuery();
                while((rs != null) && (rs.next())){
                    beantemp =new FCabin();
                    beantemp.setToPertion(rs.getInt(CABIN_SHARE_USER_ID));
                    beantemp.setUserFullName(rs.getString(USERS_FULLNAME));
                    beantemp.setEmpsRev(new FBeans());
                    beantemp.getEmpsRev().add(beantemp);
                    beans.add(beantemp);
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
    public boolean addBathCabinUsers(Connection cnn,FSeed seed) throws  EException
    {
        final String LOCATION = this.toString() + INSERT;
        boolean flag = false;
        boolean result = false;
        PreparedStatement prstm = null;
        FCabin bean=(FCabin)seed;
        if(bean.getId()==0){
        bean.setId(getTopId(cnn,bean.me.getId()));
        }
        try
        {
            prstm = cnn.prepareStatement(SQL_INSERT_CABIN_USERS);
                 for (int i =0;i<bean.getUserIdS().length;i++){
                         prstm.setInt(PARAM_01,bean.getId());
                         prstm.setInt(PARAM_02,bean.getUserIdS()[i]);
                         prstm.addBatch(); 
                         flag = true;
                 }
                  if (flag){
                      result=prstm.executeBatch().length>0;
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
      
 
      
    public int getTopId(Connection cnn,long meId) throws EException 
        {
          final String LOCATION = this.toString() + "getTopId()";
          PreparedStatement prpstm = null;
          ResultSet rs = null;
          int id=0;
          try
          {
            prpstm = cnn.prepareStatement(SQL_SELECT_CABIN_BY_TOP);
            prpstm.setLong(PARAM_01,meId);
            rs = prpstm.executeQuery();
            if((rs != null) && (rs.next()))
            {
                    id=rs.getInt(PARAM_01);
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
          return id;    
        }
        


    public FBeans getUserShare(Connection cnn,int meId) throws EException{
      final String LOCATION = this.toString() + "getUserShare()";
      PreparedStatement prstm = null;         
      ResultSet rs = null;
      FCabin beantemp=null;
      FBeans beans=new FBeans();
      try{
              List params = new ArrayList();    
              params.add(meId);
              prstm = prepareStatement(cnn,SQL_SELECT_USER_SHARE_FOR_ME,params); 
              rs = prstm.executeQuery();
              int userTemp=0;
              while((rs != null) && (rs.next())){
                    beantemp =new FCabin();
                    beantemp.setUserId(rs.getInt(PARAM_01));
                    beantemp.setFullName(rs.getString(PARAM_02));
                    if(userTemp!=rs.getInt(PARAM_01)){
                        beans.add(beantemp);
                        userTemp=rs.getInt(PARAM_01);
                    }
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
    
    public boolean checkFileStoreCabin(Connection cnn, FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "~~>checkCodeCabinType()";
      boolean result = true;
      PreparedStatement prstm = null;
      ResultSet rs = null;
      FCabin bean = (FCabin)seed;
      try
      {     
        prstm = cnn.prepareStatement(SQL_CABIN_CHECK_FILESTORE);
        prstm.setString(PARAM_01,bean.getFileStore());
        rs = prstm.executeQuery();
        result = (rs != null) && rs.next();
      }
      catch(SQLException ex)
      {
        if(AppConfigs.APP_DEBUG)throw new EException(LOCATION,ex);
        result = true;
      }
      return result;        
    }  

}
