package intellinectsschool.intellinects.com.v4flavors.Content_Provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import intellinectsschool.intellinects.com.v4flavors.Models.News;
import intellinectsschool.intellinects.com.v4flavors.Other.DatabaseConstant;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Indrajeet  on 26-05-2017
 */

public class NewsDataSource {

    private static SQLiteDatabase sqLiteDatabase = null;

    public NewsDataSource() {}

    public void open(Context context) {
        sqLiteDatabase = context.openOrCreateDatabase(DatabaseConstant.NEWS_TABLE, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL(DatabaseConstant.NEWS_TABLE_CREATE_SCRIPT);
    }

    public void close(Context context) {
        open(context);
        sqLiteDatabase.close();
    }

    public String setNewsData(String response) throws JSONException {
        JSONArray jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            insertIntoNews(jsonArray.getJSONObject(i));

        }
        return "Success";
    }

    private void insertIntoNews(JSONObject jsonObject) throws JSONException {
        News savedNews = getNewsByID(jsonObject.getString("id"));
        if (savedNews == null){
            ContentValues values = new ContentValues();
            if (jsonObject.get("postDate") == null || jsonObject.get("postDate").equals("false") || jsonObject.get("postDate").equals("")){
                values.put(DatabaseConstant.NEWS_DATE, 0);
            }else {
                values.put(DatabaseConstant.NEWS_DATE, jsonObject.getString("postDate"));
            }
            values.put(DatabaseConstant.NEWS_DESCRIPTION, jsonObject.getString("postContent"));
            values.put(DatabaseConstant.NEWS_TITLE, jsonObject.getString("postTitle"));
            values.put(DatabaseConstant.NEWS_PID_KEY, jsonObject.getString("id"));
            values.put(DatabaseConstant.NEWS_POST_STATUS, jsonObject.getString("postStatus"));
            Long res = sqLiteDatabase.insert(DatabaseConstant.NEWS_TABLE, null, values);
            System.out.println(res + "insert news");
        }else {
            ContentValues values = new ContentValues();
            if(jsonObject.get("postDate")==null || jsonObject.get("postDate").equals("false")|| jsonObject.get("postDate").equals("")) {
                values.put(DatabaseConstant.NEWS_DATE, 0);
            } else {
                values.put(DatabaseConstant.NEWS_DATE, jsonObject.getString("postDate"));
            }
            values.put(DatabaseConstant.NEWS_DESCRIPTION, jsonObject.getString("postContent"));
            values.put(DatabaseConstant.NEWS_TITLE, jsonObject.getString("postTitle"));
            values.put(DatabaseConstant.NEWS_POST_STATUS, jsonObject.getString("postStatus"));
            int res = sqLiteDatabase.update(DatabaseConstant.NEWS_TABLE, values, DatabaseConstant.NEWS_PID_KEY + " = ?", new String[] { String.valueOf(jsonObject.getString("id")) });
            System.out.println(res + "update news");
        }
    }

    private News getNewsByID(String id) {
        News news = null;
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "
                + DatabaseConstant.NEWS_TABLE + " WHERE " + DatabaseConstant.NEWS_PID_KEY
                + " = " + id, null);
        if (cursor.moveToFirst()){
            String date = cursor.getString(cursor.getColumnIndex(DatabaseConstant.NEWS_DATE));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseConstant.NEWS_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseConstant.NEWS_DESCRIPTION));
            news = new News(-1, date, title, description);
            cursor.moveToNext();
        }
        if (cursor.isAfterLast()){
            cursor.close();
        }
        return news;
    }

    public ArrayList<News> getAllNews(Context context) {
        open(context);
        ArrayList<News> news = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + DatabaseConstant.NEWS_TABLE + " WHERE "
                + DatabaseConstant.NEWS_POST_STATUS + " ='publish' " + " ORDER BY " + DatabaseConstant.NEWS_DATE + " DESC", null);
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex(DatabaseConstant.NEWS_DATE));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseConstant.NEWS_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseConstant.NEWS_DESCRIPTION));
            String id = cursor.getString(cursor.getColumnIndex(DatabaseConstant.NEWS_PID_KEY));
            News _news = new News(Integer.parseInt(id), date, title, description);
            news.add(_news);
        }
        if (cursor.isAfterLast()){
            cursor.close();
        }
        return news;
    }

    //SECURE NEWS
    private static SQLiteDatabase sqLiteDatabaseSecure = null;

    public void openSecure(Context context) {
        sqLiteDatabaseSecure = context.openOrCreateDatabase(DatabaseConstant.SECURE_NEWS_TABLE, Context.MODE_PRIVATE, null);
        sqLiteDatabaseSecure.execSQL(DatabaseConstant.SECURE_NEWS_TABLE_CREATE_SCRIPT);
    }

    public void closeSecure(Context context){
        open(context);
        sqLiteDatabaseSecure.close();
    }

    public void setSecureNews(JSONArray finalJsonArray, Context context) {
        if (finalJsonArray != null){
            for (int i = 0; i < finalJsonArray.length(); i++){
                try {
                    insertIntoSecureNews(finalJsonArray.getJSONObject(i), context);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void insertIntoSecureNews(JSONObject jsonObject, Context context) throws JSONException {
        News savedSecureNews = getSecureNewsByID(jsonObject.getString("id"));
        if (savedSecureNews == null){
            ContentValues values = new ContentValues();
            if (jsonObject.get("postDate") == null || jsonObject.get("postDate").equals("false") || jsonObject.get("postDate").equals("")){
                values.put(DatabaseConstant.SECURE_NEWS_DATE, 0);
            }else {
                values.put(DatabaseConstant.SECURE_NEWS_DATE, jsonObject.getString("postDate"));
            }
            values.put(DatabaseConstant.SECURE_NEWS_DESCRIPTION, jsonObject.getString("postContent"));
            values.put(DatabaseConstant.SECURE_NEWS_TITLE, jsonObject.getString("postTitle"));
            values.put(DatabaseConstant.SECURE_NEWS_PID_KEY, jsonObject.getString("id"));
            values.put(DatabaseConstant.SECURE_NEWS_POST_STATUS, jsonObject.getString("postStatus"));

            Long res = sqLiteDatabaseSecure.insert(DatabaseConstant.SECURE_NEWS_TABLE, null, values);
            //System.out.println(res + "insert");
        }else {
            ContentValues values = new ContentValues();
            if(jsonObject.get("postDate")==null || jsonObject.get("postDate").equals("false")|| jsonObject.get("postDate").equals("")) {
                values.put(DatabaseConstant.SECURE_NEWS_DATE, 0);
            } else {
                values.put(DatabaseConstant.SECURE_NEWS_DATE, jsonObject.getString("postDate"));
            }
            values.put(DatabaseConstant.SECURE_NEWS_DESCRIPTION, jsonObject.getString("postContent"));
            values.put(DatabaseConstant.SECURE_NEWS_TITLE, jsonObject.getString("postTitle"));
            values.put(DatabaseConstant.SECURE_NEWS_POST_STATUS, jsonObject.getString("postStatus"));
            int res = sqLiteDatabaseSecure.update(DatabaseConstant.SECURE_NEWS_TABLE, values, DatabaseConstant.SECURE_NEWS_PID_KEY + " = ?", new String[] { String.valueOf(jsonObject.getString("id")) });
            //System.out.println(res + "update");
        }
    }

    private News getSecureNewsByID(String id) {
        News news = null;
        Cursor cursor = sqLiteDatabaseSecure.rawQuery("select * from "
                + DatabaseConstant.SECURE_NEWS_TABLE + " WHERE " + DatabaseConstant.SECURE_NEWS_PID_KEY
                + " = " + id, null);
        if (cursor.moveToFirst()){
            String date = cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_NEWS_DATE));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_NEWS_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_NEWS_DESCRIPTION));
            news = new News(Integer.parseInt(id), date, title, description);
            cursor.moveToNext();
        }
        if (cursor.isAfterLast()){
            cursor.close();
        }
        return news;
    }

    public ArrayList<News> getAllSecureNews(Context context) {
        openSecure(context);
        ArrayList<News> news = new ArrayList<>();
        Cursor cursor = sqLiteDatabaseSecure.rawQuery("select * from " + DatabaseConstant.SECURE_NEWS_TABLE + " WHERE "
                + DatabaseConstant.SECURE_NEWS_POST_STATUS + " ='publish' " + " ORDER BY " + DatabaseConstant.SECURE_NEWS_DATE + " DESC", null);
        while (cursor.moveToNext()) {
            String date = cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_NEWS_DATE));
            String title = cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_NEWS_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_NEWS_DESCRIPTION));
            String id = cursor.getString(cursor.getColumnIndex(DatabaseConstant.SECURE_NEWS_PID_KEY));
            News _news = new News(Integer.parseInt(id), date, title, description);
            news.add(_news);
        }
        if (cursor.isAfterLast()){
            cursor.close();
        }
        return news;
    }

    public void deleteTables(Context context) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS '" + DatabaseConstant.NEWS_TABLE + "'");
    }
}
