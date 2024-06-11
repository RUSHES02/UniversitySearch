package com.example.universitysearch.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.universitysearch.modal.SavedCourse

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(course: SavedCourse)

    @Query("SELECT * FROM course_table ORDER BY id ASC")
    fun readAllData() : LiveData<List<SavedCourse>>

    @Delete
    suspend fun deleteCourse(course: SavedCourse)
}