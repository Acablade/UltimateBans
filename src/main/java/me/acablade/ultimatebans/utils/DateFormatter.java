package me.acablade.ultimatebans.utils;

public class DateFormatter {

    enum DateType{

        DAY(24*60*60*1000),
        MINUTE(60*1000),
        SECOND(1000),
        WEEK(7*24*60*60*1000),
        MONTH(30L*24*60*60*1000);

        long format;

        DateType(long format){
            this.format = format;
        }
    }

    public static long getExpireLong(String name){
        DateType selectedDate = DateType.WEEK;
        int multiplier = 1;
        if(name.contains("D")){
            selectedDate = DateType.DAY;
            multiplier = Integer.parseInt(name.split("D")[0]);
        }else if(name.contains("MIN")){
            selectedDate = DateType.MINUTE;
            multiplier = Integer.parseInt(name.split("MIN")[0]);
        }else if(name.contains("S")){
            selectedDate = DateType.SECOND;
            multiplier = Integer.parseInt(name.split("S")[0]);
        }else if(name.contains("MON")){
            selectedDate = DateType.MONTH;
            multiplier = Integer.parseInt(name.split("MON")[0]);
        }
        long totalLong = selectedDate.format*multiplier;
        return System.currentTimeMillis()+totalLong;
    }


}
