package com.iagora.wingman.receive_order.di.components

import com.iagora.wingman.receive_order.data.usecase.AcceptOrder
import com.iagora.wingman.receive_order.data.usecase.CancelOrder
import com.iagora.wingman.receive_order.domain.usecase.IAcceptOrder
import com.iagora.wingman.receive_order.domain.usecase.ICancelOrder
import org.koin.dsl.module

internal val useCaseModule = module {
    single<ICancelOrder> {
        CancelOrder(get())
    }
    single <IAcceptOrder>{
        AcceptOrder(get())
    }
}