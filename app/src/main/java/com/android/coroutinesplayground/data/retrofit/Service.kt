package com.android.coroutinesplayground.data.retrofit

import com.android.coroutinesplayground.data.model.HttpbinGet
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface Service {
    @GET("/get")
    fun getPosts(): Deferred<HttpbinGet>
}