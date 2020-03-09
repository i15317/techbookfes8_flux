//
// Created by Owner on 2019/03/26.
//
#pragma once

#include <string>

class APIManager {
public:
    APIManager();

    std::string getGithubAPIKey();

    std::string getGithubSecret();

private:
    static const int LOOP = 1000000;
};

