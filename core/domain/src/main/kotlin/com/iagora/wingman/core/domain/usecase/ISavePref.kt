package com.iagora.wingman.core.domain.usecase

interface ISavePref {
    operator fun invoke(key: String, value: String):Boolean
}