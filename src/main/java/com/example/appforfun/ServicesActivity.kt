package com.example.appforfun

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ServicesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_services)



        val serviceList: RecyclerView = findViewById(R.id.CleaningList)
        val services = arrayListOf<Services>()
        services.add(Services(1, "genclin","Генеральная уборка","Тип удаляемых загрязнений: максимально возможные",
                "Генеральная уборка — это услуга, которая включает:\n" +
                "\n" +
                "   1. Уборка пустых шкафов, комодов, полок, тумб и др. мебели;\n" +
                "   2. Сухая или локальная влажная уборка стен;\n" +
                "   3. Тщательная сухая чистка мягкой мебели;\n" +
                "   4. Полив цветов по просьбе заказчика;\n" +
                "   5. Мытье плафонных люстр, светильников, торшеров;\n" +
                "   6. Мытье пола, в том числе в труднодоступных местах;\n" +
                "   7. Помывка батарей, труб, другого вида отопительных элементов;\n" +
                "   8. Чистка зеркал.\n",500))
        services.add(Services(2, "stroi","Уборка после строительства","Удаление после строительной пыли и следов ремонта",
            "Уборка после строительства — это услуга, которая включает:\n" +
                "    1. Избавление от отходов стройки. Заказать вывоз строительного мусора несложно. Для этого имеется специальная техника, которая доставит его в места, отведенные для данного вида отходов.\n" +
                "    2. Очищение поверхностей и предметов от пыли и грязи. В первую очередь чистятся потолки, затем убираются стены. Окрашенный или оклеенный моющими обоями вариант протирается влажными салфетками. После этого идет очистка плинтусов и радиаторов.\n" +
                "    3. Удаление следов краски, клея, монтажной пены. Для каждого типа поверхности необходимо использовать особые средства, способные справиться с работой эффективно. И клинеры об этом проинформированы.\n" +
                "    4. Мытье полов. После удаления грязи производится натирание их специальными составами.\n" +
                "    5. Чистка окон и зеркал. Оконные рамы и стекла очищаются с наружной и внутренней стороны. Вдобавок удаляется грязь с откосов и подоконников.\n" +
                "    6. Удаление неприятных запахов. Запахи после ремонта и строительную пыль убирают с помощью специального устройства. Климатическая установка способна не только произвести очистку воздуха, но и избавиться от вирусов и бактерий.\n",500))
        services.add(Services(3, "supportclin","Поддерживающая уборка ","Тип удаляемых загрязнений: легкие",
            "Поддерживающая уборка — это услуга, которая включает:\n"+
                "    1. Сухая уборка пылесосом;\n" +
                "    2. Влажное очищение полов;\n" +
                "    3. Очищение паласов и ковров;\n" +
                "    4. Протирание дверей и зеркал;\n" +
                "    5. Устранение пыли и грязи с поверхностей;\n" +
                "    6. Влажная чистка корпусной мебели;\n" +
                "    7. Сухая чистка мягкой мебели;\n" +
                "    8. Аналогичные виды работ.\n",250))
        services.add(Services(4, "windowsclin","Мойка окон любой сложности","Двухстворчатое окно с обеих сторон (рамы и подоконники)",
            "Мойка окон любой сложности — это услуга, которая включает:\n"+
                "    1. Мытье стекла или стеклянной конструкции;\n" +
                "    2. Мойка подоконника;\n" +
                "    3. Мойка отлива;\n" +
                "    4. Удаление грязи с механизмов окна;\n" +
                "    5. Мойка фурнитуры и ручек.\n",100))

        serviceList.layoutManager = LinearLayoutManager(this)
        serviceList.adapter = ServicesAdapter(services,this)

        val profLogin = intent.getStringExtra("USER_LOGIN")

//        val adapter = ServicesAdapter(services, this)
//        if (profLogin != null) {
//            adapter.setProfLog(profLogin)
//        }
        val buttonProfSer: ImageButton = findViewById(R.id.imageButtonToProfileFromServices)


        buttonProfSer.setOnClickListener{
            val intent = Intent(this, ActivityProfile::class.java)
            intent.putExtra("USER_LOGIN", profLogin)
            startActivity(intent)
        }
        }
    }