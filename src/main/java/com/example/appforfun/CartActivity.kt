package com.example.appforfun

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class CartActivity : AppCompatActivity() {

    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var cartAdapter: CartAdapter
    private val cartItems = mutableListOf<CartItem>()  // Массив для хранения товаров в корзине

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        cartRecyclerView.layoutManager = LinearLayoutManager(this)

        cartAdapter = CartAdapter(cartItems) { item, isSelected ->
            item.isSelected = isSelected
        }
        cartRecyclerView.adapter = cartAdapter
        val profLogin = intent.getStringExtra("USER_LOGIN")
        loadCartItems()  // Загружаем товары в корзину

        val itemTitle = intent.getStringExtra("ItemTitle")
        val itemPrice = intent.getStringExtra("ItemPrice")

        // Проверяем, если данные переданы, добавляем их в список

        val cartItem = itemTitle?.let { CartItem(title = it, price = itemPrice) }
        if (cartItem != null) {
            cartItems.add(cartItem)
            saveCartItems()
        }
        findViewById<Button>(R.id.btnPlaceOrder).setOnClickListener {
            saveToOrderHistory(cartItems)  // Сохраняем товары в истории заказов
            clearCart()  // Очищаем корзину после заказа
            finish()  // Закрываем активити
        }
        findViewById<Button>(R.id.btnFromCartToServices).setOnClickListener {
            val intent = Intent(this, ServicesActivity::class.java)
            intent.putExtra("USER_LOGIN", profLogin)
            startActivity(intent)
        }
    }

    // Метод для сохранения корзины в SharedPreferences
    private fun saveCartItems() {
        val sharedPreferences = getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json = gson.toJson(cartItems)  // Преобразуем список в JSON
        editor.putString("cartItems", json)
        editor.apply()
    }
    // Метод для загрузки корзины из SharedPreferences
    private fun loadCartItems() {
        val sharedPreferences = getSharedPreferences("CartPreferences", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("cartItems", null)
        val type = object : TypeToken<MutableList<CartItem>>() {}.type
        val items: MutableList<CartItem>? = gson.fromJson(json, type)
        // Добавляем товары в корзину, если они были сохранены
        if (items != null) {
            cartItems.clear()
            cartItems.addAll(items)
        }
    }

    // Метод для очистки корзины после оформления заказа
    private fun clearCart() {
        cartItems.clear()
        saveCartItems()  // Обновляем SharedPreferences
        cartAdapter.notifyDataSetChanged()
    }

    private fun saveToOrderHistory(items: List<CartItem>) {
        val db = DataOrder(this)
        // Сохраняем каждый товар из выбранных в истории
        items.forEach { item ->
            item.price?.let { db.addOrder(item.title, it) }
        }
        Toast.makeText(this, "Заказ добавлен в историю", Toast.LENGTH_SHORT).show()
    }

}
