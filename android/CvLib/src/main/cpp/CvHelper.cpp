
#include "CvHelper.h"

using namespace cv;


extern "C" {
JNIEXPORT jstring JNICALL Java_com_qfleng_cvkit_CvHelper_nCvVersion(JNIEnv *env, jclass) {
    return env->NewStringUTF(CV_VERSION);
}


JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nBitmapToMat2
        (JNIEnv *env, jclass, jobject bitmap, jlong m_addr, jboolean needUnPremultiplyAlpha) {
    AndroidBitmapInfo info;
    void *pixels = 0;
    Mat &dst = *((Mat *) m_addr);

    try {
        LOGD("nBitmapToMat");
        CV_Assert(AndroidBitmap_getInfo(env, bitmap, &info) >= 0);
        CV_Assert(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888 ||
                  info.format == ANDROID_BITMAP_FORMAT_RGB_565);
        CV_Assert(AndroidBitmap_lockPixels(env, bitmap, &pixels) >= 0);
        CV_Assert(pixels);
        dst.create(info.height, info.width, CV_8UC4);
        if (info.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
            LOGD("nBitmapToMat: RGBA_8888 -> CV_8UC4");
            Mat tmp(info.height, info.width, CV_8UC4, pixels);
            if (needUnPremultiplyAlpha) cvtColor(tmp, dst, COLOR_mRGBA2RGBA);
            else {
                cvtColor(tmp, dst, COLOR_RGBA2BGRA);
                //tmp.copyTo(dst);//copyTo之后颜色不对，用cvtColor转换
            }
        } else {
            // info.format == ANDROID_BITMAP_FORMAT_RGB_565
            LOGD("nBitmapToMat: RGB_565 -> CV_8UC4");
            Mat tmp(info.height, info.width, CV_8UC2, pixels);
            cvtColor(tmp, dst, COLOR_BGR5652RGBA);
        }
        AndroidBitmap_unlockPixels(env, bitmap);
        return;
    } catch (const cv::Exception &e) {
        AndroidBitmap_unlockPixels(env, bitmap);
        LOGE("nBitmapToMat caught cv::Exception: %s", e.what());
        jclass je = env->FindClass("com/qfleng/cvkit/cv/CvException");
        if (!je) je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, e.what());
        return;
    } catch (...) {
        AndroidBitmap_unlockPixels(env, bitmap);
        LOGE("nBitmapToMat caught unknown exception (...)");
        jclass je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, "Unknown exception in JNI code {nBitmapToMat}");
        return;
    }
}

// old signature is left for binary compatibility with 2.4.0 & 2.4.1, to removed in 2.5
JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nBitmapToMat
        (JNIEnv *env, jclass, jobject bitmap, jlong m_addr) {
    Java_com_qfleng_cvkit_CvHelper_nBitmapToMat2(env, 0, bitmap, m_addr, false);
}


JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nMatToBitmap2
        (JNIEnv *env, jclass, jlong m_addr, jobject bitmap, jboolean needPremultiplyAlpha) {
    AndroidBitmapInfo info;
    void *pixels = 0;
    Mat &src = *((Mat *) m_addr);

    try {
        LOGD("nMatToBitmap");
        CV_Assert(AndroidBitmap_getInfo(env, bitmap, &info) >= 0);
        CV_Assert(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888 ||
                  info.format == ANDROID_BITMAP_FORMAT_RGB_565);
        //LOGE("dims:%d   info.height:%d  info.width:%d   src.rows:%d   src.cols:%d", src.dims, info.height, info.width, (uint32_t)src.rows, (uint32_t)src.cols);
        CV_Assert(src.dims == 2 && info.height == (uint32_t) src.rows &&
                  info.width == (uint32_t) src.cols);

        CV_Assert(src.type() == CV_8UC1 || src.type() == CV_8UC3 || src.type() == CV_8UC4);
        CV_Assert(AndroidBitmap_lockPixels(env, bitmap, &pixels) >= 0);
        CV_Assert(pixels);

        if (info.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
            Mat tmp(info.height, info.width, CV_8UC4, pixels);

            if (src.type() == CV_8UC1) {
                LOGD("nMatToBitmap: CV_8UC1 -> RGBA_8888");
                cvtColor(src, tmp, COLOR_GRAY2RGBA);
            } else if (src.type() == CV_8UC3) {
                LOGD("nMatToBitmap: CV_8UC3 -> RGBA_8888");
                //OpenCv 为BGR
                cvtColor(src, tmp, COLOR_BGR2RGBA);
            } else if (src.type() == CV_8UC4) {
                LOGD("nMatToBitmap: CV_8UC4 -> RGBA_8888");
                if (needPremultiplyAlpha) cvtColor(src, tmp, COLOR_RGBA2mRGBA);
                else {
                    cvtColor(src, tmp, COLOR_BGRA2RGBA);
                    //src.copyTo(tmp);
                }
            }

        } else {
            // info.format == ANDROID_BITMAP_FORMAT_RGB_565
            Mat tmp(info.height, info.width, CV_8UC2, pixels);
            if (src.type() == CV_8UC1) {
                LOGD("nMatToBitmap: CV_8UC1 -> RGB_565");
                cvtColor(src, tmp, COLOR_GRAY2BGR565);
            } else if (src.type() == CV_8UC3) {
                LOGD("nMatToBitmap: CV_8UC3 -> RGB_565");
                cvtColor(src, tmp, COLOR_RGB2BGR565);
            } else if (src.type() == CV_8UC4) {
                LOGD("nMatToBitmap: CV_8UC4 -> RGB_565");
                cvtColor(src, tmp, COLOR_RGBA2BGR565);
            }
        }

        AndroidBitmap_unlockPixels(env, bitmap);
        return;
    } catch (const cv::Exception &e) {
        AndroidBitmap_unlockPixels(env, bitmap);
        LOGE("nMatToBitmap caught cv::Exception: %s", e.what());
        jclass je = env->FindClass("com/qfleng/cvkit/cv/CvException");
        if (!je) je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, e.what());
        return;
    } catch (...) {
        AndroidBitmap_unlockPixels(env, bitmap);
        LOGE("nMatToBitmap caught unknown exception (...)");
        jclass je = env->FindClass("java/lang/Exception");
        env->ThrowNew(je, "Unknown exception in JNI code {nMatToBitmap}");
        return;
    }
}


JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nMatToBitmap
        (JNIEnv *env, jclass, jlong m_addr, jobject bitmap) {
    Java_com_qfleng_cvkit_CvHelper_nMatToBitmap2(env, 0, m_addr, bitmap, false);
}

JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nCvtColor
        (JNIEnv *env, jclass, jlong src_addr, jlong dst_addr, jint code, jint dstCn) {
    Mat &src = *((Mat *) src_addr);
    Mat &dst = *((Mat *) dst_addr);
    cvtColor(src, dst, code, dstCn);
}

JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nFlip
        (JNIEnv *env, jclass, jlong src_addr, jint flipCode) {
    Mat &src = *((Mat *) src_addr);
    transpose(src, src);
    flip(src, src, 1);
}


JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nSaveImage
        (JNIEnv *env, jclass, jlong src_addr, jstring path, jint quality, jint imwriteFlags) {
    Mat &input_image = *((Mat *) src_addr);
    const char *jpath = env->GetStringUTFChars(path, 0);

    std::vector<int> compression_params;
    //ImwriteFlags
    compression_params.push_back(imwriteFlags);//IMWRITE_JPEG_QUALITY
    compression_params.push_back(quality);

    imwrite(jpath, input_image, compression_params);

    env->ReleaseStringUTFChars(path, jpath);
}


JNIEXPORT jlong JNICALL Java_com_qfleng_cvkit_CvHelper_nCreateMat
        (JNIEnv *env, jclass, jstring strData) {
    const char *data = env->GetStringUTFChars(strData, 0);

    Mat mat = imread(data, IMREAD_ANYCOLOR);

    env->ReleaseStringUTFChars(strData, data);
    Mat *result = new Mat(mat);

    return (jlong) result;
}

