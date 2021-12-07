package com.iagora.wingman.receive_order.core.model.body


import android.os.Parcelable
import com.iagora.wingman.helper.model.DataUser
import com.iagora.wingman.helper.model.Product
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReceiveOrder(
    val dataUser: DataUser,
    val idCart: String,
    val idMarket: String,
    val index: Int,
    val listProduct: List<Product>,
) : Parcelable