package com.roula.kidslearning

import android.app.Activity
import android.app.UiModeManager
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.UserDictionary.Words.LOCALE
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.roula.kidslearning.notification.NotificationRepo
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val sharedPreferences = getSharedPreferences("SHARED_PREF", Activity.MODE_PRIVATE)
        val mode = resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)

//check the dark mood user option
        if (sharedPreferences.getBoolean("DARK_MOOD",false)) {
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
            if (mode == Configuration.UI_MODE_NIGHT_YES) {
                setContentView(R.layout.activity_main)
            }
        }else{
            setContentView(R.layout.activity_main)
        }


        supportActionBar?.hide()

        NotificationRepo().myNotification(this)

       /* if (sharedPreferences.getBoolean("DARK_MOOD", false)) {
            resources.configuration.uiMode = Configuration.UI_MODE_NIGHT_YES
            AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)


       } else {
            resources.configuration.uiMode = Configuration.UI_MODE_NIGHT_NO
        }*/
//check localization user option
        if (sharedPreferences.getString("LOCALE","") == "ar") {
            setLocate(this,"ar")
        } else {
            setLocate(this,"en")
        }

    }



    private fun setLocate(activity: Activity, Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val resources = activity.resources
       val  config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

    }

}

