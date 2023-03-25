package com.dao.disability.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.report.FReportInforNKT;
import com.form.disability.report.FReportTotal;

import com.lib.AppConfigs;

import com.util.Formater;

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


public class DReportInforNKT extends FExportExcel {
    
    public String ReportExcel(FReportInforNKT beanTemp, FSeed seed, 
                              String excelFile) throws EException, 
                                                       FileNotFoundException, 
                                                       IOException {
        final String LOCATION = 
            this.toString() + "~>ReportExcelAllReportAddTime()";
        FReportTotal bean = (FReportTotal)seed;
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
        String excelDown = excelPath + seed.me.getSessionID();
        int sheet_index = 1;
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
                    try {
                        fis.close();
                    } catch (Exception e) {
                    }
                }
            }
        } else {
                wb = new HSSFWorkbook();
        }
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);
        HSSFFont fontArialBold14 = getFont(wb, "Times New Roman", 11, false);
        CellStyle style = getStyle(wb, fontArialBold14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle cs_none = getStyle(wb, fontArialBold14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE, CellStyle.BORDER_NONE, 
                     CellStyle.BORDER_NONE);

        CellStyle style1 = getStyle(wb, fontArialBold14, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        CellStyle styleWrap = getStyle(wb, fontArialBold14, CellStyle.ALIGN_CENTER, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
        styleWrap.setWrapText(true);
        
        HSSFRow row = null;
        row = sheet.createRow(1);
        createCell(row, 0, bean.ncrToString("&#272;&#7883;a b&#224;n: "), wb, cs_none);        
        createRegion(wb, row, 1, 1, 1, 5, cs_none, beanTemp.getTinhName(), 0, 0);
        
        row = sheet.createRow(2);        
        createCell(row, 0, bean.ncrToString("T&#7893;ng s&#7889; NKT: "), wb, cs_none);
        createCell(row, 1, Formater.num2str(beanTemp.getTotal()), wb, cs_none);
        
        row = sheet.createRow(3);
        createCell(row, 0, bean.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: "), wb, cs_none);
        createCell(row, 1, bean.dateToString(bean.getCurrentDate()), wb, cs_none);
        
        int dong = 10;
        FReportInforNKT beanC = null;

        for (int i = 0; i < beanTemp.getStore().size(); i++) {
            int cot = 0;
            beanC = (FReportInforNKT)beanTemp.getStore().get(i);
            row = sheet.createRow(dong+i);
           
            createCell(row, cot++, beanC.getMa(), wb, style);            
            createCell(row, cot++, beanC.getTen(), wb, style);
            createCell(row, cot++, beanC.getNgaySinh(), wb, style1);
            createCell(row, cot++, beanC.getSex(), wb, style1);
            createCell(row, cot++, beanC.getQuanhuyen(), wb, style);
            createCell(row, cot++, beanC.getPhuongxa(), wb, style);
            createCell(row, cot++, beanC.getDiachi(), wb, style);
            createCell(row, cot++, beanC.getThonto(), wb, style); 
            createCell(row, cot++, beanC.getDangTats(), wb, style);
            
            createCell(row, cot++, beanC.getParamvalue_1(), wb, style);
            createCell(row, cot++, beanC.getParamvalue_2(), wb, style1); 
            createCell(row, cot++, beanC.getParamvalue_3(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_4(), wb, style1);            
            createCell(row, cot++, beanC.getParamvalue_5(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_6(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_7(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_8(), wb, style1);            
            createCell(row, cot++, beanC.getParamvalue_9(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_10(), wb, style1);            
            createCell(row, cot++, beanC.getParamvalue_11(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_12(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_13(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_14(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_15(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_16(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_17(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_18(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_19(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_20(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_21(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_22(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_23(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_24(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_25(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_26(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_27(), wb, style1);
            createCell(row, cot++, beanC.getParamvalue_28(), wb, style1);
            createCell(row, cot++, beanC.getDacam() + "", wb, style1);
            createCell(row, cot++, beanC.getDacam() + "", wb, style1);
            //wrap(row, cot++, styleWrap);
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

    public FBeans getData(Connection cnn, int tinh_id, int qlc) throws EException, SQLException {
        final String LOCATION = this.toString() + "~~>getDataToExport()";
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportInforNKT bean = new FReportInforNKT();
        try {
            String param_report = "#0#,#53#,#51#,#129#,#15#,#261#,#13#,#14#,#10#,#20#,#0#,#149#,#25#,#157#,#75#,#177#,#77#,#79#,#89#,#91#,#217#,#231#,#235#,#237#,#97#,#169#,#251#,#93#";
            CallableStatement state = cnn.prepareCall("{call report_infor_nkt(?, ?, ?)}");
            state.setInt(1, tinh_id);
            state.setString(2, param_report);
            state.setInt(3, qlc);
            state.execute();
            
            prpstm = prepareStatement(cnn, SQL_SELECT_REPORT_INFOR_NKT, null);
            rs = prpstm.executeQuery();
            rs.last();
            int size = rs.getRow();
            rs.beforeFirst();
            while (rs!= null && rs.next()){
                bean = new FReportInforNKT();
                bean.setTotal(size);
                bean.setId(rs.getInt("id"));
                bean.setMa(rs.getString("ma"));
                bean.setTen(rs.getString("ten"));
                bean.setNgaySinh(rs.getString("ngaysinh")==null?"":rs.getString("ngaysinh"));
                bean.setSex(rs.getString("sex"));
                bean.setQuanhuyen(rs.getString("quanhuyen"));
                bean.setPhuongxa(rs.getString("phuongxa"));
                bean.setDiachi(rs.getString("sonha"));
                bean.setThonto(rs.getString("thonto"));
                bean.setDacam(rs.getString("dacam"));
                bean.setDanhgia(rs.getString("danhgia_nkt"));
                bean.setDangTats(rs.getString("dangtat"));
                
                bean.setParamvalue_1(rs.getInt("paramvalue_1"));
                bean.setParamvalue_2(rs.getInt("paramvalue_2"));
                bean.setParamvalue_3(rs.getInt("paramvalue_3"));
                bean.setParamvalue_4(rs.getInt("paramvalue_4"));
                bean.setParamvalue_5(rs.getInt("paramvalue_5"));
                bean.setParamvalue_6(rs.getInt("paramvalue_6"));
                bean.setParamvalue_7(rs.getInt("paramvalue_7"));
                bean.setParamvalue_8(rs.getInt("paramvalue_8"));
                bean.setParamvalue_9(rs.getInt("paramvalue_9"));
                bean.setParamvalue_10(rs.getInt("paramvalue_10"));
                bean.setParamvalue_11(rs.getInt("paramvalue_11"));
                bean.setParamvalue_12(rs.getInt("paramvalue_12"));
                bean.setParamvalue_13(rs.getInt("paramvalue_13"));
                bean.setParamvalue_14(rs.getInt("paramvalue_14"));
                bean.setParamvalue_15(rs.getInt("paramvalue_15"));
                bean.setParamvalue_16(rs.getInt("paramvalue_16"));
                bean.setParamvalue_17(rs.getInt("paramvalue_17"));
                bean.setParamvalue_18(rs.getInt("paramvalue_18"));
                bean.setParamvalue_19(rs.getInt("paramvalue_19"));
                bean.setParamvalue_20(rs.getInt("paramvalue_20"));
                bean.setParamvalue_21(rs.getInt("paramvalue_21"));
                bean.setParamvalue_22(rs.getInt("paramvalue_22"));
                bean.setParamvalue_23(rs.getInt("paramvalue_23"));
                bean.setParamvalue_24(rs.getInt("paramvalue_24"));
                bean.setParamvalue_25(rs.getInt("paramvalue_25"));
                bean.setParamvalue_26(rs.getInt("paramvalue_26"));
                bean.setParamvalue_27(rs.getInt("paramvalue_27"));
                bean.setParamvalue_28(rs.getInt("paramvalue_28"));                
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

