package com.example.hw_2

import com.example.hw_2.networkService.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.Flow


class GiphRepository {

    fun getDataList(): Flow <List<POJO.Data>> = flow {
        val r = RetrofitClient.retrofit.getDataList()
        emit(r)
    }.flowOn(Dispatchers.IO)
}