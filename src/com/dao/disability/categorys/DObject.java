package com.dao.disability.categorys;


import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FObject;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DObject extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FObject bean = (FObject)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_OBJECT_INFORMATION);
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

    public FObject getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FObject bean = new FObject();
        bean = (FObject)seed;
        String SQL = "select obj.*, (select count(1) from kpi_object where parent_id=obj.id) ins, 0 total from kpi_object obj where obj.id=?";
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

    public boolean haveChild(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FObject bean = (FObject)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_OBJECT_HAVECHILD);
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
            result = execute(cnn, SQL_INSERT_OBJECT, params) > 0;
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
            FObject bean = (FObject)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_OBJECT, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FObject bean = (FObject)seed;
        return 0 < delete(cnn, TABLE_KPI_OBJECT, KPI_OBJECT_ID + EQUAL + bean.getId());
    }
    
    public String getSelIndIds(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        String retval = "";
        String SQL = "select ind_id from kpi_obj_ind where year=? and obj_id=?";
        ResultSet rs = null;
        PreparedStatement ps = null;
        FObject bean = (FObject)seed; 
        try {
            ps = cnn.prepareStatement(SQL);
            ps.setInt(1, bean.getYear());
            ps.setInt(2, bean.getId());
            rs = ps.executeQuery();
            while (rs.next()) {
                retval += rs.getString(PARAM_01) + ",";
            }
        } catch (Exception sqle) {
            if (AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);        
        }
        return retval;
    }

    public FBeans getAll(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        FBeans beans    = new FBeans();
        FObject bean    = (FObject)seed;
                
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String SQL = "select obj.*, case when map.total is null then 0 else map.total end from kpi_object obj left join \n" + 
                      "(select obj_id, count(1) total from kpi_obj_ind where year=? group by obj_id) map on obj.id=map.obj_id where 1=1 \n";
        try {
            List params = new ArrayList();
            params.add(bean.getYear());
            if (bean.getParentID()> 0) {
                SQL += " AND (obj.parent_id=? OR obj.id=?) ";
                params.add(bean.getParentID());
                params.add(bean.getParentID());
            }
            
            prstm = prepareStatement(cnn, SQL + ORDER_BY + " obj.id, obj.parent_id", params);
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
                bean = new FObject();
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
    
    public FObject getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FObject object = new FObject();
        try {
            object.setId(rs.getInt(KPI_OBJECT_ID));
            object.setParentID(rs.getInt(KPI_OBJECT_PARENT_ID));
            object.setCreateDate(object.dateToString(rs.getDate(KPI_OBJECT_CREATE_DATE)));
            object.setModifyDate(object.dateToString(rs.getDate(KPI_OBJECT_MODIFY_DATE)));
            object.setCode(rs.getString(KPI_OBJECT_CODE));
            object.setName(rs.getString(KPI_OBJECT_NAME));
            object.setDescription(rs.getString(KPI_OBJECT_DESCRIPTION));
            object.setType(rs.getInt(KPI_OBJECT_TYPE));
            //object.setIns(rs.getInt("ins"));
            object.setTotalIndicator(rs.getInt("total"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return object;
    }
    
    public FObject getInformationByLevel(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FObject object = new FObject();
        try {
            object.setId(rs.getInt("id"));
            object.setParentID(rs.getInt("parent_id"));            
            object.setCode(rs.getString("code"));
            object.setName(rs.getString("name"));
            object.setLevel(rs.getInt("level"));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return object;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FObject bean = (FObject)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getParentID()); 
            params.add(bean.stringToSqlDate(bean.getCreateDate()));
            params.add(bean.stringToSqlDate(bean.getModifyDate()));
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getDescription());
            params.add(bean.getType());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }
    
    public FBeans getObjectsByParent(Connection cnn, int parentId) throws EException {
        final String LOCATION = this.toString() + "getObjectsByParent()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FObject object = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_OBJECT_BY_PARENT);
            prstm.setInt(1, parentId);
            rs = prstm.executeQuery();
            FObject bean = null;          
            while (rs != null && rs.next()) {
                  bean = getInformation(rs);
                  beans.add(bean);
            }          
            beans.setFirstRecord(1);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FBeans getObjectsByLevel(Connection cnn, int level) throws EException {
        final String LOCATION = this.toString() + "getObjectsByLevel()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FObject object = null;
        String sql = "select * from kpi_v_object where level>=? order by id, parent_id";
        try {
            prstm = cnn.prepareStatement(sql);
            prstm.setInt(1, level);
            rs = prstm.executeQuery();
            FObject bean = null;          
            while (rs != null && rs.next()) {
                  bean = getInformationByLevel(rs);
                  beans.add(bean);
            }          
            beans.setFirstRecord(1);
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
    
    public FBeans getMultiRecords(Connection cnn, int idDepartment, int level) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String sql = "";        
        try {
            if (level>0)  sql = SQL_SELECT_OBJECT_LEVEL;
            else          sql = SQL_SELECT_OBJECT;
            prstm = cnn.prepareStatement(sql);            
            rs = prstm.executeQuery();
            String members = ",";
            FObject bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || (!start && id == idDepartment)) {
                    start = true;
                    bean = new FObject();
                    bean.setId(id);
                    bean.setCode(rs.getString(PARAM_02));
                    bean.setName(rs.getString(PARAM_03));
                    bean.setParentID(rs.getInt(PARAM_04));
                    bean.setType(rs.getInt(PARAM_05));
                    if (id > 0) {
                        members += id + ",";
                        beans.add(bean);
                    }
                }
                if (start) {
                    if (all || members.indexOf("," + id + ",") >= 0) {
                        id = rs.getInt(PARAM_06);
                        bean = new FObject();
                        bean.setId(id);
                        bean.setCode(rs.getString(PARAM_07));
                        bean.setName(rs.getString(PARAM_08));
                        bean.setParentID(rs.getInt(PARAM_09));
                        bean.setType(rs.getInt(PARAM_10));
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
