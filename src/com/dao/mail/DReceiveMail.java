package com.dao.mail;


import com.bo.mail.mailFilter.BMailFilter;

import com.exp.EException;

import com.form.FBeans;
import com.form.mail.FMail;
import com.form.mail.mailFilter.FMailFilter;

import com.inf.IKey;
import com.inf.mail.IKeyMail;

import com.lib.AppConfigs;

import com.sun.mail.imap.protocol.FLAGS;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;

import java.util.Date;

import javax.mail.Address;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.search.AndTerm;
import javax.mail.search.BodyTerm;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.FlagTerm;
import javax.mail.search.FromStringTerm;
import javax.mail.search.OrTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import javax.mail.search.SubjectTerm;


public class DReceiveMail extends DSqlMail{
    
    public void filterSpam(Folder folder,long meId) throws  EException 
    {
            Message[] msgs=null;
            try{
                msgs = folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN),false));
                if(msgs.length>0){
                FBeans beansFilters=new BMailFilter().getAll(meId);
                for (int i =0;i<msgs.length;i++){
                   FMail beanMail=getFromAddRess(msgs[i],i);
                    for (int j =0;j<beansFilters.size();j++){
                            FMailFilter beanFilter=(FMailFilter)beansFilters.get(j);
                            if(beanMail.getFrom().indexOf(beanFilter.getFrom())>=0){
                                if(beanFilter.getLikeFrom()==0){
                                    Folder f=folder.getStore().getFolder(IKeyMail.FOLDER_SPAM);
                                    f.open(Folder.READ_WRITE);
                                    folder.copyMessages(new Message[]{msgs[i]},f);
                                    f.close(true);
                               }
                               msgs[i].setFlag(FLAGS.Flag.DELETED,true);
                            }
                    }
                }
                }
            }catch (Exception ex){
                    ex.printStackTrace();
            }
    }
    
    public FBeans getINBOX(Folder folder,FMail bean) throws  EException 
    {
            FBeans beans =new FBeans();
            Message[] msgs=null;
            try{
                    int starPaging=0;
                    int endPaging=0;int finish=0;
                    if(bean.getReaded()==0){
                            if(bean.getPageIndex()==0){
                                starPaging=1;
                                endPaging=AppConfigs.APP_ROWS_VIEW;
                            }else{
                                endPaging=AppConfigs.APP_ROWS_VIEW*bean.getPageIndex();
                                starPaging=endPaging-AppConfigs.APP_ROWS_VIEW+1;
                            }
                            beans.setTotalRows(folder.getMessageCount());
                            beans.setPageIndex(bean.getPageIndex());
                            if(endPaging>beans.getTotalRows()){
                                endPaging=beans.getTotalRows(); 
                            }
                            int start = beans.getTotalRows()-endPaging+1;
                            finish = beans.getTotalRows()-starPaging+1;
                            msgs = folder.getMessages(start,finish);
                    }if(bean.getReaded()==1){//Chua doc
                        msgs=folder.search(new FlagTerm(new Flags(Flags.Flag.SEEN),false));
                        beans.setTotalRows(msgs.length);
                    }
                    for (int i = msgs.length;i>0;i--){
                       beans.add(getAllMessages(msgs[i-1],i,bean.getUserMail()));
                    }
            }catch (Exception ex){
                    ex.printStackTrace();
            }
    return beans;
    }


    
   
    public FBeans search(Folder folder,FMail bean) throws  EException {
            FBeans beans =new FBeans();
            try{
                SearchTerm term =null;
                Message[] msgs=null;
                      if (bean.getSearchContent() != null){
                            term = new SubjectTerm(bean.getSearchContent());
                            FromStringTerm fromTerm = new FromStringTerm(bean.getSearchContent());
                            term = new OrTerm(term, fromTerm);
                            term=conferDate(bean.getSearchContent(),term,bean);
                            BodyTerm contentTerm=new BodyTerm(bean.getSearchContent());
                            term = new OrTerm(term, contentTerm);
                       }
                    msgs = folder.search(term);
                    beans.setTotalRows(msgs.length);
                    for (int i = msgs.length;i>0;i--){
                           beans.add(getAllMessages(msgs[i-1],i,bean.getUserMail()));
                    }
            }catch (Exception ex){
                    ex.printStackTrace();
            }
    return beans;
    }
    
    
 
    /**
       * "printMessage()" method to print a message.
       */
     private SearchTerm conferDate(String dateString,SearchTerm term,FMail bean){
         
                if(bean.getSearchContent()!=null || !bean.getSearchContent().equals("")){
                    if(bean.isDate(bean.getSearchContent())){
                        ReceivedDateTerm dateTerm = new ReceivedDateTerm(ComparisonTerm.EQ,bean.stringToDate(bean.getSearchContent()));
                        if (term != null) {
                            term = new OrTerm(term, dateTerm);
                        } else {
                            term = dateTerm;
                        }
                    }else{
                        try{
                        String toDate=bean.dateToString(bean.getCurrentDate());
                                int inputValue=Integer.parseInt(dateString);
                                if(inputValue>0 && inputValue<=12){
                                    dateString="01/"+ inputValue + "/" + bean.getYear(bean.getCurrentSqlDate());
                                    int monthNumber=bean.getDaysOfMonth(bean.stringToSqlDate(dateString));
                                    toDate=monthNumber +"/"+ inputValue + "/" + bean.getYear(bean.getCurrentSqlDate());
                                }else{
                                    dateString="01/01/"+ inputValue;
                                    int daysNumber=bean.getDaysOfMonth(bean.stringToSqlDate("01/12/"+inputValue));
                                    toDate=daysNumber+"/12/" + inputValue;
                                }
                                ReceivedDateTerm dateFromTerm = new ReceivedDateTerm(ComparisonTerm.GT,bean.stringToDate(dateString));
                                ReceivedDateTerm dateToTerm = new ReceivedDateTerm(ComparisonTerm.LE,bean.stringToDate(toDate));
                                term = new OrTerm(term, dateFromTerm);
                                term = new AndTerm(term, dateToTerm);
                        }catch (Exception ex){
                                ex.printStackTrace();
                        }
                    }
                }
        return term;
     }
       
     private String decodeContent(String message, String contenttype){
         if(contenttype.indexOf("text/plain;")>=0){
             message = message.replaceAll("<","&lt;").replaceAll(">","&gt;");
             message=message.replaceAll(new String(new byte[]{13,10}),"<br/>");
         }else if (contenttype.indexOf("text/html")>=0){
             String buffer = message.toLowerCase();
             String mem = "";
             int s=buffer.indexOf("<script");
             int e=0;
             while(s>=0){
                 mem += message.substring(0,s);
                 e=buffer.indexOf("</script>");
                 if(e<0){
                    message = "";break;
                 }else{
                    buffer = buffer.substring(e+9,buffer.length());
                    message= message.substring(e+9,message.length());
                    s=buffer.indexOf("<script");
                 }
             }
             message = mem + message;     
         }
//         message=message.replaceAll("<style>","<style1>");
         return message;
     }
     
     
     public FMail getMessages(MimeMessage message,String userEmail)
     {
     FMail bean =new FMail();
       try
       {
         String from=((InternetAddress)message.getFrom()[0]).getPersonal();
         if (from!=null){
              from+=" <"+ ((InternetAddress)message.getFrom()[0]).getAddress() + "> ";
         }else{
             from=((InternetAddress)message.getFrom()[0]).getAddress();
         }
         bean.setFrom(from);
         String contentType=  message.getContentType();
         String msgcontent =message.getContent()==null?"":message.getContent()+"";
         if(message.getSentDate()!=null){
            bean.setDateSend(getDateToString(message.getSentDate()));
         }
         Address[] ccs=message.getRecipients(Message.RecipientType.CC);
         Address[] tos=message.getRecipients(Message.RecipientType.TO);
         bean.setCcs(ccs);
         bean.setTos(tos);
         bean.setSubject(message.getSubject()==null?"":bean.ncrToString(message.getSubject()));
         bean.setReaded(message.isSet(Flags.Flag.SEEN)?1:0);
         int sizeMail=message.getSize();
         bean.setFileSize(sizeMail<1024?sizeMail+" KB":sizeMail/1024+" B");
         Part messagePart=message;
                   Object content = messagePart.getContent();
                   
                   if (content instanceof MimeMultipart) {
                     deleteDir(new File(AppConfigs.APP_SYSTEM_PATH + AppConfigs.FILE_MAIL_UPLOAD + AppConfigs.SYSTEM_FILE_SCHIP + userEmail));
                     
                     int l=((MimeMultipart)content).getCount();
                     int fileIndex=0;
                     for (int j = 0; j < l; ++j) {
                         String disposition = ((MimeMultipart)content).getBodyPart(j).getDisposition();
                         if(((MimeMultipart)content).getBodyPart(j).getFileName()!=null){
                         
                         if(disposition!=null && (disposition.equalsIgnoreCase(Part.ATTACHMENT) || disposition.equalsIgnoreCase(Part.INLINE))){
                             ++fileIndex;
                             bean.getAllFiles().add(handlePart(((MimeMultipart)content).getBodyPart(j), userEmail,fileIndex));
                         }
                         }
                     }
                     msgcontent="";
                     for (int j = 0; j < l; ++j) {
                         messagePart = ((Multipart)content).getBodyPart(j);
                         String contenTem=getText(messagePart);
                         if(contenTem!=null && !contenTem.equals("")){
                             msgcontent = getText(messagePart);
                         }
                         if(!msgcontent.equals("")){
                         contentType = messagePart.getContentType();
                         }
                     }
                   }
           bean.setContent(decodeContent(msgcontent,contentType.toLowerCase())); 
          
       }catch (Exception ex) {
           bean.setContent("");
         ex.printStackTrace();
       }
       return bean;
     }
   
