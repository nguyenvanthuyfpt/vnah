package com.form.disability;


import com.form.FBeans;
import com.form.FSeed;

public class FUnit extends FSeed {
    //catogory
    private int total;
    private int id_type;
    private String name_type;
    private FBeans subBean = new FBeans();
    //
    private int id;
    private String name;
    private String address;
    private String phone;
    private String gioiThieu;
    private String fax;
    private String email;
    private String nguoilienhe;
    
    private int tinhId;
    private String tinhName;
   
    private int pageIndex;
    private int totalResult;

    public void reset() {
        this.id = 0;
        this.name = "";
        this.address = "";
        this.gioiThieu = "";
        this.phone = "";
        this.fax = "";
        this.email = "";
        this.tinhId = 0;    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId_type() {
        return id_type;
    }

    public void setId_type(int id_type) {
        this.id_type = id_type;
    }

    public String getName_type() {
        return name_type;
    }

    public void setName_type(String name_type) {
        this.name_type = name_type;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public FBeans getSubBean() {
        return subBean;
    }

    public void setSubBean(FBeans subBean) {
        this.subBean = subBean;
    }

    public String getGioiThieu() {
        return gioiThieu;
    }

    public void setGioiThieu(String gioiThieu) {
        this.gioiThieu = gioiThieu;
    }

    public int getTinhId() {
        return tinhId;
    }

    public void setTinhId(int tinhId) {
        this.tinhId = tinhId;
    }

    public String getNguoilienhe() {
        return nguoilienhe;
    }

    public void setNguoilienhe(String nguoilienhe) {
        this.nguoilienhe = nguoilienhe;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setTinhName(String tinhName) {
        this.tinhName = tinhName;
    }

    public String getTinhName() {
        return tinhName;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getTotalResult() {
        return totalResult;
    }
}
