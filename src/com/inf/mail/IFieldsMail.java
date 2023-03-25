package com.inf.mail;

import com.inf.IParams;

public interface IFieldsMail extends IParams {
    
    
//  SET OF FIELDS ON AR TABLES
     public final String AR_ID="ID";
     public final String AR_FULLNAME="FULLNAME";
     public final String AR_EMAIL="EMAIL";
     public final String AR_NICK_YAHOO="NICK_YAHOO";
     public final String AR_NICK_ICQ="NICK_ICQ";
     public final String AR_NICK_SKYPE="NICK_SKYPE";
     public final String AR_ADDRESS="ADDRESS";
     public final String AR_PHONE="PHONE";
     public final String AR_FAX="FAX";
     public final String AR_MOBILE="MOBILE";
     public final String AR_DECSRIPTION="DECSRIPTION";
    public final String AR_CREATE_EMAIL="CREATE_EMAIL";
     public final String[] AR_ALL_FIELDS = {AR_FULLNAME,AR_EMAIL,AR_NICK_YAHOO,AR_NICK_ICQ,AR_NICK_SKYPE,AR_ADDRESS,AR_PHONE,AR_FAX,AR_MOBILE,AR_DECSRIPTION,AR_CREATE_EMAIL}; 



    //  SET OF FIELDS ON AR TABLES MAIL FILTER
        public final String MAIL_FILTER_ID="ID";
        public final String MAIL_FILTER_FROM="FROM_ADDRESS";
        public final String MAIL_FILTER_LIKEFROM="LIKEFROM";
        public final String MAIL_FILTER_SUBJECT="SUBJECT";
        public final String MAIL_FILTER_LIKESUBJECT="LIKESUBJECT";
        public final String MAIL_FILTER_USER_ID="USER_ID";
        public final String[] MAIL_FILTER_ALL_FIELDS = {MAIL_FILTER_FROM,MAIL_FILTER_LIKEFROM,MAIL_FILTER_SUBJECT,MAIL_FILTER_LIKESUBJECT,MAIL_FILTER_USER_ID}; 
}
