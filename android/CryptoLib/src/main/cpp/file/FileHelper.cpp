
#include "FileHelper.h"


using namespace std;

string
FileHelper::copy(JNIEnv *env, jobject assetManager, const char *filename, const char *targetDir,
                 const char *targetName) {
    string targetFileName;
    targetFileName.append(targetDir);
    targetFileName.append("/");
    targetFileName.append(targetName);

    if (!opendir(targetDir)) {
        createDir(targetDir);
    }

    AAssetManager *mgr = AAssetManager_fromJava(env, assetManager);
    AAsset *asset = AAssetManager_open(mgr, filename, AASSET_MODE_UNKNOWN);
    ofstream dst(targetFileName, ios::binary);

    //size_t bufSize = AAsset_getLength(asset);
    const size_t bufSize = 1024;
    char *buffer = (char *) malloc(sizeof(char) * bufSize);
    int readSize = 0;
    while ((readSize = AAsset_read(asset, buffer, bufSize))) {
        dst.write(buffer, readSize);
    }

    dst.flush();
    dst.close();

    AAsset_close(asset);
    free(buffer);


    return targetFileName;
}
