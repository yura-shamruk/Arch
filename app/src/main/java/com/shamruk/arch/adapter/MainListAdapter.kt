package com.shamruk.arch.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.shamruk.arch.R

class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>(){

    private var list: MutableList<String> = mutableListOf()

    companion object{
        const val TAG = "MainListAdapter"
    }

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
        holder.itemView.setOnClickListener { Log.d(TAG, "click on: $title") }
    }

    override fun getItemCount() : Int{
        return list.size
    }

    private fun setData(items: List<String>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun replaceData(items: List<String>) {
        setData(items)
    }
}