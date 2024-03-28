package com.cs4520.assignment5.model.API


import com.cs4520.assignment5.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("prod/")
    suspend fun getProducts(): Response<List<APIResponse>>
}