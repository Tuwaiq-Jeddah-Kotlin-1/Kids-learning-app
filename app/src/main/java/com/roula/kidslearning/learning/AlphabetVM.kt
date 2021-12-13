package com.roula.kidslearning.learning

import Alphabet_data
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.roula.kidslearning.network.AlphabetRepo
import kotlinx.coroutines.launch

class AlphabetVM : ViewModel() {
    val repo = AlphabetRepo()
    fun fetchInterestingList() : LiveData<List<Alphabet_data>> {
        val alphabets = MutableLiveData<List<Alphabet_data>>()
        viewModelScope.launch {
            try {
                alphabets.postValue(repo.fetchInterestingList())
            }
            catch (e: Throwable) {
                Log.e("Movies Image","Movies Image Problem: ${e.localizedMessage}")
            }
        }
        return alphabets
    }
}