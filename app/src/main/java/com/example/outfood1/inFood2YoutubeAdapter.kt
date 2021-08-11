package com.example.outfood1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import kotlinx.android.synthetic.main.activity_in_food2.view.*
import kotlinx.android.synthetic.main.activity_in_food2_youtube_item.view.*

class inFood2YoutubeAdapter : RecyclerView.Adapter<inFood2YoutubeAdapter.ViewHolder>() {

    var items = ArrayList<inFood2YoutubeData>()

    lateinit var listener: inFood2YoutubeDataListener

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_in_food2_youtube_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position:Int) {
        val item = items[position]
        holder.setItem(item)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener{
                listener?.onItemClick(this, itemView, adapterPosition)
            }
        }

        fun setItem(item: inFood2YoutubeData) {
            itemView.inFood2YoutubetTitle.text = item.title
            itemView.inFood2YoutubeContents.text = item.contents
            itemView.inFood2YoutubeDate.text = item.date

            if (item.thumbnails != null && item.thumbnails.isNotEmpty()) {
                Glide.with(itemView.context)
                    .load(item.thumbnails)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .dontAnimate()
                    .into(itemView.inFood2Thumbnail)
            }



        }
    }



}