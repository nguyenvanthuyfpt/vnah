package com.action.disability;


import com.action.ACore;

import com.bo.admin.users.BUsers;
import com.bo.disability.BDanSoHuyen;
import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.exp.EException;

import com.form.FBeans;
import com.form.admin.users.FUser;
import com.form.disability.FDanSoHuyen;
import com.form.disability.categorys.FTinh;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ADanSoHuyen extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target =_LOGOUT;
        ActionErrors errors = new ActionErrors();       
        FDanSoHuyen bean = (FDanSoHuyen)form;         
        BDanSoHuyen bo=new BDanSoHuyen();
        String anchor=bean.getValue(APP_ANCHOR,"");   
        target = validate(bean,anchor,errors);
        
        if(!errors.isEmpty()){            
            FUser beanU = new FUser();                
            beanU.setId((int)me.getId());
            beanU = new BUsers().getRecordByID(beanU);
            String membersRule =beanU.getArea();
            request.setAttribute("input",1);
           
            FBeans beans=new FBeans();
            String SQL="SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
            if (membersRule!=null && !membersRule.equals("")){
                membersRule = membersRule.substring(1,membersRule.length()-1);
                SQL += " AND (tinh_id in (" + membersRule + ")" + " or tinh_id IN (select tinh_id from dr_area where parent_id =0 order by tinh_id LIMIT 1 ))" ;
            }
            
            String characters="/ ";
            beans=new BTreeView().getTree(0,false,SQL,characters,"");
            request.setAttribute("BTreeTinhs",beans);
            if (beans!=null && beans.size()>0){
                FTinh bT=(FTinh)beans.get(0);
                bean.setId_tinh(bT.getId());
            }
            bean.setTotalPopulation(new BTinh().getCount(bean.getId_tinh()));
            bean.setNam(bean.getYear(bean.getCurrentSqlDate()));
            
            if(bean.getId_tinh()>0){
                FTinh beanCa=new FTinh();
                beanCa.setParentID(bean.getId_tinh());
                List params =new ArrayList();
                for (int i=bean.getId_tinh();i>0;i=beanCa.getParentID()) {
                    beanCa.setId(beanCa.getParentID());
                    beanCa=new BTinh().getRecordByID(beanCa);
                    params.add(beanCa.getName());
                }
                for (int i=params.size()-1;i>-1;i--) {
                    bean.setTinhName(bean.getTinhName()+" - "+params.get(i));
                }
            }                      

            request.setAttribute("danSoHuyen",bean);
            request.setAttribute("BDanSoHuyens",new BDanSoHuyen().getAllByIdTinh(bean.getId_tinh()));
            target = "_DANSOHUYEN";
                
        }else{  
        
            if(anchor.equals("_LIST_SHOW")){ 
                target =anchor;
            }else if(anchor.equals(_CREATE)){
                bean.setId(0);
                    if(bo.insert(bean)){
                        errors.add("alert",new ActionError("alert.insert.successfull"));
                        bean.reset();
                    }else{
                        errors.add("alert",new ActionError("alert.disability.dansohuyen.exits.tinh"));   
                    }
                bean.setNam(bean.getYear(bean.getCurrentSqlDate()));
                request.setAttribute("danSoHuyen",bean);
                request.setAttribute("BDanSoHuyens",bo.getAllByIdTinh(bean.getId_tinh())); 
                target="_DANSOHUYEN";
            }else if(anchor.equals(_EDIT)){
                if(bo.update(bean)){
                    errors.add("alert",new ActionError("alert.update.successfull"));
                }else{
                    errors.add("alert",new ActionError("alert.disability.dansohuyen.exits.tinh"));   
                }
                bean.setNam(bean.getYear(bean.getCurrentSqlDate()));
                request.setAttribute("danSoHuyen",bean);
                request.setAttribute("BDanSoHuyens",bo.getAllByIdTinh(bean.getId_tinh())); 
                target="_DANSOHUYEN";
            }else if(anchor.equals(_DELETE)){
                if(bo.delete(bean.getId())){
                    errors.add("alert",new ActionError("alert.dansohuyen.delete.successfull"));
                }else{
                    errors.add("alert",new ActionError("alert.dansohuyen.delete.error"));
                }
                bean.reset();
                bean.setNam(bean.getYear(bean.getCurrentSqlDate()));
                request.setAttribute("danSoHuyen",bean);
                request.setAttribute("BDanSoHuyens",bo.getAllByIdTinh(bean.getId_tinh())); 
                target="_DANSOHUYEN_DELETE";
            }else if(anchor.equals("_DETAIL")){
                bean=bo.getById(bean.getId());
                bean.setNam(bean.getYear(bean.getCurrentSqlDate()));
                request.setAttribute("danSoHuyen",bean);
                request.setAttribute("BDanSoHuyens",bo.getAllByIdTinh(bean.getId_tinh())); 
                target="_DANSOHUYEN";            
            }
            if(anchor.equals(_VIEW)){
                request.setAttribute("BDanSoHuyens",bo.getAllByIdTinh(bean.getId_tinh())); 
                target=anchor;
            }else{
                FUser beanU = new FUser();                
                beanU.setId((int)me.getId());
                beanU = new BUsers().getRecordByID(beanU);
                String membersRule =beanU.getArea();
                request.setAttribute("input",1);
                
                FBeans beans=new FBeans();
                String SQL="SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
                if (membersRule!=null && !membersRule.equals("")){
                    membersRule = membersRule.substring(1,membersRule.length()-1);
                    SQL += " AND (tinh_id in (" + membersRule + ")" + " or tinh_id IN (select tinh_id from dr_area where parent_id =0 order by tinh_id LIMIT 1 ))" ;
                }
                
                String characters="/ ";
                beans=new BTreeView().getTree(0,false,SQL,characters,"");
                request.setAttribute("BTreeTinhs",beans);
                bean.setTinhName("");
            
                if(bean.getId_tinh()>0){
                    FTinh beanCa=new FTinh();
                    beanCa.setParentID(bean.getId_tinh());
                    List params =new ArrayList();
                    for (int i=bean.getId_tinh();i>0;i=beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa=new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i=params.size()-1;i>-1;i--) {
                        bean.setTinhName(bean.getTinhName()+" - "+params.get(i));
                    }
                } 
                request.setAttribute("BDanSoHuyens",bo.getAllByIdTinh(bean.getId_tinh()));
            }
        }
        request.setAttribute("anchor", "01");
        request.setAttribute("subanchor", "01.02");
        if(!errors.isEmpty()) saveErrors(request,errors);
        return mapping.findForward(target);
    }
   
   
    private String validate(FDanSoHuyen bean,String anchor,ActionErrors errors){
       
       if (anchor.equals(_CREATE) || anchor.equals(_EDIT)){
           if (bean.getTongXa()<bean.getXcct()){
               errors.add("alert",new ActionError("alert.tongxa_huyen.compare.xtct"));
           }
       }            
       return anchor;
    }
   
}
