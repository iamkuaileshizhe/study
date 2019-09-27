package cn.com.trying.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期处理工具类
 * @ClassName: DateUtil
 * @author shaosuone
 * 2017年3月21日 上午10:15:29
 */
public class DateUtil {

    private static Log log = LogFactory.getLog(DateUtil.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String YYYYMMDD = "yyyy-MM-dd";

	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

	public static final String HHMMSS = "HH:mm:ss";

	public static final String YYYYMMDD_ZH = "yyyy年MM月dd日";

	public static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY; // 中国周一是一周的第一天

    public DateUtil(){
    }
    /**
     * 将指定的日期转换成自定义格式
     * @param pattern
     * @param date
     * @return String
     * @author shaosuone
     * @date 2017-3-29 上午11:13:20
     */
    public static String getDateTime(String pattern,Date date){
        try{
        	SimpleDateFormat sd = new SimpleDateFormat(pattern);
            return sd.format(date);
        } catch(Exception e){
            log.debug("DateUtil.getDateTime(String pattern,Date date):" + e.getMessage());
            return "";
        }
    }

    /**
     * 将指定的日期字符串根据自定义格式转换成date
     * @param pattern
     * @param date
     * @return String
     * @author shaosuone
     * @date 2017-3-29 上午11:13:20
     */
    public static Date getDateTime(String pattern,String date){
        try{
        	SimpleDateFormat sd = new SimpleDateFormat(pattern);

            return sd.parse(date);
        } catch(Exception e){
            log.debug("DateUtil.getDateTime(String pattern,Date date):" + e.getMessage());
        }
		return null;
    }

    /**
     * 获得服务器当前日期及时间，以格式为：yyyy-MM-dd HH:mm:ss的日期字符串形式返回
     */
    public static String getDateTime(){
    	Calendar cale = Calendar.getInstance();
        try{
            return sdf2.format(cale.getTime());
        } catch(Exception e){
            log.debug("DateUtil.getDateTime():" + e.getMessage());
            return "";
        }
    }
    /**
     * 将指定的日期转换成yyyy-MM-dd HH:mm:ss的字符串形式
     */
    public static String dateTimeToString(Date date) {
        return getDateTime(YYYYMMDDHHMMSS, date);
    }
    /**
     * 将指定的日期字符串按照yyyy-MM-dd HH:mm:ss的格式转换成Date对象
     */
    public static Date stringToDateTime(String str) {
        return getDateTime(YYYYMMDDHHMMSS, str);
    }

    /**
     * 获得服务器当前日期，以格式为：yyyy-MM-dd的日期字符串形式返回
     */
    public static String getDate(){
    	Calendar cale = Calendar.getInstance();
        try{
            return sdf.format(cale.getTime());
        } catch(Exception e){
            log.debug("DateUtil.getDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 将指定的日期转换成yyyy-MM-dd的字符串形式
     */
    public static String dateToString(Date date) {
        return getDateTime(YYYYMMDD, date);
    }

    /**
     * 将指定的日期字符串按照yyyy-MM-dd 的格式转换成Date对象
     */
    public static Date stringToDate(String str) {
        return getDateTime(YYYYMMDD, str);
    }

    /**
     * 获得服务器当前时间，以格式为：HH:mm:ss的日期字符串形式返回
     */
    public static String getTime(){
        String temp = "";
        Calendar cale = Calendar.getInstance();
        try{
            temp += sdf1.format(cale.getTime());
            return temp;
        } catch(Exception e){
            log.debug("DateUtil.getTime():" + e.getMessage());
            return "";
        }
    }

    /**
     * 将指定的日期转换成HH:mm:ss的字符串形式
     */
    public static String timeToString(Date date) {
        return getDateTime(HHMMSS, date);
    }

    /**
     * 将指定的日期字符串按照yyyy-MM-dd 的格式转换成Date对象
     */
    public static Date stringToTime(String str) {
        return getDateTime(HHMMSS, str);
    }

    /**
     * 统计时开始日期的默认值,
     * 今年的开始时间
     */
    public static String getStartDate(){
        try{
            return getYear() + "-01-01";
        } catch(Exception e){
            log.debug("DateUtil.getStartDate():" + e.getMessage());
            return "";
        }
    }

    /**
     * 统计时结束日期的默认值
     */
    public static String getEndDate(){
        try{
            return getDate();
        } catch(Exception e){
            log.debug("DateUtil.getEndDate():" + e.getMessage());
            return "";
        }
    }


    /**
     * 获得服务器当前日期的年份
     */
    public static String getYear(){
        try{
            //返回的int型，需要字符串转换
        	Calendar cale = Calendar.getInstance();
            return String.valueOf(cale.get(Calendar.YEAR));
        } catch(Exception e){
            log.debug("DateUtil.getYear():" + e.getMessage());
            return "";
        }
    }

    /**
	 * 根据Date对象 取得日期：年
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		int year = cale.get(Calendar.YEAR);
		return year;
	}

	/**
	 * 根据String字符串 取得日期：年
	 * @param date
	 * @return
	 */
	public static int getYear(String date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(stringToDate(date));
		int year = cale.get(Calendar.YEAR);
		return year;
	}

    /**
     * 获得服务器当前日期的月份
     */
    public static String getMonth(){
    	Calendar cale = Calendar.getInstance();
        try{
            //一个数字格式，非常好
            java.text.DecimalFormat df = new java.text.DecimalFormat();
            df.applyPattern("00");
            return df.format((cale.get(Calendar.MONTH) + 1));
            //return String.valueOf(cale.get(Calendar.MONTH) + 1);
        } catch(Exception e){
            log.debug("DateUtil.getMonth():" + e.getMessage());
            return "";
        }
    }

    /**
	 *根据Date对象  取得日期：月
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		int month = cale.get(Calendar.MONTH);
		return month + 1;
	}

	/**
	 *根据日期字符串  取得日期：月
	 *
	 * @param date
	 * @return
	 */
	public static int getMonth(String date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(stringToDate(date));
		int month = cale.get(Calendar.MONTH);
		return month + 1;
	}

    /**
     * 获得服务器在当前月中天数
     */
    public static String getDay(){
    	Calendar cale = Calendar.getInstance();
        try{
            return String.valueOf(cale.get(Calendar.DAY_OF_MONTH));
        } catch(Exception e){
            log.debug("DateUtil.getDay():" + e.getMessage());
            return "";
        }
    }

	/**
	 *根据Date对象  取得日期：日
	 *
	 * @param date
	 * @return
	 */
	public static int getDay(Date date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(date);
		int da = cale.get(Calendar.DAY_OF_MONTH);
		return da;
	}

	/**
	 *根据日期字符串   取得日期：日
	 *
	 * @param date
	 * @return
	 */
	public static int getDay(String date) {
		Calendar cale = Calendar.getInstance();
		cale.setTime(stringToDate(date));
		int da = cale.get(Calendar.DAY_OF_MONTH);
		return da;
	}



    /**
     * 比较两个日期相差的天数,
     * 第一个日期要比第二个日期要晚
     */
    public static int getMargin(String date1,String date2){
        int margin;
        try{
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf.parse(date1,pos);
            Date dt2 = sdf.parse(date2,pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (int)(l / (24 * 60 * 60 * 1000));
            return margin;
        } catch(Exception e){
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }


    /**
     * 比较两个日期相差的天数，格式不一样
     * 第一个日期要比第二个日期要晚
     */
    public static double getDoubleMargin(String date1,String date2){
        double margin;
        try{
            ParsePosition pos = new ParsePosition(0);
            ParsePosition pos1 = new ParsePosition(0);
            Date dt1 = sdf2.parse(date1,pos);
            Date dt2 = sdf2.parse(date2,pos1);
            long l = dt1.getTime() - dt2.getTime();
            margin = (l / (24 * 60 * 60 * 1000.00));
            return margin;
        } catch(Exception e){
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }


    /**
     * 比较两个日期相差的月数
     */
    public static int getMonthMargin(String date1,String date2){
        int margin;
        try{
            margin  = (Integer.parseInt(date2.substring(0,4)) - Integer.parseInt(date1.substring(0,4)))* 12;
            margin += (Integer.parseInt(date2.substring(4,7).replaceAll("-0","-")) - Integer.parseInt(date1.substring(4,7).replaceAll("-0","-")));
            return margin;
        } catch(Exception e){
            log.debug("DateUtil.getMargin():" + e.toString());
            return 0;
        }
    }

    /**
     * 返回日期加X天后的日期
     */
    public static String addDay(String date,int i){
        try{
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7))-1,Integer.parseInt(date.substring(8,10)));
            gCal.add(GregorianCalendar.DATE,i);
            return sdf.format(gCal.getTime());
        } catch(Exception e){
            log.debug("DateUtil.addDay():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X月后的日期
     */
    public static String addMonth(String date,int i){
        try{
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7))-1,Integer.parseInt(date.substring(8,10)));
            gCal.add(GregorianCalendar.MONTH,i);
            return sdf.format(gCal.getTime());
        } catch(Exception e){
            log.debug("DateUtil.addMonth():" + e.toString());
            return getDate();
        }
    }

    /**
     * 返回日期加X年后的日期
     */
    public static String addYear(String date,int i){
        try{
            GregorianCalendar gCal = new GregorianCalendar(Integer.parseInt(date.substring(0,4)),Integer.parseInt(date.substring(5,7))-1,Integer.parseInt(date.substring(8,10)));
            gCal.add(GregorianCalendar.YEAR,i);
            return sdf.format(gCal.getTime());
        } catch(Exception e){
            log.debug("DateUtil.addYear():" + e.toString());
            return "";
        }
    }


    /**
     * 返回某年某月中的最大天
     */
    public static int getMaxDay(String year,String month){
        int day = 0;
        try{
            int iyear = Integer.parseInt(year);
            int imonth = Integer.parseInt(month);
            if(imonth == 1 || imonth == 3 || imonth == 5 || imonth == 7 || imonth == 8 || imonth == 10 || imonth == 12){
                day = 31;
            } else if(imonth == 4 || imonth == 6 || imonth == 9 || imonth == 11){
                day = 30;
            } else if((0 == (iyear % 4)) && (0 != (iyear % 100)) || (0 == (iyear % 400))){
                day = 29;
            } else{
                day = 28;
            }
            return day;
        } catch(Exception e){
            log.debug("DateUtil.getMonthDay():" + e.toString());
            return 1;
        }
    }



    /**
     * 格式化日期
     */
    @SuppressWarnings("static-access")
    public String rollDate(String orgDate,int Type,int Span){
        try{
            String temp = "";
            int iyear,imonth,iday;
            int iPos = 0;
            char seperater = '-';
            if(orgDate == null || orgDate.length() < 6){
                return "";
            }

            iPos = orgDate.indexOf(seperater);
            if(iPos > 0){
                iyear = Integer.parseInt(orgDate.substring(0,iPos));
                temp = orgDate.substring(iPos + 1);
            } else{
                iyear = Integer.parseInt(orgDate.substring(0,4));
                temp = orgDate.substring(4);
            }

            iPos = temp.indexOf(seperater);
            if(iPos > 0){
                imonth = Integer.parseInt(temp.substring(0,iPos));
                temp = temp.substring(iPos + 1);
            } else{
                imonth = Integer.parseInt(temp.substring(0,2));
                temp = temp.substring(2);
            }

            imonth--;
            if(imonth < 0 || imonth > 11){
                imonth = 0;
            }

            iday = Integer.parseInt(temp);
            if(iday < 1 || iday > 31)
                iday = 1;

            Calendar orgcale = Calendar.getInstance();
            orgcale.set(iyear,imonth,iday);
            temp = this.rollDate(orgcale,Type,Span);
            return temp;
        }catch(Exception e){
            return "";
        }
    }

    public static String rollDate(Calendar cal,int Type,int Span){
        try{
            String temp = "";
            Calendar rolcale;
            rolcale = cal;
            rolcale.add(Type,Span);
            temp = sdf.format(rolcale.getTime());
            return temp;
        }catch(Exception e){
            return "";
        }
    }


 /*   *//**
     * 取得当前时间的Calendar日历对象  liss
     *//*
    public Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));
        return cal;
    }  */

   /* *//**
     * 将日期字符串按指定格式转换成日期类型
     * @param aMask 指定的日期格式，如:yyyy-MM-dd
     * @param strDate 待转换的日期字符串
     *//*

    public static final Date convertStringToDate(String aMask, String strDate) {
        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(aMask);

        if (log.isDebugEnabled()) {
            log.debug("converting '" + strDate + "' to date with mask '"+ aMask + "'");
        }
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            log.error("ParseException: " + pe);

        }
        return (date);
    }  */


    /**
     * 将两个字符串格式的日期进行比较
     * @param last 要比较的第一个日期字符串
     * @param now   要比较的第二个日期格式字符串
     * @return true(last 在now 日期之前),false(last 在now 日期之后)
     */
    public static boolean compareTo(String last, String now) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(YYYYMMDDHHMMSS);
            Date temp1 = formatter.parse(last);
            Date temp2 = formatter.parse(now);
            if (temp1.after(temp2))
                return false;
            else if (temp1.before(temp2))
                return true;
        } catch (ParseException e) {
            log.debug(e.getMessage());
        }
        return false;
    }


   /*
    protected <T> Object convertToDate(Class<T> type, Object value) {
        DateFormat df = new SimpleDateFormat(YYYYMMDDHHMMSS);
        if (value instanceof String) {
            try {
                if (StringUtils.isEmpty(value.toString())) {
                    return null;
                }
                return df.parse((String) value);
            } catch (Exception pe) {
                throw new ConversionException("Error converting String to Timestamp");
            }
        }

        throw new ConversionException("Could not convert " + value.getClass().getName() + " to " + type.getName());
    }
   */




    /**
     *  为查询日期添加最小时间
     *  @param 目标类型Date
     *  @param 转换参数Date
     *  @return
     */
    @SuppressWarnings("deprecation")
    public static Date addStartTime(Date param) {
        Date date = param;
        try{
            date.setHours(0);
            date.setMinutes(0);
            date.setSeconds(0);
            return date;
        }catch(Exception ex){
            return date;
        }
    }



    /**
     * 为查询日期添加最大时间
     *  @param 目标类型Date
     *  @param 转换参数Date
     *  @return
     */
    @SuppressWarnings("deprecation")
    public static Date addEndTime(Date param) {
        Date date = param;
        try{
            date.setHours(23);
            date.setMinutes(59);
            date.setSeconds(0);
            return date;
        }catch(Exception ex){
            return date;
        }
    }



    /**
     * 返回系统现在年份中指定月份的天数
     * @param 月份month
     * @return 指定月的总天数
     */
    @SuppressWarnings("deprecation")
    public static String getMonthLastDay(int month)
    {
        Date date=new Date();
        int[][] day={{0,30,28,31,30,31,30,31,31,30,31,30,31},
                        {0,31,29,31,30,31,30,31,31,30,31,30,31}};
        int year=date.getYear()+1900;
        if(year%4==0 && year%100!=0 || year%400==0)
        {
            return day[1][month]+"";
        }
        else
        {
            return day[0][month]+"";
        }
    }

    /**
     * 返回指定年份中指定月份的天数
     * @param 年份year
     * @param 月份month
     * @return 指定月的总天数
     */
    public static String getMonthLastDay(int year,int month)
    {
        int[][] day={{0,30,28,31,30,31,30,31,31,30,31,30,31},
                        {0,31,29,31,30,31,30,31,31,30,31,30,31}};
        if(year%4==0 && year%100!=0 || year%400==0)
        {
            return day[1][month]+"";
        }
        else
        {
            return day[0][month]+"";
        }
    }

	/**
	 * 计算日期加上天数
	 *
	 * @param date
	 * @param day
	 * @return
	 */
	public static String addDate(String curDate, int day) {
		String temp = "";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = formatter.parse(curDate);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, day);
			temp = formatter.format(calendar.getTime());
			System.out.println(temp);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return temp;
	}

	/**
	 * 将String型格式化,比如想要将2011-11-11格式化成2011年11月11日,
	 * 就StringPattern("2011-11-11","yyyy年MM月dd日")
	 * @param date 想要格式化的日期
	 * @param newPattern 想要格式化成什么格式
	 * @return String
	 * @author shaosuone
	 * @date 2017-3-29 上午11:42:05
	 */
	public static String StringPattern(String date, String newPattern) {
		return getDateTime(newPattern,stringToDateTime(date));
		/*if (date == null || oldPattern == null || newPattern == null)
			return "";
		SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern); // 实例化模板对象
		SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern); // 实例化模板对象
		Date d = null;
		try {
			d = sdf1.parse(date); // 将给定的字符串中的日期提取出来
		} catch (Exception e) { // 如果提供的字符串格式有错误，则进行异常处理
			e.printStackTrace(); // 打印异常信息
		}
		return sdf2.format(d);*/
	}

	/**
	 * 计算两个时间之间的毫秒值(自定义转换类型) liss
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long timeReduction(String beginDate, String endDate, String pattern) {
		SimpleDateFormat dfs = new SimpleDateFormat(pattern);
		long between = 0;
		try {
			Date begin = dfs.parse(beginDate);
			Date end = dfs.parse(endDate);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return between;
	}

	/**
	 * 计算两个时间之间的毫秒值 liss
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long timeReduction(String beginDate, String endDate) {
		SimpleDateFormat dfs = new SimpleDateFormat(YYYYMMDDHHMMSS);
		long between = 0;
		try {
			Date begin = dfs.parse(beginDate);
			Date end = dfs.parse(endDate);
			between = (end.getTime() - begin.getTime());// 得到两者的毫秒数
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return between;
	}

	/**
	 * 计算两个时间之间的天数
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getBetweenDays(String beginDate, String endDate) {
		long between = timeReduction(beginDate, endDate);
		long day = between / (24 * 60 * 60 * 1000);
		;
		return day;
	}

	/**
	 * 计算两个时间之间的小时
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getBetweenHours(String beginDate, String endDate) {
		long between = timeReduction(beginDate, endDate);
		long hour = between / (60 * 60 * 1000);
		return hour;
	}

	/**
	 * 计算两个时间之间的分钟数
	 *
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static long getBetweenMins(String beginDate, String endDate) {
		long between = timeReduction(beginDate, endDate);
		long min = between / (60 * 1000);
		return min;
	}

	/**
	 *
	 * @param strDate
	 * @return
	 *//*
	public static Date parseDate(String strDate) {
		return parseDate(strDate, null);
	}

	*//**
	 * parseDate
	 *
	 * @param strDate
	 * @param pattern
	 * @return
	 *//*
	public static Date parseDate(String strDate, String pattern) {
		Date date = null;
		try {
			if (pattern == null) {
				pattern = YYYYMMDD;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			date = format.parse(strDate);
		} catch (Exception e) {
			log.error("parseDate error:" + e);
		}
		return date;
	}

	*//**
	 * format date
	 *
	 * @param date
	 * @return
	 *//*
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	*//**
	 * format date
	 *
	 * @param date
	 * @param pattern
	 * @return
	 *//*
	public static String formatDate(Date date, String pattern) {
		String strDate = null;
		try {
			if (pattern == null) {
				pattern = YYYYMMDD;
			}
			SimpleDateFormat format = new SimpleDateFormat(pattern);
			strDate = format.format(date);
		} catch (Exception e) {
			log.error("formatDate error:", e);
		}
		return strDate;
	}
*/


	/**
	 * 取得当天日期是周几
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.DAY_OF_WEEK);
		return week_of_year - 1;
	}

	/**
	 * 取得一年的第几周
	 *
	 * @param date
	 * @return
	 */
	public static int getdayOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayof_year = c.get(Calendar.DAY_OF_YEAR);
		return dayof_year;
	}

