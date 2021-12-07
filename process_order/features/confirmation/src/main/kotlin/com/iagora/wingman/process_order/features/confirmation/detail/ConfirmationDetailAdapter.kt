package com.iagora.wingman.process_order.features.confirmation.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.commons.views.helper.Util.clearFocus
import com.iagora.wingman.commons.views.helper.Util.setupTextWithBtn
import com.iagora.wingman.process_order.commons.views.databinding.ItemCardChangePriceBinding
import com.iagora.wingman.process_order.features.confirmation.R
import com.iagora.wingman.process_order.helper.DifferDetail
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder

class ConfirmationDetailAdapter : RecyclerView.Adapter<ConfirmationDetailAdapter.ConfirmationViewHolder>() {

    val differ = AsyncListDiffer(this, DifferDetail)

    private var onItemClickListener: ((BargainTemp, TextInputLayout, TextView) -> Unit)? = null
    fun setOnItemClickListener(listener: (BargainTemp, TextInputLayout, TextView) -> Unit) {
        onItemClickListener = listener
    }

    data class BargainTemp(
        val idProduct: String,
        val newBargain: Int,
        val uom: Int,
    )


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
                                BargainTemp(
                                    idProduct = idProduct,
                                    uom = uom,
                                    newBargain = tilPriceBargain.editText?.text.toString()
                                        .toInt(),
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