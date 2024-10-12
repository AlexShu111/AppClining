package com.example.appforfun

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataHelper(val context: Context, val factory: SQLiteDatabase.CursorFactory?):
    SQLiteOpenHelper(context, "Clining", factory,1) {
    override fun onCreate(database: SQLiteDatabase?) {
        val query = "CREATE TABLE users (id INTEGER PRIMARY KEY, login TEXT, email TEXT, password TEXT)"
        database!!.execSQL(query)
    }

    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        database!!.execSQL("DROP TABLE IF EXISTS users")
        onCreate(database)
    }
    fun addUser (user:User){
        val values = ContentValues()
        values.put("login", user.login)
        values.put("email", user.email)
        values.put("password", user.password)

        val database = this.writableDatabase
        database.insert("users", null, values)

        database.close()
    }
    fun getUser(login: String, password: String) : Boolean{
        val database = this.readableDatabase

        val result = database.rawQuery("SELECT * FROM users WHERE login = '$login' AND password = '$password'", null)
        return result.moveToFirst()
    }

    fun getEmail(login :String): String? {
        val database = this.readableDatabase

        val email = database.rawQuery("SELECT email FROM users WHERE login = '$login'", null)
        return email.getString(email.getColumnIndexOrThrow("email"))
    }
}