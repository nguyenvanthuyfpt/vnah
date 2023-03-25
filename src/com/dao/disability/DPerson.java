package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FIndicatorKpi;
import com.form.disability.FPerson;

import com.lib.AppConfigs;

import com.util.Constant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


public class DPerson extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FPerson bean = (FPerson)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_EXISTS_DATA_INDICATOR_PER);
            pstmt.setInt(PARAM_01, bean.getDataId());
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

    public FPerson getDetailByID(Connection cnn,
                                 FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FPerson bean = new FPerson();
        bean = (FPerson)seed;
        String SQL = "";
        try {
            List params = new ArrayList();
            if (bean.getState()==Constant.KPI_STATE_SELECT) {
                SQL = "SELECT p.id as per_id, p.create_date, p.event_id, 0 result, 0 hours, p.code, p.name, p.sex, p.agency, p.title, p.address, p.contact,  \n" + 
                        "p.user_id, u.username, u.fullname, p.obj_id, p.ind_id, p.location_id, a.name as location_name FROM \n" + 
                        "(SELECT distinct on (p.id) p.*, m.per_id, m.event_id, m.data_id, m.obj_id, m.ind_id \n" + 
                        "FROM kpi_person p LEFT JOIN (\n" + 
                        "SELECT DISTINCT m.per_id, m.event_id, m.data_id, h.obj_id, h.ind_id FROM kpi_data_per m, kpi_data_hdr h \n" + 
                        "WHERE m.data_id=h.id  \n" + 
                        "AND ((0=?) OR (h.id=?)) AND h.obj_id=? AND h.ind_id=?\n" + 
                        "AND ((0=?) OR (m.event_id=?))) m ON p.id = m.per_id \n" + 
                        "WHERE 1=1 AND ((0=0) OR (p.location_id=0))) p \n" + 
                        "LEFT JOIN dr_area a ON p.location_id= a.tinh_id  \n" + 
                        "INNER JOIN users u ON p.user_id = u.user_id \n" + 
                        "WHERE id=?";
            } else if (bean.getState()==Constant.KPI_STATE_AUTO) {
                SQL = "SELECT * FROM kpi_person WHERE id=?";
            } else {
                SQL = "SELECT m.per_id, m.create_date, m.event_id, m.result, m.hours, p.code, p.name, p.sex, p.agency, p.title, \n" + 
                        "h.obj_id, h.ind_id, h.location_id, a.name as location_name\n" +
                        "FROM kpi_data_per m LEFT JOIN kpi_data_hdr h ON m.data_id=h.id \n" + 
                        "INNER JOIN kpi_person p ON m.per_id=p.id \n" + 
                        "INNER JOIN dr_area a ON h.location_id = a.tinh_id \n" + 
                        "RIGHT JOIN kpi_event e on m.event_id=e.id \n" +
                        "WHERE 1=1 AND ((0=?) OR (h.id=?)) AND h.obj_id = ? AND h.ind_id=? \n" +
                        "AND ((0=?) OR (h.location_id=?)) \n" + 
                        "AND m.per_id = ? ORDER BY m.event_id";
            }
            
            if (bean.getState()==Constant.KPI_STATE_AUTO) {
                params.add(bean.getId());
            } else {
                params.add(bean.getDataId());
                params.add(bean.getDataId());
                params.add(bean.getObjId());
                params.add(bean.getIndId());
                params.add(bean.getLocationId());
                params.add(bean.getLocationId());
                params.add(bean.getId());
            }
           
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                if (bean.getState()==Constant.KPI_STATE_AUTO) {
                    bean= getInformationDetail(rs);
                }  else {
                    bean = getInformation(rs);
                }
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
    
    public FPerson getDetailByPerID(Connection cnn,
                                 FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FPerson bean = new FPerson();
        bean = (FPerson)seed;
        String SQL = "";
        try {
            List params = new ArrayList();
            SQL = "SELECT p.id as per_id, m.create_date, m.event_id event_id, 0 result, 0 hours, p.code, p.name, p.sex, p.agency, p.title, p.address, p.contact, 0 user_id, 0 username, 0 fullname, \n" + 
                  "0 obj_id, 0 ind_id, ? location_id, a.name as location_name \n" + 
                  "FROM kpi_person p LEFT JOIN dr_area a on p.location_id=a.tinh_id \n" + 
                  "INNER JOIN kpi_data_per m ON p.id=m.per_id \n" + 
                  "WHERE p.id=? \n" + 
                  "AND m.event_id=?" ;
            
            params.add(bean.getLocationId());
            params.add(bean.getId());
            params.add(bean.getEventId());
            
            prstm = prepareStatement(cnn, SQL, params);
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
    
    public FPerson getPerson(Connection cnn,
                                 FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FPerson bean = new FPerson();
        bean = (FPerson)seed;
        String SQL = "";
        try {
            List params = new ArrayList();
            SQL = "SELECT * FROM kpi_person WHERE id=?";
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformationDetail(rs);
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
        FBeans beans = new FBeans();
        FPerson bean = (FPerson)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "";
        String SQL_REPORT = "";
        try {
            List params = new ArrayList();
              if (bean.getState()==Constant.KPI_STATE_SELECT) {
                SQL = "SELECT p.id as per_id, p.create_date, p.event_id, 0 result, 0 hours, p.code, p.name, p.sex, p.agency, p.title, p.address, p.contact, " +
                      "p.user_id, u.username, u.fullname, p.obj_id, p.ind_id, p.location_id, a.name as location_name FROM \n" + 
                      "(SELECT distinct on (p.id) p.*, m.per_id, m.event_id, m.data_id, m.obj_id, m.ind_id \n" + 
                      "FROM kpi_person p LEFT JOIN (\n" + 
                      "SELECT DISTINCT m.per_id, m.event_id, m.data_id, h.obj_id, h.ind_id FROM kpi_data_per m, kpi_data_hdr h \n" + 
                      "WHERE m.data_id=h.id  \n" + 
                      "AND ((0=?) OR (h.id=?)) AND h.obj_id=? AND h.ind_id=? \n" + 
                      "AND ((0=?) OR (m.event_id=?))) m ON p.id = m.per_id \n" + 
                      "WHERE 1=1 AND ((0=?) OR (p.location_id=?))) p \n" +
                      "LEFT JOIN dr_area a ON p.location_id= a.tinh_id  \n" +
                      "INNER JOIN users u ON p.user_id = u.user_id \n" +
                      "ORDER BY p.location_id, p.name";
            } else {
                SQL = "SELECT m.per_id, m.create_date, m.event_id, m.result, m.hours, p.code, p.name, p.sex, p.agency, p.title, p.address, p.contact, \n" + 
                      "h.user_id, u.username, u.fullname, h.obj_id, h.ind_id, h.location_id, a.name as location_name \n" + 
                      "FROM kpi_data_per m LEFT JOIN kpi_data_hdr h ON m.data_id=h.id \n" + 
                      "INNER JOIN kpi_person p ON m.per_id=p.id \n" + 
                      "INNER JOIN dr_area a ON h.location_id = a.tinh_id \n" +
                      "INNER JOIN users u ON h.user_id = u.user_id \n" +
                      "RIGHT JOIN kpi_event e on m.event_id=e.id \n" +
                      "WHERE 1=1 AND ((0=?) OR (h.id=?)) AND h.obj_id = ? AND h.ind_id=? \n" + 
                      "AND ((0=?) OR (m.event_id=?)) \n" +                       
                      "AND ((0=?) OR (h.location_id=?)) \n" +
                      "AND m.create_date BETWEEN ? AND ? \n" +
                      "ORDER BY m.create_date DESC";  
            }            
            
            params.add(bean.getDataId());
            params.add(bean.getDataId());
            params.add(bean.getObjId());
            params.add(bean.getIndId());
            params.add(bean.getEventId());
            params.add(bean.getEventId());
            params.add(bean.getLocationId());
            params.add(bean.getLocationId());
            
            if (bean.getState()!=Constant.KPI_STATE_SELECT) {
                params.add(bean.stringToSqlDate(bean.getCreateFrom()));
                params.add(bean.stringToSqlDate(bean.getCreateTo()));
            }
            
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
            while (rs != null && rs.next() && (i < AppConfigs.APP_ROWS_VIEW)) {
                i++;
                bean = new FPerson();
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
    
    public FBeans getPersonAC(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        FPerson bean = (FPerson)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = SQL_SELECT_PERSON_BY_PARAM;
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();
            beans = new FBeans();  
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));            
            while (rs != null && rs.next()) {
                bean = new FPerson();
                bean = getInformationAc(rs);
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

    public int insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        int retval = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List params = setParams(seed);
            ps = prepareStatement(cnn, SQL_INSERT_KPI_PERSON, params);
            rs = ps.executeQuery();
            if (rs.next()) {
                retval = rs.getInt(1);
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return retval;
    }

    public boolean update(Connection cnn, FSeed seed, boolean updateAll) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FPerson bean = (FPerson)seed;
            List params = updateAll? setParams(seed):setParamsUpdate(seed);
            params.add(bean.getId());
            result = execute(cnn, updateAll? SQL_UPDATE_KPI_PERSON:SQL_UPDATE_KPI_PERSON_UPDATE, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FPerson bean = (FPerson)seed;
        return 0 <
            delete(cnn, TABLE_KPI_PERSON, KPI_DATA_PER_ID + EQUAL + bean.getId());
    }


    public FPerson getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FPerson dataDtl = new FPerson();
        try {
            /*
            dataDtl.setId(rs.getInt(KPI_DATA_PER_ID));
            dataDtl.setModifyDate(dataDtl.dateToString(rs.getDate(KPI_DATA_PER_MODIFY_DATE)));
            dataDtl.setUserId(rs.getInt(KPI_DATA_PER_USER_ID));
            dataDtl.setLocationId(rs.getInt(KPI_DATA_PER_LOCATION_ID));
            dataDtl.setLocationName(rs.getString("location_name"));
            dataDtl.setAddress(rs.getString(KPI_DATA_PER_ADDRESS));
            dataDtl.setContact(rs.getString(KPI_DATA_PER_CONTACT));
            dataDtl.setCode(rs.getString(KPI_DATA_PER_CODE));
            dataDtl.setName(rs.getString(KPI_DATA_PER_NAME));
            dataDtl.setSex(rs.getInt(KPI_DATA_PER_SEX));
            dataDtl.setAgency(rs.getString(KPI_DATA_PER_AGENCY));
            dataDtl.setTitle(rs.getString(KPI_DATA_PER_TITLE));            
            dataDtl.setEventId(rs.getInt("event_id"));
            dataDtl.setCreateDate(dataDtl.dateToString(rs.getDate("create_date")));
            dataDtl.setVoteResult(rs.getInt(KPI_DATA_HDR_PER_RESULT_RESULT));
            dataDtl.setHours(rs.getInt(KPI_DATA_HDR_PER_RESULT_HOURS));
            dataDtl.setChecked(rs.getString("checked"));
            */
            
            dataDtl.setId(rs.getInt("per_id"));
            dataDtl.setCreateDate(dataDtl.dateToString(rs.getDate("create_date")));
            dataDtl.setEventId(rs.getInt("event_id"));
            dataDtl.setVoteResult(rs.getInt("result"));
            dataDtl.setHours(rs.getInt("hours"));
            dataDtl.setCode(rs.getString("code"));
            dataDtl.setName(rs.getString("name"));
            dataDtl.setSex(rs.getInt("sex"));
            dataDtl.setAgency(rs.getString("agency"));
            dataDtl.setTitle(rs.getString("title"));
            dataDtl.setContact(rs.getString("contact"));
            dataDtl.setAddress(rs.getString("address"));
            
            dataDtl.setEventId(rs.getInt("event_id"));
            dataDtl.setLocationId(rs.getInt("location_id"));
            dataDtl.setLocationName(rs.getString("location_name"));
            dataDtl.setObjId(rs.getInt("obj_id"));
            dataDtl.setIndId(rs.getInt("ind_id"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return dataDtl;
    }
    
    public FPerson getInformationAc(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FPerson dataDtl = new FPerson();
        try {
            dataDtl.setId(rs.getInt(KPI_DATA_PER_ID));
            dataDtl.setCreateDate(dataDtl.dateToString(rs.getDate(KPI_DATA_PER_CREATE_DATE)));
            dataDtl.setModifyDate(dataDtl.dateToString(rs.getDate(KPI_DATA_PER_MODIFY_DATE)));
            dataDtl.setUserId(rs.getInt(KPI_DATA_PER_USER_ID));
            dataDtl.setLocationId(rs.getInt(KPI_DATA_PER_LOCATION_ID));
            dataDtl.setAddress(rs.getString(KPI_DATA_PER_ADDRESS));
            dataDtl.setContact(rs.getString(KPI_DATA_PER_CONTACT));
            dataDtl.setCode(rs.getString(KPI_DATA_PER_CODE));
            dataDtl.setName(rs.getString(KPI_DATA_PER_NAME));
            dataDtl.setSex(rs.getInt(KPI_DATA_PER_SEX));
            dataDtl.setGioiTinh(rs.getInt(KPI_DATA_PER_SEX)==1?dataDtl.ncrToString("Nam"):dataDtl.ncrToString("N&#7919;"));
            dataDtl.setAgency(rs.getString(KPI_DATA_PER_AGENCY));
            dataDtl.setTitle(rs.getString(KPI_DATA_PER_TITLE));            
            dataDtl.setLocationName(rs.getString("location_name"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return dataDtl;
    }
    
    public FPerson getInformationDetail(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FPerson dataDtl = new FPerson();
        try {
            dataDtl.setId(rs.getInt(KPI_DATA_PER_ID));
            dataDtl.setCreateDate(dataDtl.dateToString(rs.getDate(KPI_DATA_PER_CREATE_DATE)));
            dataDtl.setModifyDate(dataDtl.dateToString(rs.getDate(KPI_DATA_PER_MODIFY_DATE)));
            dataDtl.setUserId(rs.getInt(KPI_DATA_PER_USER_ID));
            dataDtl.setLocationId(rs.getInt(KPI_DATA_PER_LOCATION_ID));
            dataDtl.setAddress(rs.getString(KPI_DATA_PER_ADDRESS));
            dataDtl.setContact(rs.getString(KPI_DATA_PER_CONTACT));
            dataDtl.setCode(rs.getString(KPI_DATA_PER_CODE));
            dataDtl.setName(rs.getString(KPI_DATA_PER_NAME));
            dataDtl.setSex(rs.getInt(KPI_DATA_PER_SEX));
            dataDtl.setAgency(rs.getString(KPI_DATA_PER_AGENCY));
            dataDtl.setTitle(rs.getString(KPI_DATA_PER_TITLE));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return dataDtl;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FPerson bean = (FPerson)seed;
        List params = new ArrayList();
        try {
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(!"".equals(bean.getModifyDate()) ?
                       bean.stringToSqlDate(bean.getModifyDate()) : "");            
            
            params.add(bean.getUserId());
            params.add(bean.getLocationId());
            params.add(bean.getAddress());
            params.add(bean.getContact());
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getSex());
            params.add(bean.getAgency());
            params.add(bean.getTitle());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public List setParamsUpdate(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FPerson bean = (FPerson)seed;
        List params = new ArrayList();
        try {            
            params.add(bean.getName());
            params.add(bean.getSex());
            params.add(bean.getTitle());
            params.add(bean.getAgency());
            params.add(bean.getAddress());
            params.add(bean.getContact());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }

    public String getNextCode(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        ResultSet rs = null;
        String retval = "";
        String sql =
            "select to_char(current_date, 'yyyy')::text || '.' ||to_char(current_date, 'mm')::text|| '.' || lpad((count(1)+1)::text,5,'0') from kpi_person;";
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                retval = rs.getString(PARAM_01);
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return retval;
    }
}
