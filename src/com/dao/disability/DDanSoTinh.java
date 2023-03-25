package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanSoTinh;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDanSoTinh  extends DSqlDisability{
  

    public FDanSoTinh getByPeriod(Connection cnn,int period,int year,int idProvine) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
      FDanSoTinh bean = new FDanSoTinh();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try {
        List params = new ArrayList();
        params.add(period);
          params.add(year);
          params.add(idProvine);
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_DANSOTINH_BY_PERIOD,params);
        rs = prstm.executeQuery();
        if(rs != null && rs.next()){
                bean=new FDanSoTinh();
                bean=getInformation(rs,0);
                
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
  
    public FBeans getAllByTinh(Connection cnn,int idTinh) throws EException
    {
      final String LOCATION = this.toString() + "getAllByTinh()";
      FBeans beans = new FBeans();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try {
        List params = new ArrayList();
        params.add(idTinh);
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_DANSOTINH_BY_ID_TINH,params);
//        //.println(SQL_SELECT_ALL_DANSOTINH_BY_ID_TINH);
        rs = prstm.executeQuery();
        FDanSoTinh bean=null;
        while(rs != null && rs.next()){
                bean=new FDanSoTinh();
                bean=getInformation(rs,0);
                bean.setTinhName(rs.getString(TINH_NAME));
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
    public boolean checkExitIdTinh(Connection cnn,FSeed seed) throws EException
    {
      final String LOCATION = this.toString() + "checkExitIdTinh()";
      PreparedStatement prstm = null;
        FDanSoTinh bean = (FDanSoTinh)seed;
      ResultSet rs = null;
      boolean result=false;
      try {
        List params = new ArrayList();
        params.add(bean.getKyBao());
          params.add(bean.getNam());
          params.add(bean.getId_tinh());
          params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_CHECK_ID_TINH_IS_EXITS_DANSOTINH,params);
        rs = prstm.executeQuery();
        result = rs!=null && rs.next();
        
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
    
    
    public FDanSoTinh getById(Connection cnn,int id) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
      FDanSoTinh bean = new FDanSoTinh();
      PreparedStatement prstm = null;
      ResultSet rs = null;
      try {
        List params = new ArrayList();
        params.add(id);
        prstm = prepareStatement(cnn,SQL_SELECT_ALL_DANSOTINH_BY_ID,params);
        rs = prstm.executeQuery();
        if(rs != null && rs.next()){
                bean=new FDanSoTinh();
                bean=getInformation(rs,id);
                
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
      try
      {
          List params = setParams(seed);
          prstm = prepareStatement(cnn,SQL_INSERT_DANSOTINH,params);
          result = prstm.executeUpdate()>0;
      }catch(Exception sqle){
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
        FDanSoTinh bean = (FDanSoTinh)seed;
        List params = setParams(seed);
        params.add(bean.getId());
        prstm = prepareStatement(cnn,SQL_UPDATE_DANSOTINH,params);
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
    
    public boolean delete(Connection cnn,int id) throws EException{
            boolean result =delete(cnn, TABLE_DANSOTINH,PPLT_ID + EQUAL + id )>0;
    return result;
    }    
    

    public FDanSoTinh getInformation(ResultSet rs,int check) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDanSoTinh bean = new FDanSoTinh();
         try {
                bean.setId(rs.getInt(DANSOTINH_ID));
                bean.setId_tinh(rs.getInt(DANSOTINH_ID_PROVINCE));
                bean.setKyBao(rs.getInt(DANSOTINH_PERIOD));
                bean.setNam(rs.getInt(DANSOTINH_YEAROFPERIOD));
                bean.setTongHuyen(rs.getInt(DANSOTINH_NOOFDISTRICT));
                bean.setHuyenCct(rs.getInt(DANSOTINH_NOOFDISTRICTCBR));
                bean.setTongXa(rs.getInt(DANSOTINH_NOOFCOMMUNE));
                bean.setSoXaCoCt(rs.getInt(DANSOTINH_NOOFCOMMUNECBR));
                bean.setNoTeacher(rs.getInt(DANSOTINH_NOTEACHER));
                bean.setDateUpdate(bean.dateToString(rs.getDate(DANSOTINH_LASTUPDATE)));
                bean.setParamValue1(rs.getInt(DANSOTINH_PARAMVALUE_1));
                bean.setParamValue2(rs.getInt(DANSOTINH_PARAMVALUE_2));
                bean.setParamValue3(rs.getInt(DANSOTINH_PARAMVALUE_3));
                bean.setParamValue4(rs.getInt(DANSOTINH_PARAMVALUE_4));
             bean.setParamValue5(rs.getInt(DANSOTINH_PARAMVALUE_5));
             bean.setParamValue6(rs.getInt(DANSOTINH_PARAMVALUE_6));
             bean.setParamValue7(rs.getInt(DANSOTINH_PARAMVALUE_7));
             bean.setParamValue8(rs.getInt(DANSOTINH_PARAMVALUE_8));
             bean.setParamValue9(rs.getInt(DANSOTINH_PARAMVALUE_9));
             bean.setParamValue10(rs.getInt(DANSOTINH_PARAMVALUE_10));
             bean.setParamValue11(rs.getInt(DANSOTINH_PARAMVALUE_11));
             bean.setParamValue12(rs.getInt(DANSOTINH_PARAMVALUE_12));
             bean.setParamValue13(rs.getInt(DANSOTINH_PARAMVALUE_13));
             bean.setParamValue14(rs.getInt(DANSOTINH_PARAMVALUE_14));
             bean.setParamValue15(rs.getInt(DANSOTINH_PARAMVALUE_15));
             bean.setParamValue16(rs.getInt(DANSOTINH_PARAMVALUE_16));
             bean.setParamValue17(rs.getInt(DANSOTINH_PARAMVALUE_17));
             bean.setParamValue18(rs.getInt(DANSOTINH_PARAMVALUE_18));
             bean.setParamValue19(rs.getInt(DANSOTINH_PARAMVALUE_19));
             bean.setParamValue20(rs.getInt(DANSOTINH_PARAMVALUE_20));
             bean.setParamValue21(rs.getInt(DANSOTINH_PARAMVALUE_21));
             bean.setParamValue22(rs.getInt(DANSOTINH_PARAMVALUE_22));
             bean.setParamValue23(rs.getInt(DANSOTINH_PARAMVALUE_23));
             bean.setParamValue24(rs.getInt(DANSOTINH_PARAMVALUE_24));
             bean.setParamValue25(rs.getInt(DANSOTINH_PARAMVALUE_25));
             bean.setParamValue26(rs.getInt(DANSOTINH_PARAMVALUE_26));
             bean.setParamValue27(rs.getInt(DANSOTINH_PARAMVALUE_27));
             bean.setParamValue28(rs.getInt(DANSOTINH_PARAMVALUE_28));
             
         } catch (SQLException sqle) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,sqle);
         }
         finally {
         }
         return bean;
    }

    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FDanSoTinh bean = (FDanSoTinh)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getId_tinh());
             params.add(bean.getKyBao());
             params.add(bean.getNam());
             params.add(bean.getCurrentSqlDate());
             params.add(bean.getTongHuyen());
             params.add(bean.getHuyenCct());
             params.add(bean.getTongXa());
             params.add(bean.getSoXaCoCt());
             params.add(bean.getNoTeacher());
             params.add(bean.getParamValue1());
             params.add(bean.getParamValue2());
             params.add(bean.getParamValue3());
             params.add(bean.getParamValue4());
             params.add(bean.getParamValue5());
             params.add(bean.getParamValue6());
             params.add(bean.getParamValue7());
             params.add(bean.getParamValue8());
             params.add(bean.getParamValue9());
             params.add(bean.getParamValue10());
             params.add(bean.getParamValue11());
             params.add(bean.getParamValue12());
             params.add(bean.getParamValue13());
             params.add(bean.getParamValue14());
             params.add(bean.getParamValue15());
             params.add(bean.getParamValue16());
             params.add(bean.getParamValue17());
             params.add(bean.getParamValue18());
             params.add(bean.getParamValue19());
             params.add(bean.getParamValue20());
             params.add(bean.getParamValue21());
             params.add(bean.getParamValue22());
             params.add(bean.getParamValue23());
             params.add(bean.getParamValue24());
             params.add(bean.getParamValue25());
             params.add(bean.getParamValue26());
             params.add(bean.getParamValue27());
             params.add(bean.getParamValue28());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }


}
