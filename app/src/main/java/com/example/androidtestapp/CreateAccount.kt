package com.example.androidtestapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CreateAccount : AppCompatActivity() {

    private var nameEditText: EditText? = null
    private var lastNameEditText: EditText? = null
    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var createAccountButton: Button? = null


    private var firebaseAuth: FirebaseAuth? = null
    private var firebaseDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)



      nameEditText = findViewById<EditText>(R.id.nameEditTextCna)
        lastNameEditText = findViewById<EditText>(R.id.lastnameEditTextCna)
        emailEditText = findViewById<EditText>(R.id.emailEditTextCna)
        passwordEditText = findViewById<EditText>(R.id.passwordEditTextCna)

        createAccountButton = findViewById<Button>(R.id.signUpCNA)

        firebaseAuth = FirebaseAuth.getInstance()
        firebaseDatabase = FirebaseDatabase.getInstance().reference

        createAccountButton?.setOnClickListener({

           createNewAccount()

        })
    }

    private fun createNewAccount() {

        var email = emailEditText?.text.toString().trim()
        var password = passwordEditText?.text.toString().trim()
        var name = nameEditText?.text.toString().trim()
        var lastname = lastNameEditText?.text.toString().trim()


        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(name) || TextUtils.isEmpty(
                lastname
            )
        ) {
            Toast.makeText(applicationContext, "Please fill in all the fields", Toast.LENGTH_SHORT)
                .show()
        } else {
            firebaseAuth?.createUserWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(object :
                    OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {

                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Account created",
                                Toast.LENGTH_SHORT
                            ).show()
                           updateUserInfo(name, lastname, email)
                            startActivity(Intent(this@CreateAccount, MainActivity::class.java))

                        } else {
                            var error = task.exception?.message
                            Toast.makeText(
                                applicationContext,
                                "Error: " + error,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                })


        }
    }

    private fun updateUserInfo(firstName: String, lastName: String, email: String) {
        var curentUser = firebaseDatabase?.child("Users")?.child(firebaseAuth?.currentUser!!.uid)
        var userInfo = HashMap<String, Any>()

        userInfo.put("firstName", firstName)
        userInfo.put("lastName", lastName)
        userInfo.put("email", email)

        curentUser?.updateChildren(userInfo)
            ?.addOnCompleteListener(object : OnCompleteListener<Void> {
                override fun onComplete(task: Task<Void>) {


                    if (task.isSuccessful)
                        Toast.makeText(
                            applicationContext,
                            "Profile updated!",
                            Toast.LENGTH_SHORT
                        ).show()
                    else {
                        val error = task.exception?.message
                        Toast.makeText(
                            applicationContext,
                            "Error: " + error,
                            Toast.LENGTH_SHORT
                        ).show()

                    }

                }

            })

    }
}