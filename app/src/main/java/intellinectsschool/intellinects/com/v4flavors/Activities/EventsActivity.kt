package intellinectsschool.intellinects.com.v4flavors.Activities

import android.annotation.SuppressLint
import android.graphics.Color
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson


import intellinectsschool.intellinects.com.v4flavors.R
import intellinectsschool.intellinects.com.v4flavors.Adapter.EventsAdapter
import intellinectsschool.intellinects.com.v4flavors.Content_Provider.EventDataSource
import intellinectsschool.intellinects.com.v4flavors.Models.Event
import intellinectsschool.intellinects.com.v4flavors.Network.NetworkCall
import intellinectsschool.intellinects.com.v4flavors.Other.CustomAlert
import intellinectsschool.intellinects.com.v4flavors.Other.DataCallback
import intellinectsschool.intellinects.com.v4flavors.Utils.Util
import org.json.JSONArray
import java.text.SimpleDateFormat
import java.util.*


class EventsActivity : AppCompatActivity() {

    internal lateinit var linearLayout: LinearLayout
    internal lateinit var nda_text: TextView
//    internal lateinit var cm: Common_Methods
    internal lateinit var iv_nda: ImageView
    internal lateinit var tv_header: TextView
    internal lateinit var recyclerView: RecyclerView
    internal lateinit var layoutManager: RecyclerView.LayoutManager
    internal lateinit var swipeRefreshLayout: SwipeRefreshLayout
    internal lateinit var arrayList: ArrayList<Event>
    internal lateinit var objects: ArrayList<Any>
    internal lateinit var adapter: EventsAdapter
    internal lateinit var sMonth: String
    internal lateinit var imageViewBack: ImageView

    internal var right_colors = intArrayOf(Color.parseColor("#4c4ca6"), Color.parseColor("#cccc00"), Color.parseColor("#00cc00"))
    internal var left_colors = intArrayOf(Color.parseColor("#19198c"), Color.parseColor("#999900"), Color.parseColor("#007f00"))
    internal var icons = intArrayOf(R.drawable.event1, R.drawable.event2, R.drawable.event3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        imageViewBack = findViewById(R.id.iv_back)
        imageViewBack.setOnClickListener { finish() }
//        cm = Common_Methods(this@EventsActivity)
        linearLayout = findViewById(R.id.nda_layout)
        nda_text = findViewById(R.id.nda_text)
        iv_nda = findViewById(R.id.nda_image)

        tv_header = findViewById(R.id.header_text)
        tv_header.visibility = View.VISIBLE
        tv_header.setText(R.string.events)

        recyclerView = findViewById(R.id.events_recycler_layout)
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        swipeRefreshLayout = findViewById(R.id.events_refresh_layout)
        swipeRefreshLayout.setColorSchemeColors(ResourcesCompat.getColor(resources, R.color.colorPrimaryDark, null))
        swipeRefreshLayout.setOnRefreshListener {
            if (swipeRefreshLayout.isRefreshing) {
                swipeRefreshLayout.isRefreshing = false
            }
            getEventData()
        }


        getEventData()

    }

    private fun getEventData() {

        if (NetworkCall.isInternetAvailable(this))
        {

            NetworkCall.getRefreshedEvents(this,object: DataCallback {
                override fun onSuccess(response: String) {
                    Log.d("POJO","Event onSuccess $response")
                    if(response.equals(null) || response.equals("") || response.equals(" ") || response.equals("DB CREDS INVALID")) {
                        linearLayout.visibility=View.VISIBLE
                    }
                    else{
                        getEventOnline(response)
                    }
                }
                override fun onFailure(response: String) {
                    Log.d("POJO","Event onFailure $response")
//                    cm.alert_dialog(this@EventsActivity,getResources().getString(R.string.error_messages))
                }
            })

        }else
        {
            // CustomAlert.show(this,getString(R.string.no_internet))
            getEventOffline()
        }
    }


