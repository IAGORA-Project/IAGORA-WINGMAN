package com.iagora.wingman.market.core.domain.usecase

import com.iagora.wingman.helper.Resource
import com.iagora.wingman.market.core.domain.repository.IMarketRepository
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import kotlinx.coroutines.flow.Flow

class MarketInteractor(
    private val iMarketRepository: IMarketRepository,
) : MarketUseCase {
    override fun getListMarket(): Flow<Resource<ListMarket>> = iMarketRepository.getListMarket()

    override fun getListProduct(idMarket: String): Flow<Resource<ListProduct>> =
        iMarketRepository.getListProduct(idMarket)

    override fun getListTypeAndCategory(): Flow<Resource<ListTypeAndCategory>> =
        iMarketRepository.getListTypeAndCategory()

    override fun postAddProduct(addProduct: AddProduct): Flow<Resource<AddProductFeedBack>> =
        iMarketRepository.postAddProduct(addProduct)
}