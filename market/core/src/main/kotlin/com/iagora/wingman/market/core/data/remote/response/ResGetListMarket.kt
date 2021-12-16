package com.iagora.wingman.market.core.data.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetListMarket(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: List<Success>? = null,
) {
    data class Success(
        @SerializedName("id_pasar") var idMarket: String? = null,
        @SerializedName("info_address") var infoAddress: InfoAddress? = null,
        @SerializedName("lat") var lat: String? = null,
        @SerializedName("long") var long: String? = null,
        @SerializedName("nama_pasar") var marketName: String? = null,
    ) {
        data class InfoAddress(
            @SerializedName("address") var address: String? = null,
            @SerializedName("city") var city: String? = null,
            @SerializedName("kode_pos") var zip: String? = null,
            @SerializedName("main") var main: Boolean? = null,
            @SerializedName("provinsi") var province: String? = null,
        )
    }
}