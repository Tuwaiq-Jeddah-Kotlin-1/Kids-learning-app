package com.roula.kidslearning.network

import Alphabet_data
import android.content.ContentValues.TAG
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.roula.kidslearning.data_class.Mom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AlphabetRepo {
    private val api = Alphabet_Builder.alphabetApi
    var db = FirebaseFirestore.getInstance()
    val uid = FirebaseAuth.getInstance().currentUser?.uid


    suspend fun fetchInterestingList():List <Alphabet_data> = withContext(Dispatchers.IO) {
        api.fetchAlphabet()

    }
    suspend fun fetchText(): MutableLiveData<MutableList<Mom>> {
        var textList: MutableLiveData<MutableList<Mom>> = MutableLiveData()
        var textListIn = mutableListOf<Mom>()

        withContext(Dispatchers.IO) {

            db.collection("Users").document("$uid").collection("Mom").get()
                .addOnCompleteListener() {
                    it.addOnSuccessListener { snapshot ->
                        snapshot?.let { docSnap ->
                            var documents = docSnap.documents
                            documents.forEach { documents ->
                                var textObj = documents.toObject(Mom::class.java)
                                textObj?.let {
                                    Log.d("text Obj", textObj.toString())
                                    textListIn.add(textObj)
                                    Log.d("text textListIn", textListIn.toString())
                                }
                            }
                            textList.value = textListIn
                            Log.d("text List", textList.toString())
                        }
                    }
                }
        }
        return textList
    }
    suspend fun saveText(textMom: Mom) = withContext(Dispatchers.IO) {
        db.collection("Users").document("$uid").collection("Mom").document(textMom.textKids)
            .set(textMom)
            .addOnSuccessListener {
                Log.d("TAG", "DocumentSnapshot successfully written!")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error writing document", e)
            }
    }

    suspend fun updateText(textMom: Mom) = withContext(Dispatchers.IO){
        db.collection("Users").document("$uid").collection("Mom").document(textMom.textKids)
            .set(textMom, SetOptions.merge())

    }
    suspend fun deleteText(textMom: Mom) = withContext(Dispatchers.IO){
        db.collection("Users").document("$uid").collection("Mom").document(textMom.textKids)
            .delete()
    }

}