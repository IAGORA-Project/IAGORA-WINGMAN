package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.databinding.ItemCardChangePriceBinding
import com.ssd.iagorawingman.ui.process_order.waiting_list.DifferDetail
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.Other.clearFocus
import com.ssd.iagorawingman.utils.Other.setupTextWithBtn

class ConfirmationDetailAdapter : RecyclerView.Adapter<ConfirmationDetailAdapter.ConfirmationViewHolder>() {

    val differ = AsyncListDiffer(this, DifferDetail)

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
                        clearFocus(tilPriceBargain.editText)
                    }
                }
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConfirmationViewHolder = ConfirmationViewHolder(
        ItemCardChangePriceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: ConfirmationViewHolder,
        position: Int
    ) =
        holder.bind(differ.currentList[position])


    override fun getItemCount(): Int = differ.currentList.size
}