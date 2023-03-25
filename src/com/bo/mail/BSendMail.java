package com.bo.mail;


import com.dao.mail.DSendMail;

import com.exp.EException;

import com.form.mail.FMail;

import com.sun.mail.imap.protocol.FLAGS;

import java.security.Security;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;


public class BSendMail
{
    public boolean delete(FMail bean,Folder folder) throws Exception {
    boolean result=false;
           folder = folder.getFolder(bean.getFolderName());
           if (!folder.exists()) {
               System.exit(0);
           }
           folder.open(Folder.READ_WRITE);
        result=new DSendMail().delete(folder,bean);
           folder.close(true);
        return result;
       }
       
    public boolean restoreMail(Folder folder,FMail bean,String folderName) throws Exception {
    boolean result=false;
           folder = folder.getFolder(folderName);
           if (!folder.exists()) {
               System.exit(0);
           }
           folder.open(Folder.READ_WRITE);
           Message[] msgs =folder.getMessages(bean.getMailIds());
           Folder f=folder.getStore().getFolder(bean.getRestoreFolder());
           if (!f.exists()){
                f.create(Folder.HOLDS_MESSAGES);
           }
           f.open(Folder.READ_WRITE);            
           folder.copyMessages(msgs,f);
           f.close(true);
           for(int i=0;i<msgs.length;i++){
               msgs[i].setFlag(FLAGS.Flag.DELETED,true);
           }
           folder.close(true);
           result=true;
        return result;
       }
  public boolean sendMail(FMail bean,String[] attachments,String[] files,Folder folder) throws MessagingException,EException 
  {
      boolean result=false;
      MimeMessage message = new MimeMessage(getSession(bean));
      result = new DSendMail().sendMail(message,bean,attachments,files,folder);   
      return result;
  }
    public boolean setStoreTemp(FMail bean,String[] attachments,String[] files,Folder folder) throws MessagingException,EException 
    {
        boolean result=false;
        MimeMessage message = new MimeMessage(getSession(bean));
        result = new DSendMail().setStoreTemp(message,bean,attachments,files,folder);
        return result;
    }
    
    private String user="";
    private String pass="";
    private Session getSession(FMail bean) {
        Session session=null;
        if(bean.getSercure()==0)   {
            Authenticator authenticator = new Authenticator(bean);
            Properties properties = new Properties();
            properties.setProperty("mail.smtp.submitter", bean.getUserMail());
            properties.setProperty("mail.smtp.auth", "true");
            properties.setProperty("mail.smtp.host",bean.getHostMail());
            properties.setProperty("mail.smtp.port",bean.getPostMail()+"");
            session=Session.getInstance(properties, authenticator);
            
            
        }else{    
            user=bean.getUserMail();
            pass=bean.getPassMail();
            Properties props =new Properties();
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host",bean.getHostMail());
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");
            session = Session.getDefaultInstance(props,new javax.mail.Authenticator() 
                    {
                            protected PasswordAuthentication getPasswordAuthentication()
                            { return new PasswordAuthentication(user,pass);     }
                    });     
        }
        return session;
    }  
   
    
   
    private class Authenticator extends javax.mail.Authenticator {
            private PasswordAuthentication authentication;

            public Authenticator(FMail bean) {
                    String username =bean.getUserMail();
                    String password =bean.getPassMail();
                    authentication = new PasswordAuthentication(username, password);
            }

            protected PasswordAuthentication getPasswordAuthentication() {
                    return authentication;
            }
    }
}
