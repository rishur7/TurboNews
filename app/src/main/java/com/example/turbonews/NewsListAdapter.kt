package com.example.turbonews

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder { //jitne views hai utni baar call hoga fir recycle hoga
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false) //converts xml to view
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) { //data filling ka kaam krega basically binds the data
        holder.progbar.visibility=View.VISIBLE
        val currentItem = items[position]
        holder.titleView.text = currentItem.title //gives data to xml elements
        holder.author.text = currentItem.author //ye holder NewsViewHolder vala hi hai
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).listener(object:RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                holder.progbar.visibility=View.GONE
            return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                holder.progbar.visibility=View.GONE
            return false
            }
        }).into(holder.image)
    }

    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged() //calls all three funcs again
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val titleView: TextView = itemView.findViewById(R.id.title) //itemView is our that small view isse data itemView ke andr arha hoga
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
    val progbar: ProgressBar=itemView.findViewById(R.id.progressBar)
}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}