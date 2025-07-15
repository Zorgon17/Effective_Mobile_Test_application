package com.example.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import java.util.regex.Pattern
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class EnterScreenViewModel : ViewModel() {

    private val _openUrlEvent = MutableSharedFlow<String>()
    val openUrlEvent = _openUrlEvent.asSharedFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password.asStateFlow()

    /**
     * EMAIL И PASSWORD
     */
    // Маска email: простой regex (текст@текст.текст), кириллица не разрешена
    private val emailRegex = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")

    val isEmailValid: StateFlow<Boolean> = email.map { email ->
        emailRegex.matcher(email).matches()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    val isFormValid: StateFlow<Boolean> = combine(isEmailValid, password) { emailValid, pass ->
        emailValid && pass.isNotEmpty()
    }.stateIn(viewModelScope, SharingStarted.Eagerly, false)

    fun onEmailChanged(newEmail: String): String {
        val filtered = newEmail.filter { it.code < 128 }
        _email.value = filtered
        return filtered
    }

    fun onPasswordChanged(newPassword: String) {
        _password.value = newPassword
    }

    /**
     * КНОПКИ ВК И ОК
     */
    fun onVkButtonClick() {
        emitUrl("https://vk.com/")
    }

    fun onOkButtonClick() {
        emitUrl("https://ok.ru/")
    }

    private fun emitUrl(url: String) {
        viewModelScope.launch {
            _openUrlEvent.emit(url)
        }
    }
}