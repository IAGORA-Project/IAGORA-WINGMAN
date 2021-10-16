package com.ssd.iagorawingman.ui.pasar.add_product

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.local.model.ListVariantAddProductModel
import com.ssd.iagorawingman.databinding.ItemListVariantPriceBinding

class VariantPriceAdapter(
    private val listData: ArrayList<ListVariantAddProductModel.Variant>,
    private val context: Context
): RecyclerView.Adapter<VariantPriceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VariantPriceAdapter.ViewHolder (
        ItemListVariantPriceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VariantPriceAdapter.ViewHolder, position: Int) {
        val data = listData[position]
        val listSatuan = context.resources.getStringArray(R.array.satuan)
        val arrayAdapterTypeProduct = ArrayAdapter(context, R.layout.item_dropdown_text, listSatuan)

        holder.binding.dropdownChildSatuan.setAdapter(arrayAdapterTypeProduct)

        holder.binding.tilVariantScale.editText?.addTextChangedListener {
           data.variant = it.toString()
        }

        holder.binding.tilPrice.editText?.addTextChangedListener {
            data.price = it.toString()
        }

        holder.binding.dropdownChildSatuan.setOnItemClickListener { parent, view, positionChild, id ->
            data.satuan = listSatuan[positionChild]
        }

        holder.binding.ivDeleteVariant.setOnClickListener {

//            if(listData.size > 1){
//                listData.remove(data)
//                notifyDataSetChanged()
//            }

            println("esgfhbashfasf $data $position")
            println("listusyusyuss $listData")
        }
    }

    override fun getItemCount(): Int  = listData.size

    class ViewHolder(val binding: ItemListVariantPriceBinding): RecyclerView.ViewHolder(binding.root)
}