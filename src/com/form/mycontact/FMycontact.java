
package com.form.mycontact;


import com.form.FSeed;

import com.inf.admin.IConstantsAdmin;

public class FMycontact  extends FSeed
{
private String name;//Search
    
    private int id;
    private String fullName;
    private String cc;
    private String toAddress;
    private String picture;
    private String email;
    private String phone;
    private String address;
    private String description;
    private String dateCreate;    
    private String departmentName;
    
    private int departmentID;
    
    private String icq;
    private String ym;
    private String msn;
    private String gtalk;
    private String skype;
    private long userId;
    
    
    private int active=1;
    
    private int pgroupId;
    private String groupName;
    private String app="";
    
    private int pageIndex=1;
    private int[] groupsID;
    private String[] menus;
    private String checked ;
    

  /**
   * Construct an instance of loginSystem
   */
 
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getFullName() { return fullName; } public void setFullName(String fullName){ this.fullName = fullName; }
    public String getPicture() {   return picture;  }    public void setPicture(String picture)  { this.picture = picture;  }
    public String getEmail() {   return email;  }    public void setEmail(String email)  { this.email = email;  }
    public String getPhone() { return phone; } public void setPhone(String phone){ this.phone = phone; }
    public String getAddress() { return address; } public void setAddress(String address){ this.address = address; }
    public String getDescription() {   return description;  }    public void setDescription(String description)  { this.description = description;  }
    public String getDateCreate() {   return dateCreate;  }    public void setDateCreate(String dateCreate)  { this.dateCreate = dateCreate;  }
    
    public String getDepartmentName() { return departmentName; } public void setDepartmentName(String departmentName){ this.departmentName = departmentName; }
    
   
    
    public String getGroupName() { return groupName; } public void setGroupName(String groupName){ this.groupName = groupName; }
    public String getApp() {   return app;  }   public void setApp(String app)  { this.app = app; }
    public void setApps(int[] apps)  { 
         this.app="_";
         if(apps!=null){
             for(int i=0;i<apps.length;i++){
                 this.app+= IConstantsAdmin.APP_SEPARATE_ + apps[i] + IConstantsAdmin.APP_SEPARATE_;
             }
         }
     }

  
    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

   

    public int[] getGroupsID() {
        return groupsID;
    }

    public void setGroupsID(int[] groupsID) {
        this.groupsID = groupsID;
    }

   

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }


    public String[] getMenus() {
        return menus;
    }

    public void setMenus(String[] menus) {
        this.menus = menus;
    }

    public String getIcq() {
        return icq;
    }

    public void setIcq(String icq) {
        this.icq = icq;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn;
    }

    public String getGtalk() {
        return gtalk;
    }

    public void setGtalk(String gtalk) {
        this.gtalk = gtalk;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getPgroupId() {
        return pgroupId;
    }

    public void setPgroupId(int pgroupId) {
        this.pgroupId = pgroupId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentID = departmentID;
    }
}
