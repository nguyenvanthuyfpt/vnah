package com.form;


public class FTree extends FSeed
{
    private int id;
    private String name;
    private int parentID;
    private int level;
    private int parentName=0;
    
  public FTree()
  {
  }
    public int getId() {   return id;  }    public void setId(int id)  { this.id = id;  }
    public String getName() { return name; } public void setName(String name){ this.name = name; }
    public int getParentID() {   return parentID;  }    public void setParentID(int parentID)  { this.parentID = parentID;  }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }

    public int getParentName() {
        return parentName;
    }

    public void setParentName(int parentName) {
        this.parentName = parentName;
    }
}
