package com.example.universitysearch.ui.savedCourses

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universitysearch.R
import com.example.universitysearch.databinding.ActivitySavedCoursesBinding
import com.example.universitysearch.viewModel.SavedCourseViewModel

class SavedCoursesActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavedCoursesBinding
    private lateinit var adapter: SavedCourseAdapter
    private val savedCourseViewModel: SavedCourseViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedCoursesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SavedCourseAdapter()
        binding.recyclerSavedCourses.layoutManager = LinearLayoutManager(this)
        binding.recyclerSavedCourses.adapter = adapter

//        savedCourseViewModel = ViewModelProvider(this)[SavedCourseViewModel::class.java]
        savedCourseViewModel.readAllData.observe(this, Observer{
            adapter.saveData(it)
        })
    }
}