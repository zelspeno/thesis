package org.chemk.thesis.screens.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.chemk.thesis.R
import org.chemk.thesis.screens.news.CustomRecyclerAdapter

class HomeRecyclerAdapter(private val lessons: List<Schedule>) :
    RecyclerView.Adapter<HomeRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val subject: TextView = itemView.findViewById(R.id.subject)
        val teacher: TextView = itemView.findViewById(R.id.teacher)
        val classRoom: TextView = itemView.findViewById(R.id.classRoom)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.schedulerecyclerview_item, parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.subject.text = lessons[position].subject
        holder.teacher.text = lessons[position].teacher
        holder.classRoom.text = lessons[position].classRoom
    }

    override fun getItemCount(): Int = lessons.size

}