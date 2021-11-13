package com.ssd.iagorawingman.data.source.remote.api_handle.pasar

import com.ssd.iagorawingman.data.source.remote.body.AddProductBody
import com.ssd.iagorawingman.data.source.remote.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PasarApi {

    @GET("getPasar")
    fun get_listPasar(
    ): Call<ResGetListPasar>

    @GET("getProduct/{id_pasar}")
    fun get_listProductPasar(
        @Path("id_pasar") idPasar: String
    ): Call<ResGetListProductPasar>

    @GET("product/typeAndCategory")
    fun get_listTypeAndCategory(
    ): Call<ResGetListTypeAndCategory>

    @Multipart
    @POST("product/add")
    fun post_addProduct(
        @Part imageProduct: ArrayList<MultipartBody.Part>,
        @Part("id_pasar") id_pasar: RequestBody? = null,
        @Part("nama_produk") product_name: RequestBody? = null,
        @Part("item_categories") item_categories: RequestBody? = null,
        @Part("item_type") item_type: RequestBody? = null,
        @Part("variant[]") variant: ArrayList<AddProductBody.Variant>? = null,
        @Part("desc") desc: RequestBody? = null,
    ): Call<ResAddProduct>


    @Multipart
    @POST("addPhoto")
    fun post_addPhoto(
        @Part photo: ArrayList<MultipartBody.Part>
    ): Call<ResAddPhoto>
}