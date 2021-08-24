package com.somosmas.somosmas.home.ui.novelties

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.somosmas.somosmas.R

class RecyclerAdapterNovelties: RecyclerView.Adapter<RecyclerAdapterNovelties.ViewHolder>(){

    private var title = arrayOf("Tarde deportiva","Tarde deportiva","Tarde deportiva","Tarde deportiva")
    private var description = arrayOf("agregar texto","agregar texto","agregar texto","agregar texto")
    private var date = arrayOf("Publicado: 28/07/21","Publicado: 28/07/21","Publicado: 28/07/21","Publicado: 28/07/21")
    private var image = arrayOf(R.drawable.logo_somos_mas, R.drawable.logo_somos_mas, R.drawable.logo_somos_mas, R.drawable.logo_somos_mas)

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemImage: ImageView
        var itemTitle: TextView
        var itemDescription: TextView
        var itemDate: TextView

        init {
            itemImage = itemView.findViewById(R.id.ivFotoDeportiva)
            itemTitle = itemView.findViewById(R.id.tvTardeDeportiva)
            itemDescription = itemView.findViewById(R.id.tvDescription)
            itemDate = itemView.findViewById(R.id.tvDate)

            itemView.setOnClickListener{
                val position: Int = adapterPosition

                Toast.makeText(itemView.context,"clicked on ${title[position]}",Toast.LENGTH_SHORT).show()
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerAdapterNovelties.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.template_fragment_novelties, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapterNovelties.ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDescription.text = description[position]
        holder.itemDate.text = date[position]
        holder.itemImage.setImageResource(image[position])
    }

    override fun getItemCount(): Int = title.size

}