	/**
	 * 取得一年的第几周
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week_of_year = c.get(Calendar.WEEK_OF_YEAR);
		return week_of_year;
	}

	/**
	 * getWeekBeginAndEndDate
	 *
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String getWeekBeginAndEndDate(Date date, String pattern) {
		Date monday = getMondayOfWeek(date);
		Date sunday = getSundayOfWeek(date);
		return getDateTime(pattern,monday) + " - " + getDateTime(pattern,sunday);
	}

	/**
	 * 根据日期取得对应周周一日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar monday = Calendar.getInstance();
		monday.setTime(date);
		monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		monday.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return monday.getTime();
	}

	/**
	 * 根据日期取得对应周周日日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		Calendar sunday = Calendar.getInstance();
		sunday.setTime(date);
		sunday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		sunday.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sunday.getTime();
	}

	/**
	 * 取得月的剩余天数
	 *
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfMonth(Date date) {
		int dayOfMonth = getDayOfMonth(date);
		int day = getPassDayOfMonth(date);
		return dayOfMonth - day;
	}

	/**
	 * 取得月已经过的天数
	 *
	 * @param date
	 * @return
	 */
	public static int getPassDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月天数
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取得月第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得月最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return c.getTime();
	}

	/**
	 * 取得季度第一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDateOfSeason(Date date) {
		return getFirstDateOfMonth(getSeasonDate(date)[0]);
	}

	/**
	 * 取得季度最后一天
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfSeason(Date date) {
		return getLastDateOfMonth(getSeasonDate(date)[2]);
	}

	/**
	 * 取得季度天数
	 *
	 * @param date
	 * @return
	 */
	public static int getDayOfSeason(Date date) {
		int day = 0;
		Date[] seasonDates = getSeasonDate(date);
		for (Date date2 : seasonDates) {
			day += getDayOfMonth(date2);
		}
		return day;
	}

	/**
	 * 取得季度剩余天数
	 *
	 * @param date
	 * @return
	 */
	public static int getRemainDayOfSeason(Date date) {
		return getDayOfSeason(date) - getPassDayOfSeason(date);
	}

	/**
	 * 取得季度已过天数
	 *
	 * @param date
	 * @return
	 */
	public static int getPassDayOfSeason(Date date) {
		int day = 0;

		Date[] seasonDates = getSeasonDate(date);

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);

		if (month == Calendar.JANUARY || month == Calendar.APRIL || month == Calendar.JULY || month == Calendar.OCTOBER) {// 季度第一个月
			day = getPassDayOfMonth(seasonDates[0]);
		} else if (month == Calendar.FEBRUARY || month == Calendar.MAY || month == Calendar.AUGUST || month == Calendar.NOVEMBER) {// 季度第二个月
			day = getDayOfMonth(seasonDates[0]) + getPassDayOfMonth(seasonDates[1]);
		} else if (month == Calendar.MARCH || month == Calendar.JUNE || month == Calendar.SEPTEMBER || month == Calendar.DECEMBER) {// 季度第三个月
			day = getDayOfMonth(seasonDates[0]) + getDayOfMonth(seasonDates[1]) + getPassDayOfMonth(seasonDates[2]);
		}
		return day;
	}

	/**
	 * 取得季度月
	 *
	 * @param date
	 * @return
	 */
	public static Date[] getSeasonDate(Date date) {
		Date[] season = new Date[3];

		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int nSeason = getSeason(date);
		if (nSeason == 1) {// 第一季度
			c.set(Calendar.MONTH, Calendar.JANUARY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.FEBRUARY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MARCH);
			season[2] = c.getTime();
		} else if (nSeason == 2) {// 第二季度
			c.set(Calendar.MONTH, Calendar.APRIL);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.MAY);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.JUNE);
			season[2] = c.getTime();
		} else if (nSeason == 3) {// 第三季度
			c.set(Calendar.MONTH, Calendar.JULY);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.AUGUST);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.SEPTEMBER);
			season[2] = c.getTime();
		} else if (nSeason == 4) {// 第四季度
			c.set(Calendar.MONTH, Calendar.OCTOBER);
			season[0] = c.getTime();
			c.set(Calendar.MONTH, Calendar.NOVEMBER);
			season[1] = c.getTime();
			c.set(Calendar.MONTH, Calendar.DECEMBER);
			season[2] = c.getTime();
		}
		return season;
	}

	/**
	 *
	 * 1 第一季度 2 第二季度 3 第三季度 4 第四季度
	 *
	 * @param date
	 * @return
	 */
	public static int getSeason(Date date) {

		int season = 0;

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH);
		switch (month) {
		case Calendar.JANUARY:
		case Calendar.FEBRUARY:
		case Calendar.MARCH:
			season = 1;
			break;
		case Calendar.APRIL:
		case Calendar.MAY:
		case Calendar.JUNE:
			season = 2;
			break;
		case Calendar.JULY:
		case Calendar.AUGUST:
		case Calendar.SEPTEMBER:
			season = 3;
			break;
		case Calendar.OCTOBER:
		case Calendar.NOVEMBER:
		case Calendar.DECEMBER:
			season = 4;
			break;
		default:
			break;
		}
		return season;
	}

	/* 两个日期相差多少秒
     *
     * @param date1
     * @param date2
     * @return
     */
   public static int getTimeDelta(Date date1,Date date2){
       long timeDelta=(date1.getTime()-date2.getTime())/1000;//单位是秒
       int secondsDelta=timeDelta>0?(int)timeDelta:(int)Math.abs(timeDelta);
       return secondsDelta;
   }

   /***
    * 两个日期相差多少秒
    * @param dateStr1  :yyyy-MM-dd HH:mm:ss
    * @param dateStr2 :yyyy-MM-dd HH:mm:ss

    */
   public static int getTimeDelta(String dateStr1,String dateStr2){
       return getTimeDelta(stringToDateTime(dateStr1), stringToDateTime(dateStr2));
   }

    /**
     * 取得当前时间的日戳
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getTimestamp(){
        Date date=new Date();
        String timestamp=""+(date.getYear()+1900)+date.getMonth()+date.getDate()+date.getMinutes()+date.getSeconds()+date.getTime();
        return timestamp;
    }
    /**
     * 取得指定时间的日戳
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getTimestamp(Date date){
        String timestamp=""+(date.getYear()+1900)+date.getMonth()+date.getDate()+date.getMinutes()+date.getSeconds()+date.getTime();
        return timestamp;
    }

    public static void main(String[] args){
        /*System.out.println(DateUtil.getDate());//获取日期格式为2010-08-12
        System.out.println(DateUtil.getDateTime());//获取日期格式为2010-08-12 18:08:21
        System.out.println(DateUtil.getTime());//获取日期格式为18:08:21
        System.out.println(DateUtil.getYear());//获取当前时间年份2010
        System.out.println(DateUtil.getMonth());//获取当年时间月份08
        System.out.println(DateUtil.getStartDate());//获取2010-01-01
        System.out.println(DateUtil.getEndDate());//2010-08-12
        System.out.println(DateUtil.getDay());//获得服务器在当前月中已经过了的天数12
        System.out.println(DateUtil.getMargin("2010-05-02", "2010-04-01"));//比较两个日期相差的天数
        System.out.println(DateUtil.getDoubleMargin("2010-05-07 23:22:11", "2010-04-01 01:33:33"));  */

    	System.out.println(getDateTime(HHMMSS,stringToDateTime("2010-05-07 23:22:11")));
    }
}
