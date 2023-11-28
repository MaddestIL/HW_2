package com.example.hw_2.networkService

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {



    private val gson = GsonBuilder().setLenient().create()

    private val okHttpClient = OkHttpClient.Builder()

        .addInterceptor { chain ->
            val url = chain.request().url
                .newBuilder()
                .addQueryParameter("api_key","2GPK69Zt6jiSVpPrN3YUxD9kfUVJtr4P")
                .build()
            var request = chain.request().newBuilder().url(url).build()
            val resp = chain.proceed(request)
            // Deal with the response code
            if (resp.code == 200) {
                try {
                    val myJson = resp.peekBody(2048).string() // peekBody() will not close the response
                    println(myJson)
                } catch (e: Exception) {
                    println("Error parse json from intercept..............")
                }
            } else {
                println(resp)
            }
            resp
        }.build()

    val retrofit: RetrofitInterface = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(AllApi.BASE_URL)
            .client(okHttpClient)
            .build().create(RetrofitInterface::class.java)
    }
