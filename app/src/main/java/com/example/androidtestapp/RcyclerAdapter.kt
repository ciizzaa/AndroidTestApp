package com.example.androidtestapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RcyclerAdapter(data: ArrayList<CustomItem>,var context: Context) :
    RecyclerView.Adapter<RcyclerAdapter.ViewHolder>() {

    internal var data: List<CustomItem>

    init {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layout = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false)
        return ViewHolder(layout)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.fullName.text = data[position].itemFullName
        holder.image.setImageResource(data[position].itemImage)

    }

    override fun getItemCount(): Int {
        return data.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var fullName: TextView
        internal var image: ImageView

        init {
            fullName = itemView.findViewById<TextView>(R.id.fullNameCustomItem)
            image = itemView.findViewById<ImageView>(R.id.profileImageCustomItem)
        }
    }


}