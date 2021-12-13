package com.roula.kidslearning.network

import Alphabet_data
import retrofit2.http.GET

interface Alphabet_API {
    @GET("api/v1/kids")
    suspend fun fetchAlphabet(): List<Alphabet_data>
}
