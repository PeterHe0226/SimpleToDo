package com.example.simpletodo

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.R
import android.content.Context

import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Button





/**
 * A bridge to tell recyclerViewer how to display the data we give it
 */
class TaskItemAdapter(val listOfItems:List<String>, val longClickListener:OnLongClickListener) : RecyclerView.Adapter<TaskItemAdapter.ViewHolder>(){

    interface OnLongClickListener{
        fun OnItemLongClicked(position: Int)
    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        init {
            textView= itemView.findViewById(android.R.id.text1)
            itemView.setOnLongClickListener{
                longClickListener.OnItemLongClicked(adapterPosition)
                true
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)

        // Inflate the custom layout

        // Inflate the custom layout
        val contactView: View = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)

        // Return a new holder instance

        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=listOfItems.get(position)
        holder.textView.text=item
    }

    override fun getItemCount(): Int {
        return listOfItems.size
    }

}