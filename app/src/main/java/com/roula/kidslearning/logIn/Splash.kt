package com.roula.kidslearning.logIn

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.roula.kidslearning.R

class Splash : Fragment() {

    private lateinit var logo: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        logo = view.findViewById(R.id.logo)
        // Setting up two animations with their respective time
        logo.startAnimation(AnimationUtils.loadAnimation(context,R.anim.splash_in))
        Handler(Looper.getMainLooper()).postDelayed({
            logo.startAnimation(AnimationUtils.loadAnimation(context,R.anim.splash_out))
            Handler(Looper.getMainLooper()).postDelayed({
                logo.visibility = View.GONE

               findNavController().navigate(R.id.action_splash_to_login)

            },500)
        },1500)

    }
}
