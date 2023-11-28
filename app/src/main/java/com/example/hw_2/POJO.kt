package com.example.hw_2

import com.google.gson.annotations.SerializedName

class POJO {

    data class Data(
        @SerializedName("images")
        val images : Images? = Images() ,
    )

    data class Json(
        @SerializedName("data")
        val data: ArrayList<Data> = arrayListOf()
    )

    data class Images(
        @SerializedName("original")
        val original : Original? = Original()
    )

    data class Original(
        @SerializedName("heigth")
        val heigth: String? = null,
        @SerializedName("width")
        val width: String? = null,
        @SerializedName("size")
        val size: String? = null,
        @SerializedName("url")
        val url: String? = null
    )
}