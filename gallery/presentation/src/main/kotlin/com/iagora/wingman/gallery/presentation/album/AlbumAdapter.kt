package com.iagora.wingman.gallery.presentation.album

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.helper.SetImage.load
import com.iagora.wingman.commons.views.helper.Util.customPrimaryColor
import com.iagora.wingman.gallery.domain.models.Image
import com.iagora.wingman.gallery.presentation.R
import com.iagora.wingman.gallery.presentation.album.AlbumFragment.Companion.MAX_IMAGE
import com.iagora.wingman.gallery.presentation.databinding.ItemListAlbumsBinding

class AlbumAdapter :
    BaseListAdapter<Image>(itemsSame = { old, new -> old.imagePath == new.imagePath },
        contentsSame = { old, new -> old == new }) {

    private val selectedImageContainer = mutableListOf<Image>()
    private val isNumberOfImageNotMaxYet get() = selectedImageContainer.size < MAX_IMAGE

    private var toolbarText: ((String?) -> Unit)? = null

    fun getToolbarText(item: ((String?)) -> Unit) {
        toolbarText = item
    }


    private inner class AlbumViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemListAlbumsBinding>(
            ItemListAlbumsBinding.inflate(
                inflater,
                parent,
                false
            )
        ) {


        fun bind(data: Image) {
            setTextCountSelected(data.imagePath, data.selected)
            setImageItem(data)
        }

        private fun setImageItem(data: Image) {
            binding.ivImageItem.apply {
                load(data.imageUri.toString())
            }.setOnClickListener {
                if (!data.selected) {
                    if (isNumberOfImageNotMaxYet) {
                        data.addedToSelectedImageContainer()
                    } else {
                        showMessageCantAddImageMore()
                    }
                } else data.removedFromSelectedImageContainer()

                toolbarText?.let { set ->
                    set(
                        if (selectedImageContainer.isEmpty()) null
                        else "Selected ${selectedImageContainer.size} Image"
                    )
                }
            }

        }

        private fun setTextCountSelected(imagePath: String, isSelected: Boolean) {
            binding.apply {
                tvCountSelected.isVisible = isSelected

                selectedImageContainer.forEachIndexed { index, image ->
                    if (image.imagePath == imagePath) {
                        tvCountSelected.text = (index + 1).toString()
                    }
                }
            }
        }


        private fun Image.addedToSelectedImageContainer() {
            selected = true
            selectedImageContainer.add(this)
            notifyItemChanged(adapterPosition)
        }

        private fun Image.removedFromSelectedImageContainer() {
            selected = false
            selectedImageContainer.remove(this)
            notifyItemRangeChanged(0, itemCount)
        }

        private fun showMessageCantAddImageMore() {
            Snackbar.make(
                binding.placeSnackbar,
                itemView.resources.getString(R.string.text_max_image_seleted),
                Snackbar.LENGTH_SHORT
            ).apply { customPrimaryColor(binding.root.context) }.show()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = AlbumViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AlbumViewHolder -> holder.bind(getItem(position))
        }
    }
}