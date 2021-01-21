package io.github.sunning.util;


import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ning11.zhang
 * @Description: 基于DateTimeFormatter的日期工具类
 * @date 2021/1/15
 */
public class DateUtilByDateTimeFormatter {
    /**
     * 年，格式：yyyy
     */
    private final static DateTimeFormatter YEAR = DateTimeFormatter.ofPattern("yyyy");
    /**
     * 月，格式：MM
     */
    private final static DateTimeFormatter MONTH = DateTimeFormatter.ofPattern("MM");
    /**
     * 日，格式：dd
     */
    private final static DateTimeFormatter DAY = DateTimeFormatter.ofPattern("dd");
    /**
     * 小时，格式：HH
     */
    private final static DateTimeFormatter HOUR = DateTimeFormatter.ofPattern("HH");
    /**
     * 分钟，格式：mm
     */
    private final static DateTimeFormatter MINUTE = DateTimeFormatter.ofPattern("mm");
    /**
     * 秒钟，格式：ss
     */
    private final static DateTimeFormatter SECOND = DateTimeFormatter.ofPattern("ss");
    /**
     * 年月日，格式：yyyy-MM-dd
     */
    private final static DateTimeFormatter YEAR_MONTH_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /**
     * 年月日，格式：yyyyMMdd
     */
    private final static DateTimeFormatter YEAR_MONTH_DAY2 = DateTimeFormatter.ofPattern("yyyyMMdd");
    /**
     * 年月日时分秒，格式：yyyy-MM-dd HH:mm:ss
     */
    private final static DateTimeFormatter DATE = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取系统当前年份，YYYY格式
     *
     * @return
     */
    public static String getYear() {
        return LocalDate.now().format(YEAR);
    }

    /**
     * 获取系统当前月份，MM格式
     *
     * @return
     */
    public static String getMonth() {
        return LocalDate.now().format(MONTH);
    }

    /**
     * 获取系统当前日期，dd格式
     *
     * @return
     */
    public static String getDay() {
        return LocalDate.now().format(DAY);
    }

    /**
     * 获取系统当前小时，HH格式
     *
     * @return
     */
    public static String getHour() {
        return LocalDateTime.now().format(HOUR);
    }

    /**
     * 获取系统当前分钟，mm格式
     *
     * @return
     */
    public static String getMinute() {
        return LocalDateTime.now().format(MINUTE);
    }

    /**
     * 获取系统当前秒钟，ss格式
     *
     * @return
     */
    public static String getSecond() {
        return LocalDateTime.now().format(SECOND);
    }

    /**
     * 获取系统当前年月日，YYYY-MM-DD格式
     *
     * @return
     */
    public static String getYearMonthDay() {
        return LocalDate.now().format(YEAR_MONTH_DAY);
    }

    /**
     * 获取系统当前年月日，YYYYMMDD格式
     *
     * @return
     */
    public static String getYearMonthDay2() {
        return LocalDate.now().format(YEAR_MONTH_DAY2);
    }

    /**
     * 获取系统当前日期时间，YYYY-MM-DD HH:mm:ss格式
     *
     * @return
     */
    public static String getDate() {
        return LocalDateTime.now().format(DATE);
    }

    /**
     * 根据传入的int数字获取系统当前时间
     *
     * @param format 格式类型</br>
     *               11 格式：yyyy-MM</br>
     *               12 格式：MM-dd</br>
     *               13 格式：yyyy-MM-dd</br>
     *               14 格式：yyyy-MM-dd HH:mm</br>
     *               15 格式：yyyy-MM-dd HH:mm:ss</br>
     *               21 格式：yyyyMM</br>
     *               22 格式：MMdd</br>
     *               23 格式：yyyyMMdd</br>
     *               24 格式：yyyyMMddHHmm</br>
     *               25 格式：yyyyMMddHHmmss</br>
     *               31 格式：yyyy/MM</br>
     *               32 格式：MM/dd</br>
     *               33 格式：yyyy/MM/dd</br>
     *               34 格式：yyyy/MM/dd HH:mm</br>
     *               35 格式：yyyy/MM/dd HH:mm:ss</br>
     *               41 格式：yyyy年MM月</br>
     *               42 格式：MM月dd日</br>
     *               43 格式：yyyy年MM月dd日</br>
     *               44 格式：yyyy年MM月dd日 HH时mm分</br>
     *               45 格式：yyyy年MM月dd日 HH时mm分ss秒</br>
     *               51 格式：HH:mm</br>
     *               52 格式：HH:mm:ss</br>
     *               53 格式：HH时mm分</br>
     *               54 格式：HH时mm分ss秒</br>
     *               默认格式yyyy-MM-dd
     * @return 自定义的日期格式，共有24种
     */
    public static String getDateString(int format) {
        return getDefinedFormat(getYear(), getMonth(), getMonth(), getHour(), getMinute(), getSecond(), format);
    }

