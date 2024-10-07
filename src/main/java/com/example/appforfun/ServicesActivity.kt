package com.example.appforfun

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ServicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_services)

        val ServiceList: RecyclerView = findViewById(R.id.CleaningList)
        val services = arrayListOf<Services>()
        services.add(Services(1, "genclin","Генеральная уборка","Тип удаляемых загрязнений: максимально возможные",500))
        services.add(Services(2, "stroi","Уборка после строительства","Удаление после строительной пыли и следов ремонта",500))
        services.add(Services(3, "supportclin","Поддерживающая уборка ","Тип удаляемых загрязнений: легкие",250))
        services.add(Services(4, "windowsclin","Мойка окон любой сложности","Двухстворчатое окно с обеих сторон (рамы и подоконники)",100))

        ServiceList.layoutManager = LinearLayoutManager(this)
        ServiceList.adapter = ServicesAdapter(services,this)

        }
    }