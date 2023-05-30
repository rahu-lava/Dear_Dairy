package com.example.deardairy

import android.provider.BaseColumns

object UserContract {
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "userdata"
        const val _ID = "_id"
        const val COLUMN_NAME_FULL_NAME = "full_name"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_PASSWORD = "password"
    }
}
