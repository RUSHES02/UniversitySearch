package com.example.universitysearch.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.universitysearch.db.CourseDatabase
import com.example.universitysearch.modal.SavedCourse
import com.example.universitysearch.repository.CourseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedCourseViewModel(application: Application): AndroidViewModel(application) {

    val readAllData : LiveData<List<SavedCourse>>
    private val repository : CourseRepository

    init {
        val userDao = CourseDatabase.getDatabase(application).courseDao()
        repository = CourseRepository(userDao)
        readAllData = repository.readAllData
    }

    fun addCourse(note: SavedCourse){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCourse(note)
        }
    }

    fun deleteCourse(note: SavedCourse){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteCourse(note)
        }
    }
}