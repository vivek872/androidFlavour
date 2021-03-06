package intellinectsschool.intellinects.com.v4flavors.Activities

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import intellinectsschool.intellinects.com.v4flavors.Adapter.NavAdapter

import intellinectsschool.intellinects.com.v4flavors.Models.NavItems
import intellinectsschool.intellinects.com.v4flavors.Network.NetworkCall
import intellinectsschool.intellinects.com.v4flavors.Other.Constants
import intellinectsschool.intellinects.com.v4flavors.Other.CustomAlert
import intellinectsschool.intellinects.com.v4flavors.R
import java.util.ArrayList
import java.net.URLEncoder


class HomeActivity : AppCompatActivity() {

    /*******************VARIABLE DECLARATION ***********************/

    internal lateinit  var recyclerView: RecyclerView
    internal lateinit  var layoutManager: RecyclerView.LayoutManager
    internal lateinit  var sc_recycler: RecyclerView
    internal lateinit  var drawerLayout: DrawerLayout
    internal lateinit  var  toggle: ActionBarDrawerToggle
    internal lateinit  var  toolbar: Toolbar
   // internal lateinit  var navAdapter: NavAdapter
    internal lateinit  var navArray: ArrayList<NavItems>
    internal lateinit  var navAdapter: NavAdapter
    internal var drawerCheck = 0
    internal lateinit  var tv_dyn_login: TextView
    internal lateinit  var iv_dyn_login: ImageView
    internal lateinit  var iv_logout:ImageView
    internal lateinit  var sc_layoutManager:RecyclerView.LayoutManager
    internal lateinit var sc_frag_container_parent: LinearLayout
    internal lateinit var sc_frag_container:LinearLayout
    internal lateinit var init_view:LinearLayout
    internal lateinit  var final_view:LinearLayout
    internal lateinit  var layout_learning:LinearLayout


   /*******************VARIABLE DECLARATION END***********************/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

       /*******************METHOD FOR INITIALIZING ALL ELEMENTS ***********************/

           InitialiseHome()

