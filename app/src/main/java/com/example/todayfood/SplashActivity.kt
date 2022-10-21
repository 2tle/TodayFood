package com.example.todayfood

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.todayfood.repository.sf.SharedPreferences

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
//            if(SharedPreferences.prefs.getString("SchoolID","00") == "00") {
//                startActivity(Intent(application, SchoolUpdateActivity::class.java))
//                this@SplashActivity.finish()
//            } else {
                startActivity(Intent(application, MainActivity::class.java))
                this@SplashActivity.finish()
            //}

        }, 3000)

    }

    override fun onBackPressed() {

    }
}