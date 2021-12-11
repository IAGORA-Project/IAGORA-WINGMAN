//package com.iagora.wingman.app.ui.pasar.list_product_pasar
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.iagora.wingman.app.databinding.ItemListProductPasarBinding
//import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
//import com.iagora.wingman.market.helper.data.remote.response.ResGetListProductPasar
//
//class ListProductPasarAdapter(
//    private val listData: ArrayList<com.iagora.wingman.market.helper.data.remote.response.ResGetListProductPasar.Success>
//): RecyclerView.Adapter<ListProductPasarAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListProductPasarAdapter.ViewHolder (
//        ItemListProductPasarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//    )
//
//    override fun onBindViewHolder(holder: ListProductPasarAdapter.ViewHolder, position: Int) {
//        val data = listData[position]
//
//        Glide
//            .with(holder.itemView.context)
//            .load(data.img)
//            .into(holder.binding.ivCoverProduk)
//
//        holder.binding.tvTitleProduk.text = data.productName?.capitalizeWords()
//        holder.binding.tvPrice.formatPrice(data.price.toString())
//    }
//
//    override fun getItemCount(): Int = listData.size
//
//    class ViewHolder(val binding: ItemListProductPasarBinding): RecyclerView.ViewHolder(binding.root)
//}