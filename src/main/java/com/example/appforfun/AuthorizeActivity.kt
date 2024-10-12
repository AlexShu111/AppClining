package com.example.appforfun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class AuthorizeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_authorize)
        val userLogin: EditText = findViewById(R.id.user_login_auth)
        val userPassword: EditText = findViewById(R.id.user_password_auth)
        val buttonAu: Button = findViewById(R.id.button_reg_auth)
        val linkToReg: TextView = findViewById(R.id.link_to_reg)

        linkToReg.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        buttonAu.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == "" || password == "")
                Toast.makeText(this, "You don't feel out all field", Toast.LENGTH_LONG)
                    .show()
            else {
                val db = DataHelper(this, null)
                val isAuth = db.getUser(login, password)
                if(isAuth) {
                    Toast.makeText(this, "Welcome back! $login", Toast.LENGTH_LONG).show()
                    userLogin.text.clear()
                    userPassword.text.clear()

                    val intent = Intent(this, ServicesActivity::class.java)
                    intent.putExtra("USER_LOGIN", login)
                    startActivity(intent)
                }
                else
                    Toast.makeText(this, "SOMETHING WAS WRONG", Toast.LENGTH_LONG).show()
            }
        }


        }
    }