package com.example.restfulapp.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface DuckAPIService {

    @GET("list")
    fun getLst(): Call<Lst>

    /*@GET("images/{id}")
    suspend fun getImageById(@Path("id") id: Int): PageInf?*/
}