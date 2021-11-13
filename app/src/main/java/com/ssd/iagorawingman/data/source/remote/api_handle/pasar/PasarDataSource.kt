package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.body.AddProductBody
import com.ssd.iagorawingman.data.source.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Call

interface PasarDataSource {

    fun getListPasar(): Call<ResGetListPasar>

    fun getListProductPasar(idPasar: String): Call<ResGetListProductPasar>

    fun getListTypeAndCategory(): Call<ResGetListTypeAndCategory>

    fun postAddProduct(addProductBody: AddProductBody): Call<ResAddProduct>

    fun postAddPhoto(photo: ArrayList<MultipartBody.Part>): Call<ResAddPhoto>
}