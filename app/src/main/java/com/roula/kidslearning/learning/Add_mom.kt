package com.roula.kidslearning.learning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.roula.kidslearning.R
import com.roula.kidslearning.data_class.Mom


class Add_mom : Fragment() {


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
        return inflater.inflate(R.layout.fragment_add_mom, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val editT_mom: EditText = view.findViewById(R.id.ed_addMom)
        val btn_save_text: Button = view.findViewById(R.id.save_text)
        btn_save_text.setOnClickListener() {
            val mainVM = ViewModelProvider(this).get(AlphabetVM::class.java)


            val mom = Mom(
                textKids = editT_mom.text.toString(),
                )
            mainVM.saveText(mom)

           it.findNavController().navigate(R.id.action_add_mom_to_forMom)

        }
    }
}

