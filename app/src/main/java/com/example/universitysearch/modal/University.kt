package com.example.universitysearch.modal

data class University(
    val name: String,
    val image: String,
    val city: String,
    val state: String,
    val naac: String,
    val courses: List<Course>

)