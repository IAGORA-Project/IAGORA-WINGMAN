package com.iagora.wingman.process_order.features.waiting_list


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.fromHtml
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.commons.ui.base.BaseListAdapter
import com.iagora.wingman.commons.ui.base.BaseViewHolder
import com.iagora.wingman.commons.views.helper.FormatCurrency
import com.iagora.wingman.commons.views.helper.SetImage.loadPhotoProfile
import com.iagora.wingman.process_order.features.waiting_list.databinding.ItemListOnProcessBinding
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder


class WaitingListAdapter : BaseListAdapter<ProcessOrder.ListWaitingOnProcess.Success>(
    itemsSame = { old, new -> old.idTransaction == new.idTransaction },
    contentsSame = { old, new -> old == new }
) {
    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    inner class WaitingListViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        BaseViewHolder<ItemListOnProcessBinding>(
            ItemListOnProcessBinding.inflate(inflater, parent, false)
        ) {


        fun bind(data: ProcessOrder.ListWaitingOnProcess.Success) {
            with(binding) {
                with(data) {
                    with(dataUser) {
                        shapeIvPerson.loadPhotoProfile(imgProfile)
                        tvNamePerson.text = fullName
                        tvItemTotal.text = fromHtml(
                            "${itemView.resources.getString(R.string.text_total_item_cart)} : <b>${
                                FormatCurrency.getCurrencyRp(
                                    grandTotal.toDouble()
                                )
                            } <b/>",
                            FROM_HTML_MODE_COMPACT
                        )

                        with(listProduct) {
                            tvItem.text = if (size > 1) {
                                String.format(
                                    itemView.resources.getString(R.string.format_text_item),
                                    this[0].productName, size - 1
                                )

                            } else {
                                this[0].productName
                            }
                        }
                    }
                    itemView.setOnClickListener {
                        onItemClickListener?.let { click -> click(idTransaction) }
                    }
                }

            }
        }

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is WaitingListViewHolder -> holder.bind(getItem(position))
        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: Int,
    ): RecyclerView.ViewHolder = WaitingListViewHolder(inflater, parent)
}