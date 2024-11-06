package com.example.appforfun

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import android.net.Uri
import android.content.Intent
import com.yalantis.ucrop.UCrop
import java.io.File


class ActivityProfile : AppCompatActivity() {
    private lateinit var imageBut: ImageButton
    private val sharedPrefName = "profile_prefs"
    private val imageKey = "profile_image_uri"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_profile)


        val log: TextView = findViewById(R.id.Log)
        val em: TextView = findViewById(R.id.Email)
        val buttonToServList: Button = findViewById(R.id.ButFromProfToServ)
        val profLogin: TextView = findViewById(R.id.ProfLogin)
        val profEmail: TextView = findViewById(R.id.ProfEmail)
        imageBut = findViewById(R.id.ButtonForPhoto)


        log.text = "Логин:"
        em.text = "Почта:"

        profLogin.text = intent.getStringExtra("USER_LOGIN")
        val db = DataHelper(this, null)
        profEmail.text = db.getEmail(profLogin.text.toString())

        buttonToServList.setOnClickListener {
            val intent = Intent(this, ServicesActivity::class.java)
            intent.putExtra("USER_LOGIN", profLogin.text)
            startActivity(intent)
        }
        loadSavedImage()

        val getContent = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri: Uri? = result.data?.data
                if (selectedImageUri != null) {
                    // Создание URI для сохранения обрезанного изображения
                    val destinationUri = Uri.fromFile(File(cacheDir, "croppedImage.jpg"))
                    // Использование UCrop для обрезки изображения
                    UCrop.of(selectedImageUri, destinationUri)
                        .withAspectRatio(1f, 1f) // Соотношение сторон 1:1
                        .start(this)
                }
            }
        }
        imageBut.setOnClickListener{
            // Создание Intent для открытия галереи
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            // Запуск активности для результата через новый API
            getContent.launch(intent)
        }

    }
    @Deprecated("This method has been deprecated in favor of using the Activity Result API\n      which brings increased type safety via an {@link ActivityResultContract} and the prebuilt\n      contracts for common intents available in\n      {@link androidx.activity.result.contract.ActivityResultContracts}, provides hooks for\n      testing, and allow receiving results in separate, testable classes independent from your\n      activity. Use\n      {@link #registerForActivityResult(ActivityResultContract, ActivityResultCallback)}\n      with the appropriate {@link ActivityResultContract} and handling the result in the\n      {@link ActivityResultCallback#onActivityResult(Object) callback}.")
    // Обработка результата обрезки изображения
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            if (requestCode == UCrop.REQUEST_CROP) {
                val resultUri = UCrop.getOutput(data!!)
                if (resultUri != null) {
                    // Установка изображения в ImageView и сохранение URI
                    imageBut.setImageURI(resultUri)
                    saveImageUri(resultUri) // Сохранение нового URI
                }
            }
        } else if (resultCode == UCrop.RESULT_ERROR) {
            val cropError = UCrop.getError(data!!)
            cropError?.printStackTrace() // Обработка ошибок обрезки
        }
    }

    // Сохранение URI изображения в SharedPreferences
    private fun saveImageUri(uri: Uri) {
        val sharedPref = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString(imageKey, uri.toString())
            apply()
        }
    }

// Загружает изображение при создании активности
    private fun loadSavedImage() {
        val sharedPref = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
        val imageUriString = sharedPref.getString(imageKey, null)
        if (imageUriString != null) {
            val imageUri = Uri.parse(imageUriString)
            imageBut.setImageURI(imageUri) // Установка сохраненного изображения в ImageView
        }
    }

}

