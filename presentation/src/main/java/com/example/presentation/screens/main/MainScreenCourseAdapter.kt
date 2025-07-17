package com.example.presentation.screens.main

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.CourseItemBinding
import com.example.presentation.uiModels.UiCourse

class MainScreenCourseAdapter(private var courses: List<UiCourse>) :
    RecyclerView.Adapter<MainScreenCourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(val binding: CourseItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = CourseItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val course = courses[position]
        with(holder.binding) {
            titleView.text = course.title
            descriptionView.text = course.text
            priceView.text = course.price
            ratingView.text = course.rate
            dateView.text = course.startDate

            val bookmarkRes = if (course.hasLike) {
                R.drawable.active_bookmark
            } else {
                R.drawable.bookmark
            }
            bookmarkView.setImageResource(bookmarkRes)

            headerImageView.setImageResource(R.drawable.cover)

            learnMoreView.text = "Подробнее ➝"
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCourses: List<UiCourse>) {
        courses = newCourses
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int = courses.size
}