package com.iagora.wingman.app.auth.data.remote.response


import com.google.gson.annotations.SerializedName


data class ResLogin(
    @SerializedName("alamat") val address: String?,
    @SerializedName("available") val available: Boolean?,
    @SerializedName("bank") val bank: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("_id") val id: String?,
    @SerializedName("income") val income: Int?,
    @SerializedName("kota") val city: String?,
    @SerializedName("kotak_saran") val boxSuggestion: List<Any?>?,
    @SerializedName("ktp") val ktp: String?,
    @SerializedName("nama") val name: String?,
    @SerializedName("nama_rek") val bankName: String?,
    @SerializedName("no_hp") val noHp: String?,
    @SerializedName("no_rek") val noRek: Long?,
    @SerializedName("on_process") val onProcess: List<OnProces?>?,
    @SerializedName("pasar") val market: String?,
    @SerializedName("profile") val profile: String?,
    @SerializedName("skck") val skck: String?,
    @SerializedName("stars") val stars: Stars?,
    @SerializedName("today_order") val todayOrder: Int?,
    @SerializedName("total_order") val totalOrder: Int?,
    @SerializedName("type") val type: String?
) {
    data class OnProces(
        @SerializedName("alamat_user") val alamatUser: String?,
        @SerializedName("id_order") val idOrder: String?,
        @SerializedName("status") val status: String?,
        @SerializedName("transaction") val transaction: Transaction?,
        @SerializedName("user_hp") val userHp: String?,
        @SerializedName("user_id") val userId: String?,
        @SerializedName("wingman_hp") val wingmanHp: String?,
        @SerializedName("wingman_id") val wingmanId: String?
    ) {
        data class Transaction(
            @SerializedName("cart") val cart: List<Cart?>?,
            @SerializedName("ongkir") val ongkir: Int?,
            @SerializedName("penanganan") val penanganan: Int?,
            @SerializedName("priceTotal") val priceTotal: Int?,
            @SerializedName("total") val total: Int?
        ) {
            data class Cart(
                @SerializedName("id_product") val idProduct: String?,
                @SerializedName("mark") val mark: Boolean?,
                @SerializedName("price_awal") val priceAwal: Int?,
                @SerializedName("quantity") val quantity: String?,
                @SerializedName("type") val type: String?
            )
        }
    }

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
