package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog

class ShowPassword : AppCompatActivity() {
    lateinit var serviceName:EditText
    lateinit var okButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_showpassword)
        title ="Show Password"
        serviceName = findViewById(R.id.sp_ServiceName)
        okButton = findViewById(R.id.sp_Button)
        val actionBar = actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        okButton.setOnClickListener {
            val serviceName1 =serviceName.text.toString()
            if(serviceName1.isEmpty()){
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error")
                dialog.setMessage("Please Fill The Required Field")
                dialog.setPositiveButton("OK"){_, _ ->}
                dialog.create()
                dialog.show()
                serviceName.text.clear()

            }
            else{
                val logicObject =Logic()
                logicObject.showPassword(this,serviceName.text.toString())
                serviceName.text.clear()
            }



        }
    }
}