package com.example.myapplication.screens.profile

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityProfileBinding
import com.example.myapplication.viewModels.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel

    //    val profileData = ProfileData("Eljad Eendaz","prelookstudio@gmail.com","+1 (783) 0986 8786")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding.viewModel = viewModel


        binding.textViewFullName.setOnClickListener {
            DialogEditor("fullName", viewModel).show(
                supportFragmentManager,
                "Dialog Editor"
            )
        }
        binding.textViewEmail.setOnClickListener {
            DialogEditor("email", viewModel).show(
                supportFragmentManager,
                "Dialog Editor"
            )
        }
        binding.textViewPhone.setOnClickListener {
            DialogEditor("phoneNumber", viewModel).show(
                supportFragmentManager,
                "Dialog Editor"
            )
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
        viewModel.isSuccessEvent.observe(this) { successUserKey ->
            onDialogSubmitFinish(successUserKey)
        }
    }

    private fun onDialogSubmitFinish(fieldName: String) {
//        when (fieldName) {
//            "fullName" -> {
//                binding.textViewName.text = DataStore.currentUserData[fieldName]
//                binding.textViewFullName.text = DataStore.currentUserData[fieldName]
//            }
//            "email" -> binding.textViewEmail.text = DataStore.currentUserData[fieldName]
//            "phoneNumber" -> binding.textViewPhone.text = DataStore.currentUserData[fieldName]
//        }
    }

}