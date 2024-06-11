package com.example.universitysearch.modal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.ParcelField
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "course_table")
data class SavedCourse(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String? = null,
    val placement: String? = null,
    val fees: String? = null,
    val subCourses: String? = null,
    var saved: Boolean? = true,
    val uniName: String? = null,
    val uniImage: String? = null,
) : Parcelable