package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.response.ResGetListPasar
import com.ssd.iagorawingman.data.source.remote.response.ResGetListProductPasar
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface PasarApi {

    @GET("getPasar")
    fun get_listPasar(
        @Header("Authorization") authorization: String,
    ): Call<ResGetListPasar>

    @GET("getProduct/{id_pasar}")
    fun get_listProductPasar(
        @Header("Authorization") authorization: String,
        @Path("id_pasar") idPasar: String
    ): Call<ResGetListProductPasar>
}