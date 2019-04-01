package intellinectsschool.intellinects.com.v4flavors.Activities

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import intellinectsschool.intellinects.com.v4flavors.R


import org.json.JSONException
import org.json.JSONObject

class WebViewActivity : AppCompatActivity() {

    internal lateinit  var progress: ProgressDialog
    internal lateinit  var bundle: Bundle
    internal var url: String =""

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)

        val webView = findViewById<WebView>(R.id.web_view_global)
         bundle = intent.extras
        progress = ProgressDialog(this@WebViewActivity)
        progress.setMessage("Loading...")
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progress.isIndeterminate = true
        progress.setCancelable(false)
        progress.show()

            if (bundle.getString("URL", null) != null) {
                try {

                    url =bundle.getString("URL", null)
                    Log.d("URL_LINK", url)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            } else {

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
}