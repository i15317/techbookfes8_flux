package jp.digital.future.wearSupporter.flux.store

import android.arch.lifecycle.LiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import jp.digital.future.wearSupporter.data.entity.GithubSubmitToken
import jp.digital.future.wearSupporter.flux.Store
import jp.digital.future.wearSupporter.flux.dispatcher.GithubDispatcher
import jp.digital.future.wearSupporter.util.ext.toLiveData
import javax.inject.Inject

class GithubStore @Inject constructor(private val dispatcher: GithubDispatcher) : Store() {

    val loginCallbacks: LiveData<String> = dispatcher.onFetchGithubLogin
        .map { it.data }
        .observeOn(AndroidSchedulers.mainThread())
        .toLiveData()

    val token: LiveData<GithubSubmitToken> = dispatcher.onRequestGithubAccessToken
        .map { it.data }
        .observeOn(AndroidSchedulers.mainThread())
        .toLiveData()

}