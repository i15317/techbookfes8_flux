package jp.digital.future.wearSupporter.di.modules

import android.arch.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import jp.digital.future.wearSupporter.di.PerActivityScope
import jp.digital.future.wearSupporter.di.ViewModelKey
import jp.digital.future.wearSupporter.flux.dispatcher.GithubDispatcher
import jp.digital.future.wearSupporter.flux.store.GithubStore

@Module
internal abstract class GithubModule {

    @Binds
    @IntoMap
    @ViewModelKey(GithubStore::class)
    abstract fun bindGithubStore(viewModel: GithubStore): ViewModel

}

@Module
internal class GithubDispatcherModule {

    @PerActivityScope
    @Provides
    fun provideMainDispatcher() = GithubDispatcher()
}
