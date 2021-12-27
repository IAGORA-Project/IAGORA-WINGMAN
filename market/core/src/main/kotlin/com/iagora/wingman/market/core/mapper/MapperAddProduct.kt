package com.iagora.wingman.market.core.mapper

import com.iagora.wingman.market.core.data.remote.body.AddProductBody
import com.iagora.wingman.market.core.mapper.MapperListVariant.toBody
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