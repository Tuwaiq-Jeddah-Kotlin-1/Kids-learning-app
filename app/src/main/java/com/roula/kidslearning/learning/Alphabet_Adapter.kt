package com.roula.kidslearning.learning

import Alphabet_data
import android.app.AlertDialog
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.roula.kidslearning.R
import java.util.*

class Alphabet_Adapter (val c :Context,val alphabetData: List<Alphabet_data> ) : RecyclerView.Adapter<CustomHolder>() , TextToSpeech.OnInitListener {
    private lateinit var mTTS : TextToSpeech
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        mTTS = TextToSpeech(c,this)
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alphabet, parent, false)
        return CustomHolder(view)

    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {

        val alphabets = alphabetData[position]
        holder.descriptionAlphabet.text = alphabets.description
        holder.imageAlphabet.load(alphabets.picture)
        holder.imageAlphabet.setOnClickListener{
                /**set speak*/
                speak(alphabets.description)
                /**set Dialog*/
              //  showAlpha(alphabets.picture,alphabets.description)

        }

    }

    override fun getItemCount(): Int {
         return alphabetData.size
    }
//animation
//    private fun showAlpha(img: String, name: String) {
//        val inflater = LayoutInflater.from(c)
//        val setView = inflater.inflate(R.layout.show_alphabet,null)
//        /**set view */
//        val nameAlpha = setView.findViewById<TextView>(R.id.alphaNames)
//        val imgAlpha = setView.findViewById<ImageView>(R.id.alphaImgs)
//        val btnCancel = setView.findViewById<ImageView>(R.id.btnCancel)
//        nameAlpha.text = name
//        imgAlpha.setImageResource(img)
//        val showDialog = AlertDialog.Builder(c)
//        showDialog.setCancelable(true)
//        showDialog.setView(setView)
//        val openDialog = showDialog.create()
//        btnCancel.setOnClickListener { openDialog.dismiss() }
//        openDialog.show()
//
//    }

    private fun speak(name: String) {
        /**set TextToSpeech*/
        mTTS.setPitch(1f)
        mTTS.setSpeechRate(1.1f)
        mTTS.speak(name,TextToSpeech.QUEUE_FLUSH,null,"")

    }

    override fun onInit(status: Int) {
//        mTTS = TextToSpeech(c){ status ->
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

}

