package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.response.ResAddPhoto
import com.ssd.iagorawingman.data.source.remote.response.ResGetListPasar
import com.ssd.iagorawingman.data.source.remote.response.ResGetListProductPasar
import okhttp3.MultipartBody
import retrofit2.Call

interface PasarDataSource {

    fun getListPasar(token: String): Call<ResGetListPasar>

    fun getListProductPasar(token: String, idPasar: String): Call<ResGetListProductPasar>

    fun postAddPhoto(photo: ArrayList<MultipartBody.Part>): Call<ResAddPhoto>
}