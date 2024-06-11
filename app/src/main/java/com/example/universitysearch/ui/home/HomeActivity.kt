package com.example.universitysearch.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.universitysearch.databinding.ActivityHomeBinding
import com.example.universitysearch.ui.savedCourses.SavedCoursesActivity
import com.example.universitysearch.ui.university.UniversityActivity
import com.example.universitysearch.viewModel.UniversityViewModel
import com.google.gson.Gson

class HomeActivity: AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: HomeAdapter
    private val universityViewModel: UniversityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.homeToolbar.textPageTittle.text = "University"

        binding.homeToolbar.icSave.setOnClickListener{
            startActivity(Intent(this, SavedCoursesActivity::class.java))
        }

        adapter =  HomeAdapter()
        binding.recyclerViewUniversity.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewUniversity.adapter = adapter
        adapter.onItemClick = { university ->
            val gson = Gson()
            val universityJson = gson.toJson(university)
            val intent =  Intent(this, UniversityActivity::class.java)
            intent.putExtra("university", universityJson)
            startActivity(intent)
        }

        universityViewModel.universities.observe(this, Observer{
            adapter.saveData(it)
        })

        universityViewModel.loading.observe(this, Observer{
            if(it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })

        universityViewModel.fetchUniversities()
    }
}