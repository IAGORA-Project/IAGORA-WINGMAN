package com.iagora.wingman.market.helper.mapper

import com.iagora.wingman.market.helper.data.remote.response.ResGetListProduct
import com.iagora.wingman.market.helper.model.response.ListProduct

object MapperListProduct {
    fun ResGetListProduct.toModel() =
        ListProduct(
            status = this.status ?: 404,
            success = this.success.toList()
        )


    private fun List<ResGetListProduct.Success>?.toList(): MutableList<ListProduct.Success> {
        val listSuccess = mutableListOf<ListProduct.Success>()
        this?.forEach { success ->
            listSuccess.add(success.toModel())
        }
        return listSuccess
    }

    private fun ResGetListProduct.Success?.toModel() =
        ListProduct.Success(
            idMarket = this?.idMarket ?: "0",
            idProduct = this?.idProduct ?: "0",
            productName = this?.productName ?: "none",
            img = this?.img ?: "none",
            price = this?.price ?: 0,
            desc = this?.desc ?: "none",
            characteristics = this?.characteristics ?: "none"
        )
}