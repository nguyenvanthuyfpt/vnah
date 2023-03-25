package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.BObjectInd;
import com.bo.disability.categorys.BIndicator;
import com.bo.disability.categorys.BObject;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.disability.FObjectInd;
import com.form.disability.categorys.FIndicator;
import com.form.disability.categorys.FObject;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AObject extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FObject bean =(FObject)form;
        FIndicator fIndicator = new FIndicator();
        FObjectInd fObjInd = new FObjectInd();
        String anchor = bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);
        
        String characters = "/ ";
        String member = "";
        String SQL = "SELECT id,parent_id,name FROM kpi_object WHERE parent_id = ?";
        
        FBeans optObjects = new FBeans();
        optObjects = new BTreeView().getTree(0, false, SQL, characters, member);       
        
        if (!errors.isEmpty()) {
        } else if (anchor.equals(_EDIT)) {
            if (bean.getId() == bean.getParentID()) {
                errors.add("alert", new ActionError("errors.doituong.edit.circle"));
            } else if (new BObject().update(bean)) {
                errors.add("alert", new ActionError("alert.update.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.doituong.update.departmentcode"));
            }
            
            request.setAttribute("optObjects", optObjects);
            request.setAttribute("menuObjects", new BObject().getAllRecord(0,0));
            request.setAttribute("listObjects", new BObject().getAll(bean));
            target = anchor;
        } else if (anchor.equals(_CREATE)) {
            BObject object = new BObject();
            bean.setId(0);
            if (object.insert(bean)) {
                errors.add("alert", new ActionError("alert.insert.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.object.duplicate.code"));
            }
            bean.reset();
            target = anchor;
        } else if (anchor.equals(_DELETE)) {
            if (new BObject().delete(bean)) {                
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.doituong.delete.havechild"));
            }
            bean.reset();
            target = anchor;
        } else if (anchor.equals(_VIEW)) {                        
            if (bean.getPageIndex() <= 0)
                bean.setPageIndex(1);
            target = anchor;
        } else if (anchor.equals("_DETAIL")) {
            if (bean.getDtlId()> 0) {
                bean.setId(bean.getDtlId());
                bean = new BObject().getRecordByID(bean);                
            }
            target = anchor;
        } else if (anchor.equals("_SELECT")) {
            if (bean.getDtlId()> 0) {
                bean.setId(bean.getDtlId());
                bean = new BObject().getRecordByID(bean);
                String selIndIds = new BObject().getSelIndIds(bean);
                bean.setSelIndIds(selIndIds);
            }
            request.setAttribute("listIndicators", new BIndicator().getAll(fIndicator));
            target = anchor;
        } else if (anchor.equals("_SAVE_IND")) {
            int[] arr_indId = null;
            String selIndIds = "";
            boolean retval = false;
            
            // Delete
            fObjInd.setYear(bean.getYear());
            fObjInd.setObjId(bean.getId());            
            retval = new BObjectInd().delete(fObjInd);
            
            // Insert
            if (bean.getIndIds()!=null) {
                arr_indId = bean.getIndIds();
                for (int i=0;i<arr_indId.length;i++) {
                    selIndIds += String.valueOf(arr_indId[i]) + ",";
                    fObjInd.setIndId(arr_indId[i]);
                    retval = new BObjectInd().insert(fObjInd);
                }
                if (retval) {
                    bean = new BObject().getRecordByID(bean);
                    bean.setSelIndIds(selIndIds);
                    errors.add("alert", new ActionError("alert.insert.successfull"));
                } else {
                    errors.add("alert", new ActionError("errors.map.object.ind.has.exists"));
                }
            }
            request.setAttribute("listIndicators", new BIndicator().getAll(fIndicator));
            target = anchor;
        } else if (anchor.equals("_DELETE_IND")) {
            // Delete
            boolean retval = false;
            fObjInd.setYear(bean.getYear());
            fObjInd.setObjId(bean.getId());            
            retval = new BObjectInd().delete(fObjInd);
            if (retval) {
                bean.setSelIndIds("");
                errors.add("alert", new ActionError("alert.delete.successfull"));
            } else {
                errors.add("alert", new ActionError("errors.map.object.ind.has.exists"));
            }
            
            request.setAttribute("object", bean);
            request.setAttribute("listIndicators", new BIndicator().getAll(fIndicator));
            target = anchor;
        } else if (anchor.equals("_CHANGE_YEAR")) {
            String selIndIds = new BObject().getSelIndIds(bean);
            bean.setSelIndIds(selIndIds);
            request.setAttribute("listIndicators", new BIndicator().getAll(fIndicator));
            target = anchor;
        } else if (anchor.equals("_CANCEL_IND")) {
            bean.setId(0);
            target = anchor;
        }
        
        request.setAttribute("object", bean);
        request.setAttribute("optObjects", optObjects);
        request.setAttribute("menuObjects", new BObject().getAllRecord(0,0));
        request.setAttribute("listObjects", new BObject().getAll(bean));
        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }

    private String validate(FObject bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_VIEW)) {

        } else if (anchor.equals(_EDIT)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.object.edit.short"));
            } else if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.object.edit.idnull"));
            }
        } else if (anchor.equals(_CREATE)) {
            if ((bean.getName().trim().equals(_BLANK) || bean.getCode().trim().equals(_BLANK))) {
                errors.add("alert", new ActionError("errors.object.edit.short"));
            }
        } else if (anchor.equals(_DELETE)) {
            if (bean.getId() <= 0) {
                errors.add("alert", new ActionError("errors.object.edit.idnull"));
            }        }
        return anchor;
    }
}
