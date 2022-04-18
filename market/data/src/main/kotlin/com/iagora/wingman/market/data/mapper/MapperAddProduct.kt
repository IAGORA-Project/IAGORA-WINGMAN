package com.iagora.wingman.market.data.mapper

import com.iagora.wingman.market.data.mapper.MapperListVariant.toBody
import com.iagora.wingman.market.data.remote.request.AddProductBody
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
         listVariantBody = listVariant.toBody()
     )
}