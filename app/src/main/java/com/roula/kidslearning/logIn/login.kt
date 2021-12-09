package com.roula.kidslearning.logIn

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.roula.kidslearning.R



class login : Fragment() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btn_login: Button


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
        btn_login = view.findViewById(R.id.btn_login)

        btn_login.setOnClickListener {

            when {
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
                    val password: String = password.text.toString().trim { it <= ' ' }

                    // create an instance and create a register with email and passwords
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            // if the registration is sucessfully done
                            if (task.isSuccessful) {
                                //firebase register user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    context,
                                    "Welcome",
                                    Toast.LENGTH_LONG
                                ).show()


                            } else {
                                // if the registreation is not succsesful then show error massage
                                Toast.makeText(
                                    context,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                }


            }

        }



    }
}