       /*******************END OF METHOD FOR INITIALIZING ALL ELEMENTS ***********************/

    }



 /*******************METHOD FOR LUNCHING WEB VIEWS***********************/

    fun LaunchWebView(view: View) {

        val textView = view.findViewById<View>(R.id.home_wv) as TextView
        val intent = Intent(this@HomeActivity, WebViewActivity::class.java)

        Log.d("CHAMPP",textView.text.toString().toLowerCase())



        if (NetworkCall.isInternetAvailable(this)) {

             when (textView.text.toString().toLowerCase()) {

                "facilities" -> {
                    intent.putExtra("URL", Constants.FACILITIES_URL)
                    startActivity(intent)
                }
                "teachers" -> {
                    intent.putExtra("URL", Constants.TEACHERS_URL)
                    startActivity(intent)
                }
                "principal's message" -> {
                    intent.putExtra("URL", Constants.PRINCIPALS_MESSAGE)
                    startActivity(intent)
                }
                "contact us" -> {
                    intent.putExtra("URL", Constants.CONTACT_US)
                    startActivity(intent)
                }
                "about us" -> {
                    intent.putExtra("URL", Constants.ABOUT_US)
                    startActivity(intent)
                }
                "school website" -> {
                    intent.putExtra("URL", Constants.SCHOOL_WEBSITE)
                    startActivity(intent)
                }
                "lms" -> {
//                    val buffer = StringBuffer(Constants.LMS_URL) //("http://stage.realtylog.net/iPhone/functions.php")
//                    buffer.append("/" + URLEncoder.encode("SSCMaharashtraBoard"))
//                    buffer.append("/" + URLEncoder.encode("DIVINE"))
//                    buffer.append("act=" + URLEncoder.encode("readFileAndPrint"))
//                    webView.loadUrl(buffer.toString())
                    intent.putExtra("URL", Constants.LMS_URL)
                    startActivity(intent)
                }
                "learning" -> {
                    intent.putExtra("URL", Constants.LEARNING_CENTER)
                    startActivity(intent)
                }
            }
        }
        else
        {
            CustomAlert.show(this,"No internet connection")
        }


    }


 /*******************END OF METHOD FOR LUNCHING WEB VIEWS***********************/




 /*******************COMMON METHOD FOR CALL ALL LINKS USING INTENT***********************/

    fun LaunchHomeIntent(view: View) {
        val textView = view.findViewById(R.id.home_tv) as TextView
      //  val intent: Intent
        val intent = Intent(this@HomeActivity, WebViewActivity::class.java)



      if (NetworkCall.isInternetAvailable(this)) {
            when (textView.text.toString().toLowerCase()) {
                "news" -> {
//                    intent = Intent(this@HomeActivity, NewsActivity::class.java)
//                    startActivity(intent)

                    intent.putExtra("URL", Constants.NEWS_URL)
                    startActivity(intent)
                }
                "events" -> {
                    intent.putExtra("URL", Constants.EVENT_URL)
                    startActivity(intent)
                }
                "register" -> {

                    CustomAlert.show(this@HomeActivity, "Registration Disable at the moment")

                }

            }
        }
        else
        {
            CustomAlert.show(this,"No internet connection")
        }
    }

    /*******************END OF METHOD COMMON METHOD FOR CALL ALL LINKS USING INTENT***********************/



 /*******************METHOD FOR INITIALIZING ALL ELEMENT ***********************/

    private fun InitialiseHome() {
        //ToolBar and NavDrawer
        toolbar = findViewById(R.id.tool_bar) as Toolbar
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer_layout) as DrawerLayout
        toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)

        //NavDrawerList
        recyclerView = findViewById(R.id.nav_recycler_view) as RecyclerView
        layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        navArray = ArrayList()

        navAdapter = NavAdapter(navArray, this, drawerLayout, drawerCheck)
        recyclerView.adapter = navAdapter

        //LOGIN TEXT VIEW
        tv_dyn_login = findViewById(R.id.tv_dyn_login) as TextView
        iv_dyn_login = findViewById(R.id.iv_dyn_login) as ImageView

        //SELECT CHILD RECYCLER
        sc_recycler = findViewById(R.id.home_select_child_recycler) as RecyclerView
        sc_layoutManager = LinearLayoutManager(this)
        sc_recycler.layoutManager = sc_layoutManager
        init_view = findViewById(R.id.init_view) as LinearLayout
        final_view = findViewById(R.id.final_view) as LinearLayout


        init_view.setOnClickListener {

           CustomAlert.show(this,"Login is Disable at the moment")

        }
        //SELECT CHILD FRAG
        sc_frag_container_parent = findViewById(R.id.sc_frag_container_parent) as LinearLayout
        sc_frag_container = findViewById(R.id.sc_frag_container) as LinearLayout

        //Nav bar
        iv_logout = findViewById(R.id.logout) as ImageView
        iv_logout.setImageDrawable(Constants.LOGOUT_BUTTON)
        iv_logout.setVisibility(View.GONE)

    }


   /*******************END OF METHOD FOR INITIALIZING ALL ELEMENT ***********************/



/*******************EXIT METHOD *************************************/

    fun exit_alert(activity: Activity) {

        val builder = AlertDialog.Builder(activity)
       // Finally, make the alert dialog using builder

        // Set the alert dialog title
        builder.setTitle("Alert")

        // Display a message on alert dialog
        builder.setMessage("Do You Want to Exit??")

        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("YES") { dialogs, which ->
            // Do something when user press the positive button
            dialogs.dismiss()
            activity.finish()

        }

        // Display a neutral button on alert dialog
        builder.setNeutralButton("Cancel"){dialogs,_ ->
            dialogs.dismiss()
        }

        val dialogalert: AlertDialog = builder.create()
        // Display the alert dialog on app interface
        dialogalert.show()
    }

/*******************END OF EXIT METHOD *************************************/




/*******************BACKPRESS METHOD *************************************/


    override fun onBackPressed() {

       exit_alert(this)
    }

/*******************END OF BACK PRESS METHOD *************************************/

}
