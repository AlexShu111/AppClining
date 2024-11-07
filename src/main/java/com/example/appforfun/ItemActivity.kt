package com.example.appforfun

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class ItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item)

        val title : TextView = findViewById(R.id.Service_title)
        val textIt : TextView = findViewById(R.id.Service_desc)
        val price : TextView = findViewById(R.id.Service_price)
        val imageItem : ImageView = findViewById(R.id.Service_Im)
        val butBack : Button = findViewById(R.id.button_back)

        title.text = intent.getStringExtra("ItemTitle")
        textIt.text = intent.getStringExtra("ItemText")
        price.text = intent.getStringExtra("ItemPrice")

        val imageId = intent.getIntExtra("ItemImage", -1)
        if (imageId != -1) {
            imageItem.setImageResource(imageId)
        } else {
            Log.e("ItemActivity", "Invalid imageId")
        }

        butBack.setOnClickListener{
            val intent = Intent(this, ServicesActivity::class.java)
            startActivity(intent)
        }
    }
}