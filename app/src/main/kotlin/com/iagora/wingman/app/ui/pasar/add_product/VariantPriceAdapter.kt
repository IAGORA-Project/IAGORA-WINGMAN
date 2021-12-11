//package com.iagora.wingman.app.ui.pasar.add_product
//
//import android.annotation.SuppressLint
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.core.widget.addTextChangedListener
//import androidx.recyclerview.widget.RecyclerView
//import com.iagora.wingman.app.databinding.ItemListVariantPriceBinding
//import com.iagora.wingman.core.source.local.model.ListVariantAddProductModel
//
//class VariantPriceAdapter(
//    private val listData: ArrayList<ListVariantAddProductModel.Variant>,
//    var satuan: String = "",
//): RecyclerView.Adapter<VariantPriceAdapter.ViewHolder>() {
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VariantPriceAdapter.ViewHolder (
//        ItemListVariantPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//    )
//
//    @SuppressLint("NotifyDataSetChanged")
//    override fun onBindViewHolder(holder: VariantPriceAdapter.ViewHolder, position: Int) {
//        val data = listData[position]
//
//        if(satuan.isBlank()){
//            holder.binding.tilVariantScale.isEnabled = false
//            holder.binding.tilPrice.isEnabled = false
//        } else if(satuan == "Pcs"){
//            holder.binding.tilVariantScale.isEnabled = false
//            holder.binding.tilPrice.isEnabled = true
//            data.satuan = satuan
//        }else{
//            holder.binding.tilVariantScale.isEnabled = true
//            holder.binding.tilPrice.isEnabled = true
//            data.satuan = satuan
//        }
//
//        if(data.idList != null){
//            holder.binding.tilVariantScale.editText?.setText(data.variant)
//            holder.binding.tilPrice.editText?.setText(data.price)
//        }
//
//        holder.binding.tilVariantScale.editText?.addTextChangedListener {
//            data.variant = it.toString()
//        }
//
//        holder.binding.tilPrice.editText?.addTextChangedListener {
//            data.price = it.toString()
//        }
//
//
//
//        holder.binding.ivDeleteVariant.setOnClickListener {
//            if(listData.size > 1){
//                listData.remove(data)
//                notifyDataSetChanged()
//            }
//        }
//    }
//
//    override fun getItemCount(): Int  = listData.size
//
//    class ViewHolder(val binding: ItemListVariantPriceBinding): RecyclerView.ViewHolder(binding.root)
//}