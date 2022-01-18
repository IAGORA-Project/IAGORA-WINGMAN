package com.iagora.wingman.core.data.usecase

import com.iagora.wingman.core.domain.repository.ICoreRepository
import com.iagora.wingman.core.domain.usecase.IDeleteDataFromSharedPref
import timber.log.Timber

class DeleteDataFromSharedPref(private val repository: ICoreRepository) :
    IDeleteDataFromSharedPref {
    override fun invoke(key: String): Boolean = try {
        repository.clearData(key)
    } catch (e: Throwable) {
        Timber.e("Something Wrong : $e")
        false
    }
}