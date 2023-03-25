package com.form;

public class ChartItem {
    private String locationId;
    private String locationName;
    private String chartType;
    private String ttpId;
    private String qhuId;
    private String pxaId;
    private String level;
    private int total;

    public ChartItem(String locationId, String locationName, int total) {
        super();
        this.locationId = locationId;
        this.locationName = locationName;
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getChartType() {
        return chartType;
    }

    public void setChartType(String chartType) {
        this.chartType = chartType;
    }

    public String getTtpId() {
        return ttpId;
    }

    public void setTtpId(String ttpId) {
        this.ttpId = ttpId;
    }

    public String getQhuId() {
        return qhuId;
    }

    public void setQhuId(String qhuId) {
        this.qhuId = qhuId;
    }

    public String getPxaId() {
        return pxaId;
    }

    public void setPxaId(String pxaId) {
        this.pxaId = pxaId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

}
