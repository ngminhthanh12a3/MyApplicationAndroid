package com.example.myapplication

class GlobalVars {
    companion object {
        @JvmField
        val userProfileData = mutableMapOf<String, String>(
            "fullName" to "Eljad Eendaz",
            "email" to "prelookstudio@gmail.com",
            "phoneNumber" to "+1 (783) 0986 8786",
            "fullNameLabel" to "Full name",
            "emailLabel" to "E-mail",
            "phoneNumberLabel" to "Phone number",
        )
    }
}
