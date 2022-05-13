package org.chemk.thesis.screens.news

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.chemk.thesis.R
import java.io.IOException
import java.lang.StringBuilder

class CustomRecyclerAdapter(private val titles: List<News>):
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.r_title)
        val body: TextView = itemView.findViewById(R.id.r_body)
        val date: TextView = itemView.findViewById(R.id.r_date)
        val image: ImageView = itemView.findViewById(R.id.r_image)
        val more: TextView = itemView.findViewById(R.id.r_more)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.recyclerview_item, parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = titles[position].title
        holder.body.text = titles[position].body
        holder.date.text = titles[position].date

        holder.more.setOnClickListener {
            val url = Uri.parse(titles[position].more)
            val intent: Intent = Intent(Intent.ACTION_VIEW, url)
            holder.more.context.startActivity(intent)
        }

        if (titles[position].linkImage.isNullOrEmpty()) {
            holder.image.minimumWidth = 200
            holder.image.minimumHeight = 200
            holder.image.maxWidth = 200
            holder.image.maxHeight = 200
            holder.image.setImageResource(R.drawable.logo)
        } else {
            Picasso.get()
                ?.load(titles[position].linkImage)
                ?.into(holder.image)
        }
    }

    override fun getItemCount(): Int = titles.size
}