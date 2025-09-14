package com.abcd.dicodingsubmissionbelajarandroidinteremediate.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.utils.LoginPreferences
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.ui.activity.login.LoginViewModel

@Suppress("UNCHECKED_CAST")
class LoginFactory (
    private val preferences: LoginPreferences
): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java))
            return LoginViewModel(preferences) as T

        throw IllegalArgumentException("Unknown ViewModel class : "+modelClass.name)
    }
}