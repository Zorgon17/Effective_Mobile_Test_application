package com.example.data.retrofit.dtoModels.retrofitResponses

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CourseDto(
    @SerialName("id")
    val id: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("text")
    val text: String? = null,
    @SerialName("price")
    val price: String? = null,
    @SerialName("rate")
    val rate: String? = null,
    @SerialName("startDate")
    val startDate: String? = null,
    @SerialName("hasLike")
    val hasLike: Boolean? = null,
    @SerialName("publishDate")
    val publishDate: String? = null
)