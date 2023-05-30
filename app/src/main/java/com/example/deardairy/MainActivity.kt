package com.example.deardairy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
//import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
//import android.view.WindowInsets
import android.widget.Button
//import androidx.annotation.RequiresApi

class MainActivity : AppCompatActivity() {
//    @RequiresApi(Build.VERSION_CODES.R)
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var sharedPreferences: SharedPreferences


//    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Hide the status bar
//        window?.decorView?.windowInsetsController?.hide(WindowInsets.Type.statusBars())

        // Hide the action bar if necessary
        supportActionBar?.hide()
    sharedPreferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE)
    val value = sharedPreferences.getBoolean("isValueTrue", false)
    if (value){
        val intent = Intent(this, LandingPage::class.java)
        startActivity(intent)
    }


    setContentView(R.layout.activity_main)

        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)

        button3.setOnClickListener {
            // Handle button3 click
            try {
                val intent = Intent(this, LogInPageActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Error starting LogInPageActivity", e)
            }
        }

        button4.setOnClickListener {
            // Handle button4 click
            try {
                val intent = Intent(this, SignInPageActivity::class.java)
                startActivity(intent)
            } catch (e: Exception) {
                Log.e(TAG, "Error starting SignInPageActivity", e)
            }
        }
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
