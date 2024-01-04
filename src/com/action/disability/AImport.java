package com.action.disability;


import com.action.ACore;

import com.action.disability.dto.KpiEvent;
import com.action.disability.dto.KpiPerson;

import com.bo.disability.BDataHdr;
import com.bo.disability.BDataPer;
import com.bo.disability.BImport;

import com.bo.disability.BPerson;
import com.bo.disability.categorys.BEvent;

import com.dao.disability.DImport;

import com.dao.disability.categorys.DEvent;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDataHdr;
import com.form.disability.FDataPer;
import com.form.disability.FImport;

import com.form.disability.FPerson;
import com.form.disability.categorys.FEvent;

import com.lib.AppConfigs;

import com.util.Constant;

import java.io.File;
import java.io.IOException;

import java.sql.SQLException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;


public class AImport extends ACore {
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        final String LOCATION = this + "->executeAction()";
        String target = _LOGOUT;
        ActionErrors errors = new ActionErrors();        
        FImport bean = (FImport)form;
        String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");
        target = validate(bean, anchor, errors);
        int typeImport = 0;
        
        // Object
        FBeans optObjects = new FBeans();
        optObjects = (FBeans)request.getSession().getAttribute("OPT_TREE_OBJECT");
        request.setAttribute("optObjects", optObjects);
        
        // Event
        FEvent fEvent = new FEvent();
        fEvent.setLocationId(0);
        FBeans events = new BEvent().getEventByLocationId(fEvent);                    
        request.setAttribute("events", events);
        
