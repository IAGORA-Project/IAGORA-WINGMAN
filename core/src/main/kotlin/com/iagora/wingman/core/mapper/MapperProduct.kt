package com.iagora.wingman.core.mapper

import com.iagora.wingman.core.data.remote.RemProduct
import com.iagora.wingman.helper.model.Product

object MapperProduct {

    fun List<RemProduct>?.toListModel(): MutableList<Product> {
        val listProduct = mutableListOf<Product>()
        this?.forEach { remProduct ->
            listProduct.add(remProduct.toModel())
        }

        return listProduct
    }

    fun List<Product>?.toListRem(): MutableList<RemProduct> {
        val listProduct = mutableListOf<RemProduct>()
        this?.forEach { product ->
            listProduct.add(product.toRem())
        }

        return listProduct
    }

    private fun RemProduct?.toModel() =
        Product(
            bargainPrice = this?.bargainPrice ?: 0,
            productName = this?.productName ?: "",
            idProduct = this?.idProduct ?: "",
            unit = this?.unit ?: "unit",
            uom = this?.uom ?: 0,
            qty = this?.qty ?: 0
        )

    private fun Product?.toRem() =
        RemProduct(
            bargainPrice = this?.bargainPrice ?: 0,
            productName = this?.productName ?: "",
            idProduct = this?.idProduct ?: "",
            unit = this?.unit ?: "unit",
            uom = this?.uom ?: 0,
            qty = this?.qty ?: 0
        )

}