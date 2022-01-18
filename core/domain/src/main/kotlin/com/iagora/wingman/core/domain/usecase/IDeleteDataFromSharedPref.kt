package com.iagora.wingman.core.domain.usecase

interface IDeleteDataFromSharedPref {
    operator fun invoke(key: String): Boolean
}