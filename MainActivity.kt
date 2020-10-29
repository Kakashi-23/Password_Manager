package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.graphics.toColor
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    lateinit var newPassword:Button
    lateinit var showPassword:Button
    lateinit var changePassword:Button
    lateinit var logOutButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        newPassword = findViewById(R.id.main_New_Password)
        showPassword = findViewById(R.id.main_Show_Password)
        changePassword = findViewById(R.id.main_Change_Password)
        logOutButton = findViewById(R.id.main_LogOut)
        title = "Home"
        newPassword.setOnClickListener {
            val intent = Intent(this, Inputs::class.java)
            startActivity(intent)
        }
        showPassword.setOnClickListener {
            val intent = Intent(this, ShowPassword::class.java)
            startActivity(intent)

        }
        changePassword.setOnClickListener {
            val intent = Intent(this, ChangePassword::class.java)
            startActivity(intent)
        }

        logOutButton.setOnClickListener {
           FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()

        }
    }



}