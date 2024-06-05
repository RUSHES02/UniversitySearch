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
                name = binding.textCourseName.text.toString(),
                eligibility = binding.textCourseEligibility.text.toString(),
                exams = binding.textCourseExam.text.toString(),
                placement = binding.textCoursePlacement.text.toString(),
                fees = binding.textCourseFees.text.toString(),
                subCoures = binding.textCourseSubCourses.text.toString()
                )

            courses.add(course)

            binding.textCourseName.text.clear()
            binding.textCourseEligibility.text.clear()
            binding.textCourseExam.text.clear()
            binding.textCoursePlacement.text.clear()
            binding.textCourseFees.text.clear()
            binding.textCourseSubCourses.text.clear()
        }

        binding.buttonAdd.setOnClickListener{


            val university = University(
                name = binding.textName.text.toString(),
                image = binding.textImg.text.toString(),
                city = binding.textCity.text.toString(),
                state = binding.textState.text.toString(),
                naac = binding.textRank.text.toString(),
                courses = courses
            )

            databaseReference.document().set(university)
                .addOnSuccessListener {
                    Log.d("university", "added")

                    binding.textName.text.clear()
                    binding.textImg.text.clear()
                    binding.textCity.text.clear()
                    binding.textState.text.clear()
                    binding.textRank.text.clear()
                    binding.textCourseName.text.clear()
                    binding.textCourseEligibility.text.clear()
                    binding.textCourseExam.text.clear()
                    binding.textCoursePlacement.text.clear()
                    binding.textCourseFees.text.clear()
                    binding.textCourseSubCourses.text.clear()

                    courses.clear()
                }
                .addOnFailureListener{
                    Log.d("university", "not added")
                }
        }
    }
}