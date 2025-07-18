package com.example.presentation.viewmodels.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.usecase.GetCoursesUseCase
import com.example.presentation.mappers.toUi
import com.example.presentation.uiModels.UiCourse
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainScreenViewModel @Inject constructor(
private val getCoursesUseCase: GetCoursesUseCase
) : ViewModel() {

    private val _courses = MutableStateFlow<List<UiCourse>>(emptyList())
    val courses: StateFlow<List<UiCourse>> = _courses

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        loadCourses()
    }

    private fun loadCourses() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = getCoursesUseCase()
                _courses.value = result.map { it.toUi() }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }

    fun sortByDate() {
        val formatter = SimpleDateFormat("dd MMMM yyyy", Locale.forLanguageTag("ru"))

        _courses.value = _courses.value.sortedBy { course ->
            try {
                formatter.parse(course.startDate) ?: Date(0)
            } catch (e: Exception) {
                Date(0)
            }
        }
    }

}