package com.roula.kidslearning.network

import Alphabet_data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlphabetRepo {
    private val api = Alphabet_Builder.alphabetApi
    suspend fun fetchInterestingList():List <Alphabet_data> = withContext(Dispatchers.IO) {
        api.fetchAlphabet()

    }
}