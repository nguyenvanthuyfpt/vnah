package com.dao.mail;


import com.exp.EException;

import com.form.admin.mail.FMailAccount;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.ServerSocket;
import java.net.Socket;

import java.security.Security;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;


public class SendMail
 {
     private String userEmail ="";
     private String passEmail ="";
     private String hostEmail ="";
     private String portEmail ="";
     private int sercure =0;
     private boolean sendDocs(String fullName,FMailAccount beanEmail,String Subject,String Content,String recipient,String[] attachments,String[] files) throws MessagingException  {
            boolean result=false;
                 try{
                     userEmail=beanEmail.getUserMail();
                     passEmail=beanEmail.getPassMail();
                     hostEmail=beanEmail.getServerMail();
                     portEmail=beanEmail.getPortMail();
                     sercure=beanEmail.getSercure();
                     MimeMessage message = new MimeMessage(getSession());
                     message.addRecipient(RecipientType.TO, new InternetAddress(recipient));
                     message.addFrom(new InternetAddress[] { new InternetAddress(userEmail,fullName,"UTF-8") });
                     message.setSubject(Subject,"UTF-8");
                     message.setContent(Content, "text/html; charset=UTF-8");
                     if(attachments!=null && attachments.length>0){
                            MimeBodyPart messageBodyPart = new MimeBodyPart();
                            messageBodyPart.setContent(Content,"text/html; charset=UTF-8");
                            Multipart multipart = new MimeMultipart();
                            multipart.addBodyPart(messageBodyPart);
                            addAtachments(attachments, multipart,files);
                            message.setContent(multipart);
                     }
                     Transport.send(message);
                     result=true;
                 }catch (Exception ex) {
                   ex.printStackTrace();
                 }
                 return result;
           }

     private Session getSession() {
         Session session=null;
         if(sercure==0)   {
             Authenticator authenticator = new Authenticator();
             Properties properties = new Properties();
             properties.setProperty("mail.smtp.submitter",userEmail);
             properties.setProperty("mail.smtp.auth", "true");
             properties.setProperty("mail.smtp.host",hostEmail);
             properties.setProperty("mail.smtp.port",portEmail);
             session=Session.getInstance(properties, authenticator);
         }else{    
             Properties props =new Properties();
             Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
             props.setProperty("mail.transport.protocol", "smtp");
             props.setProperty("mail.host",hostEmail);
             props.put("mail.smtp.auth", "true");
             props.put("mail.smtp.port", "465");
             props.put("mail.smtp.socketFactory.port", "465");
             props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
             props.put("mail.smtp.socketFactory.fallback", "false");
             props.setProperty("mail.smtp.quitwait", "false");
             session = Session.getDefaultInstance(props,new javax.mail.Authenticator() 
                     {
                             protected PasswordAuthentication getPasswordAuthentication()
                             { return new PasswordAuthentication(userEmail,passEmail);     }
                     });     
         }
         return session;
     }  
          
     public boolean sendmailFromDoc(String fullName,FMailAccount beanEmail,String Subject,String Content,String recipient,String[] attachments,String[] files) throws EException
     {
      boolean result=false;
          try  {
              result=sendDocs(fullName,beanEmail,Subject,Content,recipient,attachments,files);
          } catch (MessagingException ex)  {
              ex.printStackTrace();
          } 
      return result;
     }
     

     private void addAtachments(String[] attachments, Multipart multipart,String[] files) throws MessagingException, AddressException
     {
         for(int i = 0; i<= attachments.length -1; i++)
         {
             String filename = attachments[i];
             String ten = files[i];
             MimeBodyPart attachmentBodyPart = new MimeBodyPart();
             DataSource source = new FileDataSource(filename);
             attachmentBodyPart.setDataHandler(new DataHandler(source));
             attachmentBodyPart.setFileName(ten);
             multipart.addBodyPart(attachmentBodyPart);
         }
     }
     private class Authenticator extends javax.mail.Authenticator {
             private PasswordAuthentication authentication;

             public Authenticator() {
                     authentication = new PasswordAuthentication(userEmail, passEmail);
             }

             protected PasswordAuthentication getPasswordAuthentication() {
                     return authentication;
             }
     }
 
 
 
 
     public static void main(String argv[]) throws Exception
           {
              String fromclient;
              String toclient;
               
              ServerSocket Server = new ServerSocket (5000);
              
              ////.println ("TCPServer Waiting for client on port 5000");

              while(true) 
              {
                     Socket connected = Server.accept();
                 ////.println( " THE CLIENT"+" "+connected.getInetAddress() +":"+connected.getPort()+" IS CONNECTED ");
                 
                 BufferedReader inFromUser = 
                 new BufferedReader(new InputStreamReader(System.in));    
          
                 BufferedReader inFromClient =
                    new BufferedReader(new InputStreamReader (connected.getInputStream()));
                       
                 PrintWriter outToClient =
                    new PrintWriter(
                       connected.getOutputStream(),true);
                 
                 while ( true )
                 {
                     
                     ////.println("SEND(Type Q or q to Quit):");
                     toclient = inFromUser.readLine();
                     
                     if ( toclient.equals ("q") || toclient.equals("Q") )
                     {
                             outToClient.println(toclient);
                             connected.close();
                             break;
                     }
                     else
                     {
                     outToClient.println(toclient);
                     }
                     
                     fromclient = inFromClient.readLine();
                     
                     if ( fromclient.equals("q") || fromclient.equals("Q") )
                     {
                             connected.close();
                             break;
                     }
                             
                             else
                             {
                              ////.println( "RECIEVED:" + fromclient );
                             } 
                                 
                             }  
                             
               }
           }

 
 
 }
  