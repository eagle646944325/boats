package cn.gelk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * 日期工具类：用于日期相关的处理
 * @author Administrator
 *
 */
public class DateUtil {
    // Grace style
    public static String PATTERN_GRACE = "yyyy/MM/dd HH:mm:ss";

    public static String PATTERN_GRACE_NORMAL = "yyyy/MM/dd HH:mm";

    public static String PATTERN_GRACE_SIMPLE = "yyyy/MM/dd";

    // Classical style
    public static String PATTERN_CLASSICAL = "yyyy-MM-dd HH:mm:ss";

    public static String PATTERN_CLASSICAL_NORMAL = "yyyy-MM-dd HH:mm";

    public static String PATTERN_CLASSICAL_SIMPLE = "yyyy-MM-dd";

    public static String PATTERN_CLASSICAL_SIMPLE2 = "yyyyMMdd";

    public static final int TIMENODE_INTERVAL = 10;

    public static final int PARAM_B_TIME = 1;

    public static final int PARAM_E_TIME = 2;

    private DateUtil() {
        // Cannot be instantiated
    }

    /**
     * 根据默认格式将指定字符串解析成日期.
     * @param str 指定字符串
     * @return 解析后的日期
     */
    public static Date parse(String str) {
        return parse(str, PATTERN_CLASSICAL);
    }

    /**
     * 根据指定格式将指定字符串解析成日期.
     * @param str 指定日期
     * @param pattern 指定格式
     * @return 解析后的日期
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(str);
        }
        catch (ParseException e) {
            System.out.println("根据格式" + pattern + "将字符串" + str + "解析成日期 :" + e.getMessage());
        }

        return null;
    }

    /**
     * 获取格式化后的今天时间.
     * @return 格式化（yyyy-MM-dd HH:mm:ss）后的今天日期
     */
    public static String getTodayTime(){
        return format(new Date(), PATTERN_CLASSICAL);
    }

