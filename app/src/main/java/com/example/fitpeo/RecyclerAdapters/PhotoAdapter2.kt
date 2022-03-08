package com.example.fitpeo.RecyclerAdapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fitpeo.R
import de.hdodenhof.circleimageview.CircleImageView

class PhotoAdapter2 : RecyclerView.Adapter<PhotoAdapter2.MyViewHolder>() {
    val items : ArrayList<Int> = ArrayList()

    lateinit var context: Context


    inner class MyViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        lateinit var image: CircleImageView

        init {
            image = itemView.findViewById(R.id.photo_circle)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cells = layoutInflater.inflate(R.layout.photo_item,parent,false)
        context = parent.context
        return MyViewHolder(cells)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.image.setImageResource(items[position])

    }

    override fun getItemCount(): Int {
        return items.size

    }

    fun updatePhotos(photos: ArrayList<Int>) {
        items.clear()
        items.addAll(photos)
        notifyDataSetChanged()
    }

}
