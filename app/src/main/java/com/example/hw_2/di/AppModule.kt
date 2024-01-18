package com.example.hw_2.di

import android.app.Application
import com.example.hw_2.data.LocalUserManagerImpl
import com.example.hw_2.domain.manager.LocalUserManager
import com.example.hw_2.domain.usecases.AppEntryUseCases
import com.example.hw_2.domain.usecases.ReadAppEntry
import com.example.hw_2.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )
}