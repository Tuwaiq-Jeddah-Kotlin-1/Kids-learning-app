package com.roula.kidslearning.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Alphabet_Builder {
    private const val BASE_URL = "https://61b3591aaf5ff70017ca1e9f.mockapi.io/"
    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val alphabetApi: Alphabet_API = retrofit().create(Alphabet_API::class.java)
}