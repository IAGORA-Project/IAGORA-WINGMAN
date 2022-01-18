package com.iagora.wingman.core.data.mapper

import com.iagora.wingman.core.data.remote.dto.ProductDto
import com.iagora.wingman.core.domain.model.dto.Product

 object MapperProduct {

    fun List<ProductDto>?.toListModel(): MutableList<Product> {
        val listProduct = mutableListOf<Product>()
        this?.forEach { remProduct ->
            listProduct.add(remProduct.toModel())
        }

        return listProduct
    }

    fun List<Product>?.toListRem(): MutableList<ProductDto> {
        val listProduct = mutableListOf<ProductDto>()
        this?.forEach { product ->
            listProduct.add(product.toRem())
        }

        return listProduct
    }

    private fun ProductDto?.toModel() =
        Product(
            bargainPrice = this?.bargainPrice ?: 0,
            productName = this?.productName ?: "",
            idProduct = this?.idProduct ?: "",
            unit = this?.unit ?: "unit",
            uom = this?.uom ?: 0,
            qty = this?.qty ?: 0
        )

    private fun Product?.toRem() =
        ProductDto(
            bargainPrice = this?.bargainPrice ?: 0,
            productName = this?.productName ?: "",
            idProduct = this?.idProduct ?: "",
            unit = this?.unit ?: "unit",
            uom = this?.uom ?: 0,
            qty = this?.qty ?: 0
        )

}