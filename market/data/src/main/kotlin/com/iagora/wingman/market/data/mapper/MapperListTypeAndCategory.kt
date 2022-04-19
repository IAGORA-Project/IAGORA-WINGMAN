package com.iagora.wingman.market.data.mapper

import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory

object MapperListTypeAndCategory {

    fun com.iagora.wingman.market.data.remote.response.ResGetListTypeAndCategory.toModel() = ListTypeAndCategory(
        status = status ?: 404,
        success = this.success.toModel()
    )

    private fun com.iagora.wingman.market.data.remote.response.ResGetListTypeAndCategory.Success?.toModel() = ListTypeAndCategory.Success(
        categories = this?.categories.toListCategory(),
        type = this?.type.toListType()
    )


    private fun List<com.iagora.wingman.market.data.remote.response.ResGetListTypeAndCategory.Success.Category>?.toListCategory(): MutableList<ListTypeAndCategory.Success.Category> {
        val listCategory = mutableListOf<ListTypeAndCategory.Success.Category>()
        this?.forEach { category ->
            listCategory.add(category.toModel())
        }

        return listCategory
    }

    private fun com.iagora.wingman.market.data.remote.response.ResGetListTypeAndCategory.Success.Category?.toModel() =
        ListTypeAndCategory.Success.Category(
            categoryName = this?.categoryName ?: "none",
            idCategories = this?.idCategories ?: "0"
        )

    private fun List<com.iagora.wingman.market.data.remote.response.ResGetListTypeAndCategory.Success.Type>?.toListType(): MutableList<ListTypeAndCategory.Success.Type> {
        val listType = mutableListOf<ListTypeAndCategory.Success.Type>()
        this?.forEach { type ->
            listType.add(type.toModel())
        }
        return listType
    }

    private fun com.iagora.wingman.market.data.remote.response.ResGetListTypeAndCategory.Success.Type?.toModel() =
        ListTypeAndCategory.Success.Type(
            handlingFee = this?.handlingFee ?: 0,
            idType = this?.idType ?: "none",
            typeName = this?.typeName ?: "none",
        )
}