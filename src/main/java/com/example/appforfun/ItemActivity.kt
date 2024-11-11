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
        val price : TextView = findViewById(R.id.Service_price)
        val textDesc : TextView = findViewById(R.id.Service_desc)
        val imageItem : ImageView = findViewById(R.id.Service_Im)
        val butBack : Button = findViewById(R.id.button_back)
        val butPay : Button = findViewById(R.id.button_buy)

        title.text = intent.getStringExtra("ItemTitle")
        price.text = intent.getStringExtra("ItemPrice")
        textDesc.text = intent.getStringExtra("ItemText")
        val profLogin = intent.getStringExtra("USER_LOGIN")

        val imageId = intent.getIntExtra("ItemImage", -1)
        if (imageId != -1) {
            imageItem.setImageResource(imageId)
        } else {
            Log.e("ItemActivity", "Invalid imageId")
        }

        butBack.setOnClickListener{
            val intent = Intent(this, ServicesActivity::class.java)
            intent.putExtra("USER_LOGIN", profLogin)
            startActivity(intent)
        }
        butPay.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            intent.putExtra("USER_LOGIN", profLogin)
            intent.putExtra("ItemTitle", title.text)
            intent.putExtra("ItemPrice", price.text)
            startActivity(intent)
        }

    }
}