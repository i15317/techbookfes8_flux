package jp.digital.future.wearSupporter.flux.dispatcher

import io.reactivex.Flowable
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.processors.FlowableProcessor
import jp.digital.future.wearSupporter.flux.Dispatcher
import jp.digital.future.wearSupporter.flux.action.GithubAction

class GithubDispatcher : Dispatcher() {

    private val dispatcherRefreshRepo: FlowableProcessor<GithubAction.RefreshRepo> =
        BehaviorProcessor.create<GithubAction.RefreshRepo>().toSerialized()
    val onRefreshRepo: Flowable<GithubAction.RefreshRepo> = dispatcherRefreshRepo
    private val dispatcherShowRepoReadme: FlowableProcessor<GithubAction.ShowRepoReadme> =
        BehaviorProcessor.create<GithubAction.ShowRepoReadme>().toSerialized()
    val onShowRepoReadme: Flowable<GithubAction.ShowRepoReadme> = dispatcherShowRepoReadme

    private val dispatcherFetchGithubLogin: FlowableProcessor<GithubAction.DispatchGithubLogin> =
        BehaviorProcessor.create<GithubAction.DispatchGithubLogin>().toSerialized()
    val onFetchGithubLogin: Flowable<GithubAction.DispatchGithubLogin> = dispatcherFetchGithubLogin

    private val dispatcherRequestGithubToken: FlowableProcessor<GithubAction.RequestGithubAccessToken> =
        BehaviorProcessor.create<GithubAction.RequestGithubAccessToken>().toSerialized()
    val onRequestGithubAccessToken: Flowable<GithubAction.RequestGithubAccessToken> = dispatcherRequestGithubToken

    fun dispatch(action: GithubAction.RequestGithubAccessToken) {
        dispatcherRequestGithubToken.onNext(action)
    }
    fun dispatch(action: GithubAction.DispatchGithubLogin) {
        dispatcherFetchGithubLogin.onNext(action)
    }

    fun dispatch(action: GithubAction.RefreshRepo) {
        dispatcherRefreshRepo.onNext(action)
    }

    fun dispatch(action: GithubAction.ShowRepoReadme) {
        dispatcherShowRepoReadme.onNext(action)
    }

}
