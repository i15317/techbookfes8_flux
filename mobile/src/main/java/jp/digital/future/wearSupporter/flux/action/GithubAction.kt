package jp.digital.future.wearSupporter.flux.action

import jp.digital.future.wearSupporter.data.entity.GithubSubmitToken
import jp.digital.future.wearSupporter.data.entity.Repo
import jp.digital.future.wearSupporter.flux.Action

sealed class GithubAction<out T> : Action<T> {
    class RefreshRepo(override val data: List<Repo>) : GithubAction<List<Repo>>()
    class ShowRepoReadme(override val data: String) : GithubAction<String>()
    class DispatchGithubLogin(override val data: String) : GithubAction<String>()
    class RequestGithubAccessToken(override val data:GithubSubmitToken):GithubAction<GithubSubmitToken>()

}