    /**
     * 获取指定格式化后的今天时间.
     * @return 格式化（指定pattern格式）后的今天日期
     */
    public static String getTodayTime(String pattern){
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 根据默认格式将日期转格式化成字符串.
     * @param date  指定日期
     * @return 格式化后的字符串
     */
    public static String format(Date date) {
        return format(date, PATTERN_CLASSICAL);
    }

    /**
     * 根据指定格式将指定日期格式化成字符串.
     * @param date 指定日期
     * @param pattern 指定格式
     * @return 格式化后的字符串
     */
    public static String format(Date date, String pattern) {
        if(date == null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    /**
     * 获取时间date1与date2相差的秒数.
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 相差的秒数
     */
    public static int getOffsetSeconds(Date date1, Date date2) {
        int seconds = (int) ((date2.getTime() - date1.getTime()) / 1000);
        return seconds;
    }

    /**
     * 获取时间date1与date2相差的分钟数.
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 相差的分钟数
     */
    public static int getOffsetMinutes(Date date1, Date date2) {
        return getOffsetSeconds(date1, date2) / 60;
    }

    /**
     * 获取时间date1与date2相差的小时数.
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 相差的小时数
     */
    public static int getOffsetHours(Date date1, Date date2) {
        return getOffsetMinutes(date1, date2) / 60;
    }

    /**
     * 获取时间date1与date2相差的天数.
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 相差的天数
     */
    public static int getOffsetDays(Date date1, Date date2) {
        return getOffsetHours(date1, date2) / 24;
    }

    /**
     * 获取时间date1与date2相差的周数.
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 相差的周数
     */
    public static int getOffsetWeeks(Date date1, Date date2) {
        return getOffsetDays(date1, date2) / 7;
    }

    /**
     * 获取重置指定日期的时分秒后的时间.
     * @param date 指定日期
     * @param hour 指定小时
     * @param minute 指定分钟
     * @param second 指定秒
     * @return 重置时分秒后的时间
     */
    public static Date getResetTime(Date date, int hour, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.SECOND, minute);
        cal.set(Calendar.MINUTE, second);
        return cal.getTime();
    }

    /**
     * 指定日期的起始时间.
     * @param date 指定日期（例如2014-08-01）
     * @return 起始时间（例如2014-08-01 00:00:00）
     */
    public static Date getIntegralStartTime(Date date) {
        return getResetTime(date, 0, 0, 0);
    }

    /**
     * 指定日期的结束时间.
     * @param date 指定日期（例如2014-08-01）
     * @return 结束时间（例如2014-08-01 23:59:59）
     */
    public static Date getIntegralEndTime(Date date) {
        return getResetTime(date, 23, 59, 59);
    }

    /**
     * 获取指定日期累加年月日时分秒后的时间.
     * @param date 指定日期
     * @param year 指定年数
     * @param month 指定月数
     * @param day 指定天数
     * @param hours 指定小时
     * @param minute 指定分钟
     * @param second 指定秒
     * @return 累加年月日后的时间
     */
    public static Date rollDate(Date date, int year, int month, int day, int hours, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }

        cal.add(Calendar.YEAR, year);
        cal.add(Calendar.MONTH, month);
        cal.add(Calendar.DAY_OF_MONTH, day);
        cal.add(Calendar.HOUR_OF_DAY, hours);
        cal.add(Calendar.MINUTE, minute);
        cal.add(Calendar.SECOND, second);

        return cal.getTime();
    }

    /**
     * 获取指定日期累加年月日后的时间.
     * @param date 指定日期
     * @param year 指定年数
     * @param month 指定月数
     * @param day 指定天数
     * @return 累加年月日后的时间
     */
    public static Date rollDate(Date date, int year, int month, int day) {
        return rollDate(date, year, month, day, 0, 0, 0);
    }

    /**
     * 获取指定日期累加年数后的时间.
     * @param date 指定日期
     * @param year 指定年
     * @return 累加年数后的时间
     */
    public static Date rollYear(Date date, int year) {
        return rollDate(date, year, 0, 0);
    }

    /**
     * 获取指定日期累加指定月数后的时间.
     * @param date 指定日期
     * @param month 指定月数
     * @return 累加月数后的时间
     */
    public static Date rollMonth(Date date, int month) {
        return rollDate(date, 0, month, 0);
    }

    /**
     * 获取指定日期累加指定天数后的时间.
     * @param date 指定日期
     * @param day 指定天数
     * @return 累加天数后的时间
     */
    public static Date rollDay(Date date, int day) {
        return rollDate(date, 0, 0, day);
    }

    /**
     * 获取指定日期累加指定小时后的时间.
     * @param date 指定日期
     * @param hours 指定小时
     * @return
     */
    public static Date rollHours(Date date, int hours) {
        return rollDate(date, 0, 0, 0, hours, 0, 0);
    }

    /**
     * 获取指定日期累加指定分钟后的时间.
     * @param date 指定日期
     * @param minute 指定分钟
     * @return
     */
    public static Date rollMinute(Date date, int minute) {
        return rollDate(date, 0, 0, 0, 0, minute, 0);
    }

    /**
     * 计算指定日期所在月份的天数.
     * @param date 指定日期
     * @return 所在月份的天数
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        int dayOfMonth = cal.getActualMaximum(Calendar.DATE);
        return dayOfMonth;
    }

    /**
     * 获取当月第一天的起始时间，例如2014-08-01 00:00:00 .
     * @return 当月第一天的起始时间
     */
    public static Date getMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当月最后一天的结束时间，例如2014-08-31 23:59:59 .
     * @return 当月最后一天的结束时间
     */
    public static Date getMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取上个月第一天的起始时间，例如2014-07-01 00:00:00.
     * @return 上个月第一天的起始时间
     */
    public static Date getLastMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取上个月最后一天的结束时间，例如2014-07-31 23:59:59.
     * @return 上个月最后一天的结束时间
     */
    public static Date getLastMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取下个月第一天的起始时间，例如2014-09-01 00:00:00.
     * @return 下个月第一天的起始时间
     */
    public static Date getNextMonthStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取下个月最后一天的结束时间，例如2014-09-30 23:59:59.
     * @return 下个月最后一天的结束时间
     */
    public static Date getNextMonthEndTime() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取当前季度第一天的起始时间.
     * @return 当前季度第一天的起始时间
     */
    public static Date getQuarterStartTime() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (month < 3) {
            cal.set(Calendar.MONTH, 0);
        }
        else if (month < 6) {
            cal.set(Calendar.MONTH, 3);
        }
        else if (month < 9) {
            cal.set(Calendar.MONTH, 6);
        }
        else {
            cal.set(Calendar.MONTH, 9);
        }
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当前季度最后一天的结束时间.
     * @return 当前季度最后一天的结束时间
     */
    public static Date getQuarterEndTime() {
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        if (month < 3) {
            cal.set(Calendar.MONTH, 2);
        }
        else if (month < 6) {
            cal.set(Calendar.MONTH, 5);
        }
        else if (month < 9) {
            cal.set(Calendar.MONTH, 8);
        }
        else {
            cal.set(Calendar.MONTH, 11);
        }
        cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
        return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取当前年的起始时间.
     * @return 当前年的起始时间
     */
    public static Date getYearStartTime() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);

        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取前一个工作日.
     * @return 前一个工作日
     */
    public static Date getPrevWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取下一个工作日 .
     * @return 下个工作日
     */
    public static Date getNextWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 2);
        }
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_MONTH, 1);
        }
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当周的第一个工作日.
     * @return 第一个工作日
     */
    public static Date getFirstWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当周的最后一个工作日.
     * @return 最后一个工作日
     */
    public static Date getLastWorkday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        return getIntegralStartTime(cal.getTime());
    }

    /**
     * 判断指定日期是否是工作日.
     * @param date 指定日期
     * @return 如果是工作日true，否则false
     */
    public static boolean isWorkday(Date date) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        return !(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY);
    }

    /**
     * 获取指定日期是星期几.
     * @param date 指定日期
     * @return 星期几的描述
     */
    public static String getWeekdayDesc(Date date) {
        final String[] weeks = new String[] {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return weeks[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取指定日期是星期几(自定义星期名称).
     * @param date 指定日期
     * @return 星期几的描述
     */
    public static String getWeekdayDesc(Date date, String[] weeks) {
        Calendar cal = Calendar.getInstance();
        if (date != null) {
            cal.setTime(date);
        }
        return weeks[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取指定日期距离当前时间的时间差描述（如3小时前、1天前）.
     * @param date 指定日期
     * @return 时间差的描述
     */
    public static String getTimeOffsetDesc(Date date) {
        int seconds = getOffsetSeconds(date, new Date());
        if (Math.abs(seconds) < 60) {
            return Math.abs(seconds) + "秒" + (seconds > 0 ? "前" : "后");
        }
        int minutes = seconds / 60;
        if (Math.abs(minutes) < 60) {
            return Math.abs(minutes) + "分钟" + (minutes > 0 ? "前" : "后");
        }
        int hours = minutes / 60;
        if (Math.abs(hours) < 60) {
            return Math.abs(hours) + "小时" + (hours > 0 ? "前" : "后");
        }
        int days = hours / 24;
        if (Math.abs(days) < 7) {
            return Math.abs(days) + "天" + (days > 0 ? "前" : "后");
        }
        int weeks = days / 7;
        if (Math.abs(weeks) < 5) {
            return Math.abs(weeks) + "周" + (weeks > 0 ? "前" : "后");
        }
        int monthes = days / 30;
        if (Math.abs(monthes) < 12) {
            return Math.abs(monthes) + "个月" + (monthes > 0 ? "前" : "后");
        }
        int years = monthes / 12;
        return Math.abs(years) + "年" + (years > 0 ? "前" : "后");
    }

    /**
     *
     * 比较日期大小.
     * @param date1 String类型 yyyy-MM-dd hh:mm:ss
     * @param date2 String类型 yyyy-MM-dd hh:mm:ss
     * @return 1: date1 > date2 -1: date1 < date2 0: date1 = date2
     */
    public static int compare_date(String date1, String date2) {
        try {
            Date dt1 = parse(date1);
            Date dt2 = parse(date2);
            return compare_date(dt1,dt2);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     *
     * 比较日期大小.
     * @param date1 yyyy-MM-dd hh:mm:ss
     * @param date2 yyyy-MM-dd hh:mm:ss
     * @return 1: date1 > date2 -1: date1 < date2 0: date1 = date2
     */
    public static int compare_date(Date date1, Date date2) {
        try {
            if (date1.getTime() > date2.getTime()) {
                return 1;
            }
            else if (date1.getTime() < date2.getTime()) {
                return -1;
            }
            else {
                return 0;
            }
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取指定日期是月份.
     * @return 月份（int类型）
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }

    /**
     * 获取指定日期的日.
     * @param date
     * @return 日期（int类型）
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定日期的时
     * @param date
     * @return 小时（int类型 24小时制）
     */
    public static int getHour(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 将yyyy-mm-dd 变化为 yyyy-mm-dd H:i:s 主要是用于SQL时间类型字段匹配查询.
     * @param dateTime yyyy-mm-dd的字符串
     * @param type 1:开始时间  2：结束时间
     * @return String类型的  yyyy-mm-dd H:i:s
     */
    public static String paramFormatForSql(String dateTime,int type){
        if(!StringUtils.isEmpty(dateTime) && dateTime.length() == 10){
            if(type == 1){
                return dateTime + " 00:00:00";
            }else if(type == 2){
                return dateTime + " 23:59:59";
            }else{
                return dateTime;
            }
        }else{
            return dateTime;
        }
    }

    /**
     * 将yyyy-mm-dd 变化为 yyyy-mm-dd H:i:s 主要是用于SQL时间类型字段匹配查询.
     * @param dateTime yyyy-mm-dd的字符串
     * @param type 1:开始时间  2：结束时间
     * @return String类型的  yyyy-mm-dd H:i:s
     */
    public static Date paramFormatForSql(Date dateTime,int type){
        if(dateTime != null){
            if(type == 1){
                return DateUtil.parse(DateUtil.format(dateTime,PATTERN_CLASSICAL_SIMPLE) + " 00:00:00");
            }else if(type == 2){
                return DateUtil.parse(DateUtil.format(dateTime,PATTERN_CLASSICAL_SIMPLE) + " 23:59:59");
            }else{
                return dateTime;
            }
        }else{
            return dateTime;
        }
    }

    /**
     * 返回两个string类型日期之间相差的天数.
     * @param smdate 开始日期 String类型 yyyy-mm-dd
     * @param bdate 结束日期 String类型 yyyy-mm-dd
     * @return 返回相差的天数
     */
    public static int daysBetween(String smdate,String bdate){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        long time1 = 0;
        long time2 = 0;

        try{
            cal.setTime(sdf.parse(smdate));
            time1 = cal.getTimeInMillis();
            cal.setTime(sdf.parse(bdate));
            time2 = cal.getTimeInMillis();
        }catch(Exception e){
            e.printStackTrace();
        }
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 根据开始、结束时间获得这个时间段中的每一天.
     * @param startDate yyyy-mm-dd 格式
     * @param endDate yyyy-mm-dd 格式
     * @return 包含指定时间段中的每一天的一个 List<String>
     */
    public static List<String> getDateRangeList(String startDate,String endDate){
        List<String> list = new ArrayList<>();
        Date start = parse(startDate,PATTERN_CLASSICAL_SIMPLE);
        Date end = parse(endDate,PATTERN_CLASSICAL_SIMPLE);
        int range = getOffsetDays(start,end);
        for(int i=0; i<=range; i++){
            list.add(format(rollDay(start,i),PATTERN_CLASSICAL_SIMPLE));
        }
        return list;
    }

    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        Date date = new Date();
        Date date1 = rollDay(date, -20);
        Date date2 = rollMonth(date, -3);
        System.out.println(format(date1));
        System.out.println(format(date2));
        System.out.println(getTimeOffsetDesc(date1));
        System.out.println(getTimeOffsetDesc(date2));
        System.out.println(getNextMonthStartTime());
        System.out.println(getNextMonthEndTime());
        System.out.println(getPrevWorkday());
        System.out.println(getNextWorkday());
        System.out.println(getQuarterStartTime());
        System.out.println(getQuarterEndTime());
        System.out.println(getFirstWorkday());
        System.out.println(getLastWorkday());
        System.out.println(getWeekdayDesc(null));
    }

}
