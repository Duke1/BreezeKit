
#ifndef BREEZE_FILEHELPER_H
#define BREEZE_FILEHELPER_H

#include "../Base.h"

#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>

#include <string>
#include <sys/stat.h>
#include <sys/types.h>
#include <iostream>
#include <fstream>
#include <dirent.h>

class FileHelper {
public:
    static std::string
    copy(JNIEnv *env, jobject assetManager, const char *filename, const char *targetDir,
         const char *targetName);

    static int rm(const char *path) {
        return remove(path);
    }

    static int createDir(const char *path) {
        return mkdir(path, S_IRUSR | S_IWUSR | S_IXUSR | S_IRWXG | S_IRWXO);
    }
};


#endif //BREEZE_FILEHELPER_H
