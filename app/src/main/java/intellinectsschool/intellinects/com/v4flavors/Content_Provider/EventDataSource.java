package intellinectsschool.intellinects.com.v4flavors.Content_Provider;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import intellinectsschool.intellinects.com.v4flavors.Models.Event;
import intellinectsschool.intellinects.com.v4flavors.Other.DatabaseConstant;
import intellinectsschool.intellinects.com.v4flavors.Utils.Util;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Indrajeet  on 26-05-2017
 */

public class EventDataSource {

    private SQLiteDatabase sqLiteDatabase;

    public EventDataSource() {}

    public void open(Context context) {
        sqLiteDatabase = context.openOrCreateDatabase(DatabaseConstant.EVENT_TABLE, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL(DatabaseConstant.EVENT_TABLE_CREATE_SCRIPT);
    }

    public void close(Context context) {
        open(context);
        sqLiteDatabase.close();
    }

    public void insertEvents(Context context, String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            insertIntoEvents(jsonArray.getJSONObject(i), context);
        }
    }

    private void insertIntoEvents(JSONObject jsonObject, Context context) throws JSONException {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.EVENT_JSON, jsonObject.toString());
        if (getEventByID(Integer.parseInt(jsonObject.getString("id"))) == null) {
            values.put(DatabaseConstant.EVENT_ID_KEY, jsonObject.getString("id"));
            Long i = sqLiteDatabase.insert(DatabaseConstant.EVENT_TABLE, null, values);
            System.out.println("Events Insert" + i);
        } else if (getEventByID(Integer.parseInt(jsonObject.getString("id"))) != null) {
            int i = sqLiteDatabase.update(DatabaseConstant.EVENT_TABLE, values, DatabaseConstant.EVENT_ID_KEY + " = ?",
                    new String[]{String.valueOf(jsonObject.getString("id"))});
            System.out.println("Events Update" + i);
        }
    }

    private Event getEventByID(int id) {
        Event events = null;
        try {
            String query = "select * from " + DatabaseConstant.EVENT_TABLE + " WHERE " + DatabaseConstant.EVENT_ID_KEY + " = " + id;
            Cursor cursor = sqLiteDatabase.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                Gson gson = new Gson();
                events = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.EVENT_JSON)), Event.class);
            }
            if (cursor.isLast()) {
                cursor.close();
            }
        } catch (Exception ignored) {
        }
        return events;
    }

    public ArrayList<Event> getAllEvents(Context context) {
        ArrayList<Event> events = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "
                + DatabaseConstant.EVENT_TABLE + " ORDER BY " + DatabaseConstant.EVENT_ID_KEY
                + " DESC", null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Event _event;
                Gson gson = new Gson();
                _event = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.EVENT_JSON)), Event.class);
                Long eventDate = _event.getEventTimeline().getStartDate();
                String postStatus = _event.getPostStatus();
                String mStringRecurenceDate = _event.getEventTimeline().getRecurrenceDate();
                Date currentDate = new Date();
                try {
                    @SuppressLint("SimpleDateFormat") String dateInString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    currentDate = format.parse(dateInString);
                } catch (Exception ignored) {}
                Date eventDa = new Date(eventDate);
                long dateTime = eventDate;
                String fdate = Util.getDateFromTimestampWhole(dateTime);
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    eventDa = format.parse(fdate);
                } catch (Exception ignored) {}
                if (eventDa.compareTo(currentDate) < 0) {
                } else {
                    try {
                        if (postStatus.equals("publish")) {
                            events.add(_event);
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (mStringRecurenceDate == null || mStringRecurenceDate.equals("")) {
                    //events.add(_event);
                } else {
                    if (mStringRecurenceDate.equals("false")) {
                    } else {
                        if (mStringRecurenceDate.contains(",")) {
                            String[] mStringRDate = mStringRecurenceDate.split(",");
                            for (int p = 0; p < mStringRDate.length; p++) {
                                Date evtrecdate = new Date();
                                long evtrecdateTime = Long.valueOf(mStringRDate[p]);
                                String recdate = Util.getDateFromTimestampWhole(evtrecdateTime);
                                try {
                                    DateFormat recformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                    evtrecdate = recformat.parse(recdate);
                                } catch (Exception ignored) {}
                                if (evtrecdate.compareTo(currentDate) < 0) {
                                } else {
                                    _event = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.EVENT_JSON)), Event.class);
                                    _event.getEventTimeline().setStartDate(Long.parseLong(mStringRDate[p]));
                                    if (postStatus.equals("publish")) {
                                        events.add(_event);
                                    }
                                }
                            }
                        } else {
                            Date evtrecdate = new Date();
                            long evtrecdateTime = Long.valueOf(mStringRecurenceDate);
                            String recdate = Util.getDateFromTimestampWhole(evtrecdateTime);
                            try {
                                DateFormat recformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                evtrecdate = recformat.parse(recdate);
                            } catch (Exception ignored) {}
                            if (evtrecdate.compareTo(currentDate) < 0) {
                            } else {
                                _event = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.EVENT_JSON)), Event.class);
                                _event.getEventTimeline().setStartDate(Long.parseLong(mStringRecurenceDate));
                                if (postStatus.equals("publish")) {
                                    events.add(_event);
                                }
                            }
                        }
                    }
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        return events;
    }

    //SECURE EVENTS
    private static SQLiteDatabase sqLiteDatabaseSecure = null;

    public static void openSecure(Context context){
        sqLiteDatabaseSecure = context.openOrCreateDatabase(DatabaseConstant.SECURE_EVENT_TABLE, Context.MODE_PRIVATE, null);
        sqLiteDatabaseSecure.execSQL(DatabaseConstant.SECURE_EVENT_TABLE_CREATE_SCRIPT);
    }

    private static void insertIntoSecureEvents(JSONObject jsonObject, Context context) throws JSONException {
        ContentValues values = new ContentValues();
        values.put(DatabaseConstant.SECURE_EVENT_JSON, jsonObject.toString());
        if (getSecureEventByID(Integer.parseInt(jsonObject.getString("id"))) == null) {
            values.put(DatabaseConstant.SECURE_EVENT_ID_KEY, jsonObject.getString("id"));
            sqLiteDatabaseSecure.insert(DatabaseConstant.SECURE_EVENT_TABLE, null, values);
        } else if (getSecureEventByID(Integer.parseInt(jsonObject.getString("id"))) != null) {
            sqLiteDatabaseSecure.update(DatabaseConstant.SECURE_EVENT_TABLE, values, DatabaseConstant.SECURE_EVENT_ID_KEY + " = ?",
                    new String[]{String.valueOf(jsonObject.getString("id"))});
        }
    }

    private static Event getSecureEventByID(int eventID) {
        Event event = null;
        try {
            String query = "select * from " + DatabaseConstant.SECURE_EVENT_TABLE + " WHERE " + DatabaseConstant.SECURE_EVENT_ID_KEY + " = " + eventID;
            Cursor cursor = sqLiteDatabaseSecure.rawQuery(query, null);
            if (cursor.moveToFirst()) {
                Gson gson = new Gson();
                event = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_EVENT_JSON)), Event.class);
            }
            if (cursor.isLast()){
                cursor.close();
            }
        }catch (Exception ignored) {}
        return event;
    }

    public static void setSecureEventData(JSONArray jsonArray, Context context) throws JSONException {
        if (jsonArray != null){
            for (int i = 0; i < jsonArray.length(); i++){
                insertIntoSecureEvents(jsonArray.getJSONObject(i), context);
            }
        }
    }

    public static ArrayList<Event> getAllSecureEvents(Context context) {
        openSecure(context);
        ArrayList<Event> events = new ArrayList<>();
        Cursor cursor = sqLiteDatabaseSecure.rawQuery("select * from "
                + DatabaseConstant.SECURE_EVENT_TABLE + " ORDER BY " + DatabaseConstant.SECURE_EVENT_ID_KEY
                + " DESC", null);
        if (cursor.moveToFirst()){
            while (!cursor.isAfterLast()){
                Event _event;
                Gson gson = new Gson();
                _event = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_EVENT_JSON)), Event.class);
                Long eventDate = _event.getEventTimeline().getStartDate();
                String postStatus = _event.getPostStatus();
                String mStringRecurenceDate = _event.getEventTimeline().getRecurrenceDate();
                Date currentDate = new Date();
                try {
                    String dateInString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    currentDate = format.parse(dateInString);
                } catch (Exception ignored) {}
                Date eventDa = new Date(eventDate);
                long dateTime = eventDate;
                String fdate = Util.getDateFromTimestampWhole(dateTime);
                try {
                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                    eventDa = format.parse(fdate);
                } catch (Exception ignored) {}
                if (eventDa.compareTo(currentDate) < 0) {
                } else {
                    try {
                        if (postStatus.equals("publish")) {
                            events.add(_event);
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (mStringRecurenceDate == null || mStringRecurenceDate.equals("")) {
                    //events.add(_event);
                } else {
                    if (mStringRecurenceDate.equals("false")) {
                    } else {
                        if (mStringRecurenceDate.contains(",")) {
                            String[] mStringRDate = mStringRecurenceDate.split(",");
                            for (int p = 0; p < mStringRDate.length; p++) {
                                Date evtrecdate = new Date();
                                long evtrecdateTime = Long.valueOf(mStringRDate[p]);
                                String recdate = Util.getDateFromTimestampWhole(evtrecdateTime);
                                try {
                                    DateFormat recformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                    evtrecdate = recformat.parse(recdate);
                                } catch (Exception ignored) {}
                                if (evtrecdate.compareTo(currentDate) < 0) {
                                } else {
                                    _event = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_EVENT_JSON)), Event.class);
                                    _event.getEventTimeline().setStartDate(Long.parseLong(mStringRDate[p]));
                                    if (postStatus.equals("publish")) {
                                        events.add(_event);
                                    }
                                }
                            }
                        } else {
                            Date evtrecdate = new Date();
                            long evtrecdateTime = Long.valueOf(mStringRecurenceDate);
                            String recdate = Util.getDateFromTimestampWhole(evtrecdateTime);
                            try {
                                DateFormat recformat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                evtrecdate = recformat.parse(recdate);
                            } catch (Exception ignored) {}
                            if (evtrecdate.compareTo(currentDate) < 0) {
                            } else {
                                _event = gson.fromJson(cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_EVENT_JSON)), Event.class);
                                _event.getEventTimeline().setStartDate(Long.parseLong(mStringRecurenceDate));
                                if (postStatus.equals("publish")) {
                                    events.add(_event);
                                }
                            }
                        }
                    }
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        return events;
    }

    public void deleteTables(Context context) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + DatabaseConstant.EVENT_TABLE + "'");
    }
}