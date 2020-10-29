package com.example.passwordmanager

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.util.*

class Logic() {
    private val user = FirebaseAuth.getInstance().currentUser!!
    var databaseInstance = FirebaseDatabase.getInstance()
    var myRef = databaseInstance.getReference("/users/" ).child(user.uid)


    // to get back the password back from database
    fun showPassword(context: Context,serviceName: String){
        val ref = databaseInstance.getReference("/users/").child(user.uid).child(serviceName)
        val password = object :ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val data = p0.value

                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("Success")
                dialog.setMessage("The Password is- $data")
                dialog.setPositiveButton("OK"){ _, _ -> }
                dialog.create()
                dialog.show()
            }

            override fun onCancelled(p0: DatabaseError) {
                val dialog = AlertDialog.Builder(context)
                dialog.setTitle("Failed")
                dialog.setMessage("Some Error Occurred")
                dialog.setMessage("Retry Later")
                dialog.setPositiveButton("OK"){ _, _ ->}

            }

        }
            ref.addValueEventListener(password)
    }
    // making a password
    private fun makePassword(length: Int): String {
        val allowedChars = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        return (1..length)
            .map { allowedChars.random() }
            .joinToString("")
    }

    // pushing password to database
    fun savePassword(serviceName: String, length:Int,context: Context){
        val password = makePassword(length)
        myRef.child(serviceName).setValue(password).addOnSuccessListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Success")
            dialog.setMessage("The Password is - $password")
            dialog.setPositiveButton("OK"){ _, _ -> }
            dialog.show()
        }.addOnFailureListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Failed")
            dialog.setMessage("Some Error Occurred")
            dialog.setPositiveButton("OK"){ _, _ ->}
        }
   }
    // updating of password
    fun updatePassword(serviceName: String,length:Int,context: Context){
        val update = HashMap<String,String>()
        val password =makePassword(length)
        update[serviceName] = password
        myRef.updateChildren(update as Map<String, Any>).addOnSuccessListener {
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Success")
            dialog.setMessage("Password Changed")
            dialog.setMessage("New Password is $password")
            dialog.setPositiveButton("OK"){ _, _ ->
            }
            dialog.show()
        }.addOnFailureListener{
            val dialog = AlertDialog.Builder(context)
            dialog.setTitle("Failed")
            dialog.setMessage("Some Error Occurred")
            dialog.setPositiveButton("OK"){ _, _ ->}
            dialog.show()
        }
    }

}


