package com.iagora.wingman.market.presentation.list_product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.core.data.model.market.data.MarketWithProduct
import com.iagora.wingman.core.data.model.market.data.Product
import com.iagora.wingman.core.presentation.base.BaseListAdapter
import com.iagora.wingman.core.presentation.base.BaseViewHolder
import com.iagora.wingman.core.presentation.util.FormatCurrency.formatPrice
import com.iagora.wingman.core.presentation.util.SetImage.load
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.presentation.databinding.ItemListProductMarketBinding
import kotlin.math.sign

class ListProductAdapter : BaseListAdapter<Product>(
    itemsSame = { old, new -> old.idProduct== new.idProduct },
    contentsSame = { old, new -> old == new }
) {

    inner class ListProductViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemListProductMarketBinding>(
            ItemListProductMarketBinding.inflate(inflater, parent, false)
        ) {
        fun bind(data: Product) {
            binding.apply {
                with(data) {
                    tvPrice.formatPrice(productPrice.toString())
                    tvTitleProduk.text = productName
                    ivCoverProduk.load(productImage)
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