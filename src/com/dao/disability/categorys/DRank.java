package com.dao.disability.categorys;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FRank;

import com.form.disability.report.FReportKpi;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DRank extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FRank bean = (FRank)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_RANK_INFORMATION);
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

    public FRank getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FRank bean = new FRank();
        bean = (FRank)seed;
        String SQL = "select rank.*, 0 total from kpi_rank rank where rank.id=?";
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

    public FRank getRankById(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRankById()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FRank bean = new FRank();
        bean = (FRank)seed;
        String SQL = "SELECT * FROM kpi_v_rank WHERE id= ?";
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformationById(rs);
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
    
    public FRank getRankByDtlId(Connection cnn, int rankId, int nktId, int dtlId) throws EException {
        final String LOCATION = this.toString() + "getRankById()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FRank bean = new FRank();
        String SQL = "SELECT r.*, case when p0=1 then 0 \n" + 
        "when p1=1 then 1 \n" + 
        "when p2=1 then 2 \n" + 
        "when p3=1 then 3 \n" + 
        "when p4=1 then 4 \n" + 
        "end result, v.parent_id, v.breadcrumb, u.fullname, has_sp FROM kpi_data_rank r, kpi_v_rank v, users u " +
        "WHERE r.rank_id=v.id AND r.user_id=u.user_id AND r.rank_id=? and r.nkt_id=? AND r.id=? ORDER BY r.create_date DESC";
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, rankId);
            prstm.setInt(PARAM_02, nktId);
            prstm.setInt(PARAM_03, dtlId);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInforByDtlId(rs);
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

    public FRank getRankByDtlId(Connection cnn, int rankId, int nktId, int dtlId, String createDate) throws EException {
        final String LOCATION = this.toString() + "getRankById()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FRank bean = new FRank();
        String SQL = "SELECT r.*, case when p0=1 then 0 \n" + 
        "when p1=1 then 1 \n" + 
        "when p2=1 then 2 \n" + 
        "when p3=1 then 3 \n" + 
        "when p4=1 then 4 \n" + 
        "end result, v.parent_id, v.breadcrumb, u.fullname, has_sp FROM kpi_data_rank r, kpi_v_rank v, users u " +
        "WHERE r.rank_id=v.id AND r.user_id=u.user_id AND r.rank_id=? and r.nkt_id=? AND r.id=? AND r.create_date=? ORDER BY r.create_date DESC";
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, rankId);
            prstm.setInt(PARAM_02, nktId);
            prstm.setInt(PARAM_03, dtlId);
            prstm.setDate(PARAM_04, bean.stringToSqlDate(createDate));
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInforByDtlId(rs);
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
    
    public boolean haveChild(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FRank bean = (FRank)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_RANK_HAVECHILD);
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
    
    public int countIndicator(Connection conn, int parentId) throws EException {
        final String LOCATION = "->isExist()";
        int result = 0;        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(1) from kpi_rank where parent_id <> ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(PARAM_01, parentId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
               result = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
    
    public int countIndicatorChild(Connection conn) throws EException {
        final String LOCATION = "->isExist()";
        int result = 0;        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            String sql = "select count(1) from kpi_rank where parent_id <> 0";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
               result = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
    
    public int countNumRanked(Connection conn, FSeed seed, int nktId, String createDate, int parentId) throws EException {
        final String LOCATION = "->isExist()";
        int result = 0;        
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        FRank bean = (FRank)seed;
        try {
            if (parentId==0) {
                String sql = "SELECT count(1) FROM kpi_data_rank data WHERE data.create_date = ? AND data.nkt_id = ? ";
                pstmt = conn.prepareStatement(sql);            
                pstmt.setDate(PARAM_01, bean.stringToSqlDate(createDate));
                pstmt.setInt(PARAM_02, nktId);
            } else {
                String sql = "SELECT count(1) FROM kpi_data_rank data WHERE data.create_date = ? \n" + 
                "AND data.nkt_id = ? AND EXISTS (SELECT * FROM kpi_rank dm WHERE dm.id=data.rank_id AND dm.parent_id = ?)";
                pstmt = conn.prepareStatement(sql);            
                pstmt.setDate(PARAM_01, bean.stringToSqlDate(createDate));
                pstmt.setInt(PARAM_02, nktId);
                pstmt.setInt(PARAM_03, parentId);
            }
           
            rs = pstmt.executeQuery();
            while(rs.next()) {
               result = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }
    
    public String getRankDate(Connection conn, int nktId, Date rankInitDate, int limit, int offset, String sort) throws EException {
        final String LOCATION = "->isExist()";
        String results = "";  
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            if (rankInitDate!=null) {
                String sql = "SELECT to_char(a.rank_date, 'dd/MM/yyyy') rank_date FROM\n" + 
                "(SELECT distinct create_date as rank_date FROM kpi_data_rank WHERE nkt_id = ? AND create_date > ? ORDER BY create_date " + sort +") a LIMIT ? OFFSET ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(PARAM_01, nktId);
                pstmt.setDate(PARAM_02, rankInitDate);
                pstmt.setInt(PARAM_03, limit);
                pstmt.setInt(PARAM_04, offset);
                rs = pstmt.executeQuery();
            } else {
                String sql = "SELECT to_char(a.rank_date, 'dd/MM/yyyy') rank_date FROM\n" + 
                "(SELECT distinct create_date as rank_date FROM kpi_data_rank WHERE nkt_id = ? ORDER BY create_date " + sort +") a LIMIT ? OFFSET ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(PARAM_01, nktId);
                pstmt.setInt(PARAM_02, limit);
                pstmt.setInt(PARAM_03, offset);
                rs = pstmt.executeQuery();
            }
           
            while(rs.next()) {
               results += rs.getString(1) + ",";
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return results;
    }
  
    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        Boolean result = false;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_KPI_RANK, params) > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FRank bean = (FRank)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_KPI_RANK, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FRank bean = (FRank)seed;
        return 0 < delete(cnn, TABLE_KPI_RANK, KPI_RANK_ID + EQUAL + bean.getId());
    }
    
    public FRank getInforByDtlId(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setDtlId(rs.getInt("id"));
            rank.setId(rs.getInt("rank_id"));
            rank.setCreateDate(rank.dateToString(rs.getDate("create_date")));
            rank.setResult(rs.getInt("result"));
            rank.setHasSP(rs.getInt("has_sp"));
            rank.setParentID(rs.getInt("parent_id"));
            rank.setBreadcrumb(rs.getString("breadcrumb"));
            rank.setUserName(rs.getString("fullname"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    } 

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FRank bean = (FRank)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getParentID()); 
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getReport());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public FBeans getRanksByParent(Connection cnn, int parentId) throws EException {
        final String LOCATION = this.toString() + "getRanksByParent()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT rank.*, 0 total FROM kpi_rank rank";
        sql += " WHERE rank.parent_id = ? ";
        sql += " ORDER BY code";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, parentId);
            rs = prstm.executeQuery();
            FRank bean = null;          
            while (rs != null && rs.next()) {
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
    
    public FBeans getRanksByDis(Connection cnn, FSeed seed, int nktId, String createDate, int parentId) throws EException {
        final String LOCATION = this.toString() + "getRanksByDis()";
        FBeans beans = new FBeans();
        FRank bean = (FRank)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT data.data_id, dm.id, dm.parent_id, dm.name, data.result::text, \n" + 
                      "CASE WHEN data.has_sp=0 THEN '0' \n" + 
                      "	    WHEN data.has_sp=1 THEN '1' END has_sp," +
                      "CASE WHEN data.has_rank=0 THEN '0' \n" + 
                      "     WHEN data.has_rank=1 THEN '1' END has_rank," +
                      "CASE WHEN data.has_req=0 THEN '0' \n" + 
                      "     WHEN data.has_req=1 THEN '1' END has_req," +
                      "data.create_date, 1 prev_has_rank, fn_check_delete_rank(?,?) has_del  \n" + 
                      "FROM kpi_rank dm LEFT JOIN \n" + 
                      "(SELECT data.id data_id, data.rank_id,  data.create_date,\n" + 
                      "CASE WHEN data.p0=1 THEN 0\n" + 
                      "WHEN data.p1=1 THEN 1\n" + 
                      "WHEN data.p2=1 THEN 2\n" + 
                      "WHEN data.p3=1 THEN 3\n" + 
                      "WHEN data.p4=1 THEN 4 END result, data.has_sp, data.has_rank, data.has_req \n" + 
                      " FROM kpi_data_rank data WHERE nkt_id = ? AND create_date in (?)\n" + 
                      "AND EXISTS (SELECT * FROM kpi_rank dm WHERE dm.id=data.rank_id)) data \n" + 
                      "ON dm.id=data.rank_id WHERE 1=1 ORDER BY dm.code";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, nktId);
            prstm.setDate(2, bean.stringToSqlDate(createDate));
            prstm.setInt(3, nktId);
            prstm.setDate(4, bean.stringToSqlDate(createDate));
            //prstm.setInt(3, parentId); 
            //prstm.setInt(4, parentId); 
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                  bean = getInforList(rs);
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
    
    public FBeans getRanksByDisContinues(Connection cnn, FSeed seed, int nktId, String initDate, String createDate, int parentId) throws EException {
        final String LOCATION = this.toString() + "getRanksByDis()";
        FBeans beans = new FBeans();
        FRank bean = (FRank)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT data.data_id, dm.id, dm.parent_id, dm.name, data.result::text, \n" + 
                      "CASE WHEN data.has_sp=0 THEN '0' \n" + 
                      "     WHEN data.has_sp=1 THEN '1' END has_sp," +
                      "CASE WHEN data.has_rank=0 THEN '0' \n" + 
                      "     WHEN data.has_rank=1 THEN '1' END has_rank," +
                      "CASE WHEN data.has_req=0 THEN '0' \n" + 
                      "     WHEN data.has_req=1 THEN '1' END has_req," +
                      "data.create_date, coalesce(fn_check_prev_rank(?, ?, dm.id),0) prev_has_rank, fn_check_delete_rank(?,?) has_del  \n" + 
                      "FROM kpi_rank dm LEFT JOIN \n" + 
                      "(SELECT data.id data_id, data.rank_id,  data.create_date,\n" + 
                      "CASE WHEN data.p0=1 THEN 0\n" + 
                      "WHEN data.p1=1 THEN 1\n" + 
                      "WHEN data.p2=1 THEN 2\n" + 
                      "WHEN data.p3=1 THEN 3\n" + 
                      "WHEN data.p4=1 THEN 4 END result, data.has_sp, data.has_rank, data.has_req \n" + 
                      " FROM kpi_data_rank data WHERE nkt_id = ? AND create_date in (?)\n" + 
                      "AND EXISTS (SELECT * FROM kpi_rank dm WHERE dm.id=data.rank_id)) data \n" + 
                      "ON dm.id=data.rank_id WHERE 1=1 ORDER BY dm.code";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, nktId);
            prstm.setDate(2, bean.stringToSqlDate(initDate));
            prstm.setInt(3, nktId);
            prstm.setDate(4, bean.stringToSqlDate(createDate));
            prstm.setInt(5, nktId);
            prstm.setDate(6, bean.stringToSqlDate(createDate));
            
            
            //prstm.setInt(3, parentId); 
            //prstm.setInt(4, parentId); 
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                  bean = getInforListContinues(rs);
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
    
    public FBeans getRanksByR_D(Connection cnn, int rankId, int nktId) throws EException {
        final String LOCATION = this.toString() + "getRanksByParent()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT r.*, case when p0=1 then 0 \n" + 
                      "when p1=1 then 1 \n" + 
                      "when p2=1 then 2 \n" + 
                      "when p3=1 then 3 \n" + 
                      "when p4=1 then 4 \n" + 
                      "end result, u.fullname FROM kpi_data_rank r, users u WHERE r.user_id=u.user_id AND rank_id=? and nkt_id=? ORDER BY create_date DESC";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, rankId);
            prstm.setInt(2, nktId); 
            rs = prstm.executeQuery();
            FRank bean = null;          
            while (rs != null && rs.next()) {
                  bean = getInforByR_D(rs);
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
    
    public FBeans getRankByCreateDate(Connection cnn, FSeed seed, int nktId,  String createDate) throws EException {
        final String LOCATION = this.toString() + "getRanksByParent()";
        FBeans beans = new FBeans();
        FRank bean = (FRank)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "SELECT rank_id, CASE WHEN p0=1 THEN 0 \n" + 
                    "WHEN p1=1 THEN 1 \n" + 
                    "WHEN p2=1 THEN 2 \n" + 
                    "WHEN p3=1 THEN 3 \n" + 
                    "WHEN p4=1 THEN 4 \n" + 
                    "END result, create_date \n" + 
                    "FROM kpi_data_rank r\n" + 
                    "WHERE r.nkt_id=? AND r.create_date=? ";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, nktId);
            prstm.setDate(2, bean.stringToSqlDate(createDate)); 
            rs = prstm.executeQuery();  
            while (rs != null && rs.next()) {
                  bean = getInforByCreateDate(rs);
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
    
    public FBeans getListRanks(Connection cnn, FSeed seed, int nktId, String initDate) throws EException {
        final String LOCATION = this.toString() + "getListRanks()";
        FBeans beans = new FBeans();
        FRank bean = (FRank)seed;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        /*String sql = "select ROW_NUMBER () OVER (ORDER BY create_date), a.create_date,\n" + 
                    "get_num_ranked(a.nkt_id, ?, a.create_date::date) num_ranked,\n" + 
                    "get_percent_rank(a.nkt_id, ? , a.create_date::date) from\n" + 
                    "(select create_date, max( nkt_id) nkt_id\n" + 
                    " from kpi_data_rank where nkt_id=? group by create_date) a \n" + 
                    "order by a.create_date desc";*/
        
        String sql = "SELECT a.num, a.create_date, CASE WHEN a.num = 1 THEN \n" + 
                    "	get_num_ranked(a.nkt_id, null, a.create_date::DATE)\n" + 
                    "ELSE \n" + 
                    "	get_num_ranked(a.nkt_id, ?, a.create_date::DATE) \n" + 
                    "END num_ranked, a.percent\n" + 
                    "\n" + 
                    "FROM (\n" + 
                    "select ? nkt_id, ROW_NUMBER () OVER (ORDER BY create_date) AS num, a.create_date,\n" + 
                    "get_percent_rank(a.nkt_id, ?, a.create_date::DATE) percent from\n" + 
                    "(select create_date, max( nkt_id) nkt_id\n" + 
                    " from kpi_data_rank where nkt_id=? group by create_date) a \n" + 
                    "order by a.create_date DESC) a";
        
        try {
            prstm = cnn.prepareStatement(sql);            
            prstm.setDate(1, bean.stringToSqlDate(initDate));
            prstm.setInt(2, nktId);
            prstm.setDate(3, bean.stringToSqlDate(initDate));
            prstm.setInt(4, nktId);
            
            rs = prstm.executeQuery();  
            while (rs != null && rs.next()) {
                int i = 1;
                bean = new FRank();
                bean.setRankNum(rs.getInt(i++));
                bean.setRankDate(bean.dateToString(rs.getDate(i++)));
                bean.setNumRanked(rs.getString((i++)));
                bean.setPercent(rs.getString(i++));
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
    
    public int countNumRanked(Connection cnn, int nktId) throws EException {
        final String LOCATION = this.toString() + "countNumRanked()";
        int result = 0;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "select count(1) FROM (select distinct create_date from kpi_data_rank where nkt_id=?) a";
        
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, nktId);
            rs = prstm.executeQuery();               
            while(rs.next()) {
               result = rs.getInt(1);
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
    
    public FBeans getMultiRecords(Connection cnn, int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "";        
        try {
            sql = SQL_SELECT_RANK;
            prstm = cnn.prepareStatement(sql);            
            rs = prstm.executeQuery();
            String members = ",";
            FRank bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                    start = true;
                    bean = new FRank();
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
                        bean = new FRank();
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
    
    public FRank getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setId(rs.getInt(KPI_RANK_ID));
            rank.setParentID(rs.getInt(KPI_RANK_PARENT_ID));
            rank.setCreateDate(rank.dateToString(rs.getDate(KPI_RANK_CREATE_DATE)));
            rank.setModifyDate(rank.dateToString(rs.getDate(KPI_RANK_MODIFY_DATE)));
            rank.setCode(rs.getString(KPI_RANK_CODE));
            rank.setName(rs.getString(KPI_RANK_NAME));
            rank.setReport(rs.getInt(KPI_RANK_REPORT));
            rank.setTotal(rs.getInt("total"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    }
    
    public FRank getInforList(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setDtlId(rs.getInt("data_id"));
            rank.setId(rs.getInt(KPI_RANK_ID));
            rank.setParentID(rs.getInt(KPI_RANK_PARENT_ID));
            rank.setCreateDate(rank.dateToString(rs.getDate(KPI_RANK_CREATE_DATE)));
            rank.setName(rs.getString(KPI_RANK_NAME));
            rank.setRsInit(rs.getString("result"));
            rank.setRsHasSP(rs.getString("has_sp"));
            rank.setHasRK(rs.getInt("has_rank"));
            rank.setHasRQ(rs.getInt("has_req"));
            rank.setPrevHasSP(rs.getInt("prev_has_rank"));
            rank.setHasDel(rs.getInt("has_del"));
            rank.setRs1St("");
            rank.setRs2Nd("");
            rank.setRs3Rd("");
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    }
    
    public FRank getInforListContinues(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setDtlId(rs.getInt("data_id"));
            rank.setId(rs.getInt(KPI_RANK_ID));
            rank.setParentID(rs.getInt(KPI_RANK_PARENT_ID));
            rank.setCreateDate(rank.dateToString(rs.getDate(KPI_RANK_CREATE_DATE)));
            rank.setName(rs.getString(KPI_RANK_NAME));
            rank.setRsInit(rs.getString("result"));
            rank.setRsHasSP(rs.getString("has_sp"));
            rank.setHasRK(rs.getInt("has_rank"));
            rank.setHasRQ(rs.getInt("has_req"));
            rank.setHasRQ(rs.getInt("has_req"));
            rank.setPrevHasSP(rs.getInt("prev_has_rank"));
            rank.setHasDel(rs.getInt("has_del"));
            rank.setRs1St("");
            rank.setRs2Nd("");
            rank.setRs3Rd("");
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    }
    
    public FRank getInforByR_D(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setId(rs.getInt(KPI_RANK_ID));
            rank.setCreateDate(rank.dateToString(rs.getDate(KPI_RANK_CREATE_DATE)));
            rank.setUserName(rs.getString("fullname"));
            rank.setResult(rs.getInt("result"));
            rank.setHasSP(rs.getInt("has_sp"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    }
    
    public FRank getInforByCreateDate(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setId(rs.getInt("rank_id"));
            rank.setCreateDate(rank.dateToString(rs.getDate("create_date")));
            rank.setResult(rs.getInt("result"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    }
    
    public FRank getInformationById(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FRank rank = new FRank();
        try {
            rank.setId(rs.getInt("id"));
            rank.setParentID(rs.getInt("parent_id"));
            rank.setName(rs.getString("name"));      
            rank.setLevel(rs.getInt("level"));
            rank.setBreadcrumb(rs.getString("breadcrumb"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return rank;
    } 
}
