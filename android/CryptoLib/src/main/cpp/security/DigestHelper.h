
#ifndef BREEZE_DIGESTUTIL_H
#define BREEZE_DIGESTUTIL_H

#include <string>
#include <openssl/md5.h>
#include <openssl/sha.h>
#include <openssl/crypto.h>

class DigestHelper {
public:
    static std::string fileSha1(const char *filePath);

    static std::string fileMd5(const char *filePath);

    static std::string strMd5(const char *input, size_t len);

    static std::string strSha1(const char *input, size_t len);

    static const char *getSslVersion() {
        return SSLeay_version(SSLEAY_VERSION);
    };

};

#endif //BREEZE_DIGESTUTIL_H
