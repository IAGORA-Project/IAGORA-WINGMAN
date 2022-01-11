package com.iagora.wingman.market.features.list_product_market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.commons.views.helper.SetImage.load
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.presentation.databinding.ItemListProductMarketBinding

class ListProductAdapter : BaseListAdapter<ListProduct.Success>(
    itemsSame = { old, new -> old.idProduct == new.idProduct },
    contentsSame = { old, new -> old == new }
) {

    inner class ListProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemListProductMarketBinding>(
            ItemListProductMarketBinding.inflate(inflater, parent, false)
        ) {
        fun bind(data: ListProduct.Success) {
            binding.apply {
                with(data) {
                    tvPrice.formatPrice(price.toString())
                    tvTitleProduk.text = productName
                    ivCoverProduk.load(img)
                }
            }
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int
    ): RecyclerView.ViewHolder = ListProductViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListProductViewHolder -> holder.bind(getItem(position))
        }
    }
}