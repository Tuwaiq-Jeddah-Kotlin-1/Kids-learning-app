package com.roula.kidslearning.logIn

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.roula.kidslearning.R
import com.roula.kidslearning.data_class.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class Register : Fragment() {

    private val firebaseObj = Firebase.firestore.collection("Users")
    private lateinit var name_register: EditText
    private lateinit var email_register: EditText
    private lateinit var password_rigster: EditText
    private lateinit var btn_rigster: Button
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
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    private fun saveUsers(users: Users) = CoroutineScope(Dispatchers.IO).launch {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        try {
            ///if ubdate user writ here  SetOptions.merge()
            firebaseObj.document("$uid").set(users).addOnSuccessListener {
                Toast.makeText(context, "Successfully saved data.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_register_to_home2)

            }

        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        name_register = view.findViewById(R.id.et_name_register)
        email_register = view.findViewById(R.id.et_email_register)
        password_rigster = view.findViewById(R.id.et_password_register)
        btn_rigster = view.findViewById(R.id.btn_register)
        btn_rigster.setOnClickListener {


            when {
                TextUtils.isEmpty(email_register.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter Email",
                        Toast.LENGTH_LONG
                    ).show()
                }

                TextUtils.isEmpty(password_rigster.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                        context,
                        "Please Enter Password",
                        Toast.LENGTH_LONG
                    ).show()


                }
                else -> {
                    val email: String = email_register.text.toString().trim { it <= ' ' }
                    val password: String = password_rigster.text.toString().trim { it <= ' ' }


                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            // if the registration is sucessfully done
                            if (task.isSuccessful) {
                                //firebase register user
                             //   val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    context,
                                    "You were registered succsessfuly",
                                    Toast.LENGTH_LONG
                                ).show()

                                //++++++++++++++++++++++++++

                                val usernameData = name_register.text.toString()
                                val emailData = email_register.text.toString()
                                val user = Users(emailData, usernameData)
                                saveUsers(user)


                                //++++++++++++++++++++++
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


