package com.iagora.wingman.app.auth.data.remote.response


import com.google.gson.annotations.SerializedName

data class ResLogin(
    @SerializedName("alamat") val alamat: Any?,
    @SerializedName("available") val available: Boolean?,
    @SerializedName("bank") val bank: Any?,
    @SerializedName("email") val email: Any?,
    @SerializedName("_id") val id: String?,
    @SerializedName("income") val income: Int?,
    @SerializedName("kota") val kota: Any?,
    @SerializedName("kotak_saran") val kotakSaran: List<Any?>?,
    @SerializedName("ktp") val ktp: Any?,
    @SerializedName("nama") val nama: Any?,
    @SerializedName("nama_rek") val namaRek: Any?,
    @SerializedName("no_hp") val noHp: String?,
    @SerializedName("no_rek") val noRek: Any?,
    @SerializedName("on_process") val onProcess: List<Any?>?,
    @SerializedName("pasar") val pasar: Any?,
    @SerializedName("profile") val profile: String?,
    @SerializedName("skck") val skck: Any?,
    @SerializedName("stars") val stars: Stars?,
    @SerializedName("today_order") val todayOrder: Int?,
    @SerializedName("total_order") val totalOrder: Int?,
    @SerializedName("type") val type: String?
) {
    data class Stars(
        @SerializedName("countTotal") val countTotal: Int?,
        @SerializedName("result") val result: Int?,
        @SerializedName("stars") val stars: List<Star?>?,
        @SerializedName("starsTotal") val starsTotal: Int?
    ) {
        data class Star(
            @SerializedName("count") val count: Int?,
            @SerializedName("star") val star: Int?
        )
    }
}