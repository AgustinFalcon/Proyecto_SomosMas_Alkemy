package com.somosmas.somosmas

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import org.w3c.dom.Text
import java.util.ArrayList

class SliderAdapter(
    val viewPager: ViewPager2,
    val imgList: ArrayList<SliderItem>,
    val titleList: List<String>,
    val descrptionList: List<String>
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    inner class SliderViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val img = v.findViewById<ImageView>(R.id.imageSlide)
        val title=v.findViewById<TextView>(R.id.txtTitle)
        val description=v.findViewById<TextView>(R.id.txtDescription)
    }

    override fun getItemCount(): Int = imgList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_photo, parent, false)
        return SliderViewHolder(v)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val listImg = imgList[position]
        val listTitlte = titleList[position]
        val listDescription = descrptionList[position]
        holder.img.setImageResource(listImg.img)
        holder.title.setText(listTitlte)
        holder.description.setText(listDescription)
        if (position == imgList.size) {
            viewPager.post(run)
        }
    }
    private val run = Runnable {
        imgList.addAll(imgList)
        notifyDataSetChanged()
    }
}