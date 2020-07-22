//
// Created by Duke
//

#ifndef CVIMAGE_ANDROIDHELPER_H
#define CVIMAGE_ANDROIDHELPER_H

#include "Base.h"
#include <string>

class AndroidHelper {
public:
    static jstring charToJString(JNIEnv *env, char *data, size_t len);

    static std::string jstringToString(JNIEnv *env, jstring inputStr);
};


#endif //CVIMAGE_ANDROIDHELPER_H
