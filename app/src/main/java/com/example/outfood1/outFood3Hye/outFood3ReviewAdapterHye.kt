package com.example.outfood1.outFood3Hye

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.outfood1.R
import com.example.outfood1.outFood3Review
import kotlinx.android.synthetic.main.outfood3review.view.*

class outFood3ReviewAdapterHye : RecyclerView.Adapter<outFood3ReviewAdapterHye.ViewHolder>() {
    var items = ArrayList<outFood3ReviewHye>()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.outfood3review,parent,false) //어댑터 xml은 원래 만들어놓은 어댑터 그대로 사용하면됨
        return ViewHolder(itemView)
    }

    // ?parent.context라고 써도 되는거겠지?


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.setItem(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun setItem(item: outFood3ReviewHye) {
            itemView.outFood3ReviewName.text = item.name
            itemView.outFoodStarInput.rating = item.rating?.toFloat()?:0.0f
            itemView.outFood3Review.text = item.contents
        }
    }

}