    /**
     * 时间戳转换为日期格式，根据传入的int数字获取确定要转换成的格式
     *
     * @param times  时间戳
     * @param format 格式类型</br>
     *               11 格式：yyyy-MM</br>
     *               12 格式：MM-dd</br>
     *               13 格式：yyyy-MM-dd</br>
     *               14 格式：yyyy-MM-dd HH:mm</br>
     *               15 格式：yyyy-MM-dd HH:mm:ss</br>
     *               21 格式：yyyyMM</br>
     *               22 格式：MMdd</br>
     *               23 格式：yyyyMMdd</br>
     *               24 格式：yyyyMMddHHmm</br>
     *               25 格式：yyyyMMddHHmmss</br>
     *               31 格式：yyyy/MM</br>
     *               32 格式：MM/dd</br>
     *               33 格式：yyyy/MM/dd</br>
     *               34 格式：yyyy/MM/dd HH:mm</br>
     *               35 格式：yyyy/MM/dd HH:mm:ss</br>
     *               41 格式：yyyy年MM月</br>
     *               42 格式：MM月dd日</br>
     *               43 格式：yyyy年MM月dd日</br>
     *               44 格式：yyyy年MM月dd日 HH时mm分</br>
     *               45 格式：yyyy年MM月dd日 HH时mm分ss秒</br>
     *               51 格式：HH:mm</br>
     *               52 格式：HH:mm:ss</br>
     *               53 格式：HH时mm分</br>
     *               54 格式：HH时mm分ss秒</br>
     *               默认格式yyyy-MM-dd
     * @return 自定义的日期格式，共有24种
     */
    public static String getDateString(String times, int format) {
        // 只计算到秒
        if (times.length() > 10) {
            times = times.substring(0, 10);
        }
        long timeStamp = new Long(times);
        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(timeStamp, 0, ZoneOffset.ofHours(8));
        String year = dateTime.format(YEAR);
        String month = dateTime.format(MONTH);
        String day = dateTime.format(DAY);
        String hour = dateTime.format(HOUR);
        String minute = dateTime.format(MINUTE);
        String second = dateTime.format(SECOND);
        return getDefinedFormat(year, month, day, hour, minute, second, format);
    }

    /**
     * 把日期类型转换成字符串形式，根据传入的int数字获取确定要转换成的格式
     *
     * @param date   Date类型的日期
     * @param format 格式类型</br>
     *               11 格式：yyyy-MM</br>
     *               12 格式：MM-dd</br>
     *               13 格式：yyyy-MM-dd</br>
     *               14 格式：yyyy-MM-dd HH:mm</br>
     *               15 格式：yyyy-MM-dd HH:mm:ss</br>
     *               21 格式：yyyyMM</br>
     *               22 格式：MMdd</br>
     *               23 格式：yyyyMMdd</br>
     *               24 格式：yyyyMMddHHmm</br>
     *               25 格式：yyyyMMddHHmmss</br>
     *               31 格式：yyyy/MM</br>
     *               32 格式：MM/dd</br>
     *               33 格式：yyyy/MM/dd</br>
     *               34 格式：yyyy/MM/dd HH:mm</br>
     *               35 格式：yyyy/MM/dd HH:mm:ss</br>
     *               41 格式：yyyy年MM月</br>
     *               42 格式：MM月dd日</br>
     *               43 格式：yyyy年MM月dd日</br>
     *               44 格式：yyyy年MM月dd日 HH时mm分</br>
     *               45 格式：yyyy年MM月dd日 HH时mm分ss秒</br>
     *               51 格式：HH:mm</br>
     *               52 格式：HH:mm:ss</br>
     *               53 格式：HH时mm分</br>
     *               54 格式：HH时mm分ss秒</br>
     *               默认格式yyyy-MM-dd
     * @return 自定义的日期格式，共有24种
     */
    public static String getDateString(Date date, int format) {
        return getDateString(date.getTime() + "", format);
    }

