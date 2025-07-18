package com.example.presentation.mappers

import com.example.domain.domainModels.Course
import com.example.presentation.uiModels.UiCourse
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun Course.toUi(): UiCourse {
    val formattedStartDate = startDate.let {
        try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val date = inputFormat.parse(it)

            val calendar = Calendar.getInstance().apply { time = date!! }
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.forLanguageTag("ru"))
            val year = calendar.get(Calendar.YEAR)

            "$day $month $year"
        } catch (e: Exception) {
            it
        }
    }

    return UiCourse(
        id = id,
        title = title,
        text = text,
        price = "$price ₽",
        rate = rate,
        startDate = formattedStartDate,
        hasLike = hasLike,
        publishDate = publishDate
    )
}