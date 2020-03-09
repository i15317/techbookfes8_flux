package jp.digital.future.wearSupporter.data.api

import io.reactivex.Single
import jp.digital.future.wearSupporter.data.entity.Readme
import jp.digital.future.wearSupporter.data.entity.Repo
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/users/{username}/repos")
    fun getUserRepos(@Path("username") user: String): Single<List<Repo>>

    @GET("/repos/{owner}/{repo}/readme")
    fun getReadme(@Path("owner") owner: String, @Path("repo") repo: String): Single<Readme>


}