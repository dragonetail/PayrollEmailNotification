package com.github.dragonetail.payroll.emailnotification.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String formatTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return formatter.format(date);
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        return formatter.format(date);
    }

    public static String formatMonth(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
        return formatter.format(date);
    }
}
