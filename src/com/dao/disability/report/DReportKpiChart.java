package com.dao.disability.report;


import com.bo.disability.BDataRank;
import com.bo.disability.BDisProfile;
import com.bo.disability.BDisability;

import com.bo.disability.BPhanLoai;
import com.bo.disability.BSupport;

import com.bo.disability.categorys.BDangTat;

import com.exp.EException;

import com.form.FBeans;
import com.form.FExportExcel;
import com.form.FSeed;
import com.form.disability.FDataHdr;
import com.form.disability.FDataRank;
import com.form.disability.FDisExport;
import com.form.disability.FDisProfile;
import com.form.disability.FDisability;
import com.form.disability.FPhanLoai;
import com.form.disability.FSupport;

import com.lib.AppConfigs;

import com.util.Formater;
import com.util.Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStream;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.DecimalFormat;

import java.util.Date;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;

import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;


public class DReportKpiChart extends FExportExcel{
    
    public String exportExcel(FDataHdr beanTemp, 
                                Map<String, String> map_quanhe,
                                Map<String, String> map_hotro,
                                FSeed seed, String excelFile) throws EException, 
                                FileNotFoundException, IOException {
        
        final String LOCATION = this.toString() + "~>excelUnit()";
        String excelPath = AppConfigs.APP_SYSTEM_PATH + "disability" + AppConfigs.SYSTEM_FILE_SCHIP + 
            "report" + AppConfigs.SYSTEM_FILE_SCHIP + "xls";
        
        FDataHdr bean = (FDataHdr)seed;        
        String excelDown = excelPath + seed.me.getSessionID();
  
        File file = new File(excelPath, excelFile);
        FileInputStream fis = new FileInputStream(file);
        POIFSFileSystem fs = new POIFSFileSystem(fis);
  
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);
  
        sheet = wb.getSheetAt(0);
        sheet.setAutobreaks(true);        
         
        HSSFFont fontArial11 = getFont(wb, "Times New Roman", Integer.valueOf(11), false);
        HSSFFont fontArial14 = getFont(wb, "Times New Roman", Integer.valueOf(14), false);
        HSSFFont fontArialBold14 = getFont(wb, "Times New Roman", Integer.valueOf(11), true);
  
        CellStyle csLeft =
            getStyle(wb, fontArial14, (short)1, (short)3, (short)1, (short)1,
                     (short)1, (short)1);  
  
        CellStyle csLeftNone =
            getStyle(wb, fontArial11, (short)1, (short)3, (short)0, (short)0,
                     (short)0, (short)0);
        
        CellStyle csCenterNone =
            getStyle(wb, fontArial14, (short)2, (short)3, (short)0, (short)0,
                     (short)0, (short)0);  
  
