package com.roula.kidslearning.logIn

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.roula.kidslearning.R
import com.roula.kidslearning.util.Validation


class login : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var userName: EditText
    private lateinit var btn_login: Button
    private lateinit var toRegister: TextView
    private lateinit var forgotePass: TextView
    private lateinit var remember : CheckBox
    private lateinit var myShared :SharedPreferences
    var isRemember = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        email = view.findViewById(R.id.et_email)
        password = view.findViewById(R.id.et_password)
        userName = view.findViewById(R.id.et_userName)
        btn_login = view.findViewById(R.id.btn_login)
        forgotePass = view.findViewById(R.id.tv_forgotPass)
        toRegister = view.findViewById(R.id.toRegister)
        remember = view.findViewById(R.id.check_remeber)


        forgotePass.setOnClickListener {

            findNavController().navigate(R.id.action_login_to_forgotPassword)
        }


        toRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)

        }

        myShared = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        isRemember = myShared.getBoolean("CHECKBOX", false)
        if (isRemember){
            findNavController().navigate(R.id.home2)
        }
        btn_login.setOnClickListener {

            when {
                TextUtils.isEmpty(userName.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter UserName",
                        Toast.LENGTH_LONG
                    ).show()
                }
                TextUtils.isEmpty(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter Email",
                        Toast.LENGTH_LONG
                    ).show()
                }
                !Validation.emil(email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Invalid Email",
                        Toast.LENGTH_LONG
                    ).show()

                }
                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter Password",
                        Toast.LENGTH_LONG
                    ).show()

                }

                else -> {
                    val email: String = email.text.toString().trim { it <= ' ' }
                   val username: String = userName.text.toString().trim { it <= ' ' }
                    val password: String = password.text.toString().trim { it <= ' ' }

                    // create an instance and create a register with email and passwords
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            if (task.isSuccessful) {

                                Toast.makeText(
                                    context,
                                    "Welcome",
                                    Toast.LENGTH_LONG
                                ).show()

                                val editor : SharedPreferences.Editor = myShared.edit()
                                val userPref = username
                                val emilData = email
                                val passwordPref = password
                                val check: Boolean = remember.isChecked
                                editor.putString("emile", emilData)
                                editor.putString("user name", userPref)
                                editor.putString("password", passwordPref)
                                editor.putBoolean("CHECKBOX",check )
                                editor.apply()
                                Toast.makeText(context,"Information Saved!",Toast.LENGTH_LONG).show()
                                findNavController().navigate(R.id.home2)

                            } else {
                                // if the registreation is not succsesful then show error massage
                                Toast.makeText(
                                    context,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            } } } } } }
}