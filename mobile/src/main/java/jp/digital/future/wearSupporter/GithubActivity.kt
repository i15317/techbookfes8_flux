package jp.digital.future.wearSupporter

import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import jp.digital.future.wearSupporter.data.repository.IntentRepository
import jp.digital.future.wearSupporter.databinding.ActivityGithubBinding
import jp.digital.future.wearSupporter.di.StoreProvider
import jp.digital.future.wearSupporter.flux.creator.GithubActionCreator
import jp.digital.future.wearSupporter.flux.store.GithubStore
import jp.digital.future.wearSupporter.util.PARAMATER
import jp.digital.future.wearSupporter.util.ext.observe
import timber.log.Timber
import javax.inject.Inject

class GithubActivity : AppCompatActivity(), HasSupportFragmentInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var storeProvider: StoreProvider

    @Inject
    lateinit var actionCreator: GithubActionCreator
    private val store by lazy { storeProvider.get(this, GithubStore::class) }
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityGithubBinding>(
            this,
            R.layout.activity_github
        )
    }

    override fun supportFragmentInjector() = androidInjector
    external fun getGithubAPI(): String
    external fun getGithubSecret(): String

    /**
     * Todo 見直した方が良い？
     * Activityが自身の状態管理をするのは適切なのか？
     * Viewの状態を保持する変数はViewModelに格納する方が適切なのか？
     * この場合の管理する変数はActionCreatorがどのAPIにアクセスしたコードなのかを把握するための物
     */
    //外部インテントの発火状態を格納する
    private var mIntentflag = PARAMATER.DEFAULT_ACTION_FLAG

    companion object {
        init {
            //JNI呼び出し
            System.loadLibrary("wear-support")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textview = binding.textView
        val button = binding.actionButton
        binding.setOnOkClick {
            //Todo デバッグ用の暫定的な処理
            actionCreator.updateFlag(IntentRepository.GITHUB_INTENT_KEY)
        }
        store.loginCallbacks.observe(this) {
            it ?: return@observe
            if (it == IntentRepository.GITHUB_INTENT_KEY) {
                //複数回のアクション発火防止のため値を変える
                actionCreator.updateFlag("success")
                val client_id = getGithubAPI()
                //Todo URI生成（こ↑こ↓もModel層に投げよう）
                val uri =
                    Uri.parse("https://github.com/login/oauth/authorize?client_id=$client_id&redirect_uri=callback://github&scope=public_repo")
                //Todo Activitiyが状態管理を担うのはあまりよろしくなさそう
                this.mIntentflag = PARAMATER.GITHUB_API_ACTION_FLAG
                actionCreator.requestGithubLogin(this, uri)
            }
        }
        store.token.observe(this) {
            it ?: return@observe
            textview.text = it.access_token
        }
        actionCreator.fetchGithubLogin()
    }

    override fun onStart() {
        super.onStart()
        Timber.e("Constructor is called")
    }

    //外部インテントでAPIにアクセスした際はここに残る
    override fun onRestart() {
        super.onRestart()
        val intent = intent
        var code: String = ""
        try {
            code = intent.data.getQueryParameter("code")
        } catch (e: NullPointerException) {
            Timber.e(e)
        }
        when (this.mIntentflag) {
            PARAMATER.GITHUB_API_ACTION_FLAG -> {
                actionCreator.fetchRequestGithubAccessToken(getGithubAPI(), getGithubSecret(), code)
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        super.setIntent(intent)

    }
}