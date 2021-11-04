package com.ssd.iagorawingman.ui.process_order.on_process

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.databinding.ItemCardChangePriceBinding
import com.ssd.iagorawingman.utils.FormatCurrency

class DetailOnProcessProductAdapter :
    RecyclerView.Adapter<DetailOnProcessProductAdapter.DetailOnProcessProductViewHolder>() {
    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    inner class DetailOnProcessProductViewHolder(private val binding: ItemCardChangePriceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProcessOrder.Product) {
            binding.apply {
                with(product) {
                    tvNameItem.text = productName
                    tvItemPrice.text = String.format(
                        itemView.resources.getString(R.string.format_item_price),
                        FormatCurrency.getCurrencyRp(bargainPrice.toDouble()),
                        unit
                    )
                    tilPriceBargain.editText?.text?.toString()?.run {
                        btnBargain.setOnClickListener {
                            onItemClickListener?.let { click ->
                                click(this)
                            }
                        }
                    }

                }
            }
        }

    }

    private val differCallback =
        object : DiffUtil.ItemCallback<ProcessOrder.Product>() {
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


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DetailOnProcessProductViewHolder = DetailOnProcessProductViewHolder(
        ItemCardChangePriceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )


    override fun onBindViewHolder(holder: DetailOnProcessProductViewHolder, position: Int) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size

}