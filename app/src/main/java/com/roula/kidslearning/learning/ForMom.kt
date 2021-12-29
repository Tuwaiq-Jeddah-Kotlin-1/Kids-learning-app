package com.roula.kidslearning.learning

import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.roula.kidslearning.R
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator


class ForMom : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var plus: FloatingActionButton
    private lateinit var viewModel: AlphabetVM
    private lateinit var mAdapter: Mom_Adapter


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
        return inflater.inflate(R.layout.fragment_for_mom, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plus = view.findViewById(R.id.fab_add_task)
        plus.setOnClickListener {
           it.findNavController().navigate(R.id.action_forMom_to_add_mom)
        }
        viewModel = ViewModelProvider(this).get(AlphabetVM::class.java)
        recyclerView = view.findViewById(R.id.recycler_view_text)
        recyclerView.layoutManager = LinearLayoutManager(this.context)


        viewModel.fetchText(viewLifecycleOwner).observe(viewLifecycleOwner, {

            mAdapter= Mom_Adapter(requireContext(),it,viewModel)
            recyclerView.adapter = mAdapter

        })

        val callback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mAdapter.deleteText(viewHolder.adapterPosition)
                //mAdapter.notifyItemRemoved(viewHolder.adapterPosition)
                //recyclerView.adapter!!.notifyItemRemoved(viewHolder.position)
                //recyclerView.adapter!!.notifyItemRemoved(viewHolder.adapterPosition)

                Toast.makeText(context,"deleted", Toast.LENGTH_SHORT).show()
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {

                RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            android.R.color.holo_red_light
                        )
                    )
                    .addActionIcon(R.drawable.ic_cancel)
                    .addSwipeRightLabel("Deleting the Item")
                    .addSwipeLeftLabel("Deleting the Item")
                    .setSwipeRightLabelColor(R.color.white)
                    .setSwipeLeftLabelColor(R.color.white)
                    .create()
                    .decorate()




                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)


    }
    }

