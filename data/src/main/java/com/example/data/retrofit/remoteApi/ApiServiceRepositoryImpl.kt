package com.example.data.retrofit.remoteApi

import com.example.data.retrofit.mappers.toDomain
import com.example.domain.domainModels.Course
import com.example.domain.repository.Repository
import javax.inject.Inject

class ApiServiceRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
) : Repository {

    override suspend fun getListOfCourses(): List<Course> {
        val fileId = "15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q"

        val response = apiService.getListOfCourses(fileId)

        if (response.isSuccessful) {
            val listOfCourses = response.body()?.courses ?: emptyList()
            return listOfCourses.mapNotNull { it?.toDomain() }
        } else {
            throw Exception("Ошибка загрузки курсов: ${response.code()}")
        }
    }
}
