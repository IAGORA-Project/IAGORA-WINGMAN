package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.databinding.ItemCardPriceBinding
import com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.DifferDetail
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice


class ConfirmedAdapter : RecyclerView.Adapter<ConfirmedAdapter.ConfirmedViewHolder>() {

    val differ = AsyncListDiffer(this, DifferDetail)


    inner class ConfirmedViewHolder(private val binding: ItemCardPriceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProcessOrder.Product) {
            binding.apply {
                with(product) {
                    tvNameItem.text = productName
                    tvItemPrice.formatPrice(bargainPrice.toString())
                    tvQty.text = String.format(
                        itemView.resources.getString(R.string.format_quantity_text),
                        qty
                    )
                    tvUom.text =
                        String.format(itemView.resources.getString(R.string.format_uom), uom, unit)
                }
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfirmedAdapter.ConfirmedViewHolder = ConfirmedViewHolder(
        ItemCardPriceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ConfirmedAdapter.ConfirmedViewHolder,
        position: Int
    ) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size
}