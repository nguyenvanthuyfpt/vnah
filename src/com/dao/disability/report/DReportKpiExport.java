package com.dao.disability.report;


import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.FDisExport;
import com.form.disability.FThongTinTuyen;

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


public class DReportKpiExport extends FExportExcel{
    
    public String exportExcel(FDisExport beanTemp, FSeed seed, String excelFile) throws EException, 
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
        
        FDisExport bean = (FDisExport)seed;        
        FThongTinTuyen beanTuyen    = new FThongTinTuyen();
        
        /*
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
        */
        
        // Row 4
        //areaName    = daoTinh.getTinhNameById(cnn, bean.getId_tinh());
        //row = 2;
             
        
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
    
    public FBeans getData(Connection cnn, int tinh_id) throws EException, SQLException {
        final String LOCATION =  this.toString() + "~~>getDataToExport()";
        CallableStatement state = null;
        state = cnn.prepareCall("{call kpi_gen_data(?)}");        
        state.setInt(1, tinh_id);
        state.execute(); 
        PreparedStatement prpstm = null;
        ResultSet rs = null;
        FBeans beans = new FBeans();
        FDisExport bean = new FDisExport();
        try {
            prpstm = prepareStatement(cnn, SQL_SELECT_KPI_DIS_EXPORT, null);
            rs = prpstm.executeQuery();
            
            while (rs!= null && rs.next()){
                bean = new FDisExport();                
               
                bean.setStt(rs.getInt(KPI_DIS_EXPORT_STT));
                bean.setTinhName(rs.getString(KPI_DIS_EXPORT_TINH_NAME));
                bean.setMaSo(rs.getString(KPI_DIS_EXPORT_MASO));
                bean.setTen(rs.getString(KPI_DIS_EXPORT_TEN));
                bean.setYearBirth(rs.getInt(KPI_DIS_EXPORT_YEAR_BIRTH));
                bean.setSex(rs.getString(KPI_DIS_EXPORT_SEX));
                bean.setSoNha(rs.getString(KPI_DIS_EXPORT_SONHA));
                bean.setDienThoai(rs.getString(KPI_DIS_EXPORT_DIENTHOAI));
                bean.setCreateDate(rs.getString(KPI_DIS_EXPORT_CREATE_DATE));
                
                bean.setNcsTen(rs.getString(KPI_DIS_EXPORT_NCS_TEN));
                bean.setNcsSdt(rs.getString(KPI_DIS_EXPORT_NCS_SDT));
                bean.setNcsSex(rs.getString(KPI_DIS_EXPORT_NCS_SEX));
                
                bean.setTrangThai(rs.getString(KPI_DIS_EXPORT_STATUS));
                bean.setDangTat(rs.getString(KPI_DIS_EXPORT_DANGTAT));
                bean.setMucDo(rs.getString(KPI_DIS_EXPORT_MUCDO));
                
                bean.setNgayDangTat(rs.getString(KPI_DIS_EXPORT_NGAYDANGTAT));
                bean.setDaCam(rs.getString(KPI_DIS_EXPORT_DACAM));
                
                bean.setNhuCau(rs.getString(KPI_DIS_EXPORT_NCAU));
                bean.setNgayNhuCau(rs.getString(KPI_DIS_EXPORT_NGAY_NCAU));
                
                bean.setHoTro(rs.getString(KPI_DIS_EXPORT_HOTRO));
                bean.setHoTroNoiNhan(rs.getString(KPI_DIS_EXPORT_HOTRO_NOINHAN));
                bean.setDungCu(rs.getString(KPI_DIS_EXPORT_HOTRO_DUNGCU));
                bean.setNgayHoTro(rs.getString(KPI_DIS_EXPORT_NGAY_HTRO));
                
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
