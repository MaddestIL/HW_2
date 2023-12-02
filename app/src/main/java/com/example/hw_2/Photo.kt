package com.example.hw_2

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("image_url")
    val image_url: String
)