package com.example.notebook;


import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    public static void main(String[] args) {
        getCalendareGeo();
        getGeoCal();
        getCalendareBuild();
    }

    private static void getCalendareBuild(){
        Calendar.Builder calendar = new Calendar.Builder();

        Calendar build = calendar.build();
        System.out.println(build);
        int firstDayOfWeek = build.getFirstDayOfWeek();
        System.out.println(firstDayOfWeek);
    }

    private static void getCalendareGeo(){
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, dd MMMM", Locale.getDefault());
        String title = formatter.format(new Date());
        System.out.println(title);
    }

    private static void getGeoCal(){



        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.setTimeZone(TimeZone.getDefault());
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        System.out.println(dayOfWeek-1);

        Date da = new Date();
        int i1 = da.getDate();
        System.out.println(i1);
        ArrayList<Date> dates = new ArrayList<>();



        int count = i1 - (dayOfWeek - 2);

        for (int i = 0; i < 7; i++) {
            Date dd = new Date(da.getYear(),da.getMonth(),count);
            System.out.println(dd.toString());
            count++;
        }
    }
}
