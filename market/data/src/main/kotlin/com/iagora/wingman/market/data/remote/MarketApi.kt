package com.iagora.wingman.market.data.remote

import com.iagora.wingman.market.data.remote.body.AddProductBody
import com.iagora.wingman.market.data.remote.response.ResAddProductFeedback
import com.iagora.wingman.market.data.remote.response.ResGetListMarket
import com.iagora.wingman.market.data.remote.response.ResGetListProduct
import com.iagora.wingman.market.data.remote.response.ResGetListTypeAndCategory
import retrofit2.http.*

interface MarketApi {

    @GET("getPasar")
    suspend fun getListMarket(
    ): ResGetListMarket

    @GET("getProduct/{id_pasar}")
    suspend fun getListProduct(
        @Path("id_pasar") idMarket: String,
    ): ResGetListProduct

    @GET("product/typeAndCategory")
    suspend fun getListTypeAndCategory(
    ): ResGetListTypeAndCategory

    @Multipart
    @POST("product/add")
    suspend fun postAddProduct(
        @Body addProductBody: AddProductBody,
    ): ResAddProductFeedback


//    @Multipart
//    @POST("addPhoto")
//    fun post_addPhoto(
//        @Part photo: ArrayList<MultipartBody.Part>
//    ): Call<ResAddPhoto>
}