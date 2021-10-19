package com.ssd.iagorawingman.ui.pasar.add_product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssd.iagorawingman.data.source.local.model.ImageModel
import com.ssd.iagorawingman.databinding.ItemListPhotoProductBinding

class PhotoListProductAdapter(
    private val listData: ArrayList<ImageModel>,
): RecyclerView.Adapter<PhotoListProductAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PhotoListProductAdapter.ViewHolder (
        ItemListPhotoProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: PhotoListProductAdapter.ViewHolder, position: Int) {
       val data = listData[position]

        Glide
            .with(holder.itemView.context)
            .load(data.imageUri)
            .into(holder.binding.ivItemPhoto)

        holder.binding.ivDeletePhoto.setOnClickListener {
            listData.remove(data)
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(val binding: ItemListPhotoProductBinding): RecyclerView.ViewHolder(binding.root)
}