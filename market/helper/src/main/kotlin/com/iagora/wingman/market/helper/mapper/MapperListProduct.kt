package com.iagora.wingman.market.helper.mapper

import com.iagora.wingman.market.helper.data.remote.response.ResGetListProduct
import com.iagora.wingman.market.helper.model.response.ListProduct

object MapperListProduct {
    fun resListProductToModelListProduct(input: ResGetListProduct) =
        ListProduct(
            status = input.status ?: 404,
            success = resSuccessToList(input.success)
        )


    private fun resSuccessToList(input: List<ResGetListProduct.Success>? = null): MutableList<ListProduct.Success> {
        val listSuccess = mutableListOf<ListProduct.Success>()
        input?.forEach { success ->
            listSuccess.add(resSuccessToModelSuccess(success))
        }
        return listSuccess
    }

    private fun resSuccessToModelSuccess(input: ResGetListProduct.Success? = null) =
        ListProduct.Success(
            idMarket = input?.idMarket ?: "0",
            idProduct = input?.idProduct ?: "0",
            productName = input?.productName ?: "none",
            img = input?.img ?: "none",
            price = input?.price ?: 0,
            desc = input?.desc ?: "none",
            characteristics = input?.characteristics ?: "none"
        )
}