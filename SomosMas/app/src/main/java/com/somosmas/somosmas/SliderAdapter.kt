package com.somosmas.somosmas

import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.somosmas.somosmas.databinding.ItemPhotoBinding
import com.squareup.picasso.Picasso
import org.w3c.dom.Text
import java.util.ArrayList

class SliderAdapter(
    val viewPager: ViewPager2,
    val listData:MutableList<Data>
) : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {
    inner class SliderViewHolder(var v: View) : RecyclerView.ViewHolder(v) {
        val binding=ItemPhotoBinding.bind(v)
    }

    override fun getItemCount(): Int = listData.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.item_photo, parent, false)
        return SliderViewHolder(v)
    }

    private fun MutableList<Data>.swap(position: Int): Spanned? {
        val htmlString=(Html.fromHtml(Html.fromHtml(this[position].description).toString()))
        return htmlString
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val listImg = listData[position].image
        val listTitle = listData[position].name
        Picasso.get().load(listImg).into(holder.binding.imageSlide)
        holder.binding.txtTitle.text = listTitle
        val htmlTxt=listData.swap(position)
        holder.binding.txtDescription.text = htmlTxt
        if (position == listData.size-2) {
            viewPager.post(run)
        }
    }
    private val run = Runnable {
        listData.addAll(listData)
        notifyDataSetChanged()
    }
}