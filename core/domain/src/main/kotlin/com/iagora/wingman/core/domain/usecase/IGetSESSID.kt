package com.iagora.wingman.core.domain.usecase

import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource


interface IGetSESSID {
    suspend operator fun invoke():Resource<String>
}