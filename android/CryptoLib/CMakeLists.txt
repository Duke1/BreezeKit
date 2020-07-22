
cmake_minimum_required(VERSION 3.10.2)

#add_compile_options(-std=c++11)
add_compile_options(-Wno-narrowing)
add_definitions(-DNO_LOCALE_SUPPORT)


#zlib 配置
add_definitions(-DIOAPI_NO_64)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

SET(NATIVE_CPP_PATH ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp)
SET(TR_3RDPARTY_PATH ${CMAKE_CURRENT_SOURCE_DIR}/../..)



find_library(log-lib log)

add_library(au_crypto_kit
        SHARED
        src/main/cpp/blur.c
        src/main/cpp/file/FileHelper.cpp
        src/main/cpp/file/ioapi.c
        src/main/cpp/file/unzip.c
        src/main/cpp/file/zip.c
        src/main/cpp/file/ZlibHelper.cpp
        src/main/cpp/security/DigestHelper.cpp
        src/main/cpp/AndroidHelper.cpp
        src/main/cpp/NativeMain.cpp
        )


target_link_libraries(
        au_crypto_kit
        ${log-lib}
        android
        jnigraphics
        dl
        z
        ${TR_3RDPARTY_PATH}/boringssl/lib/${ANDROID_NDK_ABI_NAME}/libcrypto.a
        ${TR_3RDPARTY_PATH}/boringssl/lib/${ANDROID_NDK_ABI_NAME}/libssl.a
)


include_directories(
        ${NATIVE_CPP_PATH}
        ${NATIVE_CPP_PATH}/include
        ${TR_3RDPARTY_PATH}/boringssl/include
)