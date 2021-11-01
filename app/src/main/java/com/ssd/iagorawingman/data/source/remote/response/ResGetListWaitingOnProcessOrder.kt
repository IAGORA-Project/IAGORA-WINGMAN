package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetListWaitingOnProcessOrder(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: List<Succes?>? = null
) {
    data class Succes(
        @SerializedName("data_user") var dataUser: DataUser? = null,
        @SerializedName("grand_total") var grandTotal: Int? = null,
        @SerializedName("id_transaction") var idTransaction: String? = null,
        @SerializedName("list_product") var listProduct: List<Product?>? = null,
        @SerializedName("no_order") var noOrder: String? = null,
        @SerializedName("status") var status: String? = null,
        @SerializedName("transaction_date") var transactionDate: Long? = null
    ) {
        data class DataUser(
            @SerializedName("full_name") var fullName: String? = null,
            @SerializedName("id_user") var idUser: String? = null,
            @SerializedName("img_profile") var imgProfile: String? = null,
            @SerializedName("phone_number") var phoneNumber: String? = null
        )

        data class Product(
            @SerializedName("avg_price") var avgPrice: Int? = null,
            @SerializedName("bergain_price") var bargainPrice: Int? = null,
            @SerializedName("id_product") var idProduct: String? = null,
            @SerializedName("product_name") var productName: String? = null,
            @SerializedName("qty") var qty: Int? = null,
            @SerializedName("satuan") var satuan: String? = null,
            @SerializedName("uom") var uom: Int? = null
        )
    }
}