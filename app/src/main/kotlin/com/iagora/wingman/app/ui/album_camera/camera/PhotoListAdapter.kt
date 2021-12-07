package com.iagora.wingman.app.ui.album_camera.camera

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.iagora.wingman.app.databinding.ItemListCameraPhotoBinding
import com.iagora.wingman.core.source.local.model.ImageModel

class PhotoListAdapter(
    private val listData: ArrayList<ImageModel>,
    private val itemCallBackAdapter: ItemCallBackAdapter
): RecyclerView.Adapter<PhotoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoListAdapter.ViewHolder (
        ItemListCameraPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    @SuppressLint("NotifyDataSetChanged")
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
        fun deletePhoto(result: ImageModel)
    }
}