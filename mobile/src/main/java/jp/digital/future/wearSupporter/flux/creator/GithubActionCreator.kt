package jp.digital.future.wearSupporter.flux.creator

import android.content.Context
import android.content.Intent
import android.net.Uri
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import jp.digital.future.wearSupporter.data.repository.GitHubRepository
import jp.digital.future.wearSupporter.data.repository.IntentRepository
import jp.digital.future.wearSupporter.data.repository.PreferenceRepository
import jp.digital.future.wearSupporter.di.PerActivityScope
import jp.digital.future.wearSupporter.flux.action.GithubAction
import jp.digital.future.wearSupporter.flux.dispatcher.GithubDispatcher
import timber.log.Timber
import javax.inject.Inject


@PerActivityScope
class GithubActionCreator @Inject constructor(
    private val dispatcher: GithubDispatcher,
    private val repository: GitHubRepository,
    private val intentRepository: IntentRepository,
    private val preferenceRepository: PreferenceRepository
) {
    fun fetchRequestGithubAccessToken(client_id: String, client_secret: String, code: String) =
        intentRepository.fetchRequestGithubAccessToken(client_id, client_secret, code)
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = {
                    Timber.d("AccessToken" + it.token_type)
                    dispatcher.dispatch(GithubAction.RequestGithubAccessToken(it))
                },
                onError = {
                    Timber.e(it)
                }
            )

    fun fetchGithubLogin() = intentRepository.fetchIntentflag()
        .subscribeOn(Schedulers.io())
        .subscribeBy(
            onNext = {
                dispatcher.dispatch(GithubAction.DispatchGithubLogin(it))
            },
            onError = {
                Timber.e(it)
            },
            onComplete = {

            })

    fun updateFlag(key: String) {
        intentRepository.fetchUpdateFlag(key)
    }

    fun requestGithubLogin(context: Context, uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }

}

