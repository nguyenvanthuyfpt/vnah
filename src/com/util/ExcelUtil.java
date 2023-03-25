package com.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import java.math.BigDecimal;

import java.sql.ResultSet;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderFormatting;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;


public class ExcelUtil {
    private Logger logger = Logger.getLogger(ExcelUtil.class);
    private static final String REF_HEADER_STT = "#";
    private static final String CURRENCY_PATTERN = "#,##0.00";
    private static final String NUMBER_PATTERN = "#,##0";
    private static final String DATE_FORMAT = "dd/MM/yyyy";

    private String report_code;
    private Workbook wb;
    private Sheet sheet;
    private ResultSet rs;
    private String sheet_name;
    private int sheet_index = 0;

    private CellStyle cs_left_11;
    private CellStyle cs_left_11b;
    private CellStyle cs_left_11_border;

    private CellStyle cs_center_11b;
    private CellStyle cs_center_11_border;
    private CellStyle cs_int_11;
    private CellStyle cs_center_12b;

    public ExcelUtil(String report_code) {
        this.report_code = report_code;
        File file = new File(Constant.report_dir + report_code + ".xls");
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

        Font f_11, f_11b, f_12b;
        f_11 = createFont(Font.BOLDWEIGHT_NORMAL, 11);
        f_11b = createFont(Font.BOLDWEIGHT_BOLD, 11);
        f_12b = createFont(Font.BOLDWEIGHT_BOLD, 12);

        cs_center_11b =
                createCellStyle(CellStyle.ALIGN_CENTER, f_11b, 0, false);
        cs_center_11_border =
                createCellStyle(CellStyle.ALIGN_CENTER, f_11, 0, true);
        cs_center_11_border.setBorderTop(CellStyle.BORDER_THIN);
        cs_center_11_border.setBorderLeft(CellStyle.BORDER_THIN);
        cs_center_11_border.setBorderRight(CellStyle.BORDER_THIN);
        cs_center_11_border.setBorderBottom(CellStyle.BORDER_THIN);
        cs_center_11_border.setTopBorderColor(HSSFColor.BLUE_GREY.index);
        cs_center_11_border.setLeftBorderColor(HSSFColor.BLUE_GREY.index);
        cs_center_11_border.setRightBorderColor(HSSFColor.BLUE_GREY.index);
        cs_center_11_border.setBottomBorderColor(HSSFColor.BLUE_GREY.index);

        cs_left_11b = createCellStyle(CellStyle.ALIGN_LEFT, f_11b, 0, true);
        cs_left_11 = createCellStyle(CellStyle.ALIGN_LEFT, f_11, 0, true);
        cs_left_11_border =
                createCellStyle(CellStyle.ALIGN_LEFT, f_11, 0, true);
        cs_left_11_border.setBorderTop(CellStyle.BORDER_THIN);
        cs_left_11_border.setBorderLeft(CellStyle.BORDER_THIN);
        cs_left_11_border.setBorderRight(CellStyle.BORDER_THIN);
        cs_left_11_border.setBorderBottom(CellStyle.BORDER_THIN);
        cs_left_11_border.setTopBorderColor(HSSFColor.BLUE_GREY.index);
        cs_left_11_border.setLeftBorderColor(HSSFColor.BLUE_GREY.index);
        cs_left_11_border.setRightBorderColor(HSSFColor.BLUE_GREY.index);
        cs_left_11_border.setBottomBorderColor(HSSFColor.BLUE_GREY.index);

        DataFormat df = wb.createDataFormat();
        cs_int_11 =
                createCellStyle(CellStyle.ALIGN_RIGHT, f_11, df.getFormat("#,##0"),
                                true);
        cs_int_11.setBorderTop(CellStyle.BORDER_THIN);
        cs_int_11.setBorderLeft(CellStyle.BORDER_THIN);
        cs_int_11.setBorderRight(CellStyle.BORDER_THIN);
        cs_int_11.setBorderBottom(CellStyle.BORDER_THIN);
        cs_int_11.setTopBorderColor(HSSFColor.BLUE_GREY.index);
        cs_int_11.setLeftBorderColor(HSSFColor.BLUE_GREY.index);
        cs_int_11.setRightBorderColor(HSSFColor.BLUE_GREY.index);
        cs_int_11.setBottomBorderColor(HSSFColor.BLUE_GREY.index);
        cs_center_12b =
                createCellStyle(CellStyle.ALIGN_CENTER, f_12b, 0, false);

    }