    private fun getEventOnline(response: String) {

        val events = ArrayList<Event>()
        val jsonArray = JSONArray(response)
        for (i in 0 until jsonArray.length()) {
            val jsonObject=jsonArray.getJSONObject(i)
            var _event: Event
            val gson = Gson()
            _event = gson.fromJson(jsonObject.toString(),Event::class.java)
            val eventDate = _event.eventTimeline!!.startDate
            val postStatus = _event.postStatus
            val mStringRecurenceDate = _event.eventTimeline!!.recurrenceDate
            var currentDate = Date()
            try
            {
                @SuppressLint("SimpleDateFormat") val dateInString = SimpleDateFormat("yyyy-MM-dd").format(Date())
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                currentDate = format.parse(dateInString)
            } catch (ignored: Exception) {
            }

            var eventDa = Date(eventDate)
            val fdate = Util.getDateFromTimestampWhole(eventDate)
            try {
                val format = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                eventDa = format.parse(fdate)
            } catch (ignored: Exception) {
            }

            if (eventDa.compareTo(currentDate) < 0) {
            } else {
                try {
                    if (postStatus == "publish") {
                        events.add(_event)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }


            if (mStringRecurenceDate == null || mStringRecurenceDate == "") {
                //events.add(_event);
            } else {
                if (mStringRecurenceDate == "false") {
                } else {
                    if (mStringRecurenceDate.contains(",")) {
                        val mStringRDate = mStringRecurenceDate.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        for (p in mStringRDate.indices) {
                            var evtrecdate = Date()
                            val evtrecdateTime = java.lang.Long.valueOf(mStringRDate[p])
                            val recdate = Util.getDateFromTimestampWhole(evtrecdateTime)
                            try {
                                val recformat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                                evtrecdate = recformat.parse(recdate)
                            } catch (ignored: Exception) {
                            }

                            if (evtrecdate.compareTo(currentDate) < 0) {
                            } else {
                                _event = gson.fromJson(jsonObject.toString(), Event::class.java)
                                _event.eventTimeline!!.startDate = java.lang.Long.parseLong(mStringRDate[p])
                                if (postStatus == "publish") {
                                    events.add(_event)
                                }
                            }
                        }
                    } else {
                        var evtrecdate = Date()
                        val evtrecdateTime = java.lang.Long.valueOf(mStringRecurenceDate)
                        val recdate = Util.getDateFromTimestampWhole(evtrecdateTime)
                        try {
                            val recformat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                            evtrecdate = recformat.parse(recdate)
                        } catch (ignored: Exception) {
                        }

                        if (evtrecdate.compareTo(currentDate) < 0) {
                        } else {
                            _event = gson.fromJson<Event>(jsonObject.toString(), Event::class.java)
                            _event.eventTimeline!!.startDate = java.lang.Long.parseLong(mStringRecurenceDate)
                            if (postStatus == "publish") {
                                events.add(_event)
                            }
                        }
                    }
                }
            }

        }

        arrayList = events
        if (arrayList.size > 0) {
            objects = createList(arrayList)
            adapter = EventsAdapter(objects, this@EventsActivity)
            recyclerView.adapter = adapter
            linearLayout.visibility = View.GONE
        } else {
            linearLayout.visibility = View.VISIBLE
            nda_text.text="No any events to show"
        }
    }

    private fun getEventOffline() {
        val eventDataSource = EventDataSource()
        eventDataSource.open(this)
        arrayList = eventDataSource.getAllEvents(this)
        eventDataSource.close(this)
        if (arrayList.size > 0) {
            objects = createList(arrayList)
            adapter = EventsAdapter(objects, this@EventsActivity)
            recyclerView.adapter = adapter
            linearLayout.visibility = View.GONE
        } else {
            linearLayout.visibility = View.VISIBLE
            nda_text.text="There are no events to show"
        }
    }

    fun createList(listEvent: ArrayList<Event>): ArrayList<Any> {
        Collections.sort(listEvent)
        val lstObj = ArrayList<Any>()
        try {
            var month = ""
            var count = 0
            for (i in listEvent.indices) {

                val dateTime = listEvent[i].eventTimeline!!.startDate
                val fDate = Util.getDateFromTimestamp(dateTime)

                val final_d = fDate.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                sMonth = Util.getMonthFrom(Integer.parseInt(final_d[1]) - 1)
                Log.e("Month ", "Date " + final_d[0])
                Log.e("Month ", "Month " + final_d[1] + " , Month : " + sMonth)
                Log.e("Month ", "Year " + final_d[2])
                if (month == "") {
                    month = sMonth
                    lstObj.add(sMonth)
                    listEvent[i].rightColor = left_colors[count]
                    listEvent[i].leftColor = right_colors[count]
                    listEvent[i].icon = icons[count]
                    count++
                    lstObj.add(listEvent[i])
                } else if (month == sMonth) {
                    if (count > 2) {
                        count = 0
                    }
                    listEvent[i].rightColor = left_colors[count]
                    listEvent[i].leftColor = right_colors[count]
                    Log.e("COUNT 2", count.toString())
                    listEvent[i].icon = icons[count]
                    count++
                    lstObj.add(listEvent[i])
                } else {
                    count = 0
                    month = sMonth
                    lstObj.add(sMonth)
                    listEvent[i].rightColor = left_colors[count]
                    listEvent[i].leftColor = right_colors[count]
                    listEvent[i].icon = icons[count]
                    count++
                    lstObj.add(listEvent[i])
                }
            }
        } catch (e: Exception) {
            Log.e("", "" + e.toString())
        }

        return lstObj
    }

    fun BackToPrevious(view: View) {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

}
