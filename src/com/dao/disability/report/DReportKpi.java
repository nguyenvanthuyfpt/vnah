package com.dao.disability.report;


import com.bo.disability.report.BReportKpi;

import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.report.FReportKpi;

import com.lib.AppConfigs;

import com.util.Utilities;

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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportKpi extends FExportExcel {
    public DReportKpi() {
    }
    
    final static Logger logger  = Logger.getLogger(DReportKpi.class);

    public String exportReportObject(FReportKpi beanTemp, FSeed seed,
                                     String excelFile) throws EException,
                                                              FileNotFoundException,
                                                              IOException {
        String LOCATION = toString() + "~>exportReportObject()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";


        String excelDown = excelPath + seed.me.getSessionID();

        File file = new File(excelPath, excelFile);
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);

        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);

        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont fontArial14 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);

        HSSFFont fontArialBold14 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), true);

        CellStyle csLeft =
            getStyle(wb, fontArial14, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csLeftNone =
            getStyle(wb, fontArial14, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csRight =
            getStyle(wb, fontArial14, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csBRight =
            getStyle(wb, fontArialBold14, (short)3, (short)3, (short)1,
                     (short)1, (short)1, (short)1);


        CellStyle csCenterWrap =
            getStyle(wb, fontArialBold14, (short)2, (short)1, (short)1,
                     (short)1, (short)1, (short)1);


        csCenterWrap.setWrapText(true);

        HSSFRow row = null;

        row = sheet.createRow(0);
        createCell(row, 0,
                   beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                   Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                   csLeftNone);


        row = sheet.getRow(2);
        int col = bean.getTinhId() > 0 ? 1 : 0;
        createCell(row, 0,
                   bean.getTinhId() > 0 ? bean.ncrToString(beanTemp.ncrToString("Tuy&#7871;n : ") +
                                                           bean.getTinhName()) :
                   "", wb, csLeftNone);


        if ("0".equals(beanTemp.getPeriodType())) {
            createCell(row, col,
                       bean.ncrToString(beanTemp.ncrToString("Th&#225;ng : ") +
                                        beanTemp.getVal()), wb, csLeftNone);

        } else if ("1".equals(beanTemp.getPeriodType())) {
            createCell(row, col,
                       bean.ncrToString(beanTemp.ncrToString("Qu&#253; : ") +
                                        beanTemp.getVal()), wb, csLeftNone);

        } else if ("2".equals(beanTemp.getPeriodType())) {
            createCell(row, col,
                       bean.ncrToString(beanTemp.ncrToString("N&#259;m : ") +
                                        beanTemp.getVal()), wb, csLeftNone);

        } else if ("3".equals(beanTemp.getPeriodType())) {
            createCell(row, col,
                       bean.ncrToString(beanTemp.ncrToString("T&#7915; th&#225;ng : ") +
                                        beanTemp.getVal()), wb, csLeftNone);
        }


        int dong = 6;
        int indNum = 1;
        int objNum = 1;
        int curObjId = 0;
        int curIndId = 0;
        String strYear =
            beanTemp.getVal().substring(beanTemp.getVal().indexOf("/") + 1);

        FReportKpi beanObj = null;

        row = sheet.getRow(5);
        createCell(row, 7, "Q1/" + strYear, wb, csCenterWrap);
        createCell(row, 8, "Q2/" + strYear, wb, csCenterWrap);
        createCell(row, 9, "Q3/" + strYear, wb, csCenterWrap);
        createCell(row, 10, "Q4/" + strYear, wb, csCenterWrap);
        for (int i = 0; i < beanTemp.getStore().size(); i++) {
            int cot = 0;
            beanObj = (FReportKpi)beanTemp.getStore().get(i);
            row = sheet.createRow(dong);
            objNum = beanObj.getObjNum();
            indNum = beanObj.getIndNum();
            if (i == 0) {
                if (objNum > 1) {
                    objNum--;
                    createRegion(wb, row, dong, dong + objNum, 0, 0, csLeft,
                                 beanObj.getObjName(), 1, 0);
                }
            } else if (beanObj.getObjId() != curObjId) {
                if (objNum > 1) {
                    objNum--;
                    createRegion(wb, row, dong, dong + objNum, 0, 0, csLeft,
                                 beanObj.getObjName(), 1, 0);
                } else {
                    createCell(row, cot++, beanObj.getObjName(), wb, csLeft);
                }
            } else {
                createCell(row, cot++, "", wb, csLeft);
            }
            curObjId = beanObj.getObjId();
            if (i == 0) {
                if (indNum > 1) {
                    indNum--;
                    createRegion(wb, row, dong, dong + indNum, 1, 1, csLeft,
                                 beanObj.getIndCode(), 1, 0);
                }
            } else if (beanObj.getIndId() != curIndId) {
                if (indNum > 1) {
                    indNum--;
                    createRegion(wb, row, dong, dong + indNum, 1, 1, csLeft,
                                 beanObj.getIndCode(), 1, 0);
                } else {
                    createCell(row, cot++, beanObj.getIndCode(), wb, csLeft);
                }
            } else {
                createCell(row, cot++, "", wb, csLeft);
            }
            curIndId = beanObj.getIndId();
            cot = 2;
            createCell(row, cot++, beanObj.getIndDesc(), wb, csLeft);
            createCell(row, cot++, beanObj.getYearBaseline(), wb, csRight);
            createCell(row, cot++, beanObj.getValueBaseline(), wb, csRight);
            createCell(row, cot++, beanObj.getTarget(), wb, csRight);
            createCell(row, cot++, beanObj.getCumulative(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ1(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ2(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ3(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ4(), wb, csRight);
            createCell(row, cot++, beanObj.getPercent(), wb, csRight);
            createCell(row, cot++, beanObj.getComment(), wb, csRight);

            dong++;
            objNum = 1;
            indNum = 1;
        }
        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }


    public String exportReportIndicator(FReportKpi beanTemp, FSeed seed,
                                        String excelFile) throws EException,
                                                                 FileNotFoundException,
                                                                 IOException {
        String LOCATION = toString() + "~>exportReportIndicator()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";


        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;

        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont fontArial14 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);

        HSSFFont fontArialBold14 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), true);

        CellStyle csLeft =
            getStyle(wb, fontArial14, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csLeftNone =
            getStyle(wb, fontArial14, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csRight =
            getStyle(wb, fontArial14, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csBRight =
            getStyle(wb, fontArialBold14, (short)3, (short)3, (short)1,
                     (short)1, (short)1, (short)1);


        CellStyle csCenterWrap =
            getStyle(wb, fontArialBold14, (short)2, (short)1, (short)1,
                     (short)1, (short)1, (short)1);


        CellStyle csLeftWrap =
            getStyle(wb, fontArialBold14, (short)1, (short)3, (short)1,
                     (short)1, (short)1, (short)1);


        csCenterWrap.setWrapText(true);

        HSSFRow row = null;

        row = sheet.createRow(0);
        createCell(row, 0,
                   beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                   Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                   csLeftNone);


        row = sheet.getRow(2);
        createCell(row, 0,
                   bean.getTinhId() > 0 ? bean.ncrToString(beanTemp.ncrToString("Tuy&#7871;n : ") +
                                                           bean.getTinhName()) :
                   "", wb, csLeftNone);


        if ("0".equals(beanTemp.getPeriodType())) {
            createCell(row, bean.getTinhId() > 0 ? 1 : 0,
                       bean.ncrToString(beanTemp.ncrToString("Th&#225;ng : ") +
                                        beanTemp.getVal()), wb, csLeftNone);

        } else if ("1".equals(beanTemp.getPeriodType())) {
            createCell(row, bean.getTinhId() > 0 ? 1 : 0,
                       bean.ncrToString(beanTemp.ncrToString("Qu&#253; : ") +
                                        beanTemp.getVal()), wb, csLeftNone);

        } else if ("2".equals(beanTemp.getPeriodType())) {
            createCell(row, bean.getTinhId() > 0 ? 1 : 0,
                       bean.ncrToString(beanTemp.ncrToString("N&#259;m : ") +
                                        beanTemp.getVal()), wb, csLeftNone);

        } else if ("3".equals(beanTemp.getPeriodType())) {
            createCell(row, bean.getTinhId() > 0 ? 1 : 0,
                       bean.ncrToString(beanTemp.ncrToString("T&#7915; th&#225;ng : ") +
                                        beanTemp.getVal()), wb, csLeftNone);
        }


        int dong = 5;
        int indNum = 1;
        int curIndId = 0;
        String strYear =
            beanTemp.getVal().substring(beanTemp.getVal().indexOf("/") + 1);

        FReportKpi beanObj = null;

        row = sheet.getRow(4);
        createCell(row, 4, "Q1/" + strYear, wb, csCenterWrap);
        createCell(row, 5, "Q2/" + strYear, wb, csCenterWrap);
        createCell(row, 6, "Q3/" + strYear, wb, csCenterWrap);
        createCell(row, 7, "Q4/" + strYear, wb, csCenterWrap);
        for (int i = 0; i < beanTemp.getStore().size(); i++) {
            int cot = 0;
            beanObj = (FReportKpi)beanTemp.getStore().get(i);
            row = sheet.createRow(dong);
            indNum = beanObj.getIndNum();
            if (i == 0) {
                if (indNum > 1) {
                    indNum--;
                    createRegion(wb, row, dong, dong + indNum, 0, 0, csLeft,
                                 beanObj.getIndCode(), 1, 0);
                }
            } else if ((beanObj.getIndId() != curIndId) && (indNum > 1)) {
                indNum--;
                createRegion(wb, row, dong, dong + indNum, 0, 0, csLeft,
                             beanObj.getIndCode(), 1, 0);
            }

            curIndId = beanObj.getIndId();
            createCell(row, cot++, beanObj.getIndCode(), wb, csLeft);
            cot = 1;
            createCell(row, cot++, beanObj.getIndDesc(), wb, csLeft);
            createCell(row, cot++, beanObj.getBaseline(), wb, csRight);
            createCell(row, cot++, beanObj.getTarget(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ1(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ2(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ3(), wb, csRight);
            createCell(row, cot++, beanObj.getActualQ4(), wb, csRight);
            createCell(row, cot++, beanObj.getCumulative(), wb, csRight);
            createCell(row, cot++, beanObj.getPercent(), wb, csRight);

            dong++;
            indNum = 1;
            wrap(row, cot++, csLeftWrap);
        }
        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }


    public String exportReportInsurance(FReportKpi beanTemp, FSeed seed,
                                        String excelFile) throws EException,
                                                                 FileNotFoundException,
                                                                 IOException {
        String LOCATION = toString() + "~>exportReportInsurance()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";


        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;

        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont fontArial14 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);

        HSSFFont fontArialBold14 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), true);

        CellStyle csLeft =
            getStyle(wb, fontArial14, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csLeftNone =
            getStyle(wb, fontArial14, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csRight =
            getStyle(wb, fontArial14, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csBRight =
            getStyle(wb, fontArialBold14, (short)3, (short)3, (short)1,
                     (short)1, (short)1, (short)1);


        CellStyle csCenterWrap =
            getStyle(wb, fontArialBold14, (short)2, (short)3, (short)1,
                     (short)1, (short)1, (short)1);


        CellStyle csLeftWrap =
            getStyle(wb, fontArialBold14, (short)1, (short)3, (short)1,
                     (short)1, (short)1, (short)1);


        csCenterWrap.setWrapText(true);

        HSSFRow row = null;
        row = sheet.getRow(2);

        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }


    public String exportReportSupport(FReportKpi beanTemp, FSeed seed,
                                     String excelFile) throws EException,
                                                              FileNotFoundException,
                                                              IOException {
        String LOCATION = toString() + "~>exportReportSuppor()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";


        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;

        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont font11 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);

        HSSFFont fontB12 =
            getFont(wb, "Times New Roman", Integer.valueOf(12), true);


        CellStyle csLeftNone =
            getStyle(wb, font11, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csRight =
            getStyle(wb, font11, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csCenterWrap =
            getStyle(wb, fontB12, (short)2, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csLeftB =
            getStyle(wb, fontB12, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        csCenterWrap.setWrapText(true);

        int dong = 6;
        FReportKpi beanObj = null;

        HSSFRow row = null;

        row = sheet.createRow(0);
        createCell(row, 0,
                   beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                   Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                   csLeftNone);


        row = sheet.createRow(1);
        createCell(row, 0, bean.getTinhName(), wb, csLeftB);

        row = sheet.getRow(4);
        createCell(row, 1,
                   bean.ncrToString("Th&#225;ng : ") + beanTemp.getVal(), wb,
                   csCenterWrap);

        int len = beanTemp.getStore().size();        
        int cot = 2;
        for (int i = 0; i < len; i++) {
            dong = 8;
            beanObj = (FReportKpi)beanTemp.getStore().get(i);  
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_1()), wb, csRight);
                        
            dong = 10;            
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_1a()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_1b()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_2a()), wb, csRight);
            // 2a: 1-4
            
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_2b()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_2c()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_3()), wb, csRight);
            
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_4()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_5()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_6()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_6a()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_6b()), wb, csRight);
            
            //II
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_7()), wb, csRight);
           
            dong = 23;
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_8()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_9()), wb, csRight);
          
            // Chi-theo-muc-do-kt
            dong = 26;            
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_10()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_11()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_12()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_13()), wb, csRight);
        
            // III: Tong-so-nkt-da-nhan-hotro-tu-du-an
            dong = 30;
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_14()), wb, csRight);          
            
            dong = 32;
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_15()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_16()), wb, csRight);
         
            // Chi-theo-muc-do-kt
            dong = 35;
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_17()), wb, csRight);         
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_18()), wb,  csRight);         
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_19()), wb, csRight);        
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_20()), wb, csRight);
         
            // IV
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_21()), wb, csRight);
            
            // V
            dong = 40;
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_2a1()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_2a2()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_2a3()), wb, csRight);
            createCell(sheet.getRow(dong++), cot, Integer.valueOf(beanObj.getParamvalue_2a4()), wb, csRight);
            cot++;
        }
  
        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }


    public FBeans getDataReportObject(Connection cnn, 
                                      int periodType,
                                      int tinh_id, 
                                      String parameter, 
                                      int year,
                                      int extend) throws EException,
                                                         SQLException {
        String LOCATION = toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            logger.debug("{call report_object("+periodType+","+ tinh_id + ",'"+ parameter + "',"+ year+ "," + extend +")}");
            
            /*
            CallableStatement state = cnn.prepareCall("{call report_object(?, ?, ?, ?, ?)}");
            
            state.setInt(1, periodType);
            state.setInt(2, tinh_id);
            state.setString(3, parameter);
            state.setInt(4, year);
            state.setInt(5, extend);
            state.execute();
            */
            
            String sql = "SELECT num_obj, num_ind, obj_id, obj_name, ind_id, ind_code, ind_name, 0, baseline, target, acc, actual_q1, actual_q2, actual_q3, actual_q4, percent, '' as comment " +
                "FROM kpi_report_object " +
                "WHERE 1=1 AND location_id="+tinh_id+" ORDER BY stt";

            prpstm = prepareStatement(cnn, sql, null);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                int i = 1;
                bean = new FReportKpi();
                bean.setObjNum(rs.getInt(i++));
                bean.setIndNum(rs.getInt(i++));
                bean.setObjId(rs.getInt(i++));
                bean.setObjName(rs.getString(i++));

                bean.setIndId(rs.getInt(i++));
                bean.setIndCode(rs.getString(i++));
                bean.setIndDesc(rs.getString(i++));

                bean.setYearBaseline(rs.getString(i++));
                bean.setValueBaseline(rs.getString(i++));

                bean.setTarget(rs.getString(i++));
                bean.setCumulative(rs.getString(i++));

                bean.setActualQ1(rs.getString(i++));
                bean.setActualQ2(rs.getString(i++));
                bean.setActualQ3(rs.getString(i++));
                bean.setActualQ4(rs.getString(i++));
                bean.setPercent(rs.getString(i++));
                bean.setComment(rs.getString(i++));
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
            logger.error(sqle.toString());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }


    public FBeans getDataReportIndicator(Connection cnn, 
                                         int periodType,
                                         int tinh_id, 
                                         String parameter,
                                         int year,
                                         int extend) throws EException,
                                                            SQLException {
        String LOCATION = toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            logger.debug("{call report_indicator("+periodType+","+ tinh_id + ",'"+ parameter + "',"+ year+ "," + extend +")}");
            /*
            CallableStatement state = cnn.prepareCall("{call report_indicator(?, ?, ?, ?, ?)}");

            state.setInt(1, periodType);
            state.setInt(2, tinh_id);
            state.setString(3, parameter);
            state.setInt(4, year);
            state.setInt(5, extend);
            state.execute();
            */
            
            // '0#2019#2'
            String param = tinh_id+"#"+year+"#"+parameter; 
            String sql =
                "SELECT num_ind, ind_id, ind_code, ind_name, baseline, target, actual_q1, actual_q2, actual_q3, actual_q4, acc, percent " +
                "FROM kpi_report_indicator WHERE 1=1 AND location_id="+tinh_id+" AND param='"+param+"' ORDER BY stt";

            prpstm = prepareStatement(cnn, sql, null);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                int i = 1;
                bean = new FReportKpi();
                bean.setIndNum(rs.getInt(i++));

                bean.setIndId(rs.getInt(i++));
                bean.setIndCode(rs.getString(i++));
                bean.setIndDesc(rs.getString(i++));

                bean.setBaseline(rs.getString(i++));
                bean.setTarget(rs.getString(i++));
                bean.setActualQ1(rs.getString(i++));
                bean.setActualQ2(rs.getString(i++));
                bean.setActualQ3(rs.getString(i++));
                bean.setActualQ4(rs.getString(i++));
                bean.setCumulative(rs.getString(i++));
                bean.setPercent(rs.getString(i++));
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
            logger.error(sqle.toString());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public FBeans getDataReportSupport(Connection cnn,                                        
                                        int lvl, 
                                        int location_id, 
                                        String periodType,
                                        String strVal) throws EException,
                                                            SQLException {
        String LOCATION = toString() + "~~>getDataReportSupport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            // logger.debug("{call report_indicator("+periodType+","+ tinh_id + ",'"+ parameter + "',"+ year+ "," + extend +")}");
            CallableStatement state = cnn.prepareCall("{call report_support_summary(?,?,?,?)}");  
            state.setInt(1, lvl);
            state.setInt(2, location_id);
            state.setString(3, periodType);
            state.setString(4, strVal);
            state.execute();
            
            // '0#2019#2'
            // String param = tinh_id+"#"+year+"#"+parameter; 
            String sql =
                "SELECT dis_ten, dis_ma, dis_sonha, dis_namsinh, dis_gioi, dis_dthoai, dis_dangtat, dis_tt_ktat, dis_ngay_hso, " +
                "tinh_name, huyen_name, pxa_name, kham_lamsang, tai_kham, num_dctg, num_htro " +
                "FROM kpi_report_support_summary WHERE 1=1 ORDER BY tinh_id, huyen_id, pxa_id, dis_ma";
  
            prpstm = prepareStatement(cnn, sql, null);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                int i = 1;
                bean = new FReportKpi();
                bean.setTen(rs.getString(i++));
                bean.setMaSo(rs.getString(i++));
                bean.setSoNha(rs.getString(i++));
                bean.setYearBirth(rs.getInt(i++));
                bean.setSex(rs.getString(i++));
                
                bean.setDienThoai(rs.getString(i++));
                bean.setDangTat(rs.getString(i++));
                bean.setTTrangKTat(rs.getString(i++));
                bean.setNgayMoHS(rs.getString(i++));
                
                bean.setTinhName(rs.getString(i++));
                bean.setQhuName(rs.getString(i++ ));
                bean.setPxaName(rs.getString(i++));
                
                bean.setKhamNum1(rs.getInt(i++));
                bean.setKhamNum2(rs.getInt(i++));
                bean.setKhamNum3(rs.getInt(i++));
                bean.setKhamNum4(rs.getInt(i++));                
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
            logger.error(sqle.toString());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }

    public FBeans getDataReportInsurance(Connection cnn, int locationId,
                                         String period) throws EException,
                                                               SQLException {
        String LOCATION = toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            CallableStatement state =
                cnn.prepareCall("{call report_insurance(?, ?)}");

            state.setInt(1, locationId);
            state.setString(1, period);
            state.execute();

            String sql =
                "select * from kpi_report_insurance where 1=1 order by stt";

            prpstm = prepareStatement(cnn, sql, null);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                int i = 1;
                bean = new FReportKpi();
                bean.setName(rs.getString(i++));
                bean.setMale(rs.getInt(i++));
                bean.setFemale(rs.getInt(i++));
                bean.setAddress(rs.getString(i++));
                bean.setPhoneNumber(rs.getString(i++));
                bean.setMethodIntervention(rs.getString(i++));
                bean.setIsDis(rs.getInt(i++));
                bean.setNotDis(rs.getInt(i++));

                bean.setTypeLevelDis(rs.getString(i++));
                bean.setNumberTimes(rs.getInt(i++));
                bean.setIsInsurance(rs.getInt(i++));
                bean.setNotInsurance(rs.getInt(i++));
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }

    public FBeans getDataReportSupport(Connection cnn, 
                                       int locationId,
                                       String period) throws EException,
                                                             SQLException {
        String LOCATION = toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            CallableStatement state = cnn.prepareCall("{call report_support(?, ?)}");
            logger.debug("{call report_support("+locationId+",'"+ period + "')}");
            state.setInt(1, locationId);
            state.setString(2, period);
            state.execute();
            String sql =
                "select * from (\n" + 
                "select sum(total_dis_sp) total_dis_sp, \n" + 
                "sum(total_dis_sp_p1a) total_dis_sp_p1a, \n" + 
                "sum(total_dis_sp_p1b) total_dis_sp_p1b,\n" + 
                "sum(total_dis_sp_p2a) total_dis_sp_p2a,\n" + 
                "sum(total_dis_sp_p2a1) total_dis_sp_p2a1,\n" + 
                "sum(total_dis_sp_p2a2) total_dis_sp_p2a2,\n" + 
                "sum(total_dis_sp_p2a3) total_dis_sp_p2a3,\n" + 
                "sum(total_dis_sp_p2a4) total_dis_sp_p2a4,\n" + 
                
                "sum(total_dis_sp_p2b) total_dis_sp_p2b,\n" + 
                "sum(total_dis_sp_p2c) total_dis_sp_p2c,\n" + 
                "sum(total_dis_sp_p3) total_dis_sp_p3,\n" + 
                "sum(total_dis_sp_p31) total_dis_sp_p31,\n" + 
                "sum(total_dis_sp_p32) total_dis_sp_p32,\n" + 
                "sum(total_dis_sp_p33) total_dis_sp_p33,\n" + 
                "sum(total_dis_sp_p34) total_dis_sp_p34,\n" + 
                
                "sum(total_dis_sp_p4) total_dis_sp_p4,\n" + 
                "sum(total_dis_sp_p5) total_dis_sp_p5,\n" + 
                "sum(total_dis_sp_p6) total_dis_sp_p6,\n" + 
                "sum(total_dis_sp_p6a) total_dis_sp_p6a,\n" + 
                "sum(total_dis_sp_p6b) total_dis_sp_p6b,\n" + 
                
                "sum(total_dis_new) total_dis_new,\n" + 
                "sum(total_dis_new_female) total_dis_new_female,\n" + 
                "sum(total_dis_new_male) total_dis_new_male,\n" + 
                "sum(total_dis_new_lv1) total_dis_new_lv1 ,\n" + 
                "sum(total_dis_new_lv2) total_dis_new_lv2 ,\n" + 
                "sum(total_dis_new_lv3) total_dis_new_lv3, \n" + 
                "sum(total_dis_new_lv4) total_dis_new_lv4, \n" + 
                
                "sum(total_dis_old) total_dis_old,\n" + 
                "sum(total_dis_old_female) total_dis_old_female ,\n" + 
                "sum(total_dis_old_male) total_dis_old_male ,\n" + 
                "sum(total_dis_old_lv1) total_dis_old_lv1 ,\n" + 
                "sum(total_dis_old_lv2) total_dis_old_lv2,\n" + 
                "sum(total_dis_old_lv3) total_dis_old_lv3,\n" + 
                "sum(total_dis_old_lv4) total_dis_old_lv4, \n" + 
                "sum(total_dis_ins) total_dis_old_ins, \n" + 
                "stt, project \n" + 
                "FROM kpi_report_support GROUP BY ROLLUP(stt, project)) a where a.stt IS NOT NULL ORDER BY a.project, a.stt";

            prpstm = prepareStatement(cnn, sql, null);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                int i = 1;
                bean = new FReportKpi();

                bean.setParamvalue_1(rs.getInt(i++)); //I
                bean.setParamvalue_1a(rs.getInt(i++)); //1a
                bean.setParamvalue_1b(rs.getInt(i++)); //1b
                
                bean.setParamvalue_2a(rs.getInt(i++)); //2a
                bean.setParamvalue_2a1(rs.getInt(i++)); //2a1
                bean.setParamvalue_2a2(rs.getInt(i++)); //2a2
                bean.setParamvalue_2a3(rs.getInt(i++)); //2a3
                bean.setParamvalue_2a4(rs.getInt(i++)); //2a4
                                
                bean.setParamvalue_2b(rs.getInt(i++)); //2b
                bean.setParamvalue_2c(rs.getInt(i++)); //2c
                
                bean.setParamvalue_3(rs.getInt(i++));   //3
                bean.setParamvalue_31(rs.getInt(i++));   //31
                bean.setParamvalue_32(rs.getInt(i++));   //32
                bean.setParamvalue_33(rs.getInt(i++));   //33
                bean.setParamvalue_34(rs.getInt(i++));   //34
                
                bean.setParamvalue_4(rs.getInt(i++));   //4
                bean.setParamvalue_5(rs.getInt(i++));   //5
                bean.setParamvalue_6(rs.getInt(i++));  // 6
                bean.setParamvalue_6a(rs.getInt(i++));
                bean.setParamvalue_6b(rs.getInt(i++));
                
                bean.setParamvalue_7(rs.getInt(i++));   //II
                bean.setParamvalue_8(rs.getInt(i++));   // 1.1.Female     
                bean.setParamvalue_9(rs.getInt(i++));   // 1.2.Male
                
                bean.setParamvalue_10(rs.getInt(i++));  // 2.1. Nhe
                bean.setParamvalue_11(rs.getInt(i++));  // 2.2. Nang
                bean.setParamvalue_12(rs.getInt(i++));  // 2.3. DBN
                bean.setParamvalue_13(rs.getInt(i++));  // 2.4. KXD
                
                bean.setParamvalue_14(rs.getInt(i++));  // III                
                bean.setParamvalue_15(rs.getInt(i++));  // 3.1. Female
                bean.setParamvalue_16(rs.getInt(i++));  // 3.2. Male
                
                bean.setParamvalue_17(rs.getInt(i++));  // 3.2.1. Nhe
                bean.setParamvalue_18(rs.getInt(i++));  // 3.2.2. Nang
                bean.setParamvalue_19(rs.getInt(i++));  // 3.2.3. DBN
                bean.setParamvalue_20(rs.getInt(i++));  // 3.2.4. KXD
                
                bean.setParamvalue_21(rs.getInt(i++));  // IV

                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
            logger.error(sqle.toString());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }


    public String reportDisExport(FReportKpi beanTemp, FSeed seed,
                                  String excelFile) throws EException,
                                                           FileNotFoundException,
                                                           IOException {
        String LOCATION = toString() + "~>exportReportSuppor()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";


        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;

        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont font11 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);

        HSSFFont fontB12 =
            getFont(wb, "Times New Roman", Integer.valueOf(12), true);


        CellStyle csLeftNone =
            getStyle(wb, font11, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csRight =
            getStyle(wb, font11, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csLeft =
            getStyle(wb, font11, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csCenterWrap =
            getStyle(wb, font11, (short)2, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csLeftB =
            getStyle(wb, fontB12, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        csCenterWrap.setWrapText(true);

        int dong = 6;
        HSSFRow row = null;

        row = sheet.createRow(0);
        createCell(row, 0,
                   beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                   Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                   csLeftNone);


        row = sheet.createRow(1);
        createCell(row, 0, bean.getTinhName(), wb, csLeftB);

        int r = 4;
        int c = 0;
        int inc = 1;
        for (int i = 0; i < beanTemp.getStore().size(); i++) {
            bean = (FReportKpi)beanTemp.getStore().get(i);
            row = sheet.createRow(r);
            
            boolean disProject = false;
            if (bean.getTinhName()!=null) {
               disProject = true;
            }
             
            createCell(row, c++, disProject ? inc++:"", wb, csLeft);
            createCell(row, c++, bean.getTinhName(), wb, csLeft);
            createCell(row, c++, bean.getMaSo(), wb, csLeft);
            
            createCell(row, c++, bean.getTen(), wb, csLeft);
            createCell(row, c++,
                       bean.getYearBirth() == 0 ? "" : Integer.valueOf(bean.getYearBirth()),
                       wb, csCenterWrap);


            createCell(row, c++, bean.getSex(), wb, csLeft);
            createCell(row, c++, bean.getSoNha(), wb, csLeft);
            createCell(row, c++, bean.getDienThoai(), wb, csLeft);
            createCell(row, c++, bean.getCreateDate(), wb, csCenterWrap);

            createCell(row, c++, bean.getNcsTen(), wb, csLeft);
            createCell(row, c++, bean.getNcsSdt(), wb, csLeft);
            createCell(row, c++, bean.getNcsSex(), wb, csLeft);

            createCell(row, c++, bean.getDaCam(), wb, csLeft);
            createCell(row, c++, bean.getTrangThai(), wb, csLeft);
            createCell(row, c++, bean.getNgayDongHS(), wb, csLeft);

            createCell(row, c++, bean.getDangTat(), wb, csLeft);
            createCell(row, c++, bean.getMucDo(), wb, csLeft);

            createCell(row, c++, bean.getNgayDangTat(), wb, csCenterWrap);

            createCell(row, c++, bean.getNCauCThiepCN(), wb, csLeft);
            createCell(row, c++, bean.getNCauPHCN(), wb, csLeft);
            createCell(row, c++, bean.getNCauDungCu(), wb, csLeft);
            createCell(row, c++, bean.getNCauCTaoCTVS(), wb, csLeft);
            createCell(row, c++, bean.getNgayNhuCau(), wb, csCenterWrap);

            createCell(row, c++, bean.getHTroCThiepCN(), wb, csLeft);
            createCell(row, c++, bean.getHTroPHCN(), wb, csLeft);
            createCell(row, c++, bean.getHTroDungCu(), wb, csLeft);
            createCell(row, c++, bean.getHTroCTaoCTVS(), wb, csLeft);
            createCell(row, c++, bean.getHTroNguon(), wb, csLeft);
            createCell(row, c++, bean.getNgayHoTro(), wb, csCenterWrap);
            
            String projectName = bean.getProjectId()==0?"Direct":"Inclusion 3";
            createCell(row, c++, disProject==true?projectName:"", wb, csLeft);
      
            r++;
            c = 0;
            disProject = false;
        }
        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }
    
    public String reportDisExport2020(FReportKpi beanTemp, FSeed seed,
                                  String excelFile) throws EException,
                                                           FileNotFoundException,
                                                           IOException {
        String LOCATION = toString() + "~>exportReportSuppor()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
  
  
        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
  
        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont font11 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);
  
        HSSFFont fontB12 =
            getFont(wb, "Times New Roman", Integer.valueOf(12), true);
  
  
        CellStyle csLeftNone =
            getStyle(wb, font11, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);
  
  
        CellStyle csRight =
            getStyle(wb, font11, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);
  
  
        CellStyle csLeft =
            getStyle(wb, font11, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);
  
  
        CellStyle csCenterWrap =
            getStyle(wb, font11, (short)2, (short)3, (short)1, (short)1,
                     (short)1, (short)1);
  
  
        CellStyle csLeftB =
            getStyle(wb, fontB12, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);
  
  
        csCenterWrap.setWrapText(true);
  
        int dong = 6;
        HSSFRow row = null;
  
        row = sheet.createRow(0);
        createCell(row, 0,
                   beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                   Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                   csLeftNone);
  
  
        row = sheet.createRow(1);
        createCell(row, 0, bean.getTinhName(), wb, csLeftB);
  
        int r = 4;
        int c = 0;
        int stt = 1;
        for (int i = 0; i < beanTemp.getStore().size(); i++) {
            bean = (FReportKpi)beanTemp.getStore().get(i);
            row = sheet.createRow(r);            
            createCell(row, c++, stt++, wb, csLeft);
            createCell(row, c++, bean.getCreateDate(), wb, csCenterWrap);            
            createCell(row, c++, bean.getMaSo(), wb, csLeft);
            createCell(row, c++, bean.getTen(), wb, csLeft);
            createCell(row, c++,
                       bean.getYearBirth() == 0 ? "" : Integer.valueOf(bean.getYearBirth()),
                       wb, csCenterWrap);
  
            createCell(row, c++, bean.getSex(), wb, csLeft);            
            createCell(row, c++, bean.getDienThoai(), wb, csLeft);
            
            createCell(row, c++, bean.getTinhName(), wb, csLeft);
            createCell(row, c++, "".equals(bean.getQhuName())?"":bean.getQhuName(), wb, csLeft);
            createCell(row, c++, "".equals(bean.getPxaName())?"":bean.getPxaName(), wb, csLeft);
            
            createCell(row, c++, bean.getDangTat(), wb, csLeft);
            createCell(row, c++, bean.getMucDo(), wb, csLeft);
            createCell(row, c++, bean.getDangTatVanDong(), wb, csLeft);
            
            createCell(row, c++, bean.getNcsTen(), wb, csLeft);        
            createCell(row, c++, bean.getNcsSex(), wb, csLeft);
          
            createCell(row, c++, "0".equals(bean.getNcauVLTL())?bean.ncrToString("Kh&#244;ng"):bean.ncrToString("C&#243;"), wb, csLeft);
            createCell(row, c++, "0".equals(bean.getNcauHDTL())?bean.ncrToString("Kh&#244;ng"):bean.ncrToString("C&#243;"), wb, csLeft);
            createCell(row, c++, "0".equals(bean.getNcauNNTL())?bean.ncrToString("Kh&#244;ng"):bean.ncrToString("C&#243;"), wb, csLeft);
            createCell(row, c++, "0".equals(bean.getNcauDCHT())?bean.ncrToString("Kh&#244;ng"):bean.ncrToString("C&#243;"), wb, csLeft);
            createCell(row, c++, "0".equals(bean.getNcauWC())?bean.ncrToString("Kh&#244;ng"):bean.ncrToString("C&#243;"), wb, csLeft);
            
            createCell(row, c++, bean.getDgNum0(), wb, csLeft);
            createCell(row, c++, bean.getDgNum1(), wb, csLeft);
            createCell(row, c++, bean.getDgNum2(), wb, csLeft);
            createCell(row, c++, bean.getDgNum3(), wb, csLeft);
            createCell(row, c++, bean.getDgNum4(), wb, csLeft);
            
            createCell(row, c++, bean.getDgCTh1(), wb, csLeft);
            createCell(row, c++, bean.getDgCTh2(), wb, csLeft);
            createCell(row, c++, bean.getDgCTh3(), wb, csLeft);
            createCell(row, c++, bean.getDgCTh4(), wb, csLeft);
            
            createCell(row, c++, bean.getHTroDungCu(), wb, csLeft);
            createCell(row, c++, "".equals(bean.getHTroCTaoCTVS())?bean.ncrToString("Ch&#432;a"):bean.ncrToString("&#272;&#227; nh&#7853;n"), wb, csLeft);
            
            createCell(row, c++, bean.getHtroPhcnVNAH(), wb, csLeft);
            createCell(row, c++, bean.getHtroPhcnTTP(), wb, csLeft);
            createCell(row, c++, bean.getHtroPhcnQHU(), wb, csLeft);
            createCell(row, c++, bean.getHtroPhcnPXA(), wb, csLeft);
            
            createCell(row, c++, bean.getDgNgay(), wb, csLeft);
            createCell(row, c++, bean.getCthNum0(), wb, csLeft);
            createCell(row, c++, bean.getCthNum1(), wb, csLeft);
            createCell(row, c++, bean.getCthNum2(), wb, csLeft);
            createCell(row, c++, bean.getCthNum3(), wb, csLeft);
            createCell(row, c++, bean.getCthNum4(), wb, csLeft);
            
            createCell(row, c++, bean.getCthTyLe(), wb, csLeft);
            createCell(row, c++, bean.getCthMuc(), wb, csLeft);
            
            String projectName = bean.getProjectId()==0?"Direct":"Inclusion 3";
            createCell(row, c++, projectName, wb, csLeft);
            
            createCell(row, c++, "", wb, csLeft);
            r++;
            c = 0;
        }
        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }

    public String reportDisSupport(FReportKpi beanTemp, FSeed seed, String excelFile) throws EException,
                                                           FileNotFoundException,
                                                           IOException {
        String LOCATION = toString() + "~>reportDisSupport()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
    
    
        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;
    
        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont font11 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);
    
        HSSFFont fontB12 =
            getFont(wb, "Times New Roman", Integer.valueOf(12), true);
    
    
        CellStyle csLeftNone =
            getStyle(wb, font11, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);
        
        CellStyle csCenterNone =
            getStyle(wb, font11, (short)2, (short)3, (short)0, (short)0,
                     (short)0, (short)0);
    
    
        CellStyle csRight =
            getStyle(wb, font11, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);
    
    
        CellStyle csLeft =
            getStyle(wb, font11, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);
    
    
        CellStyle csCenterWrap =
            getStyle(wb, font11, (short)2, (short)3, (short)1, (short)1,
                     (short)1, (short)1);
    
    
        CellStyle csLeftB =
            getStyle(wb, fontB12, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);
    
    
        csCenterWrap.setWrapText(true);
    
        int dong = 6;
        HSSFRow row = null;
    
        row = sheet.createRow(0);
        createCell(row, 0,
                   beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                   Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                   csLeftNone);
        
        row = sheet.getRow(2);
        createCell(row, 6,
                   bean.ncrToString("Th&#225;ng : ") + beanTemp.getVal(), wb,
                   csCenterNone);  
        
        int r = 6;
        int c = 0;
        int stt = 1;
        for (int i = 0; i < beanTemp.getStore().size(); i++) {
            bean = (FReportKpi)beanTemp.getStore().get(i);
            row = sheet.createRow(r);            
            createCell(row, c++, stt++, wb, csLeft);                        
            createCell(row, c++, bean.getMaSo(), wb, csLeft);
            createCell(row, c++, bean.getTen(), wb, csLeft);
            
            createCell(row, c++, bean.getSoNha(), wb, csLeft);
            createCell(row, c++, bean.getTinhName(), wb, csLeft);
            createCell(row, c++, bean.getQhuName(), wb, csLeft);
            createCell(row, c++, bean.getPxaName(), wb, csLeft);
            
            createCell(row, c++,
                       bean.getYearBirth() == 0 ? "" : Integer.valueOf(bean.getYearBirth()),
                       wb, csCenterWrap);
    
            createCell(row, c++, bean.getSex(), wb, csLeft);
            createCell(row, c++, bean.getDienThoai(), wb, csLeft);
            
            createCell(row, c++, bean.getDangTat(), wb, csLeft);            
            createCell(row, c++, bean.getTTrangKTat(), wb, csLeft);
            createCell(row, c++, bean.getNgayMoHS(), wb, csCenterWrap);
            
            createCell(row, c++, bean.getKhamNum1(), wb, csRight);
            createCell(row, c++, bean.getKhamNum2(), wb, csRight);
            createCell(row, c++, bean.getKhamNum3(), wb, csRight);
            createCell(row, c++, bean.getKhamNum4(), wb, csRight);
            
            r++;
            c = 0;
        }
        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }

    public FBeans getDataDisExport(Connection cnn, int lvl,
                                   int locationId, int duAnId,
                                   String createDateFrom, String createDateTo,
                                   String dvuDateFrom, String dvuDateTo,
                                   String tdgDateFrom, String tdgDateTo,
                                   String dmcDateFrom, String dmcDateTo) throws EException,
                                                          SQLException {
        String LOCATION = toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        String sql = "", cmd = "";
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            ArrayList params = new ArrayList();
            sql =  SQL_SELECT_KPI_DIS_EXPORT;
            if (!"".equals(createDateFrom)) {
                sql = sql.replace("[$TU_NGAY$]", " AND TO_DATE(b.create_date,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(createDateFrom));
            } else {
                sql = sql.replace("[$TU_NGAY$]", " ");
            }
            
            if (!"".equals(createDateTo)) {
                sql = sql.replace("[$DEN_NGAY$]", " AND TO_DATE(b.create_date,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(createDateTo));
            } else  {
                sql = sql.replace("[$DEN_NGAY$]", " ");
            }
                      
            // Ngay-Nhan-Dich-Vu
            if (!"".equals(dvuDateFrom)) {
                sql = sql.replace("[$TU_DVU$]", " AND TO_DATE(b.ngay_ho_tro,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(dvuDateFrom));
            } else  {
                sql = sql.replace("[$TU_DVU$]", " ");
            }
            
            if (!"".equals(dvuDateTo)) {
                sql = sql.replace("[$DEN_DVU$]", " AND TO_DATE(b.ngay_ho_tro,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(dvuDateTo));
            } else  {
                sql = sql.replace("[$DEN_DVU$]", " ");
            }
            
            // Ngay-Tai-BD
            if (!"".equals(tdgDateFrom)) {
                sql = sql.replace("[$TU_TDG$]", " AND TO_DATE(b.dg_ngay,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(tdgDateFrom));
            } else {
                sql = sql.replace("[$TU_TDG$]", " ");
            }
            
            if (!"".equals(tdgDateTo)) {
                sql = sql.replace("[$DEN_TDG$]", " AND TO_DATE(b.dg_ngay,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(tdgDateTo));
            } else {
                sql = sql.replace("[$DEN_TDG$]", " ");
            }                                            
            
            // Ngay-dong-mo-ca
            if (!"".equals(dmcDateFrom)) {
                sql = sql.replace("[$TU_DMC$]", " AND TO_DATE(b.ngay_dong_hs,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(dmcDateFrom));
            } else {
                sql = sql.replace("[$TU_DMC$]", " ");
            }
            
            if (!"".equals(dmcDateTo)) {
                sql = sql.replace("[$DEN_DMC$]", " AND TO_DATE(b.ngay_dong_hs,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(dmcDateTo));  
            } else {
                sql = sql.replace("[$DEN_DMC$]", " ");
            }
            
            if (duAnId>-1) {
                sql += " AND a.du_an = "+duAnId;
            }
            
            if (lvl==1) {
                sql += " AND a.id_tinh="+locationId;
            } else if (lvl==2) {
                sql += " AND a.id_district="+locationId;
            } else if (lvl==3) {
                sql += " AND a.id_commune="+locationId;
            }
            
            sql += " ORDER BY id, tinh_id nulls last, order_by DESC";
            System.out.println("#SQL# " + sql);            
            
            prpstm = prepareStatement(cnn, sql, params);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                bean = new FReportKpi();

                bean.setStt(rs.getInt("stt"));
                bean.setTinhName(rs.getString("tinh_name"));
                bean.setMaSo(rs.getString("maso"));
                bean.setTen(rs.getString("ten"));
                bean.setYearBirth(rs.getInt("year_of_birthday"));
                bean.setSex(rs.getString("sex"));
                bean.setSoNha(rs.getString("sonha"));
                bean.setDienThoai(rs.getString("dienthoai"));
                bean.setCreateDate(rs.getString("create_date"));

                bean.setNcsTen(rs.getString("ten_ncs"));
                bean.setNcsSdt(rs.getString("sdt_ncs"));
                bean.setNcsSex(rs.getString("gioitinh_ncs"));

                bean.setTrangThai(rs.getString("trangthai"));
                bean.setNgayDongHS(rs.getString("ngay_dong_hs"));
                bean.setDangTat(rs.getString("dang_tat"));
                bean.setMucDo(rs.getString("muc_do"));

                bean.setNgayDangTat(rs.getString("ngay_phat_hien_ktat"));
                bean.setDaCam(rs.getString("da_cam"));

                bean.setNhuCau(rs.getString("nhu_cau"));
                bean.setNCauPHCN(rs.getString("nhucau_noi_nhan"));
                bean.setNCauCThiepCN(rs.getString("nhucau_can_thiep_cn"));
                bean.setNCauDungCu(rs.getString("nhucau_ten_dung_cu"));
                bean.setNCauCTaoCTVS(rs.getString("nhucau_cai_thien_ctvs"));
                bean.setNgayNhuCau(rs.getString("ngay_nhu_cau"));

                bean.setHoTro(rs.getString("hotro_da_nhan"));
                bean.setHTroPHCN(rs.getString("hotro_noi_nhan"));
                bean.setHTroCThiepCN(rs.getString("hotro_can_thiep_cn"));
                bean.setHTroDungCu(rs.getString("hotro_ten_dung_cu"));
                bean.setHTroCTaoCTVS(rs.getString("hotro_cai_thien_ctvs"));
                bean.setHTroNguon((rs.getString("hotro_nguon")));
                
                bean.setNgayHoTro(rs.getString("ngay_ho_tro"));
                bean.setProjectId(rs.getInt("du_an"));
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
            logger.error(sqle.toString());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }
    
    public FBeans getDataDisExport2020(Connection cnn, int lvl, int locationId,
                                       String createDateFrom, String createDateTo, 
                                       String dvuDateFrom, String dvuDateTo,
                                       String tdgDateFrom, String tdgDateTo,
                                       String dmcDateFrom, String dmcDateTo) throws EException,
                                                          SQLException {
        String LOCATION = toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        String sql = "", cmd = "";
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            ArrayList params = new ArrayList();
            sql =  SQL_SELECT_KPI_DIS_EXPORT_2020;
            
            // Ngay-Mo-So
            if (!"".equals(createDateFrom)) {
                sql = sql.replace("[$TU_NGAY$]", " AND TO_DATE(a.create_date,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(createDateFrom));
            } else {
                sql = sql.replace("[$TU_NGAY$]", " ");
            }
            
            if (!"".equals(createDateTo)) {
                sql = sql.replace("[$DEN_NGAY$]", " AND TO_DATE(a.create_date,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(createDateTo));
            } else  {
                sql = sql.replace("[$DEN_NGAY$]", " ");
            }
            
            // Ngay-Nhan-Dich-Vu
            if (!"".equals(dvuDateFrom)) {
                sql = sql.replace("[$TU_DVU$]", " AND TO_DATE(a.ngay_ho_tro,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(dvuDateFrom));
            } else  {
                sql = sql.replace("[$TU_DVU$]", " ");
            }
            
            if (!"".equals(dvuDateTo)) {
                sql = sql.replace("[$DEN_DVU$]", " AND TO_DATE(a.ngay_ho_tro,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(dvuDateTo));
            } else  {
                sql = sql.replace("[$DEN_DVU$]", " ");
            }
            
            // Ngay-Tai-BD
            if (!"".equals(tdgDateFrom)) {
                sql = sql.replace("[$TU_TDG$]", " AND TO_DATE(a.dg_ngay,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(tdgDateFrom));
            } else {
                sql = sql.replace("[$TU_TDG$]", " ");
            }
            
            if (!"".equals(tdgDateTo)) {
                sql = sql.replace("[$DEN_TDG$]", " AND TO_DATE(a.dg_ngay,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(tdgDateTo));
            } else {
                sql = sql.replace("[$DEN_TDG$]", " ");
            }                                            
            
            // Ngay-dong-mo-ca
            if (!"".equals(dmcDateFrom)) {
                sql = sql.replace("[$TU_DMC$]", " AND TO_DATE(a.ngay_dong_hs,'DD/MM/YYYY') >= ?");
                params.add(bean.stringToSqlDate(dmcDateFrom));
            } else {
                sql = sql.replace("[$TU_DMC$]", " ");
            }
            
            if (!"".equals(dmcDateTo)) {
                sql = sql.replace("[$DEN_DMC$]", " AND TO_DATE(a.ngay_dong_hs,'DD/MM/YYYY') <= ?");
                params.add(bean.stringToSqlDate(dmcDateTo));  
            } else {
                sql = sql.replace("[$DEN_DMC$]", " ");
            }
            
            
            if (lvl==1) {
                sql += " AND a.tinh_id="+locationId;
            } else if (lvl==2) {
                sql += " AND a.qhu_id="+locationId;
            } else if (lvl==3) {
                sql += " AND a.pxa_id="+locationId;
            } 
            
            sql += " ORDER BY a.tinh_id, a.qhu_id, a.pxa_id, b.maso ASC";
            
            prpstm = prepareStatement(cnn, sql, params);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                bean = new FReportKpi();
  
                bean.setStt(rs.getInt("stt"));
                bean.setTinhName(rs.getString("tinh_name"));
                bean.setQhuName(rs.getString("qhu_name"));
                bean.setPxaName(rs.getString("pxa_name"));
                                
                bean.setMaSo(rs.getString("maso"));
                bean.setTen(rs.getString("ten"));
                bean.setYearBirth(rs.getInt("year_of_birthday"));
                bean.setSex(rs.getString("sex"));
                bean.setSoNha(rs.getString("sonha"));
                bean.setDienThoai(rs.getString("dienthoai"));
                bean.setCreateDate(rs.getString("create_date"));
  
                bean.setNcsTen(rs.getString("ten_ncs"));
                bean.setNcsSdt(rs.getString("sdt_ncs"));
                bean.setNcsSex(rs.getString("gioitinh_ncs"));
  
                bean.setTrangThai(rs.getString("trangthai"));
                bean.setNgayDongHS(rs.getString("ngay_dong_hs"));
                bean.setDangTat(rs.getString("dang_tat"));
                bean.setMucDo(rs.getString("muc_do"));
  
                bean.setNgayDangTat(rs.getString("ngay_phat_hien_ktat"));
                bean.setDaCam(rs.getString("da_cam"));
  
                bean.setNhuCau(rs.getString("nhu_cau"));
                bean.setNCauPHCN(rs.getString("nhucau_noi_nhan"));
                bean.setNCauCThiepCN(rs.getString("nhucau_can_thiep_cn"));
                bean.setNCauDungCu(rs.getString("nhucau_ten_dung_cu"));
                bean.setNCauCTaoCTVS(rs.getString("nhucau_cai_thien_ctvs"));
                
                bean.setNgayNhuCau(rs.getString("ngay_nhu_cau"));
  
                bean.setHoTro(rs.getString("hotro_da_nhan"));
                bean.setHTroPHCN(rs.getString("hotro_noi_nhan"));
                bean.setHTroCThiepCN(rs.getString("hotro_can_thiep_cn"));
                bean.setHTroDungCu(rs.getString("hotro_ten_dung_cu"));
                bean.setHTroCTaoCTVS(rs.getString("hotro_cai_thien_ctvs"));
                // bean.setHTroNguon(rs.getString("hotro_nguon"));
                
                bean.setNgayHoTro(rs.getString("ngay_ho_tro"));
                
                bean.setDangTatVanDong(rs.getString("dangtat_vdong"));
                bean.setNcauVLTL(rs.getString("ncau_vltl"));
                bean.setNcauHDTL(rs.getString("ncau_hdtl"));
                bean.setNcauNNTL(rs.getString("ncau_nntl"));
                bean.setNcauDCHT(rs.getString("ncau_dcht"));
                bean.setNcauWC(rs.getString("ncau_wc"));
                
                bean.setDgNum0(rs.getString("dg_num_0"));
                bean.setDgNum1(rs.getString("dg_num_1"));
                bean.setDgNum2(rs.getString("dg_num_2"));
                bean.setDgNum3(rs.getString("dg_num_3"));
                bean.setDgNum4(rs.getString("dg_num_4"));
                
                bean.setDgCTh1(rs.getString("dg_cth_1"));
                bean.setDgCTh2(rs.getString("dg_cth_2"));
                bean.setDgCTh3(rs.getString("dg_cth_3"));
                bean.setDgCTh4(rs.getString("dg_cth_4"));
                
                bean.setHtroPhcnVNAH(rs.getString("htro_phcn_vnah"));
                bean.setHtroPhcnTTP(rs.getString("htro_phcn_ttp"));
                bean.setHtroPhcnQHU(rs.getString("htro_phcn_qhu"));
                bean.setHtroPhcnPXA(rs.getString("htro_phcn_pxa"));
                
                bean.setDgNgay(rs.getString("dg_ngay"));
                bean.setCthNum0(rs.getString("cth_num_0"));
                bean.setCthNum1(rs.getString("cth_num_1"));
                bean.setCthNum2(rs.getString("cth_num_2"));
                bean.setCthNum3(rs.getString("cth_num_3"));
                bean.setCthNum4(rs.getString("cth_num_4"));
                
                bean.setCthTyLe(rs.getString("cth_tyle"));
                bean.setCthMuc(rs.getString("cth_muc"));
                bean.setProjectId(rs.getInt("du_an"));
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
            logger.error(sqle.toString());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }


    public String reportCommune(FReportKpi beanTemp, FSeed seed,
                                String excelFile) throws EException,
                                                         FileNotFoundException,
                                                         IOException {
        String LOCATION = toString() + "~>exportReportSuppor()";
        FReportKpi bean = (FReportKpi)seed;
        String excelPath =
            AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP +
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";


        String excelDown = excelPath + seed.me.getSessionID();
        HSSFWorkbook wb = null;
        HSSFSheet sheet = null;

        File file = new File(excelPath, excelFile);
        if (file.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
                wb = new HSSFWorkbook(fis);
            } catch (Exception e) {
                wb = new HSSFWorkbook();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont font11 =
            getFont(wb, "Times New Roman", Integer.valueOf(11), false);
        HSSFFont fontB12 =
            getFont(wb, "Times New Roman", Integer.valueOf(12), true);

        CellStyle csLeftNone =
            getStyle(wb, font11, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csRight =
            getStyle(wb, font11, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csLeft =
            getStyle(wb, font11, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csCenterWrap =
            getStyle(wb, fontB12, (short)2, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csCenterNone =
            getStyle(wb, font11, (short)2, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        CellStyle csCenter =
            getStyle(wb, font11, (short)2, (short)3, (short)1, (short)1,
                     (short)1, (short)1);


        CellStyle csLeftB =
            getStyle(wb, fontB12, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);


        csCenterWrap.setWrapText(true);

        int dong = 6;
        FReportKpi beanObj = null;
        String strDate = "";
        String expTime = beanTemp.getVal();
        if (expTime.startsWith("Q")) {
            strDate = bean.ncrToString("Qu&#253;: ") + expTime;
        } else {
            strDate = expTime.indexOf("/")>-1 ? bean.ncrToString("Th&#225;ng: ") + expTime : bean.ncrToString("N&#259;m: ") + expTime;
        }
        
        HSSFRow row = null;
        if (beanTemp.getExtend() == 0) {
            row = sheet.createRow(0);
            createCell(row, 0,
                       beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                       Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                       csLeftNone);


            row = sheet.createRow(1);
            createCell(row, 0, bean.getTinhName(), wb, csLeftB);

            row = sheet.createRow(4);
            createCell(row, 1, strDate, wb, csCenterNone);

            int r = 7;
            int c = 2;
            for (int i = 0; i < beanTemp.getStore().size(); i++) {
                bean = (FReportKpi)beanTemp.getStore().get(i);
                createCell(sheet.getRow(r), c, bean.getTotalVisit(), wb,
                           csRight);


                r = 9;
                createCell(sheet.getRow(r++), c, bean.getTotalVisitMale(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalVisitFemale(),
                           wb, csRight);

                r = 12;
                createCell(sheet.getRow(r++), c, bean.getTotalA(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalB(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalC(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalD(), wb,
                           csRight);

                r = 16;
                createCell(sheet.getRow(r++), c, bean.getTotalDisVisit(), wb,
                           csRight);

                r = 18;
                createCell(sheet.getRow(r++), c, bean.getTotalDisMale(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalDisFemale(), wb,
                           csRight);

                r = 21;
                createCell(sheet.getRow(r++), c, bean.getTotalDisLv1(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalDisLv2(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalDisLv3(), wb,
                           csRight);
                createCell(sheet.getRow(r++), c, bean.getTotalDisLv4(), wb,
                           csRight);
            }
        } else {
            row = sheet.createRow(0);
            createCell(row, 0,
                       beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") +
                       Utilities.getStringDateFormat("dd/MM/yyyy"), wb,
                       csLeftNone);


            row = sheet.createRow(4);
            createCell(row, 6, strDate, wb, csCenterNone);

            row = sheet.createRow(6);
            createCell(row, 1,
                       bean.ncrToString("H&#7885; t&#234;n CB chuy&#234;n tr&#225;ch: "),
                       wb, csLeftNone);
            createCell(row, 7,
                       bean.ncrToString("&#272;&#7883;a b&#224;n: ") + beanTemp.getTinhName(),
                       wb, csLeftNone);

            int r = 11;
            int c = 0;
            for (int i = 0; i < beanTemp.getStore().size(); i++) {
                bean = (FReportKpi)beanTemp.getStore().get(i);
                row = sheet.createRow(r);
                createCell(row, c++, Integer.valueOf(bean.getStt()), wb,
                           csLeft);
                createCell(row, c++, bean.getCommNgay(), wb, csLeft);
                createCell(row, c++, bean.getCommHoTen(), wb, csLeft);
                createCell(row, c++, bean.getCommMaSo(), wb, csLeft);
                createCell(row, c++, bean.getCommGioiTinh(), wb, csLeft);
                createCell(row, c++, bean.getCommDiaChi(), wb, csLeft);
                createCell(row, c++, bean.getMucDo(), wb, csLeft);

                createCell(row, c++,
                           bean.getCommP1().equals("1") ? bean.ncrToString("C&#243;") :
                           bean.ncrToString("Kh&#244;ng"), wb, csCenter);


                createCell(row, c++,
                           bean.getCommP2().equals("1") ? bean.ncrToString("C&#243;") :
                           bean.ncrToString("Kh&#244;ng"), wb, csCenter);


                createCell(row, c++,
                           bean.getCommP3().equals("1") ? bean.ncrToString("C&#243;") :
                           bean.ncrToString("Kh&#244;ng"), wb, csCenter);


                createCell(row, c++,
                           bean.getCommP4().equals("1") ? bean.ncrToString("C&#243;") :
                           bean.ncrToString("Kh&#244;ng"), wb, csCenter);


                createCell(row, c++,
                           bean.getCommP5().equals("1") ? bean.ncrToString("C&#243;") :
                           bean.ncrToString("Kh&#244;ng"), wb, csCenter);


                createCell(row, c++,
                           bean.getCommP6().equals("1") ? bean.ncrToString("C&#243;") :
                           bean.ncrToString("Kh&#244;ng"), wb, csCenter);


                createCell(row, c++,
                           bean.getCommP7().equals("1") ? bean.ncrToString("C&#243;") :
                           bean.ncrToString("Kh&#244;ng"), wb, csCenter);


                createCell(row, c++, bean.getCommP8(), wb, csCenter);
                r++;
                c = 0;
            }
        }
        sheet.setHorizontallyCenter(true);
        sheet.setMargin((short)2, 0.0D);
        sheet.setMargin((short)3, 2.5D);
        sheet.setMargin((short)0, 0.25D);
        sheet.setMargin((short)1, 0.25D);
        FileOutputStream fos = new FileOutputStream(excelDown);
        wb.write(fos);
        fos.close();
        return excelDown;
    }


    public FBeans getDataDisCommuneSummary(Connection cnn, 
                                           int lvl,
                                           int locationId,
                                           String val) throws EException,
                                                              SQLException {
        String LOCATION = toString() + "~~>getDataDisCommuneSummary()";
        CallableStatement state = null;
        state = cnn.prepareCall("{call kpi_report_commune(?,?,?,?)}");

        String from = null;
        String to = null;
        String quarter = "";
        int year = 0;
        String[] arrTemp = null;
        if (val.startsWith("Q")) {
            arrTemp = val.split("/");
            quarter = arrTemp[0];
            year = Integer.parseInt(arrTemp[1]);
            Calendar cal = Calendar.getInstance();
            if ("Q1".equals(quarter)) {
                from = "10/" + (year - 1);
                to = "12/" + (year - 1);
            } else if ("Q2".equals(quarter)) {
                from = "01/" + year;
                to = "03/" + year;
            } else if ("Q3".equals(quarter)) {
                from = "04/" + year;
                to = "06/" + year;
            } else if ("Q4".equals(quarter)) {
                from = "07/" + year;
                to = "09/" + year;
            }
        } else {
            if (val.indexOf("/")>-1) {
                from = val;
                to = val;
            } else {                
                from = "10/"+ (Integer.parseInt(val)-1);
                to = "09/" + val;
            }            
        }

        state.setInt(1, lvl);
        state.setInt(2, locationId);
        state.setString(3, from);
        state.setString(4, to);    
        state.execute();
        
        logger.debug("{call kpi_report_commune("+lvl+","+locationId+",'"+from+"','"+to+"')}");
        
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            prpstm = prepareStatement(cnn, SQL_SELECT_KPI_DIS_COMMUNE_SUMMARY, null);
            rs = prpstm.executeQuery();
            while ((rs != null) && (rs.next())) {
                bean = new FReportKpi();

                bean.setTotalVisit(String.valueOf(rs.getInt("total_visit")));
                bean.setTotalVisitMale(String.valueOf(rs.getInt("total_visit_male")));
                bean.setTotalVisitFemale(String.valueOf(rs.getInt("total_visit_female")));

                bean.setTotalA(String.valueOf(rs.getInt("total_a")));
                bean.setTotalB(String.valueOf(rs.getInt("total_b")));
                bean.setTotalC(String.valueOf(rs.getInt("total_c")));
                bean.setTotalD(String.valueOf(rs.getInt("total_d")));

                bean.setTotalDisVisit(String.valueOf(rs.getInt("total_dis_visit")));
                bean.setTotalDisMale(String.valueOf(rs.getInt("total_dis_male")));
                bean.setTotalDisFemale(String.valueOf(rs.getInt("total_dis_female")));

                bean.setTotalDisLv1(String.valueOf(rs.getInt("total_dis_lv1")));
                bean.setTotalDisLv2(String.valueOf(rs.getInt("total_dis_lv2")));
                bean.setTotalDisLv3(String.valueOf(rs.getInt("total_dis_lv3")));
                bean.setTotalDisLv4(String.valueOf(rs.getInt("total_dis_lv4")));

                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
            logger.error(sqle.toString());
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prpstm);
        }
        return beans;
    }


    public FBeans getDataDisCommuneDetail(Connection cnn, int lvl,
                                          int locationId,
                                          String val) throws EException,
                                                             SQLException {
        String LOCATION = toString() + "~~>getDataDisCommuneDetail()";
        PreparedStatement prstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpi bean = new FReportKpi();
        try {
            String from = null;
            String to = null;
            String quarter = "";
            int year = 0;
            String[] arrTemp = null;
            if (val.startsWith("Q")) {
                arrTemp = val.split("/");
                quarter = arrTemp[0];
                year = Integer.parseInt(arrTemp[1]);
                Calendar cal = Calendar.getInstance();
                if ("Q1".equals(quarter)) {
                    from = "10/" + (year - 1);
                    to = "12/" + (year - 1);
                } else if ("Q2".equals(quarter)) {
                    from = "01/" + year;
                    to = "03/" + year;
                } else if ("Q3".equals(quarter)) {
                    from = "04/" + year;
                    to = "06/" + year;
                } else if ("Q4".equals(quarter)) {
                    from = "07/" + year;
                    to = "09/" + year;
                }
            } else {
                if (val.indexOf("/")>-1) {
                    from = val;
                    to = val;
                } else {
                    from = "10/"+ (Integer.parseInt(val)-1);
                    to = "09/" + val;
                }   
            }
            String SQL = SQL_SELECT_KPI_DIS_COMMUNE_DETAIL;

            if (locationId > 0) {
                if (lvl == 1) {
                    SQL = SQL + " AND dis.id_tinh =" + locationId;
                } else if (lvl == 2) {
                    SQL = SQL + " AND dis.id_district =" + locationId;
                } else if (lvl == 3) {
                    SQL = SQL + " AND dis.id_commune =" + locationId;
                }
            }

            SQL = SQL + " ORDER BY dis.id_tinh, rpt.create_date DESC";

            List params = new ArrayList();
            params.add(from);
            params.add(to);
            prstm = prepareStatement(cnn, SQL, params);
            rs = prstm.executeQuery();

            int stt = 1;
            while ((rs != null) && (rs.next())) {
                bean = new FReportKpi();
                bean.setStt(stt++);
                bean.setCommNgay(bean.dateToString(rs.getDate("create_date")));
                bean.setCommHoTen(rs.getString("ten"));
                bean.setCommMaSo(rs.getString("maso"));
                bean.setCommGioiTinh(rs.getString("gioitinh").equals("1") ?
                                     bean.ncrToString("Nam") :
                                     bean.ncrToString("N&#7919;"));
                int mucdo = rs.getInt("mucdo_id");
                String strMucDo = "";
                switch (mucdo) {
                case 2:
                    strMucDo =
                            bean.ncrToString("Kh&#244;ng x&#225;c &#273;&#7883;nh");
                    break;
                case 3:
                    strMucDo = bean.ncrToString("Nh&#7865;");
                    break;
                case 4:
                    strMucDo = bean.ncrToString("N&#7863;ng");
                    break;
                case 5:
                    strMucDo =
                            bean.ncrToString("&#272;&#7863;c bi&#7879;t n&#7863;ng");
                }

                bean.setMucDo(strMucDo);
                bean.setCommDiaChi(rs.getString("sonha"));
                bean.setCommP1(rs.getString("P1"));
                bean.setCommP2(rs.getString("P2"));
                bean.setCommP3(rs.getString("P3"));
                bean.setCommP4(rs.getString("P4"));
                bean.setCommP5(rs.getString("P5"));
                bean.setCommP6(rs.getString("P6"));
                bean.setCommP7(rs.getString("P7"));
                bean.setCommP8(bean.dateToString(rs.getDate("P8")));
                beans.add(bean);
            }
        } catch (SQLException sqle) {
            if (AppConfigs.APP_DEBUG) {
                throw new EException(LOCATION, sqle);
            }
        } finally {
            closeResultSet(rs);
            closePreparedStatement(prstm);
        }
        return beans;
    }
}
