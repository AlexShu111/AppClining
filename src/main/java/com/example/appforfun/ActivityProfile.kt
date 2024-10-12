package com.example.appforfun

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ActivityProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)

        val profLogin: TextView = findViewById(R.id.ProfLogin)
        val profEmail: TextView = findViewById(R.id.ProfEmail)

        profLogin.text = intent.getStringExtra("USER_LOGIN")


//        val db = DataHelper(this, null)
//        profEmail.text = db.getEmail(profLogin.text.toString()).toString()
    }
}