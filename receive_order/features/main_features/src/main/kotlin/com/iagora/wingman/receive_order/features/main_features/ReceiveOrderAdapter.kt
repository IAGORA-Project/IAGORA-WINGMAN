package com.iagora.wingman.receive_order.features.main_features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.databinding.ItemProductBinding
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.core.domain.model.dto.Product


class ReceiveOrderAdapter : BaseListAdapter<Product>(
    itemsSame = { old, new -> old.idProduct == new.idProduct },
    contentsSame = { old, new -> old == new }
) {


    inner class ReceiveOrderViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemProductBinding>(
            ItemProductBinding.inflate(
                inflater, parent, false
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


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ReceiveOrderViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = ReceiveOrderViewHolder(inflater, parent)


}