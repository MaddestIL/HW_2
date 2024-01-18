package com.example.hw_2.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {

    suspend fun  saveAppEntry()

    fun readAppEntry(): Flow<Boolean>

}