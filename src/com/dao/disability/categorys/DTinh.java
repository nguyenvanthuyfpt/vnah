package com.dao.disability.categorys;


import com.dao.connection.DBConnector;
import com.dao.disability.DSqlDisability;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.categorys.FTinh;

import com.lib.AppConfigs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;


public class DTinh extends DSqlDisability {
    public boolean isExist(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FTinh bean = (FTinh)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_TINH_INFORMATION);
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

    public FTinh getRecordByID(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getRecordByID()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FTinh bean = new FTinh();
        bean = (FTinh)seed;

        FTinh beanTemp = new FTinh();
        try {
            if(cnn!=null){
                prstm = cnn.prepareStatement(SQL_SELECT_TINH_BY_ID);
            }else{
                cnn = DBConnector.getConnection();
                prstm = cnn.prepareStatement(SQL_SELECT_TINH_BY_ID);
            } 
            
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                beanTemp = getInformation(rs);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {            
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beanTemp;
    }

    public FBeans getTinhByParentId(Connection cnn, int parentId) throws EException {
        final String LOCATION = this.toString() + "getTreeMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_TINH_BY_PARENT_ID);
            prstm.setInt(PARAM_01, parentId);
            rs = prstm.executeQuery();
            FTinh bean = null;
            while ((rs != null) && (rs.next())) {
                bean = new FTinh();
                int id = rs.getInt(PARAM_01);
                bean.setId(id);
                bean.setParentID(rs.getInt(PARAM_02));
                bean.setName(id==0?bean.ncrToString(rs.getString(PARAM_03)):rs.getString(PARAM_03));
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
    
    public String[] getTinhIdByParentId(Connection cnn, int parentId) throws EException {
        final String LOCATION = this.toString() + "getTreeMultiRecords()";
        String[] arrTinhId = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_TINH_ID_BY_PARENT_ID);
            prstm.setInt(PARAM_01, parentId);
            rs = prstm.executeQuery();
            String strTinhId = "";
            while ((rs != null) && (rs.next())) {
                strTinhId += rs.getInt(PARAM_01) + ",";  
            }
            
            arrTinhId = strTinhId.split(",");
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return arrTinhId;
    }
    
    public String getTinhNameById(Connection cnn, int areaId) throws EException {
        final String LOCATION = this.toString() + "getTreeMultiRecords()";
        String  name = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_TINH_NAME_BY_TINH_ID);
            prstm.setInt(PARAM_01, areaId);
            rs = prstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                name = rs.getString(TINH_NAME);
            }
            
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return name;
    }


    public String getMaxCode(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getMaxCode()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FTinh bean = (FTinh)seed;
        String code = "";
        try {
            bean = getRecordByID(cnn, bean);
            prstm = cnn.prepareStatement(SQL_SELECT_TINH_MAX_STT);
            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                int a = rs.getInt(PARAM_01) + 1;
                if (a < 10) {
                    code = bean.getCode() + ".0" + a;
                } else {
                    code = bean.getCode() + "." + a;
                }
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

    public String getMaxCodeDis(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + "getMaxCodeDis()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FTinh bean = (FTinh)seed;
        String code = "";
        try {
            bean = getRecordByID(cnn, bean);
            String[] arrCode = bean.getCode().split("\\.");

            if (arrCode.length == 3) {
                prstm = cnn.prepareStatement(SQL_SELECT_MAX_CODE_4);
            } else if (arrCode.length == 2) {
                prstm = cnn.prepareStatement(SQL_SELECT_MAX_CODE_3);
            } else if (arrCode.length == 1) {
                prstm = cnn.prepareStatement(SQL_SELECT_MAX_CODE_2);
            }

            prstm.setInt(PARAM_01, bean.getId());
            rs = prstm.executeQuery();

            if (rs != null && rs.next()) {
                int lastMaNKT = rs.getInt(PARAM_01) + 1;
                code = bean.getCode() + ((lastMaNKT < 10) ? ".0" + lastMaNKT : "." + lastMaNKT);
            } else {
                code = "";
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

    public boolean haveChild(Connection conn, FSeed seed) throws EException {
        final String LOCATION = "->isExist()";
        boolean result = false;
        FTinh bean = (FTinh)seed;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(SQL_SELECT_TINH_HAVECHILD);
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
            result = execute(cnn, SQL_INSERT_TINH_TINH, params) > 0;
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
            FTinh bean = (FTinh)seed;
            List params = setParams(seed);
            params.add(bean.getId());
            result = execute(cnn, SQL_UPDATE_TINH_TINH, params) > 0;
            // updateUserArea(cnn,seed);
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean updateUserArea(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + UPDATE;
        boolean result = false;
        try {
            FTinh bean = (FTinh)seed;
            String member = "", resultTemp = "";
            String members = ""; //  getMembersByRule(cnn,0,bean.getCreator());
            if (bean.getMemberArea() == null || 
                (bean.getMemberArea() != null && 
                 bean.getMemberArea().equals(""))) {
                member = bean.getMemberArea();
            } else {
                member = bean.getMemberArea() + "," + members;
            }
            String[] temp = member.split(",");
            for (int i = 0; i < temp.length; i++) {

                if (!resultTemp.equals("")) {
                    if (resultTemp.indexOf(temp[i]) < 0) {
                        resultTemp += temp[i];
                    }
                } else {
                    resultTemp = temp[i];
                }
            }

            if (!resultTemp.equals(""))
                resultTemp = "," + resultTemp + ",";

            List params = new ArrayList();
            params.add(resultTemp);
            params.add(bean.getCreator());
            result = execute(cnn, SQL_UPDATE_USERS_AREA, params) > 0;
        } catch (EException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        }
        return result;
    }

    public boolean delete(Connection cnn, FSeed seed) throws EException {
        final String LOCATION = this.toString() + DELETE;
        FTinh bean = (FTinh)seed;
        return 0 < 
            delete(cnn, TABLE_TINH, TINH_TINH_ID + EQUAL + bean.getId());
    }

    public FBeans getTreeMultiRecords(Connection cnn, int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getTreeMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_TREE_TINH);
            prstm.setInt(PARAM_01, idDepartment);
            rs = prstm.executeQuery();
            FTinh bean = null;
            while ((rs != null) && (rs.next())) {
                bean = new FTinh();
                bean.setId(rs.getInt(PARAM_01));
                bean.setParentID(rs.getInt(PARAM_02));
                bean.setName(rs.getString(PARAM_03));
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

    public FBeans getMultiRecords(Connection cnn, int idDepartment) throws EException {
        final String LOCATION = this.toString() + "getMultiRecords()";
        FBeans beans = new FBeans();
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL_SELECT_TINH);
            rs = prstm.executeQuery();
            String members = ",";
            FTinh bean = null;
            boolean all = idDepartment == 0;
            boolean start = false;
            int id = -1;
            while ((rs != null) && (rs.next() && members != null)) {
                id = rs.getInt(PARAM_01);
                if ((all && members.indexOf("," + id + ",") < 0) || 
                    (!start && id == idDepartment)) {
                    start = true;
                    bean = new FTinh();
                    bean.setId(id);
                    bean.setName(rs.getString(PARAM_02));
                    bean.setParentID(rs.getInt(PARAM_03));
                    if (id > 0) {
                        members += id + ",";
                        beans.add(bean);
                    }
                }
                if (start) {
                    if (all || members.indexOf("," + id + ",") >= 0) {
                        id = rs.getInt(PARAM_04);
                        bean = new FTinh();
                        bean.setId(id);
                        bean.setName(rs.getString(PARAM_05));
                        bean.setParentID(rs.getInt(PARAM_06));
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

//    public String getMembers(Connection cnn, int idDepartment, 
//                             String membersRule) throws EException {
//        final String LOCATION = this.toString() + "getMembers()";
//        String members = ",";
//        PreparedStatement prstm = null;
//        ResultSet rs = null;
//        try {
//
//            String SQL;
//            List params = new ArrayList();
//            SQL = SQL_SELECT_TINH;
//
//
//            if (membersRule != null && !membersRule.equals("")) {
//                SQL = 
//                    SQL_SELECT_TINH_RULE + WHERE + "A." + TINH_TINH_ID + IN + OPEN + membersRule + 
//                    CLOSE + OR + "B." + TINH_TINH_ID + IN + OPEN + membersRule + CLOSE + AND + 
//                    "A." + TINH_PARENT_ID + EQUAL + QUESTION + OR + "B." + TINH_PARENT_ID + 
//                    EQUAL + QUESTION;
//            }
//
//            prstm = cnn.prepareStatement(SQL);
//            prstm.setInt(PARAM_01, idDepartment);
//            prstm.setInt(PARAM_02, idDepartment);
//            rs = prstm.executeQuery();
//            
//            boolean start = false;
//            int id = 0;
//            while ((rs != null) && (rs.next() && id >= 0)) {
//                id = rs.getInt(PARAM_01);
//                if (!start && id == idDepartment) {
//                    start = true;
//                    if (id > 0)
//                        members += id + ",";
//                }
//                if (start) {
//                    if (members.indexOf("," + id + ",") >= 0) {
//                        id = rs.getInt(PARAM_04);
//                        if (id > 0)
//                            members += id + ",";
//                    } else {
//                        id = rs.getInt(PARAM_03);
//                    }
//                }
//            }
//        } catch (SQLException sqle) {
//            if (AppConfigs.APP_DEBUG)
//                throw new EException(LOCATION, sqle);
//        } finally {
//            closeResultSet(rs);
//            closePreparedStatement(prstm);
//        }
//        return members.length() > 1 ? 
//               members.substring(1, members.length() - 1) : "";
//    }

    public String getMembers(Connection cnn, int idDepartment, String membersRule) throws EException {
        final String LOCATION = this.toString() + "getMembers()";
        String members = ",";
        PreparedStatement prstm = null;
        ResultSet rs = null;
         try {
             String SQL = SQL_SELECT_TINH;
    
             if (membersRule != null && !membersRule.equals("")) {
                 SQL = SQL_SELECT_TINH_RULE + WHERE + "A." + TINH_TINH_ID + IN + OPEN + membersRule + 
                     CLOSE + OR + "B." + TINH_TINH_ID + IN + OPEN + membersRule + CLOSE;
             } else {
                 SQL = SQL_SELECT_TINH_RULE;
             }
    
             prstm = cnn.prepareStatement(SQL);
             rs = prstm.executeQuery();
             boolean start = false;
             int id = 0;
             while ((rs != null) && (rs.next() && id >= 0)) {
                 id = rs.getInt(PARAM_01);
                 if (!start && id == idDepartment) {
                     start = true;
                     if (id > 0)
                         members += id + ",";
                 }
                 if (start) {
                     if (members.indexOf("," + id + ",") >= 0) {
                         id = rs.getInt(PARAM_04);
                         if (id > 0)
                             members += id + ",";
                     } else {
                         id = rs.getInt(PARAM_03);
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
         return members.length() > 1 ? 
                members.substring(1, members.length() - 1) : "";
    }  
    
    public String getMembersByRule(Connection cnn, int idDepartment, 
                                   int userId) throws EException {
        final String LOCATION = this.toString() + "getMembersByRule()";
        String members = "";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {

            prstm = cnn.prepareStatement(SQL_SELECT_TINH_RULE);
            prstm.setInt(PARAM_01, userId);
            rs = prstm.executeQuery();
            boolean start = false;
            int id = 0;
            while ((rs != null) && (rs.next())) {
                id = rs.getInt(PARAM_01);
                members += id + ",";
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return members.length() > 1 ? 
               members.substring(1, members.length() - 1) : "";
    }


    public FTinh getInformation(ResultSet rs) throws EException {
        final String LOCATION = "->getInformation()";
        FTinh requiretype = new FTinh();
        try {
            requiretype.setId(rs.getInt(TINH_TINH_ID));
            requiretype.setCode(rs.getString(TINH_CODE));
            requiretype.setName(rs.getString(TINH_NAME));
            requiretype.setParentID(rs.getInt(TINH_PARENT_ID));
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
        }
        return requiretype;
    }

    public List setParams(FSeed seed) throws EException {
        final String LOCATION = "->setParams()";
        FTinh bean = (FTinh)seed;
        List params = new ArrayList();
        try {
            params.add(bean.getCode());
            params.add(bean.getName());
            params.add(bean.getParentID());
        } catch (Exception exp) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, exp);
        } finally {
        }
        return params;
    }

    public FBeans getTree(Connection cnn, FBeans beans, int id, int level, 
                          String SQL, String characters) throws EException {
        final String LOCATION = this.toString() + "getTree()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        try {
            prstm = cnn.prepareStatement(SQL);
            prstm.setInt(PARAM_01, id);
            rs = prstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                FTinh bean = new FTinh();
                bean.setId(rs.getInt(PARAM_01));
                bean.setParentID(rs.getInt(PARAM_02));
                bean.setName(rs.getString(PARAM_03));
                bean.setLevel(level);
                for (int i = 0; i < level; i++) {
                    bean.setName(characters + bean.getName());
                }
                bean.setParentName(0);
                if (beans.size() > 0) {
                    FTinh beant = new FTinh();
                    for (int k = 0; k < beans.size(); k++) {
                        beant = (FTinh)beans.get(k);
                        if (bean.getParentID() == beant.getId()) {
                            beant.setParentName(1);
                        }
                    }
                }

                beans.add(bean);
                getTree(cnn, beans, bean.getId(), level + 1, SQL, characters);
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

    public FBeans getTrees(FBeans inBeans, FBeans outBeans, int id, int level) throws EException {
                           
        final String LOCATION = this.toString() + "getTree()";
        FTinh bean = null;
        for (int j = 0; j < inBeans.size(); j++) {
            bean = (FTinh)inBeans.get(j);
            if (bean.getParentID() == id) {
                for (int i = 0; i < level; i++) {
                    bean.setName("--" + bean.getName());
                }
                bean.setLevel(level);
                outBeans.add(bean);
                inBeans.remove(j);
                j--;
                getTrees(inBeans, outBeans, bean.getId(), level + 1);
            }
        }
        return outBeans;
    }


    public int getCount(Connection cnn, int parentID) throws EException {
        final String LOCATION = this.toString() + "getAll()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        int result = 0;
        String SQL_SELECT = SELECT + " COUNT(*) " + FROM + TABLE_TINH + WHERE + TINH_PARENT_ID + EQUAL + QUESTION;
        try {
            List params = new ArrayList();
            params.add(parentID);
            prstm = prepareStatement(cnn, SQL_SELECT, params);
            rs = prstm.executeQuery();
            if (rs != null && rs.next()) {
                result = rs.getInt(PARAM_01);
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
