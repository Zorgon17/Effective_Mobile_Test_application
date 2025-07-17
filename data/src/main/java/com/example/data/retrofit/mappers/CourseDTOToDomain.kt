package com.example.data.retrofit.mappers

import com.example.data.retrofit.dtoModels.retrofitResponses.CourseDto
import com.example.domain.domainModels.Course

fun CourseDto.toDomain(): Course = Course(
    id = this.id ?: 0,
    title = this.title.orEmpty(),
    text = this.text.orEmpty(),
    price = this.price.orEmpty(),
    rate = this.rate.orEmpty(),
    startDate = this.startDate.orEmpty(),
    hasLike = this.hasLike ?: false,
    publishDate = this.publishDate.orEmpty()
)
