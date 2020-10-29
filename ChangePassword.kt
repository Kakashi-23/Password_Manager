package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class ChangePassword : AppCompatActivity() {
    lateinit var serviceName: EditText
    lateinit var passwordLength: EditText
    lateinit var okButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chaange_password)
        title = "Change Password"
        serviceName = findViewById(R.id.cp_ServiceName)
        passwordLength = findViewById(R.id.cp_Length)
        okButton = findViewById(R.id.cp_Button)
        val actionBar = actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        okButton.setOnClickListener {
            val serviceName1 =serviceName.text.toString()
            val length1 = passwordLength.text.toString()
            if(serviceName1.isEmpty() && length1.isEmpty()){
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
                logicObject.updatePassword(serviceName1,length1.toInt(),this)
                serviceName.text.clear()
                passwordLength.text.clear()
            }


        }
    }
}