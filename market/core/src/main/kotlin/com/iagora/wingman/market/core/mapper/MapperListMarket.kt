package com.iagora.wingman.market.core.mapper

import com.iagora.wingman.market.core.data.remote.response.ResGetListMarket
import com.iagora.wingman.market.helper.model.response.ListMarket

object MapperListMarket {

    fun ResGetListMarket.toModel() = ListMarket(
        status = this.status ?: 404,
        success = this.success.toList()
    )


    private fun List<ResGetListMarket.Success>?.toList(): MutableList<ListMarket.Success> {
        val listSuccess = mutableListOf<ListMarket.Success>()
        this?.forEach { success ->
            listSuccess.add(success.toModel())
        }

        return listSuccess
    }

    private fun ResGetListMarket.Success?.toModel() =
        ListMarket.Success(
            idMarket = this?.idMarket ?: "0",
            infoAddress = this?.infoAddress.toModel(),
            lat = this?.lat ?: "0",
            long = this?.long ?: "0",
            marketName = this?.marketName ?: "no-name"
        )


    private fun ResGetListMarket.Success.InfoAddress?.toModel() =
        ListMarket.Success.InfoAddress(
            address = this?.address ?: "not-found",
            city = this?.city ?: "not-found",
            zip = this?.zip ?: "not-found",
            main = this?.main ?: false,
            province = this?.province ?: "not-found"
        )
}