package com.form.disability;

import com.form.FSeed;

public class FExport extends FSeed {
    private String tinh_id;
    private String tinh_name;
    private String path_folder;
    private int exportType = 2;
    private String table;
    private String columns;

    public void setTinh_id(String tinh_id) {
        this.tinh_id = tinh_id;
    }

    public String getTinh_id() {
        return tinh_id;
    }

    public void setTinh_name(String tinh_name) {
        this.tinh_name = tinh_name;
    }

    public String getTinh_name() {
        return tinh_name;
    }

    public void reset() {
        this.tinh_id = "";
        this.tinh_name = "";
    }

    public void setPath_folder(String path_folder) {
        this.path_folder = path_folder;
    }

    public String getPath_folder() {
        return path_folder;
    }


    public void setTable(String table) {
        this.table = table;
    }

    public String getTable() {
        return table;
    }

    public void setColumns(String columns) {
        this.columns = columns;
    }

    public String getColumns() {
        return columns;
    }


    public void setExportType(int exportType) {
        this.exportType = exportType;
    }

    public int getExportType() {
        return exportType;
    }
}
