package com.example.universitysearch.modal

data class University(
    val name: String? = null,
    val image: String? = null,
    val city: String? = null,
    val state: String? = null,
    val naac: String? = null,
    val courses: List<Course> = emptyList()
){
//    constructor() : this("", "", "","", "", )
}