    public Workbook exportToExcel(LinkedHashMap<String, List> sheetObj,
                                  Map<String, List> sheetHeader) throws Exception {
        // Get current time to calculate execute time for this method
        long startTime = System.currentTimeMillis();

        Workbook wb = new HSSFWorkbook();
        Sheet sheet;

        // Get cell style
        CellStyle alignLeftStyle = getAlignLeft(wb, wb.createDataFormat());
        CellStyle alignRightStyle = getAlignRight(wb, wb.createDataFormat());
        CellStyle alignCenterStyle = getAlignCenter(wb, wb.createDataFormat());
        CellStyle hdCellStyle = getHeaderStyle(wb, wb.createDataFormat());
        CellStyle dateCellStyle = getDateCellStyle(wb, wb.createDataFormat());
        //        Font requiredCell = getRequiredCell(wb, wb.createDataFormat());

        String sheetName;
        List<String> lstSheetName = new ArrayList<String>();
        List lstObj;
        //List<AfcRefCode> propsHeader;

        Iterator it = sheetObj.entrySet().iterator();
        int start = 0;
        int startRow = 1;
        while (it.hasNext()) {
            // Get sheetName and data list
            Map.Entry entry = (Map.Entry)it.next();
            sheetName = (String)entry.getKey();
            lstObj = (List)entry.getValue();

            lstSheetName.add(sheetName);

            // Create sheet by sheetName
            sheet = wb.createSheet(sheetName);

            // Freeze for Header
            sheet.createFreezePane(0, 1);

            // Get headers properties on sheet
            /*
              propsHeader = sheetHeader.get(sheetName);

              // Add column index
              AfcRefCode refIndex = new AfcRefCode();
              refIndex.setItemCode(StringUtils.EMPTY);
              refIndex.setItemNameEn(REF_HEADER_STT);
              refIndex.setItemNameVn(REF_HEADER_STT);
              propsHeader.add(0, refIndex);
              */

            // Write data row Header on first row
            Row hdRowVn = sheet.createRow(0);

            Cell hdCellVn;
            /*
              int hdSize = propsHeader.size();
              for (int i = 0; i < hdSize; i++) {
                  hdCellVn = hdRowVn.createCell(i);

                  // Set data Header
                  hdCellVn.setCellValue(propsHeader.get(i).getItemNameVn());
                  hdCellVn.setCellStyle(hdCellStyle);

                  // Set auto width belong to it's contents
                  sheet.autoSizeColumn(i);
              }
              // Remove STT from header
              propsHeader.remove(0);

  //            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));

              // Check list and write cell data
              if (!CollectionUtils.isEmpty(lstObj)) {
                  writeDataCellOnSheet(lstObj, sheet, propsHeader,
                                       alignLeftStyle, dateCellStyle,
                                       alignRightStyle, alignCenterStyle,
                                       startRow);
              }
              */
        }

        // Display total times to execute this method
        long stopTime = System.currentTimeMillis();
        // System.out.println(stopTime - startTime);
        logger.debug("time exec :" + (stopTime - startTime));
        return wb;
    }

