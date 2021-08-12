package com.example.outfood1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.outfood3review.view.*

class outFood3ReviewAdapter : RecyclerView.Adapter<outFood3ReviewAdapter.ViewHolder>() {
    var items = ArrayList<outFood3Review>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.outfood3review,parent,false)
        return ViewHolder(itemView)
    }

    // ?parent.context라고 써도 되는거겠지?


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setItem(item: outFood3Review) {
            itemView.binding.outFood3ReviewName.text = item.name
            itemView.outFoodStarInput.rating = item.rating?.toFloat()?:0.0f
            itemView.outFood3Review.text = item.contents
        }
    }

}