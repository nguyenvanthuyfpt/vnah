package com.dao.disability.report;


import com.dao.connection.DBConnector;
import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.report.FReportCollect;

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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;


public class DReportCollectData extends FExportExcel{
    
    public String exportExcel(int level,FReportCollect beanTemp, FSeed seed, String excelFile) throws EException, 
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
                     CellStyle.VERTICAL_JUSTIFY, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN, CellStyle.BORDER_THIN, 
                     CellStyle.BORDER_THIN);
        
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
        
        FReportCollect bean = (FReportCollect)seed;
        DTinh daoTinh = new DTinh();
        Connection cnn = DBConnector.getConnection();        
        String[] arrTinhId  = daoTinh.getTinhIdByParentId(cnn, bean.getId_tinh());
        
        int row = 4;
        int col = 2;
        int pos = arrTinhId.length;        
        String areaName     = "";
        
        // Row 4
        areaName    = daoTinh.getTinhNameById(cnn, bean.getId_tinh());
        row = 2;
        if(level==2||level==1){
            //Region title0 = new Region(0, (pos), 0, (pos+2));
            //sheet.addMergedRegion(title0);
            if(level==1)    createCell(sheet.getRow(0), pos, bean.ncrToString("M&#7851;u 1C"), wb, styleNormalCenter);
            else            createCell(sheet.getRow(0), pos, bean.ncrToString("M&#7851;u 1B"), wb, styleNormalCenter);
            
            //title0 = new Region(1, (pos), 1, (pos+2));
            //sheet.addMergedRegion(title0);
            if (level==1)   createCell(sheet.getRow(1), pos, bean.ncrToString("(D&#224;nh cho T&#7881;nh/Th&#224;nh ph&#7889;)"), wb, styleNormalCenter);
            else            createCell(sheet.getRow(1), pos, bean.ncrToString("(D&#224;nh cho Qu&#7853;n/huy&#7879;n)"), wb, styleNormalCenter);
                        
            //Region title1 = new Region(2, 0, 2, (pos+2));
            //sheet.addMergedRegion(title1);
            createCell(sheet.getRow(2), 0, bean.ncrToString("B&#193;O C&#193;O"), wb, styleHeader);
            
            //Region title2 = new Region(3, 0, 3, (pos+2));
            //sheet.addMergedRegion(title2);
            createCell(sheet.getRow(3), 0, bean.ncrToString("T&#7892;NG H&#7906;P NHU C&#7846;U NG&#431;&#7900;I KHUY&#7870;T T&#7852;T"), wb, styleHeader);
            
            //Region title3 = new Region(4, 0, 4, (pos+2));
            //sheet.addMergedRegion(title3);
            if (level==1)   createCell(sheet.getRow(4), 0, bean.ncrToString("T&#7881;nh/Th&#224;nh ph&#7889;: ")+areaName, wb, styleBoldNone);
            else            createCell(sheet.getRow(4), 0, bean.ncrToString("Qu&#7853;n/huy&#7879;n: ")+areaName, wb, styleBoldNone);
            
            //Region title4 = new Region(5, 0, 5, (pos+2));
            //sheet.addMergedRegion(title4);
            createCell(sheet.getRow(5), 0, bean.ncrToString("&#272;&#7871;n ng&#224;y: ") +  bean.dateToString(bean.getCurrentDate()), wb, styleBoldNone);
        } else if (level==3){
            //Region title = new Region(4, 0, 4, (pos+1));
            //sheet.addMergedRegion(title);
            //sheet.createRow(4);
            createCell(sheet.getRow(4), 0, bean.ncrToString("X&#227;/ph&#432;&#7901;ng: ")+areaName, wb, styleBoldNone);
            
            //title = new Region(5, 0, 5, (pos+1));
            createCell(sheet.getRow(5), 0, bean.ncrToString("&#272;&#7871;n ng&#224;y: ") +  bean.dateToString(bean.getCurrentDate()), wb, styleBoldNone);
        }
        
        // Ghi chu
        if(beanTemp.getQuanlyca()==1)
            createCell(sheet.getRow(0), 0, bean.ncrToString("D&#7919; li&#7879;u NKT thu&#7897;c di&#7879;n Qu&#7843;n l&#253; ca"), wb, styleNormalLeft);
        
        // Row 9        
        if(level==2 || level==1){
            col = 3;
            for (int i = 0; i < pos; i++) {            
                //Region title = new Region(7, col, 7, (pos+2));
                //sheet.addMergedRegion(title);
                if(level==1)    
                    createRegion(wb, sheet.getRow(7), 7, 7, 3, pos+2, styleBold, bean.ncrToString("Chia ra Qu&#7853;n/Huy&#7879;n"), 1, 0);
                else            
                    createRegion(wb, sheet.getRow(7), 7, 7, 3, pos+2, styleBold, bean.ncrToString("Chia ra X&#227;/Ph&#432;&#7901;ng"), 1, 0);
                sheet.getRow(7).setHeightInPoints((float)25);
                areaName    = daoTinh.getTinhNameById(cnn, Integer.parseInt(arrTinhId[i]));
                createCell(sheet.getRow(8), col, areaName , wb, styleBold);
                col++;
            }
        }
            
        bean = (FReportCollect)beanTemp.getStore().get(0);
        
        row = 10;
        col = 2;   //### Nhu Cau Ve Y Te ###     
        for(int i=0;i< bean.getParamvalue_1().split("#").length;i++){
            createCell(sheet.getRow(9),col, "", wb, styleNormal);
            createCell(sheet.getRow(10),col, bean.getParamvalue_1().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(11),col, bean.getParamvalue_2().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(12),col, bean.getParamvalue_3().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(13),col, bean.getParamvalue_4().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(14),col, bean.getParamvalue_5().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(15),col, bean.getParamvalue_6().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(16),col, bean.getParamvalue_7().split("#")[i], wb, styleNormal);            
            col++;            
        }
        
        col = 2;  //### Nhu Cau Ve Xa Hoi ###
        for(int i=0;i< bean.getParamvalue_8().split("#").length;i++){
            createCell(sheet.getRow(17),col, "", wb, styleNormal);
            createCell(sheet.getRow(18),col, bean.getParamvalue_8().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(19),col, bean.getParamvalue_9().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(20),col, bean.getParamvalue_10().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(21),col, bean.getParamvalue_11().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(22),col, bean.getParamvalue_12().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(23),col, bean.getParamvalue_13().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(24),col, bean.getParamvalue_14().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(25),col, bean.getParamvalue_15().split("#")[i], wb, styleNormal);            
            col++;            
        }
        
        col = 2;  //### Nhu Cau Ve Giao Duc ###
        for(int i=0;i< bean.getParamvalue_16().split("#").length;i++){
            createCell(sheet.getRow(26),col, "", wb, styleNormal);
            createCell(sheet.getRow(27),col, bean.getParamvalue_16().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(28),col, bean.getParamvalue_17().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(29),col, bean.getParamvalue_18().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(30),col, bean.getParamvalue_19().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(31),col, bean.getParamvalue_20().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(32),col, bean.getParamvalue_21().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(33),col, bean.getParamvalue_22().split("#")[i], wb, styleNormal);
            createCell(sheet.getRow(34),col, bean.getParamvalue_23().split("#")[i], wb, styleNormal);            
            col++;            
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
    
    public FBeans getData(Connection cnn, int tinh_id, int period, int year, int level,  String qly_ca) throws EException, SQLException {
        final String LOCATION =  this.toString() + "~~>getDataToExport()";        
        CallableStatement state = null;
        
        if(!"".equals(qly_ca))      state = cnn.prepareCall("{call report_collect_nkt_qlc(?, ?, ?, ?, ?)}");
        else                        state = cnn.prepareCall("{call report_collect_nkt(?, ?, ?, ?, ?)}");
        
        state.setInt(1, tinh_id);
        state.setInt(2, period);
        state.setInt(3, year);
        state.setInt(4, level);
        state.setString(5, qly_ca);
        state.execute();
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FReportCollect bean = new FReportCollect();
        try {
            prpstm = prepareStatement(cnn, SQL_SELECT_REPORT_COLLECT_NKT, null);
            rs = prpstm.executeQuery();
            
            while (rs!= null && rs.next()){
                bean = new FReportCollect();                
                bean.setNameArea(rs.getString("tinh_name"));                                
                bean.setParamvalue_1(rs.getString("paramvalue_1"));
                bean.setParamvalue_2(rs.getString("paramvalue_2"));
                bean.setParamvalue_3(rs.getString("paramvalue_3"));
                bean.setParamvalue_4(rs.getString("paramvalue_4"));
                bean.setParamvalue_5(rs.getString("paramvalue_5"));
                bean.setParamvalue_6(rs.getString("paramvalue_6"));
                bean.setParamvalue_7(rs.getString("paramvalue_7"));
                bean.setParamvalue_8(rs.getString("paramvalue_8"));
                bean.setParamvalue_9(rs.getString("paramvalue_9"));
                bean.setParamvalue_10(rs.getString("paramvalue_10"));
                bean.setParamvalue_11(rs.getString("paramvalue_11"));
                bean.setParamvalue_12(rs.getString("paramvalue_12"));
                bean.setParamvalue_13(rs.getString("paramvalue_13"));
                bean.setParamvalue_14(rs.getString("paramvalue_14"));
                bean.setParamvalue_15(rs.getString("paramvalue_15"));                
                bean.setParamvalue_16(rs.getString("paramvalue_16"));
                bean.setParamvalue_17(rs.getString("paramvalue_17"));
                bean.setParamvalue_18(rs.getString("paramvalue_18"));
                bean.setParamvalue_19(rs.getString("paramvalue_19"));
                bean.setParamvalue_20(rs.getString("paramvalue_20"));
                bean.setParamvalue_21(rs.getString("paramvalue_21"));
                bean.setParamvalue_22(rs.getString("paramvalue_22"));
                bean.setParamvalue_23(rs.getString("paramvalue_23"));                            
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
