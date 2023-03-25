package com.inf.agenda;

import com.inf.tasks.IKeyTasks;

public class IKeyAgenda extends IKeyTasks {  
    public static final int CALENDAR_VIEW_DAYS= Integer.parseInt(getValue("CALENDAR.VIEW.DAYS"));
    public static final int BROADCAST_VIEW_DAYS= Integer.parseInt(getValue("BROADCAST.VIEW.DAYS"));
    
    public static final int CALENDAR_TYPE_PRIVATE=0;
    public static final int CALENDAR_TYPE_PUBLIC=1;
    public static final int CALENDAR_TYPE_DEPARTMENT=2;
    public static final String[] CALENDAR_TIMES_VALUES=getValue("CALENDAR.TIMES.VALUES").split("#");
    public static final String CALENDAR_TIMES_VALUES_STRING=getValue("CALENDAR.TIMES.VALUES");
    
}