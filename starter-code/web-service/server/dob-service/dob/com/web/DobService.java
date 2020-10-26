package com.web;
import java.util.Calendar;
public class DobService
{
     public String getYear(int arg0){
       String msg = "You may be born in: ";
       int year = Calendar.getInstance().get(Calendar.YEAR);
       msg = msg.concat(Integer.toString(year - arg0));
       return msg;
     }

}

