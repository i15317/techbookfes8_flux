//
// Created by Owner on 2019/03/26.
//


#include <jni.h>
#include<string>
#include "../header/main.hpp"


extern "C" JNIEXPORT jstring JNICALL Java_jp_digital_future_wearSupporter_GithubActivity_getGithubAPI
        (JNIEnv *env, jobject /* this */) {
    APIManager manager;
    std::string str = manager.getGithubAPIKey();
    return env->NewStringUTF(str.c_str());
}

extern "C" JNIEXPORT jstring JNICALL Java_jp_digital_future_wearSupporter_GithubActivity_getGithubSecret
        (JNIEnv *env, jobject /* this */) {
    APIManager manager;
    std::string str = manager.getGithubSecret();
    return env->NewStringUTF(str.c_str());
}

