package com.roula.kidslearning.learning

import Alphabet_data
import android.util.Log
import androidx.lifecycle.*
import com.roula.kidslearning.data_class.Mom
import com.roula.kidslearning.network.AlphabetRepo
import kotlinx.coroutines.launch

class AlphabetVM : ViewModel() {
    val repo = AlphabetRepo()
    fun fetchInterestingList(): LiveData<List<Alphabet_data>> {
        val alphabets = MutableLiveData<List<Alphabet_data>>()
        viewModelScope.launch {
            try {
                alphabets.postValue(repo.fetchInterestingList())
            } catch (e: Throwable) {
                Log.e(" Alphabet Image", "Image Problem: ${e.localizedMessage}")
            }
        }
        return alphabets
    }

    fun fetchText(viewLifecycleOwner: LifecycleOwner): MutableLiveData<MutableList<Mom>> {
        var textMom: MutableLiveData<MutableList<Mom>> = MutableLiveData<MutableList<Mom>>()

        viewModelScope.launch {
            repo.fetchText().observe(viewLifecycleOwner, {
                textMom.value = it
            })


            Log.d("books vm in observe :", textMom.value.toString())
        }
        return textMom
    }

    fun saveText(textMom: Mom) {
        viewModelScope.launch {
            repo.saveText(textMom)
        }
    }
    fun updateText(textMom: Mom) {
        viewModelScope.launch {
            repo.updateText(textMom)
        }
    }
    fun deleteText(textMom: Mom) {
        viewModelScope.launch {
            repo.deleteText(textMom)
        }
    }



}