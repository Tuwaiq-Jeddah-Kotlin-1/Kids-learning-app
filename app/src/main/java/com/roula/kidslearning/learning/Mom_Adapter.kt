package com.roula.kidslearning.learning

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.roula.kidslearning.R
import com.roula.kidslearning.data_class.Mom
import java.util.*

class Mom_Adapter(val context: Context, val momData: MutableList<Mom>, val viewModel: AlphabetVM) :
    RecyclerView.Adapter<TextAdapter>(), TextToSpeech.OnInitListener {
    private lateinit var mTTS: TextToSpeech
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAdapter {
        mTTS = TextToSpeech(context, this)

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mom, parent, false)
        return TextAdapter(view)
    }

    override fun onBindViewHolder(holder: TextAdapter, position: Int) {
        val momText = momData[position]

        holder.momTextView.hint = momText.textKids
        holder.momTextView.isEnabled = false
        val old= momText.textKids
        holder.lesson.setOnClickListener {
            speak(momText.textKids)

        }
        holder.edit.setOnClickListener {
          holder.momTextView.isEnabled = !holder.momTextView.isEnabled

          if(holder.momTextView.isEnabled) {

              holder.edit.background =
                  ContextCompat.getDrawable(context, R.drawable.tick)
          }else {
              holder.edit.background =
                  ContextCompat.getDrawable(context, R.drawable.edit)
              if (old != holder.momTextView.text.toString()){
                  momText.textKids=holder.momTextView.text.toString()
                 viewModel.updateText(momText)
                  notifyItemChanged(position)
                  Toast.makeText(context,"successful update ",Toast.LENGTH_SHORT).show()
              }
          }

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
        momData.removeAt(adapterPosition)
        notifyItemRemoved(adapterPosition)
        Toast.makeText(context,"deleted",Toast.LENGTH_SHORT).show()
    }


}

class TextAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val momTextView: EditText = itemView.findViewById(R.id.emilPass)
    val lesson: ImageButton = itemView.findViewById(R.id.lesson)
    val edit: ImageButton = itemView.findViewById(R.id.edit_mom)
}