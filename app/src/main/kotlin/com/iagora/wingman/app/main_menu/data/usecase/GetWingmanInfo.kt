package com.iagora.wingman.app.main_menu.data.usecase

import com.iagora.wingman.app.main_menu.domain.model.response.WingmanInfo
import com.iagora.wingman.app.main_menu.domain.repository.IMainRepository
import com.iagora.wingman.app.main_menu.domain.usecase.IGetWingmanInfo
import com.iagora.wingman.core.util.Resource

class GetWingmanInfo(
    private val repository: IMainRepository,
    private val keySharedWingman: String
) : IGetWingmanInfo {

    override suspend fun invoke(): Resource<WingmanInfo> {
        val tempResult = repository.getNewWingmanInfo()
        return try {
            val result = repository.getWingmanInfo(keySharedWingman)
            if (result == null) {
                return tempResult
            } else {
                Resource.Success(result)
            }
        } catch (e: Throwable) {
            tempResult
        }
    }
}