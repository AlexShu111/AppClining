package com.example.appforfun

import android.content.Context
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.ScaleAnimation
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
        var isZoomed = false
        holder.title.text = services[position].title
        holder.desc.text = services[position].desc
        holder.price.text = services[position].price.toString() + "byn"

        val imageId = context.resources.getIdentifier(
            services[position].image,
            "drawable",
            context.packageName
        )
        holder.image.setImageResource(imageId)

        holder.image.setOnLongClickListener {
            if (!isZoomed) {
                // Анимация увеличения
                zoomInAnimation(holder.image)
            } else {
                // Анимация уменьшения (возврат к исходному размеру)
                zoomOutAnimation(holder.image)
            }
            isZoomed = !isZoomed
            true
        }
    }

    private fun zoomInAnimation(view: View) {
        val scaleAnimation = ScaleAnimation(
            1f, 2f,  // Масштаб по оси X от 1 до 2
            1f, 2f,  // Масштаб по оси Y от 1 до 2
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 300 // Продолжительность анимации 300 мс
        scaleAnimation.fillAfter = true // Оставить увеличенный размер
        view.startAnimation(scaleAnimation)
    }

    // Метод для уменьшения изображения
    private fun zoomOutAnimation(view: View) {
        val scaleAnimation = ScaleAnimation(
            2f, 1f,  // Масштаб по оси X от 2 до 1
            2f, 1f,  // Масштаб по оси Y от 2 до 1
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
            ScaleAnimation.RELATIVE_TO_SELF, 0.5f
        )
        scaleAnimation.duration = 300 // Продолжительность анимации 300 мс
        scaleAnimation.fillAfter = true // Вернуть исходный размер
        view.startAnimation(scaleAnimation)
    }
}