
#include "DigestHelper.h"
#include <fstream>
#include "../Base.h"

std::string DigestHelper::fileSha1(const char *filePath) {
  SHA_CTX ctx;
  unsigned char out_value[20] = {0};
  char tmp[3] = {0};
  std::string result;
  int i;

  SHA1_Init(&ctx);

  int bufferLen = 1024;
  char *buffer = new char[bufferLen];

  std::ifstream is(filePath, std::ifstream::binary);
  if (is.is_open()) {
    while (!is.eof()) {
      is.read(buffer, bufferLen);
      size_t count = is.gcount();
//      LOGE("read count : %d", count);
      SHA1_Update(&ctx, buffer, count);
    }
  }
  is.close();
  delete[] buffer;

  SHA1_Final(out_value, &ctx);

  for (i = 0; i < 20; i++) {
    sprintf(tmp, "%02x", out_value[i]);
    result.append(tmp);
  }

  return result;
}
std::string DigestHelper::fileMd5(const char *filePath) {
  MD5_CTX ctx;
  unsigned char out_value[16] = {0};
  char tmp[3] = {0};
  std::string result;
  int i;

  MD5_Init(&ctx);

  int bufferLen = 1024;
  char *buffer = new char[bufferLen];

  //    FILE *pFile = fopen(filePath, "rb");
  //    size_t len = 0;
  //    while ((len = fread(buffer, 1, 1024, pFile)) > 0) {
  //        MD5_Update(&ctx, buffer, len);
  //        LOGE("read count : %d", len);
  //    }
  //    fclose(pFile);


  std::ifstream is(filePath, std::ifstream::binary);
  if (is.is_open()) {
    while (!is.eof()) {
      is.read(buffer, bufferLen);
      size_t count = is.gcount();
//      LOGE("read count : %d", count);
      MD5_Update(&ctx, buffer, count);
    }
  }
  is.close();
  delete[] buffer;

  MD5_Final(out_value, &ctx);

  for (i = 0; i < 16; i++) {
    sprintf(tmp, "%02x", out_value[i]);
    //        strcat(buff, tmp);
    result.append(tmp);
  }
  //printf("%s\n",buff);


  return result;
}
std::string DigestHelper::strMd5(const char *input, size_t len) {
  MD5_CTX ctx;
  unsigned char out_value[16] = {0};
  char tmp[3] = {0};
  std::string result;
  int i;

  MD5_Init(&ctx);

  MD5_Update(&ctx, input, len);

  MD5_Final(out_value, &ctx);

  for (i = 0; i < 16; i++) {
    sprintf(tmp, "%02x", out_value[i]);
    //        strcat(buff, tmp);
    result.append(tmp);
  }
  //printf("%s\n",buff);


  return result;
}
std::string DigestHelper::strSha1(const char *input, size_t len) {
  SHA_CTX ctx;
  unsigned char out_value[20] = {0};
  char tmp[3] = {0};
  std::string result;
  int i;

  SHA1_Init(&ctx);

  SHA1_Update(&ctx, input, len);

  SHA1_Final(out_value, &ctx);

  for (i = 0; i < 20; i++) {
    sprintf(tmp, "%02x", out_value[i]);
    result.append(tmp);
  }

  return result;
}
