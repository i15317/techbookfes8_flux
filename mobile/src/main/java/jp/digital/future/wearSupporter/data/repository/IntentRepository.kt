package jp.digital.future.wearSupporter.data.repository

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import jp.digital.future.wearSupporter.data.api.GithubLoginService
import javax.inject.Inject

class IntentRepository @Inject constructor(private val githubloginService: GithubLoginService) {
    //todo 新規サービス追加時はこ↑こ↓に追加
    companion object {
        const val GITHUB_INTENT_KEY = "github"
    }

    private val INTENT_FLAG = BehaviorSubject.createDefault("") // 外部インデントの識別キー

    fun fetchIntentflag(): Observable<String> {
        val flag: Observable<String> = INTENT_FLAG
        return flag
    }

    fun fetchUpdateFlag(key: String) {
        this.INTENT_FLAG.onNext(key)
    }

    fun fetchRequestGithubAccessToken(client_id: String, client_secret: String, code: String) =
        githubloginService.requestAccessToken(client_id, client_secret, code)
}