package com.intellinects.intellinectsschool.divinechildcbse.adapters

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

import intellinectsschool.intellinects.com.v4flavors.Activities.NewsDetailsActivity
import intellinectsschool.intellinects.com.v4flavors.Activities.WebViewActivity
import intellinectsschool.intellinects.com.v4flavors.Models.News
import intellinectsschool.intellinects.com.v4flavors.Other.Constants
import intellinectsschool.intellinects.com.v4flavors.Other.DatabaseConstant
import intellinectsschool.intellinects.com.v4flavors.R
import intellinectsschool.intellinects.com.v4flavors.Utils.Util

import kotlinx.android.synthetic.main.more_news.view.*
import java.net.URLDecoder


import java.util.ArrayList

/**
 * Created by Indrajeet Vadgama on 22-04-2017
 */

class NewsListAdapter(arrayList: ArrayList<News>, private val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v: View
        if (viewType == FOOTER_VIEW) {
            v = LayoutInflater.from(parent!!.context).inflate(R.layout.more_news, parent, false)
            return FooterViewHolder(v)
        }
//        } else if (viewType == NO_DATA) {
//            return null
//        }
        else {
            v = LayoutInflater.from(parent!!.context).inflate(R.layout.news_list_item, parent, false)
            return NormalViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if (holder is NormalViewHolder) {

                holder.bindView(holder, position)
            } else if (holder is FooterViewHolder) {
                val vh = holder
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private var arrayList = ArrayList<News>()

    init {
        this.arrayList = arrayList
    }

    private inner class FooterViewHolder internal constructor(itemView: View) : RecyclerFooter(itemView) {

        init {
            itemView.btn_more_news.setOnClickListener {
                // Do whatever you want on clicking the item
                val intent = Intent(context, WebViewActivity::class.java)
                intent.putExtra("URL", Constants.MORE_NEWS_URL)
                context.startActivity(intent)
            }
        }
    }

    private inner class NormalViewHolder internal constructor(itemView: View) : RecyclerViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                val intent = Intent(context, NewsDetailsActivity::class.java)
                intent.putExtra(DatabaseConstant.NEWS_PID_KEY, arrayList[adapterPosition].id)
                intent.putExtra(DatabaseConstant.NEWS_EXTRA_KEY_DATE, arrayList[adapterPosition].date)
                intent.putExtra(DatabaseConstant.NEWS_EXTRA_KEY_TITLE, arrayList[adapterPosition].title)
                intent.putExtra(DatabaseConstant.NEWS_EXTRA_KEY_DETAILS, arrayList[adapterPosition].content)
                //  intent.putExtra("mylist", arrayList);

                context.startActivity(intent)
            }
        }
    }





    override fun getItemCount(): Int {
        if (arrayList == null) {
            return 0
        }

        return if (arrayList.size == 0) {
            //Return 1 here to show nothing
            1
        } else arrayList.size + 1

        // Add extra view to show the footer view
    }

    override fun getItemViewType(position: Int): Int {
        if (position == arrayList.size) {
            // This is where we'll add footer.
            return FOOTER_VIEW
        } else if (arrayList.size == 0) {
            return NO_DATA
        }else{
            return ITEMVIEW
        }
    }

    open inner class RecyclerFooter(view: View) : RecyclerView.ViewHolder(view) {

    }

    open inner class RecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var tv_news_month: TextView
        var tv_news_date: TextView
        var tv_news_title: TextView
        var linearLayout: LinearLayout

        init {
            linearLayout = view.findViewById(R.id.layout_circle)
            tv_news_month = view.findViewById(R.id.news_month)
            tv_news_date = view.findViewById(R.id.news_date)
            tv_news_title = view.findViewById(R.id.news_title)
        }

        fun bindView(holder: RecyclerViewHolder, position: Int) {
            // bindView() method to implement actions
            Log.e("POS", position.toString())
            val dateTime = java.lang.Long.parseLong(arrayList[position].date)
            val final_date = Util.getDateFromTimestamp(dateTime)
            val date = final_date.split("-".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sMonth = Util.getMonthFrom(Integer.parseInt(date[1]) - 1)
            val sDate = date[0]
            holder.tv_news_month.text = sMonth
            holder.tv_news_date.text = sDate
            holder.tv_news_title.text = URLDecoder.decode(arrayList[position].title,"UTF-8");

            when (position % 3) {
                0 -> linearLayout.background = context.getDrawable(R.drawable.news_list_date)
                1 -> linearLayout.background = context.getDrawable(R.drawable.news_list_date_two)
                2 -> linearLayout.background = context.getDrawable(R.drawable.news_list_date_three)
                else -> linearLayout.background = context.getDrawable(R.drawable.news_list_date)
            }

        }
    }

    companion object {
        private val FOOTER_VIEW = 1
        private val NO_DATA = 2
        private val ITEMVIEW = 3

    }
}
