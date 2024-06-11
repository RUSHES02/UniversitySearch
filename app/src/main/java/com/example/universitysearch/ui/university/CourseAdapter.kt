package com.example.universitysearch.ui.university

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.universitysearch.R
import com.example.universitysearch.databinding.HolderCourseBinding
import com.example.universitysearch.modal.Course
import com.example.universitysearch.modal.SavedCourse
import com.example.universitysearch.viewModel.SavedCourseViewModel

class CourseAdapter(private val courses: List<Course>, val savedCourseViewModel: SavedCourseViewModel, val uniName: String, val uniImg: String): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {


    inner class CourseViewHolder(private val binding: HolderCourseBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(course: Course){
            binding.textCourseName.text = course.name
            binding.textDesExam.text = course.exams
            binding.textDesEligibility.text = course.eligibility
            binding.textDesFees.text = course.fees
            binding.textDesPlacement.text = course.placement
            binding.textDesSubCourse.text = course.subCourses
            binding.cardCourseLower.visibility =  View.GONE

            binding.imageSave.setImageResource(
                if (course.saved == true) R.drawable.ic_save_filled else R.drawable.ic_save_outline
            )


            binding.cardCourseUpper.setOnClickListener{
                if (course.open == true){
                    course.open = false
                    binding.cardCourseLower.visibility = View.GONE
                }else{
                    course.open = true
                    binding.cardCourseLower.visibility = View.VISIBLE
                }
            }

            binding.imageSave.setOnClickListener{
                val savedCourse = SavedCourse(
                    id = 0,
                    name = course.name,
                    placement = course.placement,
                    fees = course.fees,
                    subCourses = course.subCourses,
                    saved = true,
                    uniName = uniName,
                    uniImage = uniImg
                )
                course.saved = !course.saved!!
                if (course.saved == true) {
                    savedCourseViewModel.addCourse(savedCourse)
                    binding.imageSave.setImageResource(R.drawable.ic_save_filled)
                    Log.d("course adapter", "added")
                } else {
                    savedCourseViewModel.deleteCourse(savedCourse)
                    binding.imageSave.setImageResource(R.drawable.ic_save_outline)
                    Log.d("course adapter", "deleted")

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(HolderCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        holder.bind(course)
    }
}