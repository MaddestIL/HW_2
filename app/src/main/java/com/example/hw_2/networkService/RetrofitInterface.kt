package com.example.hw_2.networkService

import com.example.hw_2.POJO
import retrofit2.http.GET

interface RetrofitInterface {
    @GET(AllApi.DATA_LIST)
    suspend fun getDataList(): List<POJO.Data>
}