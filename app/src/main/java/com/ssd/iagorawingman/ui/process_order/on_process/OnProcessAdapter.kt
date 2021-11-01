package com.ssd.iagorawingman.ui.process_order.on_process

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.core.text.HtmlCompat.fromHtml
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.domain.model.ListWaitingOnProcess
import com.ssd.iagorawingman.databinding.ItemListOnProcessBinding
import com.ssd.iagorawingman.utils.FormatCurrency
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile


class OnProcessAdapter : RecyclerView.Adapter<OnProcessAdapter.OnProcessViewHolder>() {
    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }


    inner class OnProcessViewHolder(val binding: ItemListOnProcessBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(success: ListWaitingOnProcess.Success) {
            with(binding) {
                with(success) {
                    with(dataUser) {
                        shapeIvPerson.loadPhotoProfile(imgProfile)
                        tvNamePerson.text = fullName
                        tvItemTotal.text = fromHtml(
                            "Total belanja : <b>${FormatCurrency.getCurrencyRp(grandTotal.toDouble())} <b/>",
                            FROM_HTML_MODE_COMPACT
                        )

                        with(listProduct) {
                            tvItem.text = if (size > 1) {
                                String.format(
                                    itemView.resources.getString(R.string.format_text_total_item),
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
        object : DiffUtil.ItemCallback<ListWaitingOnProcess.Success>() {
            override fun areItemsTheSame(
                oldItem: ListWaitingOnProcess.Success,
                newItem: ListWaitingOnProcess.Success
            ): Boolean {
                return oldItem.idTransaction == newItem.idTransaction
            }

            override fun areContentsTheSame(
                oldItem: ListWaitingOnProcess.Success,
                newItem: ListWaitingOnProcess.Success
            ): Boolean {
                return oldItem == newItem
            }
        }


    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): OnProcessAdapter.OnProcessViewHolder = OnProcessViewHolder(
        ItemListOnProcessBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: OnProcessAdapter.OnProcessViewHolder, position: Int) =
        holder.bind(differ.currentList[position])


    override fun getItemCount(): Int = differ.currentList.size
}