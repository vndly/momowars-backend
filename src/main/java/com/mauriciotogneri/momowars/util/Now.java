package com.mauriciotogneri.momowars.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class Now
{
    public static Timestamp timestamp()
    {
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();

        return new Timestamp(now.getTime());
    }
}