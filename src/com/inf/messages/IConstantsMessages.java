package com.inf.messages;

public interface IConstantsMessages{

    public static final String APP_SEPARATE_ = "|";
    public static final int[] LOG_RESULT = {
                                0, // LOGIN FAIL
                                1, // LOGIN SUCCESS
                                2, // LOGOUT
                                3, // CHANGE PASSWORD FAIL
                                4  // CHANGE PASSWORD SUCCESS
    };
    
    public static final int[] STATUS_HIDDEN = {
                                0, 
                                1, 
    };
    public static final int[] STATUS_REMOVE = {
                                0, 
                                1, 
    };
    
    public static final int[] STATUS_READ = {
                                0, 
                                1, 
    };
    
    public static final int[] STATUS_SEND_REV_DEL = {
                                1, 
                                2, 
                                3,
    };
    
    public static final int[] STATUS_REP = {
                                1, 
                                2, 
                                
    };
}
