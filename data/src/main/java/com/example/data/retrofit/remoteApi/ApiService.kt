package com.example.data.retrofit.remoteApi

import com.example.data.retrofit.dtoModels.retrofitResponses.GetCoursesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("uc")
    suspend fun getListOfCourses(
        @Query("id") fileId: String,
        @Query("export") export: String = "download"
    ): Response<GetCoursesResponseDto>
}