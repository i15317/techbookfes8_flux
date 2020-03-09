# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.6.0)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
set(SRC_DIR src/main/cpp)


add_library( # Sets the name of the library.
        wear-support

        # Sets the library as a shared library.
        SHARED
        # Provides a relative path to your source file(s).
        ${SRC_DIR}/source/main.cpp
        ${SRC_DIR}/source/jni.cpp
        )

set(CMAKE_CXX_FLAGS
        "${CMAKE_CXX_FLAGS} -std=c++14  -fexceptions -Wall \
            -Wextra -Wno-unused-parameter \
            -DVK_NO_PROTOTYPES -DVK_USE_PLATFORM_ANDROID_KHR \
            -DGLM_FORCE_RADIANS")

if (${ANDROID_ABI} STREQUAL "armeabi-v7a")
    set(CMAKE_CXX_FLAGS} "${CMAKE_CXX_FLAGS} \
       -mhard-float -D_NDK_MATH_NO_SOFTFP=1 -mfloat-abi=hard")
    set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} \
       -Wl,--no-warn-mismatch")
endif ()

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

#target_include_directories(wear-support PRIVATE
#        ${COMMON_DIR}/s
#        )
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        wear-support
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})