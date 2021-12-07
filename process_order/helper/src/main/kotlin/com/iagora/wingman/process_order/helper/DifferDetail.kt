package com.iagora.wingman.process_order.helper


import androidx.recyclerview.widget.DiffUtil
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder


object DifferDetail :
    DiffUtil.ItemCallback<ProcessOrder.Product>() {
    override fun areItemsTheSame(
        oldItem: ProcessOrder.Product,
        newItem: ProcessOrder.Product,
    ): Boolean {
        return oldItem.idProduct == newItem.idProduct
    }

    override fun areContentsTheSame(
        oldItem: ProcessOrder.Product,
        newItem: ProcessOrder.Product,
    ): Boolean {
        return oldItem == newItem
    }
}

