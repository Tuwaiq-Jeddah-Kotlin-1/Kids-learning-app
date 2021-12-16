package com.roula.kidslearning.learning

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.roula.kidslearning.R

private lateinit var recyclerView: RecyclerView
private lateinit var viewModel : AlphabetVM
class Alphabet : Fragment() {


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
        return inflater.inflate(R.layout.fragment_alphabet, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.alphaRecycler)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        viewModel = ViewModelProvider(this).get(AlphabetVM::class.java)
        viewModel.fetchInterestingList().observe(viewLifecycleOwner, Observer{
            recyclerView.adapter = Alphabet_Adapter(requireContext(),it)
        })

    }

    }
