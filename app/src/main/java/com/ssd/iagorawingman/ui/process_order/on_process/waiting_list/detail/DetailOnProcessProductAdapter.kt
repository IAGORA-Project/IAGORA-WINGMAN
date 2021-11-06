package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.fromHtml
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.databinding.ItemCardChangePriceBinding
import com.ssd.iagorawingman.utils.FormatCurrency

class DetailOnProcessProductAdapter :
    RecyclerView.Adapter<DetailOnProcessProductAdapter.DetailOnProcessProductViewHolder>() {
    private var onItemClickListener: ((BargainBody) -> Unit)? = null

    fun setOnItemClickListener(listener: (BargainBody) -> Unit) {
        onItemClickListener = listener
    }


    inner class DetailOnProcessProductViewHolder(private val binding: ItemCardChangePriceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProcessOrder.Product) {
            binding.apply {
                with(product) {
                    tvNameItem.text = productName
                    tvItemPrice.text =
                        fromHtml(
                            "${FormatCurrency.getCurrencyRp(bargainPrice.toDouble())}/<sub>$unit</sub>",
                            HtmlCompat.FROM_HTML_MODE_COMPACT
                        )


                    tilPriceBargain.editText?.doOnTextChanged { _, _, _, count ->
                        btnBargain.isEnabled = count > 0
                        if (tilPriceBargain.editText?.text.toString().isEmpty()
                        )
                            tilPriceBargain.editText?.clearFocus()

                    }

                    btnBargain.setOnClickListener {
                        onItemClickListener?.let { click ->
                            click(
                                BargainBody(
                                    idProduct = idProduct,
                                    uom = uom,
                                    newBargain = tilPriceBargain.editText?.text.toString().toInt()
                                )
                            )
                            tilPriceBargain.editText?.clearFocus()
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