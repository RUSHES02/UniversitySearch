package com.example.universitysearch.modal



data class Course(
    val name: String? = null,
    val eligibility: String? = null,
    val exams: String? = null,
    val placement: String? = null,
    val fees: String? = null,
    val subCourses: String? = null,
    var open: Boolean? = false,
    var saved: Boolean? = false
)