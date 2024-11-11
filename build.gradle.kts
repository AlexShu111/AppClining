plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.appforfun"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.appforfun"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        externalNativeBuild {
            cmake {
                cppFlags += ""
            }
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    externalNativeBuild {
        cmake {
            path ("C:\\Android\\Mine\\app\\src\\main\\java\\com\\example\\appforfun\\CMakeLists.txt")
            version = "3.22.1"
        }
    }
}

dependencies {

    implementation (libs.ucrop.v228)

    implementation (libs.android.image.cropper)
    implementation(libs.picasso)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.coil.compose)

    implementation(libs.glide)

    implementation(libs.kotlinx.coroutines.android)

    annotationProcessor(libs.compiler)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}