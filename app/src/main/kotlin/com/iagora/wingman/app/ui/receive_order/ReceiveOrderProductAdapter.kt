package com.iagora.wingman.app.ui.receive_order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iagora.wingman.app.databinding.ItemListReceiveOrderProductBinding
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody

class ReceiveOrderProductAdapter(
    private val listData: ArrayList<com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody.Product>
): RecyclerView.Adapter<ReceiveOrderProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemListReceiveOrderProductBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listData[position]

        holder.binding.apply {
            tvProductName.text = data.nameProduct
            tvVariant.text = StringBuilder(data.uom.toString() + "Gram")
            tvPrice.formatPrice(data.bergainPrice.toString())
            tvQty.text = StringBuilder("x${data.qty}")
        }

    }

    override fun getItemCount(): Int  = listData.size

    class ViewHolder(val binding: ItemListReceiveOrderProductBinding): RecyclerView.ViewHolder(binding.root)
}