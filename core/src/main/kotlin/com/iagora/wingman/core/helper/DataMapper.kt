package com.iagora.wingman.core.helper

import com.iagora.wingman.core.data.remote.RemAddressUser
import com.iagora.wingman.core.data.remote.RemDataUser
import com.iagora.wingman.core.data.remote.RemProduct
import com.iagora.wingman.helper.model.AddressUser
import com.iagora.wingman.helper.model.DataUser
import com.iagora.wingman.helper.model.Product

object DataMapper {
    fun mapRemDataUserToModelDataUser(input: RemDataUser? = null) =
        DataUser(
            input?.fullName ?: "",
            input?.idUser ?: "",
            input?.imgProfile ?: "",
            input?.phoneNumber ?: ""
        )

    fun mapModelDataUserToRemDataUser(input: DataUser? = null) =
        RemDataUser(
            input?.fullName ?: "",
            input?.idUser ?: "",
            input?.imgProfile ?: "",
            input?.phoneNumber ?: ""
        )

    fun mapRemProductToListRemProduct(input: List<RemProduct>? = null): MutableList<Product> {
        val listProduct = mutableListOf<Product>()
        input?.forEach { remProduct ->
            listProduct.add(mapRemProductToModelProduct(remProduct))
        }

        return listProduct
    }

    fun mapModelProductToListModelProduct(input: List<Product>? = null): MutableList<RemProduct> {
        val listProduct = mutableListOf<RemProduct>()
        input?.forEach { product ->
            listProduct.add(mapModelProductToRemProduct(product))
        }

        return listProduct
    }

    private fun mapRemProductToModelProduct(
        input: RemProduct? = null,
    ) =
        Product(
            bargainPrice = input?.bargainPrice ?: 0,
            productName = input?.productName ?: "",
            idProduct = input?.idProduct ?: "",
            unit = input?.unit ?: "unit",
            uom = input?.uom ?: 0,
            qty = input?.qty ?: 0
        )

    private fun mapModelProductToRemProduct(
        input: Product? = null,
    ) =
        RemProduct(
            bargainPrice = input?.bargainPrice ?: 0,
            productName = input?.productName ?: "",
            idProduct = input?.idProduct ?: "",
            unit = input?.unit ?: "unit",
            uom = input?.uom ?: 0,
            qty = input?.qty ?: 0
        )

    fun mapRemAddressUserToModelAddressUser(
        input: RemAddressUser? = null,
    ) = AddressUser(
        details = input?.details ?: "",
        fullName = input?.fullName ?: "",
        note = input?.note ?: "",
        phoneNumber = input?.phoneNumber ?: ""
    )

    fun mapModelAddressUserToRemAddressUser(
        input: AddressUser? = null,
    ) = RemAddressUser(
        details = input?.details ?: "",
        fullName = input?.fullName ?: "",
        note = input?.note ?: "",
        phoneNumber = input?.phoneNumber ?: ""
    )

}