package com.iagora.wingman.market.core.mapper

import com.iagora.wingman.market.core.data.remote.response.ResAddProductFeedback
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack

object MapperAddProductFeedback {
    fun ResAddProductFeedback.toModel() =
        AddProductFeedBack(
            status = this.status ?: 404,
            success = this.success.toModel()
        )

    private fun ResAddProductFeedback.Success?.toModel() =
        AddProductFeedBack.Success(this?.message ?: "no-message")
}