package com.demo.account.domain.utils;

import java.time.Instant;
import java.util.Calendar;

public class DateUtils {
    public static Instant getNDaysFromNow(int numOfDays) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, numOfDays);
        return cal.toInstant();
    }
}
