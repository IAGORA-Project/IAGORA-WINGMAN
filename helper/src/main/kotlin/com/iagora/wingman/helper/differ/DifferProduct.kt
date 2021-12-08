package com.iagora.wingman.helper.differ


import androidx.recyclerview.widget.DiffUtil
import com.iagora.wingman.helper.model.Product


object DifferProduct :
    DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(
        oldItem: Product,
        newItem: Product,
    ): Boolean {
        return oldItem.idProduct == newItem.idProduct
    }

    override fun areContentsTheSame(
        oldItem: Product,
        newItem: Product,
    ): Boolean {
        return oldItem == newItem
    }
}

