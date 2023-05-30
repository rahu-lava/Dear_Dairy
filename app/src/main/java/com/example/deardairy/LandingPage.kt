package com.example.deardairy

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LandingPage:AppCompatActivity() {
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var selectedImageResource: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)


        val greetET = findViewById<TextView>(R.id.greetingTextView)
        val username = intent.getStringExtra("data")
        val greetingText = getGreetingText()
        greetET.text = greetingText

        val fixedtextview = findViewById<TextView>(R.id.fixedtextView)
        fixedtextview.setOnClickListener {
            // Start the new activity here
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.title_dailog)

            val titleInput = dialog.findViewById<TextView>(R.id.titleTextInput)
            val imageView17 = dialog.findViewById<ImageView>(R.id.imageView17)
            val imageViews = arrayOf<ImageView>(
                dialog.findViewById<ImageView>(R.id.imageView1),
                dialog.findViewById<ImageView>(R.id.imageView2),
                dialog.findViewById<ImageView>(R.id.imageView3),
                dialog.findViewById<ImageView>(R.id.imageView4),
                dialog.findViewById<ImageView>(R.id.imageView5),
                dialog.findViewById<ImageView>(R.id.imageView6),
                dialog.findViewById<ImageView>(R.id.imageView7),
                dialog.findViewById<ImageView>(R.id.imageView8),
                dialog.findViewById<ImageView>(R.id.imageView9),
                dialog.findViewById<ImageView>(R.id.imageView10),
                dialog.findViewById<ImageView>(R.id.imageView11),
                dialog.findViewById<ImageView>(R.id.imageView12),
                dialog.findViewById<ImageView>(R.id.imageView13),
                dialog.findViewById<ImageView>(R.id.imageView14),
                dialog.findViewById<ImageView>(R.id.imageView15),
                dialog.findViewById<ImageView>(R.id.imageView16)
            )
            val buttonSave = dialog.findViewById<Button>(R.id.buttonSave)
            for (element in imageViews) {
                element.setOnClickListener {
                    // Update the image of the next ImageView
                    val drawable = element.drawable
                    imageView17.setImageDrawable(drawable)
                    selectedImageResource = element.tag.toString() // Save the resource name of the selected image
                }
            }
            buttonSave.setOnClickListener {

                val intent = Intent(this, DairyPage::class.java)
                startActivity(intent)
            }
            dialog.show()
        }
    }
    private fun getGreetingText() : String{
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Dairy")
        return getString(R.string.greeting_text, username)
    }
}