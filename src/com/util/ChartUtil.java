package com.util;


import com.form.ChartItem;

import java.util.ArrayList;
import java.util.List;

public class ChartUtil {
    public static List<ChartItem> getDataChart(List<Object[]> listObject) throws Exception {
        ArrayList<ChartItem> lst = new ArrayList<ChartItem>();
        if (!Utilities.isEmpty(listObject)) {
            for (Object[] obj : listObject) {
                ChartItem item =
                    new ChartItem(obj[0].toString(), obj[1].toString(),
                                  Integer.parseInt(obj[2].toString()));
                lst.add(item);
            }
        }
        return lst;
    }
}
