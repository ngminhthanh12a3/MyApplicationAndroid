package com.example.myapplication.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.singletonData.DataStore

class SignUpViewModel : ViewModel() {
    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    fun onSignUp(fullName: String, email: String, password: String) {

        var _errorString = ""

        // check fullName
        if (fullName.isNullOrEmpty())
            _errorString += "Your full name is null or empty"

        // check email
        if (!isEmailValid(email))
        {
            if(_errorString.isNotEmpty()) _errorString += "\n"
            _errorString += "Invalid E-mail"
        }

        if(isExistEmail(email))
        {
            if(_errorString.isNotEmpty()) _errorString += "\n"
            _errorString += "E-mail is already exist"
        }
        // check password
        if (!isPasswordValid(password))
        {
            if(_errorString.isNotEmpty()) _errorString += "\n"
            _errorString += "\nPassword "
        }

        // Throw Error
        if(_errorString.isNotEmpty())
            return _isErrorEvent.postValue(_errorString)

        _isSuccessEvent.postValue(true)

    }

    private fun isExistEmail(email: String): Boolean {
        val _isExistEmail = DataStore.userDataStore.find { userData -> userData["email"] == email }?.isNotEmpty()
        return _isExistEmail == true
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        return password.length in 8..10
    }

}