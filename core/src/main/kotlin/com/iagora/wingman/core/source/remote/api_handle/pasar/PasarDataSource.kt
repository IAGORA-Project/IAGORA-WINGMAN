package com.iagora.wingman.core.source.remote.api_handle.pasar

import okhttp3.MultipartBody
import retrofit2.Call
import com.iagora.wingman.core.source.remote.body.AddProductBody
import com.iagora.wingman.core.source.remote.response.*

interface PasarDataSource {

    fun getListPasar(): Call<ResGetListPasar>

    fun getListProductPasar(idPasar: String): Call<ResGetListProductPasar>

    fun getListTypeAndCategory(): Call<ResGetListTypeAndCategory>

    fun postAddProduct(addProductBody: AddProductBody): Call<ResAddProduct>

    fun postAddPhoto(photo: ArrayList<MultipartBody.Part>): Call<ResAddPhoto>
}