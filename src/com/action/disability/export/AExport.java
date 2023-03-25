package com.action.disability.export;


import com.action.ACore;

import com.bo.disability.BImport;
import com.bo.tree.BTreeView;

import com.dao.connection.DBConnector;
import com.dao.disability.categorys.DTinh;
import com.dao.disability.export.ExportDisabilityInfo;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FExport;

import com.inf.disability.IKeyDisability;

import java.io.File;
import java.io.IOException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;


public class AExport extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {

        final String LOCATION = this + "->executeAction()";
        String target = _LOGOUT;
        ActionErrors errors = new ActionErrors();
        ActionMessages messages = new ActionMessages();
        
        FExport bean = (FExport)form;
        
        DTinh daoT = new DTinh();                
        Connection cnn  = DBConnector.getConnection();
        String nameTinh = "";
        
        String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
        if("_EXPORT_DATA".equals(anchor)){
            target = validate(bean, anchor, errors);    
        }
        
        FBeans beans = new FBeans();
        String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
        String characters = "/ ";
        String member = "";
        
        if ((bean.me.getDepartmentName() != null) && (!bean.me.getDepartmentName().equals(""))) {
            member = bean.me.getDepartmentName();
        }

        beans = new BTreeView().getTree(0, true, SQL, characters, member);
        request.setAttribute("BTreeTinhs", beans);
        
        String jdbcUrl  = "jdbc:postgresql://localhost:5432/" + IKeyDisability.DATABASE_NAME;
        String dbuid    = IKeyDisability.DATABASE_USERNAME;
        String dbpwd    = IKeyDisability.DATABASE_PASSWORD;
        String folder   = IKeyDisability.PATH_FOLDER_EXPORT_DATA;
        String path     = "";
        String area = "";
        boolean hasGot = new File(folder).exists();
        if(!hasGot){
            File file = new File(folder);
            file.mkdirs();
        }
        
        request.getSession().setAttribute("BTables", new BImport().getAllTable(true));
        
        if (anchor.equals("_EXPORT_DATA") && errors.isEmpty()) { 
            nameTinh = daoT.getTinhNameById(cnn,Integer.parseInt(bean.getTinh_id()));
            bean.setTinh_name(nameTinh);
            ExportDisabilityInfo export = new ExportDisabilityInfo();
            path = export.getPathFile(bean);
            area = bean.getTinh_name();            
            String valReturn = "";            
            try {
                valReturn = export.doWork(bean, bean.getTinh_id(), dbuid, dbpwd, jdbcUrl);
                if(!"".equals(valReturn)){                    
                    errors.add("alert", new ActionError("alert.export.data.successfull", area, path));
                    String report = bean.getTinh_name();
                    bean.download(valReturn, bean.ncrToString(report)+".xls",null);
                    bean.deleteFile(valReturn);
                } else if ("".equals(valReturn)){
                    errors.add("alert", new ActionError("alert.export.data.max"));
                } else {
                    errors.add("alert", new ActionError("alert.export.data.unsuccessfull"));    
                }
            } catch (Exception e) {
                e.printStackTrace();
            }            
            target = _SUCCESS;
        } else if (anchor.equals("_SELECT_EXPORTTYPE")) {
            target = anchor;
        }
        
        if (!errors.isEmpty())
            saveErrors(request, errors);
        
        return mapping.findForward(target);
    }
    
    private String validate(FExport bean, String anchor, ActionErrors errors) throws EException{
        Connection cnn  = DBConnector.getConnection();        
        Statement m_Statement = null;
        ResultSet m_ResultSet = null;
        
        int areaId = Integer.parseInt(bean.getTinh_id());
        if (anchor.equals(_SWAP)) {
        } else if (anchor.equals("_EXPORT_DATA")) {
            int count = 0;
            String query = "SELECT count(1) FROM dr_disabilitypeople where id_tinh in (" + getAreaIds(cnn, areaId) + ")";
            try {
                m_Statement = cnn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                m_ResultSet = m_Statement.executeQuery(query);            
                //count = DaoUtil.get_rc(m_ResultSet);
                while(m_ResultSet.next()){
                    count = m_ResultSet.getInt(1);
                }
            } catch (SQLException e) {
            } catch (Exception e) {
            }

            if(count>=60000){
                errors.add("alert", new ActionError("alert.export.data.unsuccessfull"));
            } else if (count==0) {
                errors.add("alert", new ActionError("alert.export.nodata"));
            }
        }
        return anchor;
    }
    public String getAreaIds(Connection cnn, int areaId) throws EException {
        String strArea = String.valueOf(areaId);
        DTinh daoTinh = new DTinh();
        strArea = daoTinh.getMembers(cnn, areaId ,"");
        return strArea;
    }
}
