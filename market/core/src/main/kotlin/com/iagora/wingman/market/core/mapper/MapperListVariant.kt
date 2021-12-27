package com.iagora.wingman.market.core.mapper

import com.iagora.wingman.market.core.data.remote.body.VariantBody
import com.iagora.wingman.market.helper.model.body.Variant

object MapperListVariant {


     fun List<Variant>?.toBody(): MutableList<VariantBody> {
        val listVariantBody = mutableListOf<VariantBody>()
        this?.forEach { variant ->
            listVariantBody.add(variant.toBody())
        }

        return listVariantBody
    }

    private fun Variant?.toBody() = VariantBody(
        idList = this?.idList ?: 0,
        avgPrice = this?.avgPrice ?: 0,
        highestPrice = this?.highestPrice ?: 0,
        lowPrice = this?.lowPrice ?: 0,
        unit = this?.unit ?: "none",
        uom = this?.uom ?: 0
    )
}