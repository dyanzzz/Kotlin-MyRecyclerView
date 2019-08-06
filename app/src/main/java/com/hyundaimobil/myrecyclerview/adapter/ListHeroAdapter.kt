package com.hyundaimobil.myrecyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AdapterListUpdateCallback
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hyundaimobil.myrecyclerview.R
import com.hyundaimobil.myrecyclerview.model.Hero

import java.util.ArrayList

class ListHeroAdapter(private val listHero: ArrayList<Hero>) : RecyclerView.Adapter<ListHeroAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row_hero, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val hero = listHero[position]
        Glide.with(holder.itemView.context)
            .load(hero.photo)
            .apply(RequestOptions().override(55, 55))
            .into(holder.imgPhoto)
        holder.tvName.text = hero.name
        holder.tvFrom.text = hero.from

        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listHero[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int {
        return listHero.size
    }


    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var imgPhoto: ImageView
        internal var tvName: TextView
        internal var tvFrom: TextView

        init {
            imgPhoto = itemView.findViewById(R.id.img_item_photo)
            tvName = itemView.findViewById(R.id.tv_item_name)
            tvFrom = itemView.findViewById(R.id.tv_item_from)
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Hero)
    }

}
