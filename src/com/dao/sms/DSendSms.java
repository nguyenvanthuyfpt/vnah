package com.dao.sms;


import com.dao.DSqlAdmin;

import com.exp.EException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.net.URL;
import java.net.URLEncoder;

import sun.net.www.protocol.http.HttpURLConnection;


public class DSendSms  extends DSqlAdmin{
    public boolean sendSms(String content,String to) throws  EException{
    to="84"+to.substring(1,to.length());
        String msg="";
        try{
        msg=URLEncoder.encode(content, "UTF-8");
        } catch (UnsupportedEncodingException e) {
//        //.println("Encoding not supported");
        }
        String providerUrl ="http://messenger.vietguys.biz/api/?phone="+to+"&from=8027&sms="+msg+"&account=vietsoftware&code=edxn4";
        try{
        URL url = new URL(providerUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        BufferedReader in1 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        while ((line = in1.readLine()) != null)
        System.out.print(line +"\n");
        } catch (Exception ex)  {
           ex.printStackTrace();
        } finally  {
        }
    return true;
    }
}
