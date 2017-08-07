package com.lvjc.support.util;

import com.lvjc.exception.transaction.IncorrectDatePatternException;
import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lvjc on 2017/7/12.
 */
public class DateUtil {

    public enum DatePattern{

        YEAR("yyyy"), MONTH("yyyyMM"), DAY("yyyyMMdd");

        String pattern;

        DatePattern(String pattern){
            this.pattern = pattern;
        }
    }

    private static final SimpleDateFormat FORMATTER_DAY = new SimpleDateFormat(DatePattern.DAY.pattern);
    private static final SimpleDateFormat FORMATTER_MONTH = new SimpleDateFormat(DatePattern.MONTH.pattern);
    private static final SimpleDateFormat FORMATTER_YEAR = new SimpleDateFormat(DatePattern.YEAR.pattern);

    public static String getYearFromDate(Date date) throws IncorrectDatePatternException {
        return getYearFromString(dateToString(date));
    }

    public static String getYearFromString(String str) throws IncorrectDatePatternException {
        checkDatePattern(str);
        String newStr = deleteAllCharacterNotNum(str);
        return newStr.substring(0, 4);
    }

    public static String getMonthFromString(String str) throws IncorrectDatePatternException {
        checkDatePattern(str);
        String newStr = deleteAllCharacterNotNum(str);
        return newStr.substring(0, 6);
    }

    public static String currentDate(){
        return dateToString(new Date());
    }

    public static String dateToString(Date date){
        return FORMATTER_DAY.format(date);
    }

    public static String dateToString(Date date, String pattern){
        return date == null ? null : new SimpleDateFormat(pattern).format(date);
    }

    public static Date stringToDate(String dateString) throws ParseException, IncorrectDatePatternException {
        DatePattern pattern = checkDatePattern(dateString);
        String newStr = deleteAllCharacterNotNum(dateString);
        switch (pattern){
            case DAY:
                return FORMATTER_DAY.parse(newStr);
            case MONTH:
                return FORMATTER_MONTH.parse(newStr);
            case YEAR:
                return FORMATTER_YEAR.parse(newStr);
        }
        throw new IncorrectDatePatternException();
    }

    public static DatePattern checkDatePattern(String date) throws IncorrectDatePatternException {
        //去掉所有非数字
        String newStr = deleteAllCharacterNotNum(date);
        //8位年月日
        if(newStr.length() == 8){
            int month = Integer.parseInt(newStr.substring(4, 6));
            int day = Integer.parseInt(newStr.substring(6, 8));
            if(month < 1 || month > 12)
                throw new IncorrectDatePatternException();
            if(day < 1 || month > 31)
                throw new IncorrectDatePatternException();
            return DatePattern.DAY;
        }
        //6位年月
        else if(newStr.length() == 6){
            int month = Integer.parseInt(newStr.substring(4, 6));
            if(month < 1 || month > 12)
                throw new IncorrectDatePatternException();
            return DatePattern.MONTH;
        }
        //4位年
        else if(newStr.length() == 4){
            return DatePattern.YEAR;
        }
        //其它位数抛异常
        throw new IncorrectDatePatternException();
    }

    private static String deleteAllCharacterNotNum(String str){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < str.length(); ++i){
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9')
                stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }

}
