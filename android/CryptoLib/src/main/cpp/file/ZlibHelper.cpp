
#include "ZlibHelper.h"

#include "FileHelper.h"

int ZlibHelper::writeData(unzFile &zipfile, const char *destPathName) {
    if (destPathName == NULL) {
        LOGE("name is null/n");
        return -1;
    }
//        if (createDir(destPathName) != 0) {
//            LOGE("[writeData] create dir failed . path = %s/n", destPathName);
//            return -1;
//        }
    // 删除以.开头的文件
    std::string s_path = destPathName;
    int index = (int) s_path.find_last_of("/");
    std::string name = s_path.substr(index + 1);
    if (name.find(".") == 0) return 0;
    FILE *fp = fopen(destPathName, "wb");
    if (fp == NULL) {
        LOGE("open file failed. destPathName = %s/n", destPathName);
        return -1;
    }
    char read_buffer[1024] = {0};
    int error = UNZ_OK;
    do {
        error = unzReadCurrentFile(zipfile, read_buffer, 1024);
        if (error < 0) {
            LOGE("[unzReadCurrentFile] error = %d/n", error);
            return -1;
        }
        if (error > 0) {
            fwrite(read_buffer, error, 1, fp);
        }
    } while (error > 0);
    fclose(fp);
    fp = NULL;
    return 0;
}

int ZlibHelper::decompress(std::string zipFilePath, std::string strFolder) {
    // 1. open zip
    unzFile zipfile = unzOpen64(zipFilePath.c_str());
    if (zipfile == NULL) {
        LOGE("open zip failed , path = %s", zipFilePath.c_str());
        return -1;
    }
    // 2. get global info
    unz_global_info64 global_info;
    if (unzGetGlobalInfo64(zipfile, &global_info) != UNZ_OK) {
        unzClose(zipfile);
        LOGE("get global info failed/n");
        return -1;
    }
    // 3. loop files
    for (uLong i = 0; i < global_info.number_entry; ++i) {
        unz_file_info64 file_info64;
        char filename[1024] = {0};
        unzGetCurrentFileInfo64(zipfile, &file_info64, filename, sizeof(filename), NULL, 0,
                                NULL, 0);
        const size_t filename_length = strlen(filename);
        char name[1024] = {0};
        if (filename[filename_length - 1] == '/') {// make folder
            sprintf(name, "%s/%s", strFolder.c_str(), filename);
            FileHelper::createDir(name);
        } else {
            if (unzOpenCurrentFile(zipfile) != UNZ_OK) {
                unzClose(zipfile);
                LOGE("open current file failed/n");
                return -1;
            }
            sprintf(name, "%s/%s", strFolder.c_str(), filename);
            if (writeData(zipfile, name) < 0) {
                unzCloseCurrentFile(zipfile);
                unzClose(zipfile);
                LOGE("wtite data into file failed/n");
                return -1;
            }
            // to set file time
        }
        unzCloseCurrentFile(zipfile);
        if ((i + 1) < global_info.number_entry && unzGoToNextFile(zipfile) != UNZ_OK) {
            unzClose(zipfile);
            LOGE("go to next failed/n");
            return -1;
        }
    }
    unzClose(zipfile);
    return 0;
}
