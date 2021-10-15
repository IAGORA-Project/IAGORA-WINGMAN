package com.ssd.iagorawingman.ui.album_camera.albums

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssd.iagorawingman.data.source.local.model.Image
import com.ssd.iagorawingman.databinding.ItemListAlbumsBinding


class AlbumsAdapter(
    private val listData: ArrayList<Image>,
    private val itemCallBackAdapter: ItemCallBackAdapter,
    private val context: Context
): RecyclerView.Adapter<AlbumsAdapter.ViewHolder>() {

     var selectedImage: ArrayList<Image> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumsAdapter.ViewHolder (
        ItemListAlbumsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: AlbumsAdapter.ViewHolder, position: Int) {
        val data = listData[position]

        Glide
            .with(holder.itemView.context)
            .load(data.imageUri)
            .into(holder.binding.ivImageItem)


        holder.binding.ivImageItem.setOnClickListener {
             if(!data.selected){
                 if(selectedImage.size < 9){
                     data.selected = true
                     selectedImage.add(data)
                 }else{
                     Toast.makeText(context, "Maksimal 9 foto", Toast.LENGTH_SHORT).show()
                 }
            }else{
                 data.selected = false
                 selectedImage.remove(data)

             }

            itemCallBackAdapter.onSelectedImage(selectedImage)
            notifyDataSetChanged()
        }

        selectedImage.forEachIndexed { index, image ->
            if(image.imagePath == data.imagePath){
                holder.binding.tvCountSelected.text = (index + 1).toString()
            }
        }

        if(data.selected){
            holder.binding.coba.visibility = View.VISIBLE
            holder.binding.tvCountSelected.visibility = View.VISIBLE
        }else{
            holder.binding.coba.visibility = View.GONE
            holder.binding.tvCountSelected.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(val binding: ItemListAlbumsBinding): RecyclerView.ViewHolder(binding.root)

    interface ItemCallBackAdapter {
        fun onSelectedImage (result: ArrayList<Image>)
    }
}