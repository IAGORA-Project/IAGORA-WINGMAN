package com.iagora.wingman.core.source.remote.api_handle.pasar

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import com.iagora.wingman.core.source.remote.body.AddProductBody
import com.iagora.wingman.core.source.remote.response.*

class PasarRepository(
    private val pasarApi: PasarApi
): PasarDataSource {

    override fun getListPasar(): Call<ResGetListPasar> {
        return pasarApi.get_listPasar()
    }

    override fun getListProductPasar(idPasar: String): Call<ResGetListProductPasar> {
        return pasarApi.get_listProductPasar(idPasar)
    }

    override fun getListTypeAndCategory(): Call<ResGetListTypeAndCategory> {
        return pasarApi.get_listTypeAndCategory()
    }

    override fun postAddProduct(addProductBody: AddProductBody): Call<ResAddProduct> {
        val idPasar: RequestBody =
            addProductBody.idPasar!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val productName: RequestBody =
            addProductBody.namaProduk!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val itemCategories: RequestBody =
            addProductBody.itemCategories!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val itemType: RequestBody =
            addProductBody.itemType!!.toRequestBody("text/plain".toMediaTypeOrNull())
        val variant = addProductBody.variant
        val desc: RequestBody =
            addProductBody.desc!!.toRequestBody("text/plain".toMediaTypeOrNull())


        return pasarApi.post_addProduct(
            addProductBody.image,
            idPasar,
            productName,
            itemCategories,
            itemType,
            variant,
            desc
        )
    }

    override fun postAddPhoto(photo: ArrayList<MultipartBody.Part>): Call<ResAddPhoto> {
        return pasarApi.post_addPhoto(photo)
    }
}