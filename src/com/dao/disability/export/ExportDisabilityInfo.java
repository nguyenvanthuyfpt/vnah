package com.dao.disability.export;


import com.dao.disability.categorys.DTinh;

import com.exp.EException;

import com.form.disability.FExport;

import com.inf.disability.IKeyDisability;

import com.util.DaoUtil;
import com.util.Formater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Workbook;

import jxl.format.UnderlineStyle;

import jxl.write.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;


public class ExportDisabilityInfo{
    Connection m_Connection = null;
    Statement m_Statement = null;
    ResultSet m_ResultSet = null;
    ResultSet rs = null;
    int nextCell = 0;
    
    String[] VN_DR_DISABILITY_COLUMN_NAMES = { "S&#7889; th&#7913; t&#7921; NKT", "M&#227; s&#7889;", 
                                               "M&#227; tuy&#7871;n", "Th&#244;n/ t&#7893;", 
                                               "&#272;&#7883;a ch&#7881;/ s&#7889; nh&#224;", 
                                               "S&#7889; &#273;i&#7879;n tho&#7841;i", 
                                               "Ng&#224;y c&#7853;p nh&#7853;t", "H&#7885; v&#224; t&#234;n ng&#432;&#7901;i qu&#7843;n l&#253;", 
                                               "N&#417;i c&#244;ng t&#225;c", "H&#7885; t&#234;n NKT", 
                                               "S&#7889; CMND", "Ng&#224;y sinh","&#272;&#7883;nh d&#7841;ng Ng&#224;y sinh", "Gi&#7899;i t&#237;nh", 
                                               "D&#226;n t&#7897;c", "Tr&#236;nh &#273;&#7897; v&#259;n h&#243;a", // Trinh Do
                                               "Tr&#236;nh &#273;&#7897; chuy&#234;n m&#244;n",  // Trinh do chuyen mon
                                               "Tr&#236;nh tr&#7841;ng gi&#225;o d&#7909;c hi&#7879;n t&#7841;i", 
                                               "Ngh&#7873; nghi&#7879;p hi&#7879;n nay",  // Nghe nghiep hien nay
                                               "T&#236;nh tr&#7841;ng h&#244;n nh&#226;n",      // Tinh trang hon nhan
                                               "T&#7893;ng s&#7889; con trong gia &#273;&#236;nh",  // Tong so con trong gia dinh
                                               "T&#7893;ng s&#7889; con d&#432;&#7899;i 16",
                                               "L&#224; n&#7841;n nh&#226;n ch&#7845;t &#273;&#7897;c da cam", 
                                               "Tr&#7907; c&#7845;p",
                                               "H&#7885; t&#234;n ch&#7911; h&#7897;", "N&#259;m sinh ch&#7911; h&#7897;", 
                                               "Quan h&#7879; v&#7899;i NKT", 
                                               "T&#7893;ng s&#7889; ng&#432;&#7901;i trong gia &#273;&#236;nh", 
                                               "T&#7893;ng s&#7889; NKT trong gia &#273;&#236;nh", 
                                               "T&#236;nh tr&#7841;ng kinh t&#7871;", "Ngu&#7891;n n&#432;&#7899;c s&#7917; d&#7909;ng", 
                                               "Nh&#224; v&#7879; sinh", "NKT c&#243; th&#7875; s&#7917; d&#7909;ng nh&#224; v&#7879; sinh", 
                                               "T&#236;nh tr&#7841;ng nh&#224; &#7903;", "H&#7885; t&#234;n ng&#432;&#7901;i ch&#259;m s&#243;c", 
                                               "N&#259;m sinh", "Quan h&#7879; v&#7899;i NKT", "S&#7889; &#273;i&#7879;n tho&#7841;i" };
    String[] DR_DISABILITY_COLUMN_NAMES = { "ma", "maso", "id_tinh", "thonto", "sonha", "dienthoai", "date_last_update", "ten_nql", "donvi_nql", 
                                            "ten", "cmnd", "ngaysinh","dinhdang_ns", "sex", "dantoc_id", "trinhdo_id", "td_chuyenmon", "chuc_vu_ht", "nghe_nghiep_ht", 
                                            "tt_honnhan", "tongsocon", "conduoi16", "dacam", "trocap_id", "ten_chuho", "namsinh_chuho", "quanhe_chuho", "songuoi_chuho", "nkt_chuho", 
                                            "id_dieukien", "nguonnuoc_chuho", "nhavs_chuho", "nhavs_nkt_chuho", "nhao_chuho", "ten_ncs", "namsinh_ncs", "quanhe_ncs", "sdt_ncs" };
    
