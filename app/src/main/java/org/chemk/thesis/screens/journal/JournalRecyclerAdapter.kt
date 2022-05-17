package org.chemk.thesis.screens.journal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import org.chemk.thesis.R
import org.chemk.thesis.screens.home.HomeRecyclerAdapter

class JournalRecyclerAdapter(private val markForDay: List<MarkForDay>) :
    RecyclerView.Adapter<JournalRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val day: TextView = itemView.findViewById(R.id.j_day)
        val mark: TextView = itemView.findViewById(R.id.j_mark)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.journalrecyclerview_item, parent, false
        )
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.day.text = markForDay[position].day.toString()
        holder.mark.text = markForDay[position].mark
    }

    override fun getItemCount(): Int = markForDay.size

}