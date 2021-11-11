package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail

import android.widget.Adapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder


object DifferDetail : DiffUtil.ItemCallback<ProcessOrder.Product>() {
    override fun areItemsTheSame(
        oldItem: ProcessOrder.Product,
        newItem: ProcessOrder.Product
    ): Boolean {
        return oldItem.idProduct == newItem.idProduct
    }

    override fun areContentsTheSame(
        oldItem: ProcessOrder.Product,
        newItem: ProcessOrder.Product
    ): Boolean {
        return oldItem == newItem
    }
}

