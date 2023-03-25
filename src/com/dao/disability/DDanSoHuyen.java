package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanSoHuyen;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDanSoHuyen  extends DSqlDisability{
  
    public FDanSoHuyen getByPeriod(Connection cnn,int period,int year,int idProvine) throws EException
    {
        final String LOCATION = this.toString() + "getAll()";
        FDanSoHuyen bean = new FDanSoHuyen();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(period);
            params.add(year);
            params.add(idProvine);
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_DANSOHUYEN_BY_PERIOD, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FDanSoHuyen();
                bean = getInformation(rs, 0);

            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
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
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_DANSOHUYEN_BY_ID_TINH, params);
            rs = prstm.executeQuery();
            FDanSoHuyen bean = null;
            while (rs != null && rs.next()) {
                bean = new FDanSoHuyen();
                bean = getInformation(rs, 0);
                bean.setTinhName(rs.getString(TINH_NAME));
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    public boolean checkExitIdTinh(Connection cnn,FSeed seed) throws EException
    {
        final String LOCATION = this.toString() + "checkExitIdTinh()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDanSoHuyen bean = (FDanSoHuyen)seed;
        boolean result = false;
        try {
            List params = new ArrayList();
            params.add(bean.getKyBao());
            params.add(bean.getNam());
            params.add(bean.getId_tinh());
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_CHECK_ID_TINH_IS_EXITS, params);
            rs = prstm.executeQuery();
            result = rs != null && rs.next();

        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    
    public FDanSoHuyen getById(Connection cnn,int id) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
        FDanSoHuyen bean = new FDanSoHuyen();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(id);
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_DANSOHUYEN_BY_ID, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FDanSoHuyen();
                bean = getInformation(rs, id);

            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
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
        FDanSoHuyen bean = (FDanSoHuyen)seed;
        try {
            List params = setParams(seed);
            prstm = prepareStatement(cnn, SQL_INSERT_DANSOHUYEN, params);
            result = prstm.executeUpdate() > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public boolean update(Connection cnn, FSeed seed) throws SQLException, EException
    {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FDanSoHuyen bean = (FDanSoHuyen)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_DANSOHUYEN, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public boolean delete(Connection cnn,int id) throws EException{
        boolean result =delete(cnn, TABLE_DANSOHUYEN,PPLT_ID + EQUAL + id )>0;
        return result;
    }    
    

    public FDanSoHuyen getInformation(ResultSet rs,int check) throws EException
    {
        final String LOCATION = "->getInformation()";
        FDanSoHuyen bean = new FDanSoHuyen();
         try {
                bean.setId(rs.getInt(DANSOHUYEN_ID));
                bean.setId_tinh(rs.getInt(DANSOHUYEN_ID_PROVINCE));
                bean.setKyBao(rs.getInt(DANSOHUYEN_PERIOD));
                bean.setNam(rs.getInt(DANSOHUYEN_YEAROFPERIOD));
                bean.setTongXa(rs.getInt(DANSOHUYEN_NOOFCOMMUNE));
                bean.setXcct(rs.getInt(DANSOHUYEN_NOOFCOMMUNEVNAH));
                bean.setDateUpdate(bean.dateToString(rs.getDate(DANSOHUYEN_LASTUPDATE)));
                bean.setParamValue1(rs.getInt(DANSOHUYEN_PARAMVALUE_1));
                bean.setParamValue2(rs.getInt(DANSOHUYEN_PARAMVALUE_2));
                bean.setParamValue3(rs.getInt(DANSOHUYEN_PARAMVALUE_3));
                bean.setParamValue4(rs.getInt(DANSOHUYEN_PARAMVALUE_4));
                bean.setParamValue5(rs.getInt(DANSOHUYEN_PARAMVALUE_5));
                bean.setParamValue6(rs.getInt(DANSOHUYEN_PARAMVALUE_6));
                bean.setParamValue7(rs.getInt(DANSOHUYEN_PARAMVALUE_7));
                bean.setParamValue8(rs.getInt(DANSOHUYEN_PARAMVALUE_8));
                bean.setParamValue9(rs.getInt(DANSOHUYEN_PARAMVALUE_9));
                bean.setParamValue10(rs.getInt(DANSOHUYEN_PARAMVALUE_10));
                bean.setParamValue11(rs.getInt(DANSOHUYEN_PARAMVALUE_11));
                bean.setParamValue12(rs.getInt(DANSOHUYEN_PARAMVALUE_12));
                bean.setParamValue13(rs.getInt(DANSOHUYEN_PARAMVALUE_13));
                bean.setParamValue14(rs.getInt(DANSOHUYEN_PARAMVALUE_14));
                bean.setParamValue15(rs.getInt(DANSOHUYEN_PARAMVALUE_15));
                bean.setParamValue16(rs.getInt(DANSOHUYEN_PARAMVALUE_16));
                bean.setParamValue17(rs.getInt(DANSOHUYEN_PARAMVALUE_17));
                bean.setParamValue18(rs.getInt(DANSOHUYEN_PARAMVALUE_18));
                bean.setParamValue19(rs.getInt(DANSOHUYEN_PARAMVALUE_19));
                bean.setParamValue20(rs.getInt(DANSOHUYEN_PARAMVALUE_20));
                bean.setParamValue21(rs.getInt(DANSOHUYEN_PARAMVALUE_21));
                bean.setParamValue22(rs.getInt(DANSOHUYEN_PARAMVALUE_22));
                bean.setParamValue23(rs.getInt(DANSOHUYEN_PARAMVALUE_23));
                bean.setParamValue24(rs.getInt(DANSOHUYEN_PARAMVALUE_24));
                bean.setParamValue25(rs.getInt(DANSOHUYEN_PARAMVALUE_25));
                bean.setParamValue26(rs.getInt(DANSOHUYEN_PARAMVALUE_26));

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
        FDanSoHuyen bean = (FDanSoHuyen)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getId_tinh());
             params.add(bean.getKyBao());
             params.add(bean.getNam());
             params.add(bean.getTongXa());
             params.add(bean.getXcct());
             params.add(bean.getCurrentSqlDate());
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
             params.add(bean.getParamValue24());//moi them
             params.add(bean.getParamValue25());;//moi them 
              params.add(bean.getParamValue26());;//moi them 
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }


}
