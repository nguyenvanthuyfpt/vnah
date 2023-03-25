package com.action.require.requires;


import com.action.ACore;

import com.exp.EException;

import com.form.require.requires.FRequire;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class AList extends  ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws EException,IOException, ServletException,SQLException
    {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGIN;
        FRequire bean = (FRequire)form;
        if (bean.me.getId()==0) return null;
       
        String anchor=bean.getValue(APP_ANCHOR,"");
        ActionErrors errors = new ActionErrors();           
//           if (anchor.equals(_SEARCH_TYPE)){  
//                BList boL = new BList();                  
//                FEmpRequire beanE=new FEmpRequire();                                
//                beanE.setListId(bean.getListId());
//                request.setAttribute("requires",boL.getListAssetByListId(beanE));                                              
//               target=_SEARCH_TYPE;
//            }
//            else if (anchor.equals(_SHOW_LISTEMP)){  
//               BRequires boB=new BRequires();    
//               bean.setDepartment_id((int)bean.me.getDepartmentID());
//               request.setAttribute("beans",boB.search(bean));                                           
//               target=_SHOW_LISTEMP;
//           }else if (anchor.equals(_PREPARED_EDIT)){
//               FList beanF =new FList();  
//               BList bo = new BList();
//               beanF.setListId(bean.getListId()); 
//               beanF.setUserId((int)bean.me.getId());
//               request.setAttribute("beans",bo.getAllListReport(beanF)); 
//               beanF.setIdEditName(bean.getListId());
//               request.setAttribute("list",beanF);   
//               target=_PREPARED_EDIT;
//           }else if (anchor.equals(_DELETE)){
//               FList beanF =new FList();  
//               BList bo = new BList();
//               beanF.setListId(bean.getListId()); 
//               beanF.setUserId((int)bean.me.getId());
//               if (bo.delete(beanF)){
//                   errors.add("listErrors", new ActionError("errors.delete"));          
//               }
//               request.setAttribute("beans",bo.getAllListReport(beanF));     
//               beanF.setIdEditName(bean.getListId());
//               request.setAttribute("list",beanF);
//               target=_DELETE;               
//           }else if (anchor.equals(_EDIT)){
//               FList beanF =new FList();  
//               BList bo = new BList();
//               beanF.setListId(bean.getListId()); 
//               beanF.setUserId((int)bean.me.getId());  
//               beanF.setListName(bean.getListName());
//               if (bo.update(beanF)){          
//                  errors.add("listErrors", new ActionError("errors.edit"));                                    
//               }               
//               request.setAttribute("beans",bo.getAllListReport(beanF)); 
//               beanF.setIdEditName(0);
//               request.setAttribute("list",beanF);   
//               target=_PREPARED_EDIT;
//           }else if (anchor.equals(_SHOW_ASSET)){
//               FList beanF =new FList();  
//               BList bo = new BList();
//               beanF.setListId(bean.getListId()); 
//               beanF.setUserId((int)bean.me.getId());  
//               beanF.setListName(bean.getListName());
//               beanF.setIdEditName(bean.getListId());
//               beanF.setChon(bean.getChon());
//               if (bo.deleteAsset(beanF)){
//                   errors.add("listErrors", new ActionError("errors.delete")); 
//               }             
//               request.setAttribute("beans",bo.getAllListAsset(beanF,0)); 
//               request.setAttribute("list",beanF);                            
//               target=_SHOW_ASSET;
//           }else if (anchor.equals(_PREPARED_CREATE)){
//               FList beanF =new FList();  
//               BList bo = new BList();
//               beanF.setListId(bean.getListId()); 
//               beanF.setUserId((int)bean.me.getId());  
//               beanF.setListName(bean.getListName());
//               beanF.setIdEditName(bean.getListId());
//               beanF.setPageIndex(bean.getPageIndex());
//               request.setAttribute("beans",bo.getAllListAsset(beanF,0)); 
//               request.setAttribute("list",beanF);      
//               target=_SHOW_ASSET;
//           }else if (anchor.equals(_DETAIL_KIEMKE)){
//                 FRequire require = new FRequire();
//                 require.setId(bean.getId());
//                 BRequires requires = new BRequires();               
//                 if (requires.updateStatustRequire(bean)){
//                        errors.add("message",new ActionError("alert.update.successfull"));  
//                 }               
//                 require = requires.getRecordByID(require);
//                 request.setAttribute("BRequire",require);
//                 target=_DETAIL_KIEMKE;
//             }
//            else if (anchor.equals(_LIST_SHOW)){
//                 BList boL = new BList();
//                 FList beanA=new FList();
//                 beanA.setListId(0);
//                 beanA.setCheckAll(0);             
//                 beanA.setListName("");
//                 beanA.setUserId((int)bean.me.getId());
//                 beanA.setPageIndexList(bean.getPageIndex());
//                 request.setAttribute("beans",boL.getAllListReport(beanA));  
//                 request.setAttribute("list",beanA);  
//                 target=_DELETE;
//             }
        
       
        if(!errors.isEmpty())
        saveErrors(request,errors);    
        
        return mapping.findForward(target);
    }
}