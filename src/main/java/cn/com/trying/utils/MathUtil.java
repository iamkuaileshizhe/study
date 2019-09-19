package cn.com.trying.utils;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @Title: MathUtil
 * @Description: 数据运算类
 * @author huxx
 * @date 2018-01-04 10:55
 * @update
 */
public class MathUtil {

	/**
	 * @Title:
	 * @Description: 两个数进行相乘运算，结果保留默认位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-08 11:13
	 * @update
	 */
	public static String mutiply(String num1, String num2) {
		return mutiply(num1, num2, 4);
	}

	/**
	 * @Title:
	 * @Description: 两个数进行相加运算，结果保留默认位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-04 10:55
	 * @update
	 */
	public static String add(String num1, String num2) {
		return add(num1, num2, 4);
	}

	/**
	 * @Title:
	 * @Description: 两个数进行相减运算，结果保留默认位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-04 10:55
	 * @update
	 */
	public static String sub(String num1, String num2) {
		return sub(num1, num2, 4);
	}

	/**
	 * @Title:
	 * @Description: 两个数进行相除运算，结果保留默认位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-04 10:55
	 * @update
	 */
	public static String div(String num1, String num2) {
		return div(num1, num2, 4);
	}

	/**
	 * @Title:
	 * @Description: 两个数进行相乘运算，结果保留scale位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-04 10:55
	 * @update
	 */
	public static String mutiply(String num1, String num2, int scale) {
		if (StringUtils.isBlank(num1)) {
			num1 = "0";
		}
		if (StringUtils.isBlank(num2)) {
			num2 = "0";
		}
		BigDecimal n1 = new BigDecimal(num1);
		BigDecimal n2 = new BigDecimal(num2);
		BigDecimal n = n1.multiply(n2);
		String str = n.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
		return str;
	}

	/**
	 * @Title:
	 * @Description: 两个数进行相加运算，结果保留scale位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-04 10:55
	 * @update
	 */
	public static String add(String num1, String num2, int scale) {
		if (StringUtils.isBlank(num1)) {
			num1 = "0";
		}
		if (StringUtils.isBlank(num2)) {
			num2 = "0";
		}
		BigDecimal n1 = new BigDecimal(num1);
		BigDecimal n2 = new BigDecimal(num2);
		BigDecimal n = n1.add(n2);
		String str = n.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
		return str;
	}

	/**
	 * @Title:
	 * @Description: 两个数进行相减运算，结果保留scale位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-04 10:55
	 * @update
	 */
	public static String sub(String num1, String num2, int scale) {
		if (StringUtils.isBlank(num1)) {
			num1 = "0";
		}
		if (StringUtils.isBlank(num2)) {
			num2 = "0";
		}
		BigDecimal n1 = new BigDecimal(num1);
		BigDecimal n2 = new BigDecimal(num2);
		BigDecimal n = n1.subtract(n2);
		String str = n.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
		return str;
	}

	/**
	 * @Title:
	 * @Description: 两个数进行相除运算，结果保留scale位小数
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-04 10:55
	 * @update
	 */
	public static String div(String num1, String num2, int scale) {
		if (StringUtils.isBlank(num1)) {
			num1 = "0";
		}
		if (StringUtils.isBlank(num2)) {
			num2 = "0";
		}
		BigDecimal n1 = new BigDecimal(num1);
		BigDecimal n2 = new BigDecimal(num2);
		BigDecimal n = n1.divide(n2, scale, BigDecimal.ROUND_HALF_UP);
		String str = n.toString();
		return str;
	}

	/**
	 * @Title:
	 * @Description: 将数据转成保留指定位数的数字格式
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-08 14:38
	 * @update
	 */
	public static String getScale(String num1, int scale) {
		if (num1 == null || num1.length() < 1) {
			num1 = "0";
		}
		BigDecimal n1 = new BigDecimal(num1);

		String str = n1.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
		return str;
	}

	/**
	 * @Title:
	 * @Description: 默认将数据转成两位的数据格式
	 * @param
	 * @return
	 * @author huxx
	 * @date 2018-01-08 14:38
	 * @update
	 */
	public static String getScale(String num1) {
		return getScale(num1, 2);
	}

	/**
	 * @Title: getScaleDateTime
	 * @Description: 获取两个时间段的比对相差多少天多少小时多少分钟多少秒
	 * @param time1
	 * @param time2
	 * @return String
	 * @date: 2018年3月7日 下午4:23:50
	 * @user: oushm
	 */
	public static String getScaleDateTime(String time1, String time2) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tTime = "";
		try {
			String str = getDatePoor(format.parse(time1), format.parse(time2));
			String[] timeValue = str.split(",");
			if (!"0".equals(timeValue[0].trim())) {
				tTime = timeValue[0] + "天" + timeValue[1] + "小时" + timeValue[2] + "分";
			} else {
				if ("0".equals(timeValue[1].trim())) {
					tTime = timeValue[2] + "分";
				} else {
					tTime = timeValue[1] + "小时" + timeValue[2] + "分";
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return tTime;
	}

	/**
	 * @Title: getDatePoor
	 * @Description: 计算两个时间差
	 * @param endDate
	 * @param nowDate
	 * @return String
	 * @date: 2018年3月7日 下午4:24:41
	 * @user: oushm
	 */
	public static String getDatePoor(Date endDate, Date nowDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		long nm = 1000 * 60;
		// long ns = 1000;
		// 获得两个时间的毫秒时间差异
		long diff = endDate.getTime() - nowDate.getTime();
		// 计算差多少天
		long day = diff / nd;
		// 计算差多少小时
		long hour = diff % nd / nh;
		// 计算差多少分钟
		long min = diff % nd % nh / nm;
		// 计算差多少秒//输出结果
		// long sec = diff % nd % nh % nm / ns;
		return day + "," + hour + "," + min;
	}

	/**
	 * @Title: getDaysList
	 * @Description: 获取当前日期的前几天集合
	 * @param intervals
	 * @return ArrayList<String>
	 * @date: 2018年3月24日 下午5:11:55
	 * @user: oushm
	 */
	public static ArrayList<String> getDaysList(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		ArrayList<String> fetureDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i));
			fetureDaysList.add(getFetureDate(i));
		}
		return pastDaysList;
	}

	/**
	 * @Title: getFetureDate
	 * @Description: 获取未来 第 past 天的日期
	 * @param past
	 * @return String
	 * @date: 2018年3月24日 下午5:12:39
	 * @user: oushm
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * @Title: getPastDate
	 * @Description: 获取过去第几天的日期
	 * @param past
	 * @return String
	 * @date: 2018年3月24日 下午5:12:54
	 * @user: oushm
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
}
