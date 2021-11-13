package com.ssd.iagorawingman.ui.process_order.waiting_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.fromHtml
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.databinding.ItemListOnProcessBinding
import com.ssd.iagorawingman.utils.FormatCurrency
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile


class WaitingListAdapter : RecyclerView.Adapter<WaitingListAdapter.OnProcessViewHolder>() {
    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    inner class OnProcessViewHolder(val binding: ItemListOnProcessBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(success: ProcessOrder.ListWaitingOnProcess.Success) {
            with(binding) {
                with(success) {
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

    private val differCallback =
        object : DiffUtil.ItemCallback<ProcessOrder.ListWaitingOnProcess.Success>() {
            override fun areItemsTheSame(
                oldItem: ProcessOrder.ListWaitingOnProcess.Success,
                newItem: ProcessOrder.ListWaitingOnProcess.Success
            ): Boolean {
                return oldItem.idTransaction == newItem.idTransaction
            }

            override fun areContentsTheSame(
                oldItem: ProcessOrder.ListWaitingOnProcess.Success,
                newItem: ProcessOrder.ListWaitingOnProcess.Success
            ): Boolean {
                return oldItem == newItem
            }
        }


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnProcessViewHolder = OnProcessViewHolder(
        ItemListOnProcessBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: OnProcessViewHolder, position: Int) =
        holder.bind(differ.currentList[position])


    override fun getItemCount(): Int = differ.currentList.size
}