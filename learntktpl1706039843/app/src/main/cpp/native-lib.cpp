#include <string.h>
#include <stdio.h>
#include <jni.h>

extern "C"
JNIEXPORT jint JNICALL Java_id_ac_ui_cs_learn_1tktpl_11706039843_Counter_incrementN( JNIEnv* env, jobject thiz,  jint x)
{
    return x+1;
}

extern "C"
JNIEXPORT jint JNICALL Java_id_ac_ui_cs_learn_1tktpl_11706039843_Counter_decrementN( JNIEnv* env, jobject thiz,  jint y)
{
    return y-1;
}