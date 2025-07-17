package com.example.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.presentation.mappers.toUi
import com.example.domain.usecase.GetCoursesUseCase
import com.example.presentation.uiModels.UiCourse
import dagger.hilt.android.lifecycle.HiltViewModel
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
        Log.d("ApiServiceRepo", "ViewModel created")
        loadCourses()
    }

    private fun loadCourses() {
        Log.d("ApiServiceRepo", "loadCourses called")
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("MainScreenViewModel", "Coroutine started")
            try {
                val result = getCoursesUseCase()
                _courses.value = result.map { it.toUi() }
            } catch (e: Exception) {
                _error.value = e.message
            }
        }
    }
}
