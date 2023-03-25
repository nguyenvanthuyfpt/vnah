package com.dao.mail;


import com.exp.EException;

import com.form.mail.FMail;

import com.inf.IKey;

import com.sun.mail.imap.protocol.FLAGS;

import java.io.UnsupportedEncodingException;

import java.util.Date;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;


public class DSendMail extends DSqlMail{
/**
 * Write by duvdv
 * Date :12.12.2008
 * Funtion : send mail,addnew in to database
 * ****/
 
    public boolean delete(Folder folder,FMail bean)   throws MessagingException,EException 
    {
      boolean result=false;
      try
      {
               Message[]  msgs =null;
               if(bean.getMailIds()!=null && bean.getMailIds().length>0){
                   msgs=folder.getMessages(bean.getMailIds());//(1bean.getMailIds());
               }else if(bean.getMailId()>0){
                   msgs=folder.getMessages(bean.getMailId(),bean.getMailId());//(1bean.getMailIds());
               }
               if(msgs.length>0){
               
               if(bean.getFolderName()!=null && !bean.getFolderName().equals(IKey.FOLDER_TRASH)){
                    Folder f = folder.getStore().getFolder(IKey.FOLDER_TRASH);
                    if (!f.exists()){
                         f.create(Folder.HOLDS_MESSAGES);
                    }
                   f.open(Folder.READ_WRITE);
                   folder.copyMessages(msgs,f);
                   //f.close(true);
               }
               
               for(int i=0;i<msgs.length;i++){
                   msgs[i].setFlag(FLAGS.Flag.DELETED, true);
               }
               }
          result=true;
      }catch (Exception ex) {
        ex.printStackTrace();
        
      }
      return result;
    }


        public boolean sendMail(MimeMessage message,FMail bean,String[] attachments,String[] files,Folder folder) throws MessagingException,EException  {
            boolean result=false;
                try{
                message.setSubject(bean.getSubject(), "UTF-8");
                message.setContent(bean.getContent(),"text/html; charset=UTF-8");
                message.setFlag(FLAGS.Flag.FLAGGED,bean.getFlagged()==1?true:false);
                 if(bean.getTo()!=null && !bean.getTo().equals("")){
                    if(bean.getTo().indexOf(";",bean.getTo().length()-1)>=0){
                        bean.setTo(bean.getTo().substring(0,bean.getTo().length()-1));
                    }
                    String toUtf8 =bean.getTo().replaceAll(";",",");
                    String[] scrUsers=toUtf8.split(",");
                    for(int i=0;i<scrUsers.length;i++){
                        if(scrUsers[i]!=null){
                            String dataRoot=scrUsers[i].replaceAll("\"","");
                            String fullName="";
                            String userName="";
                            if(dataRoot.indexOf("<")>=0 && dataRoot.indexOf(">")>=0){
                                fullName=dataRoot.substring(0,dataRoot.indexOf("<"));
                                userName=dataRoot.substring(dataRoot.indexOf("<")+1,dataRoot.indexOf(">"));
                            }
                            if(!userName.equals("")){
                                InternetAddress textUtf8=new InternetAddress(userName,fullName,"UTF-8");
                                message.addRecipient(RecipientType.TO,textUtf8);
                            }else{
                                InternetAddress textUtf8=new InternetAddress(dataRoot,"","UTF-8");
                                message.addRecipient(RecipientType.TO,textUtf8);
                            }
                        }
                    }
                }
                if(bean.getCc()!=null && !bean.getCc().equals("")){
                    if(bean.getCc().indexOf(";",bean.getCc().length()-1)>=0){
                        bean.setCc(bean.getCc().substring(0,bean.getCc().length()-1));
                    }
                    String ccUtf8 =bean.getCc().replaceAll(";",",");
                    String[] scrUsers=ccUtf8.split(",");
                    for(int i=0;i<scrUsers.length;i++){
                        if(scrUsers[i]!=null){
                            String dataRoot=scrUsers[i].replaceAll("\"","");
                            String fullName="";
                            String userName="";
                            if(dataRoot.indexOf("<")>=0 && dataRoot.indexOf(">")>=0){
                                fullName=dataRoot.substring(0,dataRoot.indexOf("<"));
                                userName=dataRoot.substring(dataRoot.indexOf("<")+1,dataRoot.indexOf(">"));
                            }
                            if(!userName.equals("")){
                                InternetAddress textUtf8=new InternetAddress(userName,fullName,"UTF-8");
                                message.addRecipient(RecipientType.TO,textUtf8);
                            }else{
                                InternetAddress textUtf8=new InternetAddress(dataRoot,"","UTF-8");
                                message.addRecipient(RecipientType.CC,textUtf8);
                            }
                        }                        
                    }
                }
                    message.addFrom(new InternetAddress[] { new InternetAddress(bean.getUserMail(),bean.me.getFullName(),"UTF-8") });//bo tttt
                    message.setSentDate(new Date());
                    if(attachments!=null && attachments.length>0){
                           MimeBodyPart messageBodyPart = new MimeBodyPart();
                           messageBodyPart.setContent(bean.getContent(),"text/html; charset=UTF-8");
                           MimeMultipart multipart = new MimeMultipart();
                           multipart.addBodyPart(messageBodyPart);
                           addAtachments(attachments, multipart,files);
                           message.setContent(multipart);
                    }
//                   message.reply(true);
                   Transport.send(message);
                   message.setFlag(Flags.Flag.SEEN,true);
                   copyToFolder(folder,message,IKey.FOLDER_SENT);
                   result=true;
                  
                }catch (Exception ex) {
                  ex.printStackTrace();
                }
            return result;
            }
    
