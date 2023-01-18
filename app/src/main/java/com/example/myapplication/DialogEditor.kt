package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class DialogEditor(private val fieldName: String, private  val onSubmitFinish: () -> Unit) : DialogFragment() {
    private lateinit var textInputProfileField:TextInputEditText
    private lateinit var textViewProfileFieldLabel:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_editor, container, false)

        rootView.findViewById<Button>(R.id.buttonCancel).setOnClickListener { onCancelClick() }
        rootView.findViewById<Button>(R.id.buttonSubmit).setOnClickListener { onSubmitClick() }

        textInputProfileField = rootView.findViewById<TextInputLayout>(R.id.textInputProfileFieldLayout).findViewById(R.id.textInputProfileField)
        textViewProfileFieldLabel = rootView.findViewById(R.id.textViewProfileFieldLabel)

        textInputProfileField.setText(GlobalVars.userProfileData[fieldName])
        textViewProfileFieldLabel.text = GlobalVars.userProfileData[fieldName + "Label"]

        return rootView
    }

    private fun onSubmitClick() {

        if(textInputProfileField.text.isNullOrBlank())
        {
            Toast.makeText(context, "Blank Input",
                Toast.LENGTH_SHORT).show()
        }
        else
        {
            GlobalVars.userProfileData[fieldName] = textInputProfileField.text.toString()

            onSubmitFinish()
            dismiss()
        }

    }

    private fun onCancelClick() {
        dismiss()
    }
}