package intellinectsschool.intellinects.com.v4flavors.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import intellinectsschool.intellinects.com.v4flavors.Activities.EventDetailsActivity;
import intellinectsschool.intellinects.com.v4flavors.Models.Event;
import intellinectsschool.intellinects.com.v4flavors.Other.Constants;
import intellinectsschool.intellinects.com.v4flavors.Other.DatabaseConstant;
import intellinectsschool.intellinects.com.v4flavors.R;
import intellinectsschool.intellinects.com.v4flavors.Utils.Util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Indrajeet  on 26-05-2017
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.RecyclerViewHolder> {

    private ArrayList<Object> arrayList;
    private int MONTH = 1;
    private Context context;

    public EventsAdapter(ArrayList<Object> events, Context context) {
        this.arrayList = events;
        this.context = context;
    }

    private class MonthLayoutHolder extends RecyclerViewHolder {

        MonthLayoutHolder(View itemView) {
            super(itemView);
        }

        void bindView(RecyclerViewHolder holder, int position) throws ParseException {
        Object object = arrayList.get(position);
        if (position == 0){
            holder.iv_clock.setVisibility(View.VISIBLE);
        }else {
            holder.iv_clock.setVisibility(View.GONE);
        }
        holder.tv_month.setText(object.toString());
    }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == MONTH){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_month_layout, parent, false);
            ImageView iv_clock = view.findViewById(R.id.iv_clock);
            iv_clock.setImageDrawable(Constants.INSTANCE.getEVENTS_CLOCK());
            return new MonthLayoutHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list_item, parent, false);
            return new RecyclerViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        try {
            if (holder instanceof MonthLayoutHolder) {
                MonthLayoutHolder monthLayoutHolder = (MonthLayoutHolder)holder;

                monthLayoutHolder.bindView(holder, position);
            } else {
                Object obj = arrayList.get(position);
                Event event = (Event) obj;
                long dateTime = event.getEventTimeline().getStartDate();
                String final_date = Util.getDateFromTimestamp(dateTime);
                int weekday = 0;
                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
                Date date = null;
                try {
                    date = format.parse(final_date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                weekday = calendar.get(Calendar.DAY_OF_WEEK);
                String[] final_d = final_date.split("-");
                String sMonth = Util.getMonthFrom(Integer.parseInt(final_d[1])-1);
                String sDate = final_d[0];
                holder.tv_title.setText(event.getPostTitle());
                holder.tv_date.setText(sDate);
                holder.tv_day.setText(Util.getWeekDay(weekday));
                holder.iv_arrow.setBackgroundResource(event.getIcon());
                Log.e("time", String.valueOf(event.getEventTimeline().isAllDay()) + " ");
                if (!event.getEventTimeline().isAllDay()){
                    long startTime = event.getEventTimeline().getStartDate();
                    String sStartTime = Util.getDateTimeFromTimestamp(startTime);
                    long endTime = event.getEventTimeline().getEndDate();
                    String sEndTime = Util.getDateTimeFromTimestamp(endTime);
                    String sDateTime = sStartTime + " - " + sEndTime;
                    if (sStartTime.equalsIgnoreCase(sEndTime)){
                        holder.tv_time.setText("All Day");
                    }else {
                        holder.tv_time.setText(sDateTime);
                    }
                } else {
                    String sDateTime = "All Day";
                    holder.tv_time.setText(sDateTime);
                }
                GradientDrawable drawableTitle = (GradientDrawable)holder.tv_title.getBackground();
                drawableTitle.setColor(event.getLeftColor());
                GradientDrawable drawableTime = (GradientDrawable)holder.tv_time.getBackground();
                drawableTime.setColor(event.getRightColor());

                holder.relativeLayout.setOnClickListener(new openDetails(holder, position, event));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = arrayList.get(position);
        if (obj instanceof String){
            return MONTH;
        }else {
            return super.getItemViewType(position);
        }
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView tv_date, tv_day, tv_title, tv_time, tv_month;
        ImageView iv_arrow, iv_clock;
        RelativeLayout relativeLayout;

        RecyclerViewHolder(View view) {
            super(view);
            tv_date = view.findViewById(R.id.event_date);
            tv_day = view.findViewById(R.id.event_day);
            tv_title = view.findViewById(R.id.event_title);
            tv_time = view.findViewById(R.id.event_time);
            iv_arrow = view.findViewById(R.id.arrow);
            iv_clock = view.findViewById(R.id.iv_clock);
            tv_month = view.findViewById(R.id.event_month_name);
            relativeLayout = view.findViewById(R.id.event_list_layout);
        }
    }

    private class openDetails implements View.OnClickListener {

        private RecyclerViewHolder view;
        private int position;
        private Event event;

        openDetails(RecyclerViewHolder holder, int position, Event event) {
            this.view = holder;
            this.position = position;
            this.event = event;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, EventDetailsActivity.class);
            intent.putExtra("mycolor",event.getRightColor());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_TITLE, event.getPostTitle());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_EVENTDATE, event.getEventTimeline().getStartDate());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_START_DATE, event.getEventTimeline().getStartDate());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_END_DATE, event.getEventTimeline().getEndDate());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_END_ISALL_DAY, event.getEventTimeline().isAllDay());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_VENUE, event.getEventTimeline().getVenue());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_CONTACT_NAME, event.getEventTimeline().getContactName());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_CONTACT_PHONE, event.getEventTimeline().getContactPhone());
            intent.putExtra(DatabaseConstant.EVENT_EXTRA_KEY_DESCRIPTION, event.getPostContent());
            context.startActivity(intent);
        }
    }

}