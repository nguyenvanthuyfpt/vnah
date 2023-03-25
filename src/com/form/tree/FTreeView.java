

package com.form.tree;

import com.form.FSeed;


// Referenced classes of package com.form:
//            FSeed

public class FTreeView extends FSeed
{

    private void $init$()
    {
        parentName = 0;
    }

    public FTreeView()
    {
        $init$();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getParentID()
    {
        return parentID;
    }

    public void setParentID(int parentID)
    {
        this.parentID = parentID;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }

    public int getParentName()
    {
        return parentName;
    }

    public void setParentName(int parentName)
    {
        this.parentName = parentName;
    }

    private int id;
    private String name;
    private String nameTemp;
    private int parentID;
    private int level;
    private int parentName;

    public String getNameTemp() {
        return nameTemp;
    }

    public void setNameTemp(String nameTemp) {
        this.nameTemp = nameTemp;
    }
}
