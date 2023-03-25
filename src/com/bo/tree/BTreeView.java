package com.bo.tree;


import com.dao.connection.DBConnector;
import com.dao.tree.DTreeView;

import com.exp.EException;

import com.form.FBeans;

import com.lib.AppConfigs;

public class BTreeView {
    public FBeans getTree(int id, boolean showParent, String SQL, String characters, String member) throws EException {
        String LOCATION;
        FBeans result;
        java.sql.Connection cnn;
        LOCATION = (new StringBuilder()).append(this).append("->getTree()").toString();
        result = null;
        cnn = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DTreeView dao = new DTreeView();
            result = dao.getTree(cnn, new FBeans(), id, 0, 0, showParent, SQL, characters, member);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }

        return result;
    }


    public FBeans getTreeList(int id, String SQL, String characters, String member) throws EException {
        String LOCATION;
        FBeans result;
        java.sql.Connection cnn;
        LOCATION = (new StringBuilder()).append(this).append("->getTree()").toString();
        result = null;
        cnn = null;
        try {
            cnn = DBConnector.getConnection();
            DBConnector.startTransaction(cnn);
            DTreeView dao = new DTreeView();
            result = dao.getTreeList(cnn, new FBeans(), id, 0, 0, SQL, characters, member);
            DBConnector.endTransaction(cnn);
        } catch (EException sqle) {
            DBConnector.rollBackTransaction(cnn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            DBConnector.closeConnection(cnn);
        }

        return result;
    }
}
