package com.example.presentation.mappers

import com.example.domain.domainModels.Course
import com.example.presentation.uiModels.UiCourse

fun Course.toUi(): UiCourse {
    return UiCourse(
        id = id,
        title = title,
        text = text,
        price = "$price ₽",
        rate = rate,
        startDate = startDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}
