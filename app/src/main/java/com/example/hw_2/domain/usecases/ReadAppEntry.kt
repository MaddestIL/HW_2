package com.example.hw_2.domain.usecases

import com.example.hw_2.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    suspend operator fun invoke(): Flow<Boolean> {
       return localUserManager.readAppEntry()
    }
}