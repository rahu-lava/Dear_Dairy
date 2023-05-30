package com.example.deardairy

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class SignInPageActivity : AppCompatActivity() {
    private lateinit var dbHelper: UserDbHelper
    private lateinit var signinButton: Button
    private lateinit var password: String
    private lateinit var fullName: String
    private lateinit var email: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_page_activity)

        dbHelper = UserDbHelper(this)

        val fullnametv = findViewById<TextView>(R.id.fullNameTextView)
        val emailtv = findViewById<TextView>(R.id.emailTextView)
        val passwordtv = findViewById<TextView>(R.id.passwordTextView)
        val repasswordtv = findViewById<TextView>(R.id.re_passwordTextView)

        signinButton = findViewById(R.id.signinButton)
        // Example of inserting user data into the database
        signinButton.setOnClickListener {
            fullName = fullnametv.text.toString()
            email = emailtv.text.toString()
            if (passwordtv.text.toString() == repasswordtv.text.toString()) {
                password = passwordtv.text.toString()

                val db = dbHelper.writableDatabase

                val values = ContentValues().apply {
                    put(UserContract.UserEntry.COLUMN_NAME_FULL_NAME, fullName)
                    put(UserContract.UserEntry.COLUMN_NAME_EMAIL, email)
                    put(UserContract.UserEntry.COLUMN_NAME_PASSWORD, password)
                }

                val newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values)
                if (newRowId == -1L) {
                    // Insertion failed
                    Toast.makeText(this, "Insertion failed", Toast.LENGTH_SHORT).show()
                } else {
                    // Insertion successful
                    Toast.makeText(this, "Insertion successful", Toast.LENGTH_SHORT).show()
                    // Perform activity change here
                    val intent = Intent(this, LogInPageActivity::class.java)
                    startActivity(intent)
                    // Finish the current activity if needed
                    finish()
                }
            } else {
                Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
//        dbHelper.close()
        super.onDestroy()
    }
}
