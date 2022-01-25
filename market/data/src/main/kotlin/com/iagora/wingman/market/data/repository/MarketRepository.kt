package com.iagora.wingman.market.data.repository

import com.iagora.wingman.core.util.Resource

import com.iagora.wingman.core.util.UiText
import com.iagora.wingman.market.data.R
import com.iagora.wingman.market.data.mapper.MapperListMarket.toModel
import com.iagora.wingman.market.data.mapper.MapperListProduct.toModel
import com.iagora.wingman.market.data.mapper.MapperListTypeAndCategory.toModel
import com.iagora.wingman.market.data.remote.MarketApi
import com.iagora.wingman.market.domain.repository.IMarketRepository
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import retrofit2.HttpException
import java.io.IOException

class MarketRepository(private val api: MarketApi) : IMarketRepository {
    override suspend fun getListMarket(): Resource<ListMarket> = try {
        val listMarket = api.getListMarket().toModel()
        Resource.Success(listMarket)
    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

    override suspend fun getListProduct(idMarket: String): Resource<ListProduct> = try {
        val listProduct = api.getListProduct(idMarket).toModel()
        Resource.Success(listProduct)
    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
    } catch (e: HttpException) {
        Resource.Error(
            uiText = UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

    override suspend fun getListTypeAndCategory(): Resource<ListTypeAndCategory> = try {
        val listTypeAndCategory = api.getListTypeAndCategory().toModel()
        if (listTypeAndCategory.status == 200) {
            Resource.Success(listTypeAndCategory)
        } else {
            Resource.Error(UiText.DynamicString("Error ${listTypeAndCategory.status}"))
        }

    } catch (e: IOException) {
        Resource.Error(UiText.StringResource(R.string.error_couldnt_reach_server))
    } catch (e: HttpException) {
        Resource.Error(
            uiText =UiText.StringResource(R.string.oops_something_went_wrong)
        )
    }

    override suspend fun postAddProduct(addProduct: AddProduct): Resource<AddProductFeedBack> {
        TODO("Not yet implemented")
    }
}