package com.iagora.wingman.core.domain.usecase

interface IGetPref {
    operator fun invoke(key: String):String
}