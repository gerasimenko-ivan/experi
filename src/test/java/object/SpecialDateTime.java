package object;

import helpers.DateHelper;
import helpers.RegExpHelper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpecialDateTime implements Comparable {
    public LocalDate date;
    public Integer hour;
    public Integer minute;

    public SpecialDateTime(LocalDate date) {
        this.date = date;
    }

    public SpecialDateTime(String date) {
        this.date = DateHelper.getFromDDMMYYYY(date);
    }

    public SpecialDateTime(SpecialDateTime specialDateTime) {
        this.date = specialDateTime.date;
        this.hour = specialDateTime.hour;
        this.minute = specialDateTime.minute;
    }

    public SpecialDateTime withHour (Integer hour) {
        this.hour = hour;
        return this;
    }

    public SpecialDateTime withMinute (Integer minute) {
        this.minute = minute;
        return this;
    }

    public SpecialDateTime withTime (String time) {
        Pattern patternTime = Pattern.compile("^\\d{2}:\\d{2}$");
        Matcher matcher = patternTime.matcher(time);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Time = '" + time + "' does not match pattern = '" + patternTime + "'");
        }
        this.hour = Integer.valueOf(time.substring(0, 2));
        this.minute = Integer.valueOf(time.substring(3, 5));
        return this;
    }

    public String getTime24() {
        String hourStr, minuteStr;
        if (hour == null)
            hourStr = "--";
        else
            hourStr = String.format("%02d", hour);
        if (minute == null)
            minuteStr = "--";
        else
            minuteStr = String.format("%02d", minute);
        return hourStr + ":" + minuteStr;
    }

    public String getDate() {
        if (date == null)
            return "--.--.----";
        else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.LL.yyyy");
            return date.format(formatter);
        }
    }

    public static SpecialDateTime parse(String formatDDMMYYYY_HHMM) {
        SpecialDateTime specialDateTime = null;
        String dateText = RegExpHelper.getSubstring("\\d{2}\\.\\d{2}\\.\\d{4}", formatDDMMYYYY_HHMM);
        if (dateText != null)
            specialDateTime = new SpecialDateTime(dateText);
        else
            throw new IllegalArgumentException("Дата вида DD.MM.YYYY не найдена во входной строке '" + formatDDMMYYYY_HHMM + "'");

        String time = RegExpHelper.getSubstring("\\d{2}:\\d{2}", formatDDMMYYYY_HHMM);
        if (time != null)
            specialDateTime.withTime(time);
        else
            throw new IllegalArgumentException("Время вида HH:MM не найдено во входной строке '" + formatDDMMYYYY_HHMM + "'");
        return specialDateTime;
    }

    public long getMillisecondsSinceEpoch() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonthValue() - 1, date.getDayOfMonth(), hour, minute);
        return calendar.getTimeInMillis();
    }

    public SpecialDateTime plusHours(int hours) {
        SpecialDateTime specialDateTime =
                new SpecialDateTime(this.date)
                        .withMinute(this.minute);
        int newHour = this.hour + hours;
        if (newHour > 23) {
            int addDays = newHour / 24;
            newHour = newHour % 24;
            specialDateTime.date = specialDateTime.date.plusDays(addDays);
            specialDateTime.withHour(newHour);
        } else if (newHour < 0) {
            int addDays = (newHour / 24) - 1;
            newHour = 24 + (newHour % 24);
            specialDateTime.date = specialDateTime.date.plusDays(addDays);
            specialDateTime.withHour(newHour);
        } else {
            specialDateTime.withHour(newHour);
        }
        return specialDateTime;
    }

    public SpecialDateTime plusMinutes(int minutes) {
        SpecialDateTime specialDateTime =
                new SpecialDateTime(this.date)
                        .withHour(this.hour)
                        .withMinute(this.minute);
        int newMinute = this.minute + minutes;
        if (newMinute > 59) {
            int addHours = newMinute / 60;
            newMinute = newMinute % 60;
            specialDateTime = specialDateTime.plusHours(addHours);
            specialDateTime.withMinute(newMinute);
        } else if (newMinute < 0) {
            int addHours = (newMinute / 60) - 1;
            newMinute = 60 + (newMinute % 60);
            specialDateTime = specialDateTime.plusHours(addHours);
            specialDateTime.withMinute(newMinute);
        } else {
            specialDateTime.withMinute(newMinute);
        }
        return specialDateTime;
    }

    public void roundToMinutesUp(int roundingStep) {
        int minuteDelta = this.minute % roundingStep;
        if (minuteDelta == 0)
            return;
        SpecialDateTime newSpecialDateTime = this.plusMinutes(roundingStep - minuteDelta);
        this.date = newSpecialDateTime.date;
        this.withHour(newSpecialDateTime.hour).withMinute(newSpecialDateTime.minute);
    }
    public void roundToMinutesDown(int roundingStep) {
        int minuteDelta = this.minute % roundingStep;
        if (minuteDelta == 0)
            return;
        SpecialDateTime newSpecialDateTime = this.plusMinutes(-minuteDelta);
        this.date = newSpecialDateTime.date;
        this.withHour(newSpecialDateTime.hour).withMinute(newSpecialDateTime.minute);
    }

    @Override
    public String toString() {
        return getDate() + " " + getTime24();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj instanceof SpecialDateTime) {
            SpecialDateTime specialDateTime = (SpecialDateTime)obj;
            if (this.toString().equals(specialDateTime.toString()))
                return true;
        }
        return false;
    }

    @Override
    public int compareTo(Object o) {
        SpecialDateTime specialDateTime = (SpecialDateTime)o;
        int compareDate = this.date.compareTo(specialDateTime.date);
        if (compareDate != 0)
            return compareDate;
        int compareHour = this.hour.compareTo(specialDateTime.hour);
        if (compareHour != 0)
            return compareHour;
        int compareMinute = this.minute.compareTo(specialDateTime.minute);
        return compareMinute;
    }
}
