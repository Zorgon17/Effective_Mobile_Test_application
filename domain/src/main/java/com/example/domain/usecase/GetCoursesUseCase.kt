package com.example.domain.usecase

import com.example.domain.domainModels.Course
import com.example.domain.repository.Repository
import javax.inject.Inject

class GetCoursesUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): List<Course> {
        return repository.getListOfCourses()
    }
}