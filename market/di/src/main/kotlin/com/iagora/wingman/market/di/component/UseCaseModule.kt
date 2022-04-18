package com.iagora.wingman.market.di.component


import com.iagora.wingman.market.data.usecase.GetListMarket
import com.iagora.wingman.market.data.usecase.GetListProduct
import com.iagora.wingman.market.data.usecase.GetListTypeAndCategory
import com.iagora.wingman.market.data.usecase.PostAddNewProduct
import com.iagora.wingman.market.domain.usecase.IGetListMarket
import com.iagora.wingman.market.domain.usecase.IGetListProduct
import com.iagora.wingman.market.domain.usecase.IGetListTypeAndCategory
import com.iagora.wingman.market.domain.usecase.IPostAddNewProduct
import org.koin.dsl.module

internal val useCaseModule = module {
    single<IGetListMarket> {
        GetListMarket(get())
    }
    single<IGetListProduct> {
        GetListProduct(get())
    }
    single<IGetListTypeAndCategory> {
        GetListTypeAndCategory(get())
    }
    single<IPostAddNewProduct>{
        PostAddNewProduct(get())
    }
}