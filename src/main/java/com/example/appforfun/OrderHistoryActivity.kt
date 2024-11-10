package com.example.appforfun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val recyclerView: RecyclerView = findViewById(R.id.ListOrder)
        val buttToProf : Button = findViewById(R.id.button_hist_prof)
        val profLogin = intent.getStringExtra("USER_LOGIN")
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Получаем заказы из базы данных
        val db = DataOrder(this)
        val orders = db.getAllOrders()

        // Устанавливаем адаптер
        recyclerView.adapter = OrderAdapter(orders)
        buttToProf.setOnClickListener{
            val intent = Intent(this, ActivityProfile::class.java)
            intent.putExtra("USER_LOGIN", profLogin)
            startActivity(intent)
        }
    }
}
