package com.dao.disability.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.report.FReportKpiIndicator;
import com.form.disability.report.FReportKpiInsurance;

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

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportKpiInsurance extends FExportExcel {
    
    public String ReportExcel(FReportKpiInsurance beanTemp, FSeed seed, String excelFile) throws EException, 
                                                       FileNotFoundException, 
                                                       IOException {
        final String LOCATION = this.toString() + "~>DReportKpiInsurance()";
        FReportKpiInsurance bean = (FReportKpiInsurance)seed;
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
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
        HSSFFont fontArial14 = getFont(wb, "Times New Roman", 11, false);
        HSSFFont fontArialBold14 = getFont(wb, "Times New Roman", 11, true);
        CellStyle csLeft = getStyle(wb, fontArial14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle csLeftNone = getStyle(wb, fontArial14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);
        
        CellStyle csRight = getStyle(wb, fontArial14, CellStyle.ALIGN_RIGHT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle csBRight = getStyle(wb, fontArialBold14, CellStyle.ALIGN_RIGHT, 
                   CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                   CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                   CellStyle.BORDER_THIN);
        
        CellStyle csCenterWrap = getStyle(wb, fontArialBold14, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle csLeftWrap = getStyle(wb, fontArialBold14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        csCenterWrap.setWrapText(true);
        
        HSSFRow row = null;
        row = sheet.getRow(2);
        //createCell(row, 0, bean.ncrToString(beanTemp.ncrToString("Tuy&#7871;n : ")+bean.getTinhName()),wb, csLeftNone);
        
        /*
        if (Constant.REPORT_PERIOD_TYPE_MONTH.equals(beanTemp.getPeriodType())) {
            createCell(row, 1, bean.ncrToString(beanTemp.ncrToString("Th&#225;ng : ")+beanTemp.getVal()),wb, csLeftNone);
        } else if (Constant.REPORT_PERIOD_TYPE_QUARTER.equals(beanTemp.getPeriodType())) {
            createCell(row, 1, bean.ncrToString(beanTemp.ncrToString("Qu&#253; : ")+beanTemp.getVal()),wb, csLeftNone);
        } else if (Constant.REPORT_PERIOD_TYPE_YEAR.equals(beanTemp.getPeriodType())) {
            createCell(row, 1, bean.ncrToString(beanTemp.ncrToString("N&#259;m : ")+beanTemp.getVal()),wb, csLeftNone);
        } else if (Constant.REPORT_PERIOD_TYPE_FT.equals(beanTemp.getPeriodType())){
            createCell(row, 1, bean.ncrToString(beanTemp.ncrToString("T&#7915; th&#225;ng : ")+beanTemp.getVal()),wb, csLeftNone);
        }
        */
        
        int dong = 5;
        int indNum = 1;
        int curIndId = 0;
        FReportKpiIndicator beanObj = null;

        for (int i = 0; i < beanTemp.getStore().size(); i++) {
            int cot = 0;
            beanObj = (FReportKpiIndicator)beanTemp.getStore().get(i);
            row = sheet.createRow(dong);                      
            indNum = beanObj.getIndNum();
            if (i==0){
                if (indNum>1) {
                    --indNum;
                    createRegion(wb, row, dong, dong+indNum, 0, 0, csLeft, beanObj.getIndCode(), 1, 0);
                }
            } else {
                if (beanObj.getIndId()!=curIndId) {
                    if (indNum>1) {
                        --indNum;
                        createRegion(wb, row, dong, dong+indNum, 0, 0, csLeft, beanObj.getIndCode(), 1, 0);
                    } 
                }
            }
          
            curIndId = beanObj.getIndId(); 
            createCell(row, cot++, beanObj.getIndCode(), wb, csLeft);            
            cot=1;
            createCell(row, cot++, beanObj.getIndDesc(), wb, csLeft);
            createCell(row, cot++, beanObj.getBaseline(), wb, csRight);
            createCell(row, cot++, beanObj.getTarget(), wb, csRight);
            createCell(row, cot++, beanObj.getActual(), wb, csRight);
            
            dong++;
            indNum = 1;
            wrap(row, cot++, csLeftWrap);
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

    public FBeans getData(Connection cnn, int periodType, int tinh_id, String parameter, int year) throws EException, SQLException {
        final String LOCATION = this.toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportKpiIndicator bean = new FReportKpiIndicator();
        try {
            
            CallableStatement state = cnn.prepareCall("{call report_indicator(?, ?, ?, ?)}");
            state.setInt(1, periodType);
            state.setInt(2, tinh_id);
            state.setString(3, parameter);
            state.setInt(4, year);
            state.execute();
            
            String sql = "select num_ind, ind_id, ind_code, ind_name, baseline, target, actual, 0 from kpi_report_indicator where 1=1 order by stt";            
            prpstm = prepareStatement(cnn, sql, null);
            rs = prpstm.executeQuery();            
            while (rs!= null && rs.next()){
                int i = 1;
                bean = new FReportKpiIndicator();
                bean.setIndNum(rs.getInt(i++));                
                
                bean.setIndId(rs.getInt(i++));
                bean.setIndCode(rs.getString(i++));
                bean.setIndDesc(rs.getString(i++));
                
                bean.setBaseline(rs.getString(i++));
                bean.setTarget(rs.getString(i++));
                bean.setActual(rs.getString(i++));
                bean.setPercent(rs.getString(i++));
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

