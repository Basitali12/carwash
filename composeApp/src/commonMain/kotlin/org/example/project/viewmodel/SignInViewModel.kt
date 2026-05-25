package org.example.project.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SignInViewModel {
    private val _phone = mutableStateOf("")
    val phone: State<String> = _phone

    private val _password = mutableStateOf("")
    val password: State<String> = _password

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    fun onPhoneChange(newValue: String) {
        _phone.value = newValue
        _error.value = null
    }

    fun onPasswordChange(newValue: String) {
        _password.value = newValue
        _error.value = null
    }

    fun signIn(onSuccess: (String) -> Unit) {
        if (_phone.value.isBlank() || _password.value.isBlank()) {
            _error.value = "Please fill in all fields"
            return
        }

        // Simulate network call
        _isLoading.value = true
        // In a real app, this would be a coroutine call to a repository
        // For now, we just simulate success after a short delay in the UI if possible, 
        // but here we'll just call the success callback
        onSuccess(_phone.value)
        _isLoading.value = false
    }
}
