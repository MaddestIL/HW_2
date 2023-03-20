package com.example.hw_2.remote.dt

import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyApi {

    @GET("trending")
    suspend fun  getGiphy(
        @Query("api_key") api_key: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("random_id") random_id: String,
        @Query("bundle") bundle: String
    ): GiphyResponse
}