package com.iagora.wingman.core.data.repository

import android.content.SharedPreferences
import com.iagora.wingman.core.data.util.PrefSetting.clearData
import com.iagora.wingman.core.domain.repository.ICoreRepository

class CoreRepository(
    private val sharedPref: SharedPreferences,
) : ICoreRepository {
    override fun clearData(key: String): Boolean = sharedPref.clearData(key)

}