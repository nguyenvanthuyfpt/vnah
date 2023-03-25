package com.form;


import com.dao.disability.DSqlDisability;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;

public class FExportExcel extends DSqlDisability {


    public static CellStyle getStyleNumber(HSSFWorkbook wb, HSSFFont font, 
                                               short hAlignment, 
                                               short vAlignment, 
                                               short borderTop, 
                                               short borderBottom, 
                                               short borderLeft, 
                                               short borderRight, 
                                               String dataFormat) {
        CellStyle style = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat(dataFormat));
        //set border
        style.setBorderTop(borderTop);
        style.setBorderBottom(borderBottom);
        style.setBorderLeft(borderLeft);
        style.setBorderRight(borderRight);
        //set alignment
        style.setAlignment(hAlignment);
        style.setVerticalAlignment(vAlignment);
        //set font
        style.setFont(font);
        return style;
    }


    public final CellStyle getStyle(HSSFWorkbook wb, HSSFFont font, 
                                        short hAlignment, short vAlignment, 
                                        short borderTop, short borderBottom, 
                                        short borderLeft, short borderRight) {

        CellStyle style = wb.createCellStyle();
        //set border
        style.setBorderTop(borderTop);
        style.setBorderBottom(borderBottom);
        style.setBorderLeft(borderLeft);
        style.setBorderRight(borderRight);
        //set alignment
        style.setAlignment(hAlignment);
        style.setVerticalAlignment(vAlignment);
        //set font
        style.setFont(font);
        style.setWrapText(false);
        return style;
    }

    public static HSSFFont getFont(HSSFWorkbook wb, String fontName, Integer fontSize, boolean bold) {
        HSSFFont font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)(int)fontSize);
        if (!bold) {
            font.setBoldweight(font.BOLDWEIGHT_NORMAL);
        } else {
            font.setBoldweight(font.BOLDWEIGHT_BOLD);
        }
        return font;
    }

    public static HSSFFont getFont2(HSSFWorkbook wb, String fontName, 
                                    Integer fontSize, boolean italic) {
        HSSFFont font = wb.createFont();
        font.setFontName(fontName);
        font.setFontHeightInPoints((short)(int)fontSize);
        if (!italic) {
            font.setBoldweight(font.BOLDWEIGHT_NORMAL);
        } else {
            font.setItalic(true);
            font.setBoldweight(font.BOLDWEIGHT_BOLD);
        }
        return font;
    }
    
    private void createCell(HSSFRow row,int col,CellStyle cs,String content) {
        HSSFCell cell=row.createCell(col);
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);                
        cell.setCellValue(content);             
        if (cs !=null)  cell.setCellStyle(cs);
    }
    
    public final void wrap(HSSFRow row, int column, CellStyle cs) {
        HSSFCell cell = row.createCell(column);        
        cell.setCellStyle(cs);
    }

    public final void createCell(HSSFRow  row, int column, Object value, 
                                 HSSFWorkbook wb, CellStyle style) {
        HSSFCell cell = row.createCell(column);        
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        if (style != null)
            cell.setCellStyle(style);

        //check datatype then set value
        if (value instanceof String) {
            cell.setCellValue((String)value);
        } else if (value instanceof Integer) {
            if ((Integer)value != 0) {
                cell.setCellValue((Integer)value);
            } else {
                cell.setCellValue("0");
            }
        } else if (value instanceof Float) {
            if ((Float)value != 0) {
                cell.setCellValue((Float)value);
            }
        } else if (value instanceof Double) {
            if ((Double)value != 0) {
                cell.setCellValue((Double)value);
            }

        } else if (value instanceof Long) {
            if ((Long)value != 0) {
                cell.setCellValue((Long)value);
            }
        }
    }
    
    public final void createCellFormula(HSSFRow  row, int column, String formula, 
                                 HSSFWorkbook wb, CellStyle style) {
         HSSFCell cell = row.createCell(column);        
         cell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
         if (style != null)
             cell.setCellStyle(style);
         cell.setCellFormula(formula);
    }
    
    public final void add_merged_region(HSSFWorkbook wb, int rf,int rt,int cf,int ct,int border,int color) {
        CellRangeAddress region=new CellRangeAddress(rf,rt,cf,ct);
        HSSFSheet sheet = wb.getSheetAt(0);
        sheet.addMergedRegion(region);
        RegionUtil.setBorderLeft(border,region,sheet,wb);
        RegionUtil.setBorderBottom(border,region,sheet,wb);
        RegionUtil.setBorderRight(border,region,sheet,wb);
        RegionUtil.setBorderTop(border,region,sheet,wb);
        RegionUtil.setBottomBorderColor(color,region,sheet,wb);
        RegionUtil.setLeftBorderColor(color,region,sheet,wb);
        RegionUtil.setRightBorderColor(color,region,sheet,wb);
        RegionUtil.setTopBorderColor(color,region,sheet,wb);        
    }
    
    public final void createRegion(HSSFWorkbook wb, HSSFRow row, int rf, int rt, int cf, int ct, CellStyle cs,String content,int border,int color) {
        HSSFCell cell=row.createCell(cf);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell.setCellValue(content);     
        if (cs!=null) cell.setCellStyle(cs);
        add_merged_region(wb, rf,rt,cf,ct, border,color);
    }

    public final void bold(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        cell.setCellStyle(style);
    }

    public final void italic(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        HSSFFont font = wb.createFont();
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);    
        font.setItalic(true);
        style.setFont(font);
        cell.setCellStyle(style);
    }
    
    public final void setStyle(HSSFRow row, int column, HSSFWorkbook wb, 
                               CellStyle style) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        cell.setCellStyle(style);
    }

    public final void setAlignR(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        cell.setCellStyle(style);
    }

    public final void setAlignL(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_LEFT);
        cell.setCellStyle(style);
    }

    public final void setAlignC(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(CellStyle.ALIGN_CENTER);
        cell.setCellStyle(style);
    }

    public final void setBoderR(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cell.setCellStyle(style);
    }

    public final void setBoderL(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cell.setCellStyle(style);
    }

    public final void setBoderT(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cell.setCellStyle(style);
    }

    public final void setBoderB(HSSFRow row, int column, HSSFWorkbook wb) {
        HSSFCell cell = row.getCell(column);
        if (cell == null)
            cell = row.createCell(column);
        CellStyle style = wb.createCellStyle();
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cell.setCellStyle(style);
    }
}

