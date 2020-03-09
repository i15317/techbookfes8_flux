package jp.digital.future.wearSupporter.data.repository

import jp.digital.future.wearSupporter.data.api.GithubService
import javax.inject.Inject
import javax.inject.Named

//アクションの定義
class GitHubRepository @Inject constructor(@Named("GITHUB_API") val githubService: GithubService) {

    fun fetchUserRepos(userName: String) = githubService.getUserRepos(userName)

    fun fetchReadme(userName: String, repoName: String) = githubService.getReadme(userName, repoName)


}
