package com.iagora.wingman.process_order.features.confirmation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.commons.views.helper.Util.clearFocus
import com.iagora.wingman.commons.views.helper.Util.setupTextWithBtn
import com.iagora.wingman.core.domain.model.dto.Product
import com.iagora.wingman.process_order.commons.views.databinding.ItemCardChangePriceBinding
import com.iagora.wingman.process_order.features.confirmation.R

class ConfirmationDetailAdapter : BaseListAdapter<Product>(
    itemsSame = { old, new -> old.idProduct == new.idProduct },
    contentsSame = { old, new -> old == new }
) {


    private var onItemClickListener: ((BargainTemp, TextInputLayout, TextView) -> Unit)? = null
    fun setOnItemClickListener(listener: (BargainTemp, TextInputLayout, TextView) -> Unit) {
        onItemClickListener = listener
    }

    data class BargainTemp(
        val idProduct: String,
        val newBargain: Int,
        val uom: Int,
    )


    inner class ConfirmationViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemCardChangePriceBinding>(
            ItemCardChangePriceBinding.inflate(
                inflater, parent, false
            )
        ) {
        fun bind(data: Product) {
            binding.apply {

                tilPriceBargain.editText?.setupTextWithBtn(btnBargain)

                with(data) {
                    containerItemProduct.apply {

                        tvNameItem.text = productName
                        tvItemPrice.formatPrice(bargainPrice.toString())
                        tvQty.text = String.format(
                            itemView.resources.getString(R.string.format_quantity_text),
                            qty
                        )
                        tvUom.text =
                            String.format(
                                itemView.resources.getString(R.string.format_uom),
                                uom,
                                unit
                            )
                    }


                    btnBargain.setOnClickListener {
                        onItemClickListener?.let { click ->
                            click(
                                BargainTemp(
                                    idProduct = idProduct,
                                    uom = uom,
                                    newBargain = tilPriceBargain.editText?.text.toString()
                                        .toInt(),
                                ),
                                tilPriceBargain,
                                containerItemProduct.tvItemPrice
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
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = ConfirmationViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ConfirmationViewHolder -> holder.bind(getItem(position))
        }
    }
}