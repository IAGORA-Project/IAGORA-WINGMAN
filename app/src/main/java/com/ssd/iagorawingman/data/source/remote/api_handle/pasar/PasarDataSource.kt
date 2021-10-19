package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.body.AddProductBody
import com.ssd.iagorawingman.data.source.remote.response.*
import okhttp3.MultipartBody
import retrofit2.Call

interface PasarDataSource {

    fun getListPasar(token: String): Call<ResGetListPasar>

    fun getListProductPasar(token: String, idPasar: String): Call<ResGetListProductPasar>

    fun getListTypeAndCategory(token: String): Call<ResGetListTypeAndCategory>

    fun postAddProduct(token: String, addProductBody: AddProductBody): Call<ResAddProduct>

    fun postAddPhoto(photo: ArrayList<MultipartBody.Part>): Call<ResAddPhoto>
}