    List<String> DR_SUPPORT_COLUMN_NAMES = Arrays.asList("reson", "datecreate","nguonhotro", "macbenh", "dungcu_khac", "phauthuat_khac", "yte_khac", "trocap_thuongxuyen_khac", 
                                                                "trocap_dotxuat_khac", "caithien_khac", "loaivay_khac", "sotienvay_khac", "mucdichvay_khac", "tochucxahoi_khac", "nhucau_doisong_khac", "nhucau_giaoduc_khac");

    /**
     * @param bean
     * @param area
     * @param dbuid
     * @param dbpwd
     * @param jdbcUrl
     * @return
     * @throws WriteException
     */
    public String doWork(FExport bean, String area, String dbuid, String dbpwd, String jdbcUrl) throws Exception{
        String query = "";
        String valReturn = null;
        try {
            
            m_Connection = DriverManager.getConnection(jdbcUrl, dbuid, dbpwd);
            m_Statement = m_Connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);            
            
            try {
                List listOfDisabilityAttributes = new ArrayList<String>();
                listOfDisabilityAttributes = getDBValueListByKey(m_Connection, "1", "dr_classification", "1", "name");
                
                List listOfReasons =  new ArrayList<String>();
                listOfReasons = getDBValueListByKey(m_Connection, "1", "dr_nguyennhan", "1", "name");               
                
                List listOfSupportTypes = new ArrayList<String>();
                listOfSupportTypes = getDBValueListByKey(m_Connection, "1", "dr_hotro", "1", "name");                                  
                
                // Dang tat
                List<ReturnData> rd = getDBValueObjectListByKey(m_Connection,"dangtat_id,name,code,parent_id","1","dr_classification","1","NA","order by dangtat_id");
                Map<String, String> disabilityAttributeMap = new HashMap<String, String>();
                for (int i=0; i<rd.size();i++){
                    disabilityAttributeMap.put(rd.get(i).getValue()+"-"+rd.get(i).getCcode(),rd.get(i).getKey());
                    //disabilityAttributeMap.put(rd.get(i).getValue(),rd.get(i).getKey());
                }
                
                // Nguyen nhan
                List<ReturnData> rd1 = getDBValueObjectListByKey(m_Connection,"nguyennhan_id,name,code,parent_id","1","dr_nguyennhan","1","NA","order by nguyennhan_id");
                Map<String, String> reasonAttributeMap = new HashMap<String, String>();
                for (int i=0; i<rd1.size();i++){
                    reasonAttributeMap.put(rd1.get(i).getValue()+"-"+rd1.get(i).getCcode(),rd1.get(i).getKey());
                }
                    
                // Ho tro    
                List<ReturnData> rd2 = getDBValueObjectListByKey(m_Connection,"hotro_id,name,code,parent_id","1","dr_hotro","1","NA","order by hotro_id");
                Map<String, String> supportTypesMap = new HashMap<String, String>();
                for (int i=0; i<rd2.size();i++){
                    supportTypesMap.put(rd2.get(i).getValue()+"-"+rd2.get(i).getCcode(),rd2.get(i).getKey());
                }
                
                int areaId = Integer.parseInt(area);
                query = "SELECT * FROM dr_disabilitypeople where id_tinh in (" + getAreaIds(m_Connection, areaId) + ")";
                
                //Execute the query
                m_ResultSet = m_Statement.executeQuery(query);
                ResultSetMetaData rsmd = m_ResultSet.getMetaData();                
                int numColumns = rsmd.getColumnCount();
                int count = DaoUtil.get_rc(m_ResultSet);
                if (count>=60000) {
                    valReturn = "";  // Du lieu > 60,000
                } else {
                    
                    File file = new File(getPathFile(bean));                    
                    FileOutputStream fos = new FileOutputStream(file);                    
                    // Sheet 1 ### THONG TIN NKT
                    WritableWorkbook workbook = Workbook.createWorkbook(new File(getPathFile(bean)));
                    WritableSheet sheet = workbook.createSheet(bean.ncrToString("Th&#244;ng tin chung"), 0);
                    WritableFont headerFont = new WritableFont (WritableFont.TIMES, 10,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE );
                    WritableFont headerFont1 = new WritableFont (WritableFont.TIMES, 10,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,Colour.RED );
                    WritableCellFormat headerCells = new WritableCellFormat(headerFont);                
                    WritableCellFormat headerNone = new WritableCellFormat(headerFont);
                        
                    headerCells.setBackground(Colour.TAN);                
                    Label label = new Label(0,0,"Header ",headerCells);

                    WritableFont normalFont = new WritableFont(WritableFont.TIMES, 10);
                    WritableCellFormat normalCell = new WritableCellFormat(normalFont);
                    normalCell.setBackground(Colour.RED);
                    
                    label = new Label(0,0,bean.ncrToString("T&#7893;ng s&#7889;: " + count) ,headerNone);
                    label.setCellFormat(headerNone);
                    sheet.addCell(label);
                    
                    label = new Label(0,1,bean.ncrToString("Ng&#224;y k&#7871;t xu&#7845;t: ") + Formater.date2str(bean.getCurrentDate()),headerNone);
                    label.setCellFormat(headerNone);
                    sheet.addCell(label);                
    
                    for (int i=0; i<VN_DR_DISABILITY_COLUMN_NAMES.length; i++) {
                        label = new Label(i,2,bean.ncrToString(VN_DR_DISABILITY_COLUMN_NAMES[i]),headerCells);
                        label.setCellFormat(headerCells);
                        sheet.addCell(label);                
                    }
                    
                    for (int i=0; i<DR_DISABILITY_COLUMN_NAMES.length; i++) {                
                        label = new Label(i,3, DR_DISABILITY_COLUMN_NAMES[i],headerCells);
                        label.setCellFormat(headerCells);
                        sheet.addCell(label);                
                    }
                    
                    normalFont = new WritableFont(WritableFont.TIMES, 10);
                    normalCell = new WritableCellFormat(normalFont);
                    int rowCounter = 4;
                  
                    //Write Basic Information in the first sheet
                    while (m_ResultSet.next()) {
                        for (int i=0; i<DR_DISABILITY_COLUMN_NAMES.length; i++) {
                            if(DR_DISABILITY_COLUMN_NAMES[i].equalsIgnoreCase("unknown")){
                                label = new Label(i,rowCounter,"NO DATA",normalCell);
                            }else{                                      
                                String translatedString = m_ResultSet.getString(DR_DISABILITY_COLUMN_NAMES[i]);                                
                                if("date_last_update".equals(DR_DISABILITY_COLUMN_NAMES[i]) 
                                   || "ngaysinh".equals(DR_DISABILITY_COLUMN_NAMES[i])){
                                    translatedString = bean.dateToString(m_ResultSet.getDate(DR_DISABILITY_COLUMN_NAMES[i])) ;
                                }                                
                                label = new Label(i,rowCounter,translatedString,normalCell);
                            }
                            label.setCellFormat(normalCell);                            
                            sheet.addCell(label);
                        }
                        rowCounter++;
                    }
                    
                    //write types of disability column from dr_classification
                    Map<String, Integer> disabilityAttributeColumnMap = new HashMap<String, Integer>();
                    Map<String, Integer> reasonColumnMap = new HashMap<String, Integer>();
                    Map<String, Integer> supportColumnMap = new HashMap<String, Integer>();
                    
                    int rCounter = 4;
                    int cCount = listOfDisabilityAttributes.size() + listOfReasons.size()+3;
                    
                    // Sheet 2 ### DANG TAT
                    WritableSheet typeOfDisabilitySheet = workbook.createSheet(bean.ncrToString("D&#7841;ng khuy&#7871;t t&#7853;t"), 1);
                    int rowSheet2 = 3;
                    
                    label = new Label(0,rowSheet2,bean.ncrToString("S&#7889; th&#7913; t&#7921; NKT"),headerCells);
                    label.setCellFormat(headerCells);
                    typeOfDisabilitySheet.addCell(label);
                    
                    label = new Label(1,rowSheet2,bean.ncrToString("H&#7885; t&#234;n NKT"),headerCells);
                    label.setCellFormat(headerCells);
                    typeOfDisabilitySheet.addCell(label);
                    
                    label = new Label(2,rowSheet2,bean.ncrToString("Ng&#224;y c&#7853;p nh&#7853;t"),headerCells);
                    label.setCellFormat(headerCells);
                    typeOfDisabilitySheet.addCell(label);
                    
                    label = new Label(3,rowSheet2,bean.ncrToString("Ng&#432;&#7901;i c&#7853;p nh&#7853;t"),headerCells);
                    label.setCellFormat(headerCells);
                    typeOfDisabilitySheet.addCell(label);
                    
                    label = new Label(4,rowSheet2,bean.ncrToString("Khuy&#7871;t t&#7853;t"),headerCells);
                    label.setCellFormat(headerCells);
                    typeOfDisabilitySheet.addCell(label);
                    
                    nextCell = 5;                    
                    //  WRITE HEARDER
                    //  DANG TAT
                    for (int i=0; i<listOfDisabilityAttributes.size(); i++) {                        
                        label = new Label(nextCell,rowSheet2,(String) listOfDisabilityAttributes.get(i),headerCells);                        
                        disabilityAttributeColumnMap.put(disabilityAttributeMap.get(label.getContents()), label.getColumn()+1);                        
                        label.setCellFormat(headerCells);
                        typeOfDisabilitySheet.addCell(label);
                        nextCell++;
                    }                    
                    
                    //  NGUYEN NHAN
                    WritableFont disabilityFont = new WritableFont (WritableFont.TIMES, 10,WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE );
                    WritableCellFormat disabilityCells = new WritableCellFormat(disabilityFont);
                    disabilityCells.setBackground(Colour.BRIGHT_GREEN);                    
                    
                    for (int i=0; i<listOfReasons.size(); i++) {
                        label = new Label(nextCell,rowSheet2,(String) listOfReasons.get(i),disabilityCells);                        
                        reasonColumnMap.put(reasonAttributeMap.get(label.getContents()), label.getColumn()+1);
                        label.setCellFormat(disabilityCells);
                        typeOfDisabilitySheet.addCell(label);
                        nextCell++;
                    }
                    
                    //  WRITE CONTENT
                    Statement phanloaiQuery_Statement = m_Connection.createStatement();
                    String phanloaiQuery = "select dp.ten,dp.ma,pl.* from dr_phanloai pl inner join dr_disabilitypeople dp on pl.id_nkt = dp.id where pl.datecreate is not null and dp.id_tinh in (" + getAreaIds(m_Connection, areaId) +") order by pl.id_nkt desc, pl.datecreate desc, pl.id";
                    rs = phanloaiQuery_Statement.executeQuery(phanloaiQuery);
                    
                    String dangtat_tokens[] = null;
                    String hotro_tokens[] = null;
                    String dangtat_ids = "";
                    String hotro_ids = "";
                    int nguyennhan_id = 0;
                    int rBeginReason = listOfDisabilityAttributes.size();
                    
                    while (rs.next()) {                        
                        label = new Label(0,rCounter,rs.getString("ma"),normalCell);
                        label.setCellFormat(normalCell);
                        typeOfDisabilitySheet.addCell(label);
                        
                        label = new Label(1,rCounter,rs.getString("ten"),normalCell);
                        label.setCellFormat(normalCell);
                        typeOfDisabilitySheet.addCell(label);
                        
                        label = new Label(2,rCounter,bean.dateToString(rs.getDate("datecreate")),normalCell);
                        label.setCellFormat(normalCell);
                        typeOfDisabilitySheet.addCell(label);
                        
                        label = new Label(3,rCounter,rs.getString("user_id"),normalCell);
                        label.setCellFormat(normalCell);
                        typeOfDisabilitySheet.addCell(label);
                        
                        label = new Label(4,rCounter,rs.getString("reson"),normalCell);
                        label.setCellFormat(normalCell);
                        typeOfDisabilitySheet.addCell(label);
                        
                        dangtat_ids = rs.getString("dangtat_ids");
                        nguyennhan_id = rs.getInt("nguyennhan_id");
                        
                        if(!"".equals(dangtat_ids) && dangtat_ids!=null){
                            dangtat_tokens = dangtat_ids.split("#");    
                            if(dangtat_tokens.length>0){
                                for(int i = 0; i<dangtat_tokens.length;i++){                                
                                    if(!"".equalsIgnoreCase(dangtat_tokens[i]) && !"0".equalsIgnoreCase(dangtat_tokens[i])){                                        
                                        label = new Label(disabilityAttributeColumnMap.get(dangtat_tokens[i])-1,rCounter,"1",normalCell);                                    
                                        typeOfDisabilitySheet.addCell(label);                                        
                                    } 
                                }
                            }
                        }                        
                        
                        if(nguyennhan_id>0){
                            label = new Label(reasonColumnMap.get(String.valueOf(nguyennhan_id))-1,rCounter,"1",normalCell);                                                                
                            typeOfDisabilitySheet.addCell(label);
                        }
                        
                        rCounter++;
                    }                    
                    rs.close();
                    
                    // Sheet 3 ### NHU CAU HO TRO
                    WritableSheet supportSheet = workbook.createSheet(bean.ncrToString("Nhu c&#7847;u h&#7895; tr&#7907;"), 2);
                    label = new Label(0,3,bean.ncrToString("S&#7889; th&#7913; t&#7921; NKT"),headerCells);
                    label.setCellFormat(headerCells);
                    supportSheet.addCell(label);
                    
                    label = new Label(1,3,bean.ncrToString("H&#7885; t&#234;n NKT"),headerCells);
                    label.setCellFormat(headerCells);
                    supportSheet.addCell(label);                    
                    nextCell = 2;

                    //get and print column names
                    for (int i=0; i<listOfSupportTypes.size(); i++) {
                        label = new Label(nextCell,3,(String) listOfSupportTypes.get(i),headerCells);
                        supportColumnMap.put(supportTypesMap.get(label.getContents()), label.getColumn()+1);                        
                        label.setCellFormat(headerCells);
                        supportSheet.addCell(label);
                        nextCell++;
                    }
                    
                    for (int i=0; i<DR_SUPPORT_COLUMN_NAMES.size(); i++) {
                        label = new Label(nextCell,3,(String) DR_SUPPORT_COLUMN_NAMES.get(i),headerCells);
                        supportColumnMap.put(DR_SUPPORT_COLUMN_NAMES.get(i), label.getColumn()+1);
                        label.setCellFormat(headerCells);
                        supportSheet.addCell(label);
                        nextCell++;
                    }
                    
                    
                    Statement hotroQuery_Statement = m_Connection.createStatement();
                    String hotroQuery = "select dp.ten,dp.ma,s.* from dr_support s inner join dr_disabilitypeople dp on s.id_nkt = dp.id where s.status_id = 0 AND dp.id_tinh in (" + getAreaIds(m_Connection, areaId) + ") order by id_nkt desc,datecreate desc";
                    rs = hotroQuery_Statement.executeQuery(hotroQuery);
                    
                    rCounter = 4;
                    cCount = listOfSupportTypes.size() + 3;
                    
                    nextCell = 2;
                    while (rs.next()) {                        
                        label = new Label(0,rCounter,rs.getString("ma"),normalCell);
                        label.setCellFormat(normalCell);
                        supportSheet.addCell(label);
                         
                        label = new Label(1,rCounter,rs.getString("ten"),normalCell);
                        label.setCellFormat(normalCell);
                        supportSheet.addCell(label);
                          
                        hotro_ids = rs.getString("dm_hotro_ids");
                        if(!"".equals(hotro_ids) && hotro_ids!=null){
                            hotro_tokens = hotro_ids.split("#");
                            if (hotro_tokens.length>0){
                                for(int i = 0; i<hotro_tokens.length;i++){
                                    if(!"".equalsIgnoreCase(hotro_tokens[i]) && !"0".equalsIgnoreCase(hotro_tokens[i])){                                        
                                        label = new Label(supportColumnMap.get(hotro_tokens[i])-1,rCounter,"1",normalCell);
                                        supportSheet.addCell(label);
                                    }
                                } 
                            }
                        }
                        
                          
                        for(int i=0;i<DR_SUPPORT_COLUMN_NAMES.size();i++){
                            if(rs.getString(DR_SUPPORT_COLUMN_NAMES.get(i))!=null){
                                String translatedString = rs.getString(DR_SUPPORT_COLUMN_NAMES.get(i));
                                if("datecreate".equals(DR_SUPPORT_COLUMN_NAMES.get(i))
                                    || "datefrom".equals(DR_SUPPORT_COLUMN_NAMES.get(i))
                                    || "dateto".equals(DR_SUPPORT_COLUMN_NAMES.get(i))){
                                    translatedString = bean.dateToString(rs.getDate(DR_SUPPORT_COLUMN_NAMES.get(i)));
                                }                                
                                label = new Label(supportColumnMap.get(DR_SUPPORT_COLUMN_NAMES.get(i))-1,rCounter,translatedString,normalCell);                                
                                supportSheet.addCell(label);
                            }
                        }                        
                        rCounter++;
                    }
                    rs.close();
                        
                    //  Sheet 4 ### HO TRO DA NHAN
                    WritableSheet supportReceivedSheet = workbook.createSheet(bean.ncrToString("H&#7895; tr&#7907; &#273;&#227; nh&#7853;n"), 3);
                                        
                    label = new Label(0,3,bean.ncrToString("S&#7889; th&#7913; t&#7921; NKT"),headerCells);
                    label.setCellFormat(headerCells);
                    supportReceivedSheet.addCell(label);
                    
                    label = new Label(1,3,bean.ncrToString("H&#7885; t&#234;n NKT"),headerCells);
                    label.setCellFormat(headerCells);
                    supportReceivedSheet.addCell(label);                    
                    nextCell = 2;
                    
                    //get and print column names
                    for (int i=0; i<listOfSupportTypes.size(); i++) {
                        label = new Label(nextCell,3,(String) listOfSupportTypes.get(i),headerCells);
                        supportColumnMap.put(supportTypesMap.get(label.getContents()), label.getColumn()+1);                        
                        label.setCellFormat(headerCells);
                        supportReceivedSheet.addCell(label);
                        nextCell++;
                    }
                    
                    for (int i=0; i<DR_SUPPORT_COLUMN_NAMES.size(); i++) {
                        label = new Label(nextCell,3,(String) DR_SUPPORT_COLUMN_NAMES.get(i),headerCells);
                        supportColumnMap.put(DR_SUPPORT_COLUMN_NAMES.get(i), label.getColumn()+1);
                        label.setCellFormat(headerCells);
                        supportReceivedSheet.addCell(label);
                        nextCell++;
                    }
                    rs.close();                    
                    
                    hotroQuery_Statement = m_Connection.createStatement();                
                    hotroQuery = "select dp.ten,dp.ma,s.* from dr_support s inner join dr_disabilitypeople dp on s.id_nkt = dp.id where s.status_id = 1 and dp.id_tinh in (" + getAreaIds(m_Connection, areaId) + ") order by id_nkt desc,datecreate desc";                    
                    rs = hotroQuery_Statement.executeQuery(hotroQuery);
                    
                    rCounter = 4;
                    cCount = listOfSupportTypes.size() + 3;
                    nextCell = 2;
                    
                    while (rs.next()) {                        
                        label = new Label(0,rCounter,rs.getString("ma"),normalCell);
                        label.setCellFormat(normalCell);
                        supportReceivedSheet.addCell(label);
                        
                        label = new Label(1,rCounter,rs.getString("ten"),normalCell);
                        label.setCellFormat(normalCell);
                        supportReceivedSheet.addCell(label);
                          
                        hotro_ids = rs.getString("dm_hotro_ids");
                        if(!"".equals(hotro_ids) && hotro_ids!=null){
                            hotro_tokens = hotro_ids.split("#");
                            if (hotro_tokens.length>0){
                                for(int i = 0; i<hotro_tokens.length;i++){                            
                                    if(!"".equalsIgnoreCase(hotro_tokens[i]) && !"0".equalsIgnoreCase(hotro_tokens[i])){
                                        label = new Label(supportColumnMap.get(hotro_tokens[i])-1,rCounter,"1",normalCell);
                                        supportReceivedSheet.addCell(label);
                                    }
                                }
                            }
                        }                        
                          
                        for(int i=0;i<DR_SUPPORT_COLUMN_NAMES.size();i++){
                            if(rs.getString(DR_SUPPORT_COLUMN_NAMES.get(i))!=null){
                                String translatedString = rs.getString(DR_SUPPORT_COLUMN_NAMES.get(i));
                                if("datecreate".equals(DR_SUPPORT_COLUMN_NAMES.get(i))
                                    || "datefrom".equals(DR_SUPPORT_COLUMN_NAMES.get(i))
                                    || "dateto".equals(DR_SUPPORT_COLUMN_NAMES.get(i))){
                                    translatedString = bean.dateToString(rs.getDate(DR_SUPPORT_COLUMN_NAMES.get(i)));
                                }                                
                                label = new Label(supportColumnMap.get(DR_SUPPORT_COLUMN_NAMES.get(i))-1,rCounter,translatedString,normalCell);                                
                                supportReceivedSheet.addCell(label);
                            }
                        }                        
                        rCounter++;
                    }
                    
                    workbook.write();                    
                    workbook.close();
                    valReturn = file.getPath();
                }
              
            } catch (IOException e) {
                e.printStackTrace();
            }           
        }catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println(query);
        }
        finally {
            try {
                if (m_ResultSet != null)    m_ResultSet.close();
                if (m_Statement != null)    m_Statement.close();
                if (m_Connection != null)   m_Connection.close();
                if (rs != null)             rs.close();
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        return valReturn;
    }
                            
    public List<String> getDBValueListByKey(Connection connection, String columnName, String tableName, String key, String returnColumnValue){
        ResultSet returnResultSet = null;
        String sqlSelect = "select * from " + tableName + " where " + columnName + "=" + key;
        System.out.println("getDBValueListByKey: Firing Query - "+sqlSelect);
        List value = new ArrayList<String>();
        
        try {
            Statement m_Statement = connection.createStatement();
            returnResultSet = m_Statement.executeQuery(sqlSelect);
            while(returnResultSet.next()){
                value.add(returnResultSet.getString(returnColumnValue)+"-"+returnResultSet.getString("code"));
                //value.add(returnResultSet.getString(returnColumnValue));
            }                
        } catch (SQLException e) {
                e.printStackTrace();
        }
        return value;              
    }
    
    public List<ReturnData> getDBValueObjectListByKey(Connection connection, 
            String columnList, String columnName, String tableName, 
            String key, String returnColumnValue, String additionalClauses){
        ResultSet rs = null;
        ReturnData returnData = new ReturnData();
        List<ReturnData> returnList = new ArrayList<ReturnData>(); 
        
        String sqlSelect = "select * from " + tableName + " where " + columnName + "=" + key + " " + additionalClauses;
        String columnNames[] = columnList.split(",");      
        try {
            Statement m_Statement = connection.createStatement();
            rs = m_Statement.executeQuery(sqlSelect);
            while(rs.next()){
                returnData = new ReturnData();
                returnData.setKey(rs.getString(columnNames[0]));
                returnData.setValue(rs.getString(columnNames[1]));
                returnData.setCcode(rs.getString(columnNames[2]));
                returnData.setParentID(rs.getString(columnNames[3]));                
                returnList.add(returnData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return returnList;      
    }
    
    public String getPathFile(FExport bean){
        // Create Folder Store file data
        String pathFolder = IKeyDisability.PATH_FOLDER_EXPORT_DATA;                
        Date now = bean.getCurrentDate();                
        int year = now.getYear() + 1900;
        int month = now.getMonth() + 1;
        
        String strYear = String.valueOf(year);
        String strMonth = month<10? ("0" + month): String.valueOf(month);
        
        //if(bean.getPath_folder()==null){
        File file = new File(pathFolder);
        if(!file.exists()){
            file.mkdir();                                            
            if(strYear.indexOf(file.getPath())<0){
                file = new File(pathFolder + "\\" + strYear);
                file.mkdir();
                
                if(strMonth.indexOf(file.getPath())<0){
                    file = new File(file.getPath() + "\\" + strMonth);
                    file.mkdir();
                    bean.setPath_folder(file.getPath());
                } 
            }                    
        } else {
            if(strYear.indexOf(file.getPath())<0){
                file = new File(pathFolder + "\\" + strYear);
                file.mkdir();
                
                if(strMonth.indexOf(file.getPath())<0){
                    file = new File(file.getPath() + "\\" + strMonth);
                    file.mkdir();
                    bean.setPath_folder(file.getPath());
                } 
            }
        }
        
        return bean.getPath_folder() + "\\" + bean.getTinh_name() + ".xls";
    }
    
    public String getAreaIds(Connection cnn, int areaId) throws EException {
        String strArea = String.valueOf(areaId);
        DTinh daoTinh = new DTinh();
        strArea = daoTinh.getMembers(cnn, areaId ,"");
        return strArea;
    }
}
