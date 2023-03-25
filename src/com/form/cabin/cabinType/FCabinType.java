package com.form.cabin.cabinType;

import com.form.FSeed;

public class FCabinType extends FSeed
{
  private int id;
    private int parentID;
    private int departmentId;
    private int type;
    private int userId;
  private String code;
  private String name;
  private String description;

  public int getId()
  {
    return this.id;
  }
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getCode()
  {
    return this.code;
  }
  public void setCode(String code)
  {
    this.code = (code == null) ? "" : code;
  }
  
  public String getName()
  {
    return this.name;
  }
  public void setName(String name)
  {
    this.name = (name == null) ? "" : name;
  }
  
  public String getDescription()
  {
    return this.description;
  }
  public void setDescription(String description)
  {
    this.description = (description == null) ? "" : description;
  }
  
  public void reset()
  {
    this.id = -1;
//    this.parentID =0;
    this.name = "";
    this.code = "";
    this.description = "";
  }


    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
