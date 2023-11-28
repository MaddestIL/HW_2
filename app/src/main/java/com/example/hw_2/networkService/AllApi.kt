package com.example.hw_2.networkService

object AllApi {
    private external fun baseUrlFromJNI(boolean: Boolean): String

    const val BASE_URL = "https://api.giphy.com/"

    private const val V1 = "v1/"

    const val DATA_LIST = V1 + "trending"
}