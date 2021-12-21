package com.roula.kidslearning.learning


import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController
import com.roula.kidslearning.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class Color : Fragment() {

    var color = ""

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
        return inflater.inflate(R.layout.fragment_color, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val  buttonback = view.findViewById<Button>(R.id.buttonback)
        val  soundgreen = view.findViewById<ImageButton>(R.id.soundgreen)
        val  soundblue = view.findViewById<ImageButton>(R.id.soundblue)
        val  soundred = view.findViewById<ImageButton>(R.id.soundred)
        val  soundyellow = view.findViewById<ImageButton>(R.id.soundyellow)


        var scaleUp = AnimationUtils.loadAnimation(context,R.anim.scale_up)


        buttonback.setOnClickListener {

            findNavController().navigate(R.id.action_color_to_home2)
        }
        soundblue.setOnClickListener {

            GlobalScope.launch {
                soundblue.startAnimation(scaleUp)
                delay(500)
            }
           color = "Blue"
           soundOn()
       }
        soundgreen.setOnClickListener {

            GlobalScope.launch {
                soundgreen.startAnimation(scaleUp)
                delay(500) }
            color = "Green"
            soundOn()
        }
        soundred.setOnClickListener {
            GlobalScope.launch {
                soundred.startAnimation(scaleUp)
                delay(500)
            }
            color = "Red"
            soundOn()
        }
        soundyellow.setOnClickListener {
            GlobalScope.launch {
                soundyellow.startAnimation(scaleUp)
                delay(500)
            }
            color = "Yellow"
            soundOn()
        }
    }
    fun soundOn(){
        when (color) {
            "Blue" -> {
                val player = MediaPlayer.create(context, R.raw.blue)
                player.start()
                player.setOnCompletionListener {
                    player.release()
                }
            }
            "Red" -> {
                val player = MediaPlayer.create(context, R.raw.red)
                player.start()
                player.setOnCompletionListener {
                    player.release()
                }
            }
            "Green" -> {
                val player = MediaPlayer.create(context, R.raw.green)
                player.start()
                player.setOnCompletionListener {
                    player.release()
                }
            }
            else -> {
                val player = MediaPlayer.create(context, R.raw.yellow)
                player.start()
                player.setOnCompletionListener {
                    player.release()
                } } } } }