package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDataHdr;

import com.lib.AppConfigs;

import com.util.Constant;
import com.util.Utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DDataHdr extends DSqlDisability {
    public boolean isExists(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataHdr bean = (FDataHdr)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String sql = "select count(1) from kpi_data_hdr hdr, kpi_data_dtl dtl \n" + 
                      "where hdr.id=dtl.data_id\n" + 
                      "and hdr.location_id=? and hdr.ind_id=? and hdr.obj_id=?\n" + 
                      "and dtl.month=? and dtl.year=?";   
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getLocationId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getObjId());
            pstmt.setInt(PARAM_04, bean.getMonth());
            pstmt.setInt(PARAM_05, bean.getYear());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
            result = retval>0?true:false;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
    
    public boolean isExistsHdr(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataHdr bean = (FDataHdr)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String sql = "";
        sql += "SELECT COUNT(1) FROM kpi_data_hdr hdr \n" + 
                "WHERE 1=1 AND hdr.location_id=? AND hdr.ind_id=? AND hdr.obj_id=?"; 
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getLocationId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getObjId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
            result = retval>0?true:false;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
    
    public int getHdrId(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataHdr bean = (FDataHdr)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String sql = "";
        sql += "SELECT hdr.id FROM kpi_data_hdr hdr \n" + 
                "WHERE 1=1 AND hdr.location_id=? AND hdr.ind_id=? AND hdr.obj_id=?"; 
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getLocationId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getObjId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return retval;
    }
    
    public boolean isExistsRef(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataHdr bean = (FDataHdr)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String sql = "";
        sql += "SELECT COUNT(1) FROM kpi_data_hdr hdr \n" + 
                "WHERE 1=1 AND hdr.location_id=? AND hdr.ind_id=(SELECT id FROM kpi_indicator WHERE parent_id=?) AND hdr.obj_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getLocationId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getObjId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
            result = retval>0?true:false;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
    
    public int getRefId(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataHdr bean = (FDataHdr)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String sql = "";
        sql += "SELECT hdr.id FROM kpi_data_hdr hdr \n" + 
                "WHERE 1=1 AND hdr.location_id=? AND hdr.ind_id=(SELECT id FROM kpi_indicator WHERE parent_id=?) AND hdr.obj_id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getLocationId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getObjId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return retval;
    }
    
    public FDataHdr getRecordByIndicator(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByIndicator()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDataHdr bean = new FDataHdr();
        bean = (FDataHdr)seed;
        String tblDetail = "kpi_data_dtl";
        int yearReport = bean.getYearReport();
        String createDate = seed.dateToString(seed.getCurrentDate());
        
        if (bean.getType()==Constant.KPI_DATA_PERSON 
              ||bean.getType()==Constant.KPI_DATA_HOURS) {
            tblDetail = "kpi_data_per";
        } else if (bean.getType()==Constant.KPI_DATA_DIS) {
            tblDetail = "kpi_data_nkt";
        }
        
        String sSel = "", sWhere = "WHERE 1=1";
        sSel += "SELECT hdr.id, hdr.event_id, hdr.location_id, \n"; 
        sSel += "hdr.create_date, ind.lvl, ind.obj_id, ind.ins, ind.obj_code, ind.obj_name, ind.obj_desc, \n";
        sSel += (bean.getInputType()==3? bean.getIndId()+ " as ind_id, \n":"ind.id ind_id, \n");
        sSel += "ind.code, ind.name, ind.description, ind.target_year, ind.target_justification, \n";
        sSel += (bean.getInputType()==3?"3 as type, \n":"ind.type, \n");
        
        if (bean.getType()==Constant.KPI_DATA_VALUE) {
            sSel += bean.getDtlId()>0?"dtl.period, ":"0 as period, \n";
            sSel += bean.getDtlId()>0?"dtl.month, ":"date_part('month', current_date) as month, \n";
            sSel += bean.getDtlId()>0?"dtl.quarter, ":  Utilities.getCurrentQuarter(bean.getMonth()) + " as quarter, \n";
            sSel += bean.getDtlId()>0?"dtl.year ": Utilities.getCurrentYear(bean.getCurrentDate()) + " as year \n";
            sSel += "FROM kpi_data_hdr hdr RIGHT JOIN (SELECT ind.*, map.obj_id, map.ins, obj.code obj_code, obj.name obj_name, obj.description  obj_desc \n";
            sSel += "FROM (SELECT obj_id, ind_id, ins FROM kpi_obj_ind WHERE 1=1 AND year = ? AND obj_id=? AND ind_id=?) map, kpi_indicator ind, kpi_object obj \n";
            sSel += "WHERE map.ind_id=ind.id AND map.obj_id=obj.id) ind ON hdr.ind_id=ind.id AND hdr.obj_id=ind.obj_id \n";
            
            if (bean.getDtlId()>0) {            
                sSel += "INNER JOIN kpi_data_dtl dtl ON hdr.id=dtl.data_id ";
                sWhere += " AND dtl.id=? ";            
            }
        } else {
            sSel += "0 as period, \n";
            sSel += "date_part('month', current_date) as month, \n";
            sSel += "date_part('quarter', current_date) as quarter, \n";
            sSel += "date_part('year', current_date) as year \n";
            sSel += "FROM kpi_data_hdr hdr RIGHT JOIN (SELECT ind.*, map.obj_id, map.ins, obj.code obj_code, obj.name obj_name, obj.description  obj_desc \n";
            sSel += "FROM (SELECT obj_id, ind_id, ins FROM kpi_obj_ind WHERE 1=1 AND year = ? AND obj_id=? AND ind_id=?) map, kpi_indicator ind, kpi_object obj \n";
            sSel += "WHERE map.ind_id=ind.id AND map.obj_id=obj.id) ind ON hdr.ind_id=ind.id AND hdr.obj_id=ind.obj_id \n";
        }
        
        if (bean.getLocationId()>0) {
            sWhere += " AND hdr.location_id=? ";
        }
        
        sSel += sWhere;
        try {
            prstm = cnn.prepareStatement(sSel);
            int i=1;
            prstm.setInt(i++, bean.getYearReport());
            prstm.setInt(i++, bean.getObjId());
            prstm.setInt(i++, bean.getIndId());
            
            if (bean.getType()==Constant.KPI_DATA_VALUE && bean.getDtlId()>0) {
                prstm.setInt(i++, bean.getDtlId());
            } 
            
            if (bean.getLocationId()>0) {
                prstm.setInt(i++, bean.getLocationId()); 
            }
            
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformation(rs, createDate);
                bean.setYearReport(yearReport);
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
    
    public String getCodeData(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByIndicator()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDataHdr bean = new FDataHdr();
        bean = (FDataHdr)seed;
        String code = "";
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_DATA_INDICATOR_CODE);
            prstm.setInt(PARAM_01, bean.getIndId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                code = rs.getString(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return code;
    }
    
    public FDataHdr getDataHdrById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getRecordByIndicator()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDataHdr bean = new FDataHdr();
        try {
            prstm = cnn.prepareStatement("select obj_id, ind_id, event_id from kpi_data_hdr where id=?");           
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInfor(rs);
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
        FDataHdr bean    = (FDataHdr)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = SQL_SELECT_INDICATOR_BY_PARAM;
        String createDate = seed.dateToString(seed.getCurrentDate());
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn, SQL + ORDER_BY + KPI_INDICATOR_ID, params);
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
            while (rs != null && rs.next() && (i<AppConfigs.APP_ROWS_VIEW)) {
                i ++ ;
                bean = new FDataHdr();
                bean = getInformation(rs, createDate);
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
        FDataHdr bean = (FDataHdr)seed;
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
            ps = prepareStatement(cnn, SQL_INSERT_DATA_INDCATOR_HDR, params);            
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
    
    public int count(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + COUNT;
        int retval = 0;
        FDataHdr bean = (FDataHdr)seed;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String tblDetail = "kpi_data_dtl", sql = "";        
        if (bean.getInputType()==2||bean.getInputType()==3) { // 2: Person -- 3: Link
            tblDetail = "kpi_data_per";
        } else if (bean.getInputType()==1) {
            tblDetail = "dr_disabilitypeople";
        }
        
        if (bean.getInputType()==3) {         // Link
            sql = "select count(1) from kpi_data_hdr hdr, " +tblDetail+ " dtl \n" + 
                "where hdr.id=dtl.data_id \n" + 
                "and hdr.ind_id=(SELECT parent_id FROM kpi_indicator WHERE id=?) and hdr.obj_id=? \n" + 
                "and ((?<>0 AND hdr.location_id=?) OR (?=0 AND 1=1))";
        } else if (bean.getInputType()==2) {  // Person
              sql = "select count(1) from kpi_data_hdr hdr, " +tblDetail+ " dtl \n" + 
                  "where hdr.id=dtl.data_id \n" + 
                  "and hdr.ind_id=? and hdr.obj_id=? \n" + 
                  "and ((?<>0 AND hdr.location_id=?) OR (?=0 AND 1=1))";
        } else if (bean.getInputType()==0) {
            sql = "select count(1) from kpi_data_hdr hdr," + tblDetail + " dtl where hdr.id=dtl.data_id \n" +
                "and hdr.ind_id=? and hdr.obj_id=? \n" +  
                "and ((?<>0 AND hdr.location_id=?) OR (?=0 AND 1=1))";        
        }
        
        try {
            ps = cnn.prepareStatement(sql);            
            ps.setInt(PARAM_01, bean.getIndId());
            ps.setInt(PARAM_02, bean.getObjId());
            ps.setInt(PARAM_03, bean.getLocationId());
            ps.setInt(PARAM_04, bean.getLocationId());
            ps.setInt(PARAM_05, bean.getLocationId());
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

    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FDataHdr bean = (FDataHdr)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_DATA_INDCATOR_HDR, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FDataHdr bean = (FDataHdr)seed;
        return 0 < delete(cnn, TABLE_KPI_DATA_HDR, KPI_DATA_HDR_ID + EQUAL + bean.getId());
    }


    public FDataHdr getInformation(ResultSet rs, String createDate) throws EException {
        final String LOCATION = "->getInformation()";
        FDataHdr dataHdr = new FDataHdr();
        try {
            int hdrId = rs.getInt(KPI_DATA_HDR_ID);
            
            dataHdr.setId(hdrId);
            dataHdr.setEventIds(rs.getString(KPI_DATA_HDR_EVENT_ID)); 
            dataHdr.setLocationId(rs.getInt("location_id"));
            dataHdr.setCreateDate((hdrId==0)? createDate:dataHdr.dateToString(rs.getDate(KPI_INDICATOR_CREATE_DATE)));
            dataHdr.setLvl(rs.getInt("lvl"));
            dataHdr.setObjId(rs.getInt(KPI_DATA_HDR_OBJ_ID));
            dataHdr.setIns(rs.getInt("ins"));
            dataHdr.setObjCode(rs.getString("obj_code"));
            dataHdr.setObjName(rs.getString("obj_name"));
            dataHdr.setObjDesc(rs.getString("obj_desc"));
            dataHdr.setIndId(rs.getInt(KPI_DATA_HDR_IND_ID));
            dataHdr.setCode(rs.getString(KPI_INDICATOR_CODE));
            dataHdr.setName(rs.getString(KPI_INDICATOR_NAME));
            dataHdr.setDescription(rs.getString(KPI_INDICATOR_DESCRIPTION));
            dataHdr.setTargetYear(rs.getInt(KPI_INDICATOR_TARGET_YEAR));
            dataHdr.setTargetJustification(rs.getString(KPI_INDICATOR_TARGET_JUSTIFICATION));
            dataHdr.setType(rs.getInt(KPI_INDICATOR_TYPE));
            dataHdr.setTypePeriod(rs.getInt("period"));
            dataHdr.setMonth(rs.getInt("month"));
            dataHdr.setQuarter(rs.getInt("quarter"));
            dataHdr.setYear(rs.getInt("year")); 
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            
        }
        return dataHdr;
    }
    
    public FDataHdr getInfor(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDataHdr dataHdr = new FDataHdr();
        try {
            dataHdr.setObjId(rs.getInt(KPI_DATA_HDR_OBJ_ID));
            dataHdr.setIndId(rs.getInt(KPI_DATA_HDR_IND_ID));
            dataHdr.setEventIds(rs.getString(KPI_DATA_HDR_EVENT_ID));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            
        }
        return dataHdr;
    }
    
    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDataHdr bean = (FDataHdr)seed;
        List params = new ArrayList();
        try {
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getUserId());
            params.add(bean.getObjId());
            params.add(bean.getIndId());
            params.add(bean.getEventIds());
            params.add(bean.getLocationId());
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
            FDataHdr bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                    start = true;
                    bean = new FDataHdr();
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
                        bean = new FDataHdr();
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
    
    public boolean isExistsDtl(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataHdr bean = (FDataHdr)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int retval = 0;
        String sql = "";
        sql += "SELECT COUNT(1) FROM kpi_data_hdr hdr, kpi_data_per dtl\n" + 
                "WHERE hdr.id=dtl.data_id AND hdr.obj_id=?\n" + 
                "AND hdr.ind_id=? AND dtl.per_id=?"; 
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, bean.getObjId());
            pstmt.setInt(PARAM_02, bean.getIndId());
            pstmt.setInt(PARAM_03, bean.getDtlId());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                retval = rs.getInt(1);
            }
            result = retval>0?true:false;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
}
