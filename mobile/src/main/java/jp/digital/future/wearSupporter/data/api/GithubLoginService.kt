package jp.digital.future.wearSupporter.data.api

import io.reactivex.Single
import jp.digital.future.wearSupporter.data.entity.GithubSubmitToken
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface GithubLoginService {
    //
//    @GET("/users/{username}/repos")
//    fun getUserRepos(@Query("username") user: String): Single<List<Repo>>
//
//    @GET("/repos/{owner}/{repo}/readme")
//    fun getReadme(@Query("owner") owner: String, @Query("repo") repo: String): Single<Readme>

    @Headers("Accept: application/json")
    @GET("/login/oauth/access_token")
    fun requestAccessToken(
        @Query("client_id") client_id: String, @Query("client_secret") client_secret: String, @Query(
            "code"
        ) code: String
    ): Single<GithubSubmitToken>

}