package com.iagora.wingman.process_order.features.confirmed.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.databinding.ItemProductBinding
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.helper.model.Product
import com.iagora.wingman.process_order.features.confirmed.R


class ConfirmedDetailAdapter : BaseListAdapter<Product>(
    itemsSame = { old, new -> old.idProduct == new.idProduct },
    contentsSame = { old, new -> old == new }
) {


    inner class ConfirmedViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemProductBinding>(
            ItemProductBinding.inflate(
                inflater,
                parent,
                false
            )
        ) {
        fun bind(product: Product) {
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
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = ConfirmedViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ConfirmedViewHolder -> holder.bind(getItem(position))
        }
    }

}