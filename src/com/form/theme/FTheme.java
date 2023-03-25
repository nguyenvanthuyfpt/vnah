package com.form.theme;


import com.form.FSeed;

public class FTheme extends FSeed{
 
    private int id;
    private String title;
    private String pathImages;
    private String pathStyle;
    private int active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   
    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }


    public String getPathImages() {
        return pathImages;
    }

    public void setPathImages(String pathImages) {
        this.pathImages = pathImages;
    }

    public String getPathStyle() {
        return pathStyle;
    }

    public void setPathStyle(String pathStyle) {
        this.pathStyle = pathStyle;
    }
}