        CellStyle csRight =
            getStyle(wb, fontArial11, (short)3, (short)3, (short)1, (short)1,
                     (short)1, (short)1);
  
  
        CellStyle csBRight =
            getStyle(wb, fontArial11, (short)3, (short)3, (short)1,
                     (short)1, (short)1, (short)1);
  
  
        CellStyle csCenterWrap =
            getStyle(wb, fontArial11, (short)2, (short)1, (short)1,
                     (short)1, (short)1, (short)1);
  
  
        csCenterWrap.setWrapText(true);         
        HSSFRow row = null;
        int cot=2;
        int dong=7;
        row = sheet.createRow(0);
        createCell(row, 1, beanTemp.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") + Utilities.getStringDateFormat("dd/MM/yyyy"), wb, csLeftNone);
        
        int nktId = bean.getNktId();
        FDisability dis = new FDisability();
        FPhanLoai phanloai = new FPhanLoai();
        FSupport hotro = new FSupport();
        dis.setId(nktId);
             
        try {
            dis = new BDisability().getRecordByID(dis);
            phanloai = new BPhanLoai().getPhanLoaiByNktID(nktId);
            hotro = new BSupport().getDoiTuongHoTroByNktId(nktId);
            FDisProfile profile = new BDisProfile().getProfileLastByNktId(nktId);
            String disQuanHe = "";
            if (map_quanhe!=null) {
                disQuanHe = map_quanhe.get(String.valueOf(dis.getQuanHeChamSoc()));
            }
                       
            row = sheet.getRow(4);
            createCell(row, 1, beanTemp.ncrToString("M&#227; qu&#7843;n l&#253; NKT: ") + dis.getMa_nkt(), wb, csCenterNone);
            
            row = sheet.getRow(dong++);
            createCell(row, cot, dis.getNkt(), wb, csLeftNone);
            createCell(row, 5, dis.getSex()==1?beanTemp.ncrToString("Nam"):beanTemp.ncrToString("N&#7919;"), wb, csLeftNone);
            
            row = sheet.getRow(dong++);
            createCell(row, cot, Utilities.getCurrentYear(bean.stringToDate(dis.getNgaySinh())), wb, csLeftNone);
            createCell(row, 5, dis.getSoNha(), wb, csLeftNone);
            
            row = sheet.getRow(dong++);
            createCell(row, cot, dis.getDateLastUpdate(), wb, csLeftNone);
            createCell(row, 5, dis.getPhoneNumber(), wb, csLeftNone);

            // Add Dan-Toc + Nghe-Nghiep + Da-Cam
            row = sheet.getRow(dong++);
            createCell(row, cot, dis.getDantocName(), wb, csLeftNone);
            
            int nghenghiepId = dis.getNgheNghiepHT();
            String strNgheNghiep = "";
            if (nghenghiepId==1) {
                strNgheNghiep = beanTemp.ncrToString("C&#242;n nh&#7887;");
            } else if (nghenghiepId==2) {
                strNgheNghiep = beanTemp.ncrToString("N&#7897;i tr&#7907;");
            } else if (nghenghiepId==3) {
                strNgheNghiep = beanTemp.ncrToString("N&#244;ng nghi&#7879;p");
            } else if (nghenghiepId==4) {
                strNgheNghiep = beanTemp.ncrToString("C&#244;ng nh&#226;n - vi&#234;n ch&#7913;c");
            } else if (nghenghiepId==5) {
                strNgheNghiep = beanTemp.ncrToString("C&#244;ng nh&#226;n");
            } else if (nghenghiepId==6) {
                strNgheNghiep = beanTemp.ncrToString("Th&#7907; th&#7911; c&#244;ng");  
            } else if (nghenghiepId==7) {
                strNgheNghiep = beanTemp.ncrToString("D&#7883;ch v&#7909;/bu&#244;n b&#225;n");  
            } else if (nghenghiepId==8) {
                strNgheNghiep = beanTemp.ncrToString("Th&#7845;t nghi&#7879;p");  
            } else if (nghenghiepId==9) {
                strNgheNghiep = beanTemp.ncrToString("B&#7879;nh t&#7853;t kh&#244;ng l&#224;m g&#236; &#273;&#432;&#7907;c");  
            } else if (nghenghiepId==10) {
                strNgheNghiep = beanTemp.ncrToString("Kh&#225;c");  
            }
            
            createCell(row, 5, strNgheNghiep, wb, csLeftNone);
                                    
            row = sheet.getRow(dong++);
            createCell(row, cot, dis.getChatDocDaCam()==1?beanTemp.ncrToString("C&#243;"):beanTemp.ncrToString("Kh&#244;ng"), wb, csLeftNone);                         
            
            // Dang-Tat                        
            row = sheet.getRow(dong++);            
            createCell(row, cot, phanloai.getDangTat(), wb, csLeftNone);
            createCell(row, 5, phanloai.getMucDoKT(), wb, csLeftNone);
            
            // Add Nguyen-Nhan + Tinh-Trang
            row = sheet.getRow(dong++);            
            createCell(row, cot, phanloai.getNguyenNhan(), wb, csLeftNone);
            createCell(row, 5, phanloai.getReson(), wb, csLeftNone);
   
            // Add Ngay-Dong-Ca + Du-An
            row = sheet.getRow(dong++);
            if (profile!=null) {
                createCell(row, cot, profile.getStatus()==1? profile.getCreateOn():"", wb, csLeftNone);
            } else {
                createCell(row, cot, "", wb, csLeftNone);
            }
            String projectName = dis.getDuAnId()==0?"Direct":"Inclusion 3";
            createCell(row, 5, projectName, wb, csLeftNone);
                                    
            // Thong-Tin-Nguoi-Cham-Soc
            dong++; 
            dong++;
            row = sheet.getRow(dong++);
            createCell(row, cot, dis.getTenChamSoc(), wb, csLeftNone);
            createCell(row, 5, dis.getSdtLienLac(), wb, csLeftNone);
            
            // Add Nam-Sinh + Gioi-Tinh NCS
            row = sheet.getRow(dong++);
            createCell(row, cot, dis.getNamSinhChamSoc(), wb, csLeftNone);
            createCell(row, 5, dis.getGioiTinhChamSoc()==1?beanTemp.ncrToString("Nam"):beanTemp.ncrToString("N&#7919;"), wb, csLeftNone);
              
            row = sheet.getRow(dong++);
            createCell(row, cot, disQuanHe, wb, csLeftNone);
            
            dong = 23;
            cot = 1;
            FBeans supports = new FBeans();
            
            // Nhu-Cau-Ho-Tro
            supports = new BSupport().getSupports2020(nktId, 0);
            int numNhuCau = supports.size();
            
            // Check NumRow HTCM
            for (int j=0;j<supports.size();j++) {
                FSupport support = (FSupport)supports.get(j);
                int parentId = support.getParentId();
                if (parentId==302) {
                    numNhuCau--;
                }
            }
            
            int createNewRowAt = 25;
            sheet.shiftRows(createNewRowAt, sheet.getLastRowNum(), numNhuCau, true, false);
            
            String chuyenMon = "", currDate = "";            
            if (supports.size()>0) {
                for (int j=0;j<supports.size();j++) {
                    FSupport support = (FSupport)supports.get(j);                    
                    int parentId = support.getParentId();
                    if (!currDate.equals(support.getCreateDate())) {
                        chuyenMon = "";
                    }
                    
                    if (parentId!=302) {
                        row = sheet.getRow(dong++);
                        createCell(row, cot++, map_hotro.get(parentId+""), wb, csLeftNone);
                        createCell(row, cot++, support.getTenNhuCau(), wb, csLeftNone);
                        createCell(row, cot++, chuyenMon, wb, csLeftNone);
                        createCell(row, cot++, support.getCreateDate(), wb, csLeftNone);
                    } else {
                        chuyenMon = support.getTenNhuCau();
                    }
                    currDate = support.getCreateDate();
                    cot = 1;
                }
            }
            
            supports = new BSupport().getSupports2020(nktId, 1);
            int numHoTro = supports.size();
            
            // Check NumRow HTCM
            for (int j=0;j<supports.size();j++) {
                FSupport support = (FSupport)supports.get(j);
                int parentId = support.getParentId();
                if (parentId==302) {
                    numHoTro--;
                }
            }
            
            createNewRowAt = 29+numNhuCau;
            sheet.shiftRows(createNewRowAt, sheet.getLastRowNum(), numHoTro, true, false);
            
            dong = 27+numNhuCau;         
            supports = new BSupport().getSupports2020(nktId, 1);
            
            // Ho-Tro-Da-Nhan
            if (supports.size()>0) {                
                for (int j=0;j<supports.size();j++) {
                    FSupport support = (FSupport)supports.get(j);
                    
                    int parentId = support.getParentId();
                    if (!currDate.equals(support.getCreateDate())) {
                        chuyenMon = "";
                    }
                    
                    if (parentId!=302) {
                        row = sheet.getRow(dong++);
                        createCell(row, cot++, map_hotro.get(parentId+""), wb, csLeftNone);
                        createCell(row, cot++, support.getTenNhuCau(), wb, csLeftNone);
                        createCell(row, cot++, chuyenMon, wb, csLeftNone);
                        createCell(row, cot++, support.getMtieuDtri(), wb, csLeftNone);
                        createCell(row, cot++, support.getCtVltl(), wb, csLeftNone);
                        createCell(row, cot++, support.getCtHdtl(), wb, csLeftNone);
                        createCell(row, cot++, support.getCtAntl(), wb, csLeftNone);
                        createCell(row, cot++, support.getCtGddb(), wb, csLeftNone);
                        createCell(row, cot++, support.getCreateDate(), wb, csLeftNone);                   
                        createCell(row, cot++, support.getNguonhotro(), wb, csLeftNone);
                    } else {
                        chuyenMon = support.getTenNhuCau();
                    }
                    
                    currDate = support.getCreateDate();
                    cot = 1;
                }
            }  
            
            // Object support
            dong = 30 + numHoTro + numNhuCau;
            cot = 3;
            row = sheet.getRow(dong++);
            createCell(row, cot++, hotro.getSpVnah(), wb, csCenterWrap);
            createCell(row, cot++, hotro.getSpTtp(), wb, csCenterWrap);
            createCell(row, cot++, hotro.getSpQhu(), wb, csCenterWrap);
            createCell(row, cot++, hotro.getSpPxa(), wb, csCenterWrap);
            
            // Rank
            FDataRank dataRank = new BDataRank().getDataRank(nktId, 1);
            dong = 36 + numHoTro + numNhuCau;
            cot = 2;
            row = sheet.getRow(dong++);
            int totalP0 = dataRank.getP0();
            int totalP1 = dataRank.getP1();
            int totalP2 = dataRank.getP2();
            int totalP3 = dataRank.getP3();
            int totalP4 = dataRank.getP4();
            createCell(row, cot++, dataRank.getP0(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP1(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP2(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP3(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP4(), wb, csCenterWrap);
                        
            dataRank = new BDataRank().getDataRank(nktId, 2);
            cot = 2;
            row = sheet.getRow(dong++);
            totalP0 += dataRank.getP0();
            totalP1 += dataRank.getP1();
            totalP2 += dataRank.getP2();
            totalP3 += dataRank.getP3();
            totalP4 += dataRank.getP4();
            createCell(row, cot++, dataRank.getP0(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP1(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP2(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP3(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP4(), wb, csCenterWrap);
            
            dataRank = new BDataRank().getDataRank(nktId, 3);
            cot = 2;
            row = sheet.getRow(dong++);
            totalP0 += dataRank.getP0();
            totalP1 += dataRank.getP1();
            totalP2 += dataRank.getP2();
            totalP3 += dataRank.getP3();
            totalP4 += dataRank.getP4();
            
            createCell(row, cot++, dataRank.getP0(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP1(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP2(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP3(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP4(), wb, csCenterWrap);
            
            dataRank = new BDataRank().getDataRank(nktId, 4);
            cot = 2;
            row = sheet.getRow(dong++);
            totalP0 += dataRank.getP0();
            totalP1 += dataRank.getP1();
            totalP2 += dataRank.getP2();
            totalP3 += dataRank.getP3();
            totalP4 += dataRank.getP4();

            createCell(row, cot++, dataRank.getP0(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP1(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP2(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP3(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP4(), wb, csCenterWrap);
            
            // Total 
            cot = 2;
            row = sheet.getRow(dong++);
            createCell(row, cot++, totalP0, wb, csCenterWrap);
            createCell(row, cot++, totalP1, wb, csCenterWrap);
            createCell(row, cot++, totalP2, wb, csCenterWrap);
            createCell(row, cot++, totalP3, wb, csCenterWrap);
            createCell(row, cot++, totalP4, wb, csCenterWrap);
            
            // Support
            cot = 3;
            row = sheet.getRow(dong++);
            dataRank = new BDataRank().getDataRankSP(nktId);
            createCell(row, cot++, dataRank.getP1(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP2(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP3(), wb, csCenterWrap);
            createCell(row, cot++, dataRank.getP4(), wb, csCenterWrap);
            
            JFreeChart lineChartObject  = ChartFactory.createLineChart(
                     beanTemp.ncrToString("K&#7871;t qu&#7843; can thi&#7879;p PHCN"),                    
                     beanTemp.ncrToString("T&#225;i &#273;&#225;nh gi&#225; g&#7847;n nh&#7845;t"),    // X
                     beanTemp.ncrToString("% thay &#273;&#7893;i so v&#7899;i &#273;/gi&#225; ban &#273;&#7847;u"), // Y
                     createDataset(nktId, beanTemp.ncrToString("% thay &#273;&#7893;i so v&#7899;i &#273;/gi&#225; ban &#273;&#7847;u ng&#224;y :")),
                     PlotOrientation.VERTICAL,
                     true,
                     true,
                     false);
            
            int width = 680;    /* Width of the image */
            int height = 400;   /* Height of the image */
            
            CategoryPlot plot = (CategoryPlot) lineChartObject.getPlot();
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
            renderer.setShapesVisible(true);
            DecimalFormat decimalformat1 = new DecimalFormat("##");
            renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}", decimalformat1));
            renderer.setItemLabelsVisible(true);
            renderer.setSeriesVisible(true);
            
            ChartUtilities.saveChartAsJPEG(new File("C:\\temp\\"+dis.getId()+".png") ,lineChartObject, width ,height);
            
            InputStream inputStream = new FileInputStream("C:\\temp\\"+dis.getId()+".png");   
            byte[] bytes = IOUtils.toByteArray(inputStream);
            
            int pictureIdx = wb.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
            //close the input stream
            inputStream.close();
            //Returns an object that handles instantiating concrete classes
            CreationHelper helper = wb.getCreationHelper();
            //Creates the top-level drawing patriarch.
            Drawing drawing = sheet.createDrawingPatriarch();
            //Create an anchor that is attached to the worksheet
            ClientAnchor anchor = helper.createClientAnchor();
            anchor.setCol1(0);
            anchor.setRow1(45+numHoTro+numNhuCau);
            //Creates a picture
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //Reset the image to the original size
            pict.resize();
            
        } catch (SQLException e) {
                        
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
    
    public String getPercent(int input, int total) {
        DecimalFormat df = new DecimalFormat("0.00");
        String percent = "0%";
        float fPercent = 0;
        if(total!=0)
            fPercent = (float)input/(float)total*100;
            
        percent = String.valueOf((df.format(fPercent))) + "%";        
        return percent;        
    }
      
    
    private DefaultCategoryDataset createDataset(int nktId, String label) throws EException {
         DefaultCategoryDataset dataset = new DefaultCategoryDataset( );         
         List<Date> listDate = new BDataRank().getTimeSupport(nktId, 100, 0);         
         if (listDate!=null && listDate.size()>0) {
              String createDate = Formater.date2str(listDate.get(0));
              for (int i=1; i<listDate.size(); i++) {
                  String percent = "0";
                  try {
                      percent = new BDataRank().getPercent(nktId, createDate, Formater.date2str(listDate.get(i)));
                  } catch (SQLException e) {
                      
                  } catch (Exception e) {
                  }
                  dataset.addValue(Double.parseDouble(percent), label+createDate, Formater.date2str(listDate.get(i)));               
              }
         }
         return dataset;
      }
}
