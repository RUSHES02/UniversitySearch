package com.example.universitysearch.repository

import androidx.lifecycle.LiveData
import com.example.universitysearch.db.CourseDao
import com.example.universitysearch.modal.SavedCourse

class CourseRepository(private val courseDao: CourseDao) {

    val readAllData : LiveData<List<SavedCourse>> =  courseDao.readAllData()

    suspend fun addCourse(course : SavedCourse){
        courseDao.addUser(course)
    }

    suspend fun deleteCourse(course: SavedCourse){
        courseDao.deleteCourse(course)
    }
}