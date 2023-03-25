package com.inf.mail;

public interface IConstantsMail{

    public static final String APP_SEPARATE_ = "|";
    public static final int[] LOG_RESULT = {
                                0, // LOGIN FAIL
                                1, // LOGIN SUCCESS
                                2, // LOGOUT
                                3, // CHANGE PASSWORD FAIL
                                4  // CHANGE PASSWORD SUCCESS
    };
}
