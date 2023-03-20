package com.example.hw_2.remote.dt

import com.example.hw_2.domain.model.Data
import com.example.hw_2.domain.model.Meta
import com.example.hw_2.domain.model.Pagination

data class GiphyResponse(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)