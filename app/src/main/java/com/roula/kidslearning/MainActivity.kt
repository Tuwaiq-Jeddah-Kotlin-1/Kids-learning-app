package com.roula.kidslearning

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.roula.kidslearning.notification.NotificationRepo
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        NotificationRepo().myNotification(this)
        val sharedPreferences = getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)

//check the dark mood user option
        if (sharedPreferences.getBoolean("DARK_MOOD", true)) {
            resources.configuration.uiMode = Configuration.UI_MODE_NIGHT_YES

            //(AppCompatDelegate.MODE_NIGHT_YES)
       } else {
            resources.configuration.uiMode = Configuration.UI_MODE_NIGHT_NO
        }
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

