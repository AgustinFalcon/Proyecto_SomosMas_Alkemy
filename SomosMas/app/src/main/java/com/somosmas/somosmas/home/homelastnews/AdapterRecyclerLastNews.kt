package com.somosmas.somosmas.home.homelastnews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.somosmas.somosmas.R

class AdapterRecyclerLastNews(val image: ArrayList<String>): RecyclerView.Adapter<AdapterRecyclerLastNews.ViewHolder>() {



    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtCard: TextView = itemView.findViewById(R.id.txtCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_recyclerview_lastnews, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtCard.text = image[position]
    }

    override fun getItemCount(): Int = image.size

}