package com.bo.disability;


import com.dao.connection.DBConnector;
import com.dao.disability.DSupport;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDisExport;
import com.form.disability.FSupport;

import com.lib.AppConfigs;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;


public class BSupport {
    DSupport dao = new DSupport();

    public boolean delete(int nktId, int statusId, Date createDate, int stt, int nguonId) throws EException {
        final String LOCATION = this + "->delete()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.delete(conn, nktId, statusId, createDate, stt, nguonId);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public boolean insert(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->insert()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.insert(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public boolean update(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->update()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.update(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public boolean updateSupport(FSeed seed) throws EException, SQLException {
        final String LOCATION = this + "->update()";
        boolean result = false;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.updateSupport(conn, seed);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

  
    public FBeans getNumSupports(int idNKT, String datecreate, int nguonId) throws EException,
                                                                SQLException {
        final String LOCATION = this + "->getAllByIdNkt()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getNumSupports(conn, idNKT, datecreate, nguonId);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    public FSupport getSupportByStt(int nktId, int status_id, String datecreate, int stt) throws EException, SQLException {
        final String LOCATION = this + "->getById()";
        FSupport result = new FSupport();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getSupportByStt(conn, nktId, status_id, datecreate, stt);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    public FBeans getAllByIdNkt(int idNkt, int statusId) throws EException,
                                                                SQLException {
        final String LOCATION = this + "->getAllByIdNkt()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getAllByIdNkt(conn, idNkt, statusId);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public FSupport getById(int nktId, int status_id, String datecreate) throws EException,
                                                      SQLException {
        final String LOCATION = this + "->getById()";
        FSupport result = new FSupport();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getById(conn, nktId, status_id, datecreate);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    public int countById(int nktId, int status_id, String datecreate) throws EException,
                                                      SQLException {
        final String LOCATION = this + "->getById()";
        int retval = 0;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            retval = dao.countById(conn, nktId, status_id, datecreate);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return retval;
    }

    public FSupport getByIdNkt_IdTypeSupport(int idNkt, int idTypeSupport,
                                             int ky,
                                             int nam) throws EException,
                                                             SQLException {
        final String LOCATION = this + "->getByIdNkt_IdTypeSupport()";
        FSupport result = new FSupport();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result =
                    dao.getByIdNkt_IdTypeSupport(conn, idNkt, idTypeSupport, ky,
                                                 nam);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }

    public FSupport getSupportByNktID_HotroID(int nktId,
                                              int hotroId) throws EException,
                                                                  SQLException {
        final String LOCATION = this + "->getSupportByNktID_HotroID()";
        FSupport result = new FSupport();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getSupportByNktID_HotroID(conn, nktId, hotroId);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    public FSupport getDoiTuongHoTroByNktId(int nktId) throws EException,
                                                                  SQLException {
        final String LOCATION = this + "->getDoiTuongHoTroByNktId()";
        FSupport result = new FSupport();
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getDoiTuongHoTroByNktId(conn, nktId);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    /*public int countNhuCauByNktId(int nktId, String supportId) throws EException,
                                                                  SQLException {
       final String LOCATION = this + "->countNhuCauByNktId()";
       int result = 0;
       Connection conn = null;
       try {
           conn = DBConnector.getConnection();
           DBConnector.startTransaction(conn);
           result = dao.countNhuCauByNktId(conn, nktId, supportId);
           DBConnector.endTransaction(conn);
       } catch (EException ex) {
           DBConnector.rollBackTransaction(conn);
           if (AppConfigs.APP_DEBUG)
               throw new EException(LOCATION, ex);
       } finally {
           DBConnector.closeConnection(conn);
       }
       return result;
   }*/
    
    public FBeans getSupportsByNktId(int idNkt, String supportId) throws EException,
                                                                SQLException {
        final String LOCATION = this + "->getSupportsByNktId()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getSupportsByNktId(conn, idNkt, supportId);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
    public FBeans getSupportsForChart(int idNkt) throws EException,
                                                                SQLException {
        final String LOCATION = this + "->getSupportsForChart()";
        FBeans result = null;
        Connection conn = null;
        try {
            conn = DBConnector.getConnection();
            DBConnector.startTransaction(conn);
            result = dao.getSupportsForChart(conn, idNkt);
            DBConnector.endTransaction(conn);
        } catch (EException ex) {
            DBConnector.rollBackTransaction(conn);
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, ex);
        } finally {
            DBConnector.closeConnection(conn);
        }
        return result;
    }
    
      public FBeans getSupports2020(int idNkt, int status) throws EException,
                                                                  SQLException {
          final String LOCATION = this + "->getSupportsForChart()";
          FBeans result = null;
          Connection conn = null;
          try {
              conn = DBConnector.getConnection();
              DBConnector.startTransaction(conn);
              result = dao.getSupports2022(conn, idNkt, status);
              DBConnector.endTransaction(conn);
          } catch (EException ex) {
              DBConnector.rollBackTransaction(conn);
              if (AppConfigs.APP_DEBUG)
                  throw new EException(LOCATION, ex);
          } finally {
              DBConnector.closeConnection(conn);
          }
          return result;
      }
      
      public int countNCauHTroByNguonId(int nktId, String datecreate, int nguonId) throws EException, SQLException {
         final String LOCATION = this + "->countNCauHTroByNguonId()";
         int result = 0;
         Connection conn = null;
         try {
             conn = DBConnector.getConnection();
             DBConnector.startTransaction(conn);
             result = dao.countHTroByNguonId(conn, nktId, datecreate, nguonId);
             DBConnector.endTransaction(conn);
         } catch (EException ex) {
             DBConnector.rollBackTransaction(conn);
             if (AppConfigs.APP_DEBUG)
                 throw new EException(LOCATION, ex);
         } finally {
             DBConnector.closeConnection(conn);
         }
         return result;
      }
}
