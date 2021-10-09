package com.ssd.iagorawingman.data.source.remote.network


import com.google.gson.GsonBuilder
import com.ssd.iagorawingman.BuildConfig
import com.ssd.iagorawingman.BuildConfig.BASE_URL
import com.ssd.iagorawingman.data.source.remote.interceptor.ContentTypeInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

interface NetworkService {
    companion object {
        operator fun invoke(): Retrofit {
            fun loggingInterceptor(): Interceptor {
                val httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
                return  httpLoggingInterceptor
            }

            fun okHttpClientBuilder(builder: OkHttpClient.Builder): OkHttpClient.Builder =
                builder.addInterceptor(loggingInterceptor()).addInterceptor(ContentTypeInterceptor())

            fun provideClient(): OkHttpClient = okHttpClientBuilder(OkHttpClient.Builder())
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            fun gsonHandler(builder: GsonBuilder): GsonBuilder {
                return  builder
            }

            val gson = gsonHandler(GsonBuilder().setPrettyPrinting()).setDateFormat("yyyy-MM-dd\'T\'hh:mm:ssZ").create()

            return Retrofit.Builder()
                .client(provideClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

    }
}