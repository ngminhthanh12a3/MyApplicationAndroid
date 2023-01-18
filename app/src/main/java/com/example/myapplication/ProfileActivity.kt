package com.example.myapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var textViewName:TextView
    private lateinit var textViewFullName:TextView
    private lateinit var textViewEmail:TextView
    private lateinit var textViewPhone:TextView
    private lateinit var dialog: DialogEditor
//    val profileData = ProfileData("Eljad Eendaz","prelookstudio@gmail.com","+1 (783) 0986 8786")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        textViewName = findViewById(R.id.textViewName)
        textViewFullName = findViewById(R.id.textViewFullName)
        textViewEmail = findViewById(R.id.textViewEmail)
        textViewPhone = findViewById(R.id.textViewPhone)

        textViewName.text = GlobalVars.userProfileData["fullName"]
        textViewFullName.text = GlobalVars.userProfileData["fullName"]
        textViewEmail.text = GlobalVars.userProfileData["email"]
        textViewPhone.text = GlobalVars.userProfileData["phoneNumber"]

        dialog = DialogEditor { onSubmitFinish() }
        textViewFullName.setOnClickListener { dialog.show(supportFragmentManager, "Dialog Editor") }
        textViewEmail.setOnClickListener { dialog.show(supportFragmentManager, "Dialog Editor") }
        textViewPhone.setOnClickListener { dialog.show(supportFragmentManager, "Dialog Editor") }
    }

    private fun onSubmitFinish()
    {
        textViewName.text = GlobalVars.userProfileData["fullName"]
        textViewFullName.text = GlobalVars.userProfileData["fullName"]
        textViewEmail.text = GlobalVars.userProfileData["email"]
        textViewPhone.text = GlobalVars.userProfileData["phoneNumber"]
    }

}