package intellinectsschool.intellinects.com.v4flavors.Activities

import android.content.Context
import android.support.v4.content.res.ResourcesCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.intellinects.intellinectsschool.divinechildcbse.adapters.NewsListAdapter
import intellinectsschool.intellinects.com.v4flavors.Content_Provider.NewsDataSource
import intellinectsschool.intellinects.com.v4flavors.Models.News
import intellinectsschool.intellinects.com.v4flavors.Network.NetworkCall
import intellinectsschool.intellinects.com.v4flavors.Other.Constants
import intellinectsschool.intellinects.com.v4flavors.Other.CustomAlert
import intellinectsschool.intellinects.com.v4flavors.Other.DataCallback

import intellinectsschool.intellinects.com.v4flavors.R
import kotlinx.android.synthetic.main.activity_news.*


import kotlinx.android.synthetic.main.header_layout.*
import kotlinx.android.synthetic.main.nda_layout.*
import org.json.JSONArray
import org.json.JSONException
import java.net.URLEncoder
import java.util.*

class NewsActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener {

  /*********** VARIABLE DECLARATION****************/

    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var arrayList: ArrayList<News>
    private lateinit var adapter: NewsListAdapter
    private lateinit var linearLayout: LinearLayout
    private lateinit var iv_nda: ImageView
    val TAG="NewsActivity";

  /***********END VARIABLE DECLARATION****************/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)


      /*********** BACK BUTTON CLICK EVENT ****************/
        iv_back.setOnClickListener {
            finish();
        }


        arrayList=ArrayList()
        header_text.setText(R.string.news)
        header_text.visibility = View.VISIBLE

        //Populate News
        layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager
        //Swipe Refresh

        news_swipe_layout.setColorSchemeColors(ResourcesCompat.getColor(resources, R.color.colorPrimaryDark, null))
        nda_image.setImageDrawable(Constants.NDA_IMAGE)
        news_swipe_layout.setOnRefreshListener(this)

            val params = HashMap<String, String>()
//            params["SCHOOL_ID"] = Constants.SCHOOL_CODE_NEWS_EVENTS
//            CustomProgress.showProgress(this)


      /*********** CHEKING FOR INTERNET AVAILABLE OR NOT****************/

        if(NetworkCall.isInternetAvailable(this))
        {
            material_loader.visibility=View.VISIBLE
            NetworkCall.getNewsAPI(params,this, object : DataCallback {
                override fun onFailure(response: String) {
                    material_loader.visibility=View.GONE

//                    CustomProgress.hideProgress()
                    CustomAlert.show(this@NewsActivity,response)
                }
                override fun onSuccess(response: String) {
                    material_loader.visibility=View.GONE

//                    CustomProgress.hideProgress()
                     getNewsOnline(response)   //If Internet is there, fetch the news online
                }
            })

        }else
        {
//            CustomAlert.show(this,getString(R.string.no_internet))
            getOfflineNews()
        }


    }

    /*********** CHEKING FOR INTERNET AVAILABLE OR NOT****************/




    /*****Function IS FOR GETTING NEWS WHEN INTERNET CONNECTION IS ON*********/

    private fun getNewsOnline(response: String) {
        try {
//             val response = URLEncoder.encode(jsonObject.getString("postStatus") , "utf-8")


                val jsonArray = JSONArray(response)
            for (i in 0 until jsonArray.length()) {
                val jsonObject=jsonArray.getJSONObject(i)
                val postDate=jsonObject.getString("postDate")
                val id=jsonObject.getString("id")
                val postContent=jsonObject.getString("postContent")
                val postTitle=jsonObject.getString("postTitle")
                val postStatus= jsonObject.getString("postStatus")

                if(postStatus == "publish")
                {
                    val newsData=News(id.toInt(),postDate,postTitle,postContent)
                    arrayList.add(newsData)
                }


            }
            arrayList.reverse()
        }catch (e: JSONException)
        {
            e.printStackTrace()
        }

        if (arrayList.size > 0) {
            rv_news_list.visibility = View.VISIBLE
            nda_layout.visibility = View.GONE
            adapter = NewsListAdapter(arrayList, this@NewsActivity)
            rv_news_list.layoutManager = layoutManager
            rv_news_list.addItemDecoration(DividerItemDecoration(this@NewsActivity, DividerItemDecoration.VERTICAL))
            rv_news_list.adapter = adapter
        } else {
            rv_news_list.visibility = View.GONE
            rv_news_list.visibility = View.VISIBLE
        }

        //store data locally for getting offline
        try {
            val newsDataSource = NewsDataSource()
            newsDataSource.open(this@NewsActivity)
            newsDataSource.deleteTables(this@NewsActivity)
            newsDataSource.close(this@NewsActivity)
            newsDataSource.open(this@NewsActivity)
            newsDataSource.setNewsData(response)
            newsDataSource.close(this@NewsActivity)

        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }



    /*****Function IS FOR GETTING NEWS WHEN INTERNET CONNECTION IS ON*********/


    /*****Function IS FOR GETTING NEWS WHEN INTERNET CONNECTION IS OFF*********/

    private fun getOfflineNews() {
        val newsDataSource = NewsDataSource()
        newsDataSource.open(this)
        arrayList = newsDataSource.getAllNews(this)
        newsDataSource.close(this)
        if (arrayList.size > 0) {
            rv_news_list.visibility = View.VISIBLE
            nda_layout.visibility = View.GONE
            val sharedPreferences = this.getSharedPreferences(Constants.APP_NAME, Context.MODE_PRIVATE)
            if (sharedPreferences.getInt(Constants.SWIPE_NEWS, 0) == 0) {
                fragment_swipe_down.visibility = View.VISIBLE
                val editor = sharedPreferences.edit()
                editor.putInt(Constants.SWIPE_NEWS, 1)
                editor.apply()
            } else {
                nda_layout.visibility = View.GONE
            }
            adapter = NewsListAdapter(arrayList, this)
            rv_news_list.layoutManager = layoutManager
            rv_news_list.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            rv_news_list.adapter = adapter
        } else {
            rv_news_list.visibility = View.GONE
            nda_layout.visibility = View.VISIBLE
        }
    }


    /*****END OF FUNCTION FOR GETTING NEWS WHEN INTERNET CONNECTION IS OFF *********/


    fun BackToPrevious(view: View) {
        onBackPressed()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun EndFragment(view: View) {
        fragment_swipe_down.visibility = View.GONE
    }

    override fun onRefresh() {
        if (NetworkCall.isInternetAvailable(this))
        {
            val params = HashMap<String, String>()
//            params["SCHOOL_ID"] = Constants.SCHOOL_CODE_NEWS_EVENTS
            NetworkCall.getNewsAPI(params,this@NewsActivity, object : DataCallback {
                override fun onFailure(response: String) {
                    CustomAlert.show(this@NewsActivity,response)
                    if (news_swipe_layout.isRefreshing) {
                        news_swipe_layout.isRefreshing = false
                    }

                }

                override fun onSuccess(response: String) {
                    arrayList.clear()
                    getNewsOnline(response)
                    if (news_swipe_layout.isRefreshing) {
                        news_swipe_layout.isRefreshing = false
                        Toast.makeText(this@NewsActivity, "Refreshed", Toast.LENGTH_LONG).show()
                    }
                }
            })
        }else
        {
            CustomAlert.show(this,getString(R.string.no_internet))
            if (news_swipe_layout.isRefreshing) {
                news_swipe_layout.isRefreshing = false
            }
        }
    }
}
