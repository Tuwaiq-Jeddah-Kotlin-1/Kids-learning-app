package com.roula.kidslearning.learning


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.roula.kidslearning.R
import com.roula.kidslearning.data_class.Users

class Setting : Fragment() {

    private lateinit var userSetting: TextView
    private lateinit var emilSetting: TextView
    private lateinit var languageVal: String
    private lateinit var logout : Button
    private lateinit var preferences: SharedPreferences
    private lateinit var share : ImageButton


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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userSetting = view.findViewById(R.id.usernameSetting)
        emilSetting = view.findViewById(R.id.emilSetting)
        logout = view.findViewById(R.id.logOut)
        share = view.findViewById(R.id.share)
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
                putExtra(Intent.EXTRA_TEXT, "Download the application and enjoy your child's learning with us")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        val spinner: Spinner = view.findViewById(R.id.spinner_lang)
        ArrayAdapter.createFromResource(
            view.context,
            R.array.language,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parentView: AdapterView<*>?,
                selectedItemView: View?,
                position: Int,
                id: Long
            ) {
                languageVal = spinner.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

       preferences = requireContext().getSharedPreferences("SHARED_PREF",  Context.MODE_PRIVATE)

        logout.setOnClickListener{
            val editor : SharedPreferences.Editor = preferences.edit().clear()
            editor.apply()
            FirebaseAuth.getInstance().signOut()
            findNavController().navigate(R.id.action_settings_to_login)

        }
    }
}