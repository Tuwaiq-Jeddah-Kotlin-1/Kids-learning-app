package com.roula.kidslearning.learning

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import com.roula.kidslearning.R

private lateinit var cardAlphabet : CardView
private lateinit var cardReapet : CardView
private lateinit var cardColor : CardView
private lateinit var cardSetting : CardView
private lateinit var preferences: SharedPreferences

class Home : Fragment() {
    private lateinit var welcome : TextView


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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    cardAlphabet= view.findViewById(R.id.card_alphabet)
    cardReapet= view.findViewById(R.id.card_Repeat)
    cardColor= view.findViewById(R.id.card_Color)
    welcome= view.findViewById(R.id.welcome)
    cardSetting= view.findViewById(R.id.card_setting)

        preferences = requireContext().getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)
        val nameWelcome = preferences.getString("user name","")
        welcome.text = "Welcome "+nameWelcome

        cardAlphabet.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_alphabet)
        }
        cardColor.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_color)

        }
        cardReapet.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_forMom)

        }
        cardSetting.setOnClickListener {
            findNavController().navigate(R.id.action_home2_to_settings)

        }
    }

    }
