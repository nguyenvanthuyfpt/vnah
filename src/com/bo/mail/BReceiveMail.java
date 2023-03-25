package com.bo.mail;


import com.dao.mail.DReceiveMail;

import com.exp.EException;

import com.form.FBeans;
import com.form.mail.FMail;

import com.inf.mail.IKeyMail;

import com.sun.mail.imap.protocol.FLAGS;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;


public class BReceiveMail
{
    public void filterSpam(Folder folder,long meId) throws  EException
    {
            try{      
              if (folder == null) throw new Exception("No default folder");
                  folder = folder.getFolder(IKeyMail.FOLDER_INBOX);
                  if(folder!=null && !folder.exists()){
                      folder.create(Folder.HOLDS_MESSAGES);
                  }
                  folder.open(Folder.READ_WRITE);
                  new DReceiveMail().filterSpam(folder,meId);
                  folder.close(true);
              }catch (Exception ex){
              ex.printStackTrace();
              }
    }
    public FBeans search(FMail bean,Folder folder) throws  EException
    {
            FBeans beans =new FBeans();
            try{      
              if (folder == null) throw new Exception("No default folder");
              folder = folder.getFolder(bean.getFolderName());
              if(folder!=null && !folder.exists()){
                  folder.create(Folder.HOLDS_MESSAGES);
              }
              folder.open(Folder.READ_ONLY);
              beans=new DReceiveMail().search(folder,bean);
              folder.close(true);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
      return beans;
    }
    
    
    public FBeans getINBOX(FMail bean,Folder folder) throws  EException
    {
            FBeans beans =new FBeans();
            try{      
              if (folder == null) throw new Exception("No default folder");
              folder = folder.getFolder(bean.getFolderName());
              if(folder!=null && !folder.exists()){
                  folder.create(Folder.HOLDS_MESSAGES);
              }
              folder.open(Folder.READ_WRITE);
              beans=new DReceiveMail().getINBOX(folder,bean);
              folder.close(true);
              }catch (Exception ex){
                    ex.printStackTrace();
              }
      return beans;
    }

    public FMail getByMailIdINBOX(FMail bean,Folder folder) throws  EException
    {
            try{      
              if(bean.getFolderName()!=null && !bean.getFolderName().equals("")){
                  folder = folder.getStore().getFolder(bean.getFolderName());
              }
              if (folder == null) throw new Exception("No imap " + bean.getFolderName());
              folder.open(Folder.READ_WRITE);
              bean=new DReceiveMail().getByMailIdINBOX(folder,bean);
              folder.close(true);
            }catch (Exception ex){
                ex.printStackTrace();
//                //.println("Have error in getByMailIdINBOX------------------------------------------------------------------");
            }
      return bean;
    }
    
    public boolean setFlagged(Folder folder,int indexId,String folderName,int flagged) throws Exception {
        boolean result =false;
        try  {
            folder = folder.getFolder(folderName);
            folder.open(Folder.READ_WRITE);    
            Message msgs =folder.getMessage(indexId);
            msgs.setFlag(FLAGS.Flag.FLAGGED,flagged==1?true:false);
            folder.close(true);
            result=true;
        } catch (Exception ex)  {
            ex.printStackTrace();
        } finally  {
        }
        return result;
    }
   
    public String[] getFromByIds(Folder folder,int[] ids,String folderName) throws Exception {
        Message[] msgs=null;
        String[] mailFrom=null;
        try  {
            folder=folder.getFolder(folderName);
            folder.open(Folder.READ_ONLY);    
            msgs=folder.getMessages(ids);
            mailFrom=new String[msgs.length];
            for(int i=0;i<msgs.length;i++){
                mailFrom[i]=((InternetAddress)msgs[i].getFrom()[0]).getAddress();
            }
            folder.close(true);
        } catch (Exception ex)  {
            ex.printStackTrace();
        } finally  {
        }
        return mailFrom;
    }
    
    
    public boolean storeSpam(int[] ids,Folder folder,String folderName) throws Exception {
        boolean result =false;
        try  {
            folder=folder.getFolder(folderName);
            folder.open(Folder.READ_WRITE);    
            Message[] msgs=folder.getMessages(ids);
            
            Folder f=folder.getStore().getFolder(IKeyMail.FOLDER_SPAM);
            if(!f.exists()) f.create(Folder.HOLDS_MESSAGES);
            f.appendMessages(msgs);
            for(int i=0;i<msgs.length;i++){
                msgs[i].setFlag(FLAGS.Flag.DELETED, true);
            }
            folder.close(true);
            result=true;
        } catch (Exception ex)  {
            ex.printStackTrace();
        } finally  {
        }
        return result;
    }
    
}
