package com.somosmas.somosmas.homelastnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.somosmas.somosmas.R
import com.somosmas.somosmas.databinding.ItemRecyclerviewLastnewsBinding
import com.squareup.picasso.Picasso

class AdapterRecyclerLastNews(private val lastNewsList: MutableList<DataLastNews>): RecyclerView.Adapter<AdapterRecyclerLastNews.ViewHolder>() {



    inner class ViewHolder(var v: View): RecyclerView.ViewHolder(v) {
        val binding = ItemRecyclerviewLastnewsBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_lastnews, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = lastNewsList[position].name
        val image = lastNewsList[position].image
        Picasso.get().load(image).into(holder.binding.ivImage)
        holder.binding.txtName.text = name
    }

    override fun getItemCount(): Int = lastNewsList.size

}