package com.roula.kidslearning.learning


import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.UiModeManager.MODE_NIGHT_NO
import android.app.UiModeManager.MODE_NIGHT_YES
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat.recreate
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.roula.kidslearning.MainActivity
import com.roula.kidslearning.R
import com.roula.kidslearning.R.layout.support_simple_spinner_dropdown_item
import com.roula.kidslearning.data_class.Users
import java.util.*

class Setting : Fragment() {

    private lateinit var userSetting: TextView
    private lateinit var emilSetting: TextView
    private lateinit var languageVal: String
    private lateinit var logout: Button
    private lateinit var preferences: SharedPreferences
    private lateinit var share: ImageButton
    lateinit var mBtn: Button


    private val firebaseObj = Firebase.firestore.collection("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }


    @SuppressLint("WrongConstant")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userSetting = view.findViewById(R.id.usernameSetting)
        emilSetting = view.findViewById(R.id.emilSetting)
        logout = view.findViewById(R.id.logOut)
        share = view.findViewById(R.id.share)
        mBtn = view.findViewById(R.id.mChangeLang)

        preferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        var toggle: Switch = view.findViewById(R.id.switchtheme)
        toggle.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
                preferences.edit().putBoolean("DARK_MOOD", true).apply()

            } else {
                AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
                preferences.edit().putBoolean("DARK_MOOD", false).apply()
            }
        }

        mBtn.setOnClickListener {

            showChangeLang()

        }
        val uid = FirebaseAuth.getInstance().currentUser?.uid

        firebaseObj.document("$uid").get().addOnCompleteListener { it ->
            it.addOnSuccessListener {
                if (it != null) {
                    val user = it.toObject(Users::class.java)
                    userSetting.text = user!!.username
                    emilSetting.text = user.email
                }
            }
        }

        share.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT,
                    "Download the application and enjoy your child's learning with us"
                )
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }



        logout.setOnClickListener {
            val editor: SharedPreferences.Editor = preferences.edit().clear()
            editor.apply()
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_settings_to_login)

        }
    }

    private fun showChangeLang() {

        val listItmes = arrayOf("Arabic",                                                                -"English")

        val mBuilder = AlertDialog.Builder(context)
        mBuilder.setTitle("Choose Language")
        mBuilder.setPositiveButton("OK") { _, _ ->

//            refreshCurrentFragment()
        }
        mBuilder.setSingleChoiceItems(listItmes, -1) { dialog, which ->
            if (which == 0) {

                    preferences.edit().putString("LOCALE", "ar")
                    setLocate(requireActivity(), "ar")

            } else {

                    preferences.edit().putString("LOCALE", "en")
                    setLocate(requireActivity(), "en")
            }

            val mDialog = mBuilder.create()
            mDialog.show()

        }
    }

     fun setLocate(activity: Activity, Lang: String) {
        val locale = Locale(Lang)
        Locale.setDefault(locale)
        val resources = activity.resources
        val config: Configuration = resources.configuration
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        startActivity(Intent(activity, MainActivity::class.java))
        activity.finish()
        //        val editor = requireContext().getSharedPreferences("Settings", Context.MODE_PRIVATE).edit()
//        editor.putString("My_Lang", Lang)
//        editor.apply()
    }

//    private fun loadLocate() {
//        val sharedPreferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
//        val language = sharedPreferences.getString("My_Lang", "")
//        if (language != null) {
//            setLocate(language)
//        }
//    }
//    private fun refreshCurrentFragment(){
//        val id = findNavController().currentDestination?.id
//        findNavController().navigateUp()
//        findNavController().navigate(id!!)
//        Log.e("Access","$id")
//    }
}