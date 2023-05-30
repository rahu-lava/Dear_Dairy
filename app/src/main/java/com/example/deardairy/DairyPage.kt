package com.example.deardairy

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class DairyPage :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dairy_page)


    }
    override fun onPause() {
        super.onPause()

        val editText = findViewById<EditText>(R.id.writing_area)
        val textToSave = editText.text.toString()

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("savedText", textToSave)
        editor.apply()
    }
    override fun onResume() {
        super.onResume()

        val editText = findViewById<EditText>(R.id.writing_area)

        val sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val savedText = sharedPreferences.getString("savedText", "")

        editText.setText(savedText)
    }


}