package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    lateinit var userEmail:EditText
    lateinit var userPassword:EditText
    lateinit var registerButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
       title = "Sign Up"
        userEmail = findViewById(R.id.signUp_username)
        userPassword= findViewById(R.id.signUp_password)
        registerButton = findViewById(R.id.signUp_Rbutton)
        registerButton.setOnClickListener {
            val username = userEmail.text.toString()
            val password = userPassword.text.toString()
            print("username is $username")
            Register(username,password)

        }
    }

    private fun Register(username: String, password: String) {
        val mauth = FirebaseAuth.getInstance()
        mauth.createUserWithEmailAndPassword(username,password).addOnCompleteListener {
            if (it.isSuccessful){
                HomePage()
                userEmail.text.clear()
                userPassword.text.clear()
            }
            else{
                userEmail.text.clear()
                userPassword.text.clear()
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error")
                dialog.setMessage("Some Error Occurred")
                dialog.setMessage("Try Again Later")
                dialog.setPositiveButton("OK"){_, _ ->}
                dialog.create()
                dialog.show()
            }
        }

    }

    private fun HomePage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}