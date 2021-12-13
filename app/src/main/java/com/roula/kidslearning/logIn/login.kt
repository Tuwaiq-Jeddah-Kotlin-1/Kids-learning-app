package com.roula.kidslearning.logIn

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.roula.kidslearning.R


class login : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var userName: EditText
    private lateinit var btn_login: Button
    private lateinit var toRegister: TextView
    private lateinit var forgotePass: TextView



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
        forgotePass.setOnClickListener {

            val builder = AlertDialog.Builder (context)
            builder.setTitle("Forgot password")
            val view1 :View = layoutInflater.inflate(R.layout.dialog_forgot,null)
            val username = view1.findViewById<EditText>(R.id.et_userName_forgot)
            builder.setView(view1)
            builder.setPositiveButton("Reset") { _, _ ->
                forgotPassword(username)
            }
            builder.setNegativeButton("Close") { _, _ ->
                builder.show()
            }
        }


        toRegister.setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)

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

                TextUtils.isEmpty(password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter Password",
                        Toast.LENGTH_LONG
                    ).show()

                }
                else -> {
                    val email: String = email.text.toString().trim { it <= ' ' }
                   // val username: String = userName.text.toString().trim { it <= ' ' }
                    val password: String = password.text.toString().trim { it <= ' ' }

                    // create an instance and create a register with email and passwords
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            // if the registration is sucessfully done
                            if (task.isSuccessful) {
                                //firebase register user
                           //     val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    context,
                                    "Welcome",
                                    Toast.LENGTH_LONG
                                ).show()

                                findNavController().navigate(R.id.action_login_to_home2)

                            } else {
                                // if the registreation is not succsesful then show error massage
                                Toast.makeText(
                                    context,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            } } } } } }
    private fun forgotPassword (username: EditText){
        if (username.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()){
            return
        }
        FirebaseAuth.getInstance().sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(context,"Email sent.",Toast.LENGTH_SHORT).show()
                }

            }

    }
}