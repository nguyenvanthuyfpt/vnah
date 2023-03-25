package com.action.disability;


import com.action.ACore;

import com.bo.disability.BDanhGia;
import com.bo.disability.BDisability;
import com.bo.disability.BInforNKT;
import com.bo.disability.BPhanLoai;
import com.bo.disability.BRank;
import com.bo.disability.BRelative;
import com.bo.disability.BSupport;
import com.bo.disability.categorys.BDangTat;
import com.bo.disability.categorys.BDantoc;
import com.bo.disability.categorys.BDieuKien;
import com.bo.disability.categorys.BQuanhe;
import com.bo.disability.categorys.BTinh;
import com.bo.tree.BTreeView;

import com.dao.disability.report.DReportHuongLoi;

import com.exp.EException;

import com.form.FBeans;
import com.form.FSeed;
import com.form.disability.FDanhGia;
import com.form.disability.FDisability;
import com.form.disability.FInforNKT;
import com.form.disability.FPhanLoai;
import com.form.disability.FRank;
import com.form.disability.FRelative;
import com.form.disability.FSupport;
import com.form.disability.categorys.FTinh;

import com.inf.disability.IKeyDisability;

import java.io.IOException;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.NestableException;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class ADisability extends ACore {
    
    final static Logger logger = Logger.getLogger(ADisability.class);
    
    public ActionForward executeAction(ActionMapping mapping, ActionForm form, 
                                       HttpServletRequest request, 
                                       HttpServletResponse response) throws EException, 
                                                                            IOException, 
                                                                            ServletException, 
                                                                            SQLException {
        
        try {         
            final String LOCATION = this + "->executeAction()";
            String target = _LOGOUT;
            ActionErrors errors = new ActionErrors();
            FDisability bean = (FDisability)form;        
            BDisability bo = new BDisability();
            
            String anchor = ((FSeed)form).getValue(APP_ANCHOR, "");        
            String SQL = "SELECT tinh_id,parent_id,name FROM dr_area WHERE parent_id = ?";
            String characters = "/ ";
            String member = "";
            if ((bean.me.getDepartmentName() != null) && (!bean.me.getDepartmentName().equals(""))) {
                member = bean.me.getDepartmentName();
            }
            
            FBeans beans = new FBeans();
            beans = new BTreeView().getTree(0, false, SQL, characters, member);
            target = validate(bean, anchor, errors);
            
            if (!errors.isEmpty()) {
            } else if (anchor.equals("_LIST_SHOW")) {
                target = anchor;
            } else if (anchor.equals("_SELECT_IDTINH")) {
    
                FTinh beanT = new FTinh();
                BTinh tinh = new BTinh();
                beanT.setId(bean.getTinhId());
                String code = tinh.getMaxCodeDis(beanT);            
                request.setAttribute("BDieuKiens", new BDieuKien().getAllRecord(0));
                bean.setDateLastUpdate(bean.dateToString(bean.getCurrentDate()));
                bean.setMa(code);            
                bean.setTinhName("");
    
                if (bean.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getTinhId());
                    List params = new ArrayList();
                    for (int i = bean.getTinhId(); i > 0; 
                        i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
                
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("disability", bean);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = _CREATE;
            } else if (anchor.equals("_SELECT_IDTRINHDO")) {
                bean.setNgheNghiepKhac("");            
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("disability", bean);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = _CREATE;
            } else if (anchor.equals("_SHOW_IMG")) {
                target = anchor;
            } else if (anchor.equals(_CREATE)) {            
                bean.setTrangthai(0);
                if (bo.insert(bean)) {
                    errors.add("alert", new ActionError("alert.insert.successfull"));
                    bean = bo.getRecordByCode(bean);                
                } else {
                    if (anchor.equals(_CREATE)) {
                        errors.add("alert", new ActionError("errors.disability.edit.exitsName"));
                    } else {
                        errors.add("alert", new ActionError("errors.disability.edit.exitsCode"));
                    }
                }
                
                bean.setTinhName("");
                if (bean.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getTinhId());
                    List params = new ArrayList();
                    for (int i = bean.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
                
                request.setAttribute("disability", bean);
                request.setAttribute("BDieuKiens", new BDieuKien().getAllRecord(0));           
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("BSearchTinhs", beans);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = anchor;
            } else if (anchor.equals(_EDIT)) {
                if (bo.update(bean)) {
                    errors.add("alert", new ActionError("alert.update.successfull"));
                } else {
                    if (anchor.equals(_EDIT)) {
                        errors.add("alert", new ActionError("errors.disability.edit.exitsName"));
                    } else {
                        errors.add("alert", new ActionError("errors.disability.edit.exitsCode"));
                    }
                }
                
                bean.setTinhName("");
                if (bean.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getTinhId());
                    List params = new ArrayList();
                    for (int i = bean.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
    
                request.setAttribute("disability", bean);
                request.setAttribute("BDieuKiens", new BDieuKien().getAllRecord(0));            
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("BSearchTinhs", beans);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = anchor;
            } else if (anchor.equals(_PREPARED_EDIT)) {
                request.setAttribute("disability", bo.getRecordByID(bean));
                request.setAttribute("BDieuKiens", new BDieuKien().getAllRecord(0));
                request.setAttribute("BTreeDieuKiens", new BDieuKien().getAllRecord(0));
                request.setAttribute("BSearchTinhs", beans);
                request.setAttribute("BTreeTinhs", beans);
                target = anchor;
            } else if (anchor.equals(_DELETE)) {
                request.setAttribute("BTreeTinhs", beans);            
                FTinh beanTree = new FTinh();
                if (beans != null && beans.size() > 0) {    
                    beanTree = (FTinh)beans.get(0);
                }
                FTinh beanT = new FTinh();
                BTinh tinh = new BTinh();
                beanT.setId(beanTree.getId());
    
                String code = tinh.getMaxCodeDis(beanT);
                int id = bean.getId();
                bean = new FDisability();
                bean.setDateLastUpdate(bean.dateToString(bean.getCurrentDate()));
                bean.setMa(code);
                bean.setId(0);
                bean.setTinhName("");
                bean.setChuyenMonKhac("");
                bean.setGiaoDucKhac("");
                bean.setNgheNghiepKhac("");
                bean.setHonNhanKhac("");  
                
                FTinh beanTinh = (FTinh)beans.get(0);
                bean.setTinhId(beanTinh.getId());
    
                if (bean.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getTinhId());
                    List params = new ArrayList();
                    for (int i = bean.getTinhId(); i > 0; i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }            
    
                if (bo.delete(id)) {
                    errors.add("alert", new ActionError("alert.delete.successfull"));
                } else {
                    errors.add("alert", new ActionError("alert.delete.fail"));
                }
    
                request.setAttribute("disability", bean);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");
                target = _EDIT;
            } else if (anchor.equals("_APPROVE")) {
                request.setAttribute("BTreeTinhs", beans);
                bean = bo.getRecordByID(bean);
                bean.setTrangthai(1);
                
                if (bo.approve(bean)) {
                    errors.add("alert", new ActionError("alert.update.successfull"));
                } else {
                    errors.add("alert", new ActionError("errors.cabin.delete.fail"));
                }
    
                request.setAttribute("disability", new FDisability());
                request.setAttribute("BTreeDieuKiens", new BDieuKien().getAllRecord(0));
                target = anchor;   
            
            } else if (anchor.equals("_DETAIL")) {            
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("BSearchTinhs", beans); 
                bean = bo.getRecordByID(bean);
                bean.setTinhName("");
                bean.setChuyenMonKhac(bean.getChuyenMonKhac()==null?"":bean.getChuyenMonKhac());
                bean.setGiaoDucKhac(bean.getGiaoDucKhac()==null?"":bean.getGiaoDucKhac());
                bean.setNgheNghiepKhac(bean.getNgheNghiepKhac()==null?"":bean.getNgheNghiepKhac());
                bean.setHonNhanKhac(bean.getHonNhanKhac()==null?"":bean.getHonNhanKhac());
                bean.setTinhName("");
                if (bean.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(bean.getTinhId());
                    List params = new ArrayList();
                    for (int i = bean.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        bean.setTinhName(bean.getTinhName() + " - " + params.get(i));
                    }
                }
                request.setAttribute("disability", bean);
                request.setAttribute("subanchor", "01.01");
                request.setAttribute("anchor", "01");            
                target = anchor;
            } else if (anchor.equals("_DELETE_DISABILITY")) {
                request.setAttribute("BTreeTinhs", beans);
                bean = bo.getRecordByID(bean);
                if (bo.delete(bean.getId())) {
                    errors.add("alert", new ActionError("alert.delete.successfull"));
                } else {
                    errors.add("alert", new ActionError("errors.cabin.delete.fail"));
                }
                request.setAttribute("disability", new FDisability());
                target = anchor;
            } else if (anchor.equals("_SEARCH_RESULT")) {
                request.setAttribute("BDisabilitys", bo.getAll(bean));
                request.setAttribute("disability", bean);
                target = anchor;
            } else if (anchor.equals("_INFORMATION")) {         
                FDisability beanDis = bo.getRecordByID(bean);            
                beanDis.setTinhName("");
                FTinh beanTinh = (FTinh)beans.get(0);
                
                if(beanDis.getId()==0){
                    beanDis.setTinhId(beanTinh.getId());
                }
                
                beanDis.setId(bean.getId());
    
                if (beanDis.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(beanDis.getTinhId());
                    List params = new ArrayList();
                    for (int i = beanDis.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        beanDis.setTinhName(beanDis.getTinhName() + " - " + params.get(i));
                    }
                }
                
                request.setAttribute("disability", beanDis);
                request.setAttribute("BTreeDieuKiens", new BDieuKien().getAllRecord(0));
                request.setAttribute("BTreeTinhs", beans);
                request.setAttribute("BSearchTinhs", beans);
                target = anchor;
            } else if (anchor.equals("_PHANLOAI")) {
                request.setAttribute("BNkts", bo.getRecordByID(bean));
                FPhanLoai beantemp = new FPhanLoai();
                beantemp.setIdNkt(bean.getId());
                beantemp.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                request.setAttribute("phanloai", beantemp);
                characters = "";
                member = "";
                String SQL_Nguyennhan = "SELECT nguyennhan_id,parent_id,name FROM dr_nguyennhan WHERE parent_id = ?";
                String SQL_Mucdo = "SELECT mucdo_id,parent_id,name FROM dr_mucdo WHERE parent_id = ?";
                
                FBeans NguyennhanBeans = new FBeans();
                FBeans MucdoBeans = new FBeans();
                NguyennhanBeans = new BTreeView().getTree(0, false, SQL_Nguyennhan, characters, member);
                MucdoBeans = new BTreeView().getTree(0, false, SQL_Mucdo, characters, member);
                
                request.setAttribute("BTreeNguyennhans", NguyennhanBeans);
                request.setAttribute("BTreeMucdos", MucdoBeans);
                request.setAttribute("BPhanLoais", new BDangTat().getAllRecord(0));
                request.setAttribute("BPhanLoaiTrailers", new BPhanLoai().getAllByIdNkt(bean.getId()));
                target = anchor;
            } else if (anchor.equals("_TINHHINH")) {
                request.setAttribute("BNkts", bo.getRecordByID(bean));
                FRank beantemp = new FRank();
                beantemp.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                beantemp.setIdNkt(bean.getId());
                request.setAttribute("rank", beantemp);
                characters = "";
                member = "";
                String SQL_HOTRO = "SELECT tinhtrang_id,parent_id,name FROM DR_TINHTRANG WHERE parent_id = ? order by order_by";
                beans = new FBeans();            
                beans = new BTreeView().getTreeList(0, SQL_HOTRO, characters, member);
                request.setAttribute("BRanks", beans);
                request.setAttribute("BRankTrailers", new BRank().getAllByIdNkt(bean.getId()));
                target = anchor;
            } else if (anchor.equals("_DANHGIA")) {
                FDanhGia beanDG = new FDanhGia();
                BDanhGia boDG = new BDanhGia();
                
                beanDG.setIdNkt(bean.getId());
                beanDG.setNamDanhGia(bean.getYear(bean.getCurrentSqlDate()));
                beanDG.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                beanDG.setKyDanhGia((bean.getMonth(bean.getCurrentSqlDate())>5) ? 2 :1);
                
                request.setAttribute("danhgiaht", beanDG);
                request.setAttribute("BDanhGias", boDG.getAllByNktId(beanDG.getIdNkt(), 
                                                            beanDG.getKyDanhGia(), 
                                                            beanDG.getNamDanhGia()));
                target = anchor;
                
            } else if (anchor.equals("_HOTRO")) {
    
                request.setAttribute("BNkts", bo.getRecordByID(bean));
                FSupport beantemp = new FSupport();
                beantemp.setIdNkt(bean.getId());
                beantemp.setDateCreate(bean.dateToString(bean.getCurrentDate()));
                beantemp.setDateForm(bean.dateToString(bean.getCurrentDate()));
                beantemp.setDateTo(bean.dateToString(bean.getCurrentDate()));
                beantemp.setStatusId(bean.getType());
                request.setAttribute("support", beantemp);
                request.setAttribute("statusId", beantemp.getStatusId());
                
                characters = "";
                member = "";
                String SQL_HOTRO = "SELECT hotro_id,parent_id,name FROM DR_HOTRO WHERE parent_id = ? order by order_by";
                String SQL_NGUONHOTRO = "SELECT nguonhotro_id,parent_id,name FROM DR_NGUONHOTRO WHERE parent_id = ? ORDER BY position";
                
                FBeans HoTrobeans = new FBeans();
                FBeans NguonHoTrobeans = new FBeans();
                HoTrobeans = new BTreeView().getTreeList(0, SQL_HOTRO, characters, member);            
                NguonHoTrobeans = new BTreeView().getTreeList(0, SQL_NGUONHOTRO, characters, member);
                request.setAttribute("BSupports", HoTrobeans);
                request.setAttribute("BNguonHoTros", NguonHoTrobeans);
                request.setAttribute("BSupportTrailers", new BSupport().getAllByIdNkt(bean.getId(), bean.getType()));
                target = anchor;
    
            } else if (anchor.equals("_TN_NKT")) {
    
                FRelative beantemp = new FRelative();
                BDisability b = new BDisability();
                FDisability bTem = new FDisability();
                bTem = b.getRecordByID(bean);
                beantemp.setIdNkt(bean.getId());
    
                request.setAttribute("BRelativeNkts", b.getAllSearch(bTem));
                request.setAttribute("BLyDos", new BQuanhe().getAllRecord(0));
                request.setAttribute("BRelatives", new BRelative().getAllByIdNkt(bean.getId()));
                request.setAttribute("relative", beantemp);
    
                target = anchor;
    
            } else if (anchor.equals("_TT_NKT")) {
    
                FInforNKT beanT = new FInforNKT();
                beanT.setNktId(bean.getId());
                request.setAttribute("infor", beanT);
                request.setAttribute("srcString", new BInforNKT().getSRC(bean.getId()));
                request.setAttribute("BTemps", new BInforNKT().getAllTemp(beanT));
                target = anchor;
    
            } else if (anchor.equals("_REPORT")) {
    
                String FileName = IKeyDisability.REPORT_FILE_DISABILITY_HUONGLOI;
                FDisability beanT = new FDisability();
                beanT = bo.getRecordByIDHuongLoi(bean);              
                beanT.setTinhName("");
                if (beanT.getTinhId() > 0) {
                    FTinh beanCa = new FTinh();
                    beanCa.setParentID(beanT.getTinhId());
                    List params = new ArrayList();
                    for (int i = beanT.getTinhId(); i > 0; 
                         i = beanCa.getParentID()) {
                        beanCa.setId(beanCa.getParentID());
                        beanCa = new BTinh().getRecordByID(beanCa);
                        params.add(beanCa.getName());
                    }
                    for (int i = params.size() - 1; i > -1; i--) {
                        beanT.setTinhName(beanT.getTinhName() + " - " + params.get(i));
                    }
                }
    
                String report;
                try {
                    report = new DReportHuongLoi().excelHuongLoi(bean, beanT, FileName);            
                    bean.download(report, FileName, null);
                    bean.deleteFile(report);
                } catch (NestableException e) {
                    e.printStackTrace();
                }
                target = null;
            }
            
            FBeans beansDoiTuong = new FBeans();
            String SQL_DoiTuong="SELECT doituong_id,parent_id,name FROM dr_doituong WHERE parent_id = ?";        
            beansDoiTuong = new BTreeView().getTree(0, false, SQL_DoiTuong,characters,"");
    
            request.setAttribute("BDieuKiens", new BDieuKien().getAllRecord(0));
            request.setAttribute("BDantocForms", new BDantoc().getAllRecord(0));
            request.setAttribute("BDoiTuongForms", beansDoiTuong);
    
            if (!errors.isEmpty())
                saveErrors(request, errors);
            return mapping.findForward(target);
        } catch (EException ex) {
            logger.info(ex.toString());
            return mapping.findForward(_ERROR);
        }
    }

    private String validate(FDisability bean, String anchor, 
                            ActionErrors errors) {
        if (anchor.equals(_EDIT) || anchor.equals(_CREATE)) {
            if ((bean.getNkt().trim().equals(_BLANK)) || (bean.getMa().trim().equals(_BLANK)) 
                || (bean.getNgaySinh().trim().equals(_BLANK)) || (bean.getDateLastUpdate().trim().equals(_BLANK))) {

                errors.add("alert", new ActionError("errors.disability.edit.short"));

            } else if (bean.isDate(bean.getDateLastUpdate()) && bean.isDate(bean.getNgaySinh()) && 
                bean.getDays(bean.stringToSqlDate(bean.getNgaySinh()), bean.stringToSqlDate(bean.getDateLastUpdate())) < 0) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.date.erro"));

            } else if (bean.getTongSoCon() < bean.getTongSoConDuoi16()) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.tongsocon.tongsoconduoi16.erro"));

            } else if (bean.getSonguoi_chuho() < bean.getSoNKT_chuho()) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.songuoichuho.songuoiKTchuho"));

            } else if (!bean.isInteger(String.valueOf(bean.getNamSinhChamSoc()))) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.namsinhnguoichamsoc.invalid"));

            } else if (bean.getNamSinhChamSoc() >= bean.getYear(bean.stringToSqlDate(bean.getDateLastUpdate()))) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.namsinhnguoichamsoc.erro"));

            } else if (!bean.isInteger(String.valueOf(bean.getNamSinhChamSoc()))) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.namsinhchuho.invalid"));

            } else if (bean.getNamSinhChuHo() >= bean.getYear(bean.stringToSqlDate(bean.getDateLastUpdate()))) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.namsinhchuho.erro"));

            } else if (!bean.isDate(bean.getNgaySinh()) && bean.isInteger(bean.getNgaySinh()) && 
                       bean.isDate(bean.getDateLastUpdate())) {

                if (Integer.parseInt(bean.getNgaySinh()) >= bean.getYear(bean.stringToSqlDate(bean.getDateLastUpdate()))) {
                    errors.add("alert", new ActionError("errors.disability.cpmpare.date.year.date.erro"));                               
                }

            } else if (!bean.isDate(bean.getNgaySinh())) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.date.invalid"));

            } else if (!bean.isInteger(bean.getNgaySinh()) && !bean.isDate(bean.getNgaySinh())) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.date.year.date.not.suscess.erro"));

            } else if (!bean.isDate(bean.getNgaySinh()) && !bean.isInteger(bean.getNgaySinh())) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.date.year.date.not.suscess.erro"));

            } else if (!bean.isDate(bean.getNgaySinh()) && bean.getNgaySinh().length() != 4) {

                errors.add("alert", new ActionError("errors.disability.cpmpare.date.year.date.not.suscess.erro"));
            }
        }
        return anchor;
    }

    private String encodeFileName(FDisability bean, long userID) {
        String fileread = "";
        try {
            fileread = 
                    new String((bean.getUpFile().getFileName().getBytes()), "UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
        String doiFile = 
            fileread.substring(fileread.indexOf("."), fileread.length());
        return userID + "." + System.currentTimeMillis() + doiFile;
    }

}
