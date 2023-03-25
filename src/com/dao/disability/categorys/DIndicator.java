package com.dao.disability.categorys;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FIndicator;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DIndicator extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FIndicator bean = (FIndicator)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_INDICATOR_INFORMATION);
            pstmt.setString(PARAM_01, bean.getCode());
            pstmt.setInt(PARAM_02, bean.getId());
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

    public FIndicator getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select v.*, dm.name as parent_name from kpi_indicator v left join kpi_indicator dm on v.parent_id=dm.id where v.id=?";
        FIndicator bean = new FIndicator();
        bean = (FIndicator)seed;
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformation(rs);
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
    
    public FIndicator getRecordByParentId(Connection cnn, int parent_id) throws EException {
        final String LOCATION = this.toString() + "getRecordByParent()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select v.*, dm.name as parent_name from kpi_indicator v left join kpi_indicator dm on v.parent_id=dm.id where v.parent_id=?";
        FIndicator bean = new FIndicator();
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, parent_id);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformation(rs);
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
    
    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FIndicator bean    = (FIndicator)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select v.*, dm.name as parent_name from kpi_indicator v left join kpi_indicator dm on v.parent_id=dm.id";
        try {
            List params = new ArrayList();
    
            prstm = prepareStatement(cnn, SQL + ORDER_BY + KPI_INDICATOR_CODE, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
    
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));            
            int i = 0;  
            while (rs != null && rs.next()) {
                i ++ ;
                bean = new FIndicator();
                bean = getInformation(rs);
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

    public boolean haveChild(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FIndicator bean = (FIndicator)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_INDICATOR_HAVECHILD);
            pstmt.setInt(PARAM_01, bean.getId());
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

    public int insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        ResultSet rs = null;
        int retval = 0;
        try {
            List params = setParams(seed);
            PreparedStatement ps = prepareStatement(cnn, SQL_INSERT_INDICATOR, params);
            rs = ps.executeQuery();
            if (rs.next()) {
              retval = rs.getInt(PARAM_01);
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return retval;
    }

    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FIndicator bean = (FIndicator)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_INDICATOR, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }
    
    public String getIndIds(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        String result = "";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "";
        try {
            FIndicator bean = (FIndicator)seed;            
            SQL = "select array_to_string(array(select ind_id from kpi_ind_per where year="+bean.getYear()+" AND location_ids like '%"+String.valueOf(bean.getLocationId())+"%'),'#') as ind_ids";
            prstm = cnn.prepareStatement(SQL);            
            rs = prstm.executeQuery();
            if (rs.next()) {
                result = rs.getString(PARAM_01);
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
    
  

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FIndicator bean = (FIndicator)seed;
        return 0 < delete(cnn, TABLE_KPI_INDICATOR, KPI_INDICATOR_ID + EQUAL + bean.getId());
    }

    public FIndicator getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FIndicator indicator = new FIndicator();
        try {
            indicator.setId(rs.getInt(KPI_INDICATOR_ID));
            indicator.setParentID(rs.getInt(KPI_INDICATOR_PARENT_ID));
            indicator.setCreateDate(indicator.dateToString(rs.getDate(KPI_INDICATOR_CREATE_DATE)));
            indicator.setModifyDate(indicator.dateToString(rs.getDate(KPI_INDICATOR_MODIFY_DATE)));
            indicator.setCode(rs.getString(KPI_INDICATOR_CODE));
            indicator.setName(rs.getString(KPI_INDICATOR_NAME));
            indicator.setDescription(rs.getString(KPI_INDICATOR_DESCRIPTION));
            indicator.setBaseline(rs.getInt(KPI_INDICATOR_BASELINE));
            indicator.setTargetYear(rs.getInt(KPI_INDICATOR_TARGET_YEAR));
            indicator.setTargetJustification(rs.getString(KPI_INDICATOR_TARGET_JUSTIFICATION));
            
            indicator.setM1(rs.getInt(KPI_INDICATOR_M1));
            indicator.setM2(rs.getInt(KPI_INDICATOR_M2));
            indicator.setM3(rs.getInt(KPI_INDICATOR_M3));
            indicator.setM4(rs.getInt(KPI_INDICATOR_M4));
            indicator.setM5(rs.getInt(KPI_INDICATOR_M5));
            indicator.setM6(rs.getInt(KPI_INDICATOR_M6));
            indicator.setM7(rs.getInt(KPI_INDICATOR_M7));
            indicator.setM8(rs.getInt(KPI_INDICATOR_M8));
            indicator.setM9(rs.getInt(KPI_INDICATOR_M9));
            indicator.setM10(rs.getInt(KPI_INDICATOR_M10));
            indicator.setM11(rs.getInt(KPI_INDICATOR_M11));
            indicator.setM12(rs.getInt(KPI_INDICATOR_M12));
            
            indicator.setQ1(rs.getInt(KPI_INDICATOR_Q1));
            indicator.setQ2(rs.getInt(KPI_INDICATOR_Q2));
            indicator.setQ3(rs.getInt(KPI_INDICATOR_Q3));
            indicator.setQ4(rs.getInt(KPI_INDICATOR_Q4));
                       
            indicator.setType(rs.getInt(KPI_INDICATOR_TYPE));
            indicator.setLvl(rs.getInt(KPI_INDICATOR_LVL));
            indicator.setNameParent(rs.getString("parent_name"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return indicator;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FIndicator bean = (FIndicator)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getParentID()); 
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getDescription());            
            params.add(bean.getBaseline());
            params.add(bean.getTargetYear());
            params.add(bean.getTargetJustification());
            
            params.add(bean.getM1());
            params.add(bean.getM2());
            params.add(bean.getM3());
            params.add(bean.getM4());
            params.add(bean.getM5());
            params.add(bean.getM6());
            params.add(bean.getM7());
            params.add(bean.getM8());
            params.add(bean.getM9());
            params.add(bean.getM10());
            params.add(bean.getM11());
            params.add(bean.getM12());
            
            params.add(bean.getQ1());
            params.add(bean.getQ2());
            params.add(bean.getQ3());
            params.add(bean.getQ4());
            
            params.add(bean.getType());
            params.add(bean.getLvl());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public FBeans getMultiRecords(Connection cnn, int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_INDICATOR);
            rs = prstm.executeQuery();
            String members = ",";
            FIndicator bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
              while ((rs != null) && (rs.next() && members != null)) {
                  id = rs.getInt(PARAM_01);
                  if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                      start = true;
                      bean = new FIndicator();
                      bean.setId(id);
                      bean.setCode(rs.getString(PARAM_02));
                      bean.setName(rs.getString(PARAM_03));
                      bean.setParentID(rs.getInt(PARAM_04));
                      if (id > 0) {
                          members += id + ",";
                          beans.add(bean);
                      }
                  }
                  if (start) {
                      if (all || members.indexOf("," + id + ",") >= 0) {
                          id = rs.getInt(PARAM_05);
                          bean = new FIndicator();
                          bean.setId(id);
                          bean.setCode(rs.getString(PARAM_06));
                          bean.setName(rs.getString(PARAM_07));
                          bean.setParentID(rs.getInt(PARAM_08));
                          if (id > 0) {
                              members += id + ",";
                              beans.add(bean);
                          } else {
                              all = true;
                          }
                      } else if (!all) {
                          members = null;
                          start = false;
                      }
                  }
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
}
