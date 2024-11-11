#include <jni.h>
#include <stdio.h>
#include <string>

// Функция, которая возвращает пустую строку
extern "C" JNIEXPORT jstring JNICALL
Java_com_example_appforfun_MainActivity_clearText(JNIEnv *env, jobject obj) {
    return env->NewStringUTF(""); // Возвращает пустую строку
}
