package com.dao.disability;

public class DaoFactory {
    public static Dao getDao() throws Exception{
            return new DaoImpl();
    }   
}
