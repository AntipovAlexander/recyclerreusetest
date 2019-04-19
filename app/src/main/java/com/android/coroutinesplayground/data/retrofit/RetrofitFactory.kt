package com.android.coroutinesplayground.data.retrofit

import com.android.coroutinesplayground.BuildConfig
import com.android.coroutinesplayground.data.model.HttpbinGet
import com.android.coroutinesplayground.data.parser.HttpBinParser
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitFactory {
    private const val BASE_URL = "https://httpbin.org"

    fun makeRetrofitService(): Service {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create(makeGsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(Service::class.java)
    }

    private fun makeGsonBuilder(): GsonBuilder {
        return GsonBuilder().apply {
            registerTypeAdapter(HttpbinGet::class.java, HttpBinParser())
        }
    }

    private fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient
            .Builder()
            .addInterceptor(provideLoggingInterceptor())
        return builder.build()
    }

    private fun provideLoggingInterceptor(): Interceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }
}