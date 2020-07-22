#!/usr/bin/env bash

#build  BoringSSL
#see https://boringssl.googlesource.com/boringssl/+/HEAD/BUILDING.md


rm -r build
mkdir build
cd build

#"armeabi", "armeabi-v7a","arm64-v8a", "aarch64-linux-android-clang3.5")

CMAKE_VERSION=3.10.2.4988404
${ANDROID_HOME}/cmake/${CMAKE_VERSION}/bin/cmake \
    -DANDROID_ABI=arm64-v8a \
    -DCMAKE_TOOLCHAIN_FILE=${ANDROID_NDK}/build/cmake/android.toolchain.cmake \
    -DANDROID_NATIVE_API_LEVEL=21 \
    -DCMAKE_BUILD_TYPE=Release \
    -GNinja \
    ..


#    -DANDROID_NDK=${ANDROID_NDK} \
#    -DANDROID_TOOLCHAIN_NAME=arm-linux-androideabi-clang3.5 \


#ninja -j 8
cmake --build .


exit 0
