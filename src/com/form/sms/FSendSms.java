package com.form.sms;

import com.form.FSeed;

public class FSendSms extends FSeed {
    private String to;
    private String content;
    
public void reset(){
     this.to="";
     this.content="";
}

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
