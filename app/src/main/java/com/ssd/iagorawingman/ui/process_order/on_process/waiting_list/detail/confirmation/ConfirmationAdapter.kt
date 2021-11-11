package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmation

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.databinding.ItemCardChangePriceBinding
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.Other
import com.ssd.iagorawingman.utils.Other.setupTextWithBtn

class ConfirmationAdapter : RecyclerView.Adapter<ConfirmationAdapter.ConfirmationViewHolder>() {

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

    private var onItemClickListener: ((BargainBody, TextInputLayout, TextView) -> Unit)? = null
    fun setOnItemClickListener(listener: (BargainBody, TextInputLayout, TextView) -> Unit) {
        onItemClickListener = listener
    }


    inner class ConfirmationViewHolder(private val binding: ItemCardChangePriceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProcessOrder.Product) {
            binding.apply {

                tilPriceBargain.editText?.setupTextWithBtn(btnBargain)

                with(product) {

                    tvNameItem.text = productName
                    tvItemPrice.formatPrice(bargainPrice.toString())
                    tvQty.text = String.format(
                        itemView.resources.getString(R.string.format_quantity_text),
                        qty
                    )
                    tvUom.text =
                        String.format(itemView.resources.getString(R.string.format_uom), uom, unit)


                    btnBargain.setOnClickListener {
                        onItemClickListener?.let { click ->
                            click(
                                BargainBody(
                                    idProduct = idProduct,
                                    uom = uom,
                                    newBargain = tilPriceBargain.editText?.text.toString()
                                        .toInt()
                                ),
                                tilPriceBargain,
                                tvItemPrice
                            )
                        }
                        Other.clearFocus(tilPriceBargain.editText)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfirmationAdapter.ConfirmationViewHolder = ConfirmationViewHolder(
        ItemCardChangePriceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ConfirmationAdapter.ConfirmationViewHolder,
        position: Int
    ) =
        holder.bind(differ.currentList[position])

    override fun getItemCount(): Int = differ.currentList.size
}