
cmake_minimum_required(VERSION 3.10.2)

#add_compile_options(-std=c++11)
add_compile_options(-Wno-narrowing)
add_definitions(-DNO_LOCALE_SUPPORT)



set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

SET(NATIVE_CPP_PATH ${CMAKE_CURRENT_SOURCE_DIR}/src/main/cpp)
SET(TR_3RDPARTY_PATH ${CMAKE_CURRENT_SOURCE_DIR}/../..)


#opencv
SET(OpenCV_DIR ${TR_3RDPARTY_PATH}/opencv/opencv_android/sdk/native/jni)
set(OpenCV_FOUND 1)
message("ANDROID_NDK_ABI_NAME-->${ANDROID_NDK_ABI_NAME}")
find_package(OpenCV REQUIRED)

message(STATUS "OpenCV library status:")
message(STATUS "version: ${OpenCV_VERSION}")
message(STATUS "libraries: ${OpenCV_LIBS}")
message(STATUS "include path: ${OpenCV_INCLUDE_DIRS}")


find_library(log-lib log)

add_library(au_cv_kit
        SHARED
        src/main/cpp/Mat.cpp
        src/main/cpp/converters.cpp
        src/main/cpp/CvHelper.cpp
        )


target_link_libraries(
        au_cv_kit
        ${log-lib}
        android
        jnigraphics
        dl
        z
        ${OpenCV_LIBS}
)


include_directories(
        ${NATIVE_CPP_PATH}
        ${NATIVE_CPP_PATH}/include
        ${OpenCV_INCLUDE_DIRS}
)