//    private boolean textIsHtml = false;

    private String getText(Part p) throws   MessagingException, IOException {
            if (p.isMimeType("text/*")) {
                String s ="";
                try  {
                    if(p!=null && p.getContent()!=null){
                        s=(String)p.getContent();
//                        //.println(s);
                        int j=s.indexOf("</style>")+8;
                        
                        for(int i=s.indexOf("<style");i<s.indexOf("</head>");i=j){
                            if((s.indexOf("<style")<0)||(s.indexOf("</style>")<0)){
                                break;
                            }
                            s=s.substring(0,s.indexOf("<style"))+s.substring(s.indexOf("</style>")+8);
                            j=s.indexOf("</style>")+8;
                        }
                        
                       
                    }    
                } catch (Exception ex)  {
                }
                
                return s;
            }

            if (p.isMimeType("multipart/alternative")) {
                // prefer html text over plain text
                Multipart mp = (Multipart)p.getContent();
                String text = null;
                for (int i = 0; i < mp.getCount(); i++) {
                    Part bp = mp.getBodyPart(i);
                    if (bp.isMimeType("text/plain")) {
                        if (text == null)
                            text = getText(bp);
                        continue;
                    } else if (bp.isMimeType("text/html")) {
                        String s = getText(bp);
                        if (s != null)
                            return s;
                    } else {
                        return getText(bp);
                    }
                }
                return text;
            } else if (p.isMimeType("multipart/*")) {
                Multipart mp = (Multipart)p.getContent();
                for (int i = 0; i < mp.getCount(); i++) {
                    String s = getText(mp.getBodyPart(i));
                    if (s != null)
                        return s;
                }
            } 

            return null;
        }

    public FMail getAllMessages(Message message,int i,String userMail)
    {
    FMail bean =new FMail();
      try
      {
        String from=((InternetAddress)message.getFrom()[0]).getAddress();
        String personal=((InternetAddress)message.getFrom()[0]).getPersonal();
        if (personal==null){
            personal=((InternetAddress)message.getFrom()[0]).getAddress();
            personal = personal.substring(0,personal.indexOf("@"));
        }else if(!personal.equals("") && personal.indexOf("@")>=0){
            personal=personal.substring(0,personal.indexOf("@"));
        }
       bean.setPersonal(personal);
       bean.setFrom(from);
       String daySend=bean.dateToString(bean.getCurrentSqlDate());
       if(message.getSentDate()!=null){
            daySend=bean.dateToString(message.getSentDate());
       }
       String cusrentDate=bean.dateToString(bean.getCurrentDate());
       if(daySend.equals(cusrentDate)){
           String timeSend=getDateToString(message.getSentDate()!=null?message.getSentDate():bean.getCurrentSqlDate());
           timeSend=timeSend.substring(timeSend.indexOf(" "),timeSend.length());
           bean.setDateSend(timeSend);
       }else{
           bean.setDateSend(bean.dateToString(message.getSentDate()!=null?message.getSentDate():bean.getCurrentSqlDate()));   
       }
          
        Address[] ccs=message.getRecipients(Message.RecipientType.CC);
        Address[] tos=message.getRecipients(Message.RecipientType.TO);
        
        InternetAddress[] tosTem=(InternetAddress[])tos;
        if(tosTem!=null && tosTem.length>0){
            String personalTo=tosTem[0].getPersonal();
            if (personalTo==null){
                personalTo=tosTem[0].getAddress();
                personalTo = personalTo.substring(0,personalTo.indexOf("@"));
            }else if(!personalTo.equals("") && personalTo.indexOf("@")>=0){
                personalTo=personalTo.substring(0,personalTo.indexOf("@"));
            }
            bean.setTo(personalTo);//Nguoi nhan
        }
        bean.setCcs(ccs);
        bean.setTos(tos);
        
        //CHECK ME IS TO ?
         if(tos!=null){
            InternetAddress[] tosCheck=(InternetAddress[])bean.getTos();
            for(int k=0;k<tosCheck.length;k++){
//                //.println(tosCheck[k].getAddress());
                if(tosCheck[k].getAddress().indexOf(userMail)>=0) bean.setCheckIsTo(1); break;
            }
         }
         
        bean.setMailId(message.getMessageNumber());
        bean.setSubject("");
        if(message.getSubject()!=null && !message.getSubject().equals("") && message.getSubject().length()>50){
            bean.setSubject(message.getSubject().substring(0,50)+"...");
        }else{
            bean.setSubject(message.getSubject());
        }
        bean.setReaded(message.isSet(Flags.Flag.SEEN)?1:0);
        bean.setFlagged(message.isSet(Flags.Flag.FLAGGED)?1:0);
        int sizeMail=message.getSize();
        bean.setFileSize(sizeMail<1024?sizeMail+" KB":sizeMail/1024+" B");
        bean.setCheckHaveFile(hasAttachments(message)?1:0);
          
      }catch (Exception ex) {
        ex.printStackTrace();
      }
      return bean;
    }
    public FMail getFromAddRess(Message message,int i)
    {
      FMail bean =new FMail();
      try
      {
        String from=((InternetAddress)message.getFrom()[0]).getAddress();
        String personal=((InternetAddress)message.getFrom()[0]).getPersonal();
        if (personal==null){
            personal=((InternetAddress)message.getFrom()[0]).getAddress();
            personal = personal.substring(0,personal.indexOf("@"));
        }else if(!personal.equals("") && personal.indexOf("@")>=0){
            personal=personal.substring(0,personal.indexOf("@"));
        }
       bean.setPersonal(personal);
       bean.setFrom(from);
       bean.setReaded(message.isSet(Flags.Flag.SEEN)?1:0);
      }catch (Exception ex) {
        ex.printStackTrace();
      }
      return bean;
    }
    public boolean hasAttachments(Message message) throws java.io.IOException,MessagingException {
                 boolean hasAttachments = false;
                 if (message.isMimeType("multipart/mixed")) {
                     Multipart mp = (Multipart)message.getContent();
                     if (mp.getCount() > 1)
                         hasAttachments = true;
                 }
                 return hasAttachments;
    }

    public String getDateToString(Date sentdate) throws Exception
{
        SimpleDateFormat format = new SimpleDateFormat(AppConfigs.APP_DATE_TIME);
        return format.format(sentdate);
      }

    public FMail handlePart(Part part,String userEmail,int fileIndexId) throws MessagingException, IOException,Exception
    {
      FMail beanFile =new FMail();
                        String fileRoot=part.getFileName();
                        String fileName=decodeName(fileRoot);
                        FMail bean =new FMail();
                        fileName= bean.ncrToString(fileName);
                        beanFile.setFileName(fileName);
                        beanFile.setFileId(fileIndexId);
                        saveFile(fileName, part.getInputStream(),userEmail,fileIndexId);
    return beanFile;
    }
    
 
    
    public static void deleteDir(File dir) {
            if (dir.isDirectory()) {
                String[] children = dir.list();
                for (int i=0; i<children.length; i++) {
                    new File(dir, children[i]).delete();
                    deleteDir(new File(dir, children[i]));
                }
            }
    }
    
    public void saveFile(String filename, InputStream input,String userEmail,int fileIndexId) throws Exception {
    
    try{
  
    String pathName=AppConfigs.APP_SYSTEM_PATH + AppConfigs.FILE_MAIL_UPLOAD  + AppConfigs.SYSTEM_FILE_SCHIP + userEmail ;
    (new File(pathName)).mkdirs();
    filename =  pathName+ AppConfigs.SYSTEM_FILE_SCHIP + fileIndexId ;
    File file = new File(filename);
    
    for (int i=0; file.exists(); i++) {
        file = new File(filename+i);
    }
    FileOutputStream fos = new FileOutputStream(file);
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    BufferedInputStream bis = new BufferedInputStream(input);
    int aByte;
    while ((aByte = bis.read()) != -1) {
        bos.write(aByte);
    }
    bos.flush();
    bos.close();
    }
    catch(IOException exp){
    ////.println("IOException:" + exp);
    }
    } 
      public FMail getByMailIdINBOX(Folder folder,FMail bean) throws  EException 
            {
              FMail beanTem = new FMail();
              try{
                  MimeMessage msgs =(MimeMessage)folder.getMessage(bean.getMailId());
                  beanTem=getMessages(msgs,bean.getUserMail());
              } catch (Exception ex) {
                ex.printStackTrace();
              }
           return beanTem;
           }
 
    public String decodeName( String name ) throws Exception {
            if(name == null || name.length() == 0){
                    return "unknown";
            }
            return MimeUtility.decodeText(name);
    }


    public boolean removeTo(Folder folder,FMail bean,String folderName) throws Exception {
    boolean result=false;
           folder = folder.getFolder(folderName);
           if (!folder.exists()) {
               System.exit(0);
           }
           folder.open(Folder.READ_WRITE);
           Message[] msgs =folder.getMessages(bean.getMailIds());
           Folder f=folder.getStore().getFolder(IKey.FOLDER_INBOX);
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
    
    
    
    
    
    
    
    
    

}
