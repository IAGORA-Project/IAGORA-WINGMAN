package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.response.ResAddPhoto
import com.ssd.iagorawingman.data.source.remote.response.ResGetListPasar
import com.ssd.iagorawingman.data.source.remote.response.ResGetListProductPasar
import okhttp3.MultipartBody
import retrofit2.Call

class PasarRepository(
    private val pasarApi: PasarApi
): PasarDataSource {

    override fun getListPasar(token: String): Call<ResGetListPasar> {
        return pasarApi.get_listPasar("Bearer $token")
    }

    override fun getListProductPasar(token: String, idPasar: String): Call<ResGetListProductPasar> {
        return pasarApi.get_listProductPasar("Bearer $token", idPasar)
    }

    override fun postAddPhoto(photo: ArrayList<MultipartBody.Part>): Call<ResAddPhoto> {
        return pasarApi.post_addPhoto(photo)
    }
}