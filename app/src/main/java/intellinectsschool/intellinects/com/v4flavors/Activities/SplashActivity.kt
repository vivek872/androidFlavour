package intellinectsschool.intellinects.com.v4flavors.Activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import intellinectsschool.intellinects.com.v4flavors.R


class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        //REDIRECT TO NEXT ACTIVITY FROM AFTER 1500 MILISEC
        val SPLASH_DURATION = 1500
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_DURATION.toLong())
    }
}
