package com.ssd.iagorawingman.data.source.remote.body


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReceiveOrderBody(
    @SerializedName("data_user") var dataUser: DataUser? = null,
    @SerializedName("id_cart") var idCart: String? = null,
    @SerializedName("id_pasar") var idPasar: String? = null,
    @SerializedName("index") var index: Int? = null,
    @SerializedName("list_product") var listProduct: ArrayList<Product>? = null
): Parcelable {
    @Parcelize
    data class DataUser(
        @SerializedName("id_user") var idUser: String? = null,
        @SerializedName("full_name") var fullName: String? = null,
        @SerializedName("img_profile") var imgProfile: String? = null
    ): Parcelable
    @Parcelize
    data class Product(
        @SerializedName("id_product") var idProduct: String? = null,
        @SerializedName("name_product") var nameProduct: String? = null,
        @SerializedName("qty") var qty: Int? = null,
        @SerializedName("satuan") var satuan: String? = null,
        @SerializedName("uom") var uom: Int? = null,
        @SerializedName("price") var price: Int = 0
    ): Parcelable
}