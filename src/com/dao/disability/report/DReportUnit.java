package com.dao.disability.report;


import com.bo.disability.BCategoryUnit;

import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.FCategoryUnit;
import com.form.disability.FUnit;

import com.lib.AppConfigs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.SQLException;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportUnit extends FExportExcel {
    public String excelUnit(FBeans beans, FSeed seed, String excelFile, 
                            int type_id) throws EException, 
                                                FileNotFoundException, 
                                                IOException {
        final String LOCATION = this.toString() + "~>excelUnit()";
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
        String excelDown = excelPath + seed.me.getSessionID();
        
        File file = new File(excelPath, excelFile);
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);

        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.createSheet("Trang 1");        
        sheet.setAutobreaks(true);
        
        HSSFFont fontArialBold = getFont(wb, "Times New Roman", 12, true);        
        CellStyle style = 
            getStyle(wb, fontArialBold, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);

        HSSFFont fontArial14 = getFont(wb, "Times New Roman", 11, false);
        CellStyle style1 = 
            getStyle(wb, fontArial14, CellStyle.ALIGN_LEFT, 
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);

        int dong = 2;
        FUnit beanC = null;
        FCategoryUnit beanCU = null;
        BCategoryUnit bo = new BCategoryUnit();

        if (type_id == 0) {
            for (int i = 0; i < beans.size(); i++) {
                int cot = 1;
                beanC = (FUnit)beans.get(i);
                //Region region = new Region(dong, 0, dong, 3);
                //sheet.addMergedRegion(region);
                
                for (int i2 = 0; i2 <= 3; i2++) {
                    setStyle(sheet.createRow(dong), i2, wb, style);
                }

                createCell(sheet.createRow(dong), (cot - 1), (i + 1) + ". " + beanC.getName_type(), wb, style);

                
                dong = dong + 1;
                for (int j = 0; j < beanC.getSubBean().size(); j++) {
                    FUnit beanTem = (FUnit)beanC.getSubBean().get(j);

                    String thongtin = beanTem.getAddress() + (beanTem.getPhone() != null ? "/" + beanTem.getPhone() : beanTem.getPhone()) + 
                            (beanTem.getFax() != null ? "/" + beanTem.getFax() : beanTem.getFax()) + 
                            (beanTem.getEmail() != null ? "/" + beanTem.getEmail() : beanTem.getEmail());

                    createCell(sheet.createRow(dong), (cot - 1), "", wb, style1);
                    createCell(sheet.createRow(dong), (cot), " - " + beanTem.getName(), wb, style1);
                    createCell(sheet.createRow(dong), (cot + 1), "" + beanTem.getGioiThieu(), wb, style1);
                    createCell(sheet.createRow(dong), (cot + 2), thongtin, wb, style1);
                    dong = dong + 1;
                }
            }
        } else {
            int cot = 1;
            beanC = (FUnit)beans.get(0);
            try {
                beanCU = bo.getById(type_id);
            } catch (SQLException e) {
                
            }
            //Region region = new Region(dong, 0, dong, 3);
            //sheet.addMergedRegion(region);
            for (int i2 = 0; i2 <= 3; i2++) {
                setStyle(sheet.createRow(dong), i2, wb, style);
            }

            createCell(sheet.createRow(dong), (cot - 1), (1) + ". " + beanCU.getName(), wb, style);

            dong = dong + 1;

            for (int j = 0; j < beanC.getSubBean().size(); j++) {
                FUnit beanTem = (FUnit)beanC.getSubBean().get(j);

                createCell(sheet.createRow(dong), (cot - 1), "", wb, style1);
                createCell(sheet.createRow(dong), (cot), " - " + beanTem.getName(), wb, style1);
                createCell(sheet.createRow(dong), (cot + 1), "" + beanTem.getGioiThieu(), wb, style1);

                String thongtin = beanTem.getAddress() + (beanTem.getPhone() != null ? "/" + beanTem.getPhone() : beanTem.getPhone()) + 
                    (beanTem.getFax() != null ? "/" + beanTem.getFax() : beanTem.getFax()) + 
                    (beanTem.getEmail() != null ? "/" + beanTem.getEmail() : beanTem.getEmail());

                createCell(sheet.createRow(dong), (cot + 2), thongtin, wb, style1);

                dong = dong + 1;
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
}

