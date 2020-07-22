
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>

#include "security/DigestHelper.h"
#include "Base.h"
#include "file/ZlibHelper.h"
#include "file/FileHelper.h"
#include "AndroidHelper.h"

extern "C" {

jstring sslVersion(JNIEnv *env, jobject /* this */) {
    return env->NewStringUTF(OPENSSL_VERSION_TEXT);
}


jstring strMd5(JNIEnv *env, jobject /* this */, jstring inputStr) {
    const char *input = env->GetStringUTFChars(inputStr, JNI_FALSE);
    jsize inputLen = env->GetStringLength(inputStr);
    std::string resultCString = DigestHelper::strMd5(input, inputLen);

    jstring result = AndroidHelper::charToJString(env, (char *) resultCString.c_str(),
                                                  resultCString.size());
    env->ReleaseStringUTFChars(inputStr, input);
    return result;
}

jstring fileMd5(JNIEnv *env, jobject /* this */, jstring fileName) {
    const char *input = env->GetStringUTFChars(fileName, JNI_FALSE);
    std::string resultCString = DigestHelper::fileMd5(input);

    jstring result = AndroidHelper::charToJString(env, (char *) resultCString.c_str(),
                                                  resultCString.size());
    env->ReleaseStringUTFChars(fileName, input);
    return result;
}


jstring strSha1(JNIEnv *env, jobject /* this */, jstring inputStr) {
    const char *input = env->GetStringUTFChars(inputStr, JNI_FALSE);
    jsize inputLen = env->GetStringLength(inputStr);
    std::string resultCString = DigestHelper::strSha1(input, inputLen);

    jstring result = AndroidHelper::charToJString(env, (char *) resultCString.c_str(),
                                                  resultCString.size());
    env->ReleaseStringUTFChars(inputStr, input);
    return result;
}

jstring fileSha1(JNIEnv *env, jobject /* this */, jstring fileName) {
    const char *input = env->GetStringUTFChars(fileName, JNI_FALSE);
    std::string resultCString = DigestHelper::fileSha1(input);

    jstring result = AndroidHelper::charToJString(env, (char *) resultCString.c_str(),
                                                  resultCString.size());
    env->ReleaseStringUTFChars(fileName, input);
    return result;
}

//解压
jboolean unZip(JNIEnv *env, jobject /* this */, jstring fileName, jstring targetDir) {
    std::string oriFile = AndroidHelper::jstringToString(env, fileName);
    std::string outDir = AndroidHelper::jstringToString(env, targetDir);
    ZlibHelper::decompress(oriFile, outDir);

    //LOGI("zlib version : %s", zlibVersion());
    return static_cast<jboolean>(true);
}

jboolean unAssetZip(JNIEnv *env, jobject /* this */, jobject assetManager, jstring fileName,
                    jstring targetDir) {
    std::string oriFile = AndroidHelper::jstringToString(env, fileName);
    std::string outDir = AndroidHelper::jstringToString(env, targetDir);

    //1.copy
    std::string path = FileHelper::copy(env, assetManager, oriFile.c_str(), outDir.c_str(),
                                        oriFile.c_str());

    //2.unzip
    ZlibHelper::decompress(path, outDir);

    FileHelper::rm(path.c_str());

    //LOGI("zlib version : %s", zlibVersion());
    return static_cast<jboolean>(true);
}


extern void *
functionToBlur(JNIEnv *env, jclass clzz, jobject bitmapOut, jint radius, jint threadCount,
               jint threadIndex, jint round);
}


void *stack_blur(JNIEnv *env, jclass clzz, jobject bitmapOut, jint radius, jint threadCount,
                 jint threadIndex) {
    //LOGE("stack_blur");
    functionToBlur(env, clzz, bitmapOut, radius, threadCount, threadIndex, 1);//horizontal
    functionToBlur(env, clzz, bitmapOut, radius, threadCount, threadIndex, 2);//vertical
}


#define RNN(env, className, gMethods)   clazz = env->FindClass(className);\
                                        if (!clazz) {\
                                            LOGE("JNI_OnLoad->FindClass (%s) error!",className);\
                                            return -1;\
                                        }\
                                        if (env->RegisterNatives(clazz, gMethods, sizeof(gMethods) / sizeof(gMethods[0])) < 0) {\
                                            LOGE("JNI_OnLoad->RegisterNatives error!");\
                                            return -1;\
                                        }\

const char *className = "com/qfleng/cryptokit/NativeHelper";

static JNINativeMethod methods[] = {
        {"sslVersion", "()Ljava/lang/String;",                                                      (void *) sslVersion},
        {"strMd5",     "(Ljava/lang/String;)Ljava/lang/String;",                                    (void *) strMd5},
        {"fileMd5",    "(Ljava/lang/String;)Ljava/lang/String;",                                    (void *) fileMd5},
        {"strSha1",    "(Ljava/lang/String;)Ljava/lang/String;",                                    (void *) strSha1},
        {"fileSha1",   "(Ljava/lang/String;)Ljava/lang/String;",                                    (void *) fileSha1},
        {"unZip",      "(Ljava/lang/String;Ljava/lang/String;)Z",                                   (void *) unZip},
        {"unZip",      "(Landroid/content/res/AssetManager;Ljava/lang/String;Ljava/lang/String;)Z", (void *) unAssetZip},
        {"stack_blur", "(Landroid/graphics/Bitmap;III)V",                                           (void *) stack_blur}
};


jint JNI_OnLoad(JavaVM *vm, void *reserved) {

    jclass clazz = NULL;
    JNIEnv *env = NULL;

    if (vm->GetEnv((void **) &env, JNI_VERSION_1_6) != JNI_OK) {
        LOGE("JNI_OnLoad->GetEnv error!");
        return -1;
    }

    RNN(env, className, methods)

    return JNI_VERSION_1_6;
}