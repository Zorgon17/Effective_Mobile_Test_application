package com.example.data.retrofit.dtoModels.retrofitResponses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GetCoursesResponseDto(
    @SerialName("courses")
    val courses: List<CourseDto?>? = null
)
