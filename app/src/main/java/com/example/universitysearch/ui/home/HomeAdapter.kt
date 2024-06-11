package com.example.universitysearch.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.universitysearch.databinding.HolderUniversityBinding
import com.example.universitysearch.modal.University

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.UniversityHolder>() {

    private val differCallBack = object: DiffUtil.ItemCallback<University>() {
        override fun areItemsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: University, newItem: University): Boolean {
            return oldItem == newItem
        }
    }


    private val differ = AsyncListDiffer(this,differCallBack)

    fun saveData( dataResponse: List<University>){
        differ.submitList(dataResponse)
    }

    inner class UniversityHolder(private val binding: HolderUniversityBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(university: University){
            binding.textUniversityName.text = university.name
            binding.cardHolderUniversity.setOnClickListener{
                onItemClick?.let{ it(university) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityHolder {
        return UniversityHolder(HolderUniversityBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: UniversityHolder, position: Int) {
        val university = differ.currentList[position]
        holder.bind(university)
    }

    var onItemClick :((University) -> Unit)? = null
}