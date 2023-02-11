package com.example.myapplication.viewModels

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.singletonData.DataStore

class ProfileViewModel:ViewModel() {

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    private var _isSuccessEvent: MutableLiveData<String> = MutableLiveData()
    val isSuccessEvent: LiveData<String>
        get() = _isSuccessEvent

    fun dialogInputValid(dialogInput: Editable?, userDataKey: String, dialogDimiss: () -> Unit) {
        if(dialogInput?.isNotEmpty() == true)
        {
            // Update data
            DataStore.currentUserData[userDataKey] = dialogInput.toString()
            dialogDimiss()
            return _isSuccessEvent.postValue(userDataKey)
        }

        // Throw Error
        _isErrorEvent.postValue("Blank Input")

    }

}