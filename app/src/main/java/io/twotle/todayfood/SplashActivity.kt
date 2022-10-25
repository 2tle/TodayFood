package io.twotle.todayfood

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import kotlin.system.exitProcess

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()
        handler.postDelayed({
            val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

            remoteConfig.fetchAndActivate()
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val updated = task.result
                        if(remoteConfig.getBoolean("is_update"))
                            AlertDialog.Builder(this)
                                .setTitle("업데이트 필요")
                                .setMessage("새로운 버전이 출시되었습니다. 플레이스토어에서 업데이트 해주세요.")
                                .setOnCancelListener {
                                    //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps")))
                                    ActivityCompat.finishAffinity(this);
                                    exitProcess(0);
                                }
                                .show()

                        else if(remoteConfig.getString("notice") != "")
                            AlertDialog.Builder(this)
                                .setTitle("공지사항")
                                .setMessage(remoteConfig.getString("notice"))
                                //.setPositiveButton("확인", null)
                                .setPositiveButton("확인",
                                    DialogInterface.OnClickListener { dialog, which ->
                                        startActivity(Intent(application, io.twotle.todayfood.MainActivity::class.java))
                                        this@SplashActivity.finish()
                                })
                                .show()
                        else {
                            startActivity(Intent(application, io.twotle.todayfood.MainActivity::class.java))
                            this@SplashActivity.finish()
                        }


                    }
                }


            //}

        }, 3000)

    }

    override fun onBackPressed() {

    }
}