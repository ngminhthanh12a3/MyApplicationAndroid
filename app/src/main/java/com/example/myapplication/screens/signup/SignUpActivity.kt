package com.example.myapplication.screens.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivitySignUpBinding
import com.example.myapplication.screens.signin.SignInActivity
import com.example.myapplication.singletonData.DataStore
import com.example.myapplication.viewModels.SignUpViewModel

class SignUpActivity : AppCompatActivity() {
    // Obtain ViewModel from ViewModelProviders
    private lateinit var viewModel: SignUpViewModel
    private lateinit var  binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        binding =DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        binding.viewModel = viewModel

        binding.textViewSignin.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }

        listenerSuccessEvent()
        listenerErrorEvent()

    }

    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(this) { errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }

    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(this) { isSuccess ->
            if(isSuccess)
            {
                // Success
                DataStore.userDataStore.add(
                    mutableMapOf("fullName" to binding.fullNameInputText.text.toString(),
                        "email" to binding.emailInputText.text.toString(),
                        "password" to binding.passwordInputText.text.toString())
                )
                Toast.makeText(this, "SignUp Success", Toast.LENGTH_SHORT).show()
            }
        }
    }
}