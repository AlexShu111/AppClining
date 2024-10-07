package com.example.appforfun

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ServicesAdapter(var services: List<Services>, var context: Context): RecyclerView.Adapter<ServicesAdapter.MyViewHolder>() {
    class MyViewHolder(view: View ): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.ImGenCli)
        val title: TextView = view.findViewById(R.id.Service_list_title)
        val desc: TextView = view.findViewById(R.id.Service_list_desc)
        val price: TextView = view.findViewById(R.id.Service_list_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_services,parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return services.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = services[position].title
        holder.desc.text = services[position].desc
        holder.price.text = services[position].price.toString() + "byn"

        val imageId = context.resources.getIdentifier(
            services[position].image,
            "drawable",
            context.packageName
        )
        holder.image.setImageResource(imageId)
    }
}