package com.example.domain.repository

import com.example.domain.domainModels.Course

interface Repository {
    suspend fun getListOfCourses(): List<Course>
}