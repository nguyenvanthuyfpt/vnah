package com.form.servey;

import com.form.FSeed;


public class FServeyQuestions extends FSeed{
 
        private int questionId;
        private int serveyId;
        private String  question;
        private String  color;
        private int count;
        private int orders;
        private int totals;
        private String percent;
    
public void reset(){
        this.setQuestionId(0);
        this.setQuestion("");
        this.setCount(0);
        this.setOrders(0);
}
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getServeyId() {
        return serveyId;
    }

    public void setServeyId(int serveyId) {
        this.serveyId = serveyId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public int getOrders() {
        return orders;
    }

    public void setOrders(int orders) {
        this.orders = orders;
    }

    public int getTotals() {
        return totals;
    }

    public void setTotals(int totals) {
        this.totals = totals;
    }

   
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
