package com.example.hw_2.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface GiphyRepository {
    fun getGiphy(sources: List<String>): Flow<PagingData>
}