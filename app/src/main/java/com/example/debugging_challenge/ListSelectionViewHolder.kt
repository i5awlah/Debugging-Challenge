package com.example.debugging_challenge

import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class ListSelectionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val listPosition: TextView = itemView.findViewById(R.id.itemNumber)
    val listTitle: TextView = itemView.findViewById(R.id.countryString)
    val listSubTitle: TextView = itemView.findViewById(R.id.capitalString)
    val myView: LinearLayout = itemView.findViewById(R.id.myViewHolder)
}