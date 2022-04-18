package com.iagora.wingman.market.data.remote

import com.iagora.wingman.core.data.model.market.data.*
import com.iagora.wingman.core.data.remote.response.BasicResponse
import com.iagora.wingman.market.data.remote.request.AddProductBody
import com.iagora.wingman.core.data.model.market.remote.ReqAddNewProduct
import com.iagora.wingman.market.data.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface MarketApi {

//    needed api--
//    /api/v1/market/get-all by city?

    @GET("api/v1/city/get-all")
    suspend fun getAllCity(
    ): Response<BasicResponse<ArrayList<City>>>

    @GET("api/v1/market/get-all")
    suspend fun getAllMarket(
    ): Response<BasicResponse<ArrayList<Market>>>

    @GET("api/v1/product/category/get-all")
    suspend fun getAllCategory(
    ): Response<BasicResponse<ArrayList<ProductCategory>>>

    @GET("api/v1/market/{marketId}/get")
    suspend fun getAllProductByMarketId(
        @Path("marketId") marketId: String,
        @Query("categoryId") categoryId: String? = null,
    ): Response<BasicResponse<MarketWithProduct>>

    @GET("api/v1/product/{productId}/get")
    suspend fun getProductById(
        @Path("productId") productId: String
    ): Response<BasicResponse<Product>>

    @Multipart
    @JvmSuppressWildcards
    @POST("api/v1/wingman/request-new-product")
    suspend fun postNewProduct(
        @Header("x-access-token") accesstoken: String,
        @Part image: MultipartBody.Part,
        @PartMap params: Map<String, RequestBody>
    ): Response<BasicResponse<Product>>


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