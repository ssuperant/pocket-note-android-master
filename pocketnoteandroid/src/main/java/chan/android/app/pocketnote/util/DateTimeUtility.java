package chan.android.app.pocketnote.util;

import org.joda.time.DateTime;

public class DateTimeUtility {

  private static final String[] WEEKDAY = new String[]{
    "星期一",
    "星期二",
    "星期三",
    "星期四",
    "星期五",
    "星期六",
    "星期日",
  };
  private static final String[] MONTH = new String[]{
    "一月",
    "二月",
    "三月",
    "四月",
    "五月",
    "六月",
    "七月",
    "八月",
    "九月",
    "十月",
    "十一月",
    "十二月"
  };

  private DateTimeUtility() {
    throw new AssertionError("Oh come on");
  }

  public static String getReadableWeekDay(DateTime date) {
    return WEEKDAY[date.getDayOfWeek() - 1];
  }

  public static String getReadableWeekDay(int day) {
    return WEEKDAY[day - 1];
  }

  public static String getReadableMonth(int month) {
    return MONTH[month - 1];
  }

  public static int getNextDay(int day) {
    return (day % 7) + 1;
  }

  public static String[] getAllDaysCycleFrom(DateTime date) {
    int today = date.getDayOfWeek();
    int tomorrow = DateTimeUtility.getNextDay(today);
    String[] result = new String[8];
    result[0] = "今天 - " + getReminderReadableDate(date);
    result[1] = "明天 - " + getReminderReadableDate(date.plusDays(1));
    for (int i = 2; i < 7; ++i) {
      tomorrow = DateTimeUtility.getNextDay(tomorrow);
      result[i] = DateTimeUtility.getReadableWeekDay(tomorrow) + " - " + getReminderReadableDate(date.plusDays(i));
    }
    result[7] = "具体日期";
    return result;
  }

  public static String getReminderReadableDate(DateTime date) {
    StringBuilder sb = new StringBuilder();
    sb.append(date.getYear());
    sb.append("年 ");
    sb.append(getReadableMonth(date.getMonthOfYear()));
    sb.append(" ");
    sb.append(date.getDayOfMonth());
    sb.append(",");
    sb.append(getReadableWeekDay(date));
    sb.append(" ");
    return sb.toString();
  }

  public static String getReminderReadableTime(int hour, int minute) {
    StringBuilder sb = new StringBuilder();
    String period = "  ";
    if (hour == 0) {
      hour = 24;//设置24h格式
//      period = " AM";
//    } else if (hour == 12) {
//      period = " PM";
//    } else if (13 <= hour && hour <= 23) {
//      period = " PM";
//      hour = hour % 12;
    }

    if (hour < 10) {
      sb.append("0");
    }

    sb.append(hour);
    sb.append(":");

    if (minute < 10) {
      sb.append("0");
    }

    sb.append(minute);
    sb.append(period);
    return sb.toString();
  }
}
