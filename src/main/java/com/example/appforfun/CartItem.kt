package com.example.appforfun

data class CartItem(
    val title: String,
    val price: String?,
    var isSelected: Boolean = false
)

