package com.example.deardairy

import android.app.Dialog
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class LandingPage:AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
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
                saveSelectedImageResource(selectedImageResource)
                saveTitleForDay(titleInput.text.toString())
                val intent = Intent(this, DairyPage::class.java)
                startActivity(intent)
            }
            dialog.show()
        }
    }

    private fun getGreetingText() : String {
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "Dairy")
        return getString(R.string.greeting_text, username)
    }

    private fun saveSelectedImageResource(resource: String) {
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Remove the image resource name for the seventh day (day 7)
        if (sharedPreferences.contains("day7image")) {
            editor.remove("day7image")
        }

        // Shift the image resource name for the previous days
        for (i in 6 downTo 1) {
            val previousDayKey = "day$i"
            val nextDayKey = "day${i + 1}"
            val previousDayImageResource = sharedPreferences.getString(previousDayKey + "image", null)

            if (previousDayImageResource != null) {
                editor.putString(nextDayKey + "image", previousDayImageResource)
            }
        }

        // Save the image resource name for the first day (day 1)
        editor.putString("day1image", resource)

        editor.apply()
    }

    private fun saveTitleForDay(title: String) {
        sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Remove the title for the seventh day (day 7)
        if (sharedPreferences.contains("day7title")) {
            editor.remove("day7title")
        }

        // Shift the titles for the previous days
        for (i in 6 downTo 1) {
            val previousDayKey = "day$i"
            val nextDayKey = "day${i + 1}"
            val previousDayTitle = sharedPreferences.getString(previousDayKey + "title", null)

            if (previousDayTitle != null) {
                editor.putString(nextDayKey + "title", previousDayTitle)
            }
        }

        // Save the title for the first day (day 1)
        editor.putString("day1title", title)

        editor.apply()
    }

    private fun getCurrentDate(): String {
        val currentDate = Calendar.getInstance().time
        val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
        return dateFormat.format(currentDate)
    }

    private fun saveCurrentDateForDay(day: Int) {
        val sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val dayKey = "day$day"
        val currentDateKey = dayKey + "date"
        val currentDate = getCurrentDate()

        editor.putString(currentDateKey, currentDate)
        editor.apply()
    }

    private fun getCurrentDateForDay(day: Int): String? {
        val sharedPreferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE)
        val currentDateKey = "day$day" + "date"
        return sharedPreferences.getString(currentDateKey, null)
    }
}
