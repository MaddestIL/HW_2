package com.example.hw_2.domain.model

data class Pagination(
    val count: Int,
    val offset: Int,
    val total_count: Int
)