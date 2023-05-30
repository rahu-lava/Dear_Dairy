package com.example.deardairy

import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "my_userdata.db"
        const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        val sqlCREATEENTRIES = """
        CREATE TABLE ${UserContract.UserEntry.TABLE_NAME} (
            ${UserContract.UserEntry._ID} INTEGER PRIMARY KEY AUTOINCREMENT,
            ${UserContract.UserEntry.COLUMN_NAME_FULL_NAME} TEXT,
            ${UserContract.UserEntry.COLUMN_NAME_EMAIL} TEXT,
            ${UserContract.UserEntry.COLUMN_NAME_PASSWORD} TEXT
        )
    """.trimIndent()

        db.execSQL(sqlCREATEENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Implement if you need to upgrade the database schema in the future
    }
    fun getUserByEmailAndPassword(email: String, password: String): Cursor? {
        val db = readableDatabase
        val projection = arrayOf(
            UserContract.UserEntry._ID,
            UserContract.UserEntry.COLUMN_NAME_FULL_NAME,
            UserContract.UserEntry.COLUMN_NAME_EMAIL,
            UserContract.UserEntry.COLUMN_NAME_PASSWORD
        )
        val selection = "${UserContract.UserEntry.COLUMN_NAME_EMAIL} = ? AND ${UserContract.UserEntry.COLUMN_NAME_PASSWORD} = ?"
        val selectionArgs = arrayOf(email, password)

        return db.query(
            UserContract.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
    }
    @SuppressLint("Range")
    fun getFullNameByEmail(email: String): String? {
        val db = readableDatabase
        val projection = arrayOf(
            UserContract.UserEntry.COLUMN_NAME_FULL_NAME
        )
        val selection = "${UserContract.UserEntry.COLUMN_NAME_EMAIL} = ?"
        val selectionArgs = arrayOf(email)

        val cursor = db.query(
            UserContract.UserEntry.TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        var fullName: String? = null
        if (cursor != null && cursor.moveToFirst()) {
            fullName = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME_FULL_NAME))
        }

        cursor?.close()
        db.close()

        return fullName
    }

}
