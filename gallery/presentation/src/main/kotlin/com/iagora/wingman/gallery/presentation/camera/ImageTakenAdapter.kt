package com.iagora.wingman.gallery.presentation.camera

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.core.presentation.base.BaseListAdapter
import com.iagora.wingman.core.presentation.base.BaseViewHolder
import com.iagora.wingman.core.presentation.util.SetImage.load
import com.iagora.wingman.gallery.domain.models.Image
import com.iagora.wingman.gallery.presentation.databinding.ImageTakenBinding
import java.io.File


class ImageTakenAdapter :
    BaseListAdapter<Image>(itemsSame = { old, new -> old.imagePath == new.imagePath },
        contentsSame = { old, new -> old == new }) {


    private var itemDeleted: ((Image) -> Unit)? = null
    fun getItemDeleted(item: ((Image)) -> Unit) {
        itemDeleted = item
    }

    private inner class ImageTakenViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ImageTakenBinding>(
            ImageTakenBinding.inflate(
                inflater,
                parent,
                false
            )
        ) {
        fun bind(data: Image) {
            val fileName = File(data.imageUri.path ?: "")

            binding.apply {
                ivItemPhoto.load(data.imageUri.toString())
                btnDelete.setOnClickListener {
                    notifyItemRemoved(adapterPosition)
                    itemDeleted?.let { delete -> delete(data) }
                    fileName.delete()
                }
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = ImageTakenViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageTakenViewHolder -> holder.bind(getItem(position))
        }
    }
}