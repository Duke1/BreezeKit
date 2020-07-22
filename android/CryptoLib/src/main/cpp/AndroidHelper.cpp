//
// Created by Duke
//

#include "AndroidHelper.h"


jstring AndroidHelper::charToJString(JNIEnv *env, char *data, size_t len) {
    jbyteArray array = env->NewByteArray(len);
    env->SetByteArrayRegion(array, 0, len, (const jbyte *) data);
    jstring strEncode = env->NewStringUTF("UTF-8");
    jclass cls = env->FindClass("java/lang/String");
    jmethodID ctor = env->GetMethodID(cls, "<init>", "([BLjava/lang/String;)V");
    jstring object = (jstring) env->NewObject(cls, ctor, array, strEncode);
    return object;
}

std::string AndroidHelper::jstringToString(JNIEnv *env, jstring inputStr) {

    const char *tmpChar = env->GetStringUTFChars(inputStr, JNI_FALSE);
    std::string result(tmpChar);
    env->ReleaseStringUTFChars(inputStr, tmpChar);

    return result;
}