        if (anchor.equals(_SWAP)) {
            String dirs = 
                AppConfigs.APP_SYSTEM_PATH + AppConfigs.CABIN_FILE_PATH + 
                AppConfigs.SYSTEM_FILE_SCHIP + bean.me.getUsername() + 
                AppConfigs.SYSTEM_FILE_SCHIP;
            (new File(dirs)).mkdirs();
            String fileName = "";
            int rowBegin = 0;
            boolean haveFile = bean.getUpFile().getFileSize() > 0;
            
            if (haveFile) {
                fileName = dirs + encodeFileName(bean.me.getId()) + ".xls";
                rowBegin = bean.getRowBegin();
                typeImport = bean.getTypeImport();
                bean.upload(bean.getUpFile(), fileName);
                DImport dao = new DImport();
                bean.setRowBegin(rowBegin);
                
                bean.setHasReadFile(1);               
                if(typeImport==1){
                    bean.setTableName("dr_area");
                    request.setAttribute(Constant.map_columns, new BImport().getAllColumn("dr_area"));
                } else if (typeImport==3) {
                    List<KpiEvent> lstEvent = dao.readFileExcelEvent(fileName, typeImport, rowBegin); 
                    System.out.println("lstEvent "+lstEvent.size());
                    request.getSession().setAttribute("lstEvent", lstEvent);
                } else if (typeImport==4) {
                    List<KpiPerson> lstPerson = dao.readFileExcelPerson(fileName, typeImport, rowBegin);
                    request.getSession().setAttribute("lstPerson", lstPerson);
                } else {
                    bean.setTableName("dr_disabilitypeople");
                    request.setAttribute(Constant.map_columns, new BImport().getAllColumn("dr_disabilitypeople"));
                }
                
                bean.setTypeImport(typeImport);
                request.getSession().setAttribute("BDatas", bean);
                request.getSession().setAttribute("BTables", new BImport().getAllTable(false));                
                request.getSession().setAttribute("BImport", bean);
            }
            target = anchor;

        } else if (anchor.equals("_APPLY")) {
            int numInsert = 0;
            boolean hasSuccess = false;
            List<KpiEvent> lstEvent = (List<KpiEvent>)request.getSession().getAttribute("lstEvent");
            typeImport = bean.getTypeImport();
            if (typeImport==3) {
                FEvent event = null;
                for(KpiEvent kpiEvent: lstEvent) {
                    event = new FEvent();
                    event.setActivity(kpiEvent.getActivity());
                    event.setCreateDate(kpiEvent.getCreateDate());
                    event.setStartDate(kpiEvent.getStartDate());
                    event.setEndDate(kpiEvent.getEndDate());
                    event.setLocation(kpiEvent.getLocation());
                    event.setLocationId(kpiEvent.getLocationId());
                    
                    new BEvent().insert(event);
                    numInsert++;
                }
                
            } else if (typeImport == 4) {
                List<KpiPerson> lstPersonImp = (List<KpiPerson>)request.getSession().getAttribute("lstPerson");
                FPerson person = null;
                FEvent event = null;
                int dataId = 0;
                int locationId = 0;
                for (KpiPerson kpiPerson: lstPersonImp) {
                    // Event
                    event = new FEvent();
                    event.setEventId(bean.getEventId());
                    event = new BEvent().getRecordByID(event);
                    locationId = event.getLocationId();
                    
                    // Person
                    person = new FPerson();
                    person.setName(kpiPerson.getFullName());
                    person.setContact(kpiPerson.getContact());
                    person.setAgency(kpiPerson.getAgency());
                    person.setTitle(kpiPerson.getTitle());
                    person.setSex(kpiPerson.getSex());
                    person.setLocationId(locationId);
                    int personId = new BPerson().insert(person);
                    // System.out.println("personId " + personId);
                    
                    // Data-Hdr
                    FDataHdr dataHdr = new FDataHdr();
                    dataHdr.setObjId(bean.getObjId());
                    dataHdr.setIndId(97);
                    dataHdr.setLocationId(locationId);
                    dataId = new BDataHdr().getHdrId(dataHdr);
                    // System.out.println("dataId " + dataId);
                    
                    // Data-Event;
                    FDataPer dataPer = new FDataPer();
                    dataPer.setCreateDate(event.getCreateDate());
                    dataPer.setDataId(dataId);
                    dataPer.setPerId(personId);
                    dataPer.setEventId(bean.getEventId());
                    dataPer.setResult(0);
                    dataPer.setHours(0);                    
                    new BDataPer().insert(dataPer);
                    numInsert++;
                }
                
                // Update Event To DataHdr
                FDataHdr dataHdr = new BDataHdr().getRecordById(dataId);
                String currentEvent = dataHdr.getEventIds();
                
                dataHdr.setId(dataId);
                dataHdr.setLocationId(locationId);
                dataHdr.setEventIds(currentEvent+","+bean.getEventId());
                boolean hasUpdate = new BDataHdr().update(dataHdr);
            }
            
            if (numInsert>0) {
                hasSuccess = true;
            }
            
            if (hasSuccess) {
                bean.setImportMsg(bean.ncrToString("alert.load.todata.successfull").replace("{0}", 
                      String.valueOf(bean.getNoUpdate()).replace("{1}", String.valueOf(numInsert)).toString()));
                
                // errors.add("alert", new ActionError("alert.load.todata.successfull",String.valueOf(bean.getNoUpdate()), String.valueOf(numInsert)));
                
                request.getSession().removeAttribute("lstPerson");
                request.getSession().removeAttribute("lstEvent");                  
            } else {
                errors.add("alert", new ActionError("alert.load.todata.error"));
            }
            target = _SWAP;
        }
        
        request.setAttribute("beanImport", bean);
        if (!errors.isEmpty())
            saveErrors(request, errors);
        return mapping.findForward(target);
    }

    private String encodeFileName(long userID) {
        return userID + "." + System.currentTimeMillis();
    }
    
    private String validate(FImport bean, String anchor, ActionErrors errors) {
        if (anchor.equals(_SWAP)) {
        } else if (anchor.equals("_APPLY")) {
            if ("".equals(bean.getTableName())) {
                errors.add("alert", new ActionError("errors.import.data.must.select.table"));            
            }
        }
        return anchor;
    }
}
