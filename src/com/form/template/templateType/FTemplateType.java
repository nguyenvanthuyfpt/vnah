package com.form.template.templateType;

import com.form.FSeed;

public class FTemplateType extends FSeed
{
  private int id;
  private String code;
  private String name;
  private String description;
  
  
  public void reset()
  {
        this.setName("");
        this.setCode("");
        this.setId(0);
        this.setDescription("");
  }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
