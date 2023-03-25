package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDataRank;

import com.form.disability.FSupport;

import com.lib.AppConfigs;

import com.util.Formater;
import com.util.Utilities;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Types;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DDataRank extends DSqlDisability {
    
    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FDataRank bean    = (FDataRank)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "SELECT * FROM kpi_data_rank ";        
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn, SQL + ORDER_BY + "create_date", params);
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
                bean = new FDataRank();
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
    
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDataRank bean = (FDataRank)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql_chk_exists = "SELECT * FROM kpi_data_rank WHERE to_char(create_date, 'yyyymm') = ? AND  nkt_id=? AND rank_id=?";
        try {
            pstmt = conn.prepareStatement(sql_chk_exists);
            pstmt.setString(PARAM_01, Utilities.formatDate(bean.getCreateDate(), "dd/MM/yyyy", "yyyyMM"));
            pstmt.setInt(PARAM_02, bean.getNktId());
            pstmt.setInt(PARAM_03, bean.getRankId());
            rs = pstmt.executeQuery();
            result = rs != null && rs.next();
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } catch (ParseException pe) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, pe);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(pstmt);
        }
        return result;
    }

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List params = setParams(seed);
            result = execute(cnn, SQL_INSERT_DATA_RANK, params) > 0;  
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        };
         return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FDataRank bean = (FDataRank)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_DATA_RANK, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FDataRank bean = (FDataRank)seed;
        return 0 < delete(cnn, TABLE_KPI_DATA_RANK, KPI_DATA_RANK_ID + EQUAL + bean.getId());
    }
    
      public FDataRank getDataRank(Connection cnn, int nktId, int rankId) throws EException {
          final String LOCATION = this.toString() + "getRecordByIndicator()";
          PreparedStatement prstm = null;
          ResultSet rs = null;
          FDataRank bean = new FDataRank();
          try {
              String sql = "SELECT sum(a.p0) p0, sum(a.p1) p1, sum(a.p2) p2, sum(a.p3) p3, sum(a.p4) p4 FROM \n" + 
                          " (SELECT dm.id, a.nkt_id, a.create_date, COALESCE(a.p0,0) p0, COALESCE(a.p1,0) p1, COALESCE(a.p2,0) p2, COALESCE(a.p3, 0) p3, COALESCE(a.p4, 0) p4 FROM (\n" + 
                          " SELECT data.nkt_id, data.create_date, data.p0, data.p1, data.p2, data.p3, data.p4, dm.id, dm.parent_id FROM kpi_data_rank data INNER JOIN kpi_rank dm ON data.rank_id = dm.id\n" + 
                          " WHERE 1=1 AND dm.parent_id=? AND data.nkt_id = ? AND data.create_date = (SELECT MIN(create_date) FROM kpi_data_rank WHERE nkt_id=?)) a RIGHT JOIN kpi_rank dm ON a.id=dm.id\n" + 
                          " WHERE dm.parent_id=?) a";
              prstm = cnn.prepareStatement(sql);           
              prstm.setInt(1, rankId);
              prstm.setInt(2, nktId);
              prstm.setInt(3, nktId);
              prstm.setInt(4, rankId);
              rs = prstm.executeQuery();
              if (rs != null && rs.next()) {
                  bean = getInformationRank(rs);
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
      
    public FDataRank getDataRankSP(Connection cnn, int nktId) throws EException {
        final String LOCATION = this.toString() + "getRecordByIndicator()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDataRank bean = new FDataRank();
        try {
            String sql = "SELECT sum(a.p0) p0, sum(a.p1) p1, sum(a.p2) p2, sum(a.p3) p3, sum(a.p4) p4 FROM kpi_data_rank a\n" + 
            "WHERE 1=1 AND a.nkt_id = ? \n" + 
            "AND a.create_date = (SELECT MIN(create_date) FROM kpi_data_rank WHERE nkt_id=?) AND a.has_sp = 1";
            prstm = cnn.prepareStatement(sql);           
            prstm.setInt(1, nktId);
            prstm.setInt(2, nktId);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                bean = getInformationRank(rs);
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
      
      public List<Date> getTimeSupport(Connection cnn, int nktId, int limit, int offset) throws EException {
        final String LOCATION = this.toString() + "getRecordByIndicator()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        List<Date> listDates = new ArrayList<Date>();
        try {
            String sql = "SELECT DISTINCT create_date FROM kpi_data_rank WHERE nkt_id = ? ORDER BY create_date ASC LIMIT ? OFFSET ?";                        
            prstm = cnn.prepareStatement(sql);           
            prstm.setInt(1, nktId);
            prstm.setInt(2, limit);
            prstm.setInt(3, offset);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                Date dateSupport = rs.getDate(1);
                listDates.add(dateSupport);
            }            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return listDates;
      }


      public FDataRank getInformation(ResultSet rs) throws EException {
          final String LOCATION = "->getInformation()";
          FDataRank dataHdr = new FDataRank();
          try {
              dataHdr.setId(rs.getInt("id"));
              dataHdr.setCreateDate(dataHdr.dateToString(rs.getDate("create_date")));
              dataHdr.setModifyDate(dataHdr.dateToString(rs.getDate("modify_date")));
              dataHdr.setUserId(rs.getInt("user_id"));
              dataHdr.setNktId(rs.getInt("nkt_id"));
              dataHdr.setLocationId(rs.getInt("location_id"));
              dataHdr.setRankId(rs.getInt("rank_id"));
              dataHdr.setP0(rs.getInt("p0"));
              dataHdr.setP1(rs.getInt("p1"));
              dataHdr.setP2(rs.getInt("p2"));
              dataHdr.setP3(rs.getInt("p3"));
              dataHdr.setP4(rs.getInt("p4"));
              dataHdr.setHasSP(rs.getInt("has_sp"));
          } catch (SQLException sqle) {
              if (AppConfigs.APP_DEBUG)
                  throw new EException(LOCATION, sqle);
          } finally {
              
          }
          return dataHdr;
      }
    
    public FDataRank getInformationRank(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDataRank dataHdr = new FDataRank();
        try {           
            dataHdr.setP0(rs.getInt("p0"));
            dataHdr.setP1(rs.getInt("p1"));
            dataHdr.setP2(rs.getInt("p2"));
            dataHdr.setP3(rs.getInt("p3"));
            dataHdr.setP4(rs.getInt("p4"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            
        }
        return dataHdr;
    }
    
    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDataRank bean = (FDataRank)seed;
        List params = new ArrayList();
        try {
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getUserId());
            params.add(bean.getNktId());
            params.add(bean.getLocationId());
            params.add(bean.getRankId());
            params.add(bean.getP0());
            params.add(bean.getP1());
            params.add(bean.getP2());
            params.add(bean.getP3());
            params.add(bean.getP4());            
            params.add(bean.getHasRK());
            params.add(bean.getHasRQ());
            params.add(bean.getHasSP());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
            
        }
        return params;
    }
    
    public String getPercent(Connection cnn, int nktId, String createDate, String compareDate) throws EException, SQLException,
                                                        Exception {
        final String LOCATION =  this.toString() + "~~>getPercent()";
        CallableStatement state = null;
        state = cnn.prepareCall("{? = call get_percent_rank(?,?,?)}");
        state.registerOutParameter(1, Types.VARCHAR);
        state.setInt(2, nktId);
        state.setDate(3, Formater.str2sqldate(createDate));
        state.setDate(4, Formater.str2sqldate(compareDate));
        state.execute(); 
       String retval = "";
        try {
            retval = state.getString(1);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            state.close();
        }
        return retval;
    }
    
    public int getNumRanked(Connection cnn, int nktId) throws EException, SQLException {
        final String LOCATION =  this.toString() + "~~>getNumRanked()";
        CallableStatement state = null;
        state = cnn.prepareCall("{? = call get_num_rank(?)}");
        state.registerOutParameter(1, Types.INTEGER);
        state.setInt(2, nktId);
        state.execute(); 
        int retval = 0;
        try {
            retval = state.getInt(1);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            state.close();
        }
        return retval;
    }
}
