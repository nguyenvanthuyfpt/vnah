package com.dao.disability;


import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FThongTinTuyen;
import com.form.disability.categorys.FTinh;

import com.lib.AppConfigs;

import com.util.Formater;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DThongTinTuyen extends DSqlDisability{
   
    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            prstm = prepareStatement(cnn, SQL_INSERT_INTO_TABLE_DR_THONGTIN_TUYEN, params);
            result = prstm.executeUpdate() > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws SQLException, 
                                                             EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FThongTinTuyen bean = (FThongTinTuyen)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_INTO_TABLE_DR_THONGTIN_TUYEN, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }


    public boolean delete(Connection cnn, int id) throws EException {
        return delete(cnn, TABLE_DR_THONGTIN_TUYEN, DR_TT_TUYEN_ID + EQUAL + id) > 0;
    }
    
    public FThongTinTuyen getById(Connection cnn, int id) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs    = null;
        FThongTinTuyen bean      = new FThongTinTuyen();
        FTinh beanTinh  = new FTinh();
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_THONGTIN_TUYEN_BY_ID);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = new FThongTinTuyen();
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
    
    public FThongTinTuyen getByParam(Connection cnn, int id_tinh, int kyBC, int namBC) throws EException{
        final String LOCATION = this.toString() + "getRecordByParams()";
        PreparedStatement prstm = null;
        ResultSet rs    = null;
        FThongTinTuyen bean = new FThongTinTuyen();;
        
        try {            
            List params = new ArrayList();
            params.add(id_tinh);             
            params.add(namBC);
            params.add(kyBC);
            prstm = prepareStatement(cnn, SQL_SELECT_THONGTIN_BY_PARAM, params);            
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = getData(rs);
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
        FBeans beans        = new FBeans();
        FThongTinTuyen bean = (FThongTinTuyen)seed;        
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = SQL_SELECT_THONGTIN_TUYEN;
        try {
            List params = new ArrayList();

            if(bean.getId_tinh()>0){
                params.add(bean.getId_tinh());
                SQL += " AND a.tinh_id = ? ";
            }
            
            if(bean.getKyBC()>0) {
                params.add(bean.getKyBC());
                SQL += " AND a.period = ? ";                
            }
            
            if(bean.getKyBC()> 0 && bean.getNamBC()>0) {
                params.add(bean.getNamBC());
                SQL += " AND a.year = ? ";                
            }
            
            SQL += " ORDER BY a.create_date desc";
            
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

            while (rs != null && rs.next() && (i<AppConfigs.APP_ROWS_VIEW)) {
                i ++ ;
                bean = new FThongTinTuyen();
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


    public FThongTinTuyen getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FThongTinTuyen bean = new FThongTinTuyen();
        try {        
            bean.setId(rs.getInt(DR_TT_TUYEN_ID));
            bean.setId_tinh(rs.getInt(DR_TT_TUYEN_TINH_ID));
            bean.setName_tinh(rs.getString("TINH_NAME"));
            bean.setKyBC(rs.getInt(DR_TT_TUYEN_KY_BC));
            bean.setNamBC(rs.getInt(DR_TT_TUYEN_NAM_BC));            
            bean.setTotalPopulation(Formater.num2str(rs.getInt(DR_TT_TUYEN_TOTAL_DANSO)));
            bean.setTotalFemale(Formater.num2str(rs.getInt(DR_TT_TUYEN_TOTAL_FEMALE)));
            bean.setTotalMale(Formater.num2str(rs.getInt(DR_TT_TUYEN_TOTAL_MALE)));
            bean.setFemaleHasJob(Formater.num2str(rs.getInt(DR_TT_TUYEN_FEMALE_HAS_JOBS)));
            bean.setTotalHasJob(Formater.num2str(rs.getInt(DR_TT_TUYEN_HASJOBS)));
            bean.setFemaleJobLess(Formater.num2str(rs.getInt(DR_TT_TUYEN_FEMALE_JOBSLESS)));
            bean.setTotalJobLess(Formater.num2str(rs.getInt(DR_TT_TUYEN_JOBSLESS)));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
              throw new EException(LOCATION, sqle);
          } finally {
              //closePreparedStatement(prstm);
          }
        return bean;
    }
    
    public FThongTinTuyen getData(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FThongTinTuyen bean = new FThongTinTuyen();
        try {        
            bean.setId(rs.getInt(DR_TT_TUYEN_ID));
            bean.setId_tinh(rs.getInt(DR_TT_TUYEN_TINH_ID));
            bean.setName_tinh(rs.getString("TINH_NAME"));
            bean.setKyBC(rs.getInt(DR_TT_TUYEN_KY_BC));
            bean.setNamBC(rs.getInt(DR_TT_TUYEN_NAM_BC));            
            bean.setTotalPopulation(Formater.num2str(rs.getInt(DR_TT_TUYEN_TOTAL_DANSO)));
            bean.setTotalFemale(Formater.num2str(rs.getInt(DR_TT_TUYEN_TOTAL_FEMALE)));
            bean.setTotalMale(Formater.num2str(rs.getInt(DR_TT_TUYEN_TOTAL_MALE)));
            bean.setFemaleHasJob(Formater.num2str(rs.getInt(DR_TT_TUYEN_FEMALE_HAS_JOBS)));
            bean.setTotalHasJob(Formater.num2str(rs.getInt(DR_TT_TUYEN_HASJOBS)));
            bean.setFemaleJobLess(Formater.num2str(rs.getInt(DR_TT_TUYEN_FEMALE_JOBSLESS)));
            bean.setTotalJobLess(Formater.num2str(rs.getInt(DR_TT_TUYEN_JOBSLESS)));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
              throw new EException(LOCATION, sqle);
          } finally {
              //closePreparedStatement(prstm);
          }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FThongTinTuyen bean = (FThongTinTuyen)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getId_tinh());
            params.add(bean.getKyBC());
            params.add(bean.getNamBC());            
            params.add(Formater.str2num(bean.getTotalPopulation()));
            params.add(Formater.str2num(bean.getTotalMale()));
            params.add(Formater.str2num(bean.getTotalFemale()));
            params.add(Formater.str2num(bean.getFemaleHasJob()));
            params.add(Formater.str2num(bean.getTotalHasJob()));
            params.add(Formater.str2num(bean.getFemaleJobLess()));
            params.add(Formater.str2num(bean.getTotalJobLess()));
            params.add(bean.getCurrentSqlDate());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public boolean checkInsert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "check1to6From6To12()";
        PreparedStatement prstm = null;
        FThongTinTuyen bean = (FThongTinTuyen)seed;
        
        int kyBC    = bean.getKyBC();
        int namBC   = bean.getNamBC();
        int tinh_id = bean.getId_tinh();
        
        ResultSet rs = null;
        boolean result = false;
        try {
            List params = new ArrayList();
            params.add(kyBC); 
            params.add(namBC);
            params.add(tinh_id);            
            prstm = prepareStatement(cnn, SQL_CHECK_INSERT_DR_THONGTIN_TUYEN, params);
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
}
