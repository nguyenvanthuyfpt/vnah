package com.dao.disability;


import com.dao.disability.categorys.DDantoc;
import com.dao.disability.categorys.DDieuKien;
import com.dao.disability.categorys.DDonvi;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDisability;
import com.form.disability.FPhanLoai;
import com.form.disability.categorys.FDantoc;
import com.form.disability.categorys.FDieuKien;
import com.form.disability.categorys.FDonvi;

import com.lib.AppConfigs;

import com.util.Formater;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Types;

import java.util.ArrayList;
import java.util.List;


public class DDisability extends DSqlDisability {
    
    public FBeans getCountdownDis(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getCountdownDis()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisability bean = (FDisability)seed;
        String SQL_SELECT = "SELECT a.id_nkt, b.maso, b.ten, b.sex, b.ngaysinh, a.day, a.thoidiem_taikham, a.diadiem_kham, area.name, sp.datecreate as lastdate_support\n" + 
                            "FROM  \n" + 
                            " ( \n" + 
                            "SELECT id_nkt, thoidiem_taikham, diadiem_kham, DATE_PART('day', (thoidiem_taikham:: DATE)- NOW())+1 AS DAY \n" + 
                            "FROM dr_phanloai \n" + 
                            "WHERE thoidiem_taikham IS NOT NULL AND thoidiem_taikham BETWEEN fn_firstdate_of_month(to_char(CURRENT_DATE, 'mm/yyyy')) AND fn_lastdate_of_month(to_char(CURRENT_DATE, 'mm/yyyy')) \n" + 
                            ") a INNER JOIN dr_disabilitypeople b ON a.id_nkt=b.id \n" + 
                            "    LEFT JOIN dr_area AREA ON b.id_tinh=area.tinh_id\n" + 
                            "    INNER JOIN (select id_nkt, max(datecreate) datecreate from kpi_v_support where status_id = 1 AND dm_hotro_ids IN ('12','51','129') GROUP BY id_nkt ) sp ON a.id_nkt=sp.id_nkt\n" + 
                            "WHERE 1=1\n" + 
                            "AND ((0 = ?) OR (b.id_tinh=?)) \n" + 
                            "ORDER BY name, DAY, maso\n";
        try {
            List params = new ArrayList();            
            params.add(bean.getTinhId());
            params.add(bean.getTinhId());
            prstm = prepareStatement(cnn, SQL_SELECT, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
            int i = 0;
            while (rs != null && rs.next()) {
                i++;
                bean = new FDisability();
                bean.setId(rs.getInt("id_nkt"));                
                bean.setNkt(rs.getString("ten"));
                bean.setMa(rs.getString("maso"));
                bean.setNgaySinh(bean.dateToString(rs.getDate("ngaysinh")));
                bean.setGioiTinh(rs.getInt("sex")==0?bean.ncrToString("N&#7919;"):"Nam");
                bean.setTinhName(rs.getString("name"));
                bean.setTkThoiDiem(bean.dateToString(rs.getDate("thoidiem_taikham")));
                bean.setTkDiaDiem(rs.getInt("diadiem_kham"));
                bean.setDay(rs.getInt("day"));
                bean.setLastDateSupport(bean.dateToString(rs.getDate("lastdate_support")));
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

    public FBeans getAllSearch(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAllSearch()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisability bean = (FDisability)seed;
        String SQL_SELECT = SELECT_ALL_FROM_TABLE_DISABILITY + WHERE + TRUE;
        try {
            List params = new ArrayList();
            if (bean.getSearchSub() != null && 
                !bean.getSearchSub().equals("")) {
                params.add(PER_CENT + bean.getSearchSub().toUpperCase() + PER_CENT);
                SQL_SELECT += AND + " UPPER(" + NKT_TEN + ")" + LIKE + QUESTION;
            }
            if (bean.getTinhId() > 0) {
                params.add(bean.getTinhId());
                SQL_SELECT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID_TINH + EQUAL + QUESTION;
            }
            SQL_SELECT += AND + TABLE_DISABILITYPEOPLE + STOP + NKT_ID + DIFF + QUESTION;
            params.add(bean.getId());         
            prstm = prepareStatement(cnn, SQL_SELECT, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
            int i = 0;
            while (rs != null && rs.next()) {
                i++;
                bean = new FDisability();
                bean.setId(rs.getInt(NKT_ID));
                bean.setMa(rs.getString(NKT_MA));
                bean.setNkt(rs.getString(NKT_TEN));
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
        FDisability bean = (FDisability)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_DISABILITY_INFORMATION);
            pstmt.setString(PARAM_01, bean.getMa());
            pstmt.setInt(PARAM_02, bean.getTrangthai());
            pstmt.setInt(PARAM_03, bean.getId());
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

    public boolean isExistName(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDisability bean = (FDisability)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_DISABILITY_BY_NAME);
            pstmt.setString(PARAM_01, bean.getNkt());
            pstmt.setString(PARAM_02, bean.getSoNha());
            pstmt.setInt(PARAM_03, bean.getTinhId());
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

    public boolean isExistSoNha(Connection conn, 
                                FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FDisability bean = (FDisability)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_DISABILITY_BY_SONHA);
            pstmt.setString(PARAM_01, bean.getSoNha());
            pstmt.setInt(PARAM_02, bean.getTinhId());
            pstmt.setInt(PARAM_03, bean.getId());
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

    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisability bean = (FDisability)seed;
        try {
            List params = new ArrayList();
            prstm = prepareStatement(cnn, SELECT_ALL_FROM_TABLE_DISABILITY, params);
            rs = prstm.executeQuery();
            beans = new FBeans();
            beans.setTotalRows(count(cnn, SELECT_ALL_FROM_TABLE_DISABILITY, params));
            beans.setPageIndex(bean.getPageIndex());
            if (beans.getFirstRecord() <= 1) {
                rs.beforeFirst();
            } else {
                rs.absolute(beans.getFirstRecord() - 1);
            }
            int i = 0;
            while (rs != null && rs.next() && i < AppConfigs.APP_ROWS_VIEW) {
                i++;
                bean = new FDisability();
                bean = getInformation(rs, true);
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

    public FBeans getDisKpi(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FDisability bean    = (FDisability)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select dis.id, dis.date_last_update, dis.id_tinh, area.name name_tinh, dis.ma, dis.ten, dis.sex, dis.ngaysinh, " +
                     "dis.dantoc_id, dis.sonha, dis.ten_ncs, dis.quanhe_ncs, dis.namsinh_ncs, dis.quanhe_ncs, dis.gioitinh_ncs, dis.trangthai \n" + 
                     "from dr_disabilitypeople dis left join dr_area area on dis.id_tinh=area.tinh_id where 1=1 \n";
        try {          
            List params = new ArrayList();        
            params.add(bean.getTinhId());
            params.add(bean.getTinhId());
            params.add(bean.getTinhId());          
            SQL += "and ((?<>0 AND dis.id_tinh=?) OR (?=0 AND 1=1)) ";
            SQL += "order by dis.id ";
            
            prstm = prepareStatement(cnn, SQL, params);          
            rs = prstm.executeQuery();
            beans = new FBeans();            
            beans.setTotalRows(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            
            if (beans.getFirstRecord() <= 1) {
                rs.beforeFirst();
            } else {
                rs.absolute(beans.getFirstRecord() - 1);
            }
            
            int i = 0;  
            while (rs != null && rs.next() && (i<AppConfigs.APP_ROWS_VIEW)) {              
                i ++ ;
                bean = new FDisability();
                bean = getInformationKpi(rs);
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
    
    public FBeans getDisKpiAc(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FDisability bean    = (FDisability)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select dis.id, dis.date_last_update, dis.id_tinh, area.name name_tinh, dis.ma, dis.ten, dis.sex, dis.ngaysinh, " +
                     "dis.dantoc_id, dis.sonha, dis.ten_ncs, dis.quanhe_ncs, dis.namsinh_ncs, dis.quanhe_ncs, dis.gioitinh_ncs, dis.trangthai \n" + 
                     "from dr_disabilitypeople dis left join dr_area area on dis.id_tinh=area.tinh_id where 1=1 \n";
        try {          
            List params = new ArrayList();        
            params.add(bean.getTinhId());
            params.add(bean.getTinhId());
            params.add(bean.getTinhId());          
            SQL += "and ((?<>0 AND dis.id_tinh=?) OR (?=0 AND 1=1))";
            
            prstm = prepareStatement(cnn, SQL, params);          
            rs = prstm.executeQuery();
            beans = new FBeans();
            
            beans.setTotalRows(count(cnn, SQL, params));
            bean.setTotalResult(count(cnn, SQL, params));
            beans.setPageIndex(bean.getPageIndex());
            
            int i = 0;  
            while (rs != null && rs.next()) {
                i ++ ;
                bean = new FDisability();
                bean = getInformationKpi(rs);
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
    
    public int countDisKpi(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        int retval = 0;
        FDisability bean    = (FDisability)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select count(1) " +                      
                     "from dr_disabilitypeople dis left join dr_area area on dis.id_tinh=area.tinh_id where 1=1 \n";
        try {          
            List params = new ArrayList();        
            params.add(bean.getTinhId());
            params.add(bean.getTinhId());
            params.add(bean.getTinhId());          
            SQL += "and ((?<>0 AND dis.id_tinh=?) OR (?=0 AND 1=1))";
            
            prstm = prepareStatement(cnn, SQL, params);          
            rs = prstm.executeQuery();
            if (rs.next()) {
                retval = rs.getInt(1);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return retval;
    }
    
    public FDisability getRecordByIDHuongLoi(Connection cnn, 
                                             FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        PreparedStatement prstm1 = null;
        ResultSet rs = null;
        ResultSet rs1 = null;
        FDisability bean = (FDisability)seed;
        FPhanLoai beanDT = new FPhanLoai();
        DPhanLoai daoDT = new DPhanLoai();
        FDonvi beanDV = new FDonvi();
        DDonvi daoDV = new DDonvi();
        FBeans beans = new FBeans();

        try {
            prstm = cnn.prepareStatement(SELECT_GET_BY_ID_FROM_TABLE_DISABILITY);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = new FDisability();
                bean = getInformation(rs, true);
                //            if(bean.getLaNKT()==0){
                //                beans=daoDT.getAllByIdNkt(cnn,bean.getId());
                //                if(beans.size()>0) beanDT=(FPhanLoai)beans.get(0);
                //            
                //                if(beanDT.getNguyenNhanId()>0){
                //                    beanDV.setId(beanDT.getNguyenNhanId());
                //                    beanDV=daoDV.getRecordByID(cnn,beanDV);
                //                    bean.setNguyenNhanKT(beanDV.getName());
                //                }
                //                bean.setThoiDiemKT(beanDT.getDateCreate());
                //                if((beanDT.getDangTatIds()!=null)&&(!beanDT.getDangTatIds().equals(""))){
                //                
                //                String[] temp=beanDT.getDangTatIds().substring(1,beanDT.getDangTatIds().length()-1).split("#");
                //                    bean.setDangTat("");
                //                    for (int j=0;j<temp.length;j++) {
                //                        FDangTat beanDangT = new FDangTat();
                //                        DDangTat daoDangT = new DDangTat();
                //                        beanDangT.setId(bean.stringToInt(temp[j]));
                //                        beanDangT=daoDangT.getRecordByID(cnn,beanDangT);
                //                        bean.setDangTat(bean.getDangTat()+beanDangT.getName()+",");
                //                    }
                //                    bean.setDangTat(bean.getDangTat().substring(0,bean.getDangTat().length()-1));
                //                }
                //            }
                //            if(bean.getLaChamSoc()==0){
                //                FDisability beanFDisability = new FDisability();
                //                beanFDisability.setId(bean.getId());
                //                beanFDisability=getRecordByIDLANHL(cnn,beanFDisability);
                //                bean.setMa(beanFDisability.getMa());
                //                bean.setNkt(beanFDisability.getNkt());
                //                bean.setQuanHe(beanFDisability.getQuanHe());
                //            }

                if (bean.getDantocId() > 0) {
                    FDantoc beanDantoc = new FDantoc();
                    DDantoc daoDantoc = new DDantoc();
                    beanDantoc.setId(bean.getDantocId());
                    beanDantoc = daoDantoc.getRecordByID(cnn, beanDantoc);
                    bean.setDantocName(beanDantoc.getName());
                }
                if (bean.getDieuKienId() > 0) {
                    FDieuKien beanDieuKien = new FDieuKien();
                    DDieuKien daoDieuKien = new DDieuKien();
                    beanDieuKien.setId(bean.getDieuKienId());
                    beanDieuKien = daoDieuKien.getRecordByID(cnn, beanDieuKien);
                    bean.setDieuKienName(beanDieuKien.getName());
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

    public FDisability getRecordByIDLANHL(Connection cnn, 
                                          FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByIDLANHL()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisability bean = (FDisability)seed;
        String SQL_NHL = 
            "select c.name,d.ma,d.ten from dr_disabilitypeople a\n" + 
            "left join dr_relative b on a.id=b.id_nkt\n" + 
            "left join dr_lydo c on b.id_lydo=c.lydo_id\n" + 
            "left join dr_disabilitypeople d on b.id_relative_nkt=d.id\n" + 
            "where a.id=?";

        try {
            prstm = cnn.prepareStatement(SQL_NHL);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = new FDisability();
                bean.setQuanHe(rs.getString(PARAM_01));
                bean.setMa(rs.getString(PARAM_02));
                bean.setNkt(rs.getString(PARAM_03));
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

    public FDisability getRecordByID(Connection cnn, 
                                     FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisability bean = (FDisability)seed;
        try {
            prstm = cnn.prepareStatement(SELECT_GET_BY_ID_FROM_TABLE_DISABILITY);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {            
                bean = getInformation(rs, true);
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


    public FDisability getRecordMaxId(Connection cnn, 
                                      FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordMaxId()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisability bean = (FDisability)seed;
        try {
            prstm = cnn.prepareStatement(SELECT_GET_BY_ID_FROM_TABLE_DISABILITY_MAX_ID);
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = new FDisability();
                bean = getInformation(rs, true);
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


    public FDisability getRecordByCode(Connection cnn, 
                                       FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByCode()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FDisability bean = (FDisability)seed;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_DISABILITY_BY_CODE);
            prstm.setString(PARAM_01, bean.getMa());
            rs = prstm.executeQuery();
            if ((rs != null) && (rs.next())) {
                bean = new FDisability();
                bean = getInformation(rs, true);
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

    public boolean insert(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            List params = setParams(seed);
            prstm = prepareStatement(cnn, SQL_INSERT_INTO_TABLE_DISABILITY, params);
            result = prstm.executeUpdate() > 0;
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public int insertKpi(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + INSERT;
        int result = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            List params = setParamsKpi(seed);
            ps = prepareStatement(cnn, SQL_INSERT_INTO_TABLE_DISABILITY_KPI, params);
            rs = ps.executeQuery();
            if (rs!=null && rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(ps);
            closeResultSet(rs);
        }
        return result;
    }

    public boolean update(Connection cnn, FSeed seed) throws SQLException, 
                                                             EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FDisability bean = (FDisability)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_UPDATE_INTO_TABLE_DISABILITY, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public boolean updateStatus(Connection cnn, FSeed seed) throws SQLException, EException {
        final String LOCATION = this.toString() + "change_status";
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FDisability bean = (FDisability)seed;
            List params = new ArrayList();
            params.add(bean.getTrangthai());
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_APPROVE_STATUS_NKT, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public boolean approve(Connection cnn, FSeed seed) throws SQLException, EException {
        final String LOCATION = this.toString() + "change_status";
        boolean result = false;
        PreparedStatement prstm = null;
        try {
            FDisability bean = (FDisability)seed;
            List params = setParams(seed);
            params.add(bean.getTrangthai());
            params.add(bean.getId());
            prstm = prepareStatement(cnn, SQL_APPROVE_STATUS_NKT, params);
            result = prstm.executeUpdate() > 0;
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closePreparedStatement(prstm);
        }
        return result;
    }
    
    public boolean delete(Connection cnn, int id_nkt) throws EException {
        final String LOCATION = this.toString() + DELETE;
        return 0 < delete(cnn, TABLE_DISABILITYPEOPLE, NKT_ID + EQUAL + id_nkt);
    }
    
    public FDisability getInformationKpi(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FDisability bean = new FDisability();
        try {
            
            bean.setId(rs.getInt(NKT_ID));
            bean.setDateLastUpdate(bean.dateToString(rs.getDate(NKT_DATE_LAST_UPDATE)));
            bean.setTinhId(rs.getInt(NKT_ID_TINH));
            bean.setTinhName(rs.getString("name_tinh"));
            bean.setMa(rs.getString(NKT_MA));
            bean.setNkt(rs.getString(NKT_TEN));
            bean.setSex(rs.getInt(NKT_SEX));
            bean.setGioiTinh(rs.getInt(NKT_SEX)==1?"Nam":"N&#7919;");
            Date dateBirthDay = rs.getDate(NKT_NGAYSINH);
            int dinhdang_ns = 3;
            dinhdang_ns = bean.getDinhDang_NS()==0?3:bean.getDinhDang_NS();
            if (dateBirthDay != null)
                bean.setNgaySinh(bean.dateToString(dateBirthDay, AppConfigs.DATE_TYPES[dinhdang_ns - 1]));
            bean.setDantocId(rs.getInt(NKT_DANTOC_ID));
            bean.setSoNha(rs.getString(NKT_SONHA));
            bean.setTenChamSoc(rs.getString(NKT_TEN_NCS));
            bean.setNamSinhChamSoc(rs.getInt(NKT_NAMSINH_NCS));
            bean.setQuanHeChamSoc(rs.getInt(NKT_QUANHE_NCS));
            bean.setGioiTinhChamSoc(rs.getInt(NKT_GIOITINH_NCS));
            bean.setTrangthai(rs.getInt(NKT_TRANGTHAI));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public FDisability getInformation(ResultSet rs, boolean full) throws EException {
        final String LOCATION = "->getInformation()";
        FDisability bean = new FDisability();
        try {
            bean.setId(rs.getInt(NKT_ID));
            bean.setMa(rs.getString(NKT_MA));
            bean.setMa_nkt(rs.getString(NKT_MASO));
            bean.setNkt(rs.getString(NKT_TEN));
            bean.setCmnd(rs.getString(NKT_CMND));
            bean.setDinhDang_NS(rs.getInt(NKT_DINHDANG_NS));
            Date dateBirthDay = rs.getDate(NKT_NGAYSINH);
            int dinhdang_ns = 3;
            dinhdang_ns = bean.getDinhDang_NS()==0?3:bean.getDinhDang_NS();
            if (dateBirthDay != null)
                bean.setNgaySinh(bean.dateToString(dateBirthDay, AppConfigs.DATE_TYPES[dinhdang_ns - 1]));
            bean.setSex(rs.getInt(NKT_SEX));
            bean.setSoNha(rs.getString(NKT_SONHA));
            bean.setThonTo(rs.getString(NKT_THONTO));
            bean.setTinhId(rs.getInt(NKT_ID_TINH));
            bean.setDistrictId(rs.getInt("id_district"));
            bean.setCommuneId(rs.getInt("id_commune"));            
            
            bean.setChuanDoan(rs.getString(NKT_CHUANDOAN));
            bean.setDieuKienId(rs.getInt(NKT_ID_DIEUKIEN));

            bean.setPhoneNumber(rs.getString(NKT_DIENTHOAI));
            bean.setTrinhDoId(rs.getInt(NKT_TRINHDO_ID));
            bean.setVisitEmpId(rs.getInt(NKT_VISIT_EMP_ID));

            bean.setFileRead(rs.getString(NKT_FILE_READ));
            bean.setFileStore(rs.getString(NKT_FILE_STORE));
            bean.setDateLastUpdate(bean.dateToString(rs.getDate(NKT_DATE_LAST_UPDATE)));

            bean.setTtHonNhanId(rs.getInt(NKT_TT_HONNHAN));
            bean.setTdChuyenMon(rs.getString(NKT_TD_CHUYENMON));
            bean.setNgheNghiepHT(rs.getInt(NKT_NGHE_NGHIEP_HT));
            bean.setChucVuHT(rs.getString(NKT_CHUC_VU_HT));
            bean.setDantocId(rs.getInt(NKT_DANTOC_ID));
            bean.setDantocName(rs.getString("dantoc_name"));
            
            bean.setNguoiQuanLyTen(rs.getString(NKT_TEN_NQL));
            bean.setNguoiQuanLyDonVi(rs.getString(NKT_DONVI_NQL));
            bean.setNguoiQuanLyLinhVuc(rs.getInt(NKT_LINHVUC_NQL));

            bean.setTongSoCon(rs.getInt(NKT_TONGSOCON));
            bean.setTongSoConDuoi16(rs.getInt(NKT_CONDUOI16));
            bean.setChatDocDaCam(rs.getInt(NKT_DACAM));
            bean.setTenChuHo(rs.getString(NKT_TEN_CHUHO));
            bean.setNamSinhChuHo(rs.getInt(NKT_NAMSINH_CHUHO));

            bean.setQuanHeChuHo(rs.getInt(NKT_QUANHE_CHUHO));
            bean.setSonguoi_chuho(rs.getInt(NKT_SONGUOI_CHUHO));
            bean.setSoNKT_chuho(rs.getInt(NKT_NKT_CHUHO));
            bean.setNguonNuocId(rs.getInt(NKT_NGUONNUOC_CHUHO));
            bean.setNhaVeSinhChuHo(rs.getInt(NKT_NHAVS_CHUHO));
            bean.setNhaVeSinhNKTChuHo(rs.getInt(NKT_NHAVS_NKT_CHUHO));
            bean.setNhaOId(rs.getInt(NKT_NHAO_CHUHO));
            
            bean.setTenChamSoc(rs.getString(NKT_TEN_NCS));
            bean.setNamSinhChamSoc(rs.getInt(NKT_NAMSINH_NCS));
            bean.setQuanHeChamSoc(rs.getInt(NKT_QUANHE_NCS));
            bean.setGioiTinhChamSoc(rs.getInt(NKT_GIOITINH_NCS));
            
            // Add By ThuyNV
            bean.setSdtLienLac(rs.getString(NKT_SDT_NCS));
            bean.setChuyenMonKhac(rs.getString(NKT_CHUYENMON_KHAC));
            bean.setNgheNghiepKhac(rs.getString(NKT_NGHENGHIEP_KHAC));
            bean.setHonNhanKhac(rs.getString(NKT_HONNHAN_KHAC));
            bean.setGiaoDucKhac(rs.getString(NKT_GIAODUC_KHAC));

            bean.setTroCapId(rs.getInt(NKT_ID_TROCAP));
            bean.setTroCapKhac(rs.getString(NKT_TROCAP_KHAC));
            bean.setTrangthai(rs.getInt(NKT_TRANGTHAI));
            bean.setKhangChien(rs.getInt(NKT_KHANGCHIEN));
            bean.setDoiTuongId(rs.getInt(NKT_DOITUONG));
            bean.setDuAnId(rs.getInt(NKT_DUAN));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return bean;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDisability bean = (FDisability)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getMa());
            params.add(bean.getMa_nkt());
            params.add(bean.getNkt());
            params.add(bean.getCmnd());
            params.add(bean.getSex());
            Date dateBirthday = bean.stringToSqlDate(bean.getNgaySinh());            
            params.add(dateBirthday==null?null:dateBirthday);
            params.add(bean.getSoNha());
            params.add(bean.getTinhId());
            params.add(bean.getDistrictId());
            params.add(bean.getCommuneId());
            params.add(bean.getDieuKienId());
            params.add(bean.getChuanDoan());
            params.add(bean.getPhoneNumber());
            params.add(bean.getThonTo());
            params.add(bean.getTrinhDoId());
            params.add(bean.getVisitEmpId());
            params.add(bean.getFileStore());
            params.add(bean.getFileRead());
            params.add(3);
            params.add(bean.stringToSqlDate(bean.getDateLastUpdate()));
            params.add(bean.getTtHonNhanId());
            params.add(bean.getTdChuyenMon());
            params.add(bean.getNgheNghiepHT());
            params.add(bean.getChucVuHT());
            params.add(bean.getDantocId());

            params.add(bean.getNguoiQuanLyTen());
            params.add(bean.getNguoiQuanLyDonVi());
            params.add(bean.getNguoiQuanLyLinhVuc());
            params.add(bean.getTongSoCon());
            params.add(bean.getTongSoConDuoi16());
            params.add(bean.getChatDocDaCam());

            params.add(bean.getTenChuHo());
            params.add(bean.getNamSinhChuHo());
            params.add(bean.getQuanHeChuHo());
            params.add(bean.getSonguoi_chuho());
            params.add(bean.getSoNKT_chuho());
            params.add(bean.getNguonNuocId());
            params.add(bean.getNhaVeSinhChuHo());
            params.add(bean.getNhaVeSinhNKTChuHo());
            params.add(bean.getNhaOId());

            params.add(bean.getTenChamSoc());
            params.add(bean.getNamSinhChamSoc());
            params.add(bean.getQuanHeChamSoc());

            // Add By ThuyNV
            params.add(bean.getSdtLienLac());
            params.add(bean.getChuyenMonKhac());
            params.add(bean.getNgheNghiepKhac());
            params.add(bean.getHonNhanKhac());
            params.add(bean.getGiaoDucKhac());

            params.add(bean.getTroCapId());
            params.add(bean.getTroCapKhac());
            //params.add(bean.getTrangthai());
            params.add(bean.getKhangChien());
            params.add(bean.getDoiTuongId());
            params.add(bean.getGioiTinhChamSoc());
            params.add(bean.getDuAnId());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public List setParamsStatus(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDisability bean = (FDisability)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getTrangthai());
            params.add(bean.getId());
        } catch (Exception exp) {
          if (AppConfigs.APP_DEBUG)
              throw new EException(LOCATION, exp);
        } finally {
        
        }
        return params;
    }
    public List setParamsKpi(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FDisability bean = (FDisability)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getTinhId());
            params.add(bean.getDistrictId());
            params.add(bean.getCommuneId());
            params.add(bean.getMa());            
            params.add(bean.stringToSqlDate(bean.getDateLastUpdate()));
            
            params.add(bean.getNkt());
            params.add(bean.getCmnd());
            params.add(bean.getPhoneNumber());            
            Date dateBirthday = bean.stringToSqlDate(bean.getNgaySinh());            
            params.add(dateBirthday==null?null:dateBirthday);
            params.add(bean.getSex());
            
            params.add(bean.getSoNha());
            params.add(bean.getDantocId());
            params.add(bean.getNgheNghiepHT());
            params.add(bean.getChatDocDaCam());            
            params.add(bean.getTenChamSoc());
            
            params.add(bean.getNamSinhChamSoc());
            params.add(bean.getQuanHeChamSoc());
            params.add(bean.getSdtLienLac());
            params.add(bean.getGioiTinhChamSoc());
            params.add(bean.getDuAnId());
      } catch (Exception exp) {
          if (AppConfigs.APP_DEBUG)
              throw new EException(LOCATION, exp);
      } finally {
      }
      return params;
  }

    public String getDangTat(Connection cnn, int id_nkt, int id_phanloai) throws EException {
        final String LOCATION = this.toString() + "getStringAdd()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String result = "";
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_DANGTAT_STRIMG_BY_PHANLOAI_ID);
            prstm.setInt(PARAM_01, id_nkt);
            prstm.setInt(PARAM_02,id_phanloai);
            
            rs = prstm.executeQuery();            
            while (rs != null && rs.next()) {
                if (!result.equals("")) result += ",";
                
                if(rs.getInt(PARAM_02)==206){
                    result += getKhuyetTatKhac(cnn,id_nkt,id_phanloai);
                } else if (rs.getInt(PARAM_02)==188){
                    result += getVanDongKhac(cnn,id_nkt);
                } else {
                    result += rs.getString(PARAM_01);    
                }                
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
    
    public String getDangTat(Connection cnn, int nktId) throws EException, SQLException {
        final String LOCATION =  this.toString() + "~~>getDangTat()";
        CallableStatement state = null;
        state = cnn.prepareCall("{? = call get_dangtat(?)}");
        state.registerOutParameter(1, Types.VARCHAR);
        state.setInt(2, nktId);
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

    public boolean hasKhuyetTatKhac(Connection cnn, 
                                    int id_nkt) throws EException {
        final String LOCATION = "->isExist()";
        PreparedStatement prstm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_KHUYETTAT_KHAC_STRING);
            prstm.setInt(PARAM_01, id_nkt);
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

    public String getKhuyetTatKhac(Connection cnn, 
                                   int id_nkt,int id_phanloai) throws EException {
        final String LOCATION = "->isExist()";
        PreparedStatement prstm = null;
        String result = "";
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_KHUYETTAT_KHAC_STRING_BY_PHANLOAI_ID);
            prstm.setInt(PARAM_01, id_nkt);
            prstm.setInt(PARAM_02, id_phanloai);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                if (!result.equals(""))
                    result += ",";
                result += rs.getString(PARAM_01);
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


    public String getNguyenNhan(Connection cnn, int id_nkt, int id_phanloai) throws EException {
        final String LOCATION = this.toString() + "getStringAdd()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String resutl = "";
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_NGUYENNHAN_STRING_BY_PHANLOAI_ID);
            prstm.setInt(PARAM_01, id_nkt);
            prstm.setInt(PARAM_02, id_phanloai);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                if (!resutl.equals("")) resutl += ",";
                
                resutl += rs.getString(PARAM_01);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return resutl;
    }
    
    public boolean hasNguyenNhanKhac(Connection cnn, int id_nkt) throws EException {
        final String LOCATION = "->isExist()";
        PreparedStatement prstm = null;
        boolean result = false;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_NGUYENNHAN_KHAC_STRING);
            prstm.setInt(PARAM_01, id_nkt);
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
    
    public String getNguyenNhanKhac(Connection cnn, int id_nkt,int id_phanloai) throws EException {
        final String LOCATION = "->getString()";
        PreparedStatement prstm = null;
        String result = "";
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_NGUYENNHAN_KHAC_STRING_BY_PHANLOAI_ID);
            prstm.setInt(PARAM_01, id_nkt);
            prstm.setInt(PARAM_02, id_phanloai);
            rs = prstm.executeQuery();
            
            while (rs != null && rs.next()) {
                if (!result.equals(""))
                    result += ",";
                result += rs.getString(PARAM_01);
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
    
    public String getVanDongKhac(Connection cnn, int id_nkt) throws EException {
        final String LOCATION = "->getString()";
        PreparedStatement prstm = null;
        String result = "";
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_VANDONG_KHAC_STRING);
            prstm.setInt(PARAM_01, id_nkt);
            rs = prstm.executeQuery();
            while (rs != null && rs.next()) {
                if (!result.equals(""))
                    result += ",";
                result += rs.getString(PARAM_01);
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