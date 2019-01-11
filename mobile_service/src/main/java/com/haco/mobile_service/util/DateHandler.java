package com.haco.mobile_service.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;

public class DateHandler {

    public static Date initDateByMonth(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTime().getTime());
    }
    public static Date addAndSubtractDaysByGetTime(Date dateTime/*待处理的日期*/, int n/*加减天数*/){


        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        //System.out.println(df.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));
        //System.out.println(dd.format(new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L)));
        //注意这里一定要转换成Long类型，要不n超过25时会出现范围溢出，从而得不到想要的日期值
        return new Date(dateTime.getTime() + n * 24 * 60 * 60 * 1000L);
    }

    public static Date addAndSubtractDaysByCalendar(Date dateTime/*待处理的日期*/,int n/*加减天数*/){


        //日期格式
        SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


        Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);

        calstart.add(Calendar.DAY_OF_WEEK, n);
//        calstart.add(Calendar.YEAR,-1);//日期减1年
//        calstart.add(Calendar.MONTH,3);//日期加3个月
//        calstart.add(Calendar.DAY_OF_YEAR,10);//日期加10天

//        System.out.println(df.format(calstart.getTime()));
//        System.out.println(dd.format(calstart.getTime()));
        return new Date(calstart.getTime().getTime());
    }

    public static Date addAndSubtractMonthsByCalendar(Date dateTime/*待处理的日期*/,int n/*加减月数*/){
        Calendar calstart = java.util.Calendar.getInstance();
        calstart.setTime(dateTime);
        calstart.add(Calendar.MONTH,n);
        return new Date(calstart.getTime().getTime());
    }
    public static Date transDate(String dateStr) throws ParseException {
        Date date = new Date(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr).getTime());
        return date;
    }
}
