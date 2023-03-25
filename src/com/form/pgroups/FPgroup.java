package com.form.pgroups;

import com.form.FBeans;
import com.form.FSeed;


public class FPgroup extends FSeed
{
    private int id;
    private String code;
    private String name;
    private String description;
    private int parentId;   
    private long userId;
    
  
    private String dateCreate;
    private String selected;
   
    private FBeans mycontacts;
 
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getCode() {   return code;  }    public void setCode(String code)  { this.code = code;  }
    public String getName() { return name; } public void setName(String name){ this.name = name; }
  
  
 
    public String getDateCreate() { return dateCreate; } public void setDateCreate(String dateCreate){ this.dateCreate = dateCreate; }
   
   
    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public FBeans getMycontacts() {
        return mycontacts;
    }

    public void setMycontacts(FBeans mycontacts) {
        this.mycontacts = mycontacts;
    }

    
}
