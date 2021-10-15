package com.ssd.iagorawingman.ui.album_camera.camera

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.camera.core.ImageCapture
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssd.iagorawingman.data.source.local.model.Image
import com.ssd.iagorawingman.data.source.local.model.ImageTakeCamera
import com.ssd.iagorawingman.databinding.ItemListCameraPhotoBinding

class PhotoListAdapter(
    private val listData: ArrayList<Image>,
    private val itemCallBackAdapter: ItemCallBackAdapter
): RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoListAdapter.ViewHolder (
        ItemListCameraPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PhotoListAdapter.ViewHolder, position: Int) {
       val data = listData[position]

        println("CEKPATCHCH ${listData}")

        Glide
            .with(holder.itemView.context)
            .load(data.imageUri)
            .into(holder.binding.ivItemPhoto)

        holder.binding.ivDeletePhoto.setOnClickListener {
            listData.remove(data)
            itemCallBackAdapter.deletePhoto(data)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(val binding: ItemListCameraPhotoBinding): RecyclerView.ViewHolder(binding.root)

    interface ItemCallBackAdapter {
        fun deletePhoto(result: Image)
    }
}