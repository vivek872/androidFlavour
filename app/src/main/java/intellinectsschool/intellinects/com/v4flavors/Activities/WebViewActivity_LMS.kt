package intellinectsschool.intellinects.com.v4flavors.Activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView


import org.json.JSONException
import org.json.JSONObject
import android.view.KeyEvent.KEYCODE_BACK
import android.view.KeyEvent
import intellinectsschool.intellinects.com.v4flavors.R


class WebViewActivity_LMS : AppCompatActivity() {

    internal lateinit  var progress: ProgressDialog
    internal lateinit  var bundle: Bundle
    internal var url: String =""
    internal lateinit var back_img: ImageView
    internal lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        webView = findViewById<WebView>(R.id.web_view_global)
        bundle = intent.extras
        progress = ProgressDialog(this@WebViewActivity_LMS)
        progress.setMessage("Loading...")
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.isIndeterminate = true
        progress.setCancelable(false)
        progress.show()

            if (bundle.getString("URL", null) != null) {
                try
                {
                    url =bundle.getString("URL", null)
                    Log.d("URL_LINK", url)
                } catch (e: JSONException)
                {
                    e.printStackTrace()
                }
            } else
            {
                url = bundle.getString("URL")
            }

            webView.visibility = View.VISIBLE
            webView.settings.javaScriptEnabled = true
            if (url != null) {
                webView.loadUrl(url)
            }

            webView.webViewClient = object : WebViewClient() {
                override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                    super.onPageStarted(view, url, favicon)
                }

                override fun onPageFinished(view: WebView, url: String) {
                    super.onPageFinished(view, url)
                    progress.dismiss()
                }
            }

    }




    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }

//        val fm = supportFragmentManager
//        fm.popBackStack()
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        if (event != null) {
            if (event.getAction() === KeyEvent.ACTION_DOWN) {
                when (keyCode) {
                    KeyEvent.KEYCODE_BACK -> {
                        if (webView.canGoBack()) {
                            webView.goBack()
                        } else {
                            finish()
                        }
                        return true
                    }
                }

            }
        }
        return super.onKeyDown(keyCode, event)
    }


}