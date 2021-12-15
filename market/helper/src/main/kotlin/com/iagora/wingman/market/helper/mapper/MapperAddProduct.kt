package com.iagora.wingman.market.helper.mapper

import com.iagora.wingman.market.helper.data.remote.body.AddProductBody
import com.iagora.wingman.market.helper.model.body.AddProduct

object MapperAddProduct {

     fun AddProduct.toBody() = AddProductBody(
        idMarket = idMarket,
        image = image,
        productName = productName,
        desc = desc,
        itemCategories = itemCategories,
        itemType = itemType,
        unit = unit,
        variant = variant.toList()
    )


    private fun List<AddProduct.Variant>.toList(): MutableList<AddProductBody.Variant> {
        val listVariant = mutableListOf<AddProductBody.Variant>()
        forEach { variant ->
            listVariant.add(variant.toBody())
        }
        return listVariant
    }

    private fun AddProduct.Variant.toBody() = AddProductBody.Variant(
        idList = idList,
        avgPrice = avgPrice,
        highestPrice = highestPrice,
        lowPrice = lowPrice,
        unit = unit,
        uom = uom
    )
}