package com.iagora.wingman.market.helper.mapper

import com.iagora.wingman.market.helper.data.remote.response.ResGetListMarket
import com.iagora.wingman.market.helper.model.response.ListMarket

object MapperListMarket {

    fun resGetListMarketToModelListMarket(input: ResGetListMarket) = ListMarket(
        status = input.status ?: 404,
        success = resSuccessToList(input.success)
    )


    private fun resSuccessToList(input: List<ResGetListMarket.Success>? = null): MutableList<ListMarket.Success> {
        val listSuccess = mutableListOf<ListMarket.Success>()
        input?.forEach { success ->
            listSuccess.add(resSuccessToModelSuccess(success))
        }

        return listSuccess
    }

    private fun resSuccessToModelSuccess(input: ResGetListMarket.Success? = null) =
        ListMarket.Success(
            idMarket = input?.idMarket ?: "0",
            infoAddress = resInfoAddressToModelInfoAddress(input?.infoAddress),
            lat = input?.lat ?: "0",
            long = input?.long ?: "0",
            marketName = input?.marketName ?: "no-name"
        )


    private fun resInfoAddressToModelInfoAddress(input: ResGetListMarket.Success.InfoAddress? = null) =
        ListMarket.Success.InfoAddress(
            address = input?.address ?: "not-found",
            city = input?.city ?: "not-found",
            zip = input?.zip ?: "not-found",
            main = input?.main ?: false,
            province = input?.province ?: "not-found"
        )
}