    public byte[] wb2zip(String file_name) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zos = new ZipOutputStream(baos);
        zos.setLevel(9);
        ZipEntry ze = new ZipEntry(file_name + ".xls");
        zos.putNextEntry(ze);
        wb.write(zos);
        zos.closeEntry();
        zos.close();
        byte[] bytes = baos.toByteArray();
        baos.close();
        return bytes;
    }

    public void setFirstVisibleTab(int i) {
        wb.setFirstVisibleTab(i);
    }

    public void setActiveSheet(int i) {
        wb.setActiveSheet(i);
    }


    private Font createFont(int bold, int height) {
        Font font = wb.createFont();
        font.setBoldweight((short)bold);
        font.setFontHeightInPoints((short)height);
        font.setFontName("Times New Roman");
        return font;
    }

    private CellStyle createCellStyle(int alignment, Font font, int format,
                                      boolean wraptext) {
        CellStyle cs = wb.createCellStyle();
        cs.setAlignment((short)alignment);
        cs.setFont(font);
        cs.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        if (wraptext)
            cs.setWrapText(true);
        if (format != 0)
            cs.setDataFormat((short)format);
        return cs;
    }

    private void createCell(Row row, int col, CellStyle cs, String content) {
        Cell cell = row.createCell(col);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(content);
        if (cs != null)
            cell.setCellStyle(cs);
    }

    private void add_merged_region(int rf, int rt, int cf, int ct, int border,
                                   int color) {
        CellRangeAddress region = new CellRangeAddress(rf, rt, cf, ct);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderLeft(border, region, sheet, wb);
        RegionUtil.setBorderBottom(border, region, sheet, wb);
        RegionUtil.setBorderRight(border, region, sheet, wb);
        RegionUtil.setBorderTop(border, region, sheet, wb);
        RegionUtil.setBottomBorderColor(color, region, sheet, wb);
        RegionUtil.setLeftBorderColor(color, region, sheet, wb);
        RegionUtil.setRightBorderColor(color, region, sheet, wb);
        RegionUtil.setTopBorderColor(color, region, sheet, wb);
    }

    private void createRegion(Row row, int rf, int rt, int cf, int ct,
                              CellStyle cs, String content, int border,
                              int color) {
        Cell cell = row.createCell(cf);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(content);
        if (cs != null)
            cell.setCellStyle(cs);
        add_merged_region(rf, rt, cf, ct, border, color);
    }


    /**
     * Get value of cell as String type
     * @param cell
     * @return value String
     */
    private String getCellValueAsString(Cell cell) {
        if (cell != null) {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            return cell.getStringCellValue();
        }
        return StringUtils.EMPTY;
    }

    /**
     * Get Integer value from String
     * @param value
     * @return
     */
    private BigDecimal getBigDecimalValue(String value) throws ParseException {
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(Locale.US);
        df.setParseBigDecimal(true);
        BigDecimal bd = (BigDecimal)df.parseObject(value);

        return bd;
    }

    /**
     * Get Double value from String
     * @param value
     * @return
     */
    private double getDoubleValue(String value) throws ParseException {
        double number = Double.parseDouble(value.replaceAll(",", ""));
        return number;
    }

    /**
     * Format data number to count Number as String value
     * @param number
     * @return
     */
    private String numberAsString(Number number) {
        if (number == null) {
            return StringUtils.EMPTY;
        }

        DecimalFormat format = new DecimalFormat();
        format.applyPattern(NUMBER_PATTERN);
        return format.format(number);
    }

    /**
     * Format data number to Currency as String value
     * @param number
     * @return
     */
    private String currencyAsString(Number number) {
        if (number == null) {
            return StringUtils.EMPTY;
        }

        DecimalFormat format = new DecimalFormat();
        format.applyPattern(CURRENCY_PATTERN);
        return format.format(number);
    }

    /**
     * Get Integer value from String
     * @param value
     * @return
     */
    private Integer getIntegerValue(String value) throws ParseException {
        Integer number = Integer.parseInt(value.replaceAll(",", ""));
        return number;
    }

    /**
     * Get Long value from String
     * @param value
     * @return
     */
    private long getLongValue(String value) throws ParseException {
        long number = Long.parseLong(value.replaceAll(",", ""));
        return number;
    }

    /**
     * Define header cell style
     * @param wb
     * @return
     */
    private CellStyle getHeaderStyle(Workbook wb, DataFormat format) {
        CellStyle hdStyle = wb.createCellStyle();

        // create fonts objects
        Font f = wb.createFont();

        // Set font
        f.setFontHeightInPoints((short)10);
        f.setColor(IndexedColors.WHITE.getIndex());
        f.setBoldweight(Font.BOLDWEIGHT_BOLD);

        // Set cell style and border THIN for header
        hdStyle.setFont(f);
        hdStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
        hdStyle.setBorderRight(BorderFormatting.BORDER_THIN);
        hdStyle.setBorderTop(BorderFormatting.BORDER_THIN);
        hdStyle.setBorderBottom(BorderFormatting.BORDER_THIN);

        // Set Header text Vertical align is Center, Align is Center
        hdStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        hdStyle.setAlignment(CellStyle.ALIGN_CENTER);
        hdStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());
        hdStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        hdStyle.setDataFormat(format.getFormat("@"));

        return hdStyle;
    }

    /**
     * Checking row data is Blank by passed first column index
     * @param row
     * @param propSize
     * @return
     */
    private boolean isBlankRow(Row row, int propSize, int startCol) {
        boolean isBlank = true;
        startCol = (startCol >= 0 || startCol < propSize) ? startCol : 1;
        // By passed data column RowIndex with i = 0.
        for (int i = startCol; i <= propSize; i++) {
            if (row.getCell(i) != null &&
                Cell.CELL_TYPE_BLANK != row.getCell(i).getCellType()) {
                isBlank = false;
                break;
            }
        }
        return isBlank;
    }

    /**
     * Define Date cell style
     * @param wb
     * @return
     */
    private CellStyle getDateCellStyle(Workbook wb, DataFormat format) {
        CellStyle dateStyle = wb.createCellStyle();

        dateStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
        dateStyle.setBorderRight(BorderFormatting.BORDER_THIN);
        dateStyle.setBorderTop(BorderFormatting.BORDER_THIN);
        dateStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
        dateStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        dateStyle.setAlignment(CellStyle.ALIGN_CENTER);

        // Set Format date
        dateStyle.setDataFormat(format.getFormat(DATE_FORMAT));

        return dateStyle;
    }

    /**
     * Align text to LEFT
     * @param wb
     * @return
     */
    private CellStyle getAlignLeft(Workbook wb, DataFormat format) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(CellStyle.ALIGN_LEFT);

        cellStyle.setDataFormat(format.getFormat("@"));

        return cellStyle;
    }

    /**
     * Align text to RIGHT
     * @param wb
     * @return
     */
    private CellStyle getAlignRight(Workbook wb, DataFormat format) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(CellStyle.ALIGN_RIGHT);

        cellStyle.setDataFormat(format.getFormat("@"));

        return cellStyle;
    }

    /**
     * Align text to CENTER
     * @param wb
     * @return
     */
    private CellStyle getAlignCenter(Workbook wb, DataFormat format) {

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderLeft(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderRight(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderTop(BorderFormatting.BORDER_THIN);
        cellStyle.setBorderBottom(BorderFormatting.BORDER_THIN);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);

        cellStyle.setDataFormat(format.getFormat("@"));

        return cellStyle;
    }
}
