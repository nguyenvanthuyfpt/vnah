package com.dao.disability.categorys;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FEvent;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


public class DEvent extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FEvent bean = (FEvent)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_EVENT_INFORMATION);
            pstmt.setString(PARAM_01, bean.getCode());
            pstmt.setInt(PARAM_02, bean.getEventId());
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

    public FEvent getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FEvent bean = new FEvent();
        bean = (FEvent)seed;
        String sql =
            "SELECT event.*, area.name as location_name, fn_check_expire_date(event.end_date::date) as expire, 0 total " +
            "FROM kpi_event event, dr_area area WHERE event.location_id=area.tinh_id AND event.id=? " +
            "AND (event.location_id=? OR ?=0)";      
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(PARAM_01, bean.getEventId());
            prstm.setInt(PARAM_02, bean.getLocationId());
            prstm.setInt(PARAM_03, bean.getLocationId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
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

    public FBeans getAll(Connection cnn, FSeed seed, HttpSession session) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        FEvent bean = (FEvent)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "SELECT event.*, area.name as location_name, fn_check_expire_date(event.end_date::date) as expire, CASE WHEN v_count.total IS NULL THEN 0 ELSE v_count.total END \n" + 
                      "FROM kpi_event event INNER JOIN dr_area area ON event.location_id=area.tinh_id \n" + 
                      "LEFT JOIN kpi_v_event_count v_count ON event.id=v_count.event_id WHERE 1=1 ";
        String SQL_REPORT = "";
        try {
            List params = new ArrayList();
            if (bean.getLocationId() > 0) {
                params.add(bean.getLocationId());
                SQL += " AND location_id=? ";
            }
            
            SQL += "ORDER BY event.location_id, event.activity ASC";
            
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
                bean = new FEvent();
                bean = getInformation(rs, 0);
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
    
    public FBeans getEventByObjInd(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        FEvent bean = (FEvent)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
          String SQL =
            "SELECT event.*, area.name as location_name \n" + 
            "FROM kpi_event event, dr_area area, kpi_data_hdr hdr\n" + 
            "WHERE event.location_id=area.tinh_id \n" + 
            "AND hdr.id=?\n" + 
            "AND event.end_date between ? and ? " +
            "AND strpos(hdr.event_id, event.id::text)>0 ";
        try {
            List params = new ArrayList();
            params.add(bean.getHdrId());
            params.add(bean.stringToSqlDate(bean.getCreateFrom()));
            params.add(bean.stringToSqlDate(bean.getCreateTo()));
            
            if (bean.getLocationId() > 0) {
                params.add(bean.getLocationId());
                SQL += " AND event.location_id=? ";
            }
            
            prstm = prepareStatement(cnn, SQL + ORDER_BY + KPI_EVENT_ACTIVITY + ASC, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
  
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            int i = 0;
            while (rs != null && rs.next()) {
                i++;
                bean = new FEvent();
                bean = getInformation(rs, 1);
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
    
    public FBeans getEventByLocationId(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        FEvent bean = (FEvent)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
          String SQL =
            "SELECT event.*, area.name as location_name \n" + 
            "FROM kpi_event event, dr_area area\n" + 
            "WHERE event.location_id=area.tinh_id ";
        try {
            List params = new ArrayList();            
            if (bean.getLocationId() > 0) {
                params.add(bean.getLocationId());
                SQL += " AND event.location_id=? ";
            }
            prstm = prepareStatement(cnn, SQL + ORDER_BY + " event.activity" + ASC, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
    
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            int i = 0;
            while (rs != null && rs.next()) {
                i++;
                bean = new FEvent();
                bean = getInformation(rs, 1);
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

    public FBeans getMultiRecords(Connection cnn,
                                  int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_EVENT);
            rs = prstm.executeQuery();
            String members = ",";
            FEvent bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) ||
                    (!start && id == idDepartment)) {
                    start = true;
                    bean = new FEvent();
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
                        bean = new FEvent();
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

    public int insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        ResultSet rs = null;
        int retval = 0;
        try {
            List params = setParams(seed);
            PreparedStatement ps =
                prepareStatement(cnn, SQL_INSERT_EVENT, params);
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
            FEvent bean = (FEvent)seed;
            List params = setParams(seed);
            params.add(bean.getEventId());
            result = execute(cnn, SQL_UPDATE_EVENT, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public String getNextCode(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        FEvent bean = (FEvent)seed;
        ResultSet rs = null;
        String retval = "";
        String sql =
            "select code || '#' || (select case when lpad((max(substring(code from position('#' in code)+1)::int)+1)::text,3,'0') is null then '001' else\n" +
            "lpad((max(substring(code from position('#' in code)+1)::int)+1)::text,3,'0') end from kpi_event where location_id=?) from dr_area where tinh_id=?";
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setInt(PARAM_01, bean.getLocationId());
            ps.setInt(PARAM_02, bean.getLocationId());
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

    public int getEventIdByCode(Connection cnn,
                                String eventCode) throws EException {
        final String LOCATION = this.toString() + INSERT;
        ResultSet rs = null;
        int retval = 0;
        String sql = "select id from kpi_event where code=?";
        try {
            PreparedStatement ps = cnn.prepareStatement(sql);
            ps.setString(1, eventCode);
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

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FEvent bean = (FEvent)seed;
        boolean retval = false;
        int val = 0;
        try {
            val = delete(cnn, TABLE_KPI_EVENT, KPI_EVENT_ID + EQUAL + bean.getEventId());  
            retval = (0<val)?true:false;
        } catch (Exception sqle) {
          if (AppConfigs.APP_DEBUG)
              throw new EException(LOCATION, sqle);
        }
        return retval;
    }


    public FEvent getInformation(ResultSet rs, int type) throws EException {
        final String LOCATION = "->getInformation()";
        FEvent event = new FEvent();
        try {
            event.setEventId(rs.getInt(KPI_EVENT_ID));
            event.setLocationId(rs.getInt(KPI_EVENT_LOCATION_ID));
            event.setLocationName(rs.getString("location_name"));
            event.setCode(rs.getString(KPI_EVENT_CODE));
            event.setEventType(rs.getInt(KPI_EVENT_EVENT_TYPE));
            event.setEventField(rs.getInt(KPI_EVENT_EVENT_FIELD));
            event.setCreateDate(event.dateToString(rs.getDate(KPI_EVENT_CREATE_DATE)));
            event.setModifyDate(event.dateToString(rs.getDate(KPI_EVENT_MODIFY_DATE)));
            event.setStartDate(event.dateToString(rs.getDate(KPI_EVENT_START_DATE)));
            event.setEndDate(event.dateToString(rs.getDate(KPI_EVENT_END_DATE)));
            if (type==1) {
            event.setActivity(rs.getString(KPI_EVENT_ACTIVITY)+ ("".equals(rs.getString(KPI_EVENT_LOCATION))?"":" ("+rs.getString(KPI_EVENT_LOCATION) + ")"));  
            } else {
                event.setActivity(rs.getString(KPI_EVENT_ACTIVITY));
                event.setExpire(rs.getInt("expire"));
                event.setTotal(rs.getInt("total"));
            }
            event.setLocation(rs.getString(KPI_EVENT_LOCATION));
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return event;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FEvent bean = (FEvent)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getLocationId());
            params.add(bean.getCode());
            params.add(bean.getEventType());
            params.add(bean.getEventField());
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.stringToSqlDate(bean.getStartDate()));
            params.add(bean.stringToSqlDate(bean.getEndDate()));
            params.add(bean.getActivity());
            params.add(bean.getLocation());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
}
