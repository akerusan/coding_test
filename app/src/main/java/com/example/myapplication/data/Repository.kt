package com.example.myapplication.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    remoteDataSource: RemoteDataSource,
    localDataSource: LocalDataSource
) {
    val remote = remoteDataSource
    val local = localDataSource
}