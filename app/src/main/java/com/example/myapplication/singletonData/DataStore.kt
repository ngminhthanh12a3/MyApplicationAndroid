package com.example.myapplication.singletonData

import androidx.lifecycle.MutableLiveData

class DataStore {
    companion object {
        var userDataStore: MutableList<MutableMap<String, String>> = mutableListOf(mutableMapOf(
            "fullName" to "Eljad Eendaz",
            "email" to "username@gmail.com",
            "password" to "12345678",
            "phoneNumber" to "+1 (783) 0986 8786"
        ))
        val currentUserData: MutableLiveData<MutableMap<String, String>> = MutableLiveData()
    }
}