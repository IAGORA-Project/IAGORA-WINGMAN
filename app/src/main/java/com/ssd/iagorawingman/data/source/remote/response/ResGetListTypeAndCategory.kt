package com.ssd.iagorawingman.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ResGetListTypeAndCategory(
    @SerializedName("status") var status: Int? = null,
    @SerializedName("success") var success: Success? = null
) {
    data class Success(
        @SerializedName("categories") var categories: ArrayList<Category>? = null,
        @SerializedName("type") var type: ArrayList<Type>? = null
    ) {
        data class Category(
            @SerializedName("category_name") var categoryName: String? = null,
            @SerializedName("id_categories") var idCategories: String? = null
        )

        data class Type(
            @SerializedName("handling_fee") var handlingFee: Int? = null,
            @SerializedName("id_type") var idType: String? = null,
            @SerializedName("type_name") var typeName: String? = null
        )
    }
}