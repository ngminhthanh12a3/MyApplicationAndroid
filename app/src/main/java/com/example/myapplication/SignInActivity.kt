package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class SignInActivity : AppCompatActivity() {
    private lateinit var imageBackButton:ImageButton
    private lateinit var emailInputText:TextInputEditText
    private lateinit var passwordInputText:TextInputEditText
    private lateinit var loginBtn:Button

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        imageBackButton = findViewById(R.id.imageBackButton)
        imageBackButton.setOnClickListener{
            onBackPressed()
        }

        emailInputText = findViewById(R.id.emailInputText)
        passwordInputText = findViewById(R.id.passwordInputText)

        loginBtn = findViewById(R.id.buttonLogin)
        loginBtn.setOnClickListener { loginOnClick() }
    }

    private fun loginOnClick() {
        if(emailInputText.text.toString() == "username@gmail.com" && passwordInputText.text.toString() == "123456")
        {
            startActivity(Intent(applicationContext, ProfileActivity::class.java))
            finish()
        }

        else
        {
            Toast.makeText(this, "Login failed!",
                Toast.LENGTH_SHORT).show()
        }
    }
}