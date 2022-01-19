package com.iagora.wingman.market.presentation.list_market

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.core.presentation.base.BaseListAdapter
import com.iagora.wingman.core.presentation.base.BaseViewHolder
import com.iagora.wingman.core.presentation.util.Util.capitalizeWords
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.presentation.databinding.ItemListMarketBinding

class ListMarketAdapter : BaseListAdapter<ListMarket.Success>(
    itemsSame = { old, new -> old.idMarket == new.idMarket },
    contentsSame = { old, new -> old == new }
) {

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    inner class ListMarketViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemListMarketBinding>(ItemListMarketBinding.inflate(inflater,
            parent,
            false)) {


        fun bind(data: ListMarket.Success) {
            with(data) {
                binding.apply {
                    tvPasarName.text = marketName.capitalizeWords()
                    tvInitialName.text = marketName.substring(0, 1)
                }
                itemView.setOnClickListener {
                    onItemClickListener?.let { click -> click(idMarket) }
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ListMarketViewHolder -> holder.bind(getItem(position))
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = ListMarketViewHolder(inflater, parent)
}