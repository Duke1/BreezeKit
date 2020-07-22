
#ifndef BREEZE_ZLIBHELPER_H
#define BREEZE_ZLIBHELPER_H

#include "../Base.h"

#include <string>
#include <zlib.h>
#include "ioapi.h"
#include "unzip.h"

#include <sys/stat.h>
#include <sys/types.h>


class ZlibHelper {
public:

    static int writeData(unzFile &zipfile, const char *destPathName);

    static int decompress(std::string zipFilePath, std::string strFolder);

};


#endif //BREEZE_ZLIBHELPER_H
