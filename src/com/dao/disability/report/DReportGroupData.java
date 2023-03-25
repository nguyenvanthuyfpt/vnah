package com.dao.disability.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.report.FReportGroup;
import com.form.disability.report.FReportTotal;

import com.inf.disability.IKeyDisability;

import com.lib.AppConfigs;

import com.util.Constant;
import com.util.Formater;
import com.util.RomanNumeral;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportGroupData extends FExportExcel {
    public String ReportExcel(FReportGroup beanTemp, FSeed seed, 
                              String excelFile) throws EException, 
                                                       FileNotFoundException, 
                                                       IOException {
        final String LOCATION = this.toString() + "->ReportGroupData()";
        FReportTotal bean = (FReportTotal)seed;
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
        String excelDown = excelPath + seed.me.getSessionID();
        File file = new File(excelPath, excelFile);
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
        int sheet_index = 0;
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(sheet_index);
        sheet.setAutobreaks(true);
        
        HSSFFont f11 = getFont(wb, "Times New Roman", 11, false);
        HSSFFont fb11 = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fb12 = getFont(wb, "Times New Roman", 11, true);
        HSSFFont fb10 = getFont(wb, "Times New Roman", 10, true);
        HSSFFont fb13 = getFont(wb, "Times New Roman", 13, true);
        
        CellStyle cs_normal = getStyle(wb, f11, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_none = getStyle(wb, f11, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_bold = getStyle(wb, fb12, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_bold_center = getStyle(wb, fb12, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);

        CellStyle cs_center = getStyle(wb, f11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_bold_header = getStyle(wb, fb10, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_header = getStyle(wb, f11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_title = getStyle(wb, fb13, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle cs_right = getStyle(wb, f11, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        String[] arr_char = {"C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W"};
        String formula = "";
        String report_type = bean.getReportType();
        
        cs_bold_header.setWrapText(true);
        cs_normal.setWrapText(true);
        
        createCell(sheet.getRow(0),0, bean.ncrToString(Constant.report_donvi_1).toUpperCase() , wb, cs_header);
        
        if("01|02|03|04|05|06|08|12|13".indexOf(report_type)>-1) {
            createCell(sheet.getRow(0), 7, bean.ncrToString("Bi&#7875;u") + " " + report_type, wb, cs_header);
        } else if("09|10".equals(report_type)) {
            createCell(sheet.getRow(0), 10, bean.ncrToString("Bi&#7875;u") + " " + report_type, wb, cs_header);
        } else if ("17|18|19".indexOf(report_type)>-1) {
            createCell(sheet.getRow(0), 20, bean.ncrToString("Bi&#7875;u") + " " + report_type, wb, cs_header);
        } 
        
        //createCell(sheet.getRow(1),0, bean.ncrToString(Constant.report_donvi_2).toUpperCase() , wb, cs_header);
        //createCell(sheet.getRow(2),0, bean.ncrToString(Constant.report_donvi_3).toUpperCase() , wb, cs_header);
        
        if ("01".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_1).toUpperCase(), wb, cs_title);
        else if ("02".equals(bean.getReportType()))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_2).toUpperCase(), wb, cs_title);
        else if ("03".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_3).toUpperCase(), wb, cs_title);
        else if ("04".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_4).toUpperCase(), wb, cs_title);
        else if ("05".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_5).toUpperCase(), wb, cs_title);
        else if ("06".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_6).toUpperCase(), wb, cs_title);        
        else if ("08".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_8).toUpperCase(), wb, cs_title);
        else if ("09".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_9).toUpperCase(), wb, cs_title);
        else if ("10".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_10).toUpperCase(), wb, cs_title);        
        else if ("12".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_12).toUpperCase(), wb, cs_title);
        else if ("13".equals(report_type))
            createCell(sheet.getRow(3), 0, bean.ncrToString(Constant.report_title_13).toUpperCase(), wb, cs_title);
        else if ("17".equals(report_type))
            createCell(sheet.getRow(4), 0, bean.ncrToString(Constant.report_title_17).toUpperCase(), wb, cs_title);
        else if ("18".equals(report_type))
            createCell(sheet.getRow(4), 0, bean.ncrToString(Constant.report_title_18).toUpperCase(), wb, cs_title);
        else if ("19".equals(report_type))
            createCell(sheet.getRow(4), 0, bean.ncrToString(Constant.report_title_19).toUpperCase(), wb, cs_title);
        
        String tuyen_bcao = bean.getTinhName();
        int index = -1;
        index = bean.getTinhName().lastIndexOf("-");
        if(index>-1){
            tuyen_bcao =bean.getTinhName().substring(index, bean.getTinhName().length()).trim(); 
        }
        
        if("01|02|03|04|05|06|07|08|09|10|11|12|13|14|15|20".indexOf(report_type)>-1)             
            createCell(sheet.getRow(5), 0, tuyen_bcao.toUpperCase(), wb, cs_title);
        else 
            createCell(sheet.getRow(6), 0, tuyen_bcao.toUpperCase(), wb, cs_title);
        
        String tinh_id = "", diaban = "", v_tinh_id="";        
        int stt = 1,dong = 0;
        if("01|02|03|04|05|06|08|09|10|11|12|13".indexOf(report_type)>-1) {
            dong = 9;
        } else if ("07".equals(report_type)) {
            dong = 10;
        }
        FReportGroup beanG = null;
        HSSFRow row = null;
        
        int i=0,r_diaban=7,j=1,r=0;
        boolean is_new = false;
        RomanNumeral roman = null;
        
        if("01|02|03|04|05|06|07|08|09|10|11|12|13".indexOf(report_type)>-1) {        
            beanG = (FReportGroup)beanTemp.getStore().get(0);
            tinh_id = beanG.getTinhId();
            diaban = beanG.getDiaBan();
            
            while (i<beanTemp.getStore().size()){
                int cot = 0;            
                if(i==0||is_new){
                    roman = new RomanNumeral(j);
                    row = sheet.createRow(r_diaban);
                    createCell(row, 0, roman.toString(), wb, cs_bold_center);
                    createRegion(wb, row, r_diaban, r_diaban, 1, 3, cs_bold, diaban, 0, 0);
                    
                    if(is_new){
                        row = sheet.createRow(r_diaban+1);
                        row.setHeightInPoints(40);
                        if("01|02|03|04|05|06|08|12|13".indexOf(report_type)>-1) {
                            createCell(row, 0, "STT", wb, cs_bold_header);
                            createCell(row, 1, bean.ncrToString("H&#7884; T&#202;N"), wb, cs_bold_header);
                            createCell(row, 2, bean.ncrToString("N&#258;M SINH"), wb, cs_bold_header);
                            createCell(row, 3, bean.ncrToString("N&#7918;"), wb, cs_bold_header);
                            createCell(row, 4, bean.ncrToString("H&#7896; V&#192; T&#202;N CH&#7910; H&#7896;"), wb, cs_bold_header);
                            createCell(row, 5, bean.ncrToString("&#272;&#7882;A CH&#7880; S&#7888; NH&#192;"), wb, cs_bold_header);
                            createCell(row, 6, bean.ncrToString("TH&#212;N-T&#7892;-&#7844;P-KHU PH&#7888;"), wb, cs_bold_header);
                            createCell(row, 7, bean.ncrToString("D&#7840;NG KHUY&#7870;T T&#7852;T"), wb, cs_bold_header);
                            createCell(row, 8, bean.ncrToString("M&#7912;C &#272;&#7896; KHUY&#7870;T T&#7852;T"), wb, cs_bold_header);
                        } else if ("11".equals(report_type)){
                            createCell(row, 0, "STT", wb, cs_bold_header);
                            createCell(row, 1, bean.ncrToString("H&#7884; T&#202;N"), wb, cs_bold_header);
                            createCell(row, 2, bean.ncrToString("N&#258;M SINH"), wb, cs_bold_header);
                            createCell(row, 3, bean.ncrToString("N&#7918;"), wb, cs_bold_header);
                            createCell(row, 4, bean.ncrToString("H&#7896; V&#192; T&#202;N CH&#7910; H&#7896;"), wb, cs_bold_header);
                            createCell(row, 5, bean.ncrToString("&#272;&#7882;A CH&#7880; S&#7888; NH&#192;"), wb, cs_bold_header);
                            createCell(row, 6, bean.ncrToString("TH&#212;N-T&#7892;-&#7844;P-KHU PH&#7888;"), wb, cs_bold_header);
                            createCell(row, 7, bean.ncrToString("TR&#204;NH &#272;&#7896; V&#258;N H&#211;A"), wb, cs_bold_header);
                            createCell(row, 8, bean.ncrToString("NHU C&#7846;U H&#7884;C V&#258;N H&#211;A"), wb, cs_bold_header);
                            createCell(row, 9, bean.ncrToString("GHI CH&#218;"), wb, cs_bold_header);
                        } else if ("07".equals(report_type)) {
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 0, 0, cs_bold_header, "STT", 1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 1, 1, cs_bold_header, bean.ncrToString("H&#7884; T&#202;N"),1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 2, 2, cs_bold_header, bean.ncrToString("N&#258;M SINH"),1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 3, 3, cs_bold_header, bean.ncrToString("N&#7918;"),1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 4, 4, cs_bold_header, bean.ncrToString("H&#7896; V&#192; T&#202;N CH&#7910; H&#7896;"),1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 5, 5, cs_bold_header, bean.ncrToString("&#272;&#7882;A CH&#7880; S&#7888; NH&#192; - &#7844;P - KP"),1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 5, 5, cs_bold_header, bean.ncrToString("TH&#212;N-T&#7892;-&#7844;P-KHU PH&#7888;"),1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+1, 6, 6, cs_bold_header, bean.ncrToString("T&#204;NH TR&#7840;NG NH&#192; &#7902; KH&#211; KH&#258;N"),1, 255);                        
                            createCell(sheet.getRow(r_diaban+2), 7, bean.ncrToString("NH&#192; T&#7840;M"), wb, cs_bold_header);                        
                            createCell(sheet.getRow(r_diaban+2), 8, bean.ncrToString("NH&#192; B&#193;N KI&#202;N C&#7888;"), wb, cs_bold_header);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 9, 9, cs_bold_header, bean.ncrToString("NGUY&#7878;N V&#7884;NG, KI&#7870;N NGH&#7882;"),1, 255);
                            createRegion(wb, row, r_diaban+1, r_diaban+2, 10, 10, cs_bold_header, bean.ncrToString("GHI CH&#218;"),1, 255);
                        } else if ("09|10".indexOf(report_type)>-1) {
                            createCell(row, 0, "STT", wb, cs_bold_header);
                            createCell(row, 1, bean.ncrToString("H&#7884; T&#202;N"), wb, cs_bold_header);
                            createCell(row, 2, bean.ncrToString("N&#258;M SINH"), wb, cs_bold_header);
                            createCell(row, 3, bean.ncrToString("N&#7918;"), wb, cs_bold_header);
                            createCell(row, 4, bean.ncrToString("H&#7896; V&#192; T&#202;N CH&#7910; H&#7896;"), wb, cs_bold_header);
                            createCell(row, 5, bean.ncrToString("&#272;&#7882;A CH&#7880; S&#7888; NH&#192;"), wb, cs_bold_header);
                            createCell(row, 6, bean.ncrToString("TH&#212;N-T&#7892;-&#7844;P-KHU PH&#7888;"), wb, cs_bold_header);
                            createCell(row, 7, bean.ncrToString("TR&#204;NH &#272;&#7896; V&#258;N H&#211;A"), wb, cs_bold_header);
                            createCell(row, 8, bean.ncrToString("TR&#204;NH &#272;&#7896; CHUY&#202;N M&#212;N"), wb, cs_bold_header);
                            createCell(row, 9, bean.ncrToString("NGH&#7872; MU&#7888;N H&#7884;C"), wb, cs_bold_header);
                            createCell(row, 10, bean.ncrToString("VI&#7878; L&#192;M"), wb, cs_bold_header);
                        }
                    }
                    j++;
                }
                
                beanG = (FReportGroup)beanTemp.getStore().get(i);
                v_tinh_id = beanG.getTinhId();
                row = sheet.createRow(dong);
                if("01|02|03|04|05|06|08|12|13".indexOf(report_type)>-1) {
                    createCell(row, (cot++), stt, wb, cs_right);
                    createCell(row, (cot++), beanG.getHotenNKT(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getNamSinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getGioiTinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getHotenCH(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getDiaChi(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getThonTo(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getDangTat(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getMucDoKT(), wb, cs_normal);
                } else if ("11".equals(report_type)) {
                    createCell(row, (cot++), stt, wb, cs_right);
                    createCell(row, (cot++), beanG.getHotenNKT(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getNamSinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getGioiTinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getHotenCH(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getDiaChi(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getThonTo(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getTd_vanhoa(), wb, cs_normal);
                    createCell(row, (cot++), "", wb, cs_normal);
                    createCell(row, (cot++), beanG.getGhichu(), wb, cs_normal);
                } else if ("07".equals(report_type)) {
                    createCell(row, (cot++), stt, wb, cs_right);
                    createCell(row, (cot++), beanG.getHotenNKT(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getNamSinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getGioiTinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getHotenCH(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getDiaChi(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getThonTo(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getNhatam(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getNhakienco(), wb, cs_normal);
                    createCell(row, (cot++), "", wb, cs_normal);
                    createCell(row, (cot++), "", wb, cs_normal);                    
                } else if ("09|10".indexOf(report_type)>-1) {
                    createCell(row, (cot++), stt, wb, cs_right);
                    createCell(row, (cot++), beanG.getHotenNKT(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getNamSinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getGioiTinh(), wb, cs_center);
                    createCell(row, (cot++), beanG.getHotenCH(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getDiaChi(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getThonTo(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getTd_vanhoa(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getTd_chuyenmon(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getNghemuonhoc(), wb, cs_normal);
                    createCell(row, (cot++), beanG.getVieclam(), wb, cs_normal);    
                }
                if(!v_tinh_id.equalsIgnoreCase(tinh_id)){
                    tinh_id = v_tinh_id;
                    diaban = beanG.getDiaBan();
                    is_new = true;
                    if("01|02|03|04|05|06|08|09|10|11|12|13".indexOf(report_type)>-1) {
                        r_diaban = dong+2;                    
                        dong=dong+4;                    
                    } else {
                        r_diaban = dong+3;                    
                        dong=dong+6;
                    }                
                    stt = 1;
                } else {
                    is_new = false;
                    dong++;
                    stt++;
                }
                i++;
                r++;
                if(r>=60000){
                    sheet = wb.getSheetAt(sheet_index++);
                    r=0;
                }
            }
                    
            String total_nkt = Formater.num2str(beanTemp.getTotal());        
            String current_date = bean.dateToString(bean.getCurrentDate());
            String date[] = current_date.split("/");
            String date_time = bean.ncrToString(IKeyDisability.REPORT_FOOTER_POSITION_3).replace("{0}", date[0]).replace("{1}", date[1]).replace("{2}", date[2]);        
            
            row = sheet.createRow(dong+2);
            createRegion(wb, row, dong+2, dong+2, 0, 2, cs_bold, bean.ncrToString("T&#7893;ng s&#7889; ng&#432;&#7901;i trong danh s&#225;ch l&#224;: ") + total_nkt, 0, 0);
            int inc = 0;
            if("7".equals(report_type)) {
                inc = 1;
            }            
                                           
            /*
            row = sheet.createRow(dong+4);
            createRegion(wb, row, dong+4, dong+4, 0, 1, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_POSITION_1).toUpperCase(), 0, 0);
            createRegion(wb, row, dong+4, dong+4, 3, 4, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_POSITION_2).toUpperCase(), 0, 0);
            createRegion(wb, row, dong+4, dong+4, 6, 7+inc, cs_header, date_time, 0, 0);       
            
            row = sheet.createRow(dong+5);                
            createRegion(wb, row, dong+5, dong+5, 3, 4, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_ROLE_1).toUpperCase(), 0, 0);
            createRegion(wb, row, dong+5, dong+5, 6, 7+inc, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_ROLE_2).toUpperCase(), 0, 0);
            
            row = sheet.createRow(dong+10);        
            createRegion(wb, row, dong+10, dong+10, 3, 4, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_SIGNER_1).toUpperCase(), 0, 0);
            createRegion(wb, row, dong+10, dong+10, 6, 7+inc, cs_header, bean.ncrToString(IKeyDisability.REPORT_FOOTER_SIGNER_2).toUpperCase(), 0, 0);        
            */
        } else if ("14".equals(report_type)){    // Report_type = 14
            dong = 9;
            while (i<beanTemp.getStore().size()){
                beanG = (FReportGroup)beanTemp.getStore().get(i);
                int cot = 0;            
                row = sheet.createRow(dong);
                createCell(row, cot++, stt, wb, cs_right);
                createCell(row, cot++, beanG.getDiaBan(), wb, cs_normal);
                createCell(row, cot++, beanG.getTotal(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_m(), wb, cs_right);                
                createCell(row, cot++, beanG.getTotal_f(), wb, cs_right);
                
                createCell(row, cot++, beanG.getTotal_0_6(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_m_0_6(), wb, cs_right);                
                createCell(row, cot++, beanG.getTotal_f_0_6(), wb, cs_right);
                
                createCell(row, cot++, beanG.getTotal_6_15(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_m_6_15(), wb, cs_right);                
                createCell(row, cot++, beanG.getTotal_f_6_15(), wb, cs_right);
                
                createCell(row, cot++, beanG.getTotal_15_60(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_m_15_60(), wb, cs_right);                
                createCell(row, cot++, beanG.getTotal_f_15_60(), wb, cs_right);
                
                createCell(row, cot++, beanG.getTotal_60_80(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_m_60_80(), wb, cs_right);                
                createCell(row, cot++, beanG.getTotal_f_60_80(), wb, cs_right);
                
                createCell(row, cot++, beanG.getTotal_80(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_m_80(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_f_80(), wb, cs_right);
                
                createCell(row, cot++, beanG.getGhichu(), wb, cs_normal);
                i++;
                stt++;
                dong++;
            }
            
            formula = "SUM(@10:@#)";        
            int cot = 2;            
            i = 0;
            row = sheet.createRow(dong);
            createRegion(wb, row, dong, dong, 0, 1, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);            
            while (cot<18){                
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                if (cot==17)    createCell(row, cot++, "", wb, cs_normal);
                else            createCellFormula(row, cot++, formula, wb, cs_right);
                formula = "SUM(@11:@#)";
            }            
        } else if ("15".equals(report_type)) {
            dong = 10;
            while (i<beanTemp.getStore().size()){
                beanG = (FReportGroup)beanTemp.getStore().get(i);
                int cot = 0;            
                row = sheet.createRow(dong);
                createCell(row, cot++, stt, wb, cs_right);
                createCell(row, cot++, beanG.getDangTat(), wb, cs_normal);
                createCell(row, cot++, beanG.getTotal(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_15_60(), wb, cs_right);
                createCell(row, cot++, beanG.getT_bamsinh(), wb, cs_right);                
                createCell(row, cot++, beanG.getT_benh(), wb, cs_right);
                createCell(row, cot++, beanG.getT_laodong(), wb, cs_right);
                createCell(row, cot++, beanG.getT_giaothong(), wb, cs_right);
                createCell(row, cot++, beanG.getT_chientranh(), wb, cs_right);
                createCell(row, cot++, beanG.getT_khac(), wb, cs_right);                
                createCell(row, cot++, beanG.getTotal_60(), wb, cs_right);
                createCell(row, cot++, beanG.getT_60_bamsinh(), wb, cs_right);
                createCell(row, cot++, beanG.getT_60_benh(), wb, cs_right);
                createCell(row, cot++, beanG.getT_60_laodong(), wb, cs_right);
                createCell(row, cot++, beanG.getT_60_giaothong(), wb, cs_right);
                createCell(row, cot++, beanG.getT_60_chientranh(), wb, cs_right);
                createCell(row, cot++, beanG.getT_60_khac(), wb, cs_right);
                i++;
                stt++;
                dong++;
            }
            
            formula = "SUM(@11:@#)";        
            int cot = 2;            
            i = 0;
            row = sheet.createRow(dong);
            createRegion(wb, row, dong, dong, 0, 1, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);            
            while (cot<17){                
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                createCellFormula(row, cot++, formula, wb, cs_right);
                formula = "SUM(@12:@#)";
            }      
        } else if ("16".equals(report_type)) {
            dong = 10;
            while (i<beanTemp.getStore().size()){
                beanG = (FReportGroup)beanTemp.getStore().get(i);
                int cot = 0;            
                row = sheet.createRow(dong);
                createCell(row, cot++, stt, wb, cs_right);
                createCell(row, cot++, beanG.getDiaBan(), wb, cs_normal);
                createCell(row, cot++, beanG.getTotal(), wb, cs_right);
                createCell(row, cot++, beanG.getTotal_yte(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_1(), wb, cs_right);                
                createCell(row, cot++, beanG.getHotro_2(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_3(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_4(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_5(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_6(), wb, cs_right);                
                createCell(row, cot++, beanG.getNhucau_1(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_2(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_3(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_4(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_5(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_6(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_7(), wb, cs_right);
                createCell(row, cot++, beanG.getGhichu(), wb, cs_right);
                i++;
                stt++;
                dong++;
            }
            
            formula = "SUM(@11:@#)";        
            int cot = 2;            
            i = 0;
            row = sheet.createRow(dong);
            createRegion(wb, row, dong, dong, 0, 1, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);            
            while (cot<18){                
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                if(cot==17) createCell(row, cot++, "", wb, cs_normal);
                else        createCellFormula(row, cot++, formula, wb, cs_right);
                formula = "SUM(@11:@#)";
            }
        } else if ("17|18|19".indexOf(report_type)>-1) {
            dong = 10;
            while (i<beanTemp.getStore().size()){
                beanG = (FReportGroup)beanTemp.getStore().get(i);
                int cot = 0;            
                row = sheet.createRow(dong);
                createCell(row, cot++, stt, wb, cs_right);
                createCell(row, cot++, beanG.getDiaBan(), wb, cs_normal);
                createCell(row, cot++, beanG.getTotal(), wb, cs_right);
                createCell(row, cot++, beanG.getDantoc_kinh(), wb, cs_right);
                createCell(row, cot++, beanG.getDantoc_khac(), wb, cs_right);                
                createCell(row, cot++, beanG.getTrinhdo_1(), wb, cs_right);
                createCell(row, cot++, beanG.getTrinhdo_2(), wb, cs_right);
                createCell(row, cot++, beanG.getTrinhdo_3(), wb, cs_right);
                createCell(row, cot++, beanG.getTrinhdo_4(), wb, cs_right);
                createCell(row, cot++, beanG.getTrinhdo_5(), wb, cs_right);
                createCell(row, cot++, beanG.getTrinhdo_6(), wb, cs_right);                
                createCell(row, cot++, beanG.getHotro_1(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_2(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_3(), wb, cs_right);
                createCell(row, cot++, beanG.getHotro_4(), wb, cs_right);                
                createCell(row, cot++, beanG.getNhucau_1(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_2(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_3(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_4(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_5(), wb, cs_right);
                createCell(row, cot++, beanG.getNhucau_6(), wb, cs_right);
                i++;
                stt++;
                dong++;
            }
            
            formula = "SUM(@11:@#)";        
            int cot = 2;            
            i = 0;
            row = sheet.createRow(dong);
            createRegion(wb, row, dong, dong, 0, 1, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);            
            while (cot<21){                
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                createCellFormula(row, cot++, formula, wb, cs_right);
                formula = "SUM(@11:@#)";
            } 
        } else if ("20".equals(report_type)) {
            dong = 9;
            while (i<beanTemp.getStore().size()){
                beanG = (FReportGroup)beanTemp.getStore().get(i);
                int cot = 0;            
                row = sheet.createRow(dong);
                createCell(row, cot++, stt, wb, cs_right);
                createCell(row, cot++, beanG.getDiaBan(), wb, cs_normal);
                createCell(row, cot++, beanG.getTotal(), wb, cs_right);
                createCell(row, cot++, beanG.getDangtat_1(), wb, cs_right);
                createCell(row, cot++, beanG.getDangtat_2(), wb, cs_right);                
                createCell(row, cot++, beanG.getDangtat_3(), wb, cs_right);
                createCell(row, cot++, beanG.getDangtat_4(), wb, cs_right);
                createCell(row, cot++, beanG.getDangtat_5(), wb, cs_right);
                createCell(row, cot++, beanG.getDangtat_6(), wb, cs_right);                
                createCell(row, cot++, beanG.getMucdo_1(), wb, cs_right);
                createCell(row, cot++, beanG.getMucdo_2(), wb, cs_right);
                createCell(row, cot++, beanG.getMucdo_3(), wb, cs_right);
                createCell(row, cot++, beanG.getGhichu(), wb, cs_right);
                i++;
                stt++;
                dong++;
            }
            formula = "SUM(@10:@#)";        
            int cot = 2;            
            i = 0;
            row = sheet.createRow(dong);
            createRegion(wb, row, dong, dong, 0, 1, cs_bold_center, bean.ncrToString("T&#7893;ng c&#7897;ng"), 1, 0);            
            while (cot<13){                
                formula = formula.replaceAll("#", "" + dong + "");
                formula = formula.replaceAll("@", arr_char[i++]);
                if (cot==12)    createCell(row, cot++, "", wb, cs_normal);
                else            createCellFormula(row, cot++, formula, wb, cs_right);
                formula = "SUM(@10:@#)";
            } 
        } 
        
        sheet.setHorizontallyCenter(true);
        sheet.setMargin(sheet.TopMargin, 0);
        sheet.setMargin(sheet.BottomMargin, 2.5);
        sheet.setMargin(sheet.LeftMargin, 0.25);
        sheet.setMargin(sheet.RightMargin, 0.25);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }

    public FBeans getData(Connection cnn, int tinh_id, String loai_bc, int from, int to, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "-->getData()";
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        FBeans beans = new FBeans();
        try {            
            CallableStatement state = cnn.prepareCall("{call report_group_nkt(?,?,?,?,?)}");
            state.setInt(1, tinh_id);
            state.setString(2, loai_bc);
            state.setInt(3, from);
            state.setInt(4, to);
            state.setInt(5, qlc);
            state.execute();
            
            FReportGroup bean = new FReportGroup();            
            prpstm = prepareStatement(cnn, SQL_SELECT_REPORT_GROUP_NKT, null);
            prpstm.setInt(1, bean.stringToInt(loai_bc));
            prpstm.setInt(2, tinh_id);
            prpstm.setInt(3, qlc);
            rs = prpstm.executeQuery();
            while (rs!= null && rs.next()){
                bean = new FReportGroup();
                bean.setTinhId(rs.getString("tinh_id"));
                bean.setDiaBan(rs.getString("diaban"));
                bean.setMaNKT(rs.getString("ma_nkt"));
                bean.setHotenNKT(rs.getString("hoten_nkt"));
                bean.setNamSinh(rs.getString("namsinh"));
                bean.setGioiTinh(rs.getString("gioitinh"));
                bean.setHotenCH(rs.getString("hoten_chuho"));
                bean.setDiaChi(rs.getString("diachi"));
                bean.setThonTo(rs.getString("thonto"));
                bean.setDangTat(rs.getString("dangtat"));
                bean.setMucDoKT(rs.getString("mucdo_kt"));                
                bean.setNhatam(rs.getString("nhatam"));
                bean.setNhakienco(rs.getString("nhakienco"));
                bean.setNguyenvong(rs.getString(("nguyenvong")));                
                bean.setTd_vanhoa(rs.getString("td_vanhoa"));
                bean.setNc_vanhoa(rs.getString("nc_vanhoa"));
                bean.setGhichu(rs.getString("ghichu"));
                beans.add(bean);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public FBeans getDataReport14(Connection cnn, int tinh_id, int level, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "-->getDataReport14()";
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        FBeans beans = new FBeans();
        String sql = "";
        if (qlc==0) {     
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_14_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_14_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_14_PX;
        } else {
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_14_QLC_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_14_QLC_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_14_QLC_PX;
        }
        try {
                  
            FReportGroup bean = new FReportGroup();
            prpstm = prepareStatement(cnn, sql, null);
            if (level==3){
                prpstm.setInt(1, tinh_id);    
            } else {
                prpstm.setInt(1, tinh_id);
                prpstm.setInt(2, tinh_id);
            }
            
            rs = prpstm.executeQuery();
            while (rs!= null && rs.next()){
                bean = new FReportGroup();                
                int i = 1;
                bean.setDiaBan(rs.getString(i++));
                i=3;
                bean.setTotal(rs.getInt(i++));
                bean.setTotal_f(rs.getInt(i++));
                bean.setTotal_m(rs.getInt(i++));  
                
                bean.setTotal_0_6(rs.getInt(i++));
                bean.setTotal_f_0_6(rs.getInt(i++));
                bean.setTotal_m_0_6(rs.getInt(i++));
                
                bean.setTotal_6_15(rs.getInt(i++));
                bean.setTotal_f_6_15(rs.getInt(i++));
                bean.setTotal_m_6_15(rs.getInt(i++));
                
                bean.setTotal_15_60(rs.getInt(i++));
                bean.setTotal_f_15_60(rs.getInt(i++));
                bean.setTotal_m_15_60(rs.getInt(i++));
                
                bean.setTotal_60_80(rs.getInt(i++));
                bean.setTotal_f_60_80(rs.getInt(i++));
                bean.setTotal_m_60_80(rs.getInt(i++));
                
                bean.setTotal_80(rs.getInt(i++));
                bean.setTotal_f_80(rs.getInt(i++));
                bean.setTotal_m_80(rs.getInt(i++));
                bean.setGhichu(rs.getString(i++));                
                beans.add(bean);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    } 
    
    public FBeans getDataReport15(Connection cnn, int tinh_id, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "-->getDataReport15()";
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        FBeans beans = new FBeans();
        String sql = "";
        if (qlc==0)     sql = SQL_SELECT_REPORT_GROUP_NKT_15;
        else            sql = SQL_SELECT_REPORT_GROUP_NKT_15_QLC;
        try {
            String str_tinhid = "(" + tinh_id + ")";
            prpstm = prepareStatement(cnn, SQL_SELECT_STR_TINH_ID, null);
            prpstm.setInt(1, tinh_id);
            rs = prpstm.executeQuery();
            while (rs!=null && rs.next()) {
                str_tinhid = rs.getString(1);
            }            
            FReportGroup bean = new FReportGroup();
            prpstm = prepareStatement(cnn, sql.replaceAll("#", str_tinhid), null);            
            rs = prpstm.executeQuery();
            while (rs!= null && rs.next()){
                bean = new FReportGroup();                
                int i = 1;
                bean.setDangTat(rs.getString(i++));
                bean.setTotal(rs.getInt(i++));
                bean.setTotal_15_60(rs.getInt(i++));
                bean.setT_bamsinh(rs.getInt(i++));  
                bean.setT_benh(rs.getInt(i++));
                bean.setT_laodong(rs.getInt(i++));
                bean.setT_giaothong(rs.getInt(i++));
                bean.setT_chientranh(rs.getInt(i++));
                bean.setT_khac(rs.getInt(i++));                
                bean.setTotal_60(rs.getInt(i++));
                bean.setT_60_bamsinh(rs.getInt(i++));  
                bean.setT_60_benh(rs.getInt(i++));
                bean.setT_60_laodong(rs.getInt(i++));
                bean.setT_60_giaothong(rs.getInt(i++));
                bean.setT_60_chientranh(rs.getInt(i++));
                bean.setT_60_khac(rs.getInt(i++));                
                beans.add(bean);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public FBeans getDataReport16(Connection cnn, int tinh_id, int level, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "-->getDataReport16()";
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        FBeans beans = new FBeans();
        String sql = "";
        if (qlc==0) {     
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_16_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_16_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_16_PX;
        } else {
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_16_QLC_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_16_QLC_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_16_QLC_PX;
        }
        try {          
            FReportGroup bean = new FReportGroup();
            if (level==2) {
                prpstm = prepareStatement(cnn, sql, null);
                prpstm.setInt(1, tinh_id);
                prpstm.setInt(2, tinh_id);
            } else {
                prpstm = prepareStatement(cnn, sql, null);
                prpstm.setInt(1, tinh_id);
            }
            rs = prpstm.executeQuery();
            while (rs!= null && rs.next()){
                bean = new FReportGroup();                
                int i = 1;
                bean.setDiaBan(rs.getString(i++));
                i = 3;
                bean.setTotal(rs.getInt(i++));
                bean.setTotal_yte(rs.getInt(i++));
                bean.setHotro_1(rs.getInt(i++));  
                bean.setHotro_2(rs.getInt(i++));
                bean.setHotro_3(rs.getInt(i++));
                bean.setHotro_4(rs.getInt(i++));
                bean.setHotro_5(rs.getInt(i++));
                bean.setHotro_6(rs.getInt(i++));
                bean.setNhucau_1(rs.getInt(i++));
                bean.setNhucau_2(rs.getInt(i++));
                bean.setNhucau_3(rs.getInt(i++));
                bean.setNhucau_4(rs.getInt(i++));
                bean.setNhucau_5(rs.getInt(i++));
                bean.setNhucau_6(rs.getInt(i++));
                bean.setNhucau_7(rs.getInt(i++));
                bean.setGhichu(rs.getString(i++));                
                beans.add(bean);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public FBeans getDataReport171819(Connection cnn, int tinh_id, int level, int from, int to, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "-->getDataReport171819()";
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        FBeans beans = new FBeans();
        String sql = "";
        
        if (qlc==0) {     
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_171819_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_171819_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_171819_PX;
        } else {
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_171819_QLC_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_171819_QLC_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_171819_QLC_PX;
        }
        
        try {
            String str_tinhid = "(" + tinh_id + ")";
            prpstm = prepareStatement(cnn, SQL_SELECT_STR_TINH_ID, null);
            prpstm.setInt(1, tinh_id);
            rs = prpstm.executeQuery();
            while (rs!=null && rs.next()) {
                str_tinhid = rs.getString(1);
            }            
            FReportGroup bean = new FReportGroup();
            if (level==1){          // TP
                prpstm = prepareStatement(cnn, sql, null);
                prpstm.setInt(1, from);
                prpstm.setInt(2, to);
                prpstm.setInt(3, tinh_id);
            } else if (level==2) {  // Q.H
                prpstm = prepareStatement(cnn, sql.replaceAll("#", str_tinhid), null);
                prpstm.setInt(1, from);
                prpstm.setInt(2, to);
                prpstm.setInt(3, tinh_id);
            } else {                // P.X
                prpstm = prepareStatement(cnn, sql, null);
                prpstm.setInt(1, tinh_id);
                prpstm.setInt(2, from);
                prpstm.setInt(3, to);
            }
           
            rs = prpstm.executeQuery();
            while (rs!= null && rs.next()){
                bean = new FReportGroup();                
                int i = 1;
                bean.setDiaBan(rs.getString(i++));
                i = 3;
                bean.setTotal(rs.getInt(i++));
                bean.setDantoc_kinh(rs.getInt(i++));
                bean.setDantoc_khac(rs.getInt(i++));                  
                bean.setTrinhdo_1(rs.getInt(i++));
                bean.setTrinhdo_2(rs.getInt(i++));
                bean.setTrinhdo_3(rs.getInt(i++));
                bean.setTrinhdo_4(rs.getInt(i++));
                bean.setTrinhdo_5(rs.getInt(i++));
                bean.setTrinhdo_6(rs.getInt(i++));                
                bean.setHotro_1(rs.getInt(i++));
                bean.setHotro_2(rs.getInt(i++));
                bean.setHotro_3(rs.getInt(i++));
                bean.setHotro_4(rs.getInt(i++));                
                bean.setNhucau_1(rs.getInt(i++));
                bean.setNhucau_2(rs.getInt(i++));
                bean.setNhucau_3(rs.getInt(i++));
                bean.setNhucau_4(rs.getInt(i++));
                bean.setNhucau_5(rs.getInt(i++));
                bean.setNhucau_6(rs.getInt(i++));
                beans.add(bean);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)   throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public FBeans getDataReport20(Connection cnn, int tinh_id, int level, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "-->getDataReport20()";
        ResultSet rs = null;
        PreparedStatement prpstm = null;
        FBeans beans = new FBeans();
        String sql = "";
        if (qlc==0) {     
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_20_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_20_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_20_PX;
        } else {
            if (level==1)        sql = SQL_SELECT_REPORT_GROUP_NKT_20_TP;
            else if (level==2)   sql = SQL_SELECT_REPORT_GROUP_NKT_20_QH;
            else                 sql = SQL_SELECT_REPORT_GROUP_NKT_20_PX;
        }
        try {
            FReportGroup bean = new FReportGroup();
            if (level==2) {
                prpstm = prepareStatement(cnn, sql, null);
                prpstm.setInt(1, tinh_id);
                prpstm.setInt(2, tinh_id);
            } else {
                prpstm = prepareStatement(cnn, sql, null);
                prpstm.setInt(1, tinh_id);
            }
            rs = prpstm.executeQuery();
            while (rs!= null && rs.next()){
                bean = new FReportGroup();                
                int i = 1;
                bean.setDiaBan(rs.getString(i++));
                i = 3;
                bean.setTotal(rs.getInt(i++));                
                bean.setDangtat_1(rs.getInt(i++));  
                bean.setDangtat_2(rs.getInt(i++));
                bean.setDangtat_3(rs.getInt(i++));
                bean.setDangtat_4(rs.getInt(i++));
                bean.setDangtat_5(rs.getInt(i++));
                bean.setDangtat_6(rs.getInt(i++));
                bean.setMucdo_1(rs.getInt(i++));
                bean.setMucdo_2(rs.getInt(i++));
                bean.setMucdo_3(rs.getInt(i++));
                bean.setGhichu(rs.getString(i++));                
                beans.add(bean);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
}