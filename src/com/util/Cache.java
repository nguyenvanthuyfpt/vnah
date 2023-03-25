package com.util;


import com.dao.disability.Dao;
import com.dao.disability.DaoFactory;

import java.util.HashMap;

public class Cache extends HashMap<String, Object> {
    private static Cache cache = null;

    protected Cache() throws Exception {
        try {
            load();
        } catch (Exception ex) {
            load();
        }
    }

    public static Cache getInstance() throws Exception {
        if (cache == null)
            createCache();
        return cache;
    }

    synchronized private static void createCache() throws Exception {
        if (cache == null)
            cache = new Cache();
    }

    synchronized public static void reLoad() throws Exception {
        cache = new Cache();
    }

    private void load() throws Exception {
        Dao dao = null;
        try {
            dao = DaoFactory.getDao();                      
            
            //THKT
            /*Map<String, String> map_thkt = new LinkedHashMap<String, String>();
            map_thkt.put("qt05", "BC t?ng h?p T? khai Quy?t toán 03/TNDN");
            map_thkt.put("tk37", "BC t?ng h?p T? khai 01A/TNDN");
            map_thkt.put("tk26", "BC t?ng h?p T? khai 01B/TNDN");
            map_thkt.put("tk14", "BC t?ng h?p T? khai 01/GTGT - d? li?u s? thu?");
            map_thkt.put("tk00", "BC t?ng h?p T? khai 01/GTGT - d? li?u doanh s?");*/  

            //other
            //put("MAP_TPH_TTR_LOAI_HVI_VIPHAM_CACHE", dao.get_map_hvi_vipham());

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (dao != null)
                dao.close();
        }
    }
}
