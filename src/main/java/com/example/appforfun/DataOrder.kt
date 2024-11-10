package com.example.appforfun

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataOrder(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserOrders.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_ORDERS = "orders"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_DESCRIPTION = "description"
        private const val COLUMN_PRICE = "price"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createOrdersTable = """
            CREATE TABLE $TABLE_ORDERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_TITLE TEXT,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_PRICE TEXT
            )
        """
        db.execSQL(createOrdersTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_ORDERS")
        onCreate(db)
    }

    fun addOrder(title: String, price: String) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, title)
            put(COLUMN_PRICE, price)
        }
        db.insert(TABLE_ORDERS, null, values)
        db.close()
    }

    fun getAllOrders(): List<Order> {
        val ordersList = mutableListOf<Order>()
        val db = this.readableDatabase
        val cursor = db.query(TABLE_ORDERS, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
                val price = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PRICE))
                ordersList.add(Order(title, price))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return ordersList
    }
}
