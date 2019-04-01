package intellinectsschool.intellinects.com.v4flavors.Utils;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import intellinectsschool.intellinects.com.v4flavors.R;


import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by Indrajeet  on 26-05-2017
 */

public class Util {

    private static String[] months = new String[]{"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};

    private Util() {}

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.equalsIgnoreCase("")
                || input.trim().length() == 0;
    }

    public static String getDateFromTimestamp(long timeStamp) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.

        DateFormat objFormatter;
        objFormatter = new SimpleDateFormat("dd-MM-yyyy");
        objFormatter.setTimeZone(tz);

        Calendar objCalendar =
                Calendar.getInstance(tz);
        objCalendar.setTimeInMillis(timeStamp * 1000);//edit
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        return result;
    }

    public static String getDateFromTimestampWhole(long timeStamp) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.

        DateFormat objFormatter;
        objFormatter = new SimpleDateFormat("yyyy-MM-dd");
        objFormatter.setTimeZone(tz);

        Calendar objCalendar =
                Calendar.getInstance(tz);
        objCalendar.setTimeInMillis(timeStamp * 1000);//edit
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        return result;
    }

    public static String getMonthFrom(int id) {
        return months[id];
    }

    public static String getDateTimeFromTimestamp(long timeStamp) {
        Calendar cal = Calendar.getInstance();
        TimeZone tz = cal.getTimeZone();//get your local time zone.

        DateFormat objFormatter;
        objFormatter = new SimpleDateFormat("hh:mm aa");
        objFormatter.setTimeZone(tz);

        Calendar objCalendar =
                Calendar.getInstance(tz);
        objCalendar.setTimeInMillis(timeStamp * 1000);//edit
        String result = objFormatter.format(objCalendar.getTime());
        objCalendar.clear();
        return result;
    }

    public static String getWeekDay(int id) {
        String weekDay = "";
        Calendar c = Calendar.getInstance();

        if (Calendar.MONDAY == id) {
            weekDay = "MON";
        } else if (Calendar.TUESDAY == id) {
            weekDay = "TUE";
        } else if (Calendar.WEDNESDAY == id) {
            weekDay = "WED";
        } else if (Calendar.THURSDAY == id) {
            weekDay = "THU";
        } else if (Calendar.FRIDAY == id) {
            weekDay = "FRI";
        } else if (Calendar.SATURDAY == id) {
            weekDay = "SAT";
        } else if (Calendar.SUNDAY == id) {
            weekDay = "SUN";
        }

        return weekDay;
    }


    private void createInternalDirectories(Context context) {
        File f = new File(Environment.getExternalStorageDirectory(), "Divine Child CBSE");//Constants.APP_NAME);
        if (!f.exists()) {
            f.mkdirs();
        }

        //create directory
        File downloads = new File(Environment.getExternalStorageDirectory() + "/"
                + "Divine Child CBSE", context.getString(R.string.downloads));

        if (!downloads.exists()) {
            downloads.mkdirs();
        }
        Log.e("Directories", "Created");
    }

}