    /**
     * 自定义日期字符串
     *
     * @param year   年
     * @param month  月
     * @param day    日
     * @param hour   时
     * @param minute 分
     * @param second 秒
     * @param format 格式类型</br>
     *               11 格式：yyyy-MM</br>
     *               12 格式：MM-dd</br>
     *               13 格式：yyyy-MM-dd</br>
     *               14 格式：yyyy-MM-dd HH:mm</br>
     *               15 格式：yyyy-MM-dd HH:mm:ss</br>
     *               21 格式：yyyyMM</br>
     *               22 格式：MMdd</br>
     *               23 格式：yyyyMMdd</br>
     *               24 格式：yyyyMMddHHmm</br>
     *               25 格式：yyyyMMddHHmmss</br>
     *               31 格式：yyyy/MM</br>
     *               32 格式：MM/dd</br>
     *               33 格式：yyyy/MM/dd</br>
     *               34 格式：yyyy/MM/dd HH:mm</br>
     *               35 格式：yyyy/MM/dd HH:mm:ss</br>
     *               41 格式：yyyy年MM月</br>
     *               42 格式：MM月dd日</br>
     *               43 格式：yyyy年MM月dd日</br>
     *               44 格式：yyyy年MM月dd日 HH时mm分</br>
     *               45 格式：yyyy年MM月dd日 HH时mm分ss秒</br>
     *               51 格式：HH:mm</br>
     *               52 格式：HH:mm:ss</br>
     *               53 格式：HH时mm分</br>
     *               54 格式：HH时mm分ss秒</br>
     *               默认格式yyyy-MM-dd
     * @return
     */
    public static String getDefinedFormat(String year, String month, String day, String hour, String minute, String second, int format) {
        String dateStr = null;
        switch (format) {
            case 11:
                dateStr = year + "-" + month;
                break;
            case 12:
                dateStr = month + "-" + day;
                break;
            case 13:
                dateStr = year + "-" + month + "-" + day;
                break;
            case 14:
                dateStr = year + "-" + month + "-" + day + " " + hour + ":" + minute;
                break;
            case 15:
                dateStr = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
                break;
            case 21:
                dateStr = year + month;
                break;
            case 22:
                dateStr = month + day;
                break;
            case 23:
                dateStr = year + month + day;
                break;
            case 24:
                dateStr = year + month + day + hour + minute;
                break;
            case 25:
                dateStr = year + month + day + hour + minute + second;
                break;
            case 31:
                dateStr = year + "/" + month;
                break;
            case 32:
                dateStr = month + "/" + day;
                break;
            case 33:
                dateStr = year + "/" + month + "/" + day;
                break;
            case 34:
                dateStr = year + "/" + month + "/" + day + " " + hour + ":" + minute;
                break;
            case 35:
                dateStr = year + "/" + month + "/" + day + " " + hour + ":" + minute + ":" + second;
                break;
            case 41:
                dateStr = year + "年" + month + "月";
                break;
            case 42:
                dateStr = month + "月" + day + "日";
                break;
            case 43:
                dateStr = year + "年" + month + "月" + day + "日";
                break;
            case 44:
                dateStr = year + "年" + month + "月" + day + "日" + " " + hour + "时" + minute + "分";
                break;
            case 45:
                dateStr = year + "年" + month + "月" + day + "日" + " " + hour + "时" + minute + "分" + second + "秒";
                break;
            case 51:
                dateStr = hour + ":" + minute;
                break;
            case 52:
                dateStr = hour + ":" + minute + ":" + second;
                break;
            case 53:
                dateStr = hour + "时" + minute + "分";
                break;
            case 54:
                dateStr = hour + "时" + minute + "分" + second + "秒";
                break;
            default:
                dateStr = year + "-" + month + "-" + day;
        }
        return dateStr;
    }

