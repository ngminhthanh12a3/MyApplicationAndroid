package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Welcome : AppCompatActivity() {
    private lateinit var textViewSignin: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        textViewSignin = findViewById(R.id.textViewSignin)
        textViewSignin.setOnClickListener {
            startActivity(Intent(applicationContext, SignInActivity::class.java))
        }
    }
}