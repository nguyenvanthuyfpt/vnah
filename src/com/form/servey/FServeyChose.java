package com.form.servey;

import com.form.FSeed;


public class FServeyChose extends FSeed{
    private int questionId;
    private int userId;
    private int chosetime;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getChosetime() {
        return chosetime;
    }

    public void setChosetime(int chosetime) {
        this.chosetime = chosetime;
    }
}
