
cmake_minimum_required(VERSION 3.22.1)

project("appforfun")

add_library(
        appforfun
        SHARED
        appforfun.cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        appforfun
        ${log-lib})

target_link_libraries(${CMAKE_PROJECT_NAME}
        # List libraries link to the target library
        android
        log)