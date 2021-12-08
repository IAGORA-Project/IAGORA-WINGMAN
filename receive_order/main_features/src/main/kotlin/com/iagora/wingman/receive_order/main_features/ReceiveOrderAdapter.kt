package com.iagora.wingman.receive_order.main_features

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.helper.model.Product
import com.iagora.wingman.receive_order.main_features.databinding.ItemListReceiveOrderProductBinding

class ReceiveOrderAdapter : BaseListAdapter<Product>(
    itemsSame = { old, new -> old.idProduct == new.idProduct },
    contentsSame = { old, new -> old == new }
) {


    inner class ReceiveOrderViewHolder(inflater: LayoutInflater) :
        BaseViewHolder<ItemListReceiveOrderProductBinding>(
            ItemListReceiveOrderProductBinding.inflate(
                inflater)) {

        fun bind(data: Product) {
            binding.apply {
                with(data) {
                    tvProductName.text = productName
                    tvVariant.text = StringBuilder(data.uom.toString() + "Gram")
                    tvPrice.formatPrice(bargainPrice.toString())
                    tvQty.text = StringBuilder("x${data.qty}")
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
    ): RecyclerView.ViewHolder = ReceiveOrderViewHolder(inflater)


}