package com.somosmas.somosmas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import com.somosmas.somosmas.R

class
SliderSeccionWelcomeAdapter(
    private val listPhoto: List<Int>,
    private val titleList: List<String>,
    private val descriptionList: List<String>,
    var ctx: Context
) : PagerAdapter() {

    lateinit var layoutInflater: LayoutInflater

    override fun getCount(): Int = listPhoto.size

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = LayoutInflater.from(ctx)
        var view = layoutInflater.inflate(R.layout.item_slider_welcome, container, false)
        val img = view.findViewById<ImageView>(R.id.imageSlide)
        val title = view.findViewById<TextView>(R.id.txtTitle)
        val description = view.findViewById<TextView>(R.id.txtDescription)
        title.text = titleList.get(position)
        description.text = descriptionList.get(position)
        img.setImageResource(listPhoto.get(position))

        container.addView(view, 0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}