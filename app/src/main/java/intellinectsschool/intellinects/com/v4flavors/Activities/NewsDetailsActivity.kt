package intellinectsschool.intellinects.com.v4flavors.Activities
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import intellinectsschool.intellinects.com.v4flavors.Other.DatabaseConstant
import intellinectsschool.intellinects.com.v4flavors.Other.DrawablePainter

import intellinectsschool.intellinects.com.v4flavors.R
import intellinectsschool.intellinects.com.v4flavors.Utils.Util
import kotlinx.android.synthetic.main.activity_news_details.*


import kotlinx.android.synthetic.main.header_layout.*

import org.json.JSONException
import org.json.JSONObject

class NewsDetailsActivity : AppCompatActivity() {

    private var bundle: Bundle? = null
    private lateinit var finalDate: String
    private lateinit var title: String
    private lateinit var wv_data: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_details)
        val painter = DrawablePainter(this)
        painter.paintDrawables()
        getData()
        iv_back.setOnClickListener {
            finish()
        }
    }

    private fun getData() {

        header_text.setText(R.string.news_details)
        header_text.visibility = View.VISIBLE
        news_details_scroll_view.isVerticalScrollBarEnabled = false
        news_details_scroll_view.isHorizontalScrollBarEnabled = false
        bundle = intent.extras
        if (bundle != null) {
            if (bundle!!.getString("result") != null) {
                try {
                    val jsonObject = JSONObject(bundle!!.getString("result"))
                    title = jsonObject.getString("postTitle")
                    val date = jsonObject.getInt("postDate").toLong()
                    finalDate = Util.getDateFromTimestamp(date)
                    Log.e("date", jsonObject.getString("postTitle") + " " + title + " " + jsonObject.getString("postDate") + " " + finalDate)
                    wv_data = jsonObject.getString("postContent").replace("/n".toRegex(), "<br>")
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

                notification_body.visibility = View.GONE
                news_details_wv!!.visibility = View.VISIBLE
                setData()
            } else {
                notification_body.visibility = View.GONE
                news_details_wv!!.visibility = View.VISIBLE
                title = bundle!!.getString(DatabaseConstant.NEWS_EXTRA_KEY_TITLE, null)
                val id = bundle!!.getInt(DatabaseConstant.NEWS_PID_KEY, 0)
                Log.e("News ID", id.toString() + " ")
                val date = java.lang.Long.parseLong(bundle!!.getString(DatabaseConstant.NEWS_EXTRA_KEY_DATE))
                finalDate = Util.getDateFromTimestamp(date)
                Log.e("Date", "$finalDate ")
                setData()
            }
        }
    }

    private fun setData() {

        news_date!!.text = finalDate
        news_title!!.text = title
        Log.e("WV", "" + bundle!!.getString(DatabaseConstant.NEWS_EXTRA_KEY_DETAILS)!!)
        if (bundle!!.getString(DatabaseConstant.NEWS_EXTRA_KEY_DETAILS) != null) {
            wv_data = bundle!!.getString(DatabaseConstant.NEWS_EXTRA_KEY_DETAILS)!!.replace("\n".toRegex(), "</br>")
        }
        val style = "<style type='text/css'>" + "img {" + "margin: auto;" + "padding: 0px;" + "display:block;" + "}" + "</style>"
        news_details_wv!!.loadDataWithBaseURL("", style + " <div align='justify'> <font color='black'>" + wv_data
                + "</font></div>", "text/html", "UTF-8", "")
        news_details_wv!!.isHorizontalScrollBarEnabled = false
        news_details_wv!!.isVerticalScrollBarEnabled = false
        news_details_wv!!.settings.builtInZoomControls = true
    }

    fun shareNews(view: View) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        share.putExtra(Intent.EXTRA_SUBJECT, title)
        share.putExtra(Intent.EXTRA_TEXT, title + (System.getProperty("line.separator") + "Read More News at : https://ischoolsystem.net/news/"))
        startActivity(Intent.createChooser(share, "Share News"))
    }

    fun BackToPrevious(view: View) {
        onBackPressed()
    }
}
