package com.iagora.wingman.core.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetListPasar(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: ArrayList<Success>? = null
) {
    data class Success(
        @SerializedName("id_pasar") var idPasar: String? = null,
        @SerializedName("info_address") var infoAddress: InfoAddress? = null,
        @SerializedName("lat") var lat: String? = null,
        @SerializedName("long") var long: String? = null,
        @SerializedName("nama_pasar") var namaPasar: String? = null
    ) {
        data class InfoAddress(
            @SerializedName("address") var address: String? = null,
            @SerializedName("city") var city: String? = null,
            @SerializedName("kode_pos") var kodePos: String? = null,
            @SerializedName("main") var main: Boolean? = null,
            @SerializedName("provinsi") var provinsi: String? = null
        )
    }
}