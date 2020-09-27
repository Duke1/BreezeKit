//
//

#ifndef BREEZEKIT_CVHELPER_H
#define BREEZEKIT_CVHELPER_H

#include "opencv2/core.hpp"
#include <opencv2/opencv.hpp>
#include "opencv2/imgproc.hpp"
#include <android/bitmap.h>
#include <string>

#include "../../../../CryptoLib/src/main/cpp/Base.h"

class CvHelper {
public:
    static std::vector<int> convertJintArrayToVector(JNIEnv *env, jintArray in) {
        std::vector<int> out;
        int len = env->GetArrayLength(in);
        jint *inArray = env->GetIntArrayElements(in, 0);
        for (int i = 0; i < len; i++) {
            out.push_back(inArray[i]);
        }
        env->ReleaseIntArrayElements(in, inArray, 0);
        return out;
    }

};

#endif //BREEZEKIT_CVHELPER_H
