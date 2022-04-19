package com.iagora.wingman.market.data.mapper

import com.iagora.wingman.market.helper.model.body.Variant

object MapperListVariant {


     fun List<Variant>?.toBody(): MutableList<com.iagora.wingman.market.data.remote.request.VariantBody> {
        val listVariantBody = mutableListOf<com.iagora.wingman.market.data.remote.request.VariantBody>()
        this?.forEach { variant ->
            listVariantBody.add(variant.toBody())
        }

        return listVariantBody
    }

    private fun Variant?.toBody() = com.iagora.wingman.market.data.remote.request.VariantBody(
        idList = this?.idList ?: 0,
        avgPrice = this?.avgPrice ?: 0,
        highestPrice = this?.highestPrice ?: 0,
        lowPrice = this?.lowPrice ?: 0,
        unit = this?.unit ?: "none",
        uom = this?.uom ?: 0
    )
}