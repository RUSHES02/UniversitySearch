package com.example.universitysearch.ui.savedCourses

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.universitysearch.databinding.HolderSavedCourseBinding
import com.example.universitysearch.modal.SavedCourse

class SavedCourseAdapter: RecyclerView.Adapter<SavedCourseAdapter.SavedCourseHolder>() {


    private val differCallBack = object: DiffUtil.ItemCallback<SavedCourse>() {
        override fun areItemsTheSame(oldItem: SavedCourse, newItem: SavedCourse): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SavedCourse, newItem: SavedCourse): Boolean {
            return oldItem == newItem
        }
    }


    private val differ = AsyncListDiffer(this,differCallBack)

    fun saveData( dataResponse: List<SavedCourse>){
        differ.submitList(dataResponse)
    }
    inner class SavedCourseHolder(private val binding: HolderSavedCourseBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(course: SavedCourse){
            binding.textSCUniName.text = course.uniName
            binding.textSCName.text = course.name
            binding.textSCFees.text = course.fees
            binding.textSCPlacement.text = course.placement
            binding.textSCSubCourse.text = course.subCourses
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedCourseHolder {
        return SavedCourseHolder(HolderSavedCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: SavedCourseHolder, position: Int) {
        val course = differ.currentList[position]
        holder.bind(course)
    }
}