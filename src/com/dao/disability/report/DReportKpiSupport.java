package com.dao.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.FThongTinTuyen;
import com.form.disability.report.FReportAnalysis;
import com.form.disability.report.FReportKpiSupport;

import com.lib.AppConfigs;

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

import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportKpiSupport extends FExportExcel{
    
    public String exportExcel(FReportKpiSupport beanTemp, FSeed seed, String excelFile) throws EException, 
                                FileNotFoundException, IOException {
        
        final String LOCATION = this.toString() + "~>excelUnit()";
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + 
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
        
        String excelDown = excelPath + seed.me.getSessionID();
        File file = new File(excelPath, excelFile);        
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);

        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        
        HSSFFont TimesNewRoman11 = getFont(wb, "Times New Roman", 11, false);
        HSSFFont TimesNewRoman10I = getFont(wb, "Times New Roman", 10, false);
        HSSFFont TimesNewRomanB11 = getFont(wb, "Times New Roman", 11, true);
        HSSFFont TimesNewRomanB12 = getFont(wb, "Times New Roman", 12, true);
        HSSFFont TimesNewRoman14 = getFont(wb, "Times New Roman", 14, true);        
        TimesNewRoman10I.setItalic(true);
        
        CellStyle styleBold = getStyle(wb, TimesNewRomanB12, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_boldwrap = getStyle(wb, TimesNewRomanB12, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        cs_boldwrap.setWrapText(true);
        
        CellStyle styleBoldNone = getStyle(wb, TimesNewRomanB12, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle styleBoldRight = getStyle(wb, TimesNewRomanB12, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle styleHeader = getStyle(wb, TimesNewRoman14, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle styleNormal = getStyle(wb, TimesNewRoman11, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle styleNormalCenter = getStyle(wb, TimesNewRoman11, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle styleNormalLeft = getStyle(wb, TimesNewRoman10I, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_CENTER, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        FReportKpiSupport bean = (FReportKpiSupport)seed;        
        FThongTinTuyen beanTuyen    = new FThongTinTuyen();
        
        DTinh daoTinh = new DTinh();
        beanTuyen.setKyBC(bean.getKyBC());
        beanTuyen.setNamBC(bean.getNamBC());
        beanTuyen.setId_tinh(bean.getId_tinh());
        
        Connection cnn = DBConnector.getConnection();        
        String[] arrTinhId  = daoTinh.getTinhIdByParentId(cnn, bean.getId_tinh());
        
        int row = 4;
        int col = 2;
        int pos = arrTinhId.length;
        int total_kt_vdong = 0; // Van Dong
        int total_kt_nnoi = 0;  // Nghe Noi
        int total_kt_nhin = 0;  // Nhin
        int total_kt_ttue = 0;  // Tri Tue
        int total_kt_tthan = 0; // Tam Than
        int total_kt_khac = 0;  // Khac
        int total_nkt = 0;        
        String areaName     = "";
        
        // Row 4
        areaName    = daoTinh.getTinhNameById(cnn, bean.getId_tinh());
        row = 2;
        /*
        if(level==2||level==1){
            if(level==1) {
                createRegion(wb, sheet.createRow(0), 0, 0, pos , pos+3, styleNormalCenter, bean.ncrToString("M&#7851;u 1C"), 0, 0);
                createRegion(wb, sheet.createRow(1), 1, 1, pos , pos+3, styleNormalCenter, bean.ncrToString("(D&#224;nh cho T&#7881;nh/Th&#224;nh ph&#7889;)"), 0, 0);
            } else {
                createRegion(wb, sheet.createRow(0), 0, 0, pos , pos+3, styleNormalCenter, bean.ncrToString("M&#7851;u 1B"), 0, 0);
                createRegion(wb, sheet.createRow(1), 1, 1, pos , pos+3, styleNormalCenter, bean.ncrToString("(D&#224;nh cho Qu&#7853;n/huy&#7879;n)"), 0, 0);                
            }
            
            createRegion(wb, sheet.createRow(2), 2, 2, 0, (pos+3), styleHeader, bean.ncrToString("B&#193;O C&#193;O"), 0, 0);
            createRegion(wb, sheet.createRow(3), 3, 3, 0, (pos+3), styleHeader, bean.ncrToString("PH&#194;N T&#205;CH S&#7888; LI&#7878;U NG&#431;&#7900;I KHUY&#7870;T T&#7852;T") , 0, 0);            
            createRegion(wb, sheet.createRow(7), 7, 8, 0, 0, styleBold, "STT", 1, 0);
            
            if(level==1||level==2){
                createRegion(wb, sheet.getRow(7), 7, 8, 1, 1, styleBold, bean.ncrToString("N&#7897;i dung"), 1, 0);
                createRegion(wb, sheet.getRow(7), 7, 8, 2, 2, styleBold, bean.ncrToString("T&#7893;ng s&#7889;"), 1, 0);
                createRegion(wb, sheet.getRow(7), 7, 8, 3, 3, styleBold, bean.ncrToString("T&#7927; l&#7879;"), 1, 0);
            }
            
            createRegion(wb, sheet.getRow(7), 7, 8, 1, 1, styleBold, bean.ncrToString("N&#7897;i dung"), 1, 0);
            
            if (level==1)                   
                createRegion(wb, sheet.createRow(4), 4, 4, 0, (pos+3), styleBold, bean.ncrToString("T&#7881;nh/Th&#224;nh ph&#7889;: ")+areaName , 0, 0);
            else            
                createRegion(wb, sheet.createRow(4), 4, 4, 0, (pos+3), styleBold, bean.ncrToString("Qu&#7853;n/huy&#7879;n: ")+areaName , 0, 0);
            
            createRegion(wb, sheet.createRow(5), 5, 5, 0, (pos+3), styleBold, bean.ncrToString("&#272;&#7871;n ng&#224;y: ") +  bean.dateToString(bean.getCurrentDate()) , 0, 0);
        } else if (level==3){            
            createRegion(wb, sheet.createRow(4), 4, 4, 0, pos+2, styleBoldNone, bean.ncrToString("X&#227;/ph&#432;&#7901;ng: ")+areaName, 0, 0);            
            createRegion(wb, sheet.createRow(5), 5, 5, 0, pos+2, styleBoldNone, bean.ncrToString("&#272;&#7871;n ng&#224;y: ") +  bean.dateToString(bean.getCurrentDate()), 0, 0);
        }
        
        // Ghi chu
        if(beanTemp.getQuanlyca()==1)
            createCell(sheet.getRow(0), (0), bean.ncrToString("D&#7919; li&#7879;u NKT thu&#7897;c di&#7879;n Qu&#7843;n l&#253; ca"), wb, styleNormalLeft);
        
        // Row 9        
        if(level==2 || level==1){
            col = 4;
            if(level==1)    
                createRegion(wb, sheet.getRow(7), 7, 7, col, 3+pos, styleBold, bean.ncrToString("Chia ra Qu&#7853;n/Huy&#7879;n"), 1, 0);
            else            
                createRegion(wb, sheet.getRow(7), 7, 7, col, 3+pos, styleBold, bean.ncrToString("Chia ra X&#227;/Ph&#432;&#7901;ng"), 1, 0);
            HSSFRow r = sheet.getRow(8);
            r.setHeightInPoints(60);
            for (int i = 0; i < pos; i++) {
                areaName    = daoTinh.getTinhNameById(cnn, Integer.parseInt(arrTinhId[i]));
                createCell(sheet.getRow(8), col, areaName , wb, cs_boldwrap);                
                col++;
            }
        }
            
        bean = (FReportKpiSupport)beanTemp.getStore().get(0);
        String[] arrTotal_NKT = bean.getTotal().split("#");
        String[] arrTotalFemale = bean.getTotalFemale().split("#");
        String[] arrTotalUnder6 = bean.getTotalUnder6().split("#");
        String[] arrTotalUnder16 = bean.getTotalUnder16().split("#");
        String[] arrTotalDacam = bean.getTotalDacam().split("#");
        String[] arrTotalDihoc = bean.getTotalDihoc().split("#");
        String[] arrTotalCoviec = bean.getTotalCoviec().split("#");
        total_nkt = Integer.parseInt(beanTuyen.getTotalPopulation());
         
        
        /*
        //row = 9;
        col = 2;
        for(int i=0;i< arrTotal_NKT.length;i++){
            createCell(sheet.getRow(9),col,  i!=1 ? Formater.num2str(arrTotal_NKT[i]):arrTotal_NKT[i], wb, styleNormal);
            createCell(sheet.getRow(10),col, i!=1 ? Formater.num2str(arrTotalFemale[i]):arrTotalFemale[i], wb, styleNormal);
            createCell(sheet.getRow(11),col, i!=1 ? Formater.num2str(arrTotalUnder6[i]):arrTotalUnder6[i], wb, styleNormal);
            createCell(sheet.getRow(12),col, i!=1 ? Formater.num2str(arrTotalUnder16[i]):arrTotalUnder16[i], wb, styleNormal);
            createCell(sheet.getRow(13),col, i!=1 ? Formater.num2str(arrTotalDacam[i]):arrTotalDacam[i], wb, styleNormal);
            createCell(sheet.getRow(14),col, i!=1 ? Formater.num2str(arrTotalDihoc[i]):arrTotalDihoc[i], wb, styleNormal);
            createCell(sheet.getRow(15),col, i!=1 ? Formater.num2str(arrTotalCoviec[i]):arrTotalCoviec[i], wb, styleNormal);
            col++;            
        }
        
        col = 2;    // Khuyet Tat Van Dong        
        for(int i=0;i<arrTotal_NKT.length;i++){
            if(i==0){
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_1().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_2().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_3().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_4().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_5().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_6().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_7().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_8().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_9().split("#")[0]); 
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_10().split("#")[0]);             
                total_kt_vdong = total_kt_vdong + Integer.parseInt(bean.getParamvalue_11().split("#")[0]);                 
            }
            
            createCell(sheet.getRow(16),col, "", wb, styleNormal);
            if(i==0)        createCell(sheet.getRow(17),col, Formater.num2str(total_kt_vdong), wb, styleBoldRight);
            else if (i==1)  createCell(sheet.getRow(17),col, getPercent(total_kt_vdong, total_nkt), wb, styleBoldRight);
            else            createCell(sheet.getRow(17),col, "", wb, styleNormal);            
            createCell(sheet.getRow(18),col, i!=1 ? Formater.num2str(bean.getParamvalue_1().split("#")[i]):bean.getParamvalue_1().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(19),col, i!=1 ? Formater.num2str(bean.getParamvalue_2().split("#")[i]):bean.getParamvalue_2().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(20),col, i!=1 ? Formater.num2str(bean.getParamvalue_3().split("#")[i]):bean.getParamvalue_3().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(21),col, i!=1 ? Formater.num2str(bean.getParamvalue_4().split("#")[i]):bean.getParamvalue_4().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(22),col, i!=1 ? Formater.num2str(bean.getParamvalue_5().split("#")[i]):bean.getParamvalue_5().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(23),col, i!=1 ? Formater.num2str(bean.getParamvalue_6().split("#")[i]):bean.getParamvalue_6().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(24),col, i!=1 ? Formater.num2str(bean.getParamvalue_7().split("#")[i]):bean.getParamvalue_7().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(25),col, i!=1 ? Formater.num2str(bean.getParamvalue_8().split("#")[i]):bean.getParamvalue_8().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(26),col, i!=1 ? Formater.num2str(bean.getParamvalue_9().split("#")[i]):bean.getParamvalue_9().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(27),col, i!=1 ? Formater.num2str(bean.getParamvalue_10().split("#")[i]):bean.getParamvalue_10().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(28),col, i!=1 ? Formater.num2str(bean.getParamvalue_11().split("#")[i]):bean.getParamvalue_11().split("#")[i], wb, styleNormal);
            col++; 
        }
        
        col = 2;    // Khuyet Tat Nghe Noi
        for(int i=0;i<arrTotal_NKT.length;i++){
            if(i==0){
                total_kt_nnoi = total_kt_nnoi + Integer.parseInt(bean.getParamvalue_12().split("#")[0]);
                total_kt_nnoi = total_kt_nnoi + Integer.parseInt(bean.getParamvalue_13().split("#")[0]);
            }
            createCell(sheet.getRow(29),col, "", wb, styleNormal);
            if(i==0)        createCell(sheet.getRow(29),col, Formater.num2str(total_kt_nnoi), wb, styleBoldRight);
            else if (i==1)  createCell(sheet.getRow(29),col, getPercent(total_kt_nnoi, total_nkt), wb, styleBoldRight);
            else            createCell(sheet.getRow(29),col, "", wb, styleNormal);            
            createCell(sheet.getRow(30),col, i!=1 ? Formater.num2str(bean.getParamvalue_12().split("#")[i]):bean.getParamvalue_12().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(31),col, i!=1 ? Formater.num2str(bean.getParamvalue_13().split("#")[i]):bean.getParamvalue_13().split("#")[i], wb, styleNormal);
            col++;
        }
        
        col = 2;    // Khuyet Tat Nhin
        for(int i=0;i<arrTotal_NKT.length;i++){            
            if(i==0){
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_14().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_15().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_16().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_17().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_18().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_19().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_20().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_21().split("#")[0]);
                total_kt_nhin = total_kt_nhin + Integer.parseInt(bean.getParamvalue_22().split("#")[0]);                
            }            
            if(i==0)        createCell(sheet.getRow(32),col, Formater.num2str(total_kt_nhin), wb, styleBoldRight);
            else if (i==1)  createCell(sheet.getRow(32),col, getPercent(total_kt_nhin, total_nkt), wb, styleBoldRight);
            else            createCell(sheet.getRow(32),col, "", wb, styleNormal);            
            createCell(sheet.getRow(33),col, i!=1 ? Formater.num2str(bean.getParamvalue_14().split("#")[i]):bean.getParamvalue_14().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(34),col, i!=1 ? Formater.num2str(bean.getParamvalue_15().split("#")[i]):bean.getParamvalue_15().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(35),col, i!=1 ? Formater.num2str(bean.getParamvalue_16().split("#")[i]):bean.getParamvalue_16().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(36),col, i!=1 ? Formater.num2str(bean.getParamvalue_17().split("#")[i]):bean.getParamvalue_17().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(37),col, i!=1 ? Formater.num2str(bean.getParamvalue_18().split("#")[i]):bean.getParamvalue_18().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(38),col, i!=1 ? Formater.num2str(bean.getParamvalue_19().split("#")[i]):bean.getParamvalue_19().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(39),col, i!=1 ? Formater.num2str(bean.getParamvalue_20().split("#")[i]):bean.getParamvalue_20().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(40),col, i!=1 ? Formater.num2str(bean.getParamvalue_21().split("#")[i]):bean.getParamvalue_21().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(41),col, i!=1 ? Formater.num2str(bean.getParamvalue_22().split("#")[i]):bean.getParamvalue_22().split("#")[i], wb, styleNormal);            
            col++;
        }
        
        col = 2;    // Khuyet Tat Tri Tue
        for(int i=0;i<arrTotal_NKT.length;i++){
            if(i==0){
                total_kt_ttue = total_kt_ttue + Integer.parseInt(bean.getParamvalue_23().split("#")[0]);
                total_kt_ttue = total_kt_ttue + Integer.parseInt(bean.getParamvalue_24().split("#")[0]);
            }            
            if(i==0)        createCell(sheet.getRow(42),col, total_kt_ttue, wb, styleBoldRight);
            else if (i==1)  createCell(sheet.getRow(42),col, getPercent(total_kt_ttue, total_nkt), wb, styleBoldRight);
            else            createCell(sheet.getRow(42),col, "", wb, styleNormal);            
            createCell(sheet.getRow(43),col, i!=1 ? Formater.num2str(bean.getParamvalue_23().split("#")[i]):bean.getParamvalue_23().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(44),col, i!=1 ? Formater.num2str(bean.getParamvalue_24().split("#")[i]):bean.getParamvalue_24().split("#")[i], wb, styleNormal);            
            col++;
        }


        col = 2;    // Than Kinh, Tam Than
        for(int i=0;i<arrTotal_NKT.length;i++){ 
            if(i==0){
                total_kt_tthan = total_kt_tthan + Integer.parseInt(bean.getParamvalue_25().split("#")[0]);
                total_kt_tthan = total_kt_tthan + Integer.parseInt(bean.getParamvalue_26().split("#")[0]);
            }
            if(i==0)        createCell(sheet.getRow(45),col, total_kt_tthan, wb, styleBoldRight);
            else if (i==1)  createCell(sheet.getRow(45),col, getPercent(total_kt_tthan, total_nkt), wb, styleBoldRight);
            else            createCell(sheet.getRow(45),col, "", wb, styleNormal);            
            createCell(sheet.getRow(46),col, i!=1 ? Formater.num2str(bean.getParamvalue_25().split("#")[i]):bean.getParamvalue_25().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(47),col, i!=1 ? Formater.num2str(bean.getParamvalue_26().split("#")[i]):bean.getParamvalue_26().split("#")[i], wb, styleNormal);            
            col++;
        }
        
        col = 2;    // Khuyet Tat Khac
        for(int i=0;i<arrTotal_NKT.length;i++){
            if(i==0){
                total_kt_khac = total_kt_khac + Integer.parseInt(bean.getParamvalue_27().split("#")[0]);
                total_kt_khac = total_kt_khac + Integer.parseInt(bean.getParamvalue_28().split("#")[0]);
                total_kt_khac = total_kt_khac + Integer.parseInt(bean.getParamvalue_29().split("#")[0]);
                total_kt_khac = total_kt_khac + Integer.parseInt(bean.getParamvalue_30().split("#")[0]);
                total_kt_khac = total_kt_khac + Integer.parseInt(bean.getParamvalue_31().split("#")[0]);
            }            
            if(i==0)        createCell(sheet.getRow(48),col, total_kt_khac, wb, styleBoldRight);
            else if (i==1)  createCell(sheet.getRow(48),col, getPercent(total_kt_khac, total_nkt), wb, styleBoldRight);
            else            createCell(sheet.getRow(48),col, "", wb, styleNormal);            
            createCell(sheet.getRow(49),col, i!=1 ? Formater.num2str(bean.getParamvalue_27().split("#")[i]):bean.getParamvalue_27().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(50),col, i!=1 ? Formater.num2str(bean.getParamvalue_28().split("#")[i]):bean.getParamvalue_28().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(51),col, i!=1 ? Formater.num2str(bean.getParamvalue_29().split("#")[i]):bean.getParamvalue_29().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(52),col, i!=1 ? Formater.num2str(bean.getParamvalue_30().split("#")[i]):bean.getParamvalue_30().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(53),col, i!=1 ? Formater.num2str(bean.getParamvalue_31().split("#")[i]):bean.getParamvalue_31().split("#")[i], wb, styleNormal);            
            col++;
        }

        col = 2;    // Nguyen Nhan Khuyet Tat
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(54),col, "", wb, styleNormal);
            createCell(sheet.getRow(55),col, i!=1 ? Formater.num2str(bean.getParamvalue_32().split("#")[i]):bean.getParamvalue_32().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(56),col, i!=1 ? Formater.num2str(bean.getParamvalue_33().split("#")[i]):bean.getParamvalue_33().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(57),col, i!=1 ? Formater.num2str(bean.getParamvalue_34().split("#")[i]):bean.getParamvalue_34().split("#")[i], wb, styleNormal);            
            col++;
        }
        
        col = 2;    // Trinh Do Hoc Van
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(58),col, "", wb, styleNormal);
            createCell(sheet.getRow(59),col, i!=1 ? Formater.num2str(bean.getParamvalue_35().split("#")[i]):bean.getParamvalue_35().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(60),col, i!=1 ? Formater.num2str(bean.getParamvalue_36().split("#")[i]):bean.getParamvalue_36().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(61),col, i!=1 ? Formater.num2str(bean.getParamvalue_37().split("#")[i]):bean.getParamvalue_37().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(62),col, i!=1 ? Formater.num2str(bean.getParamvalue_38().split("#")[i]):bean.getParamvalue_38().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(63),col, i!=1 ? Formater.num2str(bean.getParamvalue_39().split("#")[i]):bean.getParamvalue_39().split("#")[i], wb, styleNormal);            
            col++;
        }

        col = 2;    // Trinh Do Chuyen Mon
        for(int i=0;i<arrTotal_NKT.length;i++){ 
            createCell(sheet.getRow(64),col, "", wb, styleNormal);
            createCell(sheet.getRow(65),col, i!=1 ? Formater.num2str(bean.getParamvalue_40().split("#")[i]):bean.getParamvalue_40().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(66),col, i!=1 ? Formater.num2str(bean.getParamvalue_41().split("#")[i]):bean.getParamvalue_41().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(67),col, i!=1 ? Formater.num2str(bean.getParamvalue_42().split("#")[i]):bean.getParamvalue_42().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(68),col, i!=1 ? Formater.num2str(bean.getParamvalue_43().split("#")[i]):bean.getParamvalue_43().split("#")[i], wb, styleNormal);
            col++;
        } 

        col = 2;    // Viec Lam
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(69),col, "", wb, styleNormal);
            createCell(sheet.getRow(70),col, i!=1 ? Formater.num2str(bean.getParamvalue_44().split("#")[i]):bean.getParamvalue_44().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(71),col, i!=1 ? Formater.num2str(bean.getParamvalue_45().split("#")[i]):bean.getParamvalue_45().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(72),col, i!=1 ? Formater.num2str(bean.getParamvalue_46().split("#")[i]):bean.getParamvalue_46().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(73),col, i!=1 ? Formater.num2str(bean.getParamvalue_47().split("#")[i]):bean.getParamvalue_47().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(74),col, i!=1 ? Formater.num2str(bean.getParamvalue_48().split("#")[i]):bean.getParamvalue_48().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(75),col, i!=1 ? Formater.num2str(bean.getParamvalue_49().split("#")[i]):bean.getParamvalue_49().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(76),col, i!=1 ? Formater.num2str(bean.getParamvalue_50().split("#")[i]):bean.getParamvalue_50().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(77),col, i!=1 ? Formater.num2str(bean.getParamvalue_51().split("#")[i]):bean.getParamvalue_51().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(78),col, i!=1 ? Formater.num2str(bean.getParamvalue_52().split("#")[i]):bean.getParamvalue_52().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(79),col, i!=1 ? Formater.num2str(bean.getParamvalue_53().split("#")[i]):bean.getParamvalue_53().split("#")[i], wb, styleNormal);
            col++;
        } 
        
        col = 2;    // Tinh Trang Hon Nhan 
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(80),col, "", wb, styleNormal);
            createCell(sheet.getRow(81),col, i!=1 ? Formater.num2str(bean.getParamvalue_54().split("#")[i]):bean.getParamvalue_54().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(82),col, i!=1 ? Formater.num2str(bean.getParamvalue_55().split("#")[i]):bean.getParamvalue_55().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(83),col, i!=1 ? Formater.num2str(bean.getParamvalue_56().split("#")[i]):bean.getParamvalue_56().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(84),col, i!=1 ? Formater.num2str(bean.getParamvalue_57().split("#")[i]):bean.getParamvalue_57().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(85),col, i!=1 ? Formater.num2str(bean.getParamvalue_58().split("#")[i]):bean.getParamvalue_58().split("#")[i], wb, styleNormal);            
            col++;
        }
        
        col = 2;    // Hoan Canh Kinh Te
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(86),col, "", wb, styleNormal);
            createCell(sheet.getRow(87),col, i!=1 ? Formater.num2str(bean.getParamvalue_59().split("#")[i]):bean.getParamvalue_59().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(88),col, i!=1 ? Formater.num2str(bean.getParamvalue_60().split("#")[i]):bean.getParamvalue_60().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(89),col, i!=1 ? Formater.num2str(bean.getParamvalue_61().split("#")[i]):bean.getParamvalue_61().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(90),col, i!=1 ? Formater.num2str(bean.getParamvalue_62().split("#")[i]):bean.getParamvalue_62().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(91),col, i!=1 ? Formater.num2str(bean.getParamvalue_63().split("#")[i]):bean.getParamvalue_63().split("#")[i], wb, styleNormal);            
            col++;
        }

        // Da Duoc Tro Giup Ve Y Te
        col = 2;
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(92),col, "", wb, styleNormal);
            createCell(sheet.getRow(93),col, i!=1 ? Formater.num2str(bean.getParamvalue_64().split("#")[i]):bean.getParamvalue_64().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(94),col, i!=1 ? Formater.num2str(bean.getParamvalue_65().split("#")[i]):bean.getParamvalue_65().split("#")[i], wb, styleNormal);            
            createCell(sheet.getRow(95),col, i!=1 ? Formater.num2str(bean.getParamvalue_66().split("#")[i]):bean.getParamvalue_66().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(96),col, i!=1 ? Formater.num2str(bean.getParamvalue_67().split("#")[i]):bean.getParamvalue_67().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(97),col, i!=1 ? Formater.num2str(bean.getParamvalue_68().split("#")[i]):bean.getParamvalue_68().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(99),col, i!=1 ? Formater.num2str(bean.getParamvalue_69().split("#")[i]):bean.getParamvalue_69().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(100),col, i!=1 ? Formater.num2str(bean.getParamvalue_70().split("#")[i]):bean.getParamvalue_70().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(101),col, i!=1 ? Formater.num2str(bean.getParamvalue_71().split("#")[i]):bean.getParamvalue_71().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(102),col, i!=1 ? Formater.num2str(bean.getParamvalue_72().split("#")[i]):bean.getParamvalue_72().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(103),col, i!=1 ? Formater.num2str(bean.getParamvalue_73().split("#")[i]):bean.getParamvalue_73().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(104),col, i!=1 ? Formater.num2str(bean.getParamvalue_74().split("#")[i]):bean.getParamvalue_74().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(105),col, i!=1 ? Formater.num2str(bean.getParamvalue_75().split("#")[i]):bean.getParamvalue_75().split("#")[i], wb, styleNormal);            
            col++;
        }
        
        // Da Duoc Tro Giup Ve Xa Hoi
        col = 2;
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(106),col, "", wb, styleNormal);
            createCell(sheet.getRow(107),col, i!=1 ? Formater.num2str(bean.getParamvalue_76().split("#")[i]):bean.getParamvalue_76().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(108),col, i!=1 ? Formater.num2str(bean.getParamvalue_77().split("#")[i]):bean.getParamvalue_77().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(109),col, i!=1 ? Formater.num2str(bean.getParamvalue_78().split("#")[i]):bean.getParamvalue_78().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(110),col, i!=1 ? Formater.num2str(bean.getParamvalue_79().split("#")[i]):bean.getParamvalue_79().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(111),col, i!=1 ? Formater.num2str(bean.getParamvalue_80().split("#")[i]):bean.getParamvalue_80().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(112),col, i!=1 ? Formater.num2str(bean.getParamvalue_81().split("#")[i]):bean.getParamvalue_81().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(113),col, i!=1 ? Formater.num2str(bean.getParamvalue_82().split("#")[i]):bean.getParamvalue_82().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(114),col, i!=1 ? Formater.num2str(bean.getParamvalue_83().split("#")[i]):bean.getParamvalue_83().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(115),col, i!=1 ? Formater.num2str(bean.getParamvalue_84().split("#")[i]):bean.getParamvalue_84().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(116),col, i!=1 ? Formater.num2str(bean.getParamvalue_85().split("#")[i]):bean.getParamvalue_85().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(117),col, i!=1 ? Formater.num2str(bean.getParamvalue_86().split("#")[i]):bean.getParamvalue_86().split("#")[i], wb, styleNormal);
            col++;
        }
        
        // Da Duoc Tro Giup Ve Giao Duc
        col = 2;
        for(int i=0;i<arrTotal_NKT.length;i++){
            createCell(sheet.getRow(118),col, "", wb, styleNormal);
            createCell(sheet.getRow(119),col, i!=1 ? Formater.num2str(bean.getParamvalue_87().split("#")[i]):bean.getParamvalue_87().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(120),col, i!=1 ? Formater.num2str(bean.getParamvalue_88().split("#")[i]):bean.getParamvalue_88().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(121),col, i!=1 ? Formater.num2str(bean.getParamvalue_89().split("#")[i]):bean.getParamvalue_89().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(122),col, i!=1 ? Formater.num2str(bean.getParamvalue_90().split("#")[i]):bean.getParamvalue_90().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(123),col, i!=1 ? Formater.num2str(bean.getParamvalue_91().split("#")[i]):bean.getParamvalue_91().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(124),col, i!=1 ? Formater.num2str(bean.getParamvalue_92().split("#")[i]):bean.getParamvalue_92().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(125),col, i!=1 ? Formater.num2str(bean.getParamvalue_93().split("#")[i]):bean.getParamvalue_93().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(126),col, i!=1 ? Formater.num2str(bean.getParamvalue_94().split("#")[i]):bean.getParamvalue_94().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(127),col, i!=1 ? Formater.num2str(bean.getParamvalue_95().split("#")[i]):bean.getParamvalue_95().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(128),col, i!=1 ? Formater.num2str(bean.getParamvalue_96().split("#")[i]):bean.getParamvalue_96().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(129),col, i!=1 ? Formater.num2str(bean.getParamvalue_97().split("#")[i]):bean.getParamvalue_97().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(130),col, i!=1 ? Formater.num2str(bean.getParamvalue_98().split("#")[i]):bean.getParamvalue_98().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(131),col, i!=1 ? Formater.num2str(bean.getParamvalue_99().split("#")[i]):bean.getParamvalue_99().split("#")[i], wb, styleNormal);           
            createCell(sheet.getRow(132),col, i!=1 ? Formater.num2str(bean.getParamvalue_100().split("#")[i]):bean.getParamvalue_100().split("#")[i], wb, styleNormal);           
            createCell(sheet.getRow(133),col, i!=1 ? Formater.num2str(bean.getParamvalue_101().split("#")[i]):bean.getParamvalue_101().split("#")[i], wb, styleNormal);           
            col++;
        }
        */
        //createCell(sheet.getRow(135), 2, "", wb, style);        
        
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
    
    public String getPercent(int input, int total) {
        DecimalFormat df = new DecimalFormat("0.00");
        String percent = "0%";
        float fPercent = 0;
        if(total!=0)
            fPercent = (float)input/(float)total*100;
            
        percent = String.valueOf((df.format(fPercent))) + "%";        
        return percent;        
    }
    
    public FBeans getData(Connection cnn, int tinh_id, String period) throws EException, SQLException {
        final String LOCATION =  this.toString() + "~~>getDataToExport()";
        CallableStatement state = null;
        state = cnn.prepareCall("{call report_analysis_nkt(?, ?, ?, ?, ?)}");        
        state.setInt(1, tinh_id);
        state.setString(2, period);
        state.execute(); 
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportAnalysis bean = new FReportAnalysis();
        try {
            prpstm = prepareStatement(cnn, SQL_SELECT_REPORT_ANALYSIS_NKT, null);
            rs = prpstm.executeQuery();
            
            while (rs!= null && rs.next()){
                bean = new FReportAnalysis();                
                bean.setNameArea(rs.getString("tinh_name"));
                bean.setTotal(rs.getString("total"));
                
                // Row9
                bean.setParamvalue_1(rs.getString("paramvalue_1"));
                bean.setParamvalue_2(rs.getString("paramvalue_2"));
                bean.setParamvalue_3(rs.getString("paramvalue_3"));
                bean.setParamvalue_4(rs.getString("paramvalue_4"));
                bean.setParamvalue_5(rs.getString("paramvalue_5"));
                
                // Row14
                bean.setParamvalue_6(rs.getString("paramvalue_6"));
                
                // Row16
                bean.setParamvalue_7(rs.getString("paramvalue_7"));
                bean.setParamvalue_8(rs.getString("paramvalue_8"));
                
                // Row19
                bean.setParamvalue_9(rs.getString("paramvalue_9"));
                bean.setParamvalue_10(rs.getString("paramvalue_10"));
                bean.setParamvalue_11(rs.getString("paramvalue_11"));
                bean.setParamvalue_12(rs.getString("paramvalue_12"));
                
                // Row23
                bean.setParamvalue_13(rs.getString("paramvalue_13"));
                
                // Row25
                bean.setParamvalue_14(rs.getString("paramvalue_14"));
                bean.setParamvalue_15(rs.getString("paramvalue_15"));  
                
                // Row28                
                bean.setParamvalue_16(rs.getString("paramvalue_16"));
                bean.setParamvalue_17(rs.getString("paramvalue_17"));
                bean.setParamvalue_18(rs.getString("paramvalue_18"));
                bean.setParamvalue_19(rs.getString("paramvalue_19"));
                
                // Row32
                bean.setParamvalue_20(rs.getString("paramvalue_20"));                
                beans.add(bean);                
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG)
                throw new EException(LOCATION, sqle);
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
}
