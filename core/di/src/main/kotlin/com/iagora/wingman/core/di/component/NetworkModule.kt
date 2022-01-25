package com.iagora.wingman.core.di.component

import com.google.gson.Gson
import com.iagora.wingman.core.data.session.SessionManager
import com.iagora.wingman.core.data.session.SessionManager.Companion.KEY_SESSID
import com.iagora.wingman.core.di.BuildConfig
import com.iagora.wingman.core.util.Constants.BASE_URL
import com.iagora.wingman.core.util.Constants.NETWORK_TIMEOUT
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {
    single {
        provideOkHttpClient(get())
    }


    single {
        Gson()
    }

    single {
        provideRetrofit(
            get(),
            get(named(BASE_URL))
        )
    }

}


private fun provideRetrofit(
    okHttpClient: OkHttpClient,
    url: String
): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(url)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()


private fun provideOkHttpClient(
    sessionManager: SessionManager
) =
    OkHttpClient().newBuilder()
        .connectTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(NETWORK_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor {
            val sessid = sessionManager.getFromPreference(KEY_SESSID) ?: ""

            val modifiedRequest = if (sessid.isNotEmpty()) {
                it.request().newBuilder()
                    .addHeader("sessid", sessid)
            } else it.request().newBuilder()

            it.proceed(modifiedRequest.build())
        }.addInterceptor(HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        })
        .build()