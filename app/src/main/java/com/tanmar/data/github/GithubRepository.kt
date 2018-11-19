package com.tanmar.data.github

import io.reactivex.Single
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(private val service: GithubService) : GithubRepository {

    override fun getGithubRepository(username: String) = service.getUserRepositories(username)

}

interface GithubRepository {
    fun getGithubRepository(username: String): Single<List<RepoEntity>>
}