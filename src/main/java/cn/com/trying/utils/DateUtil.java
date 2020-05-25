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
 * @author
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
     * 将两个字符串格式的日期进行比较
     * @param last 要比较的第一个日期字符串
     * @param now   要比较的第二个日期格式字符串
     * @return true(last 在now 日期之前),false(last 在now 日期之后)
     */
    public static int compareTo(String last, String now) {
        	return compareTo(last,now,YYYYMMDDHHMMSS) ;
    }


	public static int compareTo(String last, String now,String pattern) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			Date temp1 = formatter.parse(last);
			Date temp2 = formatter.parse(now);
			if (temp1.after(temp2)){
				return -1;
			}

			else if (temp1.before(temp2)){
				return 1;
			}

			else {
				return 0;
			}

		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return -1;
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
	}



	/**
	 * 取得当天日期是周几
	 *
	 * @param date
	 * @return
	 */
	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int day_of_week = c.get(Calendar.DAY_OF_WEEK);
		return day_of_week - 1;
	}

	/**
	 * 取得一年的第几天
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
	 * 根据日期取得对应周周一日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		return getDayOfWeek(date, Calendar.MONDAY);
	}

	public static Date getThursdayOfWeek(Date date) {
		return getDayOfWeek(date, Calendar.THURSDAY);
	}
	/**
	 * 根据日期取得对应周周日日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getSundayOfWeek(Date date) {
		return getDayOfWeek(date, Calendar.SUNDAY);
	}

	/**
	* @Title:
	* @Description:  获取指定时间所在周的星期几的日期 如获取周四的日期 getDayOfWeek(date, Calendar.THURSDAY)
	* @param
	* @return
	* @author huxx
	* @date 2019/12/12 下午4:18
	* @update
	*/
	public static Date getDayOfWeek(Date date,int dayName) {
		Calendar monday = Calendar.getInstance();
		monday.setTime(date);
		monday.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		monday.set(Calendar.DAY_OF_WEEK, dayName);
		return monday.getTime();
	}

	public static int weekOfMonth(Date date){
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);
		int week_of_year = c.get(Calendar.WEEK_OF_MONTH);
		return week_of_year;
	}


	/**
	* @Title:
	* @Description: 判断一月有几个周
	* @param
	* @return
	* @author huxx
	* @date 2019/12/12 下午4:44
	* @update
	*/
	public static int getWeekCountOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(FIRST_DAY_OF_WEEK);

		//最大周数
		int max = c.getActualMaximum(Calendar.WEEK_OF_MONTH);

		//判断本月的第一天所在周所属月份
		c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));
		Date firstDay = c.getTime();
		int firstMonth = getMonthOfWeek(firstDay);

		//判断本月的最后一天所在周所属月份
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		Date lastDay = c.getTime();
		int lastMonth = getMonthOfWeek(lastDay);

		//获取本月
		int month = getMonth(date);

		if(firstMonth != month){
			max = max -1;
		}

		if(lastMonth != month){
			max = max -1;
		}
		return max;
	}

	/**
	* @Title:
	* @Description: 获取日期所在周所属的月份
	* @param
	* @return
	* @author huxx
	* @date 2019/12/12 下午4:49
	* @update
	*/
	public static int getMonthOfWeek(Date date){
		//获取所在周的周四的日期
		Date thursday = getDayOfWeek(date,Calendar.THURSDAY);
		//判断周四在那个月，本周就属于那个月
		return getMonth(thursday);
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
	/**
	* @Title:
	* @Description: 获取指定年份的周数
	* @param
	* @return
	* @author huxx
	* @date 2019/12/12 下午4:15
	* @update
	*/
	public static int getWeekCountOfYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.WEEK_OF_YEAR);
	}


    public static void main(String[] args){
        String dateStr = "2019-12-29";
        Date date = stringToDate(dateStr);
        int day_of_month =  getDayOfMonth(date);
        int week_of_year = getWeekOfYear(date);


		int dd = getWeekCountOfYear(date);

		int weekNum = getWeekCountOfMonth(date);
		int ddd = weekOfMonth(date);
    	System.out.println(getDateTime(HHMMSS,stringToDateTime("2010-05-07 23:22:11")));
    }
}
