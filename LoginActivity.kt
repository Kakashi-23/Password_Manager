package com.example.passwordmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var userEmail:EditText
    lateinit var userPassword:EditText
    lateinit var loginButton: Button
    private lateinit var mauth:FirebaseAuth
    lateinit var registerButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val actionBar = actionBar
        actionBar?.hide()
         mauth = FirebaseAuth.getInstance()
        userEmail = findViewById(R.id.login_username)
        userPassword = findViewById(R.id.login_password)
        loginButton = findViewById(R.id.login_button)
        registerButton = findViewById(R.id.login_RegisterButton)
        loginButton.setOnClickListener {
            val email = userEmail.text.toString()
            val password = userPassword.text.toString()
            if(email.isEmpty() && password.isEmpty()){
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle("Error")
                dialog.setMessage("Please Fill The Required Field")
                dialog.setPositiveButton("OK"){_, _ ->}
                userEmail.text.clear()
                userPassword.text.clear()
            }
            else{
                login(email,password,mauth)
                userEmail.text.clear()
                userPassword.text.clear()
            }

        }
        registerButton.setOnClickListener {
            val intent =Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }

    private fun login(email:String,password: String, mAuth:FirebaseAuth) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if (it.isSuccessful){
                userEmail.text.clear()
                userPassword.text.clear()
                HomePage()

            }
            else
            {  userEmail.text.clear()
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

    override fun onStart() {
        super.onStart()
        val currentUser =mauth.currentUser
        if (currentUser != null) {
           HomePage()
            finish()
           }

    }
    private fun HomePage(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}