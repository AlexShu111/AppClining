package com.example.appforfun

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OrderHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_history)

        val recyclerView: RecyclerView = findViewById(R.id.ListOrder)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Получаем заказы из базы данных
        val db = DataOrder(this)
        val orders = db.getAllOrders()

        // Устанавливаем адаптер
        recyclerView.adapter = OrderAdapter(orders)
    }
}