    public boolean setStoreTemp(MimeMessage message,FMail bean,String[] attachments,String[] files,Folder folder) throws MessagingException,EException  {
                boolean result=false;
                    try{
                        message.setSubject(bean.getSubject(), "UTF-8");
                        message.setContent(bean.getContent(),"text/html; charset=UTF-8");
                        if(bean.getTo()!=null && !bean.getTo().equals("")){
                            if(bean.getTo().indexOf(";",bean.getTo().length()-1)>=0){
                                bean.setTo(bean.getTo().substring(0,bean.getTo().length()-1));
                            }
                            String toUtf8 =bean.getTo().replaceAll(";",",");
                            String[] scrUsers=toUtf8.split(",");
                            for(int i=0;i<scrUsers.length;i++){
                                if(scrUsers[i]!=null){
                                    String dataRoot=scrUsers[i].replaceAll("\"","");
                                    String fullName="";
                                    String userName="";
                                    if(dataRoot.indexOf("<")>=0 && dataRoot.indexOf(">")>=0){
                                        fullName=dataRoot.substring(0,dataRoot.indexOf("<"));
                                        userName=dataRoot.substring(dataRoot.indexOf("<")+1,dataRoot.indexOf(">"));
                                    }
                                    if(!userName.equals("")){
                                        InternetAddress textUtf8=new InternetAddress(userName,fullName,"UTF-8");
                                        message.addRecipient(RecipientType.TO,textUtf8);
                                    }else{
                                        InternetAddress textUtf8=new InternetAddress(dataRoot,"","UTF-8");
                                        message.addRecipient(RecipientType.TO,textUtf8);
                                    }
                                }
                            }
                        }
                        if(bean.getCc()!=null && !bean.getCc().equals("")){
                            if(bean.getCc().indexOf(";",bean.getCc().length()-1)>=0){
                                bean.setCc(bean.getCc().substring(0,bean.getCc().length()-1));
                            }
                            String ccUtf8 =bean.getCc().replaceAll(";",",");
                            String[] scrUsers=ccUtf8.split(",");
                            for(int i=0;i<scrUsers.length;i++){
                                if(scrUsers[i]!=null){
                                    String dataRoot=scrUsers[i].replaceAll("\"","");
                                    String fullName="";
                                    String userName="";
                                    if(dataRoot.indexOf("<")>=0 && dataRoot.indexOf(">")>=0){
                                        fullName=dataRoot.substring(0,dataRoot.indexOf("<"));
                                        userName=dataRoot.substring(dataRoot.indexOf("<")+1,dataRoot.indexOf(">"));
                                    }
                                    if(!userName.equals("")){
                                        InternetAddress textUtf8=new InternetAddress(userName,fullName,"UTF-8");
                                        message.addRecipient(RecipientType.TO,textUtf8);
                                    }else{
                                        InternetAddress textUtf8=new InternetAddress(dataRoot,"","UTF-8");
                                        message.addRecipient(RecipientType.CC,textUtf8);
                                    }
                                }                        
                            }
                        }
                        
                        message.addFrom(new InternetAddress[] { new InternetAddress(bean.getUserMail(),bean.me.getFullName(),"UTF-8") });//bo tttt
                        //message.setSentDate(bean.getCurrentDate());
                        message.setSentDate(new Date());
                        if(attachments!=null && attachments.length>0){
                               MimeBodyPart messageBodyPart = new MimeBodyPart();
                               //String contentMuti=MimeUtility.decodeText(bean.getContent());
                               messageBodyPart.setContent(bean.getContent(),"text/html; charset=UTF-8");
                               MimeMultipart multipart = new MimeMultipart();
                               multipart.addBodyPart(messageBodyPart);
                               addAtachments(attachments, multipart,files);
                               message.setContent(multipart);
                        }
                       message.setFlag(Flags.Flag.SEEN,true);
                       copyToFolder(folder,message,IKey.FOLDER_DRAFT);
                       result=true;
                      
                    }catch (Exception ex) {
                      ex.printStackTrace();
                    }
                return result;
                }
        

    public boolean copyToFolder(Folder folder,MimeMessage[] message,String folderName) throws EException{
           boolean result=false;
           try  {
               Folder f =folder.getFolder(folderName);
               if (!f.exists()){
                    f.create(Folder.HOLDS_MESSAGES);
               }
               f.open(Folder.READ_WRITE);
               f.appendMessages(message);
               result = true;
           } catch (MessagingException ex)  {
               ex.printStackTrace();
           } 
        return result;
       }
    public boolean copyToFolder(Folder folder,MimeMessage message,String folderName) throws EException{
        return copyToFolder(folder,new MimeMessage[]{message},folderName);
    }
   
    private void addAtachments(String[] attachments, Multipart multipart,String[] files) throws MessagingException, AddressException,UnsupportedEncodingException,Exception
    {
        for(int i = 0; i<= attachments.length -1; i++)
        {
            String filepath = attachments[i];
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filepath);
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            String sendname = MimeUtility.encodeText(files[i],"UTF8", "B");
            attachmentBodyPart.setFileName(sendname);
            multipart.addBodyPart(attachmentBodyPart);
        }
    }
 
}