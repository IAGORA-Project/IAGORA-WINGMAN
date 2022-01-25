package com.iagora.wingman.market.presentation.add_product

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.core.presentation.base.BaseListAdapter
import com.iagora.wingman.core.presentation.base.BaseViewHolder
import com.iagora.wingman.core.presentation.util.convertToLong
import com.iagora.wingman.market.presentation.R
import com.iagora.wingman.market.presentation.databinding.ItemListVariantPriceBinding

class VariantAdapter : BaseListAdapter<AddProductFragment.Companion.TempListVariant>(
    itemsSame = { old, new -> old.id == new.id },
    contentsSame = { old, new -> old == new }
) {


    private var onClickDeleteItem: ((AddProductFragment.Companion.TempListVariant) -> Unit)? = null
    fun setonClickDeleteItem(listener: (AddProductFragment.Companion.TempListVariant) -> Unit) {
        onClickDeleteItem = listener
    }

    inner class VariantViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemListVariantPriceBinding>(
            ItemListVariantPriceBinding.inflate(inflater, parent, false)
        ) {


        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(data: AddProductFragment.Companion.TempListVariant) {


            binding.apply {
                ivDeleteVariant.apply {
                    isEnabled = this@VariantAdapter.itemCount > 1
                    setImageDrawable(
                        if (this@VariantAdapter.itemCount == 1) resources.getDrawable(
                            R.drawable.ic_close,
                            null
                        ) else resources.getDrawable(R.drawable.ic_close_round_red, null)
                    )
                    setOnClickListener {
                        onClickDeleteItem?.let { delete ->
                            delete(data)
                        }
                        tilPrice.editText?.text?.clear()
                        tilVariantScale.editText?.text?.clear()
                        this@VariantAdapter.notifyItemRemoved(adapterPosition)
                    }
                }


                tilVariantScale.apply {
                    editText?.addTextChangedListener {
                        data.variant = it?.convertToLong()
                    }
                    data.variant = editText?.text?.convertToLong()
                }.isVisible =
                    data.unit == itemView.resources.getString(R.string.text_gram)

                tilPrice.apply {
                    data.price = editText?.text?.convertToLong()
                }.editText?.addTextChangedListener {
                    data.price = it?.convertToLong()
                }
            }
        }


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = VariantViewHolder(inflater, parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VariantViewHolder -> holder.bind(getItem(position))
        }
    }
}