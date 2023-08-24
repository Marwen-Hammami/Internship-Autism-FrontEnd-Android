package com.example.internship_autism.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.internship_autism.R
import com.example.internship_autism.models.ProgressionModel

class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    var progressionList = mutableListOf<ProgressionModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row_list, parent, false)
        return MyViewHolder(inflater)
    }
    override fun getItemCount(): Int {
        return progressionList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(progressionList[position])
    }

    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewIddd: TextView = view.findViewById(R.id.textViewIddd)
        val textViewKeyyy: TextView = view.findViewById(R.id.textViewKeyyy)
        val textViewValueee: TextView = view.findViewById(R.id.textViewValueee)
        fun bind(data: ProgressionModel) {
            textViewIddd.text = data._id
            textViewKeyyy.text = data.progression[0].keys.first().toString()
            textViewValueee.text = data.progression[0].values.first().toString()
        }
    }
}