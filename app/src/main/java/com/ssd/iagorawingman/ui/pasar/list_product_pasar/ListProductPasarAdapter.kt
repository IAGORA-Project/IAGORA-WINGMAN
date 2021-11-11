package com.ssd.iagorawingman.ui.pasar.list_product_pasar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ssd.iagorawingman.data.source.remote.response.ResGetListProductPasar
import com.ssd.iagorawingman.databinding.ItemListProductPasarBinding
import com.ssd.iagorawingman.utils.CapitalizeWords.capitalizeWords
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice

class ListProductPasarAdapter(
    private val listData: ArrayList<ResGetListProductPasar.Success>
): RecyclerView.Adapter<ListProductPasarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListProductPasarAdapter.ViewHolder (
        ItemListProductPasarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ListProductPasarAdapter.ViewHolder, position: Int) {
        val data = listData[position]

        Glide
            .with(holder.itemView.context)
            .load(data.img)
            .into(holder.binding.ivCoverProduk)

        holder.binding.tvTitleProduk.text = data.productName?.capitalizeWords()
        holder.binding.tvPrice.formatPrice(data.price.toString())
    }

    override fun getItemCount(): Int = listData.size

    class ViewHolder(val binding: ItemListProductPasarBinding): RecyclerView.ViewHolder(binding.root)
}