package com.shamruk.arch.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shamruk.arch.R

class MainListAdapter (private val list: List<String>) : RecyclerView.Adapter<MainListAdapter.ViewHolder>(){

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTextView: TextView = itemView.findViewById(R.id.title)

    }

    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : MainListAdapter.ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.main_list_item, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder : MainListAdapter.ViewHolder, position : Int){
        val title = list[position]
        holder.titleTextView.text = title
    }

    override fun getItemCount() : Int{
        return list.size
    }
}