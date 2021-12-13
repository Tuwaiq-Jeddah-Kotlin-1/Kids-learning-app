package com.roula.kidslearning.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Alphabet_Builder {
    private const val BASE_URL = "https://api.themoviedb.org/3/"
    private fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val alphabetApi: Alphabet_API = retrofit().create(Alphabet_API::class.java)
}