package com.form.disability;

import com.form.FSeed;


public class FRank extends FSeed {

    private int id;
    private int idNkt;
    private int userId;
    private String fullName;
    private String dateCreate;
    private String reson;
    private String danhgiaIds;
    private String tochucKhac;
    private int[] rankIds;
    
    public void reset(){
        this.id=0;
        this.fullName="";
        this.dateCreate="";
        this.reson="";
        this.danhgiaIds="";
        this.tochucKhac="";
        this.rankIds=null;
    }

    public int getIdNkt() {
        return idNkt;
    }

    public void setIdNkt(int idNkt) {
        this.idNkt = idNkt;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDanhgiaIds() {
        return danhgiaIds;
    }

    public void setDanhgiaIds(String danhgiaIds) {
        if(danhgiaIds.length()>3){
             this.danhgiaIds=danhgiaIds.replaceAll("#0#","#");
        }else{
            this.danhgiaIds="";
        }
    }
    public int[] getRankIds() {
        
        return rankIds;
    }

    public void setRankIds(int[] rankIds) {
    
        if(rankIds!=null && rankIds.length>0){
            String danhgiaIds="#";
            for(int i=0;i<rankIds.length;i++){
                danhgiaIds+=rankIds[i] + "#";
            }
            setDanhgiaIds(danhgiaIds);
        }
        this.rankIds = rankIds;
    }

    public void setTochucKhac(String tochucKhac) {
        this.tochucKhac = tochucKhac;
    }

    public String getTochucKhac() {
        return tochucKhac;
    }
}
