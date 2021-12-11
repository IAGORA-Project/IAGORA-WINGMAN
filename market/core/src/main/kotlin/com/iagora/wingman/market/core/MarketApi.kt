package com.iagora.wingman.market.core

import com.iagora.wingman.market.helper.data.remote.body.AddProductBody
import com.iagora.wingman.market.helper.data.remote.response.ResAddProductFeedback
import com.iagora.wingman.market.helper.data.remote.response.ResGetListMarket
import com.iagora.wingman.market.helper.data.remote.response.ResGetListProduct
import com.iagora.wingman.market.helper.data.remote.response.ResGetListTypeAndCategory
import retrofit2.http.*

interface MarketApi {

    @GET("getPasar")
    fun getListMarket(
    ): ResGetListMarket

    @GET("getProduct/{id_pasar}")
    fun getListProduct(
        @Path("id_pasar") idMarket: String,
    ): ResGetListProduct

    @GET("product/typeAndCategory")
    fun getListTypeAndCategory(
    ): ResGetListTypeAndCategory

    @Multipart
    @POST("product/add")
    fun postAddProduct(
        @Body addProductBody: AddProductBody,
    ): ResAddProductFeedback


//    @Multipart
//    @POST("addPhoto")
//    fun post_addPhoto(
//        @Part photo: ArrayList<MultipartBody.Part>
//    ): Call<ResAddPhoto>
}