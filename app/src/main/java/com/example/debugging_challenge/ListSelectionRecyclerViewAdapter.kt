package com.example.debugging_challenge

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ListSelectionRecyclerViewAdapter(private val activity: MainActivity, private val stateAndCapitals: ArrayList<ArrayList<String>>) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_selection_view_holder, parent, false)

        return ListSelectionViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
        holder.listPosition.text = (position+1).toString()
        holder.listTitle.text = stateAndCapitals[position][0]
        holder.listSubTitle.text = stateAndCapitals[position][1]
        holder.myView.setOnClickListener{
            Toast.makeText(activity, "${stateAndCapitals[position][1]}is capital of ${stateAndCapitals[position][0]}", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return stateAndCapitals.size
    }

}