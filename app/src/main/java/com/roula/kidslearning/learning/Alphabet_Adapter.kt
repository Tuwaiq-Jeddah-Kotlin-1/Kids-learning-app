package com.roula.kidslearning.learning

import Alphabet_data
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.roula.kidslearning.R

class Alphabet_Adapter (val alphabetData: List<Alphabet_data> ) : RecyclerView.Adapter<CustomHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alphabet, parent, false)
        return CustomHolder(view)    }

    override fun onBindViewHolder(holder: CustomHolder, position: Int) {
    val alphabets = alphabetData[position]
        holder.descriptionAlphabet.text = alphabets.description
        holder.imageAlphabet.load(alphabets.picture)

    }

    override fun getItemCount(): Int {
         return alphabetData.size
    }

}
class CustomHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val descriptionAlphabet: TextView = itemView.findViewById(R.id.alphaDescription)
    val imageAlphabet: ImageView = itemView.findViewById(R.id.alphaImg)

}

