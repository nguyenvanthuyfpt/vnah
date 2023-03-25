package com.dao.mail;


import com.exp.EException;

import com.form.mail.FMail;

import java.security.Security;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;


public class DLoginMail{
  /**
   * source :http://stackoverflow.com/questions/61176/getting-mail-from-gmail-into-java-application-using-imap
   * */
    public Folder loginEmail(FMail bean,Folder folder) throws  EException {
        try{
              if(bean.getSercure()==0){
                      Properties props=new Properties();
                      props.setProperty("mail.store.protocol", "imap");
                      //props.setProperty("mail.imap.timeout","20");
                      //props.setProperty("mail.imap.connectiontimeout","20");
                      Session session =Session.getInstance(props);
                      session.setDebug(false);
                      Store store = session.getStore("imap");
                      store.close();
                      store.connect(bean.getHostMail(),bean.getUserMail(),bean.getPassMail());
                      folder=store.getDefaultFolder();

              }else{
                      Properties props = new Properties();
                      System.setProperty("java.protocol.handler.pkgs","com.sun.net.ssl.internal.www.protocol");
                      Security.addProvider( new com.sun.net.ssl.internal.ssl.Provider());
                      props.setProperty("mail.store.protocol", "imap");
                      props.setProperty( "mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                      props.setProperty( "mail.imap.socketFactory.fallback", "false");
                      props.setProperty( "mail.imap.port", "993");
                      props.setProperty( "mail.imap.socketFactory.port", "993");
                      props.setProperty("mail.imap.connectiontimeout", "5000");
                      props.setProperty("mail.imap.timeout", "5000");
                      Session session = Session.getInstance(props);
                      session.setDebug(false);
                      Store store = session.getStore("imap");
                      store.connect(bean.getHostMail(),bean.getUserMail(),bean.getPassMail());
                      folder=store.getDefaultFolder();
              }
        }catch (Exception ex){
                ////.println(ex);
                ex.printStackTrace();
        }
        return folder;
    }
   }