    /**
     * 将java.util.Date 转换为java8 的java.time.LocalDateTime,默认时区为东8区
     *
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
    }

    /**
     * 将java8 的 java.time.LocalDateTime 转换为 java.util.Date，默认时区为东8区
     *
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.of("+8")));
    }

    /**
     * 获取DateTimeFormatter的日期
     * 传入Date和匹配格式，转为对应的字符串
     *
     * @param date   传入的日期 如new Date()
     * @param format 自定义格式，例：yyyy-MM-dd
     * @return
     */
    public static String getDateTimeFormatter(Date date, String format) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = dateToLocalDateTime(date);
        return ldt.format(dateFormat);
    }

    /**
     * 传入时间字符串-和匹配格式转为对应的Date
     *
     * @param time   2020-01-01 11:11:11
     * @param format yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date timeStrToDate(String time, String format) {
        Date date1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date1 = sdf.parse(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date1;
    }

    /**
     * 日期比较
     *
     * @param startDate 开始日期，格式yyyy-MM-dd   A
     * @param endDate   结束日期，格式yyyy-MM-dd   B
     * @return 如果startDate>endDate，返回1，startDate=endDate返回0，startDate<endDate返回-1
     */
    public static Integer compareDate(String startDate, String endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        int r = 0;
        if (getSecond(startDate) > getSecond(endDate)) {
            r = 1;
        }
        if (getSecond(startDate) < getSecond(endDate)) {
            r = -1;
        }
        return r;
    }

    /**
     * 校验日期是否合法，校验格式：yyyy-MM-dd
     *
     * @param date 日期字符串
     * @return
     */
    public static boolean isValidDate(String date) {
        try {
            YEAR_MONTH_DAY.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            // 如果throw，就说明格式不对
            return false;
        }
    }

    /**
     * 校验日期是否合法，
     *
     * @param date      日期字符串
     * @param formatter 校验格式，例：yyyy-MM-dd
     * @return
     */
    public static boolean isValidDateFormat(String date, String formatter) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(formatter);
        try {
            dateFormat.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            // 如果throw，就说明格式不对
            return false;
        }
    }


    /**
     * 格式为yyyy-MM-dd的时间字符串转为秒，默认时区为东8区
     *
     * @param dateStr
     * @return
     */
    public static long getSecond(String dateStr) {
        return LocalDateTime.parse(dateStr + " 00:00:00", DATE).toEpochSecond(ZoneOffset.of("+8"));
    }

    /**
     * 时间相减得到年份，不够365天算0年
     *
     * @param startDate 开始日期，格式yyyy-MM-dd
     * @param endDate   结束日期，格式yyyy-MM-dd
     * @return 返回相隔的年数，null表示格式不正确出现异常
     */
    public static Integer getDiffYear(String startDate, String endDate) {
        try {
            int years = (int) (((getSecond(startDate) - getSecond(endDate)) / (60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 时间相减得到天数
     *
     * @param startDate 开始日期，格式yyyy-MM-dd
     * @param endDate   结束日期，格式yyyy-MM-dd
     * @return 返回相隔的天数，null表示格式不正确出现异常
     */
    public static Integer getDaySub(String startDate, String endDate) {
        try {
            int days = (int) (getSecond(endDate) - getSecond(startDate)) / (24 * 60 * 60);
            return days;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 得到n天之后的日期 返回的是yyyy-MM-dd HH:mm:ss
     *
     * @param days
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getAfterDayDate(int days) {
        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期相减，如果不够减将会向月变动
        canlendar.add(Calendar.DATE, days);
        Date date = canlendar.getTime();

        return getDateTimeFormatter(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到n天之后的日期 返回的是yyyy-MM-dd格式
     *
     * @param days
     * @return yyyy-MM-dd
     */
    public static String getAfterDayDateYYYYMMDD(int days) {
        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期相减，如果不够减将会向月变动
        canlendar.add(Calendar.DATE, days);
        Date date = canlendar.getTime();

        return getDateTimeFormatter(date, "yyyy-MM-dd");
    }

    /**
     * 得到n天之后是周几
     *
     * @param days
     * @return
     */
    public static String getAfterDayWeek(int days) {
        // java.util包
        Calendar canlendar = Calendar.getInstance();
        // 日期相减，如果不够减将会向月变动
        canlendar.add(Calendar.DATE, days);
        Date date = canlendar.getTime();
        return getDateTimeFormatter(date, "E");
    }

    public static void main(String[] args) {
        System.out.println("年：" + DateUtilByDateTimeFormatter.getYear());
        System.out.println("月：" + DateUtilByDateTimeFormatter.getMonth());
        System.out.println("日：" + DateUtilByDateTimeFormatter.getDay());
        System.out.println("时：" + DateUtilByDateTimeFormatter.getHour());
        System.out.println("分：" + DateUtilByDateTimeFormatter.getMinute());
        System.out.println("秒：" + DateUtilByDateTimeFormatter.getSecond());
        System.out.println("年月日：" + DateUtilByDateTimeFormatter.getYearMonthDay());
        System.out.println("年月日：" + DateUtilByDateTimeFormatter.getYearMonthDay2());
        System.out.println("年月日时分秒：" + DateUtilByDateTimeFormatter.getDate());

        System.out.println("格式为yyyy-MM-dd的时间字符串转为秒，默认时区为东8区(10位):" + DateUtilByDateTimeFormatter.getSecond("2021-01-20"));
        System.out.println("根据传入的int数字获取系统当前时间:" + DateUtilByDateTimeFormatter.getDateString(15));
        String str = System.currentTimeMillis() + "";
        System.out.println("时间戳转换为日期格式，根据传入的int数字获取确定要转换成的格式:" + DateUtilByDateTimeFormatter.getDateString(str, 15));
        System.out.println("把日期类型转换成字符串形式:" + DateUtilByDateTimeFormatter.getDateString(new Date(), 15));
        System.out.println("=============自定义时间的转化==============");
        System.out.println("Date转字符串:" + DateUtilByDateTimeFormatter.getDateTimeFormatter(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println("时间字符串转Date：" + DateUtilByDateTimeFormatter.timeStrToDate("2020-01-01 11:11:11", "yyyy-MM-dd HH:mm:ss"));
        //时间的对比
        System.out.println("=============时间的对比==============");
        System.out.println("A>B：" + DateUtilByDateTimeFormatter.compareDate("2019-11-19", "2019-04-19"));
        System.out.println("A=B：" + DateUtilByDateTimeFormatter.compareDate("2019-04-19", "2019-04-19"));
        System.out.println("A<B：" + DateUtilByDateTimeFormatter.compareDate("2019-04-19", "2019-11-19"));

        System.out.println("校验日期是否合法，校验格式：yyyy-MM-dd:" + DateUtilByDateTimeFormatter.isValidDate("2019-11-19"));
        System.out.println("校验日期是否合法，校验格式：yyyy-MM-dd:" + DateUtilByDateTimeFormatter.isValidDate("2019-11>19"));
        System.out.println("校验自定义日期格式是否合法:" + DateUtilByDateTimeFormatter.isValidDateFormat("2019>11>19", "yyyy>mm>dd"));

        System.out.println("时间相减得到年份，不够365天算0年：" + DateUtilByDateTimeFormatter.getDiffYear("2010-01-08", "2019-01-02"));
        System.out.println("时间相减得到年份，不够365天算0年：（null表示格式错误）" + DateUtilByDateTimeFormatter.getDiffYear("2019", "2019-01-02"));

        System.out.println("得到2天之后是周几：" + DateUtilByDateTimeFormatter.getAfterDayWeek(2));
        System.out.println("得到2天之后的日期和时间：" + DateUtilByDateTimeFormatter.getAfterDayDate(2));
        System.out.println("得到2天前的日期和时间：" + DateUtilByDateTimeFormatter.getAfterDayDate(-2));
        System.out.println("时间相减得到天数差：" + getDaySub("2021-01-01", "2021-01-15"));
    }

}

