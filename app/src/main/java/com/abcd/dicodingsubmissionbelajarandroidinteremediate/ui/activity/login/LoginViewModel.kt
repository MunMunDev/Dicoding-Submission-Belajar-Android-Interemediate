package com.abcd.dicodingsubmissionbelajarandroidinteremediate.ui.activity.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.data.model.UserModel
import com.abcd.dicodingsubmissionbelajarandroidinteremediate.utils.LoginPreferences
import kotlinx.coroutines.launch

class LoginViewModel(
   private val preferences: LoginPreferences
): ViewModel() {

    fun getLogin():LiveData<UserModel>{
        return preferences.getSession().asLiveData()
    }

    fun saveLogin(
        userModel: UserModel
    ){
        viewModelScope.launch {
            preferences.saveLogin(userModel)
        }
    }
}