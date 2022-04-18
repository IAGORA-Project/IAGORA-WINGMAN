package com.iagora.wingman.process_order.data.remote.response


import com.google.gson.annotations.SerializedName
import com.iagora.wingman.core.data.remote.dto.DataUserDto
import com.iagora.wingman.core.data.remote.dto.AddressUserDto
import com.iagora.wingman.core.data.remote.dto.ProductDto

data class ResProcessOrder(
    var resListOrder: ResListOrder? = null,
    var resDetailOrder: ResDetailOrder? = null,
    var resGlobal: ResGlobal? = null,
) {
    data class ResListOrder(
        @SerializedName("status") var status: Int? = null,
        @SerializedName("success") var success: List<Success?>? = null,
    ) {
        data class Success(
            @SerializedName("data_user") var dataUser: DataUserDto? = null,
            @SerializedName("grand_total") var grandTotal: Long? = null,
            @SerializedName("id_transaction") var idTransaction: String? = null,
            @SerializedName("list_product") var listProduct: List<ProductDto>? = null,
            @SerializedName("no_order") var noOrder: String? = null,
            @SerializedName("status") var status: String? = null,
            @SerializedName("transaction_date") var transactionDate: Long? = null,
        )
    }

    data class ResDetailOrder(
        @SerializedName("status") var status: Int? = null,
        @SerializedName("success") var success: Success? = null,
    ) {
        data class Success(
            @SerializedName("adress") var address: AddressUserDto? = null,
            @SerializedName("biaya_penanganan") var handlingFee: Long? = null,
            @SerializedName("belanja") var totalPriceProduct: Long? = null,
            @SerializedName("data_user") var dataUser: DataUserDto? = null,
            @SerializedName("grand_total") var grandTotal: Long? = null,
            @SerializedName("id_transaction") var idTransaction: String? = null,
            @SerializedName("layanan_pengiriman") var deliveryServices: String? = null,
            @SerializedName("list_product") var listProduct: List<ProductDto>? = null,
            @SerializedName("no_order") var noOrder: String? = null,
            @SerializedName("status") var status: String? = null,
            @SerializedName("transaction_date") var transactionDate: Long? = null,
            @SerializedName("pasar_info") var storeInfo: StoreInfo? = null,
            @SerializedName("biaya_platform") var platformFee: Long? = null,
            @SerializedName("discount") var discount: Long? = null,
            @SerializedName("ongkir") var deliveryFee: Long? = null,
            @SerializedName("sub_total") var subTotal: Long? = null,
        )
    }

    data class ResGlobal(
        @SerializedName("status") var status: Int? = null,
        @SerializedName("success") var success: String? = null,
    )


    data class StoreInfo(
        @SerializedName("id_pasar") val idStore: String,
        @SerializedName("nama_pasar") val storeName: String,
    )

}