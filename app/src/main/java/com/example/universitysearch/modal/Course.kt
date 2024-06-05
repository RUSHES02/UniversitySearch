package com.example.universitysearch.modal

data class Course(
    val name: String,
    val eligibility: String,
    val exams: String,
    val placement: String,
    val fees: String,
    val subCoures: String? = null
)