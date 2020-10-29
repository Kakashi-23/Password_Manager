package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class Inputs : AppCompatActivity() {
    lateinit var serviceName:EditText
    lateinit var passwordLength:EditText
    lateinit var okButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inputs)
        serviceName = findViewById(R.id.input_ServiceName)
        passwordLength = findViewById(R.id.input_Length)
        okButton = findViewById(R.id.input_Button)
        val actionBar = actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title="New Password"
        okButton.setOnClickListener {
            val serviceNameVar = serviceName.text.toString()
            val length = passwordLength.text.toString()
            if(serviceNameVar.isEmpty() && length.isEmpty()){
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error")
                dialog.setMessage("Please Fill The Required Field")
                dialog.setPositiveButton("OK"){_, _ ->}
                dialog.create()
                dialog.show()
                serviceName.text.clear()
                passwordLength.text.clear()
            }
            else{
                val logicObject = Logic()
                logicObject.savePassword(serviceNameVar,length.toInt(),context = this)
                serviceName.text.clear()
                passwordLength.text.clear()
            }


        }


    }
}