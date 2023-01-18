package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DialogEditor(private  val onSubmitFinish: () -> Unit) : DialogFragment() {
    private lateinit var textInputFullName:TextInputEditText
    private lateinit var textInpuEmail:TextInputEditText
    private lateinit var textInputPhone:TextInputEditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_editor, container, false)

        rootView.findViewById<Button>(R.id.buttonCancel).setOnClickListener { onCancelClick() }
        rootView.findViewById<Button>(R.id.buttonSubmit).setOnClickListener { onSubmitClick() }

        textInputFullName = rootView.findViewById<TextInputLayout>(R.id.textInputFullnameLayout).findViewById(R.id.textInputFullName)
        textInpuEmail = rootView.findViewById<TextInputLayout>(R.id.textInputEmailLayout).findViewById(R.id.textInpuEmail)
        textInputPhone = rootView.findViewById<TextInputLayout>(R.id.textInputPhoneLayout).findViewById(R.id.textInputPhone)

        textInputFullName.setText(GlobalVars.userProfileData["fullName"])
        textInpuEmail.setText(GlobalVars.userProfileData["email"])
        textInputPhone.setText(GlobalVars.userProfileData["phoneNumber"])

        return rootView
    }

    private fun onSubmitClick() {

        if(textInputFullName.text.isNullOrBlank() || textInpuEmail.text.isNullOrBlank() || textInputPhone.text.isNullOrBlank())
        {
            Toast.makeText(context, "Blank Input",
                Toast.LENGTH_SHORT).show()
        }
        else
        {
            GlobalVars.userProfileData["fullName"] = textInputFullName.text.toString()
            GlobalVars.userProfileData["email"] = textInpuEmail.text.toString()
            GlobalVars.userProfileData["phoneNumber"] = textInputPhone.text.toString()

            onSubmitFinish()
            dismiss()
        }

    }

    private fun onCancelClick() {
        dismiss()
    }
}