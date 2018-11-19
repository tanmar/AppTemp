package com.tanmar.data.github

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{user_name}/repos")
    fun getUserRepositories(@Path("user_name") userName: String): Single<List<RepoEntity>>

}