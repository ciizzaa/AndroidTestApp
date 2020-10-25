package com.example.androidtestapp

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var emailEditText: EditText? = null
    private var passwordEditText: EditText? = null
    private var createNewAccButton: Button? = null
    private var loginButton: Button? = null

    private var firebaseAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loginButton = findViewById<Button>(R.id.loginButton)
        createNewAccButton = findViewById<Button>(R.id.createAccButton)
        emailEditText = findViewById<EditText>(R.id.emailEditTextLogin)
        passwordEditText = findViewById<EditText>(R.id.passwordEditTextLogin)

        firebaseAuth = FirebaseAuth.getInstance()

        loginButton?.setOnClickListener(View.OnClickListener {
            loginUser()
        })
        createNewAccButton?.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this@MainActivity, CreateAccount::class.java))

        })


    }

    private fun loginUser() {

        var email = emailEditText?.text.toString().trim()
        var password = passwordEditText?.text.toString().trim()

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please fill in all the fields", Toast.LENGTH_SHORT)
                .show()
        } else {
            firebaseAuth?.signInWithEmailAndPassword(email, password)
                ?.addOnCompleteListener(object :
                    OnCompleteListener<AuthResult> {
                    override fun onComplete(task: Task<AuthResult>) {

                        if (task.isSuccessful) {
                            Toast.makeText(
                                applicationContext,
                                "Login successful!",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@MainActivity, WorkSpaceActivity::class.java))
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


}