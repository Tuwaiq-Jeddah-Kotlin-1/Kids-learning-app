package com.roula.kidslearning.learning

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.roula.kidslearning.R
import com.roula.kidslearning.data_class.Mom
import java.util.*

class Mom_Adapter(val context: Context, val momData: List<Mom>, val viewModel: AlphabetVM) :
    RecyclerView.Adapter<TextAdapter>(), TextToSpeech.OnInitListener {
    private lateinit var mTTS: TextToSpeech
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAdapter {
        mTTS = TextToSpeech(context, this)

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mom, parent, false)
        return TextAdapter(view)
    }

    override fun onBindViewHolder(holder: TextAdapter, position: Int) {
        val momText = momData[position]

        holder.momTextView.text = momText.textKids
        holder.lesson.setOnClickListener {
            speak(momText.textKids)

        }

    }

    override fun getItemCount(): Int {
        return momData.size
    }

    private fun speak(name: String) {
        /**set TextToSpeech*/
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

    fun deleteText(adapterPosition: Int) {
        viewModel.deleteText(momData[adapterPosition])
      // notifyItemRemoved(adapterPosition)
      //  Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show()
    }


}

class TextAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val momTextView: TextView = itemView.findViewById(R.id.tv_mom)
    val lesson: Button = itemView.findViewById(R.id.lesson)
}