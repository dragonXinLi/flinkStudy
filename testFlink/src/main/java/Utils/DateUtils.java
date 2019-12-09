package Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils
{

    /**
     * 年-月-日 时:分:秒 显示格式
     */
    // 备注:如果使用大写HH标识使用24小时显示格式,如果使用小写hh就表示使用12小时制格式。
    public static final String TIME_TO_STRING_DETAIAL_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String MINUTE_PATTERN = "yyyyMMddHHmm";
    /**
     * 年-月-日 显示格式
     */
    public static final String DATE_TO_STRING_SHORT_PATTERN = "yyyy-MM-dd";

    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String DateToString(Date source, String pattern)
    {
        if ( source == null){
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(source);
    }


    /**
     * Date类型转为指定格式的String类型
     *
     * @param source
     * @return
     */
    public static String DateToString(Date source)
    {
        return DateToString(source, "yyyy-MM-dd HH:mm:ss");
    }
    

    public static String timeToString(Date source)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TO_STRING_DETAIAL_PATTERN);
        return simpleDateFormat.format(source);
    }
    public static int currentYear()
    {
        Calendar cale = Calendar.getInstance();
        return cale.get(Calendar.YEAR);
    }
    public static int currentMonth()
    {
        Calendar cale = Calendar.getInstance();
        return cale.get(Calendar.MONTH) + 1;
    }
    public static String currentDay()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TO_STRING_SHORT_PATTERN);
        return simpleDateFormat.format(new Date());

    }
    public static String currentTime()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(TIME_TO_STRING_DETAIAL_PATTERN);
        return simpleDateFormat.format(new Date());

    }
    public static String currentTimeMinute()
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(MINUTE_PATTERN);
        return simpleDateFormat.format(new Date());

    }

    /**
     * unix时间戳转为指定格式的String类型
     * <p>
     * <p>
     * System.currentTimeMillis()获得的是是从1970年1月1日开始所经过的毫秒数
     * unix时间戳:是从1970年1月1日（UTC/GMT的午夜）开始所经过的秒数,不考虑闰秒
     *
     * @param source
     * @param pattern
     * @return
     */
    public static String timeStampToString(long source, String pattern)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = new Date(source );
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期转换为时间戳(unix时间戳,单位秒)
     *
     * @param date
     * @return
     */
    public static long dateToTimeStamp(Date date)
    {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime() / 1000;

    }

    /**
     * 将日期转换为时间戳(unix时间戳,单位毫秒)
     *
     * @param date
     * @return
     */
    public static long dateToTimeStamp2(Date date)
    {
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp.getTime();

    }

    /**
     * 字符串转换为对应日期(可能会报错异常)
     *
     * @param source
     * @param pattern
     * @return
     */
    public static Date stringToDate(String source, String pattern)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try
        {
            date = simpleDateFormat.parse(source);
        }
        catch (ParseException e)
        {
        }
        return date;
    }

    /**
     * 获得当前时间对应的指定格式
     *
     * @param pattern
     * @return
     */
    public static String currentFormatDate(String pattern)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(new Date());

    }

    /**
     * 获得当前unix时间戳(单位秒)
     *
     * @return 当前unix时间戳
     */
    public static long currentTimeStamp()
    {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 相隔小时数
     * @param time1
     * @param time2
     * @return
     */
    public static long hoursBetween(long time1,long time2)
    {
        return (time2-time1)/(1000*60*60);
    }

    /**
     * 相隔天数
     * @param time1
     * @param time2
     * @return
     */
    public static long daysBetween(long time1,long time2)
    {
        return (time2-time1)/(1000*60*60*24);
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(String dateStr,int past) {
        Calendar calendar = Calendar.getInstance();
        Date date =stringToDate(dateStr,DATE_TO_STRING_SHORT_PATTERN);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(today);
    }

    /**
     * 获取当前小时
     * @param date
     * @return
     */
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }


    public static void main(String[] args) {
        System.out.println(currentYear());
    }
}
