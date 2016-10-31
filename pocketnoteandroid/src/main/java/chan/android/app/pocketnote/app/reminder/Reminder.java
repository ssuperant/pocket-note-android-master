package chan.android.app.pocketnote.app.reminder;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

public class Reminder {

  /**
   * Share across multiple reminder
   */
  private static final Gson GSON = new Gson();
  private Type type;
  private Repetition repetition = Repetition.ONE_TIME;
  private int whenIndex;
  private long begin;
  private long end;
  public Reminder(Type type, Repetition repetition, long begin, long end, int index) {
    this.type = type;
    this.repetition = repetition;
    this.begin = begin;
    this.end = end;
    this.whenIndex = index;

    if (type == Type.PIN_TO_STATUS_BAR) {
      this.repetition = Repetition.ONE_TIME;
      this.begin = 0;
      this.end = 0;
      this.whenIndex = 0;
    }
  }
  private Reminder() {
    // Internal use only
  }

  public static Reminder fromJson(String json) {
    return GSON.fromJson(json, Reminder.class);
  }

  public static String toJson(Reminder reminder) {
    return GSON.toJson(reminder);
  }

  public int getWhenIndex() {
    return whenIndex;
  }

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public Repetition getRepetition() {
    return repetition;
  }

  public void setRepetition(Repetition repetition) {
    this.repetition = repetition;
  }

  public long getBegin() {
    return begin;
  }

  public void setBegin(long begin) {
    this.begin = begin;
  }

  public long getEnd() {
    return end;
  }

  public void setEnd(long end) {
    this.end = end;
  }

  @Override
  public String toString() {
    return "提醒{" +
      "类型=" + type +
      ", 重复=" + repetition +
      ", 开始='" + begin + '\'' +
      ", 结束='" + end + '\'' +
      '}';
  }

  public boolean isExpired() {
    // Pin to status bar never expired
    if (type == Type.PIN_TO_STATUS_BAR) {
      return false;
    }

    if (end == 0 && repetition != Repetition.ONE_TIME) {
      return false;
    }

    long now = System.currentTimeMillis();
    if (repetition == Repetition.ONE_TIME) {
      return (now > begin);
    } else {
      return (now > end);
    }
  }

  public enum Repetition {
    ONE_TIME("一次", 0),
    HOURLY("每小时", TimeUnit.HOURS.toMillis(1)),
    DAILY("每日", TimeUnit.DAYS.toMillis(1)),
    WEEKLY("每周", TimeUnit.DAYS.toMillis(7)),
    MONTHLY("每月", TimeUnit.DAYS.toMillis(30)),
    YEARLY("每年", TimeUnit.DAYS.toMillis(365));

    final String description;
    final long milliseconds;

    Repetition(String description, long milliseconds) {
      this.description = description;
      this.milliseconds = milliseconds;
    }

    @Override
    public String toString() {
      return description;
    }

    public long getInterval() {
      return milliseconds;
    }
  }

  public enum Type {
    ALL_DAY("整天"),
    TIME_ALARM("定时"),
    PIN_TO_STATUS_BAR("加入到通知栏");

    final String description;

    Type(String description) {
      this.description = description;
    }

    @Override
    public String toString() {
      return description;
    }
  }
}
