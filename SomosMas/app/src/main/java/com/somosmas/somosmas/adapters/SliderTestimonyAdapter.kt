package com.somosmas.somosmas.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.somosmas.somosmas.DataTestimony
import com.somosmas.somosmas.R
import com.somosmas.somosmas.databinding.ItemSliderTestimonyBinding
import com.squareup.picasso.Picasso

class SliderTestimonyAdapter(val testimonyList: List<DataTestimony>) :
    RecyclerView.Adapter<SliderTestimonyAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val binding = ItemSliderTestimonyBinding.bind(v)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_slider_testimony, parent, false)
        return SliderViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val listName = searchSpace(testimonyList[position].name)
        val listImg = testimonyList[position].image
        Picasso.get().load(listImg).into(holder.binding.imgTestimony)
        holder.binding.txtName.text = listName
    }

    fun searchSpace(string: String): String {
        val str: String
        str = string.split("\\s".toRegex())[0]
        return str
    }

    override fun getItemCount(): Int = testimonyList.size

}