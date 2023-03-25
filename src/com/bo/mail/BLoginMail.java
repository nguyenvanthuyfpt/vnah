package com.bo.mail;


import com.dao.mail.DLoginMail;

import com.exp.EException;

import com.form.mail.FMail;

import javax.mail.Folder;

public class BLoginMail
{
    public Folder loginMail(FMail bean,Folder folder) throws  EException
    {
      try {
           folder = new DLoginMail().loginEmail(bean,folder);
       }catch (EException ex) {
           ex.printStackTrace();
       }
       return folder;
    }  

}
