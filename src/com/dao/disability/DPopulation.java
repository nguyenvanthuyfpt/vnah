package com.dao.disability;


import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FPopulation;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DPopulation  extends DSqlDisability{

    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        PreparedStatement pstmt = null;
        FPopulation bean = (FPopulation)seed;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_POPULATION_INFORMATION);
            pstmt.setInt(PARAM_01, bean.getPeriod());
            pstmt.setInt(PARAM_02, bean.getYearOfPeriod());
            pstmt.setInt(PARAM_03, bean.getId_tinh());
            pstmt.setInt(PARAM_04, bean.getId());
            rs = pstmt.executeQuery();
            result = rs != null && rs.next();
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }

    public FBeans getAllByTinh(Connection cnn, int idTinh) throws EException {
        final String LOCATION = this.toString() + "getAllByTinh()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(idTinh);
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_POPULATION_BY_ID_TINH, params);
            rs = prstm.executeQuery();
            FPopulation bean = null;
            while (rs != null && rs.next()) {
                bean = new FPopulation();
                bean = getInformation(rs, 0);
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
    
    public FPopulation getById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FPopulation bean = new FPopulation();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(id);
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_POPULATION_BY_ID, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FPopulation();
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
    
    public FPopulation getByPeriod(Connection cnn, int period, int year, int idProvine) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FPopulation bean = new FPopulation();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            List params = new ArrayList();
            params.add(period);
            params.add(year);
            params.add(idProvine);
            prstm = prepareStatement(cnn, SQL_SELECT_ALL_POPULATION_BY_PERIOD, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = new FPopulation();
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

    public FPopulation getSumByPeriod(Connection cnn, int period, int year, int idProvine) throws EException {
        final String LOCATION = this.toString() + "getSumByPeriod()";
        FPopulation bean = new FPopulation();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            DTinh dao = new DTinh();
            String members = dao.getMembers(cnn, idProvine, "");
            String SQL =
                "SELECT sum(male_less_than_18) as male_less_than_18, sum(male_more_than_18) as male_more_than_18,sum(famale_less_than_18) as famale_less_than_18, sum(famale_more_than_18) as famale_more_than_18, sum(number_1) as number_1, " +
                " sum(number_2) as number_2, sum(number_3) as number_3, sum(number_4) as number_4, sum(number_5) as number_5, sum(number_6) as number_6, sum(number_7) as number_7 " +
                " FROM dr_population where period=? and yearofperiod=? and id_tinh in (" + members + ")";
            List params = new ArrayList();
            params.add(period);
            params.add(year);
            //params.add(members);
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean.setMaleLessThan18(rs.getInt(PPLT_MALE_LESS_THAN_18));
                bean.setMaleMoreThan18(rs.getInt(PPLT_MALE_MORE_THAN_18));
                bean.setFamaleLessThan18(rs.getInt(PPLT_FAMALE_LESS_THAN_18));
                bean.setFamaleMoreThan18(rs.getInt(PPLT_FAMALE_MORE_THAN_18));
                bean.setNumber1(rs.getInt(PPLT_NUMBER_1));
                bean.setNumber2(rs.getInt(PPLT_NUMBER_2));
                bean.setNumber3(rs.getInt(PPLT_NUMBER_3));
                bean.setNumber4(rs.getInt(PPLT_NUMBER_4));
                bean.setNumber5(rs.getInt(PPLT_NUMBER_5));
                bean.setNumber6(rs.getInt(PPLT_NUMBER_6));
                bean.setNumber7(rs.getInt(PPLT_NUMBER_7));
            }
            bean.setParamValue(getCount(cnn,"SELECT count(*) FROM dr_population where period="+period+" and yearofperiod="+year+" and info_active like '%#0#%' and id_tinh in (" + members+")"),0);   ;
            bean.setParamValue(getCount(cnn,"SELECT count(*) FROM dr_population where period="+period+" and yearofperiod="+year+" and info_active like '%#1#%' and id_tinh in (" + members+")"),1);   ;
            bean.setParamValue(getCount(cnn,"SELECT count(*) FROM dr_population where period="+period+" and yearofperiod="+year+" and info_active like '%#2#%' and id_tinh in (" + members+")"),2);   ;
            bean.setParamValue(getCount(cnn,"SELECT count(*) FROM dr_population where period="+period+" and yearofperiod="+year+" and info_active like '%#3#%' and id_tinh in (" + members+")"),3);   ;
            bean.setParamValue(getCount(cnn,"SELECT count(*) FROM dr_population where period="+period+" and yearofperiod="+year+" and info_active like '%#4#%' and id_tinh in (" + members+")"),4);   ;
            bean.setParamValue(getCount(cnn,"SELECT count(*) FROM dr_population where period="+period+" and yearofperiod="+year+" and info_active like '%#5#%' and id_tinh in (" + members+")"),5);   ;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return bean;
    }  
    
    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            prstm = prepareStatement(cnn, SQL_INSERT_POPULATION, params);
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
            FPopulation bean = (FPopulation)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_POPULATION, params);
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
        boolean result = delete(cnn, TABLE_POPULATION, PPLT_ID + EQUAL + id) > 0;
        return result;
    }    
    

    public FPopulation getInformation(ResultSet rs, int check) throws EException {
        final String LOCATION = "->getInformation()";
        FPopulation bean = new FPopulation();
        try {
            bean.setId(rs.getInt(PPLT_ID));
            bean.setId_tinh(rs.getInt(PPLT_ID_TINH));
            if (rs.getDate(PPLT_DATEEDIT) != null && !rs.getDate(PPLT_DATEEDIT).equals("")) {
                bean.setDateEdit(bean.dateToString(rs.getDate(PPLT_DATEEDIT)));
            }
            bean.setMaleLessThan18(rs.getInt(PPLT_MALE_LESS_THAN_18));
            bean.setMaleMoreThan18(rs.getInt(PPLT_MALE_MORE_THAN_18));
            bean.setFamaleLessThan18(rs.getInt(PPLT_FAMALE_LESS_THAN_18));
            bean.setFamaleMoreThan18(rs.getInt(PPLT_FAMALE_MORE_THAN_18));
            bean.setInfoActive(rs.getString(PPLT_INFO_ACTIVE));
            bean.setNumber1(rs.getInt(PPLT_NUMBER_1));
            bean.setNumber2(rs.getInt(PPLT_NUMBER_2));
            bean.setNumber3(rs.getInt(PPLT_NUMBER_3));
            bean.setNumber4(rs.getInt(PPLT_NUMBER_4));
            bean.setNumber5(rs.getInt(PPLT_NUMBER_5));
            bean.setNumber6(rs.getInt(PPLT_NUMBER_6));
            bean.setNumber7(rs.getInt(PPLT_NUMBER_7));
            bean.setNumber8(rs.getInt(PPLT_NUMBER_8));
            bean.setNumber9(rs.getInt(PPLT_NUMBER_9));
            bean.setNumber10(rs.getInt(PPLT_NUMBER_10));
            bean.setNumber11(rs.getInt(PPLT_NUMBER_11));
            bean.setNumber12(rs.getInt(PPLT_NUMBER_12));
            bean.setNumber13(rs.getInt(PPLT_NUMBER_13));
            bean.setNumber14(rs.getInt(PPLT_NUMBER_14));
            bean.setNumber15(rs.getInt(PPLT_NUMBER_15));
            bean.setPeriod(rs.getInt(PPLT_PERIOD));
            bean.setYearOfPeriod(rs.getInt(PPLT_YEAROFPERIOD));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException
    {
        final String LOCATION = "->setParams()";
        FPopulation bean = (FPopulation)seed;
        List params = new ArrayList();
         try {
             params.add(bean.getId_tinh());
             params.add(bean.getCurrentSqlDate());
             params.add(bean.getMaleLessThan18());
             params.add(bean.getMaleMoreThan18());
             params.add(bean.getFamaleLessThan18());
             params.add(bean.getFamaleMoreThan18());
             params.add(bean.getInfoActive());
             params.add(bean.getNumber1());
             params.add(bean.getNumber2());
             params.add(bean.getNumber3());
             params.add(bean.getNumber4());
             params.add(bean.getNumber5());
             params.add(bean.getNumber6());
             params.add(bean.getNumber7());
             params.add(bean.getNumber8());
             params.add(bean.getNumber9());
             params.add(bean.getNumber10());
             params.add(bean.getNumber11());
             params.add(bean.getNumber12());
             params.add(bean.getNumber13());
             params.add(bean.getNumber14());
             params.add(bean.getNumber15());
             params.add(bean.getPeriod());
             params.add(bean.getYearOfPeriod());
         }
         catch (Exception exp) {            
             if(AppConfigs.APP_DEBUG) throw new EException(LOCATION,exp);
         }
         finally {
         }
         return params;
    }
    public int getCount(Connection cnn,String SQL) throws EException
    {
      final String LOCATION = this.toString() + "getAll()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        int result = 0;
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                result = rs.getInt(PARAM_01);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return result;
    }
}
