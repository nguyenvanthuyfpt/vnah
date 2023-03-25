package com.form.mail.mailFilter;

import com.form.FSeed;

public class FMailFilter extends FSeed
{
  private int id;
  private String from;
  private int likeFrom;
  private String subject;
  private int likeSubjec;
    private int userId;
    public void reset()
    {
          this.setFrom("");
          this.setLikeFrom(0);
          this.setSubject("");
          this.setLikeSubjec(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getLikeFrom() {
        return likeFrom;
    }

    public void setLikeFrom(int likeFrom) {
        this.likeFrom = likeFrom;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getLikeSubjec() {
        return likeSubjec;
    }

    public void setLikeSubjec(int likeSubjec) {
        this.likeSubjec = likeSubjec;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
