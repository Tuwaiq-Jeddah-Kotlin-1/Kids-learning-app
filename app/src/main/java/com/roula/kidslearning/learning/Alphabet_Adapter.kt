package com.roula.kidslearning.learning

import Alphabet_data
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.roula.kidslearning.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class Alphabet_Adapter (val c :Context,val alphabetData: List<Alphabet_data> ) : RecyclerView.Adapter<CustomHolder>() , TextToSpeech.OnInitListener {
    private lateinit var mTTS: TextToSpeech
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        mTTS = TextToSpeech(c, this)
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_alphabet, parent, false)
        return CustomHolder(view)

    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        val alphabets = alphabetData[position]
        holder.descriptionAlphabet.text = alphabets.description
        holder.imageAlphabet.load(alphabets.picture)
        var scaleUp = AnimationUtils.loadAnimation(c, R.anim.scale_up)
        holder.imageAlphabet.setOnClickListener {
            GlobalScope.launch {
                holder.imageAlphabet.startAnimation(scaleUp)
                delay(500)
                speak(alphabets.description)
            }
        }
        holder.cardAlphabet.setOnClickListener {
            GlobalScope.launch {
                holder.imageAlphabet.startAnimation(scaleUp)
                delay(500)
            }
            speak(alphabets.description)
        }

    }

    override fun getItemCount(): Int {
        return alphabetData.size
    }

    private fun speak(name: String) {

        mTTS.setPitch(1f)
        mTTS.setSpeechRate(0.8f)
        mTTS.speak(name, TextToSpeech.QUEUE_FLUSH, null, "")

    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = mTTS.setLanguage(Locale.ENGLISH)
            if (result == TextToSpeech.LANG_MISSING_DATA
                || result == TextToSpeech.LANG_NOT_SUPPORTED
            ) {
                Log.e("TTs", "Language is not supported")
            } else {
                //.isEnabled = true
            }
        } else {
            Log.e("TTs", "Initialization failed")
        }

    }


}
    class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val descriptionAlphabet: TextView = itemView.findViewById(R.id.alphaDescription)
        val imageAlphabet: ImageView = itemView.findViewById(R.id.alphaImg)
        val cardAlphabet: CardView = itemView.findViewById(R.id.card_alphabet)

    }