JNIEXPORT jlong JNICALL Java_com_qfleng_cvkit_CvHelper_nThreshold
        (JNIEnv *env, jclass, jlong src_addr, jint thresh) {
    Mat &input_image = *((Mat *) src_addr);

    Mat *result = new Mat();

    if (CV_8UC4 == input_image.type()) {
        cvtColor(input_image, *result, COLOR_BGR2GRAY);
    } else {
        input_image.copyTo(*result);
    }

    threshold(*result, *result, thresh, 255.0, THRESH_BINARY);

    return (jlong) result;
}

JNIEXPORT jlong JNICALL Java_com_qfleng_cvkit_CvHelper_nAdaptiveThreshold
        (JNIEnv *env, jclass, jlong src_addr, jint blockSize, jdouble C) {
    Mat &input_image = *((Mat *) src_addr);

    Mat *result = new Mat();

    if (CV_8UC4 == input_image.type()) {
        cvtColor(input_image, *result, COLOR_BGR2GRAY);
    } else {
        input_image.copyTo(*result);
    }

    adaptiveThreshold(*result, *result, 255.0, ADAPTIVE_THRESH_GAUSSIAN_C, THRESH_BINARY, blockSize,
                      C);

    return (jlong) result;
}

//https://stackoverflow.com/questions/22041699/rotate-an-image-without-cropping-in-opencv-in-c
JNIEXPORT jlong JNICALL Java_com_qfleng_cvkit_CvHelper_nRotation
        (JNIEnv *env, jclass, jlong src_addr, jint angle) {

    Mat &src = *((Mat *) src_addr);

    // get rotation matrix for rotating the image around its center in pixel coordinates
    Point2f center((src.cols - 1) / 2.0, (src.rows - 1) / 2.0);
    Mat rot = getRotationMatrix2D(center, angle, 1.0);
    // determine bounding rectangle, center not relevant
    Rect2f bbox = RotatedRect(Point2f(), src.size(), angle).boundingRect2f();
    // adjust transformation matrix
    rot.at<double>(0, 2) += bbox.width / 2.0 - src.cols / 2.0;
    rot.at<double>(1, 2) += bbox.height / 2.0 - src.rows / 2.0;

    Mat *dst = new Mat();
    warpAffine(src, *dst, rot, bbox.size(), INTER_LINEAR, BORDER_CONSTANT,
               Scalar(255, 255, 255));


    return (jlong) dst;
}


JNIEXPORT void JNICALL Java_com_qfleng_cvkit_CvHelper_nRepair
        (JNIEnv *env, jclass, jlong src_addr, jdouble thresh, jlong mask_addr,
         jintArray maskPositions) {

    Mat &src = *((Mat *) src_addr);
    Mat target;

    //[startX,startY,endX,endY]
    std::vector<int> maskPVector = CvHelper::convertJintArrayToVector(env, maskPositions);
    //有坐标数组时，按区域修复；没有，则为全图修复
    if (4 == maskPVector.size()) {
        Point pStart = Point(maskPVector[0], maskPVector[1]);
        Point pEnd = Point(maskPVector[2], maskPVector[3]);

        rectangle(src, pStart, pEnd, Scalar(255, 0, 0), LINE_8, 0);

        target = src(Rect(pStart, pEnd));
    } else {
        target = src;
    }

    Mat *imageMask;
    if (0 == mask_addr) {
        imageMask = new Mat(target.size(), CV_8UC1, Scalar::all(0));
    } else {
        imageMask = ((Mat *) mask_addr);
    }

    Mat imageGray;
    //转换为灰度图
    if (CV_8UC1 == target.type()) {
        imageGray = target.clone();
    } else {
        cvtColor(target, imageGray, COLOR_RGB2GRAY, 0);
    }

    //通过阈值处理生成Mask。修复区纯白，thresh建议值：235
    threshold(imageGray, *imageMask, thresh, 255, THRESH_BINARY);
    Mat Kernel = getStructuringElement(MORPH_RECT, Size(3, 3));
    //对Mask膨胀处理，增加Mask面积
    dilate(*imageMask, *imageMask, Kernel);

    inpaint(target, *imageMask, target, 5, INPAINT_TELEA);


    if (0 == mask_addr)
        imageMask->release();

}


} // extern "C"

