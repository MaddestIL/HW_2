package com.example.hw_2.domain.model.usecases

import com.example.hw_2.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManager
) {

    operator fun invoke(): Flow<Boolean> {
       return localUserManager.readAppEntry()
    }
}