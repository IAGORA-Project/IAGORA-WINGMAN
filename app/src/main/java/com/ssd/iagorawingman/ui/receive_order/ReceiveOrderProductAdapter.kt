package com.ssd.iagorawingman.ui.receive_order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.databinding.ItemListReceiveOrderProductBinding
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice

class ReceiveOrderProductAdapter(
    private val listData: ArrayList<ReceiveOrderBody.Product>
): RecyclerView.Adapter<ReceiveOrderProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ReceiveOrderProductAdapter.ViewHolder (
        ItemListReceiveOrderProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ReceiveOrderProductAdapter.ViewHolder, position: Int) {
        val data = listData[position]

        holder.binding.tvProductName.text = data.nameProduct
        holder.binding.tvVariant.text = data.uom.toString() + "Gram"
        holder.binding.tvPrice.formatPrice(data.price.toString())
        holder.binding.tvQty.text = "x${data.qty}"
    }

    override fun getItemCount(): Int  = listData.size


    class ViewHolder(val binding: ItemListReceiveOrderProductBinding): RecyclerView.ViewHolder(binding.root)
}