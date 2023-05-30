package com.example.deardairy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider

class LogInPageActivity : AppCompatActivity(){

    private lateinit var dbHelper: UserDbHelper
    private lateinit var loginButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page_activity)

        dbHelper = UserDbHelper(this)

        loginButton = findViewById(R.id.loginButton)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            val user = dbHelper.getUserByEmailAndPassword(email, password)

            if (user != null && user.moveToFirst()) {

                sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()

                val username = dbHelper.getFullNameByEmail(email).toString()
                editor.putString("username", username)
                editor.putBoolean("isValueTrue", true)
                editor.apply()
                Toast.makeText(this, "welcome $username", Toast.LENGTH_SHORT).show()

                startActivity(Intent(this, LandingPage::class.java))
                finish()

            } else {
                // Credentials don't match, show error message and refresh activity
                Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show()
                recreate()
            }
        }
    }

    override fun onDestroy() {
//        dbHelper.close()
        super.onDestroy()
    }


}

