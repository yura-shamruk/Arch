package com.shamruk.arch.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.shamruk.arch.databinding.MainListItemBinding
import com.shamruk.arch.model.User


class MainListAdapter : RecyclerView.Adapter<MainListAdapter.ViewHolder>(){

    private var list: MutableList<User> = mutableListOf()

    companion object{
        const val TAG = "MainListAdapter"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding:MainListItemBinding = DataBindingUtil.bind(itemView)!!

        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent : ViewGroup, type : Int) : MainListAdapter.ViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        val binding = MainListItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder : MainListAdapter.ViewHolder, position : Int){
        val user = list[position]
        holder.bind(user)
        holder.itemView.setOnClickListener { Log.d(TAG, "click on: " + user.name) }
    }

    override fun getItemCount() : Int{
        return list.size
    }

    private fun setData(items: List<User>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    fun replaceData(items: List<User>) {
        setData(items)
    }
}