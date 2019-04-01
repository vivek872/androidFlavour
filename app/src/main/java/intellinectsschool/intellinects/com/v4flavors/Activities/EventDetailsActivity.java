package intellinectsschool.intellinects.com.v4flavors.Activities;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import intellinectsschool.intellinects.com.v4flavors.Other.Constants;
import intellinectsschool.intellinects.com.v4flavors.Other.DatabaseConstant;
import intellinectsschool.intellinects.com.v4flavors.Other.DrawablePainter;
import intellinectsschool.intellinects.com.v4flavors.R;
import intellinectsschool.intellinects.com.v4flavors.Utils.Util;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EventDetailsActivity extends AppCompatActivity {

    TextView tv_month, tv_date, tv_title, tv_time, tv_contact_name, tv_contact_number, tv_venue, tv_event_description;
    String month, date, title, start_time, end_time, contact_name, contact_number, venue, event_description,i;
    ImageView iv_1, iv_2, iv_3, iv_4, iv_5, iv_6,imageViewBack;
    Boolean isAllDay = false;
    int weekday;
    LinearLayout layout_venue, layout_contact_name, layout_contact_number, layout_description, layout_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        TextView tv_header_text = findViewById(R.id.header_text);
        tv_header_text.setText(R.string.event_details);
        DrawablePainter painter = new DrawablePainter(this);
        painter.paintDrawables();
        initDetails();

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        getEventDetails();
    }

    private void getEventDetails() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("result") != null){
            try {
                JSONObject jsonObject = new JSONObject(bundle.getString("result"));
                title = jsonObject.getString("postTitle");
                month = getDate(jsonObject.getInt("postDate"), "month");
                date = getDate(jsonObject.getInt("postDate"), "date");
                event_description = jsonObject.getString("postContent");
                String s = jsonObject.getString("eventTimeline");
                JSONObject object = new JSONObject(s);
                contact_name = object.getString("contactName");
                contact_number = object.getString("contactPhone");
                venue = object.getString("venue");
                start_time = Util.getDateTimeFromTimestamp(Long.parseLong(object.getString("startDate")));
                end_time = Util.getDateTimeFromTimestamp(Long.parseLong(object.getString("endDate")));
                isAllDay = Boolean.valueOf(object.getString("allDay"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            i=bundle.getString("mycolor");

//            layout_title.setBackground(Drawable.createFromPath("#fdfdfd"));
           // layout_title.setBackground(Drawable.createFromPath(bundle.getString("mycolor","")));
            title = bundle.getString(DatabaseConstant.EVENT_EXTRA_KEY_TITLE);
            month = getDate(bundle.getLong(DatabaseConstant.EVENT_EXTRA_KEY_EVENTDATE), "month");
            date = getDate(bundle.getLong(DatabaseConstant.EVENT_EXTRA_KEY_EVENTDATE), "date");
            start_time = Util.getDateTimeFromTimestamp(bundle.getLong(DatabaseConstant.EVENT_EXTRA_KEY_START_DATE));
            end_time = Util.getDateTimeFromTimestamp(bundle.getLong(DatabaseConstant.EVENT_EXTRA_KEY_END_DATE));
            venue = bundle.getString(DatabaseConstant.EVENT_EXTRA_KEY_VENUE);
            contact_name = bundle.getString(DatabaseConstant.EVENT_EXTRA_KEY_CONTACT_NAME);
            contact_number = bundle.getString(DatabaseConstant.EVENT_EXTRA_KEY_CONTACT_PHONE);
            event_description = bundle.getString(DatabaseConstant.EVENT_EXTRA_KEY_DESCRIPTION);
            Log.e("Venue", "a" + venue + "a");
            Log.e("CName", "a" + contact_name + "a");
            Log.e("CNum", "a" + contact_number + "a");
            Log.e("Desc", "a" + event_description + "a");
            isAllDay = bundle.getBoolean(DatabaseConstant.EVENT_EXTRA_KEY_END_ISALL_DAY);
        }
        setData();
    }

    @SuppressLint("SetTextI18n")
    private void setData() {
        if (isAllDay){
            tv_time.setText(R.string.all_day);
        }else {
            if (start_time.equalsIgnoreCase(end_time)){
                tv_time.setText("All Day");
            }else {
                tv_time.setText(start_time + " - " + end_time);
            }
        }
        tv_date.setText(date);
        tv_month.setText(month);
        if (!venue.equals("") && !venue.equals(" ") && venue != null){
            layout_venue.setVisibility(View.VISIBLE);
            tv_venue.setText(""+venue);
        }else {
            layout_venue.setVisibility(View.VISIBLE);
            tv_venue.setText(""+"");

        }
        if (!contact_name.equals("") && !contact_name.equals(" ") && contact_name != null){
            layout_contact_name.setVisibility(View.VISIBLE);
            tv_contact_name.setText(""+contact_name);
        }else {
            layout_contact_name.setVisibility(View.VISIBLE);
            tv_contact_name.setText(""+"");

        }
        if (!contact_number.equals("") && !contact_number.equals(" ") && contact_number != null){
            layout_contact_number.setVisibility(View.VISIBLE);
            tv_contact_number.setText(""+contact_number);
        }else {
            layout_contact_number.setVisibility(View.VISIBLE);
            tv_contact_number.setText(""+"");

        }
        if (!event_description.equals("") && !event_description.equals(" ") && event_description != null){
            layout_description.setVisibility(View.VISIBLE);
            tv_event_description.setText(""+event_description);
        }else {
            layout_description.setVisibility(View.VISIBLE);
            tv_event_description.setText(""+"");

        }
        tv_title.setText(title);
    }

    private String getDate(long dateLong, String month) {
        String day = null;
        String f_date = Util.getDateFromTimestamp(dateLong);
        String[] final_d = f_date.split("-");
        String sMonth = Util.getMonthFrom(Integer.parseInt(final_d[1])-1);
        String sDate = final_d[0];
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
        try {
            Date date = format.parse(f_date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            weekday = calendar.get(Calendar.DAY_OF_WEEK);
            day = Util.getWeekDay(weekday);
            if (day.equalsIgnoreCase("mon")){
                day = "Monday";
            }
            if (day.equalsIgnoreCase("tue")){
                day = "Tuesday";
            }
            if (day.equalsIgnoreCase("wed")){
                day = "Wednesday";
            }
            if (day.equalsIgnoreCase("thu")){
                day = "Thursday";
            }
            if (day.equalsIgnoreCase("fri")){
                day = "Friday";
            }
            if (day.equalsIgnoreCase("sat")){
                day = "Saturday";
            }
            if (day.equalsIgnoreCase("sun")){
                day = "Sunday";
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (month.equalsIgnoreCase("month")){
            return sMonth;
        }else if (month.equalsIgnoreCase("date")){
            return day + ", " + sDate;
        }else {
            return null;
        }
    }

    private void initDetails() {
        imageViewBack=findViewById(R.id.iv_back);
        tv_month = findViewById(R.id.event_month);
        tv_date = findViewById(R.id.event_date);
        tv_title = findViewById(R.id.event_title);
        tv_time = findViewById(R.id.event_time);
        tv_contact_name = findViewById(R.id.event_contact_name);
        tv_contact_number = findViewById(R.id.event_contact_number);
        tv_venue = findViewById(R.id.event_venue);
        tv_event_description = findViewById(R.id.event_description);

        iv_1 = findViewById(R.id.event_details_ic_1);
        iv_2 = findViewById(R.id.event_details_ic_2);
        iv_3 = findViewById(R.id.event_details_ic_3);
        iv_4 = findViewById(R.id.event_details_ic_4);
        iv_5 = findViewById(R.id.event_details_ic_5);
        iv_6 = findViewById(R.id.event_details_ic_6);

        iv_1.setImageDrawable(Constants.INSTANCE.getCALENDER_ICON());
        iv_2.setImageDrawable(Constants.INSTANCE.getEVENTS_CLOCK());
        iv_3.setImageDrawable(Constants.INSTANCE.getVENUE_ICON());
        iv_4.setImageDrawable(Constants.INSTANCE.getUSER_ICON());
        iv_5.setImageDrawable(Constants.INSTANCE.getPHONE_ICON());
        iv_6.setImageDrawable(Constants.INSTANCE.getDESCRIPTION_ICON());

        layout_venue = findViewById(R.id.event_details_layout_venue);
        layout_contact_name = findViewById(R.id.event_details_layout_contact_name);
        layout_contact_number = findViewById(R.id.event_details_layout_contact_number);
        layout_description = findViewById(R.id.event_details_layout_description);
        layout_title=findViewById(R.id.ll_title_head);
    }

    public void BackToPrevious(View view) {
        onBackPressed();
        finish();
    }
}
