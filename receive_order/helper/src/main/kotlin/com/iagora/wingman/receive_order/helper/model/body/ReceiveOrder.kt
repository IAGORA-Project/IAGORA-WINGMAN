package com.iagora.wingman.receive_order.helper.model.body


import android.os.Parcelable
import com.iagora.wingman.core.domain.model.dto.DataUser
import com.iagora.wingman.core.domain.model.dto.Product
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReceiveOrder(
    val dataUser: DataUser,
    val idCart: String,
    val idMarket: String,
    val index: Int,
    val listProduct: List<Product>,
) : Parcelable