package com.example.appforfun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
            setContentView(R.layout.activity_main)
                val userLogin: EditText = findViewById(R.id.user_login)
                val userEmail: EditText = findViewById(R.id.user_email)
                val userPassword: EditText = findViewById(R.id.user_password)
                val buttonR: Button = findViewById(R.id.button_reg)

                buttonR.setOnClickListener {
                    val login = userLogin.text.toString().trim()
                    val email = userEmail.text.toString().trim()
                    val password = userPassword.text.toString().trim()

                    if (login == "" || email == "" || password == "")
                        Toast.makeText(this, "Вы не заполнили все поля!", Toast.LENGTH_LONG)
                            .show()
                    else {
                        val user = User(login, email, password)

                        val db = DataHelper(this, null)
                        db.addUser(user)
                        Toast.makeText(this, "Дабро пожаловать новый пользователь! $login", Toast.LENGTH_LONG).show()

                        userLogin.text.clear()
                        userEmail.text.clear()
                        userPassword.text.clear()
                    }
                }
                        val linkToLog : TextView = findViewById(R.id.link_to_auth)
                        linkToLog.setOnClickListener{
                            val intent = Intent(this, AuthorizeActivity::class.java)
                            startActivity(intent)
                        }
            }
        }