package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.response.ResGetListPasar
import com.ssd.iagorawingman.data.source.remote.response.ResGetListProductPasar
import retrofit2.Call

interface PasarDataSource {

    fun getListPasar(token: String): Call<ResGetListPasar>

    fun getListProductPasar(token: String, idPasar: String): Call<ResGetListProductPasar>
}