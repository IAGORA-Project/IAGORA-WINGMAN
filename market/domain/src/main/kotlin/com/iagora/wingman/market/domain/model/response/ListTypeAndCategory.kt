package com.iagora.wingman.market.helper.model.response


data class ListTypeAndCategory(
    var status: Int,
    var success: Success,
) {
    data class Success(
        val categories: List<Category>,
        val type: List<Type>,
    ) {
        data class Category(
            val categoryName: String,
            val idCategories: String,
        )

        data class Type(
            val handlingFee: Int,
            val idType: String,
            val typeName: String,
        )
    }
}