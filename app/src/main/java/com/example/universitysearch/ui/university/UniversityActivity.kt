package com.example.universitysearch.ui.university

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universitysearch.R
import com.example.universitysearch.databinding.ActivityUniversityBinding
import com.example.universitysearch.modal.University
import com.example.universitysearch.viewModel.SavedCourseViewModel
import com.google.gson.Gson

class UniversityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUniversityBinding
    private lateinit var university: University
    private lateinit var adapter: CourseAdapter
    private lateinit var savedCourseViewModel: SavedCourseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUniversityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val universityJson = intent.getStringExtra("university")
        if (universityJson != null) {
            val gson = Gson()
            university = gson.fromJson(universityJson, University::class.java)
            Log.d("university", university.name.toString())
        }else{
            Log.d("university", "not found")
        }

        savedCourseViewModel = ViewModelProvider(this)[SavedCourseViewModel::class.java]

        binding.textUniName.text = university.name.toString()
        binding.textUniLocation.text = "${university.city} | ${university.state}"
        binding.textUniRank.text = university.naac.toString()

        adapter = CourseAdapter(university.courses, savedCourseViewModel, university.name.toString(), university.image.toString())
        binding.recyclerCourses.layoutManager = LinearLayoutManager(this)
        binding.recyclerCourses.adapter = adapter
    }
}