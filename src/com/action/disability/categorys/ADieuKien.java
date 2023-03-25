package com.action.disability.categorys;


import com.action.ACore;

import com.bo.disability.categorys.BDieuKien;

import com.exp.EException;

import com.form.disability.categorys.FDieuKien;

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


public class ADieuKien extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FDieuKien bean = (FDieuKien)form;
        String anchor=bean.getValue(APP_ANCHOR,"");        
        ActionErrors errors = new ActionErrors();
        target = validate(bean,anchor,errors);        
        if(!errors.isEmpty()){
        
        }else if(anchor.equals(_EDIT)){
                BDieuKien dieukien = new BDieuKien();
                if(bean.getId()==bean.getParentID()){
                    errors.add("alert",new ActionError("errors.dangtat.edit.circle"));   
                }else if(dieukien.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dangtat.update.departmentcode"));   
                }
            target = anchor;
        }else if(anchor.equals(_CREATE)){
                BDieuKien dieukien = new BDieuKien();
                int saveID = bean.getId();
                bean.setId(0);
                if(dieukien.insert(bean)){
                    errors.add("alert",new ActionError("alert.insert.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dangtat.insert.departmentcode"));   
                }
                bean.setId(saveID);
            
            target = anchor;
        }else if(anchor.equals(_DELETE)){
                BDieuKien dieukien = new BDieuKien();
                if(dieukien.delete(bean)){
                    bean = new FDieuKien();
                    errors.add("alert",new ActionError("alert.delete.successfull"));   
                }else{
                    errors.add("alert",new ActionError("errors.dangtat.delete.havechild"));   
                }
            target = anchor;
        }

        request.setAttribute("BTreeDieuKiens", new BDieuKien().getAllRecord(0));
        request.setAttribute("dieukien",bean);

        if(!errors.isEmpty()) saveErrors(request,errors);
        
        return mapping.findForward(target);
    }
    private String validate(FDieuKien bean,String anchor,ActionErrors errors){
        if(anchor.equals(_VIEW)){
        
        }else if(anchor.equals(_EDIT)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.dangtat.edit.short"));   
            }else if( bean.getId()<=0){
                errors.add("alert",new ActionError("errors.dangtat.edit.idnull"));   
            }
        }else if(anchor.equals(_CREATE)){
            if((bean.getName().trim().equals(_BLANK) || 
                bean.getCode().trim().equals(_BLANK))){
                errors.add("alert",new ActionError("errors.dangtat.edit.short"));   
                }
        }else if(anchor.equals(_DELETE)){
            if(bean.getId()<=0){
                errors.add("alert",new ActionError("errors.dangtat.edit.idnull"));   
            }
        }
    return anchor;
    }
}
