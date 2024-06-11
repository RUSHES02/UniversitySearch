package com.example.universitysearch.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.universitysearch.modal.University
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class UniversityViewModel: ViewModel() {

    private val _university = MutableLiveData<List<University>>()
    val universities: LiveData<List<University>> get() = _university

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading
    private val databaseReference = FirebaseFirestore.getInstance().collection("university")

    fun fetchUniversities(){
        _loading.value = true
        viewModelScope.launch {
            try {
                databaseReference.get()
                    .addOnSuccessListener { result ->
                        val list = result.map { document ->
                            document.toObject(University::class.java)
                        }
                        _university.postValue(list)
                        _loading.postValue(false)
                    }
                    .addOnFailureListener{
                        Log.d("firebase", "$it")
                        _loading.postValue(false)
                    }
            }catch (e: Exception) {
                Log.d("database", "$e")
                _loading.postValue(false)
            }
        }
    }
}