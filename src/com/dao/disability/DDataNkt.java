package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDataNkt;
import com.form.disability.FIndicatorKpi;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


public class DDataNkt extends DSqlDisability {
    
    public FDataNkt getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDataNkt bean = (FDataNkt)seed;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_DATA_INDICATOR_DTL_BY_HDR);
            prstm.setInt(PARAM_01, bean.getDataId());
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
    
    public FBeans getAll(Connection cnn, FSeed seed, HttpSession session) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FDataNkt bean    = (FDataNkt)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select dis.id, dis.date_last_update, dis.ma, dis.ten, dis.sex, dis.ngaysinh, dis.dantoc_id, dis.sonha, dis.ten_ncs, dis.quanhe_ncs \n" + 
                     "from dr_disabilitypeople dis WHERE 1=1 ";
        String SQL_REPORT = "";
        
        try {
            List params = new ArrayList();
            if (bean.getDisLocationId()>0) {
                params.add(bean.getDisLocationId());
                SQL += "AND dis.id_tinh=?";
            }
            
            SQL += " ORDER BY dis.id_tinh, dis.id";
            prstm = prepareStatement(cnn, SQL, params);
            
            rs = prstm.executeQuery();
            beans = new FBeans();
    
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            SQL_REPORT = SQL;
            if (beans.getFirstRecord() <= 1) {
                rs.beforeFirst();
            } else {
                rs.absolute(beans.getFirstRecord() - 1);
            }            
            int i = 0;  
            while (rs != null && rs.next() && (i<AppConfigs.APP_ROWS_VIEW)) {
                i ++ ;
                bean = new FDataNkt();
                bean = getInformation(rs);
                beans.add(bean);
            }
            
            // Store param search to Session            
            session.setAttribute("SQL_REPORT", SQL_REPORT);
            session.setAttribute("params", params);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FBeans getAllAc(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FDataNkt bean    = (FDataNkt)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select dis.id, dis.date_last_update, dis.ma, dis.ten, dis.sex, dis.ngaysinh, dis.dantoc_id, dis.sonha, dis.ten_ncs, dis.quanhe_ncs \n" + 
                     "from dr_disabilitypeople dis WHERE 1=1 ";
        try {
            List params = new ArrayList();
            /*if (bean.getDataId()>0) {
                params.add(bean.getDataId());
                SQL += "AND data_id=?";
            }*/
            //SQL += "and ((?<>0 AND dis.id_tinh=?) OR (?=0 AND 1=1))";
            //prstm.setInt(PARAM_01, bean.getLocationId());
            //prstm.setInt(PARAM_02, bean.getLocationId());
            //prstm.setInt(PARAM_03, bean.getLocationId());
            prstm = prepareStatement(cnn, SQL, params);
            
            rs = prstm.executeQuery();
            beans = new FBeans();
    
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            
            if (beans.getFirstRecord() <= 1) {
                rs.beforeFirst();
            } else {
                rs.absolute(beans.getFirstRecord() - 1);
            }            
            int i = 0;  
            while (rs != null && rs.next()) {
                i ++ ;
                bean = new FDataNkt();
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
        FIndicatorKpi bean = (FIndicatorKpi)seed;
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

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_DATA_INDICATOR_NKT, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    /*public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FDataNkt bean = (FDataNkt)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_INDICATOR, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }*/

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FDataNkt bean = (FDataNkt)seed;
        return 0 < delete(cnn, TABLE_KPI_DATA_NKT, KPI_DATA_NKT_NKT_ID + EQUAL + bean.getNktId());
    }


    public FDataNkt getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDataNkt dataNkt = new FDataNkt();
        try {
            dataNkt.setNktId(rs.getInt("id"));
            dataNkt.setCreateDate(dataNkt.dateToString(rs.getDate("date_last_update")));
            dataNkt.setLocation(rs.getString("location_name"));
            dataNkt.setDisCode(rs.getString("ma"));
            dataNkt.setDisName(rs.getString("ten"));
            dataNkt.setDisSex(rs.getInt("sex"));
            dataNkt.setDisBirth(dataNkt.dateToString(rs.getDate("ngaysinh")));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return dataNkt;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDataNkt bean = (FDataNkt)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getDataId()); 
            params.add(bean.getNktId());            
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
            FIndicatorKpi bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                    start = true;
                    bean = new FIndicatorKpi();
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
                        bean = new FIndicatorKpi();
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
