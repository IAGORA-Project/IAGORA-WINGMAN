package com.ssd.iagorawingman.ui.pasar.list_pasar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ssd.iagorawingman.data.source.remote.response.ResGetListPasar
import com.ssd.iagorawingman.databinding.ItemListPasarBinding
import com.ssd.iagorawingman.utils.CapitalizeWords.capitalizeWords
import kotlin.collections.ArrayList

class ListPasarAdapter(
    private val listData: ArrayList<ResGetListPasar.Success>,
    private val itemCallBackAdapter: ItemCallBackAdapter
): RecyclerView.Adapter<ListPasarAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ListPasarAdapter.ViewHolder (
        ItemListPasarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

//    fun String.capitalizeWords(): String = split(" ").map { it.replaceFirstChar(Char::uppercase) }.joinToString(" ")

    override fun onBindViewHolder(holder: ListPasarAdapter.ViewHolder, position: Int) {
        val data = listData[position]

        holder.binding.tvPasarName.text = data.namaPasar?.capitalizeWords()
        holder.binding.tvInititalName.text = data.namaPasar?.substring(0,1)

        holder.binding.containerItem.setOnClickListener {
            itemCallBackAdapter.onClickPasar(data)
        }
    }

    override fun getItemCount(): Int  = listData.size

    class ViewHolder(val binding: ItemListPasarBinding): RecyclerView.ViewHolder(binding.root)

    interface ItemCallBackAdapter {
        fun onClickPasar(result: ResGetListPasar.Success)
    }
}