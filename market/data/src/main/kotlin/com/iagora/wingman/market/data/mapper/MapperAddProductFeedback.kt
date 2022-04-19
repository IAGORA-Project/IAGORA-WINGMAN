package com.iagora.wingman.market.data.mapper

import com.iagora.wingman.market.helper.model.response.AddProductFeedBack

object MapperAddProductFeedback {
    fun com.iagora.wingman.market.data.remote.response.ResAddProductFeedback.toModel() =
        AddProductFeedBack(
            status = this.status ?: 404,
            success = this.success.toModel()
        )

    private fun com.iagora.wingman.market.data.remote.response.ResAddProductFeedback.Success?.toModel() =
        AddProductFeedBack.Success(this?.message ?: "no-message")
}