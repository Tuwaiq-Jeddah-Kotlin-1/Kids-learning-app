package com.roula.kidslearning.learning

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.roula.kidslearning.R


class ForgotPassword : BottomSheetDialogFragment() {
    override fun getTheme(): Int = R.style.BottomSheetDialogTheme



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
        return inflater.inflate(R.layout.fragment_forgot_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val emailForgot = view.findViewById<EditText>(R.id.emilPass)


        val btn_ok = view.findViewById<Button>(R.id.ok)
        btn_ok.setOnClickListener{

    forgotPassword(emailForgot)
    findNavController().navigate(R.id.action_forgotPassword_to_login)
}
    }
    private fun forgotPassword (emil: EditText){
        if (emil.text.toString().isEmpty()){
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(emil.text.toString()).matches()){
            return
        }
        FirebaseAuth.getInstance().sendPasswordResetEmail(emil.text.toString())
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(context,"Email sent.", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(context,"Email not sent.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}