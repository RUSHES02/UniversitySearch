package com.example.universitysearch

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.universitysearch.databinding.ActivityMainBinding
import com.example.universitysearch.modal.Course
import com.example.universitysearch.modal.University
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var databaseReference: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        databaseReference = FirebaseFirestore.getInstance().collection("university")

        var courses : MutableList<Course> = arrayListOf()

        binding.imageAddCourse.setOnClickListener{
            val course = Course(
                name = binding.textEntCourseName.text.toString(),
                eligibility = binding.textEntCourseEligibility.text.toString(),
                exams = binding.textEntCourseExam.text.toString(),
                placement = binding.textEntCoursePlacement.text.toString(),
                fees = binding.textEntCourseFees.text.toString(),
                subCourses = binding.textEntCourseSubCourses.text.toString()
                )

            courses.add(course)

            binding.textEntCourseEligibility.text.clear()
            binding.textEntCourseExam.text.clear()
            binding.textEntCoursePlacement.text.clear()
            binding.textEntCourseFees.text.clear()
            binding.textEntCourseSubCourses.text.clear()
        }

        binding.buttonAdd.setOnClickListener{


            val university = University(
                name = binding.textEntName.text.toString(),
                image = binding.textEntImg.text.toString(),
                city = binding.textEntCity.text.toString(),
                state = binding.textEntState.text.toString(),
                naac = binding.textEntRank.text.toString(),
                courses = courses
            )

            databaseReference.document().set(university)
                .addOnSuccessListener {
                    Log.d("university", "added")

                    binding.textEntName.text.clear()
                    binding.textEntImg.text.clear()
                    binding.textEntCity.text.clear()
                    binding.textEntState.text.clear()
                    binding.textEntRank.text.clear()
                    binding.textEntCourseName.text.clear()
                    binding.textEntCourseEligibility.text.clear()
                    binding.textEntCourseExam.text.clear()
                    binding.textEntCoursePlacement.text.clear()
                    binding.textEntCourseFees.text.clear()
                    binding.textEntCourseSubCourses.text.clear()

                    courses.clear()
                }
                .addOnFailureListener{
                    Log.d("university", "not added")
                